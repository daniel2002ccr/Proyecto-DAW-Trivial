<template>
  <div class="container mt-5">
      <h2 class="text-center">Área de Administración</h2>
      <p class="text-center">Bienvenido al área de administración.</p>
      <form @submit.prevent="login" id="loginForm">
          <div class="mb-3">
              <label for="username" class="form-label">Usuario:</label>
              <input type="text" v-model="username" id="username" name="username" class="form-control" required>
          </div>
          <div class="mb-3">
              <label for="password" class="form-label">Contraseña:</label>
              <input type="password" v-model="password" id="password" name="password" class="form-control" required>
          </div>
          <div class="d-flex justify-content-center">
              <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
          </div>
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
