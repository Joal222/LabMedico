document.addEventListener('DOMContentLoaded', function () {
    let noSolicitud =    document.getElementById('noMuestra')
    let Presentacion = document.getElementById('presentacion')
    let tipoMuestra = document.getElementById('tipoMuestra')


    const selectedRowId = sessionStorage.getItem('selectedRowId');
    if (selectedRowId) {
        const apiUrl = `http://localhost:8080/api/v1/solicitud/${selectedRowId}`;
        const tableBody = document.getElementById('data');

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                tableBody.innerHTML = '';
                // Agregar texto a los elementos de la lista
                noSolicitud.textContent = `No. Solicitud: ${data.id}`;
                Presentacion.textContent = `Tipo de Solicitud: ${data.idTipoSolicitud.descripcion}`;
                tipoMuestra.textContent = `Descripcion: ${data.descripcionSolicitudMuestraMedica}`;

                // Verifica si muestraList es un array y tiene datos
                if (Array.isArray(data.itemsList) && data.itemsList.length > 0) {
                    data.itemsList.forEach(items => {


                        const row = document.createElement('tr');
                        row.innerHTML = `
                            <td class="select-checkbox"><input type="checkbox"></td>
                            <td>${items.id}</td>
                            <td>${items.idTipoItems.nombre}</td>
                            <td>${items.idTipoItems.descripcion}</td>
                            <td>${items.idTipoItems.idTipoExamen.nombre}</td>
                            <td>${items.idTipoItems.idTipoExamen.descripcion}</td>
                            <td> <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-trash" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
                                            <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
                                            <path d="M4 7l16 0"></path>
                                            <path d="M10 11l0 6"></path>
                                            <path d="M14 11l0 6"></path>
                                            <path d="M5 7l1 12a2 2 0 0 0 2 2h8a2 2 0 0 0 2 -2l1 -12"></path>
                                            <path d="M9 7v-3a1 1 0 0 1 1 -1h4a1 1 0 0 1 1 1v3"></path>
                                            </svg>
                                            <?xml version="1.0" encoding="UTF-8"?>
<svg xmlns="http://www.w3.org/2000/svg" id="Layer_1" class="icon icon-tabler icon-tabler-trash" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none"><path d="m17,24H5c-2.757,0-5-2.243-5-5V7c0-.553.447-1,1-1s1,.447,1,1v12c0,1.654,1.346,3,3,3h12c.553,0,1,.447,1,1s-.447,1-1,1Zm0-4h-8c-2.757,0-5-2.243-5-5V5C4,2.243,6.243,0,9,0h10c2.757,0,5,2.243,5,5v8h-4c-1.654,0-3,1.346-3,3v4Zm-2-7v-2h2c.553,0,1-.447,1-1s-.447-1-1-1h-2v-2c0-.553-.447-1-1-1s-1,.447-1,1v2h-2c-.553,0-1,.447-1,1s.447,1,1,1h2v2c0,.553.447,1,1,1s1-.447,1-1Zm5,2c-.552,0-1,.448-1,1v3.642c.443-.198.855-.467,1.201-.814l2.627-2.627c.346-.346.616-.758.814-1.201h-3.642Z"/></svg>
</td>
                            
                            
                        `;
                        tableBody.appendChild(row);
                    });
                } else {
                    const noSolicitudesRow = document.createElement('tr');
                    noSolicitudesRow.innerHTML = `
                         <td colspan="7" style="text-align: center;">Sin Muestras creadas.</td>
                    `;
                    tableBody.appendChild(noSolicitudesRow);
                }

            })
            .catch(error => console.error('Error al obtener los datos:', error));
    } else {
        console.error('selectedRowId no está definido en sessionStorage.');
    }
});



