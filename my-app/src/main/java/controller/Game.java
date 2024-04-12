package controller;


import static java.lang.System.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import enumClass.GamePhase;
import model.Advance;
import model.Airlift;
import model.AllotArmies;
import model.Blockade;
import model.Bomb;
import model.Card;
import model.Diplomacy;
import model.GameData;
import model.GameDataBuilder;
import model.GameMap;
import model.GamePhases;
import model.GameRunnerEngine;
import model.Load;
import model.LogBuffer;
import model.MainPlayPhase;
import model.Order;
import model.Player;
import model.ShowMap;
import model.StartUpPhase;
import model.TerritoryDetails;
import model.order1;
import view.App;
import view.WriteLogEntry;


/**
 *
 * Game class where the commands are parsed.
 */


public class Game {

    public Game d_Ge;
    public GameMap d_Map;
    public GameRunnerEngine d_RunG;
    public GameData d_Game;
    public StartUpPhase d_StartUpPhase;
    public AllotArmies d_Arfc;
    public GamePhase d_GameGamePhase;
    public ArrayList<Player> d_Players;
    public App d_Play;

    public LogBuffer d_LogEntry;
    public GamePhases d_GamePhases;

    public WriteLogEntry d_WriteLog;

    public boolean l_checkplayer = false;

    public String d_PhaseName;
    public MainPlayPhase d_MainPlay;
    public Player d_ActivePlayer;
    public Card d_Card;
    public static int load=0;

    /**
     *
     * Game constructor
     */

    public Game() {
        d_Map = new GameMap();
        d_RunG = new GameRunnerEngine();
        d_StartUpPhase = new StartUpPhase(d_Ge);
        d_Arfc = new AllotArmies();
        d_Players = new ArrayList<Player>();
        d_GameGamePhase = GamePhase.NULL;
        d_Play = new App();
        d_LogEntry = new LogBuffer();
        d_WriteLog = new WriteLogEntry();
        d_Game= new GameData();
        d_LogEntry.attach(d_WriteLog);

    }

    /**
     * Game parameterized constructor
     * 
     * @param p_gameData game data
     */
    public Game(GameData p_gameData) {
        d_Map = p_gameData.getMap();
        d_RunG = new GameRunnerEngine();
        //d_StartUp = new StartUp(d_Ge);
        d_Arfc = new AllotArmies();
        d_Players = p_gameData.getPlayers();
        d_GameGamePhase = p_gameData.getGamePhase();
        d_Play = new App();
        d_LogEntry = new LogBuffer();
        d_WriteLog = new WriteLogEntry();
        d_PhaseName= p_gameData.getD_PhaseName();

        if(d_PhaseName.equals("MainPlay")) {
            d_MainPlay = new MainPlayPhase(this);
            d_GamePhases = d_MainPlay;
        } else
            d_GamePhases=p_gameData.getD_Phase();

        this.d_Game= new GameData(d_Map,"domination", d_GameGamePhase,d_GamePhases, d_Players, d_ActivePlayer, d_Card,d_PhaseName);
        System.out.println(d_Game);
        d_LogEntry.attach(d_WriteLog);
        System.out.println("gamengine hellos");
    }

    /**
     * setter function for phase
     * @param p_GamePhases phases of game
     */
    public void setPhase(GamePhases p_GamePhases)
    {
        d_GamePhases = p_GamePhases;
    }

    /**
     * setter fucnction for game phase
     * @param p_gameGamePhase phase of game
     */
    public void setGamePhase(GamePhase p_gameGamePhase) {
        this.d_GameGamePhase = p_gameGamePhase;
    }

    /**
     * funciton to check validity of map name
     * @param p_val name of map
     * @return true if valid map name
     */
    public boolean isMapNameValid(String p_val) {
        return p_val != null && p_val.matches("^[a-zA-Z.]*$");
    }

    /**
     * getter method for players
     * @return list of players
     */
    public ArrayList<Player> getPlayers(){
        return this.d_Players;
    }

    /**
     * check valid player name
     * @param p_sample name of player
     * @return true if name is valid
     */
    public boolean isPlayerNameValid(String p_sample) {
        return p_sample != null && p_sample.matches("[a-zA-Z0-9]+");
    }

    /**
     * checking alphabetic
     * @param p_sample string to be checked
     * @return true if valid
     */
    public boolean isAlphabetic(String p_sample) {
        return p_sample != null && p_sample.matches("^[a-zA-Z-_]*$");
    }

    /**
     * checking numeric
     * @param p_sample string to be checked
     * @return true if valid
     */
    public boolean isNumeric(String p_sample) {
        return p_sample != null && p_sample.matches("[0-9]+");
    }

    /**
     * getter function for phase
     * @return phases of game
     */
    public GamePhases getPhase() {
        return d_GamePhases;
    }

    /**
     * getter method for active player
     * @return active player
     */
    public Player getD_ActivePlayer() {
        return d_ActivePlayer;
    }

    /**
     * setter method for active player
     * @param d_ActivePlayer player that is set to be active
     */
    public void setD_ActivePlayer(Player d_ActivePlayer) {
        this.d_ActivePlayer = d_ActivePlayer;
    }

