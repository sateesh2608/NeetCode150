package com.neetcode150.heap_priorityqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Q6DesignTwitter {

	
	public static void main(String[] args) {
		Twitter twitter = new Twitter();

        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1)); 
        // Expected: [5]

        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1)); 
        // Expected: [6, 5]

        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1)); 
        // Expected: [5]
	}

	static class Twitter {

		private static int timeStamp = 0;

		static class Tweet{
			
			int id;
			int time;
			Tweet next;
			
			public Tweet(int id, int time) {
				super();
				this.id = id;
				this.time = time;
				this.next = null;
			}
			
		}
		
		private HashMap<Integer, Tweet> tweetsMap;
		private HashMap<Integer, Set<Integer>> userFollowees;
		
	    public Twitter() {
	    	tweetsMap = new HashMap<Integer, Tweet>();
	    	userFollowees = new HashMap<>();
	    }
	    
	    public void postTweet(int userId, int tweetId) {
	     
	    	Tweet newTweet = new Tweet(tweetId, timeStamp--);
	    	newTweet.next = tweetsMap.get(userId);
	    	tweetsMap.put(userId, newTweet);
	    }
	    
	    /** Get 10 most recent tweets */
	    public List<Integer> getNewsFeed(int userId) {
	        
	    	//max Heap to get tweets in descending order so that will get top 10 recent ones 
	    	PriorityQueue<Tweet> maxHeap =
	                new PriorityQueue<>((a, b) -> Integer.compare(a.time, b.time));
	    	
	    	// user tweets added to priority queue.
	    	if(tweetsMap.containsKey(userId)) {
	    		maxHeap.offer(tweetsMap.get(userId));
	    	}
	    	
	    	// add followees tweets to priority queue
	    	for (Integer followeeId : userFollowees.getOrDefault(userId, new HashSet<>())) {
	    		if(tweetsMap.containsKey(followeeId))
	    			maxHeap.offer(tweetsMap.get(followeeId));
			}
	    	
	    	List<Integer> resultList = new ArrayList<>();
	    	
	    	while(!maxHeap.isEmpty() && resultList.size() < 10) {
	    		Tweet curr = maxHeap.poll();
	    		resultList.add(curr.id);
	    		if(curr.next != null) {
	    			maxHeap.offer(curr.next);
	    		}
	    	}
	    	
	    	return resultList; 
	    }
	    
	    public void follow(int followerId, int followeeId) {
	        
	    	if(followerId == followeeId) return;
	    	userFollowees.computeIfAbsent(followerId, k-> new HashSet<>()).add(followeeId);
	    }
	    
	    public void unfollow(int followerId, int followeeId) {
	        if(userFollowees.containsKey(followerId)) {
	        	userFollowees.get(followerId).remove(followeeId);
	        }
	    }
	}
}
