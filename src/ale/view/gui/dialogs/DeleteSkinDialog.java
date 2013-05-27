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
 * Class  : DeleteSkinDialog <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>DeleteSkinDialog</code> controls if a skin really should be deleted.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 15.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class DeleteSkinDialog extends Dialog {

    private static final long serialVersionUID = 1L;
    private final JPanel basePanel = new JPanel();
    private String file;

    /**
     * @param filename the filename of the skin.
     */
    public DeleteSkinDialog(String filename) {
        this.file = filename;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DeleteSkinDialog.this.setSize(new Dimension(450, 140));
                DeleteSkinDialog.this.setTitle(GUIConstants.PROGRAM_TITLE);
                DeleteSkinDialog.this.setIconImage(GUIConstants.PROGRAM_ICON);
                DeleteSkinDialog.this.setLocationRelativeTo(null);
                DeleteSkinDialog.this.getContentPane().setLayout(new BorderLayout());
                DeleteSkinDialog.this.basePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
                DeleteSkinDialog.this.basePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                DeleteSkinDialog.this.basePanel.setLayout(new BorderLayout());
                DeleteSkinDialog.this.setModalityType(ModalityType.APPLICATION_MODAL);
                DeleteSkinDialog.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                DeleteSkinDialog.this.getContentPane().add(DeleteSkinDialog.this.basePanel, BorderLayout.CENTER);

                create();

                DeleteSkinDialog.this.setVisible(true);
            }
        });
    }

    private void create() {
        JPanel textPanel = new JPanel();
        textPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        textPanel.setBorder(BorderFactory.createTitledBorder(GUIStrings.keyToLocatedString(GUIStrings.KEY_DELETEDIALOG_TITLE)));
        textPanel.setLayout(new BorderLayout());
        this.basePanel.add(textPanel, BorderLayout.CENTER);

        {
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

            JTextPane lblMessageLabel = new JTextPane();
            lblMessageLabel.setFocusable(false);
            lblMessageLabel.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_DELETEDIALOG_MSG));
            lblMessageLabel.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
            lblMessageLabel.setEditable(false);
            lblMessageLabel.setFont(GUIConstants.DEFAULT_MESSAGE_FONT);
            lblMessageLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            textPanel.add(lblMessageLabel);

            StyledDocument doc = lblMessageLabel.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        {
            final JButton okButton = new JButton(GUIStrings.keyToLocatedString(GUIStrings.KEY_DELETEDIALOG_OK));
            okButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            okButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    okButton.setEnabled(false);
                    dispose();
                    Main.deleteSkin(DeleteSkinDialog.this.file);
                }
            });
            buttonPanel.add(okButton);
            okButton.grabFocus();

            JButton cancelButton = new JButton(GUIStrings.keyToLocatedString(GUIStrings.KEY_DELETEDIALOG_CANCEL));
            cancelButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    dispose();
                }
            });
            buttonPanel.add(cancelButton);
        }
    }
}
