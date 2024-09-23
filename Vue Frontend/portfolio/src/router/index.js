import { createRouter, createWebHistory } from "vue-router";
import { HomePage } from '@/views/HomePage.vue';
import { AboutPage } from '@/views/AboutPage.vue';
import { SkillsPage } from '@/views/SkillsPage.vue';
import { ContactPage } from '@/views/ContactPage.vue';

const routes = [
  {
    path: "/home",
    name: "HomePage",
    component: HomePage,
  },
  {
    path: "/about",
    name: "AboutPage",
    component: AboutPage,
  },
  {
    path: "/skills",
    name: "SkillsPage",
    component: SkillsPage,
  },
  {
    path: "/contact",
    name: "ContactPage",
    component: ContactPage,
  },
  {
    path: '/',
    redirect: 'home' // Redirect to Home as default
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
