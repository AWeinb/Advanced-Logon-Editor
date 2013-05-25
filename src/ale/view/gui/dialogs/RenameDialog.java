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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Files;
import java.nio.file.LinkOption;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ale.Constants;
import ale.controller.Main;
import ale.view.gui.GUIConstants;
import ale.view.gui.util.GUIStrings;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.dialogs <br/>
 * Class  : RenameDialog <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>RenameDialog</code> lets you enter a new name for a skin.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 15.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class RenameDialog extends Dialog {

    private static final long serialVersionUID = 1L;
    private final JPanel basePanel = new JPanel();
    private JTextField textFieldName;

    /**
     * @param filename skinfile
     */
    public RenameDialog(final String filename) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                RenameDialog.this.setSize(new Dimension(475, 200));
                RenameDialog.this.setTitle(GUIConstants.PROGRAM_TITLE);
                RenameDialog.this.setIconImage(GUIConstants.PROGRAM_ICON);
                RenameDialog.this.setLocationRelativeTo(null);
                RenameDialog.this.getContentPane().setLayout(new BorderLayout());
                RenameDialog.this.basePanel.setBorder(null);
                RenameDialog.this.basePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                RenameDialog.this.basePanel.setLayout(new BorderLayout());
                RenameDialog.this.setModalityType(ModalityType.APPLICATION_MODAL);
                RenameDialog.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                RenameDialog.this.getContentPane().add(RenameDialog.this.basePanel, BorderLayout.CENTER);

                create(filename);

                RenameDialog.this.setVisible(true);
            }
        });
    }

    private void create(final String skinname) {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        String tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_RENAMEDIALOG_TITLE);
        topPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5),
                BorderFactory.createTitledBorder(tmp)));
        topPanel.setLayout(new BorderLayout());
        this.basePanel.add(topPanel);

        {
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

            JTextPane mainTextpane = new JTextPane();
            mainTextpane.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_RENAMEDIALOG_MSG));
            mainTextpane.setEditable(false);
            mainTextpane.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
            mainTextpane.setFont(GUIConstants.DEFAULT_MESSAGE_FONT);
            mainTextpane.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            topPanel.add(mainTextpane, BorderLayout.NORTH);

            StyledDocument doc = mainTextpane.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        }

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        inputPanel.setBorder(new EmptyBorder(5, 0, 0, 0));
        topPanel.add(inputPanel, BorderLayout.CENTER);

        this.textFieldName = new JTextField();
        this.textFieldName.setColumns(25);
        this.textFieldName.setText(skinname);
        this.textFieldName.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                while ((RenameDialog.this.textFieldName.getText().length() > 0)
                        && (!RenameDialog.this.textFieldName.getText().matches(Constants.SKIN_NAME_REGEX) || (RenameDialog.this.textFieldName
                                .getText().length() > Constants.SKIN_INPUT_MAXCHARS))) {
                    RenameDialog.this.textFieldName.setText(RenameDialog.this.textFieldName.getText().substring(0,
                            RenameDialog.this.textFieldName.getText().length() - 1));
                }

                if (!RenameDialog.this.textFieldName.getText().equals("")
                        && Files.exists(Constants.PROGRAM_SKINS_PATH.resolve((RenameDialog.this.textFieldName.getText()).trim()),
                                LinkOption.NOFOLLOW_LINKS)) {
                    RenameDialog.this.textFieldName.setBackground(GUIConstants.WARNING_BG);

                } else {
                    RenameDialog.this.textFieldName.setBackground(Color.WHITE);
                }
            }
        });
        inputPanel.add(this.textFieldName);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        buttonPanel.setLayout(new BorderLayout());
        this.basePanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel navbuttons = new JPanel();
        navbuttons.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        navbuttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(navbuttons, BorderLayout.EAST);

        {
            final JButton okButton = new JButton(GUIStrings.keyToLocatedString(GUIStrings.KEY_RENAMEDIALOG_OK));
            okButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            okButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    okButton.setEnabled(false);
                    Main.renameSkin(skinname, RenameDialog.this.textFieldName.getText());
                    dispose();
                }
            });
            navbuttons.add(okButton);

            JButton cancelButton = new JButton(GUIStrings.keyToLocatedString(GUIStrings.KEY_RENAMEDIALOG_CANCEL));
            cancelButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    dispose();
                }
            });
            navbuttons.add(cancelButton);
        }
    }
}
