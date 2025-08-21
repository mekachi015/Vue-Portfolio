<template>
  <div class="about-page">
    <div class="about-container">
      <div class="profile-section">
        <h1 class="title">About Me</h1>
        <div class="divider"></div>
        <div class="bio-content">
          <div class="bio-text">
            <p>
              I am a versatile full-stack developer specializing in frontend,
              backend, and Salesforce development, with a proven track record of
              building maintainable and scalable web applications. I recently
              completed an intensive full-stack learnership, gaining expertise
              in Angular, Java 17, and PostgreSQL, complemented by formal
              training in Salesforce administration and development. I further
              honed these skills during a practical placement at ABSA, where I
              contributed to a Salesforce development team. My hands-on
              experience includes Apex programming, building Lightning Web
              Components, and configuring platform automation tools.
            </p>
          </div>
          <div class="skills-highlight">
            <h3>Core Skills</h3>
            <div class="skills-list">
              <!-- Full Stack Development -->
              <div class="skill-item" 
                   @click="toggleSkill('fullstack')" 
                   :class="{ active: activeSkill === 'fullstack' }">
                <div class="skill-header">
                  <span>Full Stack Development</span>
                  <i class="arrow"></i>
                </div>
                <div class="skill-details" v-show="activeSkill === 'fullstack'">
                  <p>Technologies:</p>
                  <ul>
                    <li>Vue.js, Angular, React</li>
                    <li>Java Spring Boot, Node.js</li>
                    <li>PostgreSQL, MongoDB</li>
                    <li>Git, Docker, Jenkins</li>
                  </ul>
                </div>
              </div>

              <!-- Salesforce Development -->
              <div class="skill-item" 
                   @click="toggleSkill('salesforce')" 
                   :class="{ active: activeSkill === 'salesforce' }">
                <div class="skill-header">
                  <span>Salesforce Development</span>
                  <i class="arrow"></i>
                </div>
                <div class="skill-details" v-show="activeSkill === 'salesforce'">
                  <p>Expertise:</p>
                  <ul>
                    <li>Apex Programming & Testing</li>
                    <li>Lightning Web Components (LWC)</li>
                    <li>Salesforce Administration</li>
                    <li>Flow Builder & Process Automation</li>
                  </ul>
                </div>
              </div>

              <!-- Frontend Technologies -->
              <div class="skill-item" 
                   @click="toggleSkill('frontend')" 
                   :class="{ active: activeSkill === 'frontend' }">
                <div class="skill-header">
                  <span>Frontend Technologies</span>
                  <i class="arrow"></i>
                </div>
                <div class="skill-details" v-show="activeSkill === 'frontend'">
                  <p>Core Skills:</p>
                  <ul>
                    <li>HTML5, CSS3, JavaScript (ES6+)</li>
                    <li>Responsive & Mobile-First Design</li>
                    <li>State Management (Vuex, Redux)</li>
                    <li>Modern CSS (Flexbox, Grid, SASS)</li>
                  </ul>
                </div>
              </div>

              <!-- Backend Integration -->
              <div class="skill-item" 
                   @click="toggleSkill('backend')" 
                   :class="{ active: activeSkill === 'backend' }">
                <div class="skill-header">
                  <span>Backend Integration</span>
                  <i class="arrow"></i>
                </div>
                <div class="skill-details" v-show="activeSkill === 'backend'">
                  <p>Specialties:</p>
                  <ul>
                    <li>RESTful API Development</li>
                    <li>Database Design & Optimization</li>
                    <li>Authentication & Authorization</li>
                    <li>Microservices Architecture</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AboutPage',
  data() {
    return {
      activeSkill: null
    }
  },
  methods: {
    toggleSkill(skill) {
      this.activeSkill = this.activeSkill === skill ? null : skill;
    }
  }
}
</script>

<style scoped>
.about-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
}

.about-container {
  max-width: 1000px;
  width: 100%;
  margin: 0 auto;
  border-radius: 20px;
  overflow: hidden;
}

.profile-section {
  padding: 3rem;
}

.title {
  font-size: 2.5rem;
  color: var(--primary-color);
  margin-bottom: 1rem;
  font-weight: 600;
}

.divider {
  height: 4px;
  width: 60px;
  background: var(--primary-color);
  margin-bottom: 2rem;
}

.bio-content {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 3rem;
}

.bio-text p {
  font-size: 1.1rem;
  line-height: 1.8;
  color: #2c3e50;
  margin-bottom: 1.5rem;
}

.skills-highlight {
  background: #f8f9fa;
  padding: 2rem;
  border-radius: 15px;
  border-left: 4px solid var(--primary-color);
}

.skills-highlight h3 {
  color: var(--primary-color);
  margin-bottom: 1.5rem;
  font-size: 1.3rem;
}

.skills-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.skill-item {
  background: white;
  border-radius: 8px;
  border: 1px solid #eee;
  overflow: hidden;
  transition: all 0.3s ease;
  cursor: pointer;
}

.skill-item:hover {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.skill-header {
  padding: 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f8f9fa;
  font-weight: 500;
}

.skill-item.active .skill-header {
  background: var(--primary-color);
  color: white;
}

.arrow {
  width: 0;
  height: 0;
  border-left: 6px solid transparent;
  border-right: 6px solid transparent;
  border-top: 6px solid currentColor;
  transition: transform 0.3s ease;
}

.skill-item.active .arrow {
  transform: rotate(180deg);
}

.skill-details {
  padding: 1rem;
  background: white;
}

.skill-details p {
  margin-bottom: 0.8rem;
  color: var(--primary-color);
  font-weight: 500;
  font-size: 0.9rem;
}

.skill-details ul {
  list-style: none;
  padding-left: 0.5rem;
  margin: 0;
}

.skill-details li {
  padding: 0.4rem 0;
  font-size: 0.95rem;
  color: #2c3e50;
  position: relative;
  padding-left: 1.2rem;
}

.skill-details li::before {
  content: "•";
  position: absolute;
  left: 0;
  color: var(--primary-color);
}

@media (max-width: 768px) {
  .bio-content {
    grid-template-columns: 1fr;
    gap: 2rem;
  }

  .profile-section {
    padding: 2rem;
  }

  .title {
    font-size: 2rem;
  }

  .bio-text p {
    font-size: 1rem;
    line-height: 1.6;
  }

  .skill-header {
    padding: 0.8rem;
  }

  .skill-details {
    padding: 0.8rem;
  }
}

@media (max-width: 380px) {
  .about-page {
    padding: 1rem;
  }

  .profile-section {
    padding: 1.5rem;
  }

  .title {
    font-size: 1.8rem;
  }

  .skills-highlight {
    padding: 1.5rem;
  }

  .skill-header {
    font-size: 0.9rem;
  }

  .skill-details li {
    font-size: 0.85rem;
  }
}
</style>