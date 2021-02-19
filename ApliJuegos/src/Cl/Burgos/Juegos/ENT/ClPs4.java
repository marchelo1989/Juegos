/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.ENT;

/**
 *
 * @author march
 */
public class ClPs4 {
    private int id;
    private String codigo;
    private String nombre;
    private String region;
    private String idiomas;
    private int jugadores;
    private String disco;
    private boolean update;
    private String patch;
    private boolean dlc;
    private byte[] imagen;
    private String ruta;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public int getJugadores() {
        return jugadores;
    }

    public void setJugadores(int jugadores) {
        this.jugadores = jugadores;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public boolean isDlc() {
        return dlc;
    }

    public void setDlc(boolean dlc) {
        this.dlc = dlc;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    //Insertar
    public ClPs4(String codigo, String nombre, String region, String idiomas, int jugadores, String disco, boolean update, String patch, boolean dlc, String ruta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.region = region;
        this.idiomas = idiomas;
        this.jugadores = jugadores;
        this.disco = disco;
        this.update=update;
        this.patch=patch;
        this.dlc=dlc;
        this.ruta = ruta;
    }

    public ClPs4(int id, String codigo, String nombre, String region, String idiomas, int jugadores, String disco, boolean update, String patch, boolean dlc, byte[] imagen) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.region = region;
        this.idiomas = idiomas;
        this.jugadores = jugadores;
        this.disco = disco;
        this.update=update;
        this.patch=patch;
        this.dlc=dlc;
        this.imagen = imagen;
    }

    public ClPs4(int id, String codigo, String nombre, String region, String idiomas, int jugadores, String disco, boolean update, String patch, boolean dlc, String ruta) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.region = region;
        this.idiomas = idiomas;
        this.jugadores = jugadores;
        this.disco = disco;
        this.update=update;
        this.patch=patch;
        this.dlc=dlc;
        this.ruta = ruta;
    }

    public ClPs4(int id) {
        this.id = id;
    }

    public ClPs4(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
