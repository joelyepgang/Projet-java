package model.plateau;

import model.coordonees.Coordonnees;
import model.piece.Piece;

public class Case {
	private Coordonnees coordonnees;
	private Piece occupeePar;
	private boolean occupee;
	
	
	private int longueur = 2;
	private int largeur = 2;
	
	private Case caseNord;
	private Case caseSud;
	private Case caseEst;
	private Case caseOuest;
	
	private Case caseNordEst;
	private Case caseNordOuest;
	private Case caseSudEst;
	private Case caseSudOuest;
	
	
	/**
	 * 
	 * Méthode d'initialisation
	 * <hr>
	 * Permet de créer toutes les cases adjacentes aux autres & permet
	 * également d'utiliser les vecteurs de mouvements
	 * 
	 * @param cible
	 * @param orientation
	 */
	public void construitCaseAdjacentes(Case cible, String orientation) {
		
		
		switch(orientation){
			case "Nord":
				this.setCaseNord(cible);
				break;
			case "Sud":
				this.setCaseSud(cible);
				break;
			case "Est":
				this.setCaseEst(cible);
				break;
			case "Ouest":
				this.setCaseOuest(cible);
				break;
			case "Nord-Ouest":
				this.setCaseNordOuest(cible);
				break;
			case "Nord-Est":
				this.setCaseNordEst(cible);
				break;
			case "Sud-Ouest":
				this.setCaseSudOuest(cible);
				break;
			case "Sud-Est":
				this.setCaseSudEst(cible);
				break;
		}
		
		
		
	}
	
	
	public String toString() {
		String s = "";
		
		s+="Case ("+this.getCoordonnees().getX()+", "+this.getCoordonnees().getY()+")";
		
		//if()
		
		
		 return s;
	}
	
	public Case(Coordonnees coo) {
		this.coordonnees = coo;
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public Piece getOccupeePar() {
		return occupeePar;
	}

	public void setOccupeePar(Piece occupeePar) {
		this.occupeePar = occupeePar;
	}

	public Case getCaseNord() {
		return caseNord;
	}

	public void setCaseNord(Case caseNord) {
		this.caseNord = caseNord;
	}

	public Case getCaseSud() {
		return caseSud;
	}

	public void setCaseSud(Case caseSud) {
		this.caseSud = caseSud;
	}

	public Case getCaseEst() {
		return caseEst;
	}

	public void setCaseEst(Case caseEst) {
		this.caseEst = caseEst;
	}

	public Case getCaseOuest() {
		return caseOuest;
	}

	public void setCaseOuest(Case caseOuest) {
		this.caseOuest = caseOuest;
	}

	public Case getCaseNordEst() {
		return caseNordEst;
	}

	public void setCaseNordEst(Case caseNordEst) {
		this.caseNordEst = caseNordEst;
	}

	public Case getCaseNordOuest() {
		return caseNordOuest;
	}

	public void setCaseNordOuest(Case caseNordOuest) {
		this.caseNordOuest = caseNordOuest;
	}

	public Case getCaseSudEst() {
		return caseSudEst;
	}

	public void setCaseSudEst(Case caseSudEst) {
		this.caseSudEst = caseSudEst;
	}

	public Case getCaseSudOuest() {
		return caseSudOuest;
	}

	public void setCaseSudOuest(Case caseSudOuest) {
		this.caseSudOuest = caseSudOuest;
	}


	public boolean isOccupee() {
		return occupee;
	}


	public void setOccupee(boolean occupee) {
		this.occupee = occupee;
	}
	
	
}
