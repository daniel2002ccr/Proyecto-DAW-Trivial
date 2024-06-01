<template>
    <div class="container mt-5">
      <div class="card">
        <div class="card-header">
          <h4>Regístrate gratis ahora</h4>
        </div>
        <div class="card-body" v-if="!registroExitoso">
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
            <button type="button" class="btn btn-primary" @click="guardarUsuario">Registrarse</button>
          </div>
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
        registroExitoso: false
      }
    },
    methods: {
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
  .mensaje {
    color: green;
  }
  </style>