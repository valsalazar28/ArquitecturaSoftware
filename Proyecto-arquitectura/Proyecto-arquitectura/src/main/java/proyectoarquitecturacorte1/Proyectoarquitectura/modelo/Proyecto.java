package proyectoarquitecturacorte1.Proyectoarquitectura.modelo;

import java.util.Date;

public class Proyecto {
    public int id;
    public String nombre;
    public String descripcion;
    public Date fechainicio;
    public int gerente;

    public Proyecto(int id, String nombre, String descripcion, Date fecha, int gerente) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechainicio = fecha;
        this.gerente = gerente;
    }
}
