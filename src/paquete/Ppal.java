//TODO comentar todo

package paquete;

import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Usuario
 */
public class Ppal extends javax.swing.JFrame {

    Ensayo ensayo = new Ensayo();
    CanalVirtual[] canalVirtual = new CanalVirtual[51];
    Datos datos = new Datos();
    Configuracion configuracion = new Configuracion();
    CanalReal[] canalReal = new CanalReal[31];
    Indicador[] indicador = new Indicador[17];
    Salida[] salida = new Salida[2];
    ConfigGrafico[] configGrafico = new ConfigGrafico[11];
    GraficoEnsayo[] graficoEnsayo = new GraficoEnsayo[4];
    ConfigEjeY[] configEjeY = new ConfigEjeY[4];
    GraficoCanal[] graficoCanal = new GraficoCanal[51];

    boolean ag = true;
    String nombreArchivo;
    double potenciaCorr[], rpm[], potencia[];
    int posPotCorr, posPot;
    boolean modificar = false;
    Licencia Lic = new Licencia();
    double rtAnt = 0;

    /**
     * Creates new form Ppal
     */
    public Ppal() {
        try {
            initComponents();
            this.setTitle("Potencia Motor");
            this.setBounds(0, 0, 580, 360);
            this.setLocationRelativeTo(null);
            BufferedImage img = ImageIO.read(new File("Motor.png"));
            this.setIconImage(img);
            dlg_Archivos.setSize(650, 390);
            dlg_Archivos.setLocationRelativeTo(null);
            AnalizarLicencia();
            HabilitarCampos(false);
        } catch (IOException ex) {
            Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Verifica si la licencia es correcta
     */
    public void AnalizarLicencia() {
        if (!Lic.Verificar()) {
            String lc;
            lc = JOptionPane.showInputDialog(this, "Proporcione el siguiente codigo para obtener la licencia:" + '\n' + Lic.Maquina(), "ERROR DE LICENCIA", JOptionPane.PLAIN_MESSAGE);
            if (lc.equals("")) {
                this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            } else {
                try {
                    PrintWriter salida = null;
                    salida = new PrintWriter(Lic.ArchivoLicencia().getAbsolutePath());
                    salida.println(lc);
                    salida.close();
                    if (!Lic.Verificar()) {
                        this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Lee un archivo xml, o ad3
     *
     * @param ruta
     */
    public void LeerArchivo(File ruta) {
        try {
            SAXBuilder builder = new SAXBuilder();
            File xml = new File(ruta.getAbsolutePath());
            Document document = builder.build(xml);
            Element root = document.getRootElement();
            List<Element> listaEnsayo = root.getChildren("Ensayo");
            List<Element> etiquetasEnsayo = listaEnsayo.get(0).getChildren();
            Element campo = etiquetasEnsayo.get(0);
            ensayo.setNumeroDeCanales(campo.getValue());
            campo = etiquetasEnsayo.get(1);
            ensayo.setMuestrasTotales(campo.getValue());
            campo = etiquetasEnsayo.get(2);
            ensayo.setCanalRPM(campo.getValue());
            campo = etiquetasEnsayo.get(3);
            ensayo.setCanalTiempo(campo.getValue());
            campo = etiquetasEnsayo.get(4);
            ensayo.setTipoDeEnsayo(campo.getValue());
            // CanalesVirtuales
            int j = 0;
            List<Element> etiquetasVirtual;
            Element valor;
            for (int i = 5; i < 56; i++) {
                campo = etiquetasEnsayo.get(i);
                canalVirtual[j] = new CanalVirtual();
                canalVirtual[j].setNumeroCanal(String.valueOf(j));
                etiquetasVirtual = campo.getChildren();
                valor = etiquetasVirtual.get(0);
                canalVirtual[j].setUnidad(valor.getValue());
                valor = etiquetasVirtual.get(1);
                canalVirtual[j].setNombre(valor.getValue());
                valor = etiquetasVirtual.get(2);
                canalVirtual[j].setFinSeccion1(valor.getValue());
                valor = etiquetasVirtual.get(3);
                canalVirtual[j].setFinSeccion2(valor.getValue());
                valor = etiquetasVirtual.get(4);
                canalVirtual[j].setPromedio(valor.getValue());
                valor = etiquetasVirtual.get(5);
                canalVirtual[j].setIndiceMax(valor.getValue());
                valor = etiquetasVirtual.get(6);
                canalVirtual[j].setIndiceMin(valor.getValue());
                valor = etiquetasVirtual.get(7);
                canalVirtual[j].setLugaresDecimales(valor.getValue());
                valor = etiquetasVirtual.get(8);
                canalVirtual[j].setAsignacionDelCanal(valor.getValue());
                valor = etiquetasVirtual.get(9);
                canalVirtual[j].setCanalAsignado(valor.getValue());
                valor = etiquetasVirtual.get(10);
                canalVirtual[j].setMuestra(valor.getValue());
                j++;
            }
            // Datos
            campo = etiquetasEnsayo.get(56);
            etiquetasVirtual = campo.getChildren();
            valor = etiquetasVirtual.get(0);
            datos.setAltitudEnsayo(valor.getValue());
            valor = etiquetasVirtual.get(1);
            datos.setClienteEnsayo(valor.getValue());
            valor = etiquetasVirtual.get(2);
            datos.setEncabezadoEmpresa(valor.getValue());
            valor = etiquetasVirtual.get(3);
            datos.setFactorCorreccion(valor.getValue());
            valor = etiquetasVirtual.get(4);
            datos.setFechaEnsayo(valor.getValue());
            valor = etiquetasVirtual.get(5);
            datos.setHoraEnsayo(valor.getValue());
            valor = etiquetasVirtual.get(6);
            datos.setHumedadEnsayo(valor.getValue());
            valor = etiquetasVirtual.get(7);
            datos.setMarcaMotor(valor.getValue());
            valor = etiquetasVirtual.get(8);
            datos.setModeloMotor(valor.getValue());
            valor = etiquetasVirtual.get(9);
            datos.setNombreEnsayo(valor.getValue());
            valor = etiquetasVirtual.get(10);
            datos.setNumeroEnsayo(valor.getValue());
            valor = etiquetasVirtual.get(11);
            datos.setObservaciones(valor.getValue());
            valor = etiquetasVirtual.get(12);
            datos.setPresionEnsayo(valor.getValue());
            valor = etiquetasVirtual.get(13);
            datos.setPresionEsCorregida(valor.getValue());
            valor = etiquetasVirtual.get(14);
            datos.setNormaFactor(valor.getValue());
            valor = etiquetasVirtual.get(15);
            datos.setTemperaturaEnsayo(valor.getValue());
            valor = etiquetasVirtual.get(16);
            datos.setCategoriaMotor(valor.getValue());
            valor = etiquetasVirtual.get(17);
            datos.setUbicacionEnsayo(valor.getValue());
            valor = etiquetasVirtual.get(18);
            datos.setRpmFinalMuestreo(valor.getValue());
            valor = etiquetasVirtual.get(19);
            datos.setRpmInicialMuestreo(valor.getValue());
            valor = etiquetasVirtual.get(20);
            datos.setRpmFinalPromedio(valor.getValue());
            valor = etiquetasVirtual.get(21);
            datos.setRpmInicialPromedio(valor.getValue());
            valor = etiquetasVirtual.get(22);
            datos.setTimerAceleracion(valor.getValue());
            valor = etiquetasVirtual.get(23);
            datos.setTimerAsentamiento(valor.getValue());
            valor = etiquetasVirtual.get(24);
            datos.setTimerEnsayo(valor.getValue());
            valor = etiquetasVirtual.get(25);
            datos.setUnidadPresionEnsayo(valor.getValue());
            valor = etiquetasVirtual.get(26);
            datos.setArchivo(valor.getValue());
            valor = etiquetasVirtual.get(27);
            datos.setRuta(valor.getValue());
            // Configuracion
            campo = etiquetasEnsayo.get(57);
            etiquetasVirtual = campo.getChildren();
            valor = etiquetasVirtual.get(0);
            configuracion.setEsPorPuntos(valor.getValue());
            valor = etiquetasVirtual.get(1);
            configuracion.setFactorInercia(valor.getValue());
            valor = etiquetasVirtual.get(2);
            configuracion.setLimitRPMMax(valor.getValue());
            valor = etiquetasVirtual.get(3);
            configuracion.setLongitudPalanca(valor.getValue());
            valor = etiquetasVirtual.get(4);
            configuracion.setNumeroDeVariablesDePanel(valor.getValue());
            valor = etiquetasVirtual.get(5);
            configuracion.setPeriodoRegistro(valor.getValue());
            valor = etiquetasVirtual.get(6);
            configuracion.setPuerto(valor.getValue());
            valor = etiquetasVirtual.get(7);
            configuracion.setPeriodoAdquisicion(valor.getValue());
            valor = etiquetasVirtual.get(8);
            configuracion.setFiltroInercial(valor.getValue());
            valor = etiquetasVirtual.get(9);
            configuracion.setNumeroDeCanales(valor.getValue());
//            valor = etiquetasVirtual.get(10);
//            configuracion.setA(valor.getValue());            
            valor = etiquetasVirtual.get(11);
            configuracion.setTipoDeSondaLambda(valor.getValue());
            valor = etiquetasVirtual.get(12);
            configuracion.setValorLambdaAMedir(valor.getValue());
            valor = etiquetasVirtual.get(13);
            configuracion.setTipoDeCombustible(valor.getValue());
            valor = etiquetasVirtual.get(14);
            configuracion.setcCodTipo(valor.getValue());
            valor = etiquetasVirtual.get(15);
            configuracion.setRelacionCaja(valor.getValue());
            valor = etiquetasVirtual.get(16);
            configuracion.setUsarPickup(valor.getValue());
            valor = etiquetasVirtual.get(17);
            configuracion.setpPVA(valor.getValue());
            valor = etiquetasVirtual.get(18);
            configuracion.setpPVB(valor.getValue());
            valor = etiquetasVirtual.get(19);
            configuracion.setUltimaRPMCaja(valor.getValue());
            valor = etiquetasVirtual.get(20);
            configuracion.setMomentoInercia(valor.getValue());
            valor = etiquetasVirtual.get(21);
            configuracion.setDiametroRodillo(valor.getValue());
            valor = etiquetasVirtual.get(22);
            configuracion.setLimitadorMax(valor.getValue());
            valor = etiquetasVirtual.get(23);
            configuracion.setLimitadorUmbral(valor.getValue());
            valor = etiquetasVirtual.get(24);
            configuracion.setHabilitarLimitador(valor.getValue());
            valor = etiquetasVirtual.get(25);
            configuracion.setUnidadPotencia(valor.getValue());
            valor = etiquetasVirtual.get(26);
            configuracion.setUnidadTorque(valor.getValue());
            valor = etiquetasVirtual.get(27);
            configuracion.setFuzzyRPMIni(valor.getValue());
            valor = etiquetasVirtual.get(28);
            configuracion.setFuzzyRPMFin(valor.getValue());
            valor = etiquetasVirtual.get(29);
            configuracion.setFuzzyFacIni(valor.getValue());
            valor = etiquetasVirtual.get(30);
            configuracion.setFuzzyFacFin(valor.getValue());
            valor = etiquetasVirtual.get(31);
            configuracion.setNoConPerdidas(valor.getValue());
            valor = etiquetasVirtual.get(32);
            configuracion.setSecuenciaPulsador(valor.getValue());
            valor = etiquetasVirtual.get(33);
            configuracion.setRelacionPrimaria(valor.getValue());
            valor = etiquetasVirtual.get(34);
            configuracion.setDientesPinon(valor.getValue());
            valor = etiquetasVirtual.get(35);
            configuracion.setDientesCorona(valor.getValue());
            valor = etiquetasVirtual.get(36);
            configuracion.setAlarma1(valor.getValue());
            valor = etiquetasVirtual.get(37);
            configuracion.setAlarma2(valor.getValue());
            valor = etiquetasVirtual.get(38);
            configuracion.setAlarma3(valor.getValue());
            valor = etiquetasVirtual.get(39);
            configuracion.setTipoAlarma1(valor.getValue());
            valor = etiquetasVirtual.get(40);
            configuracion.setTipoAlarma2(valor.getValue());
            valor = etiquetasVirtual.get(41);
            configuracion.setTipoAlarma3(valor.getValue());
            valor = etiquetasVirtual.get(42);
            configuracion.setHisteresisAlarma(valor.getValue());
            valor = etiquetasVirtual.get(43);
            configuracion.setAlarmaOn(valor.getValue());
            valor = etiquetasVirtual.get(44);
            configuracion.setUsoDeCanales_1(valor.getValue());
            valor = etiquetasVirtual.get(45);
            configuracion.setUsoDeCanales_2(valor.getValue());
            valor = etiquetasVirtual.get(46);
            configuracion.setUsoDeCanales_3(valor.getValue());
            valor = etiquetasVirtual.get(47);
            configuracion.setUsoDeCanales_4(valor.getValue());
            valor = etiquetasVirtual.get(48);
            configuracion.setUsoDeCanales_5(valor.getValue());
            valor = etiquetasVirtual.get(49);
            configuracion.setUsoDeCanales_6(valor.getValue());
            valor = etiquetasVirtual.get(50);
            configuracion.setUsoDeCanales_7(valor.getValue());
            valor = etiquetasVirtual.get(51);
            configuracion.setUsoDeCanales_8(valor.getValue());
            valor = etiquetasVirtual.get(52);
            configuracion.setUsoDeCanales_9(valor.getValue());
            valor = etiquetasVirtual.get(53);
            configuracion.setUsoDeCanales_10(valor.getValue());
            valor = etiquetasVirtual.get(54);
            configuracion.setUsoDeCanales_11(valor.getValue());
            valor = etiquetasVirtual.get(55);
            configuracion.setUsoDeCanales_12(valor.getValue());
            valor = etiquetasVirtual.get(56);
            configuracion.setUsoDeCanales_13(valor.getValue());
            valor = etiquetasVirtual.get(57);
            configuracion.setUsoDeCanales_14(valor.getValue());
            valor = etiquetasVirtual.get(58);
            configuracion.setUsoDeCanales_15(valor.getValue());
            valor = etiquetasVirtual.get(59);
            configuracion.setUsoDeCanales_16(valor.getValue());
            // CanalesReales
            j = 0;
            List<Element> etiquetasReal;
            for (int i = 60; i < 91; i++) {
                campo = etiquetasVirtual.get(i);
                canalReal[j] = new CanalReal();
                canalReal[j].setNumeroCanal(String.valueOf(j));
                etiquetasReal = campo.getChildren();
                valor = etiquetasReal.get(0);
                canalReal[j].setCalibracionA(valor.getValue());
                valor = etiquetasReal.get(1);
                canalReal[j].setCalibracionB(valor.getValue());
                valor = etiquetasReal.get(2);
                canalReal[j].setCalibracionRefInicial(valor.getValue());
                valor = etiquetasReal.get(3);
                canalReal[j].setCalibracionRefFinal(valor.getValue());
                valor = etiquetasReal.get(4);
                canalReal[j].setMuestra(valor.getValue());
                valor = etiquetasReal.get(5);
                canalReal[j].setFuncionDelCanal(valor.getValue());
                j++;
            }
            // Indicador
            j = 0;
            List<Element> etiquetasIndicador;
            for (int i = 91; i < 108; i++) {
                campo = etiquetasVirtual.get(i);
                indicador[j] = new Indicador();
                indicador[j].setNroIndicador(String.valueOf(j));
                etiquetasIndicador = campo.getChildren();
                valor = etiquetasIndicador.get(0);
                indicador[j].setColorFondoSeccion_0(valor.getValue());
                valor = etiquetasIndicador.get(1);
                indicador[j].setColorTextoSeccion_0(valor.getValue());
                valor = etiquetasIndicador.get(2);
                indicador[j].setColorFondoSeccion_1(valor.getValue());
                valor = etiquetasIndicador.get(3);
                indicador[j].setColorTextoSeccion_1(valor.getValue());
                valor = etiquetasIndicador.get(4);
                indicador[j].setColorFondoSeccion_2(valor.getValue());
                valor = etiquetasIndicador.get(5);
                indicador[j].setColorTextoSeccion_2(valor.getValue());
                valor = etiquetasIndicador.get(6);
                indicador[j].setCanalAsignado(valor.getValue());
                j++;
            }
            // Salida
            j = 0;
            List<Element> etiquetesSalida;
            for (int i = 108; i < 110; i++) {
                campo = etiquetasVirtual.get(i);
                salida[j] = new Salida();
                salida[j].setNroSalida(String.valueOf(j));
                etiquetesSalida = campo.getChildren();
                valor = etiquetesSalida.get(0);
                salida[j].setCalibracionA(valor.getValue());
                valor = etiquetesSalida.get(1);
                salida[j].setCalibracionB(valor.getValue());
                valor = etiquetesSalida.get(2);
                salida[j].setCalibracionRefInicial(valor.getValue());
                valor = etiquetesSalida.get(3);
                salida[j].setCalibracionRefFinal(valor.getValue());
                valor = etiquetesSalida.get(4);
                salida[j].setCanalAsignado(valor.getValue());
                j++;
            }
            // Fin de lectura de ensayo
            List<Element> listaGraf = root.getChildren("ConfigGraphMemories");
            List<Element> etiquetasGraf = listaGraf.get(0).getChildren();
            j = 0;
            for (int i = 0; i < etiquetasGraf.size(); i++) {
                campo = etiquetasGraf.get(i);
                List<Element> ListCampo = campo.getChildren();
                configGrafico[j] = new ConfigGrafico();
                configGrafico[j].setNroMemoria(String.valueOf(j));
                valor = ListCampo.get(0);
                configGrafico[j].setFiltrado(valor.getValue());
                valor = ListCampo.get(1);
                configGrafico[j].setGrilla(valor.getValue());
                valor = ListCampo.get(2);
                configGrafico[j].setAsignacionGrilla(valor.getValue());
                valor = ListCampo.get(3);
                configGrafico[j].setRpmInicialPromedio(valor.getValue());
                valor = ListCampo.get(4);
                configGrafico[j].setRpmFinalPromedio(valor.getValue());
                valor = ListCampo.get(5);
                configGrafico[j].setHorizontalRef(valor.getValue());
                int p = 0;
                for (int k = 6; k < 10; k++) {
                    List<Element> listGrafEnsayo = ListCampo.get(k).getChildren();
                    graficoEnsayo[p] = new GraficoEnsayo();
                    graficoEnsayo[p].setNroEnsayo(String.valueOf(p));
                    valor = listGrafEnsayo.get(0);
                    graficoEnsayo[p].setUltimoArchivo(valor.getValue());
                    valor = listGrafEnsayo.get(1);
                    graficoEnsayo[p].setNumeroCanales(valor.getValue());
                    int q = 0;
                    for (int l = 2; l < listGrafEnsayo.size(); l++) {
                        List<Element> listGrafCanal = listGrafEnsayo.get(l).getChildren();
                        graficoCanal[q] = new GraficoCanal();
                        graficoCanal[q].setNroCanal(String.valueOf(q));
                        valor = listGrafCanal.get(0);
                        graficoCanal[q].setEjeY(valor.getValue());
                        valor = listGrafCanal.get(1);
                        graficoCanal[q].setColor(valor.getValue());
                        valor = listGrafCanal.get(2);
                        graficoCanal[q].setTipoDeLinea(valor.getValue());
                        valor = listGrafCanal.get(3);
                        graficoCanal[q].setGrosor(valor.getValue());
                        q++;
                    }
                    p++;
                }
                p = 0;
                for (int k = 10; k < ListCampo.size(); k++) {
                    List<Element> listEjeY = ListCampo.get(k).getChildren();
                    configEjeY[p] = new ConfigEjeY();
                    configEjeY[p].setNroEjeY(String.valueOf(p));
                    valor = listEjeY.get(0);
                    configEjeY[p].setTipoEscala(valor.getValue());
                    valor = listEjeY.get(1);
                    configEjeY[p].setDeltaYMinor(valor.getValue());
                    valor = listEjeY.get(2);
                    configEjeY[p].setScaleYMin(valor.getValue());
                    valor = listEjeY.get(3);
                    configEjeY[p].setScaleYMax(valor.getValue());
                    p++;
                }
                j++;
            }
        } catch (JDOMException ex) {
            Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crea un archivo xml, o ad3
     *
     * @param ruta
     */
    public void CrearArchivo(File ruta) {
        try {
            Element AD03 = new Element("AD03");
            Document doc = new Document(AD03);
            Element eleEnsayo = new Element("Ensayo");
            AD03.addContent(eleEnsayo);
            Element eleGraf;
            eleGraf = new Element("ConfigGraphMemories");
            AD03.addContent(eleGraf);
            Element elemento;
            elemento = new Element(ensayo.getEtiquetas(0)).setText(ensayo.getNumeroDeCanales());
            eleEnsayo.addContent(elemento);
            elemento = new Element(ensayo.getEtiquetas(1)).setText(ensayo.getMuestrasTotales());
            eleEnsayo.addContent(elemento);
            elemento = new Element(ensayo.getEtiquetas(2)).setText(ensayo.getCanalRPM());
            eleEnsayo.addContent(elemento);
            elemento = new Element(ensayo.getEtiquetas(3)).setText(ensayo.getCanalTiempo());
            eleEnsayo.addContent(elemento);
            elemento = new Element(ensayo.getEtiquetas(4)).setText(ensayo.getTipoDeEnsayo());
            eleEnsayo.addContent(elemento);
            // Canales Virtuales
            for (int i = 0; i < canalVirtual.length; i++) {
                Element cVirtual = new Element("CanalVirtual");
                cVirtual.setAttribute(canalVirtual[i].getEtiquetas(0), String.valueOf(i));
                elemento = new Element(canalVirtual[i].getEtiquetas(1)).setText(canalVirtual[i].getUnidad());
                cVirtual.addContent(elemento);
                elemento = new Element(canalVirtual[i].getEtiquetas(2)).setText(canalVirtual[i].getNombre());
                cVirtual.addContent(elemento);
                elemento = new Element(canalVirtual[i].getEtiquetas(3)).setText(canalVirtual[i].getFinSeccion1());
                cVirtual.addContent(elemento);
                elemento = new Element(canalVirtual[i].getEtiquetas(4)).setText(canalVirtual[i].getFinSeccion2());
                cVirtual.addContent(elemento);
                elemento = new Element(canalVirtual[i].getEtiquetas(5)).setText(canalVirtual[i].getPromedio());
                cVirtual.addContent(elemento);
                elemento = new Element(canalVirtual[i].getEtiquetas(6)).setText(canalVirtual[i].getIndiceMax());
                cVirtual.addContent(elemento);
                elemento = new Element(canalVirtual[i].getEtiquetas(7)).setText(canalVirtual[i].getIndiceMin());
                cVirtual.addContent(elemento);
                elemento = new Element(canalVirtual[i].getEtiquetas(8)).setText(canalVirtual[i].getLugaresDecimales());
                cVirtual.addContent(elemento);
                elemento = new Element(canalVirtual[i].getEtiquetas(9)).setText(canalVirtual[i].getAsignacionDelCanal());
                cVirtual.addContent(elemento);
                elemento = new Element(canalVirtual[i].getEtiquetas(10)).setText(canalVirtual[i].getCanalAsignado());
                cVirtual.addContent(elemento);
                elemento = new Element(canalVirtual[i].getEtiquetas(11)).setText(canalVirtual[i].getMuestra());
                cVirtual.addContent(elemento);
                eleEnsayo.addContent(cVirtual);
            }
            // Datos
            Element data = new Element("Datos");
            eleEnsayo.addContent(data);
            elemento = new Element(datos.getEtiquetas(0)).setText(datos.getAltitudEnsayo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(1)).setText(datos.getClienteEnsayo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(2)).setText(datos.getEncabezadoEmpresa());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(3)).setText(datos.getFactorCorreccion());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(4)).setText(datos.getFechaEnsayo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(5)).setText(datos.getHoraEnsayo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(6)).setText(datos.getHumedadEnsayo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(7)).setText(datos.getMarcaMotor());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(8)).setText(datos.getModeloMotor());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(9)).setText(datos.getNombreEnsayo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(10)).setText(datos.getNumeroEnsayo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(11)).setText(datos.getObservaciones());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(12)).setText(datos.getPresionEnsayo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(13)).setText(datos.getPresionEsCorregida());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(14)).setText(datos.getNormaFactor());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(15)).setText(datos.getTemperaturaEnsayo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(16)).setText(datos.getCategoriaMotor());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(17)).setText(datos.getUbicacionEnsayo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(18)).setText(datos.getRpmFinalMuestreo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(19)).setText(datos.getRpmInicialMuestreo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(20)).setText(datos.getRpmFinalPromedio());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(21)).setText(datos.getRpmInicialPromedio());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(22)).setText(datos.getTimerAceleracion());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(23)).setText(datos.getTimerAsentamiento());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(24)).setText(datos.getTimerEnsayo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(25)).setText(datos.getUnidadPresionEnsayo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(26)).setText(datos.getArchivo());
            data.addContent(elemento);
            elemento = new Element(datos.getEtiquetas(27)).setText(datos.getRuta());
            data.addContent(elemento);
            for (int i = 28; i < 41; i++) {//TODO poner cantidad de etiquetas
                elemento = new Element(datos.getEtiquetas(i));
                data.addContent(elemento);
            }
            // Configuracion
            Element config = new Element("Configuracion");
            eleEnsayo.addContent(config);
            elemento = new Element(configuracion.getEtiquetas(0)).setText(configuracion.getEsPorPuntos());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(1)).setText(configuracion.getFactorInercia());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(2)).setText(configuracion.getLimitRPMMax());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(3)).setText(configuracion.getLongitudPalanca());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(4)).setText(configuracion.getNumeroDeVariablesDePanel());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(5)).setText(configuracion.getPeriodoRegistro());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(6)).setText(configuracion.getPuerto());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(7)).setText(configuracion.getPeriodoAdquisicion());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(8)).setText(configuracion.getFiltroInercial());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(9)).setText(configuracion.getNumeroDeCanales());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(10));
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(11)).setText(configuracion.getTipoDeSondaLambda());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(12)).setText(configuracion.getValorLambdaAMedir());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(13)).setText(configuracion.getTipoDeCombustible());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(14)).setText(configuracion.getcCodTipo());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(15)).setText(configuracion.getRelacionCaja());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(16)).setText(configuracion.getUsarPickup());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(17)).setText(configuracion.getpPVA());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(18)).setText(configuracion.getpPVB());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(19)).setText(configuracion.getUltimaRPMCaja());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(20)).setText(configuracion.getMomentoInercia());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(21)).setText(configuracion.getDiametroRodillo());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(22)).setText(configuracion.getLimitadorMax());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(23)).setText(configuracion.getLimitadorUmbral());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(24)).setText(configuracion.getHabilitarLimitador());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(25)).setText(configuracion.getUnidadPotencia());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(26)).setText(configuracion.getUnidadTorque());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(27)).setText(configuracion.getFuzzyRPMIni());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(28)).setText(configuracion.getFuzzyRPMFin());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(29)).setText(configuracion.getFuzzyFacIni());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(30)).setText(configuracion.getFuzzyFacFin());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(31)).setText(configuracion.getNoConPerdidas());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(32)).setText(configuracion.getSecuenciaPulsador());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(33)).setText(configuracion.getRelacionPrimaria());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(34)).setText(configuracion.getDientesPinon());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(35)).setText(configuracion.getDientesCorona());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(36)).setText(configuracion.getAlarma1());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(37)).setText(configuracion.getAlarma2());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(38)).setText(configuracion.getAlarma3());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(39)).setText(configuracion.getTipoAlarma1());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(40)).setText(configuracion.getTipoAlarma2());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(41)).setText(configuracion.getTipoAlarma3());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(42)).setText(configuracion.getHisteresisAlarma());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(43)).setText(configuracion.getAlarmaOn());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(44)).setText(configuracion.getUsoDeCanales_1());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(45)).setText(configuracion.getUsoDeCanales_2());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(46)).setText(configuracion.getUsoDeCanales_3());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(47)).setText(configuracion.getUsoDeCanales_4());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(48)).setText(configuracion.getUsoDeCanales_5());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(49)).setText(configuracion.getUsoDeCanales_6());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(50)).setText(configuracion.getUsoDeCanales_7());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(51)).setText(configuracion.getUsoDeCanales_8());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(52)).setText(configuracion.getUsoDeCanales_9());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(53)).setText(configuracion.getUsoDeCanales_10());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(54)).setText(configuracion.getUsoDeCanales_11());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(55)).setText(configuracion.getUsoDeCanales_12());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(56)).setText(configuracion.getUsoDeCanales_13());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(57)).setText(configuracion.getUsoDeCanales_14());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(58)).setText(configuracion.getUsoDeCanales_15());
            config.addContent(elemento);
            elemento = new Element(configuracion.getEtiquetas(59)).setText(configuracion.getUsoDeCanales_16());
            config.addContent(elemento);
            // Canales reales
            for (int i = 0; i < canalReal.length; i++) {
                Element cReal = new Element("CanalReal");
                cReal.setAttribute("NroCanal", String.valueOf(i));
                config.addContent(cReal);
                elemento = new Element(canalReal[i].getEtiquetas(1)).setText(canalReal[i].getCalibracionA());
                cReal.addContent(elemento);
                elemento = new Element(canalReal[i].getEtiquetas(2)).setText(canalReal[i].getCalibracionB());
                cReal.addContent(elemento);
                elemento = new Element(canalReal[i].getEtiquetas(3)).setText(canalReal[i].getCalibracionRefInicial());
                cReal.addContent(elemento);
                elemento = new Element(canalReal[i].getEtiquetas(4)).setText(canalReal[i].getCalibracionRefFinal());
                cReal.addContent(elemento);
                elemento = new Element(canalReal[i].getEtiquetas(5)).setText(canalReal[i].getMuestra());
                cReal.addContent(elemento);
                elemento = new Element(canalReal[i].getEtiquetas(6)).setText(canalReal[i].getFuncionDelCanal());
                cReal.addContent(elemento);
            }
            // Indicador
            for (int i = 0; i < indicador.length; i++) {
                Element indi = new Element("Indicador");
                indi.setAttribute("NroIndicador", String.valueOf(i));
                config.addContent(indi);
                elemento = new Element(indicador[i].getEtiquetas(1)).setText(indicador[i].getColorFondoSeccion_0());
                indi.addContent(elemento);
                elemento = new Element(indicador[i].getEtiquetas(2)).setText(indicador[i].getColorTextoSeccion_0());
                indi.addContent(elemento);
                elemento = new Element(indicador[i].getEtiquetas(3)).setText(indicador[i].getColorFondoSeccion_1());
                indi.addContent(elemento);
                elemento = new Element(indicador[i].getEtiquetas(4)).setText(indicador[i].getColorTextoSeccion_1());
                indi.addContent(elemento);
                elemento = new Element(indicador[i].getEtiquetas(5)).setText(indicador[i].getColorFondoSeccion_2());
                indi.addContent(elemento);
                elemento = new Element(indicador[i].getEtiquetas(6)).setText(indicador[i].getColorTextoSeccion_2());
                indi.addContent(elemento);
                elemento = new Element(indicador[i].getEtiquetas(7)).setText(indicador[i].getCanalAsignado());
                indi.addContent(elemento);
            }
            // Salida
            for (int i = 0; i < salida.length; i++) {
                Element salir = new Element("Salida");
                salir.setAttribute("NroSalida", String.valueOf(i));
                config.addContent(salir);
                elemento = new Element(salida[i].getEtiquetas(1)).setText(salida[i].getCalibracionA());
                salir.addContent(elemento);
                elemento = new Element(salida[i].getEtiquetas(2)).setText(salida[i].getCalibracionB());
                salir.addContent(elemento);
                elemento = new Element(salida[i].getEtiquetas(3)).setText(salida[i].getCalibracionRefInicial());
                salir.addContent(elemento);
                elemento = new Element(salida[i].getEtiquetas(4)).setText(salida[i].getCalibracionRefFinal());
                salir.addContent(elemento);
                elemento = new Element(salida[i].getEtiquetas(5)).setText(salida[i].getCanalAsignado());
                salir.addContent(elemento);
            }
            // ConfigGraphMemories
            for (int i = 0; i < configGrafico.length; i++) {
                Element graf = new Element("ConfigGrafico");
                graf.setAttribute("NroMemoria", String.valueOf(i));
                eleGraf.addContent(graf);
                elemento = new Element(configGrafico[i].getEtiquetas(1)).setText(configGrafico[i].getFiltrado());
                graf.addContent(elemento);
                elemento = new Element(configGrafico[i].getEtiquetas(2)).setText(configGrafico[i].getGrilla());
                graf.addContent(elemento);
                elemento = new Element(configGrafico[i].getEtiquetas(3)).setText(configGrafico[i].getAsignacionGrilla());
                graf.addContent(elemento);
                elemento = new Element(configGrafico[i].getEtiquetas(4)).setText(configGrafico[i].getRpmInicialPromedio());
                graf.addContent(elemento);
                elemento = new Element(configGrafico[i].getEtiquetas(5)).setText(configGrafico[i].getRpmFinalPromedio());
                graf.addContent(elemento);
                elemento = new Element(configGrafico[i].getEtiquetas(6)).setText(configGrafico[i].getHorizontalRef());
                graf.addContent(elemento);
                for (int j = 0; j < graficoEnsayo.length; j++) {
                    Element grafEnsayo = new Element("GraficoEnsayo");
                    grafEnsayo.setAttribute("NroEnsayo", String.valueOf(j));
                    graf.addContent(grafEnsayo);
                    elemento = new Element(graficoEnsayo[j].getEtiquetas(1)).setText(graficoEnsayo[j].getUltimoArchivo());
                    grafEnsayo.addContent(elemento);
                    elemento = new Element(graficoEnsayo[j].getEtiquetas(2)).setText(graficoEnsayo[j].getNumeroCanales());
                    grafEnsayo.addContent(elemento);
                    for (int k = 0; k < graficoCanal.length; k++) {
                        Element grafCanal = new Element("GraficoCanal");
                        grafCanal.setAttribute("NroCanal", String.valueOf(k));
                        grafEnsayo.addContent(grafCanal);
                        elemento = new Element(graficoCanal[k].getEtiquetas(1)).setText(graficoCanal[k].getEjeY());
                        grafCanal.addContent(elemento);
                        elemento = new Element(graficoCanal[k].getEtiquetas(2)).setText(graficoCanal[k].getColor());
                        grafCanal.addContent(elemento);
                        elemento = new Element(graficoCanal[k].getEtiquetas(3)).setText(graficoCanal[k].getTipoDeLinea());
                        grafCanal.addContent(elemento);
                        elemento = new Element(graficoCanal[k].getEtiquetas(4)).setText(graficoCanal[k].getGrosor());
                        grafCanal.addContent(elemento);
                    }
                }
                for (int j = 0; j < configEjeY.length; j++) {
                    Element configY = new Element("ConfigEjeY");
                    configY.setAttribute("NroEjeY", String.valueOf(j));
                    graf.addContent(configY);
                    elemento = new Element(configEjeY[j].getEtiquetas(1)).setText(configEjeY[j].getTipoEscala());
                    configY.addContent(elemento);
                    elemento = new Element(configEjeY[j].getEtiquetas(2)).setText(configEjeY[j].getDeltaYMinor());
                    configY.addContent(elemento);
                    elemento = new Element(configEjeY[j].getEtiquetas(3)).setText(configEjeY[j].getScaleYMin());
                    configY.addContent(elemento);
                    elemento = new Element(configEjeY[j].getEtiquetas(4)).setText(configEjeY[j].getScaleYMax());
                    configY.addContent(elemento);
                }
            }
            XMLOutputter xml = new XMLOutputter();
            xml.setFormat(Format.getPrettyFormat());
            xml.output(doc, new FileWriter(ruta.getAbsolutePath() + ".ad3"));
        } catch (IOException ex) {
            Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Se llenan los cuadros de texto con los valores leidos
     */
    public void ActualizarCampos() {
        tfDatosNombre.setText(datos.getNombreEnsayo());
        tfDatosNumero.setText(datos.getNumeroEnsayo());
        tfDatosFecha.setText(datos.getFechaEnsayo());
        tfDatosHora.setText(datos.getHoraEnsayo());
        tfDatosNuevosNombre.setText(datos.getNombreEnsayo());
        tfDatosNuevosNumero.setText(datos.getNumeroEnsayo());
        tfDatosNuevosFecha.setText(datos.getFechaEnsayo());
        tfDatosNuevosHora.setText(datos.getHoraEnsayo());
        tfInercia.setText(configuracion.getMomentoInercia());
        nombreArchivo = tfDatosNombre.getText();
        IniciarCalculos();
    }

    /**
     * Se llenan los cuadros de texto con los valores leidos para modificar
     * la potencia
     */    
    public void IniciarCalculos() {
        tfSumarDesde.setText(String.valueOf((int) rpm[0]));
        tfSumarHasta.setText(String.valueOf((int) rpm[rpm.length - 1]));
        tfRestarDesde.setText(String.valueOf((int) rpm[0]));
        tfRestarHasta.setText(String.valueOf((int) rpm[rpm.length - 1]));
        tfSumarPotencia.setText("0");
        tfRestarPotencia.setText("0");
        tfRelTransmision.setText(configuracion.getRelacionCaja());
    }

    /**
     * Se generan los vectores de potencia y rpm a partir de los valores leidos
     * en el archivo
     */
    public void RpmPotencia() {
        String rev = "", pot = "", arr[];
        for (int i = 0; i < canalVirtual.length; i++) {
            if (canalVirtual[i].getNombre().equals("RPM Calc.")) {
                rev = canalVirtual[i].getMuestra();
                break;
            }
        }
        arr = rev.split(", ");
        rpm = new double[arr.length];
        for (int i = 0; i < rpm.length; i++) {
            rpm[i] = Double.parseDouble(arr[i]);
        }
        for (int i = 0; i < canalVirtual.length; i++) {
            if (canalVirtual[i].getNombre().equals("Pot. Corr")) {
                pot = canalVirtual[i].getMuestra();
                posPotCorr = i;
                break;
            }
        }
        arr = pot.split(", ");
        potenciaCorr = new double[arr.length];
        for (int i = 0; i < potenciaCorr.length; i++) {
            potenciaCorr[i] = Double.parseDouble(arr[i]);
        }

        for (int i = 0; i < canalVirtual.length; i++) {
            if (canalVirtual[i].getNombre().equals("Pot.")) {
                pot = canalVirtual[i].getMuestra();
                posPot = i;
                break;
            }
        }
        arr = pot.split(", ");
        potencia = new double[arr.length];
        for (int i = 0; i < potencia.length; i++) {
            potencia[i] = Double.parseDouble(arr[i]);
        }

    }
    
    /**
     * Se actualiza el canal virtual de relacion de transmision
     */
    public void ActualizarRT() {
        ModificarPotencia mp = new ModificarPotencia();
        String muestra = "";
        for (int i = 0; i < canalVirtual.length; i++) {
            if (canalVirtual[i].getNombre().equals("Rel. Transm")) {
                muestra = canalVirtual[i].getMuestra();
                canalVirtual[i].setMuestra(mp.RelacionTransmision(muestra, tfRelTransmision.getText()));
                break;
            }
        }
    }

    /**
     * Actualiza lapotencia corregida y la relacion de transmision
     */
    public void ActualizarValores() {
        String valor = "";
        for (int i = 0; i < potenciaCorr.length - 1; i++) {
            valor = valor + String.valueOf(potenciaCorr[i]) + ", ";
        }
        valor = valor + String.valueOf(potenciaCorr[potenciaCorr.length - 1]);
        canalVirtual[posPotCorr].setMuestra(valor);
        for (int i = 0; i < potencia.length - 1; i++) {
            valor = valor + String.valueOf(potencia[i]) + ", ";
        }
        valor = valor + String.valueOf(potencia[potencia.length - 1]);
        canalVirtual[posPot].setMuestra(valor);
        configuracion.setMomentoInercia(tfInercia.getText());
        if (!tfRelTransmision.getText().equals(configuracion.getRelacionCaja())) {
            configuracion.setRelacionCaja(tfRelTransmision.getText());
            ActualizarRT();
        }
    }

    /**
     * Actualiza la parte Datos del archivo xml
     */
    public void DatosNuevos() {
        datos.setNombreEnsayo(tfDatosNuevosNombre.getText());
        datos.setClienteEnsayo(tfDatosNuevosNombre.getText());
        datos.setNumeroEnsayo(tfDatosNuevosNumero.getText());
        datos.setFechaEnsayo(tfDatosNuevosFecha.getText());
        datos.setHoraEnsayo(tfDatosNuevosHora.getText());
        nombreArchivo = tfDatosNuevosNombre.getText();
    }

    /**
     * Habilita los textos para que puedan ser editados
     * @param hab 
     */
    public void HabilitarCampos(boolean hab) {
        tfDatosNuevosNombre.setEnabled(hab);
        tfDatosNuevosNombre.setEnabled(hab);
        tfDatosNuevosNumero.setEnabled(hab);
        tfDatosNuevosFecha.setEnabled(hab);
        tfDatosNuevosHora.setEnabled(hab);
        tfSumarPotencia.setEnabled(hab);
        tfSumarDesde.setEnabled(hab);
        tfSumarHasta.setEnabled(hab);
        tfRestarPotencia.setEnabled(hab);
        tfRestarDesde.setEnabled(hab);
        tfRestarHasta.setEnabled(hab);
        tfInercia.setEnabled(hab);
        tfRelTransmision.setEnabled(hab);
        btnModificarPotencia.setEnabled(hab);
        btnCancelarPotencia.setEnabled(hab);
        mnuGuardarArchivo.setEnabled(hab);
    }

    /**
     * Acepta solo números en tf con o sin punto decimal
     *
     * @param tf
     * @param evt
     * @param punto
     */
    private void ValidarTextField(JTextField tf, KeyEvent evt, boolean punto) {
        if (punto && evt.getKeyChar() == '.') {
            return;
        }
        if (evt.getKeyChar() < '0' || evt.getKeyChar() > '9') {
            evt.consume();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    /**
     * Si se modifica el Layout a Free Design hay, que agregar la biblioteca
     * Swing Layout
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlg_Archivos = new javax.swing.JDialog();
        fc_Archivos = new javax.swing.JFileChooser();
        pnlDatosEnsayo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfDatosHora = new javax.swing.JTextField();
        tfDatosNombre = new javax.swing.JTextField();
        tfDatosNumero = new javax.swing.JTextField();
        tfDatosFecha = new javax.swing.JTextField();
        pnlDatosEnsayo1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfDatosNuevosHora = new javax.swing.JTextField();
        tfDatosNuevosNombre = new javax.swing.JTextField();
        tfDatosNuevosNumero = new javax.swing.JTextField();
        tfDatosNuevosFecha = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfSumarPotencia = new javax.swing.JTextField();
        tfRestarPotencia = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tfSumarDesde = new javax.swing.JTextField();
        tfRestarDesde = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tfSumarHasta = new javax.swing.JTextField();
        tfRestarHasta = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnModificarPotencia = new javax.swing.JButton();
        btnCancelarPotencia = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        tfInercia = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        tfRelTransmision = new javax.swing.JTextField();
        btnCambiarRT = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArchivo = new javax.swing.JMenu();
        mnuAbrirArchivo = new javax.swing.JMenuItem();
        mnuGuardarArchivo = new javax.swing.JMenuItem();

        dlg_Archivos.setResizable(false);
        dlg_Archivos.setSize(new java.awt.Dimension(643, 443));
        dlg_Archivos.getContentPane().setLayout(null);

        fc_Archivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fc_ArchivosActionPerformed(evt);
            }
        });
        dlg_Archivos.getContentPane().add(fc_Archivos);
        fc_Archivos.setBounds(6, 6, 632, 347);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDatosEnsayo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos originales del ensayo", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 18))); // NOI18N
        pnlDatosEnsayo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Fecha:");
        pnlDatosEnsayo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 90, 55, 25));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Hora:");
        pnlDatosEnsayo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 120, 55, 25));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nombre:");
        pnlDatosEnsayo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 30, 55, 25));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Número:");
        pnlDatosEnsayo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 60, 55, 25));

        tfDatosHora.setEditable(false);
        tfDatosHora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlDatosEnsayo.add(tfDatosHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 120, 200, 25));

        tfDatosNombre.setEditable(false);
        tfDatosNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlDatosEnsayo.add(tfDatosNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 30, 200, 25));

        tfDatosNumero.setEditable(false);
        tfDatosNumero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlDatosEnsayo.add(tfDatosNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 60, 200, 25));

        tfDatosFecha.setEditable(false);
        tfDatosFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlDatosEnsayo.add(tfDatosFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 90, 200, 25));

        getContentPane().add(pnlDatosEnsayo, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 275, 155));

        pnlDatosEnsayo1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos nuevos del ensayo", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 18))); // NOI18N
        pnlDatosEnsayo1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Fecha:");
        pnlDatosEnsayo1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 90, 55, 25));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Hora:");
        pnlDatosEnsayo1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 120, 55, 25));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Nombre:");
        pnlDatosEnsayo1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 30, 55, 25));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Número:");
        pnlDatosEnsayo1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 60, 55, 25));

        tfDatosNuevosHora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlDatosEnsayo1.add(tfDatosNuevosHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 120, 200, 25));

        tfDatosNuevosNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlDatosEnsayo1.add(tfDatosNuevosNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 30, 200, 25));

        tfDatosNuevosNumero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfDatosNuevosNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDatosNuevosNumeroKeyTyped(evt);
            }
        });
        pnlDatosEnsayo1.add(tfDatosNuevosNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 60, 200, 25));

        tfDatosNuevosFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlDatosEnsayo1.add(tfDatosNuevosFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 90, 200, 25));

        getContentPane().add(pnlDatosEnsayo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 5, 275, 155));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modificar potencia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 18))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Restar:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 60, 45, 25));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Sumar:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 30, 45, 25));

        tfSumarPotencia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSumarPotencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfSumarPotencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfSumarPotenciaKeyTyped(evt);
            }
        });
        jPanel1.add(tfSumarPotencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 30, 60, 25));

        tfRestarPotencia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfRestarPotencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfRestarPotencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfRestarPotenciaKeyTyped(evt);
            }
        });
        jPanel1.add(tfRestarPotencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 60, 60, 25));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Desde:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 45, 25));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Desde:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 45, 25));

        tfSumarDesde.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSumarDesde.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfSumarDesde.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfSumarDesdeFocusLost(evt);
            }
        });
        tfSumarDesde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSumarDesdeActionPerformed(evt);
            }
        });
        tfSumarDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfSumarDesdeKeyTyped(evt);
            }
        });
        jPanel1.add(tfSumarDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 60, 25));

        tfRestarDesde.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfRestarDesde.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfRestarDesde.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfRestarDesdeFocusLost(evt);
            }
        });
        tfRestarDesde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRestarDesdeActionPerformed(evt);
            }
        });
        tfRestarDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfRestarDesdeKeyTyped(evt);
            }
        });
        jPanel1.add(tfRestarDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 60, 25));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("rpm");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 30, 25, 25));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("rpm");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 60, 25, 25));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Hasta:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 30, 45, 25));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Hasta:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 60, 45, 25));

        tfSumarHasta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSumarHasta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfSumarHasta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfSumarHastaFocusLost(evt);
            }
        });
        tfSumarHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSumarHastaActionPerformed(evt);
            }
        });
        tfSumarHasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfSumarHastaKeyTyped(evt);
            }
        });
        jPanel1.add(tfSumarHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 30, 60, 25));

        tfRestarHasta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfRestarHasta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfRestarHasta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfRestarHastaFocusLost(evt);
            }
        });
        tfRestarHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRestarHastaActionPerformed(evt);
            }
        });
        tfRestarHasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfRestarHastaKeyTyped(evt);
            }
        });
        jPanel1.add(tfRestarHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 60, 60, 25));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("rpm");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 25, 25));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("rpm");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 25, 25));

        btnModificarPotencia.setText("Modificar");
        btnModificarPotencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPotenciaActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificarPotencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 30, 90, 25));

        btnCancelarPotencia.setText("Cancelar");
        btnCancelarPotencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPotenciaActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelarPotencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 60, 90, 25));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Inercia del rodillo:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 90, 115, 25));

        tfInercia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfInercia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfInercia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfInerciaKeyTyped(evt);
            }
        });
        jPanel1.add(tfInercia, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 90, 60, 25));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Rel. Transmisión:");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 105, 25));

        tfRelTransmision.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfRelTransmision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfRelTransmision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfRelTransmisionKeyTyped(evt);
            }
        });
        jPanel1.add(tfRelTransmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 60, 25));

        btnCambiarRT.setText("Cambiar");
        btnCambiarRT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarRTActionPerformed(evt);
            }
        });
        jPanel1.add(btnCambiarRT, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 90, 75, 25));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 165, 555, 130));

        mnuArchivo.setText("Archivo");

        mnuAbrirArchivo.setText("Abrir");
        mnuAbrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAbrirArchivoActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuAbrirArchivo);

        mnuGuardarArchivo.setText("Guardar");
        mnuGuardarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGuardarArchivoActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuGuardarArchivo);

        jMenuBar1.add(mnuArchivo);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Permite elejir donde se guardan y de donde se leen los archivos
     * @param evt 
     */
    private void fc_ArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fc_ArchivosActionPerformed
        String arr[];
        File archivo;
        if (evt.getActionCommand().equals("ApproveSelection")) {
            archivo = fc_Archivos.getSelectedFile();
            dlg_Archivos.setVisible(false);
            if (ag) {
                LeerArchivo(archivo);
                RpmPotencia();
                ActualizarCampos();
                HabilitarCampos(true);
            } else {
                if (modificar) {
                    ActualizarValores();
                    modificar = false;
                }
                datos.setClienteEnsayo(archivo.getName());
                arr = archivo.getAbsolutePath().split(archivo.getName());
                datos.setRuta(arr[0]);
                datos.setArchivo(archivo.getName() + ".ad3");
                CrearArchivo(archivo);
            }
        } else {
            archivo = null;
            dlg_Archivos.setVisible(false);
        }
        ag = true;
    }//GEN-LAST:event_fc_ArchivosActionPerformed

    private void mnuAbrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAbrirArchivoActionPerformed
        ag = true;
        fc_Archivos.setDialogType(fc_Archivos.OPEN_DIALOG);
        fc_Archivos.setAcceptAllFileFilterUsed(false);
        fc_Archivos.setFileFilter(new FileNameExtensionFilter("Dyno", "ad3"));
        dlg_Archivos.setTitle("Abrir archivo de Dyno");
        dlg_Archivos.setModal(true);
        dlg_Archivos.setVisible(true);
    }//GEN-LAST:event_mnuAbrirArchivoActionPerformed

    private void mnuGuardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGuardarArchivoActionPerformed
        DatosNuevos();
        ag = false;
        fc_Archivos.setDialogType(fc_Archivos.SAVE_DIALOG);
        fc_Archivos.setFileFilter(new FileNameExtensionFilter("Dyno", "ad3"));
        fc_Archivos.setSelectedFile(new File(nombreArchivo));
        dlg_Archivos.setTitle("Guardar");
        dlg_Archivos.setModal(true);
        dlg_Archivos.setVisible(true);
    }//GEN-LAST:event_mnuGuardarArchivoActionPerformed

    private void btnCancelarPotenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPotenciaActionPerformed
        IniciarCalculos();
    }//GEN-LAST:event_btnCancelarPotenciaActionPerformed

    private void btnModificarPotenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPotenciaActionPerformed
        if (!tfSumarPotencia.getText().equals("0") || !tfRestarPotencia.getText().equals("0") || !tfInercia.getText().equals(configuracion.getMomentoInercia()) || !tfRelTransmision.getText().equals(configuracion.getRelacionCaja())) {
            ModificarPotencia mp = new ModificarPotencia();
            potenciaCorr = mp.Operacion(Double.parseDouble(tfSumarDesde.getText()), Double.parseDouble(tfSumarHasta.getText()), rpm, potenciaCorr, Double.parseDouble(tfSumarPotencia.getText()));
            potenciaCorr = mp.Operacion(Double.parseDouble(tfRestarDesde.getText()), Double.parseDouble(tfRestarHasta.getText()), rpm, potenciaCorr, -1.0 * Double.parseDouble(tfRestarPotencia.getText()));
            if (!tfInercia.getText().equals(configuracion.getMomentoInercia())) {
                potenciaCorr = mp.MomentoInercia(potenciaCorr, Double.parseDouble(configuracion.getMomentoInercia()), Double.parseDouble(tfInercia.getText()));
                configuracion.setMomentoInercia(tfInercia.getText());
            }
            if (!tfRelTransmision.getText().equals(configuracion.getRelacionCaja())) {
                potenciaCorr = mp.RelacionTransmision(potenciaCorr, Double.parseDouble(configuracion.getRelacionCaja()), Double.parseDouble(tfRelTransmision.getText()));
                ActualizarRT();
                configuracion.setRelacionCaja(tfRelTransmision.getText());
            }
            potencia = mp.CorreccionPotencia(potenciaCorr, Double.parseDouble(datos.getFactorCorreccion()));
            JOptionPane.showMessageDialog(this, "Se modificaron los datos con éxito", "Modificación", JOptionPane.PLAIN_MESSAGE);
            modificar = true;
        }
    }//GEN-LAST:event_btnModificarPotenciaActionPerformed

    private void tfSumarPotenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSumarPotenciaKeyTyped
        ValidarTextField(tfSumarPotencia, evt, true);
    }//GEN-LAST:event_tfSumarPotenciaKeyTyped

    private void tfRestarPotenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfRestarPotenciaKeyTyped
        ValidarTextField(tfRestarPotencia, evt, true);
    }//GEN-LAST:event_tfRestarPotenciaKeyTyped

    private void tfSumarDesdeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSumarDesdeKeyTyped
        ValidarTextField(tfSumarDesde, evt, false);
    }//GEN-LAST:event_tfSumarDesdeKeyTyped

    private void tfRestarDesdeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfRestarDesdeKeyTyped
        ValidarTextField(tfRestarDesde, evt, false);
    }//GEN-LAST:event_tfRestarDesdeKeyTyped

    private void tfSumarHastaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSumarHastaKeyTyped
        ValidarTextField(tfSumarHasta, evt, false);
    }//GEN-LAST:event_tfSumarHastaKeyTyped

    private void tfRestarHastaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfRestarHastaKeyTyped
        ValidarTextField(tfRestarHasta, evt, false);
    }//GEN-LAST:event_tfRestarHastaKeyTyped

    private void tfInerciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfInerciaKeyTyped
        ValidarTextField(tfInercia, evt, true);
    }//GEN-LAST:event_tfInerciaKeyTyped

    private void tfDatosNuevosNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDatosNuevosNumeroKeyTyped
        ValidarTextField(tfDatosNuevosNumero, evt, false);
    }//GEN-LAST:event_tfDatosNuevosNumeroKeyTyped

    private void tfSumarDesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSumarDesdeActionPerformed
        tfSumarDesde.transferFocus();
    }//GEN-LAST:event_tfSumarDesdeActionPerformed

    private void tfSumarDesdeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfSumarDesdeFocusLost
        if (Double.parseDouble(tfSumarDesde.getText()) < rpm[0]) {
            tfSumarDesde.setText(String.valueOf((int) rpm[0]));
        }
    }//GEN-LAST:event_tfSumarDesdeFocusLost

    private void tfRestarDesdeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfRestarDesdeFocusLost
        if (Double.parseDouble(tfRestarDesde.getText()) < rpm[0]) {
            tfRestarDesde.setText(String.valueOf((int) rpm[0]));
        }
    }//GEN-LAST:event_tfRestarDesdeFocusLost

    private void tfRestarDesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRestarDesdeActionPerformed
        tfRestarDesde.transferFocus();
    }//GEN-LAST:event_tfRestarDesdeActionPerformed

    private void tfSumarHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSumarHastaActionPerformed
        tfSumarHasta.transferFocus();
    }//GEN-LAST:event_tfSumarHastaActionPerformed

    private void tfSumarHastaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfSumarHastaFocusLost
        if (Double.parseDouble(tfSumarHasta.getText()) > rpm[rpm.length - 1]) {
            tfSumarHasta.setText(String.valueOf((int) rpm[rpm.length - 1]));
        }
    }//GEN-LAST:event_tfSumarHastaFocusLost

    private void tfRestarHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRestarHastaActionPerformed
        tfRestarHasta.transferFocus();
    }//GEN-LAST:event_tfRestarHastaActionPerformed

    private void tfRestarHastaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfRestarHastaFocusLost
        if (Double.parseDouble(tfRestarHasta.getText()) > rpm[rpm.length - 1]) {
            tfRestarHasta.setText(String.valueOf((int) rpm[rpm.length - 1]));
        }
    }//GEN-LAST:event_tfRestarHastaFocusLost

    private void tfRelTransmisionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfRelTransmisionKeyTyped
        ValidarTextField(tfRelTransmision, evt, true);
    }//GEN-LAST:event_tfRelTransmisionKeyTyped

    private void btnCambiarRTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarRTActionPerformed
        String valor = "";
        double rtNuevo = 0;
        valor = JOptionPane.showInputDialog(null, "Ingrese el valor de la relación de intalación actual", (rtAnt != 0) ? String.valueOf(rtAnt) : "");
        if (valor == null) {
            return;
        }
        valor = valor.replace(',', '.');
        rtAnt = Double.parseDouble(valor);
        valor = JOptionPane.showInputDialog(null, "Ingrese el valor de la relación de intalación nueva");
        if (valor == null) {
            return;
        }
        valor = valor.replace(',', '.');
        rtNuevo = Double.parseDouble(valor);
        double rt = (Double.parseDouble(tfRelTransmision.getText()) * rtNuevo) / rtAnt;
        DecimalFormat df = new DecimalFormat("#.###");
        tfRelTransmision.setText(df.format(rt).replace(',', '.'));
        rtAnt = rtNuevo;
    }//GEN-LAST:event_btnCambiarRTActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /**
         * Configura el Loock and Feel como el sistema operativo
         */
        try {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
             */
 /*   for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
          *      if ("Nimbus".equals(info.getName())) {
          *          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          *          break;
          *      }
          *   }
             */
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ppal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ppal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarRT;
    private javax.swing.JButton btnCancelarPotencia;
    private javax.swing.JButton btnModificarPotencia;
    private javax.swing.JDialog dlg_Archivos;
    private javax.swing.JFileChooser fc_Archivos;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem mnuAbrirArchivo;
    private javax.swing.JMenu mnuArchivo;
    private javax.swing.JMenuItem mnuGuardarArchivo;
    private javax.swing.JPanel pnlDatosEnsayo;
    private javax.swing.JPanel pnlDatosEnsayo1;
    private javax.swing.JTextField tfDatosFecha;
    private javax.swing.JTextField tfDatosHora;
    private javax.swing.JTextField tfDatosNombre;
    private javax.swing.JTextField tfDatosNuevosFecha;
    private javax.swing.JTextField tfDatosNuevosHora;
    private javax.swing.JTextField tfDatosNuevosNombre;
    private javax.swing.JTextField tfDatosNuevosNumero;
    private javax.swing.JTextField tfDatosNumero;
    private javax.swing.JTextField tfInercia;
    private javax.swing.JTextField tfRelTransmision;
    private javax.swing.JTextField tfRestarDesde;
    private javax.swing.JTextField tfRestarHasta;
    private javax.swing.JTextField tfRestarPotencia;
    private javax.swing.JTextField tfSumarDesde;
    private javax.swing.JTextField tfSumarHasta;
    private javax.swing.JTextField tfSumarPotencia;
    // End of variables declaration//GEN-END:variables
}
