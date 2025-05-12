package com.phenix.adobepremiereproject.column;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class TimecodeColumn extends Column {

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
    public TimecodeColumn(int ObjectID, String Name, String ID, int Type, int Class, boolean IsHidden, int Width) {
        super("TimecodeColumn", ObjectID, "9c9279d2-355c-487b-b644-0698b42e32f9", Name, ID, Type, Class, IsHidden, Width);
    }

    @Override
    public String toXML() {
        return super.toXML();
    }
}
