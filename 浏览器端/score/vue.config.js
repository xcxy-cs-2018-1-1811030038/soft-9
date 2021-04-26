let proxyObj = {};

proxyObj['/api']={
  // websocket
  ws:false,

  // 代理到哪里去
  target: 'http://localhost:8800',

  // 发送请求时，将请求头中的host改为target值
  changeOrigin: true,

  //不重写请求地址
  pathRewrite: {
    '^/api': '',
  }
}


module.exports = {
  lintOnSave: false,
  devServer: {
    host: 'localhost',
    port: 8080,
    proxy: proxyObj
  },
}