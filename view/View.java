package view;

import java.util.Observer;

import controller.Controller;
import model.joueur.Joueur;

public abstract class View implements Observer{

	public Joueur model;

	protected Controller controller;
	
	protected View(Joueur model, Controller controller) {
		this.model = model;
		this.controller = controller;

		model.addObserver(this);
	}

	public abstract void affiche(String string);

	public Joueur getModel() {
		return model;
	}

	public void setModel(Joueur model) {
		this.model = model;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	
	
}
