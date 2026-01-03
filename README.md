# 🎡 Park - Management Game (Java Swing)

**Projet Universitaire - Génie Logiciel (L2)** Réalisé par : **Steven BASKARA**, **Julien RAAD** & **Thomas HORNUNG**

## 📝 Présentation
Inspiré des classiques comme *RollerCoaster Tycoon*, **Park** est un jeu de gestion développé en Java. Le joueur doit construire un parc d'attractions sur une carte quadrillée, gérer ses finances en temps réel et satisfaire les besoins des visiteurs pour faire prospérer son entreprise.

## ✨ Fonctionnalités implémentées
- **Moteur de jeu en temps réel** : Simulation gérée par un Thread dédié, incluant un système de calendrier précis (minutes, heures, jours, mois, années).
- **Construction & Économie** :
  - Achat et placement de structures variées : Manèges (Carrousel, Grande Roue, Karting, Chaises Volantes), Commerces (Restaurants, Stands de glace) et Décorations (Fontaines, Lampes, Plantes).
  - Gestion du budget avec coûts d'achat et profits générés par l'attractivité du parc.
- **Interface Graphique (IHM)** :
  - Menu d'accueil interactif (`LaunchPage`).
  - Tableau de bord en temps réel affichant l'argent, le nombre de visiteurs et la date.
  - Système de curseur intelligent pour sélectionner les zones de construction.
- **Simulation des visiteurs** : Algorithme de spawn et de déplacement autonome des visiteurs sur les chemins.
- **Gestion de la carte** : Système de parcelles achetables pour agrandir l'espace disponible.

## 🛠️ Architecture du Code (Package `src`)
Le projet suit une organisation modulaire pour séparer la logique de l'affichage :
- **`config`** : Paramétrage global du jeu (taille des blocs, vitesse, dimensions).
- **`data`** : Gestion de l'état du jeu, des statistiques (`ActiveStats`) et du système de sauvegarde.
- **`gui`** : Toute la partie visuelle (Swing), incluant la stratégie de dessin (`PaintStrategy`) et le chargement des ressources.
- **`engine`** : Noyau fonctionnel gérant la logique métier et le `ParcManager`.
- **`test`** : Point d'entrée principal pour le lancement de l'application.

## 🚀 Installation et Lancement
1. Clonez le dépôt.
2. Importez le projet dans un IDE Java (Eclipse recommandé).
3. Assurez-vous que le dossier `images/` est à la racine du projet pour le chargement des textures.
4. Lancez la classe `LaunchPage.java` pour accéder au menu principal.

## 📚 Documentation
Le dossier `/docs` contient le rapport complet de Génie Logiciel avec les diagrammes de classes et le manuel utilisateur détaillé.
