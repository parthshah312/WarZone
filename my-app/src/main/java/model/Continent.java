package model;


import java.io.Serializable;
import java.util.HashMap;

/**
 * Represents a continent in the game, storing information such as its name, control value,
 * color, and index values important for gameplay. It also contains a HashMap of territories
 * belonging to this continent.
 */
public class Continent implements Serializable {

    public int d_ControlValue,d_InMapIndex;
    public String d_ContinentColor, d_ContinentId;
    public HashMap<String, TerritoryDetails> d_Territory;

    /**
     * Constructs a Continent object with the specified parameters.
     * This constructor is used when loading .map files during gameplay.
     * @param p_continentId The name of the continent.
     * @param p_controlValue The control value for this continent.
     * @param p_continentColor The color value of the continent.
     */
      Continent(String p_continentId, String p_controlValue, String p_continentColor) {
        d_InMapIndex = DominationMap.d_InMapIndex;
        d_Territory = new HashMap<>();
        d_ContinentColor = p_continentColor;
        d_ControlValue = Integer.parseInt(p_controlValue);
        d_ContinentId = p_continentId;
    }

    /**
     * Constructs a Continent object with the specified continent ID and control value.
     * @param p_continentId The ID of the continent.
     * @param p_controlValue The control value for this continent.
     */
    public Continent(String p_continentId, int p_controlValue) {
        d_ContinentId = p_continentId;
        d_ControlValue = p_controlValue;
        d_ContinentColor = "808080";
        d_Territory = new HashMap<>();
    }
    public HashMap<String, TerritoryDetails> getTerritories() {
        return d_Territory;
    }
    public String getContinentId() {
        return this.d_ContinentId;
    }
    public int getControlValue() {
        return this.d_ControlValue;
    }
    public int getInMapIndex() {
        return this.d_InMapIndex;
    }

    /**
     * Sets the Index value of this continent
     * @param p_inMapIndex Index value of the continent
     */
    public void setInMapIndex(int p_inMapIndex) {
        d_InMapIndex = p_inMapIndex;
    }
}
