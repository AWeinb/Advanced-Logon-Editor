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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Files;
import java.nio.file.LinkOption;

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
import ale.model.skin.Skin;
import ale.view.gui.GUIConstants;
import ale.view.gui.util.GUIStrings;
import ale.view.gui.util.VerticalLayout;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.dialogs <br/>
 * Class  : SaveAsDialog <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>SaveAsDialog</code> saves a skin with a new name.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 15.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class SaveAsDialog extends Dialog {

    private static final long serialVersionUID = 1L;
    private boolean replaceSkin;

    private final JPanel basePanel = new JPanel();
    private JTextField textFieldName;
    private JLabel lblNameLabel;
    private JCheckBox checkOpenSkin;

    private String strNameLabel = GUIStrings.keyToLocatedString(GUIStrings.KEY_SAVEASDIALOG_NAME);
    private String strNameExistsLabel = GUIStrings.keyToLocatedString(GUIStrings.KEY_SAVEASDIALOG_EXISTS);

    /**
     * @param skin skin
     */
    public SaveAsDialog(final Skin skin) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SaveAsDialog.this.setSize(new Dimension(475, 200));
                SaveAsDialog.this.setTitle(GUIConstants.PROGRAM_TITLE);
                SaveAsDialog.this.setIconImage(GUIConstants.PROGRAM_ICON);
                SaveAsDialog.this.setLocationRelativeTo(null);
                SaveAsDialog.this.getContentPane().setLayout(new BorderLayout());
                SaveAsDialog.this.basePanel.setBorder(null);
                SaveAsDialog.this.basePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                SaveAsDialog.this.basePanel.setLayout(new BorderLayout());
                SaveAsDialog.this.setModalityType(ModalityType.APPLICATION_MODAL);
                SaveAsDialog.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                SaveAsDialog.this.getContentPane().add(SaveAsDialog.this.basePanel, BorderLayout.CENTER);

                create(skin);

                SaveAsDialog.this.setVisible(true);
            }
        });
    }

    private void create(final Skin skin) {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        String tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_SAVEASDIALOG_TITLE);
        topPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5),
                BorderFactory.createTitledBorder(tmp)));
        topPanel.setLayout(new BorderLayout());
        this.basePanel.add(topPanel);

        {
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

            JTextPane mainTextpane = new JTextPane();
            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_SAVEASDIALOG_MSG);
            mainTextpane.setText(tmp);
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
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

        JPanel inputLabelPanel = new JPanel();
        inputLabelPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        inputLabelPanel.setBorder(new EmptyBorder(4, 0, 0, 0));
        inputLabelPanel.setLayout(new VerticalLayout(5, VerticalLayout.RIGHT));
        inputPanel.add(inputLabelPanel);

        {
            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_SAVEASDIALOG_NAME);
            this.lblNameLabel = new JLabel(tmp);
            this.lblNameLabel.setFont(UIManager.getFont("MenuItem.font"));
            this.lblNameLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.lblNameLabel.setBorder(new EmptyBorder(3, 5, 0, 5));
            inputLabelPanel.add(this.lblNameLabel);

            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_SAVEASDIALOG_REPLACE);
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
            this.textFieldName.setText(skin.getFilename());
            this.textFieldName.addKeyListener(new KeyAdapter() {

                @Override
                public void keyReleased(KeyEvent e) {
                    while ((SaveAsDialog.this.textFieldName.getText().length() > 0)
                            && (!SaveAsDialog.this.textFieldName.getText().matches(Constants.SKIN_NAME_REGEX) || (SaveAsDialog.this.textFieldName
                                    .getText().length() > Constants.SKIN_INPUT_MAXCHARS))) {
                        SaveAsDialog.this.textFieldName.setText(SaveAsDialog.this.textFieldName.getText().substring(0,
                                SaveAsDialog.this.textFieldName.getText().length() - 1));
                    }

                    if (!SaveAsDialog.this.replaceSkin
                            && !SaveAsDialog.this.textFieldName.getText().equals("")
                            && Files.exists(Constants.PROGRAM_SKINS_PATH.resolve((SaveAsDialog.this.textFieldName.getText()).trim()),
                                    LinkOption.NOFOLLOW_LINKS)) {
                        SaveAsDialog.this.lblNameLabel.setText(SaveAsDialog.this.strNameLabel + " " + SaveAsDialog.this.strNameExistsLabel);
                        SaveAsDialog.this.textFieldName.setBackground(Color.RED);

                    } else {
                        SaveAsDialog.this.textFieldName.setBackground(Color.WHITE);
                        SaveAsDialog.this.lblNameLabel.setText(SaveAsDialog.this.strNameLabel);
                    }
                }
            });
            inputTextfieldPanel.add(this.textFieldName);

            final JCheckBox btnReplaceSkinButton = new JCheckBox();
            btnReplaceSkinButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            btnReplaceSkinButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            btnReplaceSkinButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    SaveAsDialog.this.replaceSkin = btnReplaceSkinButton.isSelected();

                    if (!SaveAsDialog.this.replaceSkin
                            && !SaveAsDialog.this.textFieldName.getText().equals("")
                            && Files.exists(Constants.PROGRAM_SKINS_PATH.resolve(SaveAsDialog.this.textFieldName.getText()),
                                    LinkOption.NOFOLLOW_LINKS)) {
                        SaveAsDialog.this.lblNameLabel
                        .setText(SaveAsDialog.this.strNameLabel + "| " + SaveAsDialog.this.strNameExistsLabel);
                        SaveAsDialog.this.textFieldName.setBackground(Color.RED);

                    } else {
                        SaveAsDialog.this.textFieldName.setBackground(Color.WHITE);
                        SaveAsDialog.this.lblNameLabel.setText(SaveAsDialog.this.strNameLabel);
                    }
                }
            });
            inputTextfieldPanel.add(btnReplaceSkinButton);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        buttonPanel.setLayout(new BorderLayout());
        this.basePanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel navbuttons = new JPanel();
        navbuttons.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        navbuttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(navbuttons, BorderLayout.EAST);

        {
            final JButton okButton = new JButton(GUIStrings.keyToLocatedString(GUIStrings.KEY_SAVEASDIALOG_OK));
            okButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            okButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if ((SaveAsDialog.this.textFieldName.getText().length() <= 1)
                            || SaveAsDialog.this.textFieldName.getText().equals("")
                            || (!SaveAsDialog.this.replaceSkin && Files.exists(
                                    Constants.PROGRAM_SKINS_PATH.resolve(SaveAsDialog.this.textFieldName.getText()),
                                    LinkOption.NOFOLLOW_LINKS))) {
                        SaveAsDialog.this.textFieldName.setBackground(Color.RED);

                    } else {
                        okButton.setEnabled(false);
                        Main.saveAs(skin, SaveAsDialog.this.textFieldName.getText());
                        dispose();

                        if (SaveAsDialog.this.checkOpenSkin.isSelected()) {
                            Main.showEditor(SaveAsDialog.this.textFieldName.getText());
                        }
                    }
                }
            });
            navbuttons.add(okButton);

            JButton cancelButton = new JButton(GUIStrings.keyToLocatedString(GUIStrings.KEY_SAVEASDIALOG_CANCEL));
            cancelButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    dispose();
                }
            });
            navbuttons.add(cancelButton);
        }

        JPanel checkBPanel = new JPanel();
        checkBPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        buttonPanel.add(checkBPanel, BorderLayout.WEST);

        this.checkOpenSkin = new JCheckBox(GUIStrings.keyToLocatedString(GUIStrings.KEY_SAVEASDIALOG_OPENAFTERSAVE));
        this.checkOpenSkin.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        this.checkOpenSkin.setFocusable(false);
        checkBPanel.add(this.checkOpenSkin);
    }
}
