package SoftPudding;

import java.util.*;
import java.io.*;

public class WordLadder {
    String filename="dictionary.txt";
    Set<String> Words=new HashSet<String>();
    LinkedList<LinkedList<String>> partialLadders=new LinkedList<LinkedList<String>>();
    public WordLadder(){
        FileInputStream ifstream;
        try{
            ifstream= new FileInputStream("dictionary.txt");
            int ch;
            char c;
            char[] word=new char[100];
            int index=0;
            int ct=0;
            while((ch=ifstream.read())!=-1){
                c=(char)ch;
                if(Character.isLetter(c)){
                    word[index++]=c;
                }
                else{
                    Words.add(new String(word,0,index));
                    index=0;
                }
            }
            if(index!=0)
            {
                Words.add(new String(word,0,index));
                index=0;
            }
        }catch(Exception e){
            System.err.println("Can't open file "+filename);
            System.exit(1);
        }
    }
    public List<String> search(String word2, String word1){
        int count =0;
        partialLadders= new LinkedList<LinkedList<String>>();
        if(!isInWords(word1)||!isInWords(word2)){
            return Arrays.asList(word1+" or "+word2+" not in "+filename);
        }

        LinkedList<String> w0=new LinkedList<String>();
        w0.add(word2);
        partialLadders.add(w0);
        while(!partialLadders.isEmpty()){
            count++;
            if(count>=word1.length()*500)
                return Arrays.asList("Can not find a proper path.");
            LinkedList<String> tempLadder=partialLadders.removeFirst();
            String last=tempLadder.getLast();
            char[] lastNei=last.toCharArray();
            for(int i=0;i<last.length();i++){
                if(i>0)
                    lastNei[i-1]=last.charAt(i-1);
                for(char a='a';a<='z';a++){
                    if(a==last.charAt(i)){
                        continue;
                    }
                    else{
                        lastNei[i]=a;
                        String newLast;
                        boolean isDifferent=true;
                        if(isInWords((newLast=String.copyValueOf(lastNei)))){
                            for(String s:tempLadder){
                                if(s.equals(newLast)){
                                    isDifferent=false;
                                    break;
                                }
                            }
                            if(isDifferent)
                            {
                                LinkedList<String> newLadder=new LinkedList<String>(tempLadder);
                                newLadder.add(newLast);
                                if(newLast.equals(word1)){
                                    return newLadder;
                                }
                                partialLadders.addLast(newLadder);
                            }
                            else{
                                continue;
                            }
                        }
                    }
                }
            }
        }

        return Arrays.asList("Sorry but nothing found.");
    };
    boolean isInWords(String s){
        return Words.contains(s);
    }
}


