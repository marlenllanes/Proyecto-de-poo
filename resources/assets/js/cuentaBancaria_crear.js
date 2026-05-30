document.addEventListener("DOMContentLoaded", () => {
    const form    = document.getElementById("formCrearCuenta");
    const numero  = document.getElementById("numeroCuentaBancaria");
    const tipo    = document.getElementById("tipoCuentaBancaria");
    const saldo   = document.getElementById("saldoCuentaBancaria");
    const fecha   = document.getElementById("fechaAperturaCuentaBancaria");
    const estado  = document.getElementById("estadoCuentaBancaria");
    const cliente = document.getElementById("clienteCuentaBancaria");

    form.addEventListener("submit", (e) => {
        let valido = true;
        limpiarErrores();

        if (numero.value.trim() === "") {
            marcarError(numero, "err-numero", "El número de cuenta es obligatorio");
            valido = false;
        }
        if (tipo.value.trim() === "") {
            marcarError(tipo, "err-tipo", "Seleccione un tipo de cuenta");
            valido = false;
        }
        if (saldo.value.trim() === "" || isNaN(saldo.value) || Number(saldo.value) < 0) {
            marcarError(saldo, "err-saldo", "Ingrese un saldo válido (mayor o igual a 0)");
            valido = false;
        }
        if (fecha.value.trim() === "") {
            marcarError(fecha, "err-fecha", "La fecha de apertura es obligatoria");
            valido = false;
        }
        if (estado.value.trim() === "") {
            marcarError(estado, "err-estado", "Seleccione un estado");
            valido = false;
        }
        if (cliente.value.trim() === "") {
            marcarError(cliente, "err-cliente", "Seleccione un cliente");
            valido = false;
        }

        if (!valido) e.preventDefault();
    });

    function marcarError(campo, idSpan, mensaje) {
        campo.classList.add("invalid-field");
        const label = campo.closest("div")?.previousElementSibling;
        if (label && label.tagName === "LABEL") {
            label.classList.add("error-text-pro");
        }
        const span = document.getElementById(idSpan);
        if (span) {
            span.textContent = mensaje;
            span.style.display = "block";
        }
    }

    function limpiarErrores() {
        document.querySelectorAll(".invalid-field").forEach(el => el.classList.remove("invalid-field"));
        document.querySelectorAll(".error-text-pro").forEach(lbl => lbl.classList.remove("error-text-pro"));
        document.querySelectorAll(".msg-error").forEach(sp => {
            sp.style.display = "none";
            sp.textContent = "";
        });
    }
});