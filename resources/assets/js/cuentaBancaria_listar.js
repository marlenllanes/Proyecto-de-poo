document.addEventListener("DOMContentLoaded", () => {
    console.log("Vista de listado de cuentas bancarias cargada.");

    const alertas = document.querySelectorAll(".alerta-exito-pro, .alerta-error-pro");
    alertas.forEach(alerta => {
        setTimeout(() => {
            alerta.style.opacity = "0";
            alerta.style.transition = "opacity 0.5s ease";
            setTimeout(() => alerta.remove(), 500);
        }, 5000);
    });
});
