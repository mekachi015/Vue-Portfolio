<template>
  <div class="skills-container">
    <h2 class="page-title">Here are a few skills I have picked up over the years</h2>
    <div class="skills-list">
      <div>
        <div class="skills-info">
          <div class="progress-circle">
            <img src="#" alt="skill-icon" class="skill-icon">
          </div>
          <h1>{{ skills.name }}</h1>
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
      skills: [
        { name: "Angular", proficiency: 90, icon: "path/to/angular-icon.png" },
        { name: "Vue", proficiency: 85, icon: "path/to/vue-icon.png" },
        { name: "JavaScript", proficiency: 80, icon: "path/to/javascript-icon.png" },
        { name: "CSS", proficiency: 75, icon: "path/to/css-icon.png" },
        // Add more skills as needed
      ],
    };
  },
};
</script>




<style scoped>
.page-title{
  font-size: 1rem;
}
.skills-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px;
  background-color: #f7f7f7;
  font-family: 'Poppins', sans-serif;
}

h2 {
  font-size: 2.5rem;
  color: #333;
  margin-bottom: 20px;
}

.skills-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 20px;
}

.skills-card {
  display: flex;
  justify-content: center;
  align-items: center;
}

.progress-circle {
  position: relative;
  width: 100px; /* Adjust size as needed */
  height: 100px; /* Adjust size as needed */
  border-radius: 50%;
  background-color: #e9ecef;
  display: flex;
  align-items: center;
  justify-content: center;
}

.skill-icon {
  position: absolute;
  width: 50px; /* Adjust icon size as needed */
  height: 50px; /* Adjust icon size as needed */
  z-index: 1;
}

.skill-name {
  position: absolute;
  bottom: 10px; /* Adjust position */
  color: #333; /* Text color */
  font-size: 1rem; /* Font size */
  z-index: 2; /* Ensure text is above icon */
}
</style>




