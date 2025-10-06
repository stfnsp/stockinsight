<script setup lang="ts">
import { computed, ref, onUnmounted, useTemplateRef } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useStockStore } from '@/stores/stock'

const router = useRouter()
const route = useRoute()

const symbol = ref('')
const display = ref(false)

const searchInput = useTemplateRef('search-input')

const stockStore = useStockStore()
stockStore.loadStocks()

const stocks = computed(() => {
  return stockStore.searchList.filter((stock) =>
    stock.text.toUpperCase().includes(symbol.value.toUpperCase()),
  )
})

function searchSymbol() {
  symbol.value = symbol.value.trim()
  if (symbol.value.length > 0) {
    if (route.name === 'stock') {
      const currentSymbol = route.params.symbol as string
      if (symbol.value.toUpperCase() !== currentSymbol.toUpperCase()) {
        router.push({ name: 'stock', params: { symbol: symbol.value.toUpperCase() } })
      }
    } else {
      router.push({ name: 'stock', params: { symbol: symbol.value.toUpperCase() } })
    }
  }
}

function symbolSelected(selectedSymbol: string) {
  symbol.value = selectedSymbol
  searchSymbol()
}

function documentClick(e: MouseEvent) {
  const el = searchInput.value
  const { target } = e
  if (el !== target && !el?.contains(target as Node)) {
    display.value = false
  }
}

onUnmounted(() => {
  document.removeEventListener('click', documentClick)
})

document.addEventListener('click', documentClick)
</script>

<template>
  <form @submit.prevent="searchSymbol">
    <div id="input-bar">
      <input
        id="search-input"
        v-model="symbol"
        @focus="display = true"
        autocomplete="off"
        autocorrect="off"
        autocapitalize="off"
        spellcheck="false"
        placeholder="Enter your Symbol"
        ref="search-input"
      />
      <div v-show="display" id="suggestions">
        <li v-for="stock in stocks" :key="stock.symbol" v-on:click="symbolSelected(stock.symbol)">
          {{ stock.text }}
        </li>
      </div>
    </div>
    <button id="search-button">
      <img src="@/assets/svg/search.svg" alt="search" />
    </button>
  </form>
</template>

<style scoped>
form {
  width: 100%;
  height: 35px;
  display: flex;
  align-items: center;
  font-family: inherit;
  font-size: 14px;
  font-weight: 500;
}
#input-bar {
  width: 100%;
  position: relative;
}
#input-bar > input {
  height: 100%;
  width: 100%;
  padding: 9px 13px;
  color: #80868b;
  background: #f1f3f4;
  font-family: inherit;
  font-size: 14px;
  font-weight: 500;
  border: none;
  border-radius: 4px 0 0 4px;
}
#input-bar > input:focus {
  outline: none;
}

#suggestions {
  position: absolute;
  width: 100%;
  max-height: 300px;
  color: #80868b;
  background: #ffffff;
  font-family: inherit;
  font-size: 14px;
  font-weight: 500;
  overflow-y: auto;
  box-shadow: 0px 1px 10px 1px rgba(210, 210, 210, 0.15);
}
#suggestions > li {
  list-style: none;
  padding: 9px 13px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  cursor: pointer;
}
#suggestions > li:hover {
  background: #efefef;
}

#search-button {
  height: 100%;
  flex-shrink: 0;
  padding: 10px;
  border-radius: 0 4px 4px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  color: #ffffff;
  background: #1a73e8;
  border: none;
}
#search-button > img {
  height: 100%;
}

#search-button:hover {
  background: #1a62e8;
}
</style>
