package com.phenix.adobepremiereproject.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Utils {

    /**
     * Prend l'XML Adobe Premiere et en fait le fichier de projet Adobe
     * Premiere.
     *
     * @param file
     * @param gzipFile
     * @throws IOException
     */
    public static void compressGzipFile(File file, File gzipFile) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(gzipFile);
        GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            gzipOS.write(buffer, 0, len);
        }
        // close resources
        gzipOS.close();
        fos.close();
        fis.close();
    }

    /**
     * Décompresser un projet Adobe Premiere.
     *
     * @param gzipFile
     * @param newFile
     *
     * @throws IOException
     */
    public static void decompressGzipFile(File gzipFile, File newFile) throws IOException {
        FileInputStream fis = new FileInputStream(gzipFile);
        GZIPInputStream gis = new GZIPInputStream(fis);
        FileOutputStream fos = new FileOutputStream(newFile);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = gis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        // close resources
        fos.close();
        gis.close();
    }
}
