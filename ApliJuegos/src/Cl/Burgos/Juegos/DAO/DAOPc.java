/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.DAO;

import Cl.Burgos.Juegos.BD.BD;
import Cl.Burgos.Juegos.ENT.ClPc;
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
public class DAOPc {
    public boolean sqlInsert(ClPc pc) {
        Connection con = BD.getInstance().conectar();
        String insert = "insert into pc(codigo,nombre,disco,procesador,sistemaOperativo,ram,video,imagen) values (?,?,?,?,?,?,?,?)";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            File file = new File(pc.getRuta());
            fi = new FileInputStream(file);
            
            ps = con.prepareStatement(insert);
            ps.setString(1, pc.getCodigo());
            ps.setString(2, pc.getNombre());
            ps.setString(3, pc.getDisco());
            ps.setString(4, pc.getProce());
            ps.setString(5, pc.getSistemaOper());
            ps.setString(6, pc.getRam());
            ps.setString(7, pc.getVideo());
            ps.setBinaryStream(8, fi);
            
            ps.execute();
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean sqlUpdate(ClPc clPc){	
        Connection con = BD.getInstance().conectar();
        String insert = "update pc set codigo=?, nombre=?, disco=?, procesador=?, sistemaOperativo=?, ram=?, video=?, imagen=? where IdPc=?;";
        String insert2 = "update pc set codigo=?, nombre=?, disco=?, procesador=?, sistemaOperativo=?, ram=?, video=? where IdPc=?;";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            if(clPc.getRuta().length()!=0){
                File file = new File(clPc.getRuta());
                fi = new FileInputStream(file);

                ps = con.prepareStatement(insert);
                ps.setString(1, clPc.getCodigo());
                ps.setString(2, clPc.getNombre());
                ps.setString(3, clPc.getDisco());
                ps.setString(4, clPc.getProce());
                ps.setString(5, clPc.getSistemaOper());
                ps.setString(6, clPc.getRam());
                ps.setString(7, clPc.getVideo());
                ps.setBinaryStream(8, fi);
                ps.setInt(9, clPc.getId());
            }else{
                ps = con.prepareStatement(insert2);
                ps.setString(1, clPc.getCodigo());
                ps.setString(2, clPc.getNombre());
                ps.setString(3, clPc.getDisco());
                ps.setString(4, clPc.getProce());
                ps.setString(5, clPc.getSistemaOper());
                ps.setString(6, clPc.getRam());
                ps.setString(7, clPc.getVideo());
                ps.setInt(8, clPc.getId());
            }
            
            ps.executeUpdate();
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean sqlDelete(ClPc clPc){
        Connection con = BD.getInstance().conectar();
        PreparedStatement ps = null;
        String stSql =  "delete from pc where IdPc=?;";
        try {
            
             ps = con.prepareStatement(stSql);
            ps.setInt(1, clPc.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
//            log.info(ex.getMessage());
        }
        return false;
    }
    
    public List<ClPc> leerPc() {
        List<ClPc> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPc,codigo,nombre,disco,procesador,sistemaOperativo,ram,video,imagen from pc order by nombre asc;";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPc c = new ClPc(rs.getInt("IdPc"), rs.getString("codigo"), rs.getString("nombre"), 
                     rs.getString("disco"), rs.getString("procesador"),rs.getString("sistemaOperativo"),rs.getString("ram"),rs.getString("video"),rs.getBytes("imagen"));
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
    public List<ClPc> leerPc2(ClPc clPc) {
        List<ClPc> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPc,codigo,nombre,disco,procesador,sistemaOperativo,ram,video,imagen from pc where IdPc="+clPc.getId();
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPc c = new ClPc(rs.getInt("IdPc"), rs.getString("codigo"), rs.getString("nombre"), 
                     rs.getString("disco"), rs.getString("procesador"),rs.getString("sistemaOperativo"),rs.getString("ram"),rs.getString("video"),rs.getBytes("imagen"));
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
    public List<ClPc> leerBuscar(ClPc clPc) {
        List<ClPc> lista=new ArrayList<>();
        String strConsulta;
        String resp=null;
        
        if(clPc.getCodigo().length()>1){
            resp="codigo='"+clPc.getCodigo()+"'";
        }
        if(clPc.getNombre().length()>1){
            resp="nombre like '%"+clPc.getNombre()+"%'";
        }
        if(clPc.getCodigo().length()>1 && clPc.getNombre().length()>1){
            resp="codigo='"+clPc.getCodigo()+"' and nombre like '%"+clPc.getNombre()+"%'";
        }
        strConsulta="select IdPc,codigo,nombre,disco,procesador,sistemaOperativo,ram,video,imagen from pc where "+resp+" order by nombre asc";
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             System.out.println(rs.getString("nombre"));
             ClPc c = new ClPc(rs.getInt("IdPc"), rs.getString("codigo"), rs.getString("nombre"), 
                     rs.getString("disco"), rs.getString("procesador"),rs.getString("sistemaOperativo"),rs.getString("ram"),rs.getString("video"),rs.getBytes("imagen"));
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
        strConsulta="select count(*) as cuantos from pc where codigo LIKE 'SCPC*';";
        
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
        strConsulta="select count(*) as cuantos from pc;";
        
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
    
    public void CrearTablaPDFPC(String dest,String nombreArc){
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
            float[] anchos = {10f, 50f, 50f, 50f, 50f, 50f, 50f, 60f};
            Table tabla1 = new Table(anchos);
            Table tabla2 = new Table(anchos);
            
            // Agregamos contenido a las tablas
//            tabla1.addCell("ID");
            tabla1.addCell("Codigo");
            tabla1.addCell("Nombre");
            tabla1.addCell("Disco");
            tabla1.addCell("Procesador");
            tabla1.addCell("Windows");
            tabla1.addCell("Ram");
            tabla1.addCell("Video");
            tabla1.addCell("Imagen");
            List<ClPc> lista=new DAOPc().leerPc();
            for (int i = 0; i < lista.size(); i++) {
//                tabla1.addCell(Integer.toString(lista.get(i).getId()));
                tabla1.addCell(lista.get(i).getCodigo());
                tabla1.addCell(lista.get(i).getNombre());
                tabla1.addCell(lista.get(i).getDisco());
                tabla1.addCell(lista.get(i).getProce());
                tabla1.addCell(lista.get(i).getSistemaOper());
                tabla1.addCell(lista.get(i).getRam());
                tabla1.addCell(lista.get(i).getVideo());
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
