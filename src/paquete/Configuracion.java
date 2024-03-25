package paquete;

/**
 *
 * @author Usuario
 */
public class Configuracion {

    private String etiquetas[] = {
        "EsPorPuntos",
        "FactorInercia",
        "limitRPMMax",
        "LongitudPalanca",
        "NumeroDeVariablesDePanel",
        "PeriodoRegistro",
        "Puerto",
        "PeriodoAdquisicion",
        "FiltroInercia",
        "NumeroDeCanales",
        "ArchivoLogotipo",
        "TipoDeSondaLambda",
        "ValorLambdaAMedir",
        "TipoDeCombustible",
        "CCodTipo",
        "RelacionCaja",
        "UsarPickup",
        "PPVA",
        "PPVB",
        "UltimaRPMCaja",
        "MomentoInercia",
        "DiametroRodillo",
        "LimitadorMax",
        "LimitadorUmbral",
        "HabilitarLimitador",
        "UnidadPotencia",
        "UnidadTorque",
        "FuzzyRPMIni",
        "FuzzyRPMFin",
        "FuzzyFacIni",
        "FuzzyFacFin",
        "NoConPerdidas",
        "SecuenciaPulsador",
        "RelacionPrimaria",
        "DientesPinon",
        "DientesCorona",
        "Alarma1",
        "Alarma2",
        "Alarma3",
        "TipoAlarma1",
        "TipoAlarma2",
        "TipoAlarma3",
        "HisteresisAlarma",
        "AlarmaOn",
        "UsoDeCanales_1",
        "UsoDeCanales_2",
        "UsoDeCanales_3",
        "UsoDeCanales_4",
        "UsoDeCanales_5",
        "UsoDeCanales_6",
        "UsoDeCanales_7",
        "UsoDeCanales_8",
        "UsoDeCanales_9",
        "UsoDeCanales_10",
        "UsoDeCanales_11",
        "UsoDeCanales_12",
        "UsoDeCanales_13",
        "UsoDeCanales_14",
        "UsoDeCanales_15",
        "UsoDeCanales_16"};
    private String esPorPuntos;
    private String factorInercia;
    private String limitRPMMax;
    private String longitudPalanca;
    private String numeroDeVariablesDePanel;
    private String periodoRegistro;
    private String puerto;
    private String periodoAdquisicion;
    private String filtroInercial;
    private String numeroDeCanales;
    private String tipoDeSondaLambda;
    private String valorLambdaAMedir;
    private String tipoDeCombustible;
    private String cCodTipo;
    private String relacionCaja;
    private String usarPickup;
    private String pPVA;
    private String pPVB;
    private String ultimaRPMCaja;
    private String momentoInercia;
    private String diametroRodillo;
    private String limitadorMax;
    private String limitadorUmbral;
    private String habilitarLimitador;
    private String unidadPotencia;
    private String unidadTorque;
    private String fuzzyRPMIni;
    private String fuzzyRPMFin;
    private String fuzzyFacIni;
    private String fuzzyFacFin;
    private String noConPerdidas;
    private String secuenciaPulsador;
    private String relacionPrimaria;
    private String dientesPinon;
    private String dientesCorona;
    private String alarma1;
    private String alarma2;
    private String alarma3;
    private String tipoAlarma1;
    private String tipoAlarma2;
    private String tipoAlarma3;
    private String histeresisAlarma;
    private String alarmaOn;
    private String usoDeCanales_1;
    private String usoDeCanales_2;
    private String usoDeCanales_3;
    private String usoDeCanales_4;
    private String usoDeCanales_5;
    private String usoDeCanales_6;
    private String usoDeCanales_7;
    private String usoDeCanales_8;
    private String usoDeCanales_9;
    private String usoDeCanales_10;
    private String usoDeCanales_11;
    private String usoDeCanales_12;
    private String usoDeCanales_13;
    private String usoDeCanales_14;
    private String usoDeCanales_15;
    private String usoDeCanales_16;

