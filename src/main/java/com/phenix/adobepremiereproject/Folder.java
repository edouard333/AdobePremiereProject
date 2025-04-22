package com.phenix.adobepremiereproject;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Dossier dans un projet Adobe Premiere Pro CC2017.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class Folder extends Element {

    /**
     * Les liens qu'il a avec des enfants.
     */
    protected List<String> sub_ObjectURef;

    /**
     * Indique si le dossier doit être ouvert ou non.
     */
    private boolean expanded;

    /**
     * Crée un dossier à la racine du projet.
     *
     * @param name Nom du dossier.
     */
    public Folder(String name) {
        this(null, name, true);
    }

    /**
     * Crée un dossier à la racine du projet.
     *
     * @param name Nom du dossier.
     * @param expanded Ouvrir le dossier.
     */
    public Folder(String name, boolean expanded) {
        this(null, name, expanded);
    }

    /**
     * Crée un dossier.
     *
     * @param parent Le dossier parent.
     * @param name Nom du dossier.
     */
    public Folder(Folder parent, String name) {
        this(parent, name, true);
    }

    /**
     * Crée un dossier.
     *
     * @param parent Le dossier parent.
     * @param name
     * @param expanded
     */
    public Folder(Folder parent, String name, boolean expanded) {
        super(parent, name, TypeElement.FOLDER);

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
     * @return Si c'est deployé ou non.
     */
    public boolean getExpanded() {
        return this.expanded;
    }

    /**
     * Retourne un lien enfant selon son index.
     *
     * @param index L'index.
     * @return Le lien enfant.
     */
    public String getObjectURef(int index) {
        return this.sub_ObjectURef.get(index);
    }

    /**
     * Retourne l'Object URefs.
     *
     * @return L'Object URefs.
     */
    public List<String> getObjectURefs() {
        return this.sub_ObjectURef;
    }

    /**
     *
     * @param file
     * @param order
     */
    @Override
    public void toXML(PrintWriter writer, int order) {
        writer.append("\t<BinProjectItem ObjectUID=\"" + this.getCurrentObjectURef() + "\" ClassID=\"" + this.getClassID() + "\" Version=\"1\">\n");
        writer.append("\t\t<ProjectItem Version=\"1\">\n");
        writer.append("\t\t\t<Node Version=\"1\">\n");
        writer.append("\t\t\t\t<Properties Version=\"1\">\n");
        writer.append("\t\t\t\t\t<Column.PropertyText.Label>BE.Prefs.LabelColors.7</Column.PropertyText.Label>\n");
        writer.append("\t\t\t\t\t<list.view.expanded.state.3625b009_45_0f43_45_4db8_45_8f24_45_6be33ebbaa5f>" + (this.getExpanded() ? "true" : "false") + "</list.view.expanded.state.3625b009_45_0f43_45_4db8_45_8f24_45_6be33ebbaa5f>\n");

        // Quand c'est le niveau 0, je ne sais pas mais il y a ça...
        //if (this.getLevel() == 0) {
        writer.append("\t\t\t\t\t<project.icon.view.grid.order>" + order + "</project.icon.view.grid.order>\n");
        //}

        writer.append("\t\t\t\t</Properties>\n");

        if (order == 0) {
            writer.append("\t\t\t\t<ID>1000001</ID>\n");
        }

        writer.append("\t\t\t</Node>\n");
        writer.append("\t\t\t<Name>" + this.getName() + "</Name>\n");
        writer.append("\t\t</ProjectItem>\n");
        writer.append("\t\t<ProjectItemContainer Version=\"1\">\n");

        // En plus level 1 (s'il a des subs éléments) :
        if (!this.getObjectURefs().isEmpty()) {
            writer.append("\t\t\t<Items Version=\"1\">\n");

            for (int j = 0; j < this.getObjectURefs().size(); j++) {
                writer.append("\t\t\t\t<Item Index=\"" + j + "\" ObjectURef=\"" + this.getObjectURef(j) + "\"/>\n");
            }
            writer.append("\t\t\t</Items>\n");
        }

        writer.append("\t\t</ProjectItemContainer>\n");
        writer.append("\t</BinProjectItem>\n");
    }
}
