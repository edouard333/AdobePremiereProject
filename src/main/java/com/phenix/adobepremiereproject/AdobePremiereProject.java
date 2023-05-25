package com.phenix.adobepremiereproject;

import com.phenix.adobepremiereproject.column.BoolPropertyColumn;
import com.phenix.adobepremiereproject.column.Column;
import com.phenix.adobepremiereproject.column.CaptureSettingsColumn;
import com.phenix.adobepremiereproject.column.EditTextColumn;
import com.phenix.adobepremiereproject.column.LabelColumn;
import com.phenix.adobepremiereproject.column.NameColumn;
import com.phenix.adobepremiereproject.column.SelectedItemsColumn;
import com.phenix.adobepremiereproject.column.StringColumn;
import com.phenix.adobepremiereproject.column.TimecodeColumn;
import com.phenix.adobepremiereproject.exception.AdobePremiereProjectException;
import com.phenix.adobepremiereproject.setting.CompileSettings;
import com.phenix.adobepremiereproject.util.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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
public class AdobePremiereProject {

    public static final String EXTENSION = ".prproj";

    public static final String EXTENSION_XML = ".xml";

    public static final String EXTENSION_TMP = ".tmp";

    /**
     * Chemin et nom du fichier souhaité.
     */
    private final File fichier;

    /**
     * Les éléments du projet.
     */
    private final ArrayList<Element> elements;

    /**
     * Créé le fichier de XML qui se retrouvera dans le ZIP.
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

    private File getFichierXMLTemporaire() {
        return new File(this.fichier.getAbsolutePath().replace(EXTENSION, EXTENSION_TMP));
    }

    /**
     * Ecrit le fichier.
     *
     * @throws AdobePremiereProjectException
     */
    public void close() throws AdobePremiereProjectException {

        try {
            // Cloture le fichier temporaire.
            File fichier_tmp = this.getFichierXMLTemporaire();
            OutputStream os = new FileOutputStream(fichier_tmp);
            PrintWriter file = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));

            this.start(file);
            this.item(file);
            this.end(file);
            file.close();
            os.close();

            // Prend le fichier XML et le met dans le GZIP.
            Utils.compressGzipFile(fichier_tmp, fichier);

