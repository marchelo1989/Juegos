/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.GUI;

import Cl.Burgos.Juegos.DAO.DAOPs3;
import Cl.Burgos.Juegos.ENT.ClPs3;
import Cl.Burgos.Juegos.FUN.Archivos;
import Cl.Burgos.Juegos.FUN.Render;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.StringTokenizer;
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
 * @author Marchelo
 */
public class FrPs3 extends javax.swing.JFrame {

    Archivos archivos = new Archivos();
    DAOPs3 dAOPs3 = new DAOPs3();
    int id;

    /**
     * Creates new form FrPs3
     */
    public FrPs3() {
        initComponents();

        Limpiar();

        jPanel1.setOpaque(false);
        jPanel2.setOpaque(false);
        jPanel3.setOpaque(false);

        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Inicio de PS3");
        String url = "/Cl/Burgos/Juegos/IMG/";
        setIconImage(new ImageIcon(getClass().getResource(url + "Icono.png")).getImage());
        ((JPanel) getContentPane()).setOpaque(false);
        ImageIcon MyImgCustom = new ImageIcon(this.getClass().getResource(url + "fondo1.jpg"));
        JLabel fondo = new JLabel();

        fondo.setIcon(MyImgCustom);
        getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0, 0, MyImgCustom.getIconWidth(), MyImgCustom.getIconHeight());
    }

    public void Limpiar() {
        id = 0;
        txtCodigo.setText("");
        txtNombre.setText("");
        txtRegion.setText("");
        txtIdiomas.setText("");
        jcCapasidad.setSelectedIndex(0);
        txtDisco.setText("");
        jsJugadores.setValue(1);
        txtRuta.setText("");
        lblImgen.setText("");
        lblImgen.setIcon(null);
        defineTablaPs3();
        this.btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnDescarImg.setEnabled(false);
    }

    public String GenerCodigo(){
        String dato="SCP3";
        String resp="";
        int num = dAOPs3.Cuantos();
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
    
    public ClPs3 insert() {
        String capasid = jcCapasidad.getSelectedItem().toString();
        ClPs3 clPs3 = null;
        String codi = txtCodigo.getText();
        if(codi.length()==0){
            codi=GenerCodigo();
        }
        if (txtRuta.getText().length() > 0) {
            archivos.CopiarArchivos(txtRuta.getText(), System.getProperties().getProperty("user.dir")+"/IMG/PS3/"+codi.trim()+".jpg");
            clPs3 = new ClPs3(codi.trim(), txtNombre.getText().trim(), txtRegion.getText().trim(), txtIdiomas.getText().trim(), jsJugadores.getValue().hashCode(), txtDisco.getText().trim() + " " + capasid, txtRuta.getText());
        } else {
            String ruta = "./src/Cl/Burgos/Juegos/IMG/PS3.png";
            clPs3 = new ClPs3(codi.trim(), txtNombre.getText().trim(), txtRegion.getText().trim(), txtIdiomas.getText().trim(), jsJugadores.getValue().hashCode(), txtDisco.getText().trim() + " " + capasid, ruta);
        }
        return clPs3;
    }

    public ClPs3 actualizar() {
        String capasid = jcCapasidad.getSelectedItem().toString();
        ClPs3 clPs2= null;
        if(txtRuta.getText().length()>0){
            archivos.CopiarArchivos(txtRuta.getText(), System.getProperties().getProperty("user.dir")+"/IMG/PS3/"+txtCodigo.getText().trim()+".jpg");
            clPs2 = new ClPs3(id, txtCodigo.getText().trim(), txtNombre.getText().trim(), txtRegion.getText().trim(), txtIdiomas.getText().trim(), jsJugadores.getValue().hashCode(), txtDisco.getText().trim() + " " + capasid, txtRuta.getText());
        }else{
            clPs2 = new ClPs3(id, txtCodigo.getText().trim(), txtNombre.getText().trim(), txtRegion.getText().trim(), txtIdiomas.getText().trim(), jsJugadores.getValue().hashCode(), txtDisco.getText().trim() + " " + capasid, txtRuta.getText());
        }
        return clPs2;
    }

    public ClPs3 eliLis() {
        ClPs3 clPs3 = new ClPs3(id);
        return clPs3;
    }

    public void defineTablaPs3() {
        long lngRegistros = 1;
        long lngDesdeRegistro;

        //DEFINIMOS LA TABLA MODELO
        DefaultTableModel tablaClientes = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setDefaultRenderer(Object.class, new Render());

        //LE AGREGAMOS EL TITULO DE LAS COLUMNAS DE LA TABLA EN UN ARREGLO
        String strTitulos[] = {"ID", "CODIGO", "NOMBRE", "REGION", "IDIOMAS", "PLEYER", "DISCO", "IMAGEN"};

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
        List<ClPs3> lista = dAOPs3.leerPs3();
        Object fila[] = new Object[8];
//        String datos[]=new String [3];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = Integer.toString(lista.get(i).getId());
            fila[1] = lista.get(i).getCodigo();
            fila[2] = lista.get(i).getNombre();
            fila[3] = lista.get(i).getRegion();
            fila[4] = lista.get(i).getIdiomas();
            fila[5] = lista.get(i).getJugadores();
            fila[6] = lista.get(i).getDisco();
            try {
                String urlImagen = System.getProperties().getProperty("user.dir")+"/IMG/PS3/"+lista.get(i).getCodigo()+".jpg";
                ImageIcon icon = new ImageIcon(urlImagen);
                ImageIcon imgi = new ImageIcon(icon.getImage().getScaledInstance(120,60,Image.SCALE_DEFAULT)); 
//                byte[] bi = lista.get(i).getImagen();
//                BufferedImage image = null;
//                image = ImageIO.read(new ByteArrayInputStream(bi));
//                ImageIcon imgi = new ImageIcon(image.getScaledInstance(120, 60, 0));
                fila[7] = new JLabel(imgi);

            } catch (Exception ex) {
                fila[7] = new JLabel("No imagen");
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

    public void defineTablaPs3Buscar(ClPs3 ps3){
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
        String strTitulos[]={"ID","CODIGO","NOMBRE","REGION","IDIOMAS","PLEYER","DISCO","IMAGEN"};
        
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
        List<ClPs3> lista=dAOPs3.leerBuscar(ps3);
        Object fila[] = new Object[8];
//        String datos[]=new String [3];
        for (int i = 0; i < lista.size(); i++) {
            fila[0]=Integer.toString(lista.get(i).getId());
            fila[1]=lista.get(i).getCodigo();
            fila[2]=lista.get(i).getNombre();
            fila[3]=lista.get(i).getRegion();
            fila[4]=lista.get(i).getIdiomas();
            fila[5]=lista.get(i).getJugadores();
            fila[6]=lista.get(i).getDisco();
            try{
                String urlImagen = System.getProperties().getProperty("user.dir")+"/IMG/PS3/"+lista.get(i).getCodigo()+".jpg";
                ImageIcon icon = new ImageIcon(urlImagen);
                ImageIcon imgi = new ImageIcon(icon.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT)); 
//                    byte[] bi = lista.get(i).getImagen();
//                    BufferedImage image = null;
//                    InputStream in = new ByteArrayInputStream(bi);
//                    image = ImageIO.read(in);
//                    ImageIcon imgi = new ImageIcon(image.getScaledInstance(60, 60, 0));
                    fila[7] = new JLabel(imgi);

                }catch(Exception ex){
                    fila[7] = new JLabel("No imagen");
                }
            tablaClientes.addRow(fila);
        }
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
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRegion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIdiomas = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jsJugadores = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        txtDisco = new javax.swing.JTextField();
        jcCapasidad = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtRuta = new javax.swing.JTextField();
        lblImgen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnDescarImg = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Codigo:");

        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCodigo.setText("SCES00900");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre.setText("Marvel vs. Capcom Clash of Super Heroes EX Edition ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Region:");

        txtRegion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRegion.setText("NTSC-U");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Idiomas:");

        txtIdiomas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIdiomas.setText("Inglés-Frances-Alemán-Italiano-Español-Holandés-Checo-Húngaro-Polaco");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Juegadores:");

        jsJugadores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Disco 1:");

        txtDisco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDisco.setText("609");

        jcCapasidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcCapasidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MB", "GB", "TB" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Imagen:");

        txtRuta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblImgen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblImgen.setText("lblImagen");

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

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
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
                .addComponent(jButton4)
                .addGap(8, 8, 8)
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
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcCapasidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel10)
                                .addGap(7, 7, 7)
                                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdiomas, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImgen, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtIdiomas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jsJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcCapasidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(26, 26, 26)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(lblImgen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado"));

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String nombre, ruta;
        final JFileChooser elegirImagen = new JFileChooser();
        FileNameExtensionFilter fi = new FileNameExtensionFilter("JPG","jpg");
        elegirImagen.setFileFilter(fi);
        elegirImagen.setMultiSelectionEnabled(false);
        int o = elegirImagen.showOpenDialog(this);
        if (o == JFileChooser.APPROVE_OPTION) {
            ruta = elegirImagen.getSelectedFile().getAbsolutePath();
            nombre = elegirImagen.getSelectedFile().getName();
            txtRuta.setText(ruta);
            Image preview = Toolkit.getDefaultToolkit().getImage(ruta);
            if (preview != null) {
                lblImgen.setText("");
                ImageIcon icon = new ImageIcon(preview.getScaledInstance(lblImgen.getWidth(), lblImgen.getHeight(), Image.SCALE_DEFAULT));
                lblImgen.setIcon(icon);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        if (!dAOPs3.sqlInsert(insert())) {
            JOptionPane.showMessageDialog(null, "Juego No Agregado");
        } else {
            JOptionPane.showMessageDialog(null, "Juegos Agregado");
            Limpiar();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        if (!dAOPs3.sqlUpdate(actualizar())) {
            JOptionPane.showMessageDialog(null, "Juego No Actualizado");
        } else {
            JOptionPane.showMessageDialog(null, "Juegos Actualizado");
            Limpiar();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (!dAOPs3.sqlDelete(eliLis())) {
            JOptionPane.showMessageDialog(null, "Juego No Eliminado");
        } else {
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
            List<ClPs3> datosCliente = dAOPs3.leerPs32(eliLis());
            for (int i = 0; i < datosCliente.size(); i++) {
                id = Integer.parseInt(String.valueOf(datosCliente.get(i).getId()));
//                nombre = datosCliente.get(i).getCodigo() + "-" + datosCliente.get(i).getNombre() + ".png";
                nombre = datosCliente.get(i).getCodigo() + ".jpg";
                byte[] bi = datosCliente.get(i).getImagen();
                BufferedImage image = null;
                InputStream in = new ByteArrayInputStream(bi);
                image = ImageIO.read(in);
                img = image;
            }

            final JFileChooser elegirCarpeta = new JFileChooser();
            elegirCarpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int o = elegirCarpeta.showOpenDialog(this);
            if (o == JFileChooser.APPROVE_OPTION) {
                String path = elegirCarpeta.getSelectedFile().getAbsolutePath();
                String aux = nombre;
                StringTokenizer token = new StringTokenizer(aux, ".");
                token.nextToken();
                String formato = token.nextToken();
                ImageIO.write((RenderedImage) img, formato, new File(path + "\\" + nombre));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDescarImgActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int fila;

        //        this.txtId.getText();
        //        List<ClUsuario> datosCliente;
        fila = this.jTable1.rowAtPoint(evt.getPoint());

        if (fila > -1) {
            try {
                id = Integer.parseInt(String.valueOf(jTable1.getValueAt(fila, 0)));

                List<ClPs3> datosCliente = dAOPs3.leerPs32(eliLis());
                for (int i = 0; i < datosCliente.size(); i++) {
                    id = Integer.parseInt(String.valueOf(datosCliente.get(i).getId()));
                    this.txtCodigo.setText(datosCliente.get(i).getCodigo());
                    this.txtNombre.setText(datosCliente.get(i).getNombre());
                    this.txtRegion.setText(datosCliente.get(i).getRegion());
                    this.txtIdiomas.setText(datosCliente.get(i).getIdiomas());
                    this.jsJugadores.setValue(datosCliente.get(i).getJugadores());

                    String disco = datosCliente.get(i).getDisco();
                    String dato = disco.substring(0, disco.length() - 3);
                    this.txtDisco.setText(dato);

                    String capacidad2 = disco.substring(disco.length() - 2, disco.length());
                    cargarDatosCap(capacidad2);

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        FrHome frHome = new FrHome();
        frHome.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ClPs3 clPs3= null;
        clPs3 = new ClPs3(txtCodigo.getText().trim(), txtNombre.getText().trim());
        defineTablaPs3Buscar(clPs3);
    }//GEN-LAST:event_jButton4ActionPerformed

    public void cargarDatosCap(String ram) {
        if (ram.equals("MB")) {
            jcCapasidad.setSelectedIndex(0);
        }
        if (ram.equals("GB")) {
            jcCapasidad.setSelectedIndex(1);
        }
        if (ram.equals("TB")) {
            jcCapasidad.setSelectedIndex(2);
        }
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
            java.util.logging.Logger.getLogger(FrPs3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrPs3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrPs3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrPs3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrPs3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnDescarImg;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcCapasidad;
    private javax.swing.JSpinner jsJugadores;
    private javax.swing.JLabel lblImgen;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDisco;
    private javax.swing.JTextField txtIdiomas;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRegion;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
