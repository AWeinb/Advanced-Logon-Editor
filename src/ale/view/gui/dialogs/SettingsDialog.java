/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ale.Constants;
import ale.controller.Language;
import ale.controller.Main;
import ale.controller.Settings;
import ale.view.gui.GUIConstants;
import ale.view.gui.util.GUIStrings;
import ale.view.gui.util.VerticalLayout;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.dialogs <br/>
 * Class  : SettingsDialog <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>SettingsDialog</code> shows some settingoptions.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 15.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class SettingsDialog extends Dialog {

    private static final long serialVersionUID = 1L;
    private final JPanel basePanel = new JPanel();

    private JPanel settingsPanel;
    private JLabel lblLanguageCBLabel;
    private JLabel lblbgScaleLabel;
    private JButton okButton;
    private JButton cancelButton;

    private Language bak;

    /**
     * 
     */
    public SettingsDialog() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SettingsDialog.this.setSize(new Dimension(450, 400));
                SettingsDialog.this.setTitle(GUIConstants.PROGRAM_TITLE);
                SettingsDialog.this.setIconImage(GUIConstants.PROGRAM_ICON);
                SettingsDialog.this.setLocationRelativeTo(null);
                SettingsDialog.this.getContentPane().setLayout(new BorderLayout());
                SettingsDialog.this.basePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
                SettingsDialog.this.basePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                SettingsDialog.this.basePanel.setLayout(new BorderLayout());
                SettingsDialog.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                SettingsDialog.this.setModalityType(ModalityType.APPLICATION_MODAL);
                SettingsDialog.this.getContentPane().add(SettingsDialog.this.basePanel, BorderLayout.CENTER);

                create();
                updateLocale();

                SettingsDialog.this.setVisible(true);
            }
        });
    }

    private void updateLocale() {
        this.settingsPanel.setBorder(new TitledBorder(GUIStrings.keyToLocatedString(GUIStrings.KEY_SETTINGSDIALOG_TITLE)));
        this.lblLanguageCBLabel.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_SETTINGSDIALOG_LANGCB));
        this.lblbgScaleLabel.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_SETTINGSDIALOG_BGSCALE));
        this.okButton.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_SETTINGSDIALOG_OK));
        this.cancelButton.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_SETTINGSDIALOG_CANCEL));
    }

    private void create() {
        this.settingsPanel = new JPanel();
        this.settingsPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        this.settingsPanel.setLayout(new BorderLayout());
        this.basePanel.add(this.settingsPanel, BorderLayout.CENTER);

        {
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new VerticalLayout(15, VerticalLayout.LEFT));
            leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 0));
            leftPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.settingsPanel.add(leftPanel, BorderLayout.WEST);

            this.lblLanguageCBLabel = new JLabel();
            this.lblLanguageCBLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.lblLanguageCBLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            this.lblLanguageCBLabel.setFont(GUIConstants.DEFAULT_MESSAGE_FONT);
            leftPanel.add(this.lblLanguageCBLabel);

            this.lblbgScaleLabel = new JLabel();
            this.lblbgScaleLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.lblbgScaleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            this.lblbgScaleLabel.setFont(GUIConstants.DEFAULT_MESSAGE_FONT);
            leftPanel.add(this.lblbgScaleLabel);
        }

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new VerticalLayout(15, VerticalLayout.RIGHT));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 5));
        rightPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        this.settingsPanel.add(rightPanel, BorderLayout.EAST);

        final JComboBox<String> languageComboBox = new JComboBox<String>();
        languageComboBox.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        languageComboBox.setBorder(null);
        languageComboBox.setPreferredSize(GUIConstants.DEFAULT_BUTTON_DIM);

        boolean err = false;
        List<Language> langList = null;
        try {
            langList = Settings.getLanguageList();
        } catch (IOException e1) {
            err = true;
        }

        if (!err) {
            for (int i = 0; i < langList.size(); i++) {
                Language lang = langList.get(i);
                languageComboBox.addItem(lang.getNameString());

                if (lang.getCountryString().equals(Settings.getLocaleLanguage().getCountryString())) {
                    languageComboBox.setSelectedIndex(i);
                }
            }
            languageComboBox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int entry = languageComboBox.getSelectedIndex();
                    List<Language> langList1 = null;
                    try {
                        langList1 = Settings.getLanguageList();
                        SettingsDialog.this.bak = Settings.getLocaleLanguage();
                        Settings.setLocaleLanguage(langList1.get(entry));
                        Main.updateLocale(Settings.getLocaleLanguage());
                        updateLocale();

                    } catch (IOException e1) {
                        Main.showProblemMessage(e1.getMessage());
                    }
                }
            });
        }
        rightPanel.add(languageComboBox);

        final JCheckBox bgScaleCheckBox = new JCheckBox();
        bgScaleCheckBox.setSelected(Settings.getEditorBgScaled());
        bgScaleCheckBox.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        rightPanel.add(bgScaleCheckBox);

        String s = "...";
        if (Settings.getFilechooserBasePath() != null) {
            s = Settings.getFilechooserBasePath().toString();
            if (s.length() > 10) {
                s = s.substring(0, 10);
            }
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        {
            this.okButton = new JButton();
            this.okButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.okButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) {
                    SettingsDialog.this.okButton.setEnabled(false);
                    Settings.scaleEditorBackground(bgScaleCheckBox.isSelected());
                    Settings.saveProperties(Constants.SETTINGS_PATH);
                    dispose();
                }
            });
            buttonPanel.add(this.okButton);

            this.cancelButton = new JButton();
            this.cancelButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (SettingsDialog.this.bak != null) {
                        Settings.setLocaleLanguage(SettingsDialog.this.bak);
                        Main.updateLocale(Settings.getLocaleLanguage());
                        updateLocale();
                    }
                    dispose();
                }
            });
            buttonPanel.add(this.cancelButton);
        }
    }
}
