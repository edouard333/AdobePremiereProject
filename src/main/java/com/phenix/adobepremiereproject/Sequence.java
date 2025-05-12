package com.phenix.adobepremiereproject;

import com.phenix.timecode.Framerate;
import com.phenix.timecode.Timecode;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Timeline dans Adobe Première.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class Sequence extends ElementInSequence {

    /**
     *
     */
    private int id;

    /**
     *
     */
    private Timecode startTimecode;

    /**
     *
     */
    private Framerate framerate;

    /**
     * Hauteur de l'image en pixel.
     */
    private int hauteur;

    /**
     * Largeur de l'image en pixel.
     */
    private int largeur;

    /**
     * Nombre de séquences qu'il y a dans le projet.
     */
    private static int nombreSequence = 1;

    /**
     * Position dans la timeline.
     */
    private Timecode position;

    /**
     * Liste des éléments dans la séquence.
     */
    private List<ElementInSequence> listeClip;

    /**
     * Liste des marqueurs de la séquence.
     */
    private List<MarkerInSequence> listeMarqueur;

    /**
     * Identifiant des markers.
     */
    private int markersId;

    /**
     * Crée une séquence.
     *
     * @param name Nom de la séquence.
     */
    public Sequence(String name) {
        this(null, name);
    }

    /**
     * Crée une séquence.
     *
     * @param parent Dossier où se trouve la séquence.
     * @param name Nom de la séquence.
     */
    public Sequence(Folder parent, String name) {
        super(parent, name, TypeElement.SEQUENCE);

        // Initialise la liste des clips dans la séquence.
        this.listeClip = new ArrayList<ElementInSequence>();
        this.listeMarqueur = new ArrayList<MarkerInSequence>();

        nombreSequence++;
    }

    /**
     * Ajoute un élément à la séquence.
     *
     * @param elementInSequence Elément ajoutable à la séquence.
     */
    public void add(ElementInSequence elementInSequence) {
        this.add(elementInSequence, null, null, null);
    }

    /**
     * Ajoute un élément à la séquence.
     *
     * @param elementInSequence Elément ajoutable à la séquence.
     * @param tcIn Point in.
     * @param start Position dans la séquence.
     */
    public void add(ElementInSequence elementInSequence, Timecode tcIn, Timecode start) {
        this.add(elementInSequence, tcIn, null, start);
    }

    /**
     * Ajoute un élément à la séquence.
     *
     * @param elementInSequence Elément ajoutable à la séquence.
     * @param tcIn Point in.
     * @param out Point out.
     * @param start Position dans la séquence.
     */
    public void add(ElementInSequence elementInSequence, Timecode tcIn, Timecode out, Timecode start) {
        this.listeClip.add(elementInSequence);
    }

    /**
     * Ajouter un marqueur à la séquence.
     *
     * @param marqueur Le marqueur.
     * @param timecode Timecode où doit se trouver le marqueur.
     */
    public void add(Marker marqueur, Timecode timecode) {
        this.listeMarqueur.add(new MarkerInSequence(marqueur, timecode));
    }

    /**
     * Retourne le nombre de séquence qu'il existe.
     *
     * @return Nombre de séquence.
     */
    public static int getSequenceNumber() {
        return nombreSequence;
    }

    @Override
    public String toXML(int order) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t<ClipProjectItem ObjectUID=\"" + this.getCurrentObjectURef() + "\" ClassID=\"" + this.classID + "\" Version=\"1\">\n");
        stringBuilder.append("\t\t<ProjectItem Version=\"1\">\n");
        stringBuilder.append("\t\t\t<Node Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t<Properties Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t\t<Column.PropertyText.Label>BE.Prefs.LabelColors.5</Column.PropertyText.Label>\n");
        stringBuilder.append("\t\t\t\t\t<project.icon.view.grid.order>" + order + "</project.icon.view.grid.order>\n");
        stringBuilder.append("\t\t\t\t</Properties>\n");
        stringBuilder.append("\t\t\t</Node>\n");
        stringBuilder.append("\t\t\t<Name>" + this.getName() + "</Name>\n");
        stringBuilder.append("\t\t</ProjectItem>\n");
        stringBuilder.append("\t\t<MasterClip ObjectURef=\"ad5bd5cb-4336-473d-a7f2-74386fbfd563\"/>\n");
        stringBuilder.append("\t</ClipProjectItem>\n");

        return stringBuilder.toString();
    }

    /**
     *
     * @return
     */
    public String toXMLVideoClip() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t<VideoClip ObjectID=\"128\" ClassID=\"9308dbef-2440-4acb-9ab2-953b9a4e82ec\" Version=\"11\">\n");
        stringBuilder.append("\t\t<Clip Version=\"18\">\n");
        stringBuilder.append("\t\t\t<Node Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t<Properties Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t\t<BE.Prefs.SyntheticMedia.DefaultIsDropFrame>false</BE.Prefs.SyntheticMedia.DefaultIsDropFrame>\n");
        stringBuilder.append("\t\t\t\t\t<asl.clip.label.color>14910691</asl.clip.label.color>\n");
        stringBuilder.append("\t\t\t\t\t<asl.clip.label.name>BE.Prefs.LabelColors.3</asl.clip.label.name>\n");
        stringBuilder.append("\t\t\t\t</Properties>\n");
        stringBuilder.append("\t\t\t</Node>\n");
        stringBuilder.append("\t\t\t<Source ObjectRef=\"83\"/>\n");
        stringBuilder.append("\t\t\t<ClipID>b836a17f-9a92-4647-9fbc-2b020fc10552</ClipID>\n");
        stringBuilder.append("\t\t\t<InPoint>914457600000000</InPoint>\n");
        stringBuilder.append("\t\t\t<OutPoint>915727680000000</OutPoint>\n");
        stringBuilder.append("\t\t</Clip>\n");
        stringBuilder.append("\t</VideoClip>\n");

        return stringBuilder.toString();
    }

    /**
     *
     * @return
     */
    public String toXMLClip() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t<ClipLoggingInfo ObjectID=\"40\" ClassID=\"77ab7fdd-dcdf-465d-9906-7a330ca1e738\" Version=\"7\">\n");
        stringBuilder.append("\t	<MediaFrameRate>9223372036854775807</MediaFrameRate>\n");
        stringBuilder.append("\t</ClipLoggingInfo>\n");
        stringBuilder.append("\t<AudioComponentChain ObjectID=\"41\" ClassID=\"3cb131d1-d3c0-47ae-a19a-bdf75ea11674\" Version=\"3\">\n");
        stringBuilder.append("\t	<ComponentChain Version=\"2\">\n");
        stringBuilder.append("\t		<Components Version=\"1\">\n");
        stringBuilder.append("\t		</Components>\n");
        stringBuilder.append("\t	</ComponentChain>\n");
        stringBuilder.append("\t	<ChannelType>3</ChannelType>\n");
        stringBuilder.append("\t	<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t	<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t	<AutomationMode>1</AutomationMode>\n");
        stringBuilder.append("\t	<DefaultVol>true</DefaultVol>\n");
        stringBuilder.append("\t	<DefaultVolumeComponentID>1</DefaultVolumeComponentID>\n");
        stringBuilder.append("\t	<DefaultChannelVolumeComponentID>2</DefaultChannelVolumeComponentID>\n");
        stringBuilder.append("\t</AudioComponentChain>\n");
        stringBuilder.append("\t<AudioClip ObjectID=\"42\" ClassID=\"b8830d03-de02-41ee-84ec-fe566dc70cd9\" Version=\"8\">\n");
        stringBuilder.append("\t	<Clip Version=\"18\">\n");
        stringBuilder.append("\t		<Node Version=\"1\">\n");
        stringBuilder.append("\t			<Properties Version=\"1\">\n");
        stringBuilder.append("\t				<asl.clip.label.color>5814353</asl.clip.label.color>\n");
        stringBuilder.append("\t				<asl.clip.label.name>BE.Prefs.LabelColors.5</asl.clip.label.name>\n");
        stringBuilder.append("\t			</Properties>\n");
        stringBuilder.append("\t		</Node>\n");

        int ObjectRef = 48;

        stringBuilder.append("\t		<Source ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t		<ClipID>1811dde4-f008-48c9-8fdc-3f7261120218</ClipID>\n");
        stringBuilder.append("\t		<InUse>false</InUse>\n");
        stringBuilder.append("\t	</Clip>\n");
        stringBuilder.append("\t	<SecondaryContents Version=\"1\">\n");

        for (int i = 0; i <= 31; i++) {
            stringBuilder.append("\t\t\t<SecondaryContentItem Index=\"" + i + "\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        }

        stringBuilder.append("\t	</SecondaryContents>\n");
        stringBuilder.append("\t\t<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t</AudioClip>\n");
        stringBuilder.append("\t<VideoClip ObjectID=\"43\" ClassID=\"9308dbef-2440-4acb-9ab2-953b9a4e82ec\" Version=\"11\">\n");
        stringBuilder.append("\t	<Clip Version=\"18\">\n");
        stringBuilder.append("\t		<Node Version=\"1\">\n");
        stringBuilder.append("\t			<Properties Version=\"1\">\n");
        stringBuilder.append("\t				<asl.clip.label.color>5814353</asl.clip.label.color>\n");
        stringBuilder.append("\t				<asl.clip.label.name>BE.Prefs.LabelColors.5</asl.clip.label.name>\n");
        stringBuilder.append("\t			</Properties>\n");
        stringBuilder.append("\t		</Node>\n");
        stringBuilder.append("\t		<Source ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t		<ClipID>9cfa06ef-090e-43d2-bbc5-437803e2e0d6</ClipID>\n");
        stringBuilder.append("\t		<InUse>false</InUse>\n");
        stringBuilder.append("\t	</Clip>\n");
        stringBuilder.append("\t</VideoClip>\n");
        stringBuilder.append("\t<ClipChannelGroupVectorSerializer ObjectID=\"44\" ClassID=\"a3127a8c-95d4-456e-a7f5-171b3f922426\" Version=\"1\">\n");
        stringBuilder.append("\t	<ClipChannelVectors Version=\"1\">\n");
        stringBuilder.append("\t		<ClipChannelVectorItem Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t	</ClipChannelVectors>\n");
        stringBuilder.append("\t</ClipChannelGroupVectorSerializer>\n");

        return stringBuilder.toString();
    }

    /**
     *
     * @return
     */
    public String toXMLAudioSequenceSource() {
        StringBuilder stringBuilder = new StringBuilder();

        int ObjectID = 48;
        int ObjectRef = ObjectID;

        stringBuilder.append("\t<AudioSequenceSource ObjectID=\"" + (ObjectID++) + "\" ClassID=\"e8d4cc83-38cb-491f-9d94-e5f7e3b205ee\" Version=\"7\">\n");
        stringBuilder.append("\t	<SequenceSource Version=\"4\">\n");
        stringBuilder.append("\t		<Content Version=\"10\">\n");
        stringBuilder.append("\t		</Content>\n");
        stringBuilder.append("\t		<Sequence ObjectURef=\"9d8a2607-057b-47be-8e25-56261a940524\"/>\n");
        stringBuilder.append("\t	</SequenceSource>\n");
        stringBuilder.append("\t	<OriginalDuration>0</OriginalDuration>\n");
        stringBuilder.append("\t</AudioSequenceSource>\n");

        for (int i = 0; i <= 31; i++) {
            stringBuilder.append("\t<SecondaryContent ObjectID=\"" + (ObjectID++) + "\" ClassID=\"f9d004b5-cb04-4e2f-af6f-64fadc2c4be9\" Version=\"1\">\n");
            stringBuilder.append("\t\t<Content ObjectRef=\"" + ObjectRef + "\"/>\n");
            stringBuilder.append("\t\t<ChannelIndex>" + i + "</ChannelIndex>\n");
            stringBuilder.append("\t</SecondaryContent>\n");
        }

        stringBuilder.append("\t<VideoSequenceSource ObjectID=\"" + (ObjectID++) + "\" ClassID=\"4752dfa9-7a7e-4a3b-a25b-cafde1a8d036\" Version=\"3\">\n");
        stringBuilder.append("\t	<SequenceSource Version=\"4\">\n");
        stringBuilder.append("\t		<Content Version=\"10\">\n");
        stringBuilder.append("\t		</Content>\n");
        stringBuilder.append("\t		<Sequence ObjectURef=\"9d8a2607-057b-47be-8e25-56261a940524\"/>\n");
        stringBuilder.append("\t	</SequenceSource>\n");
        stringBuilder.append("\t	<OriginalDuration>0</OriginalDuration>\n");
        stringBuilder.append("\t</VideoSequenceSource>\n");
        stringBuilder.append("\t<ClipChannelVectorSerializer ObjectID=\"" + (ObjectID++) + "\" ClassID=\"333d203b-3a53-4195-8894-fc7523ff3dc7\" Version=\"1\">\n");
        stringBuilder.append("\t\t<ClipChannels Version=\"1\">\n");

        for (int i = 0; i <= 15; i++) {
            stringBuilder.append("\t\t\t<ClipChannelItem Index=\"" + i + "\" ObjectRef=\"" + ((ObjectID++) + 1) + "\"/>\n");
        }

        stringBuilder.append("\t	</ClipChannels>\n");
        stringBuilder.append("\t	<ChannelType>3</ChannelType>\n");
        stringBuilder.append("\t</ClipChannelVectorSerializer>\n");

        return stringBuilder.toString();
    }

    /**
     * @return
     */
    public String toXMLSequence() {
        StringBuilder stringBuilder = new StringBuilder();

        long div = 0;

        switch (this.framerate) {
            case F24 ->
                div = 10584000000L;
            case F25 ->
                div = 10160640000L;
        }

        stringBuilder.append("\t<Sequence ObjectUID=\"9d8a2607-057b-47be-8e25-56261a940524\" ClassID=\"6a15d903-8739-11d5-af2d-9b7855ad8974\" Version=\"11\">\n");
        stringBuilder.append("\t\t<Node Version=\"1\">\n");
        stringBuilder.append("\t\t\t<Properties Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t<AM.TrackScrollPosition>0</AM.TrackScrollPosition>\n");
        stringBuilder.append("\t\t\t\t<AM.TrackVScrollPosition>0</AM.TrackVScrollPosition>\n");
        stringBuilder.append("\t\t\t\t<AMM.CurrentSolo>[]</AMM.CurrentSolo>\n");
        stringBuilder.append("\t\t\t\t<HSL.TimelinePatchingAndTargeting.AudioPatches706bcde2_45_736e_45_6997_45_3385_45_a59f0000001b>[{\"mNumber\":0,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0}]</HSL.TimelinePatchingAndTargeting.AudioPatches706bcde2_45_736e_45_6997_45_3385_45_a59f0000001b>\n");
        stringBuilder.append("\t\t\t\t<HSL.TimelinePatchingAndTargeting.VideoPatches405230c6_45_7438_45_1002_45_93de_45_aac30000000f>[{\"mNumber\":0,\"mState\":0}]</HSL.TimelinePatchingAndTargeting.VideoPatches405230c6_45_7438_45_1002_45_93de_45_aac30000000f>\n");

        // Position curseur dans la séquence.
        if (position != null) {
            stringBuilder.append("\t\t\t\t<MZ.EditLine>" + (position.toImage() * div) + "</MZ.EditLine>\n");
        } else {
            stringBuilder.append("\t\t\t\t<MZ.EditLine>0</MZ.EditLine>\n");
        }

        stringBuilder.append("\t\t\t\t<MZ.Sequence.AudioTimeDisplayFormat>200</MZ.Sequence.AudioTimeDisplayFormat>\n");
        stringBuilder.append("\t\t\t\t<MZ.Sequence.EditingModeGUID>9678af98-a7b7-4bdb-b477-7ac9c8df4a4e</MZ.Sequence.EditingModeGUID>\n");
        stringBuilder.append("\t\t\t\t<MZ.Sequence.PreviewFrameSizeHeight>1080</MZ.Sequence.PreviewFrameSizeHeight>\n");
        stringBuilder.append("\t\t\t\t<MZ.Sequence.PreviewFrameSizeWidth>1920</MZ.Sequence.PreviewFrameSizeWidth>\n");
        stringBuilder.append("\t\t\t\t<MZ.Sequence.PreviewRenderingClassID>1297106761</MZ.Sequence.PreviewRenderingClassID>\n");
        stringBuilder.append("\t\t\t\t<MZ.Sequence.PreviewRenderingPresetCodec>1297107278</MZ.Sequence.PreviewRenderingPresetCodec>\n");
        stringBuilder.append("\t\t\t\t<MZ.Sequence.PreviewRenderingPresetPath>EncoderPresets\\SequencePreview\\9678af98-a7b7-4bdb-b477-7ac9c8df4a4e\\I-Frame Only MPEG.epr</MZ.Sequence.PreviewRenderingPresetPath>\n");
        stringBuilder.append("\t\t\t\t<MZ.Sequence.PreviewUseMaxBitDepth>false</MZ.Sequence.PreviewUseMaxBitDepth>\n");
        stringBuilder.append("\t\t\t\t<MZ.Sequence.PreviewUseMaxRenderQuality>false</MZ.Sequence.PreviewUseMaxRenderQuality>\n");

        String displayFormat = null;

        switch (this.framerate) {
            case F24 ->
                displayFormat = "100";
            case F25 ->
                displayFormat = "101";
            case F23976 ->
                displayFormat = "102";
            case F2997 ->
                displayFormat = "102";
        }

        stringBuilder.append("\t\t\t\t<MZ.Sequence.VideoTimeDisplayFormat>" + displayFormat + "</MZ.Sequence.VideoTimeDisplayFormat>\n");
        stringBuilder.append("\t\t\t\t<MZ.WorkInPoint>0</MZ.WorkInPoint>\n");
        stringBuilder.append("\t\t\t\t<MZ.WorkOutPoint>15240960000000</MZ.WorkOutPoint>\n");

        if (this.startTimecode != null && this.startTimecode.toImage() != 0) {
            stringBuilder.append("\t\t\t\t<MZ.ZeroPoint>" + (this.startTimecode.toImage() * div) + "</MZ.ZeroPoint>\n");
        }

        stringBuilder.append("\t\t\t\t<Monitor.ProgramZoomIn>0</Monitor.ProgramZoomIn>\n");
        stringBuilder.append("\t\t\t\t<Monitor.ProgramZoomOut>0</Monitor.ProgramZoomOut>\n");
        stringBuilder.append("\t\t\t\t<TL.SQAVDividerPosition>0.209354117513</TL.SQAVDividerPosition>\n");
        stringBuilder.append("\t\t\t\t<TL.SQAudioVisibleBase>0</TL.SQAudioVisibleBase>\n");
        stringBuilder.append("\t\t\t\t<TL.SQHeaderWidth>236</TL.SQHeaderWidth>\n");
        stringBuilder.append("\t\t\t\t<TL.SQHideShyTracks>0</TL.SQHideShyTracks>\n");
        stringBuilder.append("\t\t\t\t<TL.SQTimePerPixel>0.52310374891020051</TL.SQTimePerPixel>\n");
        stringBuilder.append("\t\t\t\t<TL.SQVideoVisibleBase>0</TL.SQVideoVisibleBase>\n");
        stringBuilder.append("\t\t\t\t<TL.SQVisibleBaseTime>0</TL.SQVisibleBaseTime>\n");
        stringBuilder.append("\t\t\t</Properties>\n");
        stringBuilder.append("\t\t</Node>\n");

        int ObjectRef = 100;

        if (!this.listeMarqueur.isEmpty()) {
            stringBuilder.append("\t\t<MarkerOwner Version=\"1\">\n");
            markersId = ObjectRef++;
            stringBuilder.append("\t\t\t<Markers ObjectRef=\"" + markersId + "\">\n");
            stringBuilder.append("\t\t</MarkerOwner>\n");
        }

        stringBuilder.append("\t\t<PersistentGroupContainer Version=\"1\">\n");
        stringBuilder.append("\t\t\t<LinkContainer Version=\"1\">\n");
        stringBuilder.append("\t\t\t</LinkContainer>\n");
        stringBuilder.append("\t\t</PersistentGroupContainer>\n");

        stringBuilder.append("\t\t<TrackGroups Version=\"1\">\n");
        stringBuilder.append("\t\t\t<TrackGroup Version=\"1\" Index=\"0\">\n");
        stringBuilder.append("\t\t\t\t<First>80b8e3d5-6dca-4195-aefb-cb5f407ab009</First>\n");
        stringBuilder.append("\t\t\t\t<Second ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t\t\t</TrackGroup>\n");
        stringBuilder.append("\t\t\t<TrackGroup Version=\"1\" Index=\"1\">\n");
        stringBuilder.append("\t\t\t\t<First>228cda18-3625-4d2d-951e-348879e4ed93</First>\n");
        stringBuilder.append("\t\t\t\t<Second ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t\t\t</TrackGroup>\n");
        stringBuilder.append("\t\t</TrackGroups>\n");
        stringBuilder.append("\t\t<ID>1</ID>\n");
        stringBuilder.append("\t\t<Name>" + this.getName() + "</Name>\n");
        stringBuilder.append("\t\t<PreviewFormatIdentifier>b0f75bf9-a1fa-f37f-f0e2-3a24000000fa</PreviewFormatIdentifier>\n");
        stringBuilder.append("\t</Sequence>\n");

        String classID = "5c89aa7a-89a6-4483-becd-f2b1def42316";
        int ObjectID = 84;

        for (int i = 0; i <= 15; i++) {
            stringBuilder.append("\t<ClipChannelSerializer ObjectID=\"" + (ObjectID++) + "\" ClassID=\"" + classID + "\" Version=\"1\">\n");
            stringBuilder.append("\t	<SourceClipIndex>" + (0) + "</SourceClipIndex>\n");
            stringBuilder.append("\t	<mSourceChannelIndex>" + (i) + "</mSourceChannelIndex>\n");
            stringBuilder.append("\t</ClipChannelSerializer>\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Définit le timecode début de la séquence.
     *
     * @param startTimecode Timecode début.
     */
    public void setStartTimecode(Timecode startTimecode) {
        this.startTimecode = startTimecode;
    }

    /**
     * Définit le framerate de la séquence.
     *
     * @param framerate Le framerate.
     */
    public void setFramerate(Framerate framerate) {
        this.framerate = framerate;
    }

    /**
     * Définit la position du curseur dans la séquence.
     *
     * @param timecode En timecode où on est dans la séquence.
     */
    public void setPosition(Timecode timecode) {
        this.position = position;
    }

    /**
     * Définit la résolution de la séquence.
     *
     * @param resolution La résolution.
     */
    public void setResolution(@NotNull ResolutionStandard resolution) {
        this.largeur = resolution.largeur;
        this.hauteur = resolution.hauteur;
    }

    /**
     * Définit la résolution de la séquence.
     *
     * @param largeur Largeur en pixel.
     * @param hauteur Hauteur en pixel.
     */
    public void setResolution(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    /**
     *
     * @return
     */
    public String toXMLAudioTrackGroup() {
        StringBuilder stringBuilder = new StringBuilder();

        int ObjectID = 100;
        int ObjectRef = 103;

        stringBuilder.append("\t<AudioTrackGroup ObjectID=\"" + (ObjectID++) + "\" ClassID=\"9b9238b9-53a8-4cc3-b03f-b36246d052e6\" Version=\"6\">\n");
        stringBuilder.append("\t	<TrackGroup Version=\"1\">\n");
        stringBuilder.append("\t		<Tracks Version=\"1\">\n");
        stringBuilder.append("\t			<Track Index=\"0\" ObjectURef=\"4aa95753-fe3b-4c32-a051-414fd49f3f62\"/>\n");
        stringBuilder.append("\t			<Track Index=\"1\" ObjectURef=\"05c90639-4a41-42a9-ae1f-b2178d20a8ab\"/>\n");
        stringBuilder.append("\t		</Tracks>\n");
        stringBuilder.append("\t		<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t		<NextTrackID>18</NextTrackID>\n");
        stringBuilder.append("\t	</TrackGroup>\n");
        stringBuilder.append("\t	<MasterTrack ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t	<AutomationSafeFlags>0</AutomationSafeFlags>\n");
        stringBuilder.append("\t	<ID>593368e4-e82f-4796-b8dc-2932326d6373</ID>\n");
        stringBuilder.append("\t	<NumAdaptiveChannels>16</NumAdaptiveChannels>\n");
        stringBuilder.append("\t</AudioTrackGroup>\n");
        stringBuilder.append("\t<VideoTrackGroup ObjectID=\"" + (ObjectID++) + "\" ClassID=\"9e9abf7a-0918-49c2-91ae-991b5dde77bb\" Version=\"10\">\n");
        stringBuilder.append("\t	<TrackGroup Version=\"1\">\n");
        stringBuilder.append("\t		<Tracks Version=\"1\">\n");
        stringBuilder.append("\t			<Track Index=\"0\" ObjectURef=\"9b6d8c60-7c45-411d-ba73-4d14d6a2fa4d\"/>\n");
        stringBuilder.append("\t		</Tracks>\n");
        long framerate_ = (long) (254016000000L / this.framerate.value);
        stringBuilder.append("\t		<FrameRate>" + framerate_ + "</FrameRate>\n");
        stringBuilder.append("\t		<NextTrackID>2</NextTrackID>\n");
        stringBuilder.append("\t	</TrackGroup>\n");
        stringBuilder.append("\t	<FrameRect>0,0," + this.largeur + "," + this.hauteur + "</FrameRect>\n");
        stringBuilder.append("\t	<PixelAspectRatio>1,1</PixelAspectRatio>\n");
        stringBuilder.append("\t	<FieldType>0</FieldType>\n");
        stringBuilder.append("\t	<AllowLinearCompositing>true</AllowLinearCompositing>\n");
        stringBuilder.append("\t	<ImmersiveVideoVRConfiguration>{\"capturedHorizontalView\":0,\"capturedVerticalView\":0,\"fieldOfHorizontalView\":90,\"fieldOfVerticalView\":60,\"projectionType\":0,\"stereoscopicEye\":0,\"stereoscopicType\":0,\"version\":2}</ImmersiveVideoVRConfiguration>\n");
        stringBuilder.append("\t</VideoTrackGroup>\n");

        if (!this.listeMarqueur.isEmpty()) {
            for (MarkerInSequence marqueur : this.listeMarqueur) {
                stringBuilder.append(marqueur.toXML());
            }
        }

        //if (!this.listeClip.isEmpty()) {
        stringBuilder.append("\t<VideoStream ObjectID=\"" + (ObjectID++) + "\" ClassID=\"a36e4719-3ec6-4a0c-ab11-8b4aab377aa5\" Version=\"15\">\n");
        stringBuilder.append("\t	<IsStill>true</IsStill>\n");
        stringBuilder.append("\t	<FrameRate>10584000000</FrameRate>\n");
        stringBuilder.append("\t	<FrameRect>0,0," + this.largeur + "," + this.hauteur + "</FrameRect>\n");
        stringBuilder.append("\t	<Duration>10973491200000000</Duration>\n");
        stringBuilder.append("\t	<AlphaType>1</AlphaType>\n");
        stringBuilder.append("\t	<CodecType>1416197228</CodecType>\n");
        stringBuilder.append("\t</VideoStream>\n");
        //}

        if (!this.listeMarqueur.isEmpty()) {
            stringBuilder.append("\t<Markers ObjectID=\"" + markersId + "\" ClassID=\"bee50706-b524-416c-9f03-b596ce5f6866\" Version=\"3\">\n");
            stringBuilder.append("\t\t<Markers Version=\"1\">\n");
            stringBuilder.append("\t\t\t<Marker Version=\"1\" Index=\"0\">\n");
            stringBuilder.append("\t\t\t\t<First>71550971-39c2-2ea3-131c-c2a500000024</First>\n");
            stringBuilder.append("\t\t\t\t<Second ObjectRef=\"105\"/>\n");
            stringBuilder.append("\t\t\t</Marker>\n");
            stringBuilder.append("\t\t\t<Marker Version=\"1\" Index=\"1\">\n");
            stringBuilder.append("\t\t\t\t<First>d60de2c2-4e47-b0ea-b911-14be00000024</First>\n");
            stringBuilder.append("\t\t\t\t<Second ObjectRef=\"106\"/>\n");
            stringBuilder.append("\t\t\t</Marker>\n");
            stringBuilder.append("\t\t</Markers>\n");
            stringBuilder.append("\t\t<ByGUID>byGUID</ByGUID>\n");
            stringBuilder.append("\t\t<LastMetadataState>00000000-0000-0000-0000-000000000000</LastMetadataState>\n");
            stringBuilder.append("\t\t<LastContentState>00000000-0000-0000-0000-000000000000</LastContentState>\n");
            stringBuilder.append("\t</Markers>\n");
        }

        stringBuilder.append("\t<AudioClipTrack ObjectUID=\"4aa95753-fe3b-4c32-a051-414fd49f3f62\" ClassID=\"097f6203-99ae-11d5-84f2-8cf14bde7040\" Version=\"6\">\n");
        stringBuilder.append("\t\t<ClipTrack Version=\"2\">\n");
        stringBuilder.append("\t\t\t<Track Version=\"3\">\n");
        stringBuilder.append("\t\t\t\t<Node Version=\"1\">\n");
        stringBuilder.append("\t				<Properties Version=\"1\">\n");
        stringBuilder.append("\t					<MZ.SourceTrackNumber>0</MZ.SourceTrackNumber>\n");
        stringBuilder.append("\t					<MZ.SourceTrackState>0</MZ.SourceTrackState>\n");
        stringBuilder.append("\t					<MZ.TrackTargeted>1</MZ.TrackTargeted>\n");
        stringBuilder.append("\t					<TL.SQTrackAudioKeyframeStyle>0</TL.SQTrackAudioKeyframeStyle>\n");
        stringBuilder.append("\t					<TL.SQTrackExpanded>0</TL.SQTrackExpanded>\n");
        stringBuilder.append("\t					<TL.SQTrackExpandedHeight>25</TL.SQTrackExpandedHeight>\n");
        stringBuilder.append("\t					<TL.SQTrackShy>0</TL.SQTrackShy>\n");
        stringBuilder.append("\t				</Properties>\n");
        stringBuilder.append("\t			</Node>\n");
        stringBuilder.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        stringBuilder.append("\t			<Index>0</Index>\n");
        stringBuilder.append("\t			<ID>2</ID>\n");
        stringBuilder.append("\t			<IsLocked>false</IsLocked>\n");
        stringBuilder.append("\t			<IsSyncLocked>true</IsSyncLocked>\n");
        stringBuilder.append("\t			<IsMuted>false</IsMuted>\n");
        stringBuilder.append("\t		</Track>\n");
        stringBuilder.append("\t		<ClipItems Version=\"3\">\n");
        stringBuilder.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        stringBuilder.append("\t			<Index>0</Index>\n");
        stringBuilder.append("\t		</ClipItems>\n");
        stringBuilder.append("\t		<TransitionItems Version=\"3\">\n");
        stringBuilder.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        stringBuilder.append("\t			<Index>0</Index>\n");
        stringBuilder.append("\t\t\t</TransitionItems>\n");
        stringBuilder.append("\t\t</ClipTrack>\n");
        stringBuilder.append("\t\t<AudioTrack Version=\"11\">\n");
        stringBuilder.append("\t\t\t<ComponentOwner Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t<Components ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t\t\t</ComponentOwner>\n");
        stringBuilder.append("\t\t\t<Panner ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t\t\t<SubType>1</SubType>\n");
        stringBuilder.append("\t\t\t<AutomationMode>1</AutomationMode>\n");
        stringBuilder.append("\t\t\t<Assign>1</Assign>\n");
        stringBuilder.append("\t\t\t<ChannelType>0</ChannelType>\n");
        stringBuilder.append("\t\t<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t\t\t<NextPannerID>4294967279</NextPannerID>\n");
        stringBuilder.append("\t\t\t<Solo>0</Solo>\n");
        stringBuilder.append("\t\t\t<MutedBySolo>0</MutedBySolo>\n");
        stringBuilder.append("\t\t\t<ID>ec0c64a5-73fe-4a33-beb3-0fe37e1e3cf9</ID>\n");
        stringBuilder.append("\t	</AudioTrack>\n");
        stringBuilder.append("\t	<RecordChannel>0</RecordChannel>\n");
        stringBuilder.append("\t</AudioClipTrack>\n");
        stringBuilder.append("\t<AudioClipTrack ObjectUID=\"05c90639-4a41-42a9-ae1f-b2178d20a8ab\" ClassID=\"097f6203-99ae-11d5-84f2-8cf14bde7040\" Version=\"6\">\n");
        stringBuilder.append("\t	<ClipTrack Version=\"2\">\n");
        stringBuilder.append("\t		<Track Version=\"3\">\n");
        stringBuilder.append("\t			<Node Version=\"1\">\n");
        stringBuilder.append("\t				<Properties Version=\"1\">\n");
        stringBuilder.append("\t					<MZ.SourceTrackNumber>-1</MZ.SourceTrackNumber>\n");
        stringBuilder.append("\t					<MZ.SourceTrackState>0</MZ.SourceTrackState>\n");
        stringBuilder.append("\t					<MZ.TrackTargeted>1</MZ.TrackTargeted>\n");
        stringBuilder.append("\t					<TL.SQTrackAudioKeyframeStyle>0</TL.SQTrackAudioKeyframeStyle>\n");
        stringBuilder.append("\t					<TL.SQTrackExpanded>0</TL.SQTrackExpanded>\n");
        stringBuilder.append("\t					<TL.SQTrackExpandedHeight>25</TL.SQTrackExpandedHeight>\n");
        stringBuilder.append("\t					<TL.SQTrackShy>0</TL.SQTrackShy>\n");
        stringBuilder.append("\t				</Properties>\n");
        stringBuilder.append("\t			</Node>\n");
        stringBuilder.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        stringBuilder.append("\t			<Index>1</Index>\n");
        stringBuilder.append("\t			<ID>3</ID>\n");
        stringBuilder.append("\t			<IsLocked>false</IsLocked>\n");
        stringBuilder.append("\t			<IsSyncLocked>true</IsSyncLocked>\n");
        stringBuilder.append("\t			<IsMuted>false</IsMuted>\n");
        stringBuilder.append("\t		</Track>\n");
        stringBuilder.append("\t		<ClipItems Version=\"3\">\n");
        stringBuilder.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        stringBuilder.append("\t			<Index>1</Index>\n");
        stringBuilder.append("\t		</ClipItems>\n");
        stringBuilder.append("\t		<TransitionItems Version=\"3\">\n");
        stringBuilder.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        stringBuilder.append("\t			<Index>1</Index>\n");
        stringBuilder.append("\t		</TransitionItems>\n");
        stringBuilder.append("\t	</ClipTrack>\n");
        stringBuilder.append("\t	<AudioTrack Version=\"11\">\n");
        stringBuilder.append("\t		<ComponentOwner Version=\"1\">\n");
        stringBuilder.append("\t			<Components ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t		</ComponentOwner>\n");
        stringBuilder.append("\t		<Panner ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t		<SubType>1</SubType>\n");
        stringBuilder.append("\t		<AutomationMode>1</AutomationMode>\n");
        stringBuilder.append("\t		<Assign>1</Assign>\n");
        stringBuilder.append("\t		<ChannelType>0</ChannelType>\n");
        stringBuilder.append("\t		<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t		<NextPannerID>4294967279</NextPannerID>\n");
        stringBuilder.append("\t		<Solo>0</Solo>\n");
        stringBuilder.append("\t		<MutedBySolo>0</MutedBySolo>\n");
        stringBuilder.append("\t		<ID>120bceae-452c-496e-8bd1-2d7a0daea3bd</ID>\n");
        stringBuilder.append("\t	</AudioTrack>\n");
        stringBuilder.append("\t	<RecordChannel>0</RecordChannel>\n");
        stringBuilder.append("\t</AudioClipTrack>\n");
        stringBuilder.append("\t<AudioMixTrack ObjectID=\"" + (ObjectID++) + "\" ClassID=\"4b1d8400-e89e-11d5-abc4-a1a13b1e80a0\" Version=\"4\">\n");
        stringBuilder.append("\t	<AudioTrack Version=\"11\">\n");
        stringBuilder.append("\t		<ComponentOwner Version=\"1\">\n");
        stringBuilder.append("\t			<Components ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t		</ComponentOwner>\n");
        stringBuilder.append("\t		<Panner ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t		<SubType>3</SubType>\n");
        stringBuilder.append("\t		<AutomationMode>1</AutomationMode>\n");
        stringBuilder.append("\t		<Assign>0</Assign>\n");
        stringBuilder.append("\t		<ChannelType>3</ChannelType>\n");
        stringBuilder.append("\t		<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t		<NextPannerID>4294967279</NextPannerID>\n");
        stringBuilder.append("\t		<Solo>0</Solo>\n");
        stringBuilder.append("\t		<MutedBySolo>0</MutedBySolo>\n");
        stringBuilder.append("\t		<ID>e6ccbd27-ba07-4342-96f2-573fafa9646f</ID>\n");
        stringBuilder.append("\t	</AudioTrack>\n");
        stringBuilder.append("\t	<Track Version=\"3\">\n");
        stringBuilder.append("\t		<Node Version=\"1\">\n");
        stringBuilder.append("\t			<Properties Version=\"1\">\n");
        stringBuilder.append("\t				<TL.SQTrackAudioKeyframeStyle>2</TL.SQTrackAudioKeyframeStyle>\n");
        stringBuilder.append("\t				<TL.SQTrackExpanded>0</TL.SQTrackExpanded>\n");
        stringBuilder.append("\t				<TL.SQTrackExpandedHeight>25</TL.SQTrackExpandedHeight>\n");
        stringBuilder.append("\t				<TL.SQTrackShy>0</TL.SQTrackShy>\n");
        stringBuilder.append("\t			</Properties>\n");
        stringBuilder.append("\t		</Node>\n");
        stringBuilder.append("\t		<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        stringBuilder.append("\t		<Index>0</Index>\n");
        stringBuilder.append("\t		<ID>1</ID>\n");
        stringBuilder.append("\t		<IsLocked>false</IsLocked>\n");
        stringBuilder.append("\t		<IsSyncLocked>true</IsSyncLocked>\n");
        stringBuilder.append("\t		<IsMuted>false</IsMuted>\n");
        stringBuilder.append("\t	</Track>\n");
        stringBuilder.append("\t	<Inlet ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t</AudioMixTrack>\n");
        stringBuilder.append("\t<VideoClipTrack ObjectUID=\"9b6d8c60-7c45-411d-ba73-4d14d6a2fa4d\" ClassID=\"f68dcd81-8805-11d5-af2d-9bfa89d4ddd4\" Version=\"1\">\n");
        stringBuilder.append("\t	<ClipTrack Version=\"2\">\n");
        stringBuilder.append("\t		<Track Version=\"3\">\n");
        stringBuilder.append("\t			<Node Version=\"1\">\n");
        stringBuilder.append("\t				<Properties Version=\"1\">\n");
        stringBuilder.append("\t					<MZ.SourceTrackNumber>0</MZ.SourceTrackNumber>\n");
        stringBuilder.append("\t					<MZ.SourceTrackState>" + ((!this.listeClip.isEmpty()) ? "2" : "0") + "</MZ.SourceTrackState>\n");
        stringBuilder.append("\t					<MZ.TrackTargeted>1</MZ.TrackTargeted>\n");
        stringBuilder.append("\t					<TL.SQTrackExpanded>0</TL.SQTrackExpanded>\n");
        stringBuilder.append("\t					<TL.SQTrackExpandedHeight>25</TL.SQTrackExpandedHeight>\n");
        stringBuilder.append("\t					<TL.SQTrackShy>0</TL.SQTrackShy>\n");
        stringBuilder.append("\t				</Properties>\n");
        stringBuilder.append("\t			</Node>\n");
        stringBuilder.append("\t			<MediaType>228cda18-3625-4d2d-951e-348879e4ed93</MediaType>\n");
        stringBuilder.append("\t			<Index>0</Index>\n");
        stringBuilder.append("\t			<ID>1</ID>\n");
        stringBuilder.append("\t			<IsLocked>false</IsLocked>\n");
        stringBuilder.append("\t			<IsSyncLocked>true</IsSyncLocked>\n");
        stringBuilder.append("\t			<IsMuted>false</IsMuted>\n");
        stringBuilder.append("\t		</Track>\n");
        stringBuilder.append("\t		<ClipItems Version=\"3\">\n");

        // Ajoute dans la séquence ici l'item/clip.
        if (!this.listeClip.isEmpty()) {
            stringBuilder.append("\t\t\t\t<TrackItems Version=\"1\">\n");
            stringBuilder.append("\t\t\t\t\t<TrackItem Index=\"0\" ObjectRef=\"111\"/>\n");
            stringBuilder.append("\t\t\t\t</TrackItems>\n");
        }

        stringBuilder.append("\t			<MediaType>228cda18-3625-4d2d-951e-348879e4ed93</MediaType>\n");
        stringBuilder.append("\t			<Index>0</Index>\n");
        stringBuilder.append("\t		</ClipItems>\n");
        stringBuilder.append("\t		<TransitionItems Version=\"3\">\n");
        stringBuilder.append("\t			<MediaType>228cda18-3625-4d2d-951e-348879e4ed93</MediaType>\n");
        stringBuilder.append("\t			<Index>0</Index>\n");
        stringBuilder.append("\t		</TransitionItems>\n");
        stringBuilder.append("\t	</ClipTrack>\n");
        stringBuilder.append("\t</VideoClipTrack>\n");

        //ObjectID= 104;
        stringBuilder.append("\t<AudioComponentChain ObjectID=\"" + (ObjectID++) + "\" ClassID=\"3cb131d1-d3c0-47ae-a19a-bdf75ea11674\" Version=\"3\">\n");
        stringBuilder.append("\t	<ComponentChain Version=\"2\">\n");
        stringBuilder.append("\t		<Components Version=\"1\">\n");
        stringBuilder.append("\t			<Component Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t			<Component Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t		</Components>\n");
        stringBuilder.append("\t	</ComponentChain>\n");
        stringBuilder.append("\t	<ChannelType>0</ChannelType>\n");
        stringBuilder.append("\t	<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t	<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t	<AutomationMode>1</AutomationMode>\n");
        stringBuilder.append("\t</AudioComponentChain>\n");
        stringBuilder.append("\t<MonoTo16ChannelPanProcessor ObjectID=\"" + (ObjectID++) + "\" ClassID=\"8c9778ad-af4e-4e98-99fe-542f4eda2dac\" Version=\"2\">\n");
        stringBuilder.append("\t	<DirectPanProcessor Version=\"2\">\n");
        stringBuilder.append("\t		<PanProcessor Version=\"3\">\n");
        stringBuilder.append("\t			<AudioComponent Version=\"3\">\n");
        stringBuilder.append("\t				<Component Version=\"5\">\n");
        stringBuilder.append("\t					<Params Version=\"1\">\n");
        stringBuilder.append("\t						<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t					</Params>\n");
        stringBuilder.append("\t					<ID>4294967280</ID>\n");
        stringBuilder.append("\t					<Bypass>false</Bypass>\n");
        stringBuilder.append("\t					<Intrinsic>false</Intrinsic>\n");
        stringBuilder.append("\t				</Component>\n");
        stringBuilder.append("\t				<ChannelType>0</ChannelType>\n");
        stringBuilder.append("\t				<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t				<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t				<AudioComponentType>0</AudioComponentType>\n");
        stringBuilder.append("\t			</AudioComponent>\n");
        stringBuilder.append("\t			<OutputAudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</OutputAudioChannelLayout>\n");
        stringBuilder.append("\t		</PanProcessor>\n");
        stringBuilder.append("\t		<Matrix>[[0,[0]]]</Matrix>\n");
        stringBuilder.append("\t	</DirectPanProcessor>\n");
        stringBuilder.append("\t</MonoTo16ChannelPanProcessor>\n");
        stringBuilder.append("\t<AudioComponentChain ObjectID=\"" + (ObjectID++) + "\" ClassID=\"3cb131d1-d3c0-47ae-a19a-bdf75ea11674\" Version=\"3\">\n");
        stringBuilder.append("\t	<ComponentChain Version=\"2\">\n");
        stringBuilder.append("\t		<Components Version=\"1\">\n");
        stringBuilder.append("\t			<Component Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t			<Component Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t		</Components>\n");
        stringBuilder.append("\t	</ComponentChain>\n");
        stringBuilder.append("\t	<ChannelType>0</ChannelType>\n");
        stringBuilder.append("\t	<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t	<AutomationMode>1</AutomationMode>\n");
        stringBuilder.append("\t	<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t</AudioComponentChain>\n");
        stringBuilder.append("\t<MonoTo16ChannelPanProcessor ObjectID=\"" + (ObjectID++) + "\" ClassID=\"8c9778ad-af4e-4e98-99fe-542f4eda2dac\" Version=\"2\">\n");
        stringBuilder.append("\t	<DirectPanProcessor Version=\"2\">\n");
        stringBuilder.append("\t		<PanProcessor Version=\"3\">\n");
        stringBuilder.append("\t			<AudioComponent Version=\"3\">\n");
        stringBuilder.append("\t				<Component Version=\"5\">\n");
        stringBuilder.append("\t					<Params Version=\"1\">\n");
        stringBuilder.append("\t						<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t					</Params>\n");
        stringBuilder.append("\t					<ID>4294967280</ID>\n");
        stringBuilder.append("\t					<Bypass>false</Bypass>\n");
        stringBuilder.append("\t					<Intrinsic>false</Intrinsic>\n");
        stringBuilder.append("\t				</Component>\n");
        stringBuilder.append("\t				<ChannelType>0</ChannelType>\n");
        stringBuilder.append("\t				<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t				<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t				<AudioComponentType>0</AudioComponentType>\n");
        stringBuilder.append("\t			</AudioComponent>\n");
        stringBuilder.append("\t			<OutputAudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</OutputAudioChannelLayout>\n");
        stringBuilder.append("\t		</PanProcessor>\n");
        stringBuilder.append("\t		<Matrix>[[0,[0]]]</Matrix>\n");
        stringBuilder.append("\t	</DirectPanProcessor>\n");
        stringBuilder.append("\t</MonoTo16ChannelPanProcessor>\n");
        stringBuilder.append("\t<AudioComponentChain ObjectID=\"" + (ObjectID++) + "\" ClassID=\"3cb131d1-d3c0-47ae-a19a-bdf75ea11674\" Version=\"3\">\n");
        stringBuilder.append("\t	<ComponentChain Version=\"2\">\n");
        stringBuilder.append("\t		<Components Version=\"1\">\n");
        stringBuilder.append("\t			<Component Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t			<Component Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t		</Components>\n");
        stringBuilder.append("\t	</ComponentChain>\n");
        stringBuilder.append("\t	<ChannelType>3</ChannelType>\n");
        stringBuilder.append("\t	<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t	<AutomationMode>1</AutomationMode>\n");
        stringBuilder.append("\t	<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t</AudioComponentChain>\n");
        stringBuilder.append("\t<DefaultPanProcessor ObjectID=\"" + (ObjectID++) + "\" ClassID=\"33a94282-ee2c-11d5-abc4-c1cd7f9e3c10\" Version=\"2\">\n");
        stringBuilder.append("\t	<PanProcessor Version=\"3\">\n");
        stringBuilder.append("\t		<AudioComponent Version=\"3\">\n");
        stringBuilder.append("\t			<Component Version=\"5\">\n");
        stringBuilder.append("\t				<ID>4294967280</ID>\n");
        stringBuilder.append("\t				<Bypass>false</Bypass>\n");
        stringBuilder.append("\t				<Intrinsic>false</Intrinsic>\n");
        stringBuilder.append("\t			</Component>\n");
        stringBuilder.append("\t			<ChannelType>3</ChannelType>\n");
        stringBuilder.append("\t			<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t			<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t			<AudioComponentType>0</AudioComponentType>\n");
        stringBuilder.append("\t		</AudioComponent>\n");
        stringBuilder.append("\t	</PanProcessor>\n");
        stringBuilder.append("\t	<DefaultPannerInputChannelType>3</DefaultPannerInputChannelType>\n");
        stringBuilder.append("\t	<DefaultPannerOutputChannelType>3</DefaultPannerOutputChannelType>\n");
        stringBuilder.append("\t</DefaultPanProcessor>\n");
        stringBuilder.append("\t<AudioTrackInlet ObjectID=\"" + (ObjectID++) + "\" ClassID=\"be3af080-e8c6-11d5-abc4-a1c6d5dee670\" Version=\"3\">\n");
        stringBuilder.append("\t	<Sources Version=\"1\">\n");
        stringBuilder.append("\t		<Source Index=\"0\" ObjectURef=\"4aa95753-fe3b-4c32-a051-414fd49f3f62\"/>\n");
        stringBuilder.append("\t		<Source Index=\"1\" ObjectURef=\"05c90639-4a41-42a9-ae1f-b2178d20a8ab\"/>\n");
        stringBuilder.append("\t	</Sources>\n");
        stringBuilder.append("\t	<ChannelType>3</ChannelType>\n");
        stringBuilder.append("\t	<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t	<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t</AudioTrackInlet>\n");

        // "VideoClipTrackItem"
        if (!this.listeClip.isEmpty()) {
            stringBuilder.append("\t<VideoClipTrackItem ObjectID=\"" + (ObjectID++) + "\" ClassID=\"368b0406-29e3-4923-9fcd-094fbf9a1089\" Version=\"5\">\n");
            stringBuilder.append("\t	<ClipTrackItem Version=\"6\">\n");
            stringBuilder.append("\t		<ComponentOwner Version=\"1\">\n");
            stringBuilder.append("\t			<Components ObjectRef=\"120\"/>\n");
            stringBuilder.append("\t		</ComponentOwner>\n");
            stringBuilder.append("\t		<TrackItem Version=\"3\">\n");
            stringBuilder.append("\t			<Start>0</Start>\n");
            stringBuilder.append("\t			<End>1270080000000</End>\n");
            stringBuilder.append("\t		</TrackItem>\n");
            stringBuilder.append("\t		<SubClip ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
            stringBuilder.append("\t	</ClipTrackItem>\n");
            stringBuilder.append("\t	<FrameRect>0,0," + this.largeur + "," + this.hauteur + "</FrameRect>\n");
            stringBuilder.append("\t	<PixelAspectRatio>1,1</PixelAspectRatio>\n");
            stringBuilder.append("\t</VideoClipTrackItem>\n");
        }

        stringBuilder.append("\t<AudioFader ObjectID=\"" + (ObjectID++) + "\" ClassID=\"1a38c583-ed5c-11d5-abc4-c1cbf61ec590\" Version=\"3\">\n");
        stringBuilder.append("\t	<AudioComponent Version=\"3\">\n");
        stringBuilder.append("\t		<Component Version=\"5\">\n");
        stringBuilder.append("\t			<Params Version=\"1\">\n");
        stringBuilder.append("\t				<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t				<Param Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t			</Params>\n");
        stringBuilder.append("\t			<ID>1</ID>\n");
        stringBuilder.append("\t			<Bypass>false</Bypass>\n");
        stringBuilder.append("\t			<Intrinsic>false</Intrinsic>\n");
        stringBuilder.append("\t		</Component>\n");
        stringBuilder.append("\t		<AudioComponentType>1</AudioComponentType>\n");
        stringBuilder.append("\t		<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t		<ChannelType>0</ChannelType>\n");
        stringBuilder.append("\t		<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t	</AudioComponent>\n");
        stringBuilder.append("\t</AudioFader>\n");
        stringBuilder.append("\t<AudioMeter ObjectID=\"" + (ObjectID++) + "\" ClassID=\"72ea4700-f615-11d5-abc4-c186585e63e0\" Version=\"2\">\n");
        stringBuilder.append("\t	<AudioComponent Version=\"3\">\n");
        stringBuilder.append("\t		<Component Version=\"5\">\n");
        stringBuilder.append("\t			<ID>2</ID>\n");
        stringBuilder.append("\t			<Bypass>false</Bypass>\n");
        stringBuilder.append("\t			<Intrinsic>false</Intrinsic>\n");
        stringBuilder.append("\t		</Component>\n");
        stringBuilder.append("\t		<AudioComponentType>2</AudioComponentType>\n");
        stringBuilder.append("\t		<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t		<ChannelType>0</ChannelType>\n");
        stringBuilder.append("\t		<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t	</AudioComponent>\n");
        stringBuilder.append("\t</AudioMeter>\n");
        stringBuilder.append("\t<AudioComponentParam ObjectID=\"" + (ObjectID++) + "\" ClassID=\"a714635e-a628-4b27-9d59-77eba47dbc1a\" Version=\"9\">\n");
        stringBuilder.append("\t	<Name>Panoramique</Name>\n");
        stringBuilder.append("\t	<ParameterControlType>2</ParameterControlType>\n");
        stringBuilder.append("\t	<RangeLocked>true</RangeLocked>\n");
        stringBuilder.append("\t	<IsInverted>true</IsInverted>\n");
        stringBuilder.append("\t	<Timestamp>358326044788400</Timestamp>\n");
        stringBuilder.append("\t	<StartKeyframe>-91445760000000000,0.,0,0,0,0,0,0</StartKeyframe>\n");
        stringBuilder.append("\t	<CurrentValue>0</CurrentValue>\n");
        stringBuilder.append("\t</AudioComponentParam>\n");
        stringBuilder.append("\t<AudioFader ObjectID=\"" + (ObjectID++) + "\" ClassID=\"1a38c583-ed5c-11d5-abc4-c1cbf61ec590\" Version=\"3\">\n");
        stringBuilder.append("\t	<AudioComponent Version=\"3\">\n");
        stringBuilder.append("\t		<Component Version=\"5\">\n");
        stringBuilder.append("\t			<Params Version=\"1\">\n");
        stringBuilder.append("\t				<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t				<Param Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t			</Params>\n");
        stringBuilder.append("\t			<ID>1</ID>\n");
        stringBuilder.append("\t			<Bypass>false</Bypass>\n");
        stringBuilder.append("\t			<Intrinsic>false</Intrinsic>\n");
        stringBuilder.append("\t		</Component>\n");
        stringBuilder.append("\t		<AudioComponentType>1</AudioComponentType>\n");
        stringBuilder.append("\t		<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t		<ChannelType>0</ChannelType>\n");
        stringBuilder.append("\t		<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t	</AudioComponent>\n");
        stringBuilder.append("\t</AudioFader>\n");
        stringBuilder.append("\t<AudioMeter ObjectID=\"" + (ObjectID++) + "\" ClassID=\"72ea4700-f615-11d5-abc4-c186585e63e0\" Version=\"2\">\n");
        stringBuilder.append("\t	<AudioComponent Version=\"3\">\n");
        stringBuilder.append("\t		<Component Version=\"5\">\n");
        stringBuilder.append("\t			<ID>2</ID>\n");
        stringBuilder.append("\t			<Bypass>false</Bypass>\n");
        stringBuilder.append("\t			<Intrinsic>false</Intrinsic>\n");
        stringBuilder.append("\t		</Component>\n");
        stringBuilder.append("\t		<AudioComponentType>2</AudioComponentType>\n");
        stringBuilder.append("\t		<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t		<ChannelType>0</ChannelType>\n");
        stringBuilder.append("\t		<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t	</AudioComponent>\n");
        stringBuilder.append("\t</AudioMeter>\n");
        stringBuilder.append("\t<AudioComponentParam ObjectID=\"" + (ObjectID++) + "\" ClassID=\"a714635e-a628-4b27-9d59-77eba47dbc1a\" Version=\"9\">\n");
        stringBuilder.append("\t	<Name>Panoramique</Name>\n");
        stringBuilder.append("\t	<ParameterControlType>2</ParameterControlType>\n");
        stringBuilder.append("\t	<RangeLocked>true</RangeLocked>\n");
        stringBuilder.append("\t	<IsInverted>true</IsInverted>\n");
        stringBuilder.append("\t	<Timestamp>358326048296100</Timestamp>\n");
        stringBuilder.append("\t</AudioComponentParam>\n");
        stringBuilder.append("\t<AudioFader ObjectID=\"" + (ObjectID++) + "\" ClassID=\"1a38c583-ed5c-11d5-abc4-c1cbf61ec590\" Version=\"3\">\n");
        stringBuilder.append("\t	<AudioComponent Version=\"3\">\n");
        stringBuilder.append("\t		<Component Version=\"5\">\n");
        stringBuilder.append("\t			<Params Version=\"1\">\n");
        stringBuilder.append("\t				<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t				<Param Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        stringBuilder.append("\t			</Params>\n");
        stringBuilder.append("\t			<ID>1</ID>\n");
        stringBuilder.append("\t			<Bypass>false</Bypass>\n");
        stringBuilder.append("\t			<Intrinsic>false</Intrinsic>\n");
        stringBuilder.append("\t		</Component>\n");
        stringBuilder.append("\t		<AudioComponentType>1</AudioComponentType>\n");
        stringBuilder.append("\t		<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t		<ChannelType>3</ChannelType>\n");
        stringBuilder.append("\t		<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t	</AudioComponent>\n");
        stringBuilder.append("\t</AudioFader>\n");
        stringBuilder.append("\t<AudioMeter ObjectID=\"" + (ObjectID++) + "\" ClassID=\"72ea4700-f615-11d5-abc4-c186585e63e0\" Version=\"2\">\n");
        stringBuilder.append("\t	<AudioComponent Version=\"3\">\n");
        stringBuilder.append("\t		<Component Version=\"5\">\n");
        stringBuilder.append("\t			<ID>2</ID>\n");
        stringBuilder.append("\t			<Bypass>false</Bypass>\n");
        stringBuilder.append("\t			<Intrinsic>false</Intrinsic>\n");
        stringBuilder.append("\t		</Component>\n");
        stringBuilder.append("\t		<AudioComponentType>2</AudioComponentType>\n");
        stringBuilder.append("\t		<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t		<ChannelType>3</ChannelType>\n");
        stringBuilder.append("\t		<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        stringBuilder.append("\t	</AudioComponent>\n");
        stringBuilder.append("\t</AudioMeter>\n");

        // VideoComponentChain.
        if (!this.listeClip.isEmpty()) {
            stringBuilder.append("\t<VideoComponentChain ObjectID=\"" + (ObjectID++) + "\" ClassID=\"0970e08a-f58f-4108-b29a-1a717b8e12e2\" Version=\"1\">\n");
            stringBuilder.append("\t	<ComponentChain Version=\"2\">\n");
            stringBuilder.append("\t		<Node Version=\"1\">\n");
            stringBuilder.append("\t			<Properties Version=\"1\">\n");
            stringBuilder.append("\t				<MZ.ComponentChain.ActiveComponentID>2</MZ.ComponentChain.ActiveComponentID>\n");
            stringBuilder.append("\t				<MZ.ComponentChain.ActiveComponentParamIndex>4294967295</MZ.ComponentChain.ActiveComponentParamIndex>\n");
            stringBuilder.append("\t			</Properties>\n");
            stringBuilder.append("\t		</Node>\n");
            stringBuilder.append("\t		<Components Version=\"1\">\n");
            stringBuilder.append("\t		</Components>\n");
            stringBuilder.append("\t	</ComponentChain>\n");
            stringBuilder.append("\t	<DefaultMotion>true</DefaultMotion>\n");
            stringBuilder.append("\t	<DefaultMotionComponentID>1</DefaultMotionComponentID>\n");
            stringBuilder.append("\t	<DefaultOpacity>true</DefaultOpacity>\n");
            stringBuilder.append("\t	<DefaultOpacityComponentID>2</DefaultOpacityComponentID>\n");
            stringBuilder.append("\t</VideoComponentChain>\n");
            stringBuilder.append("\t<SubClip ObjectID=\"121\" ClassID=\"e0c58dc9-dbdd-4166-aef7-5db7e3f22e84\" Version=\"5\">\n");
            stringBuilder.append("\t	<Clip ObjectRef=\"128\"/>\n");
            stringBuilder.append("\t	<MasterClip ObjectURef=\"8c85bb49-dcaf-4511-aed8-9f6cead61d2a\"/>\n");
            stringBuilder.append("\t	<Name>TITRE</Name>\n");
            stringBuilder.append("\t	<OrigChGrp>0</OrigChGrp>\n");
            stringBuilder.append("\t</SubClip>\n");
        }

        List<AudioComponentParam> audioComponentParams = new ArrayList<AudioComponentParam>();
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "a714635e-a628-4b27-9d59-77eba47dbc1a", 2, "Volume", "dB", 2, 358326041532800L));

        audioComponentParams.add(new AudioComponentParam(ObjectID++, "32657501-3aa4-445f-a49b-d09ecb9fa1ae", 0, "Silence", "", 4, 358326041544500L));
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "a714635e-a628-4b27-9d59-77eba47dbc1a", 2, "Volume", "dB", 2, 358326048238700L));
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "32657501-3aa4-445f-a49b-d09ecb9fa1ae", 0, "Silence", "", 4, 358326048244900L));
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "a714635e-a628-4b27-9d59-77eba47dbc1a", 2, "Volume", "dB", 2, 358326031664600L));
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "32657501-3aa4-445f-a49b-d09ecb9fa1ae", 0, "Silence", "", 4, 358326031675600L));

        for (AudioComponentParam audioComponentParam : audioComponentParams) {
            stringBuilder.append(audioComponentParam.toXML());
        }

        return stringBuilder.toString();
    }

    @Override
    public String toXMLinSequence() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    //@Override
    public String toXMLvideoMediaSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toXMLMedia() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
