package proyectoarquitecturacorte1.Proyectoarquitectura.repositorio;



import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import proyectoarquitecturacorte1.Proyectoarquitectura.modelo.Usuario;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository

public class UsuarioRepositorio {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insert;

    private final UsuarioMapper mapper = new UsuarioMapper();

    private final JdbcTemplate template;

    private final String table = "usuario";



    public UsuarioRepositorio(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                              DataSource dataSource, JdbcTemplate template) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("id");
        this.template = template;
    }

    public List<Usuario> obtenerUsuarios(){
        String sql = "select * from " + table;
        return namedParameterJdbcTemplate.query(sql,mapper);
    }

    public int crearUsuarios(Usuario newUser) {
        return insert.executeAndReturnKey( new MapSqlParameterSource()
                        .addValue("nombre", newUser.nombre)
                        .addValue("correo", newUser.correo)
                        .addValue("contrasena", newUser.getContrasena())
                        .addValue("tipo_usuario_id",newUser.tipo_usuario)
                ).intValue();
    }

    public int eliminarUsuario(int id) {
        String sql = "delete from usuario where id = ?";
        return template.update(sql,id);
    }

    public int actualizarUsuario(int id, Usuario updatedUser) {
        String sql = "update usuario set " +
                "nombre = :nombre," +
                " correo = :correo, " +
                "contrasena = :contrasena, " +
                "tipo_usuario_id = :tipo_usuario_id " +
                "WHERE id = " + id;
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("nombre", updatedUser.nombre)
                .addValue("correo", updatedUser.correo)
                .addValue("contrasena", updatedUser.getContrasena())
                .addValue("tipo_usuario_id", updatedUser.tipo_usuario);

        return namedParameterJdbcTemplate.update(sql, parameters);
    }


    private static class UsuarioMapper implements RowMapper<Usuario>{

        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            String contrasena = rs.getString("contrasena");
            int tipo_usuario = rs.getInt("tipo_usuario_id");

            return new Usuario(id, nombre, correo, contrasena, tipo_usuario);
        }
    }
}
