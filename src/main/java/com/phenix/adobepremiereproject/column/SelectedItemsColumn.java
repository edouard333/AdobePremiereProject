package com.phenix.adobepremiereproject.column;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class SelectedItemsColumn extends Column {

    public SelectedItemsColumn(int ObjectID, String ClassID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width) {
        super("SelectedItemsColumn", ObjectID, ClassID, Name, ID, Type, Class, IsHidden, Width);
    }

    @Override
    public void toXML(PrintWriter file) {
        super.toXML(file);
    }
}
