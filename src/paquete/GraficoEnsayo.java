package paquete;

/**
 *
 * @author Usuario
 */
public class GraficoEnsayo {

    private String etiquetas[] = {
        "GraficoEnsayo NroEnsayo",
        "UltimoArchivo",
        "NumeroCanales"};
    private String nroEnsayo;
    private String numeroCanales;
    private String ultimoArchivo;

    public String getEtiquetas(int pos) {
        return etiquetas[pos];
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getNroEnsayo() {
        return nroEnsayo;
    }

    public void setNroEnsayo(String nroEnsayo) {
        this.nroEnsayo = nroEnsayo;
    }

    public String getNumeroCanales() {
        return numeroCanales;
    }

    public void setNumeroCanales(String numeroCanales) {
        this.numeroCanales = numeroCanales;
    }

    public String getUltimoArchivo() {
        return ultimoArchivo;
    }

    public void setUltimoArchivo(String ultimoArchivo) {
        this.ultimoArchivo = ultimoArchivo;
    }

    
    
}
