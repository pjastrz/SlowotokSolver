package pl.slowotok

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by pawel on 2015-10-22.
 */
class SolverTest extends Specification {
    def underTest = new Solver()
    def static board = [
            ['a' as char, 'b' as char, 'c' as char] as char[],
            ['d' as char, 'e' as char, 'f' as char] as char[],
            ['e' as char, 'f' as char, 'g' as char] as char[]


    ] as char[][]

    @Unroll
    def 'for vocabulary: #vocabulary, whould find: #expectedResult'() {
        expect:
        def result = underTest.getSolutions(board, vocabulary)
        def resultStrings = result.collect { new SolutionToString().pathToString(it) }
        resultStrings as Set == expectedResult as Set

        where:
        comment                      | vocabulary     || expectedResult
        'simple case'                | ['abc']        || ['abc']
        'right to left'              | ['cba']        || ['cba']
        'multiple words'             | ['abc', 'cba'] || ['abc', 'cba']
        'change direction'           | ['aedb']       || ['aedb']
        'only neighbours build path' | ['cab']        || []
        'dont use same field twice'  | ['abeda']      || []
    }
}
