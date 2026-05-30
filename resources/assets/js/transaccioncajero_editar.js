document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("formEditarTransaccion");
    const cajero = document.getElementById("idCajero");
    const cuenta = document.getElementById("idCuenta");
    const tipo = document.getElementById("tipoTransaccionCajero");
    const valor = document.getElementById("valorTransaccionCajero");
    const fecha = document.getElementById("fechaTransaccionCajero");

    form.addEventListener("submit", (e) => {
        let valido = true;
        limpiarErrores();

        if (cajero.value.trim() === "") {
            marcarError(cajero, "Seleccione un cajero");
            valido = false;
        }

        if (cuenta.value.trim() === "") {
            marcarError(cuenta, "Seleccione una cuenta");
            valido = false;
        }

        if (tipo.value.trim() === "") {
            marcarError(tipo, "Seleccione un tipo de transacción");
            valido = false;
        }

        if (valor.value.trim() === "" || parseFloat(valor.value) <= 0) {
            marcarError(valor, "Ingrese un valor válido mayor a cero");
            valido = false;
        }

        if (fecha.value.trim() === "") {
            marcarError(fecha, "La fecha es obligatoria");
            valido = false;
        }

        if (!valido)
            e.preventDefault();
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