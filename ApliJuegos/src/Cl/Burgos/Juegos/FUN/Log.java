/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.FUN;

import Cl.Burgos.Juegos.Main.ApliJuegos;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author marchelo
 */
public class Log {
    String url = ApliJuegos.Url;
    public static void log(String msg){
//        String url="c:/Users/march/Desktop/myfile.log";
//        String url="f:/myfile.log";
        String url=Directorio.selectDirecPre()+"/Log.log";
        
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(url, true)))) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            out.println(dateFormat.format(date) + ":"+ msg + "\r\n");
            
            AbrirLog();
//            Directorio.abrirArchivo(url);
            
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
            JOptionPane.showMessageDialog(null,"Hubo un error"+e);
        }
    }
    
    public static void AbrirLog(){
        //Archivo Log
        String url=Directorio.selectDirecPre()+"/Log.log";
        
        try {
            Directorio.abrirArchivo(url);
            Directorio.abrirDirecPre();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Hubo un error"+ex);
        }
        
    }
}
