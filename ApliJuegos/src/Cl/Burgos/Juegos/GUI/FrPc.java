/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.GUI;

import Cl.Burgos.Juegos.DAO.DAOPc;
import Cl.Burgos.Juegos.ENT.ClPc;
import Cl.Burgos.Juegos.FUN.Archivos;
import Cl.Burgos.Juegos.FUN.Log;
import Cl.Burgos.Juegos.FUN.Render;
import Cl.Burgos.Juegos.Main.ApliJuegos;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author march
 */
public class FrPc extends javax.swing.JFrame {

    Archivos archivos = new Archivos();
    DAOPc dAOPc = new DAOPc();
    int id;
    /**
     * Creates new form FrPc
     */
    public FrPc() {
        initComponents();
        
        Limpiar();
        
        jPanel1.setOpaque(false);
        jPanel2.setOpaque(false);
        jPanel3.setOpaque(false);
        jPanel4.setOpaque(false);
        
        setLocationRelativeTo(null); 
        setResizable(false); 
        setTitle("Inicio de PC");
        String url="/Cl/Burgos/Juegos/IMG/";
        setIconImage(new ImageIcon(getClass().getResource(url+"Icono.png")).getImage());
        ((JPanel)getContentPane()).setOpaque(false);
        ImageIcon MyImgCustom =new ImageIcon(this.getClass().getResource(url+"fondo1.jpg"));
        JLabel fondo= new JLabel();
        
        fondo.setIcon(MyImgCustom);
        getLayeredPane().add(fondo,JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0,0,MyImgCustom.getIconWidth(),MyImgCustom.getIconHeight());
        
        jMenuItem1.setVisible(ApliJuegos.Update); 
    }

    public void Limpiar(){
        id=0;
        txtCodigo.setText("");
        txtCodigo.setEditable(false);
        txtNombre.setText("");
        txtDisco.setText("");
        jcCapaDisco.setSelectedIndex(0);
        txtProsesador.setText("");
        jcCapaProse.setSelectedIndex(0);
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        jRadioButton5.setSelected(false);
        jRadioButton6.setSelected(false);
        jRadioButton7.setSelected(false);
        jRadioButton8.setSelected(false);
        jRadioButton9.setSelected(false);
        jRadioButton10.setSelected(false);
        txtRam.setText("");
        jcCapaRam.setSelectedIndex(0);
        txtVideo.setText("");
        jcCapaVideo.setSelectedIndex(0);
        txtRuta.setText("");
        lblImgen.setText("");
        lblImgen.setIcon(null);
        defineTablaPC();
        this.btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnDescarImg.setEnabled(false);
    }
    
