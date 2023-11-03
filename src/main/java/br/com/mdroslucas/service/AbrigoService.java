package br.com.mdroslucas.service;

import br.com.mdroslucas.infra.ClientHttpConfiguration;
import br.com.mdroslucas.model.Abrigo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class AbrigoService {
    private ClientHttpConfiguration client;

    public AbrigoService(ClientHttpConfiguration client){
        this.client = client;
    }

    public void listaAbrigosCadastrados() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/abrigos";

        HttpResponse<String> response = client.disparaRequisicaoGet(uri);
        String responseBody = response.body();
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
        System.out.println("Abrigos cadastrados:");
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            long id = jsonObject.get("id").getAsLong();
            String nome = jsonObject.get("nome").getAsString();
            System.out.println(id +" - " +nome);
        }
    }


    public void cadastrarAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o nome do abrigo:");
        String nome = new Scanner(System.in).nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();

        var abrigo = new Abrigo(nome, telefone, email);

        String uri = "http://localhost:8080/abrigos";

        HttpResponse<String> response = client.disparaRequisicoesPost(uri, abrigo);

        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }
}
