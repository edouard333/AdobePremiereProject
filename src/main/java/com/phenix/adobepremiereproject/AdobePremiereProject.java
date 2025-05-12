package com.phenix.adobepremiereproject;

import com.phenix.adobepremiereproject.Element.TypeElement;
import com.phenix.adobepremiereproject.column.BoolPropertyColumn;
import com.phenix.adobepremiereproject.column.CaptureSettingsColumn;
import com.phenix.adobepremiereproject.column.Column;
import com.phenix.adobepremiereproject.column.EditTextColumn;
import com.phenix.adobepremiereproject.column.LabelColumn;
import com.phenix.adobepremiereproject.column.NameColumn;
import com.phenix.adobepremiereproject.column.SelectedItemsColumn;
import com.phenix.adobepremiereproject.column.StringColumn;
import com.phenix.adobepremiereproject.column.TimecodeColumn;
import com.phenix.adobepremiereproject.exception.AdobePremiereProjectException;
import com.phenix.adobepremiereproject.setting.CompileSettings;
import com.phenix.compression.ZipFiles;
import com.phenix.compression.exception.ZipCustomException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Génère un projet Adobe Premiere Pro CC2017.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class AdobePremiereProject {

    /**
     * Extension d'un projet Adobe Premiere.
     */
    @NotNull
    @NotBlank
    public static final String EXTENSION = ".prproj";

    /**
     * Extension pour les fichiers XML.
     */
    @NotNull
    @NotBlank
    public static final String EXTENSION_XML = ".xml";

    /**
     * Extension pour les fichiers temporaire.
     */
    @NotNull
    @NotBlank
    public static final String EXTENSION_TMP = ".tmp";

    /**
     * Chemin et nom du fichier souhaité.
     */
    private final File fichier;

    /**
     * Les éléments du projet.
     */
    @NotNull
    private final List<Element> elements;

    /**
     * Crée le fichier de XML qui se retrouvera dans le ZIP.
     *
     * @param fichier Lieu et nom de fichier
     */
    public AdobePremiereProject(File fichier) {
        this.fichier = fichier;

        // Initialise le tableau des dossiers
        this.elements = new ArrayList<Element>();
    }

    /**
     * Ajoute un élément.
     *
     * @param element Element à ajouter au projet.
     */
    public void addElement(Element element) {
        this.elements.add(element);
    }

    /**
     * Retourne le fichier XML temporaire.
     *
     * @return Le fichier XML temporaire.
     */
    @NotNull
    private File getFichierXMLTemporaire() {
        return new File(this.fichier.getAbsolutePath().replace(EXTENSION, EXTENSION_TMP));
    }

    /**
     * Écrit le fichier de projet Adobe Premiere.
     *
     * @throws AdobePremiereProjectException
     */
    public void save() throws AdobePremiereProjectException {
        // Cloture le fichier temporaire.
        File fichierTmp = this.getFichierXMLTemporaire();
        try (PrintWriter writer = new PrintWriter(fichierTmp, StandardCharsets.UTF_8)) {
            writer.append(this.toXMLStart());
            writer.append(this.toXMLItem());
            writer.append(this.toXMLEnd());
        } catch (IOException exception) {
            throw new AdobePremiereProjectException(exception.getMessage(), exception);
        }

        try {
            // Prend le fichier XML et le met dans le GZIP.
            ZipFiles.compressGzipFile(fichierTmp, this.fichier);

            // Supprime le fichier temporaire.
            fichierTmp.delete();
        } catch (ZipCustomException exception) {
            throw new AdobePremiereProjectException(exception.getMessage(), exception);
        }
    }

    /**
     * Elément à la racine du projet.
     */
    private String toXMLItem() {
        StringBuilder stringBuilder = new StringBuilder();

        if (!this.elements.isEmpty()) {
            stringBuilder.append("\t\t\t<Items Version=\"1\">\n");

            int index = 0;

            for (Element element : this.elements) {
                if (element.getLevel() == 0) {
                    stringBuilder.append("\t\t\t\t<Item Index=\"" + index + "\" ObjectURef=\"" + element.getCurrentObjectURef() + "\"/>\n"); //fea076d7-a8ae-4c4e-b592-93acb1e074fc
                    index++;
                }
            }

            stringBuilder.append("\t\t\t</Items>\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Crée les dossiers.
     *
     * @param level Niveau de dossier.
     */
    private String toXMLBinProject(int level) {
        StringBuilder stringBuilder = new StringBuilder();

        int order = 0;
        for (Element element : this.elements) {
            if (element.getLevel() == level) {
                stringBuilder.append(element.toXML(order));
                order++;
            }
        }

        return stringBuilder.toString();
    }

    /**
     *
     * @param ObjectID
     * @param ObjectRef
     * @param ProjectViewStateID
     * @param ProjectViewStateOriginalID
     * @param LastViewed
     * @param IconViewThumbnailSize
     */
    private String toXMLProjectViewState(int ObjectID, int ObjectRef, String ProjectViewStateID, String ProjectViewStateOriginalID, String LastViewed, String IconViewThumbnailSize) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t\t\t\t\t<ProjectViewState ObjectID=\"" + ObjectID + "\" ClassID=\"18fb911d-4f21-4b7b-b196-b250dad79838\" Version=\"3\">\n");
        stringBuilder.append("\t\t\t\t\t\t<Columns.List ObjectRef=\"" + ObjectRef + "\"/>\n");
        stringBuilder.append("\t\t\t\t\t\t<ProjectViewState.ID>" + ProjectViewStateID + "</ProjectViewState.ID>\n");
        stringBuilder.append("\t\t\t\t\t\t<ProjectViewState.OriginalID>" + ProjectViewStateOriginalID + "</ProjectViewState.OriginalID>\n");
        stringBuilder.append("\t\t\t\t\t\t<ProjectViewState.BinID>-1</ProjectViewState.BinID>\n");
        stringBuilder.append("\t\t\t\t\t\t<ProjectViewState.ViewHidden>false</ProjectViewState.ViewHidden>\n");
        stringBuilder.append("\t\t\t\t\t\t<PreviewView.Visible>false</PreviewView.Visible>\n");
        stringBuilder.append("\t\t\t\t\t\t<ContentView.LastViewed>" + LastViewed + "</ContentView.LastViewed>\n");
        stringBuilder.append("\t\t\t\t\t\t<IconView.Thumbnail.Size>" + IconViewThumbnailSize + "</IconView.Thumbnail.Size>\n");
        stringBuilder.append("\t\t\t\t\t\t<FreeformView.Scale>1</FreeformView.Scale>\n");
        stringBuilder.append("\t\t\t\t\t\t<ListView.Thumbnail.Size>0</ListView.Thumbnail.Size>\n");
        stringBuilder.append("\t\t\t\t\t\t<IconView.Thumbnail.State>true</IconView.Thumbnail.State>\n");
        stringBuilder.append("\t\t\t\t\t\t<ListView.Thumbnail.State>false</ListView.Thumbnail.State>\n");
        stringBuilder.append("\t\t\t\t\t\t<Thumbnail.ShowsEffects.State>true</Thumbnail.ShowsEffects.State>\n");
        stringBuilder.append("\t\t\t\t\t\t<Sort.Enabled>true</Sort.Enabled>\n");
        stringBuilder.append("\t\t\t\t\t\t<Sort.Type>0</Sort.Type>\n");
        stringBuilder.append("\t\t\t\t\t\t<Sort.Direction>0</Sort.Direction>\n");
        stringBuilder.append("\t\t\t\t\t\t<Sort.ColumnIndex>2</Sort.ColumnIndex>\n");
        stringBuilder.append("\t\t\t\t\t\t<ColumnListContents.Version>16</ColumnListContents.Version>\n");
        stringBuilder.append("\t\t\t\t\t\t<ListView.NameColumnWidth>0</ListView.NameColumnWidth>\n");
        stringBuilder.append("\t\t\t\t\t\t<IconSort.Type>0</IconSort.Type>\n");
        stringBuilder.append("\t\t\t\t\t\t<IconSort.Direction>0</IconSort.Direction>\n");
        stringBuilder.append("\t\t\t\t\t\t<IconSort.ColumnIndex>0</IconSort.ColumnIndex>\n");
        stringBuilder.append("\t\t\t\t\t\t<Project.IsEAProject>false</Project.IsEAProject>\n");
        stringBuilder.append("\t\t\t\t\t</ProjectViewState>\n");

        return stringBuilder.toString();
    }

    /**
     *
     * @param ObjectID
     * @param columnIndexMax
     * @param delta
     */
    private String toXMLColumnList(int ObjectID, int columnIndexMax, int delta) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t\t\t\t\t<ColumnList ObjectID=\"" + ObjectID + "\" ClassID=\"" + ColumnList.ClassID + "\" Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t\t\t<Columns Version=\"1\">\n");

        for (int i = 0; i < columnIndexMax; i++) {
            stringBuilder.append("\t\t\t\t\t\t\t<Column Index=\"" + i + "\" ObjectRef=\"" + (i + delta) + "\"/>\n");
        }

        stringBuilder.append("\t\t\t\t\t\t</Columns>\n");
        stringBuilder.append("\t\t\t\t\t</ColumnList>\n");

        return stringBuilder.toString();
    }

    /**
     * Écrit la structure du projet, le début.
     */
    private String toXMLStart() throws AdobePremiereProjectException {
        StringBuilder stringBuilder = new StringBuilder();

        String workspaceName = "Montage";

        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        stringBuilder.append("<PremiereData Version=\"3\">\n");
        stringBuilder.append("\t<Project ObjectRef=\"1\"/>\n");
        stringBuilder.append("\t<Project ObjectID=\"1\" ClassID=\"" + Project.ClassID + "\" Version=\"" + Version.CC2024 + "\">\n");
        stringBuilder.append("\t\t<Node Version=\"1\">\n");
        stringBuilder.append("\t\t\t<Properties Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t<ProjectViewState.List ObjectID=\"2\" ClassID=\"aab0946f-7a21-4425-8908-fafa2119e30e\" Version=\"3\">\n");
        stringBuilder.append("\t\t\t\t\t<ProjectViewStates Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t\t\t<ProjectViewState Version=\"1\" Index=\"0\">\n");
        stringBuilder.append("\t\t\t\t\t\t\t<First>361ff028-e258-4162-a70b-ddea24afb013</First>\n");
        stringBuilder.append("\t\t\t\t\t\t\t<Second ObjectRef=\"1\"/>\n");
        stringBuilder.append("\t\t\t\t\t\t</ProjectViewState>\n");
        stringBuilder.append("\t\t\t\t\t\t<ProjectViewState Version=\"1\" Index=\"1\">\n");
        stringBuilder.append("\t\t\t\t\t\t\t<First>3625b009-0f43-4db8-8f24-6be33ebbaa5f</First>\n");
        stringBuilder.append("\t\t\t\t\t\t\t<Second ObjectRef=\"2\"/>\n");
        stringBuilder.append("\t\t\t\t\t\t</ProjectViewState>\n");
        stringBuilder.append("\t\t\t\t\t</ProjectViewStates>\n");

        stringBuilder.append(this.toXMLProjectViewState(1, 3, "361ff028-e258-4162-a70b-ddea24afb013", "00000000-0000-0000-0000-000000000000", "0", "1"));
        stringBuilder.append(this.toXMLProjectViewState(2, 4, "3625b009-0f43-4db8-8f24-6be33ebbaa5f", "361ff028-e258-4162-a70b-ddea24afb013", "1", "200"));

        int columnIndexMaxExclu = 56;
        int delta = 5;

        stringBuilder.append(this.toXMLColumnList(3, columnIndexMaxExclu, delta));
        stringBuilder.append(this.toXMLColumnList(4, columnIndexMaxExclu, columnIndexMaxExclu + delta));

        List<Column> listeColumn = new ArrayList<Column>();

        int objectId = 4;

        listeColumn.add(new LabelColumn(
                ++objectId,
                "Libellé",
                "Column.PropertyText.Label",
                17,
                1,
                false,
                26
        ));

        listeColumn.add(new SelectedItemsColumn(
                ++objectId,
                "Sélectionné(s)",
                "Column.PropertyText.SelectedItems",
                0,
                1,
                true,
                26
        ));

        listeColumn.add(new NameColumn(
                ++objectId,
                "Nom",
                "Column.Intrinsic.Name",
                0,
                0,
                false,
                200
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Type de média",
                "Column.Intrinsic.MediaType",
                23,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Fréquence d'images",
                "Column.Intrinsic.MediaTimebase",
                22,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Début du média",
                "Column.Intrinsic.MediaStart",
                21,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Fin du média",
                "Column.Intrinsic.MediaEnd",
                20,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Durée du média",
                "Column.Intrinsic.MediaDuration",
                19,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Point d'entrée vidéo",
                "Column.Intrinsic.VideoInPoint",
                35,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Point de sortie vidéo",
                "Column.Intrinsic.VideoOutPoint",
                36,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Durée vidéo",
                "Column.Intrinsic.VideoDuration",
                33,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Point d'entrée audio",
                "Column.Intrinsic.AudioInPoint",
                3,
                0,
                true,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Point de sortie audio",
                "Column.Intrinsic.AudioOutPoint",
                4,
                0,
                true,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Durée audio",
                "Column.Intrinsic.AudioDuration",
                1,
                0,
                true,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Début du sous-élément",
                "Column.Intrinsic.SubclipStart",
                39,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Fin du sous-élément",
                "Column.Intrinsic.SubclipEnd",
                40,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Durée du sous-élément",
                "Column.Intrinsic.SubclipDuration",
                41,
                0,
                false,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Infos vidéo",
                "Column.Intrinsic.VideoInfo",
                34,
                0,
                false,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Infos audio",
                "Column.Intrinsic.AudioInfo",
                2,
                0,
                false,
                150
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Utilisation vidéo",
                "Column.Intrinsic.VideoUsage",
                38,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Utilisation audio",
                "Column.Intrinsic.AudioUsage",
                6,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Nom de la bande",
                "Column.Intrinsic.TapeName",
                30,
                0,
                false,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Description",
                "Column.PropertyText.Description",
                15,
                0,
                false,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Commentaire",
                "Column.PropertyText.Comment",
                10,
                1,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Remarque",
                "Column.Intrinsic.LogNote",
                18,
                0,
                false,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Chemin d'accès du média",
                "Column.Intrinsic.FilePath",
                16,
                0,
                true,
                100
        ));

        listeColumn.add(new CaptureSettingsColumn(
                ++objectId,
                "Réglages d'acquisition",
                "Column.PropertyText.CaptureSettings",
                0,
                2,
                false,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Etat",
                "Column.PropertyText.Status",
                29,
                0,
                false,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Propriétés off-line",
                "Column.PropertyText.OfflineProperties",
                25,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Nom du fichier média",
                "Column.Intrinsic.FileName",
                58,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Scène",
                "Column.PropertyText.Scene",
                27,
                0,
                false,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Plan",
                "Column.PropertyText.Shot",
                28,
                0,
                false,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Client",
                "Column.PropertyText.Client",
                9,
                1,
                true,
                100
        ));

        listeColumn.add(new BoolPropertyColumn(
                ++objectId,
                "Bon(ne)",
                "Column.PropertyBool.Good",
                0,
                2,
                false,
                100,
                "Column.PropertyBool.Good",
                "true"
        ));

        listeColumn.add(new BoolPropertyColumn(
                ++objectId,
                "Masquer",
                "Column.PropertyBool.Hide",
                0,
                2,
                false,
                100,
                "Column.PropertyBool.Hide",
                "true"
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Code temporel sonore",
                "Column.Intrinsic.SoundTimeCode",
                42,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Déroul. sonore",
                "Column.PropertyText.SoundRoll",
                43,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Pellicule",
                "Column.PropertyText.FilmCameraRoll",
                47,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Pellicule quotidienne",
                "Column.PropertyText.FilmDailyRoll",
                48,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Pellicule labo",
                "Column.PropertyText.FilmLabRoll",
                49,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Code d’identification",
                "Column.PropertyText.FilmKeycode",
                50,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Décalage de synchronisation",
                "Column.PropertyText.SyncOffset",
                44,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Codec vidéo",
                "Column.PropertyText.Codec",
                45,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Ordre des trames",
                "Column.PropertyText.FieldOrder",
                46,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Doublure",
                "Column.PropertyText.Proxy",
                51,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Projet verrouillé",
                "Column.PropertyText.BinLocked",
                52,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "ASC_SOP",
                "Column.PropertyText.ASCSOP",
                53,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "ASC_SAT",
                "Column.PropertyText.ASCSAT",
                54,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "LUT",
                "Column.PropertyText.Lut",
                55,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "LUT1",
                "Column.PropertyText.Lut1",
                56,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "LUT2",
                "Column.PropertyText.Lut2",
                57,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Nom du fichier vidéo d’origine",
                "Column.PropertyText.OriginalVideoFileName",
                59,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Nom du fichier audio d’origine",
                "Column.PropertyText.OriginalAudioFileName",
                60,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Chemin d’accès au fichier de média de doublure",
                "Column.Intrinsic.ProxyFilePath",
                37,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Nom du fichier de média de doublure",
                "Column.Intrinsic.ProxyFileName",
                61,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Statut de la transcription",
                "Column.Intrinsic.TranscriptStatus",
                63,
                0,
                false,
                100
        ));

        listeColumn.add(new LabelColumn(
                ++objectId,
                "Libellé",
                "Column.PropertyText.Label",
                17,
                1,
                false,
                26
        ));

        listeColumn.add(new SelectedItemsColumn(
                ++objectId,
                "Sélectionné(s)",
                "Column.PropertyText.SelectedItems",
                0,
                1,
                true,
                26
        ));

        listeColumn.add(new NameColumn(
                ++objectId,
                "Nom",
                "Column.Intrinsic.Name",
                0,
                0,
                false,
                200
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Type de média",
                "Column.Intrinsic.MediaType",
                23,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Fréquence d'images",
                "Column.Intrinsic.MediaTimebase",
                22,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Début du média",
                "Column.Intrinsic.MediaStart",
                21,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Fin du média",
                "Column.Intrinsic.MediaEnd",
                20,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Durée du média",
                "Column.Intrinsic.MediaDuration",
                19,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Point d'entrée vidéo",
                "Column.Intrinsic.VideoInPoint",
                35,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Point de sortie vidéo",
                "Column.Intrinsic.VideoOutPoint",
                36,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Durée vidéo",
                "Column.Intrinsic.VideoDuration",
                33,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Point d'entrée audio",
                "Column.Intrinsic.AudioInPoint",
                3,
                0,
                true,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Point de sortie audio",
                "Column.Intrinsic.AudioOutPoint",
                4,
                0,
                true,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Durée audio",
                "Column.Intrinsic.AudioDuration",
                1,
                0,
                true,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Début du sous-élément",
                "Column.Intrinsic.SubclipStart",
                39,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Fin du sous-élément",
                "Column.Intrinsic.SubclipEnd",
                40,
                0,
                false,
                100
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Durée du sous-élément",
                "Column.Intrinsic.SubclipDuration",
                41,
                0,
                false,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Infos vidéo",
                "Column.Intrinsic.VideoInfo",
                34,
                0,
                false,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Infos audio",
                "Column.Intrinsic.AudioInfo",
                2,
                0,
                false,
                150
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Utilisation vidéo",
                "Column.Intrinsic.VideoUsage",
                38,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Utilisation audio",
                "Column.Intrinsic.AudioUsage",
                6,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Nom de la bande",
                "Column.Intrinsic.TapeName",
                30,
                0,
                false,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Description",
                "Column.PropertyText.Description",
                15,
                0,
                false,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Commentaire",
                "Column.PropertyText.Comment",
                10,
                1,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Remarque",
                "Column.Intrinsic.LogNote",
                18,
                0,
                false,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Chemin d'accès du média",
                "Column.Intrinsic.FilePath",
                16,
                0,
                true,
                100
        ));

        listeColumn.add(new CaptureSettingsColumn(
                ++objectId,
                "Réglages d'acquisition",
                "Column.PropertyText.CaptureSettings",
                0,
                2,
                false,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Etat",
                "Column.PropertyText.Status",
                29,
                0,
                false,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Propriétés off-line",
                "Column.PropertyText.OfflineProperties",
                25,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Nom du fichier média",
                "Column.Intrinsic.FileName",
                58,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Scène",
                "Column.PropertyText.Scene",
                27,
                0,
                false,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Plan",
                "Column.PropertyText.Shot",
                28,
                0,
                false,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Client",
                "Column.PropertyText.Client",
                9,
                1,
                true,
                100
        ));

        listeColumn.add(new BoolPropertyColumn(
                ++objectId,
                "Bon(ne)",
                "Column.PropertyBool.Good",
                0,
                2,
                false,
                100,
                "Column.PropertyBool.Good",
                "true"
        ));

        listeColumn.add(new BoolPropertyColumn(
                ++objectId,
                "Masquer",
                "Column.PropertyBool.Hide",
                0,
                2,
                false,
                100,
                "Column.PropertyBool.Hide",
                "true"
        ));

        listeColumn.add(new TimecodeColumn(
                ++objectId,
                "Code temporel sonore",
                "Column.Intrinsic.SoundTimeCode",
                42,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Déroul. sonore",
                "Column.PropertyText.SoundRoll",
                43,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Pellicule",
                "Column.PropertyText.FilmCameraRoll",
                47,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Pellicule quotidienne",
                "Column.PropertyText.FilmDailyRoll",
                48,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Pellicule labo",
                "Column.PropertyText.FilmLabRoll",
                49,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Code d’identification",
                "Column.PropertyText.FilmKeycode",
                50,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Décalage de synchronisation",
                "Column.PropertyText.SyncOffset",
                44,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Codec vidéo",
                "Column.PropertyText.Codec",
                45,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Ordre des trames",
                "Column.PropertyText.FieldOrder",
                46,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Doublure",
                "Column.PropertyText.Proxy",
                51,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Projet verrouillé",
                "Column.PropertyText.BinLocked",
                52,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "ASC_SOP",
                "Column.PropertyText.ASCSOP",
                53,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "ASC_SAT",
                "Column.PropertyText.ASCSAT",
                54,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "LUT",
                "Column.PropertyText.Lut",
                55,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "LUT1",
                "Column.PropertyText.Lut1",
                56,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "LUT2",
                "Column.PropertyText.Lut2",
                57,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Nom du fichier vidéo d’origine",
                "Column.PropertyText.OriginalVideoFileName",
                59,
                0,
                true,
                100
        ));

        listeColumn.add(new EditTextColumn(
                ++objectId,
                "Nom du fichier audio d’origine",
                "Column.PropertyText.OriginalAudioFileName",
                60,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Chemin d’accès au fichier de média de doublure",
                "Column.Intrinsic.ProxyFilePath",
                37,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Nom du fichier de média de doublure",
                "Column.Intrinsic.ProxyFileName",
                61,
                0,
                true,
                100
        ));

        listeColumn.add(new StringColumn(
                ++objectId,
                "Statut de la transcription",
                "Column.Intrinsic.TranscriptStatus",
                63,
                0,
                false,
                100
        ));

        for (Column column : listeColumn) {
            if (column instanceof CaptureSettingsColumn captureSettingsColumn) {
                stringBuilder.append(captureSettingsColumn.toXML());
            } else if (column instanceof EditTextColumn editTextColumn) {
                stringBuilder.append(editTextColumn.toXML());
            } else if (column instanceof LabelColumn labelColumn) {
                stringBuilder.append(labelColumn.toXML());
            } else if (column instanceof SelectedItemsColumn selectedItemsColumn) {
                stringBuilder.append(selectedItemsColumn.toXML());
            } else if (column instanceof NameColumn nameColumn) {
                stringBuilder.append(nameColumn.toXML());
            } else if (column instanceof StringColumn stringColumn) {
                stringBuilder.append(stringColumn.toXML());
            } else if (column instanceof TimecodeColumn timecodeColumn) {
                stringBuilder.append(timecodeColumn.toXML());
            } else if (column instanceof BoolPropertyColumn boolPropertyColumn) {
                stringBuilder.append(boolPropertyColumn.toXML());
            } else {
                throw new AdobePremiereProjectException("Pas de : '" + column.getClass().getName() + "'.");
            }
        }

        stringBuilder.append("\t\t\t\t</ProjectViewState.List>\n");
        stringBuilder.append("\t\t\t\t<AM.PJShowWellState>0</AM.PJShowWellState>\n");
        stringBuilder.append("\t\t\t\t<BE.Prefs.AcceleratedRenderer.LastUsedDisplayName>Accélération GPU Mercury Playback Engine (Metal)</BE.Prefs.AcceleratedRenderer.LastUsedDisplayName>\n");
        stringBuilder.append("\t\t\t\t<BE.Prefs.AcceleratedRenderer.LastUsedIdentifier>6ed1497e-17ad-4a5b-846f-52bb81e20104</BE.Prefs.AcceleratedRenderer.LastUsedIdentifier>\n");
        stringBuilder.append("\t\t\t\t<BE.Prefs.kPrefsAcceleratedRenderer.OverridenIdentifier>6ed1497e-17ad-4a5b-846f-52bb81e20104</BE.Prefs.kPrefsAcceleratedRenderer.OverridenIdentifier>\n");

        if (Title.getTitleNumber() > 1) {
            stringBuilder.append("\t\t\t\t<FE.Prefs.Titler.TitleCounter>" + Title.getTitleNumber() + "</FE.Prefs.Titler.TitleCounter>\n");
        }

        stringBuilder.append("\t\t\t\t<MZ.BuildVersion.Created>24.0.0x58 - 22-01-24 12:03:57</MZ.BuildVersion.Created>\n");
        stringBuilder.append("\t\t\t\t<MZ.BuildVersion.Modified>24.0.0x58 - 22-01-24 12:03:59</MZ.BuildVersion.Modified>\n");

        // S'il y a une séquence dans le projet.
        if (Sequence.getSequenceNumber() > 1) {
            stringBuilder.append("\t\t\t\t<MZ.NextSequenceIndex>" + (Sequence.getSequenceNumber()) + "</MZ.NextSequenceIndex>\n");
            stringBuilder.append("\t\t\t\t<MZ.PrefixKey.OpenSequenceGuidList.1>9d8a2607-057b-47be-8e25-56261a940524</MZ.PrefixKey.OpenSequenceGuidList.1>\n");
        }

        stringBuilder.append("\t\t\t\t<MZ.Project.ApplicationID>Pro</MZ.Project.ApplicationID>\n");
        stringBuilder.append("\t\t\t\t<MZ.Project.GUID>91b0c78e-e019-47e0-94c4-d0581286c3ab</MZ.Project.GUID>\n");
        stringBuilder.append("\t\t\t\t<MZ.Project.WorkspaceName>" + workspaceName + "</MZ.Project.WorkspaceName>\n");
        stringBuilder.append("\t\t\t\t<ProjectViewState.Version>2</ProjectViewState.Version>\n");
        stringBuilder.append("\t\t\t\t<TL.PJSnappingState>1</TL.PJSnappingState>\n");
        stringBuilder.append("\t\t\t\t<project.settings.lastknowngoodprojectpath>/Users/macdevpro/Desktop/Projet-example.prproj</project.settings.lastknowngoodprojectpath>\n");
        stringBuilder.append("\t\t\t</Properties>\n");
        stringBuilder.append("\t\t</Node>\n");
        stringBuilder.append("\t\t<RootProjectItem ObjectURef=\"ae3aed9b-a494-4f2d-937c-2f513794f0f6\"/>\n");
        stringBuilder.append("\t\t<ProjectSettings ObjectRef=\"3\"/>\n");
        stringBuilder.append("\t\t<MovieCompileSettings ObjectRef=\"4\"/>\n");
        stringBuilder.append("\t\t<StillCompileSettings ObjectRef=\"5\"/>\n");
        stringBuilder.append("\t\t<AudioCompileSettings ObjectRef=\"6\"/>\n");
        stringBuilder.append("\t\t<CustomCompileSettings ObjectRef=\"7\"/>\n");
        stringBuilder.append("\t\t<VideoPreviewCompileSettings ObjectRef=\"8\"/>\n");
        stringBuilder.append("\t\t<ScratchDiskSettings ObjectRef=\"9\"/>\n");
        stringBuilder.append("\t\t<IngestSettings ObjectRef=\"10\"/>\n");
        stringBuilder.append("\t\t<ProjectWorkspace ObjectRef=\"11\"/>\n");
        stringBuilder.append("\t\t<NextSequenceID>" + Sequence.getSequenceNumber() + "</NextSequenceID>\n");
        stringBuilder.append("\t</Project>\n");
        stringBuilder.append("\t<RootProjectItem ObjectUID=\"ae3aed9b-a494-4f2d-937c-2f513794f0f6\" ClassID=\"1c307a89-9318-47d7-a583-bf2553736543\" Version=\"1\">\n");
        stringBuilder.append("\t\t<ProjectItem Version=\"1\">\n");
        stringBuilder.append("\t\t\t<Node Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t<Properties Version=\"1\">\n");
        stringBuilder.append("\t\t\t\t<project.freeform.view.bin.coordinate>{}</project.freeform.view.bin.coordinate>");
        stringBuilder.append("\t\t\t\t<project.freeform.view.bin.item.zoom>{}</project.freeform.view.bin.item.zoom>");

        stringBuilder.append("\t\t\t\t\t<list.view.expanded.state.379921dc_45_03cc_45_4e04_45_8bb9_45_12bf337af0c9>true</list.view.expanded.state.379921dc_45_03cc_45_4e04_45_8bb9_45_12bf337af0c9>\n");
        stringBuilder.append("\t\t\t\t</Properties>\n");
        stringBuilder.append("\t\t\t\t<ID>1000000</ID>\n");
        stringBuilder.append("\t\t\t</Node>\n");
        stringBuilder.append("\t\t\t<Name>Root Bin</Name>\n");
        stringBuilder.append("\t\t</ProjectItem>\n");
        stringBuilder.append("\t\t<ProjectItemContainer Version=\"1\">\n");

        return stringBuilder.toString();
    }

    /**
     * Ecrit la structure du projet, la fin.
     */
    private String toXMLEnd() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\t\t</ProjectItemContainer>\n");
        stringBuilder.append("\t</RootProjectItem>\n");
        stringBuilder.append("\t<ProjectSettings ObjectID=\"3\" ClassID=\"50c16708-a1a1-4d2f-98d5-4e283ae28353\" Version=\"20\">\n");
        stringBuilder.append("\t\t<VideoSettings ObjectRef=\"12\"/>\n");
        stringBuilder.append("\t\t<AudioSettings ObjectRef=\"13\"/>\n");
        stringBuilder.append("\t\t<VideoCompileSettings ObjectRef=\"14\"/>\n");
        stringBuilder.append("\t\t<AudioCompileSettings ObjectRef=\"15\"/>\n");
        stringBuilder.append("\t\t<CaptureSettings ObjectRef=\"16\"/>\n");
        stringBuilder.append("\t\t<DefaultSequenceSettings ObjectRef=\"17\"/>\n");
        stringBuilder.append("\t\t<EditingModeID>00000000-0000-0000-0000-000000000000</EditingModeID>\n");
        stringBuilder.append("\t\t<PreviewFileFormatID>00000000-0000-0000-0000-000000000000</PreviewFileFormatID>\n");
        stringBuilder.append("\t\t<VideoTimeDisplay>102</VideoTimeDisplay>\n");
        stringBuilder.append("\t\t<VideoTimeDisplayInitial>102</VideoTimeDisplayInitial>\n");
        stringBuilder.append("\t\t<AudioTimeDisplay>200</AudioTimeDisplay>\n");
        stringBuilder.append("\t\t<ActionSafeWidth>10</ActionSafeWidth>\n");
        stringBuilder.append("\t\t<ActionSafeHeight>10</ActionSafeHeight>\n");
        stringBuilder.append("\t\t<TitleSafeWidth>20</TitleSafeWidth>\n");
        stringBuilder.append("\t\t<TitleSafeHeight>20</TitleSafeHeight>\n");
        stringBuilder.append("\t\t<ShouldScaleMedia>false</ShouldScaleMedia>\n");
        stringBuilder.append("\t\t<UsePreviewCache>false</UsePreviewCache>\n");
        stringBuilder.append("\t\t<ColorManagementSettings>{\"graphicsWhiteLuminance\":203,\"lutInterpolationMethod\":1}</ColorManagementSettings>\n");
        stringBuilder.append("\t</ProjectSettings>\n");

        List<CompileSettings> listeCompileSettings = new ArrayList<CompileSettings>();

        listeCompileSettings.add(new CompileSettings(4, 18, 19));
        listeCompileSettings.add(new CompileSettings(5, 20, 21));
        listeCompileSettings.add(new CompileSettings(6, 22, 23));
        listeCompileSettings.add(new CompileSettings(7, 24, 25));
        listeCompileSettings.add(new CompileSettings(8, 26, 27));

        for (CompileSettings compileSetting : listeCompileSettings) {
            stringBuilder.append(compileSetting.toXML());
        }

        String sameAsProject = "SameAsProject";

        stringBuilder.append("\t<ScratchDiskSettings ObjectID=\"9\" ClassID=\"4c6ed82b-a81c-4df1-8bd0-750504c4b560\" Version=\"4\">\n");
        stringBuilder.append("\t\t<CapsuleMediaLocation0>" + sameAsProject + "</CapsuleMediaLocation0>\n");
        stringBuilder.append("\t\t<CCLibrariesLocation0>" + sameAsProject + "</CCLibrariesLocation0>\n");
        stringBuilder.append("\t\t<AutoSaveLocation0>" + sameAsProject + "</AutoSaveLocation0>\n");
        stringBuilder.append("\t\t<TransferMediaLocation0>" + sameAsProject + "</TransferMediaLocation0>\n");
        stringBuilder.append("\t\t<DVDEncodingLocation0>" + sameAsProject + "</DVDEncodingLocation0>\n");
        stringBuilder.append("\t\t<AudioPreviewLocation0>" + sameAsProject + "</AudioPreviewLocation0>\n");
        stringBuilder.append("\t\t<VideoPreviewLocation0>" + sameAsProject + "</VideoPreviewLocation0>\n");
        stringBuilder.append("\t\t<CapturedAudioLocation0>" + sameAsProject + "</CapturedAudioLocation0>\n");
        stringBuilder.append("\t\t<CapturedVideoLocation0>" + sameAsProject + "</CapturedVideoLocation0>\n");
        stringBuilder.append("\t</ScratchDiskSettings>\n");

        stringBuilder.append("\t<IngestSettings ObjectID=\"10\" ClassID=\"2db8f76b-2c37-48ee-925d-9a4f7278152d\" Version=\"1\">\n");
        stringBuilder.append("\t\t<Enabled>false</Enabled>\n");
        stringBuilder.append("\t\t<Action>copy</Action>\n");
        stringBuilder.append("\t\t<PresetPath>/Applications/Adobe Premiere Pro 2023/Adobe Premiere Pro 2023.app/Contents/Settings/IngestPresets/Copy/Copy With MD5 Verification.epr</PresetPath>\n");
        stringBuilder.append("\t\t<CopyDestination>SameAsProject</CopyDestination>\n");
        stringBuilder.append("\t\t<MachineID>6e386481-14b7-43bb-890d-ae81def9e5ff</MachineID>\n");
        stringBuilder.append("\t</IngestSettings>\n");

        stringBuilder.append("\t<WorkspaceSettings ObjectID=\"11\" ClassID=\"c4372273-e1aa-4683-98aa-a2ceadf3066c\" Version=\"1\">\n");
        stringBuilder.append("\t\t<WorkspaceName>Montage</WorkspaceName>\n");
        stringBuilder.append("\t</WorkspaceSettings>\n");

        // Ajoute les dossiers de niveau 0 = ceux à la racine du projet.
        stringBuilder.append(this.toXMLBinProject(0));

        stringBuilder.append("\t<VideoSettings ObjectID=\"12\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        stringBuilder.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        stringBuilder.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        stringBuilder.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        stringBuilder.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        stringBuilder.append("\t</VideoSettings>\n");

        stringBuilder.append("\t<AudioSettings ObjectID=\"13\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        stringBuilder.append("\t\t<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t\t<ChannelType>1</ChannelType>\n");
        stringBuilder.append("\t</AudioSettings>\n");

        stringBuilder.append("\t<VideoCompileSettings ObjectID=\"14\" ClassID=\"db372db5-7de2-4d3c-98ae-f42659d77b22\" Version=\"9\">\n");
        stringBuilder.append("\t\t<VideoSettings ObjectRef=\"28\"/>\n");
        stringBuilder.append("\t\t<VideoCompilerClassIDFourCC>1061109567</VideoCompilerClassIDFourCC>\n");
        stringBuilder.append("\t\t<VideoFileTypeFourCC>1096173910</VideoFileTypeFourCC>\n");
        stringBuilder.append("\t\t<Compressor>1685288560</Compressor>\n");
        stringBuilder.append("\t\t<Depth>24</Depth>\n");
        stringBuilder.append("\t\t<Aspect43>false</Aspect43>\n");
        stringBuilder.append("\t\t<Quality>100</Quality>\n");
        stringBuilder.append("\t\t<UseDataRate>false</UseDataRate>\n");
        stringBuilder.append("\t\t<DataRate>3500</DataRate>\n");
        stringBuilder.append("\t\t<ForceRecompress>true</ForceRecompress>\n");
        stringBuilder.append("\t\t<ForceRecompressValue>2</ForceRecompressValue>\n");
        stringBuilder.append("\t\t<Deinterlace>false</Deinterlace>\n");
        stringBuilder.append("\t\t<IgnoreVideoFilters>false</IgnoreVideoFilters>\n");
        stringBuilder.append("\t\t<OptimizeStills>false</OptimizeStills>\n");
        stringBuilder.append("\t\t<FramesAtMarkers>false</FramesAtMarkers>\n");
        stringBuilder.append("\t\t<RealTimePreview>true</RealTimePreview>\n");
        stringBuilder.append("\t\t<VideoFieldType>0</VideoFieldType>\n");
        stringBuilder.append("\t\t<DoKeyframeEveryNFrames>false</DoKeyframeEveryNFrames>\n");
        stringBuilder.append("\t\t<DoKeyframeEveryNFramesValue>0</DoKeyframeEveryNFramesValue>\n");
        stringBuilder.append("\t\t<AddKeyframesAtMarkers>false</AddKeyframesAtMarkers>\n");
        stringBuilder.append("\t\t<AddKeyframesAtEdits>false</AddKeyframesAtEdits>\n");
        stringBuilder.append("\t\t<RelativeFrameSize>1</RelativeFrameSize>\n");
        stringBuilder.append("\t\t<RenderDepth>0</RenderDepth>\n");
        stringBuilder.append("\t</VideoCompileSettings>\n");

        stringBuilder.append("\t<AudioCompileSettings ObjectID=\"15\" ClassID=\"34b10007-ab6d-49a7-bac5-7b60d919e387\" Version=\"6\">\n");
        stringBuilder.append("\t\t<AudioSettings ObjectRef=\"29\"/>\n");
        stringBuilder.append("\t\t<SampleType>3</SampleType>\n");
        stringBuilder.append("\t\t<Compressor>1380013856</Compressor>\n");
        stringBuilder.append("\t\t<Interleave>1</Interleave>\n");
        stringBuilder.append("\t</AudioCompileSettings>\n");

        stringBuilder.append("\t<CaptureSettings ObjectID=\"16\" ClassID=\"328c2aa2-47f9-4211-805b-b6a6dbd4ca29\" Version=\"10\">\n");
        stringBuilder.append("\t\t<RecordModuleDisplayName>HDV</RecordModuleDisplayName>\n");
        stringBuilder.append("\t\t<SupportedFileExtension>avi</SupportedFileExtension>\n");
        stringBuilder.append("\t\t<VideoFrameRate>8475667200</VideoFrameRate>\n");
        stringBuilder.append("\t\t<VideoFrameSize>0,0,720,480</VideoFrameSize>\n");
        stringBuilder.append("\t\t<VideoCompressorFourCC>0</VideoCompressorFourCC>\n");
        stringBuilder.append("\t\t<AudioCompressorFourCC>1380013856</AudioCompressorFourCC>\n");
        stringBuilder.append("\t\t<AudioCompressorDisplayName>Non compressé</AudioCompressorDisplayName>\n");
        stringBuilder.append("\t\t<AudioFrameRate>7938000</AudioFrameRate>\n");
        stringBuilder.append("\t\t<AudioSampleType>3</AudioSampleType>\n");
        stringBuilder.append("\t\t<AudioChannelType>1</AudioChannelType>\n");
        stringBuilder.append("\t\t<AbortCaptureOnDroppedFrames>false</AbortCaptureOnDroppedFrames>\n");
        stringBuilder.append("\t\t<RecorderID>ae351743-b529-451e-a2d4-9ccf1ad8d8b6</RecorderID>\n");
        stringBuilder.append("\t</CaptureSettings>\n");

        stringBuilder.append("\t<DefaultSequenceSettings ObjectID=\"17\" ClassID=\"567bdf53-d6d9-4d61-b2f1-f4834bebea9b\" Version=\"2\">\n");
        stringBuilder.append("\t\t<TotalVideoTracks>1</TotalVideoTracks>\n");
        stringBuilder.append("\t\t<DefaultAudioStandardMonoTracks>0</DefaultAudioStandardMonoTracks>\n");
        stringBuilder.append("\t\t<DefaultAudioStandardStereoTracks>1</DefaultAudioStandardStereoTracks>\n");
        stringBuilder.append("\t\t<DefaultAudioStandard51Tracks>0</DefaultAudioStandard51Tracks>\n");
        stringBuilder.append("\t\t<DefaultAudioSubmixMonoTracks>0</DefaultAudioSubmixMonoTracks>\n");
        stringBuilder.append("\t\t<DefaultAudioSubmixStereoTracks>0</DefaultAudioSubmixStereoTracks>\n");
        stringBuilder.append("\t\t<DefaultAudioSubmix51Tracks>0</DefaultAudioSubmix51Tracks>\n");
        stringBuilder.append("\t</DefaultSequenceSettings>\n");

        stringBuilder.append("\t<VideoCompileSettings ObjectID=\"18\" ClassID=\"db372db5-7de2-4d3c-98ae-f42659d77b22\" Version=\"9\">\n");
        stringBuilder.append("\t\t<VideoSettings ObjectRef=\"30\"/>\n");
        stringBuilder.append("\t\t<VideoCompilerClassIDFourCC>1061109567</VideoCompilerClassIDFourCC>\n");
        stringBuilder.append("\t\t<VideoFileTypeFourCC>1096173910</VideoFileTypeFourCC>\n");
        stringBuilder.append("\t\t<Compressor>1685288560</Compressor>\n");
        stringBuilder.append("\t\t<Depth>24</Depth>\n");
        stringBuilder.append("\t\t<Aspect43>false</Aspect43>\n");
        stringBuilder.append("\t\t<Quality>100</Quality>\n");
        stringBuilder.append("\t\t<UseDataRate>false</UseDataRate>\n");
        stringBuilder.append("\t\t<DataRate>3500</DataRate>\n");
        stringBuilder.append("\t\t<ForceRecompress>true</ForceRecompress>\n");
        stringBuilder.append("\t\t<ForceRecompressValue>2</ForceRecompressValue>\n");
        stringBuilder.append("\t\t<Deinterlace>false</Deinterlace>\n");
        stringBuilder.append("\t\t<IgnoreVideoFilters>false</IgnoreVideoFilters>\n");
        stringBuilder.append("\t\t<OptimizeStills>false</OptimizeStills>\n");
        stringBuilder.append("\t\t<FramesAtMarkers>false</FramesAtMarkers>\n");
        stringBuilder.append("\t\t<RealTimePreview>true</RealTimePreview>\n");
        stringBuilder.append("\t\t<VideoFieldType>0</VideoFieldType>\n");
        stringBuilder.append("\t\t<DoKeyframeEveryNFrames>false</DoKeyframeEveryNFrames>\n");
        stringBuilder.append("\t\t<DoKeyframeEveryNFramesValue>0</DoKeyframeEveryNFramesValue>\n");
        stringBuilder.append("\t\t<AddKeyframesAtMarkers>false</AddKeyframesAtMarkers>\n");
        stringBuilder.append("\t\t<AddKeyframesAtEdits>false</AddKeyframesAtEdits>\n");
        stringBuilder.append("\t\t<RelativeFrameSize>1</RelativeFrameSize>\n");
        stringBuilder.append("\t\t<RenderDepth>0</RenderDepth>\n");
        stringBuilder.append("\t</VideoCompileSettings>\n");

        stringBuilder.append("\t<AudioCompileSettings ObjectID=\"19\" ClassID=\"34b10007-ab6d-49a7-bac5-7b60d919e387\" Version=\"6\">\n");
        stringBuilder.append("\t\t<AudioSettings ObjectRef=\"31\"/>\n");
        stringBuilder.append("\t\t<SampleType>3</SampleType>\n");
        stringBuilder.append("\t\t<Compressor>1380013856</Compressor>\n");
        stringBuilder.append("\t\t<Interleave>1</Interleave>\n");
        stringBuilder.append("\t</AudioCompileSettings>\n");

        stringBuilder.append("\t<VideoCompileSettings ObjectID=\"20\" ClassID=\"db372db5-7de2-4d3c-98ae-f42659d77b22\" Version=\"9\">\n");
        stringBuilder.append("\t\t<VideoSettings ObjectRef=\"32\"/>\n");
        stringBuilder.append("\t\t<VideoCompilerClassIDFourCC>1061109567</VideoCompilerClassIDFourCC>\n");
        stringBuilder.append("\t\t<VideoFileTypeFourCC>1096173910</VideoFileTypeFourCC>\n");
        stringBuilder.append("\t\t<Compressor>1685288560</Compressor>\n");
        stringBuilder.append("\t\t<Depth>24</Depth>\n");
        stringBuilder.append("\t\t<Aspect43>false</Aspect43>\n");
        stringBuilder.append("\t\t<Quality>100</Quality>\n");
        stringBuilder.append("\t\t<UseDataRate>false</UseDataRate>\n");
        stringBuilder.append("\t\t<DataRate>3500</DataRate>\n");
        stringBuilder.append("\t\t<ForceRecompress>true</ForceRecompress>\n");
        stringBuilder.append("\t\t<ForceRecompressValue>2</ForceRecompressValue>\n");
        stringBuilder.append("\t\t<Deinterlace>false</Deinterlace>\n");
        stringBuilder.append("\t\t<IgnoreVideoFilters>false</IgnoreVideoFilters>\n");
        stringBuilder.append("\t\t<OptimizeStills>false</OptimizeStills>\n");
        stringBuilder.append("\t\t<FramesAtMarkers>false</FramesAtMarkers>\n");
        stringBuilder.append("\t\t<RealTimePreview>true</RealTimePreview>\n");
        stringBuilder.append("\t\t<VideoFieldType>0</VideoFieldType>\n");
        stringBuilder.append("\t\t<DoKeyframeEveryNFrames>false</DoKeyframeEveryNFrames>\n");
        stringBuilder.append("\t\t<DoKeyframeEveryNFramesValue>0</DoKeyframeEveryNFramesValue>\n");
        stringBuilder.append("\t\t<AddKeyframesAtMarkers>false</AddKeyframesAtMarkers>\n");
        stringBuilder.append("\t\t<AddKeyframesAtEdits>false</AddKeyframesAtEdits>\n");
        stringBuilder.append("\t\t<RelativeFrameSize>1</RelativeFrameSize>\n");
        stringBuilder.append("\t\t<RenderDepth>0</RenderDepth>\n");
        stringBuilder.append("\t</VideoCompileSettings>\n");

        stringBuilder.append("\t<AudioCompileSettings ObjectID=\"21\" ClassID=\"34b10007-ab6d-49a7-bac5-7b60d919e387\" Version=\"6\">\n");
        stringBuilder.append("\t\t<AudioSettings ObjectRef=\"33\"/>\n");
        stringBuilder.append("\t\t<SampleType>3</SampleType>\n");
        stringBuilder.append("\t\t<Compressor>1380013856</Compressor>\n");
        stringBuilder.append("\t\t<Interleave>1</Interleave>\n");
        stringBuilder.append("\t</AudioCompileSettings>\n");

        stringBuilder.append("\t<VideoCompileSettings ObjectID=\"22\" ClassID=\"db372db5-7de2-4d3c-98ae-f42659d77b22\" Version=\"9\">\n");
        stringBuilder.append("\t\t<VideoSettings ObjectRef=\"34\"/>\n");
        stringBuilder.append("\t\t<VideoCompilerClassIDFourCC>1061109567</VideoCompilerClassIDFourCC>\n");
        stringBuilder.append("\t\t<VideoFileTypeFourCC>1096173910</VideoFileTypeFourCC>\n");
        stringBuilder.append("\t\t<Compressor>1685288560</Compressor>\n");
        stringBuilder.append("\t\t<Depth>24</Depth>\n");
        stringBuilder.append("\t\t<Aspect43>false</Aspect43>\n");
        stringBuilder.append("\t\t<Quality>100</Quality>\n");
        stringBuilder.append("\t\t<UseDataRate>false</UseDataRate>\n");
        stringBuilder.append("\t\t<DataRate>3500</DataRate>\n");
        stringBuilder.append("\t\t<ForceRecompress>true</ForceRecompress>\n");
        stringBuilder.append("\t\t<ForceRecompressValue>2</ForceRecompressValue>\n");
        stringBuilder.append("\t\t<Deinterlace>false</Deinterlace>\n");
        stringBuilder.append("\t\t<IgnoreVideoFilters>false</IgnoreVideoFilters>\n");
        stringBuilder.append("\t\t<OptimizeStills>false</OptimizeStills>\n");
        stringBuilder.append("\t\t<FramesAtMarkers>false</FramesAtMarkers>\n");
        stringBuilder.append("\t\t<RealTimePreview>true</RealTimePreview>\n");
        stringBuilder.append("\t\t<VideoFieldType>0</VideoFieldType>\n");
        stringBuilder.append("\t\t<DoKeyframeEveryNFrames>false</DoKeyframeEveryNFrames>\n");
        stringBuilder.append("\t\t<DoKeyframeEveryNFramesValue>0</DoKeyframeEveryNFramesValue>\n");
        stringBuilder.append("\t\t<AddKeyframesAtMarkers>false</AddKeyframesAtMarkers>\n");
        stringBuilder.append("\t\t<AddKeyframesAtEdits>false</AddKeyframesAtEdits>\n");
        stringBuilder.append("\t\t<RelativeFrameSize>1</RelativeFrameSize>\n");
        stringBuilder.append("\t\t<RenderDepth>0</RenderDepth>\n");
        stringBuilder.append("\t</VideoCompileSettings>\n");

        stringBuilder.append("\t<AudioCompileSettings ObjectID=\"23\" ClassID=\"34b10007-ab6d-49a7-bac5-7b60d919e387\" Version=\"6\">\n");
        stringBuilder.append("\t\t<AudioSettings ObjectRef=\"35\"/>\n");
        stringBuilder.append("\t\t<SampleType>3</SampleType>\n");
        stringBuilder.append("\t\t<Compressor>1380013856</Compressor>\n");
        stringBuilder.append("\t\t<Interleave>1</Interleave>\n");

        stringBuilder.append("\t</AudioCompileSettings>\n");

        stringBuilder.append("\t<VideoCompileSettings ObjectID=\"24\" ClassID=\"db372db5-7de2-4d3c-98ae-f42659d77b22\" Version=\"9\">\n");
        stringBuilder.append("\t\t<VideoSettings ObjectRef=\"36\"/>\n");
        stringBuilder.append("\t\t<VideoCompilerClassIDFourCC>1061109567</VideoCompilerClassIDFourCC>\n");
        stringBuilder.append("\t\t<VideoFileTypeFourCC>1096173910</VideoFileTypeFourCC>\n");
        stringBuilder.append("\t\t<Compressor>1685288560</Compressor>\n");
        stringBuilder.append("\t\t<Depth>24</Depth>\n");
        stringBuilder.append("\t\t<Aspect43>false</Aspect43>\n");
        stringBuilder.append("\t\t<Quality>100</Quality>\n");
        stringBuilder.append("\t\t<UseDataRate>false</UseDataRate>\n");
        stringBuilder.append("\t\t<DataRate>3500</DataRate>\n");
        stringBuilder.append("\t\t<ForceRecompress>true</ForceRecompress>\n");
        stringBuilder.append("\t\t<ForceRecompressValue>2</ForceRecompressValue>\n");
        stringBuilder.append("\t\t<Deinterlace>false</Deinterlace>\n");
        stringBuilder.append("\t\t<IgnoreVideoFilters>false</IgnoreVideoFilters>\n");
        stringBuilder.append("\t\t<OptimizeStills>false</OptimizeStills>\n");
        stringBuilder.append("\t\t<FramesAtMarkers>false</FramesAtMarkers>\n");
        stringBuilder.append("\t\t<RealTimePreview>true</RealTimePreview>\n");
        stringBuilder.append("\t\t<VideoFieldType>0</VideoFieldType>\n");
        stringBuilder.append("\t\t<DoKeyframeEveryNFrames>false</DoKeyframeEveryNFrames>\n");
        stringBuilder.append("\t\t<DoKeyframeEveryNFramesValue>0</DoKeyframeEveryNFramesValue>\n");
        stringBuilder.append("\t\t<AddKeyframesAtMarkers>false</AddKeyframesAtMarkers>\n");
        stringBuilder.append("\t\t<AddKeyframesAtEdits>false</AddKeyframesAtEdits>\n");
        stringBuilder.append("\t\t<RelativeFrameSize>1</RelativeFrameSize>\n");
        stringBuilder.append("\t\t<RenderDepth>0</RenderDepth>\n");
        stringBuilder.append("\t</VideoCompileSettings>\n");

        stringBuilder.append("\t<AudioCompileSettings ObjectID=\"25\" ClassID=\"34b10007-ab6d-49a7-bac5-7b60d919e387\" Version=\"6\">\n");
        stringBuilder.append("\t\t<AudioSettings ObjectRef=\"37\"/>\n");
        stringBuilder.append("\t\t<SampleType>3</SampleType>\n");
        stringBuilder.append("\t\t<Compressor>1380013856</Compressor>\n");
        stringBuilder.append("\t\t<Interleave>1</Interleave>\n");
        stringBuilder.append("\t</AudioCompileSettings>\n");

        stringBuilder.append("\t<VideoCompileSettings ObjectID=\"26\" ClassID=\"db372db5-7de2-4d3c-98ae-f42659d77b22\" Version=\"9\">\n");
        stringBuilder.append("\t\t<VideoSettings ObjectRef=\"38\"/>\n");
        stringBuilder.append("\t\t<VideoCompilerClassIDFourCC>1061109567</VideoCompilerClassIDFourCC>\n");
        stringBuilder.append("\t\t<VideoFileTypeFourCC>1096173910</VideoFileTypeFourCC>\n");
        stringBuilder.append("\t\t<Compressor>1685288560</Compressor>\n");
        stringBuilder.append("\t\t<Depth>24</Depth>\n");
        stringBuilder.append("\t\t<Aspect43>false</Aspect43>\n");
        stringBuilder.append("\t\t<Quality>100</Quality>\n");
        stringBuilder.append("\t\t<UseDataRate>false</UseDataRate>\n");
        stringBuilder.append("\t\t<DataRate>3500</DataRate>\n");
        stringBuilder.append("\t\t<ForceRecompress>true</ForceRecompress>\n");
        stringBuilder.append("\t\t<ForceRecompressValue>2</ForceRecompressValue>\n");
        stringBuilder.append("\t\t<Deinterlace>false</Deinterlace>\n");
        stringBuilder.append("\t\t<IgnoreVideoFilters>false</IgnoreVideoFilters>\n");
        stringBuilder.append("\t\t<OptimizeStills>false</OptimizeStills>\n");
        stringBuilder.append("\t\t<FramesAtMarkers>false</FramesAtMarkers>\n");
        stringBuilder.append("\t\t<RealTimePreview>true</RealTimePreview>\n");
        stringBuilder.append("\t\t<VideoFieldType>0</VideoFieldType>\n");
        stringBuilder.append("\t\t<DoKeyframeEveryNFrames>false</DoKeyframeEveryNFrames>\n");
        stringBuilder.append("\t\t<DoKeyframeEveryNFramesValue>0</DoKeyframeEveryNFramesValue>\n");
        stringBuilder.append("\t\t<AddKeyframesAtMarkers>false</AddKeyframesAtMarkers>\n");
        stringBuilder.append("\t\t<AddKeyframesAtEdits>false</AddKeyframesAtEdits>\n");
        stringBuilder.append("\t\t<RelativeFrameSize>1</RelativeFrameSize>\n");
        stringBuilder.append("\t\t<RenderDepth>0</RenderDepth>\n");
        stringBuilder.append("\t</VideoCompileSettings>\n");

        stringBuilder.append("\t<AudioCompileSettings ObjectID=\"27\" ClassID=\"34b10007-ab6d-49a7-bac5-7b60d919e387\" Version=\"6\">\n");
        stringBuilder.append("\t\t<AudioSettings ObjectRef=\"39\"/>\n");
        stringBuilder.append("\t\t<SampleType>3</SampleType>\n");
        stringBuilder.append("\t\t<Compressor>1380013856</Compressor>\n");
        stringBuilder.append("\t\t<Interleave>1</Interleave>\n");
        stringBuilder.append("\t</AudioCompileSettings>\n");

        // Ajout les dossier de niveau 1 (sous-dossier).
        stringBuilder.append(this.toXMLBinProject(1));

        String classID = "fb11c33a-b0a9-4465-aa94-b6d5db2628cf";

        // Ajoute séquence/masterClip ici (de tous level ??????) :
        for (Element element : this.elements) {
            // Si c'est une séquence.
            if (element.getTypeElement() == TypeElement.SEQUENCE) {
                stringBuilder.append("\t<MasterClip ObjectUID=\"" + "ad5bd5cb-4336-473d-a7f2-74386fbfd563" + "\" ClassID=\"" + classID + "\" Version=\"9\">\n");
                stringBuilder.append("\t\t<LoggingInfo ObjectRef=\"40\"/>\n");
                stringBuilder.append("\t\t<AudioComponentChains Version=\"1\">\n");
                stringBuilder.append("\t\t\t<AudioComponentChain Index=\"0\" ObjectRef=\"41\"/>\n");
                stringBuilder.append("\t\t</AudioComponentChains>\n");
                stringBuilder.append("\t\t<Clips Version=\"1\">\n");
                stringBuilder.append("\t\t\t<Clip Index=\"0\" ObjectRef=\"42\"/>\n");
                stringBuilder.append("\t\t\t<Clip Index=\"1\" ObjectRef=\"43\"/>\n");
                stringBuilder.append("\t\t</Clips>\n");
                stringBuilder.append("\t\t<AudioClipChannelGroups ObjectRef=\"44\"/>\n");
                stringBuilder.append("\t\t<Name>" + element.getName() + "</Name>\n");
                stringBuilder.append("\t</MasterClip>\n");
            }
        }

        // Ajout titre/masterClip ici (de tous level ????) :
        for (Element element : this.elements) {
            // Si c'est une séquence.
            if (element.getTypeElement() == TypeElement.TITLE) {
                stringBuilder.append("\t<MasterClip ObjectUID=\"8c85bb49-dcaf-4511-aed8-9f6cead61d2a\" ClassID=\"" + classID + "\" Version=\"9\">\n");
                stringBuilder.append("\t\t<LoggingInfo ObjectRef=\"45\"/>\n");
                stringBuilder.append("\t\t<Clips Version=\"1\">\n");
                stringBuilder.append("\t\t\t<Clip Index=\"0\" ObjectRef=\"46\"/>\n");
                stringBuilder.append("\t\t</Clips>\n");
                stringBuilder.append("\t\t<AudioClipChannelGroups ObjectRef=\"47\"/>\n");
                stringBuilder.append("\t\t<Name>" + element.getName() + "</Name>\n");
                stringBuilder.append("\t</MasterClip>\n");
            }
        }

        stringBuilder.append("\t<VideoSettings ObjectID=\"28\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        stringBuilder.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        stringBuilder.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        stringBuilder.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        stringBuilder.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        stringBuilder.append("\t</VideoSettings>\n");

        stringBuilder.append("\t<AudioSettings ObjectID=\"29\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        stringBuilder.append("\t\t<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t\t<ChannelType>1</ChannelType>\n");
        stringBuilder.append("\t</AudioSettings>\n");

        stringBuilder.append("\t<VideoSettings ObjectID=\"30\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        stringBuilder.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        stringBuilder.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        stringBuilder.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        stringBuilder.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        stringBuilder.append("\t</VideoSettings>\n");

        stringBuilder.append("\t<AudioSettings ObjectID=\"31\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        stringBuilder.append("\t\t<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t\t<ChannelType>1</ChannelType>\n");
        stringBuilder.append("\t</AudioSettings>\n");

        stringBuilder.append("\t<VideoSettings ObjectID=\"32\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        stringBuilder.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        stringBuilder.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        stringBuilder.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        stringBuilder.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        stringBuilder.append("\t</VideoSettings>\n");

        stringBuilder.append("\t<AudioSettings ObjectID=\"33\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        stringBuilder.append("\t\t<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t\t<ChannelType>1</ChannelType>\n");
        stringBuilder.append("\t</AudioSettings>\n");

        stringBuilder.append("\t<VideoSettings ObjectID=\"34\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        stringBuilder.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        stringBuilder.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        stringBuilder.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        stringBuilder.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        stringBuilder.append("\t</VideoSettings>\n");

        stringBuilder.append("\t<AudioSettings ObjectID=\"35\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        stringBuilder.append("\t\t<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t\t<ChannelType>1</ChannelType>\n");
        stringBuilder.append("\t</AudioSettings>\n");

        stringBuilder.append("\t<VideoSettings ObjectID=\"36\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        stringBuilder.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        stringBuilder.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        stringBuilder.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        stringBuilder.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        stringBuilder.append("\t</VideoSettings>\n");

        stringBuilder.append("\t<AudioSettings ObjectID=\"37\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        stringBuilder.append("\t\t<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t\t<ChannelType>1</ChannelType>\n");
        stringBuilder.append("\t</AudioSettings>\n");

        stringBuilder.append("\t<VideoSettings ObjectID=\"38\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        stringBuilder.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        stringBuilder.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        stringBuilder.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        stringBuilder.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        stringBuilder.append("\t</VideoSettings>\n");

        stringBuilder.append("\t<AudioSettings ObjectID=\"39\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        stringBuilder.append("\t\t<FrameRate>5292000</FrameRate>\n");
        stringBuilder.append("\t\t<ChannelType>1</ChannelType>\n");
        stringBuilder.append("\t</AudioSettings>\n");

        // Ajout les éléments de niveau 2.
        stringBuilder.append(this.toXMLBinProject(2));

        // Clip
        for (Element element : this.elements) {
            if (element.getTypeElement() == TypeElement.SEQUENCE) {
                stringBuilder.append(((Sequence) element).toXMLClip());
            }
        }

        // Ajout des ClipLoggingInfo.
        for (Element element : this.elements) {
            if (element.getTypeElement() == TypeElement.TITLE) {
                stringBuilder.append(((Title) element).toXMLClipLoggingInfo());
            }
        }

        // AudioSequenceSource
        for (Element element : this.elements) {
            if (element.getTypeElement() == TypeElement.SEQUENCE) {
                stringBuilder.append(((Sequence) element).toXMLAudioSequenceSource());
            }
        }

        // VideoMediaSource
        for (Element element : this.elements) {
            if (element.getTypeElement() == TypeElement.TITLE) {
                stringBuilder.append(((Title) element).toXMLVideoMediaSource());
            }
        }

        // Sequence
        for (Element element : this.elements) {
            if (element.getTypeElement() == TypeElement.SEQUENCE) {
                stringBuilder.append(((Sequence) element).toXMLSequence());
            }
        }

        // Ajout des médias.
        for (Element element : this.elements) {
            if (element.getTypeElement() == TypeElement.TITLE) {
                stringBuilder.append(((Title) element).toXMLMedia());
            }
        }

        for (Element element : this.elements) {
            if (element.getTypeElement() == TypeElement.SEQUENCE) {
                stringBuilder.append(((Sequence) element).toXMLAudioTrackGroup());
            }
        }

        // VideoMediaSource
        /*for (Element element: this.elements) {
            if (element.getTypeElement() == TypeElement.TITLE) {
                ((Title) element).videoMediaSource(file);
            }
        }*/
        // clipLogginInfo.
        /*for (Element element: this.elements) {
            if (element.getTypeElement() == TypeElement.TITLE) {
                ((Title) element).clipLogginInfo(file);
            }
        }*/

 /*for (Element element: this.elements) {
            if (element.getTypeElement() == TypeElement.SEQUENCE) {
                ((Sequence) element).videoClip(file);
            }
        }*/
        stringBuilder.append("</PremiereData>\n");
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    /**
     * Version de Adobe Premiere.
     *
     * @return Liste des versions.
     */
    @NotNull
    @NotEmpty
    public Version[] getVersions() {
        return Version.values();
    }

    /**
     * Modifie la version d'un projet Adobe Premiere.
     *
     * @param version Version qu'on veut pour ce projet.
     *
     * @throws AdobePremiereProjectException
     */
    public void downgrade(String version) throws AdobePremiereProjectException {
        File fichierTmp = this.getFichierXMLTemporaire();

        try {
            ZipFiles.decompressGzipFile(this.fichier, fichierTmp);

            Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichierTmp);

            NodeList list = xml.getDocumentElement().getChildNodes();

            Node nodeItem;

            // Récupère et modifie la valeur actuelle.
            for (int i = 0; i < list.getLength(); i++) {
                nodeItem = list.item(i);
                //System.out.println("Node : " + list.item(i).getNodeName());

                if (nodeItem.getNodeType() == Node.ELEMENT_NODE && nodeItem.getNodeName().equals("Project")) {
                    org.w3c.dom.Element baliseProject = (org.w3c.dom.Element) nodeItem;

                    String attributeVersion = baliseProject.getAttribute("Version");

                    // Si pour l'attribut "Version" il y a une valeur, c'est la bonne balise !
                    if (!attributeVersion.isEmpty()) {
                        baliseProject.setAttribute("Version", version);

                        // On ne doit plus rien faire, donc on peut quitter la boucle.
                        break;
                    }
                }
            }

            // Sauve les changements.
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(xml);

            File xmlTemporaire = new File(fichierTmp.getAbsolutePath().replace(EXTENSION_TMP, ""));

            StreamResult result = new StreamResult(xmlTemporaire);
            transformer.transform(source, result);

            // For console Output.
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

            ZipFiles.compressGzipFile(xmlTemporaire, new File(this.fichier.getAbsolutePath().replace(EXTENSION, "_CC2017" + EXTENSION)));

            // On supprime les fichiers temporaires.
            fichierTmp.delete();
            xmlTemporaire.delete();
        } catch (IOException | ParserConfigurationException | SAXException | TransformerException | ZipCustomException exception) {
            throw new AdobePremiereProjectException(exception.getMessage(), exception);
        }
    }
}
