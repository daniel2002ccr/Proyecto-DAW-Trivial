<template>
  <div class="container mt-5">
    <div class="card">
      <div class="card-header">
        <h4>Regístrate gratis ahora</h4>
      </div>
      <div class="card-body" v-if="!registroExitoso">
        <form @submit.prevent="guardarUsuario" :disabled="registroExitoso">
          <div class="mb-3">
            <label for="">Nombre de usuario:</label>
            <input type="text" v-model="model.usuario.userName" class="form-control" required>
          </div>
          <div class="mb-3">
            <label for="">Email:</label>
            <input type="email" v-model="model.usuario.userEmail" class="form-control" required>
          </div>
          <div class="mb-3">
            <label for="">Contraseña:</label>
            <input type="password" v-model="model.usuario.userPasswd" class="form-control" required>
          </div>
          <div class="mb-3">
            <label for="">Foto de perfil</label>
            <input type="file" @change="handleFileUpload" class="form-control">
          </div>
          <div class="mb-3">
            <button type="submit" class="btn btn-primary">Registrarse</button>
          </div>
        </form>
      </div>
      <p v-else class="mensaje">Registro realizado correctamente</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'crearUsuario',
  data() {
    return {
      model: {
        usuario: {
          userName: '',
          userEmail: '',
          userPasswd: '',
          userImage: null,
        }
      },
      registroExitoso: JSON.parse(localStorage.getItem('registroExitoso')) || false
    }
  },
  created() {
    this.seHaRegistrado();
  },
  methods: {
    seHaRegistrado() {
      this.isRegistered = JSON.parse(localStorage.getItem('registroExitoso')) || false;
      if (!this.isRegistered) {
        this.$router.push('/registrar');
      }
    },
    handleFileUpload(event) {
      this.model.usuario.userImage = event.target.files[0];
    },
    guardarUsuario() {
      let formData = new FormData();
      formData.append('userName', this.model.usuario.userName);
      formData.append('userEmail', this.model.usuario.userEmail);
      formData.append('userPasswd', this.model.usuario.userPasswd);
      formData.append('userImage', this.model.usuario.userImage);
      formData.append('puntuacion', 0);
      formData.append('activo', 1);

      axios.post('http://localhost:8080/trivial/v1/users', formData)
        .then(response => {
          alert('Registro realizado con éxito.');
          this.registroExitoso = true;
          localStorage.setItem('isRegistered', JSON.stringify(true)); 
          localStorage.setItem('registroExitoso', JSON.stringify(this.registroExitoso));
          this.$router.push('/login'); 
        })
        .catch(error => {
          alert('Error al registrar el usuario.');
        });
    },
  },
}
</script>

<style scoped>
.container {
  color: orange;
}

.card-header {
  color: orange;
}

.card-body {
  color: orange;
}

.form-control {
  color: orange;
}

.btn-primary {
  background-color: black;
}

.btn-primary:hover {
  background-color: darkorange;
}

.mensaje {
  color: green;
}
</style>