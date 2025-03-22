package api;

import models.MatchInfo;

import java.util.List;

public interface ScoreBoardRecordable {
    MatchInfo startMatch(String homeTeam, String guestTeam) throws IllegalArgumentException;
    MatchInfo finishMatch(String homeTeam, String guestTeam) throws IllegalArgumentException;
    MatchInfo updateScore(MatchInfo matchInfo) throws IllegalArgumentException;
    List<MatchInfo> getBoardSummary();
}
