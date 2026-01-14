package com.phenix.adobepremiereproject.adobetitle;

import com.phenix.adobepremiereproject.adobetitle.font.Font;
import jakarta.validation.constraints.NotNull;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Partie qui concerne les textes dans les "Title" Adobe, la balise "TextChain".
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class Text {

    private static final String DOSSIER_TMP = "C:\\TMP";

    /**
     * Le node du TextChain.
     */
    private Node node;

    /**
     * Certaines propriétés se trouvent dedans pour le texte...
     */
    private TextDescription textDescription;

    /**
     * Indique s'il faut un textDescription dans l'XML.
     */
    private boolean needTextDescription;

    /**
     * Construit un node sur base de l'XML.
     */
    public Text() {
        try {
            this.node = (Node) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(DOSSIER_TMP + File.separator + "text_chain.xml"));
            this.textDescription = new TextDescription(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(DOSSIER_TMP + File.separator + "text_description.xml")));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        this.needTextDescription = false;
    }

    /**
     * Récupère le node.
     *
     * @param node
     * @param textDescription
     */
    public Text(@NotNull Node node, @NotNull Iterable<TextDescription> textDescription) {
        this.node = node;

        System.out.println("> text : " + node.getNodeName());

        /*
        <TextChain>
                            <ChainProperty Version="9">
                                <wordWrap>false</wordWrap>
                                <Position>
                                    <x>1073.15</x>
                                    <y>198.723</y>
                                </Position>
                                <Size>
                                    <x>257.275</x>
                                    <y>100</y>
                                </Size>
                                <leading>0</leading>
                                <lockedLinesX>true</lockedLinesX>
                                <lockedLinesY>true</lockedLinesY>
                                <boxCanGrow>false</boxCanGrow>
                                <tabModeStyle>Word</tabModeStyle>
                                <implicitTabSpacing>100</implicitTabSpacing>
                                <implicitTabType>left</implicitTabType>
                            </ChainProperty>
                            <ChainTabs>
                                <TabList></TabList>
                            </ChainTabs>
                            <TextLine Version="2" objectID="2" persistentID="2">
                                <BaseProperties Version="5">
                                    <txBase>272.209</txBase>
                                    <XPos>1073.15</XPos>
                                    <angle>0</angle>
                                    <verticalText>false</verticalText>
                                    <objectLeading>0</objectLeading>
                                </BaseProperties>
                                <EnclosingObjectType>block</EnclosingObjectType>
                                <Alignment>left</Alignment>
                                <RTL>false</RTL>
                                <TRString>Valeur</TRString>
                                <RunLengthEncodedCharacterAttributes>
                                    <CharacterAttributes RunCount="7" StyleRef="4096" TextRef="4097" TXKerning="0" TXPostKerning="0" BaselineShifting="0" />
                                </RunLengthEncodedCharacterAttributes>
                                <tagName>
                                    <name />
                                </tagName>
                            </TextLine>
                        </TextChain>
         */
        NodeList textChain = this.node.getChildNodes();

        for (int i = 0; i < textChain.getLength(); i++) {
            Node nodeTextChain = textChain.item(i);

            if (nodeTextChain.getNodeName().equals("TextLine")) {
                NodeList textLine = nodeTextChain.getChildNodes();

                for (int j = 0; j < textLine.getLength(); j++) {
                    Node nodeTextLine = textLine.item(j);

                    if (nodeTextLine.getNodeName().equals("RunLengthEncodedCharacterAttributes")) {
                        NodeList characterAttributes = nodeTextLine.getChildNodes();

                        for (int k = 0; k < characterAttributes.getLength(); k++) {
                            Node nodeCharacterAttributes = characterAttributes.item(k);

                            if (nodeCharacterAttributes.getNodeName().equals("CharacterAttributes")) {
                                Node attribute = nodeCharacterAttributes;

                                int reference = Integer.parseInt(attribute.getAttributes().getNamedItem("TextRef").getNodeValue());

                                System.out.println("TextRef : " + reference);

                                // On lie le TextDescription qu'on a avec le bon texte...
                                for (TextDescription textDescription_ : textDescription) {
                                    if (textDescription_.getReference() == reference) {
                                        this.textDescription = textDescription_;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        this.needTextDescription = (textDescription != null);
    }

    /**
     *
     * @param x
     */
    public void setPositionX(float x) {
        this.node.getChildNodes().item(0).getChildNodes().item(1).getChildNodes().item(0).setTextContent(x + "");
    }

    /**
     *
     * @param y
     */
    public void setPositionY(float y) {
        this.node.getChildNodes().item(0).getChildNodes().item(1).getChildNodes().item(1).setTextContent(y + "");
    }

    /**
     *
     * @return
     */
    public float getPositionX() {
        return Float.parseFloat(this.node.getChildNodes().item(0).getChildNodes().item(1).getChildNodes().item(0).getTextContent());
    }

    /**
     *
     * @return
     */
    public float getPositionY() {
        return Float.parseFloat(this.node.getChildNodes().item(0).getChildNodes().item(1).getChildNodes().item(1).getTextContent());
    }

    /**
     *
     * @param x
     */
    public void setSizeX(float x) {
        this.node.getChildNodes().item(0).getChildNodes().item(2).getChildNodes().item(0).setTextContent(x + "");
    }

    /**
     *
     * @param y
     */
    public void setSizeY(float y) {
        this.node.getChildNodes().item(0).getChildNodes().item(2).getChildNodes().item(1).setTextContent(y + "");
    }

    /**
     *
     * @return
     */
    public float getSizeX() {
        return Float.parseFloat(this.node.getChildNodes().item(0).getChildNodes().item(2).getChildNodes().item(0).getTextContent());
    }

    /**
     *
     * @return
     */
    public float getSizeY() {
        return Float.parseFloat(this.node.getChildNodes().item(0).getChildNodes().item(2).getChildNodes().item(1).getTextContent());
    }

    /**
     *
     * @return
     */
    public String getFont() {
        return this.textDescription.getFont();
    }

    /**
     * Définit la font du text.
     *
     * @param font
     */
    public void setFont(Font font) {
        this.textDescription.setFont(font);
        this.needTextDescription = true;
    }

    /**
     * Définit si le texte doit être en italic ou non.
     *
     * @param italic
     */
    public void setItalic(boolean italic) {
        this.textDescription.setItalic(italic);
        this.needTextDescription = true;
    }

    /**
     * Retourne le texte du title.
     *
     * @return
     */
    public String getText() {
        return this.node.getChildNodes().item(2).getChildNodes().item(4).getTextContent();
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        for (int i = 0; i < this.node.getChildNodes().getLength(); i++) {
            Node nodeEnfant = this.node.getChildNodes().item(i);

            System.out.println(">> " + nodeEnfant.getNodeName());

            if (nodeEnfant.getNodeName().equals("TextChain")) {
                NodeList textChain = nodeEnfant.getChildNodes();

                System.out.println(">>>" + textChain.item(i).getTextContent());

                for (int j = 0; j < textChain.getLength(); j++) {
                    System.out.println(">>>" + textChain.item(j).getNodeName());

                    if (textChain.item(j).getNodeName().equals("TextLine")) {
                        NodeList textLine = textChain.item(j).getChildNodes();

                        for (int k = 0; k < textLine.getLength(); k++) {
                            Node nodeTextLine = textLine.item(k);

                            if (nodeTextLine.getNodeName().equals("TRString")) {
                                System.out.println(nodeTextLine.getTextContent());
                                nodeTextLine.setTextContent(text);
                            }
                        }
                    }
                }
            }
        }

        //this.node.getChildNodes().item(2).getChildNodes().item(4).setTextContent(text + "");
    }

    /**
     * Quand on a finit avec les modifs.
     *
     * @return
     */
    public Node toNode() {
        return this.node;
    }

    /**
     * Si des métadonnées en plus du texte sont nécessaires, ils seront
     * retourné.
     *
     * @return Les métadonnées en plus pour le texte.
     */
    public TextDescription getTextDescription() {
        if (this.needTextDescription) {
            return this.textDescription;
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     */
    public boolean isItalic() {
        return this.textDescription.isItalic();
    }

    /**
     *
     * @return
     */
    public boolean isBold() {
        return this.textDescription.isBold();
    }
}
