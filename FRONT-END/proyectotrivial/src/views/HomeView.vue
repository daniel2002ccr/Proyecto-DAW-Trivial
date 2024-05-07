<template>
  <div class="home">
    <form action="/admin">
    <input type="submit" class="botonInicioSesion" value="Acceso admin" to="/admin">
  </form>
    <input type="button" class="botonRegistro" value="Crear cuenta">
    <img alt="Vue logo" src="../assets/logoTrivWorld.png" class="imagenLogo">
    <router-link to="/seleccion-dificultad" class="blinking-button">Iniciar partida</router-link>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  }
}
</script>

<style scoped>
button {
  font-family: 'Courier New', Courier, monospace;
}

.blinking-button {
  font-family: 'Courier New', Courier, monospace;
  margin-left: -430px;
  padding-top: 15px;
  padding-left: 100px;
  padding-right: 100px;
  padding-bottom: 15px;
  position: relative;
  animation: colorBlink 1s infinite alternate, flash 2s infinite;
  color: white;
  background-color: red;
  border: 5px solid white;
}

.blinking-button:hover {
  text-decoration: none;
}

@keyframes colorBlink {

  0%,
  100% {
    background-color: red;
  }

  50% {
    background-color: blue;
  }
}

@keyframes flash {

  0%,
  49% {
    opacity: 1;
    color: white;
    background-color: red;
  }

  50%,
  99% {
    opacity: 1;
    color: white;
    background-color: blueviolet;
  }
}

@keyframes flash {

  0%,
  49% {
    opacity: 1;
    color: white;
  }

  50%,
  99% {
    opacity: 1;
    color: white;
  }
}

/* .blinking-button::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  margin-left: -390px;
  padding-top: 15px;
  padding-left: 100px;
  padding-right: 100px;
  padding-bottom: 15px;
  animation: flash 2s infinite;
  color: white;
} */

label {
  margin-left: 2%;

}

input {

  width: 90%;
  margin: 0.5em 0;
  box-sizing: border-box;
  margin-left: 2%;
  padding-bottom: 6px;
  margin-bottom: 2%;
  font-size: 20px;
}

select {

  padding: 1px 2px;
  margin: 0.5em 0;
  box-sizing: border-box;
  margin-left: 2%;
  margin-bottom: 2%;
}

#activo {
  width: 10%;
  margin-left: -2%;
  margin-top: 2%;
}

#boton {
  text-align: center;
  color: white;
  background-color: black;
  width: 8%;
  padding-top: 3px;
  padding-bottom: 3px;
  padding-left: -10px;
  padding-right: -10px;
  font-size: 14px;
}

#fechaAnadido {
  width: 20%;
}

#fechaPedido {
  width: 20%;
}

.form {
  padding-top: 2%;
  margin-top: 5px;
  margin-bottom: 4%;
  margin: 5px 0 5px 0 auto;
  border: 0.2em inset black;
}

.hidden {
  display: none;
}

.agregarCarrito {
  margin-left: 2%;
}

#hgrande {
  text-align: center;
  color: red;
  font-size: 25px;
}

button {
  margin-bottom: 2%;
}

* {
  font-family: Arial, Helvetica, sans-serif;
}

body {

  display: flex;
  flex-direction: column;

}

.home {
  width: 100%;
  background-color: #231f20;
}



h2 {
  text-align: center;
  color: white;
  background-color: black;
  padding-top: 1%;
  padding-bottom: 1%;
}

h3 {
  text-align: center;
  color: red;

}

.container {
  width: 50%;
  margin: 0 auto;
}

.resultados {
  margin-top: 2em;
}

.menu {
  display: flex;
  justify-content: center;
  flex-direction: column;

}

.dropbtn {
  background-color: #ff8000;
  color: white;
  padding: 16px;
  font-size: 16px;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  font-weight: bold;
  border: none;
  cursor: pointer;
  transition: 0.5s;
  margin-right: 0.1em;
  text-align: center;
  margin-left: 41%;
  padding-left: 7%;
  padding-right: 7%;
  margin-top: 1%;
  margin-bottom: 1%;
}

button.dropbtn:nth-child(2) {
  padding-right: 125px;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {
  background-color: #f1f1f1
}

.dropdown:hover .dropdown-content {
  display: block;
}

.dropdown:hover .dropbtn {
  background-color: #3e8e41;
}

.imagenLogo {
  margin: auto;
  margin-left: 36%;
  margin-bottom: 500px;
}

.botonInicioSesion {
  float: right;
  width: 10%;
  margin-right: 2%;
  padding-top: 10px;
  padding-bottom: 10px;
  /* padding-left: 10px; */
  /* padding-right: 10px; */
  margin-top: -70px;
  color: white;
  border: 5px solid white;
  border-radius: 9999px;
  background-color: #231f20;
}

.botonInicioSesion:hover {
  float: right;
  width: 10%;
  margin-right: 4%;
  padding-top: 10px;
  padding-bottom: 10px;
  /* padding-left: 10px; */
  /* padding-right: 10px; */
  margin-top: -70px;
  transition: 0.5s;
  cursor: pointer;
  color: #231f20;
  border: 5px solid #231f20;
  border-radius: 9999px;
  background-color: white;
}

.botonRegistro {
  float: right;
  width: 12%;
  /* margin-right: 100px; */
  padding-top: 10px;
  padding-bottom: 10px;
  /* padding-left: 10px; */
  /* padding-right: 10px; */
  margin-top: -70px;
  color: white;
  border: 5px solid white;
  border-radius: 9999px;
  background-color: #231f20;
}

.botonRegistro:hover {
  float: right;
  width: 12%;
  /* margin-right: 100px; */
  padding-top: 10px;
  padding-bottom: 10px;
  /* padding-left: 10px; */
  /* padding-right: 10px; */
  margin-top: -70px;
  transition: 0.5s;
  cursor: pointer;
  color: #231f20;
  border: 5px solid #231f20;
  border-radius: 9999px;
  background-color: white;
}

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;

}

td,
th {
  border: 1px solid #dddddd;
  text-align: center;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
  text-align: center;
}
</style>