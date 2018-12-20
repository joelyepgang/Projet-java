package sockets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static ServerScanner scanner;
	public static Thread scan;
	public static boolean lancement;



	public static ServerScanner getScanner() {
		return scanner;
	}



	public static void setScanner(ServerScanner scanner) {
		Server.scanner = scanner;
	}


	/**
	 * Lancement du serveur
	 * <br>
	 * Ex�cut� au lancement de Server.java
	 * 
	 * @param args Arguments
	 */
	public static void main(String[] args){
		
		
		//Socket du serveur
		ServerSocket socket;
		
		try {
		//Cr�ation du socket serveur
		socket = new ServerSocket(4123);
		
		//Cr�ation d'un nouveau Thread visant � collecter les informations
		//de connection
		Thread t = new Thread(new Connexion(socket));
		t.start(); //Lancement
		
		scanner = new ServerScanner(socket); //Le scanner affili� au serveur
		scan = new Thread(scanner); //Le Thread affili� au scanneur et lanc�
		
		Server.setScanner(scanner); //Pas oublier de dire au serveur quel est son scanneur
		
		System.out.println("Serveur connect�"); 
		System.out.println("Scanner op�rationnel; attente de requ�tes");
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}

	
	class Connexion implements Runnable {

		   private ServerSocket socketserver;
		   private Socket socket;
		   private int numJoueur = 1;
		   	@SuppressWarnings("unused")
		   	private BufferedReader in = null;
		   private PrintWriter out = null;
		   
		   /**
		    * Constructeur du module de connection
		    * <br>
		    * G�re le trafic entrant lors de la connexion uniquement
		    * 
		    * @param s
		    */
			public Connexion(ServerSocket s){
				socketserver = s;
			}
			
			/**
			 * M�thode Serveur
			 * <br>
			 * Thread s'�x�cutant lorsque un client se connecte
			 * Il fait aussi en sorte de dire � ce client quelle place le serveur lui a donn�
			 * 
			 */
			public void run() {

		        try {
		        	//Boucle
		        	while(true){
		        		
		        		//Acceptation d'un client
						socket = socketserver.accept(); 
					    System.out.println("Un joueur s'est connect�, envoi des informations relatives � l'initialisation");
					        
					    //Out: Trafic sortant
					    //In: Trafic entrant
						out = new PrintWriter(socket.getOutputStream());
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						
						//Execution de la m�thode d'initialisation
						this.initialisation(numJoueur);
				        
						//! Pas oublier d'ajouter ce client dans la liste des clients
						//g�r�s par le scanneur du serveur
				        Server.getScanner().getSocket().add(socket);
				        
				        //Une fois qu'il y'a deux joueurs connect�s
				        if(numJoueur == 2) {
				        	
				        	//Lancement du scanneur
				        	Server.scan.start();
				        	System.out.println("Scanner serveur lanc�");
				        }
				        
						numJoueur++; //Incr�mentation du nombre de joueurs connect�s				            
		        	}
		        
		        } catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			/**
			 * Initialisation du jeu grace au serveur
			 * <br>
			 * But: Dire au premier client que c'est son tour et au deuxi�me que c'est au tour du premier.
			 * 
			 */
			public void initialisation(int nombre) {
				
				if(nombre == 1) {
					out.println("No");
					out.println("Vous �tes assign� au joueur 1");
					out.println("0");
					out.println("No");
					out.println("0");
					
					out.flush();
				}
				if(nombre == 2) {
					out.println("No");
					out.println("Vous �tes assign� au joueur 2");
					out.println("0");
					out.println("No");
					out.println("0");
					
					out.flush();
				}
				
			}

		}

	

