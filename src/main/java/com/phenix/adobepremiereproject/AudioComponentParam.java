package com.phenix.adobepremiereproject;

import com.phenix.adobepremiereproject.internal.XMLSimpleConvertible;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class AudioComponentParam implements XMLSimpleConvertible {

    /**
     * L'id de l'instance.
     */
    private int objectID;

    /**
     * L'id du type..
     */
    private final String classID;

    /**
     *
     */
    private final int upperBound;

    /**
     * Le nom.
     */
    private final String name;

    /**
     *
     */
    private final String unitsString;

    /**
     *
     */
    private final int parameterControlType;

    /**
     *
     */
    private final long timestamp;

    /**
     *
     * @param objectID
     * @param classID
     * @param upperBound
     * @param name
     * @param unitsString
     * @param parameterControlType
     * @param timestamp
     */
    public AudioComponentParam(int objectID, String classID, int upperBound, String name, String unitsString, int parameterControlType, long timestamp) {
        this.objectID = objectID;
        this.classID = classID;
        this.upperBound = upperBound;
        this.name = name;
        this.unitsString = unitsString;
        this.parameterControlType = parameterControlType;
        this.timestamp = timestamp;
    }

    @Override
    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t<AudioComponentParam ObjectID=\"" + (this.objectID++) + "\" ClassID=\"" + this.classID + "\" Version=\"9\">\n");

        if (this.classID.equals("a714635e-a628-4b27-9d59-77eba47dbc1a")) {
            stringBuilder.append("\t	<UpperBound>" + this.upperBound + "</UpperBound>\n");
        }

        stringBuilder.append("\t	<Name>" + this.name + "</Name>\n");

        if (this.classID.equals("a714635e-a628-4b27-9d59-77eba47dbc1a")) {
            stringBuilder.append("\t	<UnitsString>" + this.unitsString + "</UnitsString>\n");
        }

        stringBuilder.append("\t	<ParameterControlType>" + this.parameterControlType + "</ParameterControlType>\n");
        stringBuilder.append("\t	<Timestamp>" + this.timestamp + "</Timestamp>\n");
        stringBuilder.append("\t</AudioComponentParam>\n");

        return stringBuilder.toString();
    }
}
