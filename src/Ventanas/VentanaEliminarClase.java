package Ventanas;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import BaseDeDatos.ConexionBD;

public class VentanaEliminarClase extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombreClase;
    private JSpinner spinnerHorario;
    private JSpinner spinnerPlazas;
    private JButton btnEliminar, btnVolver;

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

        JLabel lblTitulo = new JLabel("ELIMINAR CLASE");
        lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(0, 0, 128));
        lblTitulo.setBounds(30, 5, 180, 30);
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

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnEliminar.setForeground(new Color(0, 128, 255));
        btnEliminar.setBackground(Color.WHITE);
        btnEliminar.setBounds(70, 210, 100, 30);
        btnEliminar.addActionListener(this);
        contentPane.add(btnEliminar);

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
        if (e.getSource() == btnEliminar) {
            String nombreClase = txtNombreClase.getText().trim();
            java.util.Date fechaUtil = (java.util.Date) spinnerHorario.getValue();
            java.sql.Time horaSql = new java.sql.Time(fechaUtil.getTime());
            int plazas = (int) spinnerPlazas.getValue();

            if (nombreClase.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Connection con = null;
            PreparedStatement ps = null;

            try {
                con = (Connection) ConexionBD.getConexion();
                String sql = "DELETE FROM clase WHERE Nombre = ? AND Duracion = ? AND Plazas = ?";
                ps = (PreparedStatement) con.prepareStatement(sql);
                ps.setString(1, nombreClase);
                ps.setTime(2, horaSql);
                ps.setInt(3, plazas);

                int filas = ps.executeUpdate();

                if (filas > 0) {
                    JOptionPane.showMessageDialog(this, "Clase eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    txtNombreClase.setText("");
                    spinnerHorario.setValue(new java.util.Date());
                    spinnerPlazas.setValue(1);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró una clase con esos datos.", "No encontrada", JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la clase:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
