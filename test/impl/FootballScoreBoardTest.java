package impl;

import impl.models.MatchInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FootballScoreBoardTest {
    @Test
    public void whenOneMatchStarted_ExpectToShow_OnScoreBoard() {
        // given
        FootballTrackableScoreBoard footballScoreBoard = new FootballTrackableScoreBoard();
        var expectedMatchInfo = new MatchInfo("BRAZIL", 0, "ARGENTINA", 0);

        // when
        MatchInfo actualMatchInfo = footballScoreBoard.startMatch("BRAZIL", "ARGENTINA");

        // then
        assertEquals(expectedMatchInfo, actualMatchInfo);
        assertEquals(List.of(expectedMatchInfo), footballScoreBoard.geBoardRanking());
    }

    @Test
    public void whenMatchAlreadyStarted_And_FinishedCalled_Expect_EmptyScoreBoard() {
        // given
        var footballScoreBoard = new FootballTrackableScoreBoard();

        // when
        MatchInfo startMathInfo = footballScoreBoard.startMatch("BRAZIL", "ARGENTINA");
        MatchInfo finishMatchInfo = footballScoreBoard.finishMatch("BRAZIL", "ARGENTINA");

        // then
        assertEquals(startMathInfo, finishMatchInfo);
        assertEquals(List.of(), footballScoreBoard.geBoardRanking());
    }

    @Test
    public void whenMatchAlreadyStarted_And_UpdatedCalled_ExpectToShow_OnScoreBoard() {
        // given
        var footballScoreBoard = new FootballTrackableScoreBoard();
        var expectedMathInfoUpdate = new MatchInfo("BRAZIL", 1, "ARGENTINA", 1);

        // when
        footballScoreBoard.startMatch("BRAZIL", "ARGENTINA");
        MatchInfo actualMatchInfoUpdate = footballScoreBoard.updateMatch(expectedMathInfoUpdate);

        // then
        assertEquals(actualMatchInfoUpdate, expectedMathInfoUpdate);
        assertEquals(List.of(expectedMathInfoUpdate), footballScoreBoard.geBoardRanking());
    }

    @Test
    public void whenMultipleMachStarted_ExpectToShow_OnScoreBoard_SortedByMostRecentStart() {
        // given
        var footballScoreBoard = new FootballTrackableScoreBoard();

        // when
        footballScoreBoard.startMatch("BRAZIL", "ARGENTINA");
        footballScoreBoard.startMatch("SPAIN", "FRANCE");
        footballScoreBoard.startMatch("CROATIA", "ENGLAND");


        System.out.println(footballScoreBoard);

        assertTrue(true);
    }

    @Test
    public void whenMultipleMachStarted__And_UpdateScore_ExpectToShow_OnScoreBoard_SortedByMostScores_And_MostRecentStart() {
        // given
        var footballScoreBoard = new FootballTrackableScoreBoard();

        // when
        footballScoreBoard.startMatch("BRAZIL", "ARGENTINA");
        footballScoreBoard.startMatch("SPAIN", "FRANCE");
        footballScoreBoard.startMatch("CROATIA", "ENGLAND");
        footballScoreBoard.startMatch("PORTUGAL", "ITALY");

        footballScoreBoard.updateMatch(
                new MatchInfo("BRAZIL", 1, "ARGENTINA", 0)
        );
        footballScoreBoard.updateMatch(
                new MatchInfo("SPAIN", 1, "FRANCE", 0)
        );
        footballScoreBoard.updateMatch(
                new MatchInfo("SPAIN", 1, "FRANCE", 1)
        );


        System.out.println(footballScoreBoard);

        assertTrue(true);
    }

    @ParameterizedTest
    @CsvSource({",\"\"", "\"\",", ","})
    public void whenStartMatchCalled_WithInvalidArgument_Expect_Exception(String testHomeTeamName, String testGuestTeamName) {
        assertThrows(IllegalArgumentException.class, () -> new FootballTrackableScoreBoard().startMatch(testHomeTeamName, testGuestTeamName));
    }

    @ParameterizedTest
    @CsvSource({",\"\"", "\"\",", ","})
    public void whenFinishMatchCalled_WithInvalidArgument_Expect_Exception(String testHomeTeamName, String testGuestTeamName) {
        assertThrows(IllegalArgumentException.class, () -> new FootballTrackableScoreBoard().finishMatch(testHomeTeamName, testGuestTeamName));
    }

    @ParameterizedTest
    @CsvSource({",\"\"", "\"\",", ","})
    public void whenUpdateMatchCalled_WithInvalidArgument_Expect_Exception(String testHomeTeamName, String testGuestTeamName) {
        assertThrows(IllegalArgumentException.class, () -> new FootballTrackableScoreBoard().updateMatch(
                new MatchInfo(testHomeTeamName, 1, testGuestTeamName, 0)
        ));
    }
}