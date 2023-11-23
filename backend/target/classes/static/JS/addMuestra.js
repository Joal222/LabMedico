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