<template>
    <div>
      <h2>Inicio de Sesión</h2>
      <form @submit.prevent="login">
        <label for="username">Usuario:</label>
        <input type="text" v-model="username" required />
        <br />
        <label for="password">Contraseña:</label>
        <input type="password" v-model="password" required />
        <br />
        <button type="submit">Iniciar Sesión</button>
      </form>
      <form action="/admin">
        <input type="submit" class="botonInicioSesion" value="Acceso admin">
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        username: '',
        password: ''
      };
    },
    methods: {
      login() {
        fetch('http://localhost:8082/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ username: this.username, password: this.password })
        })
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Fallo en la autenticación');
          }
        })
        .then(data => {
          localStorage.setItem('token', data.token);
          this.$router.push('/admin');
        })
        .catch(error => {
          alert(error.message);
        });
      }
    }
  };
  </script>
  