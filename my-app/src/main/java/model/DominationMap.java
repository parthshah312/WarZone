package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class DominationMap {
    public static int d_InMapIndex = 1;
    private GameMap d_Map;
    private HashMap<Integer, TerritoryDetails> d_ListOfTerritories;

    GameRunnerEngine d_RunGE= new GameRunnerEngine();

    public DominationMap(){

    }

    public static void main(String[] args) {
        Scanner l_in = new Scanner(System.in);
        System.out.println("----- ENTER THE NAME OF MAP FILE -------");
        String l_mapName = l_in.nextLine();
        LoadMap l_loadedMap = new LoadMap();
        l_loadedMap.readMap(l_mapName);
        l_in.close();
    }

    /**
     * getter method for map
     * @return map
     */
    public GameMap getMap() {
        return this.d_Map;
    }

    /**
     * setter method for map
     * @param p_map game map
     */
    public void setMap(GameMap p_map) {
        this.d_Map = p_map;
    }

    /**
     * method to read domination map
     * @param p_mapName name of map
     * @return game map
     */
    public GameMap readDominationMap(String p_mapName) {
        d_Map = new GameMap(p_mapName);
        d_ListOfTerritories = new HashMap<Integer, TerritoryDetails>();

        try {
            BufferedReader l_reader = new BufferedReader(new FileReader(p_mapName));
            String l_s;
            while ((l_s = l_reader.readLine()) != null) {
                if (l_s.equals("[continents]"))
                    l_reader = readContinents(l_reader);
                if (l_s.equals("[territories]"))
                    l_reader = readTerritories(l_reader);
                if (l_s.equals("[borders]"))
                    l_reader = readBorders(l_reader);
            }
            l_reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException");
            System.out.println(e.getMessage());
        }
        return d_Map;
    }

    /**
     * method to read territories
     * @param p_reader buffer reader
     * @return buffer reader
     */
    private BufferedReader readTerritories(BufferedReader p_reader) {
        String l_s;
        try {
            while (!((l_s = p_reader.readLine()).equals(""))) {
                String[] l_territoryString = l_s.split("\\s+");
                TerritoryDetails l_newTerritory = new TerritoryDetails(l_territoryString[0], l_territoryString[1], l_territoryString[2], l_territoryString[3], l_territoryString[4], d_Map);
                try {
                    if (l_newTerritory.d_InContinent == null) {
                        System.out.println("------- Error reading the file -------");
                        System.exit(-1);
                    }
                    addToContinentMap(l_newTerritory);
                    d_ListOfTerritories.put(l_newTerritory.d_Index, l_newTerritory);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p_reader;
    }

    /**
     * method to read continents
     * @param p_reader buffer reader
     * @return buffer reader
     */
    private BufferedReader readContinents(BufferedReader p_reader) {
        String l_s;
        try {
            while (!((l_s = p_reader.readLine()).equals(""))) {
                String[] l_continentString = l_s.split("\\s+");

                if (Integer.parseInt(l_continentString[1]) >= 0) {
                    d_Map.d_Continents.put(l_continentString[0].toLowerCase(), new Continent(l_continentString[0], l_continentString[1], l_continentString[2]));
                    d_InMapIndex++;
                } else {
                    System.out.println("------ Error reading the file -------");
                    System.exit(-1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        d_InMapIndex = 1;
        return p_reader;

    }

    /**
     * method to read borders
     * @param p_reader buffer reader
     * @return buffer reader
     */
    private BufferedReader readBorders(BufferedReader p_reader) {
        String l_s;
        try {
            while ((l_s = p_reader.readLine()) != null) {
                if (!l_s.equals("")) {
                    String[] l_borderString = l_s.split("\\s+");
                    TerritoryDetails l_argumentTerritory = new TerritoryDetails();
                    l_argumentTerritory = d_ListOfTerritories.get(Integer.parseInt(l_borderString[0]));
                    for (int l_neighbourCount = 1; l_neighbourCount < l_borderString.length; l_neighbourCount++) {
                        addNeighbour(l_argumentTerritory, l_borderString[l_neighbourCount]);

                    }

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p_reader;
    }

    /**
     * method to add neighbour
     * @param p_argumentTerritory territory details
     * @param p_stringIndex index of territory
     */
    private void addNeighbour(TerritoryDetails p_argumentTerritory, String p_stringIndex) {
        int l_borderIndex = Integer.parseInt(p_stringIndex);
        TerritoryDetails l_neighbourTerritory = new TerritoryDetails();
        try {
            l_neighbourTerritory = d_ListOfTerritories.get(l_borderIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("-------- Found error reading the .map file ------------");
            System.out.println("-------- The neighbour " + l_borderIndex + " does not exist ---------");
            System.exit(-1);
        }
        if (!p_argumentTerritory.getNeighbours().containsKey(l_neighbourTerritory.getTerritoryID().toLowerCase()))
            p_argumentTerritory.getNeighbours().put(l_neighbourTerritory.getTerritoryID().toLowerCase(), l_neighbourTerritory);
    }

    /**
     * method to add new territory to continent
     * @param l_newTerritory details of new territory
     */
    private void addToContinentMap(TerritoryDetails l_newTerritory) {
        if (!ValidateMap.isTerritoryExist(d_Map, l_newTerritory.getTerritoryID())) {
            Continent argumentContinent = d_Map.d_Continents.get(l_newTerritory.d_InContinent.toLowerCase());
            argumentContinent.d_Territory.put(l_newTerritory.getTerritoryID().toLowerCase(), l_newTerritory);
            d_Map.getTerritories().put(l_newTerritory.getTerritoryID().toLowerCase(), l_newTerritory);
        } else {
            System.out.println("-------- Error reading the file --------");
            System.out.println("-------- Territories on the same continent have identical names -------\n");
            System.exit(-1);
        }
    }

    /**
     * method to save map
     * @param p_map game map
     * @param p_fileName name of file
     * @return true if map is saved successfully
     */
    public boolean saveMap(GameMap p_map, String p_fileName) {
        if(d_RunGE.validateMap(p_map)) {
            try {
                BufferedWriter l_writer = new BufferedWriter(new FileWriter("src/main/resources/maps/"+p_fileName+ ".map"));
                int l_continentIndex = 1;
                int l_countryIndex = 1;
                HashMap<Integer, String> l_indexToCountry = new HashMap<Integer, String>();
                HashMap<String, Integer> l_countryToIndex = new HashMap<String, Integer>();

                l_writer.write("name " + p_fileName + " Map");
                l_writer.newLine();
                l_writer.newLine();
                l_writer.write("[files]");
                l_writer.newLine();
                l_writer.newLine();
                l_writer.flush();

                l_writer.write("[continents]");
                l_writer.newLine();
                for(Continent l_continent : p_map.getContinents().values()) {
                    l_writer.write(l_continent.getContinentId() + " " + Integer.toString(l_continent.getControlValue())+ " " + l_continent.d_ContinentColor);
                    l_writer.newLine();
                    l_writer.flush();
                    l_continent.setInMapIndex(l_continentIndex);
                    l_continentIndex++;
                }
                l_writer.newLine();
                l_writer.write("[countries]");
                l_writer.newLine();
                for(TerritoryDetails l_country : p_map.getTerritories().values()) {
                    l_writer.write(Integer.toString(l_countryIndex) + " " + l_country.getTerritoryID() + " " + Integer.toString(p_map.getContinents().get(l_country.getInContinent().toLowerCase()).getInMapIndex()) + " " + l_country.getxCoOrdinate() + " " + l_country.getyCoOrdinate());
                    l_writer.newLine();
                    l_writer.flush();
                    l_indexToCountry.put(l_countryIndex, l_country.getTerritoryID().toLowerCase());
                    l_countryToIndex.put(l_country.getTerritoryID().toLowerCase(), l_countryIndex);
                    l_countryIndex++;
                }
                l_writer.newLine();

                l_writer.write("[borders]");
                l_writer.newLine();
                l_writer.flush();
                for(int i=1;i<l_countryIndex;i++) {
                    String l_countryID = l_indexToCountry.get(i);
                    TerritoryDetails l_cd = p_map.getTerritories().get(l_countryID.toLowerCase());
                    l_writer.write(Integer.toString(i) + " ");
                    for(TerritoryDetails l_neighbor : l_cd.getNeighbours().values()) {
                        l_writer.write(Integer.toString(l_countryToIndex.get(l_neighbor.getTerritoryID().toLowerCase())) + " ");
                        l_writer.flush();
                    }
                    l_writer.newLine();
                }
            }
            catch(IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        else
        {
            System.out.println("----Given Map is not suitable for game play.First, Correct the map to continue with the new map or load a map from the existing maps----");
            return false;
        }
    }
}
