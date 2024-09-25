<template>
  <div class="about-container interactive">
    <h1 data-fade class="my-name">Katlego Makoti</h1>
    <h2 data-fade class="my-occupation">Aspiring Developer</h2>
</div>

<div class="content interactive">
    <p class="fade-in delay-0" data-fade>
        I am Katlego Makoti, a 25-year-old full-stack development learner with a passion for both technology and sports.
        Currently, I am immersing myself in full-stack development, building dynamic, user-centric applications. My journey
        in IT began in high school and continued through varsity, where I gained comprehensive knowledge in programming
        languages and frameworks.
    </p>
    <p class="fade-in delay-1" data-fade>
        Originally from Limpopo, I moved to pursue opportunities that would enhance my skills and broaden my horizons. My role
        as a full-stack learner reflects my dedication to growth in the tech field. I am excited about the Shaaper Learnership,
        which aligns perfectly with my career goals.
    </p>
    <p class="fade-in delay-2" data-fade>
        Outside of work, I'm an avid gamer and basketball player. Gaming helps me understand complex systems, while basketball
        has taught me teamwork, strategy, and resilience, qualities I bring into my work environment.
    </p>
    <p class="fade-in delay-3" data-fade>
        Balancing my technical skills with my interests, I contribute to innovative projects, collaborate with diverse teams,
        and continuously evolve as a developer. My drive is fueled by the pursuit of excellence and a desire to make a
        meaningful impact in tech.
    </p>
</div>


</template>

<script>
    export default {
        name: 'AboutPage'
    };
</script>

<style>
   .about-container.interactive {
    text-align: center;
    margin: 2rem 0;
    position: relative; /* For animations */
}

.my-name{
    color: var( --tertiary-color)
}

.about-container.interactive h1, .about-container.interactive h2 {
    animation: slideIn 1s ease-out forwards;
}

@keyframes slideIn {
    from {
        transform: translateY(-20px);
        opacity: 0;
    }
    to {
        transform: translateY(0);
        opacity: 1;
    }
}

.content.interactive {
    max-width: 800px;
    margin: 0 auto;
    font-size: 1.1rem;
    line-height: 1.6;
    color: #555;
    position: relative; /* For animations */
}

.content.interactive p {
    animation: fadeIn 0.5s ease forwards;
}

@keyframes fadeIn {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}


</style>
