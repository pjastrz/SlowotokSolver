package pl.slowotok;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Created by pawel on 2015-10-22.
 */
public class BoardParser {

    public List<Field> parseFields(char [][] letters) {
        int sizeX = letters.length;
        int sizeY = letters[0].length;
        List<Field> fields = new ArrayList<>();
        for(int i = 0; i < letters.length; i++) {
            for(int j = 0; j< letters[i].length; j++) {
                Coordinates coordinates = new Coordinates(i,j);
                Field field = new Field(letters[i][j], coordinates);
                fields.add(field);
            }
        }

        Map<Coordinates, Field> fieldsByCoordinates = fields.stream().collect(toMap(field -> field.getCoordinates(), field -> field));

        fields.stream().forEach(field -> {
            addNeighbour(fieldsByCoordinates, field, field.getCoordinates().getX()-1, field.getCoordinates().getY()-1, sizeX, sizeY);
            addNeighbour(fieldsByCoordinates, field, field.getCoordinates().getX(), field.getCoordinates().getY()-1, sizeX, sizeY);
            addNeighbour(fieldsByCoordinates, field, field.getCoordinates().getX()+1, field.getCoordinates().getY()-1, sizeX, sizeY);
            addNeighbour(fieldsByCoordinates, field, field.getCoordinates().getX()+1, field.getCoordinates().getY(), sizeX, sizeY);
            addNeighbour(fieldsByCoordinates, field, field.getCoordinates().getX()+1, field.getCoordinates().getY()+1, sizeX, sizeY);
            addNeighbour(fieldsByCoordinates, field, field.getCoordinates().getX(), field.getCoordinates().getY()+1, sizeX, sizeY);
            addNeighbour(fieldsByCoordinates, field, field.getCoordinates().getX()-1, field.getCoordinates().getY()+1, sizeX, sizeY);
            addNeighbour(fieldsByCoordinates, field, field.getCoordinates().getX()-1, field.getCoordinates().getY(), sizeX, sizeY);
        });

        return fields;
    }

    private void addNeighbour( Map<Coordinates, Field> fieldsByCoordinates, Field field, int x, int y, int sizeX, int sizeY) {
        if(x < 0 || x >= sizeX || y < 0 || y>= sizeY) {
            return;
        }
        Coordinates coordinates = new Coordinates(x,y);
        Field neighbour = fieldsByCoordinates.get(coordinates);
        if(!field.getNeighbours().containsKey(neighbour.getLetter())) {
            field.getNeighbours().put(neighbour.getLetter(), new ArrayList<>());
        }
        field.getNeighbours().get(neighbour.getLetter()).add(neighbour);

    }
}
