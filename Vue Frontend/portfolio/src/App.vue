<template>
  <div id="app">
    <NavComponent />
    <main>
      <section id="home">
        <HomePage />
      </section>
      <section id="about">
        <AboutPage />
      </section>
      <section id="skills">
        <SkillsPage/>
      </section>
      <section id="contact">
        <ContactPage />
      </section>
    </main>
  </div>
</template>

<script>
import NavComponent from '@/components/navBarComponent.vue';
import HomePage from '@/views/HomePage.vue';
import AboutPage from '@/views/AboutPage.vue';
import SkillsPage from '@/views/SkillsPage.vue';
import ContactPage from '@/views/ContactPage.vue';


export default {
  name: 'App',
  components: {
    NavComponent,
    HomePage,
    AboutPage,
    SkillsPage,
    ContactPage,
  },
};
</script>

<style>
/* Add some styles for the sections */
section {
  padding: 50px;
  min-height: 100vh; /* Makes each section full height */
  scroll-margin-top: 50px; /* Adjust based on navbar height */
}
</style>
