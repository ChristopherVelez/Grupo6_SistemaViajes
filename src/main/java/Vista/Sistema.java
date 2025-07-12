/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.ClienteDAO;
import Modelo.ClienteDTO;
import Controlador.FacturaDAO;
import Modelo.FacturaDTO;
import Controlador.ReservaDAO;
import Modelo.ReservaDTO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.formdev.flatlaf.FlatLightLaf;
import java.util.Collections;
import javax.swing.UIManager;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 *
 * @author Mini Wernaso
 */
public class Sistema extends javax.swing.JFrame {

    ClienteDTO cl = new ClienteDTO();
    ClienteDAO client = new ClienteDAO();
    ReservaDTO reservadto = new ReservaDTO();
    ReservaDAO reservadao = new ReservaDAO();
    FacturaDTO facturadto = new FacturaDTO();
    FacturaDAO facturadao = new FacturaDAO();
    DefaultTableModel modelo = new DefaultTableModel();

    public Sistema() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Error al aplicar FlatLaf.");
        }
        initComponents();
        this.setLocationRelativeTo(null);
        txtCodigoCliente.setVisible(false);
        txtCodigoReserva.setVisible(false);
        txtCodigoFactura.setVisible(false);
        txtcodigosReservasFactura.setVisible(false);
        txtFechadeEmisionFactura.setVisible(false);
        txtBuscarClientes.putClientProperty("JTextField.placeholderText", "Buscar por Código, DNI o Nombre");
        txtBuscarReservas.putClientProperty("JTextField.placeholderText", "Buscar por Código de reserva o código cliente");

        CargarClientesEnComboBox();
        ListarReservas();
        ListarCliente();
        ListarFacturas();
    }

    public void ListarCliente() {
        LimpiarTabla();
        List<ClienteDTO> ListarCl = client.ListarCliente();
        modelo = (DefaultTableModel) TableCliente.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarCl.size(); i++) {
            ob[0] = ListarCl.get(i).getcodigoCliente();
            ob[1] = ListarCl.get(i).getDni();
            ob[2] = ListarCl.get(i).getNombre();
            ob[3] = ListarCl.get(i).getTelefono();
            ob[4] = ListarCl.get(i).getDireccion();
            modelo.addRow(ob);
        }
        TableCliente.setModel(modelo);
    }

    public void ListarReservas() {
        LimpiarTabla();
        List<ReservaDTO> ListarRs = reservadao.ListarReservas();
        modelo = (DefaultTableModel) TableReservarPasaje.getModel(); // Asegúrate del nombre de tu JTable
        Object[] ob = new Object[8];

        for (int i = 0; i < ListarRs.size(); i++) {
            ob[0] = ListarRs.get(i).getCodigoReserva();
            ob[1] = ListarRs.get(i).getCodigoCliente();
            ob[2] = ListarRs.get(i).getOrigen();
            ob[3] = ListarRs.get(i).getDestino();
            ob[4] = ListarRs.get(i).getFechaViaje();
            ob[5] = ListarRs.get(i).getHoraSalida();
            ob[6] = ListarRs.get(i).getAsientoAsignado();
            ob[7] = ListarRs.get(i).getPrecioPasaje();
            modelo.addRow(ob);
        }

        TableReservarPasaje.setModel(modelo);
    }

    public void ListarFacturas() {
        List<FacturaDTO> listaFacturas = facturadao.ListarFacturas();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Código", "Reservas", "Cliente", "Fecha", "Monto", "Pago", "Estado"});

        for (FacturaDTO f : listaFacturas) {
            String reservas = facturadao.obtenerCadenaReservasPorFactura(f.getCodigoFactura());

            modelo.addRow(new Object[]{
                f.getCodigoFactura(),
                reservas,
                f.getCodigoCliente(),
                f.getFechaEmision(),
                f.getMontoTotal(),
                f.getMetodoPago(),
                f.getEstadoFactura()
            });
        }

        System.out.println("Total filas cargadas en tabla: " + modelo.getRowCount());

        TableFacturas.setModel(modelo);
    }

    public void LimpiarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableCliente = new javax.swing.JTable();
        btnAgregarCliente = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        txtDireccion = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtBuscarClientes = new javax.swing.JTextField();
        txtCodigoCliente = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableReservarPasaje = new javax.swing.JTable();
        btnEditarReserva = new javax.swing.JButton();
        btnAgregarReserva = new javax.swing.JButton();
        btnNuevaReserva = new javax.swing.JButton();
        btnEliminarReserva = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cmbCodigoCliente = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtOrigen = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDestino = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtFechaViaje = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPrecioPasaje = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtBuscarReservas = new javax.swing.JTextField();
        txtCodigoReserva = new javax.swing.JTextField();
        txtAsientoAsignado = new javax.swing.JTextField();
        cmbHoraSalida = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtMontoTotal = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnAgregarFactura = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnEliminarFactura = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableFacturas = new javax.swing.JTable();
        cmbCodigoClienteFactura = new javax.swing.JComboBox<>();
        btnNuevaFactura = new javax.swing.JButton();
        cmbMetodoPago = new javax.swing.JComboBox<>();
        cmbEstadoFactura = new javax.swing.JComboBox<>();
        txtBuscarFactura = new javax.swing.JTextField();
        txtFechadeEmisionFactura = new javax.swing.JTextField();
        txtCodigoFactura = new javax.swing.JTextField();
        txtcodigosReservasFactura = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnCliente = new javax.swing.JButton();
        btnReserva = new javax.swing.JButton();
        btnFactura = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        TableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Cliente", "DNI", "Nombre", "Teléfono", "Dirección"
            }
        ));
        TableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableCliente);

        btnAgregarCliente.setText("Agregar Cliente");
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });

        btnNuevoCliente.setText("Nuevo");
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        btnEditarCliente.setText("Editar");
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setText("Eliminar");
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        jLabel17.setText("Dirección");

        jLabel16.setText("Teléfono");

        jLabel15.setText("Nombre");

        jLabel14.setText("DNI");

        txtBuscarClientes.setToolTipText("");
        txtBuscarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarClientesActionPerformed(evt);
            }
        });
        txtBuscarClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClientesKeyReleased(evt);
            }
        });

        txtCodigoCliente.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDNI, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnEliminarCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel14)
                    .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscarClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregarCliente)
                            .addComponent(btnEditarCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarCliente)
                            .addComponent(btnNuevoCliente)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtBuscarClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cliente", jPanel4);

        TableReservarPasaje.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cliente", "Origen", "Destino", "Fecha de viaje", "Hora de salida", "Asiento asignado", "Precio del pasaje"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableReservarPasaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableReservarPasajeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableReservarPasaje);

        btnEditarReserva.setText("Editar");
        btnEditarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarReservaActionPerformed(evt);
            }
        });

        btnAgregarReserva.setText("Agregar");
        btnAgregarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarReservaActionPerformed(evt);
            }
        });

        btnNuevaReserva.setText("Nuevo");
        btnNuevaReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaReservaActionPerformed(evt);
            }
        });

        btnEliminarReserva.setText("Eliminar");
        btnEliminarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarReservaActionPerformed(evt);
            }
        });

        jLabel5.setText("Cliente ");

        cmbCodigoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbCodigoClienteMouseClicked(evt);
            }
        });
        cmbCodigoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCodigoClienteActionPerformed(evt);
            }
        });

        jLabel7.setText("Origen");

        jLabel8.setText("Destino\t");

        jLabel9.setText("Fecha del viaje\t");

        jLabel10.setText("Hora de salida\t");

        jLabel13.setText("Precio del pasaje\t");

        jLabel11.setText("Asiento asignado\t");

        txtBuscarReservas.setToolTipText("");
        txtBuscarReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarReservasActionPerformed(evt);
            }
        });
        txtBuscarReservas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarReservasKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBuscarReservas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBuscarReservas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        );

        txtCodigoReserva.setEditable(false);

        cmbHoraSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07:00:00", "08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCodigoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFechaViaje, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDestino, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOrigen, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbCodigoCliente, 0, 141, Short.MAX_VALUE)
                            .addComponent(cmbHoraSalida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarReserva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditarReserva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevaReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrecioPasaje)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))
                                .addGap(0, 93, Short.MAX_VALUE))
                            .addComponent(txtAsientoAsignado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cmbCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtAsientoAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)))
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecioPasaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaViaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAgregarReserva)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarReserva)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarReserva)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevaReserva)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(txtCodigoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reservas", jPanel2);

        jLabel19.setText("Código de Cliente");

        jLabel20.setText("Monto total\t");

        txtMontoTotal.setEditable(false);

        jLabel21.setText("Método de pago\t");

        jLabel22.setText("Estado de factura\t");

        btnAgregarFactura.setText("Emitir Factura");
        btnAgregarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFacturaActionPerformed(evt);
            }
        });

        btnImprimir.setText("Imprimir");
        btnImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImprimirMouseClicked(evt);
            }
        });
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnEliminarFactura.setText("Anular");
        btnEliminarFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarFacturaMouseClicked(evt);
            }
        });
        btnEliminarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFacturaActionPerformed(evt);
            }
        });

        TableFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código de factura	", "Código de cliente", "Codigo de reservas", "Fecha de emisión	", "Monto total	", "Método de pago	", "Estado de factura	"
            }
        ));
        TableFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableFacturasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableFacturas);

        cmbCodigoClienteFactura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCodigoClienteFacturaItemStateChanged(evt);
            }
        });
        cmbCodigoClienteFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbCodigoClienteFacturaMouseClicked(evt);
            }
        });
        cmbCodigoClienteFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCodigoClienteFacturaActionPerformed(evt);
            }
        });

        btnNuevaFactura.setText("Nuevo");
        btnNuevaFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaFacturaActionPerformed(evt);
            }
        });

        cmbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));

        cmbEstadoFactura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pagado", "Pendiente", " " }));

        txtBuscarFactura.setText("Buscar:");
        txtBuscarFactura.setToolTipText("");
        txtBuscarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarFacturaActionPerformed(evt);
            }
        });

        txtFechadeEmisionFactura.setEditable(false);

        txtCodigoFactura.setEditable(false);

        txtcodigosReservasFactura.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCodigoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnAgregarFactura)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22)
                                    .addComponent(cmbCodigoClienteFactura, 0, 168, Short.MAX_VALUE)
                                    .addComponent(txtMontoTotal)
                                    .addComponent(cmbMetodoPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbEstadoFactura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(txtFechadeEmisionFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnNuevaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscarFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(183, 183, 183)
                    .addComponent(txtcodigosReservasFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(732, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtBuscarFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(txtCodigoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCodigoClienteFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechadeEmisionFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbEstadoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregarFactura)
                            .addComponent(btnEliminarFactura))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevaFactura)
                            .addComponent(btnImprimir))
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(txtcodigosReservasFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(313, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Factura", jPanel5);

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));

        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cliente.png"))); // NOI18N
        btnCliente.setText("Cliente");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        btnReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Reservar.png"))); // NOI18N
        btnReserva.setText("Reservar de pasajes");
        btnReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservaActionPerformed(evt);
            }
        });

        btnFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pago.png"))); // NOI18N
        btnFactura.setText("Facturador");
        btnFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnReserva, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
            .addComponent(btnCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(btnCliente)
                .addGap(32, 32, 32)
                .addComponent(btnReserva)
                .addGap(31, 31, 31)
                .addComponent(btnFactura)
                .addGap(136, 136, 136))
        );

        jPanel3.setBackground(new java.awt.Color(255, 153, 102));

        jLabel1.setFont(new java.awt.Font("Constantia", 1, 30)); // NOI18N
        jLabel1.setText("Sistema de agencia de viaje");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LogoP.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel3)
                .addGap(198, 198, 198)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        LimpiarTabla();
        ListarCliente();
        LimpiarCliente();
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservaActionPerformed
        // TODO add your handling code here:
        LimpiarTabla();
        ListarReservas();
        LimpiarReserva();
        CargarClientesEnComboBox();
        jTabbedPane1.setSelectedIndex(1);

    }//GEN-LAST:event_btnReservaActionPerformed

    private void btnFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturaActionPerformed
        // TODO add your handling code here:
        LimpiarTabla();
        ListarFacturas();
        LimpiarFactura();
        CargarClientesEnComboBoxClienteFactura();
        jTabbedPane1.setSelectedIndex(2);

    }//GEN-LAST:event_btnFacturaActionPerformed

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        if (!"".equals(txtDNI.getText()) && !"".equals(txtNombreCliente.getText()) && !"".equals(txtTelefono.getText()) && !"".equals(txtDireccion.getText())) {
            cl.setDni(txtDNI.getText());
            cl.setNombre(txtNombreCliente.getText());
            cl.setTelefono(txtTelefono.getText());
            cl.setDireccion(txtDireccion.getText());
            client.RegistrarCliente(cl);
            JOptionPane.showMessageDialog(null, "Cliente Registrado");
            LimpiarTabla();
            ListarCliente();
            LimpiarCliente();

        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");

        }
    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        // TODO add your handling code here:
        LimpiarCliente();
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtCodigoCliente.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {

            if (!"".equals(txtDNI.getText()) && !"".equals(txtNombreCliente.getText()) && !"".equals(txtTelefono.getText()) && !"".equals(txtDireccion.getText())) {
                cl.setDni(txtDNI.getText());
                cl.setNombre(txtNombreCliente.getText());
                cl.setTelefono(txtTelefono.getText());
                cl.setDireccion(txtDireccion.getText());
                cl.setcodigoCliente(Integer.parseInt(txtCodigoCliente.getText()));
                client.ModificarCliente(cl);
                LimpiarTabla();
                LimpiarCliente();
                ListarCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }

        }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtCodigoCliente.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int CodigoCliente = Integer.parseInt(txtCodigoCliente.getText());
                client.EliminarCliente(CodigoCliente);
                LimpiarTabla();
                ListarCliente();
                LimpiarCliente();
            }
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnAgregarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFacturaActionPerformed
        // TODO add your handling code here:
        if (cmbEstadoFactura.getSelectedIndex() != -1
                && cmbMetodoPago.getSelectedIndex() != -1
                && cmbCodigoClienteFactura.getSelectedIndex() != -1) {

            FacturaDTO fac = new FacturaDTO();

            // Fecha actual
            java.util.Date fechaActual = new java.util.Date();
            fac.setFechaEmision(new java.sql.Date(fechaActual.getTime()));

            // 🔹 Obtener cliente
            int codigoCliente = Integer.parseInt(cmbCodigoClienteFactura.getSelectedItem().toString());
            fac.setCodigoCliente(codigoCliente);

            // 🔹 Obtener reservas (esto debes ajustarlo si usas una JList u otra forma de seleccionar múltiples reservas)
            List<Integer> reservas = obtenerReservasSeleccionadas(); // deberías implementar este método según tu UI
            fac.setReserva(reservas);

            if (reservas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una reserva para la factura.");
                return;
            }

            // 🔹 Calcular monto total desde DAO
            double montoCalculado = facturadao.calcularMontoTotal(reservas);
            fac.setMontoTotal(montoCalculado);

            // Método de pago
            fac.setMetodoPago(cmbMetodoPago.getSelectedItem().toString());

            // Estado
            String estado = cmbEstadoFactura.getSelectedItem().toString();
            fac.setEstadoFactura(estado);

            // 🔹 Registrar factura
            if (facturadao.RegistrarFactura(fac)) {
                JOptionPane.showMessageDialog(null, "Factura registrada correctamente");
                LimpiarTabla();
                LimpiarFactura();
                ListarFacturas();

            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar factura");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
        }
    }//GEN-LAST:event_btnAgregarFacturaActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnEliminarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarFacturaActionPerformed

    private void btnNuevaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaFacturaActionPerformed

    private void btnAgregarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarReservaActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtOrigen.getText()) && !"".equals(txtDestino.getText())
                && !"".equals(txtFechaViaje.getText()) && cmbHoraSalida.getSelectedItem() != null
                && !"".equals(txtPrecioPasaje.getText()) && !"".equals(txtAsientoAsignado.getText())) {

            try {
                int codigoCliente = Integer.parseInt(cmbCodigoCliente.getSelectedItem().toString());

                // Validar si el asiento ya está reservado
                boolean ocupado = reservadao.asientoYaReservado(
                        txtOrigen.getText(),
                        txtDestino.getText(),
                        txtFechaViaje.getText(),
                        cmbHoraSalida.getSelectedItem().toString(),
                        txtAsientoAsignado.getText()
                );

                if (ocupado) {
                    JOptionPane.showMessageDialog(null, "El asiento ya está reservado.");
                    return;
                }

                reservadto.setCodigoCliente(codigoCliente);
                reservadto.setOrigen(txtOrigen.getText());
                reservadto.setDestino(txtDestino.getText());
                reservadto.setFechaViaje(txtFechaViaje.getText());
                reservadto.setHoraSalida(cmbHoraSalida.getSelectedItem().toString());
                reservadto.setAsientoAsignado(txtAsientoAsignado.getText());
                reservadto.setPrecioPasaje(Double.parseDouble(txtPrecioPasaje.getText()));

                reservadao.RegistrarReserva(reservadto);

                JOptionPane.showMessageDialog(null, "Reserva Registrada");
                LimpiarTabla();
                LimpiarReserva();
                ListarReservas();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Los campos están vacíos");
        }
    }//GEN-LAST:event_btnAgregarReservaActionPerformed


    private void btnEditarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarReservaActionPerformed
        if ("".equals(txtCodigoReserva.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            // Validar campos obligatorios
            if (cmbCodigoCliente.getSelectedItem() != null
                    && !"".equals(txtOrigen.getText())
                    && !"".equals(txtDestino.getText())
                    && !"".equals(txtFechaViaje.getText())
                    && cmbHoraSalida.getSelectedItem() != null
                    && !"".equals(txtAsientoAsignado.getText())
                    && !"".equals(txtPrecioPasaje.getText())) {

                try {
                    int codigoReserva = Integer.parseInt(txtCodigoReserva.getText());
                    int codigoCliente = Integer.parseInt(cmbCodigoCliente.getSelectedItem().toString());

                    // Verificar si el asiento ya está reservado por otra reserva
                    boolean ocupado = reservadao.asientoYaReservado(
                            txtOrigen.getText(),
                            txtDestino.getText(),
                            txtFechaViaje.getText(),
                            cmbHoraSalida.getSelectedItem().toString(),
                            txtAsientoAsignado.getText(),
                            codigoReserva
                    );

                    if (ocupado) {
                        JOptionPane.showMessageDialog(null, "El asiento ya está reservado.");
                        return;
                    }

                    // Crear el objeto ReservaDTO con los nuevos datos
                    reservadto.setCodigoReserva(codigoReserva);
                    reservadto.setCodigoCliente(codigoCliente);
                    reservadto.setOrigen(txtOrigen.getText());
                    reservadto.setDestino(txtDestino.getText());
                    reservadto.setFechaViaje(txtFechaViaje.getText());
                    reservadto.setHoraSalida(cmbHoraSalida.getSelectedItem().toString());
                    reservadto.setAsientoAsignado(txtAsientoAsignado.getText());
                    reservadto.setPrecioPasaje(Double.parseDouble(txtPrecioPasaje.getText()));

                    // Editar la reserva
                    if (reservadao.editarReserva(reservadto)) {
                        JOptionPane.showMessageDialog(null, "Reserva actualizada correctamente.");
                        LimpiarTabla();
                        ListarReservas();
                        LimpiarReserva();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar la reserva.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }

            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            }
        }
    }//GEN-LAST:event_btnEditarReservaActionPerformed

    private void btnNuevaReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaReservaActionPerformed
        // TODO add your handling code here:
        LimpiarReserva();
    }//GEN-LAST:event_btnNuevaReservaActionPerformed

    private void btnEliminarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarReservaActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtCodigoReserva.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int CodigoReserva = Integer.parseInt(txtCodigoReserva.getText());
                reservadao.eliminarReserva(CodigoReserva);
                LimpiarTabla();
                ListarReservas();
                LimpiarReserva();
            }
        }
    }//GEN-LAST:event_btnEliminarReservaActionPerformed

    private void txtBuscarReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarReservasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarReservasActionPerformed

    private void txtBuscarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarClientesActionPerformed

    private void txtBuscarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarFacturaActionPerformed

    private void TableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableClienteMouseClicked
        // TODO add your handling code here:
        int fila = TableCliente.rowAtPoint(evt.getPoint());
        txtCodigoCliente.setText(TableCliente.getValueAt(fila, 0).toString());
        txtDNI.setText(TableCliente.getValueAt(fila, 1).toString());
        txtNombreCliente.setText(TableCliente.getValueAt(fila, 2).toString());
        txtTelefono.setText(TableCliente.getValueAt(fila, 3).toString());
        txtDireccion.setText(TableCliente.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableClienteMouseClicked

    private void cmbCodigoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCodigoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCodigoClienteActionPerformed

    private void cmbCodigoClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbCodigoClienteMouseClicked
        // TODO add your handling code here:
        CargarClientesEnComboBox();
    }//GEN-LAST:event_cmbCodigoClienteMouseClicked

    private void TableReservarPasajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableReservarPasajeMouseClicked
        // TODO add your handling code here:
        try {
            int fila = TableReservarPasaje.rowAtPoint(evt.getPoint());
            txtCodigoReserva.setText(TableReservarPasaje.getValueAt(fila, 0).toString());
            cmbCodigoCliente.setSelectedItem(TableReservarPasaje.getValueAt(fila, 1).toString());
            txtOrigen.setText(TableReservarPasaje.getValueAt(fila, 2).toString());
            txtDestino.setText(TableReservarPasaje.getValueAt(fila, 3).toString());
            txtFechaViaje.setText(TableReservarPasaje.getValueAt(fila, 4).toString());
            cmbHoraSalida.setSelectedItem(TableReservarPasaje.getValueAt(fila, 5).toString());
            txtAsientoAsignado.setText(TableReservarPasaje.getValueAt(fila, 6).toString());
            txtPrecioPasaje.setText(TableReservarPasaje.getValueAt(fila, 7).toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al seleccionar la fila: " + e.getMessage());
        }

    }//GEN-LAST:event_TableReservarPasajeMouseClicked

    private void txtBuscarClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClientesKeyReleased
        String texto = txtBuscarClientes.getText().trim();
        buscarYActualizarTabla(texto);    buscarYActualizarTabla(texto);    buscarYActualizarTabla(texto);    }//GEN-LAST:event_txtBuscarClientesKeyReleased

    private void txtBuscarReservasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarReservasKeyReleased
        // TODO add your handling code here:
        String texto = txtBuscarReservas.getText().trim();
        buscarYActualizarTablaReservas(texto);
    }//GEN-LAST:event_txtBuscarReservasKeyReleased

    private void cmbCodigoClienteFacturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCodigoClienteFacturaItemStateChanged
        // TODO add your handling code here:
        List<Integer> reservas = obtenerReservasSeleccionadas();
        double montoCalculado = facturadao.calcularMontoTotal(reservas);
        txtMontoTotal.setText(Double.toString(montoCalculado));
    }//GEN-LAST:event_cmbCodigoClienteFacturaItemStateChanged

    private void cmbCodigoClienteFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCodigoClienteFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCodigoClienteFacturaActionPerformed

    private void cmbCodigoClienteFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbCodigoClienteFacturaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cmbCodigoClienteFacturaMouseClicked

    private void btnImprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImprimirMouseClicked
        // TODO add your handling code here:
        pdf();
    }//GEN-LAST:event_btnImprimirMouseClicked

    private void TableFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableFacturasMouseClicked
        // TODO add your handling code here:
        try {
            int fila = TableFacturas.rowAtPoint(evt.getPoint());
            txtCodigoFactura.setText(TableFacturas.getValueAt(fila, 0).toString());
            txtcodigosReservasFactura.setText(TableFacturas.getValueAt(fila, 1).toString());
            cmbCodigoClienteFactura.setSelectedItem(TableFacturas.getValueAt(fila, 2).toString());
            txtFechadeEmisionFactura.setText(TableFacturas.getValueAt(fila, 3).toString());
            txtMontoTotal.setText(TableFacturas.getValueAt(fila, 4).toString());
            cmbMetodoPago.setSelectedItem(TableFacturas.getValueAt(fila, 5).toString());
            cmbEstadoFactura.setSelectedItem(TableFacturas.getValueAt(fila, 6).toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al seleccionar la fila: " + e.getMessage());
        }
    }//GEN-LAST:event_TableFacturasMouseClicked

    private void btnEliminarFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarFacturaMouseClicked
        // TODO add your handling code here:
        if (!"".equals(txtCodigoFactura.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de anular factura?");
            if (pregunta == 0) {
                int CodigoFactura = Integer.parseInt(txtCodigoFactura.getText());
                facturadao.eliminarFactura(CodigoFactura);
                LimpiarTabla();
                ListarFacturas();
                LimpiarFactura();
            }
        }
    }//GEN-LAST:event_btnEliminarFacturaMouseClicked

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
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableCliente;
    private javax.swing.JTable TableFacturas;
    private javax.swing.JTable TableReservarPasaje;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnAgregarFactura;
    private javax.swing.JButton btnAgregarReserva;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarReserva;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarFactura;
    private javax.swing.JButton btnEliminarReserva;
    private javax.swing.JButton btnFactura;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevaFactura;
    private javax.swing.JButton btnNuevaReserva;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnReserva;
    private javax.swing.JComboBox<String> cmbCodigoCliente;
    private javax.swing.JComboBox<String> cmbCodigoClienteFactura;
    private javax.swing.JComboBox<String> cmbEstadoFactura;
    private javax.swing.JComboBox<String> cmbHoraSalida;
    private javax.swing.JComboBox<String> cmbMetodoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtAsientoAsignado;
    private javax.swing.JTextField txtBuscarClientes;
    private javax.swing.JTextField txtBuscarFactura;
    private javax.swing.JTextField txtBuscarReservas;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtCodigoFactura;
    private javax.swing.JTextField txtCodigoReserva;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFechaViaje;
    private javax.swing.JTextField txtFechadeEmisionFactura;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtPrecioPasaje;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtcodigosReservasFactura;
    // End of variables declaration//GEN-END:variables