    /**
     * method to create game data
     * @param p_gameDataBuilder game data
     */
    public void createGameData(GameDataBuilder p_gameDataBuilder){
        System.out.println("bdshb"+p_gameDataBuilder.buildGameData());
        this.d_Game = p_gameDataBuilder.buildGameData();
    }

    /**
     * setter method for game
     */
    public void setGame() {
        System.out.println(d_ActivePlayer.getPlayerName());
        System.out.println(this.d_Game.getDeck());
        System.out.println(this.d_Game.getMap());
        this.d_Game.setActivePlayer(d_ActivePlayer);
        this.d_Game.setGamePhase(d_GameGamePhase);
        this.d_Game.setD_Phase(d_GamePhases);
        this.d_Game.setPlayers(d_Players);
        this.d_Game.setMapType("domination");
        this.d_Game.setCardsDealt(load);
        this.d_Game.setD_PhaseName(d_GamePhases.getD_PhaseName());
        d_Game = new GameData(d_Map,"domination", d_GameGamePhase,d_GamePhases, d_Players, d_ActivePlayer, d_Card,d_GamePhases.getD_PhaseName());
    }

    /**
     * getter method for game
     * @return game
     */
    public GameData getGame() {
        return this.d_Game;
    }

    /**
     *
     * Commands are parsed to their respective functions
     *
     * @param p_player Player object
     * @param p_newCommand command
     * @return the phase of the game
     */

