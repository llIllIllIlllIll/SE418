package SoftPudding.Evaluation;

import SoftPudding.Container.MyContainer;
import SoftPudding.Object.MyRequest;

import java.util.Date;

public class MyEvaluation_Multiproducers {
    static MyContainer container1= new MyContainer();
    static MyContainer container2= new MyContainer();
    public static void main(String [] args){
        for(int i=0;i<20;i++){
            container1.add(new MyRequest());
            container2.add(new MyRequest());
        }
        long time1= new Date().getTime();
        while(new Date().getTime()-time1<(container1.TIMEOUT+1)*1000){
            continue;
        }
        for(int i=0;i<20;i++){
            container1.add(new MyRequest());
            container2.add(new MyRequest());
        }
        container1.activate();
        container2.activate();
    }
}
