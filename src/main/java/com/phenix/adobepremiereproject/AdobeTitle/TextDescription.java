package com.phenix.adobepremiereproject.AdobeTitle;

import com.phenix.adobepremiereproject.AdobeTitle.Font.Font;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

    private int reference;

    /**
     * Indique si le texte est en gras.
     */
    private boolean bold;

    /**
     * Indique si le texte est en italic.
     */
    private boolean italic;

    private String font;

    private String style_font;

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

        NodeList liste = this.node.getChildNodes();

        if (this.node.getNodeName().equals("#document")) {
            this.node = liste.item(0);
            liste = this.node.getChildNodes();
        }

        this.reference = Integer.parseInt(this.node.getAttributes().getNamedItem("Reference").getNodeValue());

        for (int i = 0; i < liste.getLength(); i++) {
            // On rentre dans le node :
            if (liste.item(i).getNodeName().equals("TypeSpec")) {
                NodeList type_spec = liste.item(i).getChildNodes();

                for (int j = 0; j < type_spec.getLength(); j++) {
                    if (type_spec.item(j).getNodeName().equals("size")) {
                        System.out.println("size : " + type_spec.item(j).getTextContent());
                    } else if (type_spec.item(j).getNodeName().equals("fiBold")) {
                        this.bold = Boolean.parseBoolean(type_spec.item(j).getTextContent());
                    } else if (type_spec.item(j).getNodeName().equals("fiItalic")) {
                        this.italic = Boolean.parseBoolean(type_spec.item(j).getTextContent());
                    } else if (type_spec.item(j).getNodeName().equals("fifontFamilyName")) {
                        this.font = type_spec.item(j).getTextContent();
                    } else if (type_spec.item(j).getNodeName().equals("fifontStyle")) {
                        this.style_font = type_spec.item(j).getTextContent();
                    }
                }
            }

        }

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

    public int getReference() {
        return this.reference;
    }

    public String getFont() {
        return this.font;
    }

    public boolean isItalic() {
        return this.italic;
    }

    public boolean isBold() {
        return this.bold;
    }
}
