package Ventanas;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.border.LineBorder;

public class VentanaMenuUsuarios extends JFrame implements ActionListener {
    private String correoUsuario;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnVerClasesDisponibles, btnVerMisClases, btnVolver;
    private JPanel panelTitulo;
    private JLabel lblMenuUsuario;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaMenuUsuarios frame = new VentanaMenuUsuarios("");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaMenuUsuarios(String correoUsuario) {
          // Aquí el usuario logueado o pasado al constructor
        this.correoUsuario = correoUsuario;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 250);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnVerClasesDisponibles = new JButton("Ver Clases Disponibles");
        btnVerClasesDisponibles.setBounds(100, 70, 200, 30);
        btnVerClasesDisponibles.setBackground(new Color(0, 128, 255));
        btnVerClasesDisponibles.setForeground(Color.WHITE);
        btnVerClasesDisponibles.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnVerClasesDisponibles.addActionListener(this);
        contentPane.add(btnVerClasesDisponibles);

        btnVerMisClases = new JButton("Ver Mis Clases");
        btnVerMisClases.setBounds(100, 110, 200, 30);
        btnVerMisClases.setBackground(new Color(0, 128, 255));
        btnVerMisClases.setForeground(Color.WHITE);
        btnVerMisClases.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnVerMisClases.addActionListener(this);
        contentPane.add(btnVerMisClases);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(150, 160, 100, 30);
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setForeground(new Color(0, 128, 255));
        btnVolver.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnVolver.addActionListener(this);
        contentPane.add(btnVolver);
        
        panelTitulo = new JPanel();
        panelTitulo.setLayout(null);
        panelTitulo.setBorder(new LineBorder(Color.BLACK, 2, true));
        panelTitulo.setBounds(81, 10, 238, 40);
        contentPane.add(panelTitulo);
        
        lblMenuUsuario = new JLabel("MENU USUARIO");
        lblMenuUsuario.setForeground(new Color(0, 0, 128));
        lblMenuUsuario.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
        lblMenuUsuario.setBounds(50, 5, 150, 30);
        panelTitulo.add(lblMenuUsuario);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnVolver)) {
           VentanapPrincipal vp = new VentanapPrincipal();
           vp.setVisible(true);
           dispose();
        }

        if (e.getSource().equals(btnVerClasesDisponibles)) {
            if (hayClasesDisponibles()) {
                // Aquí lanzar ventana para ver clases disponibles
              VentanaUsuarios vu = new VentanaUsuarios(correoUsuario);
              vu.setVisible(true);
              dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No hay clases disponibles en este momento.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getSource().equals(btnVerMisClases)) {
            if (tieneClasesApuntado()) {
                // Aquí lanzar ventana para ver clases en las que el usuario está apuntado
            	  VentanaVerClasesUsuario vvcu = new VentanaVerClasesUsuario(correoUsuario);
                  vvcu.setVisible(true);
                  dispose();
              
            } else {
                JOptionPane.showMessageDialog(this, "No estás apuntado a ninguna clase.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private boolean hayClasesDisponibles() {
        boolean hay = false;
        String sql = "SELECT COUNT(*) FROM clase"; // Ajusta el nombre y estructura de la tabla según tu BD
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/dam-fit", "root", "");
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                hay = rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return hay;
    }

    private boolean tieneClasesApuntado() {
        boolean tiene = false;
        // Ejemplo con tabla de relación usuario_clase, donde usuarioId es el identificador del usuario
        String sql = "SELECT COUNT(*) FROM `cliente-clase` cc " +
                "JOIN cliente c ON cc.Num_Socio_Aux = c.Num_Socio " +
                "JOIN persona p ON c.Id_Persona_Aux = p.Id_Persona " +
                "WHERE p.Correo = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/dam-fit", "root", "");
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, correoUsuario);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                tiene = rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return tiene;
    }
}
