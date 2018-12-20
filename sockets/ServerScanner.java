package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerScanner implements Runnable{

	private ServerSocket socketserver;
	private ArrayList<Socket> socket = new ArrayList<Socket>();
	private BufferedReader in = null;
    private PrintWriter out = null;
	   
    
    /**
     * Constructeur du scanneur serveur
     * 
     * @param s Socket du serveur
     */
    public ServerScanner(ServerSocket s){
		socketserver = s;
	}
    
    /**
     * Thread �x�cut� par le scanner serveur
     * <br>
     * Il sert � r�cup�rer tout le trafic entrant
     * 
     */
	@Override
	public void run() {
		 try {
			 	//Boucle obligatoire se r�p�tant 5 fois par seconde
	        	while(true){
	        		
	        		//Le j sert � renvoyer les informations � l'autre socket
				    int j = 1;
	        		for(int i = 0; i < socket.size(); i++) {
	        			
	        			//Out: trafic sortant
	        			//In: trafic entrant
	        			out = new PrintWriter(socket.get(i).getOutputStream());
						in = new BufferedReader(new InputStreamReader(socket.get(j - i).getInputStream()));
						
		        		
						/*
						 * Id - Id du scanner exp�diteur
						 * Event - Nom de l'�v�nement
						 * Argument -  nombre argument, sert � savoir quelle pi�ce doit bouger
						 * Orientation - Orientation du mouvement
						 * 
						 * Structure de l'envoi de donn�es
						 */
			    		String id = in.readLine();
			        	String event = in.readLine();
			        	
				    	System.out.println("-----------[RECEP]----------");
						System.out.println("Client:"+ id);
						System.out.println("Header:"+ event);
						
			        	int argument = Integer.parseInt(in.readLine());
			        	String orientation = in.readLine();
			        	int distance = Integer.parseInt(in.readLine());

			        	out.flush();
			        	
				    	System.out.println("-----------[RECEP]----------");
						System.out.println("Client:"+ id);
						System.out.println("Header:"+ event);
						System.out.println("args:"+ argument);
						System.out.println("Orientation:"+ orientation);
						System.out.println("Distance:"+ distance);
				    	System.out.println("-----------[RECEP]----------");
			        	
				    	//Envoi des donn�es au serveur
			        	this.envoi(id, event, argument, orientation, distance);
	        		}
	        		
	        		//Mise en veille pendant 0,2 secondes
	        		Thread.sleep(200);
	        	}

	        
	        } catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	/**
	 * M�thode serveur
	 * <br>
	 * Sert � envoyer sur le trafic les informations r�cup�r�es depuis le Thread g�rant
	 * le trafic entrant
	 * 
	 * @param event
	 * @param args
	 * @param orientation
	 */
	public void envoi(String id, String event, int args, String orientation, int distance) {
		if(event.equalsIgnoreCase("placement")) {
			System.out.println("Tentative d'envoi d'un ordre de d�placement");
			
			out.println(id);
			out.println("placement");
			out.println(args);
			out.println(orientation);	
			out.println(distance);
			out.flush();
			
	    	System.out.println("-----------[ENVOI]----------");
			System.out.println("Client:"+ id);
			System.out.println("Header:"+ event);
			System.out.println("args:"+ args);
			System.out.println("Orientation:"+ orientation);
			System.out.println("Distance:"+ distance);
	    	System.out.println("-----------[ENVOI]----------");
	    	
		}
	}

	public ServerSocket getSocketserver() {
		return socketserver;
	}

	public void setSocketserver(ServerSocket socketserver) {
		this.socketserver = socketserver;
	}

	public ArrayList<Socket> getSocket() {
		return socket;
	}

	public void setSocket(ArrayList<Socket> socket) {
		this.socket = socket;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

}
