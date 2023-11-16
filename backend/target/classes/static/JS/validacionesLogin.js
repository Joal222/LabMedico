async function obtenerUsuarios() {
    try {
        const response = await fetch('http://localhost:8080/api/v1/usuarios');
        if (!response.ok) {
            throw new Error(`Error al cargar usuarios: ${response.statusText}`);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error al cargar usuarios:', error);
        return [];
    }
}

    function entrar() {
    // Obten los elementos input
    let emailInput = document.getElementById("email");
    let passwordInput = document.getElementById("password");

    // Obten la lista de usuarios
    obtenerUsuarios().then(usuarios => {
        // Obtén los valores de los campos
        const email = emailInput.value;
        const password = passwordInput.value;

        // Encuentra un usuario que coincida
        const usuario = usuarios.find(user => user.email === email && user.password === password);

        if (usuario) {
            if (usuario.idTipoUsuario === 2) {
                // Almacena el usuario en sessionStorage
                sessionStorage.setItem('usuario', JSON.stringify(usuario)   );
                window.location.href = 'dashbord.html';
               
            } else {
                // Otra redirección
                sessionStorage.setItem('usuario', JSON.stringify(usuario)   );
                // Redirige al usuario
                window.location.href = 'index.html';
            }
        } else {
            alert("Datos incorrectos");
        }
    });
}
function redirigirSiLogueado() {
    // Verifica si el usuario está autenticado (puedes utilizar tu propio criterio)
    const usuarioEnSesion = JSON.parse(sessionStorage.getItem('usuario'));

    if (usuarioEnSesion) {

        // El usuario está autenticado, redirige solo si no estamos ya en la página de origen
        const paginaOrigen = usuarioEnSesion.idTipoUsuario === 2 ? 'dashbord.html' : 'index.html';

        if (window.location.pathname.endsWith('/' + paginaOrigen)) {
            // Estamos en la página de origen, no hagas nada
            return;

        } else {
            // No estamos en la página de origen, redirige a la página de origen
            window.location.href = paginaOrigen;

        }
    }
}

// Llama a esta función cuando la página se cargue
redirigirSiLogueado();