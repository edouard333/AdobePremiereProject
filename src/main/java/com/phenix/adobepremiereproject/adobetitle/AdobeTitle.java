package com.phenix.adobepremiereproject.adobetitle;

import com.phenix.adobepremiereproject.exception.AdobePremiereProjectException;
import com.phenix.codec.Base64;
import jakarta.validation.constraints.NotNull;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.phenix.adobepremiereproject.internal.XMLSimpleConvertible;

/**
 * Traite les informations sur un titre Adobe.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class AdobeTitle implements XMLSimpleConvertible {

    /**
     *
     */
    private static int id = 0;

    /**
     *
     */
    private static String DOSSIER_TMP = "C:\\TMP";

    /**
     * L'XML.
     */
    private String dataDecode;

    /**
     *
     */
    public List<Text> texts;

    /**
     * Utiliser quand on va compresser (écrire dans le fichier).
     */
    public AdobeTitle() {
        this.texts = new ArrayList<Text>();
    }

    /**
     * Utiliser quand on veut décoder un XML et ce base64.
     *
     * @param data Le code en base 64.
     *
     * @throws AdobePremiereProjectException
     */
    public AdobeTitle(String data) throws AdobePremiereProjectException {
        this.texts = new ArrayList<Text>();

        try {
            byte[] decode = Base64.decode(data, Base64.GZIP);

            this.dataDecode = decompress(decode);

            File pathTmp = new File(DOSSIER_TMP + File.separator + "title" + id + ".tmp");

            try (PrintWriter writer = new PrintWriter(pathTmp)) {
                writer.append(this.dataDecode);
            }

            Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pathTmp);

            // Adobe_Root
            NodeList list = xml.getDocumentElement().getChildNodes();

            List<TextDescription> listeTextDescription = new ArrayList<TextDescription>();

            Node nodeItem;

            // Ce qui est dans Adobe_Root :
            for (int i = 0; i < list.getLength(); i++) {
                nodeItem = list.item(i);

                // Ce qui nous intéresse "InscriberLayouts" :
                if (nodeItem.getNodeName().equals("InscriberLayouts")) {
                    System.out.println(i + " : " + nodeItem.getNodeName());

                    NodeList inscriber = nodeItem.getChildNodes();

                    Node nodeInscriber;

                    for (int j = 0; j < inscriber.getLength(); j++) {
                        nodeInscriber = inscriber.item(j);

                        if (nodeInscriber.getNodeName().equals("Layout")) {
                            System.out.println(" * " + j + " : " + nodeInscriber.getNodeName());

                            NodeList layout = nodeInscriber.getChildNodes();

                            Node nodeLayout;

                            for (int k = 0; k < layout.getLength(); k++) {
                                nodeLayout = layout.item(k);

                                // Les fonts/cara lié au texte :
                                if (nodeLayout.getNodeName().equals("TextDescriptions")) {
                                    System.out.println(" * * " + k + " : " + layout.item(k).getNodeName());

                                    NodeList textDescriptions = layout.item(k).getChildNodes();

                                    for (int l = 0; l < textDescriptions.getLength(); l++) {
                                        if (textDescriptions.item(l).getNodeName().equals("TextDescription")) {
                                            listeTextDescription.add(new TextDescription(textDescriptions.item(l)));
                                        }
                                    }
                                } else if (nodeLayout.getNodeName().equals("Layers")) {
                                    System.out.println(" * * " + k + " : " + nodeLayout.getNodeName());

                                    NodeList layers = nodeLayout.getChildNodes();

                                    Node nodeLayers;

                                    for (int l = 0; l < layers.getLength(); l++) {
                                        nodeLayers = layers.item(l);

                                        if (nodeLayers.getNodeName().equals("Layer")) {
                                            System.out.println(" * * * " + l + " : " + nodeLayers.getNodeName());

                                            NodeList layer = nodeLayers.getChildNodes();

                                            Node nodeLayer;

                                            for (int m = 0; m < layer.getLength(); m++) {
                                                nodeLayer = layer.item(m);

                                                if (nodeLayer.getNodeName().equals("TextPage")) {
                                                    System.out.println(" * * * * " + m + " : " + nodeLayer.getNodeName());

                                                    NodeList textPage = nodeLayer.getChildNodes();

                                                    Node nodeTextPage;

                                                    for (int n = 0; n < textPage.getLength(); n++) {
                                                        nodeTextPage = textPage.item(n);

                                                        System.out.println(" * * * * * " + n + " : " + nodeTextPage.getNodeName());

                                                        Text tc = new Text(nodeTextPage, listeTextDescription);

                                                        texts.add(tc);

                                                        //System.out.println("Pos x : " + tc.getPositionX());
                                                        //System.out.println("Pos y : " + tc.getPositionY());
                                                        //System.out.println("Size x : " + tc.getSizeX());
                                                        //System.out.println("Size y : " + tc.getSizeY());
                                                        //System.out.println("Text : " + tc.getText());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            id++;
        } catch (DataFormatException | IOException | ParserConfigurationException | SAXException exception) {
            throw new AdobePremiereProjectException(exception.getMessage(), exception);
        }
    }

    /**
     * Les données décodées.
     *
     * @return
     */
    public String getDataDecode() {
        return this.dataDecode;
    }

    @Override
    public String toXML() {
        // Prend les données décodées et les recompresse.
        byte[] data = compress(this.dataDecode); // TODO : On doit faire dataDecode un XML à partir des données de ArrayList<Text>... :'(

        // Puis réapplique la Base 64.
        return Base64.encodeBytes(data, Base64.DONT_BREAK_LINES);
    }

    /**
     *
     */
    private byte[] header;

    /**
     * Prend le code décodé 64 pour le décompresser (GZIP) vers String.
     *
     * @param compressed
     * @return Le contenu (XML).
     *
     * @throws DataFormatException
     */
    private String decompress(@NotNull byte[] compressed) throws DataFormatException {
        byte[] slice = Arrays.copyOfRange(compressed, 0, 32);

        this.header = slice;

        // Decompress the bytes
        if (compressed.length <= 32) {
            return new String(compressed);
        }
        ByteArrayOutputStream xmlout = new ByteArrayOutputStream(10000);
        Inflater decompresser = new Inflater();
        decompresser.setInput(compressed, 32, compressed.length - 32);

        int i = 0;
        do {
            byte[] result = new byte[1000];
            int resultLength = decompresser.inflate(result);
            xmlout.write(result, 0, resultLength);
        } while (!decompresser.finished());

        decompresser.end();

        return new String(xmlout.toByteArray());
    }

    /**
     * Compresse les données décodée pour après passer dans le codage 64 pour
     * enregistrer le fichier.
     *
     * @param decompressed Les données décompressées.
     *
     * @return Données compressées.
     */
    private byte[] compress(String decompressed) {
        byte[] input = decompressed.getBytes();

        ByteArrayOutputStream xmlout = new ByteArrayOutputStream(10000);

        Deflater compresser = new Deflater();

        xmlout.write(this.header, 0, 32);
        compresser.setInput(input, 0, input.length);

        int i = 0;
        do {
            compresser.finish();

            byte[] output = new byte[1000];
            int compressedDataLength = compresser.deflate(output);

            xmlout.write(output, 0, compressedDataLength);
            i++;
        } while (!compresser.finished());
        compresser.end();

        return xmlout.toByteArray();
    }
}
