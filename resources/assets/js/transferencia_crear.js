document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("formCrearTransferencia");
    const cuentaOrigen = document.getElementById("idCuentaOrigen");
    const cuentaDestino = document.getElementById("idCuentaDestino");
    const valor = document.getElementById("valorTransferencia");
    const fecha = document.getElementById("fechaTransferencia");

    form.addEventListener("submit", (e) => {
        let valido = true;
        limpiarErrores();

        if (cuentaOrigen.value.trim() === "") {
            marcarError(cuentaOrigen, "Seleccione la cuenta de origen");
            valido = false;
        }

        if (cuentaDestino.value.trim() === "") {
            marcarError(cuentaDestino, "Seleccione la cuenta de destino");
            valido = false;
        }

        if (cuentaOrigen.value !== "" && cuentaDestino.value !== "" && cuentaOrigen.value === cuentaDestino.value) {
            marcarError(cuentaDestino, "La cuenta de destino debe ser diferente a la de origen");
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