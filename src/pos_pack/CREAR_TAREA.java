package pos_pack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class CREAR_TAREA extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldTitulo;
    private JTextArea textAreaDescripcion;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public CREAR_TAREA() {
        setTitle("Crear Proyecto");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 605, 475);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 165, 0)); // Fondo naranja
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setBounds(33, 22, 518, 387);
        panel.setLayout(null);
        panel.setBackground(new Color(255, 215, 0)); // Fondo dorado claro
        contentPane.add(panel);

        JLabel lblTitulo = new JLabel("Nuevo Proyecto");
        lblTitulo.setBounds(151, 10, 186, 25);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTitulo);

        JLabel lblNombre = new JLabel("Título:");
        lblNombre.setBounds(27, 50, 60, 20);
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel.add(lblNombre);

        textFieldTitulo = new JTextField();
        textFieldTitulo.setBounds(97, 48, 411, 24);
        textFieldTitulo.setColumns(10);
        panel.add(textFieldTitulo);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(27, 85, 100, 20);
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel.add(lblDescripcion);

        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setBounds(55, 120, 453, 180);
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);
        panel.add(textAreaDescripcion);

        // Radio Buttons para estado
        JRadioButton rdbtnPendiente = new JRadioButton("PENDIENTE");
        rdbtnPendiente.setBounds(151, 310, 100, 20);
        buttonGroup.add(rdbtnPendiente);
        panel.add(rdbtnPendiente);

        JRadioButton rdbtnProgreso = new JRadioButton("EN PROGRESO");
        rdbtnProgreso.setBounds(254, 310, 120, 20);
        buttonGroup.add(rdbtnProgreso);
        panel.add(rdbtnProgreso);

        JRadioButton rdbtnCompletada = new JRadioButton("COMPLETADA");
        rdbtnCompletada.setBounds(380, 310, 120, 20);
        buttonGroup.add(rdbtnCompletada);
        panel.add(rdbtnCompletada);

        JButton btnGuardar = new JButton("GUARDAR");
        btnGuardar.setBounds(322, 340, 186, 40);
        btnGuardar.setBackground(UIManager.getColor("Button.background"));
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel.add(btnGuardar);
        
       
        btnGuardar.addActionListener(e -> guardarProyecto(rdbtnPendiente, rdbtnProgreso, rdbtnCompletada));
        
    }

    private void guardarProyecto(JRadioButton pendiente, JRadioButton progreso, JRadioButton completada) {
        try {
            String estado = "";
            if (pendiente.isSelected()) {
                estado = "Pendiente";
            } else if (progreso.isSelected()) {
                estado = "En Progreso";
            } else if (completada.isSelected()) {
                estado = "Completada";
            }

            if (textFieldTitulo.getText().isEmpty() || textAreaDescripcion.getText().isEmpty() || estado.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos y el estado deben ser completados", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Conexion_BD.registrarProy(textFieldTitulo.getText(), textAreaDescripcion.getText(), estado, LOGIN.usuario);
            JOptionPane.showMessageDialog(this, "Proyecto Guardado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (Exception error) {
            error.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocurrió un error al crear el proyecto: " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CREAR_TAREA frame = new CREAR_TAREA();
            frame.setVisible(true);
        });
    }
}

