package buildit.kondeg.udacity.edu.jokeprovider;

import java.util.Random;

public class JokeProvider {

    private static final String joke1 = "3 Database Admins walked into a NoSQL bar. A little later, they walked out because they couldn’t find a table.";
    private static final String joke2 = "There are three kinds of lies: Lies, damned lies, and benchmarks.";
    private static final String joke3 = "Why are iPhone chargers not called Apple Juice?!";

    public static String getJoke() {
        Random random = new Random();
        int rd = random.nextInt(3);
        switch (rd) {
            case 0: return joke1;
            case 1: return joke2;
            case 2: return joke3;
            default: return joke1;
        }
    }


}
