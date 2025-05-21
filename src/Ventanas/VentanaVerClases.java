package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import BaseDeDatos.ConexionBD;

public class VentanaVerClases extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaClases;
    private JButton btnVolver;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaVerClases frame = new VentanaVerClases();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaVerClases() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);  // Más ancho para columnas adicionales
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(0, 64, 128));
        panelSuperior.setBorder(new LineBorder(Color.BLACK, 2, true));
        panelSuperior.setPreferredSize(new Dimension(600, 50));
        contentPane.add(panelSuperior, BorderLayout.NORTH);
        panelSuperior.setLayout(null);

        JPanel panelTitulo_1 = new JPanel();
        panelTitulo_1.setLayout(null);
        panelTitulo_1.setBorder(new LineBorder(Color.BLACK, 2, true));
        panelTitulo_1.setBounds(0, 0, 600, 50);
        panelSuperior.add(panelTitulo_1);

        JLabel lblClasesRegistradas = new JLabel("CLASES REGISTRADAS");
        lblClasesRegistradas.setForeground(new Color(0, 0, 128));
        lblClasesRegistradas.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
        lblClasesRegistradas.setBounds(200, 10, 220, 30);
        panelTitulo_1.add(lblClasesRegistradas);

        tablaClases = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaClases);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnVolver.setForeground(new Color(0, 128, 255));
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setPreferredSize(new Dimension(100, 30));
        btnVolver.addActionListener(this);
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(0, 64, 128));
        panelInferior.add(btnVolver);
        contentPane.add(panelInferior, BorderLayout.SOUTH);

        cargarClases();
    }

    private void cargarClases() {
        DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"Nombre", "Horario", "Plazas", "Fecha", "Monitor"}, 0);

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = (Connection) ConexionBD.getConexion();
            String sql = "SELECT c.Nombre, c.Duracion, c.Plazas, c.Fecha, p.Nombre AS monitor " +
                         "FROM clase c " +
                         "LEFT JOIN monitor m ON c.Id_Monitor_Aux = m.Id_Monitor " +
                         "LEFT JOIN persona p ON m.Id_Persona_Aux = p.Id_Persona";

            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String nombre = rs.getString("Nombre");
                Time duracion = rs.getTime("Duracion");
                int plazas = rs.getInt("Plazas");
                Date fecha = rs.getDate("Fecha");
                String monitor = rs.getString("Monitor");

                String horarioFormateado = duracion.toString().substring(0, 5);

                modelo.addRow(new Object[]{nombre, horarioFormateado, plazas, fecha.toString(), monitor});
            }

            tablaClases.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las clases:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            VentanaMenuMonitores vm = new VentanaMenuMonitores();
            vm.setVisible(true);
            dispose();
        }
    }
}

