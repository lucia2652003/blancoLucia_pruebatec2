//Validar si le ponemos fecha de filtración
function filtracion() {
    if(document.getElementById("fecha").value == ""){
        alert("Debes introducir una fecha");
    }else{
        alert("Turnos desde la fecha "+document.getElementById("fecha").value)
    }

}

//En el Java hicimos validaciones, también debemos avisar en la interfaz de usuario.
function enviar() {
    if(document.getElementById("fecha").value == "" || document.getElementById("descripcion")){
        alert("Hay campos inválidos o que no se han rellenado, volviendo al listado");
    }else{
        alert("Formulario válido, volviendo al listado");
    }
}