package lmc.util;

import java.util.Random;

public class RandomNumberUtil {
    public static String getRandomNumberStr(){
       return String.valueOf(getRandomNumber());

    }
    private static   int getRandomNumber(){
        Random random=new Random();
        int i= random.nextInt(100000);
        if(i<10000 || i>99999){
            return getRandomNumber();
        }else{
            return i;
        }
    }
}
