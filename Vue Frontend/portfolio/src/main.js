// import { createApp } from 'vue';
// import App from './App.vue';
// import '@/assets/styles/global.css';
// import router from '@/router/index.js';

// createApp(App).mount('#app');
// createApp(App).use(router).mount('#app'); 

import { createApp } from 'vue';
import App from './App.vue'; // Your root component
import router from './router'; // Adjust the path as needed
import '@/assets/styles/global.css';

const app = createApp(App);
app.use(router); // Use the router
app.mount('#app'); // Mount the app to the DOM