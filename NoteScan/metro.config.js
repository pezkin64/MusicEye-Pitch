const { getDefaultConfig } = require('expo/metro-config');
const http = require('http');

const config = getDefaultConfig(__dirname);

// Add .sf2 to the list of asset extensions so Metro can bundle SoundFont files
config.resolver.assetExts.push('sf2');

// Proxy /audiveris/* to the Audiveris server (port 8082)
// so the phone can reach it through the Expo tunnel.
config.server = config.server || {};
const originalMiddleware = config.server.enhanceMiddleware;
config.server.enhanceMiddleware = (middleware, metroServer) => {
  const enhanced = originalMiddleware
    ? originalMiddleware(middleware, metroServer)
    : middleware;

  return (req, res, next) => {
    // Proxy Audiveris endpoints:  /audiveris/process → localhost:8082/process
    if (req.url.startsWith('/audiveris/')) {
      const targetPath = req.url.replace('/audiveris', '');
      const options = {
        hostname: '127.0.0.1',
        port: 8082,
        path: targetPath || '/',
        method: req.method,
        headers: { ...req.headers, host: '127.0.0.1:8082' },
      };
      const proxy = http.request(options, (proxyRes) => {
        res.writeHead(proxyRes.statusCode, proxyRes.headers);
        proxyRes.pipe(res, { end: true });
      });
      proxy.on('error', (err) => {
        res.writeHead(502, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify({ error: 'Audiveris server unreachable: ' + err.message }));
      });
      req.pipe(proxy, { end: true });
      return;
    }
    enhanced(req, res, next);
  };
};

module.exports = config;
