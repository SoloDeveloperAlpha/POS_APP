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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JOptionPane;

public class LOGIN extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    public static String usuario;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LOGIN frame = new LOGIN();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LOGIN() {
        setResizable(false);
        setBounds(100, 100, 450, 490);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 165, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("LOGIN");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblTitulo.setBounds(10, 10, 416, 32);
        contentPane.add(lblTitulo);

        JLabel lblNick = new JLabel("NickName:");
        lblNick.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNick.setBounds(53, 285, 81, 23);
        contentPane.add(lblNick);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblPass.setBounds(53, 333, 81, 23);
        contentPane.add(lblPass);

        textField = new JTextField();
        textField.setBounds(134, 285, 234, 26);
        textField.setColumns(10);
        contentPane.add(textField);

        passwordField = new JPasswordField();
        passwordField.setBounds(134, 333, 234, 26);
        passwordField.setDocument(new JTextFieldLimit(10));
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("LOGEAR");
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnLogin.setBounds(53, 391, 315, 38);
        btnLogin.addActionListener(e -> loginAction());
        contentPane.add(btnLogin);

        JPanel panelCabecera = new JPanel();
        panelCabecera.setBackground(Color.BLACK);
        panelCabecera.setBounds(0, 0, 436, 50);
        panelCabecera.setLayout(null);
        contentPane.add(panelCabecera);

        JLabel lblIcono = new JLabel();
        lblIcono.setBounds(124, 67, 189, 191);
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcono.setIcon(new ImageIcon("C:\\Users\\Walter\\Desktop\\png-transparent-login-computer-icons-password-login-black-symbol-subscription-business-model-thumbnail (1).png"));
        contentPane.add(lblIcono);
    }

    private void loginAction() {
        String user = textField.getText();
        String pass = new String(passwordField.getPassword());

        if (Conexion_BD.validarUsuario(user, pass)) {
            usuario = user;
            JOptionPane.showMessageDialog(null, "Bienvenido " + user);
            dispose();
            MENU_PROY m_proy = new MENU_PROY();
            m_proy.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public class JTextFieldLimit extends PlainDocument {
        private int limit;

        public JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            } else {
                JOptionPane.showMessageDialog(null, "La contraseña debe de ser de máximo 10 caracteres", "Recuerda", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

