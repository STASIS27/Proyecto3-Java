package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Container;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMonitores extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField pswContra;
	private JButton btnVolver, btnIniciarSesion;
	private final String usuario = "monitor123";
	private final String password = "123monitor";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMonitores frame = new VentanaMonitores();
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
	public VentanaMonitores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(Color.BLACK, 2, true));
		panel_1.setBounds(100, 25, 230, 211);
		contentPane.add(panel_1);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		lblNewLabel.setBounds(12, 88, 65, 25);
		panel_1.add(lblNewLabel);

		textUsuario = new JTextField();
		textUsuario.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		textUsuario.setColumns(10);
		textUsuario.setBounds(75, 92, 147, 19);
		panel_1.add(textUsuario);

		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setForeground(new Color(0, 128, 255));
		lblContrasea.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		lblContrasea.setBounds(12, 127, 83, 25);
		panel_1.add(lblContrasea);

		btnIniciarSesion = new JButton("Acceder");
		btnIniciarSesion.addActionListener(this);
		btnIniciarSesion.setForeground(new Color(0, 128, 255));
		btnIniciarSesion.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		btnIniciarSesion.setBackground(Color.WHITE);
		btnIniciarSesion.setBounds(120, 162, 100, 29);
		panel_1.add(btnIniciarSesion);

		pswContra = new JPasswordField();
		pswContra.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		pswContra.setBounds(75, 130, 147, 19);
		panel_1.add(pswContra);

		JLabel lblNewLabel_1 = new JLabel("DAM FIT");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblNewLabel_1.setBounds(75, 10, 126, 34);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2
				.setIcon(new ImageIcon(VentanaMonitores.class.getResource("/Imagenes/fitness-7071849_1920 (1)2.png")));
		lblNewLabel_2.setBounds(185, 2, 35, 58);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1
				.setIcon(new ImageIcon(VentanaMonitores.class.getResource("/Imagenes/fitness-7071849_1920 (1).png")));
		lblNewLabel_2_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_1.setBounds(12, 1, 35, 58);
		panel_1.add(lblNewLabel_2_1);

		btnVolver = new JButton("Volver");
		btnVolver.setForeground(new Color(0, 128, 255));
		btnVolver.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBounds(12, 162, 93, 29);
		btnVolver.addActionListener(this);
		panel_1.add(btnVolver);

		JLabel lblAccedaConLas = new JLabel("Acceda con las credenciales corporativas");
		lblAccedaConLas.setBackground(new Color(0, 128, 255));
		lblAccedaConLas.setForeground(new Color(0, 128, 255));
		lblAccedaConLas.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		lblAccedaConLas.setBounds(10, 69, 218, 13);
		panel_1.add(lblAccedaConLas);
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnVolver)) {
			VentanapPrincipal vp = new VentanapPrincipal();
			vp.setVisible(true);
			dispose();
		}
		if (e.getSource().equals(btnIniciarSesion)) {
			if(pswContra.getText().equals(password) && textUsuario.getText().equals(usuario)) {
			VentanaMenuMonitores vmm = new VentanaMenuMonitores();
			vmm.setVisible(true);
			dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
			}
		}

	}

}
