package com.phenix.adobepremiereproject;

import com.phenix.adobepremiereproject.adobetitle.AdobeTitle;
import com.phenix.adobepremiereproject.adobetitle.Text;
import java.io.PrintWriter;

/**
 * Titrage dans Adobe Premiere.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Title extends ElementInSequence {

    private int id;

    /**
     * Les données du titre.
     */
    private AdobeTitle adobeTitle;

    /**
     * Nombre de séquence qu'il y a dans le projet.
     */
    private static int nombre_title = 1;

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
        super(parent, name, Sequence.TITLE);

        adobeTitle = new AdobeTitle();

        nombre_title++;
    }

    /**
     *
     * @return
     */
    public static int getTitleNumber() {
        return nombre_title;
    }

    @Override
    public void toXML(PrintWriter file, int order) {
        file.append("    <ClipProjectItem ObjectUID=\"" + this.current_ObjectURef + "\" ClassID=\"" + this.classID + "\" Version=\"1\">\n");
        file.append("		<ProjectItem Version=\"1\">\n");
        file.append("			<Node Version=\"1\">\n");
        file.append("				<Properties Version=\"1\">\n");
        file.append("					<Column.PropertyText.Label>BE.Prefs.LabelColors.3</Column.PropertyText.Label>\n");
        file.append("					<project.icon.view.grid.order>" + order + "</project.icon.view.grid.order>\n");
        file.append("				</Properties>\n");
        file.append("			</Node>\n");
        file.append("			<Name>TITRE</Name>\n");
        file.append("		</ProjectItem>\n");
        file.append("		<MasterClip ObjectURef=\"8c85bb49-dcaf-4511-aed8-9f6cead61d2a\"/>\n");
        file.append("	</ClipProjectItem>\n");
    }

    /**
     * XML qu'il y a dans une séquence.
     *
     * @param file
     */
    public void clipLoggingInfo(PrintWriter file) {
        file.append("\t<ClipLoggingInfo ObjectID=\"45\" ClassID=\"77ab7fdd-dcdf-465d-9906-7a330ca1e738\" Version=\"7\">\n");
        file.append("\t	<CaptureMode>2</CaptureMode>\n");
        file.append("\t	<ClipName>TITRE</ClipName>\n");
        file.append("\t	<MediaFrameRate>10584000000</MediaFrameRate>\n");
        file.append("\t	<TimecodeFormat>100</TimecodeFormat>\n");
        file.append("\t</ClipLoggingInfo>\n");
        file.append("\t<VideoClip ObjectID=\"46\" ClassID=\"9308dbef-2440-4acb-9ab2-953b9a4e82ec\" Version=\"11\">\n");
        file.append("\t	<Clip Version=\"18\">\n");
        file.append("\t		<Node Version=\"1\">\n");
        file.append("\t			<Properties Version=\"1\">\n");
        file.append("\t				<BE.Prefs.SyntheticMedia.DefaultIsDropFrame>false</BE.Prefs.SyntheticMedia.DefaultIsDropFrame>\n");
        file.append("\t				<asl.clip.label.color>14910691</asl.clip.label.color>\n");
        file.append("\t				<asl.clip.label.name>BE.Prefs.LabelColors.3</asl.clip.label.name>\n");
        file.append("\t			</Properties>\n");
        file.append("\t		</Node>\n");
        file.append("\t		<Source ObjectRef=\"83\"/>\n");
        file.append("\t		<ClipID>d55680c2-20d1-4bb5-b02a-c27be5413715</ClipID>\n");
        file.append("\t		<InPoint>0</InPoint>\n");
        file.append("\t		<OutPoint>1270080000000</OutPoint>\n");
        file.append("\t		<InUse>false</InUse>\n");
        file.append("\t	</Clip>\n");
        file.append("\t</VideoClip>\n");
        file.append("\t<ClipChannelGroupVectorSerializer ObjectID=\"47\" ClassID=\"a3127a8c-95d4-456e-a7f5-171b3f922426\" Version=\"1\">\n");
        file.append("\t</ClipChannelGroupVectorSerializer>\n");
    }

    //@Override
    public void videoMediaSource(PrintWriter file) {
        file.append("\t<VideoMediaSource ObjectID=\"83\" ClassID=\"e64ddf74-8fac-4682-8aa8-0e0ca2248949\" Version=\"2\">\n");
        file.append("\t\t<MediaSource Version=\"3\">\n");
        file.append("\t\t\t<Content Version=\"10\">\n");
        file.append("\t\t\t</Content>\n");
        file.append("\t\t\t<Media ObjectURef=\"285b90b1-ba3a-48f2-9807-34d474e53f16\"/>\n");
        file.append("\t\t</MediaSource>\n");
        file.append("\t\t<OriginalDuration>10973491200000000</OriginalDuration>\n");
        file.append("\t</VideoMediaSource>\n");
    }

    @Override
    public void media(PrintWriter file) {
        file.append("\t<Media ObjectUID=\"285b90b1-ba3a-48f2-9807-34d474e53f16\" ClassID=\"7a5c103e-f3ac-4391-b6b4-7cc3d2f9a7ff\" Version=\"26\">\n");
        file.append("\t	<VideoStream ObjectRef=\"102\"/>\n");
        try {
            file.append("\t	<ImporterPrefs Encoding=\"base64\" BinaryHash=\"08750ff2-29d4-c8a2-ee5f-bb8400000e04\">" + adobeTitle.toXML() + "\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        file.append("\t	</ImporterPrefs>\n");
        file.append("\t	<FilePath>1414091852</FilePath>\n");
        file.append("\t	<Infinite>true</Infinite>\n");
        file.append("\t	<ImplementationID>ad0be7ce-e015-4877-9777-93c98f30dcac</ImplementationID>\n");
        file.append("\t	<Title>TITRE</Title>\n");
        file.append("\t	<ActualMediaFilePath>1414091852</ActualMediaFilePath>\n");
        file.append("\t	<ContentAndMetadataState>00000000-0000-0000-0000-000000000000</ContentAndMetadataState>\n");
        file.append("\t	<ConformedAudioRate>9223372036854775807</ConformedAudioRate>\n");
        file.append("\t</Media>\n");
    }

    /**
     * Ajout d'un text.
     *
     * @param text
     */
    public void addText(Text text) {
        adobeTitle.texts.add(text);
    }

    @Override
    void inSequence(PrintWriter file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
