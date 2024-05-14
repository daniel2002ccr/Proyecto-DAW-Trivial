<template>
  <div class="home">
    <form action="/admin">
      <input type="submit" class="botonInicioSesion" value="Acceso admin" to="/admin">
    </form>
    <input type="button" class="botonRegistro" value="Crear cuenta">
    <img alt="Vue logo" src="../assets/logoTrivWorld.png" class="imagenLogo">
    <router-link to="/seleccion-dificultad" class="blinking-button">Iniciar partida</router-link>
  </div>
  <footer class="footer">
    <div class="social">
      <a href="https://wa.me/112233445" target="_blank" class="social-link">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512">
          <path
            d="M380.9 97.1C339 55.1 283.2 32 223.9 32c-122.4 0-222 99.6-222 222 0 39.1 10.2 77.3 29.6 111L0 480l117.7-30.9c32.4 17.7 68.9 27 106.1 27h.1c122.3 0 224.1-99.6 224.1-222 0-59.3-25.2-115-67.1-157zm-157 341.6c-33.2 0-65.7-8.9-94-25.7l-6.7-4-69.8 18.3L72 359.2l-4.4-7c-18.5-29.4-28.2-63.3-28.2-98.2 0-101.7 82.8-184.5 184.6-184.5 49.3 0 95.6 19.2 130.4 54.1 34.8 34.9 56.2 81.2 56.1 130.5 0 101.8-84.9 184.6-186.6 184.6zm101.2-138.2c-5.5-2.8-32.8-16.2-37.9-18-5.1-1.9-8.8-2.8-12.5 2.8-3.7 5.6-14.3 18-17.6 21.8-3.2 3.7-6.5 4.2-12 1.4-32.6-16.3-54-29.1-75.5-66-5.7-9.8 5.7-9.1 16.3-30.3 1.8-3.7 .9-6.9-.5-9.7-1.4-2.8-12.5-30.1-17.1-41.2-4.5-10.8-9.1-9.3-12.5-9.5-3.2-.2-6.9-.2-10.6-.2-3.7 0-9.7 1.4-14.8 6.9-5.1 5.6-19.4 19-19.4 46.3 0 27.3 19.9 53.7 22.6 57.4 2.8 3.7 39.1 59.7 94.8 83.8 35.2 15.2 49 16.5 66.6 13.9 10.7-1.6 32.8-13.4 37.4-26.4 4.6-13 4.6-24.1 3.2-26.4-1.3-2.5-5-3.9-10.5-6.6z" />
        </svg>
      </a>

      <a href="https://www.instagram.com/triworld" target="_blank" class="social-link">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512">
          <path
            d="M224.1 141c-63.6 0-114.9 51.3-114.9 114.9s51.3 114.9 114.9 114.9S339 319.5 339 255.9 287.7 141 224.1 141zm0 189.6c-41.1 0-74.7-33.5-74.7-74.7s33.5-74.7 74.7-74.7 74.7 33.5 74.7 74.7-33.6 74.7-74.7 74.7zm146.4-194.3c0 14.9-12 26.8-26.8 26.8-14.9 0-26.8-12-26.8-26.8s12-26.8 26.8-26.8 26.8 12 26.8 26.8zm76.1 27.2c-1.7-35.9-9.9-67.7-36.2-93.9-26.2-26.2-58-34.4-93.9-36.2-37-2.1-147.9-2.1-184.9 0-35.8 1.7-67.6 9.9-93.9 36.1s-34.4 58-36.2 93.9c-2.1 37-2.1 147.9 0 184.9 1.7 35.9 9.9 67.7 36.2 93.9s58 34.4 93.9 36.2c37 2.1 147.9 2.1 184.9 0 35.9-1.7 67.7-9.9 93.9-36.2 26.2-26.2 34.4-58 36.2-93.9 2.1-37 2.1-147.8 0-184.8zM398.8 388c-7.8 19.6-22.9 34.7-42.6 42.6-29.5 11.7-99.5 9-132.1 9s-102.7 2.6-132.1-9c-19.6-7.8-34.7-22.9-42.6-42.6-11.7-29.5-9-99.5-9-132.1s-2.6-102.7 9-132.1c7.8-19.6 22.9-34.7 42.6-42.6 29.5-11.7 99.5-9 132.1-9s102.7-2.6 132.1 9c19.6 7.8 34.7 22.9 42.6 42.6 11.7 29.5 9 99.5 9 132.1s2.7 102.7-9 132.1z" />
        </svg>
      </a>

      <a href="https://twitter.com/triworld" target="_blank" class="social-link">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
          <path
            d="M459.4 151.7c.3 4.5 .3 9.1 .3 13.6 0 138.7-105.6 298.6-298.6 298.6-59.5 0-114.7-17.2-161.1-47.1 8.4 1 16.6 1.3 25.3 1.3 49.1 0 94.2-16.6 130.3-44.8-46.1-1-84.8-31.2-98.1-72.8 6.5 1 13 1.6 19.8 1.6 9.4 0 18.8-1.3 27.6-3.6-48.1-9.7-84.1-52-84.1-103v-1.3c14 7.8 30.2 12.7 47.4 13.3-28.3-18.8-46.8-51-46.8-87.4 0-19.5 5.2-37.4 14.3-53 51.7 63.7 129.3 105.3 216.4 109.8-1.6-7.8-2.6-15.9-2.6-24 0-57.8 46.8-104.9 104.9-104.9 30.2 0 57.5 12.7 76.7 33.1 23.7-4.5 46.5-13.3 66.6-25.3-7.8 24.4-24.4 44.8-46.1 57.8 21.1-2.3 41.6-8.1 60.4-16.2-14.3 20.8-32.2 39.3-52.6 54.3z" />
        </svg>
      </a>

      <a href="https://www.facebook.com/triworld" target="_blank" class="social-link">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
          <path
            d="M504 256C504 119 393 8 256 8S8 119 8 256c0 123.8 90.7 226.4 209.3 245V327.7h-63V256h63v-54.6c0-62.2 37-96.5 93.7-96.5 27.1 0 55.5 4.8 55.5 4.8v61h-31.3c-30.8 0-40.4 19.1-40.4 38.7V256h68.8l-11 71.7h-57.8V501C413.3 482.4 504 379.8 504 256z" />
        </svg>
      </a>

      <a href="https://www.youtube.com/user/triworld" target="_blank" class="social-link">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512">
          <path
            d="M549.7 124.1c-6.3-23.7-24.8-42.3-48.3-48.6C458.8 64 288 64 288 64S117.2 64 74.6 75.5c-23.5 6.3-42 24.9-48.3 48.6-11.4 42.9-11.4 132.3-11.4 132.3s0 89.4 11.4 132.3c6.3 23.7 24.8 41.5 48.3 47.8C117.2 448 288 448 288 448s170.8 0 213.4-11.5c23.5-6.3 42-24.2 48.3-47.8 11.4-42.9 11.4-132.3 11.4-132.3s0-89.4-11.4-132.3zm-317.5 213.5V175.2l142.7 81.2-142.7 81.2z" />
        </svg>
      </a>
    </div>
    <div class="legal">
      <router-link to="/protecciondatos">Política de Protección de Datos</router-link>
      <router-link to="/cookies">Política de Cookies</router-link>
      <router-link to="/aviso-legal">Aviso legal</router-link>
    </div>
  </footer>
