document.addEventListener("DOMContentLoaded", () => {
   const nombre = document.getElementById("nombreCliente");
    const doc = document.getElementById("documentoCliente");
    const correo = document.getElementById("correoCliente");
    const celular = document.getElementById("celularCliente");
    const fecha = document.getElementById("fechaNacimientoCliente");
    const dire = document.getElementById("direccionCliente");
    
    
       form.addEventListener("submit", (e) => {
        let valido = true;

        limpiarErrores();
       if (nombre.value.trim() === "") {
            marcarError(nombre, "El nombre del cliente es obligatorio");
            valido = false;
        }
          if (doc.value.trim() === "") {
            marcarError(doc, "El documento del cliente es obligatorio");
            valido = false;
        }
        if (correo.value.trim() === "") {
            marcarError(correo, "El correo del cliente es obligatorio");
            valido = false;
        }
         if (celular.value.trim() === "") {
            marcarError(celular, "El celular del cliente es obligatorio");
            valido = false;
        }
           if (fecha.value.trim() === "") {
            marcarError(fecha, "La fecha del cliente es obligatorio");
            valido = false;
        }
        if (dire.value.trim() === "") {
            marcarError(nombre, "La direccion del cliente es obligatorio");
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
