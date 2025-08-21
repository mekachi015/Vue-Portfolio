<template>
  <div class="skills-dashboard">
    <h2 class="section-header">Skills Dashboard</h2>
    <div class="category-filters">
        <button 
          v-for="category in categories" 
          :key="category"
          :class="['filter-btn', { active: activeCategory === category }]"
          @click="filterByCategory(category)"
        >
          {{ category }}
        </button>
      </div>
   <div class="skills-scroll">
      <div class="skills-grid">
        <div
          v-for="skill in filteredSkills"
          :key="skill.name"
          class="skill-card"
          :class="{ 'skill-card-animate': shouldAnimate }"
        >
          <div class="skill-content">
            <div class="skill-info">
              <h3 class="skill-name">{{ skill.name }}</h3>
              <span class="skill-level">{{ getSkillLevel(skill.proficiency) }}</span>
            </div>
            <div class="skill-progress">
              <svg class="circle" viewBox="0 0 36 36">
                <path
                  class="circle-bg"
                  d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831"
                />
                <path
                  class="circle-progress"
                  :style="{ 'stroke-dasharray': `${skill.proficiency}, 100` }"
                  d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831"
                />
              </svg>
              <div class="skill-icon">
                <i :class="skill.icon"></i>
                <span class="skill-percentage">{{ skill.proficiency }}%</span>
              </div>
            </div>
          </div>
          <div class="skill-details">
            <p>{{ skill.description }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SkillsPage",
  data() {
    return {
      activeCategory: 'All',
      shouldAnimate: true,
      categories: ['All', 'Frontend', 'Backend', 'Database', 'Tools'],
      skills: [
        {
          name: "Vue.js",
          proficiency: 90,
          icon: "fab fa-vuejs",
          category: "Frontend",
          description: "Component development, state management, and routing"
        },
        {
          name: "JavaScript",
          proficiency: 85,
          icon: "fab fa-js",
          category: "Frontend",
          description: "ES6+, async programming, and DOM manipulation"
        },
        {
          name: "HTML/CSS",
          proficiency: 80,
          icon: "fab fa-html5",
          category: "Frontend",
          description: "Semantic HTML5, CSS3, Flexbox, and Grid layouts"
        },
        {
          name: "Java",
          proficiency: 65,
          icon: "fab fa-java",
          category: "Backend",
          description: "Spring Boot, REST APIs, and microservices"
        },
        {
          name: "Node.js",
          proficiency: 75,
          icon: "fab fa-node-js",
          category: "Backend",
          description: "Express.js, API development, and server-side logic"
        },
        {
          name: "PostgreSQL",
          proficiency: 60,
          icon: "fas fa-database",
          category: "Database",
          description: "Database design and complex query optimization"
        },
        {
          name: "Python",
          proficiency: 51,
          icon: "fab fa-python",
          category: "Backend",
          description: "Data analysis and automation scripting"
        },
        {
          name: "Git",
          proficiency: 85,
          icon: "fab fa-git-alt",
          category: "Tools",
          description: "Version control and collaborative development"
        }
      ]
    };
  },
  computed: {
    filteredSkills() {
      if (this.activeCategory === 'All') {
        return this.skills;
      }
      return this.skills.filter(skill => skill.category === this.activeCategory);
    }
  },
  methods: {
    filterByCategory(category) {
      this.shouldAnimate = false;
      this.activeCategory = category;
      setTimeout(() => {
        this.shouldAnimate = true;
      }, 100);
    },
    getSkillLevel(proficiency) {
      if (proficiency >= 90) return 'Expert';
      if (proficiency >= 75) return 'Advanced';
      if (proficiency >= 60) return 'Intermediate';
      return 'Beginner';
    }
  }
};
</script>

<style scoped>
.skills-dashboard {
  min-height: 100vh;
  padding: 4rem 2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 3rem;
  overflow: hidden; /* Prevent horizontal scroll on main container */
}

.section-header {
  font-size: 3rem;
  font-weight: 700;
  color: var(--primary-color);
  text-align: center;
  margin-bottom: 1rem;
  animation: fadeIn 0.8s ease;
}

