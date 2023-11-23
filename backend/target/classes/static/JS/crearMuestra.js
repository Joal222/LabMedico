document.addEventListener('DOMContentLoaded', function () {
    const selectedRowId = sessionStorage.getItem('selectedRowId');
    if (selectedRowId) {
        const apiUrl = `http://localhost:8080/api/v1/solicitud/${selectedRowId}`;
        const tableBody = document.getElementById('data');

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                tableBody.innerHTML = '';

                // Verifica si muestraList es un array y tiene datos
                if (Array.isArray(data.muestraList) && data.muestraList.length > 0) {
                    data.muestraList.forEach(muestra => {
                        const fechaCreacionMuestra = muestra.fechaCreacionMuestra.split('T')[0];

                        const row = document.createElement('tr');
                        row.innerHTML = `
                            <td class="select-checkbox"><input type="checkbox"></td>
                            <td>${muestra.id}</td>
                            <td>${muestra.idPresentacionMuestra.descripcion}</td>
                            <td>${muestra.idTipoMuestra.descripcion}</td>
                            <td>${muestra.idUnidadMedida.descripcion}</td>
                            <td>${muestra.cantidad}</td>
                            <td>${fechaCreacionMuestra}</td>
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
