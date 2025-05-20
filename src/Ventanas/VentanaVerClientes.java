package Ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import BaseDeDatos.ConexionBD;

public class VentanaVerClientes extends JFrame {

    private JPanel contentPane;
    private JTable tableClientes;
    private JTextField txtBuscar;
    private DefaultTableModel model;

    public VentanaVerClientes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 128));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("LISTA DE CLIENTES");
        lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(200, 10, 250, 30);
        contentPane.add(lblTitulo);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(20, 50, 200, 25);
        contentPane.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(230, 50, 100, 25);
        contentPane.add(btnBuscar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(470, 50, 100, 25);
        contentPane.add(btnVolver);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 90, 550, 250);
        contentPane.add(scrollPane);

        tableClientes = new JTable();
        model = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellidos", "Correo", "Teléfono"}, 0);
        tableClientes.setModel(model);
        scrollPane.setViewportView(tableClientes);

        // Cargar todos los clientes al iniciar
        cargarClientes("");

        // Acción buscar
        btnBuscar.addActionListener(e -> {
            String filtro = txtBuscar.getText().trim();
            cargarClientes(filtro);
        });

        // Acción volver
        btnVolver.addActionListener(e -> {
            VentanaMenuMonitores menu = new VentanaMenuMonitores(); // O tu clase principal
            menu.setVisible(true);
            dispose();
        });
    }

    private void cargarClientes(String nombreFiltro) {
        model.setRowCount(0); // Limpiar tabla
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean encontrado = false;
        try {
            con = ConexionBD.getConexion();
            String sql = "SELECT c.Id_Persona_Aux, p.Nombre, p.Apellido, p.Correo, p.Teléfono " +
                         "FROM cliente c JOIN persona p ON c.Id_Persona_Aux = p.Id_Persona " +
                         "WHERE p.Nombre LIKE ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombreFiltro + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id_Persona_Aux");
                String nombre = rs.getString("Nombre");
                String apellidos = rs.getString("Apellido");
                String email = rs.getString("Correo");
                String telefono = rs.getString("Teléfono");
                model.addRow(new Object[]{id, nombre, apellidos, email, telefono});
                encontrado = true;
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(this,
                    "No se encontraron clientes con ese nombre.",
                    "Sin resultados",
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes:\n" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método main para ejecutar esta ventana de forma independiente
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaVerClientes frame = new VentanaVerClientes();
            frame.setVisible(true);
        });
    }
}

