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


    let data; // Variable para almacenar los datos de la API
    let itemsSeleccionados = [];
    window.itemsListToSend = []; // Mover esta declaración al inicio del bloque de código

    if (!examSelect || !modal || !modalItemList || !modalTitle || !guardarBtn || !selectedItemsList || !elementosSeleccionados || !closeBtn) {
        console.error('No se pudo encontrar uno o más elementos en el DOM.');
        return;
    }
    // Realizar la solicitud a la API y llenar el select
    fetch(apiUrl)
        .then(response => response.json())
        .then(apiData => {
            data = apiData; // Almacena los datos de la API en la variable 'data'

            data.forEach(examen => {
                const option = document.createElement('option');
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

            // Agregar el ID del ítem como un atributo de datos
            checkbox.setAttribute('data-id', item.id);
            listItem.insertBefore(checkbox, listItem.firstChild);

            modalItemList.appendChild(listItem);
        });

        modalTitle.textContent = `Items Asociados a ${selectedExam.descripcion}`; // Verifica que 'descripcion' sea el campo correcto a mostrar
        modal.style.display = 'block';
    }


// ...

    guardarBtn.addEventListener('click', function () {
        event.preventDefault();
        const checkboxes = document.querySelectorAll('#modalItemList input[type="checkbox"]:checked');

        const selectedItemsIDs = []; // Crear un arreglo para almacenar los IDs de los elementos seleccionados

        checkboxes.forEach(checkbox => {
            const selectedItem = checkbox.getAttribute('data-id');
            selectedItemsIDs.push(selectedItem);
        });

        // Crear objetos con el formato necesario para la API usando los IDs de los elementos seleccionados
        itemsListToSend = selectedItemsIDs.map((itemID, index) => {
            return {
                id: 0, // Ajustar el ID si es necesario
                idTipoItems: itemID, // Utilizar el ID real del elemento seleccionado
                idSolicitudMuestraMedica: 0 // Ajustar según tu lógica de solicitud médica
            };
        });


        // Almacenar los elementos seleccionados en la lista
        checkboxes.forEach(checkbox => {
            const selectedItem = checkbox.nextSibling.textContent;
            itemsSeleccionados.push(selectedItem);
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
        const selectedExam = data[selectedExamIndex - 1];

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
