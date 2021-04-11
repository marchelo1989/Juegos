/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.DAO;

import Cl.Burgos.Juegos.BD.BD;
import Cl.Burgos.Juegos.ENT.ClPs3;
import Cl.Burgos.Juegos.FUN.Log;
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
 * @author Marchelo
 */
public class DAOPs3 {
    public boolean sqlInsert(ClPs3 ps3) {
        Connection con = BD.getInstance().conectar();
        String insert = "insert into ps3(codigo,nombre,region,lenguaje,jugadores,disco,update,dlc,imagen) values (?,?,?,?,?,?,?,?,?)";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            
            File file = new File(ps3.getRuta());
            fi = new FileInputStream(file);

            ps = con.prepareStatement(insert);
            ps.setString(1, ps3.getCodigo());
            ps.setString(2, ps3.getNombre());
            ps.setString(3, ps3.getRegion());
            ps.setString(4, ps3.getIdiomas());
            ps.setInt(5, ps3.getJugadores());
            ps.setString(6, ps3.getDisco());
            ps.setBoolean(7, ps3.isUpdate());
            ps.setBoolean(8, ps3.isDlc());
            ps.setBinaryStream(9, fi);
            
            ps.execute();
            return true;
        }catch(Exception ex){
            Log.log(ex.getMessage());
            return false;
        }
    }
    
    public boolean sqlUpdate(ClPs3 clPs3){	
        Connection con = BD.getInstance().conectar();
        String insert = "update ps3 set codigo=?, nombre=?, region=?, lenguaje=?, jugadores=?, disco=?,update=?, dlc=?, imagen=? where IdPs3=?;";
        String insert2 = "update ps3 set codigo=?, nombre=?, region=?, lenguaje=?, jugadores=?, disco=?, update=?, dlc=? where IdPs3=?;";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            if(clPs3.getRuta().length()!=0){
                File file = new File(clPs3.getRuta());
                fi = new FileInputStream(file);

                ps = con.prepareStatement(insert);
                ps.setString(1, clPs3.getCodigo());
                ps.setString(2, clPs3.getNombre());
                ps.setString(3, clPs3.getRegion());
                ps.setString(4, clPs3.getIdiomas());
                ps.setInt(5, clPs3.getJugadores());
                ps.setString(6, clPs3.getDisco());
                ps.setBoolean(7, clPs3.isUpdate());
                ps.setBoolean(8, clPs3.isDlc());
                ps.setBinaryStream(9, fi);
                ps.setInt(10, clPs3.getId());
            }else{
                ps = con.prepareStatement(insert2);
                ps.setString(1, clPs3.getCodigo());
                ps.setString(2, clPs3.getNombre());
                ps.setString(3, clPs3.getRegion());
                ps.setString(4, clPs3.getIdiomas());
                ps.setInt(5, clPs3.getJugadores());
                ps.setString(6, clPs3.getDisco());
                ps.setBoolean(7, clPs3.isUpdate());
                ps.setBoolean(8, clPs3.isDlc());
                ps.setInt(9, clPs3.getId());
            }
            
            ps.executeUpdate();
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean sqlDelete(ClPs3 clPs3){
        Connection con = BD.getInstance().conectar();
        PreparedStatement ps = null;
        String stSql =  "delete from ps3 where IdPs3=?;";
        try {
            
             ps = con.prepareStatement(stSql);
            ps.setInt(1, clPs3.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
//            log.info(ex.getMessage());
        }
        return false;
    }
    
    public List<ClPs3> leerPs3() {
        List<ClPs3> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPs3,codigo,nombre,region,lenguaje,jugadores,disco,update,dlc,imagen from ps3 order by nombre asc;";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPs3 c = new ClPs3(rs.getInt("IdPs3"), rs.getString("codigo"), rs.getString("nombre"), 
                     rs.getString("region"), rs.getString("lenguaje"), rs.getInt("jugadores"), rs.getString("disco"), 
                     rs.getBoolean("update"), rs.getBoolean("dlc"),rs.getBytes("imagen"));
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
    
    public List<ClPs3> leerBuscar(ClPs3 clPs3) {
        List<ClPs3> lista=new ArrayList<>();
        String strConsulta;
        String resp=null;
        
        if(clPs3.getCodigo().length()>1){
            resp="codigo='"+clPs3.getCodigo()+"'";
        }
        if(clPs3.getNombre().length()>1){
            resp="nombre like '%"+clPs3.getNombre()+"%'";
        }
        if(clPs3.getCodigo().length()>1 && clPs3.getNombre().length()>1){
            resp="codigo='"+clPs3.getCodigo()+"' and nombre like '%"+clPs3.getNombre()+"%'";
        }
        strConsulta="select IdPs3,codigo,nombre,region,lenguaje,jugadores,disco,update,dlc,imagen from ps3 where "+resp+" order by nombre asc";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPs3 c = new ClPs3(rs.getInt("IdPs3"), rs.getString("codigo"), rs.getString("nombre"), 
                     rs.getString("region"), rs.getString("lenguaje"), rs.getInt("jugadores"), rs.getString("disco"),
                     rs.getBoolean("update"), rs.getBoolean("dlc"),rs.getBytes("imagen"));
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
    
    public List<ClPs3> leerPs32(ClPs3 clPs3) {
        List<ClPs3> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPs3,codigo,nombre,region,lenguaje,jugadores,disco,update,dlc,imagen from ps3 where IdPs3="+clPs3.getId();
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPs3 c = new ClPs3(rs.getInt("IdPs3"), rs.getString("codigo"), rs.getString("nombre"), 
                     rs.getString("region"), rs.getString("lenguaje"), rs.getInt("jugadores"), rs.getString("disco"), 
                     rs.getBoolean("update"), rs.getBoolean("dlc"),rs.getBytes("imagen"));
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
        strConsulta="select count(*) as cuantos from ps3 where codigo LIKE 'SCP3*';";
        
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
        strConsulta="select count(*) as cuantos from ps3;";
        
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
    
    public void CrearTablaPDFPS3(String dest,String nombreArc){
        try {
            // Creamos un documento pdf con iText
            PdfWriter pdfWriter = new PdfWriter(dest+"./"+nombreArc+".pdf");
            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            Document doc = new Document(pdfDoc, PageSize.LETTER);
            //Arriba,Abajo,derecha,izqueda
            doc.setMargins(80, 20, 20, 20);
            
            PdfFont font1 = PdfFontFactory.createFont(StandardFonts.COURIER);
//            PdfFont font2 = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
            
            Paragraph parrafo1 = new Paragraph(nombreArc).setFont(font1);
            // Cambiamos el tamaño de fuente del parrafo 2 lo hacemos mas pequeño
            parrafo1.setFontSize(12f);
            
            // Creamos unas tablas
            float[] anchos = {10f, 50f, 50f, 50f, 50f, 50f, 50f, 50f, 60f};
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
            tabla1.addCell("DLC");
            tabla1.addCell("Imagen");
            List<ClPs3> lista=new DAOPs3().leerPs3();
            for (int i = 0; i < lista.size(); i++) {
//                tabla1.addCell(Integer.toString(lista.get(i).getId()));
                tabla1.addCell(lista.get(i).getCodigo());
                tabla1.addCell(lista.get(i).getNombre());
                tabla1.addCell(lista.get(i).getRegion());
                tabla1.addCell(lista.get(i).getIdiomas());
                tabla1.addCell(Integer.toString(lista.get(i).getJugadores()));
                tabla1.addCell(lista.get(i).getDisco());
                tabla1.addCell(siyno(lista.get(i).isUpdate()));
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
            Logger.getLogger(DAOPs2.class.getName()).log(Level.SEVERE, null, ex);
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