import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

/*
Projeto Imersão Alura
 */

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
        System.out.println(ANSI_ROXA+"\u2735\u2735\u2735\u2735\u2735\u2735\u2735" + ANSI_RESET
                                + ANSI_YELLOW+"IMERSÃO "+ ANSI_RESET +ANSI_AZUL+"JAVA"+ANSI_RESET+ ANSI_VERDE+" ALURA"+ANSI_RESET
                          +ANSI_ROXA+"\u2735\u2735\u2735\u2735\u2735\u2735\u2735" +ANSI_RESET);
        for (Map<String, String> mapFilmes : listaDeFilmes) {
            String numero = mapFilmes.get("imDbRating");
            System.out.println("\n" + ANSI_VERDE+"Rank: " +ANSI_RESET+ mapFilmes.get("rank")
                    + ",\n" + ANSI_AZUL +" Título: "+ANSI_RESET+ mapFilmes.get("title")
                    + ",\n" + ANSI_ROXA +" Ano Filme: " +ANSI_RESET+  mapFilmes.get("year")
                    + ",\n" + ANSI_CIANO +" Classificação: " +ANSI_RESET+ mapFilmes.get("imDbRating") +" "+ANSI_YELLOW +convString(numero)+ANSI_RESET
                    + " \n" + ANSI_VERMELHO +" Poster Filme: " +ANSI_RESET+  mapFilmes.get("image")


            );
        }
        System.out.println("\n"+ANSI_RED_BACKGROUND+"FIM...."+ANSI_RESET);
    }
    /*
    Cores Background
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND= "\u001B[43m";
    public static final String ANSI_CIANO_BACKGROUND= "\u001B[46m";

    /*
  Cores linecolor
   */
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_VERMELHO = "\u001B[31m";
    public static final String ANSI_VERDE = "\u001B[32m";
    public static final String ANSI_AZUL = "\u001B[34m";
    public static final String ANSI_ROXA = "\u001B[35m";
    public static final String ANSI_CIANO = "\u001B[36m";



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




