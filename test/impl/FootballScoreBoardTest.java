package impl;

import models.MatchInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FootballScoreBoardTest {
    @Test
    public void whenMatchStartedExpectToShowOnBoardSummary() {
        var footballScoreBoard = new FootballScoreBoard();

        footballScoreBoard.startMatch("BRAZIL", "ARGENTINA");

        assertEquals(
                List.of(
                        new MatchInfo(
                                "ARGENTINA", 0, "BRAZIL", 0
                        )
                ),
                footballScoreBoard.getBoardSummary()
        );
    }
}