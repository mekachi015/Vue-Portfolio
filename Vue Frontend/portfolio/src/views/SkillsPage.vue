<template>
  <div class="skills-dashboard">
    <h2 class="section-header">Skills Dashboard</h2>
    <div class="projects-list">
      <div
        v-for="skill in skills"
        :key="skill.name"
        class="skill-item"
        :style="{ '--progress': (skill.proficiency / 100) * 100 }"
      >
        <div class="skill-info">
          <!-- Dynamically render the Font Awesome icon -->
          <!-- <i :class="skill.icon" class="skill-icon"></i> -->
          <h3>{{ skill.name }}</h3>
          <div class="circular-progress">
            <svg class="circle" viewBox="0 0 36 36">
              <path
                class="circle-bg"
                d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831"
              />
              <path
                class="circle-progress"
                :stroke-dasharray="'0, 100'" 
                d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831"
              />
            </svg>
            <span class="percentage"><i :class="skill.icon" class="skill-icon"></i></span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SkillProgress",
  data() {
    return {
      skills: [
        { name: "HTML", proficiency: 80, icon: "fab fa-html5" },
        { name: "CSS", proficiency: 70, icon: "fab fa-css3-alt" },
        { name: "JavaScript", proficiency: 85, icon: "fab fa-js" },
        { name: "PostgreSQL", proficiency: 60, icon: "fas fa-database" },
       // { name: "Oracle SQL", proficiency: 55, icon: "fas fa-database" },
       // { name: "TypeScript", proficiency: 50, icon: "fas fa-typescript" },
        { name: "Java", proficiency: 65, icon: "fab fa-java" },
        { name: "Python", proficiency: 51, icon: "fab fa-python" },
        { name: "Vue", proficiency: 90, icon: "fab fa-vuejs" },
        { name: "Node.js", proficiency: 75, icon: "fab fa-node-js" },
        { name: "Express.js", proficiency: 70, icon: "fas fa-server" },
      ],
    };
  },
};
</script>

<style scoped>

.section-header{
  font-family: 'Courier New', Courier, monospace;
  color: var(--primary-color);
}


.skill-icon{
  font-size: 3rem;
}

.skills-dashboard {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px;
  /* background-color: #ffebcd; Warm off-white background */
  font-family: 'Poppins', sans-serif;
}

h2 {
  font-family: 'Pacifico', cursive; /* Creative handwritten font */
  font-size: 2.5rem;
  color: #d32f2f; /* Primary color */
  text-align: center;
  margin-bottom: 30px;
  animation: fade-in 1s ease;
}

/* .projects-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 20px; /* Space between items 
} */

.projects-list {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px; 
  justify-items: center;
}

/* .skill-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  border-radius: 12px;
  background: linear-gradient(145deg, #e6e6e6, #ffffff);
  box-shadow: 20px 20px 60px #d4d4d4, -20px -20px 60px #ffffff;
  transition: transform 0.3s, box-shadow 0.3s;
  width: 250px;
  animation: bounce-in 0.5s ease; /* Added bounce effect 
} */

.skill-item {
  width: 70%; /* Adjust width to fit 3 cards per row */
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  border-radius: 12px;
  background: linear-gradient(145deg, #e6e6e6, #ffffff);
  box-shadow: 20px 20px 60px #d4d4d4, -20px -20px 60px #ffffff;
  transition: transform 0.3s, box-shadow 0.3s;
  animation: bounce-in 0.5s ease; /* Added bounce effect */
}

.skill-item:hover {
  transform: scale(1.05);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
}

.skill-info {
  display: flex;
  align-items: center;
  flex-grow: 1;
}

h3 {
  font-size: 1.5rem;
  color: var(--primary-color); /* Accent color */
  margin-right: 20px;
  text-align: left;
}

.circular-progress {
  position: relative;
  width: 70px;
  height: 70px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.circular-progress svg {
  width: 100%;
  height: 100%;
}

.circle {
  fill: none;
  stroke-width: 4;
}

.circle-bg {
  stroke: #ddd;
}

.circle-progress {
  stroke-linecap: round;
  stroke: #3498db; /* Primary color */
  stroke-dasharray: 0 100;
  transition: stroke-dasharray 1.5s ease, stroke 0.5s ease;
}

.skill-item:hover .circle-progress {
  stroke-dasharray: var(--progress), 100;
  stroke: #e74c3c; /* Accent color on hover */
}

.percentage {
  position: absolute;
  font-size: 14px;
  font-weight: bold;
  color: #333;
}

/* Animations */
@keyframes fade-in {
  from {
    opacity: 0;
    transform: translateY(-10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes bounce-in {
  0% {
    transform: scale(0.5);
    opacity: 0;
  }
  50% {
    transform: scale(1.1);
  }
  100% {
    transform: scale(1);
    opacity: 1;
  }
}

/* Additional responsiveness */
@media (max-width: 600px) {
  h2 {
    font-size: 2rem;
  }

  .skill-item {
    width: 90%;
  }

  h3 {
    font-size: 1.2rem;
  }
}
</style>