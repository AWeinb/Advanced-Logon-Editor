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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
 * Class  : ApplySkinDialog <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>ApplySkinDialog</code> shows a dialog to confirm if a skin should be applied.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 15.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class ApplySkinDialog extends Dialog {

    private static final long serialVersionUID = 1L;
    private final JPanel basePanel = new JPanel();

    private String filename;

    /**
     * @param filename skin filename
     */
    public ApplySkinDialog(final String filename) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ApplySkinDialog.this.setSize(new Dimension(450, 200));
                ApplySkinDialog.this.setTitle(GUIConstants.PROGRAM_TITLE);
                ApplySkinDialog.this.setIconImage(GUIConstants.PROGRAM_ICON);
                ApplySkinDialog.this.setLocationRelativeTo(null);
                ApplySkinDialog.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                ApplySkinDialog.this.setModalityType(ModalityType.APPLICATION_MODAL);
                ApplySkinDialog.this.getContentPane().setLayout(new BorderLayout());
                ApplySkinDialog.this.basePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
                ApplySkinDialog.this.basePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                ApplySkinDialog.this.basePanel.setLayout(new BorderLayout());
                ApplySkinDialog.this.getContentPane().add(ApplySkinDialog.this.basePanel, BorderLayout.CENTER);

                ApplySkinDialog.this.filename = filename;
                create();

                ApplySkinDialog.this.setVisible(true);
            }
        });
    }

    private void create() {
        JPanel textPanel = new JPanel();
        textPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        String tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_APPLYDIALOG_TITLE);
        textPanel.setBorder(BorderFactory.createTitledBorder(tmp));
        textPanel.setLayout(new BorderLayout());
        this.basePanel.add(textPanel, BorderLayout.CENTER);

        final JProgressBar bar;
        {
            JTextPane lblMessageLabel = new JTextPane();
            lblMessageLabel.setFocusable(false);
            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_APPLYDIALOG_MSG);
            lblMessageLabel.setText(tmp);
            lblMessageLabel.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
            lblMessageLabel.setEditable(false);
            lblMessageLabel.setFont(GUIConstants.DEFAULT_MESSAGE_FONT);
            lblMessageLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            textPanel.add(lblMessageLabel, BorderLayout.CENTER);

            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            StyledDocument doc = lblMessageLabel.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            JPanel progressPanel = new JPanel();
            progressPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            progressPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            textPanel.add(progressPanel, BorderLayout.SOUTH);

            bar = new JProgressBar();
            bar.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            bar.setIndeterminate(true);
            bar.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
            bar.setVisible(false);
            progressPanel.add(bar);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        {
            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_APPLYDIALOG_OK);
            final JButton okButton = new JButton(tmp);
            okButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            okButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Runnable run = new Runnable() {

                        @Override
                        public void run() {
                            okButton.setEnabled(false);
                            bar.setVisible(true);
                            Main.applySkin(ApplySkinDialog.this.filename);
                            dispose();
                        }
                    };
                    Main.executeThreads(run);
                }
            });
            buttonPanel.add(okButton);
            okButton.grabFocus();

            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_APPLYDIALOG_CANCEL);
            JButton cancelButton = new JButton(tmp);
            cancelButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            buttonPanel.add(cancelButton);
        }
    }
}
