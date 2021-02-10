/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.DAO;

import Cl.Burgos.Juegos.BD.BD;
import Cl.Burgos.Juegos.ENT.ClPsp;
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
}
