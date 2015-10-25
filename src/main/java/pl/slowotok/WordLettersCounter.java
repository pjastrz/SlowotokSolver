package pl.slowotok;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pawel on 2015-10-22.
 */
public class WordLettersCounter {

    public WordSignature countLetters(String word) {
        Map<Character, Integer> map = new HashMap<>();
        char [] chars = word.toCharArray();

        for(char c : chars) {
            if(!map.containsKey(c)) {
                map.put(c, 1);
            }
            else map.put(c, map.get(c) +1);
        }
        return new WordSignature(map);
    }
}
