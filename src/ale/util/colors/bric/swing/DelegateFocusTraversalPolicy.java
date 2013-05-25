/*
 * @(#)DelegateFocusTraversalPolicy.java
 *
 * $Date: 2012-07-03 01:10:05 -0500 (Tue, 03 Jul 2012) $
 *
 * Copyright (c) 2011 by Jeremy Wood.
 * All rights reserved.
 *
 * The copyright of this software is owned by Jeremy Wood.
 * You may not use, copy or modify this software, except in
 * accordance with the license agreement you entered into with
 * Jeremy Wood. For details see accompanying license terms.
 * 
 * This software is probably, but not necessarily, discussed here:
 * http://javagraphics.java.net/
 * 
 * That site should also contain the most recent official version
 * of this software.  (See the SVN repository for more details.)
 */
package ale.util.colors.bric.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;

/**
 * A simple <code>FocusTraversalPolicy</code> object that delegates to another object.
 * 
 */
public class DelegateFocusTraversalPolicy extends FocusTraversalPolicy {
    FocusTraversalPolicy ftp;

    public DelegateFocusTraversalPolicy(FocusTraversalPolicy policy) {
        this.ftp = policy;
    }

    @Override
    public Component getComponentAfter(Container focusCycleRoot,
            Component component) {
        return this.ftp.getComponentAfter(focusCycleRoot, component);
    }

    @Override
    public Component getComponentBefore(Container focusCycleRoot,
            Component component) {
        return this.ftp.getComponentBefore(focusCycleRoot, component);
    }

    @Override
    public Component getDefaultComponent(Container focusCycleRoot) {
        return this.ftp.getDefaultComponent(focusCycleRoot);
    }

    @Override
    public Component getFirstComponent(Container focusCycleRoot) {
        return this.ftp.getFirstComponent(focusCycleRoot);
    }

    @Override
    public Component getLastComponent(Container focusCycleRoot) {
        return this.ftp.getLastComponent(focusCycleRoot);
    }
}
