/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import ale.controller.Main;
import ale.model.skin.Skin;
import ale.view.gui.GUIConstants;
import ale.view.gui.util.GUIStrings;

final class EditorMenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;

    public EditorMenuBar(Skin skin) {
        create(skin);
    }

    private void create(final Skin skin) {
        String tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_FILE);
        JMenu fileMenu = new JMenu(tmp);
        fileMenu.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        this.add(fileMenu);

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_MENUBAR_NEW);
        JMenuItem newMenuItem = new JMenuItem(tmp);
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
        newMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.showQuitConfirmationDialog(skin, true, false);
            }
        });

        Dimension menuitemDimension = newMenuItem.getPreferredSize();
        menuitemDimension.width = GUIConstants.DEFAULT_MENUITEM_WIDTH;
        newMenuItem.setPreferredSize(menuitemDimension);
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_MENUBAR_SAVE);
        JMenuItem saveMenuItem = new JMenuItem(tmp);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        saveMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.save(skin);
            }
        });
        fileMenu.add(saveMenuItem);

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_MENUBAR_SAVEAS);
        JMenuItem saveAsMenuItem = new JMenuItem(tmp);
        saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK + InputEvent.ALT_MASK));
        saveAsMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showSaveAsDialog(skin);
            }
        });
        fileMenu.add(saveAsMenuItem);

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_MENUBAR_APPLY);
        JMenuItem applyMenuItem = new JMenuItem(tmp);
        applyMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.save(skin);
                Main.showApplySkinDialog(skin.getFilename());
            }
        });
        fileMenu.add(applyMenuItem);
        fileMenu.addSeparator();

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_MENUBAR_TOCHOOSER);
        JMenuItem closeMenuItem = new JMenuItem(tmp);
        closeMenuItem.setAccelerator(KeyStroke.getKeyStroke('W', InputEvent.CTRL_MASK));
        closeMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showQuitConfirmationDialog(skin, false, true);
            }
        });
        fileMenu.add(closeMenuItem);

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_EXIT);
        JMenuItem exitMenuItem = new JMenuItem(tmp);
        exitMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showQuitConfirmationDialog(skin, false, false);
            }
        });
        fileMenu.add(exitMenuItem);

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_SETTINGS);
        JMenu settingsMenu = new JMenu(tmp);
        this.add(settingsMenu);

        JMenuItem settingsMenuItem = new JMenuItem(tmp);
        settingsMenuItem.setPreferredSize(menuitemDimension);
        settingsMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showSettingsDialog();
            }
        });
        settingsMenu.add(settingsMenuItem);

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_HELP);
        JMenu helpMenu = new JMenu(tmp);
        this.add(helpMenu);

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_DOCS);
        JMenuItem docsMenuItem = new JMenuItem(tmp);
        docsMenuItem.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_DOWN_MASK));
        docsMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showHelpDocs();
            }
        });
        helpMenu.add(docsMenuItem);

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_WEB);
        JMenuItem webMenuItem = new JMenuItem(tmp);
        webMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showWebsite();
            }
        });
        helpMenu.add(webMenuItem);
        helpMenu.addSeparator();

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_BACKUP);
        JMenuItem backupMenuItem = new JMenuItem(tmp);
        backupMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.applyBackup();
            }
        });
        helpMenu.add(backupMenuItem);
        helpMenu.addSeparator();

        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_MENUBAR_ABOUT);
        JMenuItem aboutMenuItem = new JMenuItem(tmp);
        aboutMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.showAboutDialog();
            }
        });
        helpMenu.add(aboutMenuItem);
    }
}
