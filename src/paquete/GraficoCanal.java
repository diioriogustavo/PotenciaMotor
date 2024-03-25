package paquete;

/**
 *
 * @author Usuario
 */
public class GraficoCanal {

    private String etiquetas[] = {
        "GraficoCanal NroCanal",
        "EjeY",
        "Color",
        "TipoDeLinea",
        "Grosor"};
    private String nroCanal;
    private String ejeY;
    private String color;
    private String tipoDeLinea;
    private String grosor;

    public String getEtiquetas(int pos) {
        return etiquetas[pos];
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getNroCanal() {
        return nroCanal;
    }

    public void setNroCanal(String nroCanal) {
        this.nroCanal = nroCanal;
    }

    public String getEjeY() {
        return ejeY;
    }

    public void setEjeY(String ejeY) {
        this.ejeY = ejeY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoDeLinea() {
        return tipoDeLinea;
    }

    public void setTipoDeLinea(String tipoDeLinea) {
        this.tipoDeLinea = tipoDeLinea;
    }

    public String getGrosor() {
        return grosor;
    }

    public void setGrosor(String grosor) {
        this.grosor = grosor;
    }

}
