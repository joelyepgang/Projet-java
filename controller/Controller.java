package controller;


import model.joueur.Joueur;
import view.View;

public class Controller {
	Joueur model;  
	View vue;
	
	public Controller(Joueur model) {
		this.model = model;
	}
	
	public void addView(View vue) {
		this.vue = vue;
		
	}
	

}
