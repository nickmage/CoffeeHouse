package client;

import java.util.Random;

public class Names {
    private String[] names = {"James M. Rosado", "Freddy L. Ortiz", "Kerri H. Grinnell", "Donald T. Meikle",
            "Katrina G. Ray", "Derek L. Coleman", "Martha J. Davis", "Linda C. Taylor", "Ronald M. Browning",
            "Debbie A. Bell", "Pamela J. Esposito", "Bridgette R. Hesse", "Ruth W. Neal", "Guillermo S. Cox",
            "Raymond P. Perry", "Tameka R. Gore", "Francis T. Colby", "John N. Russell", "Robert M. McGehee"};

    public String getName(){
        return names[new Random().nextInt(names.length)];
    }
}
