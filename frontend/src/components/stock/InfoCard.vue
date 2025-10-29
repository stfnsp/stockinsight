<script setup lang="ts">
import { computed, toRefs } from 'vue'
import { storeToRefs } from 'pinia'
import { useStockStore } from '@/stores/stock'
import { useCandlestickStore } from '@/stores/candlestick'

const props = defineProps({
  symbol: {
    type: String,
    required: true,
  },
})

const { symbol } = toRefs(props)

const stockStore = useStockStore()
const candlestickStore = useCandlestickStore()
const { stocks } = storeToRefs(stockStore)
const { candlesticks } = storeToRefs(candlestickStore)

const stock = computed(() => {
  return stocks.value.find((stock) => stock.symbol == symbol.value)!
})

const prices = computed(() => {
  return candlesticks.value[symbol.value]
})

const name = computed(() => {
  return `${stock.value.companyName} (${stock.value.symbol})`
})

const price = computed(() => {
  return prices.value[prices.value.length - 1].close.toFixed(2)
})

const change = computed(() => {
  const currentPrice = prices.value[prices.value.length - 1].close
  const previousClose = prices.value[prices.value.length - 2].close
  const value = (currentPrice - previousClose).toFixed(2)
  const percent = (((currentPrice - previousClose) / currentPrice) * 100).toFixed(2)
  const message = Number(value) < 0 ? `${value} (${percent}%)` : `+${value} (+${percent}%)`
  const color = Number(value) < 0 ? '#f72121' : '#19be87'
  return { message, color }
})

const marketCap = computed(() => {
  let marketCap = stock.value.marketCap
  const suffixes = ['', 'K', 'M', 'B', 'T']
  let suffixNum = 0
  while (marketCap >= 1000) {
    marketCap /= 1000
    suffixNum += 1
  }
  return `${marketCap.toPrecision(3)}${suffixes[suffixNum]}`
})

function subscribe() {
  console.log('Button clicked')
}
</script>

<template>
  <div id="info" class="card">
    <div id="display">
      <div id="general">
        <h2>{{ name }}</h2>
        <Transition mode="out-in" name="fade-in">
          <h1 :key="price">
            {{ price }}
            <span id="currency">{{ stock.currency }}</span>
            <span id="change" :style="{ color: change.color }">{{ change.message }}</span>
          </h1>
        </Transition>
      </div>
      <div id="details">
        <div class="section">
          <p>Market Capitalization</p>
          <p>
            <span>{{ marketCap }}</span>
          </p>
        </div>
        <div class="section">
          <p>Region</p>
          <p>
            <span>{{ stock.region }}</span>
          </p>
        </div>
        <div class="section">
          <p>Exchange</p>
          <p>
            <span>{{ stock.exchangeName }}</span>
          </p>
        </div>
        <div class="section">
          <p>Industry</p>
          <p>
            <span>{{ stock.industry }}</span>
          </p>
        </div>
        <div class="section">
          <p>Sector</p>
          <p>
            <span>{{ stock.sector }}</span>
          </p>
        </div>
      </div>
    </div>
    <div id="manage">
      <button id="subscribe" class="button" @click="subscribe">Subscribe</button>
    </div>
  </div>
</template>

<style scoped>
h1 {
  font-size: 32px;
}
h2 {
  font-size: 22px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
h3 {
  font-size: 16px;
  font-weight: 500;
}
p {
  font-size: 12px;
}
p > span {
  font-weight: 700;
  display: inline-block;
  padding-bottom: 5px;
}
#info {
  width: 100%;
  padding: 20px;
  display: flex;
  flex-direction: column;
}
#display {
  width: 100%;
  flex-shrink: 0;
}
#general {
  width: 100%;
}
.fade-in-enter-active {
  transition: opacity 300ms cubic-bezier(0.55, 0.085, 0.68, 0.53);
}

.fade-in-leave-active {
  transition: opacity 225ms cubic-bezier(0.25, 0.46, 0.45, 0.94);
}

.fade-in-enter,
.fade-in-leave-to {
  opacity: 0;
}
#currency {
  font-size: 12px;
}
#change {
  font-weight: 500;
  font-size: 20px;
  padding: 0 0 0 10px;
}
#details {
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-top: 30px;
}
#manage {
  display: flex;
  margin: 20px 0;
  width: 100%;
}
#subscribe {
  height: 40px;
  width: 120px;
  margin: 10px 0 0 0;
  font-size: 14px;
  font-weight: 500;
}

@media (min-width: 800px) {
  #info {
    flex-direction: row;
    padding: 30px;
  }
  #display {
    width: 80%;
    flex-direction: row;
  }
  #manage {
    justify-content: flex-end;
    width: 20%;
  }
  #subscribe {
    margin: 0;
  }
}

@media (min-width: 1200px) {
  #display {
    width: 90%;
  }
  #details {
    flex-direction: row;
  }
  .section {
    width: 25%;
    padding: 0 40px 0 0;
  }
  #manage {
    width: 10%;
  }
}
</style>
