<template>
    <div class="container d-flex justify-content-center">
        <div class="card p-3 py-4">
            <div class="text-center">
                <img src="https://us.123rf.com/450wm/hugok1000/hugok10001905/hugok1000190500198/123291745-ilustraci%C3%B3n-de-avatar-de-perfil-predeterminado-en-azul-y-blanco-sin-persona.jpg"
                    width="100" class="rounded-circle">
                <input class="inputFotito" type="file" @change="onFileChange">
                <h3 class="mt-2">{{ jugador ? jugador.name : '---' }}</h3>
                <div class="row mt-3 mb-3">
                    <div class="col-md-6">
                        <h5>Puntos</h5>
                        <span class="num">{{ jugador ? jugador.score : '---' }}</span>
                    </div>
                    <div class="col-md-6">
                        <h5>Partidas</h5>
                        <span class="num">{{ partidasJugadas }}</span>
                    </div>
                </div>
                <hr class="line">

                <small class="mt-4">Descripción del jugador</small>
                <template v-if="!editandoDescripcion && !guardado">
                    <input type="text" v-model="nuevaDescripcion" class="form-control"
                        placeholder="Añadir descripción..." :disabled="guardado">
                </template>
                <template v-else>
                    <p v-if="!editandoDescripcion">{{ jugador.descripcion }}</p>
                    <input v-else type="text" v-model="nuevaDescripcion" class="form-control"
                        placeholder="Añadir descripción..." :disabled="guardado">
                </template>

                <button class="botonGuardarDescripcion" @click="guardarDescripcion"
                    :disabled="guardado || nuevaDescripcion.trim() === ''">Guardar</button>
                <template v-if="guardado">
                    <div class="mt-4">
                        <button class="botonEditarDescripcion" @click="editarDescripcion">Editar</button>
                        <button class="botonEliminarDescripcion" @click="eliminarDescripcion">Eliminar</button>
                    </div>
                </template>
                <div class="social-buttons mt-5">
                    <button class="neo-button"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"
                            class="imagen">
                            <path fill="#1b18c9"
                                d="M80 299.3V512H196V299.3h86.5l18-97.8H196V166.9c0-51.7 20.3-71.5 72.7-71.5c16.3 0 29.4 .4 37 1.2V7.9C291.4 4 256.4 0 236.2 0C129.3 0 80 50.5 80 159.4v42.1H14v97.8H80z" />
                        </svg></button>
                    <button class="neo-button"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"
                            class="imagen">
                            <path fill="#93c8f0"
                                d="M100.3 448H7.4V148.9h92.9zM53.8 108.1C24.1 108.1 0 83.5 0 53.8a53.8 53.8 0 0 1 107.6 0c0 29.7-24.1 54.3-53.8 54.3zM447.9 448h-92.7V302.4c0-34.7-.7-79.2-48.3-79.2-48.3 0-55.7 37.7-55.7 76.7V448h-92.8V148.9h89.1v40.8h1.3c12.4-23.5 42.7-48.3 87.9-48.3 94 0 111.3 61.9 111.3 142.3V448z" />
                        </svg></button>
                    <button class="neo-button"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 488 512"
                            class="imagen">
                            <path fill="#ffffff"
                                d="M488 261.8C488 403.3 391.1 504 248 504 110.8 504 0 393.2 0 256S110.8 8 248 8c66.8 0 123 24.5 166.3 64.9l-67.5 64.9C258.5 52.6 94.3 116.6 94.3 256c0 86.5 69.1 156.6 153.7 156.6 98.2 0 135-70.4 140.8-106.9H248v-85.3h236.1c2.3 12.7 3.9 24.9 3.9 41.4z" />
                        </svg></button>
                    <button class="neo-button"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"
                            class="imagen">
                            <path fill="#e40c0c"
                                d="M549.7 124.1c-6.3-23.7-24.8-42.3-48.3-48.6C458.8 64 288 64 288 64S117.2 64 74.6 75.5c-23.5 6.3-42 24.9-48.3 48.6-11.4 42.9-11.4 132.3-11.4 132.3s0 89.4 11.4 132.3c6.3 23.7 24.8 41.5 48.3 47.8C117.2 448 288 448 288 448s170.8 0 213.4-11.5c23.5-6.3 42-24.2 48.3-47.8 11.4-42.9 11.4-132.3 11.4-132.3s0-89.4-11.4-132.3zm-317.5 213.5V175.2l142.7 81.2-142.7 81.2z" />
                        </svg></button>
                    <button class="neo-button"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"
                            class="imagen">
                            <path fill="#66c2f0"
                                d="M459.4 151.7c.3 4.5 .3 9.1 .3 13.6 0 138.7-105.6 298.6-298.6 298.6-59.5 0-114.7-17.2-161.1-47.1 8.4 1 16.6 1.3 25.3 1.3 49.1 0 94.2-16.6 130.3-44.8-46.1-1-84.8-31.2-98.1-72.8 6.5 1 13 1.6 19.8 1.6 9.4 0 18.8-1.3 27.6-3.6-48.1-9.7-84.1-52-84.1-103v-1.3c14 7.8 30.2 12.7 47.4 13.3-28.3-18.8-46.8-51-46.8-87.4 0-19.5 5.2-37.4 14.3-53 51.7 63.7 129.3 105.3 216.4 109.8-1.6-7.8-2.6-15.9-2.6-24 0-57.8 46.8-104.9 104.9-104.9 30.2 0 57.5 12.7 76.7 33.1 23.7-4.5 46.5-13.3 66.6-25.3-7.8 24.4-24.4 44.8-46.1 57.8 21.1-2.3 41.6-8.1 60.4-16.2-14.3 20.8-32.2 39.3-52.6 54.3z" />
                        </svg></button>
                </div>
                <div class="profile mt-5">
                    <button class="profile_button px-5">Mi cuenta</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
    data() {
        return {
            imagen: '',
            jugador: null,
            nuevaDescripcion: '',
            editandoDescripcion: false,
            guardado: false,
            partidasJugadas: 1
        };
    },
    mounted() {
        this.cargarDatosJugador();
    },
    computed: {
        ...mapGetters(['descripcionJugador']),
        imagenPerfil() {
            return this.jugador.imagen ? URL.createObjectURL(this.jugador.imagen) : 'https://us.123rf.com/450wm/hugok1000/hugok10001905/hugok1000190500198/123291745-ilustraci%C3%B3n-de-avatar-de-perfil-predeterminado-en-azul-y-blanco-sin-persona.jpg';
        }
    },
    methods: {
        cargarDatosJugador() {
            const { name, score, ranking } = this.$route.params;
            this.jugador = { name, score, ranking, descripcion: '' };
        },
        guardarDescripcion() {
            this.jugador.descripcion = this.nuevaDescripcion;
            if (this.imagen) {
                this.jugador.imagen = this.imagen;
                this.imagen = '';
            }
            this.guardado = true;
            this.editandoDescripcion = false;
        },
        editarDescripcion() {
            this.editandoDescripcion = true;
            this.guardado = false;
        },
        eliminarDescripcion() {
            this.nuevaDescripcion = '';
            this.guardado = false;
            this.editandoDescripcion = false;
        },
        onFileChange(event) {
            const file = event.target.files[0];
            if (file) {
                this.imagen = file;
                if (this.jugador.imagen) {
                    URL.revokeObjectURL(this.jugador.imagen);
                }
                this.jugador.imagen = URL.createObjectURL(file);
            }
        },
    }
};
</script>

