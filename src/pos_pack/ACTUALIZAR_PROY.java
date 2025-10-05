package pos_pack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ACTUALIZAR_PROY extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ACTUALIZAR_PROY frame = new ACTUALIZAR_PROY();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ACTUALIZAR_PROY() {
		setTitle("Actualizar Proyecto");
		setResizable(false);
		setBounds(100, 100, 998, 509);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 165, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(96, 59, 1, 1);
        contentPane.add(scrollPane);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(23, 81, 937, 344);
        getContentPane().add(scrollPane);

        
        table = new JTable(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 🔒 ninguna celda editable
            }
        });
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // doble click
                    int fila = table.getSelectedRow();
                    if (fila != -1) {
                        int idProyecto = (int) table.getValueAt(fila, 0); // Columna 0 = ID
                        HistorialProy historial = new HistorialProy(idProyecto);
                        historial.setVisible(true);
                    }
                }
            }
        });

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0));
        panel.setBounds(0, 0, 984, 57);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("REGISTRO DE PROYECTOS");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(370, 13, 218, 28);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton btnRefresh = new JButton("");
        btnRefresh.setBounds(817, 10, 40, 41);
        panel.add(btnRefresh);
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Ventana de loading
                JDialog loading = new JDialog();
                loading.setUndecorated(true);
                loading.setSize(150, 50);
                loading.setLocationRelativeTo(null);
                loading.getContentPane().add(new JLabel("Cargando...", SwingConstants.CENTER));
                loading.setVisible(true);

                // Usamos SwingWorker para no congelar la UI
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        // Acción en segundo plano
                        mostrarProyectos();
                        return null;
                    }

                    @Override
                    protected void done() {
                        // Cerramos el loading al terminar
                        loading.dispose();
                    }
                };

                worker.execute();
        	}
        });
        btnRefresh.setIcon(new ImageIcon("C:\\Users\\Walter\\eclipse-workspace\\POS_APP\\img\\procesamiento-de-datos.png"));
        
        JButton btnEditarProy = new JButton("");
        btnEditarProy.setBounds(867, 10, 40, 41);
        panel.add(btnEditarProy);
        btnEditarProy.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int fila = table.getSelectedRow();
        	    if (fila == -1) {
        	        JOptionPane.showMessageDialog(null, "Selecciona un registro primero.");
        	        return;
        	    }

        	    // Obtenemos datos de la tabla
        	    int id = (int) table.getValueAt(fila, 0);
        	    String titulo = (String) table.getValueAt(fila, 1);
        	    String descripcion = (String) table.getValueAt(fila, 2);
        	    String estado = (String) table.getValueAt(fila, 3);
        	    String usuario = (String) table.getValueAt(fila, 4);

        	    // Abrimos ventana de edición y le pasamos los datos
        	    EDITAR_PROY editarVentana = new EDITAR_PROY(id, titulo, descripcion, estado, usuario);
        	    editarVentana.setVisible(true);
        	}
        });
        btnEditarProy.setIcon(new ImageIcon("C:\\Users\\Walter\\eclipse-workspace\\POS_APP\\img\\nota.png"));
        
                
                JButton btnBorrarProy = new JButton("");
                btnBorrarProy.setBounds(917, 11, 40, 41);
                panel.add(btnBorrarProy);
                btnBorrarProy.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		int fila = table.getSelectedRow();
                		if (fila != -1) {
                		    int id = (int) table.getValueAt(fila, 0);
                		    int confirm = JOptionPane.showConfirmDialog(null, 
                		                     "¿Seguro que deseas eliminar este proyecto ?", 
                		                     "Confirmar", JOptionPane.YES_NO_OPTION);

                		    if (confirm == JOptionPane.YES_OPTION) {
                		        Conexion_BD.deleteProy(id); 
                		        ((DefaultTableModel) table.getModel()).removeRow(fila); // refresca la tabla
                		        JOptionPane.showMessageDialog(null, "Proyecto Eliminado");
                		        
                		    }
                		} else {
                		    JOptionPane.showMessageDialog(null, "Selecciona un registro primero.");
                		}

                	}
                });
                btnBorrarProy.setIcon(new ImageIcon("C:\\Users\\Walter\\eclipse-workspace\\POS_APP\\img\\borrar.png"));
		
		
		mostrarProyectos();

	}
	
	public void mostrarProyectos() {
	    // Modelo no editable
	    DefaultTableModel model = new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // 🔒 bloquea edición en toda la tabla
	        }
	    };

	    model.addColumn("ID");
	    model.addColumn("Título");
	    model.addColumn("Descripción");
	    model.addColumn("Estado");
	    model.addColumn("Usuario");
	    model.addColumn("Fecha Creación");
	    model.addColumn("Modificado");

	    try {
	        ResultSet rs = Conexion_BD.obtenerProy();
	        while (rs.next()) {
	            model.addRow(new Object[]{
	                rs.getInt("id"),
	                rs.getString("titulo"),
	                rs.getString("descripcion"),
	                rs.getString("estado"),
	                rs.getString("usuario_creador"),
	                rs.getTimestamp("fecha_creacion"),
	                rs.getTimestamp("actualizado")
	            });
	        }
	        table.setModel(model); // ✅ asignamos el modelo NO editable
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al mostrar datos: " + e.getMessage());
	    }
	}

}