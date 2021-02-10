/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.DAO;

import Cl.Burgos.Juegos.BD.BD;
import Cl.Burgos.Juegos.ENT.ClPsx;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
