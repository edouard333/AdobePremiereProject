package com.phenix.adobepremiereproject.column;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class BoolPropertyColumn extends Column {

    /**
     *
     */
    private final String propertyBool;

    /**
     *
     */
    private final String editableKey;

    /**
     * Initialise les valeur.
     *
     * @param objectID
     * @param name
     * @param id
     * @param type
     * @param class_
     * @param isHidden
     * @param width
     * @param propertyBool
     * @param editableKey
     */
    public BoolPropertyColumn(int objectID, String name, String id, int type, int class_, boolean isHidden, int width, String propertyBool, String editableKey) {
        super("BoolPropertyColumn", objectID, "1d4dd772-4985-4f43-874a-84b2b566e724", name, id, type, class_, isHidden, width);
        this.propertyBool = propertyBool;
        this.editableKey = editableKey;
    }

    @Override
    public String toXML() {
        return super.toXML(() -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\t\t\t\t\t\t<Column.Property.Key>" + this.propertyBool + "</Column.Property.Key>\n");
            stringBuilder.append("\t\t\t\t\t\t<Column.Editable.Key>" + this.editableKey + "</Column.Editable.Key>\n");

            return stringBuilder.toString();
        });
    }
}
