package com.phenix.adobepremiereproject;

import com.phenix.adobepremiereproject.AdobeTitle.AdobeTitle;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * TODO : a supprimer
 *
 * @author Edouard
 */
public class Base65 {

    public static void main(String[] args) throws IOException, DataFormatException, ParserConfigurationException, SAXException {

        /*ByteArrayOutputStream xmlout = new ByteArrayOutputStream(10000); // ADD

        String inputString = "blahblahblah5tr4rfh5yg4j55hygf56h4dfh65";
        byte[] input = inputString.getBytes("UTF-8");
        System.out.println("original: " + inputString); // AD
        // Compress the bytes
        byte[] output = new byte[100];
        Deflater compresser = new Deflater();
        compresser.setInput(input);
        compresser.finish();
        int compressedDataLength = compresser.deflate(output);
        xmlout.write(output, 0, compressedDataLength); // ADD
        compresser.end();

        System.out.println("compresse: " + Arrays.toString(xmlout.toByteArray()) + " (size: " + xmlout.toByteArray().length + ")"); // AD

        xmlout = new ByteArrayOutputStream(10000); // ADD

        // Decompress the bytes
        Inflater decompresser = new Inflater();
        decompresser.setInput(output, 0, compressedDataLength);
        byte[] result = new byte[100];
        int resultLength = decompresser.inflate(result);
        xmlout.write(result);// ADD
        decompresser.end();
        System.out.println("decompresse: " + new String(xmlout.toByteArray()).toString()); // AD
         */
        try {
            String data = "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";
            AdobeTitle title = new AdobeTitle(data);
            System.out.println("decode: " + title.getDataDecode());
            System.out.println();
            System.out.println(title.texts.get(0).getText());
            System.out.println(title.texts.get(0).getPositionX());
            System.out.println(title.texts.get(0).getPositionY());
            System.out.println(title.texts.get(0).getFont() + " " + title.texts.get(0).isBold() + " " + title.texts.get(0).isItalic());
            System.out.println("xml: " + title.toXML());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
