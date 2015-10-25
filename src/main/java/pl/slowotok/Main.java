package pl.slowotok;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pawel on 2015-10-22.
 */
public class Main {

    public static void main(String [] args) throws IOException {
        String str = "nyb¹mobêpieirzzæ";
        char [] chars = str.toCharArray();
        char [][] board = new char[4][4];
        for(int  i =0 ; i< 4; i++) {
            board[i] = Arrays.copyOfRange(chars, i*4, i*4 + 4);
        }

        File vocabulary = new File("src/main/resources/dic2");
        List<String> lines = FileUtils.readLines(vocabulary, "UTF-8");

        List<List<Field>> solutions = new Solver().getSolutions(board, lines);
        SolutionToString solutionToString = new SolutionToString();
        for(int i = 0; i < Math.min(20, solutions.size()); i++) {
            System.out.println(solutionToString.pathToString(solutions.get(i)));
            new SolutionPrinter().printSolution(solutions.get(i), 4,4);
        }



    }
}
