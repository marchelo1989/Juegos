/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.DAO;

import Cl.Burgos.Juegos.BD.BD;
import Cl.Burgos.Juegos.ENT.ClPs4;
import Cl.Burgos.Juegos.FUN.Log;
//import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author march
 */
public class DAOPs4 {
    public boolean sqlInsert(ClPs4 ps4) {
        Connection con = BD.getInstance().conectar();
        String insert = "insert into ps4(codigo,nombre,region,lenguaje,jugadores,disco,update,patch,dlc,imagen) values (?,?,?,?,?,?,?,?,?,?)";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            
            File file = new File(ps4.getRuta());
            fi = new FileInputStream(file);

            ps = con.prepareStatement(insert);
            ps.setString(1, ps4.getCodigo());
            ps.setString(2, ps4.getNombre());
            ps.setString(3, ps4.getRegion());
            ps.setString(4, ps4.getIdiomas());
            ps.setInt(5, ps4.getJugadores());
            ps.setString(6, ps4.getDisco());
            ps.setBoolean(7, ps4.isUpdate());
            ps.setString(8, ps4.getPatch());
            ps.setBoolean(9, ps4.isDlc());
            ps.setBinaryStream(10, fi);
            
            ps.execute();
            return true;
        }catch(Exception ex){
            Log.log(ex.getMessage());
            return false;
        }
    }
    
    public boolean sqlUpdate(ClPs4 clPs4){	
        Connection con = BD.getInstance().conectar();
        String insert = "update ps4 set codigo=?, nombre=?, region=?, lenguaje=?, jugadores=?, disco=?, imagen=?, update=?, patch=?, dlc=? where IdPs4=?;";
        String insert2 = "update ps4 set codigo=?, nombre=?, region=?, lenguaje=?, jugadores=?, disco=?, update=?, patch=?, dlc=? where IdPs4=?;";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            if(clPs4.getRuta().length()!=0){
                File file = new File(clPs4.getRuta());
                fi = new FileInputStream(file);

                ps = con.prepareStatement(insert);
                ps.setString(1, clPs4.getCodigo());
                ps.setString(2, clPs4.getNombre());
                ps.setString(3, clPs4.getRegion());
                ps.setString(4, clPs4.getIdiomas());
                ps.setInt(5, clPs4.getJugadores());
                ps.setString(6, clPs4.getDisco());
                ps.setBinaryStream(7, fi);
                ps.setBoolean(8, clPs4.isUpdate());
                ps.setString(9, clPs4.getPatch());
                ps.setBoolean(10, clPs4.isDlc());
                ps.setInt(11, clPs4.getId());
            }else{
                ps = con.prepareStatement(insert2);
                ps.setString(1, clPs4.getCodigo());
                ps.setString(2, clPs4.getNombre());
                ps.setString(3, clPs4.getRegion());
                ps.setString(4, clPs4.getIdiomas());
                ps.setInt(5, clPs4.getJugadores());
                ps.setString(6, clPs4.getDisco());
                ps.setBoolean(7, clPs4.isUpdate());
                ps.setString(8, clPs4.getPatch());
                ps.setBoolean(9, clPs4.isDlc());
                ps.setInt(10, clPs4.getId());
            }
            
            ps.executeUpdate();
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean sqlDelete(ClPs4 clPs4){
        Connection con = BD.getInstance().conectar();
        PreparedStatement ps = null;
        String stSql =  "delete from ps4 where IdPs4=?;";
        try {
            
             ps = con.prepareStatement(stSql);
            ps.setInt(1, clPs4.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
//            log.info(ex.getMessage());
        }
        return false;
    }
    
    public List<ClPs4> leerPs4() {
        List<ClPs4> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPs4,codigo,nombre,region,lenguaje,jugadores,disco,update,patch,dlc,imagen from ps4 order by nombre asc;";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPs4 c = new ClPs4(rs.getInt("IdPs4"), rs.getString("codigo"), rs.getString("nombre"), 
                     rs.getString("region"), rs.getString("lenguaje"), rs.getInt("jugadores"), rs.getString("disco"), 
                     rs.getBoolean("update"), rs.getString("patch"), rs.getBoolean("dlc"),rs.getBytes("imagen"));
              lista.add(c);
         }
         
        } catch (SQLException ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
        } catch (Exception ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
        }
        return lista;
    }
    
    public List<ClPs4> leerBuscar(ClPs4 clPs4) {
        List<ClPs4> lista=new ArrayList<>();
        String strConsulta;
        String resp=null;
        
        if(clPs4.getCodigo().length()>1){
            resp="codigo='"+clPs4.getCodigo()+"'";
        }
        if(clPs4.getNombre().length()>1){
            resp="nombre like '%"+clPs4.getNombre()+"%'";
        }
        if(clPs4.getCodigo().length()>1 && clPs4.getNombre().length()>1){
            resp="codigo='"+clPs4.getCodigo()+"' and nombre like '%"+clPs4.getNombre()+"%'";
        }
        strConsulta="select IdPs4,codigo,nombre,region,lenguaje,jugadores,disco,update,patch,dlc,imagen from ps4 where "+resp+" order by nombre asc";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPs4 c = new ClPs4(rs.getInt("IdPs4"), rs.getString("codigo"), rs.getString("nombre"), 
                     rs.getString("region"), rs.getString("lenguaje"), rs.getInt("jugadores"), rs.getString("disco"),
                     rs.getBoolean("update"), rs.getString("patch"), rs.getBoolean("dlc"),rs.getBytes("imagen"));
              lista.add(c);
         }
         
        } catch (SQLException ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
        } catch (Exception ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
        }
        return lista;
    }
    
    public List<ClPs4> leerPs42(ClPs4 clPs4) {
        List<ClPs4> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPs4,codigo,nombre,region,lenguaje,jugadores,disco,update,patch,dlc,imagen from ps4 where IdPs4="+clPs4.getId();
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPs4 c = new ClPs4(rs.getInt("IdPs4"), rs.getString("codigo"), rs.getString("nombre"), 
                     rs.getString("region"), rs.getString("lenguaje"), rs.getInt("jugadores"), rs.getString("disco"), 
                     rs.getBoolean("update"), rs.getString("patch"), rs.getBoolean("dlc"),rs.getBytes("imagen"));
              lista.add(c);
         }
         
        } catch (SQLException ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
        } catch (Exception ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
        }
        return lista;
    }
    
    public int Cuantos() {
        String strConsulta;
        int num = 0;
        strConsulta="select count(*) as cuantos from ps4 where codigo LIKE 'SCP4*';";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
//         if(rs==null)return null;
         while(rs.next()){
             num=rs.getInt("cuantos");
         }
         
        } catch (SQLException ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
        } catch (Exception ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
        }
        return num;
    }
    
    public int CuantosTotal() {
        String strConsulta;
        int num = 0;
        strConsulta="select count(*) as cuantos from ps4;";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
//         if(rs==null)return null;
         while(rs.next()){
             num=rs.getInt("cuantos");
         }
         
        } catch (SQLException ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
        } catch (Exception ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
        }
        return num;
    }
    
    public void CrearTablaPDFPS4(String dest,String nombreArc){
        try {
            // Creamos un documento pdf con iText
            PdfWriter pdfWriter = new PdfWriter(dest+"./"+nombreArc+".pdf");
            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            Document doc = new Document(pdfDoc, PageSize.LETTER);
            //Arriba,Abajo,derecha,izqueda
            doc.setMargins(80, 20, 20, 20);
            
            PdfFont font1 = PdfFontFactory.createFont(StandardFonts.TIMES_ITALIC);
//            PdfFont font2 = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
            
            Paragraph parrafo1 = new Paragraph(nombreArc).setFont(font1);
            // Cambiamos el tamaño de fuente del parrafo 2 lo hacemos mas pequeño
            parrafo1.setFontSize(12f);
            
            // Creamos unas tablas
            float[] anchos = {10f, 50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f, 60f};
            Table tabla1 = new Table(anchos);
            Table tabla2 = new Table(anchos);
            
            // Agregamos contenido a las tablas
//            tabla1.addCell("ID");
            tabla1.addCell("Codigo");
            tabla1.addCell("Nombre");
            tabla1.addCell("Region");
            tabla1.addCell("Idioma");
            tabla1.addCell("Player");
            tabla1.addCell("Disco");
            tabla1.addCell("Update");
            tabla1.addCell("Patch");
            tabla1.addCell("DLC");
            tabla1.addCell("Imagen");
            List<ClPs4> lista=new DAOPs4().leerPs4();
            for (int i = 0; i < lista.size(); i++) {
//                tabla1.addCell(Integer.toString(lista.get(i).getId()));
                tabla1.addCell(lista.get(i).getCodigo());
                tabla1.addCell(lista.get(i).getNombre());
                tabla1.addCell(lista.get(i).getRegion());
                tabla1.addCell(lista.get(i).getIdiomas());
                tabla1.addCell(Integer.toString(lista.get(i).getJugadores()));
                tabla1.addCell(lista.get(i).getDisco());
                tabla1.addCell(siyno(lista.get(i).isUpdate()));
                tabla1.addCell(lista.get(i).getPatch());
                tabla1.addCell(siyno(lista.get(i).isDlc()));
                Image img = new Image(ImageDataFactory.create(lista.get(i).getImagen()));
                img.scaleToFit(60, 60);
                tabla1.addCell(img);
                
            }
            
            Paragraph parrafo2 = new Paragraph("Comentario:");
            // Cambiamos el tamaño de fuente del parrafo 2 lo hacemos mas pequeño
            parrafo2.setFontSize(12f);
            
            doc.add(parrafo1);
            
            doc.add(tabla1);
            doc.add(parrafo2);
            doc.add(tabla2);
            
            doc.close();
            
        } catch (IOException ex) {
            Logger.getLogger(DAOPs4.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                JOptionPane.showMessageDialog(null, "Archivo Creado");
                File objetofile = new File (dest+"./"+nombreArc+".pdf");
                Desktop.getDesktop().open(objetofile);
            } catch (IOException ex) {
                Logger.getLogger(DAOPsx.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
    }
    public String siyno(boolean b){
        String rep="";
        if(b==true){ rep="Si";}
        if(b==false){ rep="No";}
        return rep;
    }
}
