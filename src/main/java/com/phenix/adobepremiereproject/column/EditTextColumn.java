package com.phenix.adobepremiereproject.column;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class EditTextColumn extends Column {

    /**
     *
     * @param objectID
     * @param name
     * @param id
     * @param type
     * @param class_
     * @param isHidden
     * @param width
     */
    public EditTextColumn(int objectID, String name, String id, int type, int class_, boolean isHidden, int width) {
        super("EditTextColumn", objectID, "e9f21f9a-b686-440c-83f4-da1685c160ad", name, id, type, class_, isHidden, width);
    }

    @Override
    public String toXML() {
        return super.toXML();
    }
}
