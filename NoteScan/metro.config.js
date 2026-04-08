const { getDefaultConfig } = require('expo/metro-config');

const config = getDefaultConfig(__dirname);

// Add custom binary/text runtime assets so Metro bundles them instead of parsing as JS.
config.resolver.assetExts.push('sf2', 'txt');

module.exports = config;
