document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector(".form-crear");
    const nombre = document.getElementById("nombreCategoria");
    const estado = document.getElementById("estadoCategoria");

    form.addEventListener("submit", (e) => {
        let valido = true;

        limpiarErrores();

        if (nombre.value.trim() === "") {
            marcarError(nombre, "El nombre es obligatorio");
            valido = false;
        }

        if (estado.value.trim() === "") {
            marcarError(estado, "Seleccione un estado");
            valido = false;
        }

        if (!valido) {
            e.preventDefault();
        }
    });

    function marcarError(campo, mensaje) {
        campo.classList.add("invalid-field");

        let label = campo.labels?.[0];
        if (label) {
            label.classList.add("error-text-pro");
            label.textContent = "* " + label.textContent.replace("* ", "");
        }

        let msg = campo.parentElement.querySelector(".msg-error");
        if (msg) {
            msg.textContent = mensaje;
            msg.style.display = "block";
        }
    }



    function limpiarErrores() {
        document.querySelectorAll(".invalid-field").forEach(el => el.classList.remove("invalid-field"));
        document.querySelectorAll(".error-text-pro").forEach(label => {
            label.classList.remove("error-text-pro");
            label.textContent = label.textContent.replace("* ", "");
        });
    }
});
