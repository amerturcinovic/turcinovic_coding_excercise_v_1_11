package api;

import models.MatchInfo;

import java.util.List;

public interface ScoreBoardRecordable {
    void startMatch(String homeTeam, String guestTeam);
    void finishMatch(String homeTeam, String guestTeam);
    void updateScore(MatchInfo matchInfo);
    List<MatchInfo> getBoardSummary();
}
