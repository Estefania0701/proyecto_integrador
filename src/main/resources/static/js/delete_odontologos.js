function deleteBy(id) {

    const url = '/odontologos/' + id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url, settings)
        .then(response => response.json())

    //borrar la fila del odontologo eliminado
    let row_id = id.toString();
    document.getElementById(row_id).remove();

}