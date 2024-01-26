package com.phenix.adobepremiereproject.column;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Column {

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

    /**
     * 
     * @param file 
     */
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
