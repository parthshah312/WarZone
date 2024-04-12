package model;

import java.util.HashMap;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;


/**
 * In this class, the map is validated.
 */
public class ValidateMap {

    private Graph<TerritoryDetails, DefaultEdge> d_MapGraph;

    /**
     * Using JGraphT, this constructor creates an instance of the Directed Graph.
     */
    public ValidateMap() {
        d_MapGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    /**
     * Uses the jgrapht package to create a graph by adding edges between each territory and its neighbors, starting with the territories as vertices.
     *
     * @param p_map Game map representing territories ,continents and borders.
     * @return returns graph representing the map
     */
    public Graph<TerritoryDetails, DefaultEdge> createGraph(GameMap p_map) {
        for (TerritoryDetails l_territoryDetails : p_map.getTerritories().values()) {
            d_MapGraph.addVertex(l_territoryDetails);
        }

        for (TerritoryDetails l_territoryDetails : p_map.getTerritories().values()) {
            for (TerritoryDetails l_neighbour : l_territoryDetails.getNeighbours().values()) {
                d_MapGraph.addEdge(l_territoryDetails, l_neighbour);
            }
        }
        return d_MapGraph;
    }

    /**
     *A continent's subgraph is created using this function.
     * @param p_subGraph subgraph for a continent
     * @param p_territories territories of a continent
     * @return p_subGraph
     */
    public Graph<TerritoryDetails, DefaultEdge> createSubGraph(Graph<TerritoryDetails, DefaultEdge> p_subGraph, HashMap<String, TerritoryDetails> p_territories) {

        for (TerritoryDetails l_territory : p_territories.values()) {
            p_subGraph.addVertex(l_territory);
        }

        for (TerritoryDetails l_territory : p_territories.values()) {
            for (TerritoryDetails l_neighbour : l_territory.getNeighbours().values()) {
                if (p_territories.containsKey(l_neighbour.getTerritoryID().toLowerCase())) {
                    p_subGraph.addEdge(l_territory, l_neighbour);
                }
            }
        }
        return p_subGraph;
    }

    /**
     * The graph's connectivity is examined by this function.
     * @param p_graph The graph whose connectivity is checked
     * @return returns true is graph is connected
     */
    public boolean isGraphConnected(Graph<TerritoryDetails, DefaultEdge> p_graph) {
        ConnectivityInspector<TerritoryDetails, DefaultEdge> l_cInspector = new ConnectivityInspector<>(p_graph);
        if (!l_cInspector.isConnected())
            return false;
        else
            return true;
    }
    
    /**
	 * Determines whether or not every continent is connected to a subgraph
	 * @param p_map GameMap object representing the game map
	 * @return true if all continents are connected sub-graph, else false indicating incorrect map
	 */
	public boolean continentConnectCheck(GameMap p_map) {
		for(Continent l_continent : p_map.d_Continents.values()) {
			Graph<TerritoryDetails, DefaultEdge> subGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
			subGraph = createSubGraph(subGraph, l_continent.d_Territory);
			if(!isGraphConnected(subGraph)) {
				return false;
			}
		}
		return true;
	}
	
    
        /**
     * Verify whether the same territory already exists.
     *
     * @param p_map GameMap object holding record of all the existing continents and territories
     * @param p_territoryId name of the territory to be checked
     * @return true if territory already exists, else false
     */
    public static boolean isTerritoryExist(GameMap p_map, String p_territoryId) {
        if (p_map.getTerritories().containsKey(p_territoryId.toLowerCase()))
            return true;
        else
            return false;
    }

    /**
     * Verify whether the continent is already occupied.
     *
     * @param p_map GameMap object holding record of all the existing continents and territories
     * @param p_continentId name of the continent to be checked
     * @return true if continent already exists, else false
     */
    public static boolean isContinentExists(GameMap p_map, String p_continentId) {
        if (p_map.d_Continents.containsKey(p_continentId.toLowerCase()))
            return true;
        else
            return false;
    }

    
	/**
	 * Examine whether any continent is empty and does not hold any territory.
	 * @param p_map GameMap object representing the game map
	 * @return true if no continent is empty, else false indicating empty continent
	 */
	public boolean notEmptyContinent(GameMap p_map) {
		for(Continent l_continent : p_map.d_Continents.values()) {
			if(l_continent.d_Territory.size()==0)
				return false;
		}
		return true;
	}
}