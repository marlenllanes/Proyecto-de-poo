document.addEventListener("DOMContentLoaded", () => {
    // El HTML ya viene armado desde el servidor Java.
    // Este archivo se deja disponible para interacciones del DOM exclusivas de la tabla.
    console.log("Vista de listado de formas de pago cargada correctamente.");
    
    // Opcional: Cerrar alertas automáticamente después de 5 segundos
    const alertas = document.querySelectorAll(".alerta-exito-pro, .alerta-error-pro");
    alertas.forEach(alerta => {
        setTimeout(() => {
            alerta.style.opacity = "0";
            setTimeout(() => alerta.remove(), 500);
        }, 5000);
    });
});
