package com.phenix.adobepremiereproject;

import com.phenix.adobepremiereproject.internal.XMLSimpleConvertible;
import com.phenix.timecode.Timecode;

/**
 * Un marqueur de séquence.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class MarkerInSequence implements XMLSimpleConvertible {

    /**
     * Le marqueur.
     */
    private Marker marker;

    /**
     * Le timecode du marqueur.
     */
    private Timecode timecode;

    /**
     * Initialise un marqueur d'une séquence.
     *
     * @param marker Le marqueur.
     * @param timecode Le timecode.
     */
    public MarkerInSequence(Marker marker, Timecode timecode) {
        this.marker = marker;
        this.timecode = timecode;
    }

    @Override
    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<Marker ObjectID=\"105\" ClassID=\"a45508e0-3ff7-4d04-90a7-2e0dfff4c910\" Version=\"3\">\n");
        stringBuilder.append("<DVAMarker>{\"DVAMarker\":{");
        stringBuilder.append("\"mCuePointList\":[");
        stringBuilder.append("{\"mKey\":\"marker_guid\",");
        stringBuilder.append("\"mValue\":\"6eedad83-545f-4a02-9933-a712097aa118\"},{\"mKey\":\"keywordExtDVAv1_606b0a42-ac82-4040-943d-ac26dff2d0db\",");
        stringBuilder.append("\"mValue\":\"{\\\"color\\\":" + this.marker.getColor().toAdobeProject() + "}\"}],");
        stringBuilder.append("\"mComment\":\"" + this.marker.getComment() + "\",");
        stringBuilder.append("\"mMarkerID\":\"f36102e3-bae8-44bb-b7fc-3d69a7b4cf3e\",");
        stringBuilder.append("\"mName\":\"" + this.marker.getName() + "\",\"mStartTime\":{\"ticks\":" + this.timecode.toImage() + "},\"mType\":\"Comment\"}}</DVAMarker>\n");
        stringBuilder.append("</Marker>\n");

        return stringBuilder.toString();
    }
}
