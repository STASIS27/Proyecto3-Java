package Ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BaseDeDatos.ConexionBD;

public class VentanaAñadirClase extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombreClase;
    private JSpinner spinnerPlazas, spinnerHorario, spinnerFecha;
    private JButton btnGuardar, btnVolver;
    private JComboBox<String> comboMonitores;
    private HashMap<String, String> monitoresMap;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaAñadirClase frame = new VentanaAñadirClase();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaAñadirClase() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 420, 390);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(null);
        panelTitulo.setBorder(new LineBorder(Color.BLACK, 2, true));
        panelTitulo.setBounds(110, 10, 200, 40);
        contentPane.add(panelTitulo);

        JLabel lblTitulo = new JLabel("AÑADIR CLASE");
        lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(0, 0, 128));
        lblTitulo.setBounds(40, 5, 150, 30);
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

        JLabel lblHorario = new JLabel("Horario:");
        lblHorario.setForeground(Color.WHITE);
        lblHorario.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        lblHorario.setBounds(50, 110, 140, 20);
        contentPane.add(lblHorario);

        spinnerHorario = new JSpinner(new SpinnerDateModel());
        spinnerHorario.setEditor(new JSpinner.DateEditor(spinnerHorario, "HH:mm"));
        spinnerHorario.setBounds(190, 110, 140, 22);
        contentPane.add(spinnerHorario);

        JLabel lblPlazas = new JLabel("Plazas:");
        lblPlazas.setForeground(Color.WHITE);
        lblPlazas.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        lblPlazas.setBounds(50, 150, 140, 20);
        contentPane.add(lblPlazas);

        spinnerPlazas = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinnerPlazas.setBounds(190, 150, 140, 22);
        contentPane.add(spinnerPlazas);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setForeground(Color.WHITE);
        lblFecha.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        lblFecha.setBounds(50, 190, 140, 20);
        contentPane.add(lblFecha);

        spinnerFecha = new JSpinner(new SpinnerDateModel());
        spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "yyyy-MM-dd"));
        spinnerFecha.setBounds(190, 190, 140, 22);
        contentPane.add(spinnerFecha);

        JLabel lblIdMonitor = new JLabel("Monitor:");
        lblIdMonitor.setForeground(Color.WHITE);
        lblIdMonitor.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        lblIdMonitor.setBounds(50, 230, 140, 20);
        contentPane.add(lblIdMonitor);

        comboMonitores = new JComboBox<>();
        comboMonitores.setBounds(190, 230, 140, 22);
        contentPane.add(comboMonitores);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnGuardar.setForeground(new Color(0, 128, 255));
        btnGuardar.setBackground(Color.WHITE);
        btnGuardar.setBounds(70, 290, 100, 30);
        btnGuardar.addActionListener(this);
        contentPane.add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnVolver.setForeground(new Color(0, 128, 255));
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setBounds(210, 290, 100, 30);
        btnVolver.addActionListener(this);
        contentPane.add(btnVolver);

        cargarMonitores();
    }

    private void cargarMonitores() {
        monitoresMap = new HashMap<>();
        try (Connection con = (Connection) ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT m.Id_Monitor, p.Nombre FROM monitor m JOIN persona p ON m.Id_Persona_Aux = p.Id_Persona")) {

            while (rs.next()) {
                String idAux = rs.getString("Id_Monitor");
                String nombre = rs.getString("Nombre");
                comboMonitores.addItem(nombre);
                monitoresMap.put(nombre, idAux);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los monitores:\n" + ex.getMessage(),
                    "Error BD",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            if (txtNombreClase.getText().trim().isEmpty() || comboMonitores.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, rellena todos los campos.",
                        "Campos vacíos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nombreClase = txtNombreClase.getText().trim();
            java.util.Date horaUtil = (java.util.Date) spinnerHorario.getValue();

            Calendar cal = Calendar.getInstance();
            cal.setTime(horaUtil);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            Time horaSql = new Time(cal.getTimeInMillis());

            int plazas = (int) spinnerPlazas.getValue();
            java.util.Date fechaUtil = (java.util.Date) spinnerFecha.getValue();
            Date fechaSql = new Date(fechaUtil.getTime());

            String monitorNombre = (String) comboMonitores.getSelectedItem();
            String idMonitor = monitoresMap.get(monitorNombre);

            // Comprobación de conflicto horario
            try (Connection con = (Connection) ConexionBD.getConexion();
                 PreparedStatement ps = (PreparedStatement) con.prepareStatement(
                     "SELECT COUNT(*) FROM clase WHERE Id_Monitor_Aux = ? AND Fecha = ? AND Duracion BETWEEN ? AND ?")) {

                Calendar calHora = Calendar.getInstance();
                calHora.setTime(horaSql);
                calHora.add(Calendar.HOUR_OF_DAY, -2);
                Time horaMin = new Time(calHora.getTimeInMillis());

                calHora.add(Calendar.HOUR_OF_DAY, 4); // Total +2 desde la hora original
                Time horaMax = new Time(calHora.getTimeInMillis());

                ps.setString(1, idMonitor);
                ps.setDate(2, fechaSql);
                ps.setTime(3, horaMin);
                ps.setTime(4, horaMax);

                ResultSet rs = ps.executeQuery();
                rs.next();
                if (rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this,
                            "Este monitor ya tiene una clase dentro de un intervalo de 2 horas.",
                            "Conflicto de horario",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al verificar el horario:\n" + ex.getMessage(),
                        "Error BD",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
                return;
            }

            // Insertar clase
            try (Connection con = (Connection) ConexionBD.getConexion();
                 PreparedStatement ps = (PreparedStatement) con.prepareStatement(
                         "INSERT INTO clase (Nombre, Duracion, Plazas, Fecha, Id_Monitor_Aux) VALUES (?, ?, ?, ?, ?)")) {

                ps.setString(1, nombreClase);
                ps.setTime(2, horaSql);
                ps.setInt(3, plazas);
                ps.setDate(4, fechaSql);
                ps.setString(5, idMonitor);

                int filas = ps.executeUpdate();

                if (filas > 0) {
                    JOptionPane.showMessageDialog(this,
                            "Clase añadida correctamente.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);

                    txtNombreClase.setText("");
                    spinnerHorario.setValue(new java.util.Date());
                    spinnerPlazas.setValue(1);
                    spinnerFecha.setValue(new java.util.Date());
                    comboMonitores.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Error al añadir la clase.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al conectar o ejecutar la consulta:\n" + ex.getMessage(),
                        "Error",
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
