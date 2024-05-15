<template>
    <h1>NOTICIAS</h1>
    <div class="container">
        <div class="noticia-grande">
            <h2 class="tituloNoticiaGrande">¡PACO CONSIGUE EL PRIMER PLENO EN TRIWORLD!</h2>
            <img src="../img/ImagenNoticiaGrande.png" alt="Noticia Grande Imagen" class="imagenNoticiaGrande">
            <p class="parrafoNoticiaGrande">¡Paco se alza con la victoria absoluta en el emocionante mundo del
                TrivWorld! En un despliegue de destreza mental sin precedentes, logra el primer pleno en la historia del
                juego. TrivWorld, una competencia de conocimientos que desafía a sus participantes con preguntas de
                diversas categorías, no logró intimidar a este hábil concursante. Con una racha impecable, Paco no solo
                respondió correctamente las diez preguntas, sino que todas ellas estaban catalogadas en el nivel de
                dificultad más alto. Su hazaña no solo lo posiciona como un maestro indiscutible del juego, sino que
                también lo convierte en unícono de inspiración para todos los aspirantes a la gloria en el TrivWorld.
                ¡Un logro que quedará grabado en la historia de esta competencia intelectual!</p>
        </div>
        <div class="noticias-pequenas">
            <div>
                <img src="../img/ImagenNoticiaPequeña1.jpg" alt="Noticia Pequeña Imagen 1">
                <h3 class="tituloNoticiaPequena">¡Pepe y María se quedan a 2 preguntas!</h3>
                <p class="parrafoNoticiaPequena">¡Estuvieron a punto de lograrlo en el TrivWorld! Demostraron su
                    destreza en el nivel medio, quedando a solo dos preguntas de la victoria.</p>
            </div>
            <div>
                <img src="../img/ImagenNoticiaPequeña2.jpg" alt="Noticia Pequeña Imagen 2">
                <h3 class="tituloNoticiaPequena2">Patricia y Marina: A un Paso de la Gloria en TrivWorld</h3>
                <p class="parrafoNoticiaPequena2">¡Estuvieron a punto de alcanzar la victoria en el nivel fácil de
                    TrivWorld! Aunque no lo consiguieron, demostraron ser competidoras destacadas en el mundo de
                    TrivWorld.</p>
            </div>
            <div>
                <img src="../img/ImagenNoticiaPequeña3.png" alt="Noticia Pequeña Imagen 3">
                <h3 class="tituloNoticiaPequena3">¡2 médicos establecen nuevo récord en TriWorld!</h3>
                <p class="parrafoNoticiaPequena3">Dos médicos sorprenden al establecer un récord de tiempo en el famoso
                    juego de Trivial. Su combinación de conocimientos profundos y rapidez mental los llevó a superar el
                    desafío en tiempo récord, destacando su excepcional habilidad intelectual más allá de su campo de
                    especialización.</p>
            </div>
            <div>
                <img src="../img/ImagenNoticiaPequeña4.jpg" alt="Noticia Pequeña Imagen 4"
                    class="imagenNoticiaPequena4">
                <h3 class="tituloNoticiaPequena4">¿Serán capaces?</h3>
                <p class="parrafoNoticiaPequena4">El próximo programa revelará si estos concursantes tienen lo necesario
                    para dejar su huella en la historia del Trivial.</p>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: 'NoticiasView'
}
</script>

<style scoped>
body,
html {
    margin: 0;
    padding: 0;
    height: 100%;
    width: 100%;
}

h1 {
    text-align: center;
    margin-top: 1%;
    font-family: cursive;
    font-size: 70px;
}

.container {
    margin-top: 1%;
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
    margin-left: 111px;
}

.tituloNoticiaGrande {
    margin-top: 3%;
    margin-bottom: 3%;
}

.tituloNoticiaPequena {
    font-size: 20px;
}

.tituloNoticiaPequena2 {
    font-size: 18px;
    margin-bottom: 3px;
    margin-left: 1px;
}

.tituloNoticiaPequena3 {
    margin-left: 13px;
    font-size: 15px;
}

.tituloNoticiaPequena4 {
    font-size: 22px;
    text-align: center;
}

.parrafoNoticiaGrande {
    padding-right: 14%;
    padding-left: 14%;
    font-size: 16px;
}

.parrafoNoticiaPequena {
    margin-left: 12px;
    margin-top: 14px;
}

.parrafoNoticiaPequena2 {
    margin-left: 3px;
    font-size: 14px;
}

.parrafoNoticiaPequena3 {
    margin-left: 6px;
    font-size: 12px;
    margin-top: 10px;
}

.parrafoNoticiaPequena4 {
    font-size: 17px;
}

.imagenNoticiaGrande {
    width: 70%;
    margin-left: 108px;
    margin-bottom: 5%
}

.imagenNoticiaPequena4 {
    height: 203px !important;
}

.noticia-grande {
    background-color: #dbdbdb;
    grid-column: 1 / 2;
    width: 800px;
    padding-right: 2%;
    padding-left: 2%;
}

.noticias-pequenas {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 20px;
}

.noticias-pequenas div {
    background-color: #f2f2f2;
    padding: 20px;
    box-sizing: border-box;
    width: 400px;
    height: 350px;
}

.noticias-pequenas img {
    width: 100%;
    height: auto;
    margin-top: -10px;
    margin-bottom: 10px;
}

@media screen and (max-width: 425px) {
    .noticia-grande {
        margin-left: -120px;
        width: 410px;
        height: 628px;
    }

    .tituloNoticiaGrande {
        margin-top: 3%;
        font-size: 18px;
        text-align: center;
        margin-bottom: 3%;
    }

    .imagenNoticiaGrande {
        margin-left: 3px;
        width: 392px;
    }

    .parrafoNoticiaGrande {
        padding-right: 10px;
        padding-left: 14px;
        font-size: 14px;
    }

    .noticias-pequenas {
        margin-top: 646px;
        margin-left: -424px;
    }

    .noticias-pequenas> :nth-child(2) {
        margin-left: -420px;
        margin-top: 361px;
        margin-bottom: -7px;
    }

    .noticias-pequenas> :nth-child(4) {
        margin-left: -420px;
        margin-top: 365px;
        margin-bottom: 15px;
    }
}
</style>