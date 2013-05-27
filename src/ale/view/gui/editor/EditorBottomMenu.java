/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ale.controller.Settings;
import ale.view.gui.util.GUIStrings;

final class EditorBottomMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private Editor editor;

    public EditorBottomMenu(Editor editor, Color background) {
        this.editor = editor;

        setBackground(background);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        create(background);
    }

    private void create(Color background) {
        final JPanel leftPanel = new JPanel();
        leftPanel.setBackground(background);
        this.add(leftPanel, BorderLayout.WEST);

        String tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_SWITCHPREVIEWS);
        JLabel description = new JLabel(tmp);
        description.setBackground(background);
        leftPanel.add(description);

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_TOGGLEUSERLIST);
        JRadioButton showUserlistPreview_btn = new JRadioButton(tmp);
        showUserlistPreview_btn.setFocusable(false);
        showUserlistPreview_btn.setSelected(true);
        showUserlistPreview_btn.setBackground(background);
        leftPanel.add(showUserlistPreview_btn);

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_TOGGLEUSERTILE);
        JRadioButton showUsertilePreview_btn = new JRadioButton(tmp);
        showUsertilePreview_btn.setFocusable(false);
        showUsertilePreview_btn.setBackground(background);
        leftPanel.add(showUsertilePreview_btn);

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_TOGGLESECURITYMENU);
        JRadioButton showSecMenuPreview_btn = new JRadioButton(tmp);
        showSecMenuPreview_btn.setFocusable(false);
        showSecMenuPreview_btn.setBackground(background);
        leftPanel.add(showSecMenuPreview_btn);

        ButtonGroup group = new ButtonGroup();
        group.add(showUserlistPreview_btn);
        group.add(showUsertilePreview_btn);
        group.add(showSecMenuPreview_btn);

        showUserlistPreview_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EditorBottomMenu.this.editor.showUserlistPreview();
            }
        });
        showUsertilePreview_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EditorBottomMenu.this.editor.showUsertilePreview();
            }
        });
        showSecMenuPreview_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EditorBottomMenu.this.editor.showSecurityMenuPreview();
            }
        });

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(background);
        this.add(rightPanel, BorderLayout.EAST);

        final JCheckBox scaleBgCheckbox = new JCheckBox(GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_SCALEBACKGROUND));
        scaleBgCheckbox.setSelected(Settings.getEditorBgScaled());
        scaleBgCheckbox.setFocusable(false);
        scaleBgCheckbox.setBackground(background);
        scaleBgCheckbox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EditorBottomMenu.this.editor.setPreviewBackgroundScaling(scaleBgCheckbox.isSelected());
            }
        });
        rightPanel.add(scaleBgCheckbox);
    }
}
