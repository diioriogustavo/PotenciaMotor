package paquete;

/**
 *
 * @author Usuario
 */
public class ConfigEjeY {

    private String etiquetas[] = {
        "ConfigEjeY NroEjeY",
        "TipoEscala",
        "DeltaYMinor",
        "ScaleYMin",
        "ScaleYMax"};
    private String nroEjeY;
    private String tipoEscala;
    private String deltaYMinor;
    private String scaleYMin;
    private String scaleYMax;

    public String getEtiquetas(int pos) {
        return etiquetas[pos];
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getNroEjeY() {
        return nroEjeY;
    }

    public void setNroEjeY(String nroEjeY) {
        this.nroEjeY = nroEjeY;
    }

    public String getTipoEscala() {
        return tipoEscala;
    }

    public void setTipoEscala(String tipoEscala) {
        this.tipoEscala = tipoEscala;
    }

    public String getDeltaYMinor() {
        return deltaYMinor;
    }

    public void setDeltaYMinor(String deltaYMinor) {
        this.deltaYMinor = deltaYMinor;
    }

    public String getScaleYMin() {
        return scaleYMin;
    }

    public void setScaleYMin(String scaleYMin) {
        this.scaleYMin = scaleYMin;
    }

    public String getScaleYMax() {
        return scaleYMax;
    }

    public void setScaleYMax(String scaleYMax) {
        this.scaleYMax = scaleYMax;
    }

}
