package impl;

import api.TrackableScoreBoard;
import impl.models.MatchInfo;
import impl.persistance.Repository;
import impl.persistance.SimpleOrderedInMemoryCollection;

import java.util.List;

/***
 * Implementation of TrackableScoreBoard for football world cup
 * Default implementation of this use simple in memory ordered collection that is not thread safe
 * For different implementation you must provide your own Repository implementation for this class
 */
class FootballWorldCupScoreBoard implements TrackableScoreBoard {
    private final Repository repository;

    public FootballWorldCupScoreBoard(Repository repository) {
        this.repository = repository;
    }

    public FootballWorldCupScoreBoard() {
        repository = new SimpleOrderedInMemoryCollection();
    }

    @Override
    public MatchInfo startMatch(String homeTeamName, String guestTeamName) throws IllegalArgumentException {
        validateArguments(homeTeamName, guestTeamName);
        return repository.save(new MatchInfo(homeTeamName, guestTeamName));
    }

    @Override
    public MatchInfo finishMatch(String homeTeamName, String guestTeamName) throws IllegalArgumentException {
        validateArguments(homeTeamName, guestTeamName);
        return repository.delete(new MatchInfo(homeTeamName, guestTeamName));
    }

    @Override
    public MatchInfo updateMatch(MatchInfo matchInfo) throws IllegalArgumentException {
        validateArguments(matchInfo);
        return repository.update(matchInfo);
    }

    @Override
    public List<MatchInfo> getBoardRanking() {
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
