package Ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import BaseDeDatos.ConexionBD;
import javax.swing.border.LineBorder;

public class VentanaVerClientes extends JFrame {
	// Declaracion de objetos
    private JPanel contentPane;
    private JTable tableClientes;
    private JTextField txtBuscar;
    private DefaultTableModel model;

 // Todo esto es a nivel grafico,como se ve la ventana
    public VentanaVerClientes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 128));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(20, 68, 200, 25);
        contentPane.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(232, 68, 100, 25);
        contentPane.add(btnBuscar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(470, 68, 100, 25);
        contentPane.add(btnVolver);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 103, 550, 250);
        contentPane.add(scrollPane);

        tableClientes = new JTable();
        model = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellidos", "Correo", "Teléfono"}, 0);
        tableClientes.setModel(model);
        scrollPane.setViewportView(tableClientes);
        
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBounds(143, 10, 276, 40);
        panelTitulo.setLayout(null);
        panelTitulo.setBorder(new LineBorder(Color.BLACK, 2, true));
        contentPane.add(panelTitulo);
        
        JLabel lblClientesRegistrados = new JLabel("Clientes Registrados");
        lblClientesRegistrados.setForeground(new Color(0, 0, 128));
        lblClientesRegistrados.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
        lblClientesRegistrados.setBounds(50, 5, 178, 30);
        panelTitulo.add(lblClientesRegistrados);

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
        model.setRowCount(0); // Limpiar tabla antes de cargar nuevos datos
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean encontrado = false;

        try {
            con = ConexionBD.getConexion(); // Obtener conexion a la base de datos

            // Consulta SQL que busca clientes cuyo nombre coincida parcialmente
            String sql = "SELECT c.Id_Persona_Aux, p.Nombre, p.Apellido, p.Correo, p.Telefono " +
                         "FROM cliente c JOIN persona p ON c.Id_Persona_Aux = p.Id_Persona " +
                         "WHERE p.Nombre LIKE ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombreFiltro + "%"); // Usar filtro con comodines

            rs = ps.executeQuery(); // Ejecutar consulta

            // Recorrer resultados y agregar cada cliente a la tabla
            while (rs.next()) {
                int id = rs.getInt("Id_Persona_Aux");
                String nombre = rs.getString("Nombre");
                String apellidos = rs.getString("Apellido");
                String email = rs.getString("Correo");
                String telefono = rs.getString("Telefono");
                model.addRow(new Object[]{id, nombre, apellidos, email, telefono});
                encontrado = true;
            }

            // Mostrar mensaje si no se encontraron clientes
            if (!encontrado) {
                JOptionPane.showMessageDialog(this,
                    "No se encontraron clientes con ese nombre.",
                    "Sin resultados",
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            // Mostrar error si ocurre un problema con la consulta
            JOptionPane.showMessageDialog(this, "Error al cargar clientes:\n" + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos
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

