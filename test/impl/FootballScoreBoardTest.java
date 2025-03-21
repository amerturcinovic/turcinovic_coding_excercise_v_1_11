package impl;

import models.MatchInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FootballScoreBoardTest {
    @Test
    public void whenMatchStartedExpectToShowOnScoreBoard() {
        var footballScoreBoard = new FootballScoreBoard();

        footballScoreBoard.startMatch("BRAZIL", "ARGENTINA");

        assertEquals(
                List.of(
                        new MatchInfo(
                                "BRAZIL", 0, "ARGENTINA", 0
                        )
                ),
                footballScoreBoard.getBoardSummary()
        );
    }

    @Test
    public void whenMatchAlreadyStartedAndFinishedCallExpectToRemoveFromScoreBoard() {
        var footballScoreBoard = new FootballScoreBoard();

        footballScoreBoard.startMatch("BRAZIL", "ARGENTINA");
        footballScoreBoard.finishMatch("BRAZIL", "ARGENTINA");
        assertEquals(List.of(), footballScoreBoard.getBoardSummary());
    }

    @Test
    public void whenMatchAlreadyStartedAndResultUpdatedExpectToShowOnmScoreBoard() {
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
}