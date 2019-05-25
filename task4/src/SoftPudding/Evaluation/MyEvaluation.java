package SoftPudding.Evaluation;

import SoftPudding.Container.MyContainer;
import SoftPudding.Object.MyRequest;

import java.util.Date;

public class MyEvaluation {
    static MyContainer myContainer= new MyContainer();
    static public void main(String [] args){
        for(int i=0;i<20;i++){
            myContainer.add(new MyRequest());
        }
        long time1= new Date().getTime();
        while(new Date().getTime()-time1<(myContainer.TIMEOUT+1)*1000){
            continue;
        }
        for(int i=0;i<20;i++){
            myContainer.add(new MyRequest());
        }
        myContainer.activate();

    }
}
