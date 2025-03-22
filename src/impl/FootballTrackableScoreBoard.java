package impl;

import api.TrackableScoreBoard;
import impl.models.MatchInfo;
import impl.persistance.InMemoryCollection;
import impl.persistance.Repository;

import java.util.List;

class FootballTrackableScoreBoard implements TrackableScoreBoard {
    private final Repository repository = new InMemoryCollection();

    @Override
    public MatchInfo startMatch(String homeTeamName, String guestTeamName) {
        validateArguments(homeTeamName, guestTeamName);
        return repository.save(new MatchInfo(homeTeamName, guestTeamName));
    }

    @Override
    public MatchInfo finishMatch(String homeTeamName, String guestTeamName) {
        validateArguments(homeTeamName, guestTeamName);
        return repository.delete(new MatchInfo(homeTeamName, guestTeamName));
    }

    @Override
    public MatchInfo updateMatch(MatchInfo matchInfo) {
        validateArguments(matchInfo);
        return repository.update(matchInfo);
    }

    @Override
    public List<MatchInfo> geBoardRanking() {
        return repository.getAll();
    }

    @Override
    public String toString() {
        return repository.toString();
    }

    private void validateArguments(String homeTeam, String guestTeam) {
        if (homeTeam == null || guestTeam == null || homeTeam.isEmpty() || guestTeam.isEmpty())
            throw new IllegalArgumentException("You must provide name for both teams");
    }

    private void validateArguments(MatchInfo matchInfo) {
        validateArguments(matchInfo.homeTeamName(), matchInfo.guestTeamName());

        if (matchInfo.guestTeamScore() < 0 || matchInfo.homeTeamScore() < 0)
            throw new IllegalArgumentException("Score number must be positive number and greater then zero");
    }
}
