document.addEventListener('DOMContentLoaded', function () {
    const apiUrl = 'http://localhost:8080/api/v1/solicitudes';
    const tableBody = document.getElementById('data');
    const filtroCodigo = document.getElementById('codigo');
    const filtroSoporte = document.getElementById('usuarioAsig');
    const filtroNIT = document.getElementById('nit');
    const filtroTipoSolicitud = document.getElementById('tipoSolicitud');
    const filtroEstadoSolicitud = document.getElementById('estadoSolicitud');
    const buttonFiltrar = document.getElementById('buttonFiltrar');
    const buttonLimpiar = document.getElementById('buttonLimpiar');
    const filtroFechaDesde = document.getElementById('fechaDesde');
    const filtroFechaHasta = document.getElementById('fechaHasta');

// Agregar eventos de cambio a los filtros de fecha
    filtroFechaDesde.addEventListener('input', fetchAndPopulateTable);
    filtroFechaHasta.addEventListener('input', fetchAndPopulateTable);

    function fetchAndPopulateTable() {
        // Obtener los valores actuales de los filtros
        const codigoValue = filtroCodigo.value.trim();
        const soporteValue = filtroSoporte.value.trim();
        const nitValue = filtroNIT.value.trim();
        const tipoSolicitudValue = filtroTipoSolicitud.value.trim();
        const estadoSolicitudValue = filtroEstadoSolicitud.value.trim();
        const fechaDesdeValue = filtroFechaDesde.value;
        const fechaHastaValue = filtroFechaHasta.value;

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                // Limpiar la tabla antes de agregar nuevas filas
                tableBody.innerHTML = '';

                // Obtener la fecha actual y la fecha hace un mes
                const fechaActual = new Date();
                const fechaUnMesAntes = new Date();
                fechaUnMesAntes.setMonth(fechaUnMesAntes.getMonth() - 1);

                // Iterar a través de los datos y agregar filas a la tabla
                data.forEach(item => {
                    // Verificar si el elemento cumple con los filtros
                    const cumpleFiltros =
                        (codigoValue === '' || item.id.toString().includes(codigoValue)) &&
                        ((item.idTipoSoporte && soporteValue === '') || (item.idTipoSoporte && item.idTipoSoporte.descripcion.includes(soporteValue))) &&
                        (nitValue === '' || item.idUsuario.nit.includes(nitValue)) &&
                        (tipoSolicitudValue === '' || item.idTipoSolicitud.descripcion.includes(tipoSolicitudValue)) &&
                        (fechaDesdeValue === '' || (new Date(item.fechaCreacionSolicitud) >= new Date(fechaUnMesAntes) && new Date(fechaDesdeValue) >= fechaUnMesAntes && new Date(fechaDesdeValue) <= fechaActual)) &&
                        (fechaHastaValue === '' || (new Date(item.fechaCreacionSolicitud) <= fechaActual && new Date(fechaHastaValue) >= new Date(fechaDesdeValue) && new Date(fechaHastaValue) <= fechaActual));

                    if (cumpleFiltros) {
                        const row = document.createElement('tr');
                        row.innerHTML = `
                        <td class="select-checkbox"><input type="checkbox" onchange="handleCheckboxChange(this)"></td>
                        <td>${item.id}</td>
                        <td>${item.idTipoSolicitante.descripcion}</td>
                        <td>${item.idTipoSoporte ? item.idTipoSoporte.descripcion : ''}</td>
                        <td>${new Date(item.fechaCreacionSolicitud).toLocaleDateString()}</td>
                        <td>${item.idUsuario.nit}</td>
                        <td>${item.idTipoSolicitud.descripcion}</td>
                        <td>${item.estado}</td>
                    `;
                        tableBody.appendChild(row);
                    }
                });
            })
            .catch(error => console.error('Error al obtener los datos:', error));
    }


    function limpiarFiltros() {
        // Limpiar los valores de los filtros
        filtroCodigo.value = '';
        filtroSoporte.value = '';
        filtroNIT.value = '';
        filtroTipoSolicitud.value = '';
        filtroEstadoSolicitud.value = '';
        filtroFechaDesde.value = '';
        filtroFechaHasta.value = '';
        // Volver a cargar la tabla con los datos originales
        fetchAndPopulateTable();
    }

    // Llamar a la función para cargar la tabla inicialmente
    fetchAndPopulateTable();

    // Agregar eventos de cambio a los filtros
    filtroCodigo.addEventListener('input', fetchAndPopulateTable);
    filtroSoporte.addEventListener('input', fetchAndPopulateTable);
    filtroNIT.addEventListener('input', fetchAndPopulateTable);
    filtroTipoSolicitud.addEventListener('change', fetchAndPopulateTable);
    filtroEstadoSolicitud.addEventListener('change', fetchAndPopulateTable);

    // Agregar evento de clic al botón de limpiar
    buttonLimpiar.addEventListener('click', function (event) {
        event.preventDefault();
        limpiarFiltros();
    });
    filtroFechaDesde.addEventListener('input', function () {
        const fechaDesdeValue = filtroFechaDesde.value;
        const fechaHastaValue = filtroFechaHasta.value;

        // Validar que la fecha desde no sea menor a un mes antes de la fecha actual
        const fechaUnMesAntes = new Date();
        fechaUnMesAntes.setMonth(fechaUnMesAntes.getMonth() - 1);

        if (fechaDesdeValue !== '' && new Date(fechaDesdeValue) < fechaUnMesAntes) {
            alert('La fecha desde no puede ser menor a un mes antes de la fecha actual.');
            filtroFechaDesde.value = '';  // Limpiar la fecha desde
            return;
        }

        // Validar que la fecha hasta no sea menor que la fecha desde
        if (fechaHastaValue !== '' && new Date(fechaHastaValue) < new Date(fechaDesdeValue)) {
            alert('La fecha hasta no puede ser menor que la fecha desde.');
            filtroFechaHasta.value = '';  // Limpiar la fecha hasta
            return;
        }

        // Actualizar la tabla
        fetchAndPopulateTable();
    });

    filtroFechaHasta.addEventListener('input', function () {
        const fechaDesdeValue = filtroFechaDesde.value;
        const fechaHastaValue = filtroFechaHasta.value;

        // Validar que la fecha desde no sea menor a un mes antes de la fecha actual
        const fechaUnMesAntes = new Date();
        fechaUnMesAntes.setMonth(fechaUnMesAntes.getMonth() - 1);

        if (fechaDesdeValue !== '' && new Date(fechaDesdeValue) < fechaUnMesAntes) {
            alert('La fecha desde no puede ser menor a un mes antes de la fecha actual.');
            filtroFechaDesde.value = '';  // Limpiar la fecha desde
            return;
        }

        // Validar que la fecha hasta no sea menor que la fecha desde
        if (fechaHastaValue !== '' && new Date(fechaHastaValue) < new Date(fechaDesdeValue)) {
            alert('La fecha hasta no puede ser menor que la fecha desde.');
            filtroFechaHasta.value = '';  // Limpiar la fecha hasta
            return;
        }

        // Actualizar la tabla
        fetchAndPopulateTable();
    });
});