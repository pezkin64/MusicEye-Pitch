const { getDefaultConfig } = require('expo/metro-config');
const http = require('http');

const config = getDefaultConfig(__dirname);

// Add .sf2 to the list of asset extensions so Metro can bundle SoundFont files
config.resolver.assetExts.push('sf2');

// Proxy /process and /docs to the local HOMR server (port 8080)
// so the phone can reach HOMR through the same Expo tunnel.
config.server = config.server || {};
const originalMiddleware = config.server.enhanceMiddleware;
config.server.enhanceMiddleware = (middleware, metroServer) => {
  const enhanced = originalMiddleware
    ? originalMiddleware(middleware, metroServer)
    : middleware;

  return (req, res, next) => {
    // Proxy HOMR endpoints
    if (req.url === '/process' || req.url === '/docs' || req.url.startsWith('/process')) {
      const options = {
        hostname: '127.0.0.1',
        port: 8080,
        path: req.url,
        method: req.method,
        headers: { ...req.headers, host: '127.0.0.1:8080' },
      };
      const proxy = http.request(options, (proxyRes) => {
        res.writeHead(proxyRes.statusCode, proxyRes.headers);
        proxyRes.pipe(res, { end: true });
      });
      proxy.on('error', (err) => {
        res.writeHead(502, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify({ error: 'HOMR server unreachable: ' + err.message }));
      });
      req.pipe(proxy, { end: true });
      return;
    }
    enhanced(req, res, next);
  };
};

module.exports = config;
