package proyectoarquitecturacorte1.Proyectoarquitectura.controlador;

import org.springframework.web.bind.annotation.*;
import proyectoarquitecturacorte1.Proyectoarquitectura.modelo.Proyecto;
import proyectoarquitecturacorte1.Proyectoarquitectura.servicios.ProyectoServicio;

import java.util.List;

@RestController
public class ProyectoController {
    private final ProyectoServicio proyectoServicio;

    public ProyectoController(ProyectoServicio proyectoServicio) {
        this.proyectoServicio = proyectoServicio;
    }

    @GetMapping("/usuario/{id}/proyecto")
    public List<Proyecto> obtenerProyectos(@PathVariable("id") int id){
        return proyectoServicio.obtenerProyectos(id);
    }

    @PostMapping ("/usuario/{id}/proyecto")
    public String crearProyecto(@RequestBody Proyecto proyectoNuevo, @PathVariable("id") int id){
        return proyectoServicio.crearProyecto(proyectoNuevo, id);
    }

    @PutMapping("/usuario/{id_usuario}/proyecto/{id_proyecto}")
    public String actualizarProyecto(@PathVariable("id_usuario") int id_usuario, @PathVariable("id_proyecto") int id_proyecto,@RequestBody Proyecto actualizarProyecto){
        return proyectoServicio.actualizarProyecto(id_usuario, id_proyecto, actualizarProyecto);
    }

    @DeleteMapping("/usuario/{id_usuario}/proyecto/{id_proyecto}")
    public String eliminarProyecto(@PathVariable("id_usuario") int id_usuario, @PathVariable("id_proyecto") int id_proyecto){
        return proyectoServicio.eliminarProyecto(id_usuario, id_proyecto);
    }


}
