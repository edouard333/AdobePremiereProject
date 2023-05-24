package com.phenix.adobepremiereproject;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Dossier dans un projet Adobe Premiere Pro CC2017.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Folder extends Element {

    /**
     * Les liens qu'il a avec des enfants.
     */
    protected ArrayList<String> sub_ObjectURef;

    /**
     * Indique si le dossier doit être ouvert où non.
     */
    private boolean expanded;

    /**
     * Créé un dossier.
     *
     * @param name Nom du dossier.
     */
    public Folder(String name) {
        this(null, name, true);
    }

    /**
     * Créé un dossier à la racine du projet.
     *
     * @param name Nom du dossier.
     * @param expanded Ouvrire le dossier.
     */
    public Folder(String name, boolean expanded) {
        this(null, name, expanded);
    }

    /**
     * Créé un dossier.
     *
     * @param parent Le dossier parent.
     * @param name Nom du dossier.
     */
    public Folder(Folder parent, String name) {
        this(parent, name, true);
    }

    /**
     * Créé un dossier.
     *
     * @param parent Le dossier parent.
     * @param name
     * @param expanded
     */
    public Folder(Folder parent, String name, boolean expanded) {
        super(parent, name, Element.FOLDER);

        // Initialise les liens.
        this.sub_ObjectURef = new ArrayList<String>();

        // Par défaut c'est fermé.
        this.expanded = expanded;
    }

    /**
     * Modifie si le dossier doit être deployé (ouvert) ou fermé.
     *
     * @param expanded True si ouvert.
     */
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * Indique si le dossier doit être deployé ou non.
     *
     * @return
     */
    public boolean getExpanded() {
        return this.expanded;
    }

    /**
     * Retourne un lien enfant.
     *
     * @param index
     * @return
     */
    public String getObjectURef(int index) {
        return this.sub_ObjectURef.get(index);
    }

    public ArrayList<String> getObjectURefs() {
        return this.sub_ObjectURef;
    }

    @Override
    public void toXML(PrintWriter file, int order) {
        file.append("\t<BinProjectItem ObjectUID=\"" + this.getCurrentObjectURef() + "\" ClassID=\"" + this.getClassID() + "\" Version=\"1\">\n");
        file.append("\t\t<ProjectItem Version=\"1\">\n");
        file.append("\t\t\t<Node Version=\"1\">\n");
        file.append("\t\t\t\t<Properties Version=\"1\">\n");
        file.append("\t\t\t\t\t<Column.PropertyText.Label>BE.Prefs.LabelColors.7</Column.PropertyText.Label>\n");
        file.append("\t\t\t\t\t<list.view.expanded.state.3625b009_45_0f43_45_4db8_45_8f24_45_6be33ebbaa5f>" + (this.getExpanded() ? "true" : "false") + "</list.view.expanded.state.3625b009_45_0f43_45_4db8_45_8f24_45_6be33ebbaa5f>\n");

        // Quand c'est le niveau 0, je ne sais pas mais il y a ça...
        if (this.getLevel() == 0) {
            file.append("\t\t\t\t\t<project.icon.view.grid.order>" + order + "</project.icon.view.grid.order>\n");
        }

        file.append("\t\t\t\t</Properties>\n");
        file.append("\t\t\t</Node>\n");
        file.append("\t\t\t<Name>" + this.getName() + "</Name>\n");
        file.append("\t\t</ProjectItem>\n");
        file.append("\t\t<ProjectItemContainer Version=\"1\">\n");

        // En plus level 1 (s'il a des subs éléments) :
        if (this.getObjectURefs().size() > 0) {
            file.append("\t\t\t<Items Version=\"1\">\n");

            for (int j = 0; j < this.getObjectURefs().size(); j++) {
                file.append("\t\t\t\t<Item Index=\"" + j + "\" ObjectURef=\"" + this.getObjectURef(j) + "\"/>\n");
            }
            file.append("\t\t\t</Items>\n");
        }

        file.append("\t\t</ProjectItemContainer>\n");
        file.append("\t</BinProjectItem>\n");
    }
}
