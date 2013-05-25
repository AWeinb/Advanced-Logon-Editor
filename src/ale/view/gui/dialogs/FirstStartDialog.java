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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
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
 * Class  : FirstStartDialog <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>FirstStartDialog</code> shows before the first start some basic infos about warranty and co.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 15.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class FirstStartDialog extends Dialog implements WindowListener {

    private static final long serialVersionUID = 1L;
    private final JPanel basePanel = new JPanel();

    /**
     * 
     */
    public FirstStartDialog() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FirstStartDialog.this.setSize(new Dimension(600, 380));
                FirstStartDialog.this.setTitle(GUIConstants.PROGRAM_TITLE);
                FirstStartDialog.this.setIconImage(GUIConstants.PROGRAM_ICON);
                FirstStartDialog.this.setLocationRelativeTo(null);
                FirstStartDialog.this.getContentPane().setLayout(new BorderLayout());
                FirstStartDialog.this.basePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
                FirstStartDialog.this.basePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                FirstStartDialog.this.basePanel.setLayout(new BorderLayout());
                FirstStartDialog.this.getContentPane().add(FirstStartDialog.this.basePanel, BorderLayout.CENTER);
                FirstStartDialog.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                FirstStartDialog.this.setModalityType(ModalityType.APPLICATION_MODAL);
                FirstStartDialog.this.addWindowListener(FirstStartDialog.this);

                create();

                FirstStartDialog.this.setVisible(true);
            }
        });
    }

    private void create() {
        JPanel textPanel = new JPanel();
        textPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        String tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_FIRSTSTARTDIALOG_TITLE);
        textPanel.setBorder(BorderFactory.createTitledBorder(tmp));
        this.basePanel.add(textPanel, BorderLayout.CENTER);

        {
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            textPanel.setLayout(new BorderLayout(0, 0));

            JTextPane lblMessageLabel = new JTextPane();
            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_FIRSTSTARTDIALOG_MSGHEAD);
            lblMessageLabel.setText(tmp);
            lblMessageLabel.setEditable(false);
            lblMessageLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            lblMessageLabel.setFont(GUIConstants.DEFAULT_HEADING_FONT);
            lblMessageLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            textPanel.add(lblMessageLabel, BorderLayout.NORTH);

            StyledDocument doc = lblMessageLabel.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            JPanel subtextPanel = new JPanel();
            subtextPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
            subtextPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            textPanel.add(subtextPanel);
            subtextPanel.setLayout(new BorderLayout(0, 0));

            {
                JTextPane lblHintLabel = new JTextPane();
                lblHintLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                lblHintLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
                tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_FIRSTSTARTDIALOG_MSG);
                lblHintLabel.setText(tmp);
                lblHintLabel.setEditable(false);
                lblHintLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                subtextPanel.add(lblHintLabel);

                doc = lblHintLabel.getStyledDocument();
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
            }
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        {
            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_FIRSTSTARTDIALOG_OK);
            final JButton okButton = new JButton(tmp);
            okButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            okButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    okButton.setEnabled(false);
                    Main.resume();
                    dispose();
                }
            });
            buttonPanel.add(okButton);
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {
        ;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        ;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Main.shutdown();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        ;
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        ;
    }

    @Override
    public void windowIconified(WindowEvent e) {
        ;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        ;
    }
}
