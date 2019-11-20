/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool
          with fuzzy matching, translation memory, keyword search,
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2014 Briac Pilpre
               2015 Aaron Madlon-Kay
               2016 Aaron Madlon-Kay
               Home page: http://www.omegat.org/
               Support center: http://groups.yahoo.com/group/OmegaT/

 This file is part of OmegaT.

 OmegaT is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 OmegaT is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **************************************************************************/

package org.omegat.gui.preferences.view;

import org.omegat.util.OStrings;

/**
 * @author Briac Pilpre
 * @author Aaron Madlon-Kay
 */
@SuppressWarnings("serial")
public class CustomColorSelectionPanel extends javax.swing.JPanel {

    /**
     * Creates new form CustomColorSelectionPanel
     */
    public CustomColorSelectionPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sampleEditorPane = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        colorStylesLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        colorStylesTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        resetCurrentColorButton = new javax.swing.JButton();
        colorChooser = new javax.swing.JColorChooser();

        sampleEditorPane.setEditable(false);
        sampleEditorPane.setText("Sample translation text");
        sampleEditorPane.setMinimumSize(new java.awt.Dimension(400, 100));
        sampleEditorPane.setName(""); // NOI18N
        sampleEditorPane.setPreferredSize(new java.awt.Dimension(400, 100));
        sampleEditorPane.setRequestFocusEnabled(false);

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(colorStylesLabel, OStrings.getString("GUI_COLORS_COLOR")); // NOI18N
        jPanel1.add(colorStylesLabel, java.awt.BorderLayout.NORTH);

        colorStylesTable.setFillsViewportHeight(true);
        colorStylesTable.setTableHeader(null);
        jScrollPane2.setViewportView(colorStylesTable);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 10, 10));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridLayout(0, 1));

        org.openide.awt.Mnemonics.setLocalizedText(resetCurrentColorButton, OStrings.getString("GUI_COLORS_RESET_COLOR")); // NOI18N
        resetCurrentColorButton.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(resetCurrentColorButton);

        jPanel2.add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel2, java.awt.BorderLayout.EAST);

        add(jPanel1, java.awt.BorderLayout.NORTH);
        add(colorChooser, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JColorChooser colorChooser;
    private javax.swing.JLabel colorStylesLabel;
    javax.swing.JTable colorStylesTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    javax.swing.JButton resetCurrentColorButton;
    private javax.swing.JEditorPane sampleEditorPane;
    // End of variables declaration//GEN-END:variables
}
