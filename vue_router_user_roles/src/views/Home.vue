<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <div>
      <button @click="setRole({ role: 'user', level: 1 })">
        유저권한 얻기
      </button>
      <button @click="setRole({ role: 'user', level: 2 })">
        유저 실버권한 얻기
      </button>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
  components: {},
  methods: {
    setRole(role) {
      this.$user.set(role);
    },
  },
};
</script>
