<template>
    <div class="seleccion-dificultad">
        <h1>Seleccione la dificultad:</h1>
        <ul>
            <li v-if="isLoggedIn"><router-link to="/easy" class="link">Fácil</router-link></li>
            <li v-if="isLoggedIn"><router-link to="/medium" class="link">Medio</router-link></li>
            <li v-if="isLoggedIn"><router-link to="/hard" class="link">Difícil</router-link></li>
            <li v-else><router-link to="/login" class="link">Iniciar sesión para seleccionar dificultad</router-link></li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'SeleccionDificultad',
    data() {
        return {
            isLoggedIn: JSON.parse(localStorage.getItem('isLoggedIn')) || false
        };
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

@media screen and (max-width: 425px) {
    h1 {
        font-size: 28px;
    }

    li {
        margin-left: -208px;
        margin-top: 74px;
    }

    li:hover {
        margin-left: -208px;
        margin-top: 74px;
    }
}
</style>