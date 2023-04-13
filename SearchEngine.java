import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class Handler2 implements URLHandler {
    
    List<String> lis = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Cameron's Strings: %s", lis);
        } 
        else if (url.getPath().equals("/search")){
            String[] parameter = url.getQuery().split("=");
            String search ="";
            List<String> searchLis = new ArrayList<>();
            if (parameter[0].equals("s")) {
                search = parameter[1];

                for (String s: lis){
                    if (s.contains(search)){
                        searchLis.add(s);
                    }
                }
                
            }
            return String.format("%s match" + search, searchLis);
        }

        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    lis.add(parameters[1]);
                    return String.format(parameters[1] + "added to Cameron's strings! It's now %s", lis);
                }
            }
            return "404 Not Found!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}