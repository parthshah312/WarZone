package view;


import java.io.File;

import controller.Game;
import model.AllotArmies;


/**
 * The person in charge of the game assumes the role of Game. It covers everything from "actual game play" to "map editing."
 * Only communicating with the user and invoking the proper methods for additional actions fall under this responsibility.
 *
 *
 */
public class App {
    /**
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\n ------- Greetings, adventurers! Welcome to the WarZone Risk Game!!! --------");
        System.out.println(" ------- You can either load/edit the existing maps or create a map !!! --------\"");
        sampleMaps();
        CommandLine.manageCommandLine();
    }

    /**
     * Based on the territories owned, each player is assigned default reinforcements.
     * @param p_cmd Game ref from main to get track of players
     */
    public static void eachPlayerReinforcements(Game p_cmd){
        p_cmd.d_Players.forEach(l_player -> AllotArmies.allotArmies(l_player));
    }

    /**
     * Shows names of existing map files from sample Resources.
     *
     */
    public static void sampleMaps() {
        File d_Folder = new File("src/main/resources/maps/");
        File[] d_Files = d_Folder.listFiles();
        System.out.println("------ To load a map file named filename.map, enter \"loadmap filename.map\". \n----- For editing a file with the extension filename.map, use \"editmap filename.map");
        System.out.println("----- LET's START ----- ");
        System.out.println();
    }
    public void printSavedGames(){
        File d_Folder = new File("src/main/resources/game/");
        File[] d_Files = d_Folder.listFiles();

        for(int i = 0; i< d_Files.length; i++) {
            if(d_Files[i].isFile())
                System.out.print(d_Files[i].getName()+" | ");
        }
        System.out.println();
    }
}