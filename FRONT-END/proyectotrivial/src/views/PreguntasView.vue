<template>
    <main class="bg-white container shadow-lg rounded-lg px-12 py-6">
        <div v-if="loading">
            Cargando...
        </div>
        <div v-else>
            <div v-if="questions && questions.length > 0">
                <div v-for="(question, index) in questions" :key="index">
                    <template v-if="question.type === 'multiple'">
                        <h2>Pregunta nº {{ index + 1 }}</h2>
                        <p>Type: {{ question.type }}</p>
                        <p>Dificultad: {{ question.difficulty }}</p>
                        <p>Categoría: {{ question.category }}</p>
                        <p class="bg-white p-5">Pregunta: {{ question.question }}</p>
                        <p class="bg-white rounded-lg font-bold flex">Respuestas:</p>
                        <ul>
                            <!-- Respuestas -->
                            <li v-for="(answer, index) in [question.correct_answer, ...question.incorrect_answers]"
                                :key="index">
                                {{ String.fromCharCode(65 + index) }} - - > {{ answer }}
                            </li>
                        </ul>
                        <p>Respuesta correcta: </p>
                        <ul>
                            <!-- Respuesta correcta -->
                            <li>
                                {{ String.fromCharCode(65) }} - - > {{ question.correct_answer }}
                            </li>
                        </ul>
                        <p>Respuestas incorrectas:</p>
                        <ul>
                            <!-- Respuestas incorrectas -->
                            <li v-for="(answer, index) in question.incorrect_answers" :key="'incorrect_' + index">
                                {{ String.fromCharCode(65 + index + 1) }} - - > {{ answer }}
                            </li>
                        </ul>
                    </template>
                </div>
            </div>
            <div v-else>
                <p>No se encontraron preguntas.</p>
            </div>
        </div>
    </main>
</template>


<script>
export default {
    data() {
        return {
            loading: true,
            questions: null
        };
    },
    mounted() {
        this.fetchQuestions();
    },
    methods: {
        async fetchQuestions() {
            try {
                const response = await fetch("https://opentdb.com/api.php?amount=10&difficulty=medium");
                const data = await response.json();
                this.questions = data.results;
                this.loading = false;
            } catch (error) {
                console.error("Hubo un error al obtener las preguntas:", error);
            }
        }
    }
};
</script>
<style scoped>
li {

    list-style: none;
}
</style>