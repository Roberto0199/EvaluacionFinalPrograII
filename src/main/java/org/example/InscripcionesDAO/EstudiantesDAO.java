package org.example.InscripcionesDAO;

import org.example.EstudiantesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class EstudiantesDAO {
    private final SessionFactory sessionFactory;

    public EstudiantesDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void agregarEstudiante(EstudiantesEntity estudiante) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(estudiante);
            transaction.commit();
        }
    }

    public void actualizarEstudiante(EstudiantesEntity estudiante) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(estudiante);
            transaction.commit();
        }
    }

    public void eliminarEstudiante(int idEstudiante) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            EstudiantesEntity estudiante = session.get(EstudiantesEntity.class, idEstudiante);
            if (estudiante != null) {
                session.delete(estudiante);
            }
            transaction.commit();
        }
    }

    public EstudiantesEntity obtenerEstudiantePorId(int idEstudiante) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(EstudiantesEntity.class, idEstudiante);
        }
    }

    public List<EstudiantesEntity> obtenerTodosLosEstudiantes() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from EstudiantesEntity", EstudiantesEntity.class).list();
        }
    }
}
