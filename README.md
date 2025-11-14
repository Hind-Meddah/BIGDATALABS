# BIGDATALABS

Bienvenue dans le dépôt **BIGDATALABS**, qui contient des labs pour apprendre et expérimenter avec Hadoop et Maven.

---

## Contenu du dépôt

Le dépôt contient deux projets principaux :

1. **Lab0** : Cluster Hadoop avec Docker Compose.
2. **Lab1** : Projet Java Maven pour manipuler HDFS.

---

## Lab0 - Cluster Hadoop avec Docker Compose

### Contenu
- `docker-compose.yml` : pour lancer un cluster Hadoop.
- `shared_volume/` et `call.txt` **ne sont pas inclus** dans le dépôt.

### Objectif
Lancer un cluster Hadoop local dans des conteneurs Docker pour tester et exécuter des programmes MapReduce ou manipuler HDFS.

### Étapes pour lancer le cluster

1. Ouvrir un terminal et se placer dans le dossier `lab0` :
   ```bash
   cd lab0

2. Lancer les conteneurs du cluster Hadoop :
    ```bash
   docker-compose up -d
   
3. Vérifier que les conteneurs tournent correctement :
    ```bash
    docker ps
4. docker-compose down :
   ```bash
    docker-compose down


---

## Lab1 - Cluster Hadoop avec Docker Compose

### Contenu
  - `pom.xml` : configuration Maven pour générer des JAR exécutables.
  - `src/` : code source Java.
  - `target/` : dossier généré par Maven après compilation (non inclus dans le dépôt).
### Génération d’un JAR
Le pom.xml est configuré pour générer un JAR exécutable.
Dans la section <configuration>, on définit la classe principale et le nom du JAR :
```xml
<configuration>
  <archive>
    <manifest>
      <mainClass>edu.supmti.hadoop.ReadHDFS</mainClass>
    </manifest>
  </archive>
  <finalName>ReadHDFS</finalName>
</configuration>
```


### Exemple pour un autre programme Java :

Pour générer un JAR pour HDFSWrite.java, modifier la section comme suit :

```xml
<configuration>
  <archive>
    <manifest>
      <mainClass>edu.supmti.hadoop.HDFSWrite</mainClass>
    </manifest>
  </archive>
  <finalName>HDFSWrite</finalName>
</configuration>
```
### Étapes pour compiler et générer le JAR :

1. Se placer dans le dossier lab1 :
     ```bash
     cd lab1

2. Compiler et générer le JAR :
    ```bash
     mvn clean package

3. Le JAR sera créé dans le dossier target/ avec le nom défini dans <finalName>.
4. copier le jar dans votre shared_volume 
5. Accéder au master
   ```bash
   docker exec -it hadoop-master bash
6. Vérifier les informations d’un fichier HDFS avec HadoopFileStatus.java
    1. **Pour afficher les informations :** :
       ```bash
         hadoop jar target/HadoopFileStatus.jar <chemin_dossier> <nom_fichier>

    3. **Pour renommer un fichier :** :
       ```bash
       hadoop jar target/HadoopFileStatus.jar <chemin_dossier> <nom_fichier> <nouveau_nom>
7. Lire un fichier HDFS avec ReadHDFS.java
     ```bash
     hadoop jar target/ReadHDFS.jar <chemin_fichier>

8. Écrire un fichier HDFS avec HDFSWrite.java
     ```bash
     hadoop jar target/HDFSWrite.jar <chemin_fichier_créer> "contenu ajouté au fichier"

## Remarques importantes :

    - Avant de générer un JAR pour un nouveau programme Java, modifier <mainClass> et <finalName> dans le pom.xml.
    
