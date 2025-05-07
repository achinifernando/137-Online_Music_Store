document.addEventListener("DOMContentLoaded", function () {
    const slider = document.querySelector(".slider");
    let index = 0;
    
    function slide() {
        index++;
        if (index >= slider.children.length) {
            index = 0;
        }
        slider.style.transform = `translateX(${-index * 250}px)`;
    }
    setInterval(slide, 3000); // Change every 3 seconds
});










