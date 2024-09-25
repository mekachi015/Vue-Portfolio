<template>
    

    <div class="nav-container">
        <nav>
            <a href="#home">Home</a>
            <a href="#about">About</a>
            <a href="#skills">Skills</a>
            <a href="#contact">Contact</a>
        </nav>
    </div>

</template>

<script>

    export default{
        name: 'navBarComponent',
    };
</script>

<style>

    .nav-container{
        position: sticky;
        top: 0;
        z-index: 1000; 
    }

    nav{
        display: flex;
        justify-content: space-around;
        background-color: var(--primary-color);
        padding: 2rem;

        a{
            color: white;
            text-decoration: none;
            font-size: 1.4rem;
        }
    }

    
</style>