    public String getEtiquetas(int pos) {
        return etiquetas[pos];
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getEsPorPuntos() {
        return esPorPuntos;
    }

    public void setEsPorPuntos(String esPorPuntos) {
        this.esPorPuntos = esPorPuntos;
    }

    public String getFactorInercia() {
        return factorInercia;
    }

    public void setFactorInercia(String factorInercia) {
        this.factorInercia = factorInercia;
    }

    public String getLimitRPMMax() {
        return limitRPMMax;
    }

    public void setLimitRPMMax(String limitRPMMax) {
        this.limitRPMMax = limitRPMMax;
    }

    public String getLongitudPalanca() {
        return longitudPalanca;
    }

    public void setLongitudPalanca(String longitudPalanca) {
        this.longitudPalanca = longitudPalanca;
    }

    public String getNumeroDeVariablesDePanel() {
        return numeroDeVariablesDePanel;
    }

    public void setNumeroDeVariablesDePanel(String numeroDeVariablesDePanel) {
        this.numeroDeVariablesDePanel = numeroDeVariablesDePanel;
    }

    public String getPeriodoRegistro() {
        return periodoRegistro;
    }

    public void setPeriodoRegistro(String periodoRegistro) {
        this.periodoRegistro = periodoRegistro;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getPeriodoAdquisicion() {
        return periodoAdquisicion;
    }

    public void setPeriodoAdquisicion(String periodoAdquisicion) {
        this.periodoAdquisicion = periodoAdquisicion;
    }

    public String getFiltroInercial() {
        return filtroInercial;
    }

    public void setFiltroInercial(String filtroInercial) {
        this.filtroInercial = filtroInercial;
    }

    public String getNumeroDeCanales() {
        return numeroDeCanales;
    }

    public void setNumeroDeCanales(String numeroDeCanales) {
        this.numeroDeCanales = numeroDeCanales;
    }

    public String getTipoDeSondaLambda() {
        return tipoDeSondaLambda;
    }

    public void setTipoDeSondaLambda(String tipoDeSondaLambda) {
        this.tipoDeSondaLambda = tipoDeSondaLambda;
    }

    public String getValorLambdaAMedir() {
        return valorLambdaAMedir;
    }

    public void setValorLambdaAMedir(String valorLambdaAMedir) {
        this.valorLambdaAMedir = valorLambdaAMedir;
    }

    public String getTipoDeCombustible() {
        return tipoDeCombustible;
    }

    public void setTipoDeCombustible(String tipoDeCombustible) {
        this.tipoDeCombustible = tipoDeCombustible;
    }

    public String getcCodTipo() {
        return cCodTipo;
    }

    public void setcCodTipo(String cCodTipo) {
        this.cCodTipo = cCodTipo;
    }

    public String getRelacionCaja() {
        return relacionCaja;
    }

    public void setRelacionCaja(String relacionCaja) {
        this.relacionCaja = relacionCaja;
    }

    public String getUsarPickup() {
        return usarPickup;
    }

    public void setUsarPickup(String usarPickup) {
        this.usarPickup = usarPickup;
    }

    public String getpPVA() {
        return pPVA;
    }

    public void setpPVA(String pPVA) {
        this.pPVA = pPVA;
    }

    public String getpPVB() {
        return pPVB;
    }

    public void setpPVB(String pPVB) {
        this.pPVB = pPVB;
    }

    public String getUltimaRPMCaja() {
        return ultimaRPMCaja;
    }

    public void setUltimaRPMCaja(String ultimaRPMCaja) {
        this.ultimaRPMCaja = ultimaRPMCaja;
    }

    public String getMomentoInercia() {
        return momentoInercia;
    }

    public void setMomentoInercia(String momentoInercia) {
        this.momentoInercia = momentoInercia;
    }

    public String getDiametroRodillo() {
        return diametroRodillo;
    }

    public void setDiametroRodillo(String diametroRodillo) {
        this.diametroRodillo = diametroRodillo;
    }

    public String getLimitadorMax() {
        return limitadorMax;
    }

    public void setLimitadorMax(String limitadorMax) {
        this.limitadorMax = limitadorMax;
    }

    public String getLimitadorUmbral() {
        return limitadorUmbral;
    }

    public void setLimitadorUmbral(String limitadorUmbral) {
        this.limitadorUmbral = limitadorUmbral;
    }

    public String getHabilitarLimitador() {
        return habilitarLimitador;
    }

    public void setHabilitarLimitador(String habilitarLimitador) {
        this.habilitarLimitador = habilitarLimitador;
    }

    public String getUnidadPotencia() {
        return unidadPotencia;
    }

    public void setUnidadPotencia(String unidadPotencia) {
        this.unidadPotencia = unidadPotencia;
    }

    public String getUnidadTorque() {
        return unidadTorque;
    }

    public void setUnidadTorque(String unidadTorque) {
        this.unidadTorque = unidadTorque;
    }

    public String getFuzzyRPMIni() {
        return fuzzyRPMIni;
    }

    public void setFuzzyRPMIni(String fuzzyRPMIni) {
        this.fuzzyRPMIni = fuzzyRPMIni;
    }

    public String getFuzzyRPMFin() {
        return fuzzyRPMFin;
    }

    public void setFuzzyRPMFin(String fuzzyRPMFin) {
        this.fuzzyRPMFin = fuzzyRPMFin;
    }

    public String getFuzzyFacIni() {
        return fuzzyFacIni;
    }

    public void setFuzzyFacIni(String fuzzyFacIni) {
        this.fuzzyFacIni = fuzzyFacIni;
    }

    public String getFuzzyFacFin() {
        return fuzzyFacFin;
    }

    public void setFuzzyFacFin(String fuzzyFacFin) {
        this.fuzzyFacFin = fuzzyFacFin;
    }

    public String getNoConPerdidas() {
        return noConPerdidas;
    }

    public void setNoConPerdidas(String noConPerdidas) {
        this.noConPerdidas = noConPerdidas;
    }

    public String getSecuenciaPulsador() {
        return secuenciaPulsador;
    }

    public void setSecuenciaPulsador(String secuenciaPulsador) {
        this.secuenciaPulsador = secuenciaPulsador;
    }

    public String getRelacionPrimaria() {
        return relacionPrimaria;
    }

    public void setRelacionPrimaria(String relacionPrimaria) {
        this.relacionPrimaria = relacionPrimaria;
    }

    public String getDientesPinon() {
        return dientesPinon;
    }

    public void setDientesPinon(String dientesPinon) {
        this.dientesPinon = dientesPinon;
    }

    public String getDientesCorona() {
        return dientesCorona;
    }

    public void setDientesCorona(String dientesCorona) {
        this.dientesCorona = dientesCorona;
    }

    public String getAlarma1() {
        return alarma1;
    }

    public void setAlarma1(String alarma1) {
        this.alarma1 = alarma1;
    }

    public String getAlarma2() {
        return alarma2;
    }

    public void setAlarma2(String alarma2) {
        this.alarma2 = alarma2;
    }

    public String getAlarma3() {
        return alarma3;
    }

    public void setAlarma3(String alarma3) {
        this.alarma3 = alarma3;
    }

    public String getTipoAlarma1() {
        return tipoAlarma1;
    }

    public void setTipoAlarma1(String tipoAlarma1) {
        this.tipoAlarma1 = tipoAlarma1;
    }

    public String getTipoAlarma2() {
        return tipoAlarma2;
    }

    public void setTipoAlarma2(String tipoAlarma2) {
        this.tipoAlarma2 = tipoAlarma2;
    }

    public String getTipoAlarma3() {
        return tipoAlarma3;
    }

    public void setTipoAlarma3(String tipoAlarma3) {
        this.tipoAlarma3 = tipoAlarma3;
    }

    public String getHisteresisAlarma() {
        return histeresisAlarma;
    }

    public void setHisteresisAlarma(String histeresisAlarma) {
        this.histeresisAlarma = histeresisAlarma;
    }

    public String getAlarmaOn() {
        return alarmaOn;
    }

    public void setAlarmaOn(String alarmaOn) {
        this.alarmaOn = alarmaOn;
    }

    public String getUsoDeCanales_1() {
        return usoDeCanales_1;
    }

    public void setUsoDeCanales_1(String usoDeCanales_1) {
        this.usoDeCanales_1 = usoDeCanales_1;
    }

    public String getUsoDeCanales_2() {
        return usoDeCanales_2;
    }

    public void setUsoDeCanales_2(String usoDeCanales_2) {
        this.usoDeCanales_2 = usoDeCanales_2;
    }

    public String getUsoDeCanales_3() {
        return usoDeCanales_3;
    }

    public void setUsoDeCanales_3(String usoDeCanales_3) {
        this.usoDeCanales_3 = usoDeCanales_3;
    }

    public String getUsoDeCanales_4() {
        return usoDeCanales_4;
    }

    public void setUsoDeCanales_4(String usoDeCanales_4) {
        this.usoDeCanales_4 = usoDeCanales_4;
    }

    public String getUsoDeCanales_5() {
        return usoDeCanales_5;
    }

    public void setUsoDeCanales_5(String usoDeCanales_5) {
        this.usoDeCanales_5 = usoDeCanales_5;
    }

    public String getUsoDeCanales_6() {
        return usoDeCanales_6;
    }

    public void setUsoDeCanales_6(String usoDeCanales_6) {
        this.usoDeCanales_6 = usoDeCanales_6;
    }

    public String getUsoDeCanales_7() {
        return usoDeCanales_7;
    }

    public void setUsoDeCanales_7(String usoDeCanales_7) {
        this.usoDeCanales_7 = usoDeCanales_7;
    }

    public String getUsoDeCanales_8() {
        return usoDeCanales_8;
    }

    public void setUsoDeCanales_8(String usoDeCanales_8) {
        this.usoDeCanales_8 = usoDeCanales_8;
    }

    public String getUsoDeCanales_9() {
        return usoDeCanales_9;
    }

    public void setUsoDeCanales_9(String usoDeCanales_9) {
        this.usoDeCanales_9 = usoDeCanales_9;
    }

    public String getUsoDeCanales_10() {
        return usoDeCanales_10;
    }

    public void setUsoDeCanales_10(String usoDeCanales_10) {
        this.usoDeCanales_10 = usoDeCanales_10;
    }

    public String getUsoDeCanales_11() {
        return usoDeCanales_11;
    }

    public void setUsoDeCanales_11(String usoDeCanales_11) {
        this.usoDeCanales_11 = usoDeCanales_11;
    }

    public String getUsoDeCanales_12() {
        return usoDeCanales_12;
    }

    public void setUsoDeCanales_12(String usoDeCanales_12) {
        this.usoDeCanales_12 = usoDeCanales_12;
    }

    public String getUsoDeCanales_13() {
        return usoDeCanales_13;
    }

    public void setUsoDeCanales_13(String usoDeCanales_13) {
        this.usoDeCanales_13 = usoDeCanales_13;
    }

    public String getUsoDeCanales_14() {
        return usoDeCanales_14;
    }

    public void setUsoDeCanales_14(String usoDeCanales_14) {
        this.usoDeCanales_14 = usoDeCanales_14;
    }

    public String getUsoDeCanales_15() {
        return usoDeCanales_15;
    }

    public void setUsoDeCanales_15(String usoDeCanales_15) {
        this.usoDeCanales_15 = usoDeCanales_15;
    }

    public String getUsoDeCanales_16() {
        return usoDeCanales_16;
    }

    public void setUsoDeCanales_16(String usoDeCanales_16) {
        this.usoDeCanales_16 = usoDeCanales_16;
    }

}
