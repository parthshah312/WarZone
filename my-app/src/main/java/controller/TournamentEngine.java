package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;

import model.AggresivePlayer;
import model.AllotArmies;
import model.BenevolentPlayer;
import model.CheaterPlayer;
import model.GameData;
import model.GameRunnerEngine;
import model.Player;
import model.RandomPlayer;
import model.StartUpPhase;
import model.order1;
import view.TournamentResultView;


public class TournamentEngine extends Game{
    StartUpPhase d_StartUp;
    public TournamentEngine(Game p_Ge){
        super();
        d_StartUp = new StartUpPhase(p_Ge);
    }

    /**
     * parsing command
     * @param p_player player object
     * @param p_newCommand new command
     * @return command
     */
    public String parse(Player p_player, String p_newCommand){
        String[] l_data = p_newCommand.split("\\s+");
        System.out.println("---- Parse in"+l_data+" ----");
        System.out.println("---- Parse in"+l_data[0]+" ----");
        String l_commandName = l_data[0];
        int l_noOfMaps;
        int l_noOfGames;
        int l_noOfTurns;
        ArrayList<String> l_maps = new ArrayList<String>();
        ArrayList<String> l_strategies = new ArrayList<String>();

        if (l_commandName.equals("tournament")) {
            try {
                if (l_data[1].equals("-M")) {
                    int i = 2;
                    while (!l_data[i].equals("-P")) {
                        if (i < l_data.length) {
                            if (isMapNameValid(l_data[i])) {
                                l_maps.add(l_data[i]);
                            } else {
                                printFailureMessage();
                                return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                            }
                        } else {
                            printFailureMessage();
                            return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                        }
                        i++;
                    }

                    if(l_maps.size()>=1 && l_maps.size()<=5 && allMapExists(l_maps)) {

                        if (l_data[i].equals("-P")) {

                            int l_indexNew = i + 1;

                            while (!l_data[l_indexNew].equals("-G")) {
                                if (l_indexNew < l_data.length) {
                                    if (isPlayerStrategyValid(l_data[l_indexNew])) {
                                        l_strategies.add(l_data[l_indexNew]);
                                    } else {
                                        printFailureMessage();
                                        return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                                    }

                                } else {
                                    printFailureMessage();
                                    return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                                }
                                l_indexNew++;
                            }

                            if(l_strategies.size()>=2 && l_strategies.size()<=4 && isPlayerStrategyDistinct(l_strategies)) {
                                if (l_data[l_indexNew].equals("-G")) {
                                    if (l_indexNew + 1 < l_data.length) {
                                        if (l_data[l_indexNew + 1].matches("[1-5]")) {
                                            l_noOfGames = Integer.parseInt(l_data[l_indexNew + 1]);
                                        } else {
                                            printFailureMessage();
                                            return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                                        }
                                    } else {
                                        printFailureMessage();
                                        return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                                    }

                                    int l_newIndex = l_indexNew + 2;

                                    if (l_data[l_newIndex].equals("-D")) {


                                        if (l_newIndex + 1 < l_data.length) {

                                            int l_n = Integer.parseInt(l_data[l_newIndex + 1]);

                                            if (l_n >= 10 && l_n <= 50) {
                                                l_noOfTurns = Integer.parseInt(l_data[l_newIndex + 1]);
                                                playTournament(l_maps, l_strategies, l_noOfGames, l_noOfTurns);
                                                return "success";
                                            } else {
                                                printFailureMessage();
                                                return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                                            }
                                        } else {
                                            printFailureMessage();
                                            return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                                        }
                                    } else {
                                        printFailureMessage();
                                        return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                                    }
                                } else {
                                    printFailureMessage();
                                    return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                                }
                            }else{
                                printFailureMessage();
                                return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                            }
                        } else {
                            printFailureMessage();
                            return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                        }
                    }else {
                        printFailureMessage();
                        return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                    }
                } else {
                    printFailureMessage();
                    return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                }
            }catch (ArrayIndexOutOfBoundsException e){
                String message = "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
                return message;
            }
        } else {
            printFailureMessage();
            return "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
        }
    }

