<template>
  <v-app>
    <template v-if="displayed">
      <mobile-settings-window
        v-if="displayDetails"
        @back="closeDetail" />
      
      <mobile-settings-download-window
        v-if="displayDownloadDetails"
        @back="closeDetail" />
        
      <v-card
        v-if="!displayDetails && !displayDownloadDetails"
        class="ma-4 border-radius"
        flat>
        <v-list>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title class="title text-color">
                {{ $t('MobileSettings.title') }}
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content class="px-0 pb-0 pt-2 mt-auto mb-2">
              <v-list-item-title class="text-color text-wrap">
                {{ $t('MobileSettings.download') }}
              </v-list-item-title>
            </v-list-item-content>
            <v-list-item-action>
              <v-btn
                small
                icon
                @click="openDownloadDetails">
                <v-icon size="24" class="text-sub-title">
                  {{ $vuetify.rtl && 'fa-caret-left' || 'fa-caret-right' }}
                </v-icon>
              </v-btn>
            </v-list-item-action>
          </v-list-item>
          <v-list-item>
            <v-list-item-content class="px-0 pb-0 pt-2 mt-auto mb-2">
              <v-list-item-title class="text-color text-wrap">
                {{ $t('MobileSettings.connect') }}
              </v-list-item-title>
            </v-list-item-content>
            <v-list-item-action>
              <v-btn
                small
                icon
                @click="openDetail">
                <v-icon size="24" class="text-sub-title">
                  {{ $vuetify.rtl && 'fa-caret-left' || 'fa-caret-right' }}
                </v-icon>
              </v-btn>
            </v-list-item-action>
          </v-list-item>
        </v-list>
      </v-card>
    </template>
  </v-app>
</template>

<script>
import MobileSettingsDownloadWindow from './MobileSettingsDownloadWindow.vue';
export default {
  components: { MobileSettingsDownloadWindow },
  data: () => ({
    id: `Mobile${parseInt(Math.random() * 10000)
      .toString()
      .toString()}`,
    displayed: true,
    displayDetails: false,
    displayDownloadDetails: false
  }),
  created() {
    document.addEventListener('hideSettingsApps', (event) => {
      if (event && event.detail && this.id !== event.detail) {
        this.displayed = false;
      }
    });
    document.addEventListener('showSettingsApps', () => this.displayed = true);
  },
  mounted() {
    this.$nextTick().then(() => this.$root.$applicationLoaded());
  },
  methods: {
    openDownloadDetails(){
      document.dispatchEvent(new CustomEvent('hideSettingsApps', {detail: this.id}));
      this.displayDownloadDetails = true;
    },
    openDetail() {
      document.dispatchEvent(new CustomEvent('hideSettingsApps', {detail: this.id}));
      this.displayDetails = true;
    },
    closeDetail() {
      document.dispatchEvent(new CustomEvent('showSettingsApps'));
      this.displayDetails = false;
      this.displayDownloadDetails=false ; 
    },
  },
};
</script>