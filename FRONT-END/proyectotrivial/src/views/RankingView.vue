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
          <th>Ajustes</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(player, index) in players" :key="index">
          <td class="posicion">{{ index + 1 }}</td>
          <td class="fila">{{ getSourceName(player) }}</td>
          <td class="fila">{{ getSourceScore(player) }}</td>
          <td class="fila">{{ player.difficulty }}</td>
          <td>
            <button class="botonPerfil" @click="verPerfil(player, index)">Perfil</button>
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
      puntuacion: "100",
      players: []
    };
  },
  mounted() {
    this.getRanking();

    const savedPlayers = JSON.parse(localStorage.getItem('players'));

    if (savedPlayers) {
      this.players = [...savedPlayers];
    }

    const params = new URLSearchParams(window.location.search);
    const name = params.get('name');
    const score = params.get('score');
    const difficulty = params.get('difficulty');

    if (name && score && difficulty) {
      this.players.push({ name, score, difficulty });
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
    getSourceName(player) {
      return player.source === 'database' ? player.userId.userName : player.name;
    },
    getSourceScore(player) {
      return player.source === 'database' ? player.puntuacion : player.score;
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

      if (player.source === 'database') {
        playerName = player.userId.userName;
        playerScore = player.puntuacion;
      }
      this.$router.push({
        name: 'CuentaView',
        params: {
          name: playerName,
          score: playerScore,
          ranking: index + 1
        }
      });
    }
  }
};
</script>

<style scoped>
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
</style>