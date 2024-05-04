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
                        </tr>
                    </thead>
                    <tbody v-if="usuarios.length > 0">
                        <tr v-for="(usuario, index) in usuarios" :key="index">
                            <td>{{ usuario.id }}</td>
                            <td>{{ usuario.name }}</td>
                            <td>{{ usuario.email }}</td>
                            <td>{{ usuario.imagen }}</td>
                            <td>{{ usuario.cantidad }}</td>
                            <td>{{ usuario.activo }}</td>
                            <td>
                                <router-link to="/" class="btn btn-success">
                                    Editar Usuario
                                </router-link>
                                <button type="button" class="btn btn-danger">Borrar Usuario</button>
                            </td>
                        </tr>
                    </tbody>
                    <tbody v-else>
                        <tr>
                            <td colspan="7">No hay usuarios</td>
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
    }
}
</script>

<style scoped>
.card-header h4 {
    margin-bottom: 0;
}
.card-header .btn {
    margin-left: auto;
    margin-top: 10px; /* Ajusta la separación del botón desde la parte superior */
}
</style>
