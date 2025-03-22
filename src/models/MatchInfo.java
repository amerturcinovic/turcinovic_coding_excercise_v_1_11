package models;

public record MatchInfo(
        String homeTeamName,
        Integer homeTeamScore,
        String guestTeamName,
        Integer guestTeamScore
) {
    public MatchInfo(String homeTeamName, String guestTeamName) {
        this(homeTeamName, 0, guestTeamName, 0);
    }
}
