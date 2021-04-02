<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/guest">Guest</router-link> |
      <router-link to="/user">User</router-link> |
      <router-link to="/silver">Silver</router-link> |
      <router-link to="/admin">Admin</router-link>
    </div>
    <div>현재 권한은 {{ $user.get().role }} 입니다</div>
    <div>현재 레벨은 {{ $user.get().level }} 입니다</div>
    <router-view />
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
