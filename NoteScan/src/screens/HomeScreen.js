import React, { useState, useEffect, useMemo } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  StatusBar,
} from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { Feather } from '@expo/vector-icons';
import { WebView } from 'react-native-webview';
import { OMRSettings } from '../services/OMRSettings';
import { getStatusBarStyleForTheme } from '../theme/themes';
import { buildThemedLogoHtml } from '../utils/logoTheme';

const defaultPalette = {
  background: '#F9F7F1',
  surface: '#FBFAF5',
  surfaceStrong: '#F1EEE4',
  border: '#D6D0C4',
  ink: '#3E3C37',
  inkMuted: '#6E675E',
  accent: '#E05A2A',
  success: '#5CB85C',
  danger: '#D9534F',
};

export const HomeScreen = ({ onNavigate, onPickFromGallery, onPickFromCamera, onPickFromFiles, goForwardState, onGoForward, theme }) => {
  const [serverStatus, setServerStatus] = useState(null);
  const insets = useSafeAreaInsets();
  const palette = { ...defaultPalette, ...(theme || {}) };
  const animatedLogoHtml = useMemo(() => buildThemedLogoHtml(palette), [palette]);

  useEffect(() => {
    OMRSettings.load().then(() => {
      checkServer();
    });
  }, []);

  const checkServer = async () => {
    setServerStatus(null);
    const service = OMRSettings.getService();
    const result = await service.checkHealth();
    setServerStatus(result.ok);
  };

  const statusLabel =
    serverStatus === null ? 'Checking Music eye...' : serverStatus ? 'Music eye connected' : 'Music eye unreachable';

  return (
    <View style={[styles.container, { backgroundColor: palette.background }]}>
      <StatusBar barStyle={getStatusBarStyleForTheme({ colors: palette })} backgroundColor={palette.background} />

      <View
        style={[
          styles.header,
          {
            paddingTop: insets.top + 24,
            paddingLeft: 24 + insets.left,
            paddingRight: 24 + insets.right,
          },
        ]}
      >
        <View style={styles.headerTop}>
          <View style={styles.headerBrand}>
            <View style={styles.logoWrap}>
              <WebView
                originWhitelist={["*"]}
                source={{ html: animatedLogoHtml }}
                style={styles.logoWebView}
                scrollEnabled={false}
                showsHorizontalScrollIndicator={false}
                showsVerticalScrollIndicator={false}
              />
            </View>
            <Text style={[styles.title, { color: palette.ink }]}>Music eye</Text>
          </View>

          {goForwardState && typeof onGoForward === 'function' && (
            <TouchableOpacity style={styles.goForwardTopLink} onPress={onGoForward}>
              <Text style={[styles.goForwardTopText, { color: palette.inkMuted }]}>Return →</Text>
            </TouchableOpacity>
          )}
        </View>
        <Text style={[styles.subtitle, { color: palette.inkMuted }]}>Powered by AI • scan and play in seconds</Text>

        {/* Engine / status indicator */}
        <TouchableOpacity style={styles.statusRow} onPress={checkServer}>
          <View style={[
            styles.statusDot,
            serverStatus === null ? styles.statusChecking :
            serverStatus ? styles.statusOk : styles.statusDown,
          ]} />
          <Text style={[styles.statusText, { color: palette.inkMuted }]}>{statusLabel}</Text>
          <Feather name="repeat" size={12} color={palette.inkMuted} style={{ marginLeft: 4 }} />
        </TouchableOpacity>
      </View>

      <View style={[styles.content, { paddingLeft: 24 + insets.left, paddingRight: 24 + insets.right }]}>
        <View style={styles.buttonContainer}>
          {/* Scan from Camera */}
          <TouchableOpacity
            style={[styles.actionButton, { backgroundColor: palette.background, borderColor: palette.border }]}
            onPress={onPickFromCamera}
          >
            <View style={[styles.buttonIconWrap, { borderColor: palette.border, backgroundColor: palette.surfaceStrong }]}> 
              <Feather name="camera" size={20} color={palette.ink} />
            </View>
            <Text style={[styles.buttonTitle, { color: palette.ink }]}>Scan from Camera</Text>
          </TouchableOpacity>

          {/* Upload Image */}
          <TouchableOpacity
            style={[styles.actionButton, { backgroundColor: palette.background, borderColor: palette.border }]}
            onPress={onPickFromGallery}
          >
            <View style={[styles.buttonIconWrap, { borderColor: palette.border, backgroundColor: palette.surfaceStrong }]}>
              <Feather name="image" size={20} color={palette.ink} />
            </View>
            <Text style={[styles.buttonTitle, { color: palette.ink }]}>Scan from Photos</Text>
          </TouchableOpacity>

          {/* Upload PDF/Files */}
          <TouchableOpacity
            style={[styles.actionButton, { backgroundColor: palette.background, borderColor: palette.border }]}
            onPress={onPickFromFiles}
          >
            <View style={[styles.buttonIconWrap, { borderColor: palette.border, backgroundColor: palette.surfaceStrong }]}>
              <Feather name="download" size={20} color={palette.ink} />
            </View>
            <Text style={[styles.buttonTitle, { color: palette.ink }]}>Scan from Files</Text>
          </TouchableOpacity>

          {/* Browse Scanned Music */}
          <TouchableOpacity
            style={[styles.actionButton, { backgroundColor: palette.background, borderColor: palette.border }]}
            onPress={() => onNavigate('library')}
          >
            <View style={[styles.buttonIconWrap, { borderColor: palette.border, backgroundColor: palette.surfaceStrong }]}>
              <Feather name="music" size={20} color={palette.ink} />
            </View>
            <Text style={[styles.buttonTitle, { color: palette.ink }]}>Browse Scanned Music</Text>
          </TouchableOpacity>
        </View>
      </View>

      {/* Footer Actions */}
      <View
        style={[
          styles.footerContainer,
          {
            paddingBottom: Math.max(16, insets.bottom + 10),
            paddingLeft: 24 + insets.left,
            paddingRight: 24 + insets.right,
          },
        ]}
      >
        <View style={styles.footer}>
          <TouchableOpacity
            style={styles.footerAction}
            onPress={() => onNavigate('settings')}
          >
            <Feather name="settings" size={20} color={palette.ink} />
            <Text style={[styles.footerText, { color: palette.inkMuted }]}>Settings</Text>
          </TouchableOpacity>
          <TouchableOpacity
            style={styles.footerAction}
            onPress={() => onNavigate('help')}
          >
            <Feather name="help-circle" size={20} color={palette.ink} />
            <Text style={[styles.footerText, { color: palette.inkMuted }]}>Help</Text>
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F9F7F1',
  },
  header: {
    paddingBottom: 28,
    paddingHorizontal: 24,
    gap: 2,
  },
  headerTop: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  headerBrand: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4,
  },
  logoWrap: {
    width: 74,
    height: 52,
    overflow: 'hidden',
    borderRadius: 8,
  },
  logoWebView: {
    width: 74,
    height: 52,
    backgroundColor: 'transparent',
  },
  title: {
    fontSize: 36,
    lineHeight: 40,
    fontWeight: '800',
    color: '#3E3C37',
    letterSpacing: -0.5,
  },
  subtitle: {
    fontSize: 14,
    color: '#6E675E',
    letterSpacing: 0.2,
    marginTop: -1,
    marginLeft: 16,
  },
  statusRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 4,
    marginLeft: 16,
  },
  statusDot: {
    width: 8,
    height: 8,
    borderRadius: 4,
    marginRight: 8,
  },
  statusChecking: {
    backgroundColor: '#F0AD4E',
  },
  statusOk: {
    backgroundColor: '#5CB85C',
  },
  statusDown: {
    backgroundColor: '#D9534F',
  },
  statusText: {
    fontSize: 12,
    color: '#6E675E',
    fontWeight: '600',
  },
  goForwardTopLink: {
    paddingVertical: 2,
    paddingHorizontal: 2,
  },
  goForwardTopText: {
    fontSize: 14,
    color: '#6E675E',
    fontWeight: '600',
  },
  content: {
    flex: 1,
    paddingHorizontal: 24,
    paddingBottom: 20,
  },
  buttonContainer: {
    marginTop: 8,
    marginBottom: 28,
    gap: 12,
  },
  actionButton: {
    backgroundColor: '#FBFAF5',
    borderRadius: 14,
    borderWidth: 2,
    borderColor: '#D6D0C4',
    paddingVertical: 12,
    paddingHorizontal: 14,
    flexDirection: 'row',
    alignItems: 'center',
    gap: 10,
  },
  buttonIconWrap: {
    width: 30,
    height: 30,
    borderRadius: 10,
    borderWidth: 2,
    borderColor: '#D6D0C4',
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#F1EEE4',
  },
  buttonTitle: {
    fontSize: 14,
    fontWeight: '700',
    color: '#3E3C37',
    textTransform: 'uppercase',
    letterSpacing: 0.6,
  },
  footerContainer: {
    paddingHorizontal: 24,
    paddingBottom: 24,
    paddingTop: 8,
  },
  footer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  footerAction: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
  },
  footerText: {
    fontSize: 14,
    fontWeight: '600',
    color: '#5C574E',
  },
});