    public void defineTablaPC(){
        long lngRegistros=1;
        long lngDesdeRegistro;
        
        //DEFINIMOS LA TABLA MODELO
        DefaultTableModel tablaClientes = new DefaultTableModel();
//        DefaultTableModel tablaClientes = new DefaultTableModel(){
//            @Override
//            public boolean isCellEditable(int row, int column){
//                return false;
//            }
//        };
        jTable1.setDefaultRenderer(Object.class, new Render());
        
        //LE AGREGAMOS EL TITULO DE LAS COLUMNAS DE LA TABLA EN UN ARREGLO
        String strTitulos[]={"ID","CODIGO","NOMBRE","DISCO","PROCESADOR","WINDOWS","RAM","VIDEO","IMAGEN"};
        
        //LE ASIGNAMOS LAS COLUMNAS AL MODELO CON LA CADENA DE ARRIBA
        tablaClientes.setColumnIdentifiers(strTitulos);
        
        //LE ASIGNAMOS EL MODELO DE ARRIBA AL JTABLE 
        this.jTable1.setModel(tablaClientes);
        
                    //AHORA A LEER LOS DATOS
        
        //ASIGNAMOS CUANTOS REGISTROS POR HOJA TRAEREMOS
//        lngRegistros=(Long.valueOf(this.txtNumReg.getText()));
        
        //ASIGNAMOS DESDE QUE REGISTRO TRAERA LA CONSULTA SQL
//        lngDesdeRegistro=(DesdeHoja*lngRegistros)-lngRegistros;
        
        //INSTANCEAMOS LA CLASE CLIENTE
//        DAOPc classCliente= new DAOPc();
        
        //LEEMOS LA CLASE CLIENTE MANDANDOLE LOS PARAMETROS
        //dAOClaves.leerClientesId(lngDesdeRegistro, (Long.valueOf(this.txtNumReg.getText())),tablaClientes,strBusqueda,idCliente);
        List<ClPc> lista=dAOPc.leerPc();
        Object fila[] = new Object[9];
//        String datos[]=new String [3];
        for (int i = 0; i < lista.size(); i++) {
            fila[0]=Integer.toString(lista.get(i).getId());
            fila[1]=lista.get(i).getCodigo();
            fila[2]=lista.get(i).getNombre();
            fila[3]=lista.get(i).getDisco();
            fila[4]=lista.get(i).getProce();
            fila[5]=lista.get(i).getSistemaOper();
            fila[6]=lista.get(i).getRam();
            fila[7]=lista.get(i).getVideo();
            try{
                String urlImagen = System.getProperties().getProperty("user.dir")+"/IMG/PC/"+lista.get(i).getCodigo()+".jpg";
                ImageIcon icon = new ImageIcon(urlImagen);
                ImageIcon imgi = new ImageIcon(icon.getImage().getScaledInstance(120,60,Image.SCALE_DEFAULT)); 
//                    byte[] bi = lista.get(i).getImagen();
//                    BufferedImage image = null;
//                    InputStream in = new ByteArrayInputStream(bi);
//                    image = ImageIO.read(in);
//                    ImageIcon imgi = new ImageIcon(image.getScaledInstance(60, 60, 0));
                    fila[8] = new JLabel(imgi);

                }catch(Exception ex){
                    fila[8] = new JLabel("No imagen");
                }
            tablaClientes.addRow(fila);
        }
        
        //LE PONEMOS EL RESULTADO DE LA CONSULA AL JTABLE
        this.jTable1.setModel(tablaClientes);
        this.jTable1.setRowHeight(60);
        
        //ASIGNAMOS LOS VALORES A LA PAGINACION
//        lngRegistros = dAOClaves.leerCuantos("");
//        lngNumPaginas= (lngRegistros/ (Long.valueOf( this.txtNumReg.getText())))+1;
//        this.jlblTotalPaginas.setText(" De " + ( lngNumPaginas));
        
    }
    public void defineTablaPsxBuscar(ClPc pc){
        long lngRegistros=1;
        long lngDesdeRegistro;
        
        //DEFINIMOS LA TABLA MODELO
        DefaultTableModel tablaClientes = new DefaultTableModel();
//        DefaultTableModel tablaClientes = new DefaultTableModel(){
//            @Override
//            public boolean isCellEditable(int row, int column){
//                return false;
//            }
//        };
        jTable1.setDefaultRenderer(Object.class, new Render());
        
        //LE AGREGAMOS EL TITULO DE LAS COLUMNAS DE LA TABLA EN UN ARREGLO
        String strTitulos[]={"ID","CODIGO","NOMBRE","DISCO","PROCESADOR","WINDOWS","RAM","VIDEO","IMAGEN"};
        
        //LE ASIGNAMOS LAS COLUMNAS AL MODELO CON LA CADENA DE ARRIBA
        tablaClientes.setColumnIdentifiers(strTitulos);
        
        //LE ASIGNAMOS EL MODELO DE ARRIBA AL JTABLE 
        this.jTable1.setModel(tablaClientes);
        
                    //AHORA A LEER LOS DATOS
        
        //ASIGNAMOS CUANTOS REGISTROS POR HOJA TRAEREMOS
//        lngRegistros=(Long.valueOf(this.txtNumReg.getText()));
        
        //ASIGNAMOS DESDE QUE REGISTRO TRAERA LA CONSULTA SQL
//        lngDesdeRegistro=(DesdeHoja*lngRegistros)-lngRegistros;
        
        //INSTANCEAMOS LA CLASE CLIENTE
//        DAOPc classCliente= new DAOPc();
        
        //LEEMOS LA CLASE CLIENTE MANDANDOLE LOS PARAMETROS
        //dAOClaves.leerClientesId(lngDesdeRegistro, (Long.valueOf(this.txtNumReg.getText())),tablaClientes,strBusqueda,idCliente);
        List<ClPc> lista=dAOPc.leerBuscar(pc);
        Object fila[] = new Object[9];
//        String datos[]=new String [3];
        for (int i = 0; i < lista.size(); i++) {
            fila[0]=Integer.toString(lista.get(i).getId());
            fila[1]=lista.get(i).getCodigo();
            fila[2]=lista.get(i).getNombre();
            fila[3]=lista.get(i).getDisco();
            fila[4]=lista.get(i).getProce();
            fila[5]=lista.get(i).getSistemaOper();
            fila[6]=lista.get(i).getRam();
            fila[7]=lista.get(i).getVideo();
            try{
                String urlImagen = System.getProperties().getProperty("user.dir")+"/IMG/PC/"+lista.get(i).getCodigo()+".jpg";
                ImageIcon icon = new ImageIcon(urlImagen);
                ImageIcon imgi = new ImageIcon(icon.getImage().getScaledInstance(120,60,Image.SCALE_DEFAULT)); 
//                    byte[] bi = lista.get(i).getImagen();
//                    BufferedImage image = null;
//                    InputStream in = new ByteArrayInputStream(bi);
//                    image = ImageIO.read(in);
//                    ImageIcon imgi = new ImageIcon(image.getScaledInstance(60, 60, 0));
                    fila[8] = new JLabel(imgi);

                }catch(Exception ex){
                    fila[8] = new JLabel("No imagen");
                }
            tablaClientes.addRow(fila);
        }
        
        //LE PONEMOS EL RESULTADO DE LA CONSULA AL JTABLE
        this.jTable1.setModel(tablaClientes);
        this.jTable1.setRowHeight(60);
        
        //ASIGNAMOS LOS VALORES A LA PAGINACION
//        lngRegistros = dAOClaves.leerCuantos("");
//        lngNumPaginas= (lngRegistros/ (Long.valueOf( this.txtNumReg.getText())))+1;
//        this.jlblTotalPaginas.setText(" De " + ( lngNumPaginas));
        
    }
    public String GenerCodigo(){
        String dato="SCPC";
        String resp="";
        int num = dAOPc.Cuantos();
        int cant=num+1;
        if(cant>=1&&cant<=9){
            resp=dato+"0000"+cant;
//            System.out.println(dato+"0000"+cant);
        }else if(cant>=10&&cant<=99){
            resp=dato+"000"+cant;
//            System.out.println(dato+"000"+cant);
        }else if(cant>=100&&cant<=999){
            resp=dato+"00"+cant;
//            System.out.println(dato+"00"+cant);
        }else if(cant>=1000&&cant<=9999){
            resp=dato+"0"+cant;
//            System.out.println(dato+"0"+cant);
        }else if(cant>=10000&&cant<=99999){
            resp=dato+""+cant;
//            System.out.println(dato+""+cant);
        }
        return resp;
    }
    
