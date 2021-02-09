package com.phenix.adobepremiereproject.AdobeTitle.Font;

/**
 * Utilise une font pour un projet AdobePremiere.
 *
 * @author Edouard Jeanjean <edouard128@hotmail.com>
 */
public class Font {

    /**
     * Nom complet de la font.
     */
    protected final String fullName;

    /**
     * Nom de la famille de la font.
     */
    protected final String familyName;

    /**
     * Définit une Font.
     *
     * @param fullName
     * @param familyName
     */
    public Font(String fullName, String familyName) {
        this.fullName = fullName;
        this.familyName = familyName;
    }

    /**
     * Récupère le nom complet de la font.
     *
     * @return Nom complet.
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * Récupère la famille de la font.
     *
     * @return Famille de la font.
     */
    public String getFamilyName() {
        return this.familyName;
    }
}
