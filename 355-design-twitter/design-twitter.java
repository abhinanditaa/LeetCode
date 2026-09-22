import java.util.*;

class Twitter {

    private static class Tweet {
        int id;
        int time;
        Tweet next;

        Tweet(int id, int time, Tweet next) {
            this.id = id;
            this.time = time;
            this.next = next;
        }
    }

    private final Map<Integer, Set<Integer>> followees;
    private final Map<Integer, Tweet> tweets;
    private int time;

    public Twitter() {
        followees = new HashMap<>();
        tweets = new HashMap<>();
        time = 0;
    }

    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(
            tweetId,
            time++,
            tweets.get(userId)
        );

        tweets.put(userId, newTweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>(10);

        PriorityQueue<Tweet> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b.time, a.time)
        );

        // User's own tweets
        Tweet own = tweets.get(userId);
        if (own != null) {
            pq.offer(own);
        }

        // Followed users' tweets
        Set<Integer> following = followees.get(userId);

        if (following != null) {
            for (int followee : following) {
                Tweet tweet = tweets.get(followee);

                if (tweet != null) {
                    pq.offer(tweet);
                }
            }
        }

        // Get at most 10 most recent tweets
        while (!pq.isEmpty() && result.size() < 10) {
            Tweet current = pq.poll();

            result.add(current.id);

            // Add the next older tweet from the same user
            if (current.next != null) {
                pq.offer(current.next);
            }
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {
        followees
            .computeIfAbsent(followerId, k -> new HashSet<>())
            .add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> set = followees.get(followerId);

        if (set != null) {
            set.remove(followeeId);
        }
    }
}