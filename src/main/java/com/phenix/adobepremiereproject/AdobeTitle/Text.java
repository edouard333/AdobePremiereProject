/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phenix.adobepremiereproject.AdobeTitle;

import com.phenix.adobepremiereproject.AdobeTitle.Font.Font;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Node;

/**
 * Partie qui conserne les textes dans les "Title" Adobe, la balise "TextChain".
 *
 * @author Edouard Jeanjean <edouard128@hotmail.com>
 */
public class Text {

    /**
     * Le node du TextChain.
     */
    private Node node;

    /**
     * Certaines propriété se trouve dedans pour le texte...
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
            this.node = (Node) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("C:\\TMP\\template_text.xml"));
            this.textDescription = new TextDescription(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("C:\\TMP\\template_textdescription.xml")));
        } catch (Exception ex) {
        }
        this.needTextDescription = false;
    }

    /**
     * Récupère le node.
     *
     * @param node
     * @param textDescription
     */
    public Text(Node node, TextDescription textDescription) {
        this.node = node;
        this.textDescription = textDescription;

        this.needTextDescription = (textDescription != null);
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
    }

    public void setPositionX(float x) {
        this.node.getChildNodes().item(0).getChildNodes().item(1).getChildNodes().item(0).setTextContent(x + "");
    }

    public void setPositionY(float y) {
        this.node.getChildNodes().item(0).getChildNodes().item(1).getChildNodes().item(1).setTextContent(y + "");
    }

    public float getPositionX() {
        return Float.parseFloat(this.node.getChildNodes().item(0).getChildNodes().item(1).getChildNodes().item(0).getTextContent());
    }

    public float getPositionY() {
        return Float.parseFloat(this.node.getChildNodes().item(0).getChildNodes().item(1).getChildNodes().item(1).getTextContent());
    }

    public void setSizeX(float x) {
        this.node.getChildNodes().item(0).getChildNodes().item(2).getChildNodes().item(0).setTextContent(x + "");
    }

    public void setSizeY(float y) {
        this.node.getChildNodes().item(0).getChildNodes().item(2).getChildNodes().item(1).setTextContent(y + "");
    }

    public float getSizeX() {
        return Float.parseFloat(this.node.getChildNodes().item(0).getChildNodes().item(2).getChildNodes().item(0).getTextContent());
    }

    public float getSizeY() {
        return Float.parseFloat(this.node.getChildNodes().item(0).getChildNodes().item(2).getChildNodes().item(1).getTextContent());
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

    public void setText(String text) {
        this.node.getChildNodes().item(2).getChildNodes().item(4).setTextContent(text + "");
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
     * Si des métadonnées en plus du texte sont nécessaire, ils seront retourné.
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

}
