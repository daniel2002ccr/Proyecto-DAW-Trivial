<template>
  <div v-if="isRegistered" id="login-container" class="container">
    <h1 class="login-titulo">Iniciar Sesión</h1>
    <form class="login-form" @submit.prevent="login">
      <div class="form-group">
        <input type="email" v-model="email" class="form-control" placeholder="Email" required>
      </div>
      <div class="form-group">
        <input type="password" v-model="password" class="form-control" placeholder="Password" required>
      </div>
      <button type="submit" class="btn btn-primary btn-block btn-orange">Log In</button>
    </form>
  </div>
</template>

<script>
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
    async login() {
      const respuesta = await fetch('http://localhost:8080/trivial/v1/users');
      const users = await respuesta.json();

      const user = users.find(user => user.userEmail === this.email && user.userPasswd === this.password);

      if (user) {
        alert('Se ha iniciado sesión correctamente');
        localStorage.setItem('user', JSON.stringify(user)); 
        localStorage.setItem('isLoggedIn', JSON.stringify(true)); 
        this.$router.push('/seleccion-dificultad');
      } else {
        alert('El email o la contraseña son incorrectas.');
      }
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
