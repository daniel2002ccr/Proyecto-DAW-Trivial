<template>
  <div>
    <h2>Área de Administración</h2>
    <p>Bienvenido al área de administración.</p>
    <form @submit.prevent="login" id="loginForm">
      <label for="username">Usuario:</label>
      <input type="text" v-model="username" id="username" name="username" required>
      <br>
      <label for="password">Contraseña:</label>
      <input type="password" v-model="password" id="password" name="password" required>
      <br>
      <button type="submit">Iniciar Sesión</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'admin',
  data() {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    login() {
      fetch('/admin', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          username: this.username,
          password: this.password
        })
      })
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Error de inicio de sesión');
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