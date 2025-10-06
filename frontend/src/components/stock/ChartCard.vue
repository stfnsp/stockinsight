<script setup lang="ts">
import { computed, toRefs, ref } from 'vue'
import { storeToRefs } from 'pinia'
import { Line } from 'vue-chartjs'
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  type ScriptableContext,
  type ChartOptions,
} from 'chart.js'
import { useCandlestickStore } from '@/stores/candlestick'

const props = defineProps({
  symbol: {
    type: String,
    required: true,
  },
})

const { symbol } = toRefs(props)

const selected = ref(30)

const candlestickStore = useCandlestickStore()
const { candlesticks } = storeToRefs(candlestickStore)

const prices = computed(() => {
  return candlesticks.value[symbol.value]
})

const days = computed(() => {
  const days = prices.value.map((price) => getDate(price.date))
  return days.slice(-selected.value)
})

const close_prices = computed(() => {
  const close = prices.value.map((price) => price.close)
  return close.slice(-selected.value)
})

const points = computed(() => {
  const points = new Array(selected.value).fill(0)
  points[selected.value - 1] = 4
  return points
})

const colors = computed(() => {
  return new Array(selected.value).fill('#3273dc')
})

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend)

const plugin = {
  id: 'vertical',
  defaults: {
    width: 2,
    color: '#c8c8c8',
    dash: [3, 3],
  },
}

const data = computed(() => {
  return {
    labels: days.value,
    datasets: [
      {
        borderColor: 'rgba(50, 115, 220, 0.5)',
        backgroundColor: (context: ScriptableContext<'line'>) => {
          const canvas = context.chart.ctx
          const gradient = canvas.createLinearGradient(0, 0, 0, 300)
          gradient.addColorStop(0, 'rgba(50, 115, 220, 0.3)')
          gradient.addColorStop(1, 'rgba(50, 115, 220, 0)')
          return gradient
        },
        data: close_prices.value,
        pointRadius: points.value,
        pointBackgroundColor: colors.value,
      },
    ],
  }
})

const options: ChartOptions<'line'> = {
  responsive: true,
  maintainAspectRatio: false,
  plugins: {
    legend: {
      display: false,
    },
    tooltip: {
      intersect: false,
      backgroundColor: '#fafafa',
      titleFont: {
        family: 'Montserrat',
        size: 10,
        style: 'normal',
      },
      titleColor: '#2c3e50',
      titleAlign: 'center',
      bodyFont: {
        family: 'Montserrat',
        size: 12,
        style: 'normal',
      },
      bodyColor: '#2c3e50',
      bodyAlign: 'center',
      displayColors: false,
      axis: 'x',
    },
  },
  interaction: {
    mode: 'index' as const,
  },
  hover: {
    intersect: false,
  },
  scales: {
    x: {
      grid: {
        display: false,
      },
    },
    y: {
      position: 'right',
      grid: {
        display: false,
      },
    },
  },
}

function getDate(dateString: string) {
  const options: Intl.DateTimeFormatOptions = { year: 'numeric', month: 'short', day: 'numeric' }
  const date = new Date(dateString)
  return date.toLocaleDateString('en-US', options)
}
</script>

<template>
  <div class="card">
    <div class="title">
      <h1>Chart</h1>
      <hr />
    </div>
    <div id="chart-wrapper">
      <Line :data="data" :options="options" :plugins="[plugin]" />
    </div>
    <div id="selector">
      <button v-bind:class="{ active: selected === 30 }" v-on:click="selected = 30">30D</button>
      <button v-bind:class="{ active: selected === 60 }" v-on:click="selected = 60">60D</button>
      <button v-bind:class="{ active: selected === 90 }" v-on:click="selected = 90">90D</button>
      <button v-bind:class="{ active: selected === 120 }" v-on:click="selected = 120">120D</button>
      <button v-bind:class="{ active: selected === 180 }" v-on:click="selected = 180">180D</button>
      <button v-bind:class="{ active: selected === 240 }" v-on:click="selected = 240">240D</button>
      <button v-bind:class="{ active: selected === 300 }" v-on:click="selected = 300">300D</button>
    </div>
  </div>
</template>

<style scoped>
h1 {
  font-size: 22px;
}
hr {
  width: 100%;
}
.title {
  display: flex;
  flex-direction: column;
  width: 100%;
}
#chart {
  width: 100%;
  padding: 20px;
  display: flex;
  flex-direction: column;
}
#chart-wrapper {
  width: 100%;
  height: 100%;
  min-height: 300px;
  padding: 20px 0 0 0;
}
#selector {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}
#selector > button {
  height: 40px;
  color: #2c3e50;
  padding: 0px 4px;
  border: none;
  background-color: transparent;
  text-decoration: none;
  cursor: pointer;
  font-size: 14px;
  font-weight: 500;
}
#selector > button:hover {
  color: black;
}
#selector > .active {
  color: black;
  border-top: 2px solid transparent;
  border-bottom: 2px solid black;
  font-weight: 700;
}

@media (min-width: 800px) {
  #chart {
    padding: 30px;
  }
}
</style>
