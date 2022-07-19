import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import static java.lang.Math.round;

public class App {

    public static void main(String[] args) throws Exception {

        /*
        Fazer uma conexão HTTP e buscar os tops 250 filmes
         */
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        /*
        Extrair apenas os dados desejados tais como: (título, poster, classificação)
         */
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
//        System.out.println(listaDeFilmes.size());
//        System.out.println(listaDeFilmes.get(0));

          /*
        Exibir e manipular os dados
         */
        for (Map<String, String> mapFilmes : listaDeFilmes) {
            String numero = mapFilmes.get("imDbRating");
            System.out.println("\n" + "Rank: " + mapFilmes.get("rank")
                    + ",\n Título: " + mapFilmes.get("title")
                    + ",\n Ano Filme: " + mapFilmes.get("year")
                    + ",\n Classificação: " + mapFilmes.get("imDbRating") +" "+ convString(numero)
                    + " \n Poster Filme: " + mapFilmes.get("image")
            );
        }
    }

    public static String convString(String numero) {
        String estrela = "\u272F";

        Double numeroConversao = Double.parseDouble(numero);
        Integer parseNumero = numeroConversao.intValue();
        for (int i = 1; i < parseNumero; i++) {
            estrela += "\u272D";
        }
        return estrela;
    }
}




