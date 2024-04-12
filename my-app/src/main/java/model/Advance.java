package model;


public class Advance implements order1 {

    private int d_NumArmies;
    private String d_SourceTerritoryId, d_TargetTerritoryId;
    private Player d_Player, d_TargetPlayer;

    /**
     * Constructor of advance class
     *
     * @param p_player          source player who is advancing armies
     * @param p_sourceTerritoryId source Territory Id
     * @param p_targetTerritoryId target Territory Id
     * @param p_numArmies       number of armies
     * @param p_targetPlayer    target player on whom advance is to be performed
     */
    public Advance(Player p_player, String p_sourceTerritoryId, String p_targetTerritoryId, int p_numArmies, Player p_targetPlayer) {
        d_Player = p_player;
        d_SourceTerritoryId = p_sourceTerritoryId;
        d_TargetTerritoryId = p_targetTerritoryId;
        d_NumArmies = p_numArmies;
        d_TargetPlayer = p_targetPlayer;
    }

    /**
     * Contain the implementation logic of advance order
     */
    @Override
    public boolean execute() {
        System.out.println("Target player" + d_TargetPlayer);

            if (d_Player.getOwnedCountries().containsKey(d_SourceTerritoryId.toLowerCase())) {
                if (((d_Player.getOwnedCountries().get(d_SourceTerritoryId.toLowerCase()).getNumberOfArmies()) - d_NumArmies) >= 1) {
                    if (d_Player.getOwnedCountries().containsKey(d_TargetTerritoryId.toLowerCase())) {

                        //advance logic
                        int fromArmies = d_Player.getOwnedCountries().get(d_SourceTerritoryId.toLowerCase()).getNumberOfArmies();
                        fromArmies -= d_NumArmies;
                        d_Player.getOwnedCountries().get(d_SourceTerritoryId.toLowerCase()).setNumberOfArmies(fromArmies);
                        int toArmies = d_Player.getOwnedCountries().get(d_TargetTerritoryId.toLowerCase()).getNumberOfArmies();
                        toArmies += d_NumArmies;
                        d_Player.getOwnedCountries().get(d_TargetTerritoryId.toLowerCase()).setNumberOfArmies(toArmies);
                        return true;

                    } else {
                        System.out.println(d_TargetTerritoryId + " is not owned by the player");

                        if (d_Player.d_NegotiateList.contains(d_TargetPlayer)) {
                            return false;
                        } else {
                            System.out.println("Attack Occur between: " + d_TargetTerritoryId + " and " + d_SourceTerritoryId);
                            TerritoryDetails attackingTerritory = d_Player.getOwnedCountries().get(d_SourceTerritoryId.toLowerCase());
                            TerritoryDetails defendingTerritory = attackingTerritory.getNeighbours().get(d_TargetTerritoryId.toLowerCase());

                            int defendArmy = defendingTerritory.getNumberOfArmies();
                            int armyToAttack = (d_NumArmies * 60) / 100;
                            int armyForDefend = (defendArmy * 70 / 100);
                            int defenderArmyLeft = defendArmy - armyToAttack;
                            int attackerArmyLeft = d_NumArmies - armyForDefend;
                            if (defenderArmyLeft <= 0) {
                                d_Player.getOwnedCountries().put(d_TargetTerritoryId, defendingTerritory);
                                d_TargetPlayer.getOwnedCountries().remove(d_TargetTerritoryId);
                                defendingTerritory.setNumberOfArmies(attackerArmyLeft);
                                attackingTerritory.setNumberOfArmies(((d_Player.getOwnedCountries().get(d_SourceTerritoryId.toLowerCase()).getNumberOfArmies()) - d_NumArmies));
                                d_Player.addCard();

                            }
                            else {
                                defendingTerritory.setNumberOfArmies(defenderArmyLeft);
                                if (attackerArmyLeft < 0)
                                    attackingTerritory.setNumberOfArmies(((d_Player.getOwnedCountries().get(d_SourceTerritoryId.toLowerCase()).getNumberOfArmies()) - d_NumArmies));
                                else
                                    attackingTerritory.setNumberOfArmies(((d_Player.getOwnedCountries().get(d_SourceTerritoryId.toLowerCase()).getNumberOfArmies()) - d_NumArmies) + attackerArmyLeft);
                            }
                            return true;
                        }
                    }
                } else {
                    System.out.println("player don't have enough armies.");
                    return false;
                }
            } else {
                System.out.println(d_SourceTerritoryId + " is not owned by the player");
                return false;
            }

        }


    /**
     * Getter for current player
     *
     * @return d_player
     */
    public Player getD_player() {
        return d_Player;
    }

    /**
     * Setter for current player
     *
     * @param p_player player
     */
    public void setD_player(Player p_player) {
        this.d_Player = p_player;
    }

}

