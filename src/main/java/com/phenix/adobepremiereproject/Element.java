package com.phenix.adobepremiereproject;

/**
 * Classe générique pour tous les éléments.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
abstract class Element implements AdobeXML {

    public static final int FOLDER = 1;

    public static final int SEQUENCE = 2;

    public static final int TITLE = 3;

    private final int type_element;

    /**
     * Utilisé pour savoir quel UID on donne au dossier.
     */
    private final int id;

    /**
     * Quantième élément créé.
     */
    private static int numero_element = 0;

    /**
     * Nom de l'élément.
     */
    private final String name;

    /**
     * Quel niveau il est dans la hiérachie.
     */
    private final int level;

    /**
     * Dire que c'est un élément de type : dossier, séquence, ...
     */
    protected final String classID;

    /**
     * A quel dossier parent il est lié.
     */
    protected final Folder parent;

    /**
     * Lien avec son parent (unique).
     */
    protected final String current_ObjectURef;

    /**
     * ID unique du dossier.
     */
    private final String[] ObjectUID = {"2650d29e-54c1-426b-9ba6-7ca4553e0deb", "b9681102-4102-4254-b37b-7484bd316927"};

    /**
     * UID entre les éléments.
     */
    private final String[] ObjectURef = {
        "a06df6aa-0739-444e-bfe5-e83daa9a046a",
        "a4f8b02c-775f-4273-81e9-8884e9fe32a0",
        "b9681102-4102-4254-b37b-7484bd316927",
        "c32e6d70-d6a9-45aa-b8a9-b548bd0857d2",
        "fd2927d8-60e8-43b4-99bc-3a1bbfa8edd0",
        "fea076d7-a8ae-4c4e-b592-93acb1e074fc",
        "f5475760-2409-4cdb-860f-1bdc9ba0f0db",
        "e1bbb937-d82b-48f6-99c3-7f490b44b68b",
        "0cbec965-75e5-4284-a57e-43a29808d457",
        "0f97481d-9b7d-4e9c-9b1b-e5d08d8e173f",
        "2f9cbb63-f135-4d37-9410-a2d9a186fac3",
        "21d044a5-9877-4860-b8e2-61b0db9f1680",
        "2650d29e-54c1-426b-9ba6-7ca4553e0deb",
        "3b24e3af-0837-41d0-80e6-ccfb1c4f6eca",
        "3b802fdc-6ad0-4966-95d2-d01586ad57b8",
        "361bd3dd-f61b-4a2c-a88c-dd4cd6c9ddeb",
        "4266ca93-4f18-4835-80ea-01d7880f0a27",
        "8a0fbe7e-1379-4fb7-8588-7328d568e9ed",
        "8c9b85dd-d43c-46c5-9539-2d4e3e763d3d",
        "8346593f-aa4c-4921-9068-7ac2a3674a59",
        "88ff89b1-b7b4-4565-b42c-53ce3e9e6a03",
        "9aeb976e-4268-4c8f-8c0b-d7d084a126d8"
    };

    /**
     * ID du lien.
     */
    private int id_ObjectURef;

    /**
     * Quantième lien entre élément fait.
     */
    private static int numero_ObjectURef = 0;

    public Element(Folder parent, String name, int type_element) {
        this.parent = parent;
        this.name = name;

        // Définit le class ID.
        switch (type_element) {
            case SEQUENCE:
                this.classID = "cb4e0ed7-aca1-4171-8525-e3658dec06dd";
                break;
            case FOLDER:
                this.classID = "dbfd6653-24da-480e-a35e-ba45e9504e4b";
                break;
            case TITLE:
                this.classID = "cb4e0ed7-aca1-4171-8525-e3658dec06dd";
                break;
            default:
                this.classID = "cb4e0ed7-aca1-4171-8525-e3658dec06dd";
        }
        this.type_element = type_element;

        this.current_ObjectURef = ObjectURef[numero_ObjectURef];

        // S'il a un parent, on prend celui du prant +1.
        if (parent != null) {
            this.level = parent.getLevel() + 1;
            // On ajoute au parent le lien entre l'enfant et le parent.
            this.parent.sub_ObjectURef.add(this.current_ObjectURef);
        } // Sinon, c'est le niveau le plus haut et c'est 0 (il a une ref parent).
        else {
            this.level = 0;
        }
        // On indique qu'on pourra récupérer un nouveau ID de lien.
        numero_ObjectURef++;

        this.id = numero_element;
        // Quantième folder créé, on incrément d'office.
        numero_element++;
    }

    /**
     * Retourne le nom du dossier.
     *
     * @return Nom de l'élément.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Niveau dans la hiérachie des dossier (0 == root, 1 = sub-folder, ...).
     *
     * @return Niveau du dossier.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Retourne le parent.
     *
     * @return
     */
    public Folder getParent() {
        return this.parent;
    }

    /**
     * Retourne le classID.
     *
     * @return
     */
    public String getClassID() {
        return this.classID;
    }

    public int getTypeElement() {
        return this.type_element;
    }

    /**
     * Retourne ...
     *
     * @return
     */
    public String getObjectUID() {
        return this.ObjectUID[id];
    }

    /**
     * Retourne son lien avec le parent.
     *
     * @return
     */
    public String getCurrentObjectURef() {
        return this.current_ObjectURef;
    }
}
