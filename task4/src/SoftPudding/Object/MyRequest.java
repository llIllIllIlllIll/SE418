package SoftPudding.Object;

import java.util.Date;

public class MyRequest implements Runnable{
    //thread
    private Thread thread;
    private static int ID = 0;
    //just a identifier which denotes the order of MyRequest object being initialized
    private int id;
    //this time unit is ms
    private long start_time;
    private long end_time;
    public MyRequest() {
        this.id = ++ID;
        start_time = new Date().getTime();
        end_time = 0;
    }
    public int getId(){
        return id;
    }
    //when container calls this method it means that this request is handles
    public void requestHandled(){
        //...
        //something that this request has to deal with
        end_time = new Date().getTime();
    }
    public void requestAbandoned(){
        System.out.println("Thread "+id+" has been abandoned, waited for "+getCurrentWaitTime()+" s.");
    }
    //after request is handled call this method to show the time this request has been waited
    public float getProcessTime() throws Exception{
        if(end_time==0f){
            System.err.println("This Request Is Never Handled.");
            throw new Exception("This Request Is Never Handled.");
        }
        return (end_time-start_time)/1000f;
    }
    public float getCurrentWaitTime(){
        return (new Date().getTime()-start_time)/1000.0f;
    }
    public void run() {
        this.requestHandled();
        try{
            System.out.println("Thread "+getId()+"has been handled, waited for "+getProcessTime()+" seconds");
        }
        catch(Exception e){
            System.err.println("Thread "+getId()+" has not been handled.");
        }
    }
    public Thread start(){
        if(thread == null){
            thread = new Thread(this,"Thread "+getId());
            thread.start();
            return thread;
        }
        return thread;
    }
}
