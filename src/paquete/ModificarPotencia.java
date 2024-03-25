
package paquete;

import java.util.Arrays;

/**
 *
 * @author Usuario
 */
public class ModificarPotencia {
    
    public double[] Operacion(double rpmMin, double rpmMax, double[] rev, double[] pot, double valorPot){
        int posMin=Arrays.binarySearch(rev, rpmMin);
        int posMax=Arrays.binarySearch(rev, rpmMax);
        if(posMin<0){
            posMin=Math.abs(posMin)-2;
        }
        if(posMax<0){
            posMax=Math.abs(posMax)-2;
        }
        for (int i = posMin; i <=posMax; i++) {
            pot[i]+=valorPot;
        }
        return pot;
    }
 
    public double[] CorreccionPotencia(double[] pot, double facCorr){
        for (int i = 0; i < pot.length; i++) {
            pot[i]=pot[i]/facCorr;
        }
        return pot;
    }
    
    public double[] MomentoInercia(double[] pot, double inerciaAnt, double inerciaNueva){
        for (int i = 0; i < pot.length; i++) {
            pot[i]=pot[i]*(inerciaNueva/inerciaAnt);
        }
        return pot;
    }
    
    public double[] RelacionTransmision(double[] pot, double rtAnt, double rtNueva){
        double factorRT=Math.pow(rtAnt, 2)/Math.pow(rtNueva, 2);
        for (int i = 0; i < pot.length; i++) {
            pot[i]=pot[i]*factorRT;
        }
        return pot;
    }
    
    public String RelacionTransmision(String muestra, String rt){
        String arr[];
        arr = muestra.split(", ");
        String valor = "";
        for (int i = 0; i < arr.length - 1; i++) {
            valor = valor +rt + ", ";
        }
        valor = valor + rt;
        return valor;
    }
}
