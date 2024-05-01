<template>
  <div class="ranking">
    <h1>RANKING</h1>
    <table>
      <thead>
        <tr>
          <th>Posición</th>
          <th>Nombre</th>
          <th>Puntuación</th>
          <!--  <th>Partidas</th> -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="(player, index) in players" :key="index">
          <td>{{ index + 1 }}</td>
          <td>{{ player.data.name }}</td>
          <td>{{ player.data.score }}</td>
          <!-- <td>{{ player.games }}</td -->
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
  methods: {
    saveScoreAndName(data) {
      console.log(this.players);
      // Método para guardar la puntuación y el nombre en el arreglo de jugadores
      this.players.push({ name: data.name, score: data.score });
      console.log(this.players);
    }
  }
};
</script>

<style scoped>
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