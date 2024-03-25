package paquete;

/**
 *
 * @author Usuario
 */
public class CanalReal {

    private String etiquetas[] = {
        "NroCanal",
        "CalibracionA",
        "CalibracionB",
        "CalibracionRefInicial",
        "CalibracionRefFinal",
        "Muestras",
        "FuncionDelCanal"};
    private String numeroCanal;
    private String calibracionA;
    private String calibracionB;
    private String calibracionRefInicial;
    private String calibracionRefFinal;
    private String funcionDelCanal;
    private String muestra;

    public String getNumeroCanal() {
        return numeroCanal;
    }

    public void setNumeroCanal(String numeroCanal) {
        this.numeroCanal = numeroCanal;
    }

    public String getEtiquetas(int pos) {
        return etiquetas[pos];
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getCalibracionA() {
        return calibracionA;
    }

    public void setCalibracionA(String calibracionA) {
        this.calibracionA = calibracionA;
    }

    public String getCalibracionB() {
        return calibracionB;
    }

    public void setCalibracionB(String calibracionB) {
        this.calibracionB = calibracionB;
    }

    public String getCalibracionRefInicial() {
        return calibracionRefInicial;
    }

    public void setCalibracionRefInicial(String calibracionRefInicial) {
        this.calibracionRefInicial = calibracionRefInicial;
    }

    public String getCalibracionRefFinal() {
        return calibracionRefFinal;
    }

    public void setCalibracionRefFinal(String calibracionRefFinal) {
        this.calibracionRefFinal = calibracionRefFinal;
    }

    public String getFuncionDelCanal() {
        return funcionDelCanal;
    }

    public void setFuncionDelCanal(String funcionDelCanal) {
        this.funcionDelCanal = funcionDelCanal;
    }

    public String getMuestra() {
        return muestra;
    }

    public void setMuestra(String muestra) {
        this.muestra = muestra;
    }

    
}
