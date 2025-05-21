package Ventanas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BaseDeDatos.ConexionBD;

public class VentanaVerClasesUsuario extends JFrame implements ActionListener {
	// Declaracion de objetos
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaMisClases;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;
    private String correoUsuario;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VentanaVerClasesUsuario frame = new VentanaVerClasesUsuario("");
            frame.setVisible(true);
        });
    }
    // Todo esto es a nivel grafico,como se ve la ventana
    public VentanaVerClasesUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;

        setTitle("Mis Clases");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 550, 350);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 128));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Clases en las que estás apuntado");
        lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(20, 10, 400, 30);
        contentPane.add(lblTitulo);

        // Definimos las columnas: NombreClase, Monitor, Horario, Fecha
        String[] columnas = { "Clase", "Monitor", "Horario", "Fecha" };
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // no editable
            }
        };
        tablaMisClases = new JTable(modeloTabla);
        tablaMisClases.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablaMisClases.setRowHeight(22);

        JScrollPane scrollPane = new JScrollPane(tablaMisClases);
        scrollPane.setBounds(20, 50, 500, 200);
        contentPane.add(scrollPane);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnVolver.setForeground(new Color(0, 128, 255));
        btnVolver.setBounds(440, 270, 80, 30);
        btnVolver.addActionListener(this);
        contentPane.add(btnVolver);

        cargarMisClases();
    }

    private int obtenerNumSocioPorCorreo(String correo) {
        // Consulta SQL para obtener el número de socio a partir del correo
        String sql = "SELECT c.Num_Socio FROM cliente c JOIN persona p ON c.Id_Persona_Aux = p.Id_Persona WHERE p.Correo = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Inserta el correo como parametro en la consulta preparada
            ps.setString(1, correo);

            // Ejecuta la consulta
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Devuelve el numero de socio si se encuentra
                    return rs.getInt("Num_Socio");
                }
            }
        } catch (SQLException e) {
            // Muestra un mensaje de error si ocurre un problema con la base de datos
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al obtener Num_Socio:\n" + e.getMessage(),
                "Error BD",
                JOptionPane.ERROR_MESSAGE);
        }
        // Devuelve -1 si no se encuentra el socio
        return -1;
    }
    private void cargarMisClases() {
        modeloTabla.setRowCount(0); // Limpia la tabla antes de cargar nuevos datos

        int numSocio = obtenerNumSocioPorCorreo(correoUsuario); // Obtiene el numero de socio con el correo del usuario
        if (numSocio == -1) {
            // Si no se encuentra el socio, muestra un mensaje de error
            JOptionPane.showMessageDialog(this,
                "No se encontró tu usuario en la base de datos.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Consulta SQL que obtiene las clases a las que está inscrito el socio
        String sql = """
            SELECT
                c.Nombre AS NombreClase,
                p.Nombre AS Monitor,
                c.Duracion AS Horario,
                c.Fecha AS Fecha
            FROM `cliente-clase` cc
            JOIN cliente cl ON cc.Num_Socio_Aux = cl.Num_Socio
            JOIN clase c ON cc.Id_Clase_Aux = c.Id_Clase
            JOIN monitor m ON c.Id_Monitor_Aux = m.Id_Monitor
            JOIN persona p ON m.Id_Persona_Aux = p.Id_Persona
            WHERE cl.Num_Socio = ?
            ORDER BY c.Fecha, c.Duracion
            """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numSocio); // Inserta el numero de socio en la consulta

            try (ResultSet rs = ps.executeQuery()) {
                boolean hayClases = false;

                // Recorre los resultados y agrega las clases a la tabla
                while (rs.next()) {
                    hayClases = true;
                    String nombreClase   = rs.getString("NombreClase");
                    String nombreMonitor = rs.getString("Monitor");
                    String horario       = rs.getString("Horario");
                    String fecha         = rs.getString("Fecha");

                    modeloTabla.addRow(new Object[] { nombreClase, nombreMonitor, horario, fecha });
                }

                // Si no hay clases, muestra un mensaje informativo
                if (!hayClases) {
                    JOptionPane.showMessageDialog(this,
                        "No estás apuntado a ninguna clase.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException e) {
            // Muestra un mensaje si ocurre un error al cargar las clases
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al cargar tus clases:\n" + e.getMessage(),
                "Error BD",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            // Regresar a la ventana de menu de usuarios
            VentanaMenuUsuarios vmu = new VentanaMenuUsuarios(correoUsuario);
            vmu.setVisible(true);
            dispose();
        }
    }

}
