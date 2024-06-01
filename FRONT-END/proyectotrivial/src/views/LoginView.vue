<template>
  <div v-if="isRegistered" id="containerLogIn" class="container">
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
      isLoggedIn: false
    }
  },
  created() {
   this.checkRegistrationStatus();
  },
  methods: {
    checkRegistrationStatus() {
      this.isRegistered = JSON.parse(localStorage.getItem('registroExitoso')) || false;
      if (!this.isRegistered) {
        this.$router.push('/signin');
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
<<<<<<< HEAD
      }
=======
        // Redirigir solo si el usuario no está registrado
       /* if (!this.isRegistered) {
            this.$router.push('/registrar');
        } */
>>>>>>> 7b5d2556b106f499204d3cb92ba473d4f176df4b
    }
  }
}
</script>

<<<<<<< HEAD
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
=======
  
<style scoped>
/* Estilos importados del primer archivo CSS */
body {
  display: grid;
  place-items: center;
  background: #AB47BC;
}

.container {
  margin-top: 10%;
  margin-left: 66%;
  color: #fff;
  width: 370px;
  height: 716px;
  border-radius: 10px;
  background: linear-gradient(145deg, #9a40a9, #b74cc9);
  box-shadow: 20px 20px 60px #913ca0, -20px -20px 60px #c552d8;
  border: none;
}

.tituloRegister {
  margin-top: 1%;
  padding-top: 1%;
  padding-right: 6%;
  text-align: center;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  font-size: 25px;
>>>>>>> 7b5d2556b106f499204d3cb92ba473d4f176df4b
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

<<<<<<< HEAD
.mensaje {
  margin-top: 20px;
  color: green;
}

.registrado {
  margin-top: 20px;
  color: rgb(61, 61, 61);
}
=======
.btn {
  border-radius: 10px;
  padding: 10px 20px;
  cursor: pointer;
  outline: none;
  border: none;
}

.btn-primary {
  background-color: #AB47BC;
  color: #fff;
}

.btn-primary:hover {
  background-color: #fff;
  color: #AB47BC;
}

.btn-block {
  width: 100%;
}

>>>>>>> 7b5d2556b106f499204d3cb92ba473d4f176df4b
</style>