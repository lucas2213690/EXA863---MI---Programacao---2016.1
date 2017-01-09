package br.uefs.stmserver.view;

import java.io.IOException;
import br.uefs.stmserver.controller.Servidor;

public class STMServer {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Servidor servidorController = new Servidor();
		servidorController.iniciaServidor();
	}

}
