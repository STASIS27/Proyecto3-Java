package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class VentanaRegistro extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	private ButtonGroup bg = new ButtonGroup();
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
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
	public VentanaRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 64, 128));
		panel.setBounds(10, 10, 416, 32);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Bienvenido,crea tu cuenta con nosotros");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 255));
		panel_1.setBounds(10, 59, 205, 183);
		panel_1.setBorder(new LineBorder(Color.BLACK, 2, true));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		textField.setBounds(99, 10, 96, 19);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		textField_1.setColumns(10);
		textField_1.setBounds(99, 39, 96, 19);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		textField_2.setColumns(10);
		textField_2.setBounds(99, 68, 96, 19);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		textField_3.setColumns(10);
		textField_3.setBounds(99, 97, 96, 19);
		panel_1.add(textField_3);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 13, 79, 13);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("1ªApellido");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(10, 42, 79, 13);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("2ªApellido");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(10, 71, 79, 13);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Correo");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNewLabel_1_3.setBounds(10, 100, 79, 13);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Contraseña");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNewLabel_1_4.setBounds(10, 129, 79, 13);
		panel_1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("F.Nacimiento");
		lblNewLabel_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_5.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNewLabel_1_5.setBounds(10, 158, 79, 13);
		panel_1.add(lblNewLabel_1_5);
		
		JSpinner spinner = new JSpinner();
		 Calendar calendar = Calendar.getInstance();
	        calendar.set(1900, Calendar.JANUARY, 1); // Establecer el rango desde 1900

	        // Crear el modelo de fecha
	        SpinnerDateModel model = new SpinnerDateModel(calendar.getTime(), null, new java.util.Date(), Calendar.DAY_OF_MONTH);
	        spinner.setModel(model);

	        // Establecer propiedades del spinner
	        spinner.setForeground(new Color(0, 128, 255));
	        spinner.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
	        spinner.setBounds(99, 155, 96, 20);

	        // Establecer el formato de la fecha en el spinner
	        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
	        spinner.setEditor(editor);

	        // Asumir que 'panel_1' ya está definido en tu ventana, solo agrega el spinner:
	        panel_1.add(spinner);
	        
	        passwordField = new JPasswordField();
	        passwordField.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
	        passwordField.setBounds(99, 126, 96, 19);
	        panel_1.add(passwordField);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(221, 59, 205, 89);
		panel_1_1.setBorder(new LineBorder(Color.BLACK, 2, true));
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblNewLabel_1_6 = new JLabel("DNI");
		lblNewLabel_1_6.setBounds(10, 10, 47, 16);
		lblNewLabel_1_6.setForeground(new Color(0, 128, 255));
		lblNewLabel_1_6.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		panel_1_1.add(lblNewLabel_1_6);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		textField_5.setColumns(10);
		textField_5.setBounds(67, 9, 128, 19);
		panel_1_1.add(textField_5);
		
		JLabel lblNewLabel_1_6_1 = new JLabel("Móvil");
		lblNewLabel_1_6_1.setForeground(new Color(0, 128, 255));
		lblNewLabel_1_6_1.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNewLabel_1_6_1.setBounds(10, 36, 47, 16);
		panel_1_1.add(lblNewLabel_1_6_1);
		
		JLabel lblNewLabel_1_6_2 = new JLabel("NºCuenta");
		lblNewLabel_1_6_2.setForeground(new Color(0, 128, 255));
		lblNewLabel_1_6_2.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNewLabel_1_6_2.setBounds(10, 62, 56, 16);
		panel_1_1.add(lblNewLabel_1_6_2);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		textField_6.setColumns(10);
		textField_6.setBounds(67, 35, 128, 19);
		panel_1_1.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		textField_7.setColumns(10);
		textField_7.setBounds(67, 61, 128, 19);
		panel_1_1.add(textField_7);
		
		btnNewButton = new JButton("Volver");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(new Color(0, 128, 255));
		btnNewButton.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		btnNewButton.setBounds(221, 210, 96, 32);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Registrar");
		btnNewButton_1.setForeground(new Color(0, 128, 255));
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		btnNewButton_1.setBounds(330, 209, 96, 32);
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(221, 158, 205, 42);
		panel_2.setBorder(new LineBorder(Color.BLACK, 2, true));
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBounds(45, 6, 73, 30);
		rdbtnHombre.setForeground(new Color(0, 128, 255));
		rdbtnHombre.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		rdbtnHombre.setBackground(new Color(255, 255, 255));
		panel_2.add(rdbtnHombre);
		
		JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setForeground(new Color(0, 128, 255));
		rdbtnMujer.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		rdbtnMujer.setBackground(Color.WHITE);
		rdbtnMujer.setBounds(126, 6, 73, 30);
		panel_2.add(rdbtnMujer);
		
		JLabel lblNewLabel_1_6_2_1 = new JLabel("Sexo");
		lblNewLabel_1_6_2_1.setForeground(new Color(0, 128, 255));
		lblNewLabel_1_6_2_1.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNewLabel_1_6_2_1.setBounds(10, 13, 56, 16);
		panel_2.add(lblNewLabel_1_6_2_1);
		
		bg.add(rdbtnHombre);
		bg.add(rdbtnMujer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if(e.getSource().equals(btnNewButton)) {
	        VentanapPrincipal vp = new VentanapPrincipal();
	        vp.setVisible(true);
	        dispose(); 
	    } else if (e.getSource().equals(btnNewButton_1)) {
	        System.out.println("Registrar usuario");
	    }
	}
}
