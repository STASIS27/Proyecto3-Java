package Ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BaseDeDatos.ConexionBD;

public class VentanaAñadirClase extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombreClase;
    private JTextField txtHorario;
    private JSpinner spinnerPlazas; 
    private JButton btnGuardar;
    private JButton btnVolver;
    private JSpinner spinnerHorario;
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
        setBounds(100, 100, 400, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(null);
        panelTitulo.setBorder(new LineBorder(Color.BLACK, 2, true));
        panelTitulo.setBounds(100, 10, 200, 40);
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

        // Spinner para la hora
        spinnerHorario = new JSpinner(new SpinnerDateModel());
        spinnerHorario.setEditor(new JSpinner.DateEditor(spinnerHorario, "HH:mm"));
        spinnerHorario.setBounds(190, 110, 140, 22);
        contentPane.add(spinnerHorario);

        JLabel lblMonitor = new JLabel("Plazas:");
        lblMonitor.setForeground(Color.WHITE);
        lblMonitor.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        lblMonitor.setBounds(50, 150, 140, 20);
        contentPane.add(lblMonitor);

        spinnerPlazas = new JSpinner();
        spinnerPlazas.setModel(new SpinnerNumberModel(1, 1, 100, 1)); 
        spinnerPlazas.setBounds(190, 150, 140, 22);
        contentPane.add(spinnerPlazas);
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnGuardar.setForeground(new Color(0, 128, 255));
        btnGuardar.setBackground(Color.WHITE);
        btnGuardar.setBounds(70, 210, 100, 30);
        btnGuardar.addActionListener(this);
        contentPane.add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnVolver.setForeground(new Color(0, 128, 255));
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setBounds(210, 210, 100, 30);
        btnVolver.addActionListener(this);
        contentPane.add(btnVolver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            if (txtNombreClase.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor, rellena todos los campos.",
                    "Campos vacíos",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nombreClase = txtNombreClase.getText().trim();
            java.util.Date fechaUtil = (java.util.Date) spinnerHorario.getValue();
            java.sql.Time horaSql = new java.sql.Time(fechaUtil.getTime());
            int plazas = (int) spinnerPlazas.getValue();

            Connection con = null;
            PreparedStatement ps = null;

            try {
                con = (Connection) ConexionBD.getConexion();
                String sql = "INSERT INTO clase (Nombre, Duracion, Plazas) VALUES (?, ?, ?)";
                ps = (PreparedStatement) con.prepareStatement(sql);
                ps.setString(1, nombreClase);
                ps.setTime(2, horaSql);
                ps.setInt(3, plazas);

                int filas = ps.executeUpdate();

                if (filas > 0) {
                    JOptionPane.showMessageDialog(this,
                        "Clase añadida correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                    // Limpiar campos tras guardar
                    txtNombreClase.setText("");
                    spinnerHorario.setValue(new java.util.Date());
                    spinnerPlazas.setValue(1);
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
            } finally {
                try {
                    if (ps != null) ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (e.getSource() == btnVolver) {
            VentanaMenuMonitores vm = new VentanaMenuMonitores();
            vm.setVisible(true);
            dispose();
        }
    }
}
