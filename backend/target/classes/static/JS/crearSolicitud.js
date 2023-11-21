function enviarSolicitud() {
    const numeroSoporte = document.getElementById('numeroSoporte').value;
    const idTipoSolicitud = document.getElementById('idTipoSolicitud').value;
    const idTipoSoporte = document.getElementById('idTipoSoporte').value;
    const descripcionSolicitudMuestraMedica = document.getElementById('descripcions').value;
    const usuarioEnSesion = JSON.parse(sessionStorage.getItem('usuario'));

    console.log('Número de Soporte:', numeroSoporte);
    console.log('ID de Tipo de Solicitud:', idTipoSolicitud);
    console.log('ID de Tipo de Soporte:', idTipoSoporte);
    console.log('Descripción de la Solicitud:', descripcionSolicitudMuestraMedica);
    const idUsuarioSesion = usuarioEnSesion ? usuarioEnSesion.id : null;
    console.log('ID de Usuario en Sesión:', idUsuarioSesion);

    const itemsList = window.itemsListToSend.map(item => {
        return {
            id: 0, // Ajustar el ID si es necesario
            idTipoItems: item.idTipoItems, // Obtener datos de itemsListToSend
            idSolicitudMuestraMedica: 0 // Debes establecer el ID de la solicitud de muestra médica aquí
        };
    });

    console.log('Antes de imprimir itemsList');
    console.log('Datos de itemsList a enviar:', itemsList);
    console.log('Después de imprimir itemsList');

    const requestData = {
        idUsuario: parseInt(idUsuarioSesion), // Asegúrate de convertir a número si es necesario
        numeroSoporte: numeroSoporte,
        idTipoSolicitud: parseInt(idTipoSolicitud), // Asegúrate de convertir a número si es necesario
        idTipoSoporte: parseInt(idTipoSoporte), // Asegúrate de convertir a número si es necesario
        descripcionSolicitudMuestraMedica: descripcionSolicitudMuestraMedica,
        itemsList: itemsList
    };

    fetch('http://localhost:8080/api/v1/solicitud/external', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al enviar los datos al servidor');
            }
            return response.json();
        })
        .then(data => {
            console.log('Solicitud enviada con éxito:', data);
            // Hacer algo con la respuesta del servidor si es necesario
        })
        .catch(error => {
            console.error('Error al enviar la solicitud:', error);
        });
}
