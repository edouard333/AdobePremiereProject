package com.phenix.adobepremiereproject.adobetitle.font;

/**
 * Utilise une font pour un projet AdobePremiere.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
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
