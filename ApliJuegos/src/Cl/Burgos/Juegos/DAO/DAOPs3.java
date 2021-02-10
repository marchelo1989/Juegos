/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.DAO;

import Cl.Burgos.Juegos.BD.BD;
import Cl.Burgos.Juegos.ENT.ClPs3;
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
 * @author Marchelo
 */
public class DAOPs3 {
    public boolean sqlInsert(ClPs3 ps3) {
        Connection con = BD.getInstance().conectar();
        String insert = "insert into ps3(codigo,nombre,region,lenguaje,jugadores,disco,imagen) values (?,?,?,?,?,?,?)";
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
            ps.setBinaryStream(7, fi);
            
            ps.execute();
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean sqlUpdate(ClPs3 clPs3){	
        Connection con = BD.getInstance().conectar();
        String insert = "update ps3 set codigo=?, nombre=?, region=?, lenguaje=?, jugadores=?, disco=?, imagen=? where IdPs3=?;";
        String insert2 = "update ps3 set codigo=?, nombre=?, region=?, lenguaje=?, jugadores=?, disco=? where IdPs3=?;";
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
                ps.setBinaryStream(7, fi);
                ps.setInt(8, clPs3.getId());
            }else{
                ps = con.prepareStatement(insert2);
                ps.setString(1, clPs3.getCodigo());
                ps.setString(2, clPs3.getNombre());
                ps.setString(3, clPs3.getRegion());
                ps.setString(4, clPs3.getIdiomas());
                ps.setInt(5, clPs3.getJugadores());
                ps.setString(6, clPs3.getDisco());
                ps.setInt(7, clPs3.getId());
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
        
        strConsulta="select IdPs3,codigo,nombre,region,lenguaje,jugadores,disco,imagen from ps3 order by nombre asc;";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPs3 c = new ClPs3(rs.getInt("IdPs3"), rs.getString("codigo"), rs.getString("nombre"), 
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
        strConsulta="select IdPs3,codigo,nombre,region,lenguaje,jugadores,disco,imagen from ps3 where "+resp+" order by nombre asc";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             System.out.println(rs.getString("nombre"));
             ClPs3 c = new ClPs3(rs.getInt("IdPs3"), rs.getString("codigo"), rs.getString("nombre"), 
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
    
    public List<ClPs3> leerPs32(ClPs3 clPs3) {
        List<ClPs3> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPs3,codigo,nombre,region,lenguaje,jugadores,disco,imagen from ps3 where IdPs3="+clPs3.getId();
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPs3 c = new ClPs3(rs.getInt("IdPs3"), rs.getString("codigo"), rs.getString("nombre"), 
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
}
