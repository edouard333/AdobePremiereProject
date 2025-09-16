package com.phenix.adobepremiereproject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class ColumnList {

    /**
     * L'ID de l'élément.
     */
    @NotNull
    @NotBlank
    public static final String CLASS_ID = "a1c709cd-35df-4821-8200-03565d374155";

    /**
     * On ne peut pas instancier cette classe.
     *
     * @throws Exception On ne peut pas instancier cette classe.
     */
    private ColumnList() throws Exception {
        throw new Exception("Cette classe ne peut pas être instanciée.");
    }
}
