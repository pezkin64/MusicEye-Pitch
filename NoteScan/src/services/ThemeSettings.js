import AsyncStorage from '@react-native-async-storage/async-storage';
import { DEFAULT_LIGHT_THEME_ID, getThemeById, normalizeThemeId } from '../theme/themes';

const STORAGE_KEY = '@app_light_theme';

class ThemeSettingsClass {
  _themeId = DEFAULT_LIGHT_THEME_ID;

  getThemeId() {
    return normalizeThemeId(this._themeId);
  }

  getTheme() {
    return getThemeById(this._themeId);
  }

  async load() {
    try {
      const saved = await AsyncStorage.getItem(STORAGE_KEY);
      const normalized = normalizeThemeId(saved);
      if (normalized && getThemeById(normalized)) {
        this._themeId = normalized;
      }
    } catch (e) {
      // ignore, fallback to default
    }
    return this.getTheme();
  }

  async setThemeId(themeId) {
    const normalized = normalizeThemeId(themeId);
    const next = getThemeById(normalized);
    this._themeId = next.id;
    try {
      await AsyncStorage.setItem(STORAGE_KEY, next.id);
    } catch (e) {
      // ignore persistence issues
    }
    return next;
  }
}

export const ThemeSettings = new ThemeSettingsClass();
