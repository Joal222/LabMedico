
function obtenerInformacionGeneral() {
    fetch(`http://localhost:8080/api/v1/solicitud/${selectedRowId}`)
        .then(response => response.json())
        .then(data => {
            console.log('Datos de la fila seleccionada:', data);

            // Convertir la fecha de creación a un objeto Date
            const fechaCreacion = new Date(data.fechaCreacionSolicitud);

            // Obtener la parte de la fecha sin la hora (formato DD/MM/YYYY)
            const fechaFormateada = fechaCreacion.toLocaleDateString('es-ES');


            // Actualiza los elementos del modal con los datos obtenidos de la API
            document.getElementById('codigoSolicitud').textContent = data.id;
            document.getElementById('nitSolicitud').textContent = data.idUsuario.nit;
            document.getElementById('noSoporteModal').textContent = data.numeroSoporte;
            document.getElementById('tipoSoporte').textContent = data.idTipoSoporte.descripcion;
            document.getElementById('tipoSolicitante').textContent = data.idTipoSolicitante.descripcion;
            document.getElementById('usuarioAsignacion').textContent = data.usuarioAsignado;
            document.getElementById('estadoSolicitudModal').textContent = data.estado;
            document.getElementById('usuarioCreacion').textContent = data.idTipoSolicitante.creadoPor;
            document.getElementById('fechaCreacion').textContent = fechaFormateada;
            document.getElementById('descripcionSolicitante').textContent = data.descripcionSolicitudMuestraMedica;
            document.getElementById('telefono').textContent = data.idUsuario.telefono;
            document.getElementById('email').textContent = data.idUsuario.email;

            // Mostrar el modal después de actualizar los datos
            const modal = document.getElementById('myModal');
            modal.style.display = 'block';

            // Agregar la lógica para cerrar el modal al hacer clic en la 'x'
            const closeBtn = document.getElementsByClassName('close')[0];
            closeBtn.onclick = function() {
                modal.style.display = 'none';
            };

            // También cerrar el modal si se hace clic fuera de él
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = 'none';
                }
            };
        })
        .catch(error => {
            console.error('Error al obtener los datos:', error);
            // Manejas cualquier error que pueda ocurrir durante la solicitud
        });
}

function exportToExcel() {
    fetch(`http://localhost:8080/api/v1/solicitud/${selectedRowId}`)
        .then(response => response.json())
        .then(data => {
            // Convertir la fecha de creación a un objeto Date
            const fechaCreacion = new Date(data.fechaCreacionSolicitud);
            // Obtener la parte de la fecha sin la hora (formato DD/MM/YYYY)
            const fechaFormateada = fechaCreacion.toLocaleDateString('es-ES');

            const datosExportacion = {
                codigoSolicitud: data.id,
                nitSolicitud: data.idUsuario.nit,
                Descripcion: data.descripcionSolicitudMuestraMedica,
                fechaCreacionSolicitud:fechaFormateada  ,
                telefono : data.idUsuario.telefono,
                email : data.idUsuario.email
            };

            const wb = XLSX.utils.book_new();
            const ws = XLSX.utils.json_to_sheet([datosExportacion]);

            // Agregar la hoja al libro
            XLSX.utils.book_append_sheet(wb, ws, "Informacion");

            // Generar un archivo
            const wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'binary' });

            function s2ab(s) {
                const buf = new ArrayBuffer(s.length);
                const view = new Uint8Array(buf);
                for (let i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
                return buf;
            }

            const blob = new Blob([s2ab(wbout)], { type: 'application/octet-stream' });
            const fileName = "solicitud_info.xlsx";

            // Descargar el archivo
            if (navigator.msSaveBlob) {
                // Para IE y Edge
                navigator.msSaveBlob(blob, fileName);
            } else {
                const link = document.createElement('a');
                if (link.download !== undefined) {
                    const url = URL.createObjectURL(blob);
                    link.setAttribute('href', url);
                    link.setAttribute('download', fileName);
                    link.style.visibility = 'hidden';
                    document.body.appendChild(link);
                    link.click();
                    document.body.removeChild(link);
                }
            }
        })
        .catch(error => {
            console.error('Error al obtener los datos:', error);
            // Manejar cualquier error que pueda ocurrir durante la solicitud
        });
}

function eliminarSolicitud() {
    const confirmarEliminar = confirm('¿Estás seguro de que deseas eliminar esta solicitud?');

    if (confirmarEliminar) {
        fetch(`http://localhost:8080/api/v1/solicitud/${selectedRowId}`, {
            method: 'DELETE',
            // Puedes incluir headers y otros detalles según la estructura de tu API
        })
            .then(response => {
                if (response.ok) {
                    console.log(`Solicitud con ID ${selectedRowId} eliminada exitosamente.`);
                    // Aquí puedes actualizar la interfaz para reflejar la eliminación de la solicitud
                } else {
                    console.error('Error al eliminar la solicitud.');
                    // Manejar errores si la solicitud no fue exitosa
                }
            })
            .catch(error => {
                console.error('Error en la solicitud:', error);
                // Manejar errores de red u otros errores durante la solicitud
            });
    } else {
        // Acción cancelada, no se elimina la solicitud
        console.log('Eliminación de solicitud cancelada.');
    }
}



