package backendc3.ClinicaOdontologica.service;

import backendc3.ClinicaOdontologica.entity.RolUsuario;
import backendc3.ClinicaOdontologica.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioService usuarioService;

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
    }
}
