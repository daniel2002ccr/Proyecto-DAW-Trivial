<template>
  <div v-if="isRegistered" id="login-container" class="container">
    <h1 class="login-titulo">Iniciar Sesión</h1>
    <form class="login-form" @submit.prevent="iniciarSesion">
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" v-model="email" class="form-control" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="password" class="form-control" required>
      </div>
      <button type="submit" class="btn btn-primary btn-block btn-orange">Log In</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      email: '',
      password: '',
      isRegistered: false
    }
  },
  created() {
    this.seHaRegistrado();
  },
  methods: {
    seHaRegistrado() {
      this.isRegistered = JSON.parse(localStorage.getItem('isRegistered')) || false;
      if (!this.isRegistered) {
        this.$router.push('/registrar');
      }
    },
    iniciarSesion() {
      axios.get('http://localhost:8080/trivial/v1/users')
        .then(response => {
          const usuarios = response.data;
          let usuarioEncontrado = null;

          usuarios.forEach(usr => {
            if (usr.userEmail === this.email && usr.userPasswd === this.password) {
              usuarioEncontrado = usr;
            }
          });

          if (usuarioEncontrado) {
            alert('Se ha iniciado sesión correctamente');
            localStorage.setItem('user', JSON.stringify(usuarioEncontrado));
            localStorage.setItem('isLoggedIn', JSON.stringify(true));
            this.$router.push('/seleccion-dificultad');
          } else {
            alert('El email o la contraseña son incorrectas.');
          }
        })
        .catch(error => {
          console.error('Error al obtener los usuarios:', error);
          alert('Hubo un problema al iniciar sesión. Por favor, inténtelo de nuevo más tarde.');
        });
    }
  }
}
</script>

<style scoped>
#login-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 700px;
}
.login-titulo {
  color: rgb(221, 118, 34);
}
.login-form {
  width: 400px;
}

.btn-orange {
  background-color: rgb(228, 228, 228);
  border-color: black;
  color: rgb(238, 155, 1);
}
.btn-orange:hover {
  background-color: orange;
  color: black;
}
</style>
