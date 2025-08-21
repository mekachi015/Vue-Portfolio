const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  devServer: {
    host: '192.168.0.151',
    port: 8080,
    allowedHosts: 'all'
  }
})
