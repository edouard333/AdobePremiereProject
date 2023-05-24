package com.phenix.adobepremiereproject.column;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class StringColumn extends Column {

    public StringColumn(int ObjectID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width) {
        super("StringColumn", ObjectID, "f0ef302d-babc-4f75-9975-923a8ca28d7e", Name, ID, Type, Class, IsHidden, Width);
    }

    @Override
    public void toXML(PrintWriter file) {
        super.toXML(file);
    }
}
