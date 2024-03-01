package proyectoarquitecturacorte1.Proyectoarquitectura.repositorio;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import proyectoarquitecturacorte1.Proyectoarquitectura.modelo.Proyecto;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class ProyectoRepositorio {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insert;

    private final ProyectoMapper mapper = new ProyectoMapper();

    private final JdbcTemplate template;

    public ProyectoRepositorio(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                              DataSource dataSource, JdbcTemplate template) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName("proyecto").usingGeneratedKeyColumns("id");
        this.template = template;
    }

    public List<Proyecto> obtenerProyectos(int id) {
        String sql = "select * from proyecto where gerente_id = "+ id;
        return namedParameterJdbcTemplate.query(sql,mapper);
    }

    public String crearProyecto(Proyecto newProject, int id) {

        if (id != 1){
            return "No tienes permiso";
        }
        insert.execute( new MapSqlParameterSource()
                .addValue("nombre", newProject.nombre)
                .addValue("descripcion", newProject.descripcion)
                .addValue("fechainicio", newProject.fechainicio)
                .addValue("gerente_id", id));
        return "Proyecto creado";
    }

    public String actualizarProyecto(int id_usuario, int id_proyecto, Proyecto updateProject) {
        if(id_usuario != 1){
            return "No tienes permiso para actualizar proyectos";
        }

        String sql = "update proyecto set " +
                "nombre = :nombre," +
                "descripcion = :descripcion," +
                "fechainicio = :fechainicio," +
                "gerente_id = :gerente_id" +
                " where id = " + id_proyecto;

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("nombre", updateProject.nombre)
                .addValue("descripcion", updateProject.descripcion)
                .addValue("fechainicio", updateProject.fechainicio)
                .addValue("gerente_id", id_usuario);

        namedParameterJdbcTemplate.update(sql, parameters);
        return "Proyecto actualizado";

    }

    public String eliminarProyecto(int id_usuario, int id_proyecto) {
        if(id_usuario != 1){
            return "No tienes permiso para eliminar proyectos";
        }
        String sql = "delete from proyecto where id = ?";
        template.update(sql,id_proyecto);
        return "Proyecto eliminado";
    }


    private static class ProyectoMapper implements RowMapper<Proyecto> {

        @Override
        public Proyecto mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            Date fechainicio = rs.getDate("fechainicio");
            int gerente = rs.getInt("gerente_id");

            return new Proyecto(id, nombre, descripcion, fechainicio, gerente);
        }
    }
}
