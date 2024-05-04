<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h4>Añadir Usuario</h4>
            </div>
            <div class="card-body">
                <div class="mb-3">
                    <label for="">Nombre</label>
                    <input type="text" v-model="model.usuario.nombre" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="">Email</label>
                    <input type="text" v-model="model.usuario.email" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="">Contraseña</label>
                    <input type="password" v-model="model.usuario.password" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="">Foto de perfil</label>
                    <input type="file" @change="handleFileUpload" class="form-control">
                </div>
                
                <div class="mb-3">
                    <button type="button" class="btn btn-primary" @click="guardarUsuario">Guardar usuario</button>
                </div>
            </div>
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
                    nombre: '',
                    email: '',
                    password: '',
                    imagen: null
                }
            }
        }
    },
    methods: {
        handleFileUpload(event) {
            this.model.usuario.imagen = event.target.files[0]; // Asignar el archivo seleccionado al modelo de datos
        },
        guardarUsuario() {
            axios.post('http://localhost:8080/trivial/v1/users', this.model.usuario)
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
<style></style>
