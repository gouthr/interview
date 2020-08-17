Design Twitter:
===============

Use cases / Core features:
Lets define twitter to its MVP (Minimum Viable Product).
1. Users can follow other users
2. Users can read other user's feed whom they follow

Data model:
	- User and Feed objects
	- Relation between users - one user following other user
	- Relation between users and feeds - every feed has a owner user

Server feed:
	- List tweets from users they follow with the most recent at the top. Don't have to list all the tweets. 
	Say top 20 at a time - Pagination. The feed could be ranked based on time or other personal traits like
	user's common interests. Tweets from users from the same location. Tweets from users with most common friends.
	Tweets from famous celebrities followed by the user.

@ and retweet feature. Any feed with the user mentioned using @ also needs to be listed in the feed.
Retweet also include the owner user id to be shown when retweeted.

Talk about the below features:
1. Trending topics
	- Generate and rank the top trending topics. Could be based on frequency of hash tags,
	Search query terms, pick common words/phrases from the top 'N' news topics in the last n hours.
	-Ranking the topics could be based on user's interests (based on favorites, retweets and comments) 
2. Moments
	- Organize the trending topics into pre-defined categories like news, sports, fun etc.
	- List the trending topics and organize them into categories. Rank the tweets in each category.
3. Whom to follow / Recommendation system
	- Recommendation system based on machine learning to recommend other users to follow
	- Provide following graphs users 2 to 3 steps away with common friends, common interests, common location,
	Users with more followers could be ranked higher while listing
	- Also include famous personalities with large number of followers in user's choice of interest
4. Search for feed
	- Weight given to certain social aspects when searching and listing search queries

How to detect fake users 
	- machine learning systems based on number of followers, tweets and amount of time logged in etc.

Design Youtube:
===============
1. Storage and data model - Youtube uses MySql DB
	- User and video tables
	- User can have login and personal details - these can be 2 separate tables
	- Video contains video metadata, likes count, view count, shares etc.
	- Relation bw users and videos - video uploaded by user, video liked by user
	
	Scaling the DB
	- Start with a single server and scale as needed as features and use case keep changing
	- Master slave model - writes to master, reads from multiple slaves
	- Sharding the DB based on user's location, route the request to the appropriate DB
	
2. Video and image storage
	- Youtube has more images than videos - multiple size images and thumbnails
3. Popular vs long tailed videos
	- Popular videos can be hosted on CDN (Content Delivery Network - nw of proxy servers around the world)
	- Long tailed videos are videos with less view counts per day (less popular), these can be stored closer to the user
	to enhance performance
	- Performance can be optimized by having separate clusters for videos and general data
4. Web server
	- a bunch of web servers behind a load balancer
	- These servers deal with authentication, session management and less heavy business logic
	- More business heavy logic are routed to dedicated servers like recommendation server, video storage server
	- A cache at the web server level and also a cache at the front end will enhance the performance for the user
5. Security
	- We also need to handle hacking view counts - These can be detected by looking at the origin IP,
	also view counts need to go with view time and other statistics like comments, likes etc. With historical data available,
	it wouldn't be difficult to predict usage patterns and detect fake view counts - a dedicated machine learning system to detect
	video hacking could be used

Design chat application - like whatsapp:
========================================
1. Use case:
	- User A sends a msg to User B
		- User A sends a msg to chat server
		- Server sends an acknowledgement (a single tick mark)
		- Now there are 2 cases:
			- B is online, server sends a msg to B
			- B is offline, a push notification is sent to B
		- B sends an acknowledgement to the server
		- server sends an acknowledgement to A that B has received (2 tick marks)
			
			
2. To enhance performance - a HTTP persistent connection can be established from the recipient to the server. The recipient
establishes a connection and waits for a response from the server. Recipient re-establishes a connection on timeout or interruption.
This enhances the performance, rather than spawn a new process/thread and generate a new HTTP request and close the connection
after the request.

3. Detecting online users - Server can send a notification when any of the friends of a user changes the status. Can be optimized 
to send status online of the user has been online for more than say 5 mins.

A simple flow of events:
	- User registers for his account 
	- User logs in with his credentials 
	- User adds/edits/deletes contacts 
	- User sends message to his contact 
	- User receives message from his contact 
	- User logs out of the server 

Server side: 
	- User account added to account-list 
	- User credentials verified while logging in and session created if success 
	- User contacts are managed with three operations addition, deletion, modification 
	- User message is queued in his contacts message queue 
	- User is delivered message from his queue if any 
	- User session is deleted

