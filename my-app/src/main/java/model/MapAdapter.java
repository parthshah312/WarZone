package model;
public class MapAdapter extends DominationMap{

    ConquestMap d_ConquestMap;

    /**
     * constructor
     * @param p_conquestMap conquest map
     */
    public MapAdapter(ConquestMap p_conquestMap){
        this.d_ConquestMap = p_conquestMap;
    }

    /**
     * method to read domination map
     * @return game map
     */
    public GameMap readDominationMap(String p_mapName) {
        return d_ConquestMap.readConquestMap(p_mapName);
    }

    /**
     * method to save map
     * @return true if map is saved successfully
     */
    public boolean saveMap(GameMap p_map, String p_fileName) {
        return d_ConquestMap.saveMap(p_map, p_fileName);
    }
}
