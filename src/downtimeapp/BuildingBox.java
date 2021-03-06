/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package downtimeapp;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author natha_000
 */
public class BuildingBox extends javax.swing.JPanel {

    /**
     * Creates new form BuildingBoxtemp
     */
    Building building;
    Player owner;
    boolean isbuilding;
    
    public BuildingBox(Building build, Player character, boolean isbuild) {
        initComponents();
        building = build;
        owner = character;
        isbuilding = isbuild;
        if(isbuild){
            BuildDetailsButton.setText("Building Details");
        }else{
            BuildDetailsButton.setText("Org. Details");
        }
    }
    
    public void updatebuildbox(){
        BuildGoodTotal.setText(Integer.toString(building.bonuses[0]));
        BuildInfTotal.setText(Integer.toString(building.bonuses[1]));
        BuildLaborTotal.setText(Integer.toString(building.bonuses[2]));
        BuildMagicTotal.setText(Integer.toString(building.bonuses[3]));
        BuildName.setText(building.name);
        BuildgpTotal.setText(Integer.toString(building.bonuses[4]));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuildName = new javax.swing.JLabel();
        BuildGoodTotal = new javax.swing.JLabel();
        BuildMagicTotal = new javax.swing.JLabel();
        BuildLaborTotal = new javax.swing.JLabel();
        BuildInfTotal = new javax.swing.JLabel();
        BuildgpTotal = new javax.swing.JLabel();
        BuildDetailsButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BuildName.setText("jLabel13");

        BuildGoodTotal.setText("0");
        BuildGoodTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BuildGoodTotal.setMaximumSize(new java.awt.Dimension(48, 16));
        BuildGoodTotal.setMinimumSize(new java.awt.Dimension(24, 16));

        BuildMagicTotal.setText("0");
        BuildMagicTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BuildMagicTotal.setMaximumSize(new java.awt.Dimension(48, 16));
        BuildMagicTotal.setMinimumSize(new java.awt.Dimension(24, 16));

        BuildLaborTotal.setText("0");
        BuildLaborTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BuildLaborTotal.setMaximumSize(new java.awt.Dimension(48, 16));
        BuildLaborTotal.setMinimumSize(new java.awt.Dimension(24, 16));

        BuildInfTotal.setText("0");
        BuildInfTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BuildInfTotal.setMaximumSize(new java.awt.Dimension(48, 16));
        BuildInfTotal.setMinimumSize(new java.awt.Dimension(24, 16));

        BuildgpTotal.setText("0");
        BuildgpTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BuildgpTotal.setMaximumSize(new java.awt.Dimension(48, 16));
        BuildgpTotal.setMinimumSize(new java.awt.Dimension(24, 16));

        BuildDetailsButton.setText("Building Details");
        BuildDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuildDetailsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BuildName, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BuildGoodTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BuildInfTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BuildLaborTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BuildMagicTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BuildgpTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BuildDetailsButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BuildName)
                    .addComponent(BuildGoodTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuildInfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuildLaborTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuildMagicTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuildgpTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuildDetailsButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BuildDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuildDetailsButtonActionPerformed
        // TODO add your handling code here:
        JDialog mydialog = new JDialog();
        buildinggui detailview = new buildinggui(building, owner, isbuilding);
        mydialog.add(detailview);
        abwinlist listener = new abwinlist(this);
        mydialog.addWindowListener(listener);
        mydialog.setSize(mydialog.getPreferredSize());
        mydialog.setVisible(true);
    }//GEN-LAST:event_BuildDetailsButtonActionPerformed

    class abwinlist extends WindowAdapter{
        private BuildingBox buildbox;
        public abwinlist(BuildingBox box){
            buildbox = box;
        }

        @Override
        public void windowClosing(WindowEvent we) {
            buildbox.updatebuildbox();
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuildDetailsButton;
    private javax.swing.JLabel BuildGoodTotal;
    private javax.swing.JLabel BuildInfTotal;
    private javax.swing.JLabel BuildLaborTotal;
    private javax.swing.JLabel BuildMagicTotal;
    private javax.swing.JLabel BuildName;
    private javax.swing.JLabel BuildgpTotal;
    // End of variables declaration//GEN-END:variables
}
