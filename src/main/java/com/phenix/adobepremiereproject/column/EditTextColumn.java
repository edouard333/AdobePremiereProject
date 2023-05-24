package com.phenix.adobepremiereproject.column;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class EditTextColumn extends Column {

    public EditTextColumn(int ObjectID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width) {
        super("EditTextColumn", ObjectID, "e9f21f9a-b686-440c-83f4-da1685c160ad", Name, ID, Type, Class, IsHidden, Width);
    }

    @Override
    public void toXML(PrintWriter file) {
        super.toXML(file);
    }
}