private void LimpiarCliente() {
        txtCodigoCliente.setText("");
        txtDNI.setText("");
        txtNombreCliente.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");

    }

    private void LimpiarReserva() {
        txtOrigen.setText("");
        txtDestino.setText("");
        txtFechaViaje.setText("");
        cmbHoraSalida.setSelectedIndex(-1); // Deselecciona el combo
        txtPrecioPasaje.setText("");
        txtAsientoAsignado.setText("");
        cmbCodigoCliente.setSelectedIndex(-1); // Deselecciona el combo
    }

    private void LimpiarFactura() {
        txtMontoTotal.setText("");
        cmbMetodoPago.setSelectedItem(-1);
        cmbEstadoFactura.setSelectedIndex(-1);
        cmbCodigoClienteFactura.setSelectedIndex(-1);
    }

    public void CargarClientesEnComboBox() {
        cmbCodigoCliente.removeAllItems();
        List<ClienteDTO> listaClientes = client.ListarCliente();

        for (ClienteDTO cl : listaClientes) {
            cmbCodigoCliente.addItem(String.valueOf(cl.getcodigoCliente()));
        }
    }

    public void CargarClientesEnComboBoxClienteFactura() {
        cmbCodigoClienteFactura.removeAllItems();
        List<ClienteDTO> listaClientes = client.ListarCliente();

        for (ClienteDTO cl : listaClientes) {
            cmbCodigoClienteFactura.addItem(String.valueOf(cl.getcodigoCliente()));
        }
    }

    private void buscarYActualizarTabla(String filtro) {
        List<ClienteDTO> lista = client.buscarClientesPorCodigoDniNombre(filtro);

        DefaultTableModel model = (DefaultTableModel) TableCliente.getModel();
        model.setRowCount(0); // limpiar tabla

        for (ClienteDTO c : lista) {
            Object[] fila = {
                c.getcodigoCliente(),
                c.getDni(),
                c.getNombre(),
                c.getTelefono(),
                c.getDireccion()
            };
            model.addRow(fila);
        }
    }

    private void buscarYActualizarTablaReservas(String filtro) {
        List<ReservaDTO> lista = reservadao.buscarReservasPorClienteOCodigo(filtro);

        DefaultTableModel model = (DefaultTableModel) TableReservarPasaje.getModel();
        model.setRowCount(0); // limpiar tabla

        for (ReservaDTO r : lista) {
            Object[] fila = {
                r.getCodigoReserva(),
                r.getCodigoCliente(),
                r.getOrigen(),
                r.getDestino(),
                r.getFechaViaje(),
                r.getHoraSalida(),
                r.getAsientoAsignado(),
                r.getPrecioPasaje()

            };
            model.addRow(fila);
        }
    }

    private List<Integer> obtenerReservasSeleccionadas() {
        Object item = cmbCodigoClienteFactura.getSelectedItem();

        if (item == null || item.toString().isEmpty()) {
            return Collections.emptyList();
        }

        try {
            int codigoCliente = Integer.parseInt(item.toString());
            return facturadao.obtenerReservasNoFacturadasPorCliente(codigoCliente);
        } catch (NumberFormatException e) {
            return Collections.emptyList();
        }
    }

    private void pdf() {
        if ("".equals(txtCodigoFactura.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            try {
                String dest = "src/main/java/pdf/factura" + txtCodigoFactura.getText() + ".pdf";
                File file = new File(dest);
                file.getParentFile().mkdirs(); // Crea los directorios si no existen

                PdfWriter writer = new PdfWriter(dest);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                // Imagen
                ImageData imageData = ImageDataFactory.create("src/main/resources/LogoP.png");
                Image img = new Image(imageData).scaleToFit(80, 80);

                // Datos del encabezado
                String ruc = "1212121212001";
                String nom = "Grupo 6";
                String tel = "094848484";
                String dir = "Ecuador";

                // Fecha
                Paragraph fecha = new Paragraph("Factura: "+txtCodigoFactura.getText()+"\nFecha: " + txtFechadeEmisionFactura.getText())
                        .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                        .setFontSize(11)
                        .setFontColor(ColorConstants.BLUE);

                // Tabla encabezado
                Table table = new Table(UnitValue.createPercentArray(new float[]{2, 1, 4, 2}))
                        .useAllAvailableWidth();
                table.addCell(new Cell().add(img).setBorder(null));
                table.addCell(new Cell().add(new Paragraph("")).setBorder(null));
                table.addCell(new Cell().add(new Paragraph("Ruc: " + ruc + "\nNombre: " + nom + "\nTeléfono: " + tel + "\nDirección: " + dir)).setBorder(null));
                table.addCell(new Cell().add(fecha).setTextAlignment(TextAlignment.RIGHT).setBorder(null));

                document.add(table);

                // Datos del cliente
                document.add(new Paragraph("\nDatos del cliente:\n")
                        .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                        .setFontSize(12));

                // Tabla del cliente
                Table tablaCli = new Table(UnitValue.createPercentArray(new float[]{2, 4, 3, 4}))
                        .useAllAvailableWidth();
                int codigoCliente = Integer.parseInt(cmbCodigoClienteFactura.getSelectedItem().toString());

                ClienteDTO cliente = new ClienteDAO().obtenerClientePorCodigo(codigoCliente);

                tablaCli.addHeaderCell(new Cell().add(new Paragraph("DNI").setBold()));
                tablaCli.addHeaderCell(new Cell().add(new Paragraph("Nombre").setBold()));
                tablaCli.addHeaderCell(new Cell().add(new Paragraph("Teléfono").setBold()));
                tablaCli.addHeaderCell(new Cell().add(new Paragraph("Dirección").setBold()));

                tablaCli.addCell(new Cell().add(new Paragraph(cliente.getDni())));
                tablaCli.addCell(new Cell().add(new Paragraph(cliente.getNombre())));
                tablaCli.addCell(new Cell().add(new Paragraph(cliente.getTelefono())));
                tablaCli.addCell(new Cell().add(new Paragraph(cliente.getDireccion())));

                document.add(tablaCli);

                // Tabla de reserva
                Table tablaReserva = new Table(UnitValue.createPercentArray(new float[]{4, 4, 3, 3, 3, 3}))
                        .useAllAvailableWidth();

                tablaReserva.addHeaderCell(new Cell().add(new Paragraph("Origen").setBold()));
                tablaReserva.addHeaderCell(new Cell().add(new Paragraph("Destino").setBold()));
                tablaReserva.addHeaderCell(new Cell().add(new Paragraph("Fecha Viaje").setBold()));
                tablaReserva.addHeaderCell(new Cell().add(new Paragraph("Hora Salida").setBold()));
                tablaReserva.addHeaderCell(new Cell().add(new Paragraph("Asiento").setBold()));
                tablaReserva.addHeaderCell(new Cell().add(new Paragraph("Precio").setBold()));

                // Suponiendo que txtcodigosReservasFactura contiene: "34,23,23"
                String codigosTexto = txtcodigosReservasFactura.getText(); // "34,23,23"

// Convertir a lista de enteros
                List<Integer> codigosreservas = Arrays.stream(codigosTexto.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                List<ReservaDTO> reservas = new ReservaDAO().obtenerReservasPorCodigosReservas(codigosreservas);

                double total = 0.0;
                for (ReservaDTO r : reservas) {
                    tablaReserva.addCell(new Cell().add(new Paragraph(r.getOrigen())));
                    tablaReserva.addCell(new Cell().add(new Paragraph(r.getDestino())));
                    tablaReserva.addCell(new Cell().add(new Paragraph(r.getFechaViaje().toString())));
                    tablaReserva.addCell(new Cell().add(new Paragraph(r.getHoraSalida())));
                    tablaReserva.addCell(new Cell().add(new Paragraph(r.getAsientoAsignado())));
                    tablaReserva.addCell(new Cell().add(new Paragraph(String.format("%.2f", r.getPrecioPasaje()))));

                    total += r.getPrecioPasaje();
                }

// 7. Añadir tabla de reservas al documento PDF
                document.add(new Paragraph("\nDetalle de reservas:\n")
                        .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                        .setFontSize(12));
                document.add(tablaReserva);
                
                
                
                
                // NUEVO: Método de Pago y Estado de Factura
            String metodoPago = cmbMetodoPago.getSelectedItem().toString();
            String estadoFactura = cmbEstadoFactura.getSelectedItem().toString();

            document.add(new Paragraph("\nDetalles de la factura:\n")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(12));

            Table tablaDetalles = new Table(UnitValue.createPercentArray(new float[]{3, 3}))
                    .useAllAvailableWidth();

            tablaDetalles.addCell(new Cell().add(new Paragraph("Método de Pago").setBold()));
            tablaDetalles.addCell(new Cell().add(new Paragraph("Estado de Factura").setBold()));

            tablaDetalles.addCell(new Cell().add(new Paragraph(metodoPago)));
            tablaDetalles.addCell(new Cell().add(new Paragraph(estadoFactura)));

            document.add(tablaDetalles);
                
                
                
                
                
                

// 8. Añadir total
                document.add(new Paragraph("\nMonto Total: $" + String.format("%.2f", total))
                        .setFontSize(11)
                        .setBold());

                document.close();
                System.out.println("PDF generado correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
