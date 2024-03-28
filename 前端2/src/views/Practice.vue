<template>
    <div class="slideshow-container">
        <div class="slider" :style="{ transform: `translateX(-${currentIndex * 100}%)` }">
            <img v-for="(image, index) in images" :key="index" :src="image.src" class="fade" alt="Slideshow Image">
        </div>
        <button @click="prevSlide" class="prev">&#10094;</button>
        <button @click="nextSlide" class="next">&#10095;</button>
    </div>
</template>

<script>
import image1 from '@/assets/clothe1.jpg';
import image2 from '@/assets/clothe2.jpg';
import image3 from '@/assets/clothe3.jpg';
export default {
    data() {
        return {
            images: [
                { src: image1 },
                { src: image2 },
                { src: image3 }
            ],
            currentIndex: 0
        };
    },
    mounted() {
        this.startSlideshow();
    },
    methods: {
        startSlideshow() {
            setInterval(this.nextSlide, 5000); // Change slide every 5 seconds
        },
        nextSlide() {
            this.currentIndex++;
            if (this.currentIndex >= this.images.length) {
                this.currentIndex = 0;
            }
        },
        prevSlide() {
            this.currentIndex--;
            if (this.currentIndex < 0) {
                this.currentIndex = this.images.length - 1;
            }
        }
    },
};
</script>

<style scoped>
.slideshow-container {
    position: relative;
    height: 50vh;
    /* Full height */
    width: 50vw;
    /* Full width */
    overflow: hidden;
}

.slider {
    display: flex;
    transition: transform 1s ease-in-out;
}

.fade {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.prev,
.next {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    background-color: rgba(0, 0, 0, 0.5);
    color: white;
    border: none;
    cursor: pointer;
    padding: 16px;
    font-size: 20px;
    z-index: 100;
}

.prev {
    left: 0;
}

.next {
    right: 0;
}
</style>