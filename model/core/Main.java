package model.core;

import java.awt.Color;

import controller.Controller;
import model.coordonees.Coordonnees;
import model.joueur.Joueur;
import model.partie.Partie;
import model.piece.Pion;
import model.plateau.Plateau;
import view.View;
import view.console.ConsoleView;

public class Main {
	public static Controller controller;
	public static View console;
	
	public static Partie partie;
	
	public static void main(String[] args) {	
		
		Plateau p1 = new Plateau(); // Création du plateau
		Coordonnees.construireListeCoordonnees(); //Construction de la liste des coordonnées
		p1.construireListeCases(); //Construction de la liste des cases sur le plateau
		
		
		Joueur j1 = new Joueur("Joël", "Cyn"); //Création du joueur 1
		Joueur j2 = new Joueur("Nadia", "èmmbé"); //Création du joueur 2
		
		//1 c'est l'id
		partie = new Partie(1, p1, j1, j2);
		
		controller = new Controller(j1);
		
		console = new ConsoleView(j1, controller);
		controller.addView(console);
		
		
		
		//Ne pas oublier de dire au jeu sur quelles cases il faut poser les pièces
		Integer[] listeIndexBlanc = p1.getListeindexblanc();
		Integer[] listeIndexNoir = p1.getListeindexnoir();
		
		
		//Boucle ajoutant 20 pièces à chaque joueur
		for(int i = 0; i < 20; i++) {
			
			//Création d'un pion noir de couleur noire se situant dans la liste des index noirs
			Pion pionNoir = new Pion(j2, Color.BLACK, p1.getListeCases().get(listeIndexNoir[i]));
			
			//Création d'un pion blanc de couleur blanche se situant dans la liste des index blancs
			Pion pionBlanc = new Pion(j1, Color.WHITE, p1.getListeCases().get(listeIndexBlanc[i]));
			
			//Ajout des pions dans la liste des pions de chaque joueur
			j1.getListePieces().add(pionBlanc);
			j2.getListePieces().add(pionNoir);
			
			//Ne pas oublier de dire à la case qu'un pion se situe sur elle
			 p1.getListeCases().get(listeIndexNoir[i]).setOccupeePar(pionNoir);
			 p1.getListeCases().get(listeIndexBlanc[i]).setOccupeePar(pionBlanc);
			 
			 p1.getListeCases().get(listeIndexNoir[i]).setOccupee(true);
			 p1.getListeCases().get(listeIndexBlanc[i]).setOccupee(true);
			
		}
		
		
		/*
		 * Cette boucle permet de calculer les cases adjacentes de toutes les cases présente dans la liste.
		 * 
		 *      NO     N     NE
		 * 				
		 * 
		 * 		O	Centre   E
		 * 
		 * 	
		 * 		SO     S     SE
		 * 
		 */
		for(int i = 0; i < p1.getListeCases().size(); i++) {
			
			
			if(p1.getListeCases().get(i).getCoordonnees().getX() < 9) {
				p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i + 10), "Nord");
			}
			
			if(p1.getListeCases().get(i).getCoordonnees().getX() > 0) {
				p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i - 10), "Sud");
			}
			
			if(p1.getListeCases().get(i).getCoordonnees().getY() > 0) {
				p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i - 1), "Ouest");
			}
			
			if(p1.getListeCases().get(i).getCoordonnees().getY() < 9) {
				p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i + 1), "Est");		
			}
			
			if(p1.getListeCases().get(i).getCoordonnees().getX() > 0 && p1.getListeCases().get(i).getCoordonnees().getY() > 0) {
				p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i - 11), "Sud-Ouest");		
			}
			if(p1.getListeCases().get(i).getCoordonnees().getX() > 0 && p1.getListeCases().get(i).getCoordonnees().getY() < 9) {
				p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i - 9), "Sud-Est");		
			}
			if(p1.getListeCases().get(i).getCoordonnees().getX() < 9 && p1.getListeCases().get(i).getCoordonnees().getY() > 0) {
				p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i + 9), "Nord-Ouest");		
			}
			if(p1.getListeCases().get(i).getCoordonnees().getX() < 9 && p1.getListeCases().get(i).getCoordonnees().getY() < 9) {
				p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i + 11), "Nord-Est");		
			}
			
		}
		

		//j1.getListePieces().get(19).bouge(p1.getListeCases().get(48));
		
		//System.out.println(j1.getListePieces().get(0).getPosition().toString());
		

	}

}