<style scoped>
.inputFotito {
    margin-top: 10px;
    width: 250px;
}

.botonGuardarDescripcion {
    margin-top: 25px;
    border-radius: 100px;
    height: 32px;
    color: #fff;
    background-color: #0069d9;
    border-color: #0062cc;
    border: none;
    width: 30%;
}

.botonGuardarDescripcion:hover {
    margin-top: 25px;
    border-radius: 100px;
    height: 32px;
    color: #0069d9;
    background-color: #fff;
    border-color: #fff;
    border: none;
    width: 30%;
    transition: all 0.6s;
}

.botonGuardarDescripcion:disabled {
    margin-top: 25px;
    border-radius: 100px;
    height: 32px;
    color: #fff;
    background-color: #0069d9;
    border-color: #0062cc;
    border: none;
    width: 30%;
}

.botonEditarDescripcion {
    margin-top: 25px;
    border-radius: 100px;
    height: 32px;
    color: #fff;
    background-color: #1c9e2d;
    border-color: #1c9e2d;
    border: none;
    width: 30%;
}

.botonEditarDescripcion:hover {
    margin-top: 25px;
    border-radius: 100px;
    height: 32px;
    color: #1c9e2d;
    background-color: #fff;
    border-color: #fff;
    border: none;
    width: 30%;
    transition: all 0.6s;
}

