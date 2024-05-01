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
                    <p>Type: {{ question.type }}</p>
                    <p>Dificultad: {{ question.difficulty }}</p>
                    <p>Categoría: {{ question.category }}</p>
                    <p class="bg-white p-5">Pregunta: {{ question.question }}</p>
                    <p class="rounded-lg font-bold flex">Respuestas:</p>
                    <ul class="neumorph-1 bg-gray-100 p-2 rounded-lg mb-3">
                        <li v-for="(answer, index) in shuffledAnswers(question)" :key="index" :class="[
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
    <div class="marcador">
        <p>Puntuación: {{ score }}</p>
        <p v-if="allQuestionsAnswered" class="mensajeMarcador">{{ getMessage(score) }}</p>
        <input type="text" v-model="nameInput" placeholder="Introduce tu nombre" v-if="showNameInput"
            class="nombreInput">
        <button @click="saveScoreAndName" :disabled="nameInput === ''" v-if="showNameInput"
            :class="{ 'botonGuardarNombre-disabled': nameInput === '', 'botonGuardarNombre': nameInput !== '' }">Guardar</button>
    </div>
    <div v-if="questions && questions.length > 0">
        <!-- Resto del contenido -->
    </div>
</template>

<script>
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
            players: []
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
                } else {
                    this.questions = null;
                }
            } catch (error) {
                console.error("Hubo un error al obtener las preguntas:", error);
            } finally {
                this.loading = false;
            }
        },
        showNamePrompt() {
            if (this.allQuestionsAnswered) {
                this.showNameInput = true;
                console.log("Todas las preguntas han sido respondidas");
            }
        },

        saveScoreAndName() {
            // Verifica que el nombre no esté vacío
            if (this.nameInput.trim() !== '') {
                // Guarda la puntuación y el nombre
                const data = {
                    name: this.nameInput,
                    score: this.score
                };
                //  this.players.push(data);
                //  this.showNameInput = false;
                //  this.$router.push('/ranking');
                console.log(this.players);
                this.$router.push({ path: '/ranking', query: { name: data.name, score: data.score } });
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
        shuffledAnswers(question) {
            const answers = [question.correct_answer, ...question.incorrect_answers];
            return this.shuffleArray(answers);
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
                    // Sumar 10 puntos si la respuesta es correcta
                    this.score += 10;
                } else {
                    // Restar 5 puntos si la respuesta es incorrecta, asegurándose de que el marcador no baje de 0
                    if (this.score >= 5) {
                        this.score -= 5;
                    } else {
                        this.score = 0;
                    }
                }
                this.selectedAnswer = answer;
                question.answered = true;

                if (this.allQuestionsAnswered) {
                    this.showNamePrompt();
                    console.log("Todas las preguntas han sido respondidas");
                }
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

.nombreInput {
    height: 50px;
    width: 280px;
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
    /* Aplica esquinas redondeadas */
}

.bg-gray-100 {
    background-color: #f3f4f6;
    /* Cambia el color de fondo a gris claro */
}

.p-2 {
    padding: 0.5rem;
    /* Agrega espacio de relleno de 0.5 rem */
}

.text-center {
    text-align: center;
    /* Centra el texto horizontalmente */
}

.font-bold {
    font-weight: 700;
    /* Aplica negrita */
}

.text-gray-800 {
    color: #374151;
    /* Cambia el color del texto a gris oscuro */
}

.bg-white {
    background-color: #ffffff;
    /* Cambia el color de fondo a blanco */
}

.p-5 {
    padding: 1.25rem;
    /* Agrega espacio de relleno de 1.25 rem */
}


.bg-red-800 {
    background-color: #dc2626;
    /* Cambia el color de fondo a rojo */
}

.rounded-lg {
    border-radius: 0.5rem;
    /* Aplica esquinas redondeadas */
}

.font-bold {
    font-weight: 700;
    /* Aplica negrita */
}

.flex {
    display: flex;
    /* Usa flexbox */
}

.p-2 {
    padding: 0.5rem;
    /* Agrega espacio de relleno de 0.5 rem */
}

.flex {
    display: flex;
    /* Usa flexbox */
}

.items-center {
    align-items: center;
    /* Centra los elementos verticalmente */
}

.pl-6 {
    padding-left: 1.5rem;
    /* Agrega espacio de relleno a la izquierda */
}

.bg-gray-400 {
    background-color: #cbd5e0;
    /* Cambia el color de fondo a gris */
}

.p-3 {
    padding: 0.75rem;
    /* Agrega espacio de relleno de 0.75 rem */
}

.rounded-lg {
    border-radius: 0.5rem;
    /* Aplica esquinas redondeadas */
}

.bg-red-800 {
    background-color: #dc2626;
    /* Cambia el color de fondo a rojo */
}

.rounded-lg {
    border-radius: 0.5rem;
    /* Aplica esquinas redondeadas */
}

.font-bold {
    font-weight: 700;
    /* Aplica negrita */
}

.flex {
    display: flex;
    /* Usa flexbox */
}

.p-2 {
    padding: 0.5rem;
    /* Agrega espacio de relleno de 0.5 rem */
}

.text-white {
    color: #ffffff;
    /* Cambia el color del texto a blanco */
}

.bg-green-700 {
    background-color: #047857;
    /* Cambia el color de fondo a verde oscuro */
}

.margin-bottom-1 {
    margin-bottom: 1%;
    /* Agrega relleno en la parte inferior del 2% */
}

.bg-gray-800 {
    background-color: #4e5a6e;
    /* Cambia el color de fondo a gris oscuro */
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