    public GamePhase parseCommand(Player p_player, String p_newCommand) {
        //System.out.println("p_newCommand"+p_newCommand);
        int l_controlValue = 0;
        int l_numberOfArmies = 0;
        String l_mapName = null;
        String l_continentId = null;
        String l_territoryId = null;
        String l_neighbourTerritoryId = null;
        String l_territoryNameFrom = null;
        String l_territoryNameTo= null;
        String l_playerName = null;
        String l_territoryID=null;
        String l_sourceterritoryId= null;
        String l_targetterritoryId= null;
        String l_playerStrategy = null;
        String[] l_data = p_newCommand.split("\\s+");
        String l_commandName = l_data[0];
        String l_fileName;

        if (d_GameGamePhase.equals(GamePhase.NULL)) {
            d_LogEntry.setMessage("------- Initial Phase --------");
            setPhase(new Load(this));
            switch (l_commandName) {

                case "loadgame":
                    try{
                        if(l_data.length == 2){
                            if(isAlphabetic(l_data[1])) {
                                l_fileName = l_data[1];
                                GameDataBuilder l_gameDataBuilder = d_RunG.loadGame(l_fileName);
                                createGameData(l_gameDataBuilder);
                                System.out.println("---- The game loaded successfully in the game ----");
                                d_LogEntry.setMessage("---- The game loaded successfully in the game ----");

                            }
                        }else{
                            System.out.println("---- There is invalid command so enter the correct file name to load a game ----");
                            d_LogEntry.setMessage("---- There is invalid command so enter the correct file name to load a game ----");
                        }

                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("---- There is invalid command and  It should be in the form of 'loadgame filename' ----");

                        d_LogEntry.setMessage("---- There is invalid command and  It should be in the form of 'loadgame filename' ----");
                    }
                    break;
                case "editmap":
                    d_LogEntry.setGamePhase(d_GamePhases);
                    d_LogEntry.setCommand("-------- "+l_commandName+" Command is being executed!!! --------\n");
                    d_GamePhases.editMap(l_data, l_mapName);
                    String str=d_GamePhases.getD_PhaseName();
                    System.out.println(str);
//
                    break;

                case "loadmap":

                    d_LogEntry.setGamePhase(d_GamePhases);
                    d_LogEntry.setCommand("-------- "+l_commandName+" Command is being executed!!! --------\n");
                    d_GamePhases.loadMap(l_data,l_mapName);
                    String str1=d_GamePhases.getD_PhaseName();
                    System.out.println(str1);
                    break;
                default:
                    System.out.println("----- Try LoadMap or EditMap first: loadmap filename.map or editmap filename.map ------");
                    d_LogEntry.setMessage("----- Try LoadMap or EditMap first: loadmap filename.map or editmap filename.map ------");
                    break;
            }
        }
        else if (d_GameGamePhase.equals(GamePhase.EDITMAP)) {

            d_LogEntry.setMessage("------- EditMap Phase --------");
            switch (l_commandName) {
                case "editcontinent":
                    setPhase(new Load(this));
                    d_LogEntry.setGamePhase(d_GamePhases);
                    d_LogEntry.setCommand("-------- "+l_commandName+" Command is being executed!!! --------\n");
                    d_GamePhases.editContinent(l_data, l_continentId, l_controlValue);
                    String str= d_GamePhases.getD_PhaseName();
                    System.out.println(str);

                    break;

                case "editcountry":
                    setPhase(new Load(this));
                    d_LogEntry.setGamePhase(d_GamePhases);
                    d_LogEntry.setCommand("-------- "+l_commandName+" Command is being executed!!! --------\n");
                    d_GamePhases.editCountry(l_data, l_continentId, l_territoryID) ;
                    d_LogEntry.setGamePhase(d_GamePhases);
                    String str1= d_GamePhases.getD_PhaseName();
                    System.out.println(str1);
                    break;

                case "editneighbor":
                    setPhase(new Load(this));
                    d_LogEntry.setGamePhase(d_GamePhases);
                    d_LogEntry.setCommand("-------- "+l_commandName+" Command is being executed!!! --------\n");
                    d_GamePhases.editNeighbour(l_data, l_territoryID, l_neighbourTerritoryId);
                    String str2= d_GamePhases.getD_PhaseName();
                    System.out.println(str2);

                    break;

                case "savemap":
                    setPhase(new Load(this));
                    d_LogEntry.setGamePhase(d_GamePhases);
                    d_LogEntry.setCommand("-------- "+l_commandName+" Command is being executed!!! --------\n");
                    d_GamePhases.savemap(l_data, l_mapName);
                    String str3= d_GamePhases.getD_PhaseName();
                    System.out.println(str3);

                    break;

                case "showmap":
                    setPhase(new Load(this));
                    d_LogEntry.setGamePhase(d_GamePhases);
                    d_LogEntry.setCommand("-------- "+l_commandName+" Command is being executed!!! --------\n");
                    d_GamePhases.showMap(d_Map);
                    String str4= d_GamePhases.getD_PhaseName();
                    System.out.println(str4);
                    d_GameGamePhase = GamePhase.EDITMAP;
                    break;

                case "validatemap":
                    setPhase(new Load(this));
                    d_LogEntry.setGamePhase(d_GamePhases);
                    d_LogEntry.setCommand("-------- "+l_commandName+" Command is being executed!!! --------\n");
                    d_GamePhases.validatemap();
                    String strValidate= d_GamePhases.getD_PhaseName();
                    System.out.println(strValidate);

                    break;

                default:
                    System.out.println("---- Invalid command format. Should be:  edit commands(editcontinent/editcountry/editneighbor) or savemap/validatemap/editmap/loadmap/showmap command");
                    d_LogEntry.setMessage("---- Invalid command format. Should be: edit commands(editcontinent/editcountry/editneighbor) or savemap/validatemap/editmap/loadmap/showmap command");
                    break;
            }
        }
        else if (d_GameGamePhase.equals(GamePhase.STARTUP)) {
            switch (l_commandName) {
                case "gameplayer":
                    l_checkplayer=true;
                    setPhase(new StartUpPhase(this));
                    d_LogEntry.setGamePhase(d_GamePhases);
                    d_LogEntry.setCommand("-------- "+l_commandName+" Command is being executed!!! --------\n");
                    d_GamePhases.gamePlayer(l_data,d_Players, l_playerName);
                    String str= d_GamePhases.getD_PhaseName();
                    System.out.println(str);
                    break;

                case "assigncountries":
                    if(!l_checkplayer){
                        System.out.println("----- Before assigning territories add players : use gameplayer -add  ----- ");
                        d_LogEntry.setMessage("----- Before assigning territories add players : use gameplayer -add  ----- ");
                        break;
                    }
                    else {
                        boolean l_check = d_GamePhases.assignCountries(d_Map, d_Players);
                        if (l_check) {
                            System.out.println("----- Territories have been randomly allocated to the players  ----- ");
                            d_LogEntry.setMessage("----- Territories have been randomly allocated to the players  ----- ");
                            setPhase(new MainPlayPhase(this));
                            d_LogEntry.setGamePhase(d_GamePhases);
                            d_LogEntry.setCommand("-------- " + l_commandName + " Command is being executed!!! --------\n");
                            d_GamePhases.reinforce();
                            String str1 = d_GamePhases.getD_PhaseName();
                            System.out.println(str1);
                        }
                        d_GameGamePhase = GamePhase.ISSUE_ORDERS;
                        break;
                    }

                case "showmap":
                    d_GamePhases.showMap(d_Players,d_Map);
                    String str2= d_GamePhases.getD_PhaseName();
                    System.out.println(str2);
                    d_LogEntry.setGamePhase(d_GamePhases);
                    d_LogEntry.setCommand("-------- "+l_commandName+" Command is being executed!!! --------\n");
                    break;
                case "savegame":
                    try{
                        if(l_data.length == 2){
                            if(isAlphabetic(l_data[1])) {
                                String fileName = l_data[1];
                                d_RunG.saveGame(this.d_Game, fileName);
                                System.out.println("---- current game is saved successfully ----");
                                d_LogEntry.setMessage("---- current Game is saved successfully ----");
                            }
                        }else{
                            String message = "---- It is not valid command so enter correct file name to save a game ----";
                            d_LogEntry.setMessage("---- It is not valid command so enter correct file name to save a game ----");
                        }

                    }catch (ArrayIndexOutOfBoundsException e){
                        String message = "---- It is not valid command so It should be in the form of 'savegame filename' ----";
                        d_LogEntry.setMessage("---- It is not valid command so It should be in the form of 'savegame filename' ----");
                    }
                    break;

                default:
                    System.out.println("----- Invalid command format. Should be:| gameplayer command | assignterritories command | showmap command | in the STARTUP Phase -----");
                    break;
            }
        }

        else if (d_GameGamePhase.equals(GamePhase.ISSUE_ORDERS)) {
            int l_counter = 0;
            Iterator<Player> l_iterator = d_Players.listIterator();
            while(l_iterator.hasNext()) {
                Player l_p = l_iterator.next();
                System.out.println("----- Player "+l_p.getPlayerName()+" has "+l_p.getOwnedArmies()+" Armies !!!!! -----");
                if (l_p.getOwnedArmies() > 0) {
                    l_counter = l_counter + l_p.getOwnedArmies();
                }
            }
            System.out.println("----- Combined armies remaining across all players in the pool "+l_counter+" -----");
            if(!p_player.getD_Deck().isEmpty()){
                p_player.showCards();
            }
            if(l_counter >= 0){
                if(!p_player.getD_isHuman()){
                    p_player.issueOrder();
                    d_GameGamePhase = GamePhase.TURN;
                    return d_GameGamePhase;
                }else
                {
                switch (l_commandName) {

                    case "deploy":
                        try {
                            if (!(l_data[1] == null) || !(l_data[2] == null)) {
                                if (GameHelper.isNumeric(l_data[1]) || GameHelper.isNumeric(l_data[2])) {
                                    l_territoryId = l_data[1];

                                    l_numberOfArmies = Integer.parseInt(l_data[2]);

                                    boolean l_checkOwnedTerritory = p_player.getOwnedTerritories().containsKey(l_territoryId.toLowerCase());
                                    boolean l_checkArmies = (p_player.getOwnedArmies() >= l_numberOfArmies);
                                    System.out.println("----- Player "+p_player.getPlayerName()+"  Can provide deploy order or pass order ----");
                                    if(l_checkOwnedTerritory && l_checkArmies) {
                                        Order l_temp = new Order(p_player, l_territoryId, l_numberOfArmies);
                                        p_player.addOrder(l_temp);
                                        p_player.issue_order();
                                        p_player.setOwnedArmies(p_player.getOwnedArmies()-l_numberOfArmies);
                                        System.out.println("----- Player " + p_player.getPlayerName() + " currently retains " +p_player.getOwnedArmies()+ " army units ----");
                                    }
                                    else{
                                        System.out.println("--- Pass to next player if no territory owned or insufficient army units ---- \n");
                                    }
                                    d_GameGamePhase = GamePhase.TURN;
                                    break;
                                }
                            } else
                                System.out.println("------ Command not valid ----- ");

                        }catch (Exception e) {
                            System.out.println("--- Pass to next player if no territory owned or insufficient army units ---- \n");
                        }
                        break;

                    case "advance":
                        d_LogEntry.setCommand("----"+l_commandName+" Command is being executed-----");
                        try {
                            if (!(l_data[1] == null) || !(l_data[2] == null) || !(l_data[3] == null)) {
                                if (GameHelper.isAlphabetic(l_data[1]) || GameHelper.isAlphabetic(l_data[2]) || GameHelper.isNumeric(l_data[3])) {
                                    l_territoryNameFrom = l_data[1];
                                    System.out.println("-----Advance Command is being executed-----");
//                                    System.out.println("L_data");
                                    System.out.println(l_data);
                                    l_territoryNameTo = l_data[2];
                                    l_numberOfArmies = Integer.parseInt(l_data[3]);
                                    boolean l_checkOwnedTerritory = p_player.getOwnedTerritories().containsKey(l_territoryNameFrom.toLowerCase());
                                    TerritoryDetails attackingTerritory = p_player.getOwnedTerritories().get(l_territoryNameFrom.toLowerCase());
                                    TerritoryDetails defendingTerritory = attackingTerritory.getNeighbours().get(l_territoryNameTo.toLowerCase());
                                    boolean l_checkNeighbourTerritory = (l_territoryNameTo.equals(defendingTerritory.getTerritoryID()));
                                    TerritoryDetails l_c= p_player.getOwnedTerritories().get(l_territoryNameFrom.toLowerCase());
                                    int l_existingArmies = l_c.getNumberOfArmies();

                                    Player l_targetPlayer=null;
                                    for(Player temp : d_Players){
                                        if(temp.getOwnedTerritories().containsKey(l_territoryNameTo.toLowerCase())){
                                            System.out.println("For loop");
                                            l_targetPlayer = temp;
                                            System.out.println("---- Traget player for loop"+l_targetPlayer+" ----");
                                            break;
                                        }
                                    }

                                    boolean l_checkArmies = (l_existingArmies >= l_numberOfArmies);
                                    if(l_checkOwnedTerritory && l_checkNeighbourTerritory && l_checkArmies){
                                        p_player.addOrder(new Advance(p_player, l_territoryNameFrom,l_territoryNameTo, l_numberOfArmies,l_targetPlayer));
                                        p_player.issue_order();
                                        d_LogEntry.setMessage("----"+p_player.getPlayerName()+" advance order added to players orders list: "+l_data[0]+"  "+l_data[1]+" "+l_data[2]+"----");
                                    }
                                    else{
                                        System.out.println("---- Territory is not owned by player or target Territory not adjacent or insufficient Army units | please pass to next player ----");
                                        d_LogEntry.setMessage("---- Territory is not owned by player or target Territory not adjacent or insufficient Army units | please pass to next player ----");
                                    }
                                    d_GameGamePhase = GamePhase.TURN;
                                    break;
                                }
                            } else
                                System.out.println("---- It is invalid Command ----");
                            d_LogEntry.setMessage("---- It is invalid Command ----");
                        }catch (Exception e) {
                            System.out.println("---- Territory is not owned by player or target Territory not adjacent or insufficient Army units | please pass to next player ----");
                            d_LogEntry.setMessage("---- Territory is not owned by player or target Territory not adjacent or insufficient Army units | please pass to next player ----");
                        }
                        break;

                    case "bomb":
                        d_LogEntry.setCommand("----"+l_commandName+" Command is being executed ----");
                        try {
                            if (!(l_data[1] == null)) {
                                if (GameHelper.isAlphabetic(l_data[1])) {
                                    l_territoryID = l_data[1];
                                    boolean l_checkOwnedTerritoryNotOfCurrent = !p_player.getOwnedTerritories().containsKey(l_territoryID.toLowerCase());
                                    boolean targetTerritoryNeighbour = false;
                                    for(TerritoryDetails cD : p_player.getOwnedTerritories().values()){
                                        if( cD.getNeighbours().containsKey(l_territoryID.toLowerCase()) && !p_player.getOwnedTerritories().containsKey(l_territoryID.toLowerCase())){
                                            targetTerritoryNeighbour= true;
                                            break;
                                        }
                                    }
                                    Player targetPlayer = null;
                                    for(Player temp : d_Players){
                                        if(temp.getOwnedTerritories().containsKey(l_territoryID.toLowerCase())){
                                            targetPlayer = temp;
                                            break;
                                        }
                                    }
                                    boolean checkCard = p_player.doesCardExists("Bomb");
                                    if(l_checkOwnedTerritoryNotOfCurrent && targetTerritoryNeighbour && checkCard){
                                        p_player.addOrder(new Bomb(p_player,targetPlayer, l_territoryID));
                                        p_player.issue_order();
                                        d_LogEntry.setMessage("----"+p_player.getPlayerName()+" Bomb order added to Players OrdersList: "+l_data[0]+"  "+l_data[1]+"----");
                                        p_player.removeCard("Bomb");
                                        d_LogEntry.setMessage("---- Bomb card removed from players card list ----");
                                    }
                                    else{
                                        System.out.println("---- Bomb Card not Owned or Territory owned by current player or target Territory not adjacent | please pass to next player ----");
                                        d_LogEntry.setMessage("---- Bomb Card not Owned or Territory owned by current player or target Territory not adjacent | please pass to next player ----");
                                    }
                                    d_GameGamePhase = GamePhase.TURN;
                                    break;
                                }
                            } else
                                System.out.println("---- It is invalid Command ----");
                            d_LogEntry.setMessage("---- It is invalid Command ----");
                        }catch (Exception e) {
                            System.out.println("---- Bomb Card not Owned or Territory owned by current player or target Territory not adjacent | please pass to next player ----");
                            d_LogEntry.setMessage("---- Bomb Card not Owned or Territory owned by current player or target Territory not adjacent | please pass to next player ----");
                        }
                        break;

                    case "blockade":
                        d_LogEntry.setCommand("----"+l_commandName+" Command is being executed ----");
                        try {
                            if (!(l_data[1] == null)) {
                                if (GameHelper.isAlphabetic(l_data[1])) {
                                    l_territoryID = l_data[1];
                                    boolean l_checkOwnedTerritory = p_player.getOwnedTerritories().containsKey(l_territoryID.toLowerCase());
                                    boolean checkCard = p_player.doesCardExists("Blockade");
                                    if(l_checkOwnedTerritory && checkCard){
                                        p_player.addOrder(new Blockade(p_player, l_territoryID));
                                        p_player.issue_order();
                                        d_LogEntry.setMessage("---- "+p_player.getPlayerName()+" Blockade order added to Players OrdersList: "+l_data[0]+"  "+l_data[1]+" ----");
                                        p_player.removeCard("Blockade");
                                        d_LogEntry.setMessage("---- Bloackade card removed from Player's cardList ----");
                                    }
                                    else{
                                        System.out.println("---- Blockade Card not Owned or Territory not owned by current player | please pass to next player ----");
                                        d_LogEntry.setMessage("---- Blockade Card not Owned or Territory not owned by current player | please pass to next player ----");
                                    }
                                    d_GameGamePhase = GamePhase.TURN;
                                    break;
                                }
                            } else
                                System.out.println("---- It is invalid Command ----");
                            d_LogEntry.setMessage("---- It is invalid Command ----");
                        }catch (Exception e) {
                            System.out.println("---- Blockade Card not Owned or Territory not owned by current player | please pass to next player ----");
                            d_LogEntry.setMessage("---- Blockade Card not Owned or Territory not owned by current player | please pass to next player ----");
                        }
                        break;

                    case "airlift":
                        d_LogEntry.setCommand("----"+l_commandName+" Command is being executed ----");
                        try {
                            if (!(l_data[1] == null) || !(l_data[2] == null) || !(l_data[3] == null)) {
                                if (GameHelper.isAlphabetic(l_data[1]) || GameHelper.isAlphabetic(l_data[2]) || GameHelper.isNumeric(l_data[3])) {
                                    l_sourceterritoryId = l_data[1];
                                    l_targetterritoryId = l_data[2];
                                    l_numberOfArmies = Integer.parseInt(l_data[3]);
                                    boolean l_checkOwnedTerritory = p_player.getOwnedTerritories().containsKey(l_sourceterritoryId.toLowerCase());
                                    boolean l_checkTargetOwnedTerritory = p_player.getOwnedTerritories().containsKey(l_targetterritoryId.toLowerCase());
                                    TerritoryDetails l_c= p_player.getOwnedTerritories().get(l_sourceterritoryId.toLowerCase());
                                    int l_existingArmies = l_c.getNumberOfArmies();
                                    boolean l_checkArmies = (l_existingArmies >= l_numberOfArmies);
                                    boolean checkCard = p_player.doesCardExists("Airlift");
                                    if(l_checkOwnedTerritory && l_checkTargetOwnedTerritory && l_checkArmies && checkCard){
                                        p_player.addOrder(new Airlift(p_player, l_sourceterritoryId, l_targetterritoryId, l_numberOfArmies));
                                        p_player.issue_order();
                                        d_LogEntry.setMessage("----"+p_player.getPlayerName()+" Airlift order added to Players OrdersList: "+l_data[0]+"  "+l_data[1]+" "+l_data[2]+" "+l_data[3]+"----");
                                        p_player.removeCard("Airlift");
                                        d_LogEntry.setMessage("---- Airlift card removed from Player's cardList ----");
                                    }
                                    else{
                                        System.out.println("---- Airlift Card not Owned or Source Territory or Target Territory not owned by player insufficient Army units | please pass to next player----");
                                        d_LogEntry.setMessage("---- Airlift Card not Owned or Source Territory or Target Territory not owned by player insufficient Army units | please pass to next player ----");
                                    }
                                    d_GameGamePhase = GamePhase.TURN;
                                    break;
                                }
                            } else
                                System.out.println("---- It is invalid Command ----");
                            d_LogEntry.setMessage("---- It is invalid Command ----");
                        }catch (Exception e) {
                            System.out.println("---- Airlift Card not Owned or Source Territory or Target Territory not owned by player insufficient Army units | please pass to next player ----");
                            d_LogEntry.setMessage("---- Airlift Card not Owned or Source Territory or Target Territory not owned by player insufficient Army units | please pass to next player ----");
                        }
                        break;
                    case "negotiate":
                        d_LogEntry.setCommand("----"+l_commandName+" Command is being executed ----");
                        try {
                            if (!(l_data[1] == null)){
                                if (GameHelper.isAlphabetic(l_data[1])) {
                                    Player l_NegPlayer = getPlayerByName(l_data[1]);
                                    boolean checkCard = p_player.doesCardExists("Diplomacy");
                                    if(checkCard){
                                        p_player.addOrder(new Diplomacy(p_player, l_NegPlayer));
                                        p_player.issue_order();
                                        d_LogEntry.setMessage("---- "+p_player.getPlayerName()+" Diplomacy order added to Players OrdersList: "+l_data[0]+"  "+l_data[1]+" "+l_data[2]+" "+l_data[3]+"----");
                                        p_player.removeCard("Diplomacy");
                                        d_LogEntry.setMessage("---- Diplomacy card removed from Player's cardList ----");
                                    }
                                }
                            } else
                                System.out.println("---- Diplomacy Card not Owned or Invalid Command ----");
                            d_LogEntry.setMessage("---- Diplomacy Card not Owned or Invalid Command ----");
                        }catch (Exception e) {
                            System.out.println("----Diplomacy Card not Owned or Invalid Player name----");
                            d_LogEntry.setMessage("----Diplomacy Card not Owned or Invalid Player name----");
                        }
                        break;
                    case "execute":
                        int l_count = 0;
                        for (Player l_p : d_Players) {
                            Queue<order1> l_temp = l_p.getD_orderList();
                            l_count = l_count +l_temp.size();
                        }

                        if(l_count == 0){
                            System.out.println(" -----  Orders already executed !!!  ----- ");
                            ShowMap.showMap(d_Players, d_Map);
                            d_GameGamePhase = GamePhase.ISSUE_ORDERS;
                            return d_GameGamePhase;
                        }
                        else{
                            System.out.println(" -----  Total Orders  :" + l_count + " -----");
                            while (l_count != 0) {
                                for (Player l_p : d_Players) {

                                    Queue<order1> l_temp = l_p.getD_orderList();
                                    if (l_temp.size() > 0) {
                                        order1 l_toRemove = l_p.next_order();
                                        System.out.println("----- Order: " +l_toRemove+ " executed for player: "+l_p.getPlayerName()+ " -----");
                                        l_toRemove.execute();
                                    }
                                }
                                l_count--;
                            }

                            System.out.println("-----  Orders executed!!! -----");
                            ShowMap.showMap(d_Players, d_Map);
                            d_GameGamePhase = GamePhase.ISSUE_ORDERS;
                            for (Player l_p : d_Players){
                                if(l_p.getOwnedTerritories().size() == d_Map.getTerritories().size()){
                                    System.out.println("----"+l_p.getPlayerName()+" has Won the Game!!! ----");
                                    d_LogEntry.setMessage("---- "+l_p.getPlayerName()+" has Won the Game!!! ----");
                                    d_LogEntry.detach(d_WriteLog);
                                    System.exit(0);
                                }
                            }
                            for (Player l_p : d_Players){
                                if(l_p.getOwnedTerritories().size() == 0){
                                    System.out.println("----"+l_p.getPlayerName()+" has lost all its territories and is no longer part of the game ----");
                                    d_LogEntry.setMessage("---- "+l_p.getPlayerName()+" has lost all its territories and is no longer part of the game ----");
                                    d_Players.remove(l_p);
                                }
                            }


                            System.out.println("---- Current Orders were executed and this is starting again with assigning Reinforcements!!! ----");
                            System.out.println("---- Reinforcements assigned!!! Players can provide deploy Orders now!!! ----");
                            System.out.println("\n---- next Player 1 can provide deploy | pass order ----");
                            d_LogEntry.setMessage("---- Current Orders were executed and this is starting again with assigning Reinforcements!!! ----");
                            d_LogEntry.setMessage("---- Reinforcements assigned!!! Players can provide deploy Orders now!!! ----");
                            d_LogEntry.setMessage("\n---- next Player 1 can provide deploy | pass order ----");

                            d_GameGamePhase = GamePhase.ISSUE_ORDERS;
                        }
                        break;
                    case "stop":
                        if(l_counter > 0) {
                            System.out.println("---- There are still certain armies left to be deployed!!! ----");
                            d_GameGamePhase = GamePhase.ISSUE_ORDERS;
                        }else {
                            System.out.println("---- It is getting you to execution phase ----");
                            d_GameGamePhase = GamePhase.EXECUTE_ORDERS;
                        }
                        break;
                    case "savegame":
                        try {
                            if (l_data.length == 2) {
                                if (isAlphabetic(l_data[1])) {
                                    String fileName = l_data[1];
                                    setGame();
                                    boolean l_save=d_RunG.saveGame(this.d_Game, fileName);
                                    if(l_save) {
                                        System.out.println("---- current game is saved successfully ----");
                                        d_LogEntry.setMessage("---- current game is saved successfully ----");
                                    }else {
                                        System.out.println("---- current game is not saved successfully ----");
                                        d_LogEntry.setMessage("---- current game is not saved successfully ----");
                                    }
                                }
                            } else {
                                String message = "---- It is not valid command so enter correct file name to save a game ----";
                                d_LogEntry.setMessage("---- It is not valid command so enter correct file name to save a game ----");
                            }

                        } catch (ArrayIndexOutOfBoundsException e) {
                            String message = "---- It is not valid command so It should be in the form of 'savegame filename' ----";
                            d_LogEntry.setMessage("---- It is not valid command so It should be in the form of 'savegame filename' ----");
                        }
                        break;

                    case "pass":
                        try {
                            d_GameGamePhase = GamePhase.TURN;
                        }catch (Exception e) {
                            System.out.println("----- Invalid command format. Should be: | deploy territoryID num | pass | ----- ");
                        }
                        break;

                    case "showmap":
                        d_GamePhases.showMap(d_Players, d_Map);
                        break;

                    default:
                        System.out.println("----- Invalid command - either use deploy | pass | showmap commands in ISSUE_ORDERS InternalPhase -------");
                        d_LogEntry.setMessage("------ Invalid command - either use deploy | pass | showmap commands in ISSUE_ORDERS InternalPhase -------");
                        break;
                }
            }
            }
            else{
                System.out.println(" -----  Press ENTER to  continue  OR  execute  Phase. ----- ");
                d_GameGamePhase = GamePhase.EXECUTE_ORDERS;
                return d_GameGamePhase;
            }
        }
        else if (d_GameGamePhase.equals(GamePhase.EXECUTE_ORDERS)) {
            if(!p_player.getD_isHuman()){
                int l_count = 0;
                for (Player l_p : d_Players) {
                    Queue<order1> l_temp = l_p.getD_orderList();
                    l_count = l_count +l_temp.size();
                }

                if(l_count == 0){
                    System.out.println(" -----  Orders already executed !!!  ----- ");
                    d_LogEntry.setMessage(" -----  Orders already executed !!!  ----- ");
                    d_GamePhases.showMap(d_Players, d_Map);
                    d_GameGamePhase = GamePhase.ISSUE_ORDERS;
                    return d_GameGamePhase;
                }
                else{
                    System.out.println(" -----  Total Orders  :" + l_count + " -----");
                    d_LogEntry.setMessage(" -----  Total Orders  :" + l_count + " -----");
                    while (l_count != 0) {
                        for (Player l_p : d_Players) {
                            Queue<order1> l_temp = l_p.getD_orderList();
                            if (l_temp.size() > 0) {
                                order1 l_toRemove = l_p.next_order();
                                System.out.println("----- Order: " +l_toRemove+ " executed for player: "+l_p.getPlayerName()+ " -----");
                                d_LogEntry.setMessage("----- Order: " +l_toRemove+ " executed for player: "+l_p.getPlayerName()+ " -----");
                                l_toRemove.execute();
                            }
                        }
                        l_count--;
                    }
                    for(Player l_p : d_Players) {
                        l_p.flushNegotiators();
                    }
                    System.out.println(" -----  Orders already executed !!!  ----- ");
                    d_LogEntry.setMessage(" -----  Orders already executed !!!  ----- ");
                    //d_Phase.showMap(d_Players, d_Map);
                    d_GamePhases.reinforce();
                    for (Player l_p : d_Players){
                        if(l_p.getOwnedCountries().size() == d_Map.getTerritories().size()){
                            System.out.println("---- "+l_p.getPlayerName()+" has Won the Game!!! ----");
                            d_LogEntry.setMessage("---- "+l_p.getPlayerName()+" has Won the Game!!! ----");
                            d_LogEntry.detach(d_WriteLog);
                            System.exit(0);
                        }
                    }
                    for (Player l_p : d_Players){
                        if(l_p.getOwnedCountries().size() == 0){
                            System.out.println("---- "+l_p.getPlayerName()+" has lost all its territories and is no longer part of the game ----");
                            d_LogEntry.setMessage("---- "+l_p.getPlayerName()+" has lost all its territories and is no longer part of the game ----");
                            d_Players.remove(l_p);
                        }
                    }

                    System.out.println("---- Current Orders were executed and this is starting again with assigning Reinforcements!!! ----");
                    System.out.println("---- Reinforcements assigned!!! Players can provide deploy Orders now!!! ----");
                    d_LogEntry.setMessage("---- Current Orders were executed and this is starting again with assigning Reinforcements!!! ----");
                    d_LogEntry.setMessage("---- Reinforcements assigned!!! Players can provide deploy Orders now!!! ----");

                    d_GameGamePhase = GamePhase.ISSUE_ORDERS;
                }

                return d_GameGamePhase;
            }
            switch (l_commandName) {
                case "execute":
                    int l_count = 0;
                    for (Player l_p : d_Players) {
                        Queue<order1> l_temp = l_p.getD_orderList();
                        l_count = l_count +l_temp.size();
                    }

                    if(l_count == 0){
                        System.out.println(" -----  Orders already executed !!!  ----- ");
                        ShowMap.showMap(d_Players, d_Map);
                        d_GameGamePhase = GamePhase.ISSUE_ORDERS;
                        return d_GameGamePhase;
                    }
                    else{
                        System.out.println(" -----  Total Orders  :" + l_count + " -----");
                        while (l_count != 0) {
                            for (Player l_p : d_Players) {
                                Queue<order1> l_temp = l_p.getD_orderList();
                                if (l_temp.size() > 0) {
                                    order1 l_toRemove = l_p.next_order();
                                    System.out.println("----- Order: " +l_toRemove+ " executed for player: "+l_p.getPlayerName()+ " -----");
                                    l_toRemove.execute();
                                }
                            }
                            l_count--;
                        }

                        System.out.println("-----  Orders executed!!! -----");
                        for (Player l_p : d_Players){
                            if(l_p.getOwnedCountries().size() == d_Map.getTerritories().size()){
                                System.out.println("---- "+l_p.getPlayerName()+" has Won the Game!!! ----");
                                d_LogEntry.setMessage("---- "+l_p.getPlayerName()+" has Won the Game!!! ----");
                                d_LogEntry.detach(d_WriteLog);
                                System.exit(0);
                            }
                        }
                        ShowMap.showMap(d_Players, d_Map);

                        for (Player l_p : d_Players){
                            if(l_p.getOwnedCountries().size() == 0){
                                System.out.println("---- "+l_p.getPlayerName()+" has lost all its territories and is no longer part of the game ----");
                                d_LogEntry.setMessage("---- "+l_p.getPlayerName()+" has lost all its territories and is no longer part of the game ----");
                                d_Players.remove(l_p);
                            }
                        }

                        System.out.println("---- Current Orders were executed and this is starting again with assigning Reinforcements!!! ----");
                        System.out.println("---- Reinforcements assigned!!! Players can provide deploy Orders now!!! ----");
                        System.out.println("\n---- next Player can provide deploy | pass order ----");
                        d_LogEntry.setMessage("---- Current Orders were executed and this is starting again with assigning Reinforcements!!! ----");
                        d_LogEntry.setMessage("---- Reinforcements assigned!!! Players can provide deploy Orders now!!! ----");
                        d_LogEntry.setMessage("\n---- next Player can provide deploy | pass order ----");

                        d_GameGamePhase = GamePhase.ISSUE_ORDERS;
                    }
                    break;

                case "showmap":
                    ShowMap.showMap(d_Players, d_Map);
                    break;

                case "exit":
                    System.out.println(" ----- [ END OF BUILD 3 ] ------- ");
                    exit(0);

                default:
                    System.out.println("----- The Order Phase has begun, proceed with either displaying the map (showmap) or executing orders (execute) ---- ");
                    break;
            }
        }
        return d_GameGamePhase;
    }
    private Player getPlayerByName(String p_playerName) {
        for(Player l_player:d_Players) {
            if(l_player.getPlayerName().equals(p_playerName))
                return l_player;
        }
        return null;
    }
}
