package com.phenix.adobepremiereproject.adobetitle;

import com.phenix.adobepremiereproject.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Traite les informations sur un titre Adobe.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class AdobeTitle {

    /**
     * 
     */
    private static int id = 0;

    /**
     * L'XML.
     */
    private String data_decode;

    /**
     * 
     */
    public ArrayList<Text> texts;

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
     * @throws DataFormatException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public AdobeTitle(String data) throws DataFormatException, ParserConfigurationException, SAXException, IOException {
        this.texts = new ArrayList<Text>();

        byte[] decode = Base64.decode(data, Base64.GZIP);

        this.data_decode = decompress(decode);

        String path_tmp = "C:\\TMP\\title" + id + ".tmp";

        PrintWriter file = new PrintWriter(path_tmp);
        file.append(this.data_decode);
        file.close();

        Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(path_tmp));

        // Adobe_Root
        NodeList list = xml.getDocumentElement().getChildNodes();

        ArrayList<TextDescription> liste_text_description = new ArrayList<TextDescription>();

        // Ce qui est dans Adobe_Root :
        for (int i = 0; i < list.getLength(); i++) {

            // Ce qui nous intéresse "InscriberLayouts" :
            if (list.item(i).getNodeName().equals("InscriberLayouts")) {
                System.out.println(i + " : " + list.item(i).getNodeName());

                NodeList inscriber = list.item(i).getChildNodes();

                for (int j = 0; j < inscriber.getLength(); j++) {

                    if (inscriber.item(j).getNodeName().equals("Layout")) {
                        System.out.println(" * " + j + " : " + inscriber.item(j).getNodeName());

                        NodeList layout = inscriber.item(j).getChildNodes();
                        for (int k = 0; k < layout.getLength(); k++) {

                            // Les fonts/cara lié au texte :
                            if (layout.item(k).getNodeName().equals("TextDescriptions")) {

                                System.out.println(" * * " + k + " : " + layout.item(k).getNodeName());

                                NodeList text_descriptions = layout.item(k).getChildNodes();

                                for (int l = 0; l < text_descriptions.getLength(); l++) {
                                    if (text_descriptions.item(l).getNodeName().equals("TextDescription")) {
                                        liste_text_description.add(new TextDescription(text_descriptions.item(l)));
                                    }
                                }
                            }

                            if (layout.item(k).getNodeName().equals("Layers")) {
                                System.out.println(" * * " + k + " : " + layout.item(k).getNodeName());

                                NodeList layers = layout.item(k).getChildNodes();
                                for (int l = 0; l < layers.getLength(); l++) {

                                    if (layers.item(l).getNodeName().equals("Layer")) {
                                        System.out.println(" * * * " + l + " : " + layers.item(l).getNodeName());

                                        NodeList layer = layers.item(l).getChildNodes();
                                        for (int m = 0; m < layer.getLength(); m++) {

                                            if (layer.item(m).getNodeName().equals("TextPage")) {
                                                System.out.println(" * * * * " + m + " : " + layer.item(m).getNodeName());

                                                NodeList textPage = layer.item(m).getChildNodes();
                                                for (int n = 0; n < textPage.getLength(); n++) {
                                                    System.out.println(" * * * * * " + n + " : " + textPage.item(n).getNodeName());

                                                    Text tc = new Text(textPage.item(n), liste_text_description);

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
    }

    /**
     * Les données décodées.
     *
     * @return
     */
    public String getDataDecode() {
        return this.data_decode;
    }

    /**
     * Retourne les données pour être ajouté à l'XML de sortie.
     *
     * @return
     * 
     * @throws DataFormatException
     * @throws UnsupportedEncodingException
     */
    public String toXML() throws DataFormatException, UnsupportedEncodingException {
        // Prend les données décodées et les recompresse.
        byte[] data = compress(this.data_decode); // TODO : on doit faire data_decode un XML à partir des données de ArrayList<Text>... :'(

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
    private String decompress(byte[] compressed) throws DataFormatException {

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
    private byte[] compress(String decompressed){
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
