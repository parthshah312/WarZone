package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * show map class
 */
public class ShowMap implements Serializable {

    public ShowMap(){
    }

    /**
     * method of show map
     * @param p_players list of players
     * @param p_map Game map
     */
    public static void showMap(ArrayList<Player> p_players, GameMap p_map) {
        if(p_map==null)
            return;
        if(p_players.size()==0 || p_players.get(0).getOwnedTerritories().size()==0) {
            showMap(p_map);
            return;
        }
        
        System.out.printf("%70s\n", "                  <><><><>             <><><><><><>                      <><><><><><>             <><><><><><>  <><><><>");
        System.out.printf("%25s%25s%35s%25s%10s\n", "|Player|", "|Territory|", "|Neighbours|","|Continent|","|Armies|");
        System.out.printf("%70s\n", "                  <><><><>              <><><><><><>                      <><><><><><>            <><><><><><>  <><><><>");
        boolean l_printPlayerName = true;
        boolean l_printContinentId = true;
        boolean l_printTerritoryId = true;
        boolean l_printNumberOfArmies = true;

        for(int i=0; i<p_players.size(); i++){
            Player l_p = p_players.get(i);
            System.out.printf("-->");
            for(TerritoryDetails l_territory : l_p.getOwnedTerritories().values()) {
                for(TerritoryDetails l_neighbour : l_territory.getNeighbours().values()) {
                    if(l_printPlayerName && l_printContinentId && l_printTerritoryId) {
                        System.out.format("\n%25s%25s%35s%25s%10d\n", l_p.getPlayerName(), l_territory.getTerritoryID(), l_neighbour.getTerritoryID(), l_territory.d_InContinent, l_territory.d_NumberOfArmies);
                        l_printPlayerName = false;
                        l_printContinentId = false;
                        l_printTerritoryId = false;
                        l_printNumberOfArmies = false;
                    }
                    else if(l_printContinentId && l_printTerritoryId && l_printNumberOfArmies) {
                        System.out.format("\n%25s%25s%35s%25s%10d\n", "", l_territory.getTerritoryID(), l_neighbour.getTerritoryID(), l_territory.d_InContinent, l_territory.d_NumberOfArmies);
                        l_printPlayerName = false;
                        l_printTerritoryId = false;
                        l_printNumberOfArmies = false;
                    }
                    else {
                        System.out.format("\n%25s%25s%35s%25s%10s\n", "", "", l_neighbour.getTerritoryID(), "", "");
                    }
                }
                l_printContinentId = true;
                l_printTerritoryId = true;
                l_printNumberOfArmies = true;
            }
            l_printPlayerName = true;
            l_printContinentId = true;
            l_printTerritoryId = true;
            l_printNumberOfArmies = true;
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
        }
        System.out.println("****************************************Good to See Map for better understanding****************************************");
    }

    /**
     * It is basically UI that shows the information about the continents, territories and each territory's neighbour in the map
     * @param p_map the map to be shown.
     */
    static public void showMap(GameMap p_map) {
        if(p_map==null)
            return;
        System.out.printf("%50s\n", "             <><><><><><>              <><><><><><>           <><><><><><><><><><><><>");
        System.out.printf("%25s%25s%35s\n", "|Continents|", "|Territory|", "|Territory's neighbours|");
        System.out.printf("%50s\n", "             <><><><><><>              <><><><><><>           <><><><><><><><><><><><>");
        boolean l_PrintContinentName = true;
        boolean l_PrintTerritoryName = true;
        for(Continent l_continent : p_map.d_Continents.values()) {
            if(l_continent.d_Territory.size()==0) {
                System.out.printf("\n%25s%25s%25s\n", l_continent.d_ContinentId, "", "");
            }
            System.out.printf("-->");
            for(TerritoryDetails l_territory : l_continent.d_Territory.values()) {
                if(l_territory.getNeighbours().size()==0) {
                    if(l_PrintContinentName && l_PrintTerritoryName) {
                        System.out.printf("\n%25s%25s%25s\n", l_continent.d_ContinentId, l_territory.getTerritoryID(), "");
                        l_PrintContinentName = false;
                        l_PrintTerritoryName = false;
                    }
                    else if(l_PrintTerritoryName) {
                        System.out.printf("\n%25s%25s%25s\n", "", l_territory.getTerritoryID(), "");
                        l_PrintTerritoryName =  false;
                    }
                }
                for(TerritoryDetails l_neighbour : l_territory.getNeighbours().values()) {
                    if(l_PrintContinentName && l_PrintTerritoryName) {
                        System.out.printf("\n%25s%25s%25s\n", l_continent.d_ContinentId, l_territory.getTerritoryID(), l_neighbour.getTerritoryID());
                        l_PrintContinentName = false;
                        l_PrintTerritoryName = false;
                    }
                    else if(l_PrintTerritoryName) {
                        System.out.printf("\n%25s%25s%25s\n", "", l_territory.getTerritoryID(), l_neighbour.getTerritoryID());
                        l_PrintTerritoryName = false;
                    }
                    else {
                        System.out.printf("%25s%25s%25s\n", "", "", l_neighbour.getTerritoryID());
                    }
                }
                l_PrintTerritoryName = true;
            }
            l_PrintContinentName = true;
            l_PrintTerritoryName = true;
            System.out.println("----------------------------------------------------------------------------");
        }
        System.out.println("******************Good to See Map for better understanding******************");
    }

}
