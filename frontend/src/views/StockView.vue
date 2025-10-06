<script setup lang="ts">
import InfoCard from '@/components/stock/InfoCard.vue'
import ChartCard from '@/components/stock/ChartCard.vue'
import ErrorCard from '@/components/stock/ErrorCard.vue'

import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { storeToRefs } from 'pinia'

import { useStockStore } from '@/stores/stock'
import { useCandlestickStore } from '@/stores/candlestick'

const route = useRoute()
const symbol = route.params.symbol as string

const stockStore = useStockStore()
const candlestickStore = useCandlestickStore()
const { stocks } = storeToRefs(stockStore)
const { candlesticks } = storeToRefs(candlestickStore)
stockStore.loadStocks()
candlestickStore.loadCandlesticks(symbol)

const stock = computed(() => {
  try {
    return stocks.value.find((stock) => stock.symbol.toUpperCase() == symbol.toUpperCase())
  } catch {
    return undefined
  }
})

const prices = computed(() => {
  try {
    return candlesticks.value[symbol]
  } catch {
    return undefined
  }
})

const status = computed(() => {
  if (stock.value === undefined || prices.value === undefined) {
    return 'loading'
  }
  if (Object.keys(stock.value).length === 0 || Object.keys(prices.value).length === 0) {
    return 'error'
  }
  return 'loaded'
})
</script>

<template>
  <div id="stock">
    <main>
      <div class="container">
        <InfoCard v-if="status === 'loaded'" :symbol="symbol" id="info" />
        <ChartCard v-if="status === 'loaded'" :symbol="symbol" id="chart" />
        <ErrorCard v-if="status === 'error'" :symbol="symbol" id="error" />
      </div>
    </main>
  </div>
</template>

<style scoped>
#info {
  margin: 60px 0 0 0;
}
#chart {
  margin: 60px 0 0 0;
}
#message {
  margin: 60px 0 0 0;
}
#error {
  margin: 60px 0 0 0;
}
</style>
