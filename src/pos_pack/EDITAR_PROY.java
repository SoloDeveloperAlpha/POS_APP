package pos_pack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EDITAR_PROY extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldTitulo;
    private JTextArea textAreaDescripcion;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    private int idTarea;
    private String usuario;

    private JRadioButton rdbtnPendiente;
    private JRadioButton rdbtnProgreso;
    private JRadioButton rdbtnCompletada;

    public EDITAR_PROY(int id, String titulo, String descripcion, String estado, String usuario) {
        setTitle("Editar Proyecto");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 605, 475);
        setLocationRelativeTo(null);

        this.idTarea = id;
        this.usuario = usuario;

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 165, 0)); // Fondo naranja suave
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setBounds(33, 10, 529, 401);
        panel.setLayout(null);
        panel.setBackground(new Color(255, 215, 0));
        contentPane.add(panel);

        JLabel lblTitulo = new JLabel("Modificar Proyecto");
        lblTitulo.setBounds(179, 10, 152, 24);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTitulo);

        JLabel lblNombre = new JLabel("Título:");
        lblNombre.setBounds(25, 54, 60, 20);
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel.add(lblNombre);

        textFieldTitulo = new JTextField(titulo);
        textFieldTitulo.setBounds(95, 50, 411, 24);
        panel.add(textFieldTitulo);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(25, 94, 110, 20);
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel.add(lblDescripcion);

        // Radio buttons para estado
        rdbtnPendiente = new JRadioButton("PENDIENTE");
        rdbtnPendiente.setBounds(157, 95, 102, 20);
        buttonGroup.add(rdbtnPendiente);
        panel.add(rdbtnPendiente);

        rdbtnProgreso = new JRadioButton("EN PROGRESO");
        rdbtnProgreso.setBounds(270, 95, 120, 20);
        buttonGroup.add(rdbtnProgreso);
        panel.add(rdbtnProgreso);

        rdbtnCompletada = new JRadioButton("COMPLETADA");
        rdbtnCompletada.setBounds(392, 95, 110, 20);
        buttonGroup.add(rdbtnCompletada);
        panel.add(rdbtnCompletada);

        // Selección del estado actual
        switch (estado.toLowerCase()) {
            case "pendiente" -> rdbtnPendiente.setSelected(true);
            case "en progreso" -> rdbtnProgreso.setSelected(true);
            case "completada" -> rdbtnCompletada.setSelected(true);
        }

        textAreaDescripcion = new JTextArea(descripcion);
        textAreaDescripcion.setBounds(25, 120, 481, 188);
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);
        panel.add(textAreaDescripcion);

        JButton btnActualizar = new JButton("ACTUALIZAR");
        btnActualizar.setBounds(320, 330, 186, 55);
        btnActualizar.setBackground(UIManager.getColor("Button.background"));
        btnActualizar.setForeground(Color.BLACK);
        btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel.add(btnActualizar);

        btnActualizar.addActionListener(e -> actualizarProyecto());
    }

    private void actualizarProyecto() {
        try {
            String nuevoEstado = "";
            if (rdbtnPendiente.isSelected()) nuevoEstado = "Pendiente";
            else if (rdbtnProgreso.isSelected()) nuevoEstado = "En Progreso";
            else if (rdbtnCompletada.isSelected()) nuevoEstado = "Completada";

            if (textFieldTitulo.getText().isEmpty() || textAreaDescripcion.getText().isEmpty() || nuevoEstado.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Conexion_BD.updateProy(idTarea, textFieldTitulo.getText(), textAreaDescripcion.getText(), nuevoEstado, usuario);
            JOptionPane.showMessageDialog(this, "Proyecto actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el proyecto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


