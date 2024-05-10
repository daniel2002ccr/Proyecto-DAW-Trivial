<template>
  <nav>
    <router-link to="/" class="link-hover"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"
        class="imagen">
        <path fill="#ffffff"
          d="M280.4 148.3L96 300.1V464a16 16 0 0 0 16 16l112.1-.3a16 16 0 0 0 15.9-16V368a16 16 0 0 1 16-16h64a16 16 0 0 1 16 16v95.6a16 16 0 0 0 16 16.1L464 480a16 16 0 0 0 16-16V300L295.7 148.3a12.2 12.2 0 0 0 -15.3 0zM571.6 251.5L488 182.6V44.1a12 12 0 0 0 -12-12h-56a12 12 0 0 0 -12 12v72.6L318.5 43a48 48 0 0 0 -61 0L4.3 251.5a12 12 0 0 0 -1.6 16.9l25.5 31A12 12 0 0 0 45.2 301l235.2-193.7a12.2 12.2 0 0 1 15.3 0L530.9 301a12 12 0 0 0 16.9-1.6l25.5-31a12 12 0 0 0 -1.7-16.9z" />
      </svg>Inicio</router-link> |

    <router-link to="/ranking" class="link-hover"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"
        class="imagen">
        <path fill="#ffffff"
          d="M223.8 130.8L154.6 15.5A32 32 0 0 0 127.2 0H16C3.1 0-4.5 14.6 2.9 25.2l111.3 159c29.7-27.8 67.5-46.8 109.6-53.4zM496 0H384.8c-11.2 0-21.7 5.9-27.4 15.5l-69.1 115.2c42 6.6 79.8 25.6 109.6 53.4L509.1 25.2C516.5 14.6 508.9 0 496 0zM256 160c-97.2 0-176 78.8-176 176s78.8 176 176 176 176-78.8 176-176-78.8-176-176-176zm92.5 157.3l-37.9 37 9 52.2c1.6 9.4-8.3 16.5-16.7 12.1L256 393.9l-46.9 24.7c-8.4 4.5-18.3-2.7-16.7-12.1l9-52.2-37.9-37c-6.8-6.6-3.1-18.2 6.4-19.6l52.4-7.6 23.4-47.5c2.1-4.3 6.2-6.4 10.3-6.4 4.1 0 8.2 2.1 10.3 6.4l23.4 47.5 52.4 7.6c9.4 1.4 13.2 13 6.4 19.6z" />
      </svg>Ranking</router-link> |
    <router-link to="/cuenta" class="link-hover"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"
        class="imagen">
        <path fill="#ffffff"
          d="M224 256c70.7 0 128-57.3 128-128S294.7 0 224 0 96 57.3 96 128s57.3 128 128 128zm89.6 32h-16.7c-22.2 10.2-46.9 16-72.9 16s-50.6-5.8-72.9-16h-16.7C60.2 288 0 348.2 0 422.4V464c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48v-41.6c0-74.2-60.2-134.4-134.4-134.4z" />
      </svg>Cuenta</router-link>

  </nav>
</template>

<style scoped>
.link-hover:hover svg path {
  fill: #000000;
}

.link-hover:hover {
  color: #000000;
}

label {
  margin-left: 2%;

}

.imagen {
  height: 20px;
  width: 20px;
  margin-right: 1%;
}

.imagen path:hover {
  fill: #000000;
  background-color: white;
}

nav {
  padding-top: 1%;
  padding-left: 36%;
  background-color: orange;
  padding-bottom: 1%;
  font-size: 30px;
}

a.router-link-active.router-link-exact-active {
  text-decoration: none;
  color: white;
  font-size: 30px;
  margin-top: 10px;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 250px;
  padding-bottom: 20px;
  font-size: 27px;
}

a.router-link-active.router-link-exact-active:hover {
  text-decoration: none;
  color: black;
  background-color: white;
  padding-bottom: 6px;
  padding-top: 1px;
  border-radius: 9999px;
}

a {
  text-decoration: none;
  color: white;
  font-size: 27px;
  margin-top: 10px;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 20px;
  padding-bottom: 20px;
}

a:hover {
  text-decoration: none;
  color: black;
  background-color: white;
  padding-bottom: 6px;
  padding-top: 1px;
  border-radius: 9999px;
}
</style>