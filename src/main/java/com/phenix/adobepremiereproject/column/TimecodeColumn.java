package com.phenix.adobepremiereproject.column;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class TimecodeColumn extends Column {

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
    public TimecodeColumn(int objectID, String name, String id, int type, int class_, boolean isHidden, int width) {
        super("TimecodeColumn", objectID, "9c9279d2-355c-487b-b644-0698b42e32f9", name, id, type, class_, isHidden, width);
    }

    @Override
    public String toXML() {
        return super.toXML();
    }
}
