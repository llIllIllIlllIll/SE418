# My Request Container
#### 关于我的请求类
类MyRequest是用来模拟我对我的容器（一个特殊的队列）进行的请求。MyRequest实现了Runnable的接口，容器可以调用MyRuest的getCurrentWaitTime()方法获取当前请求在队列中的等待时间，调用start()方法初始化该请求的处理线程，或者是调用requestAbandoned()方法表示容器放弃该请求，不做处理。
#### 关于我的容器类
##### 容器内部变量
类MyContainer是一个基于队列实现的MyRequest的一个容器。这个容器同样也是实现了Runnable的接口，相当于每一个容器实例将会有一个单独的线程。容器内部的常量有：
```JAVA
//每次检查请求队列的时间间隔 （s)
private final double CHECKGAP = 0.5;
//TIMEOUT 的时间限制
public final double TIMEOUT = 5.0;
//这个是对线程池的一个模拟 即每次处理我们允许多少个活跃的线程
private final int MAXHANDLE = 5;
```
容器内部保持了两个队列，一个是MyRequest类型的队列，一个是Thread的队列。后者用来跟踪我们由容器分配出去的线程，确保全部回收再进行下一次的容器操作：
```JAVA
//保持所有待处理请求的队列
private Queue<MyRequest> requests= new LinkedList<MyRequest>();
//每次选择处理一个请求，调用MyRequest.Start()方法之后会返回一个Thread
//我们用一个队列保持所有的Thread
private Queue<Thread> threads = new LinkedList<Thread>();
```
##### 容器对于请求的处理
这个容器实际上还是按照先进先出的顺序去处理所有请求的，只是在处理之前，会先调用请求的MyReques.getCurrentWaitTime()方法检查，如果超过了TIMEOUT就放弃该请求。
```JAVA
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
```
每次处理完成并退出上述循环之后，容器会等待CHECKGAP秒，再进行下一轮的处理。
#### 实验
##### 对于单个容器处理多请求的实验
测试内容如下：相隔6s给容器分别发了20个请求，然后在发完所有请求后启动容器。
输出如下：
[result1.md](result1.md)
基本上就是该容器经历了四次处理循环，处理了一共40次请求，其中20次因为是TIMEOUT全部被抛弃。实验过程中一共用了20个线程处理请求。
##### 对于多个容器并发处理多请求的实验
为了进行这个实验，在前面的MyContainer类和MyRequest类里添加了一些输出，来表示是哪个Container对哪个Request进行了回收。实验内容是两个Container分别相隔6s收到了20个请求，然后一起处理这些请求。其中部分输出如下：
```sh
CONTAINER1: Thread 33 has been abandoned, waited for 6.068 s.
CONTAINER1: Thread 35 has been abandoned, waited for 6.068 s.
CONTAINER1: Thread 37 has been abandoned, waited for 6.068 s.
CONTAINER1: Thread 39 has been abandoned, waited for 6.068 s.
CONTAINER1: Thread 49has been handled, waited for 0.075 seconds
CONTAINER1: Thread 43has been handled, waited for 0.076 seconds
CONTAINER1: Thread 41has been handled, waited for 0.076 seconds
CONTAINER2: Thread 48has been handled, waited for 0.077 seconds
CONTAINER2: Thread 50has been handled, waited for 0.076 seconds
```
完整的输出：
[result2.md](result2.md)