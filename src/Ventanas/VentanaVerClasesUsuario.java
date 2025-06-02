package Ventanas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BaseDeDatos.ConexionBD;

public class VentanaVerClasesUsuario extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaMisClases;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;
    private JButton btnDesapuntarse;
    private String correoUsuario;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VentanaVerClasesUsuario frame = new VentanaVerClasesUsuario("");
            frame.setVisible(true);
        });
    }

    public VentanaVerClasesUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;

        setTitle("Mis Clases");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 350);
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

        // Columnas con IdClase oculta
        String[] columnas = { "Clase", "Monitor", "Horario", "Fecha", "IdClase" };
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // no editable
            }
        };

        tablaMisClases = new JTable(modeloTabla);
        tablaMisClases.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablaMisClases.setRowHeight(22);

        // Ocultar columna IdClase (índice 4)
        tablaMisClases.getColumnModel().getColumn(4).setMinWidth(0);
        tablaMisClases.getColumnModel().getColumn(4).setMaxWidth(0);
        tablaMisClases.getColumnModel().getColumn(4).setWidth(0);
        tablaMisClases.getColumnModel().getColumn(4).setPreferredWidth(0);

        JScrollPane scrollPane = new JScrollPane(tablaMisClases);
        scrollPane.setBounds(20, 50, 550, 200);
        contentPane.add(scrollPane);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnVolver.setForeground(new Color(0, 128, 255));
        btnVolver.setBounds(440, 270, 80, 30);
        btnVolver.addActionListener(this);
        contentPane.add(btnVolver);

        btnDesapuntarse = new JButton("Desapuntarse");
        btnDesapuntarse.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnDesapuntarse.setForeground(new Color(255, 0, 0));
        btnDesapuntarse.setBounds(227, 270, 120, 30);
        btnDesapuntarse.addActionListener(this);
        contentPane.add(btnDesapuntarse);

        cargarMisClases();
    }

    private int obtenerNumSocioPorCorreo(String correo) {
        String sql = "SELECT c.Num_Socio FROM cliente c JOIN persona p ON c.Id_Persona_Aux = p.Id_Persona WHERE p.Correo = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Num_Socio");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al obtener Num_Socio:\n" + e.getMessage(),
                "Error BD",
                JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    private void cargarMisClases() {
        modeloTabla.setRowCount(0);

        int numSocio = obtenerNumSocioPorCorreo(correoUsuario);
        if (numSocio == -1) {
            JOptionPane.showMessageDialog(this,
                "No se encontró tu usuario en la base de datos.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = """
            SELECT
                c.Nombre AS NombreClase,
                p.Nombre AS Monitor,
                c.Duracion AS Horario,
                c.Fecha AS Fecha,
                c.Id_Clase
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

            ps.setInt(1, numSocio);

            try (ResultSet rs = ps.executeQuery()) {
                boolean hayClases = false;

                while (rs.next()) {
                    hayClases = true;
                    String nombreClase = rs.getString("NombreClase");
                    String nombreMonitor = rs.getString("Monitor");
                    String horario = rs.getString("Horario");
                    String fecha = rs.getString("Fecha");
                    int idClase = rs.getInt("Id_Clase");

                    modeloTabla.addRow(new Object[] { nombreClase, nombreMonitor, horario, fecha, idClase });
                }

                if (!hayClases) {
                    JOptionPane.showMessageDialog(this,
                        "No estás apuntado a ninguna clase.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al cargar tus clases:\n" + e.getMessage(),
                "Error BD",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void desapuntarseDeClase() {
        int filaSeleccionada = tablaMisClases.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                "Por favor, selecciona una clase para desapuntarte.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int numSocio = obtenerNumSocioPorCorreo(correoUsuario);
        if (numSocio == -1) {
            JOptionPane.showMessageDialog(this,
                "No se encontró tu usuario en la base de datos.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idClase = (int) modeloTabla.getValueAt(filaSeleccionada, 4);
        String nombreClase = (String) modeloTabla.getValueAt(filaSeleccionada, 0);

        String sqlEliminar = "DELETE FROM `cliente-clase` WHERE Num_Socio_Aux = ? AND Id_Clase_Aux = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement psEliminar = con.prepareStatement(sqlEliminar)) {

            psEliminar.setInt(1, numSocio);
            psEliminar.setInt(2, idClase);

            int filasAfectadas = psEliminar.executeUpdate();

            if (filasAfectadas > 0) {
            	  // Incrementar plazas disponibles en clase
                String sqlUpdatePlazas = "UPDATE clase SET Plazas = Plazas + 1 WHERE Id_Clase = ?";
                try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdatePlazas)) {
                    psUpdate.setInt(1, idClase);
                    psUpdate.executeUpdate();
                }
                JOptionPane.showMessageDialog(this,
                    "Te has desapuntado correctamente de la clase \"" + nombreClase + "\".",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

                cargarMisClases();

            } else {
                JOptionPane.showMessageDialog(this,
                    "No se pudo desapuntar de la clase. No se encontró inscripción.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al desapuntarse:\n" + e.getMessage(),
                "Error BD",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            VentanaMenuUsuarios vmu = new VentanaMenuUsuarios(correoUsuario);
            vmu.setVisible(true);
            dispose();
        } else if (e.getSource() == btnDesapuntarse) {
            desapuntarseDeClase();
        }
    }
}

