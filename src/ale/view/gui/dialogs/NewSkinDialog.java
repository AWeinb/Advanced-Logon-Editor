/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ale.Constants;
import ale.controller.Main;
import ale.view.gui.GUIConstants;
import ale.view.gui.util.GUIStrings;
import ale.view.gui.util.ImageFileFilter;
import ale.view.gui.util.VerticalLayout;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.dialogs <br/>
 * Class  : NewSkinDialog <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>NewSkinDialog</code> shows some textfields to enter name, author ... and create a new skin.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 15.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class NewSkinDialog extends Dialog {

    private static final long serialVersionUID = 1L;

    private final JPanel basePanel = new JPanel();
    private JTextField textFieldName;
    private JTextField textFieldAuthor;
    private JTextField textFieldWebsite;

    private JTextField textFieldImage;
    private JLabel lblNameLabel;
    private Path newSkinImageTmp;
    private boolean replaceSkin;

    /**
     * 
     */
    public NewSkinDialog() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setSize(new Dimension(475, 300));
                setTitle(GUIConstants.PROGRAM_TITLE);
                setIconImage(GUIConstants.PROGRAM_ICON);
                setLocationRelativeTo(null);
                getContentPane().setLayout(new BorderLayout());
                NewSkinDialog.this.basePanel.setBorder(null);
                NewSkinDialog.this.basePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                NewSkinDialog.this.basePanel.setLayout(new BorderLayout());
                setModalityType(ModalityType.APPLICATION_MODAL);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                getContentPane().add(NewSkinDialog.this.basePanel, BorderLayout.CENTER);

                create();

                setVisible(true);
            }
        });
    }

    private void create() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        String tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_TITLE);
        topPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5),
                BorderFactory.createTitledBorder(tmp)));
        topPanel.setLayout(new BorderLayout());
        this.basePanel.add(topPanel);

        {
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

            JTextPane mainTextpane = new JTextPane();
            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_MSG);
            mainTextpane.setText(tmp);
            mainTextpane.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
            mainTextpane.setEditable(false);
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
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

        JPanel inputLabelPanel = new JPanel();
        inputLabelPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        inputLabelPanel.setBorder(new EmptyBorder(4, 0, 0, 0));
        inputLabelPanel.setLayout(new VerticalLayout(5, VerticalLayout.RIGHT));
        inputPanel.add(inputLabelPanel);

        {
            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_NAME);
            this.lblNameLabel = new JLabel(tmp);
            this.lblNameLabel.setFont(UIManager.getFont("MenuItem.font"));
            this.lblNameLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.lblNameLabel.setBorder(new EmptyBorder(3, 5, 0, 5));
            inputLabelPanel.add(this.lblNameLabel);

            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_AUTHOR);
            JLabel lblAuthorLabel = new JLabel(tmp);
            lblAuthorLabel.setFont(UIManager.getFont("MenuItem.font"));
            lblAuthorLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            lblAuthorLabel.setBorder(new EmptyBorder(3, 5, 0, 5));
            inputLabelPanel.add(lblAuthorLabel);

            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_WEB);
            JLabel lblWebsiteLabel = new JLabel(tmp);
            lblWebsiteLabel.setFont(UIManager.getFont("MenuItem.font"));
            lblWebsiteLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            lblWebsiteLabel.setBorder(new EmptyBorder(3, 5, 0, 5));
            inputLabelPanel.add(lblWebsiteLabel);

            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_IMAGE);
            JLabel lblImageLabel = new JLabel(tmp);
            lblImageLabel.setFont(UIManager.getFont("MenuItem.font"));
            lblImageLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            lblImageLabel.setBorder(new EmptyBorder(4, 5, 0, 5));
            inputLabelPanel.add(lblImageLabel);

            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_REPLACE);
            JLabel lblReplaceLabel = new JLabel(tmp);
            lblReplaceLabel.setFont(UIManager.getFont("MenuItem.font"));
            lblReplaceLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            lblReplaceLabel.setBorder(new EmptyBorder(9, 5, 0, 5));
            inputLabelPanel.add(lblReplaceLabel);
        }

        JPanel inputTextfieldPanel = new JPanel();
        inputTextfieldPanel.setBorder(new EmptyBorder(5, 5, 0, 0));
        inputTextfieldPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        inputPanel.add(inputTextfieldPanel);
        inputTextfieldPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        {
            this.textFieldName = new JTextField();
            this.textFieldName.setColumns(25);
            this.textFieldName.addKeyListener(new KeyAdapter() {

                @Override
                public void keyReleased(KeyEvent e) {
                    while ((NewSkinDialog.this.textFieldName.getText().length() > 0)
                            && (!NewSkinDialog.this.textFieldName.getText().matches(Constants.SKIN_NAME_REGEX) || (NewSkinDialog.this.textFieldName
                                    .getText().length() > Constants.SKIN_INPUT_MAXCHARS))) {
                        NewSkinDialog.this.textFieldName.setText(NewSkinDialog.this.textFieldName.getText().substring(0,
                                NewSkinDialog.this.textFieldName.getText().length() - 1));
                    }

                    if (!NewSkinDialog.this.replaceSkin
                            && !NewSkinDialog.this.textFieldName.getText().equals("")
                            && Files.exists(
                                    Constants.PROGRAM_SKINS_PATH.resolve(NewSkinDialog.this.textFieldName.getText()
                                            + Constants.SKINFILE_SUFFIX),
                                    LinkOption.NOFOLLOW_LINKS)) {
                        String name = GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_NAME) + " ";
                        name += GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_EXISTS);
                        NewSkinDialog.this.lblNameLabel.setText(name);
                        NewSkinDialog.this.textFieldName.setBackground(GUIConstants.WARNING_BG);

                    } else {
                        NewSkinDialog.this.textFieldName.setBackground(Color.WHITE);
                        NewSkinDialog.this.lblNameLabel.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_NAME));
                    }
                }
            });
            inputTextfieldPanel.add(this.textFieldName);

            this.textFieldAuthor = new JTextField();
            this.textFieldAuthor.setColumns(25);
            this.textFieldAuthor.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    while (NewSkinDialog.this.textFieldAuthor.getText().length() > Constants.SKIN_INPUT_MAXCHARS) {
                        NewSkinDialog.this.textFieldAuthor.setText(NewSkinDialog.this.textFieldAuthor.getText().substring(0,
                                NewSkinDialog.this.textFieldAuthor.getText().length() - 1));
                    }
                }
            });
            inputTextfieldPanel.add(this.textFieldAuthor);

            this.textFieldWebsite = new JTextField();
            this.textFieldWebsite.setColumns(25);
            this.textFieldWebsite.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    while (NewSkinDialog.this.textFieldWebsite.getText().length() > Constants.SKIN_INPUT_MAXCHARS) {
                        NewSkinDialog.this.textFieldWebsite.setText(NewSkinDialog.this.textFieldWebsite.getText().substring(0,
                                NewSkinDialog.this.textFieldWebsite.getText().length() - 1));
                    }
                }
            });
            inputTextfieldPanel.add(this.textFieldWebsite);

            this.textFieldImage = new JTextField();
            this.textFieldImage.setEditable(false);
            this.textFieldImage.setColumns(25);
            this.textFieldImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.textFieldImage.addMouseListener(new MouseAdapter() {

                @SuppressWarnings("unused")
                @Override
                public void mouseReleased(MouseEvent e) {
                    String title = GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_FCTITLE);
                    new FileChooserDialog(title, new ImageFileFilter(Constants.DEFAULT_INPUTIMAGE_TYPE), false) {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void onApprove(File file) {
                            NewSkinDialog.this.textFieldImage.setText(file.toString());
                            NewSkinDialog.this.newSkinImageTmp = file.toPath();
                        }

                        @Override
                        public void onCancel() {
                            ;
                        }
                    };
                }
            });
            inputTextfieldPanel.add(this.textFieldImage);

            final JCheckBox btnReplaceSkinButton = new JCheckBox();
            btnReplaceSkinButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            btnReplaceSkinButton.setBorder(new EmptyBorder(7, 0, 0, 0));
            btnReplaceSkinButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    NewSkinDialog.this.replaceSkin = btnReplaceSkinButton.isSelected();

                    if (!NewSkinDialog.this.replaceSkin
                            && !NewSkinDialog.this.textFieldName.getText().equals("")
                            && Files.exists(Constants.PROGRAM_SKINS_PATH.resolve(NewSkinDialog.this.textFieldName.getText()),
                                    LinkOption.NOFOLLOW_LINKS)) {
                        String name = GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_NAME) + " ";
                        name += GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_EXISTS);
                        NewSkinDialog.this.lblNameLabel.setText(name);
                        NewSkinDialog.this.textFieldName.setBackground(Color.RED);

                    } else {
                        NewSkinDialog.this.textFieldName.setBackground(Color.WHITE);
                        NewSkinDialog.this.lblNameLabel.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_NAME));
                    }
                }
            });
            inputTextfieldPanel.add(btnReplaceSkinButton);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        {
            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_OK);
            final JButton okButton = new JButton(tmp);
            okButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            okButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if ((NewSkinDialog.this.textFieldName.getText().length() <= 1)
                            || NewSkinDialog.this.textFieldName.getText().equals("")
                            || (!NewSkinDialog.this.replaceSkin && Files.exists(
                                    Constants.PROGRAM_SKINS_PATH.resolve(NewSkinDialog.this.textFieldName.getText()),
                                    LinkOption.NOFOLLOW_LINKS))) {
                        NewSkinDialog.this.textFieldName.setBackground(Color.RED);

                    } else {
                        okButton.setEnabled(false);
                        Main.newSkin(NewSkinDialog.this.textFieldName.getText(), NewSkinDialog.this.textFieldAuthor.getText(),
                                NewSkinDialog.this.textFieldWebsite.getText(),
                                NewSkinDialog.this.newSkinImageTmp == null ? null : NewSkinDialog.this.newSkinImageTmp.toString());
                        dispose();
                    }
                }
            });
            buttonPanel.add(okButton);

            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_CANCEL);
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
}
