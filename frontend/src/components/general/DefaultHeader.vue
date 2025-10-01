<script setup lang="ts">
import { RouterLink } from 'vue-router'
import SearchBar from '@/components/general/SearchBar.vue'
</script>

<template>
  <header>
    <RouterLink id="home" class="link" :to="{ name: 'home' }">
      <img src="@/assets/png/logo.png" alt="logo" />
      <p>Stockinsight</p>
    </RouterLink>
    <SearchBar />
    <RouterLink id="login-link" class="button" :to="{ name: 'home' }">Sign In</RouterLink>
  </header>
</template>

<style scoped>
header {
  height: 60px;
  width: 100%;
  padding: 0 10px 0 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  position: fixed;
  top: 0;
  z-index: 1;
  background: #ffffff;
  box-shadow: 0 2px 4px -2px rgba(175, 175, 175, 0.5);
}
.link {
  text-decoration: none;
}
#home {
  display: flex;
  align-items: center;
}

#home > img {
  height: 30px;
  margin: 0 20px 0 0;
}
#home > p {
  display: none;
  font-size: 20px;
  color: #5f6368;
}
#login-link {
  height: 35px;
  width: 80px;
  margin: 0 0px 0 20px;
  font-size: 14px;
  font-weight: 500;
}

@media (min-width: 800px) {
  header {
    padding: 0 16px 0 32px;
  }
  #home {
    margin: 0 80px 0 0;
  }

  #home > img {
    margin: 0 10px 0 0;
  }
  #home > p {
    display: block;
  }
  #login-link {
    width: 130px;
    margin: 0 0px 0 80px;
  }
}
</style>
