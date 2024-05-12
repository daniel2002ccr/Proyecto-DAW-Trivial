<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h4 class="d-flex justify-content-between align-items-center">
                    <span>Usuarios</span>
                    <router-link to="/admin/crearUser" class="btn btn-primary">Añadir Usuario</router-link>
                </h4>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Email</th>
                            <th>Imagen</th>
                            <th>Cantidad</th>
                            <th>Activo</th>
                            <th>Función a realizar</th>
                        </tr>
                    </thead>
                    <tbody v-if="usuarios.length > 0">
                        <tr v-for="(usuario, index) in usuarios" :key="index">
                            <td class="posicion">{{ usuario.userId }}</td>
                            <td>{{ usuario.userName }}</td>
                            <td>{{ usuario.userEmail }}</td>
                            <td><img :src="'data:image/jpeg;base64,' + usuario.userImage" class="imagen-usuario" /></td>
                            <td>{{ usuario.cantidad }}</td>
                            <td>{{ usuario.activo }}</td>
                            <td>
                                <router-link :to="{ name: 'ActualizarUsuarioView', params: { userId: usuario.userId } }"
                                    class="btn btn-success mx-2">Editar</router-link>

                                <button type="button" @click="borrarUsuario(usuario.userId)"
                                    class="btn btn-danger mx-2">Desactivar</button>
                            </td>
                        </tr>
                    </tbody>
                    <tbody v-else>
                        <tr>
                            <td colspan="6">No hay usuarios</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'admin',
    data() {
        return {
            usuarios: []
        }
    },
    mounted() {
        this.getUsuarios();
    },
    methods: {
        getUsuarios() {
            axios.get('http://localhost:8080/trivial/v1/users')
                .then(response => {
                    console.log(response)
                    this.usuarios = response.data;
                })
                .catch(error => {
                    console.error('Hubo un problema con la solicitud:', error);
                });
        },
        borrarUsuario(userId) {
            const usuario = this.usuarios.find(u => u.userId === userId);

            if (usuario.activo === 0) {
                alert('El usuario ya no está activo.');
                return;
            }
            axios.delete(`http://localhost:8080/trivial/v1/users/${userId}`)
                .then(response => {
                    alert('Usuario desactivado con éxito');
                    this.getUsuarios();
                })
                .catch(error => {
                    console.error('Error al intentar desactivado usuario:', error);
                });
        }
    },
}
</script>

<style scoped>
.imagen-usuario {
    max-width: 99%;
    max-height: 87px;
}

.btn-primary {
    transition: all 0.7s;
}

.btn-success {
    transition: all 0.7s;
}

.btn-danger {
    transition: all 0.7s;
}

.btn-primary:hover {
    color: #0062cc;
    background-color: white;
    border-color: #0062cc;
    transition: all 0.5s;
}

.btn-success:hover {
    color: #218838;
    background-color: white;
    border-color: #218838;
    transition: all 0.5s;
}

.btn-danger:hover {
    color: #c82333;
    background-color: white;
    border-color: #c82333;
    transition: all 0.5s;
}

.posicion {
    font-size: 23px;
    font-weight: bold;
    padding-top: 20px;
}

span {
    font-size: 37px;
    margin-bottom: -3%;
    padding-left: 2%;
}

.card {
    border: 2px solid black;
}

.card-header h4 {
    margin-bottom: 0;
}

.card-header {
    background-color: white;
    border-bottom: 0px solid white
}

.card-header .btn {
    margin-left: auto;
    margin-top: 9px;
}

table {
    margin-top: 1%;
    width: 100%;
    border-collapse: collapse;
}

th,
td {
    border: 2px solid black;
    padding: 20px;
    text-align: center;
}

.table thead th {
    background-color: #f2f2f2;
    text-align: center;
    border-bottom: 2px solid black;
}
</style>