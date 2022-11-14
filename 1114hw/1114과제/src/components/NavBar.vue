<template>
  <!-- 
      router-link를 추가 (isLogin에 따라 링크를 보이고 안보이게 처리)
      user image를 isLogin에 따라 보이고 안보이게 처리
      => image url을 변수로 처리할 경우 생기는 문제에 대응!
      -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <a class="navbar-brand" href="#">
        <img
          src="../assets/noProfile.png"
          style="width: 24px; height: 24px; border-radius: 50%"
        />{{ userInfo.userName }}</a
      >
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link v-show="isLogin" to="/board" class="nav-link">Board</router-link>
          </li>
          <li class="nav-item">
            <router-link v-show="isLogin" to="/board" class="nav-link">My Page</router-link>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" id="btnLogout" href="#">Logout</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  props: ["isLogin", "userInfo"],
};
</script>

<style></style>
