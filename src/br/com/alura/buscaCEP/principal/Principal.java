package br.com.alura.buscaCEP.principal;
import br.com.alura.buscaCEP.modelos.CepEndereco;
import br.com.alura.buscaCEP.modelos.CepEnderecoJSON;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Principal {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner ler = new Scanner(System.in);
        String endereco;
        String busca = "";

        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

        
        List<CepEndereco> lista = new ArrayList<>();

        while(!busca.equalsIgnoreCase("sair")) {
 
            System.out.println("Qual CEP deseja pesquisar? Digite apenas os numeros");
            System.out.println("Para sair digite sair");
            busca = ler.next();

            if(busca.equalsIgnoreCase("sair")) {
                break;
            }
            if(busca.length() != 8) {
                System.out.println("CEP invalido, tente novamente com um CEP valido");
            } else {
                try {
                    endereco =  "https://viacep.com.br/ws/" + busca + "/json";
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                    HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                    String json = response.body();
                    System.out.println(json);

                    CepEnderecoJSON meuCep = gson.fromJson(json, CepEnderecoJSON.class);

                    CepEndereco cepEndereco = new CepEndereco(meuCep);
                    System.out.println("Adicionado a lista!\n");

                    lista.add(cepEndereco);
                } catch (IllegalArgumentException e) {
                    System.out.println("Aconteceu um erro na busca, verifique se o CEP e valido");
                    System.out.println(e.getMessage());
                }
            }

        }

        FileWriter writer = new FileWriter("CEPs.json");
        writer.write(gson.toJson(lista));
        writer.close();
        ler.close();

        System.out.println("Arquivo salvo com sucesso!");
        System.out.println("Programa finalizado com sucesso!");
    }
}
