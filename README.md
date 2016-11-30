# WaterPlant

Cette application a été demandé aux étudiants de Master 2 e-service à l'université de Lille 1.

## But

Le but de l'application est de gérer l'arrosage de ses plantes d'intérieur. Pour cela l'application affiche la liste des plantes que l'on possède.
Il est possible d'ajouter, de modifier et de supprimer une plante.
En cliquant sur une plante ont pour visualiser les détails de celle ci
En faisant un long click on arrose la plante.

Il y a 3 types de couleur pour l'arrosage :
    - Vert : la plante n'a pas besoin d'être arroser
    - Orange : il va falloir penser à l'arroser sous peut
    - Rouge : il faut arroser la plante.

## Architecture

Pour realiser cette application j'ai utiliser l'architecture MVP de google : https://github.com/googlesamples/android-architecture

## Base de données

La base de données contient qu'une seule table

Plantes qui contient :

    - id
    - nom
    - description
    - fréquence d'arrosage
    - une date d'arrosage
    - une date où elle a été arrosée
