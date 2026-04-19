import { MUSIC_EYE_LOGO_HTML } from '../constants/musicEyeLogoHtml';

function replaceCssVar(html, varName, value) {
  const pattern = new RegExp(`(${varName}\\s*:\\s*)([^;]+)(;)`);
  if (pattern.test(html)) {
    return html.replace(pattern, `$1${value}$3`);
  }
  return html;
}

export function buildThemedLogoHtml(theme) {
  const colors = theme || {};
  let html = MUSIC_EYE_LOGO_HTML;
  html = replaceCssVar(html, '--eye-color', colors.ink || '#000000');
  html = replaceCssVar(html, '--eye-accent', colors.accent || colors.ink || '#000000');
  html = replaceCssVar(html, '--eye-bg', colors.surface || '#ffffff');
  html = replaceCssVar(html, '--eye-page-bg', colors.background || 'transparent');
  return html;
}