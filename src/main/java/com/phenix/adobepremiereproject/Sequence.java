package com.phenix.adobepremiereproject;

import com.phenix.tools.Framerate;
import com.phenix.tools.Timecode;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Timeline dans Adobe Première.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Sequence extends ElementInSequence {

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
     *
     */
    private int hauteur;

    /**
     *
     */
    private int largeur;

    /**
     * Nombre de séquences qu'il y a dans le projet.
     */
    private static int nombre_sequence = 1;

    /**
     * Liste des éléments dans la séquence.
     */
    private ArrayList<ElementInSequence> liste_clip;

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
        super(parent, name, Element.SEQUENCE);

        // Initialise la liste des clips dans la séquence.
        this.liste_clip = new ArrayList<ElementInSequence>();

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
     * @param tc_in
     * @param start
     */
    public void add(ElementInSequence elementInSequence, Timecode tc_in, Timecode start) {
        this.add(elementInSequence, tc_in, null, null);
    }

    /**
     * Ajoute un élément à la séquence.
     *
     * @param elementInSequence Elément ajoutable à la séquence.
     * @param tc_in
     * @param out
     * @param start
     */
    public void add(ElementInSequence elementInSequence, Timecode tc_in, Timecode out, Timecode start) {
        this.liste_clip.add(elementInSequence);
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
     * @param file Fichier où on écrit les données.
     * @param order
     */
    @Override
    public void toXML(PrintWriter file, int order) {
        file.append("\t<ClipProjectItem ObjectUID=\"" + this.getCurrentObjectURef() + "\" ClassID=\"" + this.classID + "\" Version=\"1\">\n");
        file.append("\t\t<ProjectItem Version=\"1\">\n");
        file.append("\t\t\t<Node Version=\"1\">\n");
        file.append("\t\t\t\t<Properties Version=\"1\">\n");
        file.append("\t\t\t\t\t<Column.PropertyText.Label>BE.Prefs.LabelColors.5</Column.PropertyText.Label>\n");
        file.append("\t\t\t\t\t<project.icon.view.grid.order>" + order + "</project.icon.view.grid.order>\n");
        file.append("\t\t\t\t</Properties>\n");
        file.append("\t\t\t</Node>\n");
        file.append("\t\t\t<Name>" + super.getName() + "</Name>\n");
        file.append("\t\t</ProjectItem>\n");
        file.append("\t\t<MasterClip ObjectURef=\"ad5bd5cb-4336-473d-a7f2-74386fbfd563\"/>\n");
        file.append("\t</ClipProjectItem>\n");
    }

    /**
     *
     * @param file
     */
    public void videoClip(PrintWriter file) {
        file.append("\t<VideoClip ObjectID=\"128\" ClassID=\"9308dbef-2440-4acb-9ab2-953b9a4e82ec\" Version=\"11\">\n");
        file.append("\t\t<Clip Version=\"18\">\n");
        file.append("\t\t\t<Node Version=\"1\">\n");
        file.append("\t\t\t\t<Properties Version=\"1\">\n");
        file.append("\t\t\t\t\t<BE.Prefs.SyntheticMedia.DefaultIsDropFrame>false</BE.Prefs.SyntheticMedia.DefaultIsDropFrame>\n");
        file.append("\t\t\t\t\t<asl.clip.label.color>14910691</asl.clip.label.color>\n");
        file.append("\t\t\t\t\t<asl.clip.label.name>BE.Prefs.LabelColors.3</asl.clip.label.name>\n");
        file.append("\t\t\t\t</Properties>\n");
        file.append("\t\t\t</Node>\n");
        file.append("\t\t\t<Source ObjectRef=\"83\"/>\n");
        file.append("\t\t\t<ClipID>b836a17f-9a92-4647-9fbc-2b020fc10552</ClipID>\n");
        file.append("\t\t\t<InPoint>914457600000000</InPoint>\n");
        file.append("\t\t\t<OutPoint>915727680000000</OutPoint>\n");
        file.append("\t\t</Clip>\n");
        file.append("\t</VideoClip>\n");
    }

    /**
     *
     * @param file
     */
    public void clip(PrintWriter file) {
        file.append("\t<ClipLoggingInfo ObjectID=\"40\" ClassID=\"77ab7fdd-dcdf-465d-9906-7a330ca1e738\" Version=\"7\">\n");
        file.append("\t	<MediaFrameRate>9223372036854775807</MediaFrameRate>\n");
        file.append("\t</ClipLoggingInfo>\n");
        file.append("\t<AudioComponentChain ObjectID=\"41\" ClassID=\"3cb131d1-d3c0-47ae-a19a-bdf75ea11674\" Version=\"3\">\n");
        file.append("\t	<ComponentChain Version=\"2\">\n");
        file.append("\t		<Components Version=\"1\">\n");
        file.append("\t		</Components>\n");
        file.append("\t	</ComponentChain>\n");
        file.append("\t	<ChannelType>3</ChannelType>\n");
        file.append("\t	<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t	<FrameRate>5292000</FrameRate>\n");
        file.append("\t	<AutomationMode>1</AutomationMode>\n");
        file.append("\t	<DefaultVol>true</DefaultVol>\n");
        file.append("\t	<DefaultVolumeComponentID>1</DefaultVolumeComponentID>\n");
        file.append("\t	<DefaultChannelVolumeComponentID>2</DefaultChannelVolumeComponentID>\n");
        file.append("\t</AudioComponentChain>\n");
        file.append("\t<AudioClip ObjectID=\"42\" ClassID=\"b8830d03-de02-41ee-84ec-fe566dc70cd9\" Version=\"8\">\n");
        file.append("\t	<Clip Version=\"18\">\n");
        file.append("\t		<Node Version=\"1\">\n");
        file.append("\t			<Properties Version=\"1\">\n");
        file.append("\t				<asl.clip.label.color>5814353</asl.clip.label.color>\n");
        file.append("\t				<asl.clip.label.name>BE.Prefs.LabelColors.5</asl.clip.label.name>\n");
        file.append("\t			</Properties>\n");
        file.append("\t		</Node>\n");

        int ObjectRef = 48;

        file.append("\t		<Source ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t		<ClipID>1811dde4-f008-48c9-8fdc-3f7261120218</ClipID>\n");
        file.append("\t		<InUse>false</InUse>\n");
        file.append("\t	</Clip>\n");
        file.append("\t	<SecondaryContents Version=\"1\">\n");

        for (int i = 0; i <= 31; i++) {
            file.append("\t\t\t<SecondaryContentItem Index=\"" + i + "\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        }

        file.append("\t	</SecondaryContents>\n");
        file.append("\t\t<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t</AudioClip>\n");
        file.append("\t<VideoClip ObjectID=\"43\" ClassID=\"9308dbef-2440-4acb-9ab2-953b9a4e82ec\" Version=\"11\">\n");
        file.append("\t	<Clip Version=\"18\">\n");
        file.append("\t		<Node Version=\"1\">\n");
        file.append("\t			<Properties Version=\"1\">\n");
        file.append("\t				<asl.clip.label.color>5814353</asl.clip.label.color>\n");
        file.append("\t				<asl.clip.label.name>BE.Prefs.LabelColors.5</asl.clip.label.name>\n");
        file.append("\t			</Properties>\n");
        file.append("\t		</Node>\n");
        file.append("\t		<Source ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t		<ClipID>9cfa06ef-090e-43d2-bbc5-437803e2e0d6</ClipID>\n");
        file.append("\t		<InUse>false</InUse>\n");
        file.append("\t	</Clip>\n");
        file.append("\t</VideoClip>\n");
        file.append("\t<ClipChannelGroupVectorSerializer ObjectID=\"44\" ClassID=\"a3127a8c-95d4-456e-a7f5-171b3f922426\" Version=\"1\">\n");
        file.append("\t	<ClipChannelVectors Version=\"1\">\n");
        file.append("\t		<ClipChannelVectorItem Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t	</ClipChannelVectors>\n");
        file.append("\t</ClipChannelGroupVectorSerializer>\n");

    }

    /**
     *
     * @param file
     */
    public void audioSequenceSource(PrintWriter file) {

        int ObjectID = 48;
        int ObjectRef = ObjectID;

        file.append("\t<AudioSequenceSource ObjectID=\"" + (ObjectID++) + "\" ClassID=\"e8d4cc83-38cb-491f-9d94-e5f7e3b205ee\" Version=\"7\">\n");
        file.append("\t	<SequenceSource Version=\"4\">\n");
        file.append("\t		<Content Version=\"10\">\n");
        file.append("\t		</Content>\n");
        file.append("\t		<Sequence ObjectURef=\"9d8a2607-057b-47be-8e25-56261a940524\"/>\n");
        file.append("\t	</SequenceSource>\n");
        file.append("\t	<OriginalDuration>0</OriginalDuration>\n");
        file.append("\t</AudioSequenceSource>\n");

        for (int i = 0; i <= 31; i++) {
            file.append("\t<SecondaryContent ObjectID=\"" + (ObjectID++) + "\" ClassID=\"f9d004b5-cb04-4e2f-af6f-64fadc2c4be9\" Version=\"1\">\n");
            file.append("\t\t<Content ObjectRef=\"" + ObjectRef + "\"/>\n");
            file.append("\t\t<ChannelIndex>" + i + "</ChannelIndex>\n");
            file.append("\t</SecondaryContent>\n");
        }

        file.append("\t<VideoSequenceSource ObjectID=\"" + (ObjectID++) + "\" ClassID=\"4752dfa9-7a7e-4a3b-a25b-cafde1a8d036\" Version=\"3\">\n");
        file.append("\t	<SequenceSource Version=\"4\">\n");
        file.append("\t		<Content Version=\"10\">\n");
        file.append("\t		</Content>\n");
        file.append("\t		<Sequence ObjectURef=\"9d8a2607-057b-47be-8e25-56261a940524\"/>\n");
        file.append("\t	</SequenceSource>\n");
        file.append("\t	<OriginalDuration>0</OriginalDuration>\n");
        file.append("\t</VideoSequenceSource>\n");
        file.append("\t<ClipChannelVectorSerializer ObjectID=\"" + (ObjectID++) + "\" ClassID=\"333d203b-3a53-4195-8894-fc7523ff3dc7\" Version=\"1\">\n");
        file.append("\t\t<ClipChannels Version=\"1\">\n");

        for (int i = 0; i <= 15; i++) {
            file.append("\t\t\t<ClipChannelItem Index=\"" + i + "\" ObjectRef=\"" + ((ObjectID++) + 1) + "\"/>\n");
        }

        file.append("\t	</ClipChannels>\n");
        file.append("\t	<ChannelType>3</ChannelType>\n");
        file.append("\t</ClipChannelVectorSerializer>\n");
    }

    /**
     *
     * @param file
     */
    public void sequence(PrintWriter file) {
        file.append("\t<Sequence ObjectUID=\"9d8a2607-057b-47be-8e25-56261a940524\" ClassID=\"6a15d903-8739-11d5-af2d-9b7855ad8974\" Version=\"11\">\n");
        file.append("\t\t<Node Version=\"1\">\n");
        file.append("\t\t\t<Properties Version=\"1\">\n");
        file.append("\t\t\t\t<AM.TrackScrollPosition>0</AM.TrackScrollPosition>\n");
        file.append("\t\t\t\t<AM.TrackVScrollPosition>0</AM.TrackVScrollPosition>\n");
        file.append("\t\t\t\t<AMM.CurrentSolo>[]</AMM.CurrentSolo>\n");
        file.append("\t\t\t\t<HSL.TimelinePatchingAndTargeting.AudioPatches706bcde2_45_736e_45_6997_45_3385_45_a59f0000001b>[{\"mNumber\":0,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0},{\"mNumber\":-1,\"mState\":0}]</HSL.TimelinePatchingAndTargeting.AudioPatches706bcde2_45_736e_45_6997_45_3385_45_a59f0000001b>\n");
        file.append("\t\t\t\t<HSL.TimelinePatchingAndTargeting.VideoPatches405230c6_45_7438_45_1002_45_93de_45_aac30000000f>[{\"mNumber\":0,\"mState\":0}]</HSL.TimelinePatchingAndTargeting.VideoPatches405230c6_45_7438_45_1002_45_93de_45_aac30000000f>\n");
        file.append("\t\t\t\t<MZ.EditLine>0</MZ.EditLine>\n");
        file.append("\t\t\t\t<MZ.Sequence.AudioTimeDisplayFormat>200</MZ.Sequence.AudioTimeDisplayFormat>\n");
        file.append("\t\t\t\t<MZ.Sequence.EditingModeGUID>9678af98-a7b7-4bdb-b477-7ac9c8df4a4e</MZ.Sequence.EditingModeGUID>\n");
        file.append("\t\t\t\t<MZ.Sequence.PreviewFrameSizeHeight>1080</MZ.Sequence.PreviewFrameSizeHeight>\n");
        file.append("\t\t\t\t<MZ.Sequence.PreviewFrameSizeWidth>1920</MZ.Sequence.PreviewFrameSizeWidth>\n");
        file.append("\t\t\t\t<MZ.Sequence.PreviewRenderingClassID>1297106761</MZ.Sequence.PreviewRenderingClassID>\n");
        file.append("\t\t\t\t<MZ.Sequence.PreviewRenderingPresetCodec>1297107278</MZ.Sequence.PreviewRenderingPresetCodec>\n");
        file.append("\t\t\t\t<MZ.Sequence.PreviewRenderingPresetPath>EncoderPresets\\SequencePreview\\9678af98-a7b7-4bdb-b477-7ac9c8df4a4e\\I-Frame Only MPEG.epr</MZ.Sequence.PreviewRenderingPresetPath>\n");
        file.append("\t\t\t\t<MZ.Sequence.PreviewUseMaxBitDepth>false</MZ.Sequence.PreviewUseMaxBitDepth>\n");
        file.append("\t\t\t\t<MZ.Sequence.PreviewUseMaxRenderQuality>false</MZ.Sequence.PreviewUseMaxRenderQuality>\n");

        String display_format = null;

        if (this.framerate == Framerate.F24) {
            display_format = "100";
        } else if (this.framerate == Framerate.F25) {
            display_format = "101";
        }

        file.append("\t\t\t\t<MZ.Sequence.VideoTimeDisplayFormat>" + display_format + "</MZ.Sequence.VideoTimeDisplayFormat>\n");
        file.append("\t\t\t\t<MZ.WorkInPoint>0</MZ.WorkInPoint>\n");
        file.append("\t\t\t\t<MZ.WorkOutPoint>15240960000000</MZ.WorkOutPoint>\n");

        if (this.start_timecode.toImage() != 0) {
            Timecode tmp = new Timecode(this.start_timecode.toString(), Framerate.F24.getValeur());

            long div = 0;

            if (this.framerate == Framerate.F24) {
                div = 10584000000L;
            } else if (this.framerate == Framerate.F25) {
                div = 10160640000L;
            }

            file.append("\t\t\t\t<MZ.ZeroPoint>" + (this.start_timecode.toImage() * div) + "</MZ.ZeroPoint>\n");
        }

        file.append("\t\t\t\t<Monitor.ProgramZoomIn>0</Monitor.ProgramZoomIn>\n");
        file.append("\t\t\t\t<Monitor.ProgramZoomOut>0</Monitor.ProgramZoomOut>\n");
        file.append("\t\t\t\t<TL.SQAVDividerPosition>0.209354117513</TL.SQAVDividerPosition>\n");
        file.append("\t\t\t\t<TL.SQAudioVisibleBase>0</TL.SQAudioVisibleBase>\n");
        file.append("\t\t\t\t<TL.SQHeaderWidth>236</TL.SQHeaderWidth>\n");
        file.append("\t\t\t\t<TL.SQHideShyTracks>0</TL.SQHideShyTracks>\n");
        file.append("\t\t\t\t<TL.SQTimePerPixel>0.52310374891020051</TL.SQTimePerPixel>\n");
        file.append("\t\t\t\t<TL.SQVideoVisibleBase>0</TL.SQVideoVisibleBase>\n");
        file.append("\t\t\t\t<TL.SQVisibleBaseTime>0</TL.SQVisibleBaseTime>\n");
        file.append("\t\t\t</Properties>\n");
        file.append("\t\t</Node>\n");
        file.append("\t\t<PersistentGroupContainer Version=\"1\">\n");
        file.append("\t\t\t<LinkContainer Version=\"1\">\n");
        file.append("\t\t\t</LinkContainer>\n");
        file.append("\t\t</PersistentGroupContainer>\n");

        int ObjectRef = 100;

        file.append("\t\t<TrackGroups Version=\"1\">\n");
        file.append("\t\t\t<TrackGroup Version=\"1\" Index=\"0\">\n");
        file.append("\t\t\t\t<First>80b8e3d5-6dca-4195-aefb-cb5f407ab009</First>\n");
        file.append("\t\t\t\t<Second ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t\t\t</TrackGroup>\n");
        file.append("\t\t\t<TrackGroup Version=\"1\" Index=\"1\">\n");
        file.append("\t\t\t\t<First>228cda18-3625-4d2d-951e-348879e4ed93</First>\n");
        file.append("\t\t\t\t<Second ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t\t\t</TrackGroup>\n");
        file.append("\t\t</TrackGroups>\n");
        file.append("\t\t<ID>1</ID>\n");
        file.append("\t\t<Name>" + this.getName() + "</Name>\n");
        file.append("\t\t<PreviewFormatIdentifier>b0f75bf9-a1fa-f37f-f0e2-3a24000000fa</PreviewFormatIdentifier>\n");
        file.append("\t</Sequence>\n");

        String classID = "5c89aa7a-89a6-4483-becd-f2b1def42316";
        int ObjectID = 84;

        for (int i = 0; i <= 15; i++) {
            file.append("\t<ClipChannelSerializer ObjectID=\"" + (ObjectID++) + "\" ClassID=\"" + classID + "\" Version=\"1\">\n");
            file.append("\t	<SourceClipIndex>" + (0) + "</SourceClipIndex>\n");
            file.append("\t	<mSourceChannelIndex>" + (i) + "</mSourceChannelIndex>\n");
            file.append("\t</ClipChannelSerializer>\n");
        }
    }

    /**
     *
     * @param start_timecode
     */
    public void setStartTimecode(Timecode start_timecode) {
        this.start_timecode = start_timecode;
    }

    /**
     *
     * @param framerate
     */
    public void setFramerate(Framerate framerate) {
        this.framerate = framerate;
    }

    /**
     *
     * @param resolution
     */
    public void setResolution(ResolutionStandard resolution) {
        this.largeur = resolution.getLargeur();
        this.hauteur = resolution.getHauteur();
    }

    /**
     *
     * @param largeur
     * @param hauteur
     */
    public void setResolution(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    /**
     *
     * @param file
     */
    public void audioTrackGroup(PrintWriter file) {
        int ObjectID = 100;
        int ObjectRef = 103;

        file.append("\t<AudioTrackGroup ObjectID=\"" + (ObjectID++) + "\" ClassID=\"9b9238b9-53a8-4cc3-b03f-b36246d052e6\" Version=\"6\">\n");
        file.append("\t	<TrackGroup Version=\"1\">\n");
        file.append("\t		<Tracks Version=\"1\">\n");
        file.append("\t			<Track Index=\"0\" ObjectURef=\"4aa95753-fe3b-4c32-a051-414fd49f3f62\"/>\n");
        file.append("\t			<Track Index=\"1\" ObjectURef=\"05c90639-4a41-42a9-ae1f-b2178d20a8ab\"/>\n");
        file.append("\t		</Tracks>\n");
        file.append("\t		<FrameRate>5292000</FrameRate>\n");
        file.append("\t		<NextTrackID>18</NextTrackID>\n");
        file.append("\t	</TrackGroup>\n");
        file.append("\t	<MasterTrack ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t	<AutomationSafeFlags>0</AutomationSafeFlags>\n");
        file.append("\t	<ID>593368e4-e82f-4796-b8dc-2932326d6373</ID>\n");
        file.append("\t	<NumAdaptiveChannels>16</NumAdaptiveChannels>\n");
        file.append("\t</AudioTrackGroup>\n");
        file.append("\t<VideoTrackGroup ObjectID=\"" + (ObjectID++) + "\" ClassID=\"9e9abf7a-0918-49c2-91ae-991b5dde77bb\" Version=\"10\">\n");
        file.append("\t	<TrackGroup Version=\"1\">\n");
        file.append("\t		<Tracks Version=\"1\">\n");
        file.append("\t			<Track Index=\"0\" ObjectURef=\"9b6d8c60-7c45-411d-ba73-4d14d6a2fa4d\"/>\n");
        file.append("\t		</Tracks>\n");
        long framerate_ = (long) (254016000000L / this.framerate.getValeur());
        file.append("\t		<FrameRate>" + framerate_ + "</FrameRate>\n");
        file.append("\t		<NextTrackID>2</NextTrackID>\n");
        file.append("\t	</TrackGroup>\n");
        file.append("\t	<FrameRect>0,0," + this.largeur + "," + this.hauteur + "</FrameRect>\n");
        file.append("\t	<PixelAspectRatio>1,1</PixelAspectRatio>\n");
        file.append("\t	<FieldType>0</FieldType>\n");
        file.append("\t	<AllowLinearCompositing>true</AllowLinearCompositing>\n");
        file.append("\t	<ImmersiveVideoVRConfiguration>{\"capturedHorizontalView\":0,\"capturedVerticalView\":0,\"fieldOfHorizontalView\":90,\"fieldOfVerticalView\":60,\"projectionType\":0,\"stereoscopicEye\":0,\"stereoscopicType\":0,\"version\":2}</ImmersiveVideoVRConfiguration>\n");
        file.append("\t</VideoTrackGroup>\n");

        //if (this.liste_clip.size() > 0) {
        file.append("\t<VideoStream ObjectID=\"" + (ObjectID++) + "\" ClassID=\"a36e4719-3ec6-4a0c-ab11-8b4aab377aa5\" Version=\"15\">\n");
        file.append("\t	<IsStill>true</IsStill>\n");
        file.append("\t	<FrameRate>10584000000</FrameRate>\n");
        file.append("\t	<FrameRect>0,0," + this.largeur + "," + this.hauteur + "</FrameRect>\n");
        file.append("\t	<Duration>10973491200000000</Duration>\n");
        file.append("\t	<AlphaType>1</AlphaType>\n");
        file.append("\t	<CodecType>1416197228</CodecType>\n");
        file.append("\t</VideoStream>\n");
        //}

        file.append("\t<AudioClipTrack ObjectUID=\"4aa95753-fe3b-4c32-a051-414fd49f3f62\" ClassID=\"097f6203-99ae-11d5-84f2-8cf14bde7040\" Version=\"6\">\n");
        file.append("\t	<ClipTrack Version=\"2\">\n");
        file.append("\t		<Track Version=\"3\">\n");
        file.append("\t			<Node Version=\"1\">\n");
        file.append("\t				<Properties Version=\"1\">\n");
        file.append("\t					<MZ.SourceTrackNumber>0</MZ.SourceTrackNumber>\n");
        file.append("\t					<MZ.SourceTrackState>0</MZ.SourceTrackState>\n");
        file.append("\t					<MZ.TrackTargeted>1</MZ.TrackTargeted>\n");
        file.append("\t					<TL.SQTrackAudioKeyframeStyle>0</TL.SQTrackAudioKeyframeStyle>\n");
        file.append("\t					<TL.SQTrackExpanded>0</TL.SQTrackExpanded>\n");
        file.append("\t					<TL.SQTrackExpandedHeight>25</TL.SQTrackExpandedHeight>\n");
        file.append("\t					<TL.SQTrackShy>0</TL.SQTrackShy>\n");
        file.append("\t				</Properties>\n");
        file.append("\t			</Node>\n");
        file.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        file.append("\t			<Index>0</Index>\n");
        file.append("\t			<ID>2</ID>\n");
        file.append("\t			<IsLocked>false</IsLocked>\n");
        file.append("\t			<IsSyncLocked>true</IsSyncLocked>\n");
        file.append("\t			<IsMuted>false</IsMuted>\n");
        file.append("\t		</Track>\n");
        file.append("\t		<ClipItems Version=\"3\">\n");
        file.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        file.append("\t			<Index>0</Index>\n");
        file.append("\t		</ClipItems>\n");
        file.append("\t		<TransitionItems Version=\"3\">\n");
        file.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        file.append("\t			<Index>0</Index>\n");
        file.append("\t		</TransitionItems>\n");
        file.append("\t	</ClipTrack>\n");
        file.append("\t	<AudioTrack Version=\"11\">\n");
        file.append("\t		<ComponentOwner Version=\"1\">\n");
        file.append("\t			<Components ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t		</ComponentOwner>\n");
        file.append("\t		<Panner ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t		<SubType>1</SubType>\n");
        file.append("\t		<AutomationMode>1</AutomationMode>\n");
        file.append("\t		<Assign>1</Assign>\n");
        file.append("\t		<ChannelType>0</ChannelType>\n");
        file.append("\t		<FrameRate>5292000</FrameRate>\n");
        file.append("\t		<NextPannerID>4294967279</NextPannerID>\n");
        file.append("\t		<Solo>0</Solo>\n");
        file.append("\t		<MutedBySolo>0</MutedBySolo>\n");
        file.append("\t		<ID>ec0c64a5-73fe-4a33-beb3-0fe37e1e3cf9</ID>\n");
        file.append("\t	</AudioTrack>\n");
        file.append("\t	<RecordChannel>0</RecordChannel>\n");
        file.append("\t</AudioClipTrack>\n");
        file.append("\t<AudioClipTrack ObjectUID=\"05c90639-4a41-42a9-ae1f-b2178d20a8ab\" ClassID=\"097f6203-99ae-11d5-84f2-8cf14bde7040\" Version=\"6\">\n");
        file.append("\t	<ClipTrack Version=\"2\">\n");
        file.append("\t		<Track Version=\"3\">\n");
        file.append("\t			<Node Version=\"1\">\n");
        file.append("\t				<Properties Version=\"1\">\n");
        file.append("\t					<MZ.SourceTrackNumber>-1</MZ.SourceTrackNumber>\n");
        file.append("\t					<MZ.SourceTrackState>0</MZ.SourceTrackState>\n");
        file.append("\t					<MZ.TrackTargeted>1</MZ.TrackTargeted>\n");
        file.append("\t					<TL.SQTrackAudioKeyframeStyle>0</TL.SQTrackAudioKeyframeStyle>\n");
        file.append("\t					<TL.SQTrackExpanded>0</TL.SQTrackExpanded>\n");
        file.append("\t					<TL.SQTrackExpandedHeight>25</TL.SQTrackExpandedHeight>\n");
        file.append("\t					<TL.SQTrackShy>0</TL.SQTrackShy>\n");
        file.append("\t				</Properties>\n");
        file.append("\t			</Node>\n");
        file.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        file.append("\t			<Index>1</Index>\n");
        file.append("\t			<ID>3</ID>\n");
        file.append("\t			<IsLocked>false</IsLocked>\n");
        file.append("\t			<IsSyncLocked>true</IsSyncLocked>\n");
        file.append("\t			<IsMuted>false</IsMuted>\n");
        file.append("\t		</Track>\n");
        file.append("\t		<ClipItems Version=\"3\">\n");
        file.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        file.append("\t			<Index>1</Index>\n");
        file.append("\t		</ClipItems>\n");
        file.append("\t		<TransitionItems Version=\"3\">\n");
        file.append("\t			<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        file.append("\t			<Index>1</Index>\n");
        file.append("\t		</TransitionItems>\n");
        file.append("\t	</ClipTrack>\n");
        file.append("\t	<AudioTrack Version=\"11\">\n");
        file.append("\t		<ComponentOwner Version=\"1\">\n");
        file.append("\t			<Components ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t		</ComponentOwner>\n");
        file.append("\t		<Panner ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t		<SubType>1</SubType>\n");
        file.append("\t		<AutomationMode>1</AutomationMode>\n");
        file.append("\t		<Assign>1</Assign>\n");
        file.append("\t		<ChannelType>0</ChannelType>\n");
        file.append("\t		<FrameRate>5292000</FrameRate>\n");
        file.append("\t		<NextPannerID>4294967279</NextPannerID>\n");
        file.append("\t		<Solo>0</Solo>\n");
        file.append("\t		<MutedBySolo>0</MutedBySolo>\n");
        file.append("\t		<ID>120bceae-452c-496e-8bd1-2d7a0daea3bd</ID>\n");
        file.append("\t	</AudioTrack>\n");
        file.append("\t	<RecordChannel>0</RecordChannel>\n");
        file.append("\t</AudioClipTrack>\n");
        file.append("\t<AudioMixTrack ObjectID=\"" + (ObjectID++) + "\" ClassID=\"4b1d8400-e89e-11d5-abc4-a1a13b1e80a0\" Version=\"4\">\n");
        file.append("\t	<AudioTrack Version=\"11\">\n");
        file.append("\t		<ComponentOwner Version=\"1\">\n");
        file.append("\t			<Components ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t		</ComponentOwner>\n");
        file.append("\t		<Panner ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t		<SubType>3</SubType>\n");
        file.append("\t		<AutomationMode>1</AutomationMode>\n");
        file.append("\t		<Assign>0</Assign>\n");
        file.append("\t		<ChannelType>3</ChannelType>\n");
        file.append("\t		<FrameRate>5292000</FrameRate>\n");
        file.append("\t		<NextPannerID>4294967279</NextPannerID>\n");
        file.append("\t		<Solo>0</Solo>\n");
        file.append("\t		<MutedBySolo>0</MutedBySolo>\n");
        file.append("\t		<ID>e6ccbd27-ba07-4342-96f2-573fafa9646f</ID>\n");
        file.append("\t	</AudioTrack>\n");
        file.append("\t	<Track Version=\"3\">\n");
        file.append("\t		<Node Version=\"1\">\n");
        file.append("\t			<Properties Version=\"1\">\n");
        file.append("\t				<TL.SQTrackAudioKeyframeStyle>2</TL.SQTrackAudioKeyframeStyle>\n");
        file.append("\t				<TL.SQTrackExpanded>0</TL.SQTrackExpanded>\n");
        file.append("\t				<TL.SQTrackExpandedHeight>25</TL.SQTrackExpandedHeight>\n");
        file.append("\t				<TL.SQTrackShy>0</TL.SQTrackShy>\n");
        file.append("\t			</Properties>\n");
        file.append("\t		</Node>\n");
        file.append("\t		<MediaType>80b8e3d5-6dca-4195-aefb-cb5f407ab009</MediaType>\n");
        file.append("\t		<Index>0</Index>\n");
        file.append("\t		<ID>1</ID>\n");
        file.append("\t		<IsLocked>false</IsLocked>\n");
        file.append("\t		<IsSyncLocked>true</IsSyncLocked>\n");
        file.append("\t		<IsMuted>false</IsMuted>\n");
        file.append("\t	</Track>\n");
        file.append("\t	<Inlet ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t</AudioMixTrack>\n");
        file.append("\t<VideoClipTrack ObjectUID=\"9b6d8c60-7c45-411d-ba73-4d14d6a2fa4d\" ClassID=\"f68dcd81-8805-11d5-af2d-9bfa89d4ddd4\" Version=\"1\">\n");
        file.append("\t	<ClipTrack Version=\"2\">\n");
        file.append("\t		<Track Version=\"3\">\n");
        file.append("\t			<Node Version=\"1\">\n");
        file.append("\t				<Properties Version=\"1\">\n");
        file.append("\t					<MZ.SourceTrackNumber>0</MZ.SourceTrackNumber>\n");
        file.append("\t					<MZ.SourceTrackState>" + ((!this.liste_clip.isEmpty()) ? "2" : "0") + "</MZ.SourceTrackState>\n");
        file.append("\t					<MZ.TrackTargeted>1</MZ.TrackTargeted>\n");
        file.append("\t					<TL.SQTrackExpanded>0</TL.SQTrackExpanded>\n");
        file.append("\t					<TL.SQTrackExpandedHeight>25</TL.SQTrackExpandedHeight>\n");
        file.append("\t					<TL.SQTrackShy>0</TL.SQTrackShy>\n");
        file.append("\t				</Properties>\n");
        file.append("\t			</Node>\n");
        file.append("\t			<MediaType>228cda18-3625-4d2d-951e-348879e4ed93</MediaType>\n");
        file.append("\t			<Index>0</Index>\n");
        file.append("\t			<ID>1</ID>\n");
        file.append("\t			<IsLocked>false</IsLocked>\n");
        file.append("\t			<IsSyncLocked>true</IsSyncLocked>\n");
        file.append("\t			<IsMuted>false</IsMuted>\n");
        file.append("\t		</Track>\n");
        file.append("\t		<ClipItems Version=\"3\">\n");

        // Ajoute dans la séquence ici l'item/clip.
        if (!this.liste_clip.isEmpty()) {
            file.append("\t\t\t\t<TrackItems Version=\"1\">\n");
            file.append("\t\t\t\t\t<TrackItem Index=\"0\" ObjectRef=\"111\"/>\n");
            file.append("\t\t\t\t</TrackItems>\n");
        }

        file.append("\t			<MediaType>228cda18-3625-4d2d-951e-348879e4ed93</MediaType>\n");
        file.append("\t			<Index>0</Index>\n");
        file.append("\t		</ClipItems>\n");
        file.append("\t		<TransitionItems Version=\"3\">\n");
        file.append("\t			<MediaType>228cda18-3625-4d2d-951e-348879e4ed93</MediaType>\n");
        file.append("\t			<Index>0</Index>\n");
        file.append("\t		</TransitionItems>\n");
        file.append("\t	</ClipTrack>\n");
        file.append("\t</VideoClipTrack>\n");

        //ObjectID= 104;
        file.append("\t<AudioComponentChain ObjectID=\"" + (ObjectID++) + "\" ClassID=\"3cb131d1-d3c0-47ae-a19a-bdf75ea11674\" Version=\"3\">\n");
        file.append("\t	<ComponentChain Version=\"2\">\n");
        file.append("\t		<Components Version=\"1\">\n");
        file.append("\t			<Component Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t			<Component Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t		</Components>\n");
        file.append("\t	</ComponentChain>\n");
        file.append("\t	<ChannelType>0</ChannelType>\n");
        file.append("\t	<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t	<FrameRate>5292000</FrameRate>\n");
        file.append("\t	<AutomationMode>1</AutomationMode>\n");
        file.append("\t</AudioComponentChain>\n");
        file.append("\t<MonoTo16ChannelPanProcessor ObjectID=\"" + (ObjectID++) + "\" ClassID=\"8c9778ad-af4e-4e98-99fe-542f4eda2dac\" Version=\"2\">\n");
        file.append("\t	<DirectPanProcessor Version=\"2\">\n");
        file.append("\t		<PanProcessor Version=\"3\">\n");
        file.append("\t			<AudioComponent Version=\"3\">\n");
        file.append("\t				<Component Version=\"5\">\n");
        file.append("\t					<Params Version=\"1\">\n");
        file.append("\t						<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t					</Params>\n");
        file.append("\t					<ID>4294967280</ID>\n");
        file.append("\t					<Bypass>false</Bypass>\n");
        file.append("\t					<Intrinsic>false</Intrinsic>\n");
        file.append("\t				</Component>\n");
        file.append("\t				<ChannelType>0</ChannelType>\n");
        file.append("\t				<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t				<FrameRate>5292000</FrameRate>\n");
        file.append("\t				<AudioComponentType>0</AudioComponentType>\n");
        file.append("\t			</AudioComponent>\n");
        file.append("\t			<OutputAudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</OutputAudioChannelLayout>\n");
        file.append("\t		</PanProcessor>\n");
        file.append("\t		<Matrix>[[0,[0]]]</Matrix>\n");
        file.append("\t	</DirectPanProcessor>\n");
        file.append("\t</MonoTo16ChannelPanProcessor>\n");
        file.append("\t<AudioComponentChain ObjectID=\"" + (ObjectID++) + "\" ClassID=\"3cb131d1-d3c0-47ae-a19a-bdf75ea11674\" Version=\"3\">\n");
        file.append("\t	<ComponentChain Version=\"2\">\n");
        file.append("\t		<Components Version=\"1\">\n");
        file.append("\t			<Component Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t			<Component Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t		</Components>\n");
        file.append("\t	</ComponentChain>\n");
        file.append("\t	<ChannelType>0</ChannelType>\n");
        file.append("\t	<FrameRate>5292000</FrameRate>\n");
        file.append("\t	<AutomationMode>1</AutomationMode>\n");
        file.append("\t	<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t</AudioComponentChain>\n");
        file.append("\t<MonoTo16ChannelPanProcessor ObjectID=\"" + (ObjectID++) + "\" ClassID=\"8c9778ad-af4e-4e98-99fe-542f4eda2dac\" Version=\"2\">\n");
        file.append("\t	<DirectPanProcessor Version=\"2\">\n");
        file.append("\t		<PanProcessor Version=\"3\">\n");
        file.append("\t			<AudioComponent Version=\"3\">\n");
        file.append("\t				<Component Version=\"5\">\n");
        file.append("\t					<Params Version=\"1\">\n");
        file.append("\t						<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t					</Params>\n");
        file.append("\t					<ID>4294967280</ID>\n");
        file.append("\t					<Bypass>false</Bypass>\n");
        file.append("\t					<Intrinsic>false</Intrinsic>\n");
        file.append("\t				</Component>\n");
        file.append("\t				<ChannelType>0</ChannelType>\n");
        file.append("\t				<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t				<FrameRate>5292000</FrameRate>\n");
        file.append("\t				<AudioComponentType>0</AudioComponentType>\n");
        file.append("\t			</AudioComponent>\n");
        file.append("\t			<OutputAudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</OutputAudioChannelLayout>\n");
        file.append("\t		</PanProcessor>\n");
        file.append("\t		<Matrix>[[0,[0]]]</Matrix>\n");
        file.append("\t	</DirectPanProcessor>\n");
        file.append("\t</MonoTo16ChannelPanProcessor>\n");
        file.append("\t<AudioComponentChain ObjectID=\"" + (ObjectID++) + "\" ClassID=\"3cb131d1-d3c0-47ae-a19a-bdf75ea11674\" Version=\"3\">\n");
        file.append("\t	<ComponentChain Version=\"2\">\n");
        file.append("\t		<Components Version=\"1\">\n");
        file.append("\t			<Component Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t			<Component Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t		</Components>\n");
        file.append("\t	</ComponentChain>\n");
        file.append("\t	<ChannelType>3</ChannelType>\n");
        file.append("\t	<FrameRate>5292000</FrameRate>\n");
        file.append("\t	<AutomationMode>1</AutomationMode>\n");
        file.append("\t	<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t</AudioComponentChain>\n");
        file.append("\t<DefaultPanProcessor ObjectID=\"" + (ObjectID++) + "\" ClassID=\"33a94282-ee2c-11d5-abc4-c1cd7f9e3c10\" Version=\"2\">\n");
        file.append("\t	<PanProcessor Version=\"3\">\n");
        file.append("\t		<AudioComponent Version=\"3\">\n");
        file.append("\t			<Component Version=\"5\">\n");
        file.append("\t				<ID>4294967280</ID>\n");
        file.append("\t				<Bypass>false</Bypass>\n");
        file.append("\t				<Intrinsic>false</Intrinsic>\n");
        file.append("\t			</Component>\n");
        file.append("\t			<ChannelType>3</ChannelType>\n");
        file.append("\t			<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t			<FrameRate>5292000</FrameRate>\n");
        file.append("\t			<AudioComponentType>0</AudioComponentType>\n");
        file.append("\t		</AudioComponent>\n");
        file.append("\t	</PanProcessor>\n");
        file.append("\t	<DefaultPannerInputChannelType>3</DefaultPannerInputChannelType>\n");
        file.append("\t	<DefaultPannerOutputChannelType>3</DefaultPannerOutputChannelType>\n");
        file.append("\t</DefaultPanProcessor>\n");
        file.append("\t<AudioTrackInlet ObjectID=\"" + (ObjectID++) + "\" ClassID=\"be3af080-e8c6-11d5-abc4-a1c6d5dee670\" Version=\"3\">\n");
        file.append("\t	<Sources Version=\"1\">\n");
        file.append("\t		<Source Index=\"0\" ObjectURef=\"4aa95753-fe3b-4c32-a051-414fd49f3f62\"/>\n");
        file.append("\t		<Source Index=\"1\" ObjectURef=\"05c90639-4a41-42a9-ae1f-b2178d20a8ab\"/>\n");
        file.append("\t	</Sources>\n");
        file.append("\t	<ChannelType>3</ChannelType>\n");
        file.append("\t	<FrameRate>5292000</FrameRate>\n");
        file.append("\t	<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t</AudioTrackInlet>\n");

        // "VideoClipTrackItem"
        if (!this.liste_clip.isEmpty()) {
            file.append("\t<VideoClipTrackItem ObjectID=\"" + (ObjectID++) + "\" ClassID=\"368b0406-29e3-4923-9fcd-094fbf9a1089\" Version=\"5\">\n");
            file.append("\t	<ClipTrackItem Version=\"6\">\n");
            file.append("\t		<ComponentOwner Version=\"1\">\n");
            file.append("\t			<Components ObjectRef=\"120\"/>\n");
            file.append("\t		</ComponentOwner>\n");
            file.append("\t		<TrackItem Version=\"3\">\n");
            file.append("\t			<Start>0</Start>\n");
            file.append("\t			<End>1270080000000</End>\n");
            file.append("\t		</TrackItem>\n");
            file.append("\t		<SubClip ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
            file.append("\t	</ClipTrackItem>\n");
            file.append("\t	<FrameRect>0,0," + this.largeur + "," + this.hauteur + "</FrameRect>\n");
            file.append("\t	<PixelAspectRatio>1,1</PixelAspectRatio>\n");
            file.append("\t</VideoClipTrackItem>\n");
        }

        file.append("\t<AudioFader ObjectID=\"" + (ObjectID++) + "\" ClassID=\"1a38c583-ed5c-11d5-abc4-c1cbf61ec590\" Version=\"3\">\n");
        file.append("\t	<AudioComponent Version=\"3\">\n");
        file.append("\t		<Component Version=\"5\">\n");
        file.append("\t			<Params Version=\"1\">\n");
        file.append("\t				<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t				<Param Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t			</Params>\n");
        file.append("\t			<ID>1</ID>\n");
        file.append("\t			<Bypass>false</Bypass>\n");
        file.append("\t			<Intrinsic>false</Intrinsic>\n");
        file.append("\t		</Component>\n");
        file.append("\t		<AudioComponentType>1</AudioComponentType>\n");
        file.append("\t		<FrameRate>5292000</FrameRate>\n");
        file.append("\t		<ChannelType>0</ChannelType>\n");
        file.append("\t		<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t	</AudioComponent>\n");
        file.append("\t</AudioFader>\n");
        file.append("\t<AudioMeter ObjectID=\"" + (ObjectID++) + "\" ClassID=\"72ea4700-f615-11d5-abc4-c186585e63e0\" Version=\"2\">\n");
        file.append("\t	<AudioComponent Version=\"3\">\n");
        file.append("\t		<Component Version=\"5\">\n");
        file.append("\t			<ID>2</ID>\n");
        file.append("\t			<Bypass>false</Bypass>\n");
        file.append("\t			<Intrinsic>false</Intrinsic>\n");
        file.append("\t		</Component>\n");
        file.append("\t		<AudioComponentType>2</AudioComponentType>\n");
        file.append("\t		<FrameRate>5292000</FrameRate>\n");
        file.append("\t		<ChannelType>0</ChannelType>\n");
        file.append("\t		<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t	</AudioComponent>\n");
        file.append("\t</AudioMeter>\n");
        file.append("\t<AudioComponentParam ObjectID=\"" + (ObjectID++) + "\" ClassID=\"a714635e-a628-4b27-9d59-77eba47dbc1a\" Version=\"9\">\n");
        file.append("\t	<Name>Panoramique</Name>\n");
        file.append("\t	<ParameterControlType>2</ParameterControlType>\n");
        file.append("\t	<RangeLocked>true</RangeLocked>\n");
        file.append("\t	<IsInverted>true</IsInverted>\n");
        file.append("\t	<Timestamp>358326044788400</Timestamp>\n");
        file.append("\t	<StartKeyframe>-91445760000000000,0.,0,0,0,0,0,0</StartKeyframe>\n");
        file.append("\t	<CurrentValue>0</CurrentValue>\n");
        file.append("\t</AudioComponentParam>\n");
        file.append("\t<AudioFader ObjectID=\"" + (ObjectID++) + "\" ClassID=\"1a38c583-ed5c-11d5-abc4-c1cbf61ec590\" Version=\"3\">\n");
        file.append("\t	<AudioComponent Version=\"3\">\n");
        file.append("\t		<Component Version=\"5\">\n");
        file.append("\t			<Params Version=\"1\">\n");
        file.append("\t				<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t				<Param Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t			</Params>\n");
        file.append("\t			<ID>1</ID>\n");
        file.append("\t			<Bypass>false</Bypass>\n");
        file.append("\t			<Intrinsic>false</Intrinsic>\n");
        file.append("\t		</Component>\n");
        file.append("\t		<AudioComponentType>1</AudioComponentType>\n");
        file.append("\t		<FrameRate>5292000</FrameRate>\n");
        file.append("\t		<ChannelType>0</ChannelType>\n");
        file.append("\t		<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t	</AudioComponent>\n");
        file.append("\t</AudioFader>\n");
        file.append("\t<AudioMeter ObjectID=\"" + (ObjectID++) + "\" ClassID=\"72ea4700-f615-11d5-abc4-c186585e63e0\" Version=\"2\">\n");
        file.append("\t	<AudioComponent Version=\"3\">\n");
        file.append("\t		<Component Version=\"5\">\n");
        file.append("\t			<ID>2</ID>\n");
        file.append("\t			<Bypass>false</Bypass>\n");
        file.append("\t			<Intrinsic>false</Intrinsic>\n");
        file.append("\t		</Component>\n");
        file.append("\t		<AudioComponentType>2</AudioComponentType>\n");
        file.append("\t		<FrameRate>5292000</FrameRate>\n");
        file.append("\t		<ChannelType>0</ChannelType>\n");
        file.append("\t		<AudioChannelLayout>[{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t	</AudioComponent>\n");
        file.append("\t</AudioMeter>\n");
        file.append("\t<AudioComponentParam ObjectID=\"" + (ObjectID++) + "\" ClassID=\"a714635e-a628-4b27-9d59-77eba47dbc1a\" Version=\"9\">\n");
        file.append("\t	<Name>Panoramique</Name>\n");
        file.append("\t	<ParameterControlType>2</ParameterControlType>\n");
        file.append("\t	<RangeLocked>true</RangeLocked>\n");
        file.append("\t	<IsInverted>true</IsInverted>\n");
        file.append("\t	<Timestamp>358326048296100</Timestamp>\n");
        file.append("\t</AudioComponentParam>\n");
        file.append("\t<AudioFader ObjectID=\"" + (ObjectID++) + "\" ClassID=\"1a38c583-ed5c-11d5-abc4-c1cbf61ec590\" Version=\"3\">\n");
        file.append("\t	<AudioComponent Version=\"3\">\n");
        file.append("\t		<Component Version=\"5\">\n");
        file.append("\t			<Params Version=\"1\">\n");
        file.append("\t				<Param Index=\"0\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t				<Param Index=\"1\" ObjectRef=\"" + (ObjectRef++) + "\"/>\n");
        file.append("\t			</Params>\n");
        file.append("\t			<ID>1</ID>\n");
        file.append("\t			<Bypass>false</Bypass>\n");
        file.append("\t			<Intrinsic>false</Intrinsic>\n");
        file.append("\t		</Component>\n");
        file.append("\t		<AudioComponentType>1</AudioComponentType>\n");
        file.append("\t		<FrameRate>5292000</FrameRate>\n");
        file.append("\t		<ChannelType>3</ChannelType>\n");
        file.append("\t		<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t	</AudioComponent>\n");
        file.append("\t</AudioFader>\n");
        file.append("\t<AudioMeter ObjectID=\"" + (ObjectID++) + "\" ClassID=\"72ea4700-f615-11d5-abc4-c186585e63e0\" Version=\"2\">\n");
        file.append("\t	<AudioComponent Version=\"3\">\n");
        file.append("\t		<Component Version=\"5\">\n");
        file.append("\t			<ID>2</ID>\n");
        file.append("\t			<Bypass>false</Bypass>\n");
        file.append("\t			<Intrinsic>false</Intrinsic>\n");
        file.append("\t		</Component>\n");
        file.append("\t		<AudioComponentType>2</AudioComponentType>\n");
        file.append("\t		<FrameRate>5292000</FrameRate>\n");
        file.append("\t		<ChannelType>3</ChannelType>\n");
        file.append("\t		<AudioChannelLayout>[{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0},{\"channellabel\":0}]</AudioChannelLayout>\n");
        file.append("\t	</AudioComponent>\n");
        file.append("\t</AudioMeter>\n");

        // VideoComponentChain.
        if (!this.liste_clip.isEmpty()) {
            file.append("\t<VideoComponentChain ObjectID=\"" + (ObjectID++) + "\" ClassID=\"0970e08a-f58f-4108-b29a-1a717b8e12e2\" Version=\"1\">\n");
            file.append("\t	<ComponentChain Version=\"2\">\n");
            file.append("\t		<Node Version=\"1\">\n");
            file.append("\t			<Properties Version=\"1\">\n");
            file.append("\t				<MZ.ComponentChain.ActiveComponentID>2</MZ.ComponentChain.ActiveComponentID>\n");
            file.append("\t				<MZ.ComponentChain.ActiveComponentParamIndex>4294967295</MZ.ComponentChain.ActiveComponentParamIndex>\n");
            file.append("\t			</Properties>\n");
            file.append("\t		</Node>\n");
            file.append("\t		<Components Version=\"1\">\n");
            file.append("\t		</Components>\n");
            file.append("\t	</ComponentChain>\n");
            file.append("\t	<DefaultMotion>true</DefaultMotion>\n");
            file.append("\t	<DefaultMotionComponentID>1</DefaultMotionComponentID>\n");
            file.append("\t	<DefaultOpacity>true</DefaultOpacity>\n");
            file.append("\t	<DefaultOpacityComponentID>2</DefaultOpacityComponentID>\n");
            file.append("\t</VideoComponentChain>\n");
            file.append("\t<SubClip ObjectID=\"121\" ClassID=\"e0c58dc9-dbdd-4166-aef7-5db7e3f22e84\" Version=\"5\">\n");
            file.append("\t	<Clip ObjectRef=\"128\"/>\n");
            file.append("\t	<MasterClip ObjectURef=\"8c85bb49-dcaf-4511-aed8-9f6cead61d2a\"/>\n");
            file.append("\t	<Name>TITRE</Name>\n");
            file.append("\t	<OrigChGrp>0</OrigChGrp>\n");
            file.append("\t</SubClip>\n");
        }

        ArrayList<AudioComponentParam> audioComponentParams = new ArrayList<AudioComponentParam>();
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "a714635e-a628-4b27-9d59-77eba47dbc1a", 2, "Volume", "dB", 2, 358326041532800L));

        audioComponentParams.add(new AudioComponentParam(ObjectID++, "32657501-3aa4-445f-a49b-d09ecb9fa1ae", 0, "Silence", "", 4, 358326041544500L));
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "a714635e-a628-4b27-9d59-77eba47dbc1a", 2, "Volume", "dB", 2, 358326048238700L));
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "32657501-3aa4-445f-a49b-d09ecb9fa1ae", 0, "Silence", "", 4, 358326048244900L));
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "a714635e-a628-4b27-9d59-77eba47dbc1a", 2, "Volume", "dB", 2, 358326031664600L));
        audioComponentParams.add(new AudioComponentParam(ObjectID++, "32657501-3aa4-445f-a49b-d09ecb9fa1ae", 0, "Silence", "", 4, 358326031675600L));

        for (int i = 0; i < audioComponentParams.size(); i++) {
            audioComponentParams.get(i).toXML(file);
        }
    }

    /**
     *
     * @param file
     */
    @Override
    public void inSequence(PrintWriter file) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param file
     */
    //@Override
    void videoMediaSource(PrintWriter file) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param file
     */
    @Override
    void media(PrintWriter file) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
    }
}
