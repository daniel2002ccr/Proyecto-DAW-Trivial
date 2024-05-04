<template>
    <div class="container">
    <div class="card">
        <div class="card-header">
            <h4>Usuarios 
                <router-link to="/admin/crearUsuario" class="btn btn-primary float-end">Añadir Usuario</router-link></h4>    
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
                <tbody v-if="this.usuario.length > 0">
                    <tr v-for="(usuario, index) in this.usuarios" :key="index">
                        <td>usuario.id</td>
                        <td>usuario.name</td>
                        <td>usuario.email</td>
                        <td>usuario.imagen</td>
                        <td>usuario.cantidad</td>
                        <td>usuario.activo</td>
                        <td>
                            <router-link to="/" class="btn btn-success">
                                Editar Usuario</router-link>
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
export default{
    name: 'admin',
    data(){
        return{
        usuarios: []
    }
},
mounted(){
    this.getUsuarios();
},
method:{
    getUsuarios(){
        axios.get('http://localhost:8080/trivial/v1/users')
                .then(response => {
                    console.log(response)
                    this.users = response.data;
                })
                .catch(error => {
                    console.error('Hubo un problema con la solicitud:', error);
                    
                });
        },
    }
}

</script>
<style>

</style>