document.addEventListener("DOMContentLoaded", () => {
  const toggle = document.getElementById("theme-toggle");
  const root = document.documentElement;

  // Cargar tema guardado o usar el del HTML
  const savedTheme = localStorage.getItem("theme");
  const currentTheme = savedTheme || root.getAttribute("data-theme") || "light";

  root.setAttribute("data-theme", currentTheme);
  toggle.checked = currentTheme === "dark";

  // Cambiar tema al hacer click
  toggle.addEventListener("change", () => {
    const newTheme = toggle.checked ? "dark" : "light";
    root.setAttribute("data-theme", newTheme);
    localStorage.setItem("theme", newTheme);
  });
});
