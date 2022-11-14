<template>
  <div class="container">
    <div class="mb-3 mt-3 d-flex justify-content-center">
      <h1>삐따기 세상</h1>
    </div>
    <div class="mb-3">
      <h2>Login</h2>
    </div>
    <div class="mb-3">
      <label for="userEmail" class="form-label">Email</label>
      <input
        type="email"
        class="form-control"
        placeholder="이메일을 입력하세요."
        v-model="userEmail"
      />
    </div>
    <div class="mb-3">
      <label for="userPassword" class="form-label">Password</label>
      <input
        type="password"
        class="form-control"
        placeholder="비밀번호를 입력하세요."
        v-model="userPassword"
      />
    </div>
    <div>
      <button @click="login" type="button" class="btn btn-primary">로그인</button>
    </div>
  </div>
</template>
<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";

Vue.use(VueAlertify);

import http from "@/common/axios.js";

export default {
  data() {
    return {
      userEmail: "admin@admin.com",
      userPassword: "1234",
    };
  },
  methods: {
    async login() {
      try {
        //axios가 json으로 parameter를 보내는 방법? 객체를 전달
        let response = await http.post("/login", {
          userEmail: this.userEmail,
          userPassword: this.userPassword,
        });
        let { data } = response;
        console.log(data);
        //로그인 성공에 대한 처리
        //이벤트를 발생시키고 be에서 받은 user info 전달
        this.$emit("call-parent-loginSuccess", {
          userName: data.userName,
          userProfileImageUrl: data.userProfileImageUrl,
        });
        //BoardMain을 이동
        this.$router.push("board");
      } catch (error) {
        console.error(error);
        if (error.response.status == "404") {
          this.$alertify.error("이메일 또는 비밀번호를 확인하세요");
        } else {
          this.$alertify.error("서버에 문제가 생겼습니다");
        }
      }
    },
  },
};
</script>
<style></style>
