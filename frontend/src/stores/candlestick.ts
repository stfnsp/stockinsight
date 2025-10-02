import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import type Candlestick from '@/models/Candlestick'

export const useCandlestickStore = defineStore('candlestick', () => {
  const candlesticks = ref<Record<string, Candlestick[]>>({})

  async function loadCandlesticks(symbol: string) {
    try {
      const response = await axios.get(`${import.meta.env.VITE_API_URL}/candlesticks/${symbol}`)
      candlesticks.value[symbol] = response.data
    } catch (e) {
      candlesticks.value[symbol] = []
      console.error(`Could not load candlesticks for ${symbol}`, e)
    }
  }

  return { candlesticks, loadCandlesticks }
})
