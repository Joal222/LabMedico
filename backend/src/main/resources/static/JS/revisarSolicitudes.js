document.addEventListener('DOMContentLoaded', function () {


const apiUrl = 'http://localhost:8080/api/v2/solicitudes';

// Función para obtener y mostrar los datos en la tabla
fetch(apiUrl)
    .then(response => response.json())
    .then(data => {
        const tableBody = document.getElementById('data');
        // Iterar a través de los datos y agregar filas a la tabla
        data.forEach(item => {
            const row = document.createElement('tr');
            row.innerHTML = `
                        <td class="select-checkbox"><input type="checkbox" onchange="handleCheckboxChange(this)"></td>
                        <td>${item.id}</td>
                        <td>${item.idUsuario}</td>
                        <td>${item.idTipoSolicitud}</td>
                        <td>${item.idTipoSoporte}</td>
                        <td>${item.descripcionSolicitudMuestraMedica}</td>
                        <td>${ new Date(item.fechaCreacionSolicitud).toLocaleDateString()}</td>
                        <td>${item.diasVencimientoSolicitud}</td>
                        <td>${item.estado}</td>
                    `;
            tableBody.appendChild(row);
        });
    })
    .catch(error => console.error('Error al obtener los datos:', error));


});