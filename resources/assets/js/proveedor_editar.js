document.addEventListener("DOMContentLoaded", () => {
    const nombre = document.getElementById("nombreProveedor ");
    const correo = document.getElementById("correoProveedor");
    const celular = document.getElementById("celularProveedor");
    
    
       form.addEventListener("submit", (e) => {
        let valido = true;

        limpiarErrores();
        

        if (nombre.value.trim() === "") {
            marcarError(nombre, "El nombre del proveedor es obligatorio");
            valido = false;
        }
         if (correo.value.trim() === "") {
            marcarError(correo, "El correo del proveedor es obligatorio");
            valido = false;
        }
           if (celular.value.trim() === "") {
            marcarError(celular, "El correo del proveedor es obligatorio");
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
