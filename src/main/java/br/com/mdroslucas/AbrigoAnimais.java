package br.com.mdroslucas;

import br.com.mdroslucas.infra.ClientHttpConfiguration;
import br.com.mdroslucas.service.AbrigoService;
import br.com.mdroslucas.service.PetService;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class AbrigoAnimais {
    public static void main(String[] args) {
        ClientHttpConfiguration client = new ClientHttpConfiguration();
        var abrigoService = new AbrigoService(client);
        var petService = new PetService(client);
        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
                System.out.println("1 -> Listar abrigos cadastrados");
                System.out.println("2 -> Cadastrar novo abrigo");
                System.out.println("3 -> Listar pets do abrigo");
                System.out.println("4 -> Importar pets do abrigo");
                System.out.println("5 -> Sair");

                String textoDigitado = new Scanner(System.in).nextLine();
                opcaoEscolhida = Integer.parseInt(textoDigitado);

                if (opcaoEscolhida == 1) {
                    abrigoService.listaAbrigosCadastrados();
                } else if (opcaoEscolhida == 2) {
                    abrigoService.cadastrarAbrigo();
                } else if (opcaoEscolhida == 3) {
                    petService.listarPets();
                } else if (opcaoEscolhida == 4) {
                    petService.importarPetsDoAbrigo();
                } else if (opcaoEscolhida == 5) {

                } else {
                    System.out.println("NÚMERO INVÁLIDO!");
                    opcaoEscolhida = 0;
                }
            }
            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}