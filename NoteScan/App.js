import React, { useState, useEffect, useRef } from 'react';
import { StatusBar, View, Text, TouchableOpacity, Alert } from 'react-native';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import * as ImagePicker from 'expo-image-picker';
import { HomeScreen } from './src/screens/HomeScreen';
import { PlaybackScreen } from './src/screens/PlaybackScreen';
import { SettingsScreen } from './src/screens/SettingsScreen';
import { FileUploadScreen } from './src/screens/FileUploadScreen';
import { LibraryScreen } from './src/screens/LibraryScreen';
import { OMRSettings } from './src/services/OMRSettings';
import { LibraryService } from './src/services/LibraryService';

export default function App() {
  const [currentScreen, setCurrentScreen] = useState('home');
  const [playbackImageUri, setPlaybackImageUri] = useState(null);
  const [playbackScoreData, setPlaybackScoreData] = useState(null);
  const [playbackScoreEntry, setPlaybackScoreEntry] = useState(null);
  const [homeGoForward, setHomeGoForward] = useState(null);
  const goForwardTimerRef = useRef(null);

  // Load saved OMR engine preference on startup
  useEffect(() => {
    OMRSettings.load();
    LibraryService.load();

    return () => {
      if (goForwardTimerRef.current) {
        clearTimeout(goForwardTimerRef.current);
        goForwardTimerRef.current = null;
      }
    };
  }, []);

  const clearGoForward = () => {
    if (goForwardTimerRef.current) {
      clearTimeout(goForwardTimerRef.current);
      goForwardTimerRef.current = null;
    }
    setHomeGoForward(null);
  };

  const armGoForward = () => {
    const label =
      playbackScoreData?.metadata?.title ||
      playbackScoreEntry?.title ||
      'Last score';

    setHomeGoForward({ label });

    if (goForwardTimerRef.current) {
      clearTimeout(goForwardTimerRef.current);
    }
    goForwardTimerRef.current = setTimeout(() => {
      setHomeGoForward(null);
      goForwardTimerRef.current = null;
    }, 5 * 60 * 1000);
  };

  const handleGoForward = () => {
    clearGoForward();
    setCurrentScreen('playback');
  };

  const handlePlaybackBack = () => {
    armGoForward();
    setCurrentScreen('home');
  };

  const pickImageFromGallery = async () => {
    try {
      const permissionResult = await ImagePicker.requestMediaLibraryPermissionsAsync();

      if (!permissionResult.granted) {
        Alert.alert('Permission Required', 'Permission to access gallery is required!');
        return;
      }

      const result = await ImagePicker.launchImageLibraryAsync({
        mediaTypes: ['images'],
        allowsEditing: false,
        quality: 1,
      });

      if (result.canceled || !result.assets || result.assets.length === 0) {
        return;
      }

      const asset = result.assets[0];
      clearGoForward();
      setPlaybackImageUri(asset.uri);
      setPlaybackScoreData(null);
      setPlaybackScoreEntry(null);
      setCurrentScreen('playback');
    } catch (err) {
      console.error('Error picking image:', err?.message || err);
      Alert.alert('Error', 'Failed to pick image. Please try again.');
    }
  };

  const pickImageFromCamera = async () => {
    try {
      const permissionResult = await ImagePicker.requestCameraPermissionsAsync();

      if (!permissionResult.granted) {
        Alert.alert('Permission Required', 'Permission to access camera is required!');
        return;
      }

      const result = await ImagePicker.launchCameraAsync({
        mediaTypes: ['images'],
        allowsEditing: false,
        quality: 1,
      });

      if (result.canceled || !result.assets || result.assets.length === 0) {
        return;
      }

      const asset = result.assets[0];
      clearGoForward();
      setPlaybackImageUri(asset.uri);
      setPlaybackScoreData(null);
      setPlaybackScoreEntry(null);
      setCurrentScreen('playback');
    } catch (err) {
      console.error('Error capturing image:', err?.message || err);
      Alert.alert('Error', 'Failed to capture image. Please try again.');
    }
  };

  const handleFileSelected = (fileUri) => {
    clearGoForward();
    setPlaybackImageUri(fileUri);
    setPlaybackScoreData(null);
    setPlaybackScoreEntry(null);
    setCurrentScreen('playback');
  };

  const handleScoreTapFromLibrary = (scoreData, entry) => {
    clearGoForward();
    setPlaybackImageUri(null);
    setPlaybackScoreData(scoreData);
    setPlaybackScoreEntry(entry);
    setCurrentScreen('playback');
  };

  const handlePlaybackComplete = async (scoreData, engine) => {
    // After successful playback, optionally add to library
    // (can be done manually by user, or automatically here)
    try {
      const entry = await LibraryService.addScore(
        scoreData,
        engine,
        scoreData?.metadata?.workTitle || 'Scanned Score'
      );
      console.log('Score saved to library:', entry);
    } catch (e) {
      console.warn('Failed to save to library:', e.message);
    }
  };

  return (
    <SafeAreaProvider>
      <View style={{ flex: 1 }}>
        <StatusBar barStyle="dark-content" backgroundColor="#F9F7F1" />
        {currentScreen === 'home' ? (
          <HomeScreen
            onNavigate={setCurrentScreen}
            onPickFromGallery={pickImageFromGallery}
            onPickFromCamera={pickImageFromCamera}
            goForwardState={homeGoForward}
            onGoForward={handleGoForward}
          />
        ) : currentScreen === 'playback' ? (
          <PlaybackScreen
            imageUri={playbackImageUri}
            scoreData={playbackScoreData}
            scoreEntry={playbackScoreEntry}
            onNavigateBack={handlePlaybackBack}
            onScoredSaved={handlePlaybackComplete}
          />
        ) : currentScreen === 'settings' ? (
          <SettingsScreen
            onNavigateBack={() => setCurrentScreen('home')}
          />
        ) : currentScreen === 'upload-file' ? (
          <FileUploadScreen
            onNavigateBack={() => setCurrentScreen('home')}
            onFileSelected={handleFileSelected}
          />
        ) : currentScreen === 'help' ? (
          <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: '#F9F7F1' }}>
            <Text style={{ fontSize: 18, color: '#3E3C37', marginBottom: 20 }}>❓ Help</Text>
            <Text style={{ fontSize: 14, color: '#6E675E', marginBottom: 20, paddingHorizontal: 24, textAlign: 'center' }}>
              Music Eye uses OMR (Optical Music Recognition) to scan
              sheet music images and convert them to playable notes.{'\n\n'}
              1. Take a photo or pick an image of sheet music{'\n'}
              2. The image is sent to the ZemEmu engine{"\n"}
              3. Returned MusicXML is converted to playable audio{"\n\n"}
              In Settings, select ZemEmu and run ZemskyHarness in the same Android emulator.
            </Text>
            <TouchableOpacity onPress={() => setCurrentScreen('home')}>
              <Text style={{ color: '#6E675E', fontSize: 16, fontWeight: '600' }}>← Back to Home</Text>
            </TouchableOpacity>
          </View>
        ) : currentScreen === 'library' ? (
          <LibraryScreen
            onNavigateBack={() => setCurrentScreen('home')}
            onScoreTap={handleScoreTapFromLibrary}
          />
        ) : null}
      </View>
    </SafeAreaProvider>
  );
}