</template>

<script>

export default {
  name: 'HomeView'
}
</script>

<style scoped>
.social-link {
  width: 30px;
  height: 40px;
}

.footer {
  color: white;
  padding: 2px;
  position: absolute;
  background-color: orange;
  bottom: 0px;
  width: 100%;
  height: 54px;
}

.social {
  display: flex;
  width: 50%;
  float: left;
}

.social-link {
  color: white;
  font-size: 20px;
  margin-top: 9px;
  margin-left: 33px;
}

.social-link:hover {
  cursor: pointer;
}

.legal {
  font-size: 14px;
  font-size: 14px;
  width: 50%;
  float: right;
  margin-top: 15px;
  padding-left: 294px;

  a {
    color: black;
    font-weight: bold;
    padding: 33px;
  }
}

.legal a:hover {
  text-decoration: none;
}

.no-scroll {
  overflow: hidden;
}

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
  padding-top: 10px;
  padding-bottom: 10px;
  margin-top: -70px;
  color: white;
  border: 5px solid white;
  border-radius: 9999px;
  background-color: #231f20;
}

.botonRegistro:hover {
  float: right;
  width: 12%;
  padding-top: 10px;
  padding-bottom: 10px;
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

@media screen and (max-width: 425px) {
  .nav {}

  .home {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
  }

  .imagenLogo {
    width: 100%;
    margin-bottom: 300px;
    margin-right: 143px;
  }

  a.blinking-button {
    margin: auto;
    margin-top: -358px;
    margin-bottom: 400px;
  }

  .content {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .botonInicioSesion {
    height: 57px;
    width: 200px;
    margin-left: 224px;
    margin-top: -62px;
  }

  .botonInicioSesion:hover {
    height: 57px;
    width: 200px;
    margin-left: 224px;
    margin-top: -62px;
  }

  .botonRegistro {
    height: 57px;
    width: 200px;
    margin-right: 227px;
    margin-left: 14px;
    margin-top: -65px;
  }

  .botonRegistro:hover {
    height: 57px;
    width: 200px;
    margin-right: 227px;
    margin-left: 14px;
    margin-top: -65px;
  }

  .footer {
    margin-top: auto;
    text-align: center;
  }

  .social {
    width: 425px;
    margin-left: 40px;
  }

  .legal {}
}
</style>