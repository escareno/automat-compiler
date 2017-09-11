 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automat_compiler;

import java.awt.List;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Alma
 */

public class OpenFile {
    
    Collection<String> aux=new LinkedList<>();
    
     public String AbrirArchivo() {
        String tempo="", temp2 = "";
        int i = 0;
        JFileChooser buscar = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos txt", "txt");
        buscar.setFileFilter(filter);
        buscar.showOpenDialog(buscar);

        try {
            String destino = buscar.getSelectedFile().getAbsolutePath();
            //Abrir
            FileInputStream archivo = new FileInputStream(destino);
            //Lectura
            try ( //Entrada
                    DataInputStream entrada = new DataInputStream(archivo)) {
                //Lectura
                StringBuilder str = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(entrada));
                while ((tempo = br.readLine()) != null) {
                    temp2 += tempo+"\n";                                      
                }              
                                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Al Abrir El Archivo: " +e);
        }
        return temp2;
    }                                   
}