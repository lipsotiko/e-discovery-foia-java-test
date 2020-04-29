const axios = require('axios')

export default class Repository {
  async _getQuotes () {
    const quotesResponse = await axios.get('/quote?page=0')
    return {
      quotes: quotesResponse.data._embedded.quote,
      page: quotesResponse.data.page
    }
  }
}
