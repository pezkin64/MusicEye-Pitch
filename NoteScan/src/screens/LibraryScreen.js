import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  StatusBar,
  FlatList,
  ActivityIndicator,
  Alert,
  Animated,
} from 'react-native';
import { Feather } from '@expo/vector-icons';
import { LibraryService } from '../services/LibraryService';

/**
 * LibraryScreen — Browse previously scanned music from the local library.
 *
 * Displays all saved scores with metadata (composer, key, time signature).
 * Tap to play, swipe to delete, or use actions menu.
 */
export const LibraryScreen = ({ onNavigateBack, onScoreTap }) => {
  const [entries, setEntries] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [refreshing, setRefreshing] = useState(false);

  useEffect(() => {
    loadLibrary();
  }, []);

  const loadLibrary = async () => {
    try {
      setIsLoading(true);
      const allEntries = await LibraryService.getAll();
      setEntries(allEntries);
    } catch (e) {
      console.error('Failed to load library:', e.message);
      Alert.alert('Error', 'Failed to load library');
    } finally {
      setIsLoading(false);
      setRefreshing(false);
    }
  };

  const handleRefresh = () => {
    setRefreshing(true);
    loadLibrary();
  };

  const handleScoreTap = async (entry) => {
    try {
      const scoreData = await LibraryService.getScore(entry.id);
      if (scoreData && onScoreTap) {
        onScoreTap(scoreData, entry);
      }
    } catch (e) {
      Alert.alert('Error', 'Failed to load score');
    }
  };

  const handleDeleteScore = (entry) => {
    Alert.alert(
      'Delete Score?',
      `Remove "${entry.title}" from library?`,
      [
        { text: 'Cancel', onPress: () => {}, style: 'cancel' },
        {
          text: 'Delete',
          onPress: async () => {
            try {
              await LibraryService.deleteScore(entry.id);
              setEntries(entries.filter(e => e.id !== entry.id));
            } catch (e) {
              Alert.alert('Error', 'Failed to delete score');
            }
          },
          style: 'destructive',
        },
      ]
    );
  };

  const handleClearAll = () => {
    Alert.alert(
      'Clear Library?',
      'Remove all scanned scores? This cannot be undone.',
      [
        { text: 'Cancel', style: 'cancel' },
        {
          text: 'Clear All',
          onPress: async () => {
            try {
              await LibraryService.clearAll();
              setEntries([]);
            } catch (e) {
              Alert.alert('Error', 'Failed to clear library');
            }
          },
          style: 'destructive',
        },
      ]
    );
  };

  const formatDate = (timestamp) => {
    const date = new Date(timestamp);
    return date.toLocaleDateString('en-US', {
      month: 'short',
      day: 'numeric',
      year: date.getFullYear() === new Date().getFullYear() ? undefined : 'numeric',
    });
  };

  const renderEmptyState = () => (
    <View style={styles.emptyState}>
      <Feather name="music" size={64} color="#9B967B" style={{ marginBottom: 16 }} />
      <Text style={styles.emptyStateTitle}>No Scanned Music Yet</Text>
      <Text style={styles.emptyStateText}>
        Start by scanning sheet music from camera, photos, or files to build your library.
      </Text>
    </View>
  );

  const renderScoreItem = ({ item: entry }) => (
    <View style={styles.scoreCard}>
      <TouchableOpacity
        style={styles.scoreCardContent}
        onPress={() => handleScoreTap(entry)}
      >
        <View style={styles.scoreCardIcon}>
          <Feather name="music" size={20} color="#3E3C37" />
        </View>

        <View style={styles.scoreCardInfo}>
          <Text style={styles.scoreTitle} numberOfLines={1}>
            {entry.title || 'Untitled'}
          </Text>
          <View style={styles.scoreMetadata}>
            {entry.composer && entry.composer !== 'Unknown' && (
              <Text style={styles.scoreMetaText}>
                {entry.composer}
              </Text>
            )}
            {entry.timeSignature && (
              <Text style={styles.scoreMetaText}>
                • {entry.timeSignature}
              </Text>
            )}
          </View>
          <View style={styles.scoreFooter}>
            <Text style={styles.scoreNoteCount}>
              {entry.noteCount} note{entry.noteCount !== 1 ? 's' : ''}
            </Text>
            <Text style={styles.scoreDate}>
              {formatDate(entry.timestamp)}
            </Text>
          </View>
        </View>

        <Feather name="play" size={24} color="#6E675E" style={styles.playIcon} />
      </TouchableOpacity>

      <View style={styles.scoreCardActions}>
        <TouchableOpacity
          style={styles.actionButton}
          onPress={() => handleScoreTap(entry)}
        >
          <Feather name="play" size={16} color="#3E3C37" />
          <Text style={styles.actionButtonText}>Play</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={[styles.actionButton, styles.deleteButton]}
          onPress={() => handleDeleteScore(entry)}
        >
          <Feather name="trash-2" size={16} color="#D9534F" />
          <Text style={[styles.actionButtonText, styles.deleteButtonText]}>Delete</Text>
        </TouchableOpacity>
      </View>
    </View>
  );

  return (
    <View style={styles.container}>
      <StatusBar barStyle="dark-content" backgroundColor="#F9F7F1" />

      {/* Header */}
      <View style={styles.header}>
        <TouchableOpacity
          style={styles.backButton}
          onPress={onNavigateBack}
        >
          <Feather name="arrow-left" size={24} color="#3E3C37" />
        </TouchableOpacity>
        <Text style={styles.title}>Scanned Music</Text>
        {entries.length > 0 && (
          <TouchableOpacity
            style={styles.clearButton}
            onPress={handleClearAll}
          >
            <Feather name="trash" size={20} color="#D9534F" />
          </TouchableOpacity>
        )}
        {entries.length === 0 && <View style={{ width: 40 }} />}
      </View>

      {/* Library Content */}
      {isLoading ? (
        <View style={styles.loadingContainer}>
          <ActivityIndicator size="large" color="#3E3C37" />
        </View>
      ) : entries.length === 0 ? (
        renderEmptyState()
      ) : (
        <View style={styles.content}>
          <Text style={styles.libraryStats}>
            {entries.length} score{entries.length !== 1 ? 's' : ''} in library
          </Text>
          <FlatList
            data={entries}
            renderItem={renderScoreItem}
            keyExtractor={(item) => item.id}
            scrollEnabled={true}
            onRefresh={handleRefresh}
            refreshing={refreshing}
            contentContainerStyle={styles.listContent}
          />
        </View>
      )}
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
    borderBottomWidth: 1,
    borderBottomColor: '#D6D0C4',
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
    flex: 1,
    textAlign: 'center',
  },
  clearButton: {
    width: 40,
    height: 40,
    borderRadius: 10,
    justifyContent: 'center',
    alignItems: 'center',
  },
  content: {
    flex: 1,
    paddingHorizontal: 16,
    paddingVertical: 12,
  },
  libraryStats: {
    fontSize: 12,
    color: '#6E675E',
    fontWeight: '600',
    marginBottom: 8,
    textTransform: 'uppercase',
    letterSpacing: 0.5,
  },
  listContent: {
    paddingBottom: 20,
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  emptyState: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    paddingHorizontal: 24,
  },
  emptyStateTitle: {
    fontSize: 20,
    fontWeight: '700',
    color: '#3E3C37',
    marginBottom: 8,
    textAlign: 'center',
  },
  emptyStateText: {
    fontSize: 14,
    color: '#6E675E',
    textAlign: 'center',
    lineHeight: 20,
  },
  scoreCard: {
    marginBottom: 12,
    backgroundColor: '#FBFAF5',
    borderRadius: 14,
    borderWidth: 1,
    borderColor: '#D6D0C4',
    overflow: 'hidden',
  },
  scoreCardContent: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 12,
    paddingHorizontal: 14,
  },
  scoreCardIcon: {
    width: 40,
    height: 40,
    borderRadius: 10,
    backgroundColor: '#F1EEE4',
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 12,
  },
  scoreCardInfo: {
    flex: 1,
  },
  scoreTitle: {
    fontSize: 14,
    fontWeight: '700',
    color: '#3E3C37',
    marginBottom: 2,
  },
  scoreMetadata: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 6,
  },
  scoreMetaText: {
    fontSize: 12,
    color: '#6E675E',
  },
  scoreFooter: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
  },
  scoreNoteCount: {
    fontSize: 11,
    color: '#9B967B',
    fontWeight: '600',
  },
  scoreDate: {
    fontSize: 11,
    color: '#9B967B',
  },
  playIcon: {
    marginLeft: 8,
  },
  scoreCardActions: {
    flexDirection: 'row',
    borderTopWidth: 1,
    borderTopColor: '#D6D0C4',
  },
  actionButton: {
    flex: 1,
    paddingVertical: 10,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    gap: 6,
  },
  deleteButton: {
    borderLeftWidth: 1,
    borderLeftColor: '#D6D0C4',
  },
  actionButtonText: {
    fontSize: 12,
    fontWeight: '600',
    color: '#3E3C37',
    textTransform: 'uppercase',
    letterSpacing: 0.3,
  },
  deleteButtonText: {
    color: '#D9534F',
  },
});
