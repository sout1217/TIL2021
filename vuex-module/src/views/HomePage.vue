<template>
  <div>
    <h1>home page</h1>

    <h2>longStateName : {{ longStateName }}</h2>
    <h2>shortStateName : {{ shortStateName }}</h2>
    <h2>longGettersName : {{ longGettersName }}</h2>
    <h2>shortGettersName : {{ GET_NAME }}</h2>

    <hr />

    <h2>auth module token : {{ token }}</h2>
    <h2>auth module client : {{ client }}</h2>

    <hr />
    <h2>namespaceEmail : {{ namespaceEmail }}</h2>
    <h2>GET_PHONE_NUMBER : {{ GET_PHONE_NUMBER }}</h2>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex';
export default {
  name: 'HomePage',
  computed: {
    ...mapState({
      longStateName(state) {
        return state.account.name;
      },
    }),
    ...mapState({
      shortStateName: state => state.account.name,
    }),
    ...mapGetters({
      longGettersName: 'GET_NAME',
    }),
    ...mapGetters(['GET_NAME']),

    ...mapState({
      token: state => state.auth.token,
      client: state => state.auth.client,
    }),

    ...mapGetters({
      namespaceEmail: 'GET_EMAIL',
    }),

    ...mapGetters(['GET_PHONE_NUMBER']),
  },
};
</script>

<style scoped></style>
