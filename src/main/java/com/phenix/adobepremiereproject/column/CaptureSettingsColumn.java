package com.phenix.adobepremiereproject.column;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class CaptureSettingsColumn extends Column {

    public CaptureSettingsColumn(int ObjectID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width) {
        super("CaptureSettingsColumn", ObjectID, "97dc9c98-3a27-4320-91c3-cc222addeef7", Name, ID, Type, Class, IsHidden, Width);
    }

    @Override
    public void toXML(PrintWriter file) {
        super.toXML(file);
    }
}
