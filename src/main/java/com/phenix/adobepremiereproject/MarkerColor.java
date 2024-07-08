package com.phenix.adobepremiereproject;

/**
 * Couleur que peut prendre un marqueur.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public enum MarkerColor {
    RED(4281740498L);

    /**
     * Valeur de la couleur.
     */
    private long value;

    /**
     * Définit une couleur de marqueur.
     *
     * @param value Valeur de la couleur.
     */
    private MarkerColor(long value) {
        this.value = value;
    }

    /**
     * Retourne une couleur en fonction de la valeur de couleur.
     *
     * @param value Valeur de la couleur.
     * @return Le marqueur.
     */
    public static MarkerColor from(long value) {
        MarkerColor[] liste = values();

        for (MarkerColor marqueur : liste) {
            if (marqueur.value == value) {
                return marqueur;
            }
        }
        return null;
    }

    /**
     * Retourne la valeur de la couleur.
     *
     * @return Valeur de la couleur.
     */
    public long getValue() {
        return this.value;
    }
}
