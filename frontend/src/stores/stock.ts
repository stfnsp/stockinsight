import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import type Stock from '@/models/Stock'

export const useStockStore = defineStore('stock', () => {
  const stocks = ref<Stock[]>([])
  const searchList = computed(() =>
    stocks.value.map((stock) => ({
      symbol: stock.symbol,
      text: `${stock.company_name} (${stock.symbol})`,
    })),
  )

  async function loadStocks() {
    try {
      const response = await axios.get(`${import.meta.env.VITE_API_URL}/stocks`)
      stocks.value = response.data
    } catch (e: unknown) {
      console.error('Could not load stocks', e)
    }
  }

  return { stocks, searchList, loadStocks }
})
