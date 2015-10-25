package pl.slowotok;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pawel on 2015-10-22.
 */
public class SolutionPrinter {

    public void printSolution(List<Field> fields, int width, int height) {
        int [][] solution = new int [height][width];
        for(int [] row : solution) {
            Arrays.fill(row, -1);
        }

        for(int i = 0; i< fields.size(); i++) {
            Field field = fields.get(i);
            Coordinates coordinates = field.getCoordinates();
            solution[coordinates.getX()][coordinates.getY()] = i;
        }

        for(int [] row : solution) {
            StringBuilder sb = new StringBuilder();
            for(int i : row) {
                if(i == -1) {
                    sb.append("**|");
                }
                else if ( i < 10) {
                    sb.append("*" + i+ "|");
                }
                else{
                   sb.append(i +"|");
                }
            }
            System.out.println(sb.toString());
        }
    }
}
