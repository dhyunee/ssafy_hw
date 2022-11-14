<template>
  <div>
    <nav-bar v-bind:isLogin="isLogin" v-bind:userInfo="userInfo"></nav-bar
    ><!-- login 여부에 따라 UI 변경, 변경되는 UI에 userProfileImageUrl 사용 -->
    <router-view v-on:call-parent-loginSuccess="loginSuccess"></router-view
    ><!-- LoginPage 또는 BoardMain 컴포넌트가 rendering 경우에는 login 여부를 nav-bar로 전달하기 위해
      $emit으로 부모 App.vue에게 event 와 객체(userInfo)를 전달-->
  </div>
</template>

<script>
import NavBar from "./components/NavBar.vue";

export default {
  name: "App",
  components: {
    NavBar,
  },
  data() {
    return {
      //login 여부
      isLogin: false,
      userInfo: {},
    };
  },
  methods: {
    loginSuccess(userInfo) {
      this.isLogin = true;
      this.userInfo = userInfo;
    },
  },
};
</script>

<style></style>