Data structures:
```
enum Status{offline, online, away}
struct Message{ User from_user; User to_user; String post_message;}
class User {Status type; Message posts[]; sendMessage(); addGroup(); updateStatus(); createGroup(); } 
struct Group { Message posts[]; User list[];}
```

Design an eCommerce website like amazon:
========================================
1. Data model
	- Users
		- user details of the purchaser
	- Product (Can have a product Category)
		- product details including category and price
	- Order
		- shopping cart order details like amount, quantity, total price
		
	Relational DB vs NoSQL DB
		- Relational DB like MySQL would need a product table. Product table would have a number of 
		columns each representing a attribute of a product.
		- NoSQL has the advantage of specifying only the details needed for that particular product. 
		Other attributes of the product can be null. Details can be stored something like a JSON.
		
2. Concurrency, consistency and Availability are very important when scaling an eCommerce site
	- Concurrency - suppose there is a single copy of the book remaining. How to ensure concurrency if
	2 users are trying to buy the same.
		- Optimistic concurrency control (OCC) - Lock the entire row for the transaction.
		This approach is better if there are a lot of conflicts.
		- Pessimistic concurrency control (PCC) - Check the value during the start of the transaction and 
		check again before committing the transaction, if the value has changed, roll-back and start over.
		
		For a site like amazon with so many products and categories, there being a conflict is less likely, 
		PCC is better suited here.
		
	- Consistency and availability go hand in hand. We need thousands of resources/servers for better availability.
	Increased availability reduces the consistency as the values need to be consistent across all resources.
		- Strong consistency - The updates are atomic across all resources like in a single system.
		- Weak consistency - The updates are not consistent across resources. In this case, it is upto
		the clients to make a decision. If a shopping cart has different values across different hosts,
		it might be more beneficial to pick the cart with more items.
		- Eventual consistency - The updates become consistent eventually over time but maybe inconsistent
		in the mean time.

Shopping-cart data structure:
```
ShoppingCart
	List<ItemOrder> items;
	add(ItemOrder)
	remove(ItemOrder)
	applyCoupan(Coupan)
	getTotal()	

ItemOrder
	Item item;
	int quantity
	double price;
	getPrice()
	getQuantity()
	changeQuantity();

Enum ItemTypes\ or Category

Item
	String name;
	ItemTypes type;
	double price;
	String itemID;
	getName()
	getItemID();
	getItemPrice();
```

Design HitCounter - Number of visitors in the last 1 min:
=========================================================
Simplest approach:
	- Log users in a DB along with timestamp, then filter and return count of visitors in the last 1m - O(n),
	n is the number of visitors
	- A simple optimization would be to sort based on timestamp

A better approach would be to maintain a queue (linkedlist) and keep appending users to the list, when the timestamp
of first entry in the queue is > 1m, remove the first entry from list. Time efficiency is optimized to O(1) put space 
efficiency still has a list of users in the queue. We could just store the timestamp and not the user object to optimize 
space.

Similar to the moving average problem below:
```
public class MovingAverage {
    private int size;
    private int sum;
    private Queue<Integer> window;

    public MovingAverage(int size) {
        this.size = size;
        this.sum = 0;
        this.window = new ArrayDeque<>();
    }

    public double next(int val) {
        window.offer(val);
        if (window.size() > size) {
            sum -= window.poll();
        }
        sum += val;
        return (double) sum / window.size();
    }
}
```

An even better space efficiency would be if we can reduce accuracy by having visitor count upto the last second.
That way space is optimized to constant as shown in the code below:
```
public class HitCounter {
    int[] hits;
    int[] times;

    public HitCounter() {
        hits = new int[300];
        times = new int[300];
    }

    public void hit(int time) {
        int index = time % 300;
        if (times[index] != time) {
            times[index] = time;
            hits[index] = 1;
        } else {
            hits[index]++;
        }
    }

    public int getHits(int time) {
        int count = 0;

        for (int i = 0; i < 300; i++) {
            if (time - times[i] < 300) {
                count += hits[i];
            }
        }

        return count;
    }
}
```
For a distributed system with a large number of requests, a lock could be used but that reduces the performance. A better approach
would be to distribute the counters across hosts and then add them back.


Design ticketmaster:
====================
Design a ticket sales site where people can buy tickets to sporting events and concerts.

