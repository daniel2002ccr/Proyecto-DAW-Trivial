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
          <td class="fila">{{ player.name }}</td>
          <td class="fila">{{ player.score }}</td>
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

export default {

  data() {
    return {
      players: []
    };
  },
  mounted() {
    // Recuperar datos del almacenamiento local al iniciar el componente
    const savedPlayers = JSON.parse(localStorage.getItem('players'));
    if (savedPlayers) {
      // Copiar los jugadores guardados en el almacenamiento local en la lista de jugadores
      this.players = [...savedPlayers];
    }

    // Verificar si hay nuevos datos en la URL y añadirlos
    const params = new URLSearchParams(window.location.search);
    const name = params.get('name');
    const score = params.get('score');
    const difficulty = params.get('difficulty');

    if (name && score && difficulty) {
      // Añadir los nuevos datos a la lista de jugadores
      this.players.push({ name, score, difficulty });
    }

    // Ordenar los jugadores por puntuación y luego por dificultad
    this.players.sort((a, b) => {
      // Si la puntuación es diferente, ordena por puntuación
      if (b.score !== a.score) {
        return b.score - a.score; // Orden descendente por puntuación
      } else {
        // Si la puntuación es igual, ordena por dificultad
        const difficultyOrder = { "hard": 3, "medium": 2, "easy": 1 };
        return difficultyOrder[b.difficulty] - difficultyOrder[a.difficulty];
      }
    });

    // Convertir los valores de dificultad a texto legible
    this.players.forEach(player => {
      player.difficulty = this.getReadableDifficulty(player.difficulty);
    });

    // Guardar los jugadores actualizados en localStorage
    localStorage.setItem('players', JSON.stringify(this.players));
  },
  methods: {
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
      // Eliminar todos los datos de la lista de jugadores
      this.players = [];

      // Limpiar también el almacenamiento local
      localStorage.removeItem('players');
    },
    verPerfil(player, index) {
      this.$router.push({
        name: 'CuentaView',
        params: {
          name: player.name,
          score: player.score,
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