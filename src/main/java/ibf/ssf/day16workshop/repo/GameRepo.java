package ibf.ssf.day16workshop.repo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf.ssf.day16workshop.model.Game;
import ibf.ssf.day16workshop.util.Util;

@Repository
public class GameRepo {

    @Autowired
    @Qualifier(Util.REDIS_TWO)
    // RedisTemplate template;
    HashOperations<String, String, Game> hashOps;

    public void test() {
        // HashOperations hashOps = template.opsForHash();
    }

    // Create (in Redis Map)
    public void saveGame(Game game) {
        hashOps.put(Util.KEY_GAME, game.getGameId(), game);
    }

    // Read (from Redis Map)
    public Map<String, Game> getAllGames() {
        return hashOps.entries(Util.KEY_GAME);
    }

    // Read one specific record
    public Game getGame(String gameId) {
        return hashOps.get(Util.KEY_GAME, gameId);
    }

    public void updateGame(Game game) {
        hashOps.put(Util.KEY_GAME, game.getGameId(), game); // if absent, create. if present, update.
    }

    // Delete operateions of a record
    public void deleteGame(String gameId) {
        hashOps.delete(Util.KEY_GAME, gameId);
    }

}
