package org.example.Interfaz;

import org.example.EstudiantesEntity;
import org.example.InscripcionesDAO.EstudiantesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EstudiantesGUI extends JFrame {
    private EstudiantesDAO estudiantesDAO;

    // Componentes de la UI
    private JButton addButton, updateButton, deleteButton, listButton;
    private JTextArea resultTextArea;

    public EstudiantesGUI(EstudiantesDAO estudiantesDAO) {
        this.estudiantesDAO = estudiantesDAO;

        // Configura la ventana
        setTitle("Gestión de Estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        addButton = new JButton("Agregar Estudiante");
        updateButton = new JButton("Actualizar Estudiante");
        deleteButton = new JButton("Eliminar Estudiante");
        listButton = new JButton("Listar Estudiantes");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);

        // Área de texto para resultados
        resultTextArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Agrega controladores de eventos para los botones
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Solicitar información del nuevo estudiante
                    String nombre = JOptionPane.showInputDialog("Nombre del estudiante:");
                    String apellido = JOptionPane.showInputDialog("Apellido del estudiante:");
                    String email = JOptionPane.showInputDialog("Correo electrónico del estudiante:");

                    // Validar que los campos no estén vacíos
                    if (nombre != null && !nombre.isEmpty() && apellido != null && !apellido.isEmpty() && email != null && !email.isEmpty()) {
                        // Crear una instancia de EstudiantesEntity con los datos proporcionados
                        EstudiantesEntity nuevoEstudiante = new EstudiantesEntity();
                        nuevoEstudiante.setNombre(nombre);
                        nuevoEstudiante.setApellido(apellido);
                        nuevoEstudiante.setEmail(email);

                        // Llamar al método del DAO para agregar el estudiante
                        estudiantesDAO.agregarEstudiante(nuevoEstudiante);

                        // Actualizar la lista de estudiantes en el área de texto
                        actualizarListaEstudiantes();
                        // Mostrar un mensaje de éxito
                        JOptionPane.showMessageDialog(null, "Estudiante agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    // Manejar errores
                    JOptionPane.showMessageDialog(null, "Error al agregar estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Solicitar el ID del estudiante a actualizar
                    int idEstudiante = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del estudiante a actualizar:"));

                    // Verificar si el estudiante existe
                    EstudiantesEntity estudianteExistente = estudiantesDAO.obtenerEstudiantePorId(idEstudiante);
                    if (estudianteExistente != null) {
                        // Solicitar y actualizar la información del estudiante
                        String nombre = JOptionPane.showInputDialog("Nuevo nombre del estudiante:", estudianteExistente.getNombre());
                        String apellido = JOptionPane.showInputDialog("Nuevo apellido del estudiante:", estudianteExistente.getApellido());
                        String email = JOptionPane.showInputDialog("Nuevo correo electrónico del estudiante:", estudianteExistente.getEmail());

                        // Actualizar la información del estudiante existente
                        estudianteExistente.setNombre(nombre);
                        estudianteExistente.setApellido(apellido);
                        estudianteExistente.setEmail(email);

                        // Llamar al método del DAO para actualizar el estudiante
                        estudiantesDAO.actualizarEstudiante(estudianteExistente);

                        // Actualizar la lista de estudiantes en el área de texto
                        actualizarListaEstudiantes();
                        // Mostrar un mensaje de éxito
                        JOptionPane.showMessageDialog(null, "Estudiante actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    // Manejar errores
                    JOptionPane.showMessageDialog(null, "Error al actualizar estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Solicitar el ID del estudiante a eliminar
                    int idEstudiante = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del estudiante a eliminar:"));

                    // Llamar al método del DAO para eliminar el estudiante
                    estudiantesDAO.eliminarEstudiante(idEstudiante);

                    // Actualizar la lista de estudiantes en el área de texto
                    actualizarListaEstudiantes();
                    // Mostrar un mensaje de éxito
                    JOptionPane.showMessageDialog(null, "Estudiante eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    // Manejar errores
                    JOptionPane.showMessageDialog(null, "Error al eliminar estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Actualizar la lista de estudiantes en el área de texto
                    actualizarListaEstudiantes();
                } catch (Exception ex) {
                    // Manejar errores
                    JOptionPane.showMessageDialog(null, "Error al listar estudiantes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void actualizarListaEstudiantes() {
        List<EstudiantesEntity> estudiantes = estudiantesDAO.obtenerTodosLosEstudiantes();

        // Mostrar la lista en el área de texto
        resultTextArea.setText(""); // Borrar el contenido anterior
        for (EstudiantesEntity estudiante : estudiantes) {
            resultTextArea.append("ID: " + estudiante.getIdEstudiante() + "\n");
            resultTextArea.append("Nombre: " + estudiante.getNombre() + "\n");
            resultTextArea.append("Apellido: " + estudiante.getApellido() + "\n");
            resultTextArea.append("Email: " + estudiante.getEmail() + "\n\n");
        }
    }


}
