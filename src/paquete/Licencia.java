/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Licencia {

    public String Maquina() {
        String clave = "";
        String mensaje[] = new String[5];
        mensaje[0] = "wmic bios get version";
        mensaje[1] = "wmic bios get smbiosbiosversion";
        mensaje[2] = "wmic bios get smbiosmajorversion";
        mensaje[3] = "wmic bios get smbiosminorversion";
        mensaje[4] = "wmic bios get releasedate";
        Process Proceso = null;
        InputStream PIS;
        InputStreamReader ISR;
        BufferedReader reader;
        for (int i = 0; i < mensaje.length; i++) {
            try {
                Proceso = Runtime.getRuntime().exec(mensaje[i]);
                PIS = Proceso.getInputStream();
                ISR = new InputStreamReader(PIS);
                reader = new BufferedReader(ISR);
                String name, str = null;
                while ((name = reader.readLine()) != null) {
                    str = str + name;
                }
                String arr[], linea;
                arr = str.split(" ");
                linea = arr[arr.length - 1];
                if (i >= (mensaje.length - 1)) {
                    linea = linea.substring(0, 8);
                }
                clave = clave + linea;
            } catch (IOException ex) {
                Logger.getLogger(Licencia.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        Proceso.destroy();
        return clave;
    }

    public File ArchivoLicencia() {
        File archivo;
        String dir;
//        boolean existe = false;
        dir = System.getProperty("user.dir");
        archivo = new File(dir + "/Licencia.txt");
        if (!archivo.exists()) {
            archivo = new File(getClass().getResource("/paquete/Licencia.txt").getFile());
        }
        return archivo;
    }

    public String[] LeerLicencia() {
        try {
            String linea = "", arr[];
            FileReader abrir = null;
            BufferedReader miBuffer = null;
            abrir = new FileReader(ArchivoLicencia().getAbsolutePath());
            miBuffer = new BufferedReader(abrir);
            linea = miBuffer.readLine();
            arr = linea.split("-");
            return arr;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Licencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Licencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean Verificar() {
        byte[] byt = Maquina().getBytes();
        ArrayList<Integer> Vector = new ArrayList();
        switch (Math.floorMod(byt.length, 3)) {
            case 0:
                for (int i = 0; i < byt.length; i += 3) {
                    Vector.add(byt[i] * (int) Math.pow(2.0, 24.0) + byt[i + 1] * (int) Math.pow(2.0, 16.0) + byt[i + 2] * (int) Math.pow(2.0, 8.0) + 0xAA);
                }
                break;
            case 1:
                for (int i = 0; i < byt.length - 2; i += 3) {
                    Vector.add(byt[i] * (int) Math.pow(2.0, 24.0) + byt[i + 1] * (int) Math.pow(2.0, 16.0) + byt[i + 2] * (int) Math.pow(2.0, 8.0) + 0xAA);
                }
                Vector.add(byt[byt.length - 1] * (int) Math.pow(2.0, 24.0) + 0xAAAAAA);
                break;
            case 2:
                for (int i = 0; i < byt.length - 2; i += 3) {
                    Vector.add(byt[i] * (int) Math.pow(2.0, 24.0) + byt[i + 1] * (int) Math.pow(2.0, 16.0) + byt[i + 2] * (int) Math.pow(2.0, 8.0));
                }
                Vector.add(byt[byt.length - 2] * (int) Math.pow(2.0, 24.0) + byt[byt.length - 1] * (int) Math.pow(2.0, 16.0) + 0xAAAA);
                break;
        }
        int Polinomio = 0xAA;
        int[] Resto = new int[Vector.size()];
        for (int i = 0; i < Vector.size(); i++) {
            Resto[i] = Math.floorMod(Vector.get(i), Polinomio);
        }
        String arr[];
        arr = LeerLicencia();
        if (Resto.length != arr.length) {
            return false;
        } else {
            boolean igual = true;
            for (int i = 0; i < Resto.length; i++) {
                if (Resto[i] != Integer.parseInt(arr[i])) {
                    igual = false;
                }
            }
            if (!igual) {
                return false;
            } else {
                return true;
            }
        }
    }
}
