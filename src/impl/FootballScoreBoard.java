package impl;

import api.ScoreBoardRecordable;
import models.MatchInfo;
import models.MatchResultChangeRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FootballScoreBoard implements ScoreBoardRecordable {
    private final Map<Integer, MatchInfo> scoreBoard = new HashMap<>();

    @Override
    public MatchInfo startMatch(String homeTeam, String guestTeam) {
        return null;
    }

    @Override
    public MatchInfo finishMatch(String homeTeam, String guestTeam) {
        return null;
    }

    @Override
    public MatchInfo updateScore(MatchResultChangeRequest matchResultChangeRequest) {
        return null;
    }

    @Override
    public List<MatchInfo> getBoardSummary() {
        return List.of();
    }

    private record MatchKey(String homeTeam, String awayTeam) { }
}
