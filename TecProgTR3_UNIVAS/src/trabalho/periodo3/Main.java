package trabalho.periodo3;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	static Scanner input = new Scanner(System.in);
	static ArrayList<Players> playersList = new ArrayList<Players>();
	static ArrayList<Titles> titlesList = new ArrayList<Titles>();
	
	public static void main(String[] args) {
		
	    int option;
	        
	     do {
	    	 System.out.print("-= CINEM�O DAHORA =-\n"
	    			+ "1 - Cadastro de Player\n"
	    	 		+ "2 - Cadastro de T�tulo\n"
	    	 		+ "3 - Recomenda��o\n"
	    	 		+ "4 - Sair\n"
	    	 		+ "> ");
	        	 
	        option = input.nextInt();
	        
	        switch (option) {
	        case 1:
	        	System.out.println(addPlayer());
	        	break;
	        case 2:
	        	System.out.println(addTitle());
	        	break;
	        case 3:
	        	System.out.println(recommendTitle());
	        	break;
	        case 4:
	        	System.out.println("Programa finalizado!");
	        	System.exit(0);
	        default:
	        	System.out.println("Op��o inv�lida! Tente novamente:\n");
	        	}
	     } while (true);
	}

	static String recommendTitle() {
		
		if(playersList.isEmpty()) return "Cadastre ao menos um t�tulo!\n";
		
		do {
			System.out.print("Escolha uma op��o:\n"
					+ "1 - Recomenda��o por g�nero\n"
					+ "2 - Recomenda��o aleat�ria\n"
					+ "> ");
			int option = input.nextInt();
			
			switch (option) {
			case 1:
				System.out.println("De acordo com o(s) g�nero(s) cadastrado(s), escolha um:");
				Titles title;
				for(int i = 0; i < playersList.size(); i++) {
					title = titlesList.get(i);
					//tor�a pro usu�rio n�o ter cadastrado o mesmo g�nero mais de 1 vez
					System.out.println(i + " - " + title.getGenre());
				}
				int genreOption = input.nextInt();
				title = titlesList.get(genreOption);
				String genre = title.getGenre();
				
				for(Titles t : titlesList) {
					if(t.getGenre().equals(genre)) {
						System.out.println(t.toString());
					}
				}
				break;
			case 2:
				Random randomize = new Random();
				//int fixRandomize = titlesList.size() + 1;
				//int n = randomize.nextInt(fixRandomize);
				int n = randomize.nextInt(titlesList.size());
				Titles t = titlesList.get(n);
				System.out.println(t.toString());
				break;
			default:
				System.out.println("Op��o inv�lida!");
			}
		} while(true);
		
	}

	static String addPlayer() {
		
		Players player = new Players();
    	
    	System.out.println("Digite o nome da plataforma de streaming:");
    	player.setName(input.next());
    	
    	for(Players p : playersList) {
    		
    		if(player.getName().equalsIgnoreCase(p.getName())) {
    			return "Esse player j� est� cadastrado!\n";
    		}
    		
    	}
    	
    	System.out.println("Digite o site da plataforma de streaming:");
    	player.setSite(input.next());
    	
    	playersList.add(player);
    	
    	return "Player cadastrado!\n";
		
	}
	
	static String addTitle() {
		
		if(playersList.isEmpty()) return "Cadastre ao menos um player!\n";
		
		Titles title = new Titles();
    	
		// TIPO
		do {
	    	System.out.printf("Deseja adicionar uma s�rie ou um filme?\n"
	    			+ "1 - S�rie\n"
	    			+ "2 - Filme\n"
	    			+ "> ");
	    	int option = input.nextInt();
	    	
	    	if(option == 1) {
	    		// T�TULO
	    		System.out.println("Digite o nome da s�rie:");
	    		title.setTitle(input.next());
	    		break;
	    	} else if(option == 2) {
	    		System.out.println("Digite o nome do filme:");
	    		title.setTitle(input.next());
	    		break;
	    	} else {
	    		System.out.println("Op��o inv�lida!");
	    	}
		} while(true);

		// ELENCO
		do {
			System.out.println("Digite o(s) nome(s) do(s) ator(es) para adicion�-lo(s) ao elenco ou 0 para terminar de adicionar:");
			String name = input.next();
			
			if(title.checkNames(name)) {
				System.out.println("Este(a) ator(riz) j� est� no elenco!");
			} else {
				if(name.equalsIgnoreCase("0")) {
					System.out.println("Elenco cadastrado ao t�tulo " + title.getTitle() + "\n");
					break;
				} else {
					title.addCast(name);
				}
			}

		} while(true);
		
		// DESCRI��O
		System.out.println("Adicione uma descri��o para " + title.getTitle());
		title.setDescription(input.next());
		
		// G�NERO
		System.out.println("Digite o g�nero:");
		title.setGenre(input.next());
		
		// DIRETOR
		System.out.println("Digite o nome do diretor:");
		title.setDirector(input.next());
		
		// CLASSIFICA��O ET�RIA
		System.out.println("Digite a classifica��o indicativa:");
		title.setParentalRating(input.nextInt());
		
		// PLATAFORMA
		ArrayList<Players> addedPlataforms = new ArrayList<Players>();
		do {
			System.out.println("De acordo com o(s) player(s) cadastrado(s), escolha o(s) que possui(em) este t�tulo dispon�vel:");
			for(int i = 0; i < playersList.size(); i++) {
				System.out.println(i + " - " + playersList.get(i));
			}
			System.out.println(playersList.size() + " - Terminar de adicionar");
			
			int option = input.nextInt();
			
			if(option > playersList.size() || option < 0) {
				System.out.println("Op��o inv�lida!\n");
			} else {
				if(option == playersList.size()) {
					if(addedPlataforms.isEmpty()) {
						System.out.println("Adicione ao menos um player ao t�tulo!\n");
					} else {
						System.out.println("Players cadastrados ao t�tulo!\n");
						break;
					}
				} else {
					Players plataform = new Players();
					plataform = playersList.get(option);
					addedPlataforms.add(plataform);
					title.setPlataforms(addedPlataforms);
					System.out.println(title.getTitle() + " pode ser encontrado em " + plataform.getName() + "\n");
				}
			}
		} while(true);

    	titlesList.add(title);
    	return "T�tulo cadastrado!\n";
	}
}
