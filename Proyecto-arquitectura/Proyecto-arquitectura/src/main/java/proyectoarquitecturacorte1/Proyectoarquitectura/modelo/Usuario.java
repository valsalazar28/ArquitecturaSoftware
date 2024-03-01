package proyectoarquitecturacorte1.Proyectoarquitectura.modelo;

public class Usuario {
    public int id;
    public String nombre;
    public String correo;
    private String contrasena;
    public int tipo_usuario;

    public String getContrasena() {
        return contrasena;
    }


    public Usuario(int id, String nombre, String correo, String contrasena, int tipo_usuario) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipo_usuario = tipo_usuario;
    }
}
