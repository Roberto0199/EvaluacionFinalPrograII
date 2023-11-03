package org.example;

import org.example.InscripcionesDAO.CursosDAO;
import org.example.InscripcionesDAO.EstudiantesDAO;
import org.example.InscripcionesDAO.InscripcionesDAO;
import org.example.Interfaz.CursosGUI;
import org.example.Interfaz.EstudiantesGUI;
import org.example.Interfaz.InscripcionesGUI;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        // Crear la instancia de SessionFactory
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        // Asegurarse de que la sesión se abrió correctamente
        if (sessionFactory != null) {
            System.out.println("SessionFactory creada con éxito");

            // Crear instancias de los DAO y pasa estas instancias al constructor de MainGUI
            EstudiantesDAO estudiantesDAO = new EstudiantesDAO(sessionFactory);

            // Crear una instancia de MainGUI
            SwingUtilities.invokeLater(() -> {
                EstudiantesGUI estudiantesGUI= new EstudiantesGUI(estudiantesDAO);
                estudiantesGUI.setVisible(true);
            });

            SwingUtilities.invokeLater(() -> {
                CursosDAO cursosDAO = new CursosDAO(sessionFactory);
                CursosGUI cursosGUI = new CursosGUI(cursosDAO);
                cursosGUI.setVisible(true);
            });

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        InscripcionesDAO inscripcionesDAO = new InscripcionesDAO(sessionFactory);
                        new InscripcionesGUI(inscripcionesDAO);
                    }
                });

        } else {
            System.out.println("Error creando SessionFactory");
        }
    }
}


