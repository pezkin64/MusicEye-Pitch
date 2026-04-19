import React, { useMemo, useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  TextInput,
  Alert,
  ActivityIndicator,
  Platform,
  StatusBar,
  KeyboardAvoidingView,
  ScrollView,
  Image,
  Modal,
  Dimensions,
} from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import * as ImagePicker from 'expo-image-picker';
import { Feather } from '@expo/vector-icons';
import { WebView } from 'react-native-webview';
import { ZemskyEmulatorService } from '../services/ZemskyEmulatorService';
import { OMRSettings } from '../services/OMRSettings';
import { OMRCacheService } from '../services/OMRCacheService';
import { LIGHT_THEME_OPTIONS, getStatusBarStyleForTheme } from '../theme/themes';
import { buildThemedLogoHtml } from '../utils/logoTheme';

const palette = {
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

export const SettingsScreen = ({ onNavigateBack, theme, themeId, onThemeChange }) => {
  const [engine, setEngine] = useState(OMRSettings.getEngine());
  const [fullscreenImage, setFullscreenImage] = useState(null);
  const insets = useSafeAreaInsets();
  const appTheme = { ...palette, ...(theme || {}) };
  const animatedLogoHtml = useMemo(() => buildThemedLogoHtml(appTheme), [appTheme]);

  React.useEffect(() => {
    OMRSettings.load().then(() => {
      setEngine(OMRSettings.getEngine());
    });
  }, []);

  const selectEngine = async (eng) => {
    await OMRSettings.setEngine(eng);
    setEngine(eng);
  };

  const handleClearCache = async () => {
    Alert.alert(
      'Clear OMR Cache?',
      'This will delete all cached OMR scan results. You\'ll need to rescan images, but it will use the latest fixes.',
      [
        { text: 'Cancel', style: 'cancel' },
        {
          text: 'Clear',
          style: 'destructive',
          onPress: async () => {
            try {
              await OMRCacheService.clearAll();
              Alert.alert('Success', 'OMR cache cleared. Next scan will use updated parser.');
            } catch (err) {
              Alert.alert('Error', 'Failed to clear cache: ' + (err.message || err));
            }
          }
        }
      ]
    );
  };

  return (
    <KeyboardAvoidingView
      style={[styles.container, { backgroundColor: appTheme.background }]}
      behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
    >
      <StatusBar barStyle={getStatusBarStyleForTheme({ colors: appTheme })} backgroundColor={appTheme.background} />

      <View
        style={[
          styles.header,
          {
            paddingTop: insets.top + 16,
            paddingLeft: 24 + insets.left,
            paddingRight: 24 + insets.right,
            backgroundColor: appTheme.background,
          },
        ]}
      >
        <TouchableOpacity onPress={onNavigateBack}>
          <Text style={[styles.linkText, { color: appTheme.inkMuted }]}>← Back</Text>
        </TouchableOpacity>
        <View style={styles.titleRow}>
          <Text style={[styles.title, { color: appTheme.ink }]}>Settings</Text>
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
        </View>
        <View style={{ width: 68 }} />
      </View>

      <ScrollView
        style={[
          styles.scrollContent,
          { paddingLeft: 24 + insets.left, paddingRight: 24 + insets.right },
        ]}
        contentContainerStyle={{ paddingBottom: insets.bottom + 24 }}
      >
        {/* OMR Engine Info */}
        <View style={styles.section}>
          <View style={styles.sectionHeader}>
            <Feather name="cpu" size={18} color={palette.ink} />
            <Text style={[styles.sectionTitle, { color: appTheme.ink }]}>OMR Engine</Text>
          </View>

          <View style={styles.engineRow}>
            <TouchableOpacity
              style={[styles.engineCard, engine === 'zemsky' && styles.engineCardActive]}
              onPress={() => selectEngine('zemsky')}
            >
              <View style={styles.engineHeader}>
                <View style={[styles.radioOuter, engine === 'zemsky' && styles.radioOuterActive]}>
                  {engine === 'zemsky' && <View style={styles.radioInner} />}
                </View>
                <Text style={[styles.engineName, engine === 'zemsky' && styles.engineNameActive]}>
                  Music eye
                </Text>
              </View>
              <Text style={[styles.engineDesc, { color: appTheme.inkMuted }]}>
                Uses the separate Music eye app to run the native OMR stack.
              </Text>
              <Text style={[styles.engineVersion, { color: appTheme.inkMuted }]}>arm64 device • Native runtime</Text>
            </TouchableOpacity>
          </View>
        </View>

        <View style={styles.section}>
          <View style={styles.sectionHeader}>
            <Feather name="droplet" size={18} color={appTheme.ink} />
            <Text style={[styles.sectionTitle, { color: appTheme.ink }]}>Theme Palette</Text>
          </View>
          <Text style={[styles.sectionDescription, { color: appTheme.inkMuted }]}> 
            Pick a muted modern palette for the app, including dark and warm options.
          </Text>
          <View style={styles.themeGrid}>
            {LIGHT_THEME_OPTIONS.map((opt) => {
              const selected = opt.id === themeId;
              return (
                <TouchableOpacity
                  key={opt.id}
                  style={[
                    styles.themeCard,
                    { backgroundColor: opt.colors.surface, borderColor: selected ? opt.colors.accent : opt.colors.border },
                    selected && styles.themeCardActive,
                  ]}
                  onPress={() => onThemeChange && onThemeChange(opt.id)}
                >
                  <View style={styles.themeSwatches}>
                    <View style={[styles.themeSwatch, { backgroundColor: opt.colors.background }]} />
                    <View style={[styles.themeSwatch, { backgroundColor: opt.colors.surfaceStrong }]} />
                    <View style={[styles.themeSwatch, { backgroundColor: opt.colors.accent }]} />
                  </View>
                  <Text style={[styles.themeName, { color: opt.colors.ink }]}>{opt.name}</Text>
                </TouchableOpacity>
              );
            })}
          </View>
        </View>

        <View style={styles.section}>
          <View style={styles.sectionHeader}>
            <Feather name="smartphone" size={18} color={palette.ink} />
            <Text style={[styles.sectionTitle, { color: appTheme.ink }]}>Music eye helper</Text>
          </View>
          <Text style={[styles.sectionDescription, { color: appTheme.inkMuted }]}>
            Default endpoint: {ZemskyEmulatorService.getServerUrl()}. Run the Music eye helper app on the same phone, then select Music eye here before scanning.
          </Text>
        </View>

        {/* Cache Management */}
        <View style={styles.section}>
          <View style={styles.sectionHeader}>
            <Feather name="trash-2" size={18} color={palette.ink} />
            <Text style={[styles.sectionTitle, { color: appTheme.ink }]}>Cache Management</Text>
          </View>
          <Text style={[styles.sectionDescription, { color: appTheme.inkMuted }]}>
            OMR results are cached to avoid reprocessing. After parser updates, clear the cache to use new fixes.
          </Text>
          <TouchableOpacity style={[styles.button, styles.dangerButton]} onPress={handleClearCache}>
            <Feather name="trash-2" size={14} color="#fff" />
            <Text style={[styles.buttonText, { color: '#fff' }]}>Clear OMR Cache</Text>
          </TouchableOpacity>
        </View>

        {/* Docker Setup Help */}
        <View style={styles.section}>
          <View style={styles.sectionHeader}>
            <Feather name="terminal" size={18} color={palette.ink} />
            <Text style={styles.sectionTitle}>Quick Setup</Text>
          </View>

          <Text style={styles.sectionDescription}>
            Run the Music eye helper app:
          </Text>
          <View style={styles.codeBlock}>
            <Text style={styles.codeText} selectable>
              open the Music eye helper app{'\n'}
              ./gradlew installDebug{'\n'}
              start the helper screen
            </Text>
          </View>

          <Text style={styles.hint}>
            Music eye uses a separate Android app running on the same phone as NoteScan.
          </Text>
        </View>

        <View style={{ height: 40 }} />
      </ScrollView>

      {/* Fullscreen image modal */}
      <Modal
        visible={!!fullscreenImage}
        transparent={true}
        animationType="fade"
        onRequestClose={() => setFullscreenImage(null)}
      >
        <View style={styles.fullscreenOverlay}>
          <TouchableOpacity
            style={[styles.fullscreenClose, { top: insets.top + 16, right: insets.right + 20 }]}
            onPress={() => setFullscreenImage(null)}
          >
            <Feather name="x" size={28} color="#fff" />
          </TouchableOpacity>
          {fullscreenImage && (
            <Image
              source={{ uri: fullscreenImage }}
              style={styles.fullscreenImage}
              resizeMode="contain"
            />
          )}
        </View>
      </Modal>
    </KeyboardAvoidingView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: palette.background,
  },
  header: {
    paddingHorizontal: 24,
    paddingBottom: 12,
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    backgroundColor: palette.background,
  },
  title: {
    fontSize: 24,
    fontWeight: '800',
    color: palette.ink,
    letterSpacing: -0.4,
  },
  titleRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
  },
  logoWrap: {
    width: 64,
    height: 46,
    overflow: 'hidden',
    borderRadius: 8,
  },
  logoWebView: {
    width: 64,
    height: 46,
    backgroundColor: 'transparent',
  },
  linkText: {
    fontSize: 14,
    color: palette.inkMuted,
    fontWeight: '600',
  },
  scrollContent: {
    flex: 1,
    paddingHorizontal: 24,
  },
  section: {
    marginBottom: 28,
  },
  sectionHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
    marginBottom: 8,
  },
  sectionTitle: {
    fontSize: 17,
    fontWeight: '700',
    color: palette.ink,
  },
  sectionDescription: {
    fontSize: 13,
    color: palette.inkMuted,
    lineHeight: 18,
    marginBottom: 12,
  },
  themeGrid: {
    marginTop: 6,
    flexDirection: 'row',
    flexWrap: 'wrap',
    gap: 10,
  },
  themeCard: {
    width: '48%',
    borderRadius: 12,
    borderWidth: 1,
    padding: 10,
  },
  themeCardActive: {
    borderWidth: 2,
  },
  themeSwatches: {
    flexDirection: 'row',
    gap: 6,
    marginBottom: 8,
  },
  themeSwatch: {
    flex: 1,
    height: 18,
    borderRadius: 6,
  },
  themeName: {
    fontSize: 13,
    fontWeight: '700',
  },
  label: {
    fontSize: 12,
    fontWeight: '700',
    color: palette.ink,
    textTransform: 'uppercase',
    letterSpacing: 0.8,
    marginBottom: 6,
  },
  input: {
    backgroundColor: palette.surface,
    borderWidth: 2,
    borderColor: palette.border,
    borderRadius: 12,
    paddingHorizontal: 14,
    paddingVertical: 12,
    fontSize: 15,
    color: palette.ink,
    fontWeight: '500',
    marginBottom: 12,
  },
  buttonRow: {
    flexDirection: 'row',
    gap: 10,
  },
  button: {
    flex: 1,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    gap: 6,
    backgroundColor: palette.surface,
    borderWidth: 2,
    borderColor: palette.border,
    borderRadius: 12,
    paddingVertical: 10,
    paddingHorizontal: 14,
  },
  saveButton: {
    backgroundColor: palette.surfaceStrong,
  },
  dangerButton: {
    backgroundColor: palette.danger,
    borderColor: palette.danger,
  },
  buttonText: {
    fontSize: 12,
    fontWeight: '700',
    color: palette.ink,
    textTransform: 'uppercase',
    letterSpacing: 0.6,
  },
  resultBox: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
    marginTop: 12,
    paddingHorizontal: 14,
    paddingVertical: 10,
    borderRadius: 10,
    borderWidth: 1,
  },
  resultOk: {
    backgroundColor: '#E8F5E9',
    borderColor: '#C8E6C9',
  },
  resultFail: {
    backgroundColor: '#FFEBEE',
    borderColor: '#FFCDD2',
  },
  resultText: {
    fontSize: 13,
    fontWeight: '600',
    flex: 1,
  },
  codeBlock: {
    backgroundColor: '#2A2925',
    borderRadius: 10,
    padding: 14,
    marginBottom: 10,
  },
  codeText: {
    fontFamily: Platform.OS === 'ios' ? 'Menlo' : 'monospace',
    fontSize: 12,
    color: '#F3F1EA',
    lineHeight: 18,
  },
  hint: {
    fontSize: 12,
    color: palette.inkMuted,
    fontStyle: 'italic',
    marginTop: 4,
  },
  engineRow: {
    flexDirection: 'row',
    gap: 10,
    marginTop: 4,
  },
  engineCard: {
    flex: 1,
    backgroundColor: palette.surface,
    borderWidth: 2,
    borderColor: palette.border,
    borderRadius: 14,
    padding: 14,
  },
  engineCardActive: {
    borderColor: palette.accent,
    backgroundColor: '#FFF8F5',
  },
  engineHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
    marginBottom: 6,
  },
  radioOuter: {
    width: 18,
    height: 18,
    borderRadius: 9,
    borderWidth: 2,
    borderColor: palette.border,
    alignItems: 'center',
    justifyContent: 'center',
  },
  radioOuterActive: {
    borderColor: palette.accent,
  },
  radioInner: {
    width: 10,
    height: 10,
    borderRadius: 5,
    backgroundColor: palette.accent,
  },
  engineName: {
    fontSize: 15,
    fontWeight: '800',
    color: palette.ink,
  },
  engineNameActive: {
    color: palette.accent,
  },
  engineDesc: {
    fontSize: 12,
    color: palette.inkMuted,
    lineHeight: 16,
    marginBottom: 4,
  },
  engineVersion: {
    fontSize: 10,
    color: palette.border,
    fontWeight: '600',
    textTransform: 'uppercase',
    letterSpacing: 0.5,
  },
  previewLoading: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 10,
    marginTop: 12,
    padding: 14,
    backgroundColor: '#FFF8F5',
    borderRadius: 10,
    borderWidth: 1,
    borderColor: '#FFE0D0',
  },
  previewLoadingText: {
    fontSize: 13,
    color: palette.accent,
    fontWeight: '600',
  },
  previewContainer: {
    marginTop: 14,
  },
  previewLabel: {
    fontSize: 11,
    fontWeight: '800',
    color: palette.inkMuted,
    textTransform: 'uppercase',
    letterSpacing: 1,
    marginBottom: 6,
  },
  previewImage: {
    width: '100%',
    height: 260,
    backgroundColor: '#F0EDE5',
    borderRadius: 10,
    borderWidth: 1,
    borderColor: palette.border,
  },
  fullscreenOverlay: {
    flex: 1,
    backgroundColor: 'rgba(0,0,0,0.95)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  fullscreenClose: {
    position: 'absolute',
    right: 20,
    zIndex: 10,
    padding: 8,
    backgroundColor: 'rgba(255,255,255,0.15)',
    borderRadius: 20,
  },
  fullscreenImage: {
    width: Dimensions.get('window').width,
    height: Dimensions.get('window').height * 0.85,
  },
});
