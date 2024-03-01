package proyectoarquitecturacorte1.Proyectoarquitectura.servicios;



import org.springframework.stereotype.Service;
import proyectoarquitecturacorte1.Proyectoarquitectura.modelo.Usuario;
import proyectoarquitecturacorte1.Proyectoarquitectura.repositorio.UsuarioRepositorio;

import java.util.List;

@Service
public class UsuarioServicio {
    private final UsuarioRepositorio repositorio;

    public UsuarioServicio(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Usuario> obtenerUsuarios(){
        return repositorio.obtenerUsuarios();
    }


    public int crearUsuarios(Usuario newUser) {
        return repositorio.crearUsuarios(newUser);
    }

    public int eliminarUsuario(int id) {
        return repositorio.eliminarUsuario(id);
    }

    public int actualizarUsuario(int id, Usuario updateUser) {
        return repositorio.actualizarUsuario(id, updateUser);
    }
}
