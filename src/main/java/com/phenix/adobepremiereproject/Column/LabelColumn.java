package com.phenix.adobepremiereproject.Column;

import com.phenix.adobepremiereproject.Column.Column;
import java.io.PrintWriter;

/**
 *
 * @author Edouard Jeanjean <edouard128@hotmail.com>
 */
public class LabelColumn extends Column {

    public LabelColumn(int ObjectID, String ClassID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width) {
        super(ObjectID, ClassID, Name, ID, Type, Class, IsHidden, Width);
    }

    @Override
    public void toXML(PrintWriter file) {
        super.toXML(file);
    }

}
