package com.phenix.adobepremiereproject.column;

import com.phenix.adobepremiereproject.internal.XMLSimpleConvertible;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class StringColumn extends Column implements XMLSimpleConvertible {

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
    public StringColumn(int objectID, String name, String id, int type, int class_, boolean isHidden, int width) {
        super("StringColumn", objectID, "f0ef302d-babc-4f75-9975-923a8ca28d7e", name, id, type, class_, isHidden, width);
    }

    @Override
    public String toXML() {
        return super.toXML();
    }
}
