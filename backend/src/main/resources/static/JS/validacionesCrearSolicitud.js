document.addEventListener('DOMContentLoaded', function () {
    const apiUrl = 'http://localhost:8080/api/v6/tipos-examenes';
    const selectExamenes = document.getElementById('listExamenes');
    const modal = document.getElementById('myModal');
    const titleExamenes = document.getElementById('titleExamenes');
    // Función para obtener y agregar opciones al select
    function cargarTiposExamenes() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                // Limpiar las opciones existentes
                selectExamenes.innerHTML = '<option value="null"> --Tipo De Examenes-- </option>';

                // Agregar nuevas opciones desde los datos obtenidos
                data.forEach(tipoExamen => {
                    const option = document.createElement('option');
                    option.value = tipoExamen.id;
                    option.textContent = tipoExamen.descripcion;
                    selectExamenes.appendChild(option);
                });

                // Agregar evento de cambio al select
                selectExamenes.addEventListener('change', abrirModal);
            })
            .catch(error => console.error('Error al obtener tipos de exámenes:', error));
    }

    // Llamar a la función para cargar tipos de exámenes al cargar la página
    cargarTiposExamenes();

    // Función para abrir la ventana modal y cargar contenido
    function abrirModal() {
        const tipoExamenId = selectExamenes.value;

        if (tipoExamenId === 'null') {
            // Si no se ha seleccionado un tipo de examen, no hacer nada
            return;
        }

        // Obtener el nombre del tipo de examen seleccionado
        const tipoExamenSeleccionado = selectExamenes.options[selectExamenes.selectedIndex].textContent;

        // Actualizar el contenido del elemento h2
        titleExamenes.textContent = `Items para ${tipoExamenSeleccionado}`;

        // Luego, aquí deberías cargar dinámicamente el contenido de la ventana modal
        // Puedes utilizar fetch para obtener la lista de elementos desde la API
        cargarContenidoModal(tipoExamenId);

        // Mostrar el modal
        modal.style.display = 'block';
    }

    // Función para cargar los elementos asociados al tipo de examen seleccionado
    function cargarContenidoModal(tipoExamenId) {
        const apiUrl = `http://localhost:8080/api/v7/tipos-items?tipoExamenId=${tipoExamenId}`;
        const modalContent = document.getElementById('modalContent');

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                // Limpiar el contenido existente
                modalContent.innerHTML = '';

                // Filtrar solo los elementos cuyo tipoExamen.id coincide con el tipoExamenId seleccionado
                const filteredData = data.filter(item => item.tipoExamen.id === parseInt(tipoExamenId));

                // Iterar a través de los datos filtrados y agregar checkboxes al contenido modal
                filteredData.forEach(item => {
                    // Crear el checkbox
                    const checkbox = document.createElement('input');
                    checkbox.type = 'checkbox';
                    checkbox.value = item.id;
                    checkbox.classList.add('item-checkbox');

                    // Crear el título del item
                    const label = document.createElement('label');
                    label.textContent = item.descripcion;
                    label.classList.add('item-label');

                    // Agregar el checkbox y su label al contenido modal
                    modalContent.appendChild(checkbox);
                    modalContent.appendChild(label);
                });
            })
            .catch(error => console.error('Error al obtener tipos de items:', error));
    }


});

function cerrarModal() {
    const modal = document.getElementById('myModal')
    modal.style.display = 'none';
}