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
public class ClPsx {
    private int id;
    private String codigo;
    private String nombre;
    private String region;
    private String idiomas;
    private int jugadores;
    private String disco;
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

    public ClPsx(String codigo, String nombre, String region, String idiomas, int jugadores, String disco, String ruta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.region = region;
        this.idiomas = idiomas;
        this.jugadores = jugadores;
        this.disco = disco;
        this.ruta = ruta;
    }

    public ClPsx(int id, String codigo, String nombre, String region, String idiomas, int jugadores, String disco, byte[] imagen) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.region = region;
        this.idiomas = idiomas;
        this.jugadores = jugadores;
        this.disco = disco;
        this.imagen = imagen;
    }

    public ClPsx(int id, String codigo, String nombre, String region, String idiomas, int jugadores, String disco, String ruta) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.region = region;
        this.idiomas = idiomas;
        this.jugadores = jugadores;
        this.disco = disco;
        this.ruta = ruta;
    }

    public ClPsx(int id) {
        this.id = id;
    }

    public ClPsx(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
