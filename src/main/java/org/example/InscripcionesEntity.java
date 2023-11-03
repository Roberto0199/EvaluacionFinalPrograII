package org.example;

import java.sql.Date;

public class InscripcionesEntity {
    private int idInscripcion;
    private Integer idEstudiante;
    private Integer idCurso;
    private Date fechaInscripcion;

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InscripcionesEntity that = (InscripcionesEntity) o;

        if (idInscripcion != that.idInscripcion) return false;
        if (idEstudiante != null ? !idEstudiante.equals(that.idEstudiante) : that.idEstudiante != null) return false;
        if (idCurso != null ? !idCurso.equals(that.idCurso) : that.idCurso != null) return false;
        if (fechaInscripcion != null ? !fechaInscripcion.equals(that.fechaInscripcion) : that.fechaInscripcion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idInscripcion;
        result = 31 * result + (idEstudiante != null ? idEstudiante.hashCode() : 0);
        result = 31 * result + (idCurso != null ? idCurso.hashCode() : 0);
        result = 31 * result + (fechaInscripcion != null ? fechaInscripcion.hashCode() : 0);
        return result;
    }
}