    /**
     * method for tournament
     * @param p_mapFiles list of maps
     * @param p_strategies list of strategy
     * @param p_numberOfGames number of games
     * @param p_numberOfTurns number of turns
     */
    public void playTournament(ArrayList<String> p_mapFiles, ArrayList<String> p_strategies, int p_numberOfGames, int p_numberOfTurns) {
        int l_numberOfPlayers = p_strategies.size();
        int l_traversalCounter = 0;
        int l_gameNumber = 0;
        HashMap<Integer, String> l_winner = new HashMap<>();
        for (String l_mapName : p_mapFiles) {
            System.out.println("---- This playing on :" + l_mapName+" ----");
            for (int i = 1; i <= p_numberOfGames; i++) {
                l_gameNumber++;
                System.out.println("---- This playing on :" + l_gameNumber+" ----");
                d_Ge = new Game();
                d_Game = new GameData();
                d_RunG = new GameRunnerEngine();
                d_StartUp = new StartUpPhase(d_Ge);
                d_Map = d_RunG.loadMap(l_mapName);
                for (String l_strategy : p_strategies) {
                    d_StartUp.addPlayer(d_Players, l_strategy);
                }
                for (Player l_p : d_Players) {
                    switch (l_p.getPlayerName().toLowerCase()) {
                        case "aggressive":
                            l_p.setStrategy(new AggresivePlayer(l_p, d_Map));
                            l_p.setD_isHuman(false);
                            break;
                        case "benevolent":
                            l_p.setStrategy(new BenevolentPlayer(l_p, d_Map));
                            l_p.setD_isHuman(false);
                            break;
                        case "random":
                            l_p.setStrategy(new RandomPlayer(l_p, d_Map));
                            l_p.setD_isHuman(false);
                            break;
                        case "cheater":
                            l_p.setStrategy(new CheaterPlayer(l_p, d_Map));
                            l_p.setD_isHuman(false);
                            break;
                        default:
                            System.out.println("---- It is not valid Player Strategy ----");
                            break;
                    }
                }
                d_StartUp.assignCountries(d_Map, d_Players);
                assignEachPlayerReinforcements(d_Players);
                for (int j = 1; j <= p_numberOfTurns; j++) {
                    int l_counter = 0;
                    System.out.println("---- It is a " + j + " Turn ----");
                    Iterator<Player> l_itr = new ArrayList<>(d_Players).iterator();
                    while (l_itr.hasNext()) {
                        Player l_p = l_itr.next();
                        if (l_p.getOwnedArmies() > 0) {
                            l_counter = l_counter + l_p.getOwnedArmies();
                        }
                    }

                    System.out.println("---- There is total Armies left with all Players in Pool: " + l_counter+" ----");
                    while (l_counter > 0) {
                        for (Player p : d_Players) {
                            p.issueOrder();
                        }
                        l_counter = 0;
                        for (Player p : d_Players) {
                            if (p.getOwnedArmies() > 0) {
                                l_counter = l_counter + p.getOwnedArmies();
                            }
                        }
                    }

                    int l_count = 0;
                    for (Player l_p : d_Players) {
                        Queue<order1> l_temp = l_p.getD_orderList();
                        l_count = l_count + l_temp.size();
                    }

                    if (l_count == 0) {
                        System.out.println("---- Orders already executed!!! ----");
                    } else {
                        System.out.println("---- Total Orders is  :" + l_count+" ----");
                        while (l_count != 0) {
                            if (d_Players.size() == 1) {
                                order1 l_toRemove = d_Players.get(0).next_order();
                                if (l_toRemove != null) {
                                    l_toRemove.execute();
                                }
                                l_winner.put(l_gameNumber, d_Players.get(0).getPlayerName());
                                break;
                            } else {
                                for (Player l_p : new ArrayList<>(d_Players)) {
                                    Queue<order1> l_temp = l_p.getD_orderList();
                                    if (l_temp.size() > 0) {
                                        order1 l_toRemove = l_p.next_order();
                                        if (l_toRemove != null) {
                                            l_toRemove.execute();
                                        }
                                    }
                                    if (l_p.getOwnedCountries().size() == 0) {
                                        d_Players.remove(l_p);
                                    }
                                }
                                l_count--;
                            }
                        }

                        for (Player l_p : d_Players) {
                            l_p.flushNegotiators();
                        }

                        assignEachPlayerReinforcements(d_Players);
                    }
                }

                int maxOwnedCountries = 0;
                String winnerName = null;
                for (Player l_p : d_Players) {
                    int ownedCountries = l_p.getOwnedCountries().size();
                    if (ownedCountries > maxOwnedCountries) {
                        maxOwnedCountries = ownedCountries;
                        winnerName = l_p.getPlayerName();
                    }
                }

                if (winnerName != null) {
                    System.out.println("---- "+winnerName + " has Won the Game with " + maxOwnedCountries + " territories!!!");
                    d_LogEntry.setMessage(winnerName + " has Won the Game with " + maxOwnedCountries + " territories!!!");
                    l_winner.put(l_gameNumber, winnerName);
                } else {
                    System.out.println("Draw!");
                    l_winner.put(l_gameNumber, "Draw");
                }
                d_Players.clear();
            }
        }
        TournamentResultView tournamentResultView = new TournamentResultView();
        tournamentResultView.displayTournamentResult(l_winner, p_mapFiles);
    }

