package backendc3.ClinicaOdontologica.service;

import backendc3.ClinicaOdontologica.entity.*;
import backendc3.ClinicaOdontologica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {

        // crear usuario normal
        String passwordSinCifrar = "estefa";
        String passwordCifrado = passwordEncoder.encode(passwordSinCifrar);
        Usuario usuario = new Usuario("Estefanía", "estefa", "estefa@gmail.com", passwordCifrado, RolUsuario.ROLE_USER);
        usuarioService.guardar(usuario);

        // crear usuario admin
        passwordSinCifrar = "admin";
        passwordCifrado = passwordEncoder.encode(passwordSinCifrar);
        usuario = new Usuario("Admin", "admin", "admin@mail.com", passwordCifrado, RolUsuario.ROLE_ADMIN);
        usuarioService.guardar(usuario);

        // crear dos pacientes
        Domicilio domicilio = new Domicilio("Calle Falsa", 123, "Springfield", "USA");
        Paciente paciente = new Paciente("Homero", "Simpson", 123456, LocalDate.now(), "homero@mail.com", domicilio);
        pacienteService.guardar(paciente);

        domicilio = new Domicilio("Avenida Siempreviva", 742, "Springfield", "USA");
        paciente = new Paciente("Marge", "Simpson", 654321, LocalDate.now(), "marge@mail.com", domicilio);
        pacienteService.guardar(paciente);

        // crear dos odontólogos
        Odontologo odontologo = new Odontologo("Julio", "Costa", "123456");
        odontologoService.guardar(odontologo);

        odontologo = new Odontologo("Roberto", "Gómez", "654321");
        odontologoService.guardar(odontologo);
    }
}
