package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Game RUnner Engine class
 */

public class GameRunnerEngine {
	DominationMap l_domMap;
	/**
	 *it loads the map and check basic error handling
	 * @param p_mapName name of the map to be used for playing the game
	 * @return l_gameMap is used to represent the current map
	 */
	public GameMap loadMap(String p_mapName) {
		String l_filePath = "src/main/resources/maps/" + p_mapName;
		GameMap l_gameMap;
		String l_MapType;
		File l_file = new File(l_filePath);
		if(l_file.exists())
		{
			LoadMap l_loadMap = new LoadMap();
			l_MapType = l_loadMap.readMap(l_filePath);
			System.out.println("---- MapType is : "+ l_MapType+" ----");
			if(l_MapType.equals("domination")) {
				l_domMap= new DominationMap();
			}
			else {
				l_domMap= new MapAdapter(new ConquestMap());
			}
			l_gameMap= l_domMap.readDominationMap(l_filePath);
			l_gameMap.setMapName(p_mapName);
			if(validateMap(l_gameMap)) {
				l_gameMap.setValid(true);
			}
			else {
				System.out.println("---- Map is not suitable for playing So  Correct the map to continue with this map or load another map from the existing maps to coutinue ----");
				l_gameMap.setValid(false);
			}
		}else {
			System.out.println("---- Map " + p_mapName + " does not exist so try to load again or use 'editMap' to create a map for playing the game ----");
			return null;
		}
		return l_gameMap;
	}

	public GameMap editMap(String p_mapName) {
		String l_filePath = "src/main/resources/maps/" + p_mapName;
		GameMap l_gameMap;
		String l_MapType;
		LoadMap l_loadMap = new LoadMap();
		File l_file = new File(l_filePath);
		if(l_file.exists()) {
			l_MapType = l_loadMap.readMap(l_filePath);
			System.out.println("---- MapType is: "+ l_MapType+" ----");
			System.out.println("---- "+p_mapName+" exist and you can start edit it ----");
			if(l_MapType.equals("domination")) {
				l_domMap= new DominationMap();
			}
			else {
				l_domMap= new MapAdapter(new ConquestMap());
			}
			l_gameMap= l_domMap.readDominationMap(l_filePath);
			l_gameMap.setMapName(p_mapName);
		}
		else {
			l_loadMap.setMapType("domination");
			System.out.println("---- MapType is : "+ l_loadMap.getMapType()+" ----");
			System.out.println("---- "+p_mapName + " does not exist ----");
			System.out.println("---- Now Creating a new Map named " + p_mapName+" ----");
			l_gameMap = new GameMap(p_mapName);
		}

		return l_gameMap;
	}
	/**
	 *it is add the continent and if it there then says it is exist.
	 * @param p_map  continent should be added
	 * @param p_continentID ID of the continent should be added
	 * @param p_continentValue Continent value of the continent to be added
	 * @return true if run fine otherwise throw the error
	 */
	public boolean addContinent(GameMap p_map, String p_continentID, int p_continentValue) {
		
		if(!(ValidateMap.isContinentExists(p_map,p_continentID))) {
			if(p_continentValue<0)
				return false;
			Continent l_continent = new Continent(p_continentID, p_continentValue);
			String l_cName=p_continentID.toLowerCase();
			p_map.d_Continents.put(l_cName, l_continent);
			return true;
		}
		else
			return false;	
	}
	
	/**
	 * remove the continenet, territories and neighbour of territories
	 * @param p_map represent the map that edit
	 * @param p_continentID ID of the continent is going to removed
	 * @return true if run fine otherwise throw the error
	 */
	public boolean removeContinent(GameMap p_map, String p_continentID) {
		
		if(p_map.d_Continents.containsKey(p_continentID.toLowerCase())) {
			Continent l_continent = p_map.d_Continents.get(p_continentID.toLowerCase());
			
			ArrayList<TerritoryDetails> l_tList = new ArrayList<TerritoryDetails>();
			for(TerritoryDetails l_cd : l_continent.d_Territory.values()) {
				l_tList.add(l_cd);
			}

			boolean l_remTer= removeTerritoryContinent(p_map, l_tList);
			if(l_remTer){
				p_map.d_Continents.remove(p_continentID.toLowerCase());
				return true;
			}	
			else
				return false;
		}
		else {
			System.out.println(p_continentID + " does not exist.");
			return false;
		}
	}

