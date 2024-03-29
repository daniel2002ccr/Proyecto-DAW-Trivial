<template>
  <div class="ranking">
    <h1>RANKING</h1>
    <table>
      <thead>
        <tr>
          <th>Posición</th>
          <th>Nombre</th>
          <th>Puntuación</th>
          <th>Partidas</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>nombre1</td>
          <td>puntuacion1</td>
          <td>partidas1</td>
        </tr>
        <tr>
          <td>2</td>
          <td>nombre2</td>
          <td>puntuacion2</td>
          <td>partidas2</td>
        </tr>
        <tr>
          <td>3</td>
          <td>nombre3</td>
          <td>puntuacion3</td>
          <td>partidas3</td>
        </tr>
        <tr>
          <td>4</td>
          <td>nombre4</td>
          <td>puntuacion4</td>
          <td>partidas4</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

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