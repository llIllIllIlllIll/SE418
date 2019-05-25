package SoftPudding.Container;
import SoftPudding.Object.MyRequest;
import java.util.*;
public class MyContainer implements Runnable{
    private Thread thread;
    //The gap between each check of the container
    private final double CHECKGAP = 0.5;
    //timeout constriant
    public final double TIMEOUT = 5.0;
    //MAX number of requests that container can handle at once
    private final int MAXHANDLE = 5;
    //queue that contains all request
    private Queue<MyRequest> requests= new LinkedList<MyRequest>();
    //queue that contains current threads
    private Queue<Thread> threads = new LinkedList<Thread>();
    //constructor
    public MyContainer(){}
    //add a request to queue
    public void add(MyRequest request){
        requests.add(request);
    }
    public void run(){
        //ct counts the requests that actually get handled in container
        int ct =0;
        int rounds=0;
        while(!requests.isEmpty()){
            System.out.println("ROUND"+ rounds+++":");
            ct=0;
            Iterator<MyRequest> myRequestIterator= requests.iterator();
            while(myRequestIterator.hasNext()){
                //container has 2 conditions to end this loop:
                //1. MyContainer has already delt with more than MAXHANDLE requests
                //2. requests is empty
                MyRequest mr= myRequestIterator.next();
                if(requests.isEmpty()||ct>=MAXHANDLE){
                    break;
                }
                if(mr.getCurrentWaitTime()>TIMEOUT)
                {
                    mr.requestAbandoned();
                    myRequestIterator.remove();
                }
                else{
                    //handle request
                    threads.add(mr.start());
                    ct++;
                    myRequestIterator.remove();
                }

            }
            try{
                for(Thread tr: threads){
                    tr.join();
                }
                System.out.println("ROUND "+(rounds-1)+": Container handled "+(ct)+" requests.\n\n");
                Thread.sleep((long)(CHECKGAP*1000));
            }
            catch(InterruptedException e){
                System.err.println("Container has been interrupted.");
            }
        }
    }
    public void activate(){
        if(thread == null){
            thread= new Thread(this,"MyContainerThread");
            System.out.println("MyContainer has been activated:\nCHECKGAP: "+CHECKGAP+" s, TIMEOUT: "+TIMEOUT+" s.");
            thread.start();
        }
    }

}
