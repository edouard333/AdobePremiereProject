package com.phenix.adobepremiereproject;

/**
 * Un marqueur dans une séquence.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Marker {

    /**
     * Nom du marqueur.
     */
    private String name;

    /**
     * Couleur du marqueur.
     */
    private MarkerColor color;

    /**
     * Commentaire du marqueur.
     */
    private String comment;

    /**
     * Construit un marqueur.
     */
    public Marker() {
    }

    /**
     * Construit un marqueur.
     *
     * @param name Nom du marqueur.
     * @param comment Commentaire du marqueur.
     * @param color Couleur du marqueur.
     */
    public Marker(String name, String comment, MarkerColor color) {
        this.name = name;
        this.comment = comment;
        this.color = color;
    }

    /**
     * Retourne la couleur du marqueur.
     *
     * @return La couleur.
     */
    public MarkerColor getColor() {
        return this.color;
    }

    /**
     * Retourne le commentaire.
     *
     * @return Le commentaire.
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * Retourne le nom du marqueur.
     *
     * @return Nom du marqueur.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Modifie la couleur du marqueur.
     *
     * @param color La couleur.
     */
    public void setColor(MarkerColor color) {
        this.color = color;
    }

    /**
     * Modifie le commentaire.
     *
     * @param comment Le commentaire.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Modifie le nom du marqueur.
     *
     * @param name Le nom.
     */
    public void setName(String name) {
        this.name = name;
    }
}
