package com.phenix.adobepremiereproject.AdobeTitle;

import utils.Base64;
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
 * @author Edouard Jeanjean <edouard128@hotmail.com>
 */
public class AdobeTitle {

    private static int id = 0;

    /**
     * L'XML.
     */
    private String data_decode;

    public ArrayList<Text> texts;

    /**
     * Utiliser quand on va compresser (écrire dans le fichier).
     */
    public AdobeTitle() {
        texts = new ArrayList<Text>();
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
        texts = new ArrayList<Text>();

        byte[] decode = Base64.decode(data, Base64.GZIP);

        this.data_decode = decompress(decode);

        String path_tmp = "C:\\TMP\\title" + id + ".tmp";

        PrintWriter file = new PrintWriter(path_tmp);
        file.append(this.data_decode);
        file.close();

        Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(path_tmp));

        NodeList list = xml.getDocumentElement().getChildNodes();

        for (int i = 0; i < list.getLength(); i++) {

            if (list.item(i).getNodeName().equals("InscriberLayouts")) {
                System.out.println(i + " : " + list.item(i).getNodeName());

                NodeList inscriber = list.item(i).getChildNodes();

                for (int j = 0; j < inscriber.getLength(); j++) {

                    if (inscriber.item(j).getNodeName().equals("Layout")) {
                        System.out.println(" * " + j + " : " + inscriber.item(j).getNodeName());

                        NodeList layout = inscriber.item(j).getChildNodes();
                        for (int k = 0; k < layout.getLength(); k++) {

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

                                                    Text tc = new Text(textPage.item(n), null);

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
     */
    public String toXML() throws DataFormatException, UnsupportedEncodingException {
        // Prend les données décodée et les recompresse.
        byte[] data = compress(this.data_decode);

        // Puis réapplique la Base 64.
        return Base64.encodeBytes(data, Base64.DONT_BREAK_LINES);
    }

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
        //System.out.println("byte: " + Arrays.toString(compressed));
        //System.out.println("size compress: " + compressed.length);

        byte[] slice = Arrays.copyOfRange(compressed, 0, 32);

        //System.out.println("str" + new String(slice) + " size:" + slice.length);
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
        //finalSize = decompresser.bytesWritten;

        decompresser.end();

        //System.out.println("size decompresse " + xmlout.size());
        return new String(xmlout.toByteArray());
    }

    /**
     * Compresse les données décodée pour après passer dans le codage 64 pour enregistrer le fichier.
     *
     * @param decompressed Les données décompressées.
     *
     * @return Données compressée.
     */
    private byte[] compress(String decompressed) throws DataFormatException, UnsupportedEncodingException {
        byte[] input = decompressed.getBytes();

        //System.out.println("size in decompress: " + input.length);
        ByteArrayOutputStream xmlout = new ByteArrayOutputStream(10000);

        Deflater compresser = new Deflater();

        xmlout.write(this.header, 0, 32); // ADD
        compresser.setInput(input, 0, input.length);

        int i = 0;
        do {
            //System.out.println("WHILE " + i);
            compresser.finish();

            byte[] output = new byte[1000];
            int compressedDataLength = compresser.deflate(output);
            //System.out.println("need:"+compresser.needsInput() + ", " + compressedDataLength);

            xmlout.write(output, 0, compressedDataLength);
            i++;
        } while (!compresser.finished());
        compresser.end();

        //System.out.println("compresse: " + Arrays.toString(xmlout.toByteArray()));
        //System.out.println("size compresse: " + xmlout.toByteArray().length);
        return xmlout.toByteArray();
    }

}
