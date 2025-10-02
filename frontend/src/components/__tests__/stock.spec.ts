import { describe, it, expect } from 'vitest'
import { mount } from '@vue/test-utils'
import ErrorCard from '@/components/stock/ErrorCard.vue'

describe('ErrorCard', () => {
  it('renders properly', () => {
    const wrapper = mount(ErrorCard, { props: { symbol: 'AAPL' } })
    expect(wrapper.text()).toContain('Symbol AAPL not found')
  })
})
