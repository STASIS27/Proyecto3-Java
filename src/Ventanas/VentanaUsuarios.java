package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(231, 231, 231));
		panel_2_1.setBounds(20, 72, 394, 139);
		contentPane.add(panel_2_1);
		panel_2_1.setLayout(null);
	
		String[] columnas = {"Clase", "Horario"};
		String[][] datos = {
		    {"Yoga", "Lunes 10:00"},
		    {"Pilates", "Miércoles 12:00"},
		    {"Spinning", "Viernes 18:00"}
		};

		JTable tablaClases = new JTable(datos, columnas) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; 
		    }
		};

		tablaClases.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tablaClases.setRowHeight(20);

		JScrollPane scrollPane = new JScrollPane(tablaClases);
		scrollPane.setBounds(10, 50, 370, 60);
		panel_2_1.add(scrollPane);


		JButton btnApuntarse = new JButton("Apuntarse");
		btnApuntarse.setForeground(new Color(0, 128, 255));
		btnApuntarse.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));
		btnApuntarse.setBounds(60, 115, 100, 20);
		panel_2_1.add(btnApuntarse);

	
		JButton btnDesapuntarse = new JButton("Desapuntarse");
		btnDesapuntarse.setForeground(new Color(0, 128, 255));
		btnDesapuntarse.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));
		btnDesapuntarse.setBounds(220, 115, 120, 20);
		panel_2_1.add(btnDesapuntarse);

		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		textField.setColumns(10);
		textField.setBounds(75, 92, 147, 19);
		panel.add(textField);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(Color.BLACK, 2, true));
		panel_1.setBackground(new Color(0, 128, 255));
		panel_1.setBounds(10, 47, 416, 170);
		contentPane_1.add(panel_1);
		
		
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
		panel_2_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Clases Disponibles");
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblNewLabel.setBounds(38, -20, 132, 73);
		panel_2_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("La duración de las clases es de 1 hora");
		lblNewLabel_1.setForeground(new Color(0, 128, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblNewLabel_1.setBounds(38, 0, 302, 73);
		panel_2_1.add(lblNewLabel_1);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(this);
		btnVolver.setForeground(new Color(0, 128, 255));
		btnVolver.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBounds(313, 221, 101, 32);
		contentPane.add(btnVolver);
	
		btnApuntarse.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int filaSeleccionada = tablaClases.getSelectedRow();
		        if (filaSeleccionada != -1) {
		            String clase = (String) tablaClases.getValueAt(filaSeleccionada, 0);
		            JOptionPane.showMessageDialog(null, "Te has apuntado a la clase de " + clase + ".");
		        } else {
		            JOptionPane.showMessageDialog(null, "Selecciona una clase para apuntarte.", "Aviso", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});

		btnDesapuntarse.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int filaSeleccionada = tablaClases.getSelectedRow();
		        if (filaSeleccionada != -1) {
		            String clase = (String) tablaClases.getValueAt(filaSeleccionada, 0);
		            JOptionPane.showMessageDialog(null, "Te has desapuntado de la clase de " + clase + ".");
		        } else {
		            JOptionPane.showMessageDialog(null, "Selecciona una clase para desapuntarte.", "Aviso", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});

		
		
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
