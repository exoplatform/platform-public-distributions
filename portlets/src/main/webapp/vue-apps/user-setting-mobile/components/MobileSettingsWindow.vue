<template>
  <v-card
    class="ma-4"
    flat>
    <v-toolbar
      class="border-box-sizing"
      flat>
      <v-btn
        class="mx-1"
        icon
        height="36"
        width="36"
        @click="$emit('back')">
        <v-icon size="20">
          {{ $vuetify.rtl && 'mdi-arrow-right' || 'mdi-arrow-left' }}
        </v-icon>
      </v-btn>
      <v-toolbar-title class="ps-0">
        {{ $t('MobileSettings.connect') }}
      </v-toolbar-title>
      <v-spacer />
    </v-toolbar>
    <div class="d-flex mx-11 py-6 my-6 justify-space-between flex-wrap align-center text-left">
      <div>
        <v-list-item>
          <v-list-item-content class="pb-0 pt-2 mt-auto mb-12">
            <v-list-item-title class="dark-grey-color font-weight-bold text-wrap">
              {{ $t('MobileSettings.subtitle') }}
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-content class="pb-0 pt-2 mt-auto mb-5">
            <v-list-item-title class="text-color text-wrap">
              {{ $t('MobileSettings.step.one') }}
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-content class="pb-0 pt-2 mt-auto mb-5">
            <v-list-item-title class="text-color text-wrap">
              {{ $t('MobileSettings.step.two') }}<v-icon class="primary--text primaryBackground mx-1 py-1 px-1" size="14">mdi-scan-helper</v-icon><span class="font-weight-medium">{{ $t('MobileSettings.bg') }}</span>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-content class="pb-0 pt-2 mt-auto mb-5">
            <v-list-item-title class="text-color text-wrap">
              {{ $t('MobileSettings.step.three') }}
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </div>
      <div class="ma-7">
        <div class="background-qrcode-image">
          <div
            class="mt-2"
            height="145"
            width="145"
            id="qrcode">
          </div>
        </div>
      </div>
    </div>
  </v-card>
</template>

<script>
export default {
  mounted(){
    // Options
    const options = {
      text: ((window.location.port > 0) ? `${window.location.protocol}//${window.location.hostname}:${window.location.port}/portal/login?username=${eXo.env.portal.userName}` : `${window.location.protocol}//${window.location.hostname}/portal/login?username=${eXo.env.portal.userName}`),
      width: 123,
      height: 123,
      colorLight: '#e1e8ee',
      logo: '/social-portlet/images/logo.png',
      logoBackgroundColor: '#e1e8ee',
      logoWidth: 38,
      quietZone: 8,
      quietZoneColor: '#e1e8ee',
      drawer: 'svg',
    };

    // Create new QRCode Object
    new QRCode(document.getElementById('qrcode'), options);
  },
};
</script>

