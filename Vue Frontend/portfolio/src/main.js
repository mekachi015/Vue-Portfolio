import { createApp } from 'vue';
import App from './App.vue';
import '@/assets/styles/global.css';
import router from '@/router/index.js';

createApp(App).mount('#app');
createApp(App).use(router).mount('#app'); 

