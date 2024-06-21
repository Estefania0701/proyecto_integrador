window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará de la nueva turno
    const formulario = document.querySelector('#add_new_turno');


    fetchPacientes();
    fetchOdontologos();


    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {

        //creamos un JSON que tendrá los datos de la nueva película
        const formData = {
            fecha: document.querySelector('#fecha').value,
            pacienteId: document.querySelector('#pacienteId').value,
            odontologoId: document.querySelector('#odontologoId').value,

        };
        //invocamos utilizando la función fetch la API turnos con el método POST que guardará
        //la película que enviaremos en formato JSON
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
                //Si no hay ningun error se muestra un mensaje diciendo que la turno
                //se agrego bien
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> turno agregada </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();

            })
            .catch(error => {
                //Si hay algun error se muestra un mensaje diciendo que la turno
                //no se pudo guardar y se intente nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                //se dejan todos los campos vacíos por si se quiere ingresar otra turno
                resetUploadForm();})
    });


    function resetUploadForm(){
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

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/get_turnos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});