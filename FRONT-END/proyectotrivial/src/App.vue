<template>
  <nav>
    <router-link to="/"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" class="imagen">
        <path fill="#ffffff"
          d="M280.4 148.3L96 300.1V464a16 16 0 0 0 16 16l112.1-.3a16 16 0 0 0 15.9-16V368a16 16 0 0 1 16-16h64a16 16 0 0 1 16 16v95.6a16 16 0 0 0 16 16.1L464 480a16 16 0 0 0 16-16V300L295.7 148.3a12.2 12.2 0 0 0 -15.3 0zM571.6 251.5L488 182.6V44.1a12 12 0 0 0 -12-12h-56a12 12 0 0 0 -12 12v72.6L318.5 43a48 48 0 0 0 -61 0L4.3 251.5a12 12 0 0 0 -1.6 16.9l25.5 31A12 12 0 0 0 45.2 301l235.2-193.7a12.2 12.2 0 0 1 15.3 0L530.9 301a12 12 0 0 0 16.9-1.6l25.5-31a12 12 0 0 0 -1.7-16.9z" />
      </svg>Inicio</router-link> |
    <router-link to="/ranking">Ranking</router-link> |
    <router-link to="/cuenta">Cuenta</router-link>
  </nav>
  <router-view />
</template>

<style scoped>
label {
  margin-left: 2%;

}

.imagen {
  height: 20px;
  width: 20px;
  margin-right: 1%;
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

nav {
  padding-top: 1%;
  padding-left: 38%;
  background-color: #231f20;
  font-size: 25px;
}

a.router-link-active.router-link-exact-active {
  text-decoration: none;
  color: white;
  margin-top: 10px;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 250px;
  padding-bottom: 20px;
  font-size: 27px;
}

a {
  text-decoration: none;
  color: white;
  margin-top: 10px;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 20px;
  padding-bottom: 20px;
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
}

.botonInicioSesion {
  float: right;
  margin-right: 16%;
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 10px;
  padding-right: 10px;
  margin-top: 1%;
  color: white;
  border: 5px solid white;
  background-color: #231f20;
}

.botonInicioSesion:hover {
  float: right;
  margin-right: 16%;
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 10px;
  padding-right: 10px;
  margin-top: 1%;
  transition: 0.5s;
  cursor: pointer;
  color: #231f20;
  border: 5px solid #231f20;
  background-color: white;
}

.botonRegistro {
  float: right;
  margin-right: -300px;
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 10px;
  padding-right: 10px;
  margin-top: 1%;
  color: white;
  border: 5px solid white;
  background-color: #231f20;
}

.botonRegistro:hover {
  float: right;
  margin-right: -300px;
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 10px;
  padding-right: 10px;
  margin-top: 1%;
  cursor: pointer;
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