package Ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import com.mysql.jdbc.Connection;
import BaseDeDatos.ConexionBD;

public class VentanaClasesUsuario extends JFrame implements ActionListener {
    private String correoUsuario;
    private JTable tablaClases;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public VentanaClasesUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
        setTitle("Clases del usuario: " + correoUsuario);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponentes();
        cargarClasesUsuario();
    }

    private void initComponentes() {
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[] {"Clase", "Fecha", "Horario", "Monitor"});

        tablaClases = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClases);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(this);
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }
    private void cargarClasesUsuario() {
        try (Connection con = (Connection) ConexionBD.getConexion()) {
            // Consulta para obtener las clases del usuario dado su correo
            String sql = "SELECT cl.Nombre AS nombreClase, cl.Fecha, cl.Duracion, " +
                    "p.Nombre AS nombreMonitor, p.Apellido AS apellidoMonitor " +
                    "FROM `cliente-clase` cc " +
                    "JOIN cliente c ON cc.Num_Socio_Aux = c.Num_Socio " +
                    "JOIN persona per ON c.Id_Persona_Aux = per.Id_Persona " +
                    "JOIN clase cl ON cc.Id_Clase_Aux = cl.Id_Clase " +
                    "JOIN monitor m ON cl.Id_Monitor_Aux = m.Id_Monitor " +
                    "JOIN persona p ON m.Id_Persona_Aux = p.Id_Persona " +
                    "WHERE per.Correo = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correoUsuario);

            ResultSet rs = ps.executeQuery();

            // Limpiar tabla antes de cargar datos
            modeloTabla.setRowCount(0);

            boolean hayClases = false;
            while (rs.next()) {
                hayClases = true;
                String nombreClase = rs.getString("nombreClase");
                Date fecha = rs.getDate("Fecha");
                String horario = rs.getString("Horario");
                String monitor = rs.getString("nombreMonitor") + " " + rs.getString("apellidoMonitor");

                modeloTabla.addRow(new Object[]{nombreClase, fecha.toString(), horario, monitor});
            }

            if (!hayClases) {
                JOptionPane.showMessageDialog(this, "No estás apuntado a ninguna clase.", "Información", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las clases: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource().equals(btnVolver)) {
	           VentanaMenuUsuarios vmu = new VentanaMenuUsuarios(correoUsuario);
	           vmu.setVisible(true);
	           dispose();
	        }
		
	}

}
