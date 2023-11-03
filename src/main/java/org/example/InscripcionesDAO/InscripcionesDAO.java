package org.example.InscripcionesDAO;

import org.example.InscripcionesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class InscripcionesDAO {
    private final SessionFactory sessionFactory;

    public InscripcionesDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void agregarInscripcion(InscripcionesEntity inscripcion) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(inscripcion);
            transaction.commit();
        }
    }

    public void actualizarInscripcion(InscripcionesEntity inscripcion) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(inscripcion);
            transaction.commit();
        }
    }

    public void eliminarInscripcion(int idInscripcion) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            InscripcionesEntity inscripcion = session.get(InscripcionesEntity.class, idInscripcion);
            if (inscripcion != null) {
                session.delete(inscripcion);
            }
            transaction.commit();
        }
    }

    public InscripcionesEntity obtenerInscripcionPorId(int idInscripcion) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(InscripcionesEntity.class, idInscripcion);
        }
    }

    public List<InscripcionesEntity> obtenerTodasLasInscripciones() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from InscripcionesEntity", InscripcionesEntity.class).list();
        }
    }
}