MVP:
User case:
1. User looks at the list of events in a time range - say 3 months
2. User selects an event
3. User chooses a seat from amongst the available
4. User purchases the ticket to the event

User's browser  --> Web server ---> DB

Data Model:
	- Users
		- userId
		- userName
		- userCreditCard
		- userCreditCardExpiry
		- userCVV
	- Events
		- eventId
		- eventDate
		- eventVenue
		- eventDescription
	- Seats
		- seatId
		- seatSection
		- seatRow
		- seatNumber
		- seatPrice
		- seatAvailable
		- eventId
	- Purchases
		- userId
		- eventId
		- seatId

Concurrency: What happens if 2 users book the same seat to the same event at the same time
Use a ACID DB. Check for seat availability at the start of the transaction. Before committing the transaction
check if the value is the same. If the seat is no longer available, roll-back and request to choose another seat.

We could also block the seat on a first come first server basis for a small time period (say 5 mins) in order to
server the user who blocked the seat first.

Scaling:
Load balancer (Backup Load Balancer) ---> a Number of web servers ---> Cache (DB optimization) ---> DB (backup DB)

Design order tracking system like doordash:
===========================================
Design an order tracking system using the below constraints. 

Once an order is received, it will be assigned to a delivery boy and sends notification at every stage of the order
such as order received with expected time of delivery, delivery boy assigned, order picked up, order delivered. 

The main idea is to implement the Observable architecture.
We need to create OrderManager Class that observe Order Class for status changes.

```
package com.cracking.amazon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AmazonOrders {
	
	
	public static interface Observer<T> {
		public void onChange(T subject);
	}
	
	public static interface Subject<T>{
		public boolean registerObserver(Observer<T> observer);
		public void notifyAllObservers();
		public boolean deleteObserver(Observer<T> observer);
	}
	
	public static enum OrderStatus {
		Received("Received"),
		Assigned("Assigned"),
		Delivered("Delivered"),
		PickedUp("PickedUp");
		
		public String description;
		
		OrderStatus(String description) {
			this.description = description;
		}
	}
	
	public static class DeliveryBoy {
		private int id;
		private String name;
		
		public DeliveryBoy(int id, String name){
			this.id = id;
			this.name = name;
		}
	}
	
	public static class Order implements Subject<Order> {
		
		private ArrayList<Observer<Order>> observers;
		private int id;
		private Date expectedTime;
		private OrderStatus status;
		private DeliveryBoy deliveryBoy;
		
		public Order(int id, Date expectedTime){
			this.observers = new ArrayList<Observer<Order>>();
			this.id = id;
			this.expectedTime = expectedTime;
		}
		
		public int getId(){
			return this.id;
		}
		
		public void assignDeliveryBoy(DeliveryBoy deliveryBoy) {
			this.deliveryBoy = deliveryBoy;
			this.setStatus(OrderStatus.Assigned);
		}
		
		public void setStatus(OrderStatus status) {
			this.status = status;
			this.notifyAllObservers();
		}

		@Override
		public boolean registerObserver(Observer<Order> observer) {
			if(!this.observers.contains(observer)) {
				this.observers.add(observer);
				return true;
			}
			return false;
		}

		@Override
		public boolean deleteObserver(Observer<Order> observer) {
			return this.observers.remove(observer);
		}

		@Override
		public void notifyAllObservers() {
			for(Observer<Order> observer:this.observers) {
				observer.onChange(this);
			}
		}
	}
	
	public static class OrderManager implements Observer<Order> {
		
		private ArrayList<Order> orders;
		
		public OrderManager() {
			this.orders = new ArrayList<Order>();
		}
		
		public boolean addOrder(Order order) {
			if(!this.orders.contains(order)){
				this.orders.add(order);
				order.registerObserver(this);
				order.setStatus(OrderStatus.Received);
				return true;
			}
			
			return false;
		}

		@Override
		public void onChange(Order subject) {
			// TODO Auto-generated method stub
			String msg = String.format("Order #%d status changed - %s", 
					subject.getId(),
					subject.status.description);
			System.out.println(msg);
		}
		
	}
	
	public static void main(String[] args) {
		OrderManager orderManager = new OrderManager();
		DeliveryBoy mainDeliveryBoy = new DeliveryBoy(100, "Main Delivery Boy");
		
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 2);
		Date date1 = cal.getTime();

		Order order1 = new Order(1, date1);
		
		//Order Received
		orderManager.addOrder(order1);
		
		//Order Assigned
		order1.assignDeliveryBoy(mainDeliveryBoy);
		
		//Order Delivered
		order1.setStatus(OrderStatus.Delivered);
		
		//Picked Up
		order1.setStatus(OrderStatus.PickedUp);
		
	}
}
```

