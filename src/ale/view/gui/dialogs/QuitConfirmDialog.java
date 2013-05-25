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
import ale.model.skin.Skin;
import ale.view.gui.GUIConstants;
import ale.view.gui.editor.Editor;
import ale.view.gui.util.GUIStrings;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.dialogs <br/>
 * Class  : ContinueWOSaveDialog <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>ContinueWOSaveDialog</code> asks if the last changes should be saved or not.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 15.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class QuitConfirmDialog extends Dialog {

    private static final long serialVersionUID = 1L;
    private final JPanel basePanel = new JPanel();

    /**
     * @param skin skin, in order to save it
     * @param editor to dispose it if asked
     * @param openNewSkin if the user switches from one skin to a new one.
     * @param openChooser if the user wants to open the chooser.
     */
    public QuitConfirmDialog(final Skin skin, final Editor editor, final boolean openNewSkin, final boolean openChooser) {
        if (skin.isSkinChanged()) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    QuitConfirmDialog.this.setSize(new Dimension(475, 170));
                    QuitConfirmDialog.this.setTitle(GUIConstants.PROGRAM_TITLE);
                    QuitConfirmDialog.this.setIconImage(GUIConstants.PROGRAM_ICON);
                    QuitConfirmDialog.this.setLocationRelativeTo(null);
                    QuitConfirmDialog.this.getContentPane().setLayout(new BorderLayout());
                    QuitConfirmDialog.this.basePanel.setBorder(null);
                    QuitConfirmDialog.this.basePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                    QuitConfirmDialog.this.basePanel.setLayout(new BorderLayout());
                    QuitConfirmDialog.this.setModalityType(ModalityType.APPLICATION_MODAL);
                    QuitConfirmDialog.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    QuitConfirmDialog.this.getContentPane().add(QuitConfirmDialog.this.basePanel, BorderLayout.CENTER);

                    create(skin, editor, openNewSkin, openChooser);

                    QuitConfirmDialog.this.setVisible(true);
                }
            });

        } else {
            handleInput(editor, skin, openNewSkin, openChooser, false);
        }
    }

    private void create(final Skin skin, final Editor editor, final boolean openNewSkin, final boolean openChooser) {
        JPanel textPanel = new JPanel();
        textPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        String tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_QUITCONFIRMDIALOG_TITLE);
        textPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createTitledBorder(tmp)));
        textPanel.setLayout(new BorderLayout());
        this.basePanel.add(textPanel, BorderLayout.CENTER);

        {
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

            JTextPane lblMessageLabel = new JTextPane();
            lblMessageLabel.setFocusable(false);
            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_QUITCONFIRMDIALOG_MSG);
            lblMessageLabel.setText(tmp);
            lblMessageLabel.setEditable(false);
            lblMessageLabel.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
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
            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_QUITCONFIRMDIALOG_SAVE);
            final JButton saveButton = new JButton(tmp);
            saveButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            saveButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    saveButton.setEnabled(false);
                    handleInput(editor, skin, openNewSkin, openChooser, true);
                }
            });
            buttonPanel.add(saveButton);
            saveButton.grabFocus();

            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_QUITCONFIRMDIALOG_SKIP);
            final JButton noSaveButton = new JButton(tmp);
            noSaveButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            noSaveButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    noSaveButton.setEnabled(false);
                    handleInput(editor, skin, openNewSkin, openChooser, false);
                }
            });
            buttonPanel.add(noSaveButton);

            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_QUITCONFIRMDIALOG_CANCEL);
            JButton cancelButton = new JButton(tmp);
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

    private void handleInput(Editor editor, Skin skin, boolean openNewSkin, boolean openChooser, boolean saveSkin) {
        dispose();
        if (editor != null) {
            editor.dispose();
        }

        if (saveSkin) {
            Main.save(skin);
        }

        if (!openNewSkin) {
            if (!openChooser) {
                Main.shutdown();

            } else {
                Main.showQuickChooser();
            }

        } else {
            if (!openChooser) {
                Main.showNewSkinDialog();

            } else {
                Main.showQuickChooser();
            }
        }
    }
}
