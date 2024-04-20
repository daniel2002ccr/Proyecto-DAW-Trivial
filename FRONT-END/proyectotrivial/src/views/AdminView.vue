<template>
    <div class="admin">
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>E-mail</th>
                    <th>Imagen</th>
                    <th>Cantidad</th>
                    <th>Activo</th>
                    <th>Actividad</th>
                </tr>
            </thead>
            <tbody v-if="this.users.length > 0">
                <tr v-for="(user, index) in this.users" :key="index">
                    <td>{{ user.userId }}</td>
                    <td>{{ user.userName }}</td>
                    <td>{{ user.userEmail }}</td>
                    <td>{{ user.userImage }}</td>
                    <td>{{ user.cantidad }}</td>
                    <td>{{ user.activo }}</td>
                    <td>
                        <router-link to="/" class="btn btn-success">
                            Edit
                        </router-link>
                        <button type="button" class="btn btn-danger">Delete</button>
                    </td>
                </tr>
            </tbody>
            <tbody v-else>
                <tr>
                    <td colspan="7">Loading...</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
/* import axios from 'axios'

export default {

    name: 'users',
    data() {
        return {
            users: []
        }
    },
    mounted() {
        this.getUsers();
    },

    methods: {
        getUsers() {
            axios.get('http://localhost:8080/trivial/v1/users')
                .then(response => {
                    this.users = response.data;
                })
                .catch(error => {
                    console.error('Hubo un problema con la solicitud:', error);
                    // Muestra un mensaje de error en la aplicación
                });
        },
    },

};
const express = require('express');
const app = express();

// Middleware para habilitar CORS
app.use((req, res, next) => {
    res.setHeader('Access-Control-Allow-Origin', 'http://localhost:8081'); // Permite solicitudes desde este origen
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE'); // Métodos HTTP permitidos
    res.setHeader('Access-Control-Allow-Headers', 'Content-Type, Authorization'); // Encabezados permitidos
    res.setHeader('Access-Control-Allow-Credentials', true); // Permite enviar credenciales (cookies, por ejemplo)
    next();
});

// Resto de la configuración de tu servidor Express
// Define tus rutas, controladores, etc.

// Ejemplo de ruta para obtener usuarios
app.get('/trivial/v1/users', (req, res) => {
    // Aquí iría la lógica para obtener y enviar los usuarios
});

// Configuración del puerto
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
    console.log(`Servidor escuchando en el puerto ${PORT}`);
}); */

</script>

<style scoped>
.admin {
    background-color: rgb(255, 50, 50);
}

h1 {
    text-align: center;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
    margin-top: 1%;
    padding-top: 1%;
    padding-right: 6%;
}

table {
    margin-top: 1%;
    width: 100%;
    border-collapse: collapse;
}

th,
td {
    border: 1px solid black;
    padding: 20px;
    text-align: center;
    background-color: rgb(182, 182, 182);
}

th {
    background-color: #f2f2f2;
    text-align: center;
}
</style>