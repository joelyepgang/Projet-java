package model.piece;

import java.awt.Color;

import model.joueur.Joueur;
import model.plateau.Case;

public abstract class Piece {
	private Color couleur;
	private Joueur proprietaire;
	private Case position;
		
	
	public Piece(Joueur proprietaire, Color couleur, Case position) {
		this.couleur = couleur;
		this.position = position;
		this.proprietaire = proprietaire;
	}
	
	
	/**
	 * <b> Méthode interne </b>
	 * <hr>
	 * <i> Elle faut bouger la pièce en suivant les conditions du paramètre x.
	 * <br>X = 1 pour les blancs parce-qu'ils sont en bas du plateau et ne peuvent que monter.
	 * <br>X = -1 pour les noirs parce-qu'ils sont en haut du plateau et ne peuvent que descendre.
	 * 
	 * 
	 * @param cible case ciblée
	 * @param x condition en x
	 */
	private void bougeInterne(Case cible, int x) {
		/* Avant de faire déplacer une pièce, le jeu doit vérifier si la case est atteignable. Elle doit obligatoirement se 
		 * situer à X + 1 et Y + 1 OU -1. 
		 * Il doit également vérifier que la case ciblée n'est pas occupée par une pièce alliée.
		 * 
		 */
		if(cible.getCoordonnees().getX() == this.getPosition().getCoordonnees().getX() + x
				&& (cible.getCoordonnees().getY() == this.getPosition().getCoordonnees().getY() + 1 
						|| cible.getCoordonnees().getY() == this.getPosition().getCoordonnees().getY() - 1)) {
			
			
			//Eviter le pointeur null lorsque la case est vide
			try {
				if(cible.getOccupeePar().getCouleur().equals(this.getCouleur())) {
					System.out.println("Case occupée par une pièce appartenant à "+cible.getOccupeePar().getProprietaire().getNomEntier());
					return;
				}
			}
			catch(NullPointerException e) {
				
			}
			
			
			//Changement de position
			this.setPosition(cible);
			System.out.println("Pièce déplacée en coordonnées "+this.getPosition().getCoordonnees().toString());
			
			
		}
		else {
			System.out.println("Case inatteignable ...");
			return;
		}
	}
	
	/**
	 * Méthode qui fait bouger la pièce vers une case.
	 *<hr>
	 * @param cible case ciblée
	 */
	public void bouge(Case cible) {
		
		if(this.getCouleur().equals(Color.WHITE)) {
			this.bougeInterne(cible, 1);
		
		}
		else if(this.getCouleur().equals(Color.BLACK)) {
			this.bougeInterne(cible, -1);
		}
		
		
	}

	
	

	public Color getCouleur() {
		return couleur;
	}


	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}


	public Case getPosition() {
		return position;
	}


	public void setPosition(Case position) {
		this.position = position;
	};
	
	
	
	public Joueur getProprietaire() {
		return proprietaire;
	}




	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}




	public String toString() {
		String s = "";
		
		String couleur = "";
		if(this.getCouleur().equals(Color.BLACK)) {
			couleur = "Noir";
		}
		else {
			couleur = "Blanc";
		}
		
		s+="Couleur: ("+couleur+") "+this.getProprietaire().getNomEntier()+" > "+this.getPosition();
		
		return s;
	}
	
	
}
