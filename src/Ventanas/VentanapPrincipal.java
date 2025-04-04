 package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class VentanapPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCorreo;
	private JPanel panel;
	private JButton btnRegistrarse;
	private JButton btnIniciarSesion;
	private JPasswordField passwordField;
	private JLabel lblIniciaSesinComo;
	private JTabbedPane tabbedPane;
	private final String usuario = "usuario123";
	private final String password = "123usuario";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanapPrincipal frame = new VentanapPrincipal();
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
	public VentanapPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panel = new JPanel();
		panel.setBounds(23, 26, 230, 211);
		panel.setBorder(new LineBorder(Color.BLACK, 2, true));
		contentPane.add(panel);
		
		
		
		JLabel lblIniciarSesion = new JLabel("Iniciar Sesión");
		lblIniciarSesion.setBounds(47, 2, 185, 99);
		lblIniciarSesion.setForeground(new Color(0, 128, 255));
		lblIniciarSesion.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		panel.add(lblIniciarSesion);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(12, 88, 65, 25);
		lblCorreo.setForeground(new Color(0, 128, 255));
		lblCorreo.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		panel.add(lblCorreo);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(12, 127, 83, 25);
		lblContrasea.setForeground(new Color(0, 128, 255));
		lblContrasea.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		panel.add(lblContrasea);
		
		
		textCorreo = new JTextField();
		textCorreo.setBounds(75, 92, 147, 19);
		textCorreo.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		textCorreo.setColumns(10);
		panel.add(textCorreo);
		
		panel.setLayout(null);
		
		btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.addActionListener(this);
		btnIniciarSesion.setBounds(41, 162, 149, 29);
		btnIniciarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIniciarSesion.setForeground(new Color(0, 128, 255));
		btnIniciarSesion.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		btnIniciarSesion.setBackground(Color.WHITE);
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
		lblNewLabel_2.setIcon(new ImageIcon(VentanapPrincipal.class.getResource("/Imagenes/fitness-7071849_1920 (1)2.png")));
		lblNewLabel_2.setBounds(185, 2, 35, 58);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setForeground(new Color(192, 192, 192));
		lblNewLabel_2_1.setIcon(new ImageIcon(VentanapPrincipal.class.getResource("/Imagenes/fitness-7071849_1920 (1).png")));
		lblNewLabel_2_1.setBounds(12, 1, 35, 58);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblIniciaSesinComo = new JLabel("<html><u>Iniciar sesión como monitor</u></html>");
		lblIniciaSesinComo.setForeground(Color.BLUE);
		lblIniciaSesinComo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIniciaSesinComo.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		lblIniciaSesinComo.setBounds(44, 186, 178, 25);
		lblIniciaSesinComo.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
		        VentanaMonitores vm = new VentanaMonitores(); 
			        vm.setVisible(true);
				     dispose(); 
				    }
			 });
			
		panel.add(lblIniciaSesinComo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 255));
		panel_1.setBounds(10, 47, 416, 170);
		panel_1.setBorder(new LineBorder(Color.BLACK, 2, true));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblanNoTienes = new JLabel("¿Aún no \r\ntienes cuenta?");
		lblanNoTienes.setBackground(new Color(240, 240, 240));
		lblanNoTienes.setForeground(new Color(255, 255, 255));
		lblanNoTienes.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblanNoTienes.setBounds(244, -21, 182, 93);
		panel_1.add(lblanNoTienes);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(this);
		btnRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistrarse.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		btnRegistrarse.setBackground(new Color(255, 255, 255));
		btnRegistrarse.setForeground(new Color(0, 128, 255));
		btnRegistrarse.setBounds(265, 82, 129, 29);
		panel_1.add(btnRegistrarse);

		
		JLabel lblRegistrateMensaje = new JLabel("Registrate para poder");
		lblRegistrateMensaje.setBounds(269, 39, 193, 13);
		panel_1.add(lblRegistrateMensaje);
		lblRegistrateMensaje.setForeground(new Color(255, 255, 255));
		lblRegistrateMensaje.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		
		JLabel lbliniciarsesion = new JLabel("Iniciar Sesion");
		lbliniciarsesion.setForeground(new Color(255, 255, 255));
		lbliniciarsesion.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		lbliniciarsesion.setBounds(289, 62, 147, 13);
		panel_1.add(lbliniciarsesion);
		
	}
	public Border getPanelBorder() {
		return panel.getBorder();
	}
	public void setPanelBorder(Border border) {
		panel.setBorder(border);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrarse) { 
            VentanaRegistro vr = new VentanaRegistro();
            vr.setVisible(true);
            dispose();
        } else if (e.getSource() == btnIniciarSesion) {  
            if (passwordField.getText().isEmpty() || textCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (passwordField.getText().equals(password) && textCorreo.getText().equals(usuario)) {
                VentanaUsuarios vu = new VentanaUsuarios();
                vu.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
	
}

