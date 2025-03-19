package src;

public class RecycleDriver {
    public static void main(String[] args) {
        String[][] a = Loader.load_csv("src/data/text/a.csv", null);

        for(String[] b : a)
            for(String c : b)
                System.out.println(c);
    }
}