document.addEventListener("DOMContentLoaded", () => {

    console.log("Vista de listado de transacciones cargada correctamente.");

    const alertas = document.querySelectorAll(".alerta-exito-pro, .alerta-error-pro");
    alertas.forEach(alerta => {
        setTimeout(() => {
            alerta.style.opacity = "0";
            setTimeout(() => alerta.remove(), 500);
        }, 5000);
    });

});