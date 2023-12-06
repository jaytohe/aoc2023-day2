import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static final int MAX_RED = 12;
    private static final int MAX_GREEN = 13;
    private static final int MAX_BLUE = 14;
    private static final String PUZZLE_FILENAME = "input.txt";
    
    public static void main(String[] args) {

        File puzzleInput = new File(PUZZLE_FILENAME);
        String gameLine;
        int sumOfGameIds = 0;
        int gameId = 1;
        Pattern subsetPattern = Pattern.compile("(\\d+) (red|green|blue)(?:, ?|$)");
        try(
            BufferedReader br = new BufferedReader(new FileReader(puzzleInput));

        ) {
            while((gameLine = br.readLine()) != null) {
                if (isPossible(gameLine, subsetPattern)) {
                    sumOfGameIds += gameId;
                }
                gameId++;
            }

            System.out.println("Sum of Game IDs: "+ sumOfGameIds);
        }

        catch(FileNotFoundException fe) {
            fe.printStackTrace();
        }
        catch(NumberFormatException ne) {
            ne.printStackTrace();
        }

        catch(IOException ie) {
            ie.printStackTrace();
        }

    }


    private static boolean isPossible(String gameLine, Pattern subsetPattern) throws NumberFormatException {
        gameLine = gameLine.substring(gameLine.indexOf(":")+2);

        String[] subsets = gameLine.split(";");

        for (String subset: subsets) {
            Matcher m = subsetPattern.matcher(subset);
            while(m.find()) {
                int numOfCubes = Integer.parseInt(m.group(1));
                //System.out.println("numOfCubes: "+numOfCubes+"\t"+"color: "+m.group(2)); //DEBUG
                switch(m.group(2)) {
                    case "red":
                        if (numOfCubes > MAX_RED) return false;
                    case "green":
                        if (numOfCubes > MAX_GREEN) return false;
                    case "blue":
                        if (numOfCubes > MAX_BLUE) return false;
                }
            }
        }

        return true;
    }
}