Design a key-value data store:
==============================

Storing key-value pairs on a single machine is usually done in memory using something like a hash-map. 
Hash-map has O(1) time for insert and retrieval. But when large amounts of data needs to be stored as
key-value pair, they need to stored on disk. Some approaches:
1. Compress data
2. Use a cache system; frequently accessed data is in cache, the rest in disk

Distributed key-value storage: The idea is to split the data into multiple machines and have a coordinator machine
route the request appropriately. We need to come up with a good partition scheme (sharding) to distribute the data equally among
the multiple hosts. We could take a hash of the resource (something like a URL) and then distribute the data into multiple machines.

System availability: This can be handled by having replicas for every machines.

Sharding is basically used to splitting data to multiple machines since a single machine cannot store too much data. 
Replica is a way to protect the system from downtime. 

Consistency: 
By introducing replicas, we can make the system more robust. However, one issue is about consistency. Let’s say for machine A1, 
we have replica A2. How do you make sure that A1 and A2 have the same data? For instance, when inserting a new entry, 
we need to update both machines. But it’s possible that the write operation fails in one of them. So over time, 
A1 and A2 might have quite a lot inconsistent data, which is a big problem.
There are a few approaches to achieve consistency.
1. The coordinator machine maintains a copy of the updated version and retries if update to one of the replica fails
2. Use a commit log, the updates are written to a commit log and a background job processes these commit operations. If any update
fails, they can be redone as the details are in a commit log.
3. The coordinator machine can read the data from all the available replicas and resolve conflicts on the fly during a read

Read throughput can be optimized by introducing a cache system.

Design a cache system:
======================

Cache is used to provide quick access to frequently accessed data by storing them in memory.

One of the most commonly used cache is LRU (Least Recently Used) cache. It works as below:

1. If the requested key is in the cache, it returns the result and moves the key to the head of the cache queue
2. If the key isn't present, it fetches it from disk and writes it to the head of the cache queue
3. While writing to cache, if the cache is full, the least recently used data is evicted; in this case, the tail of the
cache queue

LRU design:
	- It involves having a hash-map to store the key and Node mapping.
	- Node is maintained in a doubly linked list with head and tail pointers.

Eviction policies:
	1. LRU - least recently used
	2. Random replacement - random element is replaced
	3. LFU - least frequently used is evicted. A count is maintained, and the entry with least count is evicted
	4. W-TinyLFU - It is similar to LFU but it uses a time window since sometimes an item is only used frequently in the past, 
	but LFU will still keep this item for a long while.

Concurrency: This is handled by using a lock but that slows the performance. One way to elevate performance 
is to use a distributed cache. Now the data is spit across multiple machines and there is less contention for locks.
An alternative is to use commit logs. To update the cache, we can store all the mutations into logs rather than update 
immediately. And then some background processes will execute all the logs asynchronously. 
This strategy is commonly adopted in database design.

In a distributed cache, a coordinator machines routes the request to a machine with the resource. The machine with the
resource has a local cache. If the data is present in the cache, it returns immediately, else it fetches from disk, stores
in cache and returns the result back.

Design Uber/Lyft:
=================
User cases:
	- Major
		- Customer to driver matching
			- Matching riders to nearest drivers
		- Routing/ETA
			- Providing an estimated time of arrival to rider
	- Minor
		- Payment processing
		- Feedback processing

Storage:
	- Every trip needs to be recorded and stored. Have a primary and backup DB.
	- The data is also archived into Date Warehouse systems for analytics/trends
Cache:
	- Cache will be used for retrieving constant (non-real time) data like maps and events. For real time data
	persistent cache like Redis can be used
Logging:
	- Every rider and driver location data needs to be logged for legal reasons - in case of attack etc.
	- Message queue like Kafka can be used to log both rider and driver locations periodically (like every 4 secs)
Web-server:
	- Micro-services based approach - separate independent services for every feature/functionality
	- Need to ensure provisioning (pushing static code to web servers. Terraform is a tool used for provisioning)
	 is done correctly so rider requests can be routed to any state-less webs server
	- Services are deployed in docker containers. These can be managed independently as if they were hosted on
	separate hosts. Mesos is used to manage bringing up/down these cluster of services.
	
