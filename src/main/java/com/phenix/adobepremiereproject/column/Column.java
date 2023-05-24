package com.phenix.adobepremiereproject.column;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Column {

    private final String ColumnType;

    private final int ObjectID;

    private final String ClassID;

    private final String Name;

    private final String ID;

    private final int Type;

    private final int Class;

    private final boolean IsHidden;

    private final int Width;

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

    public int getObjectID() {
        return this.ObjectID;
    }

    public String getClassID() {
        return this.ClassID;
    }

    public String getName() {
        return this.Name;
    }

    public String getID() {
        return this.ID;
    }

    public int getType() {
        return this.Type;
    }

    public int getClassAtr() {
        return this.Class;
    }

    public boolean getIsHidden() {
        return this.IsHidden;
    }

    public int getWidth() {
        return this.ObjectID;
    }

    protected void toXML(PrintWriter file) {
        this.toXML(file, null);
    }

    /**
     *
     * @param file
     * @param properties
     */
    protected void toXML(PrintWriter file, PropertiesAdd properties) {
        file.append("\t\t\t\t\t<" + this.ColumnType + " ObjectID=\"" + this.ObjectID + "\" ClassID=\"" + this.ClassID + "\" Version=\"1\">\n");
        file.append("\t\t\t\t\t\t<Column.Name>" + this.Name + "</Column.Name>\n");
        file.append("\t\t\t\t\t\t<Column.ID>" + this.ID + "</Column.ID>\n");
        file.append("\t\t\t\t\t\t<Column.Type>" + this.Type + "</Column.Type>\n");
        file.append("\t\t\t\t\t\t<Column.Class>" + this.Class + "</Column.Class>\n");
        file.append("\t\t\t\t\t\t<Column.IsHidden>" + this.IsHidden + "</Column.IsHidden>\n");
        file.append("\t\t\t\t\t\t<Column.Width>" + this.Width + "</Column.Width>\n");

        if (properties != null) {
            properties.addProperties(file);
        }
        file.append("\t\t\t\t\t</" + this.ColumnType + ">\n");
    }
}
