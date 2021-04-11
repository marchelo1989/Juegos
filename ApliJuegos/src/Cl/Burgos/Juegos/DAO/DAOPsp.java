/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.DAO;

import Cl.Burgos.Juegos.BD.BD;
import Cl.Burgos.Juegos.ENT.ClPsp;
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
public class DAOPsp {
    public boolean sqlInsert(ClPsp psp) {
        Connection con = BD.getInstance().conectar();
        String insert = "insert into psp(codigo,nombre,region,lenguaje,jugadores,disco,imagen) values (?,?,?,?,?,?,?)";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            File file = new File(psp.getRuta());
            fi = new FileInputStream(file);
            
            ps = con.prepareStatement(insert);
            ps.setString(1, psp.getCodigo());
            ps.setString(2, psp.getNombre());
            ps.setString(3, psp.getRegion());
            ps.setString(4, psp.getIdiomas());
            ps.setInt(5, psp.getJugadores());
            ps.setString(6, psp.getDisco());
            ps.setBinaryStream(7, fi);
            
            ps.execute();
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean sqlUpdate(ClPsp clPsp){	
        Connection con = BD.getInstance().conectar();
        String insert = "update psp set codigo=?, nombre=?, region=?, lenguaje=?, jugadores=?, disco=?, imagen=? where IdPsp=?;";
        String insert2 = "update psp set codigo=?, nombre=?, region=?, lenguaje=?, jugadores=?, disco=? where IdPsp=?;";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            if(clPsp.getRuta().length()!=0){
                File file = new File(clPsp.getRuta());
                fi = new FileInputStream(file);

                ps = con.prepareStatement(insert);
                ps.setString(1, clPsp.getCodigo());
                ps.setString(2, clPsp.getNombre());
                ps.setString(3, clPsp.getRegion());
                ps.setString(4, clPsp.getIdiomas());
                ps.setInt(5, clPsp.getJugadores());
                ps.setString(6, clPsp.getDisco());
                ps.setBinaryStream(7, fi);
                ps.setInt(8, clPsp.getId());
            }else{
                ps = con.prepareStatement(insert2);
                ps.setString(1, clPsp.getCodigo());
                ps.setString(2, clPsp.getNombre());
                ps.setString(3, clPsp.getRegion());
                ps.setString(4, clPsp.getIdiomas());
                ps.setInt(5, clPsp.getJugadores());
                ps.setString(6, clPsp.getDisco());
                ps.setInt(7, clPsp.getId());
            }
            
            ps.executeUpdate();
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean sqlDelete(ClPsp clPsp){
        Connection con = BD.getInstance().conectar();
        PreparedStatement ps = null;
        String stSql =  "delete from psp where IdPsp=?;";
        try {
            
             ps = con.prepareStatement(stSql);
            ps.setInt(1, clPsp.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
//            log.info(ex.getMessage());
        }
        return false;
    }
    
    public List<ClPsp> leerPsp() {
        List<ClPsp> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPsp,codigo,nombre,region,lenguaje,jugadores,disco,imagen from psp order by nombre asc;";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPsp c = new ClPsp(rs.getInt("IdPsp"), rs.getString("codigo"), rs.getString("nombre"), 
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
    
    public List<ClPsp> leerPsp2(ClPsp clPsx) {
        List<ClPsp> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPsp,codigo,nombre,region,lenguaje,jugadores,disco,imagen from psp where IdPsp="+clPsx.getId();
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPsp c = new ClPsp(rs.getInt("IdPsp"), rs.getString("codigo"), rs.getString("nombre"), 
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
    
    public List<ClPsp> leerBuscar(ClPsp clPsp) {
        List<ClPsp> lista=new ArrayList<>();
        String strConsulta;
        String resp=null;
        
        if(clPsp.getCodigo().length()>1){
            resp="codigo='"+clPsp.getCodigo()+"'";
        }
        if(clPsp.getNombre().length()>1){
            resp="nombre like '%"+clPsp.getNombre()+"%'";
        }
        if(clPsp.getCodigo().length()>1 && clPsp.getNombre().length()>1){
            resp="codigo='"+clPsp.getCodigo()+"' and nombre like '%"+clPsp.getNombre()+"%'";
        }
        strConsulta="select IdPsp,codigo,nombre,region,lenguaje,jugadores,disco,imagen from psp where "+resp+" order by nombre asc";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             System.out.println(rs.getString("nombre"));
             ClPsp c = new ClPsp(rs.getInt("IdPsp"), rs.getString("codigo"), rs.getString("nombre"), 
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
        strConsulta="select count(*) as cuantos from psp where codigo LIKE 'SCPP*';";
        
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
        strConsulta="select count(*) as cuantos from psp;";
        
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
    
    public void CrearTablaPDFPSP(String dest,String nombreArc){
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
            List<ClPsp> lista=new DAOPsp().leerPsp();
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
}
