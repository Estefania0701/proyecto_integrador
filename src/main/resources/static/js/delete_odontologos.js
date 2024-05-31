function deleteBy(id)
{
    //con fetch invocamos a la API de odontologos con el método DELETE
    //pasandole el id en la URL
    const url = '/odontologos/'+ id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url,settings)
        .then(response => response.json())

    //borrar la fila de la odontologo eliminada
    let row_id =  id.toString();
    document.getElementById(row_id).remove();

}