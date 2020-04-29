<template>
  <div>
    <div v-for="(quote, index) in quotesData.quotes" :key="index" class="quote">
      <div>
        <label>Original Quote: </label>
        <span>{{quote.quoteText}}</span>
      </div>
      <hr />
      <div>
        <label>Quote Missing the 3rd and 5th Sentences: </label>
        <span>{{quote.quoteMissingSentence3and5}}</span>
      </div>
      <hr />
      <div class="stats">
        <div>
          <label>Required Counts: </label>
          <ul>
            <li v-for="(count, word) in quote.wordCounts" :key="word">
              <label>{{word}}</label>: {{count}}</li>
          </ul>
        </div>
        <div v-for="(posVal, posKey) in quote.posCounts" :key="posKey">
          <label>{{posKey}}(s)</label>: <span>{{posVal.count}}</span>
          <ul>
              <li v-for="(v, k, i) in posVal.words" :key="i">
                <label>{{k}}: </label><span>{{v}}</span>
              </li>
            </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  props: ['repository'],
  data: () => ({
    quotesData: {},
    newQuote: null
  }),
  async created () {
    const quotesData = await this.repository._getQuotes()
    this.quotesData = quotesData
  }
}
</script>
<style scoped>
.quote {
  border: .5px solid black;
  margin: 24px;
  padding: 12px;
}

label {
  font-weight: bold;
}

.stats {
  display: flex;
}

.stats div {
  margin: 0px 22px 0px 16px;
}

textarea {
  width: 100%;
}
</style>
