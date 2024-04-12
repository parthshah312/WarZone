package view;

import java.util.ArrayList;
import java.util.HashMap;

public class TournamentResultView {

    /**
     * method to display tournament result
     * @param p_winner winner
     * @param p_maps list of maps
     */
    public void displayTournamentResult(HashMap<Integer, String> p_winner, ArrayList<String> p_maps){
        int l_mapChangeAfter = p_winner.size()/p_maps.size();
        int l_index = 0;
        int l_changeIndex = 1;
        System.out.format("%25s%25s%35s\n", "Sequence of Game ", "Game map", "Winner of the Game");
        System.out.format("%85s\n", "-------------------------------------------------------------------------------------------");
        String l_mapName = p_maps.get(l_index);
        for(int i = 1; i<=p_winner.size(); i++){
            if(l_changeIndex>l_mapChangeAfter){
                l_index++;
                l_mapName = p_maps.get(l_index);
                l_changeIndex = 0;
            }
            l_changeIndex++;
            System.out.format("\n%25s%25s%25s\n", i, l_mapName, p_winner.get(i));
        }
    }
}
