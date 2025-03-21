package impl;

import api.ScoreBoardRecordable;
import models.MatchInfo;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class FootballScoreBoard implements ScoreBoardRecordable {
    private final Map<Integer, MatchDetails> scoreBoard = new ConcurrentHashMap<>();

    @Override
    public void startMatch(String homeTeam, String guestTeam) {
        var matchDetails = new MatchDetails(homeTeam, guestTeam);
        scoreBoard.putIfAbsent(matchDetails.hashCode(), matchDetails);
    }

    @Override
    public void finishMatch(String homeTeam, String guestTeam) {
        var matchChangeInfo = new MatchDetails(homeTeam, guestTeam);
        scoreBoard.remove(matchChangeInfo.hashCode());
    }

    @Override
    public void updateScore(MatchInfo matchInfo) {
        var matchDetails = toMatchDetails(matchInfo);
        scoreBoard.computeIfPresent(matchDetails.hashCode(), (_, _) -> matchDetails);
    }

    private static MatchDetails toMatchDetails(MatchInfo matchInfo) {
        return new MatchDetails(
                matchInfo.homeTeamName(),
                matchInfo.homeTeamScore(),
                matchInfo.guestTeamName(),
                matchInfo.guestTeamScore()
        );
    }

    private MatchInfo toMatchInfo(MatchDetails matchDetails) {
        return new MatchInfo(
                matchDetails.homeTeamName,
                matchDetails.homeTeamScore(),
                matchDetails.guestTeamName(),
                matchDetails.guestTeamScore
        );
    }

    @Override
    public List<MatchInfo> getBoardSummary() {
        return scoreBoard
                .values()
                .stream()
                .map(this::toMatchInfo)
                .toList();
    }

    private record MatchDetails(
            String homeTeamName,
            Integer homeTeamScore,
            String guestTeamName,
            Integer guestTeamScore
    ) {
        public MatchDetails(String homeTeam, String guestTeamName) {
            this(homeTeam, 0, guestTeamName, 0);
        }

        @Override
        public int hashCode() {
            return Objects.hash(homeTeamName, guestTeamName);
        }
    }
}
