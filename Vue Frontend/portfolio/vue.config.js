const { defineConfig } = require('@vue/cli-service')


module.exports = {
  publicPath: process.env.NODE_ENV === 'production' ? '/' : '/',
  devServer: {
    proxy: {
      '^/api': {
        target: process.env.VUE_APP_API_URL || 'http://localhost:8080',
        changeOrigin: true
      }
    }
  }
}
  