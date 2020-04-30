/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

//JSON
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Toolkit;

//JAVA
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

//IMAGE
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

//SWING
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

//REQUEST
import org.jsoup.Jsoup;

/**
 *
 * @author ecyk
 */

public class CoronaApp extends javax.swing.JFrame {


    /**
     * Creates new form CoronaApp
     * @param country
     * @return 
     * @throws java.io.IOException
     */

    public ArrayList<String> returnData(String country) throws IOException{
        String requestJson;
        if(country == "World"){ // Total veri
        requestJson = Jsoup.connect("https://api.collectapi.com/corona/totalData")
                                .timeout(20000) // api request timeout
                                .header("content-type", "text/javascript")
                                .header("authorization", "apikey 5BNGKjmUL16yvBIMiYJ4Sb:6OGtIwLR43FvSvU7vSwTtH")
                                .userAgent("Mozilla/5.0 (Windows NT 6.1; rv:40.0) Gecko/20100101 Firefox/40.0")
                                .ignoreContentType(true)
                                .get()
                                .body()
                                .text();
        }
        
        else{ // Ülke verileri
        requestJson = Jsoup.connect("https://api.collectapi.com/corona/countriesData?&country=" + country)
                                .timeout(20000) // api request timeout
                                .header("content-type", "text/javascript")
                                .header("authorization", "apikey 5BNGKjmUL16yvBIMiYJ4Sb:6OGtIwLR43FvSvU7vSwTtH")
                                .userAgent("Mozilla/5.0 (Windows NT 6.1; rv:40.0) Gecko/20100101 Firefox/40.0")
                                .ignoreContentType(true)
                                .get()
                                .body()
                                .text();
        }

        JsonObject jsonObject = new JsonParser().parse(requestJson).getAsJsonObject(); 

        JsonElement totalDeaths;
        JsonElement totalCases;
        JsonElement totalRecovered;
        
        if(country == "Turkey" || country == "World"){ // Json array yok
            totalDeaths = jsonObject.get("result").getAsJsonObject().get("totalDeaths"); // toplam ölüm
            totalCases = jsonObject.get("result").getAsJsonObject().get("totalCases"); // toplam vaka
            totalRecovered = jsonObject.get("result").getAsJsonObject().get("totalRecovered"); // toplam kurtalrılan
        }
        else{ // Json array var
            totalDeaths = jsonObject.get("result").getAsJsonArray().get(0).getAsJsonObject().get("totalDeaths"); // toplam ölüm
            totalCases = jsonObject.get("result").getAsJsonArray().get(0).getAsJsonObject().get("totalCases"); // toplam vaka
            totalRecovered = jsonObject.get("result").getAsJsonArray().get(0).getAsJsonObject().get("totalRecovered"); // toplam kurtalrılan
        }
        
        ArrayList<String> list = new ArrayList<>();
        list.add(totalDeaths.toString());
        list.add(totalCases.toString());
        list.add(totalRecovered.toString());
     return list; // listeyi döndür {totalDeaths, totalCases, totalRecovered}
    }
    
    DefaultTreeModel model;
    private void tree(){
      
      //Tüm Dünya
      DefaultMutableTreeNode world = new DefaultMutableTreeNode("World"); // root node
      DefaultTreeModel model = new DefaultTreeModel(world);
      
      //Ülkeler
      world.add(new DefaultMutableTreeNode("Turkey"));
      world.add(new DefaultMutableTreeNode("Germany"));
      world.add(new DefaultMutableTreeNode("France"));
      world.add(new DefaultMutableTreeNode("UK"));
      world.add(new DefaultMutableTreeNode("Italy"));
      world.add(new DefaultMutableTreeNode("Spain"));
      world.add(new DefaultMutableTreeNode("USA"));
      world.add(new DefaultMutableTreeNode("Russia"));
      world.add(new DefaultMutableTreeNode("China"));
      world.add(new DefaultMutableTreeNode("Austria"));
      world.add(new DefaultMutableTreeNode("Poland"));
      jTree1.setVisible(true);
      jTree1.setModel(model);
    }
    
    public void DisplayImage(String flagName) throws IOException // resimi jLabel2 atar ve seçilen ülkenin bayrağını gösterir
    {
            String path = new File(".").getCanonicalPath(); // CoronaApp.java nın bulunduğu dizin
            String fullPath = path.replace("\\", "\\\\") + "\\\\src\\\\main\\\\java\\\\com\\\\mycompany\\\\mavenproject2\\\\flags\\\\" + flagName +".jpg"; //  flags kalsörünün tam konumu 
            System.out.println(fullPath);
            BufferedImage img = ImageIO.read(new File(fullPath));
            jLabel2.setIcon(new ImageIcon(img)); // resmi yerleştir
    }
    
    public CoronaApp() throws IOException {
        setIcon();
        initComponents();
        tree(); //JFrame
        jLabel1.setText("HAZIR..."); 

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CoronApp");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setToolTipText("");
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Toplam Ölümler", "Toplam Vakalar", "Toplam Tedavi Edilen"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setText("STATUS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked

    }//GEN-LAST:event_jTree1MouseClicked
    
    DefaultMutableTreeNode selectedNode;
    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        selectedNode = (DefaultMutableTreeNode)jTree1.getLastSelectedPathComponent();
        System.out.println("Seçilen Ülke: " + selectedNode);
        
        if(selectedNode != null){
            try {
                
                ArrayList<String> data = returnData(selectedNode.toString()); // döndürülüen verileri diziye eşitler
                System.out.println(data);
                String data1 = data.get(0); // totalDeaths
                String data2 = data.get(1); // totalCases
                String data3 = data.get(2); // totalRecovered
                
                Object[] row = { data1, data2, data3 }; // verileri rowa yerleştirir

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); // JTable model
                
                //model.addRow(row); satır ekleme
                model.setValueAt(data1, 0, 0); // Ölüm
                model.setValueAt(data2, 0, 1); // Vaka
                model.setValueAt(data3, 0, 2); // Tedavi Edilen
                
                DisplayImage(selectedNode.toString()); // Seçilen ülkenin bayrağını gösterir
                
                jLabel1.setText("VERİ TABLOYA YAZILDI..");
                
            } catch (IOException ex) {
                Logger.getLogger(CoronaApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            jLabel1.setText("HATA, TEKRAR DENEYİN...");
        }
    }//GEN-LAST:event_jTree1ValueChanged

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
            java.util.logging.Logger.getLogger(CoronaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CoronaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CoronaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CoronaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CoronaApp().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(CoronaApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

    private void setIcon() throws IOException {
        String iconPath = new File(".").getCanonicalPath().replace("\\", "\\\\") + "\\\\src\\\\main\\\\java\\\\com\\\\mycompany\\\\mavenproject2\\\\flags\\\\" + "icon.png";
        setIconImage(Toolkit.getDefaultToolkit().getImage(iconPath));
    }
}
