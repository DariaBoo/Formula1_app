package ua.foxminded.dto;

import java.util.Objects;

/**
 * The class which represents data transfer object that aggregate and
 * encapsulate partial data from the database for further processing. RacerDTO
 * has three fields:
 * <p>
 * name = string
 * <p>
 * represents a not null racer's name.
 * <p>
 * team = string
 * <p>
 * represents a not null racer's team name.
 * <p>
 * lapTime = long
 * <p>
 * represents a racer's lap time greater than zero.
 * <p>
 * It's possible to initialize this fields by creating a new object. And also
 * it's possible to get the values of this fields by using getters.
 * 
 * @author Daria Bogush
 * @version 1.0
 */
public class RacerDTO {
    private final String name;
    private final String team;
    private final Long lapTime;

    /**
     * Creates a new RacerDTO object with initialized racer's name, team, and lap
     * time.
     * 
     * @param name    a first and last name not equals null.
     * @param team    a name of team for which the rider is playing not equals null.
     * @param lapTime time in milliseconds greater then zero during which the rider
     *                completed one lap.
     */
    public RacerDTO(String name, String team, Long lapTime) {
        this.name = Objects.requireNonNull(name,
                "Racer's name must not be null. NullPointerException inside RacerDTO constructor.");
        this.team = Objects.requireNonNull(team,
                "Name of team must not be null.NullPointerException inside RacerDTO constructor.");
        if (lapTime <= 0) {
            throw new IllegalArgumentException(
                    "Racer's lap time must be greater than zero. IllegalArgumentException inside RacerDTO constructor.");
        } else {
            this.lapTime = lapTime;
        }
    }

    /**
     * Returns a String which represents racer's name.
     * 
     * @return a String with racer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Return a String which represents racer's team.
     * 
     * @return a String with racer's team.
     */
    public String getTeam() {
        return team;
    }

    /**
     * Returns a long value which represents racer's lap time in milliseconds.
     * 
     * @return a long value with racer's lap time in milliseconds.
     */
    public Long getLapTime() {
        return lapTime;
    }

    /**
     * Returns a hash code for this object. The hash code for an object is computed
     * as a sum of name hash code, team hash code and lapTime hash code.
     * 
     * @return a hash code value for this object.
     */
    public int hashcode() {
        return Objects.hash(name, team, lapTime);
    }

    /**
     * Compares this object to the specified object. The result is true if and only
     * if the argument is not null and is an object that represents the same fields
     * as this object.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        RacerDTO other = (RacerDTO) otherObject;
        return Objects.equals(name, other.name) && Objects.equals(team, other.team) && lapTime == other.lapTime;
    }
}
