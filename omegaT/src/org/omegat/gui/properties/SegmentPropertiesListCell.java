/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool
          with fuzzy matching, translation memory, keyword search,
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2016 Aaron Madlon-Kay
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

package org.omegat.gui.properties;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.omegat.util.gui.JTextPaneLinkifier;
import org.omegat.util.gui.StaticUIUtils;
import org.omegat.util.gui.Styles;

/**
 * A cell for the SegmentPropertiesListView
 *
 * @author Aaron Madlon-Kay
 */
@SuppressWarnings("serial")
public class SegmentPropertiesListCell extends javax.swing.JPanel {

    String key = null;

    /**
     * Creates new form SegmentPropertiesListCell
     */
    public SegmentPropertiesListCell() {
        initComponents();
        Color highlightColor = StaticUIUtils
                .getHighlightColor(Styles.EditorColor.COLOR_BACKGROUND.getColor());
        setBackground(Styles.EditorColor.COLOR_BACKGROUND.getColor());
        labelPanel.setBackground(highlightColor);
        labelPanel.setBorder(ISegmentPropertiesView.MARGIN_BORDER);
        label.setFont(UIManager.getFont("Label.font"));
        label.setForeground(Styles.EditorColor.COLOR_FOREGROUND.getColor());
        label.setBackground(highlightColor);
        value.setEditorKit(new ForcedWrappingEditorKit());
        value.setForeground(Styles.EditorColor.COLOR_FOREGROUND.getColor());
        value.setBackground(Styles.EditorColor.COLOR_BACKGROUND.getColor());
        value.setBorder(ISegmentPropertiesView.MARGIN_BORDER);
        JTextPaneLinkifier.linkify(value);
        settingsButton.setBackground(highlightColor);
        settingsButton.setIcon(ISegmentPropertiesView.SETTINGS_ICON_INVISIBLE);
        settingsButton.setRolloverIcon(ISegmentPropertiesView.SETTINGS_ICON);
        settingsButton.setPressedIcon(ISegmentPropertiesView.SETTINGS_ICON_PRESSED);
        settingsButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        MouseAdapter revealSettingsIcon = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                settingsButton.setIcon(ISegmentPropertiesView.SETTINGS_ICON_INACTIVE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingsButton.setIcon(ISegmentPropertiesView.SETTINGS_ICON_INVISIBLE);
            }
        };
        label.addMouseListener(revealSettingsIcon);
        value.addMouseListener(revealSettingsIcon);
        // Prevent list from scrolling down as new cells are added
        StaticUIUtils.setCaretUpdateEnabled(label, false);
        StaticUIUtils.setCaretUpdateEnabled(value, false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPanel = new javax.swing.JPanel();
        label = new javax.swing.JTextArea();
        settingsButton = new javax.swing.JButton();
        value = new org.omegat.gui.properties.FlashingTextArea();

        setLayout(new java.awt.BorderLayout());

        labelPanel.setLayout(new java.awt.BorderLayout());

        label.setEditable(false);
        label.setLineWrap(true);
        labelPanel.add(label, java.awt.BorderLayout.CENTER);

        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusable(false);
        settingsButton.setRolloverEnabled(true);
        labelPanel.add(settingsButton, java.awt.BorderLayout.EAST);

        add(labelPanel, java.awt.BorderLayout.NORTH);

        value.setEditable(false);
        add(value, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTextArea label;
    private javax.swing.JPanel labelPanel;
    javax.swing.JButton settingsButton;
    org.omegat.gui.properties.FlashingTextArea value;
    // End of variables declaration//GEN-END:variables
}