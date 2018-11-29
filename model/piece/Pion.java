package model.piece;

import java.awt.Color;

import model.core.Main;
import model.joueur.Joueur;
import model.plateau.Case;

public class Pion extends Piece{

	public Pion(Joueur proprietaire, Color couleur, Case position) {
		super(proprietaire, couleur, position);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * <b> M�thode interne </b>
	 * <hr>
	 * <i> Elle faut bouger la pi�ce en suivant l'orientation
	 * 
	 * 
	 * @param orientation Vecteur de mouvement
	 */
	@Override
	public void bougeInterne(String orientation) {
		/*
		 * Il doit �galement v�rifier que la case cibl�e n'est pas occup�e par une pi�ce alli�e.
		 * 
		 */
			
		//R�cup�re la case qui se trouve � l'orientation sp�cifi�e.
		
		//Annulation d'un d�placement en ligne droite
		if(orientation.equals("Nord") || orientation.equals("Sud") || orientation.equals("Est") || orientation.equals("Ouest")) {
			return;
		}
		System.out.println("");
		
		Case cible = Pion.traduireStringVersCaseOrientation(this.getPosition(), orientation);
		System.out.println("Coordonn�es actuelles >"+this.getPosition().getCoordonnees().toString());
		
		try {
			cible.equals(null);
		}
		catch(NullPointerException e) {
			System.out.println("Aucune case au "+orientation+" de notre position actuelle");
			return;
		}
			
			//Eviter le pointeur null lorsque la case est vide
			try {
				
				if(cible.isOccupee() == false) {
					//Changement de position
					this.getPosition().setOccupee(false);
					this.getPosition().setOccupeePar(null);
					this.setPosition(cible);
					
					cible.setOccupee(true);
					cible.setOccupeePar(this);
					
					System.out.println("("+this.getProprietaire().getNomEntier()+") | Pi�ce d�plac�e aux coordonn�es "+this.getPosition().getCoordonnees().toString());
					
					Main.partie.passe();
				}
				
				else if(cible.getOccupeePar().getCouleur().equals(this.getCouleur())) {
					System.out.println("Case occup�e par une pi�ce alli�e");
					return;
				}
				
				else if(!cible.getOccupeePar().getCouleur().equals(this.getCouleur())) {
					System.out.println("Case occup�e par une pi�ce ennemie");
					System.out.println("Tentative de destruction");
					
					if(Pion.traduireStringVersCaseOrientation(cible, orientation).isOccupee() == true) {
						return;
					}
					else {
						System.out.println("La pi�ce a �t� d�truite");
						cible.getOccupeePar().getProprietaire().getListePieces().remove(cible.getOccupeePar());
						this.getProprietaire().getListePiecesPrises().add(cible.getOccupeePar());
						
						cible.getOccupeePar().setPosition(null);
						cible.setOccupee(false);
						cible.setOccupeePar(null);
						
						
						//Changement de position
						this.getPosition().setOccupee(false);
						this.getPosition().setOccupeePar(null);					
			
						this.setPosition(Pion.traduireStringVersCaseOrientation(cible, orientation));
						
						this.getPosition().setOccupee(true);
						this.getPosition().setOccupeePar(this);
						System.out.println("("+this.getProprietaire().getNomEntier()+") | Pi�ce d�plac�e aux coordonn�es "+this.getPosition().getCoordonnees().toString());
						System.out.println("");
						
						Main.partie.passe();
						
					}
					
					return;
				}
				
				
			}
			catch(NullPointerException e) {
				System.out.println("Ordre de mouvement impossible");
			}
			
			
			
			
		
	}
	
	/**
	 * M�thode qui fait bouger la pi�ce vers une case.
	 *<hr>
	 * @param orientation vecteur de mouvement
	 */
	@Override
	public void bouge(String orientation) {
		
			this.bougeInterne(orientation);

		
		
	}
	
	/**
	 * 
	 * M�thode utilisateur
	 * <hr>
	 * Permet de traduire un vecteur de mouvement en une case pr�cise
	 * 
	 * @param caseSource case source o� appliquer le d�placement
	 * @param orientation Vecteur de mouvement
	 * @return La case cibl�e par le mouvement 
	 */
	public static Case traduireStringVersCaseOrientation(Case caseSource, String orientation) {
		Case c = null;
		
		switch(orientation){
		case "Nord":
			c = caseSource.getCaseNord();
			break;
		case "Sud":
			c = caseSource.getCaseSud();
			break;
		case "Est":
			c = caseSource.getCaseEst();
			break;
		case "Ouest":
			c = caseSource.getCaseOuest();
			break;
		case "Nord-Ouest":
			c = caseSource.getCaseNordOuest();
			break;
		case "Nord-Est":
			c = caseSource.getCaseNordEst();
			break;
		case "Sud-Ouest":
			c = caseSource.getCaseSudOuest();
			break;
		case "Sud-Est":
			c = caseSource.getCaseSudEst();
			break;
	}
		
		return c;
	}
}
