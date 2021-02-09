/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phenix.adobepremiereproject;

import java.io.PrintWriter;

/**
 * Indique ce qui doit être implémenté pour que l'XML Adobe soit OK.
 *
 * @author Edouard
 */
public interface AdobeXML {

    /**
     * Génère le XML pour le projet.
     *
     * @param file Flux où on ajoute les informations.
     * @param order Numéro d'ordre.
     */
    public void toXML(PrintWriter file, int order);
}
