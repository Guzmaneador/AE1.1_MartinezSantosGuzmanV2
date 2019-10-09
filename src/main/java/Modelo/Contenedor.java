package Modelo;

/**
 *
 * @author Vulture
 */
public class Contenedor {
    float codcalle;
    String texto;
    long capacidad;
    long utm_x;
    long utm_y;
    String nota;
    String tipo;

    public Contenedor(float codcalle, String texto, long capacidad, long utm_x, long utm_y, String nota, String tipo) {
        this.codcalle = codcalle;
        this.texto = texto;
        this.capacidad = capacidad;
        this.utm_x = utm_x;
        this.utm_y = utm_y;
        this.nota = nota;
        this.tipo = tipo;
    }

    public float getCodcalle() {
        return codcalle;
    }

    public void setCodcalle(float codcalle) {
        this.codcalle = codcalle;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public long getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(long capacidad) {
        this.capacidad = capacidad;
    }

    public long getUtm_x() {
        return utm_x;
    }

    public void setUtm_x(long utm_x) {
        this.utm_x = utm_x;
    }

    public long getUtm_y() {
        return utm_y;
    }

    public void setUtm_y(long utm_y) {
        this.utm_y = utm_y;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
