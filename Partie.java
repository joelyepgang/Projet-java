package model.partie;

import model.joueur.Joueur;

public class Partie {
	private int id;
	private Joueur j1;
	private Joueur j2;
	
	/**<hr>
	 * Constructeur de la partie
	 * <b><br>(� ne pas utiliser)</b>
	 * <hr>
	 * @param id > Num�ro de la partie en cours (Pour l'instant inutile)
	 * @param j1 > 
	 * @param j2
	 */
	@Deprecated
	public Partie(int id, Joueur j1, Joueur j2) {
		this.id = id;
		this.j1 = j1;
		this.j2 = j2;
	}
	
	/**<hr>
	 * Constructeur de la partie
	 * <b><br>(� utiliser)</b>
	 * <hr>
	 * 
	 * @param j1 > Joueur 1
	 * @param j2 > Joueur 2
	 */
	public Partie(Joueur j1, Joueur j2) {
		this.j1 = j1;
		this.j2 = j2;
	}
	
	
	/**
	 * Determine quel joueur poss�de le score le plus �lev�
	 * 
	 * @return Pr�nom du joueur gagnant
	 */
	public String determineGagnant() {
		String s = "";
		
		if(j1.getScore() > j2.getScore()) {
			s = j1.getPrenom();
		}
		else if(j2.getScore() > j1.getScore()) {
			s = j2.getPrenom();
		}
		else {
			s = "Egalit�";
		}
		
		return s;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Joueur getJ1() {
		return j1;
	}

	public void setJ1(Joueur j1) {
		this.j1 = j1;
	}

	public Joueur getJ2() {
		return j2;
	}

	public void setJ2(Joueur j2) {
		this.j2 = j2;
	}
	
	
}
