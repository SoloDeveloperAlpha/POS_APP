package pos_pack;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class HistorialProy extends JFrame {
    private JTable table;

    public HistorialProy(int idProyecto) {
        setTitle("Historial de Proyecto");
        setSize(843, 439);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Fecha Modificación");
        model.addColumn("Usuario");
        model.addColumn("Título");
        model.addColumn("Descripción");
        model.addColumn("Estado");

        table = new JTable(model);
        getContentPane().add(new JScrollPane(table));

        cargarHistorial(idProyecto, model);
    }

    private void cargarHistorial(int idProyecto, DefaultTableModel model) {
        try (ResultSet rs = Conexion_BD.obtenerHistorial(idProyecto)) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getTimestamp("fecha_modificacion"),
                    rs.getString("usuario_modificador"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("estado")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar historial: " + e.getMessage());
        }
    }
}

