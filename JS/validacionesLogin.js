document.addEventListener("DOMContentLoaded", async function () {
    let email = document.getElementById('email');
    let password = document.getElementById('password');
    let loginButton = document.getElementById('login');
    let logoutButton = document.getElementById('logout');
    let messageDiv = document.getElementById('message');

    // Función para crear una cookie
    function crearCookie(nombre, valor, dias) {
        let fecha = new Date();
        fecha.setTime(fecha.getTime() + (dias * 24 * 60 * 60 * 1000));
        let expira = "expires=" + fecha.toUTCString();
        document.cookie = nombre + "=" + valor + ";" + expira + ";path=/";
    }

    // Función para obtener el valor de una cookie
    function obtenerCookie(nombre) {
        let nombreC = nombre + "=";
        let cookies = document.cookie.split(';');
        for (let i = 0; i < cookies.length; i++) {
            let cookie = cookies[i];
            while (cookie.charAt(0) === ' ') {
                cookie = cookie.substring(1);
            }
            if (cookie.indexOf(nombreC) === 0) {
                return cookie.substring(nombreC.length, cookie.length);
            }
        }
        return "";
    }

    // Función para cerrar sesión (eliminar la cookie)
    function cerrarSesion() {
        document.cookie = "usuarioLogueado=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    }

    // Comprobar si la sesión está iniciada al cargar la página
    if (obtenerCookie("usuarioLogueado") === "true") {
        // La sesión está iniciada
        loginButton.style.display = "none";
        logoutButton.style.display = "block";
    }

    // Al hacer clic en el botón de inicio de sesión
    loginButton.addEventListener('click', function(event) {
        event.preventDefault();
        // Tu lógica de validación de inicio de sesión aquí
        // ...

        // Si la autenticación es exitosa, inicia sesión y crea una cookie
        crearCookie("usuarioLogueado", "true", 1); // La cookie caduca en 1 día
        loginButton.style.display = "none";
        logoutButton.style.display = "block";
        messageDiv.innerText = "Sesión iniciada";
        messageDiv.style.display = "block";
    });

    // Al hacer clic en el botón de cierre de sesión
    logoutButton.addEventListener('click', function() {
        cerrarSesion(); // Elimina la cookie
        loginButton.style.display = "block";
        logoutButton.style.display = "none";
        messageDiv.innerText = "Sesión cerrada";
        messageDiv.style.display = "block";
    });
});





document.addEventListener("DOMContentLoaded", async function () {
    let usuarios = await obtenerUsuarios(); // Obtiene los usuarios aquí
    console.log(usuarios);



    function validarDatosIngresados(email, password, usuarios) {
        // Itera a través de los datos de usuarios y compara con los datos ingresados
        for (const usuario of usuarios) {
            if (usuario.email === email && usuario.password === password) {
                // Coinciden los datos ingresados con un usuario en la API
                return true;
            }
        }
        // No se encontró coincidencia
        return false;
    }

    document.getElementById('login').addEventListener('click', function(event) {
        event.preventDefault(); // Previene el envío del formulario

        let emailValue = email.value;
        let passwordValue = password.value;

        if (validarDatosIngresados(emailValue, passwordValue, usuarios)) {
            // Obtén el ID del usuario, asumiendo que está en el objeto usuario
            const usuario = usuarios.find(usuario => usuario.email === emailValue && usuario.password === passwordValue);
        
            if (usuario) {
                if (usuario.id === 1) {
                    // Redirige a la página 1
                    window.location.href = 'index.html';
                } else {
                    // Redirige a la página 2
                    window.location.href = 'dashbord.html';
                }
            } else {
                alert('Credenciales válidas pero no se encontró el usuario en la API.');
            }
        } else {
            alert('Credenciales incorrectas');
        }
    });
});
