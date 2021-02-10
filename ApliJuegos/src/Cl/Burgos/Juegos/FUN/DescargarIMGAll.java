/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.FUN;

import Cl.Burgos.Juegos.DAO.DAOPs2;
import Cl.Burgos.Juegos.DAO.DAOPs3;
import Cl.Burgos.Juegos.DAO.DAOPsp;
import Cl.Burgos.Juegos.DAO.DAOPsx;
import Cl.Burgos.Juegos.ENT.ClPs2;
import Cl.Burgos.Juegos.ENT.ClPs3;
import Cl.Burgos.Juegos.ENT.ClPsp;
import Cl.Burgos.Juegos.ENT.ClPsx;
import Cl.Burgos.Juegos.Main.ApliJuegos;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;

/**
 *
 * @author Marchelo
 */
public class DescargarIMGAll {
    String url = ApliJuegos.Url+"\\IMG\\";
    int numPSX=0;
    int numPS2=0;
    int numPSP=0;
    int numPS3=0;
    public void DescargarImgPS1(){
        DAOPsx dAOPsx = new DAOPsx();
        String nombre = null;
        Image img = null;
        try {
            List<ClPsx> datosCliente = dAOPsx.leerPsx();
            numPSX = datosCliente.size();
            System.out.println("Juegos de PSX= "+numPSX);
            for (int i = 0; i < datosCliente.size(); i++) {
                System.out.println("Juegos Descargados= "+i);
//                id = Integer.parseInt(String.valueOf(datosCliente.get(i).getId()));
//                nombre=datosCliente.get(i).getCodigo()+"-"+datosCliente.get(i).getNombre()+".png";
                nombre = datosCliente.get(i).getCodigo() + ".jpg";
                byte[] bi = datosCliente.get(i).getImagen();
                BufferedImage image = null;
                InputStream in = new ByteArrayInputStream(bi);
                image = ImageIO.read(in);
                img = image;
                String aux = nombre;
                StringTokenizer token = new StringTokenizer(aux, ".");
                token.nextToken();
                String formato = token.nextToken();
                ImageIO.write((RenderedImage) img, formato, new File(url+"PSX" + "\\" + nombre));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
//                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DescargarImgPS2(){
        DAOPs2 dAOPs2 = new DAOPs2();
        String nombre = null;
        Image img = null;
        try {
            List<ClPs2> datosCliente = dAOPs2.leerPs2();
            numPS2 = datosCliente.size();
            System.out.println("Juegos de PS2= "+numPS2);
            for (int i = 0; i < datosCliente.size(); i++) {
                System.out.println("Juegos Descargados= "+i);
//                id = Integer.parseInt(String.valueOf(datosCliente.get(i).getId()));
//                nombre=datosCliente.get(i).getCodigo()+"-"+datosCliente.get(i).getNombre()+".png";
                nombre = datosCliente.get(i).getCodigo() + ".jpg";
                byte[] bi = datosCliente.get(i).getImagen();
                BufferedImage image = null;
                InputStream in = new ByteArrayInputStream(bi);
                image = ImageIO.read(in);
                img = image;
                String aux = nombre;
                StringTokenizer token = new StringTokenizer(aux, ".");
                token.nextToken();
                String formato = token.nextToken();
                ImageIO.write((RenderedImage) img, formato, new File(url+"PS2" + "\\" + nombre));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
//                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DescargarImgPSP(){
        DAOPsp dAOPsp = new DAOPsp();
        String nombre = null;
        Image img = null;
        try {
            List<ClPsp> datosCliente = dAOPsp.leerPsp();
            numPSP = datosCliente.size();
            System.out.println("Juegos de PSP= "+numPSP);
            for (int i = 0; i < datosCliente.size(); i++) {
                System.out.println("Juegos Descargados= "+i);
//                id = Integer.parseInt(String.valueOf(datosCliente.get(i).getId()));
//                nombre=datosCliente.get(i).getCodigo()+"-"+datosCliente.get(i).getNombre()+".png";
                nombre = datosCliente.get(i).getCodigo() + ".jpg";
                byte[] bi = datosCliente.get(i).getImagen();
                BufferedImage image = null;
                InputStream in = new ByteArrayInputStream(bi);
                image = ImageIO.read(in);
                img = image;
                String aux = nombre;
                StringTokenizer token = new StringTokenizer(aux, ".");
                token.nextToken();
                String formato = token.nextToken();
                ImageIO.write((RenderedImage) img, formato, new File(url+"PSP" + "\\" + nombre));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
//                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DescargarImgPS3(){
        DAOPs3 dAOPs3 = new DAOPs3();
        String nombre = null;
        Image img = null;
        try {
            List<ClPs3> datosCliente = dAOPs3.leerPs3();
            numPS3 = datosCliente.size();
            System.out.println("Juegos de PS3= "+numPS3);
            for (int i = 0; i < datosCliente.size(); i++) {
                System.out.println("Juegos Descargados= "+i);
//                id = Integer.parseInt(String.valueOf(datosCliente.get(i).getId()));
//                nombre=datosCliente.get(i).getCodigo()+"-"+datosCliente.get(i).getNombre()+".png";
                nombre = datosCliente.get(i).getCodigo() + ".jpg";
                byte[] bi = datosCliente.get(i).getImagen();
                BufferedImage image = null;
                InputStream in = new ByteArrayInputStream(bi);
                image = ImageIO.read(in);
                img = image;
                String aux = nombre;
                StringTokenizer token = new StringTokenizer(aux, ".");
                token.nextToken();
                String formato = token.nextToken();
                ImageIO.write((RenderedImage) img, formato, new File(url+"PS3" + "\\" + nombre));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
//                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int TotalJuegos(){
        int total = numPSX+numPS2+numPSP+numPS3;
        return total;
    }
}
