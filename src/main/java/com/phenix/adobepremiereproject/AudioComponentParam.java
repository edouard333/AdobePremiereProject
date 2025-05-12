package com.phenix.adobepremiereproject;

import com.phenix.adobepremiereproject.internal.XMLSimpleConvertible;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class AudioComponentParam implements XMLSimpleConvertible {

    /**
     *
     */
    private int ObjectID;

    /**
     *
     */
    private final String ClassID;

    /**
     *
     */
    private final int UpperBound;

    /**
     *
     */
    private final String Name;

    /**
     *
     */
    private final String UnitsString;

    /**
     *
     */
    private final int ParameterControlType;

    /**
     *
     */
    private final long Timestamp;

    /**
     *
     * @param ObjectID
     * @param ClassID
     * @param UpperBound
     * @param Name
     * @param UnitsString
     * @param ParameterControlType
     * @param Timestamp
     */
    public AudioComponentParam(int ObjectID, String ClassID, int UpperBound, String Name, String UnitsString, int ParameterControlType, long Timestamp) {
        this.ObjectID = ObjectID;
        this.ClassID = ClassID;
        this.UpperBound = UpperBound;
        this.Name = Name;
        this.UnitsString = UnitsString;
        this.ParameterControlType = ParameterControlType;
        this.Timestamp = Timestamp;
    }

    @Override
    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t<AudioComponentParam ObjectID=\"" + (this.ObjectID++) + "\" ClassID=\"" + this.ClassID + "\" Version=\"9\">\n");

        if (this.ClassID.equals("a714635e-a628-4b27-9d59-77eba47dbc1a")) {
            stringBuilder.append("\t	<UpperBound>" + this.UpperBound + "</UpperBound>\n");
        }

        stringBuilder.append("\t	<Name>" + this.Name + "</Name>\n");

        if (this.ClassID.equals("a714635e-a628-4b27-9d59-77eba47dbc1a")) {
            stringBuilder.append("\t	<UnitsString>" + this.UnitsString + "</UnitsString>\n");
        }

        stringBuilder.append("\t	<ParameterControlType>" + this.ParameterControlType + "</ParameterControlType>\n");
        stringBuilder.append("\t	<Timestamp>" + this.Timestamp + "</Timestamp>\n");
        stringBuilder.append("\t</AudioComponentParam>\n");

        return stringBuilder.toString();
    }
}
