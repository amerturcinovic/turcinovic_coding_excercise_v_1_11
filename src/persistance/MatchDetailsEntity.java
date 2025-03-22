package persistance;

import java.util.Objects;

record MatchDetailsEntity(
        String homeTeamName,
        Integer homeTeamScore,
        String guestTeamName,
        Integer guestTeamScore
) implements Comparable<MatchDetailsEntity> {
    public MatchDetailsEntity(String homeTeam, String guestTeamName) {
        this(homeTeam, 0, guestTeamName, 0);
    }

    @Override
    public String toString() {
        return homeTeamName + " " + homeTeamScore + " - " + guestTeamName + " " + guestTeamScore;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MatchDetailsEntity match = (MatchDetailsEntity) obj;
        return (homeTeamName + guestTeamName).equals(match.homeTeamName + match.guestTeamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeamName, guestTeamName);
    }

    @Override
    public int compareTo(MatchDetailsEntity matchDetailsEntity) {
        return (matchDetailsEntity.homeTeamScore() + matchDetailsEntity.guestTeamScore()) - (homeTeamScore + guestTeamScore);
    }
}