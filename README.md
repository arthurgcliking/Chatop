# Projet Chatop - README

## Prérequis

* Java 11 ou version ultérieure installé sur votre ordinateur
* Un éditeur de code (ex: IntelliJ IDEA, Eclipse, Visual Studio Code)
* MySQL installé sur votre ordinateur
* Un client MySQL (ex: MySQLWorkbench)

## Installation du projet

1. Téléchargez le projet depuis le repository GitHub suivant : <https://github.com/arthurgcliking/Chatop.git>
2. Décompressez le fichier ZIP téléchargé
3. Ouvrez le projet dans votre éditeur de code préféré
4. Ouvrez le fichier `application.properties` situé dans le répertoire `src/main/resources`
5. Configurez les paramètres de connexion à la base de données MySQL en renseignant les valeurs correspondant à votre BDD :
```vbnet
spring.datasource.url=jdbc:postgresql://localhost:3306/chatop
spring.datasource.username=votre-nom-utilisateur
spring.datasource.password=votre-mot-de-passe
spring.jpa.hibernate.ddl-auto=update
```
6. Enregistrez le fichier `application.properties`
7. Ouvrez un terminal et naviguez jusqu'au répertoire racine du projet Angular
8. Installez les dépendances :
```
npm install
```
9. Exécutez la commande suivante pour lancer le projet côté Angular :
```
ng serve
```
10. Le projet Angular est maintenant accessible à l'adresse suivante : http://localhost:4200
11. Ouvrez un terminal et naviguez jusqu'au répertoire racine du projet Spring Chatop
12. Exécutez la commande suivante pour installer les dépendances du projet :
```
mvn clean install
```
13. Une fois l'installation terminée, exécutez la commande suivante pour lancer le projet :
```
java -jar target/Chatop-0.0.1-SNAPSHOT.jar
```
14. Le projet est maintenant accessible à l'adresse suivante : http://localhost:3001
15. Les deux serveurs étant lancés, vous pouvez désormais utiliser le site Angular avec l'API Spring



## Installation de la base de données

1. Ouvrez votre console MySQL dans votre Terminal
2. Connectez-vous à votre serveur MySQL local
3. Créez une nouvelle base de données nommée `chatop`
4. Après avoir installer les dépendances et lancer le projet, Hibernate a modifié la base de données en fonction des structures d'entités données grâce à cette ligne dans `application.properties` : `spring.jpa.hibernate.ddl-auto=update`. Il n'est donc pas nécessaire de créer les tables pour l'application manuellement.
5. La base de données est maintenant prête à être utilisée par l'application.

## Documentation

La documentation de l'API est accessible à l'adresse suivante : <http://localhost:3001/swagger-ui/>

Vous y trouverez la liste complète des routes disponibles ainsi que leur documentation.

## Sécurisation

L'application est sécurisée à l'aide de Spring Security et JWT. Toutes les routes nécessitent une authentification à l'exception des routes de création de compte, de connexion et la documentation Swagger. Le mot de passe est crypté en base de données à l'aide de BCrypt. Les credentials de la base de données ne sont pas stockés dans le code mais dans le fichier `application.properties`.
