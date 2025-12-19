package com.phenix.adobepremiereproject.adobetitle;

import com.phenix.adobepremiereproject.adobetitle.font.Font;
import jakarta.validation.constraints.NotNull;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Gère la balise "TextDescription".
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
class TextDescription {

    /**
     * Node XML avec les données.
     */
    private Node node;

    /**
     *
     */
    private int reference;

    /**
     * Indique si le texte est en gras.
     */
    private boolean bold;

    /**
     * Indique si le texte est en italic.
     */
    private boolean italic;

    /**
     *
     */
    private String font;

    /**
     *
     */
    private String styleFont;

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
    public TextDescription(@NotNull Node node) {
        this.node = node;

        NodeList liste = this.node.getChildNodes();

        if (this.node.getNodeName().equals("#document")) {
            this.node = liste.item(0);
            liste = this.node.getChildNodes();
        }

        this.reference = Integer.parseInt(this.node.getAttributes().getNamedItem("Reference").getNodeValue());

        for (int i = 0; i < liste.getLength(); i++) {
            Node nodeItem = liste.item(i);

            // On rentre dans le node :
            if (nodeItem.getNodeName().equals("TypeSpec")) {
                NodeList listeTypeSpec = nodeItem.getChildNodes();

                for (int j = 0; j < listeTypeSpec.getLength(); j++) {
                    Node nodeTypeSpec = listeTypeSpec.item(j);

                    switch (nodeTypeSpec.getNodeName()) {
                        case "size" ->
                            System.out.println("size : " + nodeTypeSpec.getTextContent());
                        case "fiBold" ->
                            this.bold = Boolean.parseBoolean(nodeTypeSpec.getTextContent());
                        case "fiItalic" ->
                            this.italic = Boolean.parseBoolean(nodeTypeSpec.getTextContent());
                        case "fifontFamilyName" ->
                            this.font = nodeTypeSpec.getTextContent();
                        case "fifontStyle" ->
                            this.styleFont = nodeTypeSpec.getTextContent();
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
     * @param font
     */
    public void setFont(Font font) {
        //this.node
    }

    /**
     * Définit le style de la font, si disponible.
     *
     * @param fontStyle Style de la font.
     */
    public void setFontStyle(String fontStyle) {
    }

    /**
     *
     * @return
     */
    public int getReference() {
        return this.reference;
    }

    /**
     *
     * @return
     */
    public String getFont() {
        return this.font;
    }

    /**
     *
     * @return
     */
    public boolean isItalic() {
        return this.italic;
    }

    /**
     *
     * @return
     */
    public boolean isBold() {
        return this.bold;
    }
}
