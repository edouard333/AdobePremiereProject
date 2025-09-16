package com.phenix.adobepremiereproject.column;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class NameColumn extends Column {

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
    public NameColumn(int objectID, String name, String id, int type, int class_, boolean isHidden, int width) {
        super("NameColumn", objectID, "0547b302-c849-46b3-ae2a-b245e9dd59eb", name, id, type, class_, isHidden, width);
    }

    @Override
    public String toXML() {
        return super.toXML();
    }
}
