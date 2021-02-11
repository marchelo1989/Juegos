/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.Main;

import Cl.Burgos.Juegos.FUN.Actualizacion;
import Cl.Burgos.Juegos.FUN.Confi;
import Cl.Burgos.Juegos.FUN.DescargarIMGAll;
import Cl.Burgos.Juegos.FUN.Directorio;
import Cl.Burgos.Juegos.GUI.FrDescargarImg;
import Cl.Burgos.Juegos.GUI.FrHome;
import javax.swing.JOptionPane;

/**
 *
 * @author march
 */
public class ApliJuegos {

    public static String Url=System.getProperty("user.dir");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Directorio.crearDirec();
        if(buscarUpdate()==false){
            new FrHome().setVisible(true);
        }
    }
    public static boolean buscarUpdate(){
        boolean resp;
        if(Actualizacion.verificarConexion()){
            if(Actualizacion.obtenerVersion().equals(Confi.Version)){
                resp=false;
            }else{
                resp=true;
                int respu = JOptionPane.showConfirmDialog(null, "Version "+Actualizacion.obtenerVersion()+ " Diponible \n¿Desea Descargar?");
                if(respu==0){
                    JOptionPane.showMessageDialog(null, "Descargando Update \nEspere Mensaje");
                    Actualizacion.descargarUpdate();
                    resp=true;
                }else{
                    resp=false;
}
            }
        }else{
            resp=false;
        }
        return resp;
    }
}
