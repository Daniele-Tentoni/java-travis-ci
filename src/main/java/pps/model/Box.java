package pps.model;

import java.util.Optional;

public class Box implements Comparable<Box> {
    Optional<String> taken;
    private final int originalPosition;
    private int currentPosition;

    public Box(final int originalPosition, final int currentPosition) {
        taken = Optional.empty();
        this.originalPosition = originalPosition;
        this.currentPosition = currentPosition;
    }

    public void take(String id) {
        taken = id.isEmpty() ? Optional.empty() : Optional.of(id);
    }

    public boolean isTaken() {
        return taken.isPresent();
    }

    public boolean isTaken(String name) {
        return taken.isPresent() && taken.get().equals(name);
    }

    public String getTaker() {
        return taken.orElse("");
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public int getOriginalPosition() {
        return originalPosition;
    }

    public boolean isInRightPlace() {
        return currentPosition == originalPosition;
    }

    public void setCurrentPosition(final String player, final int newPosition) {
        taken.ifPresent(p -> {
            if(p.equals(player)) {
                currentPosition = newPosition;
                take("");
            }
        });
    }

    @Override
    public int compareTo(Box other) {
        return Integer.compare(this.currentPosition, other.currentPosition);
    }
}
