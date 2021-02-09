package com.phenix.adobepremiereproject.AdobeTitle;

import com.phenix.adobepremiereproject.AdobeTitle.Font.Font;
import org.w3c.dom.Node;

/**
 * Gère la balise "TextDescription".
 *
 * @author Edouard Jeanjean <edouard128@hotmail.com>
 */
class TextDescription {

    /**
     * Node XML avec les données.
     */
    private Node node;

    /**
     * Quand on va créer un style.
     */
    public TextDescription() {
        this.node = (Node) (null);
    }

    /**
     * Quand on récupère les données.
     *
     * @param node
     */
    public TextDescription(Node node) {
        this.node = node;
        /*<TextDescription Reference="4096">
                    <TypeSpec>
                        <size>360</size>
                        <txHeight>100</txHeight>
                        <txKern>0</txKern>
                        <baselineShift>0</baselineShift>
                        <leading>0</leading>
                        <txSCaps>75</txSCaps>
                        <txSCapsOn>false</txSCapsOn>
                        <txSlant>0</txSlant>
                        <txUnderline>false</txUnderline>
                        <txWidth>100</txWidth>
                        <linked>false</linked>
                        <fiBold>0</fiBold>
                        <fiItalic>0</fiItalic>
                        <fifullName>Chicago</fifullName>
                        <fifontFamilyName>Chicago</fifontFamilyName>
                        <fifontStyle>Regular</fifontStyle>
                        <fifontType>5</fifontType>
                        <ficategory>536870912</ficategory>
                    </TypeSpec>
                </TextDescription>*/
    }

    /**
     * Si on veut que le text soit en gras.
     */
    public void setBold(boolean bold) {
    }

    /**
     * Définit si c'est en italic.
     *
     * @param italic
     */
    public void setItalic(boolean italic) {
    }

    /**
     * Définit la font à utiliser.
     *
     * @param fullName
     * @param familyName
     */
    public void setFont(Font font) {
        //this.node
    }

    /**
     * Définit le style de la font, si disponible.
     *
     * @param fontStyle
     */
    public void setFontStyle(String fontStyle) {
    }
}
