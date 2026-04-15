import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  StatusBar,
} from 'react-native';
import { Feather } from '@expo/vector-icons';
import { WebView } from 'react-native-webview';
import { OMRSettings } from '../services/OMRSettings';

const animatedLogoHtml = `
<!doctype html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <style>
      html, body {
        margin: 0;
        padding: 0;
        width: 100%;
        height: 100%;
        background: transparent;
        overflow: hidden;
      }
      .wrap {
        width: 100%;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
      }
      @keyframes blink {
        0%, 88%, 100% { transform: scaleY(1); }
        92%, 96% { transform: scaleY(0.04); }
      }
      @keyframes drift {
        0%, 25% { transform: translate(0,0); }
        30%, 55% { transform: translate(2px,-1px); }
        60%, 85% { transform: translate(-2px,1px); }
        90%, 100% { transform: translate(0,0); }
      }
      .eye {
        animation: blink 6s ease-in-out infinite;
        transform-origin: 41px 41px;
      }
      .pupil {
        animation: drift 8s cubic-bezier(.45,.05,.55,.95) infinite;
        transform-origin: 41px 41px;
      }
    </style>
  </head>
  <body>
    <div class="wrap">
      <svg width="70" height="52" viewBox="0 0 82 82" xmlns="http://www.w3.org/2000/svg" style="overflow:visible;">
        <defs>
          <clipPath id="homeLogoClip">
            <path d="M4,41 Q41,-8 78,41 Q41,90 4,41Z" />
          </clipPath>
        </defs>
        <g class="eye">
          <path d="M4,41 Q41,-8 78,41 Q41,90 4,41Z" fill="#F1EEE4" stroke="#3E3C37" stroke-width="2.3"/>
          <g clip-path="url(#homeLogoClip)">
            <circle cx="41" cy="41" r="22" fill="#E8DCC8"/>
            <circle cx="41" cy="41" r="22" fill="none" stroke="#3E3C37" stroke-width="1"/>
            <g class="pupil">
              <circle cx="41" cy="41" r="13" fill="#3E3C37"/>
              <text id="homeLogoNoteGlyph" x="41" y="45" text-anchor="middle" dominant-baseline="middle" font-family="Georgia, serif" font-size="16" fill="#FFFFFF">&#9835;</text>
              <circle cx="35" cy="34" r="2.5" fill="#FFFFFF" opacity="0.85"/>
            </g>
          </g>
          <path d="M4,41 Q41,-8 78,41" fill="none" stroke="#3E3C37" stroke-width="2.3" stroke-linecap="round"/>
          <path d="M4,41 Q41,90 78,41" fill="none" stroke="#3E3C37" stroke-width="1.2" stroke-linecap="round"/>
        </g>
      </svg>
    </div>
    <script>
      (function () {
        var eye = document.querySelector('.eye');
        var note = document.getElementById('homeLogoNoteGlyph');
        if (!eye || !note) return;

        var notes = ['\u2669', '\u266A', '\u266B', '\u266C'];
        var idx = notes.indexOf(note.textContent);
        if (idx < 0) idx = 1;
        var blinkDurationMs = 6000;
        // Blink is closed between 92% and 96%; 94% is fully closed.
        var fullyClosedOffsetMs = Math.round(blinkDurationMs * 0.94);

        function setNextNote() {
          idx = (idx + 1) % notes.length;
          note.textContent = notes[idx];
        }

        setTimeout(function () {
          setNextNote();
          setInterval(setNextNote, blinkDurationMs);
        }, fullyClosedOffsetMs);
      })();
    </script>
  </body>
</html>
`;

export const HomeScreen = ({ onNavigate, onPickFromGallery, onPickFromCamera }) => {
  const [serverStatus, setServerStatus] = useState(null);

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
    <View style={styles.container}>
      <StatusBar barStyle="dark-content" backgroundColor="#F9F7F1" />

      <View style={styles.header}>
        <View style={styles.headerTop}>
          <Text style={styles.title}>Music eye</Text>
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
        <Text style={styles.subtitle}>Powered by AI • scan and play in seconds</Text>

        {/* Engine / status indicator */}
        <TouchableOpacity style={styles.statusRow} onPress={checkServer}>
          <View style={[
            styles.statusDot,
            serverStatus === null ? styles.statusChecking :
            serverStatus ? styles.statusOk : styles.statusDown,
          ]} />
          <Text style={styles.statusText}>{statusLabel}</Text>
          <Feather name="repeat" size={12} color="#6E675E" style={{ marginLeft: 4 }} />
        </TouchableOpacity>
      </View>

      <View style={styles.content}>
        <View style={styles.buttonContainer}>
          {/* Scan from Camera */}
          <TouchableOpacity
            style={styles.actionButton}
            onPress={onPickFromCamera}
          >
            <View style={styles.buttonIconWrap}>
              <Feather name="camera" size={20} color="#3E3C37" />
            </View>
            <Text style={styles.buttonTitle}>Scan from Camera</Text>
          </TouchableOpacity>

          {/* Upload Image */}
          <TouchableOpacity
            style={styles.actionButton}
            onPress={onPickFromGallery}
          >
            <View style={styles.buttonIconWrap}>
              <Feather name="image" size={20} color="#3E3C37" />
            </View>
            <Text style={styles.buttonTitle}>Scan from Photos</Text>
          </TouchableOpacity>

          {/* Upload PDF/Files */}
          <TouchableOpacity
            style={styles.actionButton}
            onPress={() => onNavigate('upload-file')}
          >
            <View style={styles.buttonIconWrap}>
              <Feather name="download" size={20} color="#3E3C37" />
            </View>
            <Text style={styles.buttonTitle}>Scan from Files</Text>
          </TouchableOpacity>

          {/* Browse Scanned Music */}
          <TouchableOpacity
            style={styles.actionButton}
            onPress={() => onNavigate('library')}
          >
            <View style={styles.buttonIconWrap}>
              <Feather name="music" size={20} color="#3E3C37" />
            </View>
            <Text style={styles.buttonTitle}>Browse Scanned Music</Text>
          </TouchableOpacity>
        </View>
      </View>

      {/* Footer Actions */}
      <View style={styles.footerContainer}>
        <View style={styles.footer}>
          <TouchableOpacity
            style={styles.footerAction}
            onPress={() => onNavigate('settings')}
          >
            <Feather name="settings" size={20} color="#3E3C37" />
            <Text style={styles.footerText}>Settings</Text>
          </TouchableOpacity>
          <TouchableOpacity
            style={styles.footerAction}
            onPress={() => onNavigate('help')}
          >
            <Feather name="help-circle" size={20} color="#3E3C37" />
            <Text style={styles.footerText}>Help</Text>
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
    paddingTop: 72,
    paddingBottom: 28,
    paddingHorizontal: 24,
    gap: 8,
  },
  headerTop: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 10,
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
  },
  statusRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 4,
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
