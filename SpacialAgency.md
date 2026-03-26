Commandes Java pour démarrer :
- Récupération des fichirs .java à compiler : ``Get-ChildItem -Recurse -Filter "*.java" src/main/java | % { $_.FullName } | Out-File -Encoding ASCII sources.txt``
- Compilation : ``javac -cp "C:\Users\Flori\.m2\repository\org\projectlombok\lombok\1.18.36\lombok-1.18.36.jar" -processorpath "C:\Users\Flori\.m2\repository\org\projectlombok\lombok\1.18.36\lombok-1.18.36.jar" -d out `@sources.txt``
- Création du .jar : ``jar cfm SpacialAgency.jar MANIFEST.MF -C out . -C src/main/resources .``
- Démarrage de l'application : ``java -jar SpacialAgency.jar``