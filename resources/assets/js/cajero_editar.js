document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formEditarCajero");
    const sucursal = document.getElementById("sucursalCajero");
    const nombre = document.getElementById("nombreCajero");
    const turno = document.getElementById("turnoCajero");

    form.addEventListener("submit", (e) => {
        let valido = true;
        limpiarErrores();

        if (sucursal.value.trim() === "") {
            marcarError(sucursal, "Seleccione una sucursal");
            valido = false;
        }
        if (nombre.value.trim() === "") {
            marcarError(nombre, "El nombre es obligatorio");
            valido = false;
        }
        if (turno.value.trim() === "") {
            marcarError(turno, "Seleccione un turno");
            valido = false;
        }
        if (!valido) e.preventDefault();
    });

    function marcarError(campo, mensaje) {
        campo.classList.add("invalid-field");
        let label = campo.labels?.[0];
        if (label) {
            label.classList.add("error-text-pro");
            label.textContent = "* " + label.textContent.replace("* ", "");
        }
    }

    function limpiarErrores() {
        document.querySelectorAll(".invalid-field").forEach(el => el.classList.remove("invalid-field"));
        document.querySelectorAll(".error-text-pro").forEach(label => {
            label.classList.remove("error-text-pro");
            label.textContent = label.textContent.replace("* ", "");
        });
    }

    const alertas = document.querySelectorAll(".alerta-exito-pro, .alerta-error-pro");
    alertas.forEach(alerta => {
        setTimeout(() => {
            alerta.style.opacity = "0";
            setTimeout(() => alerta.remove(), 500);
        }, 5000);
    });
});