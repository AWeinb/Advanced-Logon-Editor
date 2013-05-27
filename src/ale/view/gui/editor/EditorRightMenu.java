/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ale.view.gui.GUIConstants;
import ale.view.gui.GUIConstants.RightMenu;
import ale.view.gui.editor.fields.MenuHint;
import ale.view.gui.editor.menus.ButtonChangesMenu;
import ale.view.gui.editor.menus.FontChangesMenu;
import ale.view.gui.editor.menus.GeneralChangesMenu;
import ale.view.gui.editor.menus.UserlistChangesMenu;
import ale.view.gui.editor.menus.UsertileChangesMenu;
import ale.view.gui.util.GUIStrings;
import ale.view.gui.util.VerticalLayout;

final class EditorRightMenu extends JPanel {

    private static final long serialVersionUID = 1L;

    private Editor editor;
    private boolean rightPanelHidden = true;
    private Color bg;
    private JButton rightHideBtn;
    private JComboBox<String> rightPanelComboBox;
    private JPanel rightPanelSubBtm;
    private JLabel rightPanelInfo;

    private String comboboxHintAddition;
    private String strComboboxHint = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_COMBOBOXHINT);

    public EditorRightMenu(Editor editor, Color background) {
        this.editor = editor;
        this.bg = background;

        setBackground(background);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        create();
    }

    protected void setInfotext(String text) {
        if (text != null) {
            this.rightPanelInfo.setText(text);
        }
    }

    protected void clearContent() {
        this.rightPanelComboBox.removeAllItems();
        this.rightPanelSubBtm.removeAll();
    }

    protected boolean isHidden() {
        return this.rightPanelHidden;
    }

    protected void clickHideButton() {
        this.rightHideBtn.doClick();
    }

    protected void setFocusToCombobox() {
        this.rightPanelComboBox.grabFocus();
    }

    protected void updateMenu(String[] comboboxEntries, final RightMenu menu) {
        for (String s : comboboxEntries) {
            this.rightPanelComboBox.addItem(s);
        }

        if ((this.rightPanelComboBox.getActionListeners() != null) && (this.rightPanelComboBox.getActionListeners().length > 0)) {
            this.rightPanelComboBox.removeActionListener(this.rightPanelComboBox.getActionListeners()[0]);
        }

        this.rightPanelComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int entry = EditorRightMenu.this.rightPanelComboBox.getSelectedIndex();
                showSubmenu(menu, entry);
            }
        });

        this.rightPanelSubBtm.add(MenuHint.getInstance(this.bg, this.strComboboxHint + getContentInfo(comboboxEntries)));
        revalidate();

    }

    /*
     * 
     */
    private void showSubmenu(RightMenu menu, int id) {
        this.rightPanelSubBtm.removeAll();

        if (id == 0) {
            this.rightPanelSubBtm.add(MenuHint.getInstance(this.bg, this.strComboboxHint + getContentInfo(null)));

        } else {
            switch (menu) {
                case GEN_CHANGES_MENU:
                    if (id == 1) { // this.strArGenChangeOptions[1]
                        this.rightPanelSubBtm.add(GeneralChangesMenu.getBackgroundMenu());

                    } else if (id == 2) {// this.strArGenChangeOptions[2] ...
                        this.rightPanelSubBtm.add(GeneralChangesMenu.getBrandingMenu());

                    } else if (id == 3) {
                        this.rightPanelSubBtm.add(GeneralChangesMenu.getWindowMenu());

                    } else if (id == 4) {
                        this.rightPanelSubBtm.add(GeneralChangesMenu.getOptionBarMenu());

                    } else if (id == 5) {
                        this.rightPanelSubBtm.add(GeneralChangesMenu.getSecOptMenu());

                    } else if (id == 6) {
                        this.rightPanelSubBtm.add(GeneralChangesMenu.getSliderMenu());

                    } else if (id == 7) {
                        this.rightPanelSubBtm.add(GeneralChangesMenu.getLoadingstatusMenu());
                    }
                    break;

                case BTN_CHANGES_MENU:
                    if (id == 1) {
                        this.rightPanelSubBtm.add(ButtonChangesMenu.getAccessibilityBtnMenu());

                    } else if (id == 2) {
                        this.rightPanelSubBtm.add(ButtonChangesMenu.getCommandBtnMenu());

                    } else if (id == 3) {
                        this.rightPanelSubBtm.add(ButtonChangesMenu.getStandardBtnMenu());

                    } else if (id == 4) {
                        this.rightPanelSubBtm.add(ButtonChangesMenu.getPasswordBtnMenu());

                    } else if (id == 5) {
                        this.rightPanelSubBtm.add(ButtonChangesMenu.getShutdownFrameMenu());

                    } else if (id == 6) {
                        this.rightPanelSubBtm.add(ButtonChangesMenu.getShutdownBtnMenu());

                    } else if (id == 7) {
                        this.rightPanelSubBtm.add(ButtonChangesMenu.getShutdownmenuMenu());

                    } else if (id == 8) {
                        this.rightPanelSubBtm.add(ButtonChangesMenu.getLocaleBtnMenu());

                    } else if (id == 9) {
                        this.rightPanelSubBtm.add(ButtonChangesMenu.getMiscMenu());
                    }
                    break;

                case USERLIST_CHANGES_MENU:
                    if (id == 1) {
                        this.rightPanelSubBtm.add(UserlistChangesMenu.getUserlistImageMenu());

                    } else if (id == 2) {
                        this.rightPanelSubBtm.add(UserlistChangesMenu.getUserlistImageframeMenu());

                    } else if (id == 3) {
                        this.rightPanelSubBtm.add(UserlistChangesMenu.getUserlistLayoutMenu());
                    }
                    break;

                case USERTILE_CHANGES_MENU:
                    if (id == 1) {
                        this.rightPanelSubBtm.add(UsertileChangesMenu.getUsertileImageMenu());

                    } else if (id == 2) {
                        this.rightPanelSubBtm.add(UsertileChangesMenu.getUsertileImageframeMenu());

                    } else if (id == 3) {
                        this.rightPanelSubBtm.add(UsertileChangesMenu.getUsertileLayoutMenu());

                    } else if (id == 4) {
                        this.rightPanelSubBtm.add(UsertileChangesMenu.getUsertilePWFieldMenu());
                    }
                    break;

                case FONT_MENU:
                    if (id == 1) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getFontshadowChangesMenu());

                    } else if (id == 2) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getAllFontsChangesMenu());

                    } else if (id == 3) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getShutdownChangesMenu());

                    } else if (id == 4) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getShutdownMenuChangesMenu());

                    } else if (id == 5) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getPWFieldBtnChangesMenu());

                    } else if (id == 6) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getPWResetBtnChangesMenu());

                    } else if (id == 7) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getLoadingstatusChangesMenu());

                    } else if (id == 8) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getShortMsgChangesMenu());

                    } else if (id == 9) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getCommandBtnChangesMenu());

                    } else if (id == 10) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getStandardBtnChangesMenu());

                    } else if (id == 11) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getStatustextInListChangesMenu());

                    } else if (id == 12) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getStatustextInTileChangesMenu());

                    } else if (id == 13) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getNametextInListChangesMenu());

                    } else if (id == 14) {
                        this.rightPanelSubBtm.add(FontChangesMenu.getNametextInTileChangesMenu());
                    }
                    break;

                default:
                    throw new IllegalArgumentException();
            }
        }

        revalidate();
        this.repaint();
    }

    private String getContentInfo(String[] cbEntries) {
        if (cbEntries != null) {
            String sep = System.getProperty("line.separator");
            this.comboboxHintAddition = sep + sep + GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_COMBOBOXHINTCONTENT) + sep;

            for (int i = 1; i < cbEntries.length; i++) {
                this.comboboxHintAddition += cbEntries[i] + sep;
            }
        }

        return this.comboboxHintAddition;
    }

    private void create() {
        final JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        top.setBackground(this.bg);
        top.setVisible(false);
        this.add(top, BorderLayout.NORTH);

        this.rightPanelInfo = new JLabel();
        this.rightPanelInfo.setFont(GUIConstants.DEFAULT_HEADING_FONT);
        this.rightPanelInfo.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        this.rightPanelInfo.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_RIGHTINFO));
        top.add(this.rightPanelInfo);

        final JPanel rightPanelMain = new JPanel();
        rightPanelMain.setBackground(this.bg);
        rightPanelMain.setLayout(new BorderLayout());
        rightPanelMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        rightPanelMain.setVisible(false);
        this.add(rightPanelMain);

        JPanel rightPanelSubTop = new JPanel();
        rightPanelSubTop.setBackground(this.bg);
        rightPanelSubTop.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)), BorderFactory.createEmptyBorder(0, 5, 5, 0)));
        rightPanelMain.add(rightPanelSubTop, BorderLayout.NORTH);

        this.rightPanelComboBox = new JComboBox<>();
        this.rightPanelComboBox.setFont(GUIConstants.DEFAULT_BUTTON_FONT);
        this.rightPanelComboBox.setPreferredSize(GUIConstants.DEFAULT_COMBOBOX_DIM);
        this.rightPanelComboBox.setOpaque(false);
        this.rightPanelComboBox.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        rightPanelSubTop.add(this.rightPanelComboBox);
        this.rightPanelComboBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        EditorRightMenu.this.editor.setFocusToLeftMenu();
                        break;
                    default:
                        break;
                }
            }
        });

        this.rightPanelSubBtm = new JPanel();
        this.rightPanelSubBtm.setBackground(this.bg);
        this.rightPanelSubBtm.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 15));
        this.rightPanelSubBtm.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        JScrollPane scroll = new JScrollPane(this.rightPanelSubBtm, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        rightPanelMain.add(scroll, BorderLayout.CENTER);

        final JPanel btm = new JPanel();
        btm.setLayout(new BorderLayout());
        btm.setBackground(this.bg);
        btm.setOpaque(false);
        this.add(btm, BorderLayout.SOUTH);

        this.rightHideBtn = new JButton();
        this.rightHideBtn.setOpaque(false);
        this.rightHideBtn.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_SHOWHIDEMENU));
        btm.add(this.rightHideBtn, BorderLayout.EAST);
        this.rightHideBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!EditorRightMenu.this.rightPanelHidden) {
                    EditorRightMenu.this.rightPanelHidden = true;
                    top.setVisible(false);
                    rightPanelMain.setVisible(false);
                    btm.remove(EditorRightMenu.this.rightHideBtn);
                    btm.add(EditorRightMenu.this.rightHideBtn, BorderLayout.EAST);
                    btm.setOpaque(false);
                    EditorRightMenu.this.rightHideBtn.grabFocus();

                } else {
                    EditorRightMenu.this.rightPanelHidden = false;
                    top.setVisible(true);
                    rightPanelMain.setVisible(true);
                    btm.remove(EditorRightMenu.this.rightHideBtn);
                    btm.add(EditorRightMenu.this.rightHideBtn, BorderLayout.WEST);
                    btm.setOpaque(true);
                    EditorRightMenu.this.rightHideBtn.grabFocus();
                }
            }
        });

        this.rightPanelSubBtm.add(MenuHint.getInstance(this.bg, GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_MENUHINT)));
    }
}
