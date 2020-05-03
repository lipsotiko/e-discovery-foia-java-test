<template>
  <div>
    <div class="new-quote-panel">
      <textarea v-model="newQuoteText" rows="5"></textarea>
      <button @click="addQuote" type="submit">+ QUOTE</button>
    </div>
    <div v-for="(quote, index) in quotesData.quotes" :key="index" class="quote-content">
      <div class="quote">{{quote.quoteText}}</div>
      <details>
        <summary>Results</summary>
        <div class="stats">
          <div class="stat-detail">
            <label>Quote Missing the 3rd and 5th Sentences:</label>
            <div>{{quote.quoteMissingSentence3and5}}</div>
          </div>
          <div class="stat-detail">
            <label>Required Counts:</label>
            <ul>
              <li v-for="(count, word) in quote.wordCounts" :key="word">
                <label>{{word}}</label>: {{count}}</li>
            </ul>
          </div>
          <div v-for="(posVal, posKey) in quote.posCounts" :key="posKey" class="stat-detail">
            <label>{{posKey}}(s)</label>: <span>{{posVal.count}}</span>
            <ul>
                <li v-for="(v, k, i) in posVal.words" :key="i">
                  <label>{{k}}: </label><span>{{v}}</span>
                </li>
              </ul>
          </div>
        </div>
      </details>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  props: ['repository'],
  data: () => ({
    quotesData: {},
    newQuoteText: null
  }),
  async created () {
    const quotesData = await this.repository._getQuotes()
    this.quotesData = quotesData
  },
  methods: {
    async addQuote () {
      const newQuote = await this.repository._addQuote(this.newQuoteText)
      this.quotesData.quotes.push(newQuote)
      this.newQuoteText = null
    }
  }
}
</script>
<style scoped>
.quote {
  padding: 20px;
}

label {
  font-weight: bold;
}

.quote-content {
  border: .5px solid black;
  margin: 24px;
  padding: 12px;
}

.new-quote-panel {
  padding: 24px;
}

.stats {
  display: flex;
}

.stat-detail {
  width: 222px;
  margin: 0px 22px 0px 16px;
  padding: 12px 0 0 32px;
}

textarea {
  width: 100%;
}
</style>
