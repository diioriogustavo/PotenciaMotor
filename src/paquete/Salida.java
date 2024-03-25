
package paquete;

/**
 *
 * @author Usuario
 */
public class Salida {

    private String etiquetas[] = {
        "Salida NroSalida",
        "CalibracionA",
        "CalibracionB",
        "CalibracionRefInicial",
        "CalibracionRefFinal",
        "CanalAsignado"};
    private String nroSalida;
    private String calibracionA;
    private String calibracionB;
    private String calibracionRefInicial;
    private String calibracionRefFinal;
    private String canalAsignado;

    public String getEtiquetas(int pos) {
        return etiquetas[pos];
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getNroSalida() {
        return nroSalida;
    }

    public void setNroSalida(String nroSalida) {
        this.nroSalida = nroSalida;
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

    public String getCanalAsignado() {
        return canalAsignado;
    }

    public void setCanalAsignado(String canalAsignado) {
        this.canalAsignado = canalAsignado;
    }

}
