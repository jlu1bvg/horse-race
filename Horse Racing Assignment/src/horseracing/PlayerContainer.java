package horseracing;

import java.util.ArrayList;
import java.util.List;

public class PlayerContainer {
    private List<Player> players;

    public PlayerContainer() {
        this.players=new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
