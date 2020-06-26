package pps.stores;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Provide the operation to interact with the set o players.
 */
public class PlayerStore {
    private static PlayerStore singleton;

    public static PlayerStore instance() {
        if (singleton == null)
            singleton = new PlayerStore();
        return singleton;
    }

    private final Set<String> players;

    private PlayerStore() {
        players = new HashSet<>();
    }

    /**
     * Get the set of players.
     *
     * @return Set of players.
     */
    public Set<String> getPlayers() {
        return players;
    }

    /**
     * Add a player to the collection.
     *
     * @param name Name of the player.
     */
    public void addPlayer(String name) {
        Optional<String> player = players.stream().filter(f -> f.equals(name)).findFirst();
        if (player.isEmpty())
            players.add(name);
    }

    /**
     * Delete a player from the collection.
     *
     * @param name Name of the player.
     */
    public void deletePlayer(String name) {
        players.stream().filter(f -> f.equals(name)).findFirst().ifPresent(players::remove);
    }

    /**
     * Reset the list of players.
     */
    public void reset() {
        players.clear();
    }
}