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

import ale.model.skin.SkinPreviewVO;
import ale.view.gui.util.GUIStrings;

final class PopUpMenu extends JPopupMenu {

    private static final long serialVersionUID = 1L;
    private JMenuItem nameItem;
    private JMenuItem authorItem;
    private JMenuItem webItem;

    PopUpMenu(final SkinPreviewVO skinprw) {
        this.nameItem = new JMenuItem(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_COPYNAME));
        this.authorItem = new JMenuItem(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_COPYAUTHOR));
        this.webItem = new JMenuItem(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_COPYWEBSITE));

        add(this.nameItem);
        add(this.authorItem);
        add(this.webItem);

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
    }
}
