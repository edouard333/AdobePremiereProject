package com.phenix.adobepremiereproject;

import com.phenix.timecode.Framerate;
import com.phenix.timecode.Timecode;
import java.io.PrintWriter;
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
    private Timecode start_timecode;

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
    private static int nombre_sequence = 1;

    /**
     * Position dans la timeline.
     */
    private Timecode position;

    /**
     * Liste des éléments dans la séquence.
     */
    private List<ElementInSequence> liste_clip;

    /**
     * Liste des marqueurs de la séquence.
     */
    private List<MarkerInSequence> liste_marqueur;

    /**
     * Identifiant des markers.
     */
    private int markers_id;

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
        this.liste_clip = new ArrayList<ElementInSequence>();
        this.liste_marqueur = new ArrayList<MarkerInSequence>();

        nombre_sequence++;
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
     * @param tc_in Point in.
     * @param start Position dans la séquence.
     */
    public void add(ElementInSequence elementInSequence, Timecode tc_in, Timecode start) {
        this.add(elementInSequence, tc_in, null, start);
    }

    /**
     * Ajoute un élément à la séquence.
     *
     * @param elementInSequence Elément ajoutable à la séquence.
     * @param tc_in Point in.
     * @param out Point out.
     * @param start Position dans la séquence.
     */
    public void add(ElementInSequence elementInSequence, Timecode tc_in, Timecode out, Timecode start) {
        this.liste_clip.add(elementInSequence);
    }

    /**
     * Ajouter un marqueur à la séquence.
     *
     * @param marqueur Le marqueur.
     * @param timecode Timecode où doit se trouver le marqueur.
     */
    public void add(Marker marqueur, Timecode timecode) {
        this.liste_marqueur.add(new MarkerInSequence(marqueur, timecode));
    }

    /**
     * Retourne le nombre de séquence qu'il existe.
     *
     * @return Nombre de séquence.
     */
    public static int getSequenceNumber() {
        return nombre_sequence;
    }

    /**
     *
     * @param writer Flux où on écrit les données.
     * @param order
     */
    @Override
    public void toXML(PrintWriter writer, int order) {
        writer.append("\t<ClipProjectItem ObjectUID=\"" + this.getCurrentObjectURef() + "\" ClassID=\"" + this.classID + "\" Version=\"1\">\n");
        writer.append("\t\t<ProjectItem Version=\"1\">\n");
        writer.append("\t\t\t<Node Version=\"1\">\n");
        writer.append("\t\t\t\t<Properties Version=\"1\">\n");
        writer.append("\t\t\t\t\t<Column.PropertyText.Label>BE.Prefs.LabelColors.5</Column.PropertyText.Label>\n");
        writer.append("\t\t\t\t\t<project.icon.view.grid.order>" + order + "</project.icon.view.grid.order>\n");
        writer.append("\t\t\t\t</Properties>\n");
        writer.append("\t\t\t</Node>\n");
        writer.append("\t\t\t<Name>" + this.getName() + "</Name>\n");
        writer.append("\t\t</ProjectItem>\n");
        writer.append("\t\t<MasterClip ObjectURef=\"ad5bd5cb-4336-473d-a7f2-74386fbfd563\"/>\n");
        writer.append("\t</ClipProjectItem>\n");
    }

    /**
     *
     * @param writer
     */
    public void videoClip(PrintWriter writer) {
        writer.append("\t<VideoClip ObjectID=\"128\" ClassID=\"9308dbef-2440-4acb-9ab2-953b9a4e82ec\" Version=\"11\">\n");
        writer.append("\t\t<Clip Version=\"18\">\n");
        writer.append("\t\t\t<Node Version=\"1\">\n");
        writer.append("\t\t\t\t<Properties Version=\"1\">\n");
        writer.append("\t\t\t\t\t<BE.Prefs.SyntheticMedia.DefaultIsDropFrame>false</BE.Prefs.SyntheticMedia.DefaultIsDropFrame>\n");
        writer.append("\t\t\t\t\t<asl.clip.label.color>14910691</asl.clip.label.color>\n");
        writer.append("\t\t\t\t\t<asl.clip.label.name>BE.Prefs.LabelColors.3</asl.clip.label.name>\n");
        writer.append("\t\t\t\t</Properties>\n");
        writer.append("\t\t\t</Node>\n");
        writer.append("\t\t\t<Source ObjectRef=\"83\"/>\n");
        writer.append("\t\t\t<ClipID>b836a17f-9a92-4647-9fbc-2b020fc10552</ClipID>\n");
        writer.append("\t\t\t<InPoint>914457600000000</InPoint>\n");
        writer.append("\t\t\t<OutPoint>915727680000000</OutPoint>\n");
        writer.append("\t\t</Clip>\n");
        writer.append("\t</VideoClip>\n");
    }

    /**
     *
     * @param writer
     */
    public void clip(PrintWriter writer) {
        writer.append("\t<ClipLoggingInfo ObjectID=\"40\" ClassID=\"77ab7fdd-dcdf-465d-9906-7a330ca1e738\" Version=\"7\">\n");
        writer.append("\t	<MediaFrameRate>9223372036854775807</MediaFrameRate>\n");
        writer.append("\t</ClipLoggingInfo>\n");
        writer.append("\t<AudioComponentChain ObjectID=\"41\" ClassID=\"3cb131d1-d3c0-47ae-a19a-bdf75ea11674\" Version=\"3\">\n");
        writer.append("\t	<ComponentChain Version=\"2\">\n");
        writer.append("\t		<Components Version=\"1\">\n");
        writer.append("\t		</Components>\n");
        writer.append("\t	</ComponentChain>\n");
        writer.append("\t	<ChannelType>3</ChannelType>\n");
        writer.append("\t	<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t	<FrameRate>5292000</FrameRate>\n");
        writer.append("\t	<AutomationMode>1</AutomationMode>\n");
        writer.append("\t	<DefaultVol>true</DefaultVol>\n");
        writer.append("\t	<DefaultVolumeComponentID>1</DefaultVolumeComponentID>\n");
        writer.append("\t	<DefaultChannelVolumeComponentID>2</DefaultChannelVolumeComponentID>\n");
        writer.append("\t</AudioComponentChain>\n");
        writer.append("\t<AudioClip ObjectID=\"42\" ClassID=\"b8830d03-de02-41ee-84ec-fe566dc70cd9\" Version=\"8\">\n");
        writer.append("\t	<Clip Version=\"18\">\n");
        writer.append("\t		<Node Version=\"1\">\n");
        writer.append("\t			<Properties Version=\"1\">\n");
        writer.append("\t				<asl.clip.label.color>5814353</asl.clip.label.color>\n");
        writer.append("\t				<asl.clip.label.name>BE.Prefs.LabelColors.5</asl.clip.label.name>\n");
        writer.append("\t			</Properties>\n");
        writer.append("\t		</Node>\n");

        int ObjectRef = 48;

        writer.append("\t		<Source ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t		<ClipID>1811dde4-f008-48c9-8fdc-3f7261120218</ClipID>\n");
        writer.append("\t		<InUse>false</InUse>\n");
        writer.append("\t	</Clip>\n");
        writer.append("\t	<SecondaryContents Version=\"1\">\n");

        for (int i = 0; i <= 31; i++) {
            writer.append("\t\t\t<SecondaryContentItem Index=\"" + i + "\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        }

        writer.append("\t	</SecondaryContents>\n");
        writer.append("\t\t<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t</AudioClip>\n");
        writer.append("\t<VideoClip ObjectID=\"43\" ClassID=\"9308dbef-2440-4acb-9ab2-953b9a4e82ec\" Version=\"11\">\n");
        writer.append("\t	<Clip Version=\"18\">\n");
        writer.append("\t		<Node Version=\"1\">\n");
        writer.append("\t			<Properties Version=\"1\">\n");
        writer.append("\t				<asl.clip.label.color>5814353</asl.clip.label.color>\n");
        writer.append("\t				<asl.clip.label.name>BE.Prefs.LabelColors.5</asl.clip.label.name>\n");
        writer.append("\t			</Properties>\n");
        writer.append("\t		</Node>\n");
        writer.append("\t		<Source ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t		<ClipID>9cfa06ef-090e-43d2-bbc5-437803e2e0d6</ClipID>\n");
        writer.append("\t		<InUse>false</InUse>\n");
        writer.append("\t	</Clip>\n");
        writer.append("\t</VideoClip>\n");
        writer.append("\t<ClipChannelGroupVectorSerializer ObjectID=\"44\" ClassID=\"a3127a8c-95d4-456e-a7f5-171b3f922426\" Version=\"1\">\n");
        writer.append("\t	<ClipChannelVectors Version=\"1\">\n");
        writer.append("\t		<ClipChannelVectorItem Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t	</ClipChannelVectors>\n");
        writer.append("\t</ClipChannelGroupVectorSerializer>\n");
    }

    /**
     *
     * @param writer
     */
    public void audioSequenceSource(PrintWriter writer) {
        int ObjectID = 48;
        int ObjectRef = ObjectID;

        writer.append("\t<AudioSequenceSource ObjectID=\"" + (ObjectID++) + "\" ClassID=\"e8d4cc83-38cb-491f-9d94-e5f7e3b205ee\" Version=\"7\">\n");
        writer.append("\t	<SequenceSource Version=\"4\">\n");
        writer.append("\t		<Content Version=\"10\">\n");
        writer.append("\t		</Content>\n");
        writer.append("\t		<Sequence ObjectURef=\"9d8a2607-057b-47be-8e25-56261a940524\"/>\n");
        writer.append("\t	</SequenceSource>\n");
        writer.append("\t	<OriginalDuration>0</OriginalDuration>\n");
        writer.append("\t</AudioSequenceSource>\n");

        for (int i = 0; i <= 31; i++) {
            writer.append("\t<SecondaryContent ObjectID=\"" + (ObjectID++) + "\" ClassID=\"f9d004b5-cb04-4e2f-af6f-64fadc2c4be9\" Version=\"1\">\n");
            writer.append("\t\t<Content ObjectRef=\"" + ObjectRef + "\"/>\n");
            writer.append("\t\t<ChannelIndex>" + i + "</ChannelIndex>\n");
            writer.append("\t</SecondaryContent>\n");
        }

        writer.append("\t<VideoSequenceSource ObjectID=\"" + (ObjectID++) + "\" ClassID=\"4752dfa9-7a7e-4a3b-a25b-cafde1a8d036\" Version=\"3\">\n");
        writer.append("\t	<SequenceSource Version=\"4\">\n");
        writer.append("\t		<Content Version=\"10\">\n");
        writer.append("\t		</Content>\n");
        writer.append("\t		<Sequence ObjectURef=\"9d8a2607-057b-47be-8e25-56261a940524\"/>\n");
        writer.append("\t	</SequenceSource>\n");
        writer.append("\t	<OriginalDuration>0</OriginalDuration>\n");
        writer.append("\t</VideoSequenceSource>\n");
        writer.append("\t<ClipChannelVectorSerializer ObjectID=\"" + (ObjectID++) + "\" ClassID=\"333d203b-3a53-4195-8894-fc7523ff3dc7\" Version=\"1\">\n");
        writer.append("\t\t<ClipChannels Version=\"1\">\n");

        for (int i = 0; i <= 15; i++) {
            writer.append("\t\t\t<ClipChannelItem Index=\"" + i + "\" ObjectRef=\"" + ((ObjectID++) + 1) + "\"/>\n");
        }

        writer.append("\t	</ClipChannels>\n");
        writer.append("\t	<ChannelType>3</ChannelType>\n");
        writer.append("\t</ClipChannelVectorSerializer>\n");
    }

    /**
     *
     * @param writer
     */
    public void sequence(PrintWriter writer) {
        long div = 0;

        switch (this.framerate) {
            case F24 ->
                div = 10584000000L;
            case F25 ->
                div = 10160640000L;
        }

        writer.append("\t<Sequence ObjectUID=\"9d8a2607-057b-47be-8e25-56261a940524\" ClassID=\"6a15d903-8739-11d5-af2d-9b7855ad8974\" Version=\"11\">\n");
        writer.append("\t\t<Node Version=\"1\">\n");
        writer.append("\t\t\t<Properties Version=\"1\">\n");
        writer.append("\t\t\t\t<AM.TrackScrollPosition>0</AM.TrackScrollPosition>\n");
        writer.append("\t\t\t\t<AM.TrackVScrollPosition>0</AM.TrackVScrollPosition>\n");
        writer.append("\t\t\t\t<AMM.CurrentSolo>[]</AMM.CurrentSolo>\n");
        writer.append("\t\t\t\t<HSL.TimelinePatchingAndTargeting.AudioPatches706bcde2_45_736e_45_6997_45_3385_45_a59f0000001b>[{\"mNumber\":0,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0}]</HSL.TimelinePatchingAndTargeting.AudioPatches706bcde2_45_736e_45_6997_45_3385_45_a59f0000001b>\n");
        writer.append("\t\t\t\t<HSL.TimelinePatchingAndTargeting.VideoPatches405230c6_45_7438_45_1002_45_93de_45_aac30000000f>[{\"mNumber\":0,\"mState\":0}]</HSL.TimelinePatchingAndTargeting.VideoPatches405230c6_45_7438_45_1002_45_93de_45_aac30000000f>\n");

        // Position curseur dans la séquence.
        if (position != null) {
            writer.append("\t\t\t\t<MZ.EditLine>" + (position.toImage() * div) + "</MZ.EditLine>\n");
        } else {
            writer.append("\t\t\t\t<MZ.EditLine>0</MZ.EditLine>\n");
        }
        writer.append("\t\t\t\t<MZ.Sequence.AudioTimeDisplayFormat>200</MZ.Sequence.AudioTimeDisplayFormat>\n");
        writer.append("\t\t\t\t<MZ.Sequence.EditingModeGUID>9678af98-a7b7-4bdb-b477-7ac9c8df4a4e</MZ.Sequence.EditingModeGUID>\n");
        writer.append("\t\t\t\t<MZ.Sequence.PreviewFrameSizeHeight>1080</MZ.Sequence.PreviewFrameSizeHeight>\n");
        writer.append("\t\t\t\t<MZ.Sequence.PreviewFrameSizeWidth>1920</MZ.Sequence.PreviewFrameSizeWidth>\n");
        writer.append("\t\t\t\t<MZ.Sequence.PreviewRenderingClassID>1297106761</MZ.Sequence.PreviewRenderingClassID>\n");
        writer.append("\t\t\t\t<MZ.Sequence.PreviewRenderingPresetCodec>1297107278</MZ.Sequence.PreviewRenderingPresetCodec>\n");
        writer.append("\t\t\t\t<MZ.Sequence.PreviewRenderingPresetPath>EncoderPresets\\SequencePreview\\9678af98-a7b7-4bdb-b477-7ac9c8df4a4e\\I-Frame Only MPEG.epr</MZ.Sequence.PreviewRenderingPresetPath>\n");
        writer.append("\t\t\t\t<MZ.Sequence.PreviewUseMaxBitDepth>false</MZ.Sequence.PreviewUseMaxBitDepth>\n");
        writer.append("\t\t\t\t<MZ.Sequence.PreviewUseMaxRenderQuality>false</MZ.Sequence.PreviewUseMaxRenderQuality>\n");

        String display_format = null;

        switch (this.framerate) {
            case F24 ->
                display_format = "100";
            case F25 ->
                display_format = "101";
            case F23976 ->
                display_format = "102";
            case F2997 ->
                display_format = "102";
        }

        writer.append("\t\t\t\t<MZ.Sequence.VideoTimeDisplayFormat>" + display_format + "</MZ.Sequence.VideoTimeDisplayFormat>\n");
        writer.append("\t\t\t\t<MZ.WorkInPoint>0</MZ.WorkInPoint>\n");
        writer.append("\t\t\t\t<MZ.WorkOutPoint>15240960000000</MZ.WorkOutPoint>\n");

        if (this.start_timecode != null && this.start_timecode.toImage() != 0) {
            writer.append("\t\t\t\t<MZ.ZeroPoint>" + (this.start_timecode.toImage() * div) + "</MZ.ZeroPoint>\n");
        }

        writer.append("\t\t\t\t<Monitor.ProgramZoomIn>0</Monitor.ProgramZoomIn>\n");
        writer.append("\t\t\t\t<Monitor.ProgramZoomOut>0</Monitor.ProgramZoomOut>\n");
        writer.append("\t\t\t\t<TL.SQAVDividerPosition>0.209354117513</TL.SQAVDividerPosition>\n");
        writer.append("\t\t\t\t<TL.SQAudioVisibleBase>0</TL.SQAudioVisibleBase>\n");
        writer.append("\t\t\t\t<TL.SQHeaderWidth>236</TL.SQHeaderWidth>\n");
        writer.append("\t\t\t\t<TL.SQHideShyTracks>0</TL.SQHideShyTracks>\n");
        writer.append("\t\t\t\t<TL.SQTimePerPixel>0.52310374891020051</TL.SQTimePerPixel>\n");
        writer.append("\t\t\t\t<TL.SQVideoVisibleBase>0</TL.SQVideoVisibleBase>\n");
        writer.append("\t\t\t\t<TL.SQVisibleBaseTime>0</TL.SQVisibleBaseTime>\n");
        writer.append("\t\t\t</Properties>\n");
        writer.append("\t\t</Node>\n");

        int ObjectRef = 100;

        if (!this.liste_marqueur.isEmpty()) {
            writer.append("\t\t<MarkerOwner Version=\"1\">\n");
            markers_id = ObjectRef++;
            writer.append("\t\t\t<Markers ObjectRef=\"" + markers_id + "\">\n");
            writer.append("\t\t</MarkerOwner>\n");
        }

        writer.append("\t\t<PersistentGroupContainer Version=\"1\">\n");
        writer.append("\t\t\t<LinkContainer Version=\"1\">\n");
        writer.append("\t\t\t</LinkContainer>\n");
        writer.append("\t\t</PersistentGroupContainer>\n");

        writer.append("\t\t<TrackGroups Version=\"1\">\n");
        writer.append("\t\t\t<TrackGroup Version=\"1\" Index=\"0\">\n");
        writer.append("\t\t\t\t<First>80b8e3d5-6dca-4195-aefb-cb5f407ab009</First>\n");
        writer.append("\t\t\t\t<Second ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t\t\t</TrackGroup>\n");
        writer.append("\t\t\t<TrackGroup Version=\"1\" Index=\"1\">\n");
        writer.append("\t\t\t\t<First>228cda18-3625-4d2d-951e-348879e4ed93</First>\n");
        writer.append("\t\t\t\t<Second ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t\t\t</TrackGroup>\n");
        writer.append("\t\t</TrackGroups>\n");
        writer.append("\t\t<ID>1</ID>\n");
        writer.append("\t\t<Name>" + this.getName() + "</Name>\n");
        writer.append("\t\t<PreviewFormatIdentifier>b0f75bf9-a1fa-f37f-f0e2-3a24000000fa</PreviewFormatIdentifier>\n");
        writer.append("\t</Sequence>\n");

        String classID = "5c89aa7a-89a6-4483-becd-f2b1def42316";
        int ObjectID = 84;

        for (int i = 0; i <= 15; i++) {
            writer.append("\t<ClipChannelSerializer ObjectID=\"" + (ObjectID++) + "\" ClassID=\"" + classID + "\" Version=\"1\">\n");
            writer.append("\t	<SourceClipIndex>" + (0) + "</SourceClipIndex>\n");
            writer.append("\t	<mSourceChannelIndex>" + (i) + "</mSourceChannelIndex>\n");
            writer.append("\t</ClipChannelSerializer>\n");
        }
    }

    /**
     * Définit le timecode début de la séquence.
     *
     * @param start_timecode Timecode début.
     */
    public void setStartTimecode(Timecode start_timecode) {
        this.start_timecode = start_timecode;
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
    public void setResolution(ResolutionStandard resolution) {
        this.largeur = resolution.getLargeur();
        this.hauteur = resolution.getHauteur();
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
     * @param writer
     */
    public void audioTrackGroup(PrintWriter writer) {
        int ObjectID = 100;
        int ObjectRef = 103;

        writer.append("\t<AudioTrackGroup ObjectID=\"" + (ObjectID++) + "\" ClassID=\"9b9238b9-53a8-4cc3-b03f-b36246d052e6\" Version=\"6\">\n");
        writer.append("\t	<TrackGroup Version=\"1\">\n");
        writer.append("\t		<Tracks Version=\"1\">\n");
        writer.append("\t			<Track Index=\"0\" ObjectURef=\"4aa95753-fe3b-4c32-a051-414fd49f3f62\"/>\n");
        writer.append("\t			<Track Index=\"1\" ObjectURef=\"05c90639-4a41-42a9-ae1f-b2178d20a8ab\"/>\n");
        writer.append("\t		</Tracks>\n");
        writer.append("\t		<FrameRate>5292000</FrameRate>\n");
        writer.append("\t		<NextTrackID>18</NextTrackID>\n");
        writer.append("\t	</TrackGroup>\n");
        writer.append("\t	<MasterTrack ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t	<AutomationSafeFlags>0</AutomationSafeFlags>\n");
        writer.append("\t	<ID>593368e4-e82f-4796-b8dc-2932326d6373</ID>\n");
        writer.append("\t	<NumAdaptiveChannels>16</NumAdaptiveChannels>\n");
        writer.append("\t</AudioTrackGroup>\n");
        writer.append("\t<VideoTrackGroup ObjectID=\"" + (ObjectID++) + "\" ClassID=\"9e9abf7a-0918-49c2-91ae-991b5dde77bb\" Version=\"10\">\n");
        writer.append("\t	<TrackGroup Version=\"1\">\n");
        writer.append("\t		<Tracks Version=\"1\">\n");
        writer.append("\t			<Track Index=\"0\" ObjectURef=\"9b6d8c60-7c45-411d-ba73-4d14d6a2fa4d\"/>\n");
        writer.append("\t		</Tracks>\n");
        long framerate_ = (long) (254016000000L / this.framerate.getValeur());
        writer.append("\t		<FrameRate>" + framerate_ + "</FrameRate>\n");
        writer.append("\t		<NextTrackID>2</NextTrackID>\n");
        writer.append("\t	</TrackGroup>\n");
        writer.append("\t	<FrameRect>0,0," + this.largeur + "," + this.hauteur + "</FrameRect>\n");
        writer.append("\t	<PixelAspectRatio>1,1</PixelAspectRatio>\n");
        writer.append("\t	<FieldType>0</FieldType>\n");
        writer.append("\t	<AllowLinearCompositing>true</AllowLinearCompositing>\n");
        writer.append("\t	<ImmersiveVideoVRConfiguration>{\"capturedHorizontalView\":0,\"capturedVerticalView\":0,\"fieldOfHorizontalView\":90,\"fieldOfVerticalView\":60,\"projectionType\":0,\"stereoscopicEye\":0,\"stereoscopicType\":0,\"version\":2}</ImmersiveVideoVRConfiguration>\n");
        writer.append("\t</VideoTrackGroup>\n");

        if (!this.liste_marqueur.isEmpty()) {
            for (MarkerInSequence marqueur : this.liste_marqueur) {
                marqueur.toXML(file);
            }
        }

        //if (!this.liste_clip.isEmpty()) {
        writer.append("\t<VideoStream ObjectID=\"" + (ObjectID++) + "\" ClassID=\"a36e4719-3ec6-4a0c-ab11-8b4aab377aa5\" Version=\"15\">\n");
        writer.append("\t	<IsStill>true</IsStill>\n");
        writer.append("\t	<FrameRate>10584000000</FrameRate>\n");
        writer.append("\t	<FrameRect>0,0," + this.largeur + "," + this.hauteur + "</FrameRect>\n");
        writer.append("\t	<Duration>10973491200000000</Duration>\n");
        writer.append("\t	<AlphaType>1</AlphaType>\n");
        writer.append("\t	<CodecType>1416197228</CodecType>\n");
        writer.append("\t</VideoStream>\n");
        //}

        if (!this.liste_marqueur.isEmpty()) {
            writer.append("\t<Markers ObjectID=\"" + markers_id + "\" ClassID=\"bee50706-b524-416c-9f03-b596ce5f6866\" Version=\"3\">\n");
            writer.append("\t\t<Markers Version=\"1\">\n");
            writer.append("\t\t\t<Marker Version=\"1\" Index=\"0\">\n");
            writer.append("\t\t\t\t<First>71550971-39c2-2ea3-131c-c2a500000024</First>\n");
            writer.append("\t\t\t\t<Second ObjectRef=\"105\"/>\n");
            writer.append("\t\t\t</Marker>\n");
            writer.append("\t\t\t<Marker Version=\"1\" Index=\"1\">\n");
            writer.append("\t\t\t\t<First>d60de2c2-4e47-b0ea-b911-14be00000024</First>\n");
            writer.append("\t\t\t\t<Second ObjectRef=\"106\"/>\n");
            writer.append("\t\t\t</Marker>\n");
            writer.append("\t\t</Markers>\n");
            writer.append("\t\t<ByGUID>byGUID</ByGUID>\n");
            writer.append("\t\t<LastMetadataState>00000000-0000-0000-0000-000000000000</LastMetadataState>\n");
            writer.append("\t\t<LastContentState>00000000-0000-0000-0000-000000000000</LastContentState>\n");
            writer.append("\t</Markers>\n");
        }

        writer.append("\t<AudioClipTrack ObjectUID=\"4aa95753-fe3b-4c32-a051-414fd49f3f62\" ClassID=\"097f6203-99ae-11d5-84f2-8cf14bde7040\" Version=\"6\">\n");
        writer.append("\t\t<ClipTrack Version=\"2\">\n");
        writer.append("\t\t\t<Track Version=\"3\">\n");
        writer.append("\t\t\t\t<Node Version=\"1\">\n");
        writer.append("\t				<Properties Version=\"1\">\n");
        writer.append("\t					<MZ.SourceTrackNumber>0</MZ.SourceTrackNumber>\n");
        writer.append("\t					<MZ.SourceTrackState>0</MZ.SourceTrackState>\n");
        writer.append("\t					<MZ.TrackTargeted>1</MZ.TrackTargeted>\n");
        writer.append("\t					<TL.SQTrackAudioKeyframeStyle>0</TL.SQTrackAudioKeyframeStyle>\n");
        writer.append("\t					<TL.SQTrackExpanded>0</TL.SQTrackExpanded>\n");
        writer.append("\t					<TL.SQTrackExpandedHeight>25</TL.SQTrackExpandedHeight>\n");
        writer.append("\t					<TL.SQTrackShy>0</TL.SQTrackShy>\n");
        writer.append("\t				</Properties>\n");
        writer.append("\t			</Node>\n");
        writer.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        writer.append("\t			<Index>0</Index>\n");
        writer.append("\t			<ID>2</ID>\n");
        writer.append("\t			<IsLocked>false</IsLocked>\n");
        writer.append("\t			<IsSyncLocked>true</IsSyncLocked>\n");
        writer.append("\t			<IsMuted>false</IsMuted>\n");
        writer.append("\t		</Track>\n");
        writer.append("\t		<ClipItems Version=\"3\">\n");
        writer.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        writer.append("\t			<Index>0</Index>\n");
        writer.append("\t		</ClipItems>\n");
        writer.append("\t		<TransitionItems Version=\"3\">\n");
        writer.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        writer.append("\t			<Index>0</Index>\n");
        writer.append("\t\t\t</TransitionItems>\n");
        writer.append("\t\t</ClipTrack>\n");
        writer.append("\t\t<AudioTrack Version=\"11\">\n");
        writer.append("\t\t\t<ComponentOwner Version=\"1\">\n");
        writer.append("\t\t\t\t<Components ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t\t\t</ComponentOwner>\n");
        writer.append("\t\t\t<Panner ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t\t\t<SubType>1</SubType>\n");
        writer.append("\t\t\t<AutomationMode>1</AutomationMode>\n");
        writer.append("\t\t\t<Assign>1</Assign>\n");
        writer.append("\t\t\t<ChannelType>0</ChannelType>\n");
        writer.append("\t\t<FrameRate>5292000</FrameRate>\n");
        writer.append("\t\t\t<NextPannerID>4294967279</NextPannerID>\n");
        writer.append("\t\t\t<Solo>0</Solo>\n");
        writer.append("\t\t\t<MutedBySolo>0</MutedBySolo>\n");
        writer.append("\t\t\t<ID>ec0c64a5-73fe-4a33-beb3-0fe37e1e3cf9</ID>\n");
        writer.append("\t	</AudioTrack>\n");
        writer.append("\t	<RecordChannel>0</RecordChannel>\n");
        writer.append("\t</AudioClipTrack>\n");
        writer.append("\t<AudioClipTrack ObjectUID=\"05c90639-4a41-42a9-ae1f-b2178d20a8ab\" ClassID=\"097f6203-99ae-11d5-84f2-8cf14bde7040\" Version=\"6\">\n");
        writer.append("\t	<ClipTrack Version=\"2\">\n");
        writer.append("\t		<Track Version=\"3\">\n");
        writer.append("\t			<Node Version=\"1\">\n");
        writer.append("\t				<Properties Version=\"1\">\n");
        writer.append("\t					<MZ.SourceTrackNumber>-1</MZ.SourceTrackNumber>\n");
        writer.append("\t					<MZ.SourceTrackState>0</MZ.SourceTrackState>\n");
        writer.append("\t					<MZ.TrackTargeted>1</MZ.TrackTargeted>\n");
        writer.append("\t					<TL.SQTrackAudioKeyframeStyle>0</TL.SQTrackAudioKeyframeStyle>\n");
        writer.append("\t					<TL.SQTrackExpanded>0</TL.SQTrackExpanded>\n");
        writer.append("\t					<TL.SQTrackExpandedHeight>25</TL.SQTrackExpandedHeight>\n");
        writer.append("\t					<TL.SQTrackShy>0</TL.SQTrackShy>\n");
        writer.append("\t				</Properties>\n");
        writer.append("\t			</Node>\n");
        writer.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        writer.append("\t			<Index>1</Index>\n");
        writer.append("\t			<ID>3</ID>\n");
        writer.append("\t			<IsLocked>false</IsLocked>\n");
        writer.append("\t			<IsSyncLocked>true</IsSyncLocked>\n");
        writer.append("\t			<IsMuted>false</IsMuted>\n");
        writer.append("\t		</Track>\n");
        writer.append("\t		<ClipItems Version=\"3\">\n");
        writer.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        writer.append("\t			<Index>1</Index>\n");
        writer.append("\t		</ClipItems>\n");
        writer.append("\t		<TransitionItems Version=\"3\">\n");
        writer.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        writer.append("\t			<Index>1</Index>\n");
        writer.append("\t		</TransitionItems>\n");
        writer.append("\t	</ClipTrack>\n");
        writer.append("\t	<AudioTrack Version=\"11\">\n");
        writer.append("\t		<ComponentOwner Version=\"1\">\n");
        writer.append("\t			<Components ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t		</ComponentOwner>\n");
        writer.append("\t		<Panner ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t		<SubType>1</SubType>\n");
        writer.append("\t		<AutomationMode>1</AutomationMode>\n");
        writer.append("\t		<Assign>1</Assign>\n");
        writer.append("\t		<ChannelType>0</ChannelType>\n");
        writer.append("\t		<FrameRate>5292000</FrameRate>\n");
        writer.append("\t		<NextPannerID>4294967279</NextPannerID>\n");
        writer.append("\t		<Solo>0</Solo>\n");
        writer.append("\t		<MutedBySolo>0</MutedBySolo>\n");
        writer.append("\t		<ID>120bceae-452c-496e-8bd1-2d7a0daea3bd</ID>\n");
        writer.append("\t	</AudioTrack>\n");
        writer.append("\t	<RecordChannel>0</RecordChannel>\n");
        writer.append("\t</AudioClipTrack>\n");
        writer.append("\t<AudioMixTrack ObjectID=\"" + (ObjectID++) + "\" ClassID=\"4b1d8400-e89e-11d5-abc4-a1a13b1e80a0\" Version=\"4\">\n");
        writer.append("\t	<AudioTrack Version=\"11\">\n");
        writer.append("\t		<ComponentOwner Version=\"1\">\n");
        writer.append("\t			<Components ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t		</ComponentOwner>\n");
        writer.append("\t		<Panner ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t		<SubType>3</SubType>\n");
        writer.append("\t		<AutomationMode>1</AutomationMode>\n");
        writer.append("\t		<Assign>0</Assign>\n");
        writer.append("\t		<ChannelType>3</ChannelType>\n");
        writer.append("\t		<FrameRate>5292000</FrameRate>\n");
        writer.append("\t		<NextPannerID>4294967279</NextPannerID>\n");
        writer.append("\t		<Solo>0</Solo>\n");
        writer.append("\t		<MutedBySolo>0</MutedBySolo>\n");
        writer.append("\t		<ID>e6ccbd27-ba07-4342-96f2-573fafa9646f</ID>\n");
        writer.append("\t	</AudioTrack>\n");
        writer.append("\t	<Track Version=\"3\">\n");
        writer.append("\t		<Node Version=\"1\">\n");
        writer.append("\t			<Properties Version=\"1\">\n");
        writer.append("\t				<TL.SQTrackAudioKeyframeStyle>2</TL.SQTrackAudioKeyframeStyle>\n");
        writer.append("\t				<TL.SQTrackExpanded>0</TL.SQTrackExpanded>\n");
        writer.append("\t				<TL.SQTrackExpandedHeight>25</TL.SQTrackExpandedHeight>\n");
        writer.append("\t				<TL.SQTrackShy>0</TL.SQTrackShy>\n");
        writer.append("\t			</Properties>\n");
        writer.append("\t		</Node>\n");
        writer.append("\t		<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        writer.append("\t		<Index>0</Index>\n");
        writer.append("\t		<ID>1</ID>\n");
        writer.append("\t		<IsLocked>false</IsLocked>\n");
        writer.append("\t		<IsSyncLocked>true</IsSyncLocked>\n");
        writer.append("\t		<IsMuted>false</IsMuted>\n");
        writer.append("\t	</Track>\n");
        writer.append("\t	<Inlet ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t</AudioMixTrack>\n");
        writer.append("\t<VideoClipTrack ObjectUID=\"9b6d8c60-7c45-411d-ba73-4d14d6a2fa4d\" ClassID=\"f68dcd81-8805-11d5-af2d-9bfa89d4ddd4\" Version=\"1\">\n");
        writer.append("\t	<ClipTrack Version=\"2\">\n");
        writer.append("\t		<Track Version=\"3\">\n");
        writer.append("\t			<Node Version=\"1\">\n");
        writer.append("\t				<Properties Version=\"1\">\n");
        writer.append("\t					<MZ.SourceTrackNumber>0</MZ.SourceTrackNumber>\n");
        writer.append("\t					<MZ.SourceTrackState>" + ((!this.liste_clip.isEmpty()) ? "2" : "0") + "</MZ.SourceTrackState>\n");
        writer.append("\t					<MZ.TrackTargeted>1</MZ.TrackTargeted>\n");
        writer.append("\t					<TL.SQTrackExpanded>0</TL.SQTrackExpanded>\n");
        writer.append("\t					<TL.SQTrackExpandedHeight>25</TL.SQTrackExpandedHeight>\n");
        writer.append("\t					<TL.SQTrackShy>0</TL.SQTrackShy>\n");
        writer.append("\t				</Properties>\n");
        writer.append("\t			</Node>\n");
        writer.append("\t			<MediaType>228cda18-3625-4d2d-951e-348879e4ed93</MediaType>\n");
        writer.append("\t			<Index>0</Index>\n");
        writer.append("\t			<ID>1</ID>\n");
        writer.append("\t			<IsLocked>false</IsLocked>\n");
        writer.append("\t			<IsSyncLocked>true</IsSyncLocked>\n");
        writer.append("\t			<IsMuted>false</IsMuted>\n");
        writer.append("\t		</Track>\n");
        writer.append("\t		<ClipItems Version=\"3\">\n");

        // Ajoute dans la séquence ici l'item/clip.
        if (!this.liste_clip.isEmpty()) {
            writer.append("\t\t\t\t<TrackItems Version=\"1\">\n");
            writer.append("\t\t\t\t\t<TrackItem Index=\"0\" ObjectRef=\"111\"/>\n");
            writer.append("\t\t\t\t</TrackItems>\n");
        }

        writer.append("\t			<MediaType>228cda18-3625-4d2d-951e-348879e4ed93</MediaType>\n");
        writer.append("\t			<Index>0</Index>\n");
        writer.append("\t		</ClipItems>\n");
        writer.append("\t		<TransitionItems Version=\"3\">\n");
        writer.append("\t			<MediaType>228cda18-3625-4d2d-951e-348879e4ed93</MediaType>\n");
        writer.append("\t			<Index>0</Index>\n");
        writer.append("\t		</TransitionItems>\n");
        writer.append("\t	</ClipTrack>\n");
        writer.append("\t</VideoClipTrack>\n");

        //ObjectID= 104;
        writer.append("\t<AudioComponentChain ObjectID=\"" + (ObjectID++) + "\" ClassID=\"3cb131d1-d3c0-47ae-a19a-bdf75ea11674\" Version=\"3\">\n");
        writer.append("\t	<ComponentChain Version=\"2\">\n");
        writer.append("\t		<Components Version=\"1\">\n");
        writer.append("\t			<Component Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t			<Component Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t		</Components>\n");
        writer.append("\t	</ComponentChain>\n");
        writer.append("\t	<ChannelType>0</ChannelType>\n");
        writer.append("\t	<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t	<FrameRate>5292000</FrameRate>\n");
        writer.append("\t	<AutomationMode>1</AutomationMode>\n");
        writer.append("\t</AudioComponentChain>\n");
        writer.append("\t<MonoTo16ChannelPanProcessor ObjectID=\"" + (ObjectID++) + "\" ClassID=\"8c9778ad-af4e-4e98-99fe-542f4eda2dac\" Version=\"2\">\n");
        writer.append("\t	<DirectPanProcessor Version=\"2\">\n");
        writer.append("\t		<PanProcessor Version=\"3\">\n");
        writer.append("\t			<AudioComponent Version=\"3\">\n");
        writer.append("\t				<Component Version=\"5\">\n");
        writer.append("\t					<Params Version=\"1\">\n");
        writer.append("\t						<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t					</Params>\n");
        writer.append("\t					<ID>4294967280</ID>\n");
        writer.append("\t					<Bypass>false</Bypass>\n");
        writer.append("\t					<Intrinsic>false</Intrinsic>\n");
        writer.append("\t				</Component>\n");
        writer.append("\t				<ChannelType>0</ChannelType>\n");
        writer.append("\t				<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t				<FrameRate>5292000</FrameRate>\n");
        writer.append("\t				<AudioComponentType>0</AudioComponentType>\n");
        writer.append("\t			</AudioComponent>\n");
        writer.append("\t			<OutputAudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</OutputAudioChannelLayout>\n");
        writer.append("\t		</PanProcessor>\n");
        writer.append("\t		<Matrix>[[0,[0]]]</Matrix>\n");
        writer.append("\t	</DirectPanProcessor>\n");
        writer.append("\t</MonoTo16ChannelPanProcessor>\n");
        writer.append("\t<AudioComponentChain ObjectID=\"" + (ObjectID++) + "\" ClassID=\"3cb131d1-d3c0-47ae-a19a-bdf75ea11674\" Version=\"3\">\n");
        writer.append("\t	<ComponentChain Version=\"2\">\n");
        writer.append("\t		<Components Version=\"1\">\n");
        writer.append("\t			<Component Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t			<Component Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t		</Components>\n");
        writer.append("\t	</ComponentChain>\n");
        writer.append("\t	<ChannelType>0</ChannelType>\n");
        writer.append("\t	<FrameRate>5292000</FrameRate>\n");
        writer.append("\t	<AutomationMode>1</AutomationMode>\n");
        writer.append("\t	<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t</AudioComponentChain>\n");
        writer.append("\t<MonoTo16ChannelPanProcessor ObjectID=\"" + (ObjectID++) + "\" ClassID=\"8c9778ad-af4e-4e98-99fe-542f4eda2dac\" Version=\"2\">\n");
        writer.append("\t	<DirectPanProcessor Version=\"2\">\n");
        writer.append("\t		<PanProcessor Version=\"3\">\n");
        writer.append("\t			<AudioComponent Version=\"3\">\n");
        writer.append("\t				<Component Version=\"5\">\n");
        writer.append("\t					<Params Version=\"1\">\n");
        writer.append("\t						<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t					</Params>\n");
        writer.append("\t					<ID>4294967280</ID>\n");
        writer.append("\t					<Bypass>false</Bypass>\n");
        writer.append("\t					<Intrinsic>false</Intrinsic>\n");
        writer.append("\t				</Component>\n");
        writer.append("\t				<ChannelType>0</ChannelType>\n");
        writer.append("\t				<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t				<FrameRate>5292000</FrameRate>\n");
        writer.append("\t				<AudioComponentType>0</AudioComponentType>\n");
        writer.append("\t			</AudioComponent>\n");
        writer.append("\t			<OutputAudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</OutputAudioChannelLayout>\n");
        writer.append("\t		</PanProcessor>\n");
        writer.append("\t		<Matrix>[[0,[0]]]</Matrix>\n");
        writer.append("\t	</DirectPanProcessor>\n");
        writer.append("\t</MonoTo16ChannelPanProcessor>\n");
        writer.append("\t<AudioComponentChain ObjectID=\"" + (ObjectID++) + "\" ClassID=\"3cb131d1-d3c0-47ae-a19a-bdf75ea11674\" Version=\"3\">\n");
        writer.append("\t	<ComponentChain Version=\"2\">\n");
        writer.append("\t		<Components Version=\"1\">\n");
        writer.append("\t			<Component Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t			<Component Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t		</Components>\n");
        writer.append("\t	</ComponentChain>\n");
        writer.append("\t	<ChannelType>3</ChannelType>\n");
        writer.append("\t	<FrameRate>5292000</FrameRate>\n");
        writer.append("\t	<AutomationMode>1</AutomationMode>\n");
        writer.append("\t	<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t</AudioComponentChain>\n");
        writer.append("\t<DefaultPanProcessor ObjectID=\"" + (ObjectID++) + "\" ClassID=\"33a94282-ee2c-11d5-abc4-c1cd7f9e3c10\" Version=\"2\">\n");
        writer.append("\t	<PanProcessor Version=\"3\">\n");
        writer.append("\t		<AudioComponent Version=\"3\">\n");
        writer.append("\t			<Component Version=\"5\">\n");
        writer.append("\t				<ID>4294967280</ID>\n");
        writer.append("\t				<Bypass>false</Bypass>\n");
        writer.append("\t				<Intrinsic>false</Intrinsic>\n");
        writer.append("\t			</Component>\n");
        writer.append("\t			<ChannelType>3</ChannelType>\n");
        writer.append("\t			<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t			<FrameRate>5292000</FrameRate>\n");
        writer.append("\t			<AudioComponentType>0</AudioComponentType>\n");
        writer.append("\t		</AudioComponent>\n");
        writer.append("\t	</PanProcessor>\n");
        writer.append("\t	<DefaultPannerInputChannelType>3</DefaultPannerInputChannelType>\n");
        writer.append("\t	<DefaultPannerOutputChannelType>3</DefaultPannerOutputChannelType>\n");
        writer.append("\t</DefaultPanProcessor>\n");
        writer.append("\t<AudioTrackInlet ObjectID=\"" + (ObjectID++) + "\" ClassID=\"be3af080-e8c6-11d5-abc4-a1c6d5dee670\" Version=\"3\">\n");
        writer.append("\t	<Sources Version=\"1\">\n");
        writer.append("\t		<Source Index=\"0\" ObjectURef=\"4aa95753-fe3b-4c32-a051-414fd49f3f62\"/>\n");
        writer.append("\t		<Source Index=\"1\" ObjectURef=\"05c90639-4a41-42a9-ae1f-b2178d20a8ab\"/>\n");
        writer.append("\t	</Sources>\n");
        writer.append("\t	<ChannelType>3</ChannelType>\n");
        writer.append("\t	<FrameRate>5292000</FrameRate>\n");
        writer.append("\t	<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t</AudioTrackInlet>\n");

        // "VideoClipTrackItem"
        if (!this.liste_clip.isEmpty()) {
            writer.append("\t<VideoClipTrackItem ObjectID=\"" + (ObjectID++) + "\" ClassID=\"368b0406-29e3-4923-9fcd-094fbf9a1089\" Version=\"5\">\n");
            writer.append("\t	<ClipTrackItem Version=\"6\">\n");
            writer.append("\t		<ComponentOwner Version=\"1\">\n");
            writer.append("\t			<Components ObjectRef=\"120\"/>\n");
            writer.append("\t		</ComponentOwner>\n");
            writer.append("\t		<TrackItem Version=\"3\">\n");
            writer.append("\t			<Start>0</Start>\n");
            writer.append("\t			<End>1270080000000</End>\n");
            writer.append("\t		</TrackItem>\n");
            writer.append("\t		<SubClip ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
            writer.append("\t	</ClipTrackItem>\n");
            writer.append("\t	<FrameRect>0,0," + this.largeur + "," + this.hauteur + "</FrameRect>\n");
            writer.append("\t	<PixelAspectRatio>1,1</PixelAspectRatio>\n");
            writer.append("\t</VideoClipTrackItem>\n");
        }

        writer.append("\t<AudioFader ObjectID=\"" + (ObjectID++) + "\" ClassID=\"1a38c583-ed5c-11d5-abc4-c1cbf61ec590\" Version=\"3\">\n");
        writer.append("\t	<AudioComponent Version=\"3\">\n");
        writer.append("\t		<Component Version=\"5\">\n");
        writer.append("\t			<Params Version=\"1\">\n");
        writer.append("\t				<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t				<Param Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t			</Params>\n");
        writer.append("\t			<ID>1</ID>\n");
        writer.append("\t			<Bypass>false</Bypass>\n");
        writer.append("\t			<Intrinsic>false</Intrinsic>\n");
        writer.append("\t		</Component>\n");
        writer.append("\t		<AudioComponentType>1</AudioComponentType>\n");
        writer.append("\t		<FrameRate>5292000</FrameRate>\n");
        writer.append("\t		<ChannelType>0</ChannelType>\n");
        writer.append("\t		<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t	</AudioComponent>\n");
        writer.append("\t</AudioFader>\n");
        writer.append("\t<AudioMeter ObjectID=\"" + (ObjectID++) + "\" ClassID=\"72ea4700-f615-11d5-abc4-c186585e63e0\" Version=\"2\">\n");
        writer.append("\t	<AudioComponent Version=\"3\">\n");
        writer.append("\t		<Component Version=\"5\">\n");
        writer.append("\t			<ID>2</ID>\n");
        writer.append("\t			<Bypass>false</Bypass>\n");
        writer.append("\t			<Intrinsic>false</Intrinsic>\n");
        writer.append("\t		</Component>\n");
        writer.append("\t		<AudioComponentType>2</AudioComponentType>\n");
        writer.append("\t		<FrameRate>5292000</FrameRate>\n");
        writer.append("\t		<ChannelType>0</ChannelType>\n");
        writer.append("\t		<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t	</AudioComponent>\n");
        writer.append("\t</AudioMeter>\n");
        writer.append("\t<AudioComponentParam ObjectID=\"" + (ObjectID++) + "\" ClassID=\"a714635e-a628-4b27-9d59-77eba47dbc1a\" Version=\"9\">\n");
        writer.append("\t	<Name>Panoramique</Name>\n");
        writer.append("\t	<ParameterControlType>2</ParameterControlType>\n");
        writer.append("\t	<RangeLocked>true</RangeLocked>\n");
        writer.append("\t	<IsInverted>true</IsInverted>\n");
        writer.append("\t	<Timestamp>358326044788400</Timestamp>\n");
        writer.append("\t	<StartKeyframe>-91445760000000000,0.,0,0,0,0,0,0</StartKeyframe>\n");
        writer.append("\t	<CurrentValue>0</CurrentValue>\n");
        writer.append("\t</AudioComponentParam>\n");
        writer.append("\t<AudioFader ObjectID=\"" + (ObjectID++) + "\" ClassID=\"1a38c583-ed5c-11d5-abc4-c1cbf61ec590\" Version=\"3\">\n");
        writer.append("\t	<AudioComponent Version=\"3\">\n");
        writer.append("\t		<Component Version=\"5\">\n");
        writer.append("\t			<Params Version=\"1\">\n");
        writer.append("\t				<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t				<Param Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t			</Params>\n");
        writer.append("\t			<ID>1</ID>\n");
        writer.append("\t			<Bypass>false</Bypass>\n");
        writer.append("\t			<Intrinsic>false</Intrinsic>\n");
        writer.append("\t		</Component>\n");
        writer.append("\t		<AudioComponentType>1</AudioComponentType>\n");
        writer.append("\t		<FrameRate>5292000</FrameRate>\n");
        writer.append("\t		<ChannelType>0</ChannelType>\n");
        writer.append("\t		<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t	</AudioComponent>\n");
        writer.append("\t</AudioFader>\n");
        writer.append("\t<AudioMeter ObjectID=\"" + (ObjectID++) + "\" ClassID=\"72ea4700-f615-11d5-abc4-c186585e63e0\" Version=\"2\">\n");
        writer.append("\t	<AudioComponent Version=\"3\">\n");
        writer.append("\t		<Component Version=\"5\">\n");
        writer.append("\t			<ID>2</ID>\n");
        writer.append("\t			<Bypass>false</Bypass>\n");
        writer.append("\t			<Intrinsic>false</Intrinsic>\n");
        writer.append("\t		</Component>\n");
        writer.append("\t		<AudioComponentType>2</AudioComponentType>\n");
        writer.append("\t		<FrameRate>5292000</FrameRate>\n");
        writer.append("\t		<ChannelType>0</ChannelType>\n");
        writer.append("\t		<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t	</AudioComponent>\n");
        writer.append("\t</AudioMeter>\n");
        writer.append("\t<AudioComponentParam ObjectID=\"" + (ObjectID++) + "\" ClassID=\"a714635e-a628-4b27-9d59-77eba47dbc1a\" Version=\"9\">\n");
        writer.append("\t	<Name>Panoramique</Name>\n");
        writer.append("\t	<ParameterControlType>2</ParameterControlType>\n");
        writer.append("\t	<RangeLocked>true</RangeLocked>\n");
        writer.append("\t	<IsInverted>true</IsInverted>\n");
        writer.append("\t	<Timestamp>358326048296100</Timestamp>\n");
        writer.append("\t</AudioComponentParam>\n");
        writer.append("\t<AudioFader ObjectID=\"" + (ObjectID++) + "\" ClassID=\"1a38c583-ed5c-11d5-abc4-c1cbf61ec590\" Version=\"3\">\n");
        writer.append("\t	<AudioComponent Version=\"3\">\n");
        writer.append("\t		<Component Version=\"5\">\n");
        writer.append("\t			<Params Version=\"1\">\n");
        writer.append("\t				<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t				<Param Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        writer.append("\t			</Params>\n");
        writer.append("\t			<ID>1</ID>\n");
        writer.append("\t			<Bypass>false</Bypass>\n");
        writer.append("\t			<Intrinsic>false</Intrinsic>\n");
        writer.append("\t		</Component>\n");
        writer.append("\t		<AudioComponentType>1</AudioComponentType>\n");
        writer.append("\t		<FrameRate>5292000</FrameRate>\n");
        writer.append("\t		<ChannelType>3</ChannelType>\n");
        writer.append("\t		<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t	</AudioComponent>\n");
        writer.append("\t</AudioFader>\n");
        writer.append("\t<AudioMeter ObjectID=\"" + (ObjectID++) + "\" ClassID=\"72ea4700-f615-11d5-abc4-c186585e63e0\" Version=\"2\">\n");
        writer.append("\t	<AudioComponent Version=\"3\">\n");
        writer.append("\t		<Component Version=\"5\">\n");
        writer.append("\t			<ID>2</ID>\n");
        writer.append("\t			<Bypass>false</Bypass>\n");
        writer.append("\t			<Intrinsic>false</Intrinsic>\n");
        writer.append("\t		</Component>\n");
        writer.append("\t		<AudioComponentType>2</AudioComponentType>\n");
        writer.append("\t		<FrameRate>5292000</FrameRate>\n");
        writer.append("\t		<ChannelType>3</ChannelType>\n");
        writer.append("\t		<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        writer.append("\t	</AudioComponent>\n");
        writer.append("\t</AudioMeter>\n");

        // VideoComponentChain.
        if (!this.liste_clip.isEmpty()) {
            writer.append("\t<VideoComponentChain ObjectID=\"" + (ObjectID++) + "\" ClassID=\"0970e08a-f58f-4108-b29a-1a717b8e12e2\" Version=\"1\">\n");
            writer.append("\t	<ComponentChain Version=\"2\">\n");
            writer.append("\t		<Node Version=\"1\">\n");
            writer.append("\t			<Properties Version=\"1\">\n");
            writer.append("\t				<MZ.ComponentChain.ActiveComponentID>2</MZ.ComponentChain.ActiveComponentID>\n");
            writer.append("\t				<MZ.ComponentChain.ActiveComponentParamIndex>4294967295</MZ.ComponentChain.ActiveComponentParamIndex>\n");
            writer.append("\t			</Properties>\n");
            writer.append("\t		</Node>\n");
            writer.append("\t		<Components Version=\"1\">\n");
            writer.append("\t		</Components>\n");
            writer.append("\t	</ComponentChain>\n");
            writer.append("\t	<DefaultMotion>true</DefaultMotion>\n");
            writer.append("\t	<DefaultMotionComponentID>1</DefaultMotionComponentID>\n");
            writer.append("\t	<DefaultOpacity>true</DefaultOpacity>\n");
            writer.append("\t	<DefaultOpacityComponentID>2</DefaultOpacityComponentID>\n");
            writer.append("\t</VideoComponentChain>\n");
            writer.append("\t<SubClip ObjectID=\"121\" ClassID=\"e0c58dc9-dbdd-4166-aef7-5db7e3f22e84\" Version=\"5\">\n");
            writer.append("\t	<Clip ObjectRef=\"128\"/>\n");
            writer.append("\t	<MasterClip ObjectURef=\"8c85bb49-dcaf-4511-aed8-9f6cead61d2a\"/>\n");
            writer.append("\t	<Name>TITRE</Name>\n");
            writer.append("\t	<OrigChGrp>0</OrigChGrp>\n");
            writer.append("\t</SubClip>\n");
        }

        List<AudioComponentParam> audioComponentParams = new ArrayList<AudioComponentParam>();
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "a714635e-a628-4b27-9d59-77eba47dbc1a", 2, "Volume", "dB", 2, 358326041532800L));

        audioComponentParams.add(new AudioComponentParam(ObjectID++, "32657501-3aa4-445f-a49b-d09ecb9fa1ae", 0, "Silence", "", 4, 358326041544500L));
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "a714635e-a628-4b27-9d59-77eba47dbc1a", 2, "Volume", "dB", 2, 358326048238700L));
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "32657501-3aa4-445f-a49b-d09ecb9fa1ae", 0, "Silence", "", 4, 358326048244900L));
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "a714635e-a628-4b27-9d59-77eba47dbc1a", 2, "Volume", "dB", 2, 358326031664600L));
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "32657501-3aa4-445f-a49b-d09ecb9fa1ae", 0, "Silence", "", 4, 358326031675600L));

        for (AudioComponentParam audio_component_param : audioComponentParams) {
            audio_component_param.toXML(file);
        }
    }

    /**
     *
     * @param writer
     */
    @Override
    public void inSequence(PrintWriter writer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param writer
     */
    //@Override
    void videoMediaSource(PrintWriter writer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param writer
     */
    @Override
    void media(PrintWriter writer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
