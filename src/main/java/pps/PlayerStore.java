package pps;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PlayerStore {
    private static PlayerStore singleton;

    public static PlayerStore instance() {
        if(singleton == null)
            singleton = new PlayerStore();
        return singleton;
    }

    private final Set<String> players;

    private PlayerStore() {
        players = new HashSet<>();
    }

    public Set<String> getPlayers() {
        return players;
    }

    public void addPlayer(String name) {
        Optional<String> player = players.stream().filter(f -> f.equals(name)).findFirst();
        if(player.isEmpty())
            players.add(name);
    }
}
