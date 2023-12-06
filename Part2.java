import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private static final String PUZZLE_FILENAME = "input.txt";
    
    public static void main(String[] args) {

        File puzzleInput = new File(PUZZLE_FILENAME);
        String gameLine;
        int sumOfPowers = 0;
        Pattern linePattern = Pattern.compile("(\\d+) (red|green|blue)(?:, ?|;|$)");
        try(
            BufferedReader br = new BufferedReader(new FileReader(puzzleInput));

        ) {
            while((gameLine = br.readLine()) != null) {
                sumOfPowers += calculatePower(gameLine, linePattern);
            }

            System.out.println("Sum of Game Powers: "+ sumOfPowers);
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


    private static int calculatePower(String gameLine, Pattern linePattern) throws NumberFormatException {
        gameLine = gameLine.substring(gameLine.indexOf(":")+2);
        Matcher m = linePattern.matcher(gameLine);

        int minRedCubes = 1;
        int minGreenCubes = 1;
        int minBlueCubes = 1;
        
            while(m.find()) {
                int numOfCubes = Integer.parseInt(m.group(1));
                //System.out.println("numOfCubes: "+numOfCubes+"\t"+"color: "+m.group(2)); //DEBUG
                switch(m.group(2)) {
                    case "red":
                        minRedCubes = (numOfCubes > minRedCubes) ? numOfCubes : minRedCubes;
                    break;
                    case "green":
                        minBlueCubes = (numOfCubes > minBlueCubes) ? numOfCubes : minBlueCubes;
                    break;
                    case "blue":
                        minGreenCubes = (numOfCubes > minGreenCubes) ? numOfCubes : minGreenCubes;
                    break;
                }
            }

        //System.out.println(); //DEBUG
        //System.out.println(minRedCubes+"\t"+minBlueCubes+"\t"+minGreenCubes);
        //System.out.println();
        return minRedCubes*minBlueCubes*minGreenCubes;
    }
}