package org.example.InscripcionesDAO;

import org.example.CursosEntity;
import org.example.EstudiantesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CursosDAO {
    private SessionFactory sessionFactory;

    public CursosDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void agregarCurso(CursosEntity curso) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(curso);
            transaction.commit();
        }
    }

    public void actualizarCurso(CursosEntity curso) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(curso);
            transaction.commit();
        }
    }

    public void eliminarCurso(int idCurso) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            CursosEntity curso = session.get(CursosEntity.class, idCurso);
            if (curso != null) {
                session.delete(curso);
                transaction.commit();
            }
        }
    }
    public List<CursosEntity> obtenerTodosLosCursos() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CursosEntity ", CursosEntity.class).list();
        }
    }

    public CursosEntity obtenerCursoPorId(int idCurso) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CursosEntity.class, idCurso);
        }
    }
}

