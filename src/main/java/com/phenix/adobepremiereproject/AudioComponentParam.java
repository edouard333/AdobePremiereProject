package com.phenix.adobepremiereproject;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class AudioComponentParam {

    /**
     *
     */
    private int ObjectID;

    /**
     *
     */
    private String ClassID;

    /**
     *
     */
    private int UpperBound;

    /**
     *
     */
    private String Name;

    /**
     *
     */
    private String UnitsString;

    /**
     *
     */
    private int ParameterControlType;

    /**
     *
     */
    private long Timestamp;

    public AudioComponentParam(int ObjectID, String ClassID, int UpperBound, String Name, String UnitsString, int ParameterControlType, long Timestamp) {
        this.ObjectID = ObjectID;
        this.ClassID = ClassID;
        this.UpperBound = UpperBound;
        this.Name = Name;
        this.UnitsString = UnitsString;
        this.ParameterControlType = ParameterControlType;
        this.Timestamp = Timestamp;
    }

    public void toXML(PrintWriter file) {
        file.append("\t<AudioComponentParam ObjectID=\"" + (this.ObjectID++) + "\" ClassID=\"" + this.ClassID + "\" Version=\"9\">\n");

        if (this.ClassID.equals("a714635e-a628-4b27-9d59-77eba47dbc1a")) {
            file.append("\t	<UpperBound>" + this.UpperBound + "</UpperBound>\n");
        }

        file.append("\t	<Name>" + this.Name + "</Name>\n");

        if (this.ClassID.equals("a714635e-a628-4b27-9d59-77eba47dbc1a")) {
            file.append("\t	<UnitsString>" + this.UnitsString + "</UnitsString>\n");
        }

        file.append("\t	<ParameterControlType>" + this.ParameterControlType + "</ParameterControlType>\n");
        file.append("\t	<Timestamp>" + this.Timestamp + "</Timestamp>\n");
        file.append("\t</AudioComponentParam>\n");
    }

}
