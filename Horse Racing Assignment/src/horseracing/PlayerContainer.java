package horseracing;

import java.util.ArrayList;
import java.util.List;

public class PlayerContainer {
    private List<Player> players; // List of players

    public PlayerContainer() {
        this.players=new ArrayList<>(); //initializes the arraylist
    }

    public List<Player> getPlayers() {
        return players; //a global function that gets all the players.
    }

    public void addPlayer(Player player) {
        players.add(player); //adds a player to the arraylist
    }
}
