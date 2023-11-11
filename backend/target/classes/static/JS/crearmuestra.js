function mostrar(){
    swal({
        title: "Información General",
        text: "se muestra Info General",
        icon: "info",
      });
}
function expediente(){
  swal({
      title: "Expediente",
      text: "se muestra Info",
      icon: "info",
    });
}
function contribuyente(){
  swal({
      title: "contribuyente",
      text: "se muestra Info",
      icon: "info",
    });
}
function eliminar(){
  swal({
    title: "Eliminar Solicitud",
    text: "Una vez eliminada la solicitud, no se podra recuperar",
    icon:"warning",
    buttons: true,
    dangerMode: true,
  })
  .then((willDelete) => {
    if (willDelete) {
      swal("Solicitud Eliminada", {
        icon: "success",
      });
    } else {
      swal("Regresar!","","info")
    }
  });
}