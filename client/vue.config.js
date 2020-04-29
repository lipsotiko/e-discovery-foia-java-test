module.exports = {
  devServer: {
    port: 8081,
    proxy: {
      '/quote': {
        target: 'http://localhost:8080',
        changeOrigin: true
      },
      '/users/api/read': {
        target: 'http://localhost:5000'
      }
    }
  }
}
