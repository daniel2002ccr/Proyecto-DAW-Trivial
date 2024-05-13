<template>
    <main class="bg-white container shadow-lg rounded-lg px-12 py-6">
        <div v-if="loading">
            Cargando...
        </div>
        <div v-else>
            <div v-if="questions && questions.length > 0">
                <div v-for="(question, index) in questions" :key="index"
                    class="rounded-lg bg-gray-200 p-2 neumorph-1 text-center font-bold text-gray-800">
                    <h2>Pregunta nº {{ index + 1 }}</h2>
                    <p>Dificultad: {{ question.difficulty }}</p>
                    <p>Categoría: {{ question.category }}</p>
                    <p class="bg-white p-5">Pregunta: {{ question.question }}</p>
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
                                class="flex items-center pl-6">{{ answer }}</span>
                        </li>
                    </ul>
                    <p>Respuesta correcta: </p>
                    <ul class="neumorph-1 bg-gray-100 p-2 rounded-lg mb-3">
                        <li class="bg-green-700 rounded-lg font-bold flex p-2 text-white">
                            <span class="bg-gray-800 p-3 rounded-lg">{{ String.fromCharCode(65) }}</span> <span
                                class="flex items-center pl-6">{{ question.correct_answer }}</span>
                        </li>
                    </ul>
                    <p>Respuestas incorrectas:</p>
                    <ul class="neumorph-1 bg-gray-100 p-2 rounded-lg mb-3">
                        <li v-for="(answer, index) in question.incorrect_answers" :key="'incorrect_' + index"
                            class="bg-red-800 rounded-lg font-bold flex p-2 text-white margin-bottom-1">
                            <span class="bg-gray-800 p-3 rounded-lg">{{ String.fromCharCode(65 + index + 1) }}</span>
                            <span class="flex items-center pl-6">{{ answer }}</span>
                        </li>
                    </ul>
                </div>
            </div>
            <div v-else>
                <p>No se encontraron preguntas.</p>
            </div>
        </div>
    </main>
    <div class="nuevaCategoria">
        <h3 class="nuevaCategoriaTitulo">Añadir nueva categoría:</h3>
        <div class="flex flex-col space-y-2">
            <label for="newCategory" class="nuevaCategoriaEstilos">Nombre:</label>
            <input type="text" id="newCategory" v-model="newCategory" class="categoriaInput">
            <button @click="addCategory"
                :class="{ 'botonGuardarCategoria-disabled': newCategory.trim() === '', 'botonGuardarCategoria': newCategory.trim() !== '' }"
                :disabled="newCategory.trim() === ''">Añadir</button>
        </div>
    </div>
    <div class="marcador">
        <p>Puntuación: {{ score }}</p>
        <p v-if="allQuestionsAnswered" class="mensajeMarcador">{{ getMessage(score) }}</p>
        <input type="text" v-model="nameInput" placeholder="Introduce tu nombre" v-if="showNameInput"
            class="nombreInput">
        <button @click="saveScoreAndName" :disabled="nameInput === ''" v-if="showNameInput"
            :class="{ 'botonGuardarNombre-disabled': nameInput === '', 'botonGuardarNombre': nameInput !== '' }">Guardar</button>
    </div>
    <div v-if="questions && questions.length > 0">
    </div>
</template>

<script>
import axios from 'axios';
import SeleccionDificultad from '../components/SeleccionDificultad.vue';

export default {
    props: ['difficulty'],
    components: {
        SeleccionDificultad
    },
    data() {
        return {
            loading: true,
            questions: null,
            selectedAnswer: null,
            score: 0,
            nameInput: '',
            showNameInput: false,
            players: [],
            answeredPlayers: [],
            originalAnswersOrder: [],
            newCategory: ''
        };
    },
    mounted() {
        this.fetchQuestions(this.difficulty);
    },
    computed: {
        allQuestionsAnswered() {
            return this.questions && this.questions.every(question => question.answered);
        }
    },
    methods: {
        async fetchQuestions(difficulty) {
            this.loading = true;
            try {
                const response = await fetch(`https://opentdb.com/api.php?amount=10&difficulty=${difficulty}&type=multiple`);
                const data = await response.json();
                if (data.results.length) {
                    this.questions = data.results;
                    this.questions.forEach(question => {
                        const allAnswers = [...question.incorrect_answers, question.correct_answer];
                        this.shuffleArray(allAnswers);
                        this.originalAnswersOrder.push(allAnswers);
                    });
                }
                else {
                    this.questions = null;
                }
            } catch (error) {
                console.error("Hubo un error al obtener las preguntas:", error);
            } finally {
                this.loading = false;
            }
        },
        async addCategory() {
            try {
                const response = await axios.post('http://localhost:8080/trivial/v1/categorias', {
                    nombre: this.newCategory,
                    activo: 0
                });
                console.log(response.data);
                alert('Categoría agregada con éxito.');
                this.newCategory = '';
            } catch (error) {
                console.error('Error al agregar la categoría:', error);
                alert('Error al agregar la categoría. Por favor, inténtalo de nuevo.');
            }
        },
        showNamePrompt() {
            if (this.allQuestionsAnswered) {
                this.showNameInput = true;
                console.log("Todas las preguntas han sido respondidas");
            }
        },
        saveScoreAndName() {
            if (this.nameInput.trim() !== '') {
                const data = {
                    name: this.nameInput,
                    score: this.score,
                    difficulty: this.difficulty,
                    answered: true
                };
                this.players.push(data);
                this.$router.push({ path: '/ranking', query: { name: data.name, score: data.score, difficulty: data.difficulty } });
            }
        },
        getMessage(score) {
            if (score >= 90) {
                return "¡Excelente trabajo! ¡Eres un experto!";
            } else if (score >= 70) {
                return "¡Muy bien hecho! ¡Estás en el camino correcto!";
            } else if (score >= 50) {
                return "¡Buen trabajo! ¡Sigue así!";
            } else if (score >= 20) {
                return "¡Puedes mejorar! ¡Sigue practicando!";
            } else {
                return "¡Necesitas practicar más! ¡No te rindas!";
            }
        },
        shuffledAnswers(index) {
            if (this.questions && this.questions.length > index) {
                return this.originalAnswersOrder[index];
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
        answerClass(question, answer) {
            if (question.answered) {
                if (this.isCorrectAnswer(question, answer)) {
                    return 'bg-green-700 text-white margin-bottom-1';
                } else if (this.selectedAnswer === answer) {
                    return 'bg-red-800 text-white margin-bottom-1';
                }
            }
            return '';
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
                this.showNamePrompt();
            }
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