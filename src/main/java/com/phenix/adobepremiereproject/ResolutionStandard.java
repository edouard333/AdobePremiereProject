package com.phenix.adobepremiereproject;

/**
 * Liste de résolution standard.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public enum ResolutionStandard {
    HD(1920, 1080),
    UHD(3840, 2160);

    /**
     * Hauteur.
     */
    private final int hauteur;

    /**
     * Largeur
     */
    private final int largeur;

    private ResolutionStandard(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public int getHauteur() {
        return this.hauteur;
    }

    public int getLargeur() {
        return this.largeur;
    }
}
