package pos_pack;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

public class POS_INICIO extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                POS_INICIO frame = new POS_INICIO();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public POS_INICIO() {
        setTitle("INICIO");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 1007, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 165, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBorder(new TitledBorder(""));
        panelTitulo.setBounds(153, 35, 612, 34);
        contentPane.add(panelTitulo);

        JLabel lblTitulo = new JLabel("PLATAFORMA DE ORGANIZACION Y SEGUIMIENTO DE PROYECTOS");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        panelTitulo.add(lblTitulo);

        JButton btnRegistrarUsuario = new JButton("REGISTRAR USUARIO");
        btnRegistrarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnRegistrarUsuario.setBounds(570, 247, 329, 34);
        btnRegistrarUsuario.addActionListener(e -> {
            REG_USER reg = new REG_USER();
            reg.setVisible(true);
        });
        contentPane.add(btnRegistrarUsuario);

        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnLogin.setBounds(570, 291, 329, 32);
        btnLogin.addActionListener(e -> {
            LOGIN log = new LOGIN();
            log.setVisible(true);
        });
        contentPane.add(btnLogin);

        JButton btnSalir = new JButton("SALIR");
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBackground(new Color(0, 0, 0));
        btnSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSalir.setBounds(570, 333, 329, 32);
        btnSalir.addActionListener(e -> System.exit(0));
        contentPane.add(btnSalir);

        JLabel lblImagenFondo = new JLabel();
        lblImagenFondo.setVerticalAlignment(SwingConstants.TOP);
        lblImagenFondo.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagenFondo.setIcon(new ImageIcon("C:\\Users\\Walter\\eclipse-workspace\\POS_APP\\img\\pexels-olly-941555.jpg"));
        lblImagenFondo.setBounds(0, 0, 467, 563);
        contentPane.add(lblImagenFondo);
    }
}