.botonEliminarDescripcion {
    margin-top: 25px;
    border-radius: 100px;
    height: 32px;
    color: #fff;
    background-color: #eb0f0f;
    border-color: #eb0f0f;
    border: none;
    width: 30%;
}

.botonEliminarDescripcion:hover {
    margin-top: 25px;
    border-radius: 100px;
    height: 32px;
    color: #eb0f0f;
    background-color: #fff;
    border-color: #fff;
    border: none;
    width: 30%;
    transition: all 0.6s;
}

.form-control {
    margin-top: 9px;
    width: 100%;
    height: 40px;
    font-size: 14px;
}

small.mt-4 {
    font-size: 17px;
}

.rounded-circle {
    height: 19%;
}

.imagen {
    height: 60%;
}

@import url('https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap');

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Noto Sans', sans-serif
}

html,
body {
    height: 100%
}

body {
    display: grid;
    place-items: center;
    background: #AB47BC
}

.card {
    margin-top: 10%;
    margin-left: 66%;
    color: #fff;
    width: 370px;
    height: 716px;
    border-radius: 10px;
    background: linear-gradient(145deg, #9a40a9, #b74cc9);
    box-shadow: 20px 20px 60px #913ca0,
        -20px -20px 60px #c552d8;
    border: none
}

.neo-button {
    width: 40px;
    height: 40px;
    outline: 0 !important;
    cursor: pointer;
    color: #fff;
    font-size: 15px;
    border: none;
    margin-right: 10px;
    border-radius: 50%;
    background: linear-gradient(145deg, #9a40a9, #b74cc9);
    box-shadow: inset 20px 20px 60px #913ca0,
        inset -20px -20px 60px #c552d8;
}

.num {
    color: #eee !important;
}

.line {
    color: #fff;
    margin-bottom: 20px;
}

.neo-button:active {
    border-radius: 50%;
    background: #AB47BC;
    box-shadow: 28px 28px 57px #933da2,
        -28px -28px 57px #c351d6;
}

.fa-facebook {
    color: #3b5998
}

.fa-linkedin {
    color: #0077b5
}

.fa-google {
    color: #dc4e41
}

.fa-youtube {
    color: #cd201f
}

.fa-twitter {
    color: #55acee
}

.profile_button {
    color: #fff;
    padding: 10px;
    border: none;
    outline: 0 !important;
    border-radius: 50px;
    background: #AB47BC;
    box-shadow: 28px 28px 57px #933da2,
        -28px -28px 57px #c351d6;
}

.profile_button:hover {
    color: #AB47BC;
    font-weight: bold;
    padding: 10px;
    border: none;
    outline: 0 !important;
    border-radius: 50px;
    background: #fff;
    box-shadow: 28px 28px 57px #933da2,
        -28px -28px 57px #c351d6;
    cursor: pointer;
    transition: 0.5s;
}

@media screen and (max-width: 425px) {
    .card {
        margin-left: 1px
    }

    .col-md-6 {
        width: 50%;
    }
}
</style>