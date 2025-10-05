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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class REG_USER extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre, txtApellido, txtNick, txtPassword, txtEmail;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                REG_USER frame = new REG_USER();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public REG_USER() {
        setResizable(false);
        setBounds(100, 100, 665, 574);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 165, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("REGISTRAR USUARIO");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setBounds(10, 10, 631, 36);
        contentPane.add(lblTitulo);

        JPanel panelCabecera = new JPanel();
        panelCabecera.setBackground(Color.BLACK);
        panelCabecera.setBounds(10, 10, 631, 36);
        contentPane.add(panelCabecera);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNombre.setBounds(10, 102, 84, 23);
        contentPane.add(lblNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblApellido.setBounds(10, 148, 84, 23);
        contentPane.add(lblApellido);

        JLabel lblNick = new JLabel("NickName:");
        lblNick.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNick.setBounds(10, 201, 84, 23);
        contentPane.add(lblNick);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblPass.setBounds(10, 259, 84, 23);
        contentPane.add(lblPass);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblEmail.setBounds(10, 319, 84, 23);
        contentPane.add(lblEmail);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtNombre.setBounds(97, 97, 258, 23);
        contentPane.add(txtNombre);

        txtApellido = new JTextField();
        txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtApellido.setBounds(97, 151, 258, 23);
        contentPane.add(txtApellido);

        txtNick = new JTextField();
        txtNick.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtNick.setBounds(97, 204, 258, 23);
        contentPane.add(txtNick);

        txtPassword = new JTextField();
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtPassword.setBounds(97, 262, 258, 23);
        txtPassword.setDocument(new JTextFieldLimit(10));
        contentPane.add(txtPassword);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtEmail.setBounds(97, 322, 258, 23);
        contentPane.add(txtEmail);

        JButton btnRegistrar = new JButton("REGISTRAR");
        btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnRegistrar.setBounds(10, 404, 345, 41);
        btnRegistrar.addActionListener(e -> registrarUsuario());
        contentPane.add(btnRegistrar);

        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setIcon(new ImageIcon("C:\\Users\\Walter\\eclipse-workspace\\POS_APP\\img\\pexels-rdne-7821686.jpg"));
        lblImagen.setBounds(368, 0, 283, 537);
        contentPane.add(lblImagen);
    }

    private void registrarUsuario() {
        try {
            Conexion_BD.registrarUsuario(
                txtNombre.getText(),
                txtApellido.getText(),
                txtNick.getText(),
                txtPassword.getText(),
                txtEmail.getText()
            );
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
            limpiarCampos();
            dispose();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtNick.setText("");
        txtPassword.setText("");
        txtEmail.setText("");
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
                JOptionPane.showMessageDialog(null, "Máximo de caracteres alcanzado", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

