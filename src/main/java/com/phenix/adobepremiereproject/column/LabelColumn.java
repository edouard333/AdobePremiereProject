package com.phenix.adobepremiereproject.column;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class LabelColumn extends Column {

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
    public LabelColumn(int objectID, String name, String id, int type, int class_, boolean isHidden, int width) {
        super("LabelColumn", objectID, "0b8cc011-65dd-4b47-aad9-751ca2891f4a", name, id, type, class_, isHidden, width);
    }

    @Override
    public String toXML() {
        return super.toXML();
    }
}
