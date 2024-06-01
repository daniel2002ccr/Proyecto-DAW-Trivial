<template>
  <div class="ranking">
    <h1>RANKING</h1>
    <table>
      <thead>
        <tr>
          <th>Posición</th>
          <th>Nombre</th>
          <th>Puntuación</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(player, index) in players" :key="index">
          <td class="posicion">{{ index + 1 }}</td>
          <td class="fila">{{ player.name }}</td>
          <td class="fila">{{ player.puntuacion }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      players: [],
    };
  },
  mounted() {
    this.getRanking();
  },
  methods: {
    getRanking() {
    axios.get('http://localhost:8080/trivial/v1/users')
      .then(response => {
        this.players = response.data.map(player => ({
          name: player.userName,
          puntuacion: player.puntuacion,
          source: 'database'
        }));
        this.players.sort((a, b) => b.puntuacion - a.puntuacion);
      })
      .catch(error => {
        console.error('Error al obtener el ranking:', error);
      });
  },
},
};
</script>


<style scoped>
.botonActualizarDescripcion {
  padding-top: 2px;
  margin-right: 22px;
  border-radius: 100px;
  height: 38px;
  color: #fff;
  background-color: #0069d9;
  border-color: #0062cc;
  border: none;
  width: 47%;
}

.botonActualizarDescripcion:hover {
  padding-top: 2px;
  margin-right: 22px;
  border-radius: 100px;
  height: 38px;
  color: #0069d9;
  background-color: #fff;
  border-color: #fff;
  border: none;
  width: 47%;
  transition: all 0.6s;
}

.botonActualizarDescripcion:disabled {
  padding-top: 2px;
  margin-right: 22px;
  border-radius: 100px;
  height: 38px;
  color: #fff;
  background-color: #0069d9;
  border-color: #0062cc;
  border: none;
  width: 47%;
}

.botonEliminarDescripcion {
  border-radius: 100px;
  height: 38px;
  color: #fff;
  background-color: #eb0f0f;
  border-color: #eb0f0f;
  border: none;
  width: 47%;
}

.botonEliminarDescripcion:hover {
  border-radius: 100px;
  height: 38px;
  color: #eb0f0f;
  background-color: #fff;
  border-color: #fff;
  border: none;
  width: 47%;
  transition: all 0.6s;
}

.posicion {
  font-size: 20px;
  font-weight: bold;
}

.fila {
  font-size: 20px;
}

.botonPerfil {
  background-color: white;
  color: black;
  font-size: 17px;
  font-weight: bold;
  border-radius: 100px;
  height: 30px;
  width: 30%;
  padding-top: 1%;
  padding-bottom: 1%;
  border: none;
  outline: none;
  cursor: pointer;
  transition: all 0.3s;
}

.botonPerfil:hover {
  background-color: black;
  color: white;
  transition: all 0.7s;
}

.botonEliminarDatos {
  float: right;
  width: 200px;
  height: 40px;
  border: none;
  font-size: 16px;
  font-weight: bold;
  margin-right: 100px;
  margin-top: -50px;
}

.botonEliminarDatos:hover {
  float: right;
  width: 200px;
  height: 40px;
  border: 3px solid white;
  font-size: 16px;
  background-color: grey;
  color: white;
  font-weight: bold;
  margin-right: 100px;
  margin-top: -50px;
}

.botonEliminarDatos:disabled {
  pointer-events: none;
}

.botonEliminarDatos:disabled:hover {
  pointer-events: none;
}

.ranking {
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

@media screen and (max-width: 425px) {
  h1 {
    margin-right: 137px;
    font-size: 30px;
  }

  .botonEliminarDatos {
    margin-right: 8px;
    margin-top: -45px;
    width: 123px;
  }

  .botonEliminarDatos:hover {
    margin-right: 8px;
    margin-top: -45px;
    width: 123px;
    font-size: 15px;
  }

  th,
  td {
    padding: 0px;
  }

  .botonPerfil {
    font-size: 15px;
    margin-right: 22px;
    margin-left: 17px;
    width: 63%;
  }

  .botonPerfil:hover {
    font-size: 15px;
    margin-right: 22px;
    margin-left: 17px;
    width: 63%;
  }
}
</style>