            // Supprime le fichier temporaire.
            fichier_tmp.delete();
        } catch (IOException exception) {
            throw new AdobePremiereProjectException(exception.getMessage());
        }
    }

    /**
     * Element à la racine du projet.
     *
     * @param file Flux où écrire les données XML.
     */
    private void item(PrintWriter file) {
        file.append("\t\t\t<Items Version=\"1\">\n");

        Element element;

        int index = 0;

        for (int i = 0; i < this.elements.size(); i++) {
            element = this.elements.get(i);

            if (element.getLevel() == 0) {
                file.append("\t\t\t\t<Item Index=\"" + index + "\" ObjectURef=\"" + element.getCurrentObjectURef() + "\"/>\n");//fea076d7-a8ae-4c4e-b592-93acb1e074fc
                index++;
            }
        }

        file.append("\t\t\t</Items>\n");
    }

    /**
     * Créé les dossiers.
     *
     * @param file Flux où il faut écrire.
     * @param level Niveau de dossier.
     */
    private void binProject(PrintWriter file, int level) {
        int order = 0;
        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).getLevel() == level) {
                this.elements.get(i).toXML(file, order);
                order++;
            }
        }
    }

    /**
     *
     * @param file
     * @param object_id
     * @param object_ref
     * @param id_project_view_state
     * @param id_original_project_view_state
     */
    private void ProjectViewState(PrintWriter file, int ObjectID, int ObjectRef, String ProjectViewStateID, String ProjectViewStateOriginalID, String LastViewed, String IconViewThumbnailSize) {
        file.append("\t\t\t\t\t<ProjectViewState ObjectID=\"" + ObjectID + "\" ClassID=\"18fb911d-4f21-4b7b-b196-b250dad79838\" Version=\"3\">\n");
        file.append("\t\t\t\t\t\t<Columns.List ObjectRef=\"" + ObjectRef + "\"/>\n");
        file.append("\t\t\t\t\t\t<ProjectViewState.ID>" + ProjectViewStateID + "</ProjectViewState.ID>\n");
        file.append("\t\t\t\t\t\t<ProjectViewState.OriginalID>" + ProjectViewStateOriginalID + "</ProjectViewState.OriginalID>\n");
        file.append("\t\t\t\t\t\t<ProjectViewState.BinID>-1</ProjectViewState.BinID>\n");
        file.append("\t\t\t\t\t\t<ProjectViewState.ViewHidden>false</ProjectViewState.ViewHidden>\n");
        file.append("\t\t\t\t\t\t<PreviewView.Visible>false</PreviewView.Visible>\n");
        file.append("\t\t\t\t\t\t<ContentView.LastViewed>" + LastViewed + "</ContentView.LastViewed>\n");
        file.append("\t\t\t\t\t\t<IconView.Thumbnail.Size>" + IconViewThumbnailSize + "</IconView.Thumbnail.Size>\n");
        file.append("\t\t\t\t\t\t<FreeformView.Scale>1</FreeformView.Scale>\n");
        file.append("\t\t\t\t\t\t<ListView.Thumbnail.Size>0</ListView.Thumbnail.Size>\n");
        file.append("\t\t\t\t\t\t<IconView.Thumbnail.State>true</IconView.Thumbnail.State>\n");
        file.append("\t\t\t\t\t\t<ListView.Thumbnail.State>false</ListView.Thumbnail.State>\n");
        file.append("\t\t\t\t\t\t<Thumbnail.ShowsEffects.State>true</Thumbnail.ShowsEffects.State>\n");
        file.append("\t\t\t\t\t\t<Sort.Enabled>true</Sort.Enabled>\n");
        file.append("\t\t\t\t\t\t<Sort.Type>0</Sort.Type>\n");
        file.append("\t\t\t\t\t\t<Sort.Direction>0</Sort.Direction>\n");
        file.append("\t\t\t\t\t\t<Sort.ColumnIndex>2</Sort.ColumnIndex>\n");
        file.append("\t\t\t\t\t\t<ColumnListContents.Version>15</ColumnListContents.Version>\n");
        file.append("\t\t\t\t\t\t<ListView.NameColumnWidth>0</ListView.NameColumnWidth>\n");
        file.append("\t\t\t\t\t\t<IconSort.Type>0</IconSort.Type>\n");
        file.append("\t\t\t\t\t\t<IconSort.Direction>0</IconSort.Direction>\n");
        file.append("\t\t\t\t\t\t<IconSort.ColumnIndex>0</IconSort.ColumnIndex>\n");
        file.append("\t\t\t\t\t\t<Project.CloudSyncEnabled>false</Project.CloudSyncEnabled>\n");
        file.append("\t\t\t\t\t\t<Project.IsEAProject>false</Project.IsEAProject>\n");
        file.append("\t\t\t\t\t</ProjectViewState>\n");
    }

    private void ColumnList(PrintWriter file, int ObjectID, int column_index_max, int delta) {
        file.append("\t\t\t\t\t<ColumnList ObjectID=\"" + ObjectID + "\" ClassID=\"" + ColumnList.ClassID + "\" Version=\"1\">\n");
        file.append("\t\t\t\t\t\t<Columns Version=\"1\">\n");

        for (int i = 0; i < column_index_max; i++) {
            file.append("\t\t\t\t\t\t\t<Column Index=\"" + i + "\" ObjectRef=\"" + (i + delta) + "\"/>\n");
        }

        file.append("\t\t\t\t\t\t</Columns>\n");
        file.append("\t\t\t\t\t</ColumnList>\n");
    }

    /**
     * Ecrit la structure du projet, le début.
     *
     * @param file Flux où il faut écrire.
     */
    private void start(PrintWriter file) throws AdobePremiereProjectException {

        String workspace_name = "Formation";

        file.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        file.append("<PremiereData Version=\"3\">\n");
        file.append("\t<Project ObjectRef=\"1\"/>\n");
        file.append("\t<Project ObjectID=\"1\" ClassID=\"" + Project.ClassID + "\" Version=\"" + Version.CC2022 + "\">\n");
        file.append("\t\t<Node Version=\"1\">\n");
        file.append("\t\t\t<Properties Version=\"1\">\n");
        file.append("\t\t\t\t<ProjectViewState.List ObjectID=\"2\" ClassID=\"aab0946f-7a21-4425-8908-fafa2119e30e\" Version=\"3\">\n");
        file.append("\t\t\t\t\t<ProjectViewStates Version=\"1\">\n");
        file.append("\t\t\t\t\t\t<ProjectViewState Version=\"1\" Index=\"0\">\n");
        file.append("\t\t\t\t\t\t\t<First>361ff028-e258-4162-a70b-ddea24afb013</First>\n");
        file.append("\t\t\t\t\t\t\t<Second ObjectRef=\"1\"/>\n");
        file.append("\t\t\t\t\t\t</ProjectViewState>\n");
        file.append("\t\t\t\t\t\t<ProjectViewState Version=\"1\" Index=\"1\">\n");
        file.append("\t\t\t\t\t\t\t<First>3625b009-0f43-4db8-8f24-6be33ebbaa5f</First>\n");
        file.append("\t\t\t\t\t\t\t<Second ObjectRef=\"2\"/>\n");
        file.append("\t\t\t\t\t\t</ProjectViewState>\n");
        file.append("\t\t\t\t\t</ProjectViewStates>\n");

        this.ProjectViewState(file, 1, 3, "361ff028-e258-4162-a70b-ddea24afb013", "00000000-0000-0000-0000-000000000000", "0", "1");
        this.ProjectViewState(file, 2, 4, "3625b009-0f43-4db8-8f24-6be33ebbaa5f", "361ff028-e258-4162-a70b-ddea24afb013", "1", "200");

        int column_index_max_exclu = 55;
        int delta = 5;

        this.ColumnList(file, 3, column_index_max_exclu, delta);
        this.ColumnList(file, 4, column_index_max_exclu, column_index_max_exclu + delta);

        ArrayList<Column> liste_column = new ArrayList<Column>();

        int object_id = 4;

        liste_column.add(new LabelColumn(
                ++object_id,
                "Libellé",
                "Column.PropertyText.Label",
                17,
                1,
                false,
                26
        ));

        liste_column.add(new SelectedItemsColumn(
                ++object_id,
                "Sélectionné(s)",
                "Column.PropertyText.SelectedItems",
                0,
                1,
                true,
                26
        ));

        liste_column.add(new NameColumn(
                ++object_id,
                "Nom",
                "Column.Intrinsic.Name",
                0,
                0,
                false,
                200
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Type de média",
                "Column.Intrinsic.MediaType",
                23,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Fréquence d'images",
                "Column.Intrinsic.MediaTimebase",
                22,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Début du média",
                "Column.Intrinsic.MediaStart",
                21,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Fin du média",
                "Column.Intrinsic.MediaEnd",
                20,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Durée du média",
                "Column.Intrinsic.MediaDuration",
                19,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Point d'entrée vidéo",
                "Column.Intrinsic.VideoInPoint",
                35,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Point de sortie vidéo",
                "Column.Intrinsic.VideoOutPoint",
                36,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Durée vidéo",
                "Column.Intrinsic.VideoDuration",
                33,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Point d'entrée audio",
                "Column.Intrinsic.AudioInPoint",
                3,
                0,
                true,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Point de sortie audio",
                "Column.Intrinsic.AudioOutPoint",
                4,
                0,
                true,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Durée audio",
                "Column.Intrinsic.AudioDuration",
                1,
                0,
                true,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Début du sous-élément",
                "Column.Intrinsic.SubclipStart",
                39,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Fin du sous-élément",
                "Column.Intrinsic.SubclipEnd",
                40,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Durée du sous-élément",
                "Column.Intrinsic.SubclipDuration",
                41,
                0,
                false,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Infos vidéo",
                "Column.Intrinsic.VideoInfo",
                34,
                0,
                false,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Infos audio",
                "Column.Intrinsic.AudioInfo",
                2,
                0,
                false,
                150
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Utilisation vidéo",
                "Column.Intrinsic.VideoUsage",
                38,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Utilisation audio",
                "Column.Intrinsic.AudioUsage",
                6,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Nom de la bande",
                "Column.Intrinsic.TapeName",
                30,
                0,
                false,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Description",
                "Column.PropertyText.Description",
                15,
                0,
                false,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Commentaire",
                "Column.PropertyText.Comment",
                10,
                1,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Remarque",
                "Column.Intrinsic.LogNote",
                18,
                0,
                false,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Chemin d'accès du média",
                "Column.Intrinsic.FilePath",
                16,
                0,
                true,
                100
        ));

        liste_column.add(new CaptureSettingsColumn(
                ++object_id,
                "Réglages d'acquisition",
                "Column.PropertyText.CaptureSettings",
                0,
                2,
                false,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Etat",
                "Column.PropertyText.Status",
                29,
                0,
                false,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Propriétés off-line",
                "Column.PropertyText.OfflineProperties",
                25,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Nom du fichier média",
                "Column.Intrinsic.FileName",
                58,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Scène",
                "Column.PropertyText.Scene",
                27,
                0,
                false,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Plan",
                "Column.PropertyText.Shot",
                28,
                0,
                false,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Client",
                "Column.PropertyText.Client",
                9,
                1,
                true,
                100
        ));

        liste_column.add(new BoolPropertyColumn(
                ++object_id,
                "Bon(ne)",
                "Column.PropertyBool.Good",
                0,
                2,
                false,
                100,
                "Column.PropertyBool.Good",
                "true"
        ));

        liste_column.add(new BoolPropertyColumn(
                ++object_id,
                "Masquer",
                "Column.PropertyBool.Hide",
                0,
                2,
                false,
                100,
                "Column.PropertyBool.Hide",
                "true"
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Code temporel sonore",
                "Column.Intrinsic.SoundTimeCode",
                42,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Déroul. sonore",
                "Column.PropertyText.SoundRoll",
                43,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Pellicule",
                "Column.PropertyText.FilmCameraRoll",
                47,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Pellicule quotidienne",
                "Column.PropertyText.FilmDailyRoll",
                48,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Pellicule labo",
                "Column.PropertyText.FilmLabRoll",
                49,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Code d’identification",
                "Column.PropertyText.FilmKeycode",
                50,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Décalage de synchronisation",
                "Column.PropertyText.SyncOffset",
                44,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Codec vidéo",
                "Column.PropertyText.Codec",
                45,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Ordre des trames",
                "Column.PropertyText.FieldOrder",
                46,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Doublure",
                "Column.PropertyText.Proxy",
                51,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Projet verrouillé",
                "Column.PropertyText.BinLocked",
                52,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "ASC_SOP",
                "Column.PropertyText.ASCSOP",
                53,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "ASC_SAT",
                "Column.PropertyText.ASCSAT",
                54,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "LUT",
                "Column.PropertyText.Lut",
                55,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "LUT1",
                "Column.PropertyText.Lut1",
                56,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "LUT2",
                "Column.PropertyText.Lut2",
                57,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Nom du fichier vidéo d’origine",
                "Column.PropertyText.OriginalVideoFileName",
                59,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Nom du fichier audio d’origine",
                "Column.PropertyText.OriginalAudioFileName",
                60,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Chemin d’accès au fichier de média de doublure",
                "Column.Intrinsic.ProxyFilePath",
                37,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Nom du fichier de média de doublure",
                "Column.Intrinsic.ProxyFileName",
                61,
                0,
                true,
                100
        ));

        liste_column.add(new LabelColumn(
                ++object_id,
                "Libellé",
                "Column.PropertyText.Label",
                17,
                1,
                false,
                26
        ));

        liste_column.add(new SelectedItemsColumn(
                ++object_id,
                "Sélectionné(s)",
                "Column.PropertyText.SelectedItems",
                0,
                1,
                true,
                26
        ));

        liste_column.add(new NameColumn(
                ++object_id,
                "Nom",
                "Column.Intrinsic.Name",
                0,
                0,
                false,
                333
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Type de média",
                "Column.Intrinsic.MediaType",
                23,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Fréquence d'images",
                "Column.Intrinsic.MediaTimebase",
                22,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Début du média",
                "Column.Intrinsic.MediaStart",
                21,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Fin du média",
                "Column.Intrinsic.MediaEnd",
                20,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Durée du média",
                "Column.Intrinsic.MediaDuration",
                19,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Point d'entrée vidéo",
                "Column.Intrinsic.VideoInPoint",
                35,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Point de sortie vidéo",
                "Column.Intrinsic.VideoOutPoint",
                36,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Durée vidéo",
                "Column.Intrinsic.VideoDuration",
                33,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Point d'entrée audio",
                "Column.Intrinsic.AudioInPoint",
                3,
                0,
                true,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Point de sortie audio",
                "Column.Intrinsic.AudioOutPoint",
                4,
                0,
                true,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Durée audio",
                "Column.Intrinsic.AudioDuration",
                1,
                0,
                true,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Début du sous-élément",
                "Column.Intrinsic.SubclipStart",
                39,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Fin du sous-élément",
                "Column.Intrinsic.SubclipEnd",
                40,
                0,
                false,
                100
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Durée du sous-élément",
                "Column.Intrinsic.SubclipDuration",
                41,
                0,
                false,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Infos vidéo",
                "Column.Intrinsic.VideoInfo",
                34,
                0,
                false,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Infos audio",
                "Column.Intrinsic.AudioInfo",
                2,
                0,
                false,
                150
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Utilisation vidéo",
                "Column.Intrinsic.VideoUsage",
                38,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Utilisation audio",
                "Column.Intrinsic.AudioUsage",
                6,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Nom de la bande",
                "Column.Intrinsic.TapeName",
                30,
                0,
                false,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Description",
                "Column.PropertyText.Description",
                15,
                0,
                false,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Commentaire",
                "Column.PropertyText.Comment",
                10,
                1,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Remarque",
                "Column.Intrinsic.LogNote",
                18,
                0,
                false,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Chemin d'accès du média",
                "Column.Intrinsic.FilePath",
                16,
                0,
                true,
                100
        ));

        liste_column.add(new CaptureSettingsColumn(
                ++object_id,
                "Réglages d'acquisition",
                "Column.PropertyText.CaptureSettings",
                0,
                2,
                false,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Etat",
                "Column.PropertyText.Status",
                29,
                0,
                false,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Propriétés off-line",
                "Column.PropertyText.OfflineProperties",
                25,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Nom du fichier média",
                "Column.Intrinsic.FileName",
                58,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Scène",
                "Column.PropertyText.Scene",
                27,
                0,
                false,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Plan",
                "Column.PropertyText.Shot",
                28,
                0,
                false,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Client",
                "Column.PropertyText.Client",
                9,
                1,
                true,
                100
        ));

        liste_column.add(new BoolPropertyColumn(
                ++object_id,
                "Bon(ne)",
                "Column.PropertyBool.Good",
                0,
                2,
                false,
                100,
                "Column.PropertyBool.Good",
                "true"
        ));

        liste_column.add(new BoolPropertyColumn(
                ++object_id,
                "Masquer",
                "Column.PropertyBool.Hide",
                0,
                2,
                false,
                100,
                "Column.PropertyBool.Hide",
                "true"
        ));

        liste_column.add(new TimecodeColumn(
                ++object_id,
                "Code temporel sonore",
                "Column.Intrinsic.SoundTimeCode",
                42,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Déroul. sonore",
                "Column.PropertyText.SoundRoll",
                43,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Pellicule",
                "Column.PropertyText.FilmCameraRoll",
                47,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Pellicule quotidienne",
                "Column.PropertyText.FilmDailyRoll",
                48,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Pellicule labo",
                "Column.PropertyText.FilmLabRoll",
                49,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Code d’identification",
                "Column.PropertyText.FilmKeycode",
                50,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Décalage de synchronisation",
                "Column.PropertyText.SyncOffset",
                44,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Codec vidéo",
                "Column.PropertyText.Codec",
                45,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Ordre des trames",
                "Column.PropertyText.FieldOrder",
                46,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Doublure",
                "Column.PropertyText.Proxy",
                51,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Projet verrouillé",
                "Column.PropertyText.BinLocked",
                52,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "ASC_SOP",
                "Column.PropertyText.ASCSOP",
                53,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "ASC_SAT",
                "Column.PropertyText.ASCSAT",
                54,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "LUT",
                "Column.PropertyText.Lut",
                55,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "LUT1",
                "Column.PropertyText.Lut1",
                56,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "LUT2",
                "Column.PropertyText.Lut2",
                57,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Nom du fichier vidéo d’origine",
                "Column.PropertyText.OriginalVideoFileName",
                59,
                0,
                true,
                100
        ));

        liste_column.add(new EditTextColumn(
                ++object_id,
                "Nom du fichier audio d’origine",
                "Column.PropertyText.OriginalAudioFileName",
                60,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Chemin d’accès au fichier de média de doublure",
                "Column.Intrinsic.ProxyFilePath",
                37,
                0,
                true,
                100
        ));

        liste_column.add(new StringColumn(
                ++object_id,
                "Nom du fichier de média de doublure",
                "Column.Intrinsic.ProxyFileName",
                61,
                0,
                true,
                100
        ));

        for (int i = 0; i < liste_column.size(); i++) {

            Column column = liste_column.get(i);
            String nom_classe_column = column.getClass().getName();

            if (nom_classe_column.equals(CaptureSettingsColumn.class.getName())) {
                ((CaptureSettingsColumn) column).toXML(file);
            } else if (nom_classe_column.equals(EditTextColumn.class.getName())) {
                ((EditTextColumn) column).toXML(file);
            } else if (nom_classe_column.equals(LabelColumn.class.getName())) {
                ((LabelColumn) column).toXML(file);
            } else if (nom_classe_column.equals(SelectedItemsColumn.class.getName())) {
                ((SelectedItemsColumn) column).toXML(file);
            } else if (nom_classe_column.equals(NameColumn.class.getName())) {
                ((NameColumn) column).toXML(file);
            } else if (nom_classe_column.equals(StringColumn.class.getName())) {
                ((StringColumn) column).toXML(file);
            } else if (nom_classe_column.equals(TimecodeColumn.class.getName())) {
                ((TimecodeColumn) column).toXML(file);
            } else if (nom_classe_column.equals(BoolPropertyColumn.class.getName())) {
                ((BoolPropertyColumn) column).toXML(file);
            } else {
                throw new AdobePremiereProjectException("Pas de : '" + nom_classe_column + "'");
            }

        }

        file.append("\t\t\t\t</ProjectViewState.List>\n");
        file.append("\t\t\t\t<BE.Prefs.AcceleratedRenderer.LastUsedDisplayName>Accélération GPU Mercury Playback Engine (Metal)</BE.Prefs.AcceleratedRenderer.LastUsedDisplayName>\n");
        file.append("\t\t\t\t<BE.Prefs.AcceleratedRenderer.LastUsedIdentifier>6ed1497e-17ad-4a5b-846f-52bb81e20104</BE.Prefs.AcceleratedRenderer.LastUsedIdentifier>\n");
        file.append("\t\t\t\t<BE.Prefs.kPrefsAcceleratedRenderer.OverridenIdentifier>6ed1497e-17ad-4a5b-846f-52bb81e20104</BE.Prefs.kPrefsAcceleratedRenderer.OverridenIdentifier>\n");

        if (Title.getTitleNumber() > 1) {
            file.append("\t\t\t\t<FE.Prefs.Titler.TitleCounter>" + Title.getTitleNumber() + "</FE.Prefs.Titler.TitleCounter>\n");
        }

        file.append("\t\t\t\t<MZ.BuildVersion.Created>23.2.0x69 - Wed Mar  1 16:01:33 2023</MZ.BuildVersion.Created>\n");
        file.append("\t\t\t\t<MZ.BuildVersion.Modified>23.2.0x69 - Wed May 24 11:07:03 2023</MZ.BuildVersion.Modified>\n");

        // S'il y a une séquence dans le projet.
        if (Sequence.getSequenceNumber() > 1) {
            file.append("\t\t\t\t<MZ.NextSequenceIndex>" + (Sequence.getSequenceNumber()) + "</MZ.NextSequenceIndex>\n");
            file.append("\t\t\t\t<MZ.PrefixKey.OpenSequenceGuidList.1>9d8a2607-057b-47be-8e25-56261a940524</MZ.PrefixKey.OpenSequenceGuidList.1>\n");
        }

        file.append("\t\t\t\t<MZ.Prefs.UseProjectItemOrMasterClipProperiesForTrackItems>false</MZ.Prefs.UseProjectItemOrMasterClipProperiesForTrackItems>\n");
        file.append("\t\t\t\t<MZ.Project.ApplicationID>Pro</MZ.Project.ApplicationID>\n");
        file.append("\t\t\t\t<MZ.Project.GUID>91b0c78e-e019-47e0-94c4-d0581286c3ab</MZ.Project.GUID>\n");
        file.append("\t\t\t\t<MZ.Project.WorkspaceName>" + workspace_name + "</MZ.Project.WorkspaceName>\n");
        file.append("\t\t\t\t<ProjectViewState.Version>2</ProjectViewState.Version>\n");
        file.append("\t\t\t\t<TL.PJSnappingState>1</TL.PJSnappingState>\n");
        file.append("\t\t\t\t<project.settings.lastknowngoodprojectpath>/Users/macdevpro/Desktop/Projet-example.prproj</project.settings.lastknowngoodprojectpath>\n");
        file.append("\t\t\t</Properties>\n");
        file.append("\t\t</Node>\n");
        file.append("\t\t<RootProjectItem ObjectURef=\"ae3aed9b-a494-4f2d-937c-2f513794f0f6\"/>\n");
        file.append("\t\t<ProjectSettings ObjectRef=\"3\"/>\n");
        file.append("\t\t<MovieCompileSettings ObjectRef=\"4\"/>\n");
        file.append("\t\t<StillCompileSettings ObjectRef=\"5\"/>\n");
        file.append("\t\t<AudioCompileSettings ObjectRef=\"6\"/>\n");
        file.append("\t\t<CustomCompileSettings ObjectRef=\"7\"/>\n");
        file.append("\t\t<VideoPreviewCompileSettings ObjectRef=\"8\"/>\n");
        file.append("\t\t<ScratchDiskSettings ObjectRef=\"9\"/>\n");
        file.append("\t\t<IngestSettings ObjectRef=\"10\"/>\n");
        file.append("\t\t<ProjectWorkspace ObjectRef=\"11\"/>\n");
        file.append("\t\t<NextSequenceID>" + Sequence.getSequenceNumber() + "</NextSequenceID>\n");
        file.append("\t\t<NextID>1000001</NextID>\n");
        file.append("\t</Project>\n");
        file.append("\t<RootProjectItem ObjectUID=\"ae3aed9b-a494-4f2d-937c-2f513794f0f6\" ClassID=\"1c307a89-9318-47d7-a583-bf2553736543\" Version=\"1\">\n");
        file.append("\t\t<ProjectItem Version=\"1\">\n");
        file.append("\t\t\t<Node Version=\"1\">\n");
        file.append("\t\t\t\t<Properties Version=\"1\">\n");
        file.append("\t\t\t\t\t<list.view.expanded.state.379921dc_45_03cc_45_4e04_45_8bb9_45_12bf337af0c9>true</list.view.expanded.state.379921dc_45_03cc_45_4e04_45_8bb9_45_12bf337af0c9>\n");
        file.append("\t\t\t\t</Properties>\n");
        file.append("\t\t\t\t<ID>1000000</ID>\n");
        file.append("\t\t\t</Node>\n");
        file.append("\t\t\t<Name>Root Bin</Name>\n");
        file.append("\t\t</ProjectItem>\n");
        file.append("\t\t<ProjectItemContainer Version=\"1\">\n");
    }

    /**
     * Ecrit la structure du projet, la fin.
     *
     * @param file
     */
    private void end(PrintWriter file) {
        file.append("\t\t</ProjectItemContainer>\n");
        file.append("\t</RootProjectItem>\n");
        file.append("\t<ProjectSettings ObjectID=\"3\" ClassID=\"50c16708-a1a1-4d2f-98d5-4e283ae28353\" Version=\"18\">\n");
        file.append("\t\t<VideoSettings ObjectRef=\"12\"/>\n");
        file.append("\t\t<AudioSettings ObjectRef=\"13\"/>\n");
        file.append("\t\t<VideoCompileSettings ObjectRef=\"14\"/>\n");
        file.append("\t\t<AudioCompileSettings ObjectRef=\"15\"/>\n");
        file.append("\t\t<CaptureSettings ObjectRef=\"16\"/>\n");
        file.append("\t\t<DefaultSequenceSettings ObjectRef=\"17\"/>\n");
        file.append("\t\t<EditingModeID>00000000-0000-0000-0000-000000000000</EditingModeID>\n");
        file.append("\t\t<PreviewFileFormatID>00000000-0000-0000-0000-000000000000</PreviewFileFormatID>\n");
        file.append("\t\t<VideoTimeDisplay>102</VideoTimeDisplay>\n");
        file.append("\t\t<VideoTimeDisplayInitial>102</VideoTimeDisplayInitial>\n");
        file.append("\t\t<AudioTimeDisplay>200</AudioTimeDisplay>\n");
        file.append("\t\t<ActionSafeWidth>10</ActionSafeWidth>\n");
        file.append("\t\t<ActionSafeHeight>10</ActionSafeHeight>\n");
        file.append("\t\t<TitleSafeWidth>20</TitleSafeWidth>\n");
        file.append("\t\t<TitleSafeHeight>20</TitleSafeHeight>\n");
        file.append("\t\t<ShouldScaleMedia>false</ShouldScaleMedia>\n");
        file.append("\t\t<UsePreviewCache>false</UsePreviewCache>\n");
        file.append("\t\t<ColorManagementSettings>{\"graphicsWhiteLuminance\":203,\"lutInterpolationMethod\":1}</ColorManagementSettings>\n");
        file.append("\t</ProjectSettings>\n");

        ArrayList<CompileSettings> liste_compile_settings = new ArrayList<CompileSettings>();

        liste_compile_settings.add(new CompileSettings(4, 18, 19));
        liste_compile_settings.add(new CompileSettings(5, 20, 21));
        liste_compile_settings.add(new CompileSettings(6, 22, 23));
        liste_compile_settings.add(new CompileSettings(7, 24, 25));
        liste_compile_settings.add(new CompileSettings(8, 26, 27));

        for (int i = 0; i < liste_compile_settings.size(); i++) {
            liste_compile_settings.get(i).toXML(file);
        }

        String same_as_project = "SameAsProject";

        file.append("\t<ScratchDiskSettings ObjectID=\"9\" ClassID=\"4c6ed82b-a81c-4df1-8bd0-750504c4b560\" Version=\"4\">\n");
        file.append("\t\t<CapturedVideoLocation0>" + same_as_project + "</CapturedVideoLocation0>\n");
        file.append("\t\t<CapturedAudioLocation0>" + same_as_project + "</CapturedAudioLocation0>\n");
        file.append("\t\t<VideoPreviewLocation0>" + same_as_project + "</VideoPreviewLocation0>\n");
        file.append("\t\t<AudioPreviewLocation0>" + same_as_project + "</AudioPreviewLocation0>\n");
        file.append("\t\t<AutoSaveLocation0>" + same_as_project + "</AutoSaveLocation0>\n");
        file.append("\t\t<CCLibrariesLocation0>" + same_as_project + "</CCLibrariesLocation0>\n");
        file.append("<CapsuleMediaLocation0>" + same_as_project + "</CapsuleMediaLocation0>\n");
        file.append("\t\t<DVDEncodingLocation0>" + same_as_project + "</DVDEncodingLocation0>\n");
        file.append("\t\t<TransferMediaLocation0>" + same_as_project + "</TransferMediaLocation0>\n");
        file.append("\t</ScratchDiskSettings>\n");

        file.append("\t<IngestSettings ObjectID=\"10\" ClassID=\"2db8f76b-2c37-48ee-925d-9a4f7278152d\" Version=\"1\">\n");
        file.append("\t\t<Enabled>false</Enabled>\n");
        file.append("\t\t<Action>copy</Action>\n");
        file.append("\t\t<PresetPath>/Applications/Adobe Premiere Pro 2023/Adobe Premiere Pro 2023.app/Contents/Settings/IngestPresets/Copy/Copy With MD5 Verification.epr</PresetPath>\n");
        file.append("\t\t<CopyDestination>SameAsProject</CopyDestination>\n");
        file.append("\t\t<ProxyDestination>SameAsProject</ProxyDestination>\n");
        file.append("\t\t<MachineID>6e386481-14b7-43bb-890d-ae81def9e5ff</MachineID>\n");
        file.append("\t</IngestSettings>\n");

        file.append("\t<WorkspaceSettings ObjectID=\"11\" ClassID=\"c4372273-e1aa-4683-98aa-a2ceadf3066c\" Version=\"1\">\n");
        file.append("\t</WorkspaceSettings>\n");

        // Ajoute les dossiers de niveau 0 = ceux à la racine du projet.
        binProject(file, 0);

        file.append("\t<VideoSettings ObjectID=\"12\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        file.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        file.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        file.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        file.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        file.append("\t</VideoSettings>\n");

        file.append("\t<AudioSettings ObjectID=\"13\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        file.append("\t\t<FrameRate>5292000</FrameRate>\n");
        file.append("\t\t<ChannelType>1</ChannelType>\n");
        file.append("\t</AudioSettings>\n");

        file.append("\t<VideoCompileSettings ObjectID=\"14\" ClassID=\"db372db5-7de2-4d3c-98ae-f42659d77b22\" Version=\"9\">\n");
        file.append("\t\t<VideoSettings ObjectRef=\"28\"/>\n");
        file.append("\t\t<VideoCompilerClassIDFourCC>1061109567</VideoCompilerClassIDFourCC>\n");
        file.append("\t\t<VideoFileTypeFourCC>1096173910</VideoFileTypeFourCC>\n");
        file.append("\t\t<Compressor>1685288560</Compressor>\n");
        file.append("\t\t<Depth>24</Depth>\n");
        file.append("\t\t<Aspect43>false</Aspect43>\n");
        file.append("\t\t<Quality>100</Quality>\n");
        file.append("\t\t<UseDataRate>false</UseDataRate>\n");
        file.append("\t\t<DataRate>3500</DataRate>\n");
        file.append("\t\t<ForceRecompress>true</ForceRecompress>\n");
        file.append("\t\t<ForceRecompressValue>2</ForceRecompressValue>\n");
        file.append("\t\t<Deinterlace>false</Deinterlace>\n");
        file.append("\t\t<IgnoreVideoFilters>false</IgnoreVideoFilters>\n");
        file.append("\t\t<OptimizeStills>false</OptimizeStills>\n");
        file.append("\t\t<FramesAtMarkers>false</FramesAtMarkers>\n");
        file.append("\t\t<RealTimePreview>true</RealTimePreview>\n");
        file.append("\t\t<VideoFieldType>0</VideoFieldType>\n");
        file.append("\t\t<DoKeyframeEveryNFrames>false</DoKeyframeEveryNFrames>\n");
        file.append("\t\t<DoKeyframeEveryNFramesValue>0</DoKeyframeEveryNFramesValue>\n");
        file.append("\t\t<AddKeyframesAtMarkers>false</AddKeyframesAtMarkers>\n");
        file.append("\t\t<AddKeyframesAtEdits>false</AddKeyframesAtEdits>\n");
        file.append("\t\t<RelativeFrameSize>1</RelativeFrameSize>\n");
        file.append("\t\t<RenderDepth>0</RenderDepth>\n");
        file.append("\t</VideoCompileSettings>\n");

        file.append("\t<AudioCompileSettings ObjectID=\"15\" ClassID=\"34b10007-ab6d-49a7-bac5-7b60d919e387\" Version=\"6\">\n");
        file.append("\t\t<AudioSettings ObjectRef=\"29\"/>\n");
        file.append("\t\t<Compressor>1380013856</Compressor>\n");
        file.append("\t\t<Interleave>1</Interleave>\n");
        file.append("\t\t<SampleType>3</SampleType>\n");
        file.append("\t</AudioCompileSettings>\n");

        file.append("\t<CaptureSettings ObjectID=\"16\" ClassID=\"328c2aa2-47f9-4211-805b-b6a6dbd4ca29\" Version=\"10\">\n");
        file.append("\t\t<RecordModuleDisplayName>HDV</RecordModuleDisplayName>\n");
        file.append("\t\t<SupportedFileExtension>avi</SupportedFileExtension>\n");
        file.append("\t\t<RecorderID>ae351743-b529-451e-a2d4-9ccf1ad8d8b6</RecorderID>\n");
        file.append("\t\t<VideoFrameRate>8475667200</VideoFrameRate>\n");
        file.append("\t\t<VideoFrameSize>0,0,720,480</VideoFrameSize>\n");
        file.append("\t\t<VideoCompressorFourCC>0</VideoCompressorFourCC>\n");
        file.append("\t\t<AudioCompressorFourCC>1380013856</AudioCompressorFourCC>\n");
        file.append("\t\t<AudioCompressorDisplayName>Non compressé</AudioCompressorDisplayName>\n");
        file.append("\t\t<AudioFrameRate>7938000</AudioFrameRate>\n");
        file.append("\t\t<AudioSampleType>3</AudioSampleType>\n");
        file.append("\t\t<AudioChannelType>1</AudioChannelType>\n");
        file.append("\t\t<AbortCaptureOnDroppedFrames>false</AbortCaptureOnDroppedFrames>\n");
        file.append("\t</CaptureSettings>\n");

        file.append("\t<DefaultSequenceSettings ObjectID=\"17\" ClassID=\"567bdf53-d6d9-4d61-b2f1-f4834bebea9b\" Version=\"2\">\n");
        file.append("\t\t<TotalVideoTracks>1</TotalVideoTracks>\n");
        file.append("\t\t<DefaultAudioStandardMonoTracks>0</DefaultAudioStandardMonoTracks>\n");
        file.append("\t\t<DefaultAudioStandardStereoTracks>1</DefaultAudioStandardStereoTracks>\n");
        file.append("\t\t<DefaultAudioStandard51Tracks>0</DefaultAudioStandard51Tracks>\n");
        file.append("\t\t<DefaultAudioSubmixMonoTracks>0</DefaultAudioSubmixMonoTracks>\n");
        file.append("\t\t<DefaultAudioSubmixStereoTracks>0</DefaultAudioSubmixStereoTracks>\n");
        file.append("\t\t<DefaultAudioSubmix51Tracks>0</DefaultAudioSubmix51Tracks>\n");
        file.append("\t</DefaultSequenceSettings>\n");

        file.append("\t<VideoCompileSettings ObjectID=\"18\" ClassID=\"db372db5-7de2-4d3c-98ae-f42659d77b22\" Version=\"9\">\n");
        file.append("\t\t<VideoSettings ObjectRef=\"30\"/>\n");
        file.append("\t\t<VideoCompilerClassIDFourCC>1061109567</VideoCompilerClassIDFourCC>\n");
        file.append("\t\t<VideoFileTypeFourCC>1096173910</VideoFileTypeFourCC>\n");
        file.append("\t\t<Compressor>1685288560</Compressor>\n");
        file.append("\t\t<Depth>24</Depth>\n");
        file.append("\t\t<Aspect43>false</Aspect43>\n");
        file.append("\t\t<Quality>100</Quality>\n");
        file.append("\t\t<UseDataRate>false</UseDataRate>\n");
        file.append("\t\t<DataRate>3500</DataRate>\n");
        file.append("\t\t<ForceRecompress>true</ForceRecompress>\n");
        file.append("\t\t<ForceRecompressValue>2</ForceRecompressValue>\n");
        file.append("\t\t<Deinterlace>false</Deinterlace>\n");
        file.append("\t\t<IgnoreVideoFilters>false</IgnoreVideoFilters>\n");
        file.append("\t\t<OptimizeStills>false</OptimizeStills>\n");
        file.append("\t\t<FramesAtMarkers>false</FramesAtMarkers>\n");
        file.append("\t\t<RealTimePreview>true</RealTimePreview>\n");
        file.append("\t\t<VideoFieldType>0</VideoFieldType>\n");
        file.append("\t\t<DoKeyframeEveryNFrames>false</DoKeyframeEveryNFrames>\n");
        file.append("\t\t<DoKeyframeEveryNFramesValue>0</DoKeyframeEveryNFramesValue>\n");
        file.append("\t\t<AddKeyframesAtMarkers>false</AddKeyframesAtMarkers>\n");
        file.append("\t\t<AddKeyframesAtEdits>false</AddKeyframesAtEdits>\n");
        file.append("\t\t<RelativeFrameSize>1</RelativeFrameSize>\n");
        file.append("\t\t<RenderDepth>0</RenderDepth>\n");
        file.append("\t</VideoCompileSettings>\n");

        file.append("\t<AudioCompileSettings ObjectID=\"19\" ClassID=\"34b10007-ab6d-49a7-bac5-7b60d919e387\" Version=\"6\">\n");
        file.append("\t\t<AudioSettings ObjectRef=\"31\"/>\n");
        file.append("\t\t<Compressor>1380013856</Compressor>\n");
        file.append("\t\t<Interleave>1</Interleave>\n");
        file.append("\t\t<SampleType>3</SampleType>\n");
        file.append("\t</AudioCompileSettings>\n");

        file.append("\t<VideoCompileSettings ObjectID=\"20\" ClassID=\"db372db5-7de2-4d3c-98ae-f42659d77b22\" Version=\"9\">\n");
        file.append("\t\t<VideoSettings ObjectRef=\"32\"/>\n");
        file.append("\t\t<VideoCompilerClassIDFourCC>1061109567</VideoCompilerClassIDFourCC>\n");
        file.append("\t\t<VideoFileTypeFourCC>1096173910</VideoFileTypeFourCC>\n");
        file.append("\t\t<Compressor>1685288560</Compressor>\n");
        file.append("\t\t<Depth>24</Depth>\n");
        file.append("\t\t<Aspect43>false</Aspect43>\n");
        file.append("\t\t<Quality>100</Quality>\n");
        file.append("\t\t<UseDataRate>false</UseDataRate>\n");
        file.append("\t\t<DataRate>3500</DataRate>\n");
        file.append("\t\t<ForceRecompress>true</ForceRecompress>\n");
        file.append("\t\t<ForceRecompressValue>2</ForceRecompressValue>\n");
        file.append("\t\t<Deinterlace>false</Deinterlace>\n");
        file.append("\t\t<IgnoreVideoFilters>false</IgnoreVideoFilters>\n");
        file.append("\t\t<OptimizeStills>false</OptimizeStills>\n");
        file.append("\t\t<FramesAtMarkers>false</FramesAtMarkers>\n");
        file.append("\t\t<RealTimePreview>true</RealTimePreview>\n");
        file.append("\t\t<VideoFieldType>0</VideoFieldType>\n");
        file.append("\t\t<DoKeyframeEveryNFrames>false</DoKeyframeEveryNFrames>\n");
        file.append("\t\t<DoKeyframeEveryNFramesValue>0</DoKeyframeEveryNFramesValue>\n");
        file.append("\t\t<AddKeyframesAtMarkers>false</AddKeyframesAtMarkers>\n");
        file.append("\t\t<AddKeyframesAtEdits>false</AddKeyframesAtEdits>\n");
        file.append("\t\t<RelativeFrameSize>1</RelativeFrameSize>\n");
        file.append("\t\t<RenderDepth>0</RenderDepth>\n");
        file.append("\t</VideoCompileSettings>\n");

        file.append("\t<AudioCompileSettings ObjectID=\"21\" ClassID=\"34b10007-ab6d-49a7-bac5-7b60d919e387\" Version=\"6\">\n");
        file.append("\t\t<AudioSettings ObjectRef=\"33\"/>\n");
        file.append("\t\t<Compressor>1380013856</Compressor>\n");
        file.append("\t\t<Interleave>1</Interleave>\n");
        file.append("\t\t<SampleType>3</SampleType>\n");
        file.append("\t</AudioCompileSettings>\n");

        file.append("\t<VideoCompileSettings ObjectID=\"22\" ClassID=\"db372db5-7de2-4d3c-98ae-f42659d77b22\" Version=\"9\">\n");
        file.append("\t\t<VideoSettings ObjectRef=\"34\"/>\n");
        file.append("\t\t<VideoCompilerClassIDFourCC>1061109567</VideoCompilerClassIDFourCC>\n");
        file.append("\t\t<VideoFileTypeFourCC>1096173910</VideoFileTypeFourCC>\n");
        file.append("\t\t<Compressor>1685288560</Compressor>\n");
        file.append("\t\t<Depth>24</Depth>\n");
        file.append("\t\t<Aspect43>false</Aspect43>\n");
        file.append("\t\t<Quality>100</Quality>\n");
        file.append("\t\t<UseDataRate>false</UseDataRate>\n");
        file.append("\t\t<DataRate>3500</DataRate>\n");
        file.append("\t\t<ForceRecompress>true</ForceRecompress>\n");
        file.append("\t\t<ForceRecompressValue>2</ForceRecompressValue>\n");
        file.append("\t\t<Deinterlace>false</Deinterlace>\n");
        file.append("\t\t<IgnoreVideoFilters>false</IgnoreVideoFilters>\n");
        file.append("\t\t<OptimizeStills>false</OptimizeStills>\n");
        file.append("\t\t<FramesAtMarkers>false</FramesAtMarkers>\n");
        file.append("\t\t<RealTimePreview>true</RealTimePreview>\n");
        file.append("\t\t<VideoFieldType>0</VideoFieldType>\n");
        file.append("\t\t<DoKeyframeEveryNFrames>false</DoKeyframeEveryNFrames>\n");
        file.append("\t\t<DoKeyframeEveryNFramesValue>0</DoKeyframeEveryNFramesValue>\n");
        file.append("\t\t<AddKeyframesAtMarkers>false</AddKeyframesAtMarkers>\n");
        file.append("\t\t<AddKeyframesAtEdits>false</AddKeyframesAtEdits>\n");
        file.append("\t\t<RelativeFrameSize>1</RelativeFrameSize>\n");
        file.append("\t\t<RenderDepth>0</RenderDepth>\n");
        file.append("\t</VideoCompileSettings>\n");

        file.append("\t<AudioCompileSettings ObjectID=\"23\" ClassID=\"34b10007-ab6d-49a7-bac5-7b60d919e387\" Version=\"6\">\n");
        file.append("\t\t<AudioSettings ObjectRef=\"35\"/>\n");
        file.append("\t\t<Compressor>1380013856</Compressor>\n");
        file.append("\t\t<Interleave>1</Interleave>\n");
        file.append("\t\t<SampleType>3</SampleType>\n");
        file.append("\t</AudioCompileSettings>\n");

        file.append("\t<VideoCompileSettings ObjectID=\"24\" ClassID=\"db372db5-7de2-4d3c-98ae-f42659d77b22\" Version=\"9\">\n");
        file.append("\t\t<VideoSettings ObjectRef=\"36\"/>\n");
        file.append("\t\t<VideoCompilerClassIDFourCC>1061109567</VideoCompilerClassIDFourCC>\n");
        file.append("\t\t<VideoFileTypeFourCC>1096173910</VideoFileTypeFourCC>\n");
        file.append("\t\t<Compressor>1685288560</Compressor>\n");
        file.append("\t\t<Depth>24</Depth>\n");
        file.append("\t\t<Aspect43>false</Aspect43>\n");
        file.append("\t\t<Quality>100</Quality>\n");
        file.append("\t\t<UseDataRate>false</UseDataRate>\n");
        file.append("\t\t<DataRate>3500</DataRate>\n");
        file.append("\t\t<ForceRecompress>true</ForceRecompress>\n");
        file.append("\t\t<ForceRecompressValue>2</ForceRecompressValue>\n");
        file.append("\t\t<Deinterlace>false</Deinterlace>\n");
        file.append("\t\t<IgnoreVideoFilters>false</IgnoreVideoFilters>\n");
        file.append("\t\t<OptimizeStills>false</OptimizeStills>\n");
        file.append("\t\t<FramesAtMarkers>false</FramesAtMarkers>\n");
        file.append("\t\t<RealTimePreview>true</RealTimePreview>\n");
        file.append("\t\t<VideoFieldType>0</VideoFieldType>\n");
        file.append("\t\t<DoKeyframeEveryNFrames>false</DoKeyframeEveryNFrames>\n");
        file.append("\t\t<DoKeyframeEveryNFramesValue>0</DoKeyframeEveryNFramesValue>\n");
        file.append("\t\t<AddKeyframesAtMarkers>false</AddKeyframesAtMarkers>\n");
        file.append("\t\t<AddKeyframesAtEdits>false</AddKeyframesAtEdits>\n");
        file.append("\t\t<RelativeFrameSize>1</RelativeFrameSize>\n");
        file.append("\t\t<RenderDepth>0</RenderDepth>\n");
        file.append("\t</VideoCompileSettings>\n");

        file.append("\t<AudioCompileSettings ObjectID=\"25\" ClassID=\"34b10007-ab6d-49a7-bac5-7b60d919e387\" Version=\"6\">\n");
        file.append("\t\t<AudioSettings ObjectRef=\"37\"/>\n");
        file.append("\t\t<Compressor>1380013856</Compressor>\n");
        file.append("\t\t<Interleave>1</Interleave>\n");
        file.append("\t\t<SampleType>3</SampleType>\n");
        file.append("\t</AudioCompileSettings>\n");

        file.append("\t<VideoCompileSettings ObjectID=\"26\" ClassID=\"db372db5-7de2-4d3c-98ae-f42659d77b22\" Version=\"9\">\n");
        file.append("\t\t<VideoSettings ObjectRef=\"38\"/>\n");
        file.append("\t\t<VideoCompilerClassIDFourCC>1061109567</VideoCompilerClassIDFourCC>\n");
        file.append("\t\t<VideoFileTypeFourCC>1096173910</VideoFileTypeFourCC>\n");
        file.append("\t\t<Compressor>1685288560</Compressor>\n");
        file.append("\t\t<Depth>24</Depth>\n");
        file.append("\t\t<Aspect43>false</Aspect43>\n");
        file.append("\t\t<Quality>100</Quality>\n");
        file.append("\t\t<UseDataRate>false</UseDataRate>\n");
        file.append("\t\t<DataRate>3500</DataRate>\n");
        file.append("\t\t<ForceRecompress>true</ForceRecompress>\n");
        file.append("\t\t<ForceRecompressValue>2</ForceRecompressValue>\n");
        file.append("\t\t<Deinterlace>false</Deinterlace>\n");
        file.append("\t\t<IgnoreVideoFilters>false</IgnoreVideoFilters>\n");
        file.append("\t\t<OptimizeStills>false</OptimizeStills>\n");
        file.append("\t\t<FramesAtMarkers>false</FramesAtMarkers>\n");
        file.append("\t\t<RealTimePreview>true</RealTimePreview>\n");
        file.append("\t\t<VideoFieldType>0</VideoFieldType>\n");
        file.append("\t\t<DoKeyframeEveryNFrames>false</DoKeyframeEveryNFrames>\n");
        file.append("\t\t<DoKeyframeEveryNFramesValue>0</DoKeyframeEveryNFramesValue>\n");
        file.append("\t\t<AddKeyframesAtMarkers>false</AddKeyframesAtMarkers>\n");
        file.append("\t\t<AddKeyframesAtEdits>false</AddKeyframesAtEdits>\n");
        file.append("\t\t<RelativeFrameSize>1</RelativeFrameSize>\n");
        file.append("\t\t<RenderDepth>0</RenderDepth>\n");
        file.append("\t</VideoCompileSettings>\n");

        file.append("\t<AudioCompileSettings ObjectID=\"27\" ClassID=\"34b10007-ab6d-49a7-bac5-7b60d919e387\" Version=\"6\">\n");
        file.append("\t\t<AudioSettings ObjectRef=\"39\"/>\n");
        file.append("\t\t<Compressor>1380013856</Compressor>\n");
        file.append("\t\t<Interleave>1</Interleave>\n");
        file.append("\t\t<SampleType>3</SampleType>\n");
        file.append("\t</AudioCompileSettings>\n");

        // Ajout les dossier de niveau 1 (sous-dossier).
        this.binProject(file, 1);

        String classID = "fb11c33a-b0a9-4465-aa94-b6d5db2628cf";

        // Ajoute séquence/masterClip ici (de tous level ??????):
        for (int i = 0; i < this.elements.size(); i++) {

            // Si c'est une séquence.
            if (this.elements.get(i).getTypeElement() == Element.SEQUENCE) {
                file.append("\t<MasterClip ObjectUID=\"" + "ad5bd5cb-4336-473d-a7f2-74386fbfd563" + "\" ClassID=\"" + classID + "\" Version=\"9\">\n");
                file.append("\t\t<LoggingInfo ObjectRef=\"40\"/>\n");
                file.append("\t\t<AudioComponentChains Version=\"1\">\n");
                file.append("\t\t\t<AudioComponentChain Index=\"0\" ObjectRef=\"41\"/>\n");
                file.append("\t\t</AudioComponentChains>\n");
                file.append("\t\t<Clips Version=\"1\">\n");
                file.append("\t\t\t<Clip Index=\"0\" ObjectRef=\"42\"/>\n");
                file.append("\t\t\t<Clip Index=\"1\" ObjectRef=\"43\"/>\n");
                file.append("\t\t</Clips>\n");
                file.append("\t\t<AudioClipChannelGroups ObjectRef=\"44\"/>\n");
                file.append("\t\t<Name>" + this.elements.get(i).getName() + "</Name>\n");
                file.append("\t</MasterClip>\n");
            }
        }

        // Ajout titre/masterClip ici (de tous level ????):
        for (int i = 0; i < this.elements.size(); i++) {

            // Si c'est une séquence.
            if (this.elements.get(i).getTypeElement() == Element.TITLE) {
                file.append("\t<MasterClip ObjectUID=\"" + "8c85bb49-dcaf-4511-aed8-9f6cead61d2a" + "" + "\" ClassID=\"" + classID + "\" Version=\"9\">\n");
                file.append("\t\t<LoggingInfo ObjectRef=\"45\"/>\n");
                file.append("\t\t<Clips Version=\"1\">\n");
                file.append("\t\t\t<Clip Index=\"0\" ObjectRef=\"46\"/>\n");
                file.append("\t\t</Clips>\n");
                file.append("\t\t<AudioClipChannelGroups ObjectRef=\"47\"/>\n");
                file.append("\t\t<Name>" + this.elements.get(i).getName() + "</Name>\n");
                file.append("\t</MasterClip>\n");
            }
        }

        file.append("\t<VideoSettings ObjectID=\"28\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        file.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        file.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        file.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        file.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        file.append("\t</VideoSettings>\n");

        file.append("\t<AudioSettings ObjectID=\"29\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        file.append("\t\t<FrameRate>5292000</FrameRate>\n");
        file.append("\t\t<ChannelType>1</ChannelType>\n");
        file.append("\t</AudioSettings>\n");

        file.append("\t<VideoSettings ObjectID=\"30\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        file.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        file.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        file.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        file.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        file.append("\t</VideoSettings>\n");

        file.append("\t<AudioSettings ObjectID=\"31\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        file.append("\t\t<FrameRate>5292000</FrameRate>\n");
        file.append("\t\t<ChannelType>1</ChannelType>\n");
        file.append("\t</AudioSettings>\n");

        file.append("\t<VideoSettings ObjectID=\"32\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        file.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        file.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        file.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        file.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        file.append("\t</VideoSettings>\n");

        file.append("\t<AudioSettings ObjectID=\"33\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        file.append("\t\t<FrameRate>5292000</FrameRate>\n");
        file.append("\t\t<ChannelType>1</ChannelType>\n");
        file.append("\t</AudioSettings>\n");

        file.append("\t<VideoSettings ObjectID=\"34\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        file.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        file.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        file.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        file.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        file.append("\t</VideoSettings>\n");

        file.append("\t<AudioSettings ObjectID=\"35\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        file.append("\t\t<FrameRate>5292000</FrameRate>\n");
        file.append("\t\t<ChannelType>1</ChannelType>\n");
        file.append("\t</AudioSettings>\n");

        file.append("\t<VideoSettings ObjectID=\"36\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        file.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        file.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        file.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        file.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        file.append("\t</VideoSettings>\n");

        file.append("\t<AudioSettings ObjectID=\"37\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        file.append("\t\t<FrameRate>5292000</FrameRate>\n");
        file.append("\t\t<ChannelType>1</ChannelType>\n");
        file.append("\t</AudioSettings>\n");

        file.append("\t<VideoSettings ObjectID=\"38\" ClassID=\"58474264-30c4-43a2-bba5-dc0812df8a3a\" Version=\"9\">\n");
        file.append("\t\t<FrameRate>8475667200</FrameRate>\n");
        file.append("\t\t<FrameSize>0,0,720,480</FrameSize>\n");
        file.append("\t\t<PixelAspectRatio>10,11</PixelAspectRatio>\n");
        file.append("\t\t<MaximumBitDepth>false</MaximumBitDepth>\n");
        file.append("\t</VideoSettings>\n");

        file.append("\t<AudioSettings ObjectID=\"39\" ClassID=\"6baf5521-b132-4634-840e-13cec5bc86a4\" Version=\"7\">\n");
        file.append("\t\t<FrameRate>5292000</FrameRate>\n");
        file.append("\t\t<ChannelType>1</ChannelType>\n");
        file.append("\t</AudioSettings>\n");

        // Ajout les éléments de niveau 2.
        this.binProject(file, 2);

        // Clip
        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).getTypeElement() == Element.SEQUENCE) {
                ((Sequence) this.elements.get(i)).clip(file);
            }
        }

        // Ajout des ClipLoggingInfo.
        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).getTypeElement() == Element.TITLE) {
                ((Title) this.elements.get(i)).clipLoggingInfo(file);
            }
        }

        // AudioSequenceSource
        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).getTypeElement() == Element.SEQUENCE) {
                ((Sequence) this.elements.get(i)).audioSequenceSource(file);
            }
        }

        // VideoMediaSource
        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).getTypeElement() == Element.TITLE) {
                ((Title) this.elements.get(i)).videoMediaSource(file);
            }
        }

        // Sequence
        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).getTypeElement() == Element.SEQUENCE) {
                ((Sequence) this.elements.get(i)).sequence(file);
            }
        }

        // Ajout des médias.
        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).getTypeElement() == Element.TITLE) {
                ((Title) this.elements.get(i)).media(file);
            }
        }

        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).getTypeElement() == Element.SEQUENCE) {
                ((Sequence) this.elements.get(i)).audioTrackGroup(file);
            }
        }

        // VideoMediaSource
        /*for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).getTypeElement() == Element.TITLE) {
                ((Title) this.elements.get(i)).videoMediaSource(file);
            }
        }*/
        // clipLogginInfo.
        /*for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).getTypeElement() == Element.TITLE) {
                ((Title) this.elements.get(i)).clipLogginInfo(file);
            }
        }*/

 /*for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).getTypeElement() == Element.SEQUENCE) {
                ((Sequence) this.elements.get(i)).videoClip(file);
            }
        }*/
        file.append("</PremiereData>\n");
        file.append("\n");
    }

    /**
     * ...
     *
     * @return Liste des versions.
     */
    public String[] getVersions() {
        return new String[]{
            Version.CC2015.toString(),
            Version.CC2017.toString(),
            Version.CC2018.toString(),
            Version.CC2019.toString(),
            Version.CC2020.toString()
        };
    }

    /**
     * Modifie la version d'un projet Adobe Premiere.
     *
     * @param version Version qu'on veut pour ce projet.
     *
     * @throws FileNotFoundException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformerConfigurationException
     */
    public void downgrade(String version) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {

        File fichier_tmp = this.getFichierXMLTemporaire();

        Utils.decompressGzipFile(fichier, fichier_tmp);

        Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichier_tmp);

        NodeList list = xml.getDocumentElement().getChildNodes();

        // Récupère et modifie la valeur actuelle.
        for (int i = 0; i < list.getLength(); i++) {
            //System.out.println("Node : " + list.item(i).getNodeName());

            if (list.item(i).getNodeType() == Node.ELEMENT_NODE && list.item(i).getNodeName().equals("Project")) {

                org.w3c.dom.Element balise_project = (org.w3c.dom.Element) list.item(i);

                String attribute_version = balise_project.getAttribute("Version");

                // Si pour l'attribut "Version" il y a une valeur, c'est la bonne balise !
                if (!attribute_version.equals("")) {
                    balise_project.setAttribute("Version", version);

                    // On ne doit plus rien faire, donc on peut quitter la boucle.
                    break;
                }
            }
        }

        // Sauve les changements.
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(xml);

        File xml_temporaire = new File(fichier_tmp.getAbsolutePath().replace(EXTENSION_TMP, ""));

        StreamResult result = new StreamResult(xml_temporaire);
        transformer.transform(source, result);

        //For console Output.
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);

        Utils.compressGzipFile(xml_temporaire, new File(this.fichier.getAbsolutePath().replace(EXTENSION, "_CC2017" + EXTENSION)));

        // On supprime les fichiers temporaires.
        fichier_tmp.delete();
        xml_temporaire.delete();
    }
}
