package com.phenix.adobepremiereproject.column;

import java.io.PrintWriter;

/**
 *
 * @author Edouard Jeanjean <edouard128@hotmail.com>
 */
public class LabelColumn extends Column {

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
    public LabelColumn(int ObjectID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width) {
        super("LabelColumn", ObjectID, "0b8cc011-65dd-4b47-aad9-751ca2891f4a", Name, ID, Type, Class, IsHidden, Width);
    }

    /**
     * 
     * @param file 
     */
    @Override
    public void toXML(PrintWriter file) {
        super.toXML(file);
    }

}
