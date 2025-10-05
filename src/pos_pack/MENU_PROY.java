package pos_pack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MENU_PROY extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MENU_PROY frame = new MENU_PROY();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MENU_PROY() {
        setTitle("Menú de Proyectos");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 764, 590);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 165, 0)); // fondo gold suave
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        panel.setBounds(37, 33, 673, 479);
        panel.setLayout(null);
        contentPane.add(panel);

        JButton btnCerrarSesion = new JButton("CERRAR SESIÓN");
        btnCerrarSesion.setBackground(new Color(128, 0, 0));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCerrarSesion.setBounds(186, 380, 306, 44);
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        panel.add(btnCerrarSesion);

        JButton btnCrearTarea = new JButton("CREAR PROYECTO");
        btnCrearTarea.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnCrearTarea.setBounds(186, 166, 306, 44);
        btnCrearTarea.addActionListener(e -> new CREAR_TAREA().setVisible(true));
        panel.add(btnCrearTarea);

        JButton btnVerUsuarios = new JButton("VER USUARIOS REGISTRADOS");
        btnVerUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnVerUsuarios.setBounds(186, 112, 306, 44);
        btnVerUsuarios.addActionListener(e -> {
            LISTA_USERS listaUsers = new LISTA_USERS();
            listaUsers.setVisible(true);
            listaUsers.setLocation(getX() + getWidth() + 20, getY() + 50);
        });
        panel.add(btnVerUsuarios);

        JButton btnActualizarProyecto = new JButton("ACTUALIZAR PROYECTO");
        btnActualizarProyecto.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnActualizarProyecto.setBounds(186, 220, 306, 44);
        btnActualizarProyecto.addActionListener(e -> new ACTUALIZAR_PROY().setVisible(true));
        panel.add(btnActualizarProyecto);

        JButton btnMisProyectos = new JButton("MIS PROYECTOS");
        btnMisProyectos.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnMisProyectos.setBounds(186, 274, 306, 44);
        btnMisProyectos.addActionListener(e -> new MY_PROYS().setVisible(true));
        panel.add(btnMisProyectos);

        JLabel lblTitulo = new JLabel("MENÚ DE PROYECTOS");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(186, 57, 306, 28);
        panel.add(lblTitulo);

        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(Color.BLACK);
        panelHeader.setBounds(0, 0, 673, 36);
        panelHeader.setLayout(null);
        panel.add(panelHeader);

        JLabel lblUsuario = new JLabel("♦ " + LOGIN.usuario + " ♦");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUsuario.setBounds(10, 0, 653, 36);
        panelHeader.add(lblUsuario);
    }

    private void cerrarSesion() {
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "¿Seguro que deseas cerrar sesión?",
            "Cerrar Sesión",
            JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            LOGIN.usuario = null;
            dispose();
            new LOGIN().setVisible(true);
        }
    }
}
