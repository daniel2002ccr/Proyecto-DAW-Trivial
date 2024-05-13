<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h4>Editar Usuario</h4>
            </div>
            <div class="card-body">
                <div class="mb-3">
                    <label for="">ID:</label>
                    <input type="text" :value="userId" class="form-control" readonly>
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
                    <input type="file" @change="handleFileUpload" class="form-control" id="botonInput">
                </div>
                <div class="mb-3">
                    <label for="">Cantidad</label>
                    <input type="number" v-model="model.usuario.cantidad" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="">Activo:</label>
                    <input type="number" v-model="model.usuario.activo" class="form-control" min="0" max="1">
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
    props: {
        userName: String,
        userEmail: String,
        userImage: String,
        cantidad: Number,
        activo: String
    },
    data() {
        return {
            userId: null,
            model: {
                usuario: {
                    userName: '',
                    userEmail: '',
                    userImage: '',
                    cantidad: '',
                    activo: 0
                }
            }
        }
    },
    mounted() {
        this.userId = this.$route.params.userId;
        this.model.usuario.userName = this.userName;
        this.model.usuario.userEmail = this.userEmail;
        this.model.usuario.userImage = this.userImage;
        this.model.usuario.cantidad = this.cantidad;
        this.model.usuario.activo = this.activo;
    },
    methods: {
        handleFileUpload(event) {
            this.model.usuario.userImage = event.target.files[0];
        },
        actualizarUsuario() {
            axios.put(`http://localhost:8080/trivial/v1/users/${this.userId}`, this.model.usuario)
                .then(response => {
                    alert('Usuario actualizado con éxito.');
                    this.$router.push('/admin');
                })
                .catch(error => {
                });
        }
    }
}
</script>

<style scoped>
.form-check-input[type=checkbox] {
    transform: scale(1.5);
}

#botonInput {
    padding-top: 14px;
    padding-bottom: 43px;
}

.btn.btn-primary:hover {
    color: #0062cc;
    background-color: white;
    border-color: #0062cc;
    transition: all 0.5s;
}
</style>