console.log('Videoclub Application Loaded');

// Función para confirmar eliminación
function confirmarEliminacion(id) {
    return confirm('¿Estás seguro de que deseas eliminar esta cinta?');
}

// Función para validar formulario
function validarFormulario(form) {
    const campos = form.querySelectorAll('input[required]');
    
    for (let campo of campos) {
        if (campo.value.trim() === '') {
            alert('Por favor completa todos los campos');
            campo.focus();
            return false;
        }
    }
    
    return true;
}

// Event listeners al cargar el DOM
document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM cargado');
    
    // Agregar validación a formularios
    const formularios = document.querySelectorAll('.formulario');
    formularios.forEach(form => {
        form.addEventListener('submit', function(e) {
            if (!validarFormulario(this)) {
                e.preventDefault();
            }
        });
    });
});
