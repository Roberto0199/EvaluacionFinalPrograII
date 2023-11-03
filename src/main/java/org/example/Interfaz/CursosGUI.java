package org.example.Interfaz;

import org.example.CursosEntity;
import org.example.InscripcionesDAO.CursosDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CursosGUI extends JFrame {
    private CursosDAO cursosDAO;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton listButton;
    private JTextArea resultTextArea;

    public CursosGUI(CursosDAO cursosDAO) {
        this.cursosDAO = cursosDAO;

        setTitle("Gestión de Cursos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        addButton = new JButton("Agregar Curso");
        updateButton = new JButton("Actualizar Curso");
        deleteButton = new JButton("Eliminar Curso");
        listButton = new JButton("Listar Cursos");
        resultTextArea = new JTextArea(10, 40);

        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(listButton);
        add(resultTextArea);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCurso();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCurso();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCurso();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCursos();
            }
        });
    }

    private void agregarCurso() {
        String nombreCurso = JOptionPane.showInputDialog("Nombre del curso:");
        String profesor = JOptionPane.showInputDialog("Nombre del profesor:");

        if (nombreCurso != null && !nombreCurso.isEmpty() && profesor != null && !profesor.isEmpty()) {
            CursosEntity nuevoCurso = new CursosEntity();
            nuevoCurso.setNombreCurso(nombreCurso);
            nuevoCurso.setProfesor(profesor);

            cursosDAO.agregarCurso(nuevoCurso);
            actualizarListaCursos();
            JOptionPane.showMessageDialog(this, "Curso agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCurso() {
        int idCurso = obtenerIdCurso();

        if (idCurso > 0) {
            CursosEntity cursoExistente = cursosDAO.obtenerCursoPorId(idCurso);
            if (cursoExistente != null) {
                String nombreCurso = JOptionPane.showInputDialog("Nuevo nombre del curso:", cursoExistente.getNombreCurso());
                String profesor = JOptionPane.showInputDialog("Nuevo profesor:", cursoExistente.getProfesor());

                cursoExistente.setNombreCurso(nombreCurso);
                cursoExistente.setProfesor(profesor);

                cursosDAO.actualizarCurso(cursoExistente);
                actualizarListaCursos();
                JOptionPane.showMessageDialog(this, "Curso actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Curso no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarCurso() {
        int idCurso = obtenerIdCurso();

        if (idCurso > 0) {
            cursosDAO.eliminarCurso(idCurso);
            actualizarListaCursos();
            JOptionPane.showMessageDialog(this, "Curso eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private int obtenerIdCurso() {
        String idCursoStr = JOptionPane.showInputDialog("ID del curso a actualizar/eliminar:");
        if (idCursoStr != null && !idCursoStr.isEmpty()) {
            try {
                return Integer.parseInt(idCursoStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID de curso no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return -1;
    }

    private void listarCursos() {
        actualizarListaCursos();
    }

    private void actualizarListaCursos() {
        resultTextArea.setText("");
        List<CursosEntity> cursos = cursosDAO.obtenerTodosLosCursos();

        for (CursosEntity curso : cursos) {
            resultTextArea.append("ID: " + curso.getIdCurso() + "\n");
            resultTextArea.append("Nombre del Curso: " + curso.getNombreCurso() + "\n");
            resultTextArea.append("Profesor: " + curso.getProfesor() + "\n\n");
        }
    }
}