<template>
  <v-card 
    class="ma-4 w-80"
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
        {{ $t('MobileSettings.download') }}
      </v-toolbar-title>
      <v-spacer />
    </v-toolbar>
    <span v-if="!isMobile" class="d-flex mx-16 my-6 justify-space-between flex-wrap align-center text-left">
      <v-responsive max-width="70%">
        <span class="body-1">
          {{ $t('MobileSettings.download.detail') }}
        </span>
      </v-responsive>
      <span>
        <span class="background-qrcode-image h1">
          <span
            class="mt-2"
            height="145"
            width="145"
            id="qrcode">
          </span>
        </span>
      </span>
      <v-container>
        <v-row align="center">
          <v-divider /><span class="grey--text text--lighten-2">{{ $t('MobileSettings.download.or') }}</span><v-divider />
        </v-row>
      </v-container>
    </span>
    <span
      class="d-flex justify-space-between flex-wrap align-center text-left" 
      :class="`${isMobile ? 'mt-10' : ''}`">
      <div class="ml-16 mt-n9">
        <span>
          <span class="body-1 ">
            {{ $t(getAppropriateStringName()) }}
          </span>
        </span> 
      </div>  
      <div class="mb-10 mr-5">
        <div v-if="getMobileOperatingSystem()==='android'||getMobileOperatingSystem()==='unknown'" class="d-block-flex">
          <a
            class="mr-16 mb-6  d-block"
            :class="`${isMobile ? 'ml-14' : ''}`"
            href="https://play.google.com/store/apps/details?id=org.exoplatform">
            <img 
              :alt="$t('MobileSettings.download.google.play.alt')"
              src="/community-portlets/images/googlePlay.png">
          </a>
        </div>
        
        <div v-if="getMobileOperatingSystem()==='ios'||getMobileOperatingSystem()==='unknown'" class="d-block-flex mt-n5"> 
          <a
            class=" ml-1 d-block"
            :class="`${isMobile ? 'ml-14 mt-6' : ''}`"
            href="https://apps.apple.com/tn/app/exo/id410476273">
            <img 
              :alt="$t('MobileSettings.download.app.store.alt')" 
              src="/community-portlets/images/appStore.png">
          </a>
        </div>
      </div>
    </span>
  </v-card>
</template>
<script>
export default {
  mounted(){
    // Options
    const options = {
      text: ((window.location.port > 0) ? `${window.location.protocol}//${window.location.hostname}:${window.location.port}/portal/rest/mobile/app/download` : `${window.location.protocol}//${window.location.hostname}/portal/rest/mobile/app/download`),
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
    new QRCode(document.getElementById('qrcode'),options);
  },
  computed: {
    isMobile() {
      return this.$vuetify && this.$vuetify.breakpoint && this.$vuetify.breakpoint.name === 'xs';
    },
  },
  methods: {
    getMobileOperatingSystem() {
      const userAgent = navigator.userAgent || navigator.vendor || window.opera;
      if (/android/i.test(userAgent)) {
        return 'android';
      }
      if (/iPad|iPhone|iPod/.test(userAgent) && !window.MSStream) {
        return 'ios';
      }
      return 'unknown';
    },
    getAppropriateStringName(){
      const stringName= this.getMobileOperatingSystem();

      if (stringName==='android'){
        return 'MobileSettings.download.alternative.android' ; 
      }
      if (stringName==='ios'){
        return 'MobileSettings.download.alternative.ios' ; 
      }
      else {
        return 'MobileSettings.download.alternative' ; 
      }
    }
  }
};
</script>
