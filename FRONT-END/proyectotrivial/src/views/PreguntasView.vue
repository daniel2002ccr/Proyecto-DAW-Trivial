<template>
    <main class="bg-white container shadow-lg rounded-lg px-12 py-6">
        <div v-if="loading">
            Cargando...
        </div>
        <div v-else>
            <div v-if="isLoggedIn">
                <div v-if="questions && questions.length > 0">
                    <div v-for="(question, index) in questions" :key="index"
                        class="rounded-lg bg-gray-200 p-2 neumorph-1 text-center font-bold text-gray-800">
                        <h2>Pregunta nº {{ index + 1 }}</h2>
                        <p>Dificultad: {{ question.difficulty }}</p>
                        <p>Categoría: {{ unescapeHtml(question.category) }}</p>
                        <p class="bg-white p-5">Pregunta: {{ unescapeHtml(question.question) }}</p>
                        <p class="rounded-lg font-bold flex">Respuestas:</p>
                        <ul class="neumorph-1 bg-gray-100 p-2 rounded-lg mb-3">
                            <li v-for="(answer, index) in shuffledAnswers(index)" :key="index" :class="[
                                'rounded-lg',
                                'font-bold',
                                'flex',
                                'p-2',
                                isSelectedAnswer(question, answer) ? 'bg-green-700 text-white margin-bottom-1' : '',
                                !isSelectedAnswer(question, answer) && question.answered && !isCorrectAnswer(question, answer) ? 'bg-red-800 text-white margin-bottom-1' : '',
                                !isSelectedAnswer(question, answer) && question.answered && isCorrectAnswer(question, answer) ? 'bg-green-700 text-white margin-bottom-1' : '',
                                isSelectedAnswer(question, answer) || question.answered ? 'selected' : ''
                            ]" @click="selectAnswer(question, answer)" @mouseover="hoverEffect" @mouseout="resetHoverEffect"
                                :style="{ pointerEvents: question.answered ? 'none' : 'auto' }">
                                <span class="bg-gray-400 p-3 rounded-lg">{{ String.fromCharCode(65 + index) }}</span> <span
                                    class="flex items-center pl-6">{{ unescapeHtml(answer) }}</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div v-else>
                    <p>No se encontraron preguntas.</p>
                </div>
            </div>
            <div v-else>
                <p>Debes iniciar sesión para acceder a las preguntas.</p>
                <router-link to="/login">Iniciar sesión</router-link>
            </div>
        </div>
        <div class="marcador">{{ score }}</div>
    </main>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            loading: true,
            questions: null,
            score: 0,
            originalAnswersOrder: [],
            isLoggedIn: JSON.parse(localStorage.getItem('isLoggedIn')) || false
        };
    },
    mounted() {
        if (this.isLoggedIn) {
            this.fetchQuestions(this.$route.params.difficulty);
        } else {
            // Redirigir al usuario al login si no está logueado
            this.$router.push('/login');
        }
    },
    methods: {
        unescapeHtml(data) {
            return data.replace(/&amp;/g, '&')
                .replace(/&lt;/g, '<')
                .replace(/&gt;/g, '>')
                .replace(/&quot;/g, '"')
                .replace(/&#039;/g, "'")
                .replace(/&amp;/g, '&')
                .replace(/&eacute;/g, 'é')
                .replace(/&euml;/g, 'ë')
                .replace(/&deg;/g, '°')
                .replace(/&ouml;/g, 'ö')
                .replace(/&rsquo;/g, '’')
                .replace(/&iuml;/g, 'ï');
        },
        async fetchQuestions(difficulty) {
            this.loading = true;
            try {
                const response = await fetch(`https://opentdb.com/api.php?amount=10&difficulty=${difficulty}&type=multiple`);
                const data = await response.json();
                if (data.results.length) {
                    this.questions = data.results.map(question => {
                        const decodedQuestion = this.unescapeHtml(decodeURIComponent(question.question));
                        const decodedIncorrectAnswers = question.incorrect_answers.map(answer => this.unescapeHtml(decodeURIComponent(answer)));
                        const decodedCorrectAnswer = this.unescapeHtml(decodeURIComponent(question.correct_answer));
                        
                        return {
                            ...question,
                            question: decodedQuestion,
                            incorrect_answers: decodedIncorrectAnswers,
                            correct_answer: decodedCorrectAnswer
                        };
                    });
                    this.questions.forEach(question => {
                        const allAnswers = [...question.incorrect_answers, question.correct_answer];
                        this.shuffleArray(allAnswers);
                        this.originalAnswersOrder.push(allAnswers);
                    });
                } else {
                    this.questions = null;
                }
            } catch (error) {
                console.error('Error al obtener las preguntas:', error);
            } finally {
                this.loading = false;
            }
        },
        async saveScoreToUser() {
    try {
        const user = JSON.parse(localStorage.getItem('user'));
        const userId = user.userId;
        console.log(userId);

        
        const updatedUser = {
            ...user, 
            puntuacion: this.score + (user.puntuacion || 0) 
        };

        const response = await axios.put(`http://localhost:8080/trivial/v1/users/${userId}`, updatedUser);

        console.log('Puntuación del usuario actualizada correctamente:', response.data);
    } catch (error) {
        console.error('Error al actualizar la puntuación del usuario:', error);
    }
},
        async goToHome() {
            this.saveScoreToUser();
            await this.$router.push('/');
        },
        shuffledAnswers(index) {
            if (this.questions && this.questions.length > index) {
                return this.originalAnswersOrder[index].map(answer => this.unescapeHtml(decodeURIComponent(answer)));
            }
            return [];
        },
        shuffleArray(array) {
            for (let i = array.length - 1; i > 0; i--) {
                const j = Math.floor(Math.random() * (i + 1));
                [array[i], array[j]] = [array[j], array[i]];
            }
            return array;
        },
        selectAnswer(question, answer) {
            if (!question.answered) {
                if (this.isCorrectAnswer(question, answer)) {
                    this.score += 10;
                } else {
                    if (this.score >= 5) {
                        this.score -= 5;
                    } else {
                        this.score = 0;
                    }
                }
                question.answered = true;
                if (this.allQuestionsAnswered()) {
                    this.goToHome();
                }
            }
        },
        allQuestionsAnswered() {
            return this.questions && this.questions.every(question => question.answered);
        },
        isSelectedAnswer(question, answer) {
            return this.selectedAnswer === answer && this.isCorrectAnswer(question, answer);
        },
        isCorrectAnswer(question, answer) {
            return question.correct_answer === answer;
        },
        hoverEffect(event) {
            event.target.style.cursor = 'pointer';
        },
        resetHoverEffect(event) {
            event.target.style.cursor = 'auto';
        }
    }
};
</script>
<style scoped>
.nuevaCategoriaEstilos {
    margin-top: 3px;
    margin-left: 5px;
    font-size: 20px;
}

