<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h4>Editar Usuario</h4>
            </div>
            <div class="card-body">
                <div class="mb-3">
                    <label for="">Nombre</label>
                    <input type="text" v-model="model.usuario.userName" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="">Email</label>
                    <input type="text" v-model="model.usuario.email" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="">Foto de perfil</label>
                    <input type="file" @change="handleFileUpload" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="">Cantidad</label>
                    <input type="number" v-model="model.usuario.cantidad" class="form-control">
                </div>
                <div class="form-check mb-3">
                    <input type="checkbox" class="form-check-input" id="activo" v-model="model.usuario.activo">
                    <label class="form-check-label" for="activo">Activo</label>
                </div>
                <div class="mb-3">
                    <button type="button" class="btn btn-primary" @click="actualizarUsuario">Actualizar usuario</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name: 'editarUsuario',
    data() {
        return {
            model: {
                usuario: {
                    userName: this.userName,
                    email: '',
                    cantidad: 0,
                    activo: true,
                    imagen: null
                }
            }
        }
    },
    methods: {
        handleFileUpload(event) {
            this.model.usuario.imagen = event.target.files[0]; // Asignar el archivo seleccionado al modelo de datos
        },
        actualizarUsuario() {
            axios.put('http://localhost:8080/trivial/v1/users', this.model.usuario)
                .then(response => {
                    console.log(response)
                    this.users = response.data;
                })
                .catch(error => {
                    console.error('Hubo un problema con la solicitud:', error);
                    
                });
            console.log(this.model.usuario); // Ejemplo de cómo acceder al modelo de usuario con la imagen
        }
    }
}
</script>

<style>
.form-check-input[type=checkbox] {
    transform: scale(1.5);
}
</style>