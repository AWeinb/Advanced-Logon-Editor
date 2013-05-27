/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.chooser;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ale.controller.Main;
import ale.model.skin.SkinPreviewVO;
import ale.view.gui.util.GUIStrings;

final class ListPopUpMenu extends JPopupMenu {

    private static final long serialVersionUID = 1L;
    private JMenuItem nameItem;
    private JMenuItem authorItem;
    private JMenuItem webItem;

    private JMenuItem applyItem;
    private JMenuItem editItem;
    private JMenuItem deleteItem;

    private JMenuItem renameItem;

    ListPopUpMenu(final SkinPreviewVO skinprw) {
        this.nameItem = new JMenuItem(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_COPYNAME));
        this.authorItem = new JMenuItem(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_COPYAUTHOR));
        this.webItem = new JMenuItem(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_COPYWEBSITE));
        this.applyItem = new JMenuItem(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_APPLYBTN));
        this.editItem = new JMenuItem(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_EDITBTN));
        this.deleteItem = new JMenuItem(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_DELETEBTN));
        this.renameItem = new JMenuItem(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_RENAMEBTN));

        add(this.nameItem);
        add(this.authorItem);
        add(this.webItem);
        addSeparator();
        add(this.applyItem);
        add(this.editItem);
        add(this.deleteItem);
        addSeparator();
        add(this.renameItem);

        final Clipboard clipboard = getToolkit().getSystemClipboard();

        this.nameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection data = new StringSelection(skinprw.getName());
                clipboard.setContents(data, data);
            }
        });

        this.authorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection data = new StringSelection(skinprw.getAuthor());
                clipboard.setContents(data, data);
            }
        });

        this.webItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection data = new StringSelection(skinprw.getWebsite());
                clipboard.setContents(data, data);
            }
        });

        this.applyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showApplySkinDialog(skinprw.getFilename());
            }
        });

        this.editItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showEditor(skinprw.getFilename());
            }
        });

        this.deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.deleteSkin(skinprw.getFilename());
            }
        });

        this.renameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showRenameSkinDialog(skinprw.getFilename());
            }
        });
    }
}
