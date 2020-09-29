import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

//BFS
public class crawler {

    private static final HashSet<String> visitedLinks = new HashSet<>(); //Set of Visited Strings
    private static final Queue<Link> linkQueue = new LinkedList<>(); //Links Queue for BFS
    private static final List<String> facultyRelatedWordsList = new LinkedList<>(); //List of faculty related words
    private static int maxDepth = 3; //MAX DEPTH for traversing
    private static FileWriter textFile; //Writer for file storing extracted texts
    private static FileWriter linksFile; //Writer for file storing extracted links
    private static FileWriter nonHtmlLinksFile; //Writer for file storing extracted links to object files like pdfs
    private static String urlContains = ""; //String which should be there in each url to prevent crawler from going outside the website
    private static int depth = 0; //Current Depth

    public static boolean isValid(String url) {
        /*
            To check validity of url
            Param:
            url:String - URL
            Return:
            Boolean-Validity
        */
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }
        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }

    public static int frequency(String paragraph, String substring) {
        /*
            To find frequency of a string(substring) in another string(paragraph)
            param:
            paragraph:String-String in which frequency to be calculated
            substring:String-String of which frequency is to be calculated
            return:
            frequency:Integer
         */
        if (paragraph.length() == 0 || substring.length() == 0) {
            return 0;
        }
        return paragraph.split(substring).length - 1;
    }

    public static boolean facultyRelated(final Document page) {
        /*
            Check if the page is related to faculty by checking the frequency of faculty related words
            param:
            page:Document-HTML page
            return
            boolean-related or not
         */
        if (page == null) {
            return false;
        }
        int frequencyOfFacultyRelatedWords = 0;
        String htmlPageString = page.toString().toLowerCase();
        for (String word :
                facultyRelatedWordsList) {
            frequencyOfFacultyRelatedWords += frequency(htmlPageString, word);
        }
        return frequencyOfFacultyRelatedWords > 5;

    }

    public static boolean facultyRelated(String url) {
        if (url.length() == 0) {
            return false;
        }
        int frequencyOfFacultyRelatedWords = 0;
        url = url.toLowerCase();
        for (String word :
                facultyRelatedWordsList) {
            frequencyOfFacultyRelatedWords += frequency(url, word);
        }
        return frequencyOfFacultyRelatedWords > 0;

    }

    public static void extractLinks(final Document page) {
        /*
            To extract the links from "<a>" tags
            param:
            page:Document-HTML page
         */
        if (page == null) {
            return;
        }
        Elements links = page.getElementsByTag("a");
        for (Element link : links) {
            String newURL = link.attr("abs:href");
            if (!newURL.contains("#") && !visitedLinks.contains(newURL) && newURL.contains(urlContains)) {
                Link temp = new Link();
                temp.url = newURL;
                temp.text = link.text();
                linkQueue.add(temp);
            }
        }
    }

    public static void extractText(final Document page) {
        if (page == null) {
            return;
        }
        for (String tag :
                ("p h1 h2 h3 h4 h5 h6 em strong blockquote").split(" ")) {
            Elements tagElementsList = page.getElementsByTag(tag);
            for (Element tagElement : tagElementsList) {
                if (tagElement.text().length() > 0) {
                    try {
                        String row = "<" + tag + ">,\"" + tagElement.text() + "\"\r\n";
                        textFile.write(row);
                    } catch (IOException e) {
                        System.out.println("Error writing to text.csv!");
                    }
                }
            }

        }

    }

    public static void crawl(String url, String text) {
        if (url.length() == 0) {
            return;
        }
        if (visitedLinks.contains(url)) {
            return;
        }
        System.out.println("Parsing: " + url);//+ " Depth: " + depth);
        visitedLinks.add(url);
        try {
            if (depth <= maxDepth) {
                Document page = Jsoup.connect(url).get(); //send get request
                if (facultyRelated(url) || facultyRelated(page)) { //if page/url is related to faculty
                    extractText(page);
                    try {
                        String row = text + "," + url + "\r\n";
                        linksFile.write(row);
                    } catch (IOException e) {
                        System.out.println("Error writing to links.csv!");
                    }
                }
                extractLinks(page);
            } else if (facultyRelated(url)) { //if url is faculty related
                Jsoup.connect(url).execute(); //send get request to check for nonHTML links
                try {
                    String row = text + "," + url + "\r\n";
                    linksFile.write(row);
                } catch (IOException e) {
                    System.out.println("Error writing to links.csv!");
                }
            }

        } catch (UnsupportedMimeTypeException type) {//for nonHTML(objects like pdf,jpg etc) links
            if (facultyRelated(url)) {
                try {
                    nonHtmlLinksFile.write(text + "," + url + "\r\n");
                } catch (IOException e) {
                    System.out.println("Error writing to nonHtmlLinks.csv!");
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to parse " + url);
        }

    }

    public static void main(String[] args) {
        try {
            linksFile = new FileWriter("Assignment_3\\links.csv");
            textFile = new FileWriter("Assignment_3\\text.csv");
            nonHtmlLinksFile = new FileWriter("Assignment_3\\nonHtmlLinks.csv");

            String row = "Tag ,Text\r\n";
            textFile.write(row);

            row = "Link Text,URL\r\n";
            linksFile.write(row);

            row = "Link Text,URL\r\n";
            nonHtmlLinksFile.write(row);

        } catch (IOException e) {
            System.out.println("Unable To Create File(s)!");
            System.exit(1);
        }
        //Input
        String url = "";
        try {
            System.out.print("Input URL: ");
            Scanner input = new Scanner(System.in);
            url = input.nextLine(); //"https://pec.ac.in/";
            System.out.print("Enter String which should be in every url: ");
            urlContains = input.nextLine(); // "pec.ac.in/
            System.out.print("Enter MAX Depth(BFS): ");
            maxDepth = input.nextInt(); // 4
            input.nextLine();
            input.close();
        } catch (Exception e) {
            System.out.println("Invalid Input!");
            System.exit(1);
        }

        long startTime = System.nanoTime(); //start time

        //Check url validity
        if (!isValid(url)) {
            System.out.println("INVALID URL!");
            return;
        }

        //add words to list
        facultyRelatedWordsList.addAll(Arrays.asList(("faculty professor prof assistant teacher teaching engineer engineering publications qualification books published projects").split(" ")));

        //BFS
        Link temp = new Link();
        temp.url = url;
        temp.text = " ---ROOT--- ";
        linkQueue.add(temp);
        linkQueue.add(null);//For separating levels
        while (!linkQueue.isEmpty()) {
            temp = linkQueue.remove();
            if (temp == null) {
                depth++;
                if (!linkQueue.isEmpty()) {//For non leaf level
                    linkQueue.add(null);
                }
            } else {
                crawl(temp.url, temp.text);
            }
        }

        try {

            textFile.close();
            linksFile.close();
            nonHtmlLinksFile.close();

        } catch (IOException e) {
            System.out.println("Unable to close files!");
        }

        long endTime = System.nanoTime();
        double timeElapsed = (endTime - startTime) / 1000000000.0;
        System.out.println("Time Elapsed: " + timeElapsed / 60.0 + " minutes");//Time in minutes

    }

    //Class for URLS
    private static class Link {
        public String url = ""; //url
        public String text = "";
    }
}