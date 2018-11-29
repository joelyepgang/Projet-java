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
	 * <b> M�thode interne </b>
	 * <hr>
	 * <i> Elle faut bouger la pi�ce en suivant les conditions du param�tre x.
	 * <br>X = 1 pour les blancs parce-qu'ils sont en bas du plateau et ne peuvent que monter.
	 * <br>X = -1 pour les noirs parce-qu'ils sont en haut du plateau et ne peuvent que descendre.
	 * 
	 * 
	 * @param cible case cibl�e
	 * @param x condition en x
	 */
	private void bougeInterne(Case cible, int x) {
		/* Avant de faire d�placer une pi�ce, le jeu doit v�rifier si la case est atteignable. Elle doit obligatoirement se 
		 * situer � X + 1 et Y + 1 OU -1. 
		 * Il doit �galement v�rifier que la case cibl�e n'est pas occup�e par une pi�ce alli�e.
		 * 
		 */
		if(cible.getCoordonnees().getX() == this.getPosition().getCoordonnees().getX() + x
				&& (cible.getCoordonnees().getY() == this.getPosition().getCoordonnees().getY() + 1 
						|| cible.getCoordonnees().getY() == this.getPosition().getCoordonnees().getY() - 1)) {
			
			
			//Eviter le pointeur null lorsque la case est vide
			try {
				if(cible.getOccupeePar().getCouleur().equals(this.getCouleur())) {
					System.out.println("Case occup�e par une pi�ce appartenant � "+cible.getOccupeePar().getProprietaire().getNomEntier());
					return;
				}
			}
			catch(NullPointerException e) {
				
			}
			
			
			//Changement de position
			this.setPosition(cible);
			System.out.println("Pi�ce d�plac�e en coordonn�es "+this.getPosition().getCoordonnees().toString());
			
			
		}
		else {
			System.out.println("Case inatteignable ...");
			return;
		}
	}
	
	/**
	 * M�thode qui fait bouger la pi�ce vers une case.
	 *<hr>
	 * @param cible case cibl�e
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
