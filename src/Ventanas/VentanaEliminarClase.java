package Ventanas;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BaseDeDatos.ConexionBD;

public class VentanaEliminarClase extends JFrame implements ActionListener {
	// Declaracion de objetos
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreClase;
	private JSpinner spinnerHorario;
	private JSpinner spinnerPlazas;
	private JSpinner spinnerFecha;
	private JButton btnEliminar, btnVolver;
	private JComboBox<String> comboMonitores;
	private HashMap<String, String> monitoresMap; // nombre -> idAux

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				VentanaEliminarClase frame = new VentanaEliminarClase();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public VentanaEliminarClase() {
		// Todo esto es a nivel grafico,como se ve la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(null);
		panelTitulo.setBorder(new LineBorder(Color.BLACK, 2, true));
		panelTitulo.setBounds(72, 10, 238, 40);
		contentPane.add(panelTitulo);

		JLabel lblTitulo = new JLabel("ELIMINAR CLASE");
		lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblTitulo.setForeground(new Color(0, 0, 128));
		lblTitulo.setBounds(50, 5, 150, 30);
		panelTitulo.add(lblTitulo);

		JLabel lblNombreClase = new JLabel("Nombre de la clase:");
		lblNombreClase.setForeground(Color.WHITE);
		lblNombreClase.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblNombreClase.setBounds(50, 70, 140, 20);
		contentPane.add(lblNombreClase);

		txtNombreClase = new JTextField();
		txtNombreClase.setBounds(190, 70, 140, 22);
		contentPane.add(txtNombreClase);
		txtNombreClase.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblFecha.setBounds(50, 110, 140, 20);
		contentPane.add(lblFecha);
		// Metemos spinner para poner un formato de horas y minutos
		spinnerFecha = new JSpinner(new SpinnerDateModel());
		spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "yyyy-MM-dd"));
		spinnerFecha.setBounds(190, 110, 140, 22);
		contentPane.add(spinnerFecha);

		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setForeground(Color.WHITE);
		lblHorario.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblHorario.setBounds(50, 150, 140, 20);
		contentPane.add(lblHorario);

		spinnerHorario = new JSpinner(new SpinnerDateModel());
		spinnerHorario.setEditor(new JSpinner.DateEditor(spinnerHorario, "HH:mm"));
		spinnerHorario.setBounds(190, 150, 140, 22);
		contentPane.add(spinnerHorario);

		JLabel lblPlazas = new JLabel("Plazas:");
		lblPlazas.setForeground(Color.WHITE);
		lblPlazas.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblPlazas.setBounds(50, 190, 140, 20);
		contentPane.add(lblPlazas);
		// Otro spinner para el numero de plazas
		spinnerPlazas = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerPlazas.setBounds(190, 190, 140, 22);
		contentPane.add(spinnerPlazas);

		JLabel lblMonitor = new JLabel("Monitor:");
		lblMonitor.setForeground(Color.WHITE);
		lblMonitor.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblMonitor.setBounds(50, 230, 140, 20);
		contentPane.add(lblMonitor);
		// ComboBox donde metemos todos los monitores registrados
		comboMonitores = new JComboBox<>();
		comboMonitores.setBounds(190, 230, 140, 22);
		contentPane.add(comboMonitores);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnEliminar.setForeground(new Color(0, 128, 255));
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setBounds(70, 280, 100, 30);
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnVolver.setForeground(new Color(0, 128, 255));
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBounds(210, 280, 100, 30);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);

		cargarMonitores(); // Cargar monitores y validar si hay
	}

	// Metodo para cargar los monitores para la comboBox
	private void cargarMonitores() {
		// Utilizamos un hashmap para ello
		monitoresMap = new HashMap<>();
		// Try-catch para control de excepciones
		try (Connection con = (Connection) ConexionBD.getConexion();
				Statement st = con.createStatement();
				// Cogemos el nombre de los monitores
				ResultSet rs = st.executeQuery(
						"SELECT m.Id_Monitor, p.Nombre FROM monitor m JOIN persona p ON m.Id_Persona_Aux = p.Id_Persona")) {

			comboMonitores.removeAllItems();

			while (rs.next()) {
				String idAux = rs.getString("Id_Monitor");
				String nombre = rs.getString("Nombre");
				// Añadimos el nombre a la combo box
				comboMonitores.addItem(nombre);
				// De esta manera con el hashmap puedo mostrar solo los nombres de los monitores
				// y podemos recuperar facilmente su id porque estan relacionados
				monitoresMap.put(nombre, idAux);
			}

			if (comboMonitores.getItemCount() == 0) {
				btnEliminar.setEnabled(false);
				JOptionPane.showMessageDialog(this, "No hay monitores en la base de datos. No puedes eliminar clases.",
						"Sin monitores", JOptionPane.WARNING_MESSAGE);
			} else {
				btnEliminar.setEnabled(true);
				comboMonitores.setSelectedIndex(0);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Error al cargar los monitores:\n" + ex.getMessage(), "Error BD",
					JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			btnEliminar.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Verificamos que los campos no esten vacios
		if (e.getSource() == btnEliminar) {
			String nombreClase = txtNombreClase.getText().trim();

			if (nombreClase.isEmpty() || comboMonitores.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.", "Campos vacíos",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			java.util.Date fechaUtil = (java.util.Date) spinnerFecha.getValue();
			Date fechaSql = new Date(fechaUtil.getTime());

			java.util.Date horaUtil = (java.util.Date) spinnerHorario.getValue();
			// Hacemos que en el calendar los segundos y milisegundos siempre esten a cero
			// por
			// comodidad
			Calendar cal = Calendar.getInstance();
			cal.setTime(horaUtil);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			Time horaSql = new Time(cal.getTimeInMillis());

			int plazas = (int) spinnerPlazas.getValue();
			// Cogemos el nombre del monitor y el id lo cojo gracias a que esta relacionado
			// ya por el hashmap
			String monitorNombre = (String) comboMonitores.getSelectedItem();
			String idMonitor = monitoresMap.get(monitorNombre);

			try (Connection con = (Connection) ConexionBD.getConexion();
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(
							"DELETE FROM clase WHERE Nombre = ? AND Fecha = ? AND Duracion = ? AND Plazas = ? AND Id_Monitor_Aux = ?")) {

				ps.setString(1, nombreClase);
				ps.setDate(2, fechaSql);
				ps.setTime(3, horaSql);
				ps.setInt(4, plazas);
				ps.setString(5, idMonitor);

				int filas = ps.executeUpdate();
//Si todo es correcto la clase se elimina
				if (filas > 0) {
					JOptionPane.showMessageDialog(this, "Clase eliminada correctamente.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					txtNombreClase.setText("");
					spinnerFecha.setValue(new java.util.Date());
					spinnerHorario.setValue(new java.util.Date());
					spinnerPlazas.setValue(1);
					comboMonitores.setSelectedIndex(0);
				} else {
					JOptionPane.showMessageDialog(this, "No se encontró una clase con esos datos.", "No encontrada",
							JOptionPane.WARNING_MESSAGE);
				}

			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(this, "Error al eliminar la clase:\n" + ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}

		if (e.getSource() == btnVolver) {
			VentanaMenuMonitores vm = new VentanaMenuMonitores();
			vm.setVisible(true);
			dispose();
		}
	}
}
