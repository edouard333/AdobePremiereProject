package com.phenix.adobepremiereproject.column;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class NameColumn extends Column {

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
    public NameColumn(int ObjectID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width) {
        super("NameColumn", ObjectID, "0547b302-c849-46b3-ae2a-b245e9dd59eb", Name, ID, Type, Class, IsHidden, Width);
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
