import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Handler3 implements URLHandler {
    
    List<String> lis = new ArrayList<>();
    String[] l = {"a \n","b"};
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("%s", lis);
        }

        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    lis.add(parameters[1]+ "\n");
                    
                    String str = "";
                    
                    for(String s: lis) {
                        str += s;
                    }
                    
                    return String.format("%s",str);
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

        Server.start(port, new Handler3());
    }
}
