import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  StatusBar,
  ActivityIndicator,
  Alert,
  ScrollView,
} from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { Feather } from '@expo/vector-icons';
import * as ImagePicker from 'expo-image-picker';
import { FileHandlerService } from '../services/FileHandlerService';
import { getStatusBarStyleForTheme } from '../theme/themes';

/**
 * FileUploadScreen — allows users to pick sheet music image files from device storage
 * and scan them through the OMR engine.
 */
export const FileUploadScreen = ({ onNavigateBack, onFileSelected, theme: incomingTheme }) => {
  const [isLoading, setIsLoading] = useState(false);
  const insets = useSafeAreaInsets();
  const theme = incomingTheme || {
    background: '#F9F7F1',
    surface: '#FBFAF5',
    surfaceStrong: '#F1EEE4',
    border: '#D6D0C4',
    ink: '#3E3C37',
    inkMuted: '#6E675E',
    accent: '#E05A2A',
  };

  const pickImageFile = async () => {
    try {
      setIsLoading(true);

      const permissionResult = await ImagePicker.requestMediaLibraryPermissionsAsync();
      if (!permissionResult.granted) {
        Alert.alert('Permission Required', 'Permission to access media library is required');
        setIsLoading(false);
        return;
      }

      const result = await ImagePicker.launchImageLibraryAsync({
        mediaTypes: ['images'],
        allowsEditing: false,
        quality: 1,
      });

      if (result.canceled || !result.assets || result.assets.length === 0) {
        setIsLoading(false);
        return;
      }

      const asset = result.assets[0];
      const fileUri = asset.uri;

      const fileInfo = await FileHandlerService.getFileInfo(fileUri);
      if (!fileInfo) {
        Alert.alert('Error', 'Could not read file information');
        setIsLoading(false);
        return;
      }

      if (!FileHandlerService.isSupported(fileUri) || !fileInfo.isImage) {
        Alert.alert(
          'Unsupported File Type',
          `File type "${fileInfo.typeLabel}" is not supported directly. Please select PNG or JPEG.`
        );
        setIsLoading(false);
        return;
      }

      setIsLoading(false);
      if (onFileSelected) {
        onFileSelected(fileUri);
      }
    } catch (err) {
      console.error('FileUploadScreen: Error picking file:', err?.message || err);
      Alert.alert('Error', 'Failed to pick image. Please try again.');
      setIsLoading(false);
    }
  };

  const showPDFInstructions = () => {
    const instructions = FileHandlerService.getPDFInstructions();
    Alert.alert(
      'PDF Support',
      `Music eye scans sheet music images. To use a PDF:\n\n${instructions.join('\n')}`
    );
  };

  return (
    <View style={[styles.container, { backgroundColor: theme.background }]}>
      <StatusBar barStyle={getStatusBarStyleForTheme({ colors: theme })} backgroundColor={theme.background} />

      <View
        style={[
          styles.header,
          {
            paddingTop: insets.top + 8,
            paddingLeft: 16 + insets.left,
            paddingRight: 16 + insets.right,
          },
        ]}
      >
        <TouchableOpacity style={styles.backButton} onPress={onNavigateBack}>
          <Feather name="arrow-left" size={24} color={theme.ink} />
        </TouchableOpacity>
        <Text style={[styles.title, { color: theme.ink }]}>Scan from Files</Text>
        <View style={{ width: 40 }} />
      </View>

      <ScrollView
        style={[
          styles.content,
          { paddingLeft: 24 + insets.left, paddingRight: 24 + insets.right },
        ]}
        contentContainerStyle={{ flexGrow: 1, justifyContent: 'center' }}
      >
        <View style={styles.illustrationContainer}>
          <Feather name="image" size={64} color={theme.inkMuted} style={{ marginBottom: 16 }} />
          <Text style={[styles.instructionTitle, { color: theme.ink }]}>Pick Sheet Music Image</Text>
        </View>

        <View style={[styles.infoBox, { backgroundColor: theme.surface, borderColor: theme.border }]}>
          <Text style={styles.infoLabel}>Supported Formats:</Text>
          <Text style={styles.infoText}>PNG, JPEG (ready to scan)</Text>
          <Text style={styles.infoText}>PDF (convert pages to images first)</Text>

          <Text style={styles.infoLabel}>Tips for Best Results:</Text>
          <Text style={styles.infoText}>• Use clear, well-lit images or scans</Text>
          <Text style={styles.infoText}>• Scan one page of music at a time</Text>
          <Text style={styles.infoText}>• Avoid shadows, glare, and folds</Text>
          <Text style={styles.infoText}>• Ensure the sheet music is straight</Text>

          <Text style={styles.infoLabel}>Using PDF Files:</Text>
          <TouchableOpacity onPress={showPDFInstructions}>
            <Text style={[styles.infoLink, { color: theme.accent }]}>Tap here to learn how to convert PDFs</Text>
          </TouchableOpacity>
        </View>
      </ScrollView>

      <View
        style={[
          styles.footer,
          {
            paddingBottom: insets.bottom + 16,
            paddingLeft: 24 + insets.left,
            paddingRight: 24 + insets.right,
          },
        ]}
      >
        <TouchableOpacity style={[styles.browseButton, { backgroundColor: theme.ink }]} onPress={pickImageFile} disabled={isLoading}>
          {isLoading ? (
            <ActivityIndicator size="small" color={theme.surface} />
          ) : (
            <>
              <Feather name="folder" size={20} color={theme.surface} style={{ marginRight: 8 }} />
              <Text style={[styles.browseButtonText, { color: theme.surface }]}>Browse Images</Text>
            </>
          )}
        </TouchableOpacity>
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
    paddingTop: 12,
    paddingBottom: 12,
    paddingHorizontal: 16,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  backButton: {
    width: 40,
    height: 40,
    borderRadius: 10,
    justifyContent: 'center',
    alignItems: 'center',
  },
  title: {
    fontSize: 18,
    fontWeight: '700',
    color: '#3E3C37',
    textAlign: 'center',
    flex: 1,
  },
  content: {
    flex: 1,
    paddingHorizontal: 24,
    paddingVertical: 32,
  },
  illustrationContainer: {
    alignItems: 'center',
    marginBottom: 48,
  },
  instructionTitle: {
    fontSize: 20,
    fontWeight: '700',
    color: '#3E3C37',
    marginBottom: 8,
  },
  infoBox: {
    backgroundColor: '#FBFAF5',
    borderRadius: 14,
    borderWidth: 1,
    borderColor: '#D6D0C4',
    padding: 16,
    marginBottom: 20,
  },
  infoLabel: {
    fontSize: 13,
    fontWeight: '700',
    color: '#6E675E',
    textTransform: 'uppercase',
    letterSpacing: 0.5,
    marginTop: 8,
  },
  infoText: {
    fontSize: 14,
    color: '#5C574E',
    marginTop: 6,
    lineHeight: 20,
  },
  infoLink: {
    fontSize: 14,
    color: '#E05A2A',
    fontWeight: '600',
    marginTop: 8,
    textDecorationLine: 'underline',
    lineHeight: 20,
  },
  footer: {
    paddingHorizontal: 24,
    paddingBottom: 32,
    paddingTop: 16,
  },
  browseButton: {
    backgroundColor: '#3E3C37',
    borderRadius: 12,
    paddingVertical: 14,
    paddingHorizontal: 20,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
  },
  browseButtonText: {
    fontSize: 16,
    fontWeight: '700',
    color: '#FBFAF5',
    textTransform: 'uppercase',
    letterSpacing: 0.5,
  },
});
