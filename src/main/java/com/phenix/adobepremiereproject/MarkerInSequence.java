package com.phenix.adobepremiereproject;

import com.phenix.timecode.Timecode;
import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class MarkerInSequence {

    /**
     *
     */
    private Marker marker;

    /**
     *
     */
    private Timecode timecode;

    /**
     *
     * @param marker
     * @param timecode
     */
    public MarkerInSequence(Marker marker, Timecode timecode) {
        this.marker = marker;
        this.timecode = timecode;
    }

    /**
     *
     * @param file
     */
    public void toXML(PrintWriter file) {
        file.append("<Marker ObjectID=\"105\" ClassID=\"a45508e0-3ff7-4d04-90a7-2e0dfff4c910\" Version=\"3\">\n");
        file.append("<DVAMarker>{\"DVAMarker\":{");
        file.append("\"mCuePointList\":[");
        file.append("{\"mKey\":\"marker_guid\",");
        file.append("\"mValue\":\"6eedad83-545f-4a02-9933-a712097aa118\"},{\"mKey\":\"keywordExtDVAv1_606b0a42-ac82-4040-943d-ac26dff2d0db\",");
        file.append("\"mValue\":\"{\\\"color\\\":" + this.marker.getColor().getValue() + "}\"}],");
        file.append("\"mComment\":\"" + this.marker.getComment() + "\",");
        file.append("\"mMarkerID\":\"f36102e3-bae8-44bb-b7fc-3d69a7b4cf3e\",");
        file.append("\"mName\":\"" + this.marker.getName() + "\",\"mStartTime\":{\"ticks\":" + this.timecode.toImage() + "},\"mType\":\"Comment\"}}</DVAMarker>\n");
        file.append("</Marker>\n");
    }
}
