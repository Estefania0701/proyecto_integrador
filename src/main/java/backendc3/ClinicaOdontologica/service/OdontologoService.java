package backendc3.ClinicaOdontologica.service;

import backendc3.ClinicaOdontologica.entity.Odontologo;
import backendc3.ClinicaOdontologica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository repository;

    public Odontologo buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public Odontologo buscarPorMatricula(String matricula){
        return repository.findByMatricula(matricula).orElse(null);
    }

    public List<Odontologo> buscarTodos(){
        return repository.findAll();
    }

    public Odontologo guardar(Odontologo odontologo){
        return repository.save(odontologo);
    }

    public Odontologo actualizar(Odontologo odontologo){
        Odontologo odontologoExistente = buscarPorId(odontologo.getId());
        if(odontologoExistente == null){
            return null;
        }
        odontologoExistente.setNombre(odontologo.getNombre());
        odontologoExistente.setApellido(odontologo.getApellido());
        odontologoExistente.setMatricula(odontologo.getMatricula());
        return repository.save(odontologo);
    }

    public boolean eliminar(Long id) {
        Odontologo odontologoEncontrado = buscarPorId(id);
        if(odontologoEncontrado == null) {
            return false;
        }
        repository.delete(odontologoEncontrado);
        return true;
    }

}