Testing:
	- Shadow fleet environment can be used to test cases where any of the number of services can go down. This is done
	by using tools like ChaosMonkey/Hailstorm which is used to shutdown services abruptly.

Algorithm:
	- Cities and roads connecting them are represented as graph nodes and edges respectively
	- Dijkstra's algorithm to determine the shortest distance between any 2 points in a graph
	- Distance between nodes can be pre-calculated based on speed zones. Since these are static, these can be
	pre-calculated and for taking into consideration things like traffic, historical data for similar trips can be used
	- For places where uber is launched recently, no historical data is present. In those cases, cities are partitioned into
	small cells and something like Dijkstra's algorithm is used in real time to predict ETA. Traffic in adjacent cells can also
	affect the ETA. This also needs to be taken into account.

Uber specific architecture - Matt Ranney talk:
	
				demand             supply
						
						
			maps/ETA	services    post trip processing
			
							DB       money/payment systems
							
 To make it more available, scalable a new dispatch system is developed.
 
 Dispatch uses a ringpop architecture with gossip
 A new RPC protocol TChannel
 Every service also has some routing information in case Load Balancer between services is down
 Stateful systems - in case Data center is down, state is also stored on driver partner phones as a backup
 Geo-spatial index uses Google S2 library
 	A cell is a level 12 (3km - 6km radius)
 	Sharded by cell
 	

Design a URL shortening service like bit.ly:
============================================
1. Use cases:
	- Major
		- Shorten - Given a url, shorten and return the shortened url
		- Redirect - Given a shortened url, redirect to the original url
	- Minor
		- Custom url
		- Analytics
		- Automatic link expiration
		- Manual link removal
		- UI vs API
	
2. Constraints:
	- Traffic - Requests per second
	- Data - Amount of storage needed

	Lets consider twitter. There are about 500M new tweets every day. That is 15B per month.
	Lets take 20% of those tweets are urls == 300M per month
	Of the 300M, 80% is handled by top 3 sites, the remaining 20% by the other sites
	Lets say, our service is not in the top 3. Of the remaining services, lets say 1/3 is handled by ours.
	So, 100M per month new urls.
	
	Lets say 90% of the traffic is redirects and 10% shortens, 900M requests per month for redirects and 100M for shortens.
	Now if we bring it down to Requests per second, 400+ RPS, 40: shortens, 360:redirects
	
	Similarly we would need say 500 bytes for original url, 6 bytes for shortened url.
	That is, 100M*12*5*506 = 6B*506 bytes approx = 3TB for 5 years
	
3. Abstract design:
	1. Aplication service layer
		- Shortening service
		- Redirection service
	2. Data service layer
		- a huge key:value map
	
	hash_url = convertBase62(md5(originalUrl+salt))[:6]
	
	```
	/**
	 * TinyUrl encode and decode implementation.
	 */
	Map<String, String> map = new HashMap<>();
	String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Random rand = new Random();
	
	private String getRand() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<6; i++) {
			sb.append(alphabet.charAt(rand.nextInt(alphabet.length())));
		}
		return sb.toString();
	}
	
	public String encodeUrl(String longUrl) {
		String key = getRand();
		while (map.containsKey(key)) {
			key = getRand(); 
		}
		String value = "http://tinyurl.com/" + key;
		map.put(key, longUrl);
		return value;
	}
	
	public String decodeUrl(String tinyUrl) {
		return map.get(tinyUrl.replace("http://tinyurl.com/", ""));
	}
	```
	
	A good hash function:
	```
	int hash = 7;
	for (int i = 0; i < strlen; i++) {
	    hash = hash*31 + charAt(i);
	}
	```
	
4. Bottleneck:
	-400 RPS is fine
	-scanning 3TB of data for redirect urls - more interesting
	
5. Scalable design
	1. Application service layer
		- Start with a single server
		- Add a load balancer with a cluster of machines to handle spike-y traffic and to deal with availability
	2. Data storage
		- Start with a single MySQL server
		- Add a mappings table with 2 fields, hash and originalUrl
		- Add an index of hashes for quicker retrievals
		- As the load increases, vertically scale the server
		- Eventually, partition the data into multiple small DBs and distribute the load using the first char of hash
		and mod with the number of machines. Think about master-slave architecture (for more reads from replica slaves)

Twitter design (HiredInTech):
=============================
1. Use cases:
	- Users should be able to post tweets
	- Users should be able to follow other users
	- Users can favorite other tweets
