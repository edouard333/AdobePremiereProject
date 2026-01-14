package com.phenix.adobepremiereproject.column;

import com.phenix.adobepremiereproject.internal.XMLSimpleConvertible;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class SelectedItemsColumn extends Column implements XMLSimpleConvertible {

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
    public SelectedItemsColumn(int objectID, String name, String id, int type, int class_, boolean isHidden, int width) {
        super("SelectedItemsColumn", objectID, "88bcfb15-97a7-49ed-ac05-7d3ce637d2a0", name, id, type, class_, isHidden, width);
    }

    @Override
    public String toXML() {
        return super.toXML();
    }
}
