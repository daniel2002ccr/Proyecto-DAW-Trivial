<template>
  <div v-if="isRegistered" id="containerLogIn" class="container">
    <h1 class="tituloLogIn">Iniciar Sesión</h1>
    <form class="formularioLogin" @submit.prevent="login">
      <div class="form-group">
        <input type="email" v-model="email" class="form-control" placeholder="Email" required>
      </div>
      <div class="form-group">
        <input type="password" v-model="password" class="form-control" placeholder="Password" required>
      </div>
      <button type="submit" class="btn btn-primary btn-block naranja">Log In</button>
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
   this.checkRegistrationStatus();
  },
  methods: {
    checkRegistrationStatus() {
      this.isRegistered = JSON.parse(localStorage.getItem('isRegistered')) || false;
      if (!this.isRegistered) {
        this.$router.push('/registrar');
      }
    },
    async login() {
      const response = await fetch('http://localhost:8080/trivial/v1/users');
      const users = await response.json();

      const user = users.find(user => user.userEmail === this.email && user.userPasswd === this.password);

      if (user) {
        alert('Inicio de sesión correcto.');
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

#containerLogIn {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.tituloLogIn {
  color: rgb(221, 118, 34);
}
.formularioLogin {
  max-width: 400px;
  width: 100%;
}

.mensaje {
  margin-top: 20px;
  color: green;
}
.naranja {
  background-color: rgb(228, 228, 228);
  border-color: black;
  color: rgb(238, 155, 1);
}
.naranja:hover{
  background-color: orange;
  color: black;
}
</style>