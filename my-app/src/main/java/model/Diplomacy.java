package model;

/**
 * Diplomacy class
 */
public class Diplomacy implements order1{

    private Player d_currentPlayer, d_targetPlayer;

    /**
     * Diplomacy constructor
     * @param p_currentPlayer Current player
     * @param p_targetPlayer Target player
     */
    public Diplomacy(Player p_currentPlayer,Player p_targetPlayer){
        this.d_currentPlayer = p_currentPlayer;
        this.d_targetPlayer = p_targetPlayer;
    }
    @Override
    public boolean execute() {
        this.d_currentPlayer.addPlayerNegList(d_targetPlayer);
        this.d_targetPlayer.addPlayerNegList(d_currentPlayer);

        return true;
    }
}