package pos_pack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MY_PROYS extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboProys;
    private JTextPane textPane;

    public MY_PROYS() {
        setTitle("Mis Proyectos");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 548, 509);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 165, 0)); // Fondo gold
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Proyectos de " + LOGIN.usuario);
        lblTitulo.setBounds(10, 10, 514, 30);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);

        comboProys = new JComboBox<>();
        comboProys.setBounds(10, 50, 514, 30);
        comboProys.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contentPane.add(comboProys);

        textPane = new JTextPane();
        textPane.setBounds(10, 100, 514, 359);
        textPane.setContentType("text/html"); // Permite formato HTML
        textPane.setEditable(false);
        textPane.setBackground(Color.WHITE);
        textPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contentPane.add(textPane);

        cargarProyectos();

        comboProys.addActionListener(e -> {
            String tituloSeleccionado = (String) comboProys.getSelectedItem();
            if (tituloSeleccionado != null) {
                mostrarInformacionProyecto(tituloSeleccionado);
            }
        });
    }

    private void cargarProyectos() {
        comboProys.removeAllItems();
        String sql = "SELECT titulo FROM pos_tarea WHERE usuario_creador = ? ORDER BY fecha_creacion DESC";

        try (Connection conn = Conexion_BD.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, LOGIN.usuario);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    comboProys.addItem(rs.getString("titulo"));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar proyectos: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarInformacionProyecto(String titulo) {
        String sql = "SELECT * FROM pos_tarea WHERE titulo = ? AND usuario_creador = ?";

        try (Connection conn = Conexion_BD.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, titulo);
            pst.setString(2, LOGIN.usuario);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String info = "<html>" +
                            "<b>ID:</b> " + rs.getInt("id") + "<br>" +
                            "<b>Título:</b> " + rs.getString("titulo") + "<br>" +
                            "<b>Descripción:</b> " + rs.getString("descripcion") + "<br>" +
                            "<b>Estado:</b> " + rs.getString("estado") + "<br>" +
                            "<b>Usuario Creador:</b> " + rs.getString("usuario_creador") + "<br>" +
                            "<b>Fecha Creación:</b> " + rs.getTimestamp("fecha_creacion") + "<br>" +
                            "<b>Última Actualización:</b> " + rs.getTimestamp("actualizado") +
                            "</html>";
                    textPane.setText(info);
                } else {
                    textPane.setText("");
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al mostrar proyecto: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MY_PROYS frame = new MY_PROYS();
            frame.setVisible(true);
        });
    }
}


