package com.phenix.adobepremiereproject;

/**
 * Liste des versions d'Adobe Premiere Pro.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public enum Version {
    /**
     * Version CC2015 d'Adobe Premiere Pro.
     */
    CC2015("31"),
    /**
     * Version CC2017 d'Adobe Premiere Pro (version 11.0).
     */
    CC2017("32"),
    /**
     * Version CC2017.1.2 d'Adobe Premiere Pro (version 11.X).
     */
    CC2017_1_2("33"),
    /**
     * Version CC2018 d'Adobe Premiere Pro (version 12.1.2).
     */
    CC2018("35"),
    /**
     * Version CC2018 d'Adobe Premiere Pro (version 13.0).
     */
    CC2019("36"),
    /**
     * Version CC2018 d'Adobe Premiere Pro (version 13.1).
     */
    CC2019_01("36"),
    /**
     * Version CC2018 d'Adobe Premiere Pro (version 13.1.1).
     */
    CC2019_02("36"),
    /**
     * Version CC2018 d'Adobe Premiere Pro (version 13.1.2).
     */
    CC2019_03("36"),
    /**
     * Version CC2018 d'Adobe Premiere Pro (version 13.1.3).
     */
    CC2019_04("36"),
    /**
     * Version CC2018 d'Adobe Premiere Pro (version 13.1.4).
     */
    CC2019_05("37"),
    /**
     * Version CC2018 d'Adobe Premiere Pro (version 13.1.5).
     */
    CC2019_06("37"),
    /**
     * Version CC2020 d'Adobe Premiere Pro (version 14.3.1).
     */
    CC2020("38"),
    /**
     * Version CC2021 d'Adobe Premiere Pro (version 15.X.X).
     */
    CC2021("40"),
    /**
     * Version CC2022 d'Adobe Premiere Pro (version 22.0.0).
     */
    CC2022("41"),
    /**
     * Version CC2023 d'Adobe Premiere Pro (version 23.2).
     */
    CC2023(null);

    /**
     * Numéro de version.
     */
    private final String valeur;

    private Version(String valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return this.valeur;
    }
}
