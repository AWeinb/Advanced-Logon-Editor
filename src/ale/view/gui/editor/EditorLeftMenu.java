/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import ale.Constants;
import ale.controller.Main;
import ale.model.skin.Skin;
import ale.view.gui.GUIConstants;
import ale.view.gui.GUIConstants.RightMenu;
import ale.view.gui.dialogs.FileChooserDialog;
import ale.view.gui.util.GUIStrings;
import ale.view.gui.util.ImageFileFilter;
import ale.view.gui.util.VerticalLayout;

final class EditorLeftMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private String filechooserTitle;
    private JButton[] buttons = new JButton[RightMenu.values().length];

    private Skin skin;
    private Editor editor;
    private JButton leftHideBtn;

    public EditorLeftMenu(Skin skin, Editor editor, Color background) {
        this.skin = skin;
        this.editor = editor;

        setBackground(background);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        create(background);
    }

    protected void setFocusToButton(int i) {
        if (i > this.buttons.length) {
            throw new IllegalArgumentException();
        }

        this.buttons[i].grabFocus();
    }

    private void create(Color background) {
        final JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));
        top.setBackground(background);
        this.add(top, BorderLayout.NORTH);

        JLabel info = new JLabel();
        info.setFont(GUIConstants.DEFAULT_HEADING_FONT);
        info.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_LEFTINFO));
        top.add(info);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(background);
        buttonPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 10));
        this.add(buttonPanel, BorderLayout.CENTER);

        addButtons(buttonPanel);

        JPanel baseSkinDataPanel = new JPanel();
        baseSkinDataPanel.setBackground(background);
        baseSkinDataPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0),
                BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY)));
        baseSkinDataPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));
        buttonPanel.add(baseSkinDataPanel);

        {
            JLabel lblAuthorLabel = new JLabel(GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_AUTHOR));
            lblAuthorLabel.setFont(UIManager.getFont("MenuItem.font"));
            lblAuthorLabel.setBackground(background);
            lblAuthorLabel.setBorder(new EmptyBorder(15, 10, 0, 5));
            baseSkinDataPanel.add(lblAuthorLabel);

            final JTextField textFieldAuthor = new JTextField();
            textFieldAuthor.setColumns(25);
            textFieldAuthor.setText(this.editor.getSkinAuthor());
            textFieldAuthor.setCaretPosition(0);
            textFieldAuthor.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    while (textFieldAuthor.getText().length() > 50) {
                        textFieldAuthor.setText(textFieldAuthor.getText().substring(0, textFieldAuthor.getText().length() - 1));
                    }

                    EditorLeftMenu.this.editor.setSkinAuthor(textFieldAuthor.getText());
                }
            });
            baseSkinDataPanel.add(textFieldAuthor);

            JLabel lblWebsiteLabel = new JLabel(GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_WEB));
            lblWebsiteLabel.setFont(UIManager.getFont("MenuItem.font"));
            lblWebsiteLabel.setBackground(background);
            lblWebsiteLabel.setBorder(new EmptyBorder(8, 10, 0, 5));
            baseSkinDataPanel.add(lblWebsiteLabel);

            final JTextField textFieldWebsite = new JTextField();
            textFieldWebsite.setColumns(25);
            textFieldWebsite.setText(this.editor.getSkinWebsite());
            textFieldWebsite.setToolTipText(this.editor.getSkinWebsite());
            textFieldWebsite.setCaretPosition(0);
            textFieldWebsite.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    while (textFieldWebsite.getText().length() > 50) {
                        textFieldWebsite.setText(textFieldWebsite.getText().substring(0, textFieldWebsite.getText().length() - 1));
                    }

                    EditorLeftMenu.this.editor.setSkinWebsite(textFieldWebsite.getText());
                    textFieldWebsite.setToolTipText(textFieldWebsite.getText());
                }
            });
            baseSkinDataPanel.add(textFieldWebsite);

            JLabel lblImageLabel = new JLabel(GUIStrings.keyToLocatedString(GUIStrings.KEY_NEWSKINDIALOG_IMAGE));
            lblImageLabel.setFont(UIManager.getFont("MenuItem.font"));
            lblImageLabel.setBackground(background);
            lblImageLabel.setBorder(new EmptyBorder(8, 10, 0, 5));
            baseSkinDataPanel.add(lblImageLabel);

            final JTextField textFieldImage = new JTextField();
            textFieldImage.setEditable(false);
            textFieldImage.setColumns(25);
            textFieldImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            textFieldImage.setText(this.editor.getPreviewimage() != null ? this.editor.getPreviewimage().toString() : "");
            textFieldImage.setToolTipText(textFieldImage.getText());
            textFieldImage.setCaretPosition(0);
            textFieldImage.addMouseListener(new MouseAdapter() {

                @SuppressWarnings("unused")
                @Override
                public void mouseReleased(MouseEvent e) {
                    new FileChooserDialog(EditorLeftMenu.this.filechooserTitle, new ImageFileFilter(Constants.DEFAULT_INPUTIMAGE_TYPE),
                            false) {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void onApprove(File file) {
                            textFieldImage.setText(file.toString());
                            textFieldImage.setToolTipText(textFieldImage.getText());
                            EditorLeftMenu.this.editor.setPreviewimage(file.toPath());
                        }

                        @Override
                        public void onCancel() {
                            ;
                        }

                    };
                }
            });
            baseSkinDataPanel.add(textFieldImage);
        }

        final JPanel btm = new JPanel();
        btm.setLayout(new BorderLayout());
        btm.setBackground(background);
        this.add(btm, BorderLayout.SOUTH);

        final JPanel applyBtnPanel = new JPanel();
        applyBtnPanel.setBackground(background);
        applyBtnPanel.setLayout(new VerticalLayout(5, VerticalLayout.CENTER));
        applyBtnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        btm.add(applyBtnPanel, BorderLayout.NORTH);

        JButton applyBtn = new JButton(GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_MENUBAR_APPLY));
        applyBtn.setBackground(background);
        applyBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.save(EditorLeftMenu.this.skin);
                Main.showApplySkinDialog(EditorLeftMenu.this.skin.getFilename());
            }
        });
        applyBtnPanel.add(applyBtn);

        JButton backupBtn = new JButton(GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_BACKUP));
        backupBtn.setBackground(background);
        backupBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.applyBackup();
            }
        });
        applyBtnPanel.add(backupBtn);

        this.leftHideBtn = new JButton();
        this.leftHideBtn.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_SHOWHIDEMENU));
        this.leftHideBtn.setOpaque(false);
        this.leftHideBtn.addActionListener(new ActionListener() {
            private boolean hidden;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!this.hidden) {
                    this.hidden = true;
                    top.setVisible(false);
                    buttonPanel.setVisible(false);
                    btm.remove(EditorLeftMenu.this.leftHideBtn);
                    btm.add(EditorLeftMenu.this.leftHideBtn, BorderLayout.WEST);
                    btm.setOpaque(false);
                    applyBtnPanel.setVisible(false);
                    EditorLeftMenu.this.leftHideBtn.grabFocus();

                } else {
                    this.hidden = false;
                    top.setVisible(true);
                    buttonPanel.setVisible(true);
                    btm.remove(EditorLeftMenu.this.leftHideBtn);
                    btm.add(EditorLeftMenu.this.leftHideBtn, BorderLayout.EAST);
                    btm.setOpaque(true);
                    applyBtnPanel.setVisible(true);
                    EditorLeftMenu.this.leftHideBtn.grabFocus();
                }
            }
        });
        this.leftHideBtn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        EditorLeftMenu.this.leftHideBtn.doClick();
                        EditorLeftMenu.this.leftHideBtn.grabFocus();
                        break;
                    case KeyEvent.VK_RIGHT:
                        EditorLeftMenu.this.leftHideBtn.doClick();
                        EditorLeftMenu.this.buttons[0].grabFocus();
                        break;

                    default:
                        break;
                }
            }
        });

        btm.add(this.leftHideBtn, BorderLayout.EAST);
    }

    private void addButtons(JPanel buttonPanel) {
        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i] = new JButton();
            this.buttons[i].setFont(GUIConstants.DEFAULT_BUTTON_FONT);
            this.buttons[i].setPreferredSize(GUIConstants.DEFAULT_BUTTON_DIM);
            this.buttons[i].setOpaque(false);
        }

        this.buttons[0].setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_GENCHANGESBTN));
        this.buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditorLeftMenu.this.editor.showMenu(RightMenu.GEN_CHANGES_MENU);
                EditorLeftMenu.this.editor.setFocusToRightMenu();
            }
        });
        buttonPanel.add(this.buttons[0]);

        this.buttons[1].setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_BTNCHANGESBTN));
        this.buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditorLeftMenu.this.editor.showMenu(RightMenu.BTN_CHANGES_MENU);
                EditorLeftMenu.this.editor.setFocusToRightMenu();
            }
        });
        buttonPanel.add(this.buttons[1]);

        this.buttons[2].setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERLISTCHANGESBTN));
        this.buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditorLeftMenu.this.editor.showMenu(RightMenu.USERLIST_CHANGES_MENU);
                EditorLeftMenu.this.editor.setFocusToRightMenu();
            }
        });
        buttonPanel.add(this.buttons[2]);

        this.buttons[3].setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERTILECHANGESBTN));
        this.buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditorLeftMenu.this.editor.showMenu(RightMenu.USERTILE_CHANGES_MENU);
                EditorLeftMenu.this.editor.setFocusToRightMenu();
            }
        });
        buttonPanel.add(this.buttons[3]);

        this.buttons[4].setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESBTN));
        this.buttons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditorLeftMenu.this.editor.showMenu(RightMenu.FONT_MENU);
                EditorLeftMenu.this.editor.setFocusToRightMenu();
            }
        });
        buttonPanel.add(this.buttons[4]);

        {
            /*
             * Focus management
             */
            this.buttons[0].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            EditorLeftMenu.this.buttons[3].grabFocus();
                            break;
                        case KeyEvent.VK_DOWN:
                            EditorLeftMenu.this.buttons[1].grabFocus();
                            break;
                        case KeyEvent.VK_LEFT:
                            EditorLeftMenu.this.leftHideBtn.doClick();
                            EditorLeftMenu.this.leftHideBtn.grabFocus();
                            break;
                        default:
                            break;
                    }
                }
            });
            this.buttons[1].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            EditorLeftMenu.this.buttons[0].grabFocus();
                            break;
                        case KeyEvent.VK_DOWN:
                            EditorLeftMenu.this.buttons[2].grabFocus();
                            break;
                        case KeyEvent.VK_LEFT:
                            EditorLeftMenu.this.leftHideBtn.doClick();
                            EditorLeftMenu.this.leftHideBtn.grabFocus();
                            break;
                        default:
                            break;
                    }
                }
            });
            this.buttons[2].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            EditorLeftMenu.this.buttons[1].grabFocus();
                            break;
                        case KeyEvent.VK_DOWN:
                            EditorLeftMenu.this.buttons[3].grabFocus();
                            break;
                        case KeyEvent.VK_LEFT:
                            EditorLeftMenu.this.leftHideBtn.doClick();
                            EditorLeftMenu.this.leftHideBtn.grabFocus();
                            break;
                        default:
                            break;
                    }
                }
            });
            this.buttons[3].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            EditorLeftMenu.this.buttons[2].grabFocus();
                            break;
                        case KeyEvent.VK_DOWN:
                            EditorLeftMenu.this.buttons[4].grabFocus();
                            break;
                        case KeyEvent.VK_LEFT:
                            EditorLeftMenu.this.leftHideBtn.doClick();
                            EditorLeftMenu.this.leftHideBtn.grabFocus();
                            break;
                        default:
                            break;
                    }
                }
            });
            this.buttons[4].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            EditorLeftMenu.this.buttons[3].grabFocus();
                            break;
                        case KeyEvent.VK_DOWN:
                            EditorLeftMenu.this.buttons[0].grabFocus();
                            break;
                        case KeyEvent.VK_LEFT:
                            EditorLeftMenu.this.leftHideBtn.doClick();
                            EditorLeftMenu.this.leftHideBtn.grabFocus();
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }
}
