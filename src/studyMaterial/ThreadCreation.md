How to Create a Java Thread?
Java lets you create thread in following two ways:

By implementing the Runnableinterface.
By extending the Thread
Let's look at how both ways help in implementing the Java thread.

Runnable Interface
The easiest way to create a thread is to create a class that implements the Runnable interface.

To implement Runnable interface, a class need only implement a single method called run( ), which is declared like this:

public void run( )
Inside run( ), we will define the code that constitutes the new thread. Example:

public class MyClass implements Runnable {
public void run(){
System.out.println("MyClass running");
   } 
}
To execute the run() method by a thread, pass an instance of MyClass to a Thread in its constructor (A constructor in Java is a block of code similar to a method that's called when an instance of an object is created). Here is how that is done:

Thread t1 = new Thread(new MyClass ());
t1.start();
When the thread is started it will call the run() method of the MyClass instance instead of executing its own run() method. The above example would print out the text "MyClass running ".

Extending Java Thread
The second way to create a thread is to create a new class that extends Thread, then override the run() method and then to create an instance of that class. The run() method is what is executed by the thread after you call start(). Here is an example of creating a Java Thread subclass:

public class MyClass extends Thread {
     public void run(){
     System.out.println("MyClass running");
   }
}
To create and start the above thread you can do so like this:

MyClass t1 = new MyClass ();
T1.start();
When the run() method executes it will print out the text " MyClass running ".

So far, we have been using only two threads: the main thread and one child thread. However, our program can affect as many threads as it needs. Let's see how we can create multiple threads.