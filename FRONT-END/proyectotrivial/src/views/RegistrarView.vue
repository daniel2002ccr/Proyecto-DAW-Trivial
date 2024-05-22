<template>
    <div id="containerSignIn" class="container">
      <h1 v-if="!registroExitoso" class="tituloRegister">¡¡Regístrate gratis ahora!!</h1>
      <p v-if="registroExitoso" class="registrado">Registro Realizado Correctamente</p>
      <form v-if="!registroExitoso" class="formularioRegister" @submit.prevent="register" :disabled="registroExitoso">
        <div class="form-group">
          <input type="email" v-model="email" class="form-control rounded" placeholder="Email" required>
        </div>
        <div class="form-group">
          <input type="text" v-model="username" class="form-control rounded" placeholder="Nombre de usuario" required>
        </div>
        <div class="form-group">
          <input type="password" v-model="password" class="form-control rounded" placeholder="Contraseña" required>
        </div>
        <button type="submit" class="btn btn-primary btn-block">Registrarse</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        email: '',
        username: '',
        password: '',
        imagen: '',
        cantidad: 0,
        registroExitoso: JSON.parse(localStorage.getItem('registroExitoso')) || false
      }
    },
    methods: {
        handleFileUpload(event) {
            this.model.usuario.userImage = event.target.files[0];
        },
      async register() {
        try {
          const response = await fetch('http://localhost:8080/trivial/v1/users', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              userEmail: this.email,
              userName: this.username,
              userPasswd: this.password,
              imagen: this.imagen,
              cantidad: 0,
              user_role: ["jugador"]
            })
          });
  
          if (response.ok) {
            alert('Usuario registrado correctamente.');
            this.email = '';
            this.username = '';
            this.password = '';
            this.imagen = '',
            this.cantidad = 0;
            this.registroExitoso = true;
            localStorage.setItem('registroExitoso', JSON.stringify(this.registroExitoso));
            this.$router.push('/login'); 
          } else {
            alert('Este usuario ya existe');
          }
        } catch (error) {
          alert(`Error: ${error.message || 'Error desconocido al intentar conectar con el servidor.'}`);
        }
      }
    }
  }
  </script>
  
  <style scoped>
  body {
    background-color: #F0F0F0;
  }
  
  #containerSignIn {
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
  