document.addEventListener("DOMContentLoaded", () => {

    // ============================
    // Elementos base
    // ============================
    const navLinks = document.querySelectorAll(".nav-link, .dropdown-content-pro a");
    const hamburger = document.getElementById("hamburger");
    const navMenu = document.getElementById("navMenu");
    const dropdownToggles = document.querySelectorAll(".dropdown-toggle");

    // ============================
    // Overlay (creado si no existe)
    // ============================
    let overlay = document.querySelector(".nav-overlay");
    if (!overlay) {
        overlay = document.createElement("div");
        overlay.className = "nav-overlay";
        document.body.appendChild(overlay);
    }

    // ============================
    // Detectar sección activa
    // ============================
    const path = window.location.pathname;
    const sectionMap = {
        "/categorias/": "categorias",
        "/productos/": "productos",
        "/proveedores/": "proveedores",
        "/clientes/": "clientes",
        "/formapago/": "fpago",
        "/ventas/": "ventas"
    };

    let activeSection = "inicio";
    for (const [urlPart, section] of Object.entries(sectionMap)) {
        if (path.includes(urlPart)) {
            activeSection = section;
            break;
        }
    }

    // Marcar link activo (pero NO abrir dropdown automáticamente)
    navLinks.forEach((link) => {
        if (link.dataset.section === activeSection) {
            link.classList.add("active-link");
        }
    });

    // ============================
    // Función para abrir/cerrar menú móvil
    // ============================
    function toggleMobileMenu(forceClose = false) {
        const isMobile = window.innerWidth < 768;
        if (!isMobile && !forceClose)
            return;

        const shouldClose = forceClose || navMenu.classList.contains("active");

        navMenu.classList.toggle("active", !shouldClose);
        hamburger?.classList.toggle("active", !shouldClose);
        overlay.classList.toggle("active", !shouldClose);
        document.body.style.overflow = !shouldClose ? "hidden" : "";

        if (shouldClose) {
            document.querySelectorAll(".dropdown-pro.active").forEach((dd) => dd.classList.remove("active"));
    }
    }

    // ============================
    // Cerrar menú al navegar
    // ============================
    navLinks.forEach((link) => {
        link.addEventListener("click", (e) => {

            // Si el usuario tocó un dropdown-toggle en móvil, NO cerrar menú
            if (isMobile() && link.classList.contains("dropdown-toggle")) {
                return;
            }

            // Si es un link real (que navega), cerrar menú
            toggleMobileMenu(true);

            document.querySelectorAll(".dropdown-pro.active").forEach((dd) => {
                dd.classList.remove("active");
            });
        });
    });

    // ============================
    // Hamburguesa
    // ============================
    if (hamburger) {
        hamburger.addEventListener("click", (e) => {
            e.stopPropagation();
            toggleMobileMenu();
        });
    }

    overlay.addEventListener("click", () => toggleMobileMenu(true));

    document.addEventListener("keydown", (e) => {
        if (e.key === "Escape" && navMenu.classList.contains("active")) {
            toggleMobileMenu(true);
        }
    });

    // ============================
    // Función helper
    // ============================
    const isMobile = () => window.innerWidth < 768;

    // ============================
    // Dropdowns
    // ============================
    dropdownToggles.forEach((toggle) => {
        const dropdown = toggle.closest(".dropdown-pro");

        toggle.addEventListener("click", (e) => {
            if (isMobile()) {
                e.preventDefault();
                e.stopPropagation();

                document.querySelectorAll(".dropdown-pro.active").forEach((other) => {
                    if (other !== dropdown)
                        other.classList.remove("active");
                });

                dropdown.classList.toggle("active");
                return;
            }

            // Desktop
            if (!dropdown.classList.contains("active")) {
                document.querySelectorAll(".dropdown-pro.active").forEach((other) => {
                    if (other !== dropdown)
                        other.classList.remove("active");
                });
                dropdown.classList.add("active");
            }
        });
    });

    // Cerrar dropdowns en desktop al hacer clic fuera
    document.addEventListener("click", (e) => {
        if (isMobile())
            return;

        dropdownToggles.forEach((toggle) => {
            const dropdown = toggle.closest(".dropdown-pro");
            if (dropdown && !dropdown.contains(e.target)) {
                dropdown.classList.remove("active");
            }
        });
    });

    // ============================
    // Cerrar menú móvil al seleccionar opción del dropdown
    // ============================
    document.querySelectorAll(".dropdown-content-pro a").forEach((link) => {
        link.addEventListener("click", () => {
            if (isMobile() && navMenu.classList.contains("active")) {
                setTimeout(() => toggleMobileMenu(true), 150);
            }
        });
    });

    // ============================
    // Resize
    // ============================
    let resizeTimeout;
    window.addEventListener("resize", () => {
        clearTimeout(resizeTimeout);
        resizeTimeout = setTimeout(() => {

            if (window.innerWidth >= 768) {
                // Reset móvil
                navMenu.classList.remove("active");
                hamburger?.classList.remove("active");
                overlay.classList.remove("active");
                document.body.style.overflow = "";

                // NO abrir dropdowns automáticamente
                document.querySelectorAll(".dropdown-pro").forEach((dd) => {
                    dd.classList.remove("active");
                });

            } else {
                // En móvil, cerrar dropdowns sin active-link
                document.querySelectorAll(".dropdown-pro.active").forEach((dd) => {
                    if (!dd.querySelector(".active-link"))
                        dd.classList.remove("active");
                });
            }

        }, 200);
    });
});
