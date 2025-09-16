package com.phenix.adobepremiereproject;

/**
 * Liste de résolution standard.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public enum ResolutionStandard {
    /**
     * La HD.
     */
    HD(1920, 1080),
    /**
     * L'UHD.
     */
    UHD(3840, 2160);

    /**
     * La hauteur.
     */
    public final int hauteur;

    /**
     * La largeur
     */
    public final int largeur;

    /**
     * Crée une résolution standard avec une largeur et hauteur.
     *
     * @param largeur La largeur.
     * @param hauteur La hauteur.
     */
    private ResolutionStandard(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
}
