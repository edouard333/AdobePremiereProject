package com.phenix.adobepremiereproject.column;

import com.phenix.adobepremiereproject.column.internal.AddPropertiesXML;
import jakarta.validation.constraints.Null;
import com.phenix.adobepremiereproject.internal.XMLWithPropertiesConvertible;
import com.phenix.adobepremiereproject.internal.XMLSimpleConvertible;

/**
 * La classe est faite pour être dérivée.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Column implements XMLWithPropertiesConvertible, XMLSimpleConvertible {

    /**
     *
     */
    private final String ColumnType;

    /**
     *
     */
    private final int ObjectID;

    /**
     *
     */
    private final String ClassID;

    /**
     *
     */
    private final String Name;

    /**
     *
     */
    private final String ID;

    /**
     *
     */
    private final int Type;

    /**
     *
     */
    private final int Class;

    /**
     *
     */
    private final boolean IsHidden;

    /**
     *
     */
    private final int Width;

    /**
     *
     * @param ColumnType
     * @param ObjectID
     * @param ClassID
     * @param Name
     * @param ID
     * @param Type
     * @param Class
     * @param IsHidden
     * @param Width
     */
    public Column(String ColumnType, int ObjectID, String ClassID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width) {
        this.ColumnType = ColumnType;
        this.ObjectID = ObjectID;
        this.ClassID = ClassID;
        this.Name = Name;
        this.ID = ID;
        this.Type = Type;
        this.Class = Class;
        this.IsHidden = IsHidden;
        this.Width = Width;
    }

    /**
     *
     * @return
     */
    public int getObjectID() {
        return this.ObjectID;
    }

    /**
     *
     * @return
     */
    public String getClassID() {
        return this.ClassID;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.Name;
    }

    /**
     *
     * @return
     */
    public String getID() {
        return this.ID;
    }

    /**
     *
     * @return
     */
    public int getType() {
        return this.Type;
    }

    /**
     *
     * @return
     */
    public int getClassAtr() {
        return this.Class;
    }

    /**
     *
     * @return
     */
    public boolean getIsHidden() {
        return this.IsHidden;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return this.ObjectID;
    }

    @Override
    public String toXML() {
        return this.toXML(null);
    }

    @Override
    public String toXML(@Null AddPropertiesXML properties) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t\t\t\t\t<" + this.ColumnType + " ObjectID=\"" + this.ObjectID + "\" ClassID=\"" + this.ClassID + "\" Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t\t\t<Column.Name>" + this.Name + "</Column.Name>\n");
        stringBuilder.append("\t\t\t\t\t\t<Column.ID>" + this.ID + "</Column.ID>\n");
        stringBuilder.append("\t\t\t\t\t\t<Column.Type>" + this.Type + "</Column.Type>\n");
        stringBuilder.append("\t\t\t\t\t\t<Column.Class>" + this.Class + "</Column.Class>\n");
        stringBuilder.append("\t\t\t\t\t\t<Column.IsHidden>" + this.IsHidden + "</Column.IsHidden>\n");
        stringBuilder.append("\t\t\t\t\t\t<Column.Width>" + this.Width + "</Column.Width>\n");

        if (properties != null) {
            stringBuilder.append(properties.addXMLProperties());
        }

        stringBuilder.append("\t\t\t\t\t</" + this.ColumnType + ">\n");

        return stringBuilder.toString();
    }
}