    public String Windows(){
        String resp;
        String siste95="",siste98="",sisteNT="",sisteME="",siste2000="",sisteXP="",sisteVista="",siste7="",siste8="",siste10="";
        if(jRadioButton1.isSelected()){ siste95="95 "; }
        if(jRadioButton2.isSelected()){ siste98="98 "; }
        if(jRadioButton3.isSelected()){ sisteNT="NT "; }
        if(jRadioButton4.isSelected()){ sisteME="ME "; }
        if(jRadioButton10.isSelected()){ sisteME="2000 "; }
        if(jRadioButton5.isSelected()){ sisteXP="XP "; }
        if(jRadioButton6.isSelected()){ sisteVista="Vista "; }
        if(jRadioButton7.isSelected()){ siste7="7 "; }
        if(jRadioButton8.isSelected()){ siste8="8 "; }
        if(jRadioButton9.isSelected()){ siste10="10 "; }
        resp = siste95+""+siste98+""+sisteNT+""+sisteME+""+siste2000+""+sisteXP+""+sisteVista+""+siste7+""+siste8+""+siste10;
        return resp;
    }
    public ClPc insert(){
        ClPc clPc = null;
//        String cd1;
        String disco1 = txtDisco.getText().trim();
        String capasid1 = jcCapaDisco.getSelectedItem().toString();
//        if(disco1.length()>0){ cd1=disco1+" "+capasid1; }else{ cd1=""; }
        
        String capaRam= jcCapaRam.getSelectedItem().toString();
        String capaVideo= jcCapaVideo.getSelectedItem().toString();
        
        String codi = txtCodigo.getText();
        String proce= txtProsesador.getText().trim();
        String capasidPro=jcCapaProse.getSelectedItem().toString();
        String siste1 = Windows();
        if(codi.length()==0){
            codi=GenerCodigo();
        }
        if(txtRuta.getText().length()>0){
            archivos.CopiarArchivos(txtRuta.getText(), System.getProperties().getProperty("user.dir")+"/IMG/PC/"+codi.trim()+".jpg");
            clPc = new ClPc(codi.trim(), txtNombre.getText().trim(), disco1+" "+capasid1, 
                    proce+" "+capasidPro, siste1, txtRam.getText().trim()+" "+capaRam, txtVideo.getText().trim()+" "+capaVideo, txtRuta.getText());
        }else{
            String ruta = "./src/Cl/Burgos/Juegos/IMG/PC.png";
            clPc = new ClPc(codi.trim(), txtNombre.getText().trim(), disco1+" "+capasid1, 
                    proce+" "+capasidPro, siste1, txtRam.getText().trim()+" "+capaRam, txtVideo.getText().trim()+" "+capaVideo, ruta);
        }
        return clPc;
    }
    public ClPc actualizar(){
        ClPc clPsx= null;
//        String cd1,cd2,cd3,cd4;
        String disco1 = txtDisco.getText().trim();
        String capasid1 = jcCapaDisco.getSelectedItem().toString();
//        if(disco1.length()>0){ cd1=disco1+" "+capasid1; }else{ cd1=""; }
        String capaRam= jcCapaRam.getSelectedItem().toString();
        String capaVideo= jcCapaVideo.getSelectedItem().toString();
        String proce= txtProsesador.getText().trim();
        String capasidPro=jcCapaProse.getSelectedItem().toString();
        String siste1 = Windows();
        if(txtRuta.getText().length()>0){
            archivos.CopiarArchivos(txtRuta.getText(), System.getProperties().getProperty("user.dir")+"/IMG/PC/"+txtCodigo.getText().trim()+".jpg");
            clPsx = new ClPc(id,txtCodigo.getText().trim(), txtNombre.getText().trim(), disco1+" "+capasid1, 
                    proce+" "+capasidPro, siste1, txtRam.getText().trim()+" "+capaRam, txtVideo.getText().trim()+" "+capaVideo, txtRuta.getText());
        }else{
            clPsx = new ClPc(id,txtCodigo.getText().trim(), txtNombre.getText().trim(), disco1+" "+capasid1, 
                    proce+" "+capasidPro, siste1, txtRam.getText().trim()+" "+capaRam, txtVideo.getText().trim()+" "+capaVideo, txtRuta.getText());
        }
        return clPsx;
    }
    public ClPc eliLis(){
        ClPc clPsx = new ClPc(id);
        return clPsx;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDisco = new javax.swing.JTextField();
        jcCapaDisco = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtRuta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnDescarImg = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtProsesador = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        txtRam = new javax.swing.JTextField();
        jcCapaRam = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txtVideo = new javax.swing.JTextField();
        jcCapaVideo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jcCapaProse = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        lblImgen = new javax.swing.JLabel();
        jRadioButton10 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre.setText("Los Sims 2 Cocina y Baño, Diseño de interiores Accesorios ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Disco:");

        txtDisco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDisco.setText("287");

        jcCapaDisco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcCapaDisco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MB", "GB", "TB" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Imagen:");

