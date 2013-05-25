/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.chooser;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import ale.controller.Main;
import ale.view.gui.GUIConstants;
import ale.view.gui.util.GUIStrings;

/*
 * Contains the menubar of the chooser.
 */
final class QuickChooserMenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;

    private JMenu fileMenu;
    private JMenuItem fileExit;
    private JMenu settings;
    private JMenuItem settingsItem;
    private JMenu helpMenu;
    private JMenuItem docsMenuItem;
    private JMenuItem webMenuItem;
    private JMenuItem backupMenuItem;
    private JMenuItem aboutMenuItem;

    public QuickChooserMenuBar() {
        create();
        updateLocale();
    }

    /**
     * Updates the strings of the menubar.
     *
     *
     */
    protected void updateLocale() {
        String tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_FILE);
        this.fileMenu.setText(tmp);
        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_EXIT);
        this.fileExit.setText(tmp);
        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_SETTINGS);
        this.settings.setText(tmp);
        this.settingsItem.setText(tmp); // Same string as above
        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_HELP);
        this.helpMenu.setText(tmp);
        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_DOCS);
        this.docsMenuItem.setText(tmp);
        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_WEB);
        this.webMenuItem.setText(tmp);
        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_BACKUP);
        this.backupMenuItem.setText(tmp);
        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_ABOUT);
        this.aboutMenuItem.setText(tmp);
    }

    private void create() {
        this.fileMenu = new JMenu();
        this.add(this.fileMenu);

        this.fileExit = new JMenuItem();
        this.fileExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.shutdown();
            }
        });
        Dimension tmp = this.fileExit.getPreferredSize();
        tmp.width = GUIConstants.DEFAULT_MENUITEM_WIDTH;
        this.fileExit.setPreferredSize(tmp);
        this.fileMenu.add(this.fileExit);

        this.settings = new JMenu();
        this.add(this.settings);

        this.settingsItem = new JMenuItem();
        this.settingsItem.setPreferredSize(tmp);
        this.settingsItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.showSettingsDialog();
            }
        });
        this.settings.add(this.settingsItem);

        this.helpMenu = new JMenu();
        this.add(this.helpMenu);

        this.docsMenuItem = new JMenuItem();
        this.docsMenuItem.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_DOWN_MASK));
        this.docsMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showHelpDocs();
            }
        });
        this.helpMenu.add(this.docsMenuItem);

        this.webMenuItem = new JMenuItem();
        this.webMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showWebsite();
            }
        });
        this.helpMenu.add(this.webMenuItem);

        this.helpMenu.addSeparator();
        this.backupMenuItem = new JMenuItem();
        this.backupMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.applyBackup();
            }
        });
        this.helpMenu.add(this.backupMenuItem);

        this.helpMenu.addSeparator();
        this.aboutMenuItem = new JMenuItem();
        this.aboutMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.showAboutDialog();
            }
        });
        this.helpMenu.add(this.aboutMenuItem);
    }
}
