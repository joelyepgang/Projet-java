package model.joueur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.piece.Pion;

class JoueurTest {

	@Test
	void test() {
		Joueur j1 = new Joueur("Joueur", "1");
		Joueur j2 = new Joueur("Joueur", "2");
		
		for(int i = 0; i < 24; i++) {
			j1.getListePieces().add(new Pion());
			j2.getListePieces().add(new Pion());
		}
		
		j1.prendPiece(j2, 3); //Le joueur 1 prend une pièce au joueur 2
		
		assertEquals(1 ,j1.getListePiecesPrises().size()); //Une pièce dans les pièces prises du joueur 1
		assertEquals(23 ,j2.getListePieces().size()); //Une pièce en moins dans la liste du joueur 2

	}

}
