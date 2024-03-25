
package paquete;

/**
 *
 * @author Usuario
 */
public class Indicador {
    private String etiquetas[] = {
        "Indicador NroIndicador",
        "ColorFondoSeccion_0",
        "ColorTextoSeccion_0",
        "ColorFondoSeccion_1",
        "ColorTextoSeccion_1",
        "ColorFondoSeccion_2",
        "ColorTextoSeccion_2",
        "CanalAsignado"};
    private String nroIndicador;
    private String colorFondoSeccion_0;
    private String colorTextoSeccion_0;
    private String colorFondoSeccion_1;
    private String colorTextoSeccion_1;
    private String colorFondoSeccion_2;
    private String colorTextoSeccion_2;
    private String canalAsignado;

    public String getEtiquetas(int pos) {
        return etiquetas[pos];
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getNroIndicador() {
        return nroIndicador;
    }

    public void setNroIndicador(String nroIndicador) {
        this.nroIndicador = nroIndicador;
    }

    public String getColorFondoSeccion_0() {
        return colorFondoSeccion_0;
    }

    public void setColorFondoSeccion_0(String colorFondoSeccion_0) {
        this.colorFondoSeccion_0 = colorFondoSeccion_0;
    }

    public String getColorTextoSeccion_0() {
        return colorTextoSeccion_0;
    }

    public void setColorTextoSeccion_0(String colorTextoSeccion_0) {
        this.colorTextoSeccion_0 = colorTextoSeccion_0;
    }

    public String getColorFondoSeccion_1() {
        return colorFondoSeccion_1;
    }

    public void setColorFondoSeccion_1(String colorFondoSeccion_1) {
        this.colorFondoSeccion_1 = colorFondoSeccion_1;
    }

    public String getColorTextoSeccion_1() {
        return colorTextoSeccion_1;
    }

    public void setColorTextoSeccion_1(String colorTextoSeccion_1) {
        this.colorTextoSeccion_1 = colorTextoSeccion_1;
    }

    public String getColorFondoSeccion_2() {
        return colorFondoSeccion_2;
    }

    public void setColorFondoSeccion_2(String colorFondoSeccion_2) {
        this.colorFondoSeccion_2 = colorFondoSeccion_2;
    }

    public String getColorTextoSeccion_2() {
        return colorTextoSeccion_2;
    }

    public void setColorTextoSeccion_2(String colorTextoSeccion_2) {
        this.colorTextoSeccion_2 = colorTextoSeccion_2;
    }

    public String getCanalAsignado() {
        return canalAsignado;
    }

    public void setCanalAsignado(String canalAsignado) {
        this.canalAsignado = canalAsignado;
    }

}
