package api;

import models.MatchInfo;
import models.MatchResultChangeRequest;

import java.util.List;

public interface ScoreBoardRecordable {
    MatchInfo startMatch(String homeTeam, String guestTeam);
    MatchInfo finishMatch(String homeTeam, String guestTeam);
    MatchInfo updateScore(MatchResultChangeRequest matchResultChangeRequest);
    List<MatchInfo> getBoardSummary();
}
