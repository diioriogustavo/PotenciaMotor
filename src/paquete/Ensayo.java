package paquete;

/**
 *
 * @author Usuario
 */
public class Ensayo {

    private String etiquetas[] = {
        "NumeroDeCanales",
        "MuestrasTotales",
        "CanalRPM",
        "CanalTiempo",
        "TipoDeEnsayo"};
    private String numeroDeCanales;
    private String muestrasTotales;
    private String canalRPM;
    private String canalTiempo;
    private String tipoDeEnsayo;

    public String getEtiquetas(int pos) {
        return etiquetas[pos];
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getNumeroDeCanales() {
        return numeroDeCanales;
    }

    public void setNumeroDeCanales(String numeroDeCanales) {
        this.numeroDeCanales = numeroDeCanales;
    }

    public String getMuestrasTotales() {
        return muestrasTotales;
    }

    public void setMuestrasTotales(String muestrasTotales) {
        this.muestrasTotales = muestrasTotales;
    }

    public String getCanalRPM() {
        return canalRPM;
    }

    public void setCanalRPM(String canalRPM) {
        this.canalRPM = canalRPM;
    }

    public String getCanalTiempo() {
        return canalTiempo;
    }

    public void setCanalTiempo(String canalTiempo) {
        this.canalTiempo = canalTiempo;
    }

    public String getTipoDeEnsayo() {
        return tipoDeEnsayo;
    }

    public void setTipoDeEnsayo(String tipoDeEnsayo) {
        this.tipoDeEnsayo = tipoDeEnsayo;
    }

}
