package com.rmg.springbootwebflux.models.documents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthDataDto {

    private Sueno sueno;
    private List<Medicamento> medicamentos;
    private Presion presion;
    private Ejercicio ejercicio;
    private Meditacion meditacion;
    private String observacionesGenerales;

    public HealthDataDto() {}

    public Sueno getSueno() {
        return sueno;
    }

    public void setSueno(Sueno sueno) {
        this.sueno = sueno;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public Presion getPresion() {
        return presion;
    }

    public void setPresion(Presion presion) {
        this.presion = presion;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Meditacion getMeditacion() {
        return meditacion;
    }

    public void setMeditacion(Meditacion meditacion) {
        this.meditacion = meditacion;
    }

    public String getObservacionesGenerales() {
        return observacionesGenerales;
    }

    public void setObservacionesGenerales(String observacionesGenerales) {
        this.observacionesGenerales = observacionesGenerales;
    }

    // Clases internas

    public static class Sueno {
        private String total;
        private String profundo;

        public Sueno() {}

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getProfundo() {
            return profundo;
        }

        public void setProfundo(String profundo) {
            this.profundo = profundo;
        }
    }

    public static class Medicamento {
        private String nombre;
        private String dosis;

        public Medicamento() {}

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDosis() {
            return dosis;
        }

        public void setDosis(String dosis) {
            this.dosis = dosis;
        }
    }

    public static class Presion {
        private String sistolica;
        private String diastolica;

        public Presion() {}

        public String getSistolica() {
            return sistolica;
        }

        public void setSistolica(String sistolica) {
            this.sistolica = sistolica;
        }

        public String getDiastolica() {
            return diastolica;
        }

        public void setDiastolica(String diastolica) {
            this.diastolica = diastolica;
        }
    }

    public static class Ejercicio {
        private String distancia;
        private String frecuenciaCardiacaPromedio;

        public Ejercicio() {}

        public String getDistancia() {
            return distancia;
        }

        public void setDistancia(String distancia) {
            this.distancia = distancia;
        }

        public String getFrecuenciaCardiacaPromedio() {
            return frecuenciaCardiacaPromedio;
        }

        public void setFrecuenciaCardiacaPromedio(String frecuenciaCardiacaPromedio) {
            this.frecuenciaCardiacaPromedio = frecuenciaCardiacaPromedio;
        }
    }

    public static class Meditacion {
        private String duracion;

        public Meditacion() {}

        public String getDuracion() {
            return duracion;
        }

        public void setDuracion(String duracion) {
            this.duracion = duracion;
        }
    }
}
