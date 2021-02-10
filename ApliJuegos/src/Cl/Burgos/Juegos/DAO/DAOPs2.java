/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.DAO;

import Cl.Burgos.Juegos.BD.BD;
import Cl.Burgos.Juegos.ENT.ClPs2;
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
public class DAOPs2 {
    public boolean sqlInsert(ClPs2 ps2) {
        Connection con = BD.getInstance().conectar();
        String insert = "insert into ps2(codigo,nombre,region,lenguaje,jugadores,disco,imagen) values (?,?,?,?,?,?,?)";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            File file = new File(ps2.getRuta());
            fi = new FileInputStream(file);
            
            ps = con.prepareStatement(insert);
            ps.setString(1, ps2.getCodigo());
            ps.setString(2, ps2.getNombre());
            ps.setString(3, ps2.getRegion());
            ps.setString(4, ps2.getIdiomas());
            ps.setInt(5, ps2.getJugadores());
            ps.setString(6, ps2.getDisco());
            ps.setBinaryStream(7, fi);
            
            ps.execute();
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean sqlUpdate(ClPs2 clPs2){	
        Connection con = BD.getInstance().conectar();
        String insert = "update ps2 set codigo=?, nombre=?, region=?, lenguaje=?, jugadores=?, disco=?, imagen=? where IdPs2=?;";
        String insert2 = "update ps2 set codigo=?, nombre=?, region=?, lenguaje=?, jugadores=?, disco=? where IdPs2=?;";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            if(clPs2.getRuta().length()!=0){
                File file = new File(clPs2.getRuta());
                fi = new FileInputStream(file);

                ps = con.prepareStatement(insert);
                ps.setString(1, clPs2.getCodigo());
                ps.setString(2, clPs2.getNombre());
                ps.setString(3, clPs2.getRegion());
                ps.setString(4, clPs2.getIdiomas());
                ps.setInt(5, clPs2.getJugadores());
                ps.setString(6, clPs2.getDisco());
                ps.setBinaryStream(7, fi);
                ps.setInt(8, clPs2.getId());
            }else{
                ps = con.prepareStatement(insert2);
                ps.setString(1, clPs2.getCodigo());
                ps.setString(2, clPs2.getNombre());
                ps.setString(3, clPs2.getRegion());
                ps.setString(4, clPs2.getIdiomas());
                ps.setInt(5, clPs2.getJugadores());
                ps.setString(6, clPs2.getDisco());
                ps.setInt(7, clPs2.getId());
            }
            
            ps.executeUpdate();
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean sqlDelete(ClPs2 clPs2){
        Connection con = BD.getInstance().conectar();
        PreparedStatement ps = null;
        String stSql =  "delete from ps2 where IdPs2=?;";
        try {
            
             ps = con.prepareStatement(stSql);
            ps.setInt(1, clPs2.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
//            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
//            Log.log(ex.getMessage());
//            log.info(ex.getMessage());
        }
        return false;
    }
    
    public List<ClPs2> leerPs2() {
        List<ClPs2> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPs2,codigo,nombre,region,lenguaje,jugadores,disco,imagen from ps2 order by nombre asc;";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPs2 c = new ClPs2(rs.getInt("IdPs2"), rs.getString("codigo"), rs.getString("nombre"), 
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
    
    public List<ClPs2> leerPs22(ClPs2 clPs2) {
        List<ClPs2> lista=new ArrayList<>();
        String strConsulta;
        
        strConsulta="select IdPs2,codigo,nombre,region,lenguaje,jugadores,disco,imagen from ps2 where IdPs2="+clPs2.getId();
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             ClPs2 c = new ClPs2(rs.getInt("IdPs2"), rs.getString("codigo"), rs.getString("nombre"), 
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
    
    public List<ClPs2> leerBuscar(ClPs2 clPs2) {
        List<ClPs2> lista=new ArrayList<>();
        String strConsulta;
        String resp=null;
        
        if(clPs2.getCodigo().length()>1){
            resp="codigo='"+clPs2.getCodigo()+"'";
        }
        if(clPs2.getNombre().length()>1){
            resp="nombre like '%"+clPs2.getNombre()+"%'";
        }
        if(clPs2.getCodigo().length()>1 && clPs2.getNombre().length()>1){
            resp="codigo='"+clPs2.getCodigo()+"' and nombre like '%"+clPs2.getNombre()+"%'";
        }
        strConsulta="select IdPs2,codigo,nombre,region,lenguaje,jugadores,disco,imagen from ps2 where "+resp+" order by nombre asc";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         if(rs==null)return null;
         while(rs.next()){
             System.out.println(rs.getString("nombre"));
             ClPs2 c = new ClPs2(rs.getInt("IdPs2"), rs.getString("codigo"), rs.getString("nombre"), 
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
        strConsulta="select count(*) as cuantos from ps2 where codigo LIKE 'SCP2*';";
        
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
        strConsulta="select count(*) as cuantos from ps2;";
        
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