.nuevaCategoriaTitulo {
    font-size: 22.6px;
    font-family: monospace;
}

.nuevaCategoria {
    position: fixed;
    top: 50%;
    left: 9px;
    text-align: center;
    width: 17%;
    height: 21%;
    transform: translateY(-50%);
    background-color: rgba(0, 0, 0, 0.5);
    color: white;
    padding: 18px;
    border-radius: 5px;
    font-size: 32px;
    z-index: 9999;
}

h2 {
    font-family: cursive;
}

.no-scroll {
    overflow: hidden;
}

[type=button]:not(:disabled),
[type=reset]:not(:disabled),
[type=submit]:not(:disabled),
button:not(:disabled) {
    cursor: pointer;
    height: 50px;
    width: 125px;
    background-color: rgb(76, 206, 44);
    text-align: center;
    color: black;
    margin-top: 6%;
    font-weight: bold;
    font-size: 16px;
    border: 4px white solid;
}

.botonGuardarNombre-disabled {
    opacity: 0.5;
    cursor: not-allowed;
    height: 50px;
    width: 125px;
    background-color: red;
    text-align: center;
    color: black;
    margin-top: 6%;
    font-size: 16px;
    border: 4px black solid;
}

.botonGuardarNombre {
    height: 50px;
    width: 125px;
    background-color: white;
    text-align: center;
    color: black;
    margin-top: 6%;
    font-size: 16px;
    border: 4px black solid;
}

