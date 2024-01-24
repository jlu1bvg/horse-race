package horseracing;

import java.util.List;

public class PlayerContainer {
    private static List<Player> players;

    public PlayerContainer() {
        
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
