/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.fields;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import ale.model.skin.SkinConstants.Animation;
import ale.view.gui.GUIConstants;
import ale.view.gui.util.VerticalLayout;

public abstract class AnimationField extends Field {

    private static final int FIELDHEIGHT = 160;

    public AnimationField(Color bg, Animation init, String fieldTitle) {
        if (fieldTitle == null) {
            fieldTitle = "";
        }

        if (init == null) {
            init = Animation.ORIGINAL;
        }

        if (bg == null) {
            bg = Color.WHITE;
        }

        create(bg, init, fieldTitle);
    }

    public abstract void originalAnimChosen();

    public abstract void horizontalSlowAnimChosen();

    public abstract void horizontalFastAnimChosen();

    public abstract void verticalSlowAnimChosen();

    public abstract void verticalFastAnimChosen();

    private void create(Color bg, Animation init, String fieldTitle) {
        setPreferredSize(new Dimension(GUIConstants.DEFAULT_FIELD_WIDTH, FIELDHEIGHT));
        setBackground(bg);
        setLayout(new VerticalLayout(5, VerticalLayout.CENTER));
        setBorder(BorderFactory.createTitledBorder(fieldTitle));

        JRadioButton noAnimation = new JRadioButton(Field.strAnimationOriginal);
        JRadioButton animHSlow = new JRadioButton(Field.strAnimationHorizontal + " & " + Field.strAnimationSlow);
        JRadioButton animVSlow = new JRadioButton(Field.strAnimationVertical + " & " + Field.strAnimationSlow);
        JRadioButton animHFast = new JRadioButton(Field.strAnimationHorizontal + " & " + Field.strAnimationFast);
        JRadioButton animVFast = new JRadioButton(Field.strAnimationVertical + " & " + Field.strAnimationFast);

        noAnimation.setBackground(bg);
        animHSlow.setBackground(bg);
        animVSlow.setBackground(bg);
        animHFast.setBackground(bg);
        animVFast.setBackground(bg);

        noAnimation.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                originalAnimChosen();
            }
        });
        animHSlow.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                horizontalSlowAnimChosen();
            }
        });
        animVSlow.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                verticalSlowAnimChosen();

            }
        });
        animHFast.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                horizontalFastAnimChosen();
            }
        });
        animVFast.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                verticalFastAnimChosen();
            }
        });

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(noAnimation);
        btnGroup.add(animHSlow);
        btnGroup.add(animVSlow);
        btnGroup.add(animHFast);
        btnGroup.add(animVFast);

        add(noAnimation);
        add(animHSlow);
        add(animVSlow);
        add(animHFast);
        add(animVFast);

        switch (init) {
            case ORIGINAL:
                noAnimation.setSelected(true);
                break;

            case RECTANGLE_H_FAST:
                animHFast.setSelected(true);
                break;

            case RECTANGLE_H_SLOW:
                animHSlow.setSelected(true);
                break;

            case RECTANGLE_V_FAST:
                animVFast.setSelected(true);
                break;

            case RECTANGLE_V_SLOW:
                animVSlow.setSelected(true);
                break;

            default:
                noAnimation.setSelected(true);
                break;
        }
    }
}
