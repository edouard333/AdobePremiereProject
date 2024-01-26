Génère un projet Adobe Premiere Pro à l'aide de Java.

Cela génère un projet compatible et prévu pour Adobe Premiere Pro CC2017, cela doit être compatible avec les versions supérieures.

Actuellement :
* On peut ajouter des dossiers (dans des dossiers) dans le projet.
* On peut ajouter une séquence (sans la modifier) dans le projet.
* On peut ajouter un titre (sans le modifier) dans le projet.

Roadmap :
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

Dans un 1er temps, les métadonnées (résolutions, framerate, stéréo, ...) des fichiers multimédias ajoutés aux projets devront être géré par l'utilisateur.
Dans un second temps, cela sera pris en charge (partiellement ou complètement) par le projet Java via MediaInfo.