/**
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
module com.phenix.adobepremiereproject {
    requires com.phenix.codec;
    requires com.phenix.compression;
    requires com.phenix.timecode;
    requires jakarta.validation;
    requires java.xml;

    exports com.phenix.adobepremiereproject;
    exports com.phenix.adobepremiereproject.adobetitle;
    exports com.phenix.adobepremiereproject.adobetitle.font;
    exports com.phenix.adobepremiereproject.column;
    exports com.phenix.adobepremiereproject.exception;
    exports com.phenix.adobepremiereproject.setting;
}
