<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h4>Añadir Usuario</h4>
            </div>
            <div class="card-body">
                <div class="mb-3">
                    <label for="">Nombre:</label>
                    <input type="text" v-model="model.usuario.userName" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="">Email:</label>
                    <input type="text" v-model="model.usuario.userEmail" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="">Contraseña:</label>
                    <input type="password" v-model="model.usuario.userPasswd" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="">Foto de perfil</label>
                    <input type="file" @change="handleFileUpload" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="">Cantidad:</label>
                    <input type="number" v-model="model.usuario.cantidad" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="">Activo:</label>
                    <input type="number" v-model="model.usuario.activo" class="form-control" min="0" max="1">
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
                    userName: '',
                    userEmail: '',
                    userPasswd: '',
                    userImage: null,
                    cantidad: '',
                    activo: 0
                }
            }
        }
    },
    methods: {
        handleFileUpload(event) {
            this.model.usuario.userImage = event.target.files[0];
        },
        validarActivo(activo) {
            return activo === 0 || activo === 1;
        },
        guardarUsuario() {
            if (!this.validarActivo(this.model.usuario.activo)) {
                alert('El valor de "Activo" debe ser 0 o 1.');
                return;
            }
            let formData = new FormData();
            formData.append('userName', this.model.usuario.userName);
            formData.append('userEmail', this.model.usuario.userEmail);
            formData.append('userPasswd', this.model.usuario.userPasswd);
            formData.append('userImage', this.model.usuario.userImage);
            formData.append('cantidad', this.model.usuario.cantidad);
            formData.append('activo', this.model.usuario.activo);

            axios.post('http://localhost:8080/trivial/v1/users', formData)
                .then(response => {
                    window.location.href = 'http://localhost:8081/login';
                })
                .catch(error => {
                });
        }
    }
}
</script>
<style scoped>
@media screen and (max-width: 425px) {
    .form-control {
        height: 45px;
    }
}
</style>