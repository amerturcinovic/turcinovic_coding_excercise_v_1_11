package impl;

import api.ScoreBoardRecordable;
import models.MatchInfo;
import persistance.InMemoryCollection;
import persistance.Repository;

import java.util.List;

public class FootballScoreBoard implements ScoreBoardRecordable {
    private final Repository repository = new InMemoryCollection();

    @Override
    public MatchInfo startMatch(String homeTeam, String guestTeam) {
        validateArguments(homeTeam, guestTeam);

        MatchInfo matchInfo = new MatchInfo(homeTeam, guestTeam);
        return repository.save(matchInfo);
    }

    @Override
    public MatchInfo finishMatch(String homeTeam, String guestTeam) {
        validateArguments(homeTeam, guestTeam);

        MatchInfo matchInfo = new MatchInfo(homeTeam, guestTeam);
        return repository.delete(matchInfo);
    }

    @Override
    public MatchInfo updateScore(MatchInfo matchInfo) {
        validateArguments(matchInfo);
        return repository.update(matchInfo);
    }

    @Override
    public List<MatchInfo> getBoardSummary() {
        return repository.getAll();
    }

    private void validateArguments(String homeTeam, String guestTeam) {
        if (homeTeam == null || guestTeam == null || homeTeam.isEmpty() || guestTeam.isEmpty())
            throw new IllegalArgumentException("You must provide name for both teams");
    }

    private void validateArguments(MatchInfo matchInfo) {
        validateArguments(matchInfo.homeTeamName(), matchInfo.guestTeamName());

        if (matchInfo.guestTeamScore() < 1 || matchInfo.homeTeamScore() < 1)
            throw new IllegalArgumentException("Score number must be positive number and greater then zero");
    }
}
