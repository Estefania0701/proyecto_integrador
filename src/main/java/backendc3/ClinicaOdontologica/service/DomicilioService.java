package backendc3.ClinicaOdontologica.service;

import backendc3.ClinicaOdontologica.entity.Domicilio;
import backendc3.ClinicaOdontologica.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioService {

    @Autowired
    private DomicilioRepository repository;

    public Domicilio buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

}
