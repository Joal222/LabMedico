document.getElementById('formulario').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita que el formulario se envíe de forma predeterminada

    // Captura los valores del formulario
    const formData = new FormData(this);

    // Convierte los datos del formulario a un objeto
    const formObject = {};
    formData.forEach((value, key) => {
        formObject[key] = value;
    });

    const cui = formObject.cui;
    if (cui.length !== 13 || !/^\d{13}$/.test(cui)) {
        mostrarError('El CUI debe tener exactamente 13 números.');
        return; // Detiene el proceso si la validación falla
    }

    const telefono = formObject.telefono;
    if (!/^\d{8}$/.test(telefono)) {
        mostrarError('El número de teléfono debe tener 8 dígitos.');
        return; // Detiene el proceso si la validación falla
    }

    const nit = formObject.nit;
    if (nit.length !== 8) {
        mostrarError('El NIT debe tener 8 caracteres.');
        return; // Detiene el proceso si la validación falla
    }

    const password = formObject.password;
    if (password.length < 8) {
        mostrarError('La contraseña debe tener al menos 8 caracteres.');
        return; // Detiene el proceso si la validación falla
    }

    const confirmarContrasena = formObject.confiPass;
    if (password !== confirmarContrasena) {
        mostrarError('Las contraseñas no coinciden.');
        return; // Detiene el proceso si la validación falla
    }

    const email = formObject.email;
    // Realiza una solicitud a tu API para verificar si el correo ya existe
    fetch('http://localhost:8080/api/v1/usuarios/all')
        .then(response => response.json())
        .then(usuarios => {
            const correoExistente = usuarios.find(usuario => usuario.email === email);
            if (correoExistente) {
                mostrarError('El correo ingresado ya está registrado.');
            } else {
                // El correo no existe en la lista de usuarios, puedes continuar con la creación de usuario
                // Realiza una solicitud POST a tu API para crear el usuario
                fetch('http://localhost:8080/api/v1/usuario/external', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(formObject),
                })
                    .then(response => response.json())
                    .then(data => {
                        console.log('Usuario creado con éxito:', data);
                        mostrarError('Usuario creado con éxito.');
                        window.location.href = 'login.html';
                    })
                    .catch(error => {
                        console.error('Error al crear el usuario:', error);
                    });
            }
        })
        .catch(error => {
            console.error('Error al consultar la lista de usuarios:', error);
        });
});

function mostrarError(mensaje) {
    const errorDiv = document.getElementById('errorMessages');
    errorDiv.innerHTML = `<p class="error-message">${mensaje}</p>`;
}
