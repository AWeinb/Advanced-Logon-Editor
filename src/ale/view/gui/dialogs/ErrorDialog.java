/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ale.view.gui.GUIConstants;
import ale.view.gui.util.GUIStrings;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.dialogs <br/>
 * Class  : AboutDialog <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>AboutDialog</code> shows infos about the program.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 15.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class ErrorDialog extends Dialog {

    private static final long serialVersionUID = 1L;

    protected JPanel basePanel = new JPanel();

    /**
     * Creates the dialog and shows it.
     * @param informations Some infos about the error
     */
    public ErrorDialog(final String informations) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ErrorDialog.this.setSize(new Dimension(600, 250));
                ErrorDialog.this.setTitle(GUIStrings.keyToLocatedString(GUIStrings.KEY_ERRORDIALOG_TITLE));
                ErrorDialog.this.setIconImage(GUIConstants.PROGRAM_ICON);
                ErrorDialog.this.setLocationRelativeTo(null);
                ErrorDialog.this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                ErrorDialog.this.setModalityType(ModalityType.APPLICATION_MODAL);
                ErrorDialog.this.getContentPane().setLayout(new BorderLayout());
                ErrorDialog.this.basePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
                ErrorDialog.this.basePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                ErrorDialog.this.basePanel.setLayout(new BorderLayout());
                ErrorDialog.this.getContentPane().add(ErrorDialog.this.basePanel, BorderLayout.CENTER);

                create(informations);

                ErrorDialog.this.setVisible(true);
            }
        });
    }

    private void create(String informations) {
        JTextPane lblMessageLabel = new JTextPane();
        lblMessageLabel.setFocusable(false);
        lblMessageLabel.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_ERRORDIALOG_MSG));
        lblMessageLabel.setBorder(BorderFactory.createEmptyBorder(20, 5, 0, 5));
        lblMessageLabel.setEditable(false);
        lblMessageLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        lblMessageLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        this.basePanel.add(lblMessageLabel, BorderLayout.NORTH);

        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyledDocument doc = lblMessageLabel.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        JTextPane lblErrorLabel = new JTextPane();
        lblErrorLabel.setFocusable(false);
        lblErrorLabel.setText(informations);
        lblErrorLabel.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
        lblErrorLabel.setEditable(false);
        lblErrorLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        lblErrorLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        this.basePanel.add(lblErrorLabel, BorderLayout.CENTER);

        doc = lblErrorLabel.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.basePanel.add(buttonPanel, BorderLayout.SOUTH);

        {
            JButton okButton = new JButton(GUIStrings.keyToLocatedString(GUIStrings.KEY_ERRORDIALOG_OK));
            okButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            okButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    System.exit(1);
                }
            });
            buttonPanel.add(okButton);
        }
    }
}
