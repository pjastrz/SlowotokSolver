package pl.slowotok;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Created by pawel on 2015-10-22.
 */
public class Solver {

    public List<List<Field>> getSolutions(char [][] chars, List<String> vocabulary) {
        List<Field> fields = new BoardParser().parseFields(chars);
        Map<Character, List<Field>> groupebByChar = fields.stream().collect(groupingBy(Field::getLetter));
        List<String> sortedByLength = vocabulary.stream().filter(word -> word.length() <= fields.size())
                .sorted((a, b) -> b.length() - a.length())
                .collect(toList());

        return sortedByLength.stream().map(word -> canBuild(fields, groupebByChar, word))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    Optional<List<Field>> canBuild(List<Field> fields, Map<Character, List<Field>> fieldsByChar,  String word) {
        Character [] chars = ArrayUtils.toObject(word.toCharArray());
        char first = chars[0];

        return fieldsByChar.getOrDefault(first, Collections.emptyList()).stream().map(field ->  canBuildFrom(field, Collections.emptySet(), chars,0))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findAny();
    }

    Optional<List<Field>> canBuildFrom(Field startingField, final Set<Field> usedFields, Character [] word, int letterIndex) {
        if(letterIndex +1 == word.length) {
            return Optional.of(Collections.singletonList(startingField));
        }
        Character letter = word[letterIndex +1 ];
        List<Field> possibleFields = startingField.getNeighbours().getOrDefault(letter, Collections.emptyList()).stream().filter(field -> !usedFields.contains(field)).collect(toList());
        if(possibleFields.size() == 0) {
            return Optional.empty();
        }
        Set<Field> extendedUserFields = new HashSet<>(usedFields);
        extendedUserFields.add(startingField);
        for(Field possibleField : possibleFields) {

            Optional<List<Field>> subSolution = canBuildFrom(possibleField, extendedUserFields, word, letterIndex +1);
            if(subSolution.isPresent()) {
                List<Field> solution = new ArrayList<>();
                solution.add(startingField);
                solution.addAll(subSolution.get());
                return Optional.of(solution);
            }
        }
        return Optional.empty();
    }
}
