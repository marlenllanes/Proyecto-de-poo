document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("formCrearTarjeta");
    const numero = document.getElementById("numeroTarjeta");
    const tipo = document.getElementById("tipoTarjeta");
    const fechaExpedicion = document.getElementById("fechaExpedicionTarjeta");
    const fechaVencimiento = document.getElementById("fechaVencimientoTarjeta");
    const estado = document.getElementById("estadoTarjeta");
    const cliente = document.getElementById("idCliente");
    const cuenta = document.getElementById("idCuenta");

    form.addEventListener("submit", (e) => {
        let valido = true;
        limpiarErrores();

        if (numero.value.trim() === "") {
            marcarError(numero, "El número de tarjeta es obligatorio");
            valido = false;
        }

        if (tipo.value.trim() === "") {
            marcarError(tipo, "Seleccione un tipo de tarjeta");
            valido = false;
        }

        if (fechaExpedicion.value.trim() === "") {
            marcarError(fechaExpedicion, "La fecha de expedición es obligatoria");
            valido = false;
        }

        if (fechaVencimiento.value.trim() === "") {
            marcarError(fechaVencimiento, "La fecha de vencimiento es obligatoria");
            valido = false;
        }

        if (estado.value.trim() === "") {
            marcarError(estado, "Seleccione un estado");
            valido = false;
        }

        if (cliente.value.trim() === "") {
            marcarError(cliente, "Seleccione un cliente");
            valido = false;
        }

        if (cuenta.value.trim() === "") {
            marcarError(cuenta, "Seleccione una cuenta");
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