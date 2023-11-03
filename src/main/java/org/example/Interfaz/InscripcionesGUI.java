package org.example.Interfaz;

import org.example.InscripcionesDAO.InscripcionesDAO;
import org.example.InscripcionesEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.Date;


public class InscripcionesGUI {
    private InscripcionesDAO inscripcionesDAO;

    private JFrame frame;
    private JButton addButton, updateButton, deleteButton, viewButton;
    private JTextArea outputTextArea;

    public InscripcionesGUI(InscripcionesDAO inscripcionesDAO) {
        this.inscripcionesDAO = inscripcionesDAO;

        frame = new JFrame("Gestión de Inscripciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        addButton = new JButton("Agregar Inscripción");
        updateButton = new JButton("Actualizar Inscripción");
        deleteButton = new JButton("Eliminar Inscripción");
        viewButton = new JButton("Ver Inscripciones");

        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Solicitar información para una nueva inscripción
                    String estudianteIdStr = JOptionPane.showInputDialog("ID del Estudiante:");
                    String cursoIdStr = JOptionPane.showInputDialog("ID del Curso:");
                    String fechaInscripcionStr = JOptionPane.showInputDialog("Fecha de Inscripción (AAAA-MM-DD):");

                    if (estudianteIdStr != null && cursoIdStr != null && fechaInscripcionStr != null) {
                        // Convertir las cadenas en los tipos de datos necesarios
                        int estudianteId = Integer.parseInt(estudianteIdStr);
                        int cursoId = Integer.parseInt(cursoIdStr);
                        Date fechaInscripcion = Date.valueOf(fechaInscripcionStr);

                        // Crear una nueva instancia de InscripcionesEntity
                        InscripcionesEntity nuevaInscripcion = new InscripcionesEntity();
                        nuevaInscripcion.setIdEstudiante(estudianteId);
                        nuevaInscripcion.setIdCurso(cursoId);
                        nuevaInscripcion.setFechaInscripcion(fechaInscripcion);

                        // Llamar al método del DAO para agregar la inscripción
                        inscripcionesDAO.agregarInscripcion(nuevaInscripcion);

                        // Actualizar la lista de inscripciones en la interfaz gráfica si es necesario
                        // (depende de tu implementación)
                        // Por ejemplo, puedes llamar a una función que refresque la lista de inscripciones en tu ventana.

                        // Mostrar un mensaje de éxito
                        JOptionPane.showMessageDialog(frame, "Inscripción agregada con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    // Manejar errores
                    JOptionPane.showMessageDialog(frame, "Error al agregar inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agregar la lógica para actualizar una inscripción aquí
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agregar la lógica para eliminar una inscripción aquí
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<InscripcionesEntity> inscripciones = inscripcionesDAO.obtenerTodasLasInscripciones();
                outputTextArea.setText("");
                for (InscripcionesEntity inscripcion : inscripciones) {
                    outputTextArea.append(inscripcion.toString() + "\n");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }


}

