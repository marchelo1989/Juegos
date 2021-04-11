/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.DAO;

import Cl.Burgos.Juegos.BD.BD;
import Cl.Burgos.Juegos.ENT.ClPsx;
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
public class DAOPsx {
    public boolean sqlInsert(ClPsx psx) {
        Connection con = BD.getInstance().conectar();
        String insert = "insert into psx(codigo,nombre,region,lenguaje,jugadores,disco,imagen) values (?,?,?,?,?,?,?)";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            File file = new File(psx.getRuta());
            fi = new FileInputStream(file);
            
            ps = con.prepareStatement(insert);
            ps.setString(1, psx.getCodigo());
            ps.setString(2, psx.getNombre());
            ps.setString(3, psx.getRegion());
            ps.setString(4, psx.getIdiomas());
            ps.setInt(5, psx.getJugadores());
            ps.setString(6, psx.getDisco());
            ps.setBinaryStream(7, fi);
            
            ps.execute();
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean sqlUpdate(ClPsx clPsx){	
        Connection con = BD.getInstance().conectar();
        String insert = "update psx set codigo=?, nombre=?, region=?, lenguaje=?, jugadores=?, disco=?, imagen=? where IdPsx=?;";
        String insert2 = "update psx set codigo=?, nombre=?, region=?, lenguaje=?, jugadores=?, disco=? where IdPsx=?;";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            if(clPsx.getRuta().length()!=0){
                File file = new File(clPsx.getRuta());
                fi = new FileInputStream(file);

                ps = con.prepareStatement(insert);
                ps.setString(1, clPsx.getCodigo());
                ps.setString(2, clPsx.getNombre());
                ps.setString(3, clPsx.getRegion());
                ps.setString(4, clPsx.getIdiomas());
                ps.setInt(5, clPsx.getJugadores());
                ps.setString(6, clPsx.getDisco());
                ps.setBinaryStream(7, fi);
                ps.setInt(8, clPsx.getId());
            }else{
                ps = con.prepareStatement(insert2);
                ps.setString(1, clPsx.getCodigo());
                ps.setString(2, clPsx.getNombre());
                ps.setString(3, clPsx.getRegion());
                ps.setString(4, clPsx.getIdiomas());
                ps.setInt(5, clPsx.getJugadores());
                ps.setString(6, clPsx.getDisco());
                ps.setInt(7, clPsx.getId());
            }
            
            ps.executeUpdate();
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean sqlDelete(ClPsx clPsx){
        Connection con = BD.getInstance().conectar();
        PreparedStatement ps = null;
        String stSql =  "delete from psx where IdPsx=?;";
        try {
            
             ps = con.prepareStatement(stSql);
            ps.setInt(1, clPsx.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
//            log.info(ex.getMessage());
        }
        return false;
    }
    
    public List<ClPsx> leerPsx() {
        List<ClPsx> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPsx,codigo,nombre,region,lenguaje,jugadores,disco,imagen from psx order by nombre asc;";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPsx c = new ClPsx(rs.getInt("IdPsx"), rs.getString("codigo"), rs.getString("nombre"), 
                     rs.getString("region"), rs.getString("lenguaje"), rs.getInt("jugadores"), rs.getString("disco"), rs.getBytes("imagen"));
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
    
    public List<ClPsx> leerPsx2(ClPsx clPsx) {
        List<ClPsx> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPsx,codigo,nombre,region,lenguaje,jugadores,disco,imagen from psx where IdPsx="+clPsx.getId();
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPsx c = new ClPsx(rs.getInt("IdPsx"), rs.getString("codigo"), rs.getString("nombre"), 
                     rs.getString("region"), rs.getString("lenguaje"), rs.getInt("jugadores"), rs.getString("disco"), rs.getBytes("imagen"));
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
    public List<ClPsx> leerBuscar(ClPsx clPsx) {
        List<ClPsx> lista=new ArrayList<>();
        String strConsulta;
        String resp=null;
        
        if(clPsx.getCodigo().length()>1){
            resp="codigo='"+clPsx.getCodigo()+"'";
        }
        if(clPsx.getNombre().length()>1){
            resp="nombre like '%"+clPsx.getNombre()+"%'";
        }
        if(clPsx.getCodigo().length()>1 && clPsx.getNombre().length()>1){
            resp="codigo='"+clPsx.getCodigo()+"' and nombre like '%"+clPsx.getNombre()+"%'";
        }
//        strConsulta="select IdPsx,codigo,nombre,region,lenguaje,jugadores,disco,imagen from psx where codigo='"+clPsx.getCodigo()+"' or nombre like '%"+clPsx.getNombre()+"%' order by nombre asc";
        strConsulta="select IdPsx,codigo,nombre,region,lenguaje,jugadores,disco,imagen from psx where "+resp+" order by nombre asc";
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             System.out.println(rs.getString("nombre"));
             ClPsx c = new ClPsx(rs.getInt("IdPsx"), rs.getString("codigo"), rs.getString("nombre"), 
                     rs.getString("region"), rs.getString("lenguaje"), rs.getInt("jugadores"), rs.getString("disco"), rs.getBytes("imagen"));
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
        strConsulta="select count(*) as cuantos from psx where codigo LIKE 'SCPX*';";
        
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
        strConsulta="select count(*) as cuantos from psx;";
        
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
    
    public void CrearTablaPDFPSX(String dest,String nombreArc){
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
            float[] anchos = {10f, 50f, 50f, 50f, 50f, 50f, 60f};
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
            tabla1.addCell("Imagen");
            List<ClPsx> lista=new DAOPsx().leerPsx();
            for (int i = 0; i < lista.size(); i++) {
//                tabla1.addCell(Integer.toString(lista.get(i).getId()));
                tabla1.addCell(lista.get(i).getCodigo());
                tabla1.addCell(lista.get(i).getNombre());
                tabla1.addCell(lista.get(i).getRegion());
                tabla1.addCell(lista.get(i).getIdiomas());
                tabla1.addCell(Integer.toString(lista.get(i).getJugadores()));
                tabla1.addCell(lista.get(i).getDisco());
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
            Logger.getLogger(DAOPsx.class.getName()).log(Level.SEVERE, null, ex);
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
}
