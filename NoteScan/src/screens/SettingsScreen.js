import React, { useMemo, useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  Alert,
  Platform,
  StatusBar,
  KeyboardAvoidingView,
  ScrollView,
  Modal,
} from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { Feather } from '@expo/vector-icons';
import { WebView } from 'react-native-webview';
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
  danger: '#D9534F',
};

export const SettingsScreen = ({ onNavigateBack, theme, themeId, onThemeChange }) => {
  const [engine, setEngine] = useState(OMRSettings.getEngine());
  const [showThemePicker, setShowThemePicker] = useState(false);
  const [showAboutDetails, setShowAboutDetails] = useState(false);
  const [showAcknowledgements, setShowAcknowledgements] = useState(false);
  const insets = useSafeAreaInsets();
  const appTheme = { ...palette, ...(theme || {}) };
  const animatedLogoHtml = useMemo(() => buildThemedLogoHtml(appTheme), [appTheme]);
  const selectedTheme = useMemo(() => {
    return LIGHT_THEME_OPTIONS.find((opt) => opt.id === themeId) || LIGHT_THEME_OPTIONS[0];
  }, [themeId]);

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
      'This will delete all cached OMR scan results. You will need to rescan images, but it will use the latest fixes.',
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
          },
        },
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
          <Text style={[styles.linkText, { color: appTheme.inkMuted }]}>Back</Text>
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
        <View style={{ width: 48 }} />
      </View>

      <ScrollView
        style={[
          styles.scrollContent,
          { paddingLeft: 24 + insets.left, paddingRight: 24 + insets.right },
        ]}
        contentContainerStyle={{ paddingBottom: insets.bottom + 24 }}
      >
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
              <Text style={[styles.engineDesc, { color: appTheme.inkMuted }]}>Uses the separate Music eye app to run the native OMR stack.</Text>
              <Text style={[styles.engineVersion, { color: appTheme.inkMuted }]}>arm64 device - Native runtime</Text>
            </TouchableOpacity>
          </View>
        </View>

        <View style={styles.section}>
          <View style={styles.sectionHeader}>
            <Feather name="droplet" size={18} color={appTheme.ink} />
            <Text style={[styles.sectionTitle, { color: appTheme.ink }]}>Theme Palette</Text>
          </View>
          <Text style={[styles.sectionDescription, { color: appTheme.inkMuted }]}>Pick a muted modern palette for the app.</Text>

          <TouchableOpacity
            style={[styles.themeDropdownTrigger, { backgroundColor: appTheme.surface, borderColor: appTheme.border }]}
            onPress={() => setShowThemePicker(true)}
            activeOpacity={0.85}
          >
            <View style={styles.themeDropdownLeft}>
              <View style={styles.themeSwatchesCompact}>
                <View style={[styles.themeSwatchCompact, { backgroundColor: selectedTheme?.colors?.background || appTheme.background }]} />
                <View style={[styles.themeSwatchCompact, { backgroundColor: selectedTheme?.colors?.surfaceStrong || appTheme.surfaceStrong }]} />
                <View style={[styles.themeSwatchCompact, { backgroundColor: selectedTheme?.colors?.accent || appTheme.accent }]} />
              </View>
              <Text style={[styles.themeDropdownText, { color: appTheme.ink }]} numberOfLines={1}>
                {selectedTheme?.name || 'Select theme'}
              </Text>
            </View>
            <Feather name="chevron-down" size={18} color={appTheme.inkMuted} />
          </TouchableOpacity>
        </View>

        <View style={styles.section}>
          <View style={styles.sectionHeader}>
            <Feather name="trash-2" size={18} color={palette.ink} />
            <Text style={[styles.sectionTitle, { color: appTheme.ink }]}>Cache Management</Text>
          </View>
          <Text style={[styles.sectionDescription, { color: appTheme.inkMuted }]}>OMR results are cached to avoid reprocessing. Clear the cache after parser updates.</Text>
          <TouchableOpacity style={[styles.button, styles.dangerButton]} onPress={handleClearCache}>
            <Feather name="trash-2" size={14} color="#fff" />
            <Text style={[styles.buttonText, { color: '#fff' }]}>Clear OMR Cache</Text>
          </TouchableOpacity>
        </View>

        <View style={styles.section}>
          <TouchableOpacity
            style={[styles.collapsibleHeader, { borderColor: appTheme.border, backgroundColor: appTheme.surface }]}
            onPress={() => setShowAboutDetails((prev) => !prev)}
            activeOpacity={0.85}
          >
            <View style={styles.collapsibleHeaderLeft}>
              <Feather name="info" size={18} color={palette.ink} />
              <Text style={[styles.sectionTitle, { color: appTheme.ink }]}>About Music Eye</Text>
            </View>
            <Feather name={showAboutDetails ? 'chevron-up' : 'chevron-down'} size={18} color={appTheme.inkMuted} />
          </TouchableOpacity>
          {showAboutDetails && (
            <View style={[styles.collapsibleBody, { borderColor: appTheme.border, backgroundColor: appTheme.surface }]}> 
              <Text style={[styles.sectionDescription, { color: appTheme.inkMuted }]}>Music Eye scans sheet music and helps you practice by focusing on each vocal part.</Text>
              <Text style={[styles.aboutList, { color: appTheme.inkMuted }]}> 
                - Soprano: highest vocal line{"\n"}
                - Alto: upper-middle vocal line{"\n"}
                - Tenor: lower-middle vocal line{"\n"}
                - Bass: lowest vocal line{"\n"}
                - Use playback voice filters to isolate or combine SATB parts.
              </Text>
              <Text style={[styles.aboutEmail, { color: appTheme.inkMuted }]}>Contact: nuffledroid1243@gmail.com</Text>
            </View>
          )}
        </View>

        <View style={styles.section}>
          <TouchableOpacity
            style={[styles.collapsibleHeader, { borderColor: appTheme.border, backgroundColor: appTheme.surface }]}
            onPress={() => setShowAcknowledgements((prev) => !prev)}
            activeOpacity={0.85}
          >
            <View style={styles.collapsibleHeaderLeft}>
              <Feather name="book-open" size={18} color={palette.ink} />
              <Text style={[styles.sectionTitle, { color: appTheme.ink }]}>Copyright Information & Acknowledgement</Text>
            </View>
            <Feather name={showAcknowledgements ? 'chevron-up' : 'chevron-down'} size={18} color={appTheme.inkMuted} />
          </TouchableOpacity>
          {showAcknowledgements && (
            <View style={[styles.collapsibleBody, { borderColor: appTheme.border, backgroundColor: appTheme.surface }]}> 
              <Text style={[styles.sectionDescription, { color: appTheme.inkMuted }]}>Open Source Acknowledgements{"\n"}MusicEye is built on the shoulders of these excellent open source projects. We are grateful to their authors and contributors.</Text>

              <View style={[styles.ackContainer, { backgroundColor: appTheme.surface, borderColor: appTheme.border }]}> 
            <Text style={[styles.ackProjectName, { color: appTheme.ink }]}>Leptonica</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Copyright (c) 2001 Leptonica. All rights reserved.</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>License: BSD 2-Clause</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Link: http://leptonica.org</Text>

            <Text style={[styles.ackProjectName, { color: appTheme.ink }]}>Tesseract OCR</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Copyright (c) Tesseract OCR. All rights reserved.</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>License: Apache License, Version 2.0</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Link: http://www.apache.org/licenses/LICENSE-2.0</Text>

            <Text style={[styles.ackProjectName, { color: appTheme.ink }]}>LAME</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Copyright (c) The LAME team and contributors.</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>License: LGPL</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Link: http://lame.sourceforge.net</Text>

            <Text style={[styles.ackProjectName, { color: appTheme.ink }]}>Frugally-Deep</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Copyright (c) 2021 Tobias Hermann.</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>License: MIT License</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Link: https://opensource.org/licenses/MIT</Text>

            <Text style={[styles.ackProjectName, { color: appTheme.ink }]}>FunctionalPlus</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Copyright (c) 2021 Tobias Hermann and the FunctionalPlus contributors.</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>License: Boost Software License, Version 1.0</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Link: https://www.boost.org/LICENSE_1_0.txt</Text>

            <Text style={[styles.ackProjectName, { color: appTheme.ink }]}>Eigen</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Copyright (c) 2021 Eigen.</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>License: Mozilla Public License, Version 2.0</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Link: https://www.mozilla.org/en-US/MPL/2.0/</Text>

            <Text style={[styles.ackProjectName, { color: appTheme.ink }]}>JSON for Modern C++ (Niels Lohmann)</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Copyright (c) 2013-2021 Niels Lohmann.</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>License: MIT License</Text>
            <Text style={[styles.ackMeta, { color: appTheme.inkMuted }]}>Link: https://opensource.org/licenses/MIT</Text>
          </View>
            </View>
          )}
        </View>

        <View style={{ height: 40 }} />
      </ScrollView>

      <Modal
        visible={showThemePicker}
        transparent={true}
        animationType="fade"
        onRequestClose={() => setShowThemePicker(false)}
      >
        <View style={styles.themeModalOverlay}>
          <TouchableOpacity style={styles.themeModalBackdrop} activeOpacity={1} onPress={() => setShowThemePicker(false)} />
          <View style={[styles.themeModalCard, { backgroundColor: appTheme.surface, borderColor: appTheme.border, marginBottom: insets.bottom + 12 }]}>
            <Text style={[styles.themeModalTitle, { color: appTheme.ink }]}>Choose Theme</Text>
            {LIGHT_THEME_OPTIONS.map((opt) => {
              const selected = opt.id === themeId;
              return (
                <TouchableOpacity
                  key={opt.id}
                  style={[styles.themeOptionRow, { borderColor: appTheme.border }, selected && { backgroundColor: appTheme.surfaceStrong }]}
                  onPress={() => {
                    if (onThemeChange) onThemeChange(opt.id);
                    setShowThemePicker(false);
                  }}
                >
                  <View style={styles.themeSwatchesCompact}>
                    <View style={[styles.themeSwatchCompact, { backgroundColor: opt.colors.background }]} />
                    <View style={[styles.themeSwatchCompact, { backgroundColor: opt.colors.surfaceStrong }]} />
                    <View style={[styles.themeSwatchCompact, { backgroundColor: opt.colors.accent }]} />
                  </View>
                  <Text style={[styles.themeOptionText, { color: appTheme.ink }]}>{opt.name}</Text>
                  {selected && <Feather name="check" size={16} color={appTheme.accent} />}
                </TouchableOpacity>
              );
            })}
          </View>
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
  collapsibleHeader: {
    borderWidth: 1,
    borderRadius: 12,
    minHeight: 46,
    paddingHorizontal: 12,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  collapsibleHeaderLeft: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
    flex: 1,
    paddingRight: 10,
  },
  collapsibleBody: {
    marginTop: 8,
    borderWidth: 1,
    borderRadius: 12,
    padding: 12,
  },
  themeDropdownTrigger: {
    marginTop: 6,
    borderWidth: 1,
    borderRadius: 12,
    minHeight: 46,
    paddingHorizontal: 12,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  themeDropdownLeft: {
    flexDirection: 'row',
    alignItems: 'center',
    flex: 1,
    gap: 10,
    paddingRight: 8,
  },
  themeDropdownText: {
    fontSize: 14,
    fontWeight: '700',
    flexShrink: 1,
  },
  themeSwatchesCompact: {
    flexDirection: 'row',
    gap: 4,
  },
  themeSwatchCompact: {
    width: 14,
    height: 14,
    borderRadius: 4,
  },
  themeModalOverlay: {
    flex: 1,
    justifyContent: 'flex-end',
    backgroundColor: 'rgba(0,0,0,0.32)',
  },
  themeModalBackdrop: {
    flex: 1,
  },
  themeModalCard: {
    marginHorizontal: 14,
    borderRadius: 14,
    borderWidth: 1,
    padding: 12,
  },
  themeModalTitle: {
    fontSize: 15,
    fontWeight: '800',
    marginBottom: 10,
  },
  themeOptionRow: {
    minHeight: 42,
    borderRadius: 10,
    borderWidth: 1,
    paddingHorizontal: 10,
    marginBottom: 8,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  themeOptionText: {
    flex: 1,
    marginLeft: 10,
    fontSize: 14,
    fontWeight: '600',
  },
  button: {
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
  aboutList: {
    fontSize: 13,
    lineHeight: 19,
    marginBottom: 8,
  },
  aboutEmail: {
    fontSize: 13,
    fontWeight: '700',
    lineHeight: 18,
  },
  ackContainer: {
    borderWidth: 1,
    borderRadius: 12,
    padding: 12,
    gap: 4,
  },
  ackProjectName: {
    fontSize: 13,
    fontWeight: '800',
    marginTop: 8,
  },
  ackMeta: {
    fontSize: 12,
    lineHeight: 17,
  },
});
