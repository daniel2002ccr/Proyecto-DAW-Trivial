<template>
  <div class="ranking">
    <h1>RANKING</h1>
    <button @click="eliminarDatos" class="botonEliminarDatos" :disabled="players.length === 0">Eliminar Datos</button>
    <table>
      <thead>
        <tr>
          <th>Posición</th>
          <th>Nombre</th>
          <th>Puntuación</th>
          <th>Dificultad</th>
          <th>Descripción</th>
          <th>Ajustes</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(player, index) in players" :key="index">
          <td class="posicion">{{ index + 1 }}</td>
          <td class="fila">{{ getSourceName(player) }}</td>
          <td class="fila">{{ getSourceScore(player) }}</td>
          <td class="fila">{{ player.difficulty }}</td>
          <td class="fila">
            <input type="text" v-model="player.descripcion">
          </td>
          <td>
            <button class="botonActualizarDescripcion" @click="actualizarDescripcion(player)">Actualizar</button>
            <button class="botonEliminarDescripcion" @click="eliminarDescripcion(player, index)">Eliminar</button>
          </td>
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
      rankingData: [],
      rankingId: 1,
      userName: '',
      puntuacion: "100",
      descripcion: '',
      players: [],
      model: {
        usuario: {
          userName: '',
          userEmail: '',
          userPasswd: '',
          userImage: null,
          cantidad: '',
          activo: 0
        }
      }
    };
  },
  mounted() {
    this.getRanking();
    this.insertarJugador();

    const savedPlayers = JSON.parse(localStorage.getItem('players'));

    if (savedPlayers) {
      this.players = [...savedPlayers];
    }

    const params = new URLSearchParams(window.location.search);
    const name = params.get('name');
    const score = params.get('score');
    const difficulty = params.get('difficulty');
    const descripcion = params.get('descripcion');

    if (name && score && difficulty) {
      const descripcionValue = descripcion ? descripcion : "Pendiente";
      this.players.push({ name, score, difficulty, descripcion: descripcionValue });
    }

    this.players.sort((a, b) => {
      if (b.score !== a.score) {
        return b.score - a.score;
      } else {
        const difficultyOrder = { "hard": 3, "medium": 2, "easy": 1 };
        return difficultyOrder[b.difficulty] - difficultyOrder[a.difficulty];
      }
    });
    this.players.forEach(player => {
      player.difficulty = this.getReadableDifficulty(player.difficulty);
    });
    localStorage.setItem('players', JSON.stringify(this.players));
  },
  methods: {
    insertarJugador() {
      let formData = new FormData();
      formData.append('rankingId', this.rankingId);
      formData.append('userName', this.model.usuario.userName);
      formData.append('puntuacion', this.puntuacion);
      formData.append('descripcion', this.descripcion);

      axios.post('http://localhost:8080/trivial/v1/ranking', formData)
        .then(response => {
          window.location.href = 'http://localhost:8081/ranking';
        })
        .catch(error => {
        });
    },
    getRanking() {
      axios.get('http://localhost:8080/trivial/v1/ranking')
        .then(response => {
          this.players = [...this.players, ...response.data.map(player => ({
            ...player,
            source: 'database'
          }))];
          this.players.forEach(player => {
            if (player.source === 'database') {
              player.difficulty = 'Inicial';
            } else {
              player.difficulty = this.getReadableDifficulty(player.difficulty);
            }
          });
          this.players.sort((a, b) => {
            if (this.getSourceScore(b) !== this.getSourceScore(a)) {
              return this.getSourceScore(b) - this.getSourceScore(a);
            } else {
              const difficultyOrder = { "hard": 3, "medium": 2, "easy": 1 };
              return difficultyOrder[b.difficulty] - difficultyOrder[a.difficulty];
            }
          });
        });
    },
    async insertarJugador() {
      let jugadorExistente = this.players.find(player => player.name === this.name);

      if (jugadorExistente) {
        if (parseInt(this.puntuacion) > parseInt(jugadorExistente.puntuacion)) {
          jugadorExistente.puntuacion = parseInt(this.puntuacion);
          jugadorExistente.descripcion = this.descripcion;
          axios.put(`http://localhost:8080/trivial/v1/ranking/${jugadorExistente.rankingId}`, {
            puntuacion: parseInt(this.puntuacion),
            descripcion: this.descripcion
          })
            .then(response => {
              alert('Datos del jugador actualizados con éxito');
            })
            .catch(error => {
            });
        } else {
          alert('Ya existe un jugador con el mismo nombre en el ranking con una puntuación igual o mayor.');
        }
      } else {
        let formData = new FormData();
        formData.append('userName', this.name);
        formData.append('puntuacion', this.puntuacion);
        formData.append('descripcion', this.descripcion);

        axios.post('http://localhost:8080/trivial/v1/ranking', formData)
          .then(response => {
            alert('Jugador insertado en el ranking con éxito');
            window.location.href = 'http://localhost:8081/ranking';
          })
          .catch(error => {
          });
      }
    },
    getSourceName(player) {
      return player.source === 'database' && player.userId ? player.userId.userName : player.name;
    },
    getSourceScore(player) {
      return player.source === 'database' ? player.puntuacion : player.score;
    },
    actualizarDescripcion(player) {
      axios.put(`http://localhost:8080/trivial/v1/ranking/${player.rankingId}`, { descripcion: player.descripcion })
        .then(response => {
          alert('Descripción actualizada con éxito');
        })
        .catch(error => {
        });
    },
    eliminarDescripcion(player, index) {
      axios.delete(`http://localhost:8080/trivial/v1/ranking/${player.rankingId}`)
        .then(response => {
          this.players[index].descripcion = '';
          alert('Descripción eliminada con éxito');
        })
        .catch(error => {
        });
    },
    getReadableDifficulty(difficulty) {
      switch (difficulty) {
        case "hard":
          return "Difícil";
        case "medium":
          return "Medio";
        case "easy":
          return "Fácil";
        default:
          return difficulty;
      }
    },
    eliminarDatos() {
      this.players = [];
      localStorage.removeItem('players');
    },
    verPerfil(player, index) {
      let playerName = player.name;
      let playerScore = player.score;
      let playerDescripcion = player.descripcion;

      if (player.source === 'database') {
        playerName = player.userId.userName;
        playerScore = player.puntuacion;
        playerDescripcion = player.descripcion;
      }
      this.$router.push({
        name: 'CuentaView',
        params: {
          name: playerName,
          score: playerScore,
          ranking: index + 1,
          descripcion: playerDescripcion
        }
      });

    }
  }
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