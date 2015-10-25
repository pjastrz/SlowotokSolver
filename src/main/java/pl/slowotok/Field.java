package pl.slowotok;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pawel on 2015-10-22.
 */
public class Field {

    private Character letter;
    private Coordinates coordinates;
    private Map<Character, List<Field>> neighbours;

    public Field(Character letter, Coordinates coordinates) {
        this.letter = letter;
        this.coordinates = coordinates;
        this.neighbours = new HashMap<Character, List<Field>>();
    }

    public Character getLetter() {
        return letter;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Map<Character, List<Field>> getNeighbours() {
        return neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (letter != null ? !letter.equals(field.letter) : field.letter != null) return false;
        if (coordinates != null ? !coordinates.equals(field.coordinates) : field.coordinates != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = letter != null ? letter.hashCode() : 0;
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Field{" +
                "letter=" + letter +
                ", coordinates=" + coordinates +
                '}';
    }
}