	/**
	 * 
	 * It removes territory of the continents
	 * 
	 * @param p_map represent map 
	 * @param p_tList represents list of territories
	 * @return true if all territories are removed
	 */

	private boolean removeTerritoryContinent(GameMap p_map, ArrayList<TerritoryDetails> p_tList){
		Iterator<TerritoryDetails> l_iterator = p_tList.listIterator();
		while(l_iterator.hasNext()) {
			TerritoryDetails l_c = l_iterator.next();
			if(!removeTerritory(p_map, l_c.getTerritoryID()))
				return false;
		}
		return true;
	}
	
	
	/**
	 * it basically add the territory and check whethere it is present or not.
	 * @param p_map map to which territory is going to add
	 * @param p_territoryID ID of the territory that should be add
	 * @param p_continentID ID of the continent to that helps to add new territory
	 * @return true if run fine otherwise throw the error
	 */
	public boolean addTerritory(GameMap p_map, String p_territoryID, String p_continentID) {
		
		if(ValidateMap.isTerritoryExist(p_map, p_territoryID)) 
			return false;
		else{
			if(!p_map.d_Continents.containsKey(p_continentID.toLowerCase())) {
				System.out.println(p_continentID + " does not exist.");
				return false;
			}
			TerritoryDetails l_territory = new TerritoryDetails(p_territoryID, p_continentID);
			Continent l_continent = p_map.d_Continents.get(p_continentID.toLowerCase());
			l_continent.d_Territory.put(p_territoryID.toLowerCase(), l_territory);
			p_map.getTerritories().put(p_territoryID.toLowerCase(), l_territory);
			return true;
		}
	}
	private boolean removeNeighbourTerritory(GameMap p_map, TerritoryDetails p_territory) {
		for (TerritoryDetails l_neighbour : p_territory.getNeighbours().values()) {
			if (!removeNeighbour(p_map, p_territory.getTerritoryID(), l_neighbour.getTerritoryID())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * it removes the territory and remove all its neighbours
	 * @param p_map represent the map that going to add
	 * @param p_territoryID ID of the territory that is going to remove
	 * @return true if run fine otherwise throw the error
	 */
	public boolean removeTerritory(GameMap p_map, String p_territoryID) {
		if(p_map.getTerritories().containsKey(p_territoryID.toLowerCase())) {
			TerritoryDetails l_territory = p_map.getTerritories().get(p_territoryID.toLowerCase());
			ArrayList<TerritoryDetails> l_tList = new ArrayList<TerritoryDetails>(l_territory.getNeighbours().values());
			
			boolean remTer = removeNeighbourTerritory(p_map, l_territory);
			if(remTer) {
				p_map.getTerritories().remove(p_territoryID.toLowerCase());
				p_map.d_Continents.get(l_territory.d_InContinent.toLowerCase()).d_Territory.remove(p_territoryID.toLowerCase());
				return true;
			} else {
				return false;
			}
		} else {
			System.out.println(p_territoryID + " does not exist.");
			return false;
		}
	}


	 
	
	
	/**
	 *it creates the link between the two argument territories and made the neighbour
	 * @param p_map represent map that going to edit
	 * @param p_territoryID ID of one argument territory
	 * @param p_neighbourTerritoryID ID of other argument territory
	 * @return true if run fine otherwise throw the error
	 */
	public boolean addNeighbour(GameMap p_map, String p_territoryID, String p_neighbourTerritoryID) {
		if (!territoriesExist(p_map, p_territoryID, p_neighbourTerritoryID)) {
			return false;
		}
		
		TerritoryDetails l_c1 = p_map.getTerritories().get(p_territoryID.toLowerCase());
		TerritoryDetails l_c2 = p_map.getTerritories().get(p_neighbourTerritoryID.toLowerCase());
	
		if (!l_c1.getNeighbours().containsKey(p_neighbourTerritoryID.toLowerCase())) {
			l_c1.getNeighbours().put(p_neighbourTerritoryID.toLowerCase(), l_c2);
			System.out.println(p_territoryID + " added as neighbour to " + p_neighbourTerritoryID);
		} else {
			System.out.println("Already Neighbour");
		}
		
		if (!l_c2.getNeighbours().containsKey(p_territoryID.toLowerCase())) {
			l_c2.getNeighbours().put(p_territoryID.toLowerCase(), l_c1);
			System.out.println(p_neighbourTerritoryID + " added as neighbour to " + p_territoryID);
		} else {
			System.out.println("Already Neighbour");
		}
	
		return true;
	}
	
	/**
	 * Private method to check if territory exists
	 * @param p_map Game map
	 * @param p_territoryID Territory id
	 * @param p_neighbourTerritoryID Neighbour territory id
	 * @return true if territory exists
	 */
	private boolean territoriesExist(GameMap p_map, String p_territoryID, String p_neighbourTerritoryID) {
		if (!p_map.getTerritories().containsKey(p_territoryID.toLowerCase()) && !p_map.getTerritories().containsKey(p_neighbourTerritoryID.toLowerCase())) {
			System.out.println(p_territoryID + " and " + p_neighbourTerritoryID + " do not exist. Set their neighbors after creating their own territory.\n");
			return false;
		} else if (!p_map.getTerritories().containsKey(p_territoryID.toLowerCase())) {
			System.out.println(p_territoryID + " does not exist. Set their neighbors after creating their own territory.\n");
			return false;
		} else if (!p_map.getTerritories().containsKey(p_neighbourTerritoryID.toLowerCase())) {
			System.out.println(p_neighbourTerritoryID + " does not exist. Set their neighbors after creating their own territory.\n");
			return false;
		}
		return true;
	}
	
	
	/**
	 * it removes the link between the two neighbouring Territory
	 * @param p_map represent map that going to edit
	 * @param p_territoryID ID of one argument territory
	 * @param p_neighbourTerritoryID ID of other argument territory
	 * @return true if run fine otherwise throw the error
	 */
	public boolean removeNeighbour(GameMap p_map, String p_territoryID, String p_neighbourTerritoryID) {
		if(p_map.getTerritories().containsKey(p_territoryID.toLowerCase()) && p_map.getTerritories().containsKey(p_neighbourTerritoryID.toLowerCase())) {
			TerritoryDetails l_c1 = p_map.getTerritories().get(p_territoryID.toLowerCase());
			TerritoryDetails l_c2 = p_map.getTerritories().get(p_neighbourTerritoryID.toLowerCase());

			if(l_c1.getNeighbours().containsKey(l_c2.getTerritoryID().toLowerCase())) {
				l_c1.getNeighbours().remove(p_neighbourTerritoryID.toLowerCase());
				System.out.println(p_territoryID+" remove as neighbour to "+p_neighbourTerritoryID);
			}
			if(l_c2.getNeighbours().containsKey(l_c1.getTerritoryID().toLowerCase())) {
				l_c2.getNeighbours().remove(p_territoryID.toLowerCase());
				System.out.println(p_neighbourTerritoryID+" remove as neighbour to "+p_territoryID);
			}
			return true;
		}
		else {
			if(!p_map.getTerritories().containsKey(p_territoryID.toLowerCase()) && !p_map.getTerritories().containsKey(p_neighbourTerritoryID.toLowerCase()))
				System.out.println(p_territoryID + " and " + p_neighbourTerritoryID + "  does not exist.");
			else if(!p_map.getTerritories().containsKey(p_territoryID.toLowerCase()))
				System.out.println(p_territoryID + " does not exist.");
			else
				System.out.println(p_neighbourTerritoryID + " does not exist.");
			return false;
		}
	}

	/**
	 * it saves the save GameMap as ".map" file using Domination game format
	 * @param p_map The map is going to save
	 * @param p_fileName name of the file 
	 * @return true if run fine otherwise throw the error
	 */
	public boolean saveMap(GameMap p_map, String p_fileName) {
		if(validateMap(p_map)) {
			try {
				BufferedWriter l_writer = new BufferedWriter(new FileWriter("src/main/resources/maps/"+p_fileName+ ".map"));
				int l_continentIndex = 1;	 
				int l_territoryIndex = 1;		
				HashMap<Integer, String> l_indexToTerritory = new HashMap<Integer, String>(); 
				HashMap<String, Integer> l_territoryToIndex = new HashMap<String, Integer>(); 
				
				l_writer.write("name " + p_fileName + " Map");
				l_writer.newLine();
				l_writer.newLine();
				l_writer.newLine();
				l_writer.write("[files]");
				l_writer.newLine();
				l_writer.newLine();
				l_writer.newLine();
				l_writer.flush();
				
				l_writer.write("[continents]");
				l_writer.newLine();

				for(Continent l_continent : p_map.d_Continents.values()) {
					String l_cn=l_continent.d_ContinentId;
					int l_ccv=l_continent.d_ControlValue;
					String l_cc=l_continent.d_ContinentColor;

					l_writer.write(String.format("%s %d %s", l_cn, l_ccv, l_cc));
					l_writer.newLine();
					l_writer.flush();

					l_continent.d_InMapIndex = l_continentIndex;
					l_continentIndex++;
				}
				l_writer.newLine();


				
				l_writer.write("[territories]");
				l_writer.newLine();

				for(TerritoryDetails l_territory : p_map.getTerritories().values()) {
					l_writer.write(Integer.toString(l_territoryIndex) + " " + l_territory.getTerritoryID() + " " + Integer.toString(p_map.d_Continents.get(l_territory.d_InContinent.toLowerCase()).d_InMapIndex) );
					l_writer.newLine();
					l_writer.flush();
					String l_terrName=l_territory.getTerritoryID().toLowerCase();
					l_indexToTerritory.put(l_territoryIndex, l_terrName);
					l_territoryToIndex.put(l_terrName, l_territoryIndex);
					l_territoryIndex++;
				}
				l_writer.newLine();


				
				l_writer.write("[borders]");
				l_writer.newLine();
				l_writer.flush();
				for(int i=1;i<l_territoryIndex;i++) {
					String l_territoryID = l_indexToTerritory.get(i);
					TerritoryDetails l_cd = p_map.getTerritories().get(l_territoryID.toLowerCase());
					l_writer.write(Integer.toString(i) + " ");
					for(TerritoryDetails l_neighbour : l_cd.getNeighbours().values()) {
						l_writer.write(Integer.toString(l_territoryToIndex.get(l_neighbour.getTerritoryID().toLowerCase())) + " ");
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
			System.out.println("Map unsuitable for using in a game. Adjust the map to proceed with the new one or import a map from the pre-existing maps.\n");
			return false;
		}
	}
	
	/**
	 * 	it checks any empty continent is present or not, i.e. continent without any territory
	 * 	whether the map for the game is connected graph or not
	 * 	whether each continent in map is a connected sub-graph or not
	 * @param p_map GameMap is going to be check
	 * @return  true if run fine otherwise throw the error
	 */
	public boolean validateMap(GameMap p_map) {
		String l_mapName=p_map.d_MapName;
		ValidateMap l_mv = new ValidateMap();
		if(!l_mv.isGraphConnected(l_mv.createGraph(p_map))){
			System.out.println("Invalid Map --- "+l_mapName+" is not a connected graph.");
			return false;
		}
		else if(!l_mv.continentConnectCheck(p_map)){
			System.out.println("Invalid Map --- "+l_mapName+" is not a connected sub-graph.");
			return false;
		}
		else if (!l_mv.notEmptyContinent(p_map)) {
			System.out.println("Invalid Map --- "+l_mapName+" contains empty continent.");
			return false;
		}
		return true;
	}
	public GameDataBuilder loadGame(String p_fileName){
		GameDataBuilder l_gameDataBuilder=new GameDataBuilder();
		try{
			FileInputStream l_f = new FileInputStream((new File("src/main/resources/game/" + p_fileName)));
			ObjectInputStream l_o = new ObjectInputStream((l_f));
			l_gameDataBuilder = (GameDataBuilder) l_o.readObject();

		} catch(FileNotFoundException e){
			System.out.println("Entered file name doesnot exist.");
			return null;
		} catch(IOException e) {
			return null;
		} catch (ClassNotFoundException e){
			return null;
		}
		return l_gameDataBuilder;
	}

	public boolean saveGame(GameData  p_game, String p_fileName){
		GameDataBuilder l_gameDataBuilder = new GameDataBuilder();
		l_gameDataBuilder.setMap(p_game.getMap());
		l_gameDataBuilder.setMapType(p_game.getMapType());
		l_gameDataBuilder.setGamePhase(p_game.getGamePhase());
		l_gameDataBuilder.setPlayers(p_game.getPlayers());
		l_gameDataBuilder.setActivePlayer(p_game.getActivePlayer());
		l_gameDataBuilder.setDeck(p_game.getDeck());
		l_gameDataBuilder.setCardsDealt((p_game.getCardsDealt()));
		l_gameDataBuilder.setPhase(p_game.getD_Phase());
		l_gameDataBuilder.setPhaseName(p_game.getD_Phase().getD_PhaseName());

		System.out.println(l_gameDataBuilder);
		try{
			ObjectOutputStream l_o = new ObjectOutputStream(new FileOutputStream(new File("src/main/resources/game/" + p_fileName)));
			l_o.writeObject(l_gameDataBuilder);
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
