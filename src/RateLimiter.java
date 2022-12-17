import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

interface RateLimiter {
    boolean grantAccess();
}

class FixedWindow implements RateLimiter {
    int windowSize; // Eg: 1 sec
    int maxLimit; // Eg: 3 requests
    int curCount;
    long lastAccessTimestampInSecs;
    
    public FixedWindow(int windowSize, int maxLimit) {
        this.windowSize = windowSize;
        this.maxLimit = maxLimit;
        this.curCount = 0;
        this.lastAccessTimestampInSecs = 0;
    }
    
	@Override
	public boolean grantAccess() {
        long curTimeinSecs = System.currentTimeMillis()/1000;
        if (curTimeinSecs - lastAccessTimestampInSecs >= windowSize || curCount == 0) {
            curCount = 1;
            lastAccessTimestampInSecs = curTimeinSecs;
            return true;
        } else {
            if (curCount < maxLimit) {
                curCount++;
                return true;
            }
        }
		return false;
	}
}

class SlidingWindow implements RateLimiter {
    Queue<Long> window;
    int windowSize; // Eg: 1 sec
    int maxLimit; // Eg: 3 requests
    
    public SlidingWindow(int windowSize, int maxLimit) {
        this.windowSize = windowSize;
        this.maxLimit = maxLimit;
        window = new ConcurrentLinkedQueue<>();    
    }
    
	@Override
	public boolean grantAccess() {
        long curTimeInSecs = System.currentTimeMillis() / 1000;
        checkAndUpdate(curTimeInSecs);
		if (window.size() < maxLimit) {
            window.offer(curTimeInSecs);
            return true;
        }
		return false;
	}
    
    private void checkAndUpdate(long curTimeInSecs) {
        while (!window.isEmpty() && (curTimeInSecs - window.peek() >= windowSize)) {
            window.poll();
        }
    }
    
}

class UserBucketFixedWindow {
    Map<Integer, FixedWindow> map;
    
    public UserBucketFixedWindow() {
        map = new HashMap<>();
    }
    
    void getApplicationAccess(int userId) {
        map.putIfAbsent(userId, new FixedWindow(1, 3));
        if (map.get(userId).grantAccess()) {
            System.out.println("Access Granted");
        } else {
            System.out.println("Too many requests!");
        }
    }
}

class UserBucketSlidingWindow {
    Map<Integer, SlidingWindow> map;
    
    public UserBucketSlidingWindow() {
        map = new HashMap<>();
    }
    
    void getApplicationAccess(int userId) {
        map.putIfAbsent(userId, new SlidingWindow(1, 3));
        if (map.get(userId).grantAccess()) {
            System.out.println("Access Granted");
        } else {
            System.out.println("Too many requests!");
        }
    }
}


// Main class should be named 'Solution'
class Solution {
    public static void main(String[] args) {
        UserBucketFixedWindow bucketFW = new UserBucketFixedWindow();
        for (int i=0; i<5; i++) {
            bucketFW.getApplicationAccess(1);
        }
        bucketFW.getApplicationAccess(2);
        
        
        UserBucketSlidingWindow bucketSW = new UserBucketSlidingWindow();
        for (int i=0; i<5; i++) {
            bucketSW.getApplicationAccess(1);
        }
        bucketSW.getApplicationAccess(2);
    }
}