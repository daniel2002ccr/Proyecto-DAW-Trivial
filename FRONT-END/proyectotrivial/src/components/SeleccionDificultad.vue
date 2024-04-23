<template>
    <div class="seleccion-dificultad">
        <h1>Seleccione la dificultad:</h1>
        <ul>
            <li><router-link to="/easy" class="link">Fácil</router-link></li>
            <li><router-link to="/medium" class="link">Medio</router-link></li>
            <li><router-link to="/hard" class="link">Difícil</router-link></li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'SeleccionDificultad',
    methods: {
        selectDifficulty(difficulty) {
            this.$emit('select-difficulty', difficulty);
        }
    }
};
</script>

<style scoped>
.link {
    color: #ffffff;
    text-decoration: none;
    padding-left: 362px;
    padding-right: 368px;
    transition: color 0.3s, background-color 0.3s;
}

.link:hover {
    color: #000000;
    padding-left: 362px;
    padding-right: 368px;
    text-align: center;
}

.seleccion-dificultad {
    background-color: black;
    height: 883px;
}

h1 {
    padding-top: 15%;
    text-align: center;
    color: white;
    font-size: 60px;
    font-family: monospace;
}

ul {
    text-align: center;
    list-style: none;
    padding: 0;
}

li {
    font-size: 40px;
    background-color: #000000;
    color: #ffffff;
    transition: background-color 0.3s, color 0.3s;
    padding: 10px;
    cursor: pointer;
    margin-left: 546px;
    margin-right: 546px;
}

li:hover {
    color: #000000;
    background-color: #ffffff;
    margin-left: 546px;
    margin-right: 546px;
}
</style>