# Adobe Premiere Project
Librairie qui génère des projets Adobe Premiere Pro.

Cela génère un projet compatible et prévu pour Adobe Premiere Pro CC2024.

# Fonctionnalités
* Ajouter des dossiers (dans des dossiers) ;
* Ajouter une séquence (sans la modifier) ;
* Ajouter un titre (sans le modifier) ;
* Ajouter des marqueurs à une séquence ;

# Comment l'utiliser ?
## Dépendances
Le projet dépendant des projets suivants :
* [Timecode](https://github.com/edouard333/Timecode)
```xml
<dependency>
  <groupId>com.phenix</groupId>
  <artifactId>timecode</artifactId>
  <version>0.7.1</version>
</dependency>
```

## Utilisation de la librairie
```java
// Initier un projet :
AdobePremiereProject projet = new AdobePremiereProject(new File(AdobePremiereProject.EXTENSION));

// Créer et ajouter un dossier :
Folder dossier = new Folder("Nom dossier", false);
projet.addElement(dossier);

// Créer et ajouter une séquence :
Sequence sequence = new Sequence(dossier, "Nom séquence");
sequence.setStartTimecode(new Timecode("01:00:00:00", Framerate.25D));
sequence.setFramerate(Framerate.25D);
sequence.setResolution(3840, 2160);

projet.addElement(sequence);

// Générer le projet.
projet.close();
```

# Roadmap
* Personnalisation :
  * Séquence
  * Titre
  * Interface utilisateur

* Element prévu à supporter :
  * Image (fichier)
  * Audio (fichier)
  * Vidéo (fichier)
  * Mire, décompte, 1.000Hz, ...

* Séquence :
  * Pouvoir insérer un élément (titre, image, son, ...) à une séquence.
  * Pouvoir insérer une séquence dans une séquence (notion de séquence imbriquée).

Dans un 1er temps, les métadonnées (résolutions, framerate, stéréo, ...) des fichiers multimédias ajoutés aux projets devront être géré par l'utilisateur.\
Dans un second temps, cela sera pris en charge (partiellement ou complètement) par le projet Java via MediaInfo.