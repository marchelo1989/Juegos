/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.ENT;

/**
 *
 * @author Marchelo
 */
public class ClPc {
    private int id;
    private String codigo;
    private String nombre;
    private String disco;
    
    private String proce;
    private String sistemaOper;
    private String ram;
    private String video;
    
    private byte[] imagen;
    private String ruta;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }
    
    public String getProce() {
        return proce;
    }

    public void setProce(String proce) {
        this.proce = proce;
    }

    public String getSistemaOper() {
        return sistemaOper;
    }

    public void setSistemaOper(String sistemaOper) {
        this.sistemaOper = sistemaOper;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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

    public ClPc(String codigo, String nombre, String disco, String proce, String sistemaOper, String ram, String video, String ruta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.disco = disco;
        this.proce = proce;
        this.sistemaOper = sistemaOper;
        this.ram = ram;
        this.video = video;
        this.ruta = ruta;
    }

    public ClPc(int id, String codigo, String nombre, String disco, String proce, String sistemaOper, String ram, String video, byte[] imagen) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.disco = disco;
        this.proce = proce;
        this.sistemaOper = sistemaOper;
        this.ram = ram;
        this.video = video;
        this.imagen = imagen;
    }

    public ClPc(int id, String codigo, String nombre, String disco, String proce, String sistemaOper, String ram, String video, String ruta) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.disco = disco;
        this.proce = proce;
        this.sistemaOper = sistemaOper;
        this.ram = ram;
        this.video = video;
        this.ruta = ruta;
    }

    public ClPc(int id) {
        this.id = id;
    }

    public ClPc(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    
    
    
}
