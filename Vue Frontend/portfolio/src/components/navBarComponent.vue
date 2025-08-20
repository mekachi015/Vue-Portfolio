<template>
    <div class="nav-container">
        <!-- Hamburger Button -->
        <button class="hamburger" :class="{ 'is-active': isMenuOpen }" @click="toggleMenu" aria-label="Menu">
            <span class="hamburger-line"></span>
            <span class="hamburger-line"></span>
            <span class="hamburger-line"></span>
        </button>

        <!-- Side Navigation -->
        <nav :class="{ 'nav-active': isMenuOpen }">
            <div class="nav-links">
                <a href="#home" @click="closeMenu">Home</a>
                <a href="#about" @click="closeMenu">About</a>
                <a href="#skills" @click="closeMenu">Skills</a>
                <a href="#contact" @click="closeMenu">Contact</a>
            </div>
        </nav>

        <!-- Overlay -->
        <div 
            class="overlay" 
            :class="{ 'overlay-active': isMenuOpen }" 
            @click="closeMenu">
        </div>
    </div>
</template>

<script>
export default {
    name: 'navBarComponent',
    data() {
        return {
            isMenuOpen: false
        }
    },
    methods: {
        toggleMenu() {
            this.isMenuOpen = !this.isMenuOpen;
            document.body.style.overflow = this.isMenuOpen ? 'hidden' : 'auto';
        },
        closeMenu() {
            this.isMenuOpen = false;
            document.body.style.overflow = 'auto';
        }
    }
};
</script>

<style scoped>
.nav-container {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    z-index: 1000;
}

/* Hamburger Button Styling */
.hamburger {
    position: fixed;
    top: 1.5rem;
    right: 1.5rem;
    width: 2.5rem;
    height: 2rem;
    background: transparent;
    border: none;
    cursor: pointer;
    padding: 0;
    z-index: 1002; /* Ensure it's above the nav and overlay */
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.hamburger-line {
    width: 100%;
    height: 3px;
    background: var(--primary-color);
    border-radius: 5px;
    transition: all 0.3s ease-in-out;
    transform-origin: center;
}

/* Hamburger Animation - make it white when menu is open */
.hamburger.is-active .hamburger-line {
    background: white; /* Change color when menu is open */
}

.hamburger.is-active .hamburger-line:nth-child(1) {
    transform: translateY(13px) rotate(45deg);
}

.hamburger.is-active .hamburger-line:nth-child(2) {
    transform: scaleX(0);
    opacity: 0;
}

.hamburger.is-active .hamburger-line:nth-child(3) {
    transform: translateY(-13px) rotate(-45deg);
}

/* Navigation Menu */
nav {
    position: fixed;
    top: 0;
    right: -300px; /* Changed from left to right */
    height: 100vh;
    width: 300px;
    background-color: var(--primary-color);
    padding-top: 5rem;
    transition: transform 0.3s ease-in-out;
    z-index: 1001;
    box-shadow: -2px 0 5px rgba(0, 0, 0, 0.1);
}

.nav-active {
    transform: translateX(-300px); /* Changed to slide from right */
}

.nav-links {
    display: flex;
    flex-direction: column;
    padding: 2rem;
}

.nav-links a {
    color: white;
    text-decoration: none;
    font-size: 1.4rem;
    padding: 1rem 0;
    transition: all 0.3s ease;
    border-bottom: 2px solid transparent;
}

.nav-links a:hover {
    color: var(--tertiary-color);
    border-bottom: 2px solid var(--tertiary-color);
    padding-left: 1rem;
}

/* Remove the close button since we're using hamburger animation */
.close-btn {
    display: none;
    background: var(--primary-color)
}

/* Overlay */
.overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    visibility: hidden;
    opacity: 0;
    transition: all 0.3s ease-in-out;
    z-index: 1000;
    backdrop-filter: blur(3px);
}

.overlay-active {
    visibility: visible;
    opacity: 1;
}

/* Responsive Design */
@media (max-width: 768px) {
    .hamburger {
        top: 1rem;
        right: 1rem;
    }

    nav {
        width: 100%;
        right: -100%;
    }

    .nav-active {
        transform: translateX(-100%);
    }

    .close.btn{
        color: white;
        background-color:red;
    }
}
</style>