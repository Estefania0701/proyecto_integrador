window.addEventListener('load', function () {
    (function () {

        const url = '/turnos';
        const settings = {
            method: 'GET'
        }

        const urlPaciente = '/pacientes';
        const urlOdontologo = '/odontologos';

        fetch(url, settings)
            .then(response => response.json())
            .then(async data => {
                //recorremos la colección de turnos del JSON
                for (turno of data) {

                    const [responsePaciente, responseOdontologo] = await Promise.all([
                        fetch(urlPaciente + '/' + turno.pacienteId),
                        fetch(urlOdontologo + '/' + turno.odontologoId)
                    ]);

                    if (responsePaciente.ok && responseOdontologo.ok) {
                        const paciente = await responsePaciente.json();
                        const odontologo = await responseOdontologo.json();

                        var table = document.getElementById("turnoTable");
                        var turnoRow = table.insertRow();
                        let tr_id = turno.id;
                        turnoRow.id = tr_id;

                        let deleteButton = '<button' +
                            ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                            ' type="button" onclick="deleteBy(' + turno.id + ')" class="btn btn-danger btn_delete">' +
                            '&times' +
                            '</button>';

                        let updateButton = '<button' +
                            ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                            ' type="button" onclick="findBy(' + turno.id + ')" class="btn btn-info btn_id">' +
                            turno.id +
                            '</button>';

                        turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                            '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                            '<td class=\"td_paciente\">' + paciente.nombre + " " + paciente.apellido + '</td>' +
                            '<td class=\"td_odontologo\">' + odontologo.nombre + " " + odontologo.apellido + '</td>' +
                            '<td>' + deleteButton + '</td>';
                    }
                }
            })
    })

    (function () {
        let pathname = window.location.pathname;
        if (pathname == "/get_turnos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })
})