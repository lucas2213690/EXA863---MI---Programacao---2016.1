package br.uefs.stmclient.view;

import java.io.IOException;
//import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import br.uefs.stmclient.controller.Cliente;
import br.uefs.stmclient.util.Console;

public class STMClient {

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		
		int escolha = 0;
		
		Cliente clienteController = new Cliente();
		String msg = "";
		String to = "";
		String nick = "";
		String[] arrayString = null;
		
		
		while (true){
			
			Menu.exibir();
			
			escolha = Console.readInt();
			
			
			switch (escolha){
			
			case 1:
				String s = "";
				System.out.println("Digite o seu nick");
				
				nick = Console.readString();
				
				
				
				s = clienteController.login(nick);
				
				System.out.println(s);
				
				break;
			
			
			case 2:
				System.out.println("Digite o destinatário");
				to = Console.readString();
				System.out.println("Digite a mensagem");
				msg = Console.readString();
				
				s = clienteController.enviaMensagem(msg, to);
				System.out.println(s);
				
				break;
				
			case 3:
				arrayString = clienteController.recebeMensagens();
				for (String i: arrayString)
					System.out.println(i);
				
				break;
			case 4:
				System.out.println("Lista de usuarios atualizada");
				System.out.println("..................");
				arrayString = clienteController.atualizaUsuarios();
				for (String i: arrayString)
					System.out.println(i);
				
				break;
			
			case 5:
				arrayString = clienteController.recebeMensagens();
				if (arrayString != null){
				for (String i: arrayString)
					System.out.println(i);
				}
				TimeUnit.SECONDS.sleep(3);
				s = clienteController.logoff();
				System.out.println(s);
			}
		}

	}

}
