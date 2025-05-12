package com.phenix.adobepremiereproject.column;

import com.phenix.adobepremiereproject.internal.XMLSimpleConvertible;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class SelectedItemsColumn extends Column implements XMLSimpleConvertible {

    /**
     *
     * @param ObjectID
     * @param Name
     * @param ID
     * @param Type
     * @param Class
     * @param IsHidden
     * @param Width
     */
    public SelectedItemsColumn(int ObjectID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width) {
        super("SelectedItemsColumn", ObjectID, "88bcfb15-97a7-49ed-ac05-7d3ce637d2a0", Name, ID, Type, Class, IsHidden, Width);
    }

    @Override
    public String toXML() {
        return super.toXML();
    }
}
