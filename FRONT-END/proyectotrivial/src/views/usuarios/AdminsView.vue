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
                            <th>Funcion a realizar</th>
                        </tr>
                    </thead>
                  <!--   <tbody v-if="usuarios.length > 0">
                        <tr v-for="(usuario, index) in usuarios" :key="index">
                            <td>{{ usuario.id }}</td>
                            <td>{{ usuario.name }}</td>
                            <td>{{ usuario.email }}</td>
                            <td>{{ usuario.imagen }}</td>
                            <td>{{ usuario.cantidad }}</td>
                            <td>{{ usuario.activo }}</td>
                            <td>
                                <router-link :to="{path: '/admin/'+usuario.id+'actualizarUser'}" class="btn btn-success mx-2">
                                    Editar
                                </router-link>
                                <button type="button" class="btn btn-danger mx-2">Borrar</button>
                            </td>
                        </tr>
                    </tbody> 
                    <tbody v-else>
                        <tr>
                            <td colspan="6">No hay usuarios</td>
                        </tr>
                    </tbody> -->
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Antonio</td>
                            <td>antonio@email.com</td>
                            <td>aqui no hay imagen</td>
                            <td>20</td>
                            <td>Full activo</td>
                            <td>
                                <router-link to="/admin/ActualizarUser" class="btn btn-success mx-2">
                                    Editar
                                </router-link>
                                <button type="button" class="btn btn-danger mx-2">Borrar</button>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Luis</td>
                            <td>luis@email.com</td>
                            <td>aqui no hay imagen</td>
                            <td>20</td>
                            <td>Full activo</td>
                            <td>
                                <router-link to="/admin/ActualizarUser" class="btn btn-success mx-2">
                                    Editar
                                </router-link>
                                <button type="button" @click="borrarUsuario(usuario.id)" class="btn btn-danger mx-2">Borrar</button>
                            </td>
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
        borrarUsuario(usuarioId){
            if(confirm('¿Estas seguro de que quieres borrar este usuario?')){
            axios.delete(`http://localhost:8080/trivial/v1/users/${usuarioId}/delete`)
            .then(res =>{
                alert('El usuario ha sido borrado con éxito!');
                this.getUsuarios();
            })
            .catch(function (error) {
                if (error.response){
                    if (error.response.status == 404){
                        alert('Error 404, no existe el id de ese usuario...');
                    }
                }
            })
        }
    },
    },
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
