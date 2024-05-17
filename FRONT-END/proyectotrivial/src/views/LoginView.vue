<template>
    <div>
        <h2>Inicio de Sesión</h2>
        <form @submit.prevent="admin">
            <label for="username">Usuario:</label>
            <input type="text" v-model="username" required />
            <br />
            <label for="password">Contraseña:</label>
            <input type="password" v-model="password" required />
            <br />
            <button type="submit">Iniciar Sesión</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'admin',
    data() {
        return {
            username: '',
            password: ''
        }
    },
    methods: {
        login() {
            fetch('/admin', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username: this.username, password: this.password })
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Fallo en la autenticación');
                    }
                })
                .then(data => {
                    localStorage.setItem('token', data.token);
                    this.$router.push('/admin');
                })
                .catch(error => {
                    alert(error.message);
                });
        }
    }
};
</script>

<style scoped></style>