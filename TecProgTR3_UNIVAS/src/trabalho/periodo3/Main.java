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
	    	 System.out.print("-= CINEMÃO DAHORA =-\n"
	    			+ "1 - Cadastro de Player\n"
	    	 		+ "2 - Cadastro de Título\n"
	    	 		+ "3 - Recomendação\n"
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
	        	System.out.println("Opção inválida! Tente novamente:\n");
	        	}
	     } while (true);
	}

	static String recommendTitle() {
		
		if(playersList.isEmpty()) return "Cadastre ao menos um título!\n";
		
		do {
			System.out.print("Escolha uma opção:\n"
					+ "1 - Recomendação por gênero\n"
					+ "2 - Recomendação aleatória\n"
					+ "> ");
			int option = input.nextInt();
			
			switch (option) {
			case 1:
				System.out.println("De acordo com o(s) gênero(s) cadastrado(s), escolha um:");
				Titles title;
				for(int i = 0; i < playersList.size(); i++) {
					title = titlesList.get(i);
					//torça pro usuário não ter cadastrado o mesmo gênero mais de 1 vez
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
				System.out.println("Opção inválida!");
			}
		} while(true);
		
	}

	static String addPlayer() {
		
		Players player = new Players();
    	
    	System.out.println("Digite o nome da plataforma de streaming:");
    	player.setName(input.next());
    	
    	for(Players p : playersList) {
    		
    		if(player.getName().equalsIgnoreCase(p.getName())) {
    			return "Esse player já está cadastrado!\n";
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
	    	System.out.printf("Deseja adicionar uma série ou um filme?\n"
	    			+ "1 - Série\n"
	    			+ "2 - Filme\n"
	    			+ "> ");
	    	int option = input.nextInt();
	    	
	    	if(option == 1) {
	    		// TÍTULO
	    		System.out.println("Digite o nome da série:");
	    		title.setTitle(input.next());
	    		break;
	    	} else if(option == 2) {
	    		System.out.println("Digite o nome do filme:");
	    		title.setTitle(input.next());
	    		break;
	    	} else {
	    		System.out.println("Opção inválida!");
	    	}
		} while(true);

		// ELENCO
		do {
			System.out.println("Digite o(s) nome(s) do(s) ator(es) para adicioná-lo(s) ao elenco ou 0 para terminar de adicionar:");
			String name = input.next();
			
			if(title.checkNames(name)) {
				System.out.println("Este(a) ator(riz) já está no elenco!");
			} else {
				if(name.equalsIgnoreCase("0")) {
					System.out.println("Elenco cadastrado ao título " + title.getTitle() + "\n");
					break;
				} else {
					title.addCast(name);
				}
			}

		} while(true);
		
		// DESCRIÇÃO
		System.out.println("Adicione uma descrição para " + title.getTitle());
		title.setDescription(input.next());
		
		// GÊNERO
		System.out.println("Digite o gênero:");
		title.setGenre(input.next());
		
		// DIRETOR
		System.out.println("Digite o nome do diretor:");
		title.setDirector(input.next());
		
		// CLASSIFICAÇÃO ETÁRIA
		System.out.println("Digite a classificação indicativa:");
		title.setParentalRating(input.nextInt());
		
		// PLATAFORMA
		ArrayList<Players> addedPlataforms = new ArrayList<Players>();
		do {
			System.out.println("De acordo com o(s) player(s) cadastrado(s), escolha o(s) que possui(em) este título disponível:");
			for(int i = 0; i < playersList.size(); i++) {
				System.out.println(i + " - " + playersList.get(i));
			}
			System.out.println(playersList.size() + " - Terminar de adicionar");
			
			int option = input.nextInt();
			
			if(option > playersList.size() || option < 0) {
				System.out.println("Opção inválida!\n");
			} else {
				if(option == playersList.size()) {
					if(addedPlataforms.isEmpty()) {
						System.out.println("Adicione ao menos um player ao título!\n");
					} else {
						System.out.println("Players cadastrados ao título!\n");
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
    	return "Título cadastrado!\n";
	}
}
