const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  devServer: {
    host: '192.168.0.151',
    port: 8080,
    allowedHosts: 'all',
    proxy: {
      '/api' : {
        target: "http://localhost:8081", // Backend server URL
        changeOrigin: true,
        secure: false,
        logLevel: 'debug',
        pathRewrite: {
          '^/api': '/api' // Remove /api prefix when forwarding to backend
        },
        onProxyReq: (proxyReq, req, res) => {
          console.log('Proxying request:', req.url);

          proxyReq.setHeader('Origin', 'http://localhost:8080')
        }
      }
    }
  }

})
