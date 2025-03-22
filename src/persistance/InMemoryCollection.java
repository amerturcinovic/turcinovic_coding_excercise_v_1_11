package persistance;

import models.MatchInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCollection implements Repository {
    private final Map<Integer, MatchDetailsEntity> storage = new ConcurrentHashMap<>();

    @Override
    public MatchInfo save(MatchInfo matchInfo) {
        MatchDetailsEntity matchDetailsEntity = toMatchDetails(matchInfo);

        storage.computeIfPresent(matchDetailsEntity.hashCode(), (_, _) -> {
            throw new IllegalArgumentException("Match already started");
        });

        storage.putIfAbsent(matchDetailsEntity.hashCode(), matchDetailsEntity);

        return toMatchInfo(matchDetailsEntity);
    }

    @Override
    public MatchInfo delete(MatchInfo matchInfo) {
        var matchDetailsEntity = new MatchDetailsEntity(matchInfo.homeTeamName(), matchInfo.guestTeamName());

        storage.computeIfPresent(matchDetailsEntity.hashCode(), (_, _) -> null);

        return toMatchInfo(matchDetailsEntity);
    }

    @Override
    public MatchInfo update(MatchInfo matchInfo) {
        var matchDetailsEntity = toMatchDetails(matchInfo);

        storage.computeIfPresent(matchDetailsEntity.hashCode(), (_, _) -> matchDetailsEntity);

        return matchInfo;
    }

    @Override
    public List<MatchInfo> getAll() {
        return storage
                .values()
                .stream()
                .map(this::toMatchInfo)
                .toList();
    }
}
