package hoangdz.discord.redispubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.function.Function;

public class RedisManager {
    private static RedisManager instance = new RedisManager();
    private static final String RedisServer = "27.27.0.1";
    private static final int RedisPort = 6379;
    // A static getter for the instance so that it can be retrieved without making a new instance.
    public static RedisManager getInstance(){
        return instance;
    }

    // A private constructor so instances cannot be created from out of the class.
    private RedisManager() {
    }

    public void subscribe(Function<String, String> callback){
        Jedis subscriber = new Jedis(RedisServer, RedisPort);
        subscriber.connect();

        new Thread("Redis Subscriber"){
            @Override
            public void run(){
                String[] channels = {"server_events"};
                subscriber.subscribe(new JedisPubSub(){
                    @Override
                    public void onMessage(String channel, String message){
                        callback.apply(channel + " :: " + message);
                    }
                }, channels);
            }
        }.start();
    }

    public void publish(String message){
        String channel = "server_events";
        try(Jedis publisher = new Jedis(RedisServer, RedisPort)){
            publisher.publish(channel, message);
        }
    }
}
