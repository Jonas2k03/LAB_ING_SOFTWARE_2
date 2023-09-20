/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicauca.openmarket.presentation;

import co.edu.unicauca.openmarket.access.CategoryRepository;
import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Product;
import co.edu.unicauca.openmarket.domain.service.CategoryService;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import co.edu.unicauca.openmarket.infra.Messages;

/**
 *
 * @author Libardo Pantoja
 */
public class GUIProductsFind extends javax.swing.JDialog {

    private ProductService productService;
    private CategoryService categoryService;

    private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

    /**
     * Creates new form GUIProductsFind
     */
    public GUIProductsFind(java.awt.Frame parent, boolean modal, ProductService productService, CategoryService categoryService) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        initializeTable();
        jComboBoxCategoryFind.hide();
        this.productService = productService;
        this.categoryService = categoryService;
        setLocationRelativeTo(null); //centrar al ventana
    }

    private void initializeTable() {
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Name", "Description", "Categoria"
                }
        ));
    }

    private void fillTable(List<Product> listProducts) {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();

        Object rowData[] = new Object[4];//No columnas
        for (int i = 0; i < listProducts.size(); i++) {
            rowData[0] = listProducts.get(i).getProductId();
            rowData[1] = listProducts.get(i).getName();
            rowData[2] = listProducts.get(i).getDescription();
            rowData[3] = listProducts.get(i).getCategory().getName();

            model.addRow(rowData);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        pnlNorth = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rdoId = new javax.swing.JRadioButton();
        rdoName = new javax.swing.JRadioButton();
        jRadioButtonCategoryFind = new javax.swing.JRadioButton();
        jComboBoxCategoryFind = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnSearchAll = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Búsqueda de productos");

        pnlCenter.setLayout(new java.awt.BorderLayout());

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblProducts);

        pnlCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

        pnlNorth.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Buscar por:");
        pnlNorth.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 13, -1, -1));

        buttonGroup1.add(rdoId);
        rdoId.setSelected(true);
        rdoId.setText("Id");
        rdoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoIdActionPerformed(evt);
            }
        });
        pnlNorth.add(rdoId, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 10, -1, -1));

        buttonGroup1.add(rdoName);
        rdoName.setText("Nombre del producto");
        rdoName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNameActionPerformed(evt);
            }
        });
        pnlNorth.add(rdoName, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 10, -1, -1));

        buttonGroup1.add(jRadioButtonCategoryFind);
        jRadioButtonCategoryFind.setText("Categoria");
        jRadioButtonCategoryFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCategoryFindActionPerformed(evt);
            }
        });
        pnlNorth.add(jRadioButtonCategoryFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 10, -1, -1));

        pnlNorth.add(jComboBoxCategoryFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 10, -1, -1));

        txtSearch.setPreferredSize(new java.awt.Dimension(62, 32));
        pnlNorth.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(484, 5, -1, -1));

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        pnlNorth.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 9, -1, -1));

        btnSearchAll.setText("Buscar Todos");
        btnSearchAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAllActionPerformed(evt);
            }
        });
        pnlNorth.add(btnSearchAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 9, -1, -1));

        getContentPane().add(pnlNorth, java.awt.BorderLayout.PAGE_START);

        btnClose.setText("Cerrar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        jPanel1.add(btnClose);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSearchAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAllActionPerformed
        fillTable(productService.findAllProducts());
    }//GEN-LAST:event_btnSearchAllActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String search = txtSearch.getText();
        List<Product> products = new ArrayList<>();
        try {
            
        
        if (rdoId.isSelected()) {
            products.clear();
            products.add(productService.findProductById(Long.parseLong(search)));
            fillTable(products);
        } else if (rdoName.isSelected()) {
            products.clear();

            fillTable(productService.findProductByName(search));
        } else if (jRadioButtonCategoryFind.isSelected()) {

            
            GUIProducts gUIProducts = new GUIProducts(productService, categoryService);
            if (jComboBoxCategoryFind.getSelectedIndex()==0) {
                Messages.showMessageDialog("Por favor seleccione una categoria", "Alerta");
                return;
            }
            else {
            Category category =  gUIProducts.findCategoryByIndex(Long.valueOf(jComboBoxCategoryFind.getSelectedIndex()));
            fillTable(productService.findProductsByCategory(category.getCategoryId()));
            }
            
        } else {
            if (txtSearch.getText() == "") {
                JOptionPane.showMessageDialog(null, "Debe llenar el cuadro de texto");
            }

            if (products.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el producto específicado, pipipipi");

            }

        }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se han encontrado productos");
        }


    }//GEN-LAST:event_btnSearchActionPerformed

    private void jRadioButtonCategoryFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCategoryFindActionPerformed
        //rdoId.setSelected(true);
        List<Category> listCategories = categoryService.findAllCategorys();
        if(listCategories.isEmpty()) {
            Messages.showMessageDialog("No hay categorias registradas", "ATENCION");
            return;
        }
        else {
            update_cmbCategories(listCategories);
            
        }
            
        rdoName.setSelected(false);
        jComboBoxCategoryFind.show();
        txtSearch.hide();
    }//GEN-LAST:event_jRadioButtonCategoryFindActionPerformed

    private void rdoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoIdActionPerformed
        jRadioButtonCategoryFind.setSelected(false);

    }//GEN-LAST:event_rdoIdActionPerformed

    private void rdoNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNameActionPerformed
        jRadioButtonCategoryFind.setSelected(false);

    }//GEN-LAST:event_rdoNameActionPerformed

    private void update_cmbCategories(List<Category> listCategories) {
        model.addElement("Seleccione una opcion...");
        for (Category category : listCategories) {
            model.addElement(category.getName());
        }
        jComboBoxCategoryFind.setModel(model);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchAll;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBoxCategoryFind;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonCategoryFind;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlNorth;
    private javax.swing.JRadioButton rdoId;
    private javax.swing.JRadioButton rdoName;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
