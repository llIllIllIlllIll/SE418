import java.util.*;
import java.io.*;
public class WordLadder {
    String filename;
    Set<String> Words=new HashSet<String>();
    LinkedList<LinkedList<String>> partialLadders=new LinkedList<LinkedList<String>>();
    WordLadder(){
        System.out.println("Welcome to Word Ladder.");
        System.out.println("Now Please enter dictionary name:");
        Scanner in=new Scanner(System.in);
        filename=in.next();
        FileInputStream ifstream;
        System.out.println("Please wait a few seconds...");
        try{
            ifstream= new FileInputStream(filename);
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
        System.out.println("Input finished. Initialization down.");
    }
    LinkedList<String> search(String word2,String word1){
        if(!isInWords(word1)||!isInWords(word2)){
            System.out.println(word1+" or "+word2+" not in "+filename);
            System.exit(1);
        }

        LinkedList<String> w0=new LinkedList<String>();
        w0.add(word2);
        partialLadders.add(w0);
        while(!partialLadders.isEmpty()){
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
        System.out.println("Sorry but nothing found.");
        return null;
    };
    boolean isInWords(String s){
        return Words.contains(s);
    }
    public static void main (String [] args){
        WordLadder w=new WordLadder();
        Scanner in=new Scanner(System.in);
        LinkedList<String> res;
        System.out.println("Word #1 (or Enter to quit, enter \"####\" to run unit test):");
        String word1,word2;
        word1=in.nextLine();
        if(word1.isEmpty())
            System.exit(0);
        else if(word1.equals("####")){
            w.test();
            System.exit(0);
        }
        System.out.println("Word #2 (or Enter to quit, enter \"####\" to run unit test)):");
        word2=in.nextLine();
        if(word2.isEmpty())
            System.exit(0);
        else if(word1.equals("####")){
            w.test();
            System.exit(0);
        }
        res=w.search(word2,word1);
        for(String s:res)
            System.out.print(s+" ");
    }
    void test(){
        LinkedList<String> res;
        System.out.println("Test input : code data\n Expected result length:5");
        res=search("code","data");
        System.out.println("Result length = "+res.size()+" ,details: ");
        for(String s: res){
            System.out.print(s+' ');
        }
    }
}

