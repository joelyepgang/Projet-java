package model.plateau;


public class Case {
	private Coordonnees coordonnees;
	private Piece occupeePar;
	
	
	private int longueur = 2;
	private int largeur = 2;
	
	
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
	
	
}
