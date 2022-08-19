import MobileSettings from './components/MobileSettings.vue';
import MobileSettingsWindow from './components/MobileSettingsWindow.vue';
import MobileSettingsDownloadWindow from './components/MobileSettingsDownloadWindow.vue';

const components = {
  'mobile-settings': MobileSettings,
  'mobile-settings-window': MobileSettingsWindow,
  'mobile-settings-download-window': MobileSettingsDownloadWindow,

};

for (const key in components) {
  Vue.component(key, components[key]);
}