package model;

import controller.Game;
import controller.GameHelper;
import enumClass.GamePhase;

/**
 * Class for loading
 */
public class Load extends EditGame
{

    ShowMap mv;

    /**
     * Load constructor
     * @param p_ge Game
     */
    public Load(Game p_ge)
    {
        d_gameinstance = p_ge;
        d_PhaseName = "PreLoad";
        mv=new ShowMap();
    }

    /**
     * edit continent function
     */
    public void editContinent(String[] p_data, String p_continentId, int p_controlValue) {
        try {
            d_gameinstance.d_LogEntry.setMessage("Command given by user:"+p_data[0]);
            for (int i = 1; i < p_data.length; i++) {
                if (p_data[i].equals("-add")) {
                    if (GameHelper.isAlphabetic(p_data[i + 1])) {
                        p_continentId = p_data[i + 1];
                        d_gameinstance.d_LogEntry.setMessage(" "+p_data[i]+" "+p_continentId);
                    } else {
                        System.out.println("Invalid Continent ID");
                        d_gameinstance.d_LogEntry.setMessage("Invalid Continent ID");
                        d_gameinstance.d_LogEntry.setMessage(" "+p_data[i]);
                    }
                    p_controlValue = Integer.parseInt(p_data[i + 2]);

                    boolean l_check = d_gameinstance.d_RunG.addContinent(d_gameinstance.d_Map, p_continentId, p_controlValue);
                    if (l_check) {
                        System.out.println(p_continentId + " continent added to the map");
                        d_gameinstance.d_LogEntry.setMessage(p_continentId + " continent added to the map");
                        d_gameinstance.d_GameGamePhase = GamePhase.EDITMAP;
                        d_gameinstance.setGamePhase(d_gameinstance.d_GameGamePhase);
                    } else {
                        System.out.println("Continent already exists - Please add valid Continent ID");
                        d_gameinstance.d_LogEntry.setMessage("Continent already exists - Please add valid Continent ID");
                    }
                } else if (p_data[i].equals("-remove")) {
                    if (GameHelper.isAlphabetic(p_data[i + 1])) {
                        p_continentId = p_data[i + 1];
                        d_gameinstance.d_LogEntry.setMessage(" "+p_data[i]);
                    } else
                        System.out.println("Invalid Continent Id");
                    d_gameinstance.d_LogEntry.setMessage("Invalid Continent ID");

                    boolean l_check = d_gameinstance.d_RunG.removeContinent(d_gameinstance.d_Map, p_continentId);
                    if (l_check) {
                        System.out.println("Continent removed from Map");
                        d_gameinstance.d_LogEntry.setMessage(p_continentId+" Continent removed from Map");
                        d_gameinstance.d_GameGamePhase = GamePhase.EDITMAP;
                        d_gameinstance.setGamePhase(d_gameinstance.d_GameGamePhase);
                    } else
                        System.out.println("Continent doesn't exist - Please enter valid Continent ID");
                    d_gameinstance.d_LogEntry.setMessage("Continent doesn't exist - Please enter valid Continent ID");
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid command - It should be of the form editcontinent -add continentID controlvalue -remove continentID");
            d_gameinstance.d_LogEntry.setMessage("Invalid command - It should be of the form editcontinent -add continentID controlvalue -remove continentID");
        } catch (Exception e) {
            System.out.println("Invalid command - it should be of the form editcontinent -add continentID controlvalue -remove continentID");
            d_gameinstance.d_LogEntry.setMessage("Invalid command - it should be of the form editcontinent -add continentID controlvalue -remove continentID");
        }
    }

    /**
     * edit country function
     */
    public void editCountry(String[] p_data, String p_continentId, String p_territoryId) {
        try {
            d_gameinstance.d_LogEntry.setMessage("Command given by user:"+p_data[0]);
            for (int i = 1; i < p_data.length; i++) {
                if (p_data[i].equals("-add")) {
                    if (GameHelper.isAlphabetic(p_data[i + 1]) || GameHelper.isAlphabetic(p_data[i + 2])) {
                        p_territoryId = p_data[i + 1];
                        p_continentId = p_data[i + 2];
                        d_gameinstance.d_LogEntry.setMessage(" "+p_data[i]+" "+p_territoryId+" "+p_continentId);
                    } else {
                        System.out.println("Invalid Territory name");
                        d_gameinstance.d_LogEntry.setMessage("Invalid Continent ID");
                    }
                    boolean l_check = d_gameinstance.d_RunG.addTerritory(d_gameinstance.d_Map, p_territoryId, p_continentId);
                    if (l_check) {
                        System.out.println(p_territoryId+" Territory added to the map "+p_continentId);
                        d_gameinstance.d_LogEntry.setMessage(p_territoryId+" Territory added to the map "+p_continentId);
                        d_gameinstance.d_GameGamePhase = GamePhase.EDITMAP;
                        d_gameinstance.setGamePhase(d_gameinstance.d_GameGamePhase);
                    } else {
                        System.out.println("Territory already exists - Please add valid Territory ID");
                        d_gameinstance.d_LogEntry.setMessage("Territory already exists - Please add valid Territory ID");
                    }
                } else if (p_data[i].equals("-remove")) {
                    if (GameHelper.isAlphabetic(p_data[i + 1])) {
                        p_territoryId = p_data[i + 1];
                        d_gameinstance.d_LogEntry.setMessage(" "+p_data[i]+" "+p_territoryId);
                    } else {
                        System.out.println("Invalid Territory name");
                        d_gameinstance.d_LogEntry.setMessage("Invalid Territory name");
                    }
                    boolean l_check = d_gameinstance.d_RunG.removeTerritory(d_gameinstance.d_Map, p_territoryId);
                    if (l_check) {
                        System.out.println(p_territoryId+" Territory removed from the map");
                        d_gameinstance.d_LogEntry.setMessage(p_territoryId+" Territory removed from the map");
                        d_gameinstance.d_GameGamePhase = GamePhase.EDITMAP;
                        d_gameinstance.setGamePhase(d_gameinstance.d_GameGamePhase);
                    } else {
                        System.out.println("Territory does not exist - Please enter valid Territory name");
                        d_gameinstance.d_LogEntry.setMessage("Territory does not exist - Please enter valid Territory name");
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command - it should be of the form editterritory -add territoryId continentId -remove territoryId");
            d_gameinstance.d_LogEntry.setMessage("Invalid command - it should be of the form editterritory -add territoryId continentId -remove territoryId");
        } catch (Exception e) {
            System.out.println("Invalid command - it should be of the form editterritory -add territoryId continentId -remove territoryId");
            d_gameinstance.d_LogEntry.setMessage("Invalid command - it should be of the form editterritory -add territoryId continentId -remove territoryId");
        }
    }

    
    /** edit neighbour function
     * @param p_data Command
     * @param p_territoryId Territory id
     * @param p_neighborTerritoryId Neighbour territory id
     */
    public void editNeighbour(String[] p_data, String p_territoryId, String p_neighborTerritoryId) {
        try {
            d_gameinstance.d_LogEntry.setMessage("Command given by user:"+p_data[0]);
            for (int i = 1; i < p_data.length; i++) {
                if (p_data[i].equals("-add")) {
                    if (GameHelper.isAlphabetic(p_data[i + 1]) || GameHelper.isAlphabetic(p_data[i + 2])) {
                        p_territoryId = p_data[i + 1];
                        p_neighborTerritoryId = p_data[i + 2];
                        d_gameinstance.d_LogEntry.setMessage(" "+p_data[i]+" "+p_territoryId+" "+p_neighborTerritoryId);
                    } else {
                        System.out.println("Invalid Territory ID");
                        d_gameinstance.d_LogEntry.setMessage("Invalid Territory ID");
                    }

                    boolean l_check = d_gameinstance.d_RunG.addNeighbour(d_gameinstance.d_Map, p_territoryId, p_neighborTerritoryId);
                    if (l_check) {
                        d_gameinstance.d_GameGamePhase = GamePhase.EDITMAP;
                        d_gameinstance.setGamePhase(d_gameinstance.d_GameGamePhase);
                    } else {
                        System.out.println("Territory does not exist - Please enter valid TerritoryID neighborterritoryID");
                        d_gameinstance.d_LogEntry.setMessage("Territory does not exist - Please enter valid TerritoryID neighborterritoryID");
                    }
                } else if (p_data[i].equals("-remove")) {
                    if (GameHelper.isAlphabetic(p_data[i + 1]) || GameHelper.isAlphabetic(p_data[i + 2])) {
                        p_territoryId = p_data[i + 1];
                        p_neighborTerritoryId = p_data[i + 2];
                        d_gameinstance.d_LogEntry.setMessage(" "+p_data[i]+" "+p_territoryId+" "+p_neighborTerritoryId);
                    } else {
                        System.out.println("Invalid Territory ID");
                        d_gameinstance.d_LogEntry.setMessage("Invalid Territory ID");
                    }

                    boolean l_check = d_gameinstance.d_RunG.removeNeighbour(d_gameinstance.d_Map, p_territoryId, p_neighborTerritoryId);
                    if (l_check) {
                        d_gameinstance.d_GameGamePhase = GamePhase.EDITMAP;
                        d_gameinstance.setGamePhase(d_gameinstance.d_GameGamePhase);
                    } else {
                        System.out.println("Territory does not exist - Please enter valid TerritoryID neighborterritoryID");
                        d_gameinstance.d_LogEntry.setMessage("Territory does not exist - Please enter valid TerritoryID neighborterritoryID");
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command - it should be of the form editneighbor -add territoryID neighborterritoryID -remove territoryID neighborterritoryID");
            d_gameinstance.d_LogEntry.setMessage("Invalid command - it should be of the form editneighbor -add territoryID neighborterritoryID -remove territoryID neighborterritoryID");
        } catch (Exception e) {
            System.out.println("Invalid command - it should be of the form editneighbor -add territoryID neighborterritoryID -remove territoryID neighborterritoryID");
            d_gameinstance.d_LogEntry.setMessage("Invalid command - it should be of the form editneighbor -add territoryID neighborterritoryID -remove territoryID neighborterritoryID");
        }
    }

    /**
     * Save map function
     */

    public void savemap(String[] p_data, String p_mapName) {
        try {
            d_gameinstance.d_LogEntry.setMessage("Command given by user:"+p_data[0] + " " +p_data[1]);
            if (p_data[1] != "") {
                if (GameHelper.isValidMap(p_data[1])) {
                    p_mapName = p_data[1];
                    boolean l_check = d_gameinstance.d_RunG.saveMap(d_gameinstance.d_Map, p_mapName);
                    if (l_check) {
                        System.out.println("Map file saved successfully");
                        d_gameinstance.d_LogEntry.setMessage("Map file saved successfully");
                        d_gameinstance.d_GameGamePhase = GamePhase.NULL;
                        d_gameinstance.setGamePhase(d_gameinstance.d_GameGamePhase);
                    } else {
                        System.out.println("Error in saving - invalid map");
                        d_gameinstance.d_LogEntry.setMessage("Error in saving - invalid map");
                    }
                } else {
                    System.out.println("Map name not valid!");
                    d_gameinstance.d_LogEntry.setMessage("Map name not valid!");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command - it should be of the form(without extension) savemap filename");
            d_gameinstance.d_LogEntry.setMessage("Invalid command - it should be of the form(without extension) savemap filename");
        } catch (Exception e) {
            System.out.println("Invalid command - it should be of the form(without extension) savemap filename");
            d_gameinstance.d_LogEntry.setMessage("Invalid command - it should be of the form(without extension) savemap filename");
        }
    }

    public void editMap(String[] p_data, String p_mapName) {
        try {
            d_gameinstance.d_LogEntry.setMessage("Command given by user:"+p_data[0] + " " +p_data[1]);
            if (p_data[1] != null) {
                if (GameHelper.isValidMap(p_data[1])) {
                    p_mapName = p_data[1];
                    d_gameinstance.d_Map = d_gameinstance.d_RunG.editMap(p_mapName);
                    System.out.println("Start editing " + p_mapName);
                    d_gameinstance.d_LogEntry.setMessage("Start editing " + p_mapName);
                    d_gameinstance.d_GameGamePhase = GamePhase.EDITMAP;
                    d_gameinstance.setGamePhase(d_gameinstance.d_GameGamePhase);
                } else {
                    System.out.println("Map name is invalid!");
                    d_gameinstance.d_LogEntry.setMessage("Map name is invalid!");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command - it should be of the form editmap name.map");
            d_gameinstance.d_LogEntry.setMessage("Invalid command - it should be of the form editmap name.map");
        } catch (Exception e) {
            System.out.println("Invalid command - it should be of the form editmap name.map");
            d_gameinstance.d_LogEntry.setMessage("Invalid command - it should be of the form editmap name.map");
        }
        d_gameinstance.setPhase(new Load(d_gameinstance));
        d_gameinstance.d_LogEntry.setGamePhase(d_gameinstance.d_GamePhases);

    }


    @Override
    public void loadMap(String[] p_data,String p_mapName) {
        try {
            d_gameinstance.d_LogEntry.setMessage("Command given by user:"+ p_data[0] + " " +p_data[1]);
            if (p_data[1] != null) {
                if (GameHelper.isValidMap(p_data[1])) {
                    p_mapName = p_data[1];
                    d_gameinstance.d_Map = d_gameinstance.d_RunG.loadMap(p_mapName);
                    if (d_gameinstance.d_Map != null) {
                        if (!d_gameinstance.d_Map.getValid()) {
                            System.out.println("Map is not valid");
                            d_gameinstance.d_LogEntry.setMessage("Map is not valid");
                            d_gameinstance.d_GameGamePhase = GamePhase.NULL;
                            d_gameinstance.setGamePhase(d_gameinstance.d_GameGamePhase);
                        } else {
                            System.out.println(p_mapName+" ----- Map Validated !!!! -----\n" +
                                    "----  You can start adding players :- !!!! ----");
                            d_gameinstance.d_LogEntry.setMessage(p_mapName+" ----- Map Validated !!!! -----\n" +
                                    "----  You can start adding players :- !!!! ---- ");
                            d_gameinstance.d_GameGamePhase = GamePhase.STARTUP;
                            d_gameinstance.setGamePhase(d_gameinstance.d_GameGamePhase);
                        }
                    } else {
                        d_gameinstance.d_GameGamePhase = GamePhase.NULL;
                        d_gameinstance.setGamePhase(d_gameinstance.d_GameGamePhase);
                    }
                } else {
                    System.out.println("Map name not valid");
                    d_gameinstance.d_LogEntry.setMessage("Map name not valid");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid command - try -> loadmap name.map");
            d_gameinstance.d_LogEntry.setMessage("Invalid command - try -> loadmap name.map");
        }
        d_gameinstance.setPhase(new StartUpPhase(d_gameinstance));
        d_gameinstance.d_LogEntry.setGamePhase(d_gameinstance.d_GamePhases);
    }

    @Override
    public void showMap(GameMap p_map) {
        mv.showMap(p_map);
    }



    /**
     * Validate map function
     */
    public void validatemap()
    {
        if(d_gameinstance.d_RunG.validateMap(d_gameinstance.d_Map)) {
            System.out.println("Map is Validated and Correct!");
            d_gameinstance.d_LogEntry.setMessage("Map is Validated and Correct!");
        }
        else {
            System.out.println("Invalid map");
            d_gameinstance.d_LogEntry.setMessage("Invalid map");
        }
    }
}
