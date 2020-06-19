package googlesearch;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.Arrays;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class Search{
    private String search;
    private int num;
    Scanner scanner = new Scanner(System.in);
    public Search(String search){
        this.search = search;
        this.num = 10;
    }
    public Search(String search, int num){
        this.search = search;
        this.num = num;
    }
    public void search() throws IOException, URISyntaxException{
        
        //Catches errors.
        try {
        int count = 0;
        String[] linkArray = {};
        
        //Generates the URL which will be used to search google for results.
        String url = "https://www.google.com/search?q=" + search + "&num=" + num;
        
        //Connects to the URL generated above and extracts the HTML doc.
        Document doc = Jsoup.connect(url).get();
        
        //Prints out the title of the doc, in this case the search query.
        System.out.println(doc.title());
        
        //Scrapes the HTML doc for any divs with the "a" tag thus getting every link in said HTML doc.
        Elements links = doc.select("a");
        for (Element link : links) {
            
            //Filters google caches, extra google links and etc.
            if (!link.absUrl("href").contains("google") 
                    
                //Filters false positives.
                && !link.absUrl("href").isEmpty()
                    
                //Ensures that the searched term is at least in the text or link.
                && (link.text().contains(search) || link.absUrl("href").contains(search))) {
            
                //Keeps track and prints which number the link is. 
                count = count + 1;
                System.out.println(count + ".");
            
                //Makes a copy the original array with an addditional empty element.
                linkArray = Arrays.copyOf(linkArray, linkArray.length + 1);
            
                //Assigns the new link to the empty element.
                linkArray[linkArray.length - 1] = link.absUrl("href");
                
                //Prints out the text and absolute URL.
                System.out.println("Text: " + link.text());
                System.out.println("Link: " + link.absUrl("href") + "\n");
            }
        }
        
        //Prints out and accepts input for which link the user wants to open.
        System.out.print("\nWhich link would you like to open? ");
        int option = scanner.nextInt();
        
        //Asks the OS to open the selected link with the default browser.
        Desktop desktop = java.awt.Desktop.getDesktop();
        URI oURL = new URI(linkArray[option - 1]);
        desktop.browse(oURL);
        System.out.print("\n");
        
        }
        catch (Exception e){
            System.out.println("Invalid input.\n");
        }
    }
}
