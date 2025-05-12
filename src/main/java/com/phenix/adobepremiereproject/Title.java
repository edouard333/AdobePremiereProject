package com.phenix.adobepremiereproject;

import com.phenix.adobepremiereproject.adobetitle.AdobeTitle;
import com.phenix.adobepremiereproject.adobetitle.Text;
import com.phenix.timecode.Timecode;

/**
 * Titrage dans Adobe Premiere.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class Title extends ElementInSequence {

    /**
     *
     */
    private int id;

    /**
     * Les données du titre.
     */
    private AdobeTitle adobeTitle;

    /**
     * Nombre de séquences qu'il y a dans le projet.
     */
    private static int nombreTitle = 1;

    /**
     *
     */
    private Timecode duree;

    /**
     *
     * @param name
     */
    public Title(String name) {
        this(null, name);
    }

    /**
     *
     * @param parent
     * @param name
     */
    public Title(Folder parent, String name) {
        super(parent, name, TypeElement.TITLE);

        adobeTitle = new AdobeTitle();

        nombreTitle++;
    }

    /**
     * Ajout d'un text.
     *
     * @param text
     */
    public void addText(Text text) {
        adobeTitle.texts.add(text);
    }

    /**
     * XML qu'il y a dans une séquence.
     *
     * @return
     */
    public String toXMLClipLoggingInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t<ClipLoggingInfo ObjectID=\"45\" ClassID=\"77ab7fdd-dcdf-465d-9906-7a330ca1e738\" Version=\"7\">\n");
        stringBuilder.append("\t	<CaptureMode>2</CaptureMode>\n");
        stringBuilder.append("\t	<ClipName>TITRE</ClipName>\n");
        stringBuilder.append("\t	<MediaFrameRate>10584000000</MediaFrameRate>\n");
        stringBuilder.append("\t	<TimecodeFormat>100</TimecodeFormat>\n");
        stringBuilder.append("\t</ClipLoggingInfo>\n");
        stringBuilder.append("\t<VideoClip ObjectID=\"46\" ClassID=\"9308dbef-2440-4acb-9ab2-953b9a4e82ec\" Version=\"11\">\n");
        stringBuilder.append("\t	<Clip Version=\"18\">\n");
        stringBuilder.append("\t		<Node Version=\"1\">\n");
        stringBuilder.append("\t			<Properties Version=\"1\">\n");
        stringBuilder.append("\t				<BE.Prefs.SyntheticMedia.DefaultIsDropFrame>false</BE.Prefs.SyntheticMedia.DefaultIsDropFrame>\n");
        stringBuilder.append("\t				<asl.clip.label.color>14910691</asl.clip.label.color>\n");
        stringBuilder.append("\t				<asl.clip.label.name>BE.Prefs.LabelColors.3</asl.clip.label.name>\n");
        stringBuilder.append("\t			</Properties>\n");
        stringBuilder.append("\t		</Node>\n");
        stringBuilder.append("\t		<Source ObjectRef=\"83\"/>\n");
        stringBuilder.append("\t		<ClipID>d55680c2-20d1-4bb5-b02a-c27be5413715</ClipID>\n");
        stringBuilder.append("\t		<InPoint>0</InPoint>\n");
        stringBuilder.append("\t		<OutPoint>1270080000000</OutPoint>\n");
        stringBuilder.append("\t		<InUse>false</InUse>\n");
        stringBuilder.append("\t	</Clip>\n");
        stringBuilder.append("\t</VideoClip>\n");
        stringBuilder.append("\t<ClipChannelGroupVectorSerializer ObjectID=\"47\" ClassID=\"a3127a8c-95d4-456e-a7f5-171b3f922426\" Version=\"1\">\n");
        stringBuilder.append("\t</ClipChannelGroupVectorSerializer>\n");

        return stringBuilder.toString();
    }

    /**
     *
     * @return
     */
    public static int getTitleNumber() {
        return nombreTitle;
    }

    @Override
    public String toXMLinSequence() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toXMLMedia() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t<Media ObjectUID=\"285b90b1-ba3a-48f2-9807-34d474e53f16\" ClassID=\"7a5c103e-f3ac-4391-b6b4-7cc3d2f9a7ff\" Version=\"26\">\n");
        stringBuilder.append("\t	<VideoStream ObjectRef=\"102\"/>\n");

        try {
            stringBuilder.append("\t	<ImporterPrefs Encoding=\"base64\" BinaryHash=\"08750ff2-29d4-c8a2-ee5f-bb8400000e04\">" + this.adobeTitle.toXML() + "\n");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        stringBuilder.append("\t	</ImporterPrefs>\n");
        stringBuilder.append("\t	<FilePath>1414091852</FilePath>\n");
        stringBuilder.append("\t	<Infinite>true</Infinite>\n");
        stringBuilder.append("\t	<ImplementationID>ad0be7ce-e015-4877-9777-93c98f30dcac</ImplementationID>\n");
        stringBuilder.append("\t	<Title>TITRE</Title>\n");
        stringBuilder.append("\t	<ActualMediaFilePath>1414091852</ActualMediaFilePath>\n");
        stringBuilder.append("\t	<ContentAndMetadataState>00000000-0000-0000-0000-000000000000</ContentAndMetadataState>\n");
        stringBuilder.append("\t	<ConformedAudioRate>9223372036854775807</ConformedAudioRate>\n");
        stringBuilder.append("\t</Media>\n");

        return stringBuilder.toString();
    }

    /**
     * Définit la durée du titre.
     *
     * @param duree
     */
    public void setDuree(Timecode duree) {
        this.duree = duree;
    }

    @Override
    public String toXML(int order) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("    <ClipProjectItem ObjectUID=\"" + this.current_ObjectURef + "\" ClassID=\"" + this.classID + "\" Version=\"1\">\n");
        stringBuilder.append("		<ProjectItem Version=\"1\">\n");
        stringBuilder.append("			<Node Version=\"1\">\n");
        stringBuilder.append("				<Properties Version=\"1\">\n");
        stringBuilder.append("					<Column.PropertyText.Label>BE.Prefs.LabelColors.3</Column.PropertyText.Label>\n");
        stringBuilder.append("					<project.icon.view.grid.order>" + order + "</project.icon.view.grid.order>\n");
        stringBuilder.append("				</Properties>\n");
        stringBuilder.append("			</Node>\n");
        stringBuilder.append("			<Name>TITRE</Name>\n");
        stringBuilder.append("		</ProjectItem>\n");
        stringBuilder.append("		<MasterClip ObjectURef=\"8c85bb49-dcaf-4511-aed8-9f6cead61d2a\"/>\n");
        stringBuilder.append("	</ClipProjectItem>\n");

        return stringBuilder.toString();
    }

    /**
     *
     * @return
     */
    //@Override
    public String toXMLVideoMediaSource() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t<VideoMediaSource ObjectID=\"83\" ClassID=\"e64ddf74-8fac-4682-8aa8-0e0ca2248949\" Version=\"2\">\n");
        stringBuilder.append("\t\t<MediaSource Version=\"3\">\n");
        stringBuilder.append("\t\t\t<Content Version=\"10\">\n");
        stringBuilder.append("\t\t\t</Content>\n");
        stringBuilder.append("\t\t\t<Media ObjectURef=\"285b90b1-ba3a-48f2-9807-34d474e53f16\"/>\n");
        stringBuilder.append("\t\t</MediaSource>\n");
        stringBuilder.append("\t\t<OriginalDuration>10973491200000000</OriginalDuration>\n");
        stringBuilder.append("\t</VideoMediaSource>\n");

        return stringBuilder.toString();
    }
}
