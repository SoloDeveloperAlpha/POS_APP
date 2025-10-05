package pos_pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion_BD {

    private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/pos_bd";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() {
        try {
            Class.forName(CONTROLADOR);
            Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("✅ Conexión a la base de datos OK");
            return conexion;
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error al cargar el controlador");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Error en la conexión");
            e.printStackTrace();
        }
        return null;
    }
    
    //REGISTRO DE USUARIOS
    public static void registrarUsuario(String nombre, String apellido, String nickname, String password, String email) {
        String sql = "INSERT INTO pos_reg (nombre, apellido, nickname, password, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = conectar(); PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, nombre);
            pst.setString(2, apellido);
            pst.setString(3, nickname);
            pst.setString(4, password);
            pst.setString(5, email);
            if (pst.executeUpdate() > 0) {
                System.out.println("✅ Usuario registrado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al registrar usuario");
            e.printStackTrace();
        }
    }
    //VALIDACION DE USUARIOS
    public static boolean validarUsuario(String nickname, String password) {
        String sql = "SELECT * FROM pos_reg WHERE nickname = ? AND password = ?";
        try (Connection conexion = conectar(); PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, nickname);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //MOSTRAR USUARIOS
    public static ResultSet obtenerUsuarios() {
        try {
            Connection conexion = conectar();
            String sql = "SELECT id, nickname FROM pos_reg";
            PreparedStatement pst = conexion.prepareStatement(sql);
            return pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //REGISTRAR PROYECTO
    public static void registrarProy(String titulo, String descripcion, String estado, String usuario) {
        String sql = "INSERT INTO pos_tarea (titulo, descripcion, estado, usuario_creador, fecha_creacion) VALUES (?, ?, ?, ?, NOW())";
        try (Connection conexion = conectar(); PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, titulo);
            pst.setString(2, descripcion);
            pst.setString(3, estado);
            pst.setString(4, usuario);
            if (pst.executeUpdate() > 0) {
                System.out.println("✅ Tarea registrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al registrar la tarea");
            e.printStackTrace();
        }
    }
    //ACTUALIZAR PROYECTO
    public static void updateProy(int id, String titulo, String descripcion, String estado, String usuario) {
        String sqlHist = "INSERT INTO pos_tarea_historial (id_proyecto, titulo, descripcion, estado, usuario_modificador) "
                       + "SELECT id, titulo, descripcion, estado, ? FROM pos_tarea WHERE id = ?";
        String sqlUpdate = "UPDATE pos_tarea SET titulo = ?, descripcion = ?, estado = ?, usuario_creador = ?, actualizado = NOW() WHERE id = ?";
        try (Connection conexion = conectar();
             PreparedStatement pstHist = conexion.prepareStatement(sqlHist);
             PreparedStatement pstUpdate = conexion.prepareStatement(sqlUpdate)) {

            pstHist.setString(1, usuario);
            pstHist.setInt(2, id);
            pstHist.executeUpdate();

            pstUpdate.setString(1, titulo);
            pstUpdate.setString(2, descripcion);
            pstUpdate.setString(3, estado);
            pstUpdate.setString(4, usuario);
            pstUpdate.setInt(5, id);

            if (pstUpdate.executeUpdate() > 0) {
                System.out.println("✅ Proyecto actualizado correctamente.");
            } else {
                System.out.println("⚠ No se encontró el proyecto con id: " + id);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar la tarea");
            e.printStackTrace();
        }
    }
    //ELIMINACION DE PROYECTO
    public static void deleteProy(int id) {
        String sql = "DELETE FROM pos_tarea WHERE id = ?";
        try (Connection conexion = conectar(); PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, id);
            if (pst.executeUpdate() > 0) {
                System.out.println("✅ Proyecto eliminado correctamente.");
            } else {
                System.out.println("⚠ No se encontró el proyecto con id: " + id);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar el proyecto");
            e.printStackTrace();
        }
    }
    //MOSTRAR PROYECTOS
    public static ResultSet obtenerProy() {
        try {
            Connection conexion = conectar();
            String sql = "SELECT id, titulo, descripcion, estado, usuario_creador, fecha_creacion, actualizado FROM pos_tarea";
            PreparedStatement pst = conexion.prepareStatement(sql);
            return pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //MOSTRAR HISTORIAL DE PROYECTOS UNICO
    public static ResultSet obtenerHistorial(int idProyecto) {
        try {
            Connection conexion = conectar();
            String sql = "SELECT fecha_modificacion, usuario_modificador, titulo, descripcion, estado "
                       + "FROM pos_tarea_historial WHERE id_proyecto = ? ORDER BY fecha_modificacion ASC";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idProyecto);
            return pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //INICIO DE LA CONEXION
    public static void main(String[] args) {
        conectar();
    }
}
