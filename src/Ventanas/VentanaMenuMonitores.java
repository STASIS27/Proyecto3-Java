package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenuMonitores extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVolver,btnVerClientes,btnVerClases,btnAñadirClase,btnEliminarClases;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenuMonitores frame = new VentanaMenuMonitores();
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
	public VentanaMenuMonitores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(Color.BLACK, 2, true));
		panel_1.setBounds(134, 9, 166, 35);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("DAM FIT");
		lblNewLabel_1.setBounds(47, 5, 126, 24);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		
		JLabel lblNewLabel_2_1_1 = new JLabel("New label");
		lblNewLabel_2_1_1.setIcon(new ImageIcon(VentanaMenuMonitores.class.getResource("/Imagenes/fitness-7071849_1920 (1).png")));
		lblNewLabel_2_1_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_1_1.setBounds(10, -13, 35, 57);
		panel_1.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("New label");
		lblNewLabel_2_2.setIcon(new ImageIcon(VentanaMenuMonitores.class.getResource("/Imagenes/fitness-7071849_1920 (1)2.png")));
		lblNewLabel_2_2.setBounds(128, -11, 35, 58);
		panel_1.add(lblNewLabel_2_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 47, 126, 96);
		contentPane.add(panel);
		
		btnAñadirClase = new JButton("AÑADIR CLASE");
		btnAñadirClase.setBounds(10, 5, 106, 23);
		btnAñadirClase.addActionListener(this);
		btnAñadirClase.setBackground(new Color(0, 128, 255));
		btnAñadirClase.setFont(new Font("Segoe UI Black", Font.BOLD, 8));
		btnAñadirClase.setForeground(new Color(255, 255, 255));
		
		JLabel lblNewLabel = new JLabel("Aquí podras añadir");
		lblNewLabel.setBounds(15, 34, 115, 14);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		lblNewLabel.setForeground(new Color(0, 128, 255));
		
		JLabel lblNewLabel_2 = new JLabel("clases,sus horarios");
		lblNewLabel_2.setBounds(15, 54, 99, 14);
		lblNewLabel_2.setForeground(new Color(0, 128, 255));
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		
		JLabel lblNewLabel_2_1 = new JLabel(" y su druación");
		lblNewLabel_2_1.setBounds(25, 74, 99, 14);
		lblNewLabel_2_1.setForeground(new Color(0, 128, 255));
		lblNewLabel_2_1.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		panel.setLayout(null);
		panel.add(btnAñadirClase);
		panel.add(lblNewLabel_2);
		panel.add(lblNewLabel);
		panel.add(lblNewLabel_2_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 128, 255));
		panel_2.setBounds(10, 153, 126, 96);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Aquí podras eliminar");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		
		JLabel lblNewLabel_4 = new JLabel("  clases,sus horarios");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		
		JLabel lblNewLabel_5 = new JLabel("   y su duración");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		
		btnEliminarClases = new JButton("ELIMINAR CLASE");
		btnEliminarClases.addActionListener(this);
		btnEliminarClases.setForeground(new Color(0, 128, 255));
		btnEliminarClases.setFont(new Font("Segoe UI Black", Font.BOLD, 8));
		btnEliminarClases.setBackground(Color.WHITE);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEliminarClases, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addComponent(btnEliminarClases, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(0, 64, 128));
		panel_2_1.setBounds(157, 47, 126, 139);
		contentPane.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel lblImagen = new JLabel("New label");
		lblImagen.setBounds(-85, 10, 268, 139);
		lblImagen.setIcon(new ImageIcon(VentanaMenuMonitores.class.getResource("/Imagenes/sports-1975689_1920 (1).png")));
		panel_2_1.add(lblImagen);
		
		JPanel panel_2_3 = new JPanel();
		panel_2_3.setBackground(new Color(0, 128, 255));
		panel_2_3.setBounds(300, 47, 126, 96);
		contentPane.add(panel_2_3);
		
		btnVerClases = new JButton("VER CLASES");
		btnVerClases.addActionListener(this);
		btnVerClases.setBackground(new Color(255, 255, 255));
		btnVerClases.setForeground(new Color(0, 128, 255));
		btnVerClases.setFont(new Font("Segoe UI Black", Font.BOLD, 8));
		
		JLabel lblAquPodrasVer = new JLabel("Aquí podras ver");
		lblAquPodrasVer.setBackground(new Color(0, 128, 255));
		lblAquPodrasVer.setForeground(new Color(255, 255, 255));
		lblAquPodrasVer.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		
		JLabel lblClasessusHorarios = new JLabel(" clases,sus horarios,");
		lblClasessusHorarios.setBackground(new Color(0, 128, 255));
		lblClasessusHorarios.setForeground(new Color(255, 255, 255));
		lblClasessusHorarios.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		
		JLabel lblNewLabel_7_1 = new JLabel("  monitor y su duración");
		lblNewLabel_7_1.setBackground(new Color(0, 128, 255));
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		GroupLayout gl_panel_2_3 = new GroupLayout(panel_2_3);
		gl_panel_2_3.setHorizontalGroup(
			gl_panel_2_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClasessusHorarios, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2_3.createSequentialGroup()
							.addGap(10)
							.addComponent(lblAquPodrasVer, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(lblNewLabel_7_1, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
				.addGroup(gl_panel_2_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVerClases, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		gl_panel_2_3.setVerticalGroup(
			gl_panel_2_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2_3.createSequentialGroup()
					.addGap(5)
					.addComponent(btnVerClases)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAquPodrasVer)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblClasessusHorarios)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_7_1)
					.addGap(4))
		);
		panel_2_3.setLayout(gl_panel_2_3);
		
		JPanel panel_2_4 = new JPanel();
		panel_2_4.setBackground(new Color(255, 255, 255));
		panel_2_4.setBounds(300, 153, 126, 96);
		contentPane.add(panel_2_4);
		
		btnVerClientes = new JButton("VER CLIENTES");
		btnVerClientes.addActionListener(this);
		btnVerClientes.setBounds(10, 10, 106, 21);
		btnVerClientes.setBackground(new Color(0, 128, 255));
		btnVerClientes.setForeground(new Color(255, 255, 255));
		btnVerClientes.setFont(new Font("Segoe UI Black", Font.BOLD, 8));
		
		JLabel lblNewLabel_7_2 = new JLabel(" Aquí podras ver");
		lblNewLabel_7_2.setBounds(19, 36, 115, 14);
		lblNewLabel_7_2.setBackground(new Color(0, 128, 255));
		lblNewLabel_7_2.setForeground(new Color(0, 128, 255));
		lblNewLabel_7_2.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		
		JLabel lblNewLabel_7_3 = new JLabel(" los clientes que");
		lblNewLabel_7_3.setBounds(19, 56, 115, 14);
		lblNewLabel_7_3.setBackground(new Color(0, 128, 255));
		lblNewLabel_7_3.setForeground(new Color(0, 128, 255));
		lblNewLabel_7_3.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		panel_2_4.setLayout(null);
		
		JLabel lblNewLabel_7_4 = new JLabel("  esten apuntados aquí");
		lblNewLabel_7_4.setBounds(0, 76, 134, 14);
		lblNewLabel_7_4.setBackground(new Color(0, 128, 255));
		lblNewLabel_7_4.setForeground(new Color(0, 128, 255));
		lblNewLabel_7_4.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		panel_2_4.add(lblNewLabel_7_4);
		panel_2_4.add(lblNewLabel_7_3);
		panel_2_4.add(lblNewLabel_7_2);
		panel_2_4.add(btnVerClientes);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(this);
		btnVolver.setForeground(new Color(0, 128, 255));
		btnVolver.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBounds(168, 209, 101, 32);
		contentPane.add(btnVolver);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource().equals(btnVolver)) {
		        VentanaMonitores vm = new VentanaMonitores();
		        vm.setVisible(true);
		        dispose();
		 }
		        if(e.getSource().equals(btnVerClientes)) {
			        VentanaVerClientes vvc = new VentanaVerClientes();
			        vvc.setVisible(true);
			        dispose();
			        
		
	}
		        if(e.getSource().equals(btnVerClases)) {
			        VentanaVerClases vvcc = new VentanaVerClases();
			        vvcc.setVisible(true);
			        dispose();
			        
		
	}
		        if(e.getSource().equals(btnAñadirClase)) {
			        VentanaAñadirClase vac = new VentanaAñadirClase();
			        vac.setVisible(true);
			        dispose();
			        
		
	}
		        if(e.getSource().equals(btnEliminarClases)) {
			        VentanaEliminarClase vec = new VentanaEliminarClase();
			        vec.setVisible(true);
			        dispose();
			        
		
	}
}
}
