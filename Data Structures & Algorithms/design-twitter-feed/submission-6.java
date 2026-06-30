// Design Twitter
// Medium
// Topics
// Company Tags
// Hints
// Implement a simplified version of Twitter which allows users to post tweets, follow/unfollow each other, and view the 10 most recent tweets within their own news feed.

// Users and tweets are uniquely identified by their IDs (integers).

// Implement the following methods:

// Twitter() Initializes the twitter object.
// void postTweet(int userId, int tweetId) Publish a new tweet with ID tweetId by the user userId. 
// You may assume that each tweetId is unique.

// List<Integer> getNewsFeed(int userId) Fetches at most the 10 most recent tweet IDs in the user's news feed.
//  Each item must be posted by users who the user is following or by the user themself. 
// Tweets IDs should be ordered from most recent to least recent.

// void follow(int followerId, int followeeId) The user with ID followerId follows the user with ID followeeId.


// void unfollow(int followerId, int followeeId) The user with ID followerId unfollows the user with ID followeeId.

// Example 1:

// Input:
// ["Twitter", "postTweet", [1, 10], "postTweet", [2, 20], 
// "getNewsFeed", [1], "getNewsFeed", [2], "follow", [1, 2], 
// "getNewsFeed", [1], "getNewsFeed", [2], "unfollow", [1, 2],
//  "getNewsFeed", [1]]

// Output:
// [null, null, null, [10], [20], null, [20, 10], [20], null, [10]]

// Explanation:
// Twitter twitter = new Twitter();
// twitter.postTweet(1, 10); // User 1 posts a new tweet with id = 10.
// twitter.postTweet(2, 20); // User 2 posts a new tweet with id = 20.
// twitter.getNewsFeed(1);   // User 1's news feed should only contain their own tweets -> [10].
// twitter.getNewsFeed(2);   // User 2's news feed should only contain their own tweets -> [20].
// twitter.follow(1, 2);     // User 1 follows user 2.
// twitter.getNewsFeed(1);   // User 1's news feed should contain both tweets from user 1 and user 2 -> [20, 10].
// twitter.getNewsFeed(2);   // User 2's news feed should still only contain their own tweets -> [20].
// twitter.unfollow(1, 2);   // User 1 unfollows user 2.
// twitter.getNewsFeed(1);   // User 1's news feed should only contain their own tweets -> [10].
// Constraints:

// 1 <= userId, followerId, followeeId <= 100
// 0 <= tweetId <= 1000


// ["Twitter", "postTweet", [1, 101], "postTweet", [1, 102], "follow", [2, 1], "getNewsFeed", [2], "unfollow", [2, 1], "getNewsFeed", [2]]


// Your Output:


// [null,null,null,null,[101,102],null,[]]
// Expected output:


// [null,null,null,null,[102,101],null,[]]


// 53分开始的


class Twitter {
    // 核心数据结构？
    // 每个user id的发推信息。
    // 根据时间线，倒序排列。默认的话，就是插入的是倒着的，头插法。始终有序。时间大的在前。
    


    // 之后，要维护follow表？
    // Map《user id，map〈followed user id？〉》


    // 这样的话，去拿信息的话，就是直接合并链表？

    // 发推的信息，因为要按照时间序列，所以，就是除了tweetId之外，还要有一个时间戳。戳子用一个递增的long就可以了？


    public static class Tweet{
        int tweetId;
        long timestamp;
    }

    public long timestamp = 0;


    // 说是说链表，不过我们用一个linkedlist就方便了，不用真的自己维护链表，太繁琐。
    public Map<Integer,LinkedList<Tweet>> tweets = new HashMap<>();

    public Map<Integer,Set<Integer>> follow = new HashMap<>();


    // 没有处理好，自己follow自己
    // 逻辑上，自己follow自己是必然的。
    // 所以就是说，直接add进去，并且，如果有unfollow，不进行删除。


    public Twitter() {
        
    }
    // 因为有remove，应该用LinkedList！！
    
    public void postTweet(int userId, int tweetId) {
        // 开始就是说发推特。
        Tweet tweet = new Tweet();
        tweet.tweetId = tweetId;
        tweet.timestamp = this.timestamp+1;
        this.timestamp = this.timestamp+1; 
        if(!tweets.containsKey(userId)){
            tweets.put(userId,new LinkedList<Tweet>());
        }
        tweets.get(userId).addFirst(tweet);

        follow(userId,userId);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        // 开启找到所有相关的LinkedList，之后合并。
        LinkedList<LinkedList<Tweet>> lists = new LinkedList<>();
        if(follow.containsKey(userId)){
            for(Integer i: follow.get(userId)){
                if(tweets.containsKey(i) && tweets.get(i).size()!=0){
                    lists.add(copy(tweets.get(i)));
                }
            }
        }

        // 现在已经就绪，开启下一步，考虑到就是说负载，暂时不用这个最大堆？
        // 因为逻辑上，不算是特别麻烦吧，后续再考虑优化这个时间复杂度。

        while(lists.size()!=0 && result.size() <10){
            // 有不是空的。

            // 遍历，找到这个最大的。
            int largestIndex = 0;
            for(int i = 0;i<lists.size();i++){
                if(lists.get(i).getFirst().timestamp > lists.get(largestIndex).getFirst().timestamp ){
                    largestIndex = i;
                }
            }
            // 好的，现在，我们找到了这个最大的了。
            // 先塞入
            result.add(lists.get(largestIndex).getFirst().tweetId);
            // 现在，开始更新这个list。
            if(lists.get(largestIndex).size()==1){
                // 最后一个了。特化处理。
                lists.remove(largestIndex);
            }else{
                lists.get(largestIndex).removeFirst();
            }
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        if(!follow.containsKey(followerId)){
            follow.put(followerId,new HashSet<>());
        }
        follow.get(followerId).add(followeeId);        
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(followerId!=followeeId)
            follow.get(followerId).remove(followeeId); 
    }

    public LinkedList<Tweet> copy(LinkedList<Tweet> original){
         LinkedList<Tweet>  result = new  LinkedList<Tweet>();
         for(Tweet t : original){
            result.add(t);
         }
         return result;
    }
}
