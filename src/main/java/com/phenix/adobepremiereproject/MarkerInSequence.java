package com.phenix.adobepremiereproject;

import com.phenix.timecode.Timecode;
import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class MarkerInSequence {

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
     * @param writer
     */
    public void toXML(PrintWriter writer) {
        writer.append("<Marker ObjectID=\"105\" ClassID=\"a45508e0-3ff7-4d04-90a7-2e0dfff4c910\" Version=\"3\">\n");
        writer.append("<DVAMarker>{\"DVAMarker\":{");
        writer.append("\"mCuePointList\":[");
        writer.append("{\"mKey\":\"marker_guid\",");
        writer.append("\"mValue\":\"6eedad83-545f-4a02-9933-a712097aa118\"},{\"mKey\":\"keywordExtDVAv1_606b0a42-ac82-4040-943d-ac26dff2d0db\",");
        writer.append("\"mValue\":\"{\\\"color\\\":" + this.marker.getColor().getValue() + "}\"}],");
        writer.append("\"mComment\":\"" + this.marker.getComment() + "\",");
        writer.append("\"mMarkerID\":\"f36102e3-bae8-44bb-b7fc-3d69a7b4cf3e\",");
        writer.append("\"mName\":\"" + this.marker.getName() + "\",\"mStartTime\":{\"ticks\":" + this.timecode.toImage() + "},\"mType\":\"Comment\"}}</DVAMarker>\n");
        writer.append("</Marker>\n");
    }
}
