package com.phenix.adobepremiereproject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Le projet.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class Project {

    /**
     * L'id du type.
     */
    @NotNull
    @NotBlank
    public static final String CLASS_ID = "62ad66dd-0dcd-42da-a660-6d8fbde94876";

    /**
     * On ne peut pas instancier cette classe.
     *
     * @throws Exception On ne peut pas instancier cette classe.
     */
    private Project() throws Exception {
        throw new Exception("Cette classe ne peut pas être instanciée.");
    }
}