button.botonGuardarCategoria-disabled {
    opacity: 0.5;
    cursor: not-allowed;
    height: 50px;
    width: 100%;
    font-weight: bold;
    font-size: 16px;
    color: white;
    text-align: center;
    height: 50px;
    background-color: black;
    margin-left: -278px;
    margin-top: 70px;
}

button.botonGuardarCategoria {
    height: 50px;
    width: 100%;
    color: white;
    transition: all 0.6s;
    border: 4px solid white;
    background-color: black;
    margin-left: -278px;
    margin-top: 70px;
}

button.botonGuardarCategoria:hover {
    height: 50px;
    width: 100%;
    color: black;
    transition: all 0.6s;
    border: 4px solid black;
    background-color: white;
    margin-left: -278px;
    margin-top: 70px;
}

.nombreInput {
    height: 50px;
    width: 280px;
    text-align: center;
    font-size: 16px;
    border: none;
}

.categoriaInput {
    height: 41px;
    margin-left: 6px;
    width: 196px;
    color: black;
    text-align: center;
    font-size: 16px;
    border: none;
}

.mensajeMarcador {
    font-size: 16px;
    text-align: center;
    font-family: cursive;
}

.marcador {
    position: fixed;
    top: 50%;
    right: 9px;
    text-align: center;
    width: 17%;
    transform: translateY(-50%);
    background-color: rgba(0, 0, 0, 0.5);
    color: white;
    padding: 18px;
    border-radius: 5px;
    font-size: 32px;
    z-index: 9999;
}

main {
    margin-top: 2%;
}

li {
    list-style: none;
}

@media screen and (max-width: 425px) {
    .nuevaCategoria {
        display: none;
    }

    .marcador {
        display: block;
        position: relative;
        bottom: 0;
        top: 89%;
        margin-top: 351px;
        left: 0;
        width: 100%;
        z-index: 1000;
    }

}

.neumorph-1 {
    box-shadow: 6px 6px 18px rgba(0, 0, 0, 0.09), -6px -6px 18px #ffffff;
}

.rounded-lg {
    border-radius: 0.5rem;
}

.bg-gray-100 {
    background-color: #f3f4f6;
}

.p-2 {
    padding: 0.5rem;
}

.text-center {
    text-align: center;
}

.font-bold {
    font-weight: 700;
}

.text-gray-800 {
    color: #374151;
}

.bg-white {
    background-color: #ffffff;
}

.p-5 {
    padding: 1.25rem;
}

.bg-red-800 {
    background-color: #dc2626;
}

.rounded-lg {
    border-radius: 0.5rem;
}

.font-bold {
    font-weight: 700;
}

.flex {
    display: flex;
}

.p-2 {
    padding: 0.5rem;
}

.flex {
    display: flex;
}

.items-center {
    align-items: center;
}

.pl-6 {
    padding-left: 1.5rem;
}

.bg-gray-400 {
    background-color: #cbd5e0;
}

.p-3 {
    padding: 0.75rem;
}

.rounded-lg {
    border-radius: 0.5rem;
}

.bg-red-800 {
    background-color: #dc2626;
}

.rounded-lg {
    border-radius: 0.5rem;
}

.font-bold {
    font-weight: 700;
}

.flex {
    display: flex;
}

.p-2 {
    padding: 0.5rem;
}

.text-white {
    color: #ffffff;
}

.bg-green-700 {
    background-color: #047857;
}

.margin-bottom-1 {
    margin-bottom: 1%;
}

.bg-gray-800 {
    background-color: #4e5a6e;
}

.bg-gray-200 {
    background-color: #E0E0E0;
}

.bg-gray-300 {
    background-color: #D1D1D1;
}

.bg-gray-400 {
    background-color: #B0B0B0;
}

.bg-gray-500 {
    background-color: #9E9E9E;
}

.bg-gray-600 {
    background-color: #757575;
}
</style>