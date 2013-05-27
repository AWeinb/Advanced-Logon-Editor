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

import ale.model.skin.SkinPropertiesVO;
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

    private GeneralChangesMenu generalChangesMenu;
    private ButtonChangesMenu buttonChangesMenu;
    private UserlistChangesMenu userlistChangesMenu;
    private UsertileChangesMenu usertileChangesMenu;
    private FontChangesMenu fontChangesMenu;

    private String strComboboxHint = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_COMBOBOXHINT);

    public EditorRightMenu(Editor editor, SkinPropertiesVO skinProps, Color background) {
        this.editor = editor;
        this.bg = background;

        this.generalChangesMenu = new GeneralChangesMenu(this.bg, skinProps);
        this.buttonChangesMenu = new ButtonChangesMenu(this.bg, skinProps);
        this.userlistChangesMenu = new UserlistChangesMenu(this.bg, skinProps);
        this.usertileChangesMenu = new UsertileChangesMenu(this.bg, skinProps);
        this.fontChangesMenu = new FontChangesMenu(this.bg, skinProps);

        while (!this.generalChangesMenu.isInitialized() && !this.buttonChangesMenu.isInitialized()
                && !this.userlistChangesMenu.isInitialized()
                && !this.usertileChangesMenu.isInitialized() && !this.fontChangesMenu.isInitialized()) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

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
                        this.rightPanelSubBtm.add(this.generalChangesMenu.getBackgroundMenu());

                    } else if (id == 2) {// this.strArGenChangeOptions[2] ...
                        this.rightPanelSubBtm.add(this.generalChangesMenu.getBrandingMenu());

                    } else if (id == 3) {
                        this.rightPanelSubBtm.add(this.generalChangesMenu.getWindowMenu());

                    } else if (id == 4) {
                        this.rightPanelSubBtm.add(this.generalChangesMenu.getOptionBarMenu());

                    } else if (id == 5) {
                        this.rightPanelSubBtm.add(this.generalChangesMenu.getSecOptMenu());

                    } else if (id == 6) {
                        this.rightPanelSubBtm.add(this.generalChangesMenu.getSliderMenu());

                    } else if (id == 7) {
                        this.rightPanelSubBtm.add(this.generalChangesMenu.getLoadingstatusMenu());
                    }
                    break;

                case BTN_CHANGES_MENU:
                    if (id == 1) {
                        this.rightPanelSubBtm.add(this.buttonChangesMenu.getAccessibilityBtnMenu());

                    } else if (id == 2) {
                        this.rightPanelSubBtm.add(this.buttonChangesMenu.getCommandBtnMenu());

                    } else if (id == 3) {
                        this.rightPanelSubBtm.add(this.buttonChangesMenu.getStandardBtnMenu());

                    } else if (id == 4) {
                        this.rightPanelSubBtm.add(this.buttonChangesMenu.getPasswordBtnMenu());

                    } else if (id == 5) {
                        this.rightPanelSubBtm.add(this.buttonChangesMenu.getShutdownFrameMenu());

                    } else if (id == 6) {
                        this.rightPanelSubBtm.add(this.buttonChangesMenu.getShutdownBtnMenu());

                    } else if (id == 7) {
                        this.rightPanelSubBtm.add(this.buttonChangesMenu.getShutdownmenuMenu());

                    } else if (id == 8) {
                        this.rightPanelSubBtm.add(this.buttonChangesMenu.getLocaleBtnMenu());

                    } else if (id == 9) {
                        this.rightPanelSubBtm.add(this.buttonChangesMenu.getMiscMenu());
                    }
                    break;

                case USERLIST_CHANGES_MENU:
                    if (id == 1) {
                        this.rightPanelSubBtm.add(this.userlistChangesMenu.getUserlistImageMenu());

                    } else if (id == 2) {
                        this.rightPanelSubBtm.add(this.userlistChangesMenu.getUserlistImageframeMenu());

                    } else if (id == 3) {
                        this.rightPanelSubBtm.add(this.userlistChangesMenu.getUserlistLayoutMenu());
                    }
                    break;

                case USERTILE_CHANGES_MENU:
                    if (id == 1) {
                        this.rightPanelSubBtm.add(this.usertileChangesMenu.getUsertileImageMenu());

                    } else if (id == 2) {
                        this.rightPanelSubBtm.add(this.usertileChangesMenu.getUsertileImageframeMenu());

                    } else if (id == 3) {
                        this.rightPanelSubBtm.add(this.usertileChangesMenu.getUsertileLayoutMenu());

                    } else if (id == 4) {
                        this.rightPanelSubBtm.add(this.usertileChangesMenu.getUsertilePWFieldMenu());
                    }
                    break;

                case FONT_MENU:
                    if (id == 1) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getFontshadowChangesMenu());

                    } else if (id == 2) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getAllFontsChangesMenu());

                    } else if (id == 3) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getShutdownChangesMenu());

                    } else if (id == 4) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getShutdownMenuChangesMenu());

                    } else if (id == 5) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getPWFieldBtnChangesMenu());

                    } else if (id == 6) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getPWResetBtnChangesMenu());

                    } else if (id == 7) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getLoadingstatusChangesMenu());

                    } else if (id == 8) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getShortMsgChangesMenu());

                    } else if (id == 9) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getCommandBtnChangesMenu());

                    } else if (id == 10) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getStandardBtnChangesMenu());

                    } else if (id == 11) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getStatustextInListChangesMenu());

                    } else if (id == 12) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getStatustextInTileChangesMenu());

                    } else if (id == 13) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getNametextInListChangesMenu());

                    } else if (id == 14) {
                        this.rightPanelSubBtm.add(this.fontChangesMenu.getNametextInTileChangesMenu());
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
        String comboboxHintAddition = "";
        if (cbEntries != null) {
            String sep = System.getProperty("line.separator");
            comboboxHintAddition = sep + sep + GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_COMBOBOXHINTCONTENT) + sep;

            for (int i = 1; i < cbEntries.length; i++) {
                comboboxHintAddition += cbEntries[i] + sep;
            }
        }

        return comboboxHintAddition;
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

    public void shutdown() {
        this.editor = null;
        this.bg = null;

        this.generalChangesMenu.shutdown();
        this.buttonChangesMenu.shutdown();
        this.userlistChangesMenu.shutdown();
        this.usertileChangesMenu.shutdown();
        this.fontChangesMenu.shutdown();

        this.generalChangesMenu = null;
        this.buttonChangesMenu = null;
        this.userlistChangesMenu = null;
        this.usertileChangesMenu = null;
        this.fontChangesMenu = null;

        this.rightHideBtn = null;
        this.rightPanelComboBox = null;
        this.rightPanelSubBtm = null;
        this.rightPanelInfo = null;
    }
}
