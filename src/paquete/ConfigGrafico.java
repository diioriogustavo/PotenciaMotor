package paquete;

/**
 *
 * @author Usuario
 */
public class ConfigGrafico {

    private String etiquetas[] = {
        "ConfigGrafico NroMemoria",
        "Filtrado",
        "Grilla",
        "AsignacionGrilla",
        "RPMInicialPromedio",
        "RPMFinalPromedio",
        "HorizontalRef"};
    private String nroMemoria;
    private String filtrado;
    private String grilla;
    private String asignacionGrilla;
    private String rpmInicialPromedio;
    private String rpmFinalPromedio;
    private String horizontalRef;

    public String getEtiquetas(int pos) {
        return etiquetas[pos];
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getNroMemoria() {
        return nroMemoria;
    }

    public void setNroMemoria(String nroMemoria) {
        this.nroMemoria = nroMemoria;
    }

    public String getFiltrado() {
        return filtrado;
    }

    public void setFiltrado(String filtrado) {
        this.filtrado = filtrado;
    }

    public String getGrilla() {
        return grilla;
    }

    public void setGrilla(String grilla) {
        this.grilla = grilla;
    }

    public String getAsignacionGrilla() {
        return asignacionGrilla;
    }

    public void setAsignacionGrilla(String asignacionGrilla) {
        this.asignacionGrilla = asignacionGrilla;
    }

    public String getRpmInicialPromedio() {
        return rpmInicialPromedio;
    }

    public void setRpmInicialPromedio(String rpmInicialPromedio) {
        this.rpmInicialPromedio = rpmInicialPromedio;
    }

    public String getRpmFinalPromedio() {
        return rpmFinalPromedio;
    }

    public void setRpmFinalPromedio(String rpmFinalPromedio) {
        this.rpmFinalPromedio = rpmFinalPromedio;
    }

    public String getHorizontalRef() {
        return horizontalRef;
    }

    public void setHorizontalRef(String horizontalRef) {
        this.horizontalRef = horizontalRef;
    }
    
}
