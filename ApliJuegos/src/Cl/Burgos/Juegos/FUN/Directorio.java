/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.FUN;

import Cl.Burgos.Juegos.Main.ApliJuegos;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author march
 */
public class Directorio {
    
    //Variables del Log4j
//    static Logger log =Logger.getLogger(Directorio.class);
//    static String carpeta = "IMG";
    static String SO = System.getProperty("os.name");
    static String userDir = System.getProperty("user.home");
    
//    static String Url = "F:\\";
    static String Url=ApliJuegos.Url;
    static String carpeta1 = "IMG";
    static String carpeta2 = "PSX";
    static String carpeta3 = "PS2";
    static String carpeta4 = "PSP";
    static String carpeta5 = "PS3";
    static String carpeta6 = "PC";
    static String carpeta7 = "PS4";
    
    public static String selecDirectrorio(){
        JFileChooser fileChooser = new JFileChooser("F:\\");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(null);
//        System.out.println(fileChooser.getSelectedFile());
        //String url = "c:/Users/march/Desktop/";
        String url = fileChooser.getSelectedFile().toString();
        //url=url.replace('\\', '/');
        return url;
    }
    public static void abrirArchivo(String url) throws IOException{
        File objetofile = new File (url);
        Desktop.getDesktop().open(objetofile);
    }
    public static void crearDirec(){
        crearDirec(Url,carpeta1);
        crearDirec(Url+"/"+carpeta1, carpeta2);
        crearDirec(Url+"/"+carpeta1, carpeta3);
        crearDirec(Url+"/"+carpeta1, carpeta4);
        crearDirec(Url+"/"+carpeta1, carpeta5);
        crearDirec(Url+"/"+carpeta1, carpeta6);
        crearDirec(Url+"/"+carpeta1, carpeta7);
    }
    public static void crearDirec(String url,String carpeta){
        File directorio = new File(url +"/"+ carpeta );
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    }
    public static void crearDirecPre(){
        if(SO.startsWith("Windows")){
            File directorio = new File(Url +"/"+ carpeta1 ); 
            directorio.mkdir(); 
        }else{
            File directorio = new File(Url +"/"+ carpeta1); 
            directorio.mkdir();
        } 
    }
    public static void crearDirecPSX(){
        if(SO.startsWith("Windows")){
            File directorio = new File(Url +"/"+ carpeta1 +"/"+ carpeta2 ); 
            directorio.mkdir(); 
        }else{
            File directorio = new File(Url +"/"+ carpeta1 +"/"+ carpeta2 ); 
            directorio.mkdir();
        } 
    }
    public static void crearDirecPS2(){
        if(SO.startsWith("Windows")){
            File directorio = new File(Url +"/"+ carpeta1 +"/"+ carpeta3 ); 
            directorio.mkdir(); 
        }else{
            File directorio = new File(Url +"/"+ carpeta1 +"/"+ carpeta3 ); 
            directorio.mkdir();
        } 
    }
    public static void crearDirecPSP(){
        if(SO.startsWith("Windows")){
            File directorio = new File(Url +"/"+ carpeta1 +"/"+ carpeta4 ); 
            directorio.mkdir(); 
        }else{
            File directorio = new File(Url +"/"+ carpeta1 +"/"+ carpeta4 ); 
            directorio.mkdir();
        } 
    }
    public static void crearDirecPS3(){
        if(SO.startsWith("Windows")){
            File directorio = new File(Url +"/"+ carpeta1 +"/"+ carpeta5 ); 
            directorio.mkdir(); 
        }else{
            File directorio = new File(Url +"/"+ carpeta1 +"/"+ carpeta5 ); 
            directorio.mkdir();
        } 
    }
    public static void crearDirecPC(){
        if(SO.startsWith("Windows")){
            File directorio = new File(Url +"/"+ carpeta1 +"/"+ carpeta6 ); 
            directorio.mkdir(); 
        }else{
            File directorio = new File(Url +"/"+ carpeta1 +"/"+ carpeta6 ); 
            directorio.mkdir();
        } 
    }
    public static void crearDirecPS4(){
        if(SO.startsWith("Windows")){
            File directorio = new File(Url +"/"+ carpeta1 +"/"+ carpeta7 ); 
            directorio.mkdir(); 
        }else{
            File directorio = new File(Url +"/"+ carpeta1 +"/"+ carpeta7 ); 
            directorio.mkdir();
        } 
    }
    public static void abrirDirecPre() throws IOException{
        if(SO.startsWith("Windows")){
            File directorio = new File(Url +"/"+ carpeta1 );
            Desktop.getDesktop().open(directorio);
        }else{
            File directorio = new File(userDir +"/"+ carpeta2 );
            Desktop.getDesktop().open(directorio);
        }
    }
    public static File selectDirecPre(){
        File f = null;
        if(SO.startsWith("Windows")){
            String url = Url +"/"+ carpeta1 +"/"+ carpeta2 ;
            f = new File(url);
        }else{
            String url = userDir +"/"+carpeta1;
            f = new File(url);
        }
        return f;
    }
    
    
}
