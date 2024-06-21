window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_turno');

    fetchPacientes();
    fetchOdontologos();

    formulario.addEventListener('submit', function (event) {
        const formData = {
            fecha: document.querySelector('#fecha').value,
            pacienteId: document.querySelector('#pacienteId').value,
            odontologoId: document.querySelector('#odontologoId').value,

        };
        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> turno agregada </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();

            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                //se dejan todos los campos vacíos por si se quiere ingresar otra turno
                resetUploadForm();
            })
    });

    function resetUploadForm() {
        document.querySelector('#fecha').value = "";
        document.querySelector('#pacienteId').value = "";
        document.querySelector('#odontologoId').value = "";
    }

    function fetchPacientes() {
        fetch('/pacientes')
            .then(response => response.json())
            .then(data => {
                let pacientes = data;
                console.log(pacientes);

                // insertar primero una opción deshabilitada
                const option = document.createElement('option');
                option.value = "";
                option.text = "Seleccione un paciente";
                option.disabled = true;
                option.selected = true;
                document.querySelector('#pacienteId').appendChild(option);

                pacientes.forEach(paciente => {
                    const option = document.createElement('option');
                    option.value = paciente.id;
                    option.text = `${paciente.nombre} ${paciente.apellido}`;
                    document.querySelector('#pacienteId').appendChild(option);
                })
            })
            .catch(error => console.log(error));
    }

    function fetchOdontologos() {
        fetch('/odontologos')
            .then(response => response.json())
            .then(data => {
                let odontologos = data;
                console.log(odontologos);

                // insertar primero una opción deshabilitada
                const option = document.createElement('option');
                option.value = "";
                option.text = "Seleccione un odontologo";
                option.disabled = true;
                option.selected = true;
                document.querySelector('#odontologoId').appendChild(option);

                odontologos.forEach(odontologo => {
                    const option = document.createElement('option');
                    option.value = odontologo.id;
                    option.text = `${odontologo.nombre} ${odontologo.apellido}`;
                    document.querySelector('#odontologoId').appendChild(option);
                })
            })
            .catch(error => console.log(error));
    }

    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/") {
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/get_turnos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});