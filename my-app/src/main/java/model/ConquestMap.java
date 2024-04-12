package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ConquestMap {

    GameMap  d_ConquestMap;
    HashMap<Integer, TerritoryDetails> d_ListOfCountries;
    GameRunnerEngine d_RunGE= new GameRunnerEngine();

    /**
     * method to read conquest map
     * @param p_mapName name of map
     * @return game map
     */
    public GameMap readConquestMap(String p_mapName){
        d_ConquestMap = new GameMap(p_mapName);
        d_ListOfCountries = new HashMap<Integer, TerritoryDetails>();

        try {
            BufferedReader l_reader = new BufferedReader(new FileReader(p_mapName));
            String l_s;
            while ((l_s = l_reader.readLine()) != null) {
                if(l_s.equals("[Continents]")) {
                    l_reader = readContinents(l_reader, d_ConquestMap);
                }
                if(l_s.equals("[Territories]")) {
                    l_reader = readTerritories(l_reader, d_ConquestMap);
                }
            }
            l_reader.close();
            for(TerritoryDetails l_country : d_ConquestMap.getTerritories().values()){
                for(String l_neighbor : l_country.getNeighbours().keySet()){
                    if(l_country.getNeighbours().get(l_neighbor.toLowerCase())==null){
                        if(d_ConquestMap.getTerritories().get(l_neighbor.toLowerCase())==null){
                            return null;
                        }
                        l_country.getNeighbours().put(l_neighbor.toLowerCase(), d_ConquestMap.getTerritories().get(l_neighbor.toLowerCase()));
                    }
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            System.out.println(e.getMessage());
        }
        catch(IOException e) {
            System.out.println("IOException");
            System.out.println(e.getMessage());
        }
        return d_ConquestMap;
    }

    /**
     * method to read continents
     * @param p_reader buffer reader
     * @param p_map game map
     * @return buffer reader
     */
    public BufferedReader readContinents(BufferedReader p_reader, GameMap p_map){
        String l_s;
        try {
            while(!((l_s = p_reader.readLine()).equals(""))) {
                String[] continentString = l_s.split("=");

                if(Integer.parseInt(continentString[1])>=0) {
                    p_map.getContinents().put(continentString[0].toLowerCase(), new Continent(continentString[0], Integer.parseInt(continentString[1])));
                }
                else {
                    System.out.println("----There is problem for reading the file----");
                    System.out.println("----There is negative control value exists----");
                    System.exit(-1);
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return p_reader;
    }

    /**
     * method to read territories
     * @param p_reader buffer reader
     * @param p_map game map
     * @return buffer reader
     */
    public BufferedReader readTerritories(BufferedReader p_reader, GameMap p_map){
        String l_s;
        try {
            while((l_s = p_reader.readLine()) != null) {
                if(l_s.equals("")){
                    continue;
                }
                String[] l_countryString = l_s.split(",");
                TerritoryDetails l_newCountry = new TerritoryDetails(l_countryString[0], l_countryString[1], l_countryString[2], l_countryString[3]);
                try {
                    if(l_newCountry.getInContinent()==null)
                    {
                        System.out.println("----There is problem for reading the file----");
                        System.out.println("--------This continent does not exist--------");
                        System.exit(-1);
                    }
                    addToContinentMap(l_newCountry, p_map);
                    for(int i=4; i<l_countryString.length; i++){
                        if(p_map.getTerritories().containsKey(l_countryString[i].toLowerCase())){
                            l_newCountry.getNeighbours().put(l_countryString[i].toLowerCase(), p_map.getTerritories().get(l_countryString[i].toLowerCase()));
                            p_map.getTerritories().get(l_countryString[i].toLowerCase()).getNeighbours().put(l_newCountry.getTerritoryID().toLowerCase(), l_newCountry);
                        } else {
                            l_newCountry.getNeighbours().put(l_countryString[i].toLowerCase(), null);
                        }
                    }
                }
                catch(NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return p_reader;
    }

    /**
     * method to add new country to continent
     * @param p_newCountry details of new country
     * @param p_map game map
     */
    public void addToContinentMap(TerritoryDetails p_newCountry, GameMap p_map){
        if(!ValidateMap.isTerritoryExist(p_map, p_newCountry.getTerritoryID())) {
            Continent argumentContinent = p_map.getContinents().get(p_newCountry.getInContinent().toLowerCase());
            argumentContinent.getTerritories().put(p_newCountry.getTerritoryID().toLowerCase(), p_newCountry);
            p_map.getTerritories().put(p_newCountry.getTerritoryID().toLowerCase(), p_newCountry);
        }
        else {
            System.out.println("----There is problem for reading the file----");
            System.out.println("----There is two territories of same name exists in the same continent----");
            System.exit(-1);
        }
    }

    /**
     * check if map is saved
     * @param p_map game map
     * @param p_fileName name of file
     * @return true if map is saved successfully
     */
    public boolean saveMap(GameMap p_map, String p_fileName) {
        if(d_RunGE.validateMap(p_map)) {
            try{
                BufferedWriter l_writer = new BufferedWriter(new FileWriter("src/main/resources/maps/" + p_fileName + ".map"));

                //write preliminary information
                l_writer.write("[Map]");
                l_writer.newLine();
                l_writer.newLine();
                l_writer.flush();

                l_writer.write("[Continents]");
                l_writer.newLine();
                for (Continent continent : p_map.getContinents().values()) {
                    l_writer.write(continent.getContinentId() + "=" + continent.getControlValue());
                    l_writer.newLine();
                    l_writer.flush();
                }
                l_writer.newLine();
                l_writer.write("[Territories]");
                l_writer.newLine();
                String s;
                for(TerritoryDetails country : p_map.getTerritories().values()){
                    s = country.getTerritoryID() + "," + country.getxCoOrdinate() + "," + country.getyCoOrdinate() + "," + country.getInContinent();
                    for(TerritoryDetails  neighbor : country.getNeighbours().values()){
                        s += "," + neighbor.getTerritoryID();
                    }
                    l_writer.write(s);
                    l_writer.newLine();
                    l_writer.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;

        } else {
            return false;
        }
    }

}
