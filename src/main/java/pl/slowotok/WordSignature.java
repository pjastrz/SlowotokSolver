package pl.slowotok;

import java.util.Map;

/**
 * Created by pawel on 2015-10-22.
 */
public class WordSignature {

    Map<Character, Integer> lettersCounts;

    public WordSignature(Map<Character, Integer> lettersCounts) {
        this.lettersCounts = lettersCounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordSignature that = (WordSignature) o;

        return !(lettersCounts != null ? !lettersCounts.equals(that.lettersCounts) : that.lettersCounts != null);

    }

    @Override
    public int hashCode() {
        return lettersCounts != null ? lettersCounts.hashCode() : 0;
    }
}
