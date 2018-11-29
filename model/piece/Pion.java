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
	 * <b> Méthode interne </b>
	 * <hr>
	 * <i> Elle faut bouger la pièce en suivant l'orientation
	 * 
	 * 
	 * @param orientation Vecteur de mouvement
	 */
	@Override
	public void bougeInterne(String orientation) {
		/*
		 * Il doit également vérifier que la case ciblée n'est pas occupée par une pièce alliée.
		 * 
		 */
			
		//Récupère la case qui se trouve à l'orientation spécifiée.
		
		//Annulation d'un déplacement en ligne droite
		if(orientation.equals("Nord") || orientation.equals("Sud") || orientation.equals("Est") || orientation.equals("Ouest")) {
			return;
		}
		System.out.println("");
		
		Case cible = Pion.traduireStringVersCaseOrientation(this.getPosition(), orientation);
		System.out.println("Coordonnées actuelles >"+this.getPosition().getCoordonnees().toString());
		
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
					
					System.out.println("("+this.getProprietaire().getNomEntier()+") | Pièce déplacée aux coordonnées "+this.getPosition().getCoordonnees().toString());
					
					Main.partie.passe();
				}
				
				else if(cible.getOccupeePar().getCouleur().equals(this.getCouleur())) {
					System.out.println("Case occupée par une pièce alliée");
					return;
				}
				
				else if(!cible.getOccupeePar().getCouleur().equals(this.getCouleur())) {
					System.out.println("Case occupée par une pièce ennemie");
					System.out.println("Tentative de destruction");
					
					if(Pion.traduireStringVersCaseOrientation(cible, orientation).isOccupee() == true) {
						return;
					}
					else {
						System.out.println("La pièce a été détruite");
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
						System.out.println("("+this.getProprietaire().getNomEntier()+") | Pièce déplacée aux coordonnées "+this.getPosition().getCoordonnees().toString());
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
	 * Méthode qui fait bouger la pièce vers une case.
	 *<hr>
	 * @param orientation vecteur de mouvement
	 */
	@Override
	public void bouge(String orientation) {
		
			this.bougeInterne(orientation);

		
		
	}
	
	/**
	 * 
	 * Méthode utilisateur
	 * <hr>
	 * Permet de traduire un vecteur de mouvement en une case précise
	 * 
	 * @param caseSource case source où appliquer le déplacement
	 * @param orientation Vecteur de mouvement
	 * @return La case ciblée par le mouvement 
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
