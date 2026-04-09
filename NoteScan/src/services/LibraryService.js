/**
 * Library Service
 *
 * Manages a persistent library of scanned music scores.
 * Stores metadata about each scanned score separate from the OMR cache.
 *
 * Each library entry contains:
 * - id: unique identifier
 * - title: user-friendly title (from filename or MusicXML metawork)
 * - timestamp: when it was scanned
 * - engine: which OMR engine processed it
 * - scoreData: the actual OMR result
 * - metadata: from the score (composer, key, time sig, etc)
 */
import AsyncStorage from '@react-native-async-storage/async-storage';

const LIBRARY_INDEX_KEY = 'music_library_index_v1';
const LIBRARY_ENTRY_KEY = 'music_library_entry_v1_';
const MAX_LIBRARY_ENTRIES = 50;

class LibraryServiceClass {
  _index = null; // Array of { id, title, timestamp, engine, noteCount, composer, timeSignature }

  /**
   * Initialize or load the library index from storage.
   */
  async load() {
    try {
      const raw = await AsyncStorage.getItem(LIBRARY_INDEX_KEY);
      this._index = raw ? JSON.parse(raw) : [];
      console.log(`📚 LibraryService: loaded ${this._index.length} entries`);
    } catch (e) {
      console.warn('LibraryService: load failed', e.message);
      this._index = [];
    }
  }

  /**
   * Add a scanned score to the library.
   * Returns the created entry ID.
   */
  async addScore(scoreData, engine, titleHint = 'Untitled Score') {
    try {
      await this.load();

      // Extract metadata from scoreData
      const noteCount = scoreData?.notes?.filter(n => n.type === 'note')?.length || 0;
      const composer = scoreData?.metadata?.composer || 'Unknown';
      const title = scoreData?.metadata?.workTitle || titleHint;
      const timeSignature = scoreData?.metadata?.timeSignature || '4/4';
      const tempo = scoreData?.metadata?.tempo || 120;

      const id = `score_${Date.now()}_${Math.random().toString(36).substr(2, 9)}`;
      const entry = {
        id,
        title,
        timestamp: Date.now(),
        engine,
        noteCount,
        composer,
        timeSignature,
        tempo,
      };

      // Store the full scoreData separately
      const entryKey = `${LIBRARY_ENTRY_KEY}${id}`;
      await AsyncStorage.setItem(entryKey, JSON.stringify(scoreData));

      // Update index
      this._index.unshift(entry);

      // Prune if needed
      if (this._index.length > MAX_LIBRARY_ENTRIES) {
        const toRemove = this._index.pop();
        await AsyncStorage.removeItem(`${LIBRARY_ENTRY_KEY}${toRemove.id}`);
      }

      // Save index
      await AsyncStorage.setItem(LIBRARY_INDEX_KEY, JSON.stringify(this._index));

      console.log(`📚 LibraryService: added score "${title}" (${noteCount} notes)`);
      return id;
    } catch (e) {
      console.warn('LibraryService: addScore failed', e.message);
      return null;
    }
  }

  /**
   * Get all library entries (just metadata, not full scoreData).
   */
  async getAll() {
    try {
      await this.load();
      return this._index || [];
    } catch (e) {
      console.warn('LibraryService: getAll failed', e.message);
      return [];
    }
  }

  /**
   * Get a full score entry by ID.
   */
  async getScore(id) {
    try {
      const entryKey = `${LIBRARY_ENTRY_KEY}${id}`;
      const raw = await AsyncStorage.getItem(entryKey);
      if (!raw) return null;

      const scoreData = JSON.parse(raw);
      console.log(`📚 LibraryService: loaded score ${id}`);
      return scoreData;
    } catch (e) {
      console.warn('LibraryService: getScore failed', e.message);
      return null;
    }
  }

  /**
   * Delete a score from the library.
   */
  async deleteScore(id) {
    try {
      await this.load();

      // Remove from index
      this._index = this._index.filter(e => e.id !== id);
      await AsyncStorage.setItem(LIBRARY_INDEX_KEY, JSON.stringify(this._index));

      // Remove full entry
      const entryKey = `${LIBRARY_ENTRY_KEY}${id}`;
      await AsyncStorage.removeItem(entryKey);

      console.log(`📚 LibraryService: deleted score ${id}`);
    } catch (e) {
      console.warn('LibraryService: deleteScore failed', e.message);
    }
  }

  /**
   * Clear all library entries.
   */
  async clearAll() {
    try {
      const keys = await AsyncStorage.getAllKeys();
      const libraryKeys = keys.filter(k => k.startsWith(LIBRARY_ENTRY_KEY));

      if (libraryKeys.length > 0) {
        await AsyncStorage.multiRemove(libraryKeys);
      }

      await AsyncStorage.removeItem(LIBRARY_INDEX_KEY);
      this._index = [];

      console.log(`📚 LibraryService: cleared all entries`);
    } catch (e) {
      console.warn('LibraryService: clearAll failed', e.message);
    }
  }
}

export const LibraryService = new LibraryServiceClass();
