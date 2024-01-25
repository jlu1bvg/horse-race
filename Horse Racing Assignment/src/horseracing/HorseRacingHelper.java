package horseracing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class HorseRacingHelper {
    public static final int SHORT = 0;
    public static final int MIDDLE = 1;
    public static final int LONG = 2;

    public static final int GRASS = 0;
    public static final int DIRT = 1;
    public static final int MUD = 2;
    public static final int AIR=3;
    public static final int PARADISUS=4;
    public static final int KITCHEN=5;

    private static final double[] SHORT_RACES = {5, 5.5, 6};
    private static final double[] MIDDLE_RACES = {7, 8};
    private static final double[] LONG_RACES = {9, 10, 12};

    private static volatile Thread musicThread;
    private static volatile boolean shouldContinue = true;

    private static List<Horse> allHorses;
    
    private HorseRacingHelper() {
        allHorses = new ArrayList<>();
        loadHorsesFromCSV("horses.csv"); 
    }

    public static void prepareHorseRacingSimulation(){
        if (allHorses == null)
            new HorseRacingHelper();
    }

    // Private method to load horses from a CSV file
    private void loadHorsesFromCSV(String csvFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean headerSkipped = false; // Skip the header row

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] data = line.split(",");
                if (data.length == 8) {

                    //gets values from csv
                    String name = data[0];
                    int mudRating = Integer.parseInt(data[1]);
                    int grassRating = Integer.parseInt(data[2]);
                    int dirtRating = Integer.parseInt(data[3]);
                    int airRating=Integer.parseInt(data[5]);
                    int paradisusRating=Integer.parseInt(data[6]);
                    int kitchenRating=Integer.parseInt(data[7]);
                    double preferredLength = Double.parseDouble(data[4]);

                    //constructs horse with values
                    Horse horse = new Horse(name, mudRating, grassRating, dirtRating, airRating,paradisusRating,kitchenRating,preferredLength);
                    allHorses.add(horse);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public List<Horse> getHorses(int numHorses) {
        return allHorses;
    }

    // private static List<Horse> getNDifferentHorses(int n) {
    //     // Ensure n is not greater than the size of the input list
    //     n = Math.min(n, allHorses.size());

    //     // Shuffle the list
    //     List<Horse> shuffledList = new ArrayList<>(allHorses);
    //     Collections.shuffle(shuffledList);

    //     // Take the first n elements
    //     return shuffledList.subList(0, n);
    // }

    public static List<Horse> shuffleHorses(){
        // Shuffle the list
        List<Horse> shuffledList = new ArrayList<>(allHorses);
        Collections.shuffle(shuffledList);

        return shuffledList;
    }

    public static Race createRace(int numHorses, int raceType, int raceTerrain, PlayerContainer players,List<Horse> horseList){
        
        //converts parameter to double
        double[] raceLengths;
        if (raceType == SHORT)
            raceLengths = SHORT_RACES;
        else if (raceType == MIDDLE)
            raceLengths = MIDDLE_RACES;
        else
            raceLengths = LONG_RACES;

        //converts parameter to text
        String terrain = "";
        if (raceTerrain == GRASS)
            terrain = "Grass";
        else if (raceTerrain == DIRT)
            terrain = "Dirt";
        else if(raceTerrain==MUD)
            terrain = "Mud";
        else if(raceTerrain==AIR)
            terrain="Air";
        else if(raceTerrain==PARADISUS)
            terrain="Paradisus Varadero";
        else
            terrain="Mr. Deslauriers Malevolent Kitchen";

        //randomizes racelength
        double raceLength = raceLengths[(int)(Math.random()*raceLengths.length)];
            

        // List<Horse> horses = getNDifferentHorses(numHorses);

        //sets numbers for horses in race
        for (int j = 1; j <= horseList.size(); j++) {
            horseList.get(j-1).setNumber(j);
        }

        //thing for making ruhao op
        for(int i=0;i<horseList.size();i++){
            if(horseList.get(i).getName().equals("Ruhao")){
                horseList.get(i).setLength(raceLength);
            }
        }

        return new Race(horseList, raceLength, terrain, players);
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // For Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix-like systems
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            // Handle exceptions if any
            e.printStackTrace();
        }
    }

    public static void pauseForMilliseconds(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // Handle the InterruptedException if needed
            e.printStackTrace();
        }
    }
   
    public static void drawHorse(Horse horse, int width, int number){
        // Using printf to display the formatted string with the number
        System.out.printf("|%"+((width - horse.getCurrentPosition())>1?(horse.getCurrentPosition()):width-1)+"s%" + ((width - horse.getCurrentPosition())>1?(width - horse.getCurrentPosition()):1) + "s\n", horse.getNumber(),"|");
        System.out.flush();
    }

    public static void drawEmptyTrack(int width){
        // Using printf to display the formatted string with the number
        System.out.printf("|%"+(width+1) + "s","|\n");
        System.out.flush();
    }

    public static void playBackgroundMusic(String filePath, boolean repeat) {
        shouldContinue = true;
        musicThread = new Thread(() -> {
            do{
            try {
                File audioFile = new File(filePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

                // Get the format of the audio file
                AudioFormat format = audioStream.getFormat();

                // Set up the source data line to play the audio
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                sourceLine.open(format);
                sourceLine.start();

                // Read the audio file and play it
                byte[] buffer = new byte[4096];
                int bytesRead = 0;
                while (shouldContinue && (bytesRead = audioStream.read(buffer, 0, buffer.length)) != -1) {
                    sourceLine.write(buffer, 0, bytesRead);
                }

                // Close the source data line and audio stream
                sourceLine.drain();
                sourceLine.close();
                audioStream.close();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
            }while(repeat);
        });
    
        musicThread.start();
    }

    public static void updateTrack(int numSpaces, List<Horse> horses) {
        int i = 1;
        
        for(Horse horse : horses){
            drawEmptyTrack(numSpaces);
            drawHorse(horse, numSpaces, i++);
        }
        
        drawEmptyTrack(numSpaces);
    }

    public static void stopMusic() {
        // Interrupt the music thread to stop it
        shouldContinue = false;
    }

    public static void playBackgroundMusicAndWait(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get the format of the audio file
            AudioFormat format = audioStream.getFormat();

            // Set up the source data line to play the audio
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(format);
            sourceLine.start();

            // Read the audio file and play it
            byte[] buffer = new byte[4096];
            int bytesRead = 0;
            while ((bytesRead = audioStream.read(buffer, 0, buffer.length)) != -1) {
                sourceLine.write(buffer, 0, bytesRead);
            }

            // Close the source data line and audio stream
            sourceLine.drain();
            sourceLine.close();
            audioStream.close();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    } 
}
