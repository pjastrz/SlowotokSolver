package pl.slowotok

import spock.lang.Specification

/**
 * Created by pawel on 2015-10-22.
 */
class BoardParserTest extends Specification {

    def underTest = new BoardParser()

    def 'should porperly parse 1x1 board'(){
        given:
        def board = [['a' as char] as char[] ] as char[][]

        def expectedField = new Field('a' as char, new Coordinates(0,0))

        expect:
        [expectedField] == underTest.parseFields(board)

    }

    def 'should properly parse 2x1 board'() {
        given:
        def board = [['a' as char, 'b' as char] as char[] ] as char[][]

        def expectedField1 = new Field('a' as char, new Coordinates(0,0))
        def expectedField2 = new Field('b' as char, new Coordinates(0,1))
        expectedField1.neighbours.put('b' as char, [expectedField2])
        expectedField2.neighbours.put('a' as char, [expectedField1])

        when:
        def result =  underTest.parseFields(board) as Set

        then:
        result == [expectedField1, expectedField2] as Set;
    }
}
