package proyectoarquitecturacorte1.Proyectoarquitectura.servicios;


import org.springframework.stereotype.Service;
import proyectoarquitecturacorte1.Proyectoarquitectura.modelo.Proyecto;
import proyectoarquitecturacorte1.Proyectoarquitectura.repositorio.ProyectoRepositorio;

import java.util.List;

@Service
public class ProyectoServicio {

    private final ProyectoRepositorio repositorio;

    public ProyectoServicio(ProyectoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Proyecto> obtenerProyectos(int id) {
        return repositorio.obtenerProyectos(id);}

    public String crearProyecto(Proyecto proyectoNuevo, int id) {
        return repositorio.crearProyecto(proyectoNuevo, id);
    }

    public String actualizarProyecto(int id_usuario, int id_proyecto, Proyecto actualizarProyecto) {
        return repositorio.actualizarProyecto(id_usuario, id_proyecto, actualizarProyecto);
    }
    public String eliminarProyecto(int id_usuario, int id_proyecto) {
        return repositorio.eliminarProyecto(id_usuario, id_proyecto);
    }


}
