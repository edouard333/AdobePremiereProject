package com.phenix.adobepremiereproject.column;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class BoolPropertyColumn extends Column {

    /**
     * 
     */
    private final String PropertyBool;

    /**
     * 
     */
    private final String EditableKey;

    /**
     * 
     * @param ObjectID
     * @param Name
     * @param ID
     * @param Type
     * @param Class
     * @param IsHidden
     * @param Width
     * @param PropertyBool
     * @param EditableKey 
     */
    public BoolPropertyColumn(int ObjectID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width, String PropertyBool, String EditableKey) {
        super("BoolPropertyColumn", ObjectID, "1d4dd772-4985-4f43-874a-84b2b566e724", Name, ID, Type, Class, IsHidden, Width);
        this.PropertyBool = PropertyBool;
        this.EditableKey = EditableKey;
    }

    /**
     * 
     * @param file 
     */
    @Override
    public void toXML(PrintWriter file) {
        super.toXML(file, (file2) -> {
            file.append("\t\t\t\t\t\t<Column.Property.Key>" + this.PropertyBool + "</Column.Property.Key>\n");
            file.append("\t\t\t\t\t\t<Column.Editable.Key>" + this.EditableKey + "</Column.Editable.Key>\n");
        });
    }
}
