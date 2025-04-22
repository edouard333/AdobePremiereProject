package com.phenix.adobepremiereproject.column;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class EditTextColumn extends Column {

    /**
     *
     * @param ObjectID
     * @param Name
     * @param ID
     * @param Type
     * @param Class
     * @param IsHidden
     * @param Width
     */
    public EditTextColumn(int ObjectID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width) {
        super("EditTextColumn", ObjectID, "e9f21f9a-b686-440c-83f4-da1685c160ad", Name, ID, Type, Class, IsHidden, Width);
    }

    /**
     *
     * @param writer
     */
    @Override
    public void toXML(PrintWriter writer) {
        super.toXML(writer);
    }
}
