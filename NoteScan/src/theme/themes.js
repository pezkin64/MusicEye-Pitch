export const LIGHT_THEMES = {
  original: {
    id: 'original',
    name: 'Original Score Info',
    colors: {
      background: '#F9F7F1',
      surface: '#FBFAF5',
      surfaceStrong: '#F1EEE4',
      border: '#D6D0C4',
      ink: '#3E3C37',
      inkMuted: '#6E675E',
      accent: '#E05A2A',
      success: '#5CB85C',
      danger: '#D9534F',
      statusBarStyle: 'dark-content',
    },
  },
  white: {
    id: 'white',
    name: 'Modern White',
    colors: {
      background: '#F7F8FA',
      surface: '#FFFFFF',
      surfaceStrong: '#EDF1F5',
      border: '#D5DDE6',
      ink: '#1F2933',
      inkMuted: '#5A6776',
      accent: '#6E7F93',
      success: '#5F8B74',
      danger: '#B86A6A',
      statusBarStyle: 'dark-content',
    },
  },
  gold: {
    id: 'gold',
    name: 'Modern Gold',
    colors: {
      background: '#F5F1E8',
      surface: '#FCFAF4',
      surfaceStrong: '#EDE3CF',
      border: '#D3C3A4',
      ink: '#2E281E',
      inkMuted: '#6A604F',
      accent: '#9C8352',
      success: '#6A8A68',
      danger: '#B87164',
      statusBarStyle: 'dark-content',
    },
  },
  ivory: {
    id: 'ivory',
    name: 'Linen',
    colors: {
      background: '#F5F1EA',
      surface: '#FCFAF5',
      surfaceStrong: '#EEE7DC',
      border: '#D2C6B4',
      ink: '#322D27',
      inkMuted: '#6B6358',
      accent: '#6C7C70',
      success: '#5E8A6C',
      danger: '#B96A64',
    },
  },
  slate: {
    id: 'slate',
    name: 'Cloud Slate',
    colors: {
      background: '#EEF2F5',
      surface: '#F8FAFC',
      surfaceStrong: '#DFE7EE',
      border: '#C4D0DB',
      ink: '#25303A',
      inkMuted: '#5C6B79',
      accent: '#607A94',
      success: '#537E6D',
      danger: '#B96969',
    },
  },
  sage: {
    id: 'sage',
    name: 'Moss Paper',
    colors: {
      background: '#EEF3EC',
      surface: '#F9FCF7',
      surfaceStrong: '#E0E9DD',
      border: '#C2CFC0',
      ink: '#2A342D',
      inkMuted: '#5F6C62',
      accent: '#6E8672',
      success: '#5B9168',
      danger: '#B76A67',
    },
  },
  rose: {
    id: 'rose',
    name: 'Dust Rose',
    colors: {
      background: '#F4EEEC',
      surface: '#FAF6F4',
      surfaceStrong: '#E9DEDA',
      border: '#CFBFBC',
      ink: '#362C2D',
      inkMuted: '#6D6061',
      accent: '#8B6C74',
      success: '#63836D',
      danger: '#B86866',
    },
  },
  clay: {
    id: 'clay',
    name: 'Clay Studio',
    colors: {
      background: '#F1EAE3',
      surface: '#F9F4EE',
      surfaceStrong: '#E3D5C8',
      border: '#C9B8A8',
      ink: '#342A24',
      inkMuted: '#695A4E',
      accent: '#8A705E',
      success: '#5E856F',
      danger: '#B4685F',
    },
  },
  walnut: {
    id: 'walnut',
    name: 'Cocoa',
    colors: {
      background: '#ECE4DA',
      surface: '#F6EFE6',
      surfaceStrong: '#DBCABA',
      border: '#BFA893',
      ink: '#31261F',
      inkMuted: '#645346',
      accent: '#7D644F',
      success: '#587F66',
      danger: '#AF665F',
      statusBarStyle: 'dark-content',
    },
  },
  fjord: {
    id: 'fjord',
    name: 'Muted Fjord',
    colors: {
      background: '#1B2328',
      surface: '#232D33',
      surfaceStrong: '#2A363D',
      border: '#3D4B54',
      ink: '#E8EEF2',
      inkMuted: '#AEBBC4',
      accent: '#6F8F9A',
      success: '#6A9A84',
      danger: '#C27B79',
      statusBarStyle: 'light-content',
    },
  },
  midnight: {
    id: 'midnight',
    name: 'Ink Night',
    colors: {
      background: '#13171B',
      surface: '#1A2127',
      surfaceStrong: '#222B33',
      border: '#36424E',
      ink: '#EDF2F6',
      inkMuted: '#AEB9C3',
      accent: '#6F86A8',
      success: '#6D9A80',
      danger: '#C57B7B',
      statusBarStyle: 'light-content',
    },
  },
  graphite: {
    id: 'graphite',
    name: 'Graphite',
    colors: {
      background: '#17181A',
      surface: '#202225',
      surfaceStrong: '#2A2D31',
      border: '#3E4248',
      ink: '#F1F1EF',
      inkMuted: '#B6B7B3',
      accent: '#8A8D95',
      success: '#71927B',
      danger: '#BE7A74',
      statusBarStyle: 'light-content',
    },
  },
  ember: {
    id: 'ember',
    name: 'Ember Smoke',
    colors: {
      background: '#1A1411',
      surface: '#241D19',
      surfaceStrong: '#2E251F',
      border: '#44362E',
      ink: '#EFE5DD',
      inkMuted: '#C0AEA1',
      accent: '#A6765D',
      success: '#6D9278',
      danger: '#C97A72',
      statusBarStyle: 'light-content',
    },
  },
};

export const DEFAULT_LIGHT_THEME_ID = 'original';

export const LEGACY_LIGHT_THEME_ID_ALIASES = {
  parchment: 'original',
  sand: 'original',
  mist: 'slate',
  mint: 'sage',
};

export const normalizeThemeId = (id) => {
  if (!id) return DEFAULT_LIGHT_THEME_ID;
  return LEGACY_LIGHT_THEME_ID_ALIASES[id] || id;
};

export const getThemeById = (id) => {
  const normalizedId = normalizeThemeId(id);
  if (normalizedId && LIGHT_THEMES[normalizedId]) return LIGHT_THEMES[normalizedId];
  return LIGHT_THEMES[DEFAULT_LIGHT_THEME_ID];
};

export const LIGHT_THEME_OPTIONS = Object.values(LIGHT_THEMES).map((theme) => ({
  id: theme.id,
  name: theme.name,
  colors: theme.colors,
}));

export const getStatusBarStyleForTheme = (theme) => {
  if (theme?.colors?.statusBarStyle) return theme.colors.statusBarStyle;
  const bg = String(theme?.colors?.background || '').replace('#', '');
  const r = parseInt(bg.slice(0, 2), 16);
  const g = parseInt(bg.slice(2, 4), 16);
  const b = parseInt(bg.slice(4, 6), 16);
  if ([r, g, b].some((value) => Number.isNaN(value))) return 'dark-content';
  const luminance = (0.299 * r + 0.587 * g + 0.114 * b) / 255;
  return luminance < 0.5 ? 'light-content' : 'dark-content';
};
