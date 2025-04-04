package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class VentanaUsuarios extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnVolver; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuarios frame = new VentanaUsuarios();
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
	public VentanaUsuarios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setBounds(217, 10, 1, 1);
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(0, 64, 128));
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.BLACK, 2, true));
		panel.setBounds(23, 26, 230, 211);
		contentPane_1.add(panel);
		
		JLabel lblIniciarSesion = new JLabel("Iniciar Sesión");
		lblIniciarSesion.setForeground(new Color(0, 128, 255));
		lblIniciarSesion.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblIniciarSesion.setBounds(47, 2, 185, 99);
		panel.add(lblIniciarSesion);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setForeground(new Color(0, 128, 255));
		lblCorreo.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		lblCorreo.setBounds(12, 88, 65, 25);
		panel.add(lblCorreo);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setForeground(new Color(0, 128, 255));
		lblContrasea.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		lblContrasea.setBounds(12, 127, 83, 25);
		panel.add(lblContrasea);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		textField.setColumns(10);
		textField.setBounds(75, 92, 147, 19);
		panel.add(textField);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setForeground(new Color(0, 128, 255));
		btnIniciarSesion.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		btnIniciarSesion.setBackground(Color.WHITE);
		btnIniciarSesion.setBounds(41, 162, 149, 29);
		panel.add(btnIniciarSesion);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		passwordField.setBounds(75, 130, 147, 19);
		panel.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("DAM FIT");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblNewLabel_1.setBounds(75, 10, 126, 34);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(VentanaUsuarios.class.getResource("/Imagenes/fitness-7071849_1920 (1)2.png")));
		lblNewLabel_2.setBounds(185, 2, 35, 58);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setIcon(new ImageIcon(VentanaUsuarios.class.getResource("/Imagenes/fitness-7071849_1920 (1).png")));
		lblNewLabel_2_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_1.setBounds(12, 1, 35, 58);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblIniciaSesinComo = new JLabel("<html><u>Iniciar sesión como monitor</u></html>");
		lblIniciaSesinComo.setForeground(Color.BLUE);
		lblIniciaSesinComo.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		lblIniciaSesinComo.setBounds(44, 186, 178, 25);
		panel.add(lblIniciaSesinComo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(Color.BLACK, 2, true));
		panel_1.setBackground(new Color(0, 128, 255));
		panel_1.setBounds(10, 47, 416, 170);
		contentPane_1.add(panel_1);
		
		JLabel lblanNoTienes = new JLabel("¿Aún no \r\ntienes cuenta?");
		lblanNoTienes.setForeground(Color.WHITE);
		lblanNoTienes.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblanNoTienes.setBackground(UIManager.getColor("Button.background"));
		lblanNoTienes.setBounds(244, -21, 182, 93);
		panel_1.add(lblanNoTienes);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setForeground(new Color(0, 128, 255));
		btnRegistrarse.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		btnRegistrarse.setBackground(Color.WHITE);
		btnRegistrarse.setBounds(265, 82, 129, 29);
		panel_1.add(btnRegistrarse);
		
		JLabel lblRegistrateMensaje = new JLabel("Registrate para poder");
		lblRegistrateMensaje.setForeground(Color.WHITE);
		lblRegistrateMensaje.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		lblRegistrateMensaje.setBounds(269, 39, 193, 13);
		panel_1.add(lblRegistrateMensaje);
		
		JLabel lbliniciarsesion = new JLabel("Iniciar Sesion");
		lbliniciarsesion.setForeground(Color.WHITE);
		lbliniciarsesion.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		lbliniciarsesion.setBounds(289, 62, 147, 13);
		panel_1.add(lbliniciarsesion);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(Color.BLACK, 2, true));
		panel_1_1.setBounds(130, 21, 166, 35);
		contentPane.add(panel_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("DAM FIT");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_1.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(47, 5, 126, 24);
		panel_1_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("New label");
		lblNewLabel_2_1_1.setIcon(new ImageIcon(VentanaUsuarios.class.getResource("/Imagenes/fitness-7071849_1920 (1).png")));
		lblNewLabel_2_1_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_1_1.setBounds(10, -13, 35, 57);
		panel_1_1.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("New label");
		lblNewLabel_2_2.setIcon(new ImageIcon(VentanaUsuarios.class.getResource("/Imagenes/fitness-7071849_1920 (1)2.png")));
		lblNewLabel_2_2.setBounds(128, -11, 35, 58);
		panel_1_1.add(lblNewLabel_2_2);
		
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
		        VentanapPrincipal vp = new VentanapPrincipal();
		        vp.setVisible(true);
		        dispose(); 
		
	}
}
}
