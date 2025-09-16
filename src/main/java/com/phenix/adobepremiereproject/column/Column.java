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
    private final String columnType;

    /**
     *
     */
    private final int objectID;

    /**
     *
     */
    private final String classID;

    /**
     *
     */
    private final String name;

    /**
     *
     */
    private final String id;

    /**
     *
     */
    private final int type;

    /**
     *
     */
    private final int class_;

    /**
     *
     */
    private final boolean isHidden;

    /**
     *
     */
    private final int width;

    /**
     *
     * @param columnType
     * @param objectID
     * @param classID
     * @param name
     * @param id
     * @param type
     * @param class_
     * @param isHidden
     * @param width
     */
    public Column(String columnType, int objectID, String classID, String name, String id, int type, int class_, boolean isHidden, int width) {
        this.columnType = columnType;
        this.objectID = objectID;
        this.classID = classID;
        this.name = name;
        this.id = id;
        this.type = type;
        this.class_ = class_;
        this.isHidden = isHidden;
        this.width = width;
    }

    /**
     *
     * @return
     */
    public int getObjectID() {
        return this.objectID;
    }

    /**
     *
     * @return
     */
    public String getClassID() {
        return this.classID;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return
     */
    public String getID() {
        return this.id;
    }

    /**
     *
     * @return
     */
    public int getType() {
        return this.type;
    }

    /**
     *
     * @return
     */
    public int getClassAtr() {
        return this.class_;
    }

    /**
     *
     * @return
     */
    public boolean getIsHidden() {
        return this.isHidden;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return this.objectID;
    }

    @Override
    public String toXML() {
        return this.toXML(null);
    }

    @Override
    public String toXML(@Null AddPropertiesXML properties) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t\t\t\t\t<" + this.columnType + " ObjectID=\"" + this.objectID + "\" ClassID=\"" + this.classID + "\" Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t\t\t<Column.Name>" + this.name + "</Column.Name>\n");
        stringBuilder.append("\t\t\t\t\t\t<Column.ID>" + this.id + "</Column.ID>\n");
        stringBuilder.append("\t\t\t\t\t\t<Column.Type>" + this.type + "</Column.Type>\n");
        stringBuilder.append("\t\t\t\t\t\t<Column.Class>" + this.class_ + "</Column.Class>\n");
        stringBuilder.append("\t\t\t\t\t\t<Column.IsHidden>" + this.isHidden + "</Column.IsHidden>\n");
        stringBuilder.append("\t\t\t\t\t\t<Column.Width>" + this.width + "</Column.Width>\n");

        if (properties != null) {
            stringBuilder.append(properties.addXMLProperties());
        }

        stringBuilder.append("\t\t\t\t\t</" + this.columnType + ">\n");

        return stringBuilder.toString();
    }
}
