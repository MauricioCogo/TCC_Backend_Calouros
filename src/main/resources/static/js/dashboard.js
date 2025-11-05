document.addEventListener("DOMContentLoaded", () => {
  const links = document.querySelectorAll(".sidebar nav ul li a");
  const currentPath = window.location.pathname;

  links.forEach(link => {
    if (link.getAttribute("href") === currentPath) {
      link.classList.add("active");
    } else {
      link.classList.remove("active");
    }
  });
});
