document.addEventListener('DOMContentLoaded', function () {
    const examSelect = document.getElementById('listExamenes');
    const apiUrl = 'http://localhost:8080/api/v1/tipos-examenes';
    const modal = document.getElementById('myModal');
    const modalItemList = document.getElementById('modalItemList');
    const modalTitle = document.getElementById('modalTitle');
    const guardarBtn = document.getElementById('guardarBtn');
    const selectedItemsList = document.getElementById('selectedItemsList');
    const elementosSeleccionados = document.getElementById('elementosSeleccionados');
    const closeBtn = document.getElementsByClassName('close')[0];

    if (!examSelect || !modal || !modalItemList || !modalTitle || !guardarBtn || !selectedItemsList || !elementosSeleccionados || !closeBtn) {
        console.error('No se pudo encontrar uno o más elementos en el DOM.');
        return;
    }

    let data; // Variable para almacenar los datos de la API

    // Realizar la solicitud a la API y llenar el select
    fetch(apiUrl)
        .then(response => response.json())
        .then(apiData => {
            data = apiData; // Almacena los datos de la API en la variable 'data'

            data.forEach(examen => {
                const option = document.createElement('option');
                option.value = examen.id;
                option.text = examen.descripcion;
                examSelect.add(option);
            });
        })
        .catch(error => console.error('Error al obtener datos de la API:', error));


    // Función para llenar el modal con los items asociados al examen seleccionado
    function fillModal(selectedExam) {
        modalItemList.innerHTML = '';

        selectedExam.itemsList.forEach(item => {
            const listItem = document.createElement('li');
            listItem.textContent = item.descripcion;
            listItem.style.color = 'black';

            // Agregar checkbox junto a cada item
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            listItem.insertBefore(checkbox, listItem.firstChild);

            modalItemList.appendChild(listItem);
        });

        modalTitle.textContent = `Items Asociados a ${selectedExam.descripcion}`;
        modal.style.display = 'block';
    }

    // Evento al hacer clic en el botón de guardar
    guardarBtn.addEventListener('click', function () {
        // Obtener los checkboxes seleccionados
        const checkboxes = document.querySelectorAll('#modalItemList input[type="checkbox"]:checked');

        // Hacer algo con los checkboxes seleccionados (por ejemplo, enviar al servidor)
        checkboxes.forEach(checkbox => {
            const selectedItem = document.createElement('li');
            selectedItem.textContent = checkbox.nextSibling.textContent;
            selectedItemsList.appendChild(selectedItem);
        });

        // Ajustar dinámicamente el tamaño del div elementosSeleccionados
        elementosSeleccionados.style.height = `${checkboxes.length * 20}px`;

        // Mostrar el div con id "elementosSeleccionados" después de guardar
        elementosSeleccionados.style.display = 'block';

        // Cerrar el modal después de guardar
        modal.style.display = 'none';
    });

    // Evento al hacer clic en el botón de cerrar
    closeBtn.addEventListener('click', function () {
        modal.style.display = 'none';
    });

    // Evento al seleccionar un examen
    examSelect.addEventListener('change', function () {
        const selectedExamIndex = examSelect.selectedIndex;
        const selectedExam = data[selectedExamIndex];

        if (!selectedExam || !selectedExam.itemsList) {
            console.error('No se pudo encontrar o cargar la información del examen seleccionado.');
            return;
        }

        selectedItemsList.innerHTML = '';

        // Llenar el modal con los items asociados al examen seleccionado
        fillModal(selectedExam);
    });

    // Evento al hacer clic fuera del modal
    window.addEventListener('click', function (event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });
});