    /**
     * method to check if all map exists
     * @param p_list list of maps
     * @return true if all map exists
     */
    public boolean allMapExists(ArrayList<String> p_list){
        int l_counter=0;
        for(String s:p_list){
            if(isMapExists(s)){
                l_counter++;
            }
        }
        if(l_counter == p_list.size()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * method to check if map exists
     * @param p_mapName name of map
     * @return true if map exists
     */
    public boolean isMapExists(String p_mapName) {
        String l_filePath = "src/main/resources/maps/" + p_mapName;
        File f = new File(l_filePath);
        if (f.exists()) {
            GameRunnerEngine rGE = new GameRunnerEngine();
            d_Map = rGE.loadMap(p_mapName);
            return true;
        } else {
            return false;
        }
    }

    /**
     * method to print failure message
     */
    public void printFailureMessage(){
        String message = "Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns";
        System.out.println(message);
        d_LogEntry.setMessage("Command is need to be in the  form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns");
    }

    /**
     * method to check if map name is valid
     * @param p_s name of map
     */
    public boolean isMapNameValid(String p_s) {
        return p_s != null && p_s.matches("^[a-zA-Z.]*$");
    }

    /**
     * method to check if player strategy is valid
     * @param p_s strategy of player
     * @return true if strategy is valid
     */
    public boolean isPlayerStrategyValid(String p_s){

        String[] l_array = new String[]{"aggressive", "benevolent", "random", "cheater"};
        for(int i=0; i<4; i++){
            if(p_s.equals(l_array[i])){
                return true;
            }
        }
        return false;
    }

    /**
     * method to check if player strategy is distinct
     * @param p_list list of strategies
     * @return true if distinct
     */
    public boolean isPlayerStrategyDistinct(ArrayList<String> p_list){

        for(int i=0;i<p_list.size();i++){
            for(int j = i+1; j<p_list.size(); j++){
                if(p_list.get(i).equals(p_list.get(j))){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * mehtod to allot reinforcements to every player
     * @param p_Players list of players
     */
    public void assignEachPlayerReinforcements(ArrayList<Player> p_Players){
        for(Player l_p: p_Players) {
            AllotArmies.allotArmies(l_p);
        }
    }
}
