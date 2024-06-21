function deleteBy(id) {

    const url = '/pacientes/' + id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url, settings)
        .then(response => response.json())

    //borrar la fila del paciente eliminado
    let row_id = id.toString();
    document.getElementById(row_id).remove();

}