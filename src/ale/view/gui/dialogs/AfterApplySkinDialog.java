/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ale.controller.Main;
import ale.view.gui.GUIConstants;
import ale.view.gui.util.GUIStrings;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.dialogs <br/>
 * Class  : AfterApplySkinDialog <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>AfterApplySkinDialog</code> shows hints what to do after a skin was applied.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 15.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class AfterApplySkinDialog extends Dialog {

    private static final long serialVersionUID = 1L;
    private final JPanel basePanel = new JPanel();

    /**
     * 
     */
    public AfterApplySkinDialog() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                AfterApplySkinDialog.this.setSize(new Dimension(600, 400));
                AfterApplySkinDialog.this.setTitle(GUIConstants.PROGRAM_TITLE);
                AfterApplySkinDialog.this.setIconImage(GUIConstants.PROGRAM_ICON);
                AfterApplySkinDialog.this.setLocationRelativeTo(null);
                AfterApplySkinDialog.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                AfterApplySkinDialog.this.setModalityType(ModalityType.APPLICATION_MODAL);
                AfterApplySkinDialog.this.getContentPane().setLayout(new BorderLayout());
                AfterApplySkinDialog.this.basePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
                AfterApplySkinDialog.this.basePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                AfterApplySkinDialog.this.basePanel.setLayout(new BorderLayout());
                AfterApplySkinDialog.this.getContentPane().add(AfterApplySkinDialog.this.basePanel, BorderLayout.CENTER);

                create();

                AfterApplySkinDialog.this.setVisible(true);
            }
        });
    }

    private void create() {
        JPanel textPanel = new JPanel();
        textPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        String tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_AFTERAPPLYDIALOG_TITLE);
        textPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(tmp),
                BorderFactory.createEmptyBorder(5, 5, 0, 5)));
        textPanel.setLayout(new BorderLayout(0, 0));
        this.basePanel.add(textPanel, BorderLayout.CENTER);

        {
            JTextPane lblMessageLabel = new JTextPane();
            lblMessageLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
            lblMessageLabel.setFocusable(false);
            lblMessageLabel.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_AFTERAPPLYDIALOG_MSG));
            lblMessageLabel.setEditable(false);
            lblMessageLabel.setFont(GUIConstants.DEFAULT_MESSAGE_FONT.deriveFont(Font.BOLD, 14));
            lblMessageLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            textPanel.add(lblMessageLabel, BorderLayout.NORTH);

            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            StyledDocument doc = lblMessageLabel.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            JPanel subtextPanel = new JPanel();
            subtextPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
            subtextPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            subtextPanel.setLayout(new BorderLayout(0, 5));
            textPanel.add(subtextPanel, BorderLayout.CENTER);

            Font font = new Font("Segoe UI", Font.PLAIN, 14);
            {

                JTextArea lblHintOneLabel = new JTextArea();
                lblHintOneLabel.setFont(font);
                lblHintOneLabel.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_AFTERAPPLYDIALOG_HINT1));
                lblHintOneLabel.setFocusable(false);
                lblHintOneLabel.setEditable(false);
                lblHintOneLabel.setLineWrap(true);
                lblHintOneLabel.setWrapStyleWord(true);
                lblHintOneLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                subtextPanel.add(lblHintOneLabel, BorderLayout.NORTH);

                JTextArea lblHintTwoLabel = new JTextArea();
                lblHintTwoLabel.setFont(font);
                lblHintTwoLabel.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_AFTERAPPLYDIALOG_HINT2));
                lblHintTwoLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
                lblHintTwoLabel.setFocusable(false);
                lblHintTwoLabel.setEditable(false);
                lblHintTwoLabel.setLineWrap(true);
                lblHintTwoLabel.setWrapStyleWord(true);
                lblHintTwoLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                subtextPanel.add(lblHintTwoLabel);
            }

            JPanel backupBtnPanel = new JPanel();
            backupBtnPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            backupBtnPanel.setLayout(new BorderLayout());
            backupBtnPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));
            subtextPanel.add(backupBtnPanel, BorderLayout.SOUTH);

            JTextPane lblBackupHintLabel = new JTextPane();
            lblBackupHintLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
            lblBackupHintLabel.setFocusable(false);
            lblBackupHintLabel.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_AFTERAPPLYDIALOG_BACKUPHINT));
            lblBackupHintLabel.setEditable(false);
            lblBackupHintLabel.setFont(font.deriveFont(Font.ITALIC, 12));
            lblBackupHintLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            backupBtnPanel.add(lblBackupHintLabel, BorderLayout.NORTH);

            doc = lblBackupHintLabel.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            final JButton btnApplyBackupButton = new JButton(GUIStrings.keyToLocatedString(GUIStrings.KEY_AFTERAPPLYDIALOG_BACKUP));
            btnApplyBackupButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            btnApplyBackupButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    btnApplyBackupButton.setEnabled(false);
                    if (Main.applyBackup()) {
                        btnApplyBackupButton.setForeground(Color.GREEN.darker());
                        btnApplyBackupButton.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_AFTERAPPLYDIALOG_BACKUPAPPLIED));
                    } else {
                        btnApplyBackupButton.setForeground(Color.RED.darker());
                    }
                }
            });
            backupBtnPanel.add(btnApplyBackupButton, BorderLayout.CENTER);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        {
            JButton okButton = new JButton(GUIStrings.keyToLocatedString(GUIStrings.KEY_AFTERAPPLYDIALOG_OK));
            okButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            okButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            buttonPanel.add(okButton);
        }
    }
}
