/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Copyright (C) 2009 Yan Cheng Cheok <yccheok@yahoo.com>
 */

package org.yccheok.jstock.gui.portfolio;

import java.awt.Component;
import javax.swing.JComponent;
import org.jdesktop.swingx.JXTree;
import org.jdesktop.swingx.decorator.AbstractHighlighter;
import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.yccheok.jstock.gui.Utils;

/**
 *
 * @author yccheok
 */
public class ToolTipHighlighter extends AbstractHighlighter {

    @Override
    protected Component doHighlight(Component component, ComponentAdapter adapter) {
        int modelIndex = adapter.getColumnIndex("Comment");
        Object object = adapter.getValue(modelIndex);
        
        // Empty string. Nothing to be displayed.
        if (object.toString().length() <= 0) {
            return component;
        }

        if (component instanceof JXTree) {
            // Hack around #794-swingx:
            // PENDING: treetableCellRenderer doesn't reset tooltip
            ((JComponent) component).setToolTipText(null);
        }
        else {
            // In order to support multi-line effect, we must first convert it
            // to HTML.
            ((JComponent) component).setToolTipText(Utils.toHTML(object.toString()));
        }        
        return component;
    }
}