2. Constraints:
	- Low response time
	- Highly available
	- There could be spikey traffic
3. Abstract design:
	- Application server layer
		- Multiple application servers behind a load balancer to provide high availability
		- Auto scaling to account for spikey traffic
	- Data Storage layer
		- Relational DB like MySQL
		- Scale vertically
		- Then partition into multiple small DBs
4. Low level design:
	```
	Schema design:
	- User table
		user_id
		username
		firstName
		lastName
		password
		created_at
		updated_at
		profileDetail
	- Tweet table
		tweet_id
		author_id
		content
		created_at
	- Connections table
		follower_id
		followee_id
		created_at
	- Favorites table
		user_id
		tweet_id
		created_at
	```	
	RESTful APIs:
	```
	Get profile details of a user - GET /api/users/<username>
	To get the tweets of a given user ordered by date - GET /api/users/<username>/tweets
		pagination - GET /api/users/<username>/tweets?page=4
	Users following a given user - GET /api/users/<username>/followers
	Users followed for a given user - GET /api/users/<username>/followees
	Posting a tweet - POST /api/users/<username>/tweets
	Following a given user - POST /api/users/<username>/followers
	see a list of all users that favorited a tweet - GET /api/users/<username>/tweets/<tweetid>/favorites
	Favoriting a tweet - POST /api/users/<username>/tweets/<tweetid>/favorites
	```
	
	Detailed diagram:
	![alt text](https://train.hiredintech.com/lecture_materials/twitter_problem_system_design.png)
	
Netflix design:
===============

Let’s look at some impressive Netflix statistics for 2017.

- Netflix has more than 110 million subscribers.
- Netflix operates in more than 200 countries. 
- Netflix has nearly $3 billion in revenue per quarter.
- Netflix adds more than 5 million new subscribers per quarter.
- Netflix plays more than 1 billion hours of video each week. As a comparison, YouTube streams 1 billion hours of video every day 
while Facebook streams 110 million hours of video every day.
- Netflix played 250 million hours of video on a single day in 2017.
- Netflix accounts for over 37% of peak internet traffic in the United States.
- Netflix plans to spend $7 billion on new content in 2018. 

Finally: Here’s What Happens When You Press Play
It’s been a long road getting here. We’ve learned a lot. Here’s what we’ve learned so far:

- Netflix can be divided into three parts: the backend, the client, and the CDN. 
- All requests from Netflix clients are handled in AWS.
- All video is streamed from a nearby Open Connect Appliance (OCA) in the Open Connect CDN
- Netflix operates out of three AWS regions and can usually handle a failure in any region without members even noticing
- New video content is transformed by Netflix into many different formats so the best format can be selected for viewing 
based on the device type, network quality, geographic location, and the member’s subscription plan
- Every day, over Open Connect, Netflix distributes video throughout the world, based on what they predict members in each
location will want to watch.

Here’s a picture of how Netflix describes the play process:

![alt text](https://c1.staticflickr.com/5/4598/25067243468_6e16c1052a.jpg?__SQUARESPACE_CACHEVERSION=1512840290262)

Now, let’s complete the picture:
- You select a video to watch using a client running on some device. The client sends a play request, indicating which
video you want to play, to Netflix’s Playback Apps service running in AWS.
- We’ve not talked about this before, but a big part of what happens after you hit play has to do with licensing. Not every 
location in the world has a license to view every video. Netflix must determine if you have a valid license to view a particular 
video. We won’t talk about how that works—it’s really boring—but keep in mind it’s always happening. One reason Netflix started 
developing its own content is to avoid licensing issues. Netflix wants to release a show to everyone in the world all at the 
same time. Creating its own content is the easiest way for Netflix to avoid worrying about licensing problems.
- Taking into account all the relevant information, the Playback Apps service returns URLs for up to ten different OCA servers. 
These are the same sort of URLs you use all the time in your web browser. Netflix uses your IP address and information from ISPs 
to identify which OCA clusters are best for you to use.
- The client intelligently selects which OCA to use. It does this by testing the quality of the network connection to each OCA. 
- It will connect to the fastest, most reliable OCA first. The client keeps running these tests throughout the video streaming process.
The client probes to figure out the best way to receive content from the OCA.
- The client connects to the OCA and starts streaming video to your device. 
Have you noticed when watching a video the picture quality varies? Sometimes it will look pixelated, and after awhile the 
picture snaps back to HD quality? That’s because the client is adapting to the quality of the network. If the network quality 
declines, the client lowers video quality to match. The client will switch to another OCA when the quality declines too much.
That’s what happens when you press play on Netflix.

Original source link with complete details - http://highscalability.com/blog/2017/12/11/netflix-what-happens-when-you-press-play.html

Logging system design:
======================

Design a logging system. The system contains multiple application servers which are logging the information to file system. In this scenario, we want to check and alarm in case an exception is thrown in any of the servers. We want a system that checks for appearance of specific words, "Error", "Exception", "Disk Full" etc. in the logs of any of the servers. How would you design this system? 
What if we want to scale the system in future?

I believe, we can implement observer pattern between all application servers serving as event creator and a listener working as a observer which is subscribed to this app servers. The moment desired condition is encountered in any app server, it calls its update function to pass on this information to the listener (could be a messaging queue), and then listener pass on the this message to a parser module which will parse the message and accordingly will send the message to the concrete implementation of logging interface (Error, Warning, DiskFull... etc) 

This way the system remains open to be scaled in future.
1. New app servers can be added in future. All it has to do is to implement the subject Interface and implement the functionality to register the observer.
2. Observer (listener will have to subcribe to the new subject)
3. In case new pattern comes (Out of Memory, Application Error...) in future, they will have their own concrete implementation of logging interface.
In total allowing all major actors to be independent to each other.

The main aspects you will need to address are: collection, transport, storage, and analysis. In some special cases, you may also want to have an alerting capability as well.

Collection: 
Applications create logs in different ways, some log through syslog, others log directly to files. If you consider a typical web application running on a linux hosts, there will be a dozen or more log files in /var/log as well as a few application specific logs in home directories or other locations.

If you are supporting a web based application and your developers or operations staff need access to log data quickly in order to troubleshoot live issues, you need a solution that is able to monitor changes to log files in near real-time. If you are using a file replication based approach where files are replicated to a central server on a fixed schedule, then you can only inspect logs as frequently as the replication runs. A one minute rsync cron job might not be fast enough when your site is down and you are waiting for the relevant log data to be replicated.

On the other hand, if you need to analyze log data offline for calculating metrics or other batch related work, a file replication strategy might be a good fit.

Transport: 
Log data can accumulate quickly on multiple hosts. Transporting it reliably and quickly to your centralized location may need additional tooling in order to effectively transmit it and ensure data is not lost.

Frameworks such as Scribe, Flume, Heka, Logstash, Chukwa, fluentd, nsq and Kafka are designed for transporting large volumes of data from one host to another reliably. Although each of these frameworks addresses the transport problem, they do so quite differently.

For example, Scribe, nsq and Kafka, require clients to log data via their API. Typically, application code is written to log directly to these sources which allows them to reduce latency and improve reliability. If you want to centralize typical log file data, you would need something to tail and stream the logs via their respective APIs. If you control the app that is logging the data you want to collect, these can be much more efficient.

Logstash, Heka, fluentd and Flume provide a number of input sources but also support natively tailing files and transporting them reliably. These are a better fit for more general log collection.

While rsyslog and Syslog-ng are typically thought of as the defacto log collector, not all applications use syslog.

Storage: 
Now that your log data is being transfered, it needs a destination. Your centralized storage system needs to be able to handle the growth in data over time. Each day will add a certain amount of storage that is relative to the number of hosts and processes that are generating log data.

How you store things depends on a few things:

How long should it be stored - If the logs are for long-term, archival purposes and do not require immediate analysis, S3, AWS Glacier, or tape backup might be a suitable option since they provide relatively low cost for large volumes of data. If you only need a few days or months worth of logs, storing them on some form distributed storage systems such as HDFS, Cassandara, MongoDB or ElasticSearch also works well. If you only need a few hours worth of retention for real-time analysis, Redis might work as well.

Your environments data volume. - A days worth of logs for Google is much different than a days worth of logs for ACME Fishing Supplies. The storage system you chose should allow you to scale-out horizontally if your data volume will be large.

How will you need to access the logs - Some storage is not suitable for real-time or even batch analysis. AWS Glacier or tape backup can take hours to load a file. These don’t work if you need log access for production troubleshooting. If you plan to do more interactive data analysis, storing log data in ElasticSearch or HDFS may allow you work with the raw data more effectively. Some log data is so large that it can only be analyzed in more batch oriented frameworks. The defacto standard is this case is Apache Hadoop along with HDFS.

Analysis: 
Once your logs are stored on a centralized storage platform, you need a way to analyze them. The most common approach is a batch oriented process that runs periodically. If you are storing log data in HDFS, Hive or Pig might help analyzing the data easier than writing native MapReduce jobs.

If you need a UI for analysis, you can store parsed log data in ElasticSearch and use a front-end such as Kibana or Graylog2 to query and inspect the data. The log parsing can be handled by Logstash, Heka or applications logging with JSON directly. This approach allows more real-time, interactive access to the data but is not really suited for a mass batch processing.

Alerting: 
The last component that is sometimes nice to have is the ability to alert on log patterns or calculated metrics based on log data. Two common uses for this are error reporting and monitoring.

Most log data is not interesting but errors almost always indicate a problem. It’s much more effective to have the logging system email or notify respective parties when errors ocurr instead of having someone repeatedly watch for the events. There are several services that solely provide application error logging such as Sentry or HoneyBadger. These can also aggregate repetitve exceptions which can give you and idea of how frequently an error is occuring.

Another use case is monitoring. For example, you may have hundreds of web servers and want to know if they start returning 500 status codes. If you can parse your web log files and record a metric on the status code, you can then trigger alerts when that metric crosses a certain threshold. Riemann is designed for detecting scenarios just like this.

Reference - http://jasonwilder.com/blog/2013/07/16/centralized-logging-architecture/

Kafka architecture - Log systems:
=================================

Kafka consists of 3 major parts:
1. Kafka producers - produce messages to be written to the clusters
2. Kafka clusters - consist of a set of brokers. Brokers host what are known as topics which is basically messages of a certain type. Topics can be partitioned to enable distributed system and scaling.
3. Kafka consumers - apps that consume from the topics. There exists consumer groups where consumers in a group has access to only one partition of a topic. Having a single consumer in a group (as a unit of parallelism) helps to NOT to have any coordination between them.

Kafka Brokers:
Kafka brokers maintain log segment files where messages are appended to the end of the log file. Log segment files are of fixed size and flushed to persistent store at fixed time intervals or when the files reach a predetermined size. Messages are available for consumers to consume when the messages are persisted. Consumers are blocked if there are no messages to consume. Brokers maintain offset of messages as index. Brokers maintain the start offset of every log segment file in an index.

Brokers are also state-less in the sense the consumers pull the data and maintain the offset of where to read-back from. Messages in the broker are persisted for an SLA amount of time (similar to TTL). Consumers once they consume a message, provide the next offset to read from the broker based on the previous message offset and length of message read previously.

Kafka uses Zookeeper for the following tasks: (1) detecting the addition and the removal of brokers and consumers, (2) triggering a rebalance process in each consumer when the above events happen, and (3) maintaining the consumption relationship and keeping track of the consumed offset of each partition. Specifically, when each broker or consumer starts up, it stores its information in a broker or consumer registry in Zookeeper. The broker registry contains the broker’s host name and port, and the set of topics and partitions stored on it. The consumer registry includes the consumer group to which a consumer belongs and the set of topics that it subscribes to. Each consumer group is associated with an ownership registry and an offset registry in Zookeeper. The ownership registry has one path for every subscribed partition and the path value is the id of the consumer currently consuming from this partition (we use the terminology that the consumer owns this partition). The offset registry stores for each subscribed partition, the offset of the last consumed message in the partition. 

In this section, we describe how we use Kafka at LinkedIn. Figure3 shows a simplified version of our deployment. We have one Kafka cluster co-located with each datacenter where our userfacing services run. The frontend services generate various kinds of log data and publish it to the local Kafka brokers in batches. We rely on a hardware load-balancer to distribute the publish requests to the set of Kafka brokers evenly. The online consumers of Kafka run in services within the same datacenter.  We also deploy a cluster of Kafka in a separate datacenter for offline analysis, located geographically close to our Hadoop cluster and other data warehouse infrastructure. This instance of Kafka runs a set of embedded consumers to pull data from the Kafka instances in the live datacenters. We then run data load jobs to pull data from this replica cluster of Kafka into Hadoop and our data warehouse, where we run various reporting jobs and analytical process on the data. We also use this Kafka cluster for prototyping and have the ability to run simple scripts against the raw event streams for ad hoc querying. Without too much tuning, the end-to-end latency for the complete pipeline is about 10 seconds on average, good enough for our requirements

Refer to this paper for further details - http://notes.stephenholiday.com/Kafka.pdf