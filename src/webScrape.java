import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class webScrape {
    public static void main(String[] args) {
        /*
            Sjekker top 100 Crypto
         */

        final String url = "https://coinmarketcap.com/";

        try {
            final Document doc = Jsoup.connect(url).get();

            for (Element row : doc.select("table.h7vnx2-2.czTsgW.cmc-table tr")) {
                // I noen tilfeller er den første raden tom, i tilfelle jeg skal bruke den på andre sider,
                // Legger jeg nå til en if setning som sjekker om den første er tom
                if (row.select("td:nth-of-type(3)").text().equals("")) {
                    continue;
                }
                else {
                    String name = row.select("td:nth-of-type(3)").text();
                    String tempPrice = row.select("td:nth-of-type(4)").text();

                    //Skal gjøre om String som består av tall til double, og fjerne komma og dollar tegn.
                    tempPrice = tempPrice.replace(",","");
                    tempPrice = tempPrice.replace("$","");
                    final double price = Double.parseDouble(tempPrice);

                    System.out.println("Navn: " + name  + "\n" +
                                        "Pris: " + price + "\n");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
