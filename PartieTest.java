package model.partie;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.joueur.Joueur;

class PartieTest {

	@Test
	void testGagnant() {
		Joueur j1 = new Joueur("Joueur", "1");
		Joueur j2 = new Joueur("Joueur", "2");
		Partie p1 = new Partie(j1, j2);
		
		j1.setScore(10);
		j2.setScore(1);
		
		assertEquals("1",p1.determineGagnant());
	}

}