        txtRuta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnDescarImg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDescarImg.setText("Descargar IMG");
        btnDescarImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescarImgActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Atras");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDescarImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(jButton2)
                    .addComponent(btnDescarImg)
                    .addComponent(jButton3)
                    .addComponent(btnBuscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Prosesador:");

        txtProsesador.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProsesador.setText("90");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Windows:");

        jRadioButton1.setText("95");

        jRadioButton2.setText("98");

        jRadioButton3.setText("NT");

        jRadioButton4.setText("ME");

        jRadioButton5.setText("XP");

        jRadioButton6.setText("Vista");

        jRadioButton7.setText("7");

        jRadioButton8.setText("8");

        jRadioButton9.setText("10");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("RAM:");

        txtRam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRam.setText("24");

        jcCapaRam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcCapaRam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MB", "GB", "TB" }));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Video:");

        txtVideo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtVideo.setText("1");

        jcCapaVideo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcCapaVideo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MB", "GB", "TB" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Codigo:");

        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCodigo.setText("SCES03008");

        jcCapaProse.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcCapaProse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MHz", "GHz" }));

        lblImgen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblImgen.setText("lblImagen");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImgen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImgen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jRadioButton10.setText("2000");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcCapaDisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProsesador, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcCapaProse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton9)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRam, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcCapaRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcCapaVideo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(7, 7, 7)
                                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcCapaDisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtProsesador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcCapaProse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton4)
                            .addComponent(jRadioButton5)
                            .addComponent(jRadioButton6)
                            .addComponent(jRadioButton7)
                            .addComponent(jRadioButton8)
                            .addComponent(jRadioButton9)
                            .addComponent(jLabel14)
                            .addComponent(txtRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcCapaRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton10))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtVideo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcCapaVideo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado"));

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );

        jMenu1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenu1.setText("Inicio");
        jMenuBar1.add(jMenu1);

        jMenu2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenu2.setText("Ayuda");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cl/Burgos/Juegos/IMG/Update.png"))); // NOI18N
        jMenuItem1.setText("Actualizacion");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cl/Burgos/Juegos/IMG/Ayuda.png"))); // NOI18N
        jMenu3.setText("Contacto");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cl/Burgos/Juegos/IMG/WEB.png"))); // NOI18N
        jMenuItem3.setText("Pagina Web");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cl/Burgos/Juegos/IMG/WhatsApp.png"))); // NOI18N
        jMenuItem4.setText("Whatsapp");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String nombre,ruta;
        final JFileChooser elegirImagen = new JFileChooser();
        //        FileNameExtensionFilter fi = new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
        FileNameExtensionFilter fi = new FileNameExtensionFilter("JPG","jpg");
        elegirImagen.setFileFilter(fi);
        elegirImagen.setMultiSelectionEnabled(false);
        int o = elegirImagen.showOpenDialog(this);
        if(o == JFileChooser.APPROVE_OPTION){
            ruta = elegirImagen.getSelectedFile().getAbsolutePath();
            nombre = elegirImagen.getSelectedFile().getName();
            txtRuta.setText(ruta);
            Image preview = Toolkit.getDefaultToolkit().getImage(ruta);
            if(preview != null){
                lblImgen.setText("");
                ImageIcon icon = new ImageIcon(preview.getScaledInstance(lblImgen.getWidth(), lblImgen.getHeight(), Image.SCALE_DEFAULT));
                lblImgen.setIcon(icon);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        if(!dAOPc.sqlInsert(insert())){
            JOptionPane.showMessageDialog(null, "Juego No Agregado");
        }else{
            JOptionPane.showMessageDialog(null, "Juegos Agregado");
            Limpiar();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        if(!dAOPc.sqlUpdate(actualizar())){
            JOptionPane.showMessageDialog(null, "Juego No Actualizado");
        }else{
            JOptionPane.showMessageDialog(null, "Juegos Actualizado");
            Limpiar();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if(!dAOPc.sqlDelete(eliLis())){
            JOptionPane.showMessageDialog(null, "Juego No Eliminado");
        }else{
            JOptionPane.showMessageDialog(null, "Juegos Eliminado");
            Limpiar();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnDescarImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescarImgActionPerformed
        // TODO add your handling code here:
        String nombre = null;
        Image img = null;
        //descargar
        //        System.out.println(imagenes.get(contador).getNombre());
        try {
            List<ClPc> datosCliente=dAOPc.leerPc2( eliLis());
            for (int i = 0; i < datosCliente.size(); i++) {
                id=Integer.parseInt(String.valueOf(datosCliente.get(i).getId()));
                //                    nombre=datosCliente.get(i).getCodigo()+"-"+datosCliente.get(i).getNombre()+".png";
                nombre = datosCliente.get(i).getCodigo() + ".jpg";
                byte[] bi = datosCliente.get(i).getImagen();
                BufferedImage image = null;
                InputStream in = new ByteArrayInputStream(bi);
                image = ImageIO.read(in);
                img = image;
            }

            final JFileChooser elegirCarpeta = new JFileChooser();
            elegirCarpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int o =elegirCarpeta.showOpenDialog(this);
            if(o == JFileChooser.APPROVE_OPTION){
                String path = elegirCarpeta.getSelectedFile().getAbsolutePath();
                System.out.println(path);
                String aux = nombre;
                StringTokenizer token = new StringTokenizer(aux,".");
                token.nextToken();
                String formato = token.nextToken();
                ImageIO.write((RenderedImage) img, formato, new File(path+"\\"+nombre));
                }
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnDescarImgActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        FrHome frHome = new FrHome();
        frHome.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        ClPc clPc= null;
        clPc = new ClPc(txtCodigo.getText().trim(), txtNombre.getText().trim());
        defineTablaPsxBuscar(clPc);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int fila;

        //        this.txtId.getText();
        //        List<ClUsuario> datosCliente;
        fila = this.jTable1.rowAtPoint(evt.getPoint());
        Limpiar();
        if (fila > -1){
            try {
                id=Integer.parseInt(String.valueOf(jTable1.getValueAt(fila, 0)));

                List<ClPc> datosCliente=dAOPc.leerPc2(eliLis());
                for (int i = 0; i < datosCliente.size(); i++) {
                    id=Integer.parseInt(String.valueOf(datosCliente.get(i).getId()));
                    this.txtCodigo.setText(datosCliente.get(i).getCodigo());
                    this.txtNombre.setText(datosCliente.get(i).getNombre());

                    String disco1 = datosCliente.get(i).getDisco();
                    String dato1 =disco1.substring(0,disco1.length()-3);
                    this.txtDisco.setText(dato1);
                    String capacidad1 = disco1.substring(disco1.length()-2,disco1.length());
                    cargarDatosCapDisco(capacidad1);
                    
                    String proce1 = datosCliente.get(i).getProce();
                    String dato4 =proce1.substring(0,proce1.length()-3);
                    this.txtProsesador.setText(dato4);
                    String capacidad4 = proce1.substring(proce1.length()-3,proce1.length());
                    cargarDatosProc(capacidad4);
                    
                    windows(datosCliente.get(i).getSistemaOper());
                    
                    String disco5 = datosCliente.get(i).getRam();
                    String dato5 =disco5.substring(0,disco5.length()-3);
                    this.txtRam.setText(dato5);
                    String capacidad5 = disco5.substring(disco5.length()-2,disco5.length());
                    cargarDatosCapRam(capacidad5);
                    
                    String disco6 = datosCliente.get(i).getVideo();
                    String dato6 =disco6.substring(0,disco6.length()-3);
                    this.txtVideo.setText(dato6);
                    String capacidad6 = disco6.substring(disco6.length()-2,disco6.length());
                    cargarDatosCapVideo(capacidad6);
                    
                    byte[] bi = datosCliente.get(i).getImagen();
                    BufferedImage image = null;
                    InputStream in = new ByteArrayInputStream(bi);
                    image = ImageIO.read(in);
                    ImageIcon imgi = new ImageIcon(image.getScaledInstance(lblImgen.getWidth(), lblImgen.getHeight(), Image.SCALE_DEFAULT));
                    this.lblImgen.setText("");
                    this.lblImgen.setIcon(imgi);
                }
                this.btnAgregar.setEnabled(false);
                this.btnEliminar.setEnabled(true);
                this.btnActualizar.setEnabled(true);
                this.btnDescarImg.setEnabled(true);

                //                if(Long.valueOf( datosCliente[0])>0){
                    //                    this.btnActualizar.setLabel("Actualizar");
                    //                }
            } catch (Exception ex) {
                //                Logger.getLogger(FrManteLogin.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
                //                Log.log(ex.getMessage());
                //                log.fatal(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new FrUpdate().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        URL url=null;
        try {
            url = new URL("https://marchelo1989.github.io/");
            try {
                Desktop.getDesktop().browse(url.toURI());
            } catch (IOException e) {
                Log.log("Error en Clase FrContacto: "+e.getMessage());
                e.printStackTrace();
            } catch (URISyntaxException e) {
                Log.log("Error en Clase FrContacto: "+e.getMessage());
                e.printStackTrace();
            }
        } catch (MalformedURLException e1) {
            Log.log("Error en Clase FrContacto: "+e1.getMessage());
            e1.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        URL url=null;
        try {
            url = new URL("https://api.whatsapp.com/send?phone=+56920473627");
            try {
                Desktop.getDesktop().browse(url.toURI());
            } catch (IOException e) {
                Log.log("Error en Clase FrContacto: "+e.getMessage());
                e.printStackTrace();
            } catch (URISyntaxException e) {
                Log.log("Error en Clase FrContacto: "+e.getMessage());
                e.printStackTrace();
            }
        } catch (MalformedURLException e1) {
            Log.log("Error en Clase FrContacto: "+e1.getMessage());
            e1.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    public void windows(String wind){
        String[] parts = wind.split(" ");
        for (int i = 0; i < parts.length; i++) {
//            System.out.println(parts[i].toString());
            if(parts[i].equals("95")){ jRadioButton1.setSelected(true); }
            if(parts[i].equals("98")){ jRadioButton2.setSelected(true); }
            if(parts[i].equals("NT")){ jRadioButton3.setSelected(true); }
            if(parts[i].equals("ME")){ jRadioButton4.setSelected(true); }
            if(parts[i].equals("2000")){ jRadioButton10.setSelected(true); }
            if(parts[i].equals("XP")){ jRadioButton5.setSelected(true); }
            if(parts[i].equals("Vista")){ jRadioButton6.setSelected(true); }
            if(parts[i].equals("7")){ jRadioButton7.setSelected(true); }
            if(parts[i].equals("8")){ jRadioButton8.setSelected(true); }
            if(parts[i].equals("10")){ jRadioButton9.setSelected(true); }
        }
    }
    public void cargarDatosCapDisco(String disco){
        if(disco.equals("MB")){jcCapaDisco.setSelectedIndex(0);}
        if(disco.equals("GB")){jcCapaDisco.setSelectedIndex(1);}
        if(disco.equals("TB")){jcCapaDisco.setSelectedIndex(2);}
    }
    public void cargarDatosCapRam(String ram){
        if(ram.equals("MB")){jcCapaRam.setSelectedIndex(0);}
        if(ram.equals("GB")){jcCapaRam.setSelectedIndex(1);}
        if(ram.equals("TB")){jcCapaRam.setSelectedIndex(2);}
    }
    public void cargarDatosCapVideo(String video){
        if(video.equals("MB")){jcCapaVideo.setSelectedIndex(0);}
        if(video.equals("GB")){jcCapaVideo.setSelectedIndex(1);}
        if(video.equals("TB")){jcCapaVideo.setSelectedIndex(2);}
    }
    public void cargarDatosProc(String pro){
        if(pro.equals("MHz")){jcCapaProse.setSelectedIndex(0);}
        if(pro.equals("GHz")){jcCapaProse.setSelectedIndex(1);}
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrPc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrPc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrPc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrPc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrPc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDescarImg;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcCapaDisco;
    private javax.swing.JComboBox<String> jcCapaProse;
    private javax.swing.JComboBox<String> jcCapaRam;
    private javax.swing.JComboBox<String> jcCapaVideo;
    private javax.swing.JLabel lblImgen;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDisco;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtProsesador;
    private javax.swing.JTextField txtRam;
    private javax.swing.JTextField txtRuta;
    private javax.swing.JTextField txtVideo;
    // End of variables declaration//GEN-END:variables
}
