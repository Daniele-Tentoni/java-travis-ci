package pps.model;

import java.util.Optional;

public class Box implements Comparable<Box> {
    int id;
    Optional<String> taken;
    private final int originalPosition;
    private int currentPosition;

    public Box(final int id, final int originalPosition, final int currentPosition) {
        taken = Optional.empty();
        this.id = id;
        this.originalPosition = originalPosition;
        this.currentPosition = currentPosition;
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

    public int getCurrentPosition() {
        return currentPosition;
    }

    public boolean isInRightPlace() {
        return currentPosition == originalPosition;
    }

    public void setCurrentPosition(final String player, final int newPosition) {
        taken.ifPresent(p -> {
            if(p.equals(player)) {
                currentPosition = newPosition;
                taken = Optional.empty();
            }
        });
    }

    @Override
    public int compareTo(Box other) {
        return Integer.compare(this.currentPosition, other.currentPosition);
    }
}
