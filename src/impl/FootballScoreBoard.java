package impl;

import api.ScoreBoardRecordable;
import models.MatchInfo;
import models.MatchResultChangeRequest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FootballScoreBoard implements ScoreBoardRecordable {
    private final Map<Integer, MatchInfo> scoreBoard = new ConcurrentHashMap<>();

    @Override
    public MatchInfo startMatch(String homeTeam, String guestTeam) {
        MatchInfo matchInfo = new MatchInfo(homeTeam, 0, guestTeam, 0);
        scoreBoard.putIfAbsent(matchInfo.hashCode(), matchInfo);
        return matchInfo;
    }

    @Override
    public MatchInfo finishMatch(String homeTeam, String guestTeam) {
        //TODO: Consider to use different value model internal and private as we do not need for remove result
        MatchInfo matchInfo = new MatchInfo(homeTeam, 0, guestTeam, 0);
        scoreBoard.remove(matchInfo.hashCode());
        return matchInfo;
    }

    @Override
    public MatchInfo updateScore(MatchResultChangeRequest matchResultChangeRequest) {
        return null;
    }

    @Override
    public List<MatchInfo> getBoardSummary() {
        return scoreBoard.values().stream().toList();
    }

    private record MatchKey(String homeTeam, String awayTeam) { }
}
