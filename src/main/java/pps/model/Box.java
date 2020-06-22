package pps.model;

import java.util.Optional;

public class Box {
    int id;
    Optional<String> taken;
    int x, y;

    public Box(int id, int x, int y) {
        taken = Optional.empty();
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void take(String id) {
        taken = Optional.of(id);
    }

    public boolean isTaken() {
        return taken.isPresent();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(String player, int x, int y) {
        taken.ifPresent(p -> {
            if(p.equals(player)) {
                this.y = y;
                this.x = x;
                taken = Optional.empty();
            }
        });
    }
}
