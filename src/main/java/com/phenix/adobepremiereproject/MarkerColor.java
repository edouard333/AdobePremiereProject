package com.phenix.adobepremiereproject;

import com.phenix.adobepremiereproject.internal.AdobeProjectConvertible;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

/**
 * Couleur que peut prendre un marqueur.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public enum MarkerColor implements AdobeProjectConvertible {
    /**
     * La couleur rouge.
     */
    RED(4281740498L);

    /**
     * Valeur de la couleur.
     */
    private final long value;

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
    @Null
    public static MarkerColor fromValue(long value) {
        for (MarkerColor marqueur : values()) {
            if (marqueur.value == value) {
                return marqueur;
            }
        }

        return null;
    }

    @NotNull
    @NotBlank
    @Override
    public String toAdobeProject() {
        return "" + this.value;
    }
}
