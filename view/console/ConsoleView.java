package view.console;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

import controller.Controller;
import model.joueur.Joueur;
import view.View;

public class ConsoleView extends View {
	protected Scanner sc;
	
	public ConsoleView(Joueur model, Controller controller) {
		super(model, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();	
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	private class ReadInput implements Runnable{
		public void run() {
			while(true){
				try{
					String c = sc.next();
					
					switch(c){
						case "" : 
							//Méthode controller;
							break;
					}
					
						
				}
				catch(InputMismatchException e){
					affiche("");
		
	}}}}
				
	@Override
	public void affiche(String string) {
		System.out.println(string);
		
	}
	
}
	
