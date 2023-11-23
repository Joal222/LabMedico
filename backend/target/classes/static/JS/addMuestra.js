// Obtener referencia al select
const selectTiposMuestra = document.getElementById('tipoMuestra');


// Hacer la solicitud a la API
fetch('http://localhost:8080/api/v1/tipos/muestras/all')
    .then(response => response.json())
    .then(data => {
        data.forEach(tipo => {
            const option = document.createElement('option');
            option.value = tipo.id;
            option.textContent = tipo.descripcion;
            selectTiposMuestra.appendChild(option);
        });
    })
    .catch(error => {
        console.error('Ha ocurrido un error:', error);
    });
const storedSelectedRowId = sessionStorage.getItem('selectedRowId');
console.log(storedSelectedRowId);

function crearMuestra() {
    const form = document.querySelector('form');

    form.addEventListener('submit', event => {
        event.preventDefault(); // Evita que el formulario se envíe de forma predeterminada

        const storedSelectedRowId = sessionStorage.getItem('selectedRowId'); // Asegúrate de tener este valor almacenado

        const tipoMuestra = document.getElementById('tipoMuestra').value;
        const presentacion = document.getElementById('presentacion').value;
        const cantidadUnidades = document.getElementById('cantidadUnidades').value;
        const unidadMedida = document.getElementById('unidadMedida').value;

        const data = {
            idSolicitudMuestraMedica: storedSelectedRowId,
            idPresentacionMuestra: presentacion,
            idTipoMuestra: tipoMuestra,
            idUnidadMedida: unidadMedida,
            cantidad: cantidadUnidades.toString()
        };

        fetch('http://localhost:8080/api/v1/muestra/created', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Hubo un problema al enviar los datos.');
                }
                return response.json(); // Si la respuesta es en JSON, obtén los datos de la respuesta
            })
            .then(data => {
                // Manejar la respuesta de la API
                console.log('Respuesta de la API:', data);
                // Hacer algo con la respuesta, como mostrar un mensaje de éxito o redirigir a otra página
                cerrarModal();
                // Recarga la página
                window.location.reload();
            })
            .catch(error => {
                console.error('Error al enviar los datos:', error);
                // Manejar errores, mostrar mensajes de error, etc.
            });
    });
    function cerrarModal() {
        var modal = document.getElementById("myModal");
        modal.style.display = "none";
    }
}
