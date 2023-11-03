package org.example;

public class CursosEntity {
    private int idCurso;
    private String nombreCurso;
    private String profesor;

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CursosEntity that = (CursosEntity) o;

        if (idCurso != that.idCurso) return false;
        if (nombreCurso != null ? !nombreCurso.equals(that.nombreCurso) : that.nombreCurso != null) return false;
        if (profesor != null ? !profesor.equals(that.profesor) : that.profesor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCurso;
        result = 31 * result + (nombreCurso != null ? nombreCurso.hashCode() : 0);
        result = 31 * result + (profesor != null ? profesor.hashCode() : 0);
        return result;
    }
}
