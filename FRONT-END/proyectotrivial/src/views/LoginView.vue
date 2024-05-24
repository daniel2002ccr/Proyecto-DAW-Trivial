<template>
    <div v-if="!isRegistered" id="containerLogIn" class="container">
      <h1 class="tituloRegister">Log In</h1>
      <form class="formularioRegister" @submit.prevent="login">
        <div class="form-group">
          <input type="email" v-model="email" class="form-control rounded" placeholder="Email" required>
        </div>
        <div class="form-group">
          <input type="password" v-model="password" class="form-control rounded" placeholder="Password" required>
        </div>
        <button type="submit" class="btn btn-primary btn-block">Log In</button>
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
      this.isRegistered = JSON.parse(localStorage.getItem('registroExitoso')) || false;
     if (!this.isRegistered) {
        this.$router.push('/registrar');
      }
    },
    async login() {
    this.checkRegistrationStatus(); // Asegurarse de que isRegistered esté actualizado

    const response = await fetch('http://localhost:8080/trivial/v1/users');
    const users = await response.json();

    const user = users.find(user => user.userEmail === this.email && user.userPasswd === this.password);

    if (user) {
        alert('Inicio de sesión correcto.');
        localStorage.setItem('user', JSON.stringify(user)); 
        localStorage.setItem('isLoggedIn', JSON.stringify(true)); 
        this.$router.push('/seleccion-dificultad');
    } else {
        alert('Credenciales incorrectas.');
        // Redirigir solo si el usuario no está registrado
        if (!this.isRegistered) {
            this.$router.push('/registrar');
        }
    }
}
  }
}
</script>

  
  <style scoped>
  body {
    background-color: #F0F0F0;
  }
  
  #containerLogIn {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }
  
  .tituloRegister {
    color: blue;
  }
  
  .formularioRegister {
    max-width: 400px;
    width: 100%;
  }
  
  .rounded {
    border-radius: 10px;
    padding: 10px;
    margin-bottom: 10px;
  }
  
  .register {
    border-radius: 10px;
    padding: 10px 20px;
    cursor: pointer;
  }
  
  .mensaje {
    margin-top: 20px;
    color: green;
  }
  
  .registrado {
    margin-top: 20px;
    color: rgb(61, 61, 61);
  }
  </style>
  