package enumClass;

public enum GamePhase {

    /**
     * The game has not yet started.
     * The user's initial CLI is launched.
     * The 'editmap' or 'loadmap' command signals the end of the phase.
     */
    NULL,

    /**
     * * Editing an already-existing game map or starting from scratch to create a new one
     * The 'loadmap' instruction signals the conclusion of the phase.
     */
    EDITMAP,
    ASSIGN_REINFORCEMENTS,

    /**
     * The game is now in its startup phase, during which players can be added or withdrawn, territories can be divided up, and participants will be given their first armies.
     * Phase is often set subsequent to the loadmap command.
     * The phase comes to a close when the command "assignterritories" is executed, which distributes the territories and assigns the first armies.
     */
    STARTUP,

    /**
     * The player places the first army on the regions that they control.
     * After each player assigns their first army, the phase comes to a close.
     */
    ALLOT_ARMIES,


    /**
     * Player turns start individually in a round-robin format.
     * Player adds order to the list of current orders as in deploy command.
     * At phase's end, each player gives an order based on their unique turn.
     */
    ISSUE_ORDERS,

    /**
     * Carries out orders for each and every Player from the order list pool.
     * The phase comes to a close when every numeric army is assigned a TerritoryID based on the order list.
     */
    EXECUTE_ORDERS,

    /**
     * * Phase ends when the following player begins their move.
     * Indicates the end of the current player and signals for the player to get their turn.
     */
    TURN,

    /**
     * CLoses the Game.
     */
    QUIT
}