.category-filters {
  display: flex;
  gap: 1rem;
  margin-bottom: 2rem;
  padding: 0.5rem;
  overflow-x: auto;
  width: 100%;
  max-width: 1200px;
  scrollbar-width: none; /* Firefox */
  -ms-overflow-style: none; /* IE/Edge */
}

.category-filters::-webkit-scrollbar {
  display: none; /* Chrome/Safari/Opera */
}

.filter-btn {
  padding: 0.5rem 1.5rem;
  border: 2px solid var(--primary-color);
  border-radius: 25px;
  background: transparent;
  color: var(--primary-color);
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
}

.filter-btn.active,
.filter-btn:hover {
  background: var(--primary-color);
  color: white;
}

/* Modified skills grid for horizontal scroll */
.skills-scroll {
  width: 100%;
  max-width: 1200px;
  overflow-x: auto;
  scroll-behavior: smooth;
  scrollbar-width: none;
  -ms-overflow-style: none;
  padding: 1rem 0;
}

.skills-scroll::-webkit-scrollbar {
  display: none;
}

.skills-grid {
  display: grid;
  grid-auto-flow: column;
  grid-auto-columns: minmax(300px, 400px);
  gap: 2rem;
  padding: 0 2rem;
}

.skill-card {
  background: white;
  border-radius: 16px;
  padding: 1.5rem;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
}

.skill-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 30px rgba(0, 0, 0, 0.12);
}

.skill-content {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  gap: 1rem;
}

.skill-info {
  flex: 1;
}

.skill-name {
  font-size: 1.25rem;
  font-weight: 600;
  color: var(--primary-color);
  margin: 0 0 0.5rem 0;
}

.skill-level {
  font-size: 0.9rem;
  color: #666;
  font-weight: 500;
}

.skill-progress {
  position: relative;
  width: 80px;
  height: 80px;
}

/* Optional: Add scroll indicators */
.skills-scroll {
  position: relative;
}

.skills-scroll::before,
.skills-scroll::after {
  content: '';
  position: absolute;
  top: 0;
  bottom: 0;
  width: 60px;
  pointer-events: none;
  z-index: 1;
}

.skills-scroll::before {
  left: 0;
  background: linear-gradient(to right, rgba(255,255,255,0.9), transparent);
}

.skills-scroll::after {
  right: 0;
  background: linear-gradient(to left, rgba(255,255,255,0.9), transparent);
}

/* Add scroll snap */
.skills-scroll {
  scroll-snap-type: x mandatory;
  -webkit-overflow-scrolling: touch;
}

.skill-card {
  scroll-snap-align: start;
  min-width: 300px;
}

.circle {
  transform: rotate(-90deg);
  width: 100%;
  height: 100%;
}

.circle-bg {
  fill: none;
  stroke: #f0f0f0;
  stroke-width: 3;
}

.circle-progress {
  fill: none;
  stroke: var(--primary-color);
  stroke-width: 3;
  stroke-linecap: round;
  transition: stroke-dasharray 1s ease-in-out;
}

.skill-icon {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.25rem;
}

.skill-icon i {
  font-size: 1.75rem;
  color: var(--primary-color);
}

.skill-percentage {
  font-size: 0.8rem;
  font-weight: 600;
  color: var(--primary-color);
}

.skill-details {
  margin-top: 1rem;
  padding-top: 1rem;
  border-top: 1px solid #eee;
}

.skill-details p {
  font-size: 0.9rem;
  color: #666;
  line-height: 1.5;
  margin: 0;
}

.skill-card-animate {
  animation: fadeInUp 0.5s ease forwards;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@media (max-width: 768px) {
    .skills-grid {
    grid-auto-columns: minmax(260px, 320px);
  }
  
  .category-filters {
    padding: 0 0.5rem;
  }
  
  .filter-btn {
    padding: 0.4rem 1rem;
    font-size: 0.9rem;
    white-space: nowrap;
  }
}

@media (max-width: 480px) {
    .skills-grid {
    grid-auto-columns: minmax(240px, 300px);
  }

  .skill-card {
    min-width: 240px;
  }

  .skills-scroll::before,
  .skills-scroll::after {
    width: 30px;
  }
}
</style>