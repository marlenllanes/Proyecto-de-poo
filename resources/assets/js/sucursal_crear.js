document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formCrearSucursal");
    const nombre = document.getElementById("nombreSucursal");
    const direccion = document.getElementById("direccionSucursal");
    const telefono = document.getElementById("telefonoSucursal");

    form.addEventListener("submit", (e) => {
        let valido = true;
        limpiarErrores();

        if (nombre.value.trim() === "") {
            marcarError(nombre, "El nombre es obligatorio");
            valido = false;
        }
        if (direccion.value.trim() === "") {
            marcarError(direccion, "La dirección es obligatoria");
            valido = false;
        }
        if (telefono.value.trim() === "") {
            marcarError(telefono, "El teléfono es obligatorio");
            valido = false;
        }
        if (!valido) e.preventDefault();
    });

    function marcarError(campo) {
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
