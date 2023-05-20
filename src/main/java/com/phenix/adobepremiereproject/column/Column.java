package com.phenix.adobepremiereproject.column;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Column {

    private int ObjectID;

    private String ClassID;

    private String Name;
    private String ID;

    private int Type;
    private int Class;

    private boolean IsHidden;

    private int Width;

    public Column(int ObjectID, String ClassID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width) {
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
        toXML(file, null);
    }

    /**
     *
     * @param file
     */
    public void toXML(PrintWriter file, Class add_properties) {
        file.append("\t\t\t\t\t<LabelColumn ObjectID=\"" + this.ObjectID + "\" ClassID=\"" + this.ClassID + "\" Version=\"1\">\n");
        file.append("\t\t\t\t\t\t<Column.Name>" + this.Name + "</Column.Name>\n");
        file.append("\t\t\t\t\t\t<Column.ID>" + this.ID + "</Column.ID>\n");
        file.append("\t\t\t\t\t\t<Column.Type>" + this.Type + "</Column.Type>\n");
        file.append("\t\t\t\t\t\t<Column.Class>" + this.Class + "</Column.Class>\n");
        file.append("\t\t\t\t\t\t<Column.IsHidden>" + this.IsHidden + "</Column.IsHidden>\n");
        file.append("\t\t\t\t\t\t<Column.Width>" + this.Width + "</Column.Width>\n");

        if (add_properties != null) {
        }

        file.append("\t\t\t\t\t</LabelColumn>\n");
    }

}
