package pl.slowotok;

import java.util.List;

/**
 * Created by pawel on 2015-10-22.
 */
public class SolutionToString {
    public String pathToString(List<Field> path) {
        StringBuilder sb = new StringBuilder();
        path.stream().forEach(field -> {
            sb.append(field.getLetter());
        });
        return sb.toString();
    }
}
