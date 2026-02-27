/**
 * Simple proxy server that forwards /homr/* requests to the local HOMR server.
 * This lets the phone reach HOMR through the same tunnel as the Expo dev server.
 *
 * Usage: node proxy.js
 * Runs on port 3001 — the app points to this.
 */
const http = require('http');

const HOMR_HOST = 'localhost';
const HOMR_PORT = 8080;
const PROXY_PORT = 3001;

const server = http.createServer((req, res) => {
  // CORS headers for dev
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', '*');

  if (req.method === 'OPTIONS') {
    res.writeHead(200);
    res.end();
    return;
  }

  // Forward everything to HOMR
  const options = {
    hostname: HOMR_HOST,
    port: HOMR_PORT,
    path: req.url,
    method: req.method,
    headers: { ...req.headers, host: `${HOMR_HOST}:${HOMR_PORT}` },
  };

  const proxy = http.request(options, (proxyRes) => {
    res.writeHead(proxyRes.statusCode, proxyRes.headers);
    proxyRes.pipe(res, { end: true });
  });

  proxy.on('error', (err) => {
    console.error('Proxy error:', err.message);
    res.writeHead(502);
    res.end(JSON.stringify({ error: 'HOMR server unreachable: ' + err.message }));
  });

  req.pipe(proxy, { end: true });
});

server.listen(PROXY_PORT, '0.0.0.0', () => {
  console.log(`HOMR proxy running on http://0.0.0.0:${PROXY_PORT} → http://${HOMR_HOST}:${HOMR_PORT}`);
});
