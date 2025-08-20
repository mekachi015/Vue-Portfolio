<template>
    <div class="main-container">
        <!-- Name and Heading Container -->
        <div class="name-container">
            <h1>Katlego Makoti</h1>
            <h2>Developer</h2>
            
            <div class="quotes"> 
                <h3 class="personal-quote">"Vision Without Execution Is Delusion"</h3>
                <h3 class="tatto-quote">メメントモリ</h3>
            </div>

            <!-- Social Media Links -->
            <div class="social-links">
                <a href="https://linkedin.com/in/yourusername" 
                   target="_blank" 
                   rel="noopener noreferrer" 
                   aria-label="LinkedIn Profile">
                    <svg class="bi bi-linkedin" xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M0 1.146C0 .513.526 0 1.175 0h13.65C15.474 0 16 .513 16 1.146v13.708c0 .633-.526 1.146-1.175 1.146H1.175C.526 16 0 15.487 0 14.854zm4.943 12.248V6.169H2.542v7.225zm-1.2-8.212c.837 0 1.358-.554 1.358-1.248-.015-.709-.52-1.248-1.342-1.248S2.4 3.226 2.4 3.934c0 .694.521 1.248 1.327 1.248zm4.908 8.212V9.359c0-.216.016-.432.08-.586.173-.431.568-.878 1.232-.878.869 0 1.216.662 1.216 1.634v3.865h2.401V9.25c0-2.22-1.184-3.252-2.764-3.252-1.274 0-1.845.7-2.165 1.193v.025h-.016l.016-.025V6.169h-2.4c.03.678 0 7.225 0 7.225z"/>
                    </svg>
                </a>

                <a href="https://github.com/yourusername" 
                   target="_blank" 
                   rel="noopener noreferrer" 
                   aria-label="GitHub Profile">
                    <svg class="bi bi-github" xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27s1.36.09 2 .27c1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.01 8.01 0 0 0 16 8c0-4.42-3.58-8-8-8"/>
                    </svg>
                </a>
            </div>
        </div>

        <!-- Image Container -->
        <div class="image-container">
            <img 
                :src="require('@/assets/oroborous.webp')" 
                alt="Profile Image" 
                class="home-img"
            >
        </div>
    </div>
</template>



<style scoped>
:root{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}
.main-container {
    display: grid;
    grid-template-areas:
        'img img name name'
        'img img icon icon';
    gap: 2rem;
    padding: 5%;
    justify-items: center;
    text-align: left;
    max-width: 1200px;
    min-height: 100vh;
    margin: 0 auto;
    overflow: hidden;
}

.name-container {
    grid-area: name;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.name-container h1{
    font-size: 1.5rem;
}

.image-container {
    grid-area: img;
    width: 100%;
    display: flex;
    justify-content: center;
    overflow: hidden;
}

.icon-container {
    grid-area: icon;
    display: flex;
    justify-content: space-around;
    align-items: center;
    gap: 15px;
}

.home-img {
    max-width: 250px;
    max-height: 250px;
    border-radius: 50%;
    box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.3);
    transition: transform 0.3s;
    object-fit: cover;
}

@keyframes rotate {
    from { transform: rotate(0deg); }
    to { transform: rotate(360deg); }
}

.home-img:hover {
    animation: rotate 5s linear infinite;
}

h1 {
    font-size: 3rem;
    color: var(--primary-color);
    margin-bottom: 1rem;
}

h2 {
    font-size: 2rem;
    margin-bottom: 1.5rem;
}

.quotes {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

.bi-github, .bi-linkedin {
    transition: transform 0.3s ease;
    cursor: pointer;
}

.bi-github:hover, .bi-linkedin:hover {
    transform: scale(1.1);
}

/* Tablet Responsive */
@media (max-width: 768px) {
    .main-container {
        grid-template-areas:
            'img'
            'name'
            'icon';
        gap: 1.5rem;
        padding: 3rem 1.5rem;
        overflow: hidden;
    }

    .home-img {
        width: 200px;
        height: 200px;
    }

    h1 {
        font-size: 2.5rem;
        text-align: center;
    }

    h2 {
        font-size: 1.8rem;
        text-align: center;
    }

    .quotes {
        text-align: center;
        gap: 0.8rem;
    }

    .name-container {
        align-items: center;
        color: var(--primary-color);
    }
}

/* Mobile Responsive */
@media (max-width: 480px) {
    .main-container {
        padding: 2rem 1rem;
        border-width: 3px;
        overflow: hidden;
    }

    .home-img {
        width: 150px;
        height: 150px;
    }

    h1 {
        font-size: 2rem;
    }

    h2 {
        font-size: 1.5rem;
    }

    .quotes {
        gap: 0.5rem;
    }

    .name-container {
       color: var(--primary-color);
    }

    .quotes h3 {
        font-size: 1rem;
    }

    .bi-github, .bi-linkedin {
        width: 25px;
        height: 25px;
    }
}
</style>

