package paquete;

/**
 *
 * @author Usuario
 */
public class CanalVirtual {

    private String etiquetas[] = {
        "NroCanal",
        "Unidad",
        "Nombre",
        "FinSeccion1",
        "FinSeccion2",
        "Promedio",
        "IndiceMax",
        "IndiceMin",
        "LugaresDecimales",
        "AsignacionDelCanal",
        "CanalAsignado",
        "Muestra",
        "CanalVirtual"};
    private String numeroCanal;
    private String unidad;
    private String nombre;
    private String finSeccion1;
    private String finSeccion2;
    private String promedio;
    private String indiceMax;
    private String indiceMin;
    private String lugaresDecimales;
    private String asignacionDelCanal;
    private String canalAsignado;
    private String muestra;
    private String nMuestras;

    public String getnMuestras() {
        return nMuestras;
    }

    public void setnMuestras(String nMuestras) {
        this.nMuestras = nMuestras;
    }

    public String getEtiquetas(int pos) {
        return etiquetas[pos];
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getNumeroCanal() {
        return numeroCanal;
    }

    public void setNumeroCanal(String numeroCanal) {
        this.numeroCanal = numeroCanal;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFinSeccion1() {
        return finSeccion1;
    }

    public void setFinSeccion1(String finSeccion1) {
        this.finSeccion1 = finSeccion1;
    }

    public String getFinSeccion2() {
        return finSeccion2;
    }

    public void setFinSeccion2(String finSeccion2) {
        this.finSeccion2 = finSeccion2;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getIndiceMax() {
        return indiceMax;
    }

    public void setIndiceMax(String indiceMax) {
        this.indiceMax = indiceMax;
    }

    public String getIndiceMin() {
        return indiceMin;
    }

    public void setIndiceMin(String indiceMin) {
        this.indiceMin = indiceMin;
    }

    public String getLugaresDecimales() {
        return lugaresDecimales;
    }

    public void setLugaresDecimales(String lugaresDecimales) {
        this.lugaresDecimales = lugaresDecimales;
    }

    public String getAsignacionDelCanal() {
        return asignacionDelCanal;
    }

    public void setAsignacionDelCanal(String asignacionDelCanal) {
        this.asignacionDelCanal = asignacionDelCanal;
    }

    public String getCanalAsignado() {
        return canalAsignado;
    }

    public void setCanalAsignado(String canalAsignado) {
        this.canalAsignado = canalAsignado;
    }

    public String getMuestra() {
        return muestra;
    }

    public void setMuestra(String muestra) {
        this.muestra = muestra;
    }

}
