<script setup lang="ts">
import { computed, toRefs } from 'vue'

const props = defineProps({
  symbol: {
    type: String,
    required: true,
  },
})

const { symbol } = toRefs(props)

const message = computed(() => {
  return `Symbol ${symbol.value} not found`
})
</script>

<template>
  <div id="error" class="card">
    <h1>{{ message }}</h1>
  </div>
</template>

<style scoped>
h1 {
  font-size: 32px;
}
#error {
  width: 100%;
  padding: 50px 20px;
  display: flex;
  justify-content: center;
}
</style>
