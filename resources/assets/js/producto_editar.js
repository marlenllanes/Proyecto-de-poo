document.addEventListener("DOMContentLoaded", () => {
    const nombre = document.getElementById("nombreProducto");
    const categoria = document.getElementById("categoriaProducto");
    const proveedor = document.getElementById("proveedorProducto");
    const precio = document.getElementById("precioProducto");
    const costo = document.getElementById("costoProducto");
    const cantidad = document.getElementById("cantidadProducto");
    
       form.addEventListener("submit", (e) => {
        let valido = true;

        limpiarErrores();
        

        if (nombre.value.trim() === "") {
            marcarError(nombre, "El nombre del producto es obligatorio");
            valido = false;
        }

        if (categoria.value.trim() === "") {
            marcarError(categoria, "Seleccione una categoría");
            valido = false;
        }

        if (proveedor.value.trim() === "") {
            marcarError(proveedor, "Seleccione un proveedor");
            valido = false;
        }

        if (precio.value.trim() === "" || isNaN(precio.value) || Number(precio.value) <= 0) {
            marcarError(precio, "Ingrese un precio válido");
            valido = false;
        }

        if (costo.value.trim() === "" || isNaN(costo.value) || Number(costo.value) < 0) {
            marcarError(costo, "Ingrese un costo válido");
            valido = false;
        }

        if (cantidad.value.trim() === "" || isNaN(cantidad.value) || Number(cantidad.value) < 0) {
            marcarError(cantidad, "Ingrese una cantidad válida");
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
