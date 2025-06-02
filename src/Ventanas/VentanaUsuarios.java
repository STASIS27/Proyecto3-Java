package Ventanas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BaseDeDatos.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaUsuarios extends JFrame implements ActionListener {
	// Objetos y Variables
	private String correoUsuario;
	private JPanel contentPane;
	private JTable tablaClases;
	private DefaultTableModel modeloTabla;
	private JButton btnApuntarse, btnVolver;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				VentanaUsuarios frame = new VentanaUsuarios("");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

//Pasamos el correo del usuario que se ha loggeado
	public VentanaUsuarios(String correoUsuario) {
		this.correoUsuario = correoUsuario;
		// Parte Grafica
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Clases Disponibles");
		lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblTitulo.setForeground(new Color(0, 128, 255));
		lblTitulo.setBounds(20, 10, 300, 30);
		contentPane.add(lblTitulo);

		// Tabla con columnas: Nombre clase, Nombre monitor, Horario, Fecha
		String[] columnas = { "Clase", "Monitor", "Horario", "Fecha" };
		modeloTabla = new DefaultTableModel(null, columnas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // No editable
			}
		};

		tablaClases = new JTable(modeloTabla);
		tablaClases.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tablaClases.setRowHeight(22);

		JScrollPane scrollPane = new JScrollPane(tablaClases);
		scrollPane.setBounds(20, 50, 540, 220);
		contentPane.add(scrollPane);

		btnApuntarse = new JButton("Apuntarse");
		btnApuntarse.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnApuntarse.setForeground(new Color(0, 128, 255));
		btnApuntarse.setBounds(211, 292, 130, 30);
		contentPane.add(btnApuntarse);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnVolver.setForeground(new Color(0, 128, 255));
		btnVolver.setBounds(480, 330, 80, 25);
		contentPane.add(btnVolver);

		// Boton para apuntarse, con avisos si no selecciona clase o no la encuentra
		btnApuntarse.addActionListener(e -> {
			int fila = tablaClases.getSelectedRow();
			if (fila == -1) {
				JOptionPane.showMessageDialog(this, "Selecciona una clase para apuntarte.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			String nombreClase = (String) modeloTabla.getValueAt(fila, 0);
			String horario = (String) modeloTabla.getValueAt(fila, 2);
			String fecha = (String) modeloTabla.getValueAt(fila, 3);

			String idClase = obtenerIdClase(nombreClase, horario, fecha);
			if (idClase == null) {
				JOptionPane.showMessageDialog(this, "Clase no encontrada en base de datos.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			apuntarse(idClase);
			cargarClases(); // refresca tabla por si quieres mostrar estado actualizado
		});

		btnVolver.addActionListener(this);

		cargarClases();
	}

//Metodo para cargar las clases en la tabla
	private void cargarClases() {
		// Vaciar tabla
		modeloTabla.setRowCount(0);

		String sql = "SELECT c.Nombre AS NombreClase, p.Nombre, c.Duracion, c.Fecha " + "FROM clase c "
				+ "JOIN monitor m ON c.Id_Monitor_Aux = m.Id_Monitor "
				+ "JOIN persona p ON m.Id_Persona_Aux = p.Id_Persona " + "ORDER BY c.Fecha, c.Duracion";

		try (Connection con = ConexionBD.getConexion();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				String nombreClase = rs.getString("NombreClase");
				String nombreMonitor = rs.getString("Nombre");
				String horario = rs.getString("Duracion");
				String fecha = rs.getString("Fecha");

				modeloTabla.addRow(new Object[] { nombreClase, nombreMonitor, horario, fecha });
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cargar clases:\n" + e.getMessage(), "Error BD",
					JOptionPane.ERROR_MESSAGE);
		}
	}

//Metodo para obtener el id de la clase especifica, para apuntarse
	private String obtenerIdClase(String nombreClase, String horario, String fecha) {
		String sql = "SELECT Id_Clase FROM clase WHERE Nombre = ? AND Duracion = ? AND Fecha = ?";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, nombreClase);
			ps.setString(2, horario);
			ps.setString(3, fecha);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getString("Id_Clase");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al obtener ID clase:\n" + e.getMessage(), "Error BD",
					JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

//Metodo para conseguir el numero del socio a traves del correo y apuntarle a la clase
	private int obtenerNumSocioPorCorreo(String correoUsuario) {
		String sql = "SELECT c.Num_Socio FROM cliente c JOIN persona p ON c.Id_Persona_Aux = p.Id_Persona WHERE p.Correo = ?";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, correoUsuario);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("Num_Socio");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al obtener Num_Socio:\n" + e.getMessage(), "Error BD",
					JOptionPane.ERROR_MESSAGE);
		}
		return -1;
	}

//Metodo para comprobar si el usuario esta apuntado en esa clase
	private boolean estaApuntado(String idClase) {
		int numSocioAux = obtenerNumSocioPorCorreo(correoUsuario);
		if (numSocioAux == -1)
			return false;

		String sql = "SELECT * FROM `cliente-clase` WHERE Num_Socio_Aux = ? AND Id_Clase_Aux = ?";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, numSocioAux);
			ps.setString(2, idClase);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al comprobar apuntado:\n" + e.getMessage(), "Error BD",
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

//Metodo para apuntarse
	private void apuntarse(String idClase) {
		if (estaApuntado(idClase)) {
			JOptionPane.showMessageDialog(this, "Ya estás apuntado a esta clase.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int numSocio = obtenerNumSocioPorCorreo(correoUsuario);
		if (numSocio == -1) {
			JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Verificar plazas disponibles
		String sqlPlazas = "SELECT Plazas FROM clase WHERE Id_Clase = ?";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement psPlazas = con.prepareStatement(sqlPlazas)) {

			psPlazas.setString(1, idClase);
			try (ResultSet rs = psPlazas.executeQuery()) {
				if (rs.next()) {
					int plazas = rs.getInt("Plazas");
					if (plazas <= 0) {
						JOptionPane.showMessageDialog(this, "No hay plazas disponibles para esta clase.", "Sin plazas",
								JOptionPane.WARNING_MESSAGE);
						return; //Returnea si no hay plazas
					}
				} else {
					JOptionPane.showMessageDialog(this, "Clase no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			// Insertar en cliente-clase
			String sqlInsert = "INSERT INTO `cliente-clase` (Num_Socio_Aux, Id_Clase_Aux, Horario) VALUES (?, ?, ?)";
			try (PreparedStatement psInsert = con.prepareStatement(sqlInsert)) {
				psInsert.setInt(1, numSocio);
				psInsert.setString(2, idClase);
				psInsert.setInt(3, numSocio); // Nota: aquí está como en el original

				int filas = psInsert.executeUpdate();
				if (filas > 0) {
					// Restar una plaza
					String sqlUpdatePlazas = "UPDATE clase SET Plazas = Plazas - 1 WHERE Id_Clase = ?";
					try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdatePlazas)) {
						psUpdate.setString(1, idClase);
						psUpdate.executeUpdate();
					}

					JOptionPane.showMessageDialog(this, "Te has apuntado correctamente.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "No se pudo apuntar a la clase.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error en la base de datos:\n" + e.getMessage(), "Error BD",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVolver) {
			VentanaMenuUsuarios vmu = new VentanaMenuUsuarios(correoUsuario);
			vmu.setVisible(true);
			dispose();
		}
	}
}
