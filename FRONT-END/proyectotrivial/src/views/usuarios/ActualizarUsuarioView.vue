<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h4>Editar Usuario</h4>
            </div>
            <div class="card-body">
                <div class="mb-3">
                    <label for="">ID:</label>
                    <input type="text" v-model="model.usuario.userId" class="form-control" readonly>
                </div>
                <div class="mb-3">
                    <label for="">Nombre</label>
                    <input type="text" v-model="model.usuario.userName" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="">Email</label>
                    <input type="text" v-model="model.usuario.userEmail" class="form-control" required>
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
    props: ["userId", "userName", "userEmail", "userImage", "cantidad", "activo"],
    data() {
        return {
            model: {
                usuario: {
                    userId: this.userId,
                    userName: this.userName,
                    userEmail: this.userEmail,
                    userPasswd: '',
                    userImage: this.userImage,
                    cantidad: this.cantidad,
                    activo: this.activo
                }
            }
        }
    },
    methods: {
        handleFileUpload(event) {
            this.model.usuario.userImage = event.target.files[0];
        },
        actualizarUsuario() {
            axios.put(`http://localhost:8080/trivial/v1/users/${this.userId}`, this.model.usuario)
                .then(response => {
                    console.log(response)
                    alert('Usuario actualizado con éxito.');
                    this.$router.push('/admin');
                })
                .catch(error => {
                    console.error('Error al intentar actualizar el usuario:', error);
                });
        }
    }
}
</script>

<style>
.form-check-input[type=checkbox] {
    transform: scale(1.5);
}
</style>