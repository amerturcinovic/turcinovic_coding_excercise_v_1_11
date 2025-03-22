package impl;

import models.MatchInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FootballScoreBoardTest {
    @Test
    public void whenOneMatchStarted_ExpectToShow_OnScoreBoard() {
        var expectedResult = new MatchInfo("BRAZIL", 0, "ARGENTINA", 0);

        MatchInfo actualResult =  new FootballScoreBoard().startMatch("BRAZIL", "ARGENTINA");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void whenMatchAlreadyStarted_And_FinishedCalled_Expect_EmptyScoreBoard() {
        var footballScoreBoard = new FootballScoreBoard();

        footballScoreBoard.startMatch("BRAZIL", "ARGENTINA");
        footballScoreBoard.finishMatch("BRAZIL", "ARGENTINA");
        assertEquals(List.of(), footballScoreBoard.getBoardSummary());
    }

    @Test
    public void whenMatchAlreadyStarted_And_ResultUpdated_Expect_ToShow_OnScoreBoard() {
        var footballScoreBoard = new FootballScoreBoard();

        footballScoreBoard.startMatch("BRAZIL", "ARGENTINA");
        footballScoreBoard.updateScore(
                new MatchInfo(
                        "BRAZIL",
                        1,
                        "ARGENTINA",
                        1
                )
        );
        assertEquals(List.of(
                new MatchInfo(
                        "BRAZIL", 1, "ARGENTINA", 1
                )
        ), footballScoreBoard.getBoardSummary());
    }

    @ParameterizedTest
    @CsvSource({",\"\"", "\"\",", ","})
    public void whenStartMatchCalled_WithInvalidArgument_Expect_Exception(String testHomeTeamName, String testGuestTeamName) {
        assertThrows(IllegalArgumentException.class, () ->   new FootballScoreBoard().startMatch(testHomeTeamName, testGuestTeamName));
    }

    @ParameterizedTest
    @CsvSource({",\"\"", "\"\",", ","})
    public void whenFinishMatchCalled_WithInvalidArgument_Expect_Exception(String testHomeTeamName, String testGuestTeamName) {
        assertThrows(IllegalArgumentException.class, () ->   new FootballScoreBoard().finishMatch(testHomeTeamName, testGuestTeamName));
    }

    @ParameterizedTest
    @CsvSource({",\"\"", "\"\",", ","})
    public void whenUpdateMatchCalled_WithInvalidArgument_Expect_Exception(String testHomeTeamName, String testGuestTeamName) {
        assertThrows(IllegalArgumentException.class, () ->   new FootballScoreBoard().updateScore(
                new MatchInfo(testHomeTeamName, 1, testGuestTeamName, 0)
        ));
    }
}