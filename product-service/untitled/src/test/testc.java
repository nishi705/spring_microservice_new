package test;

import java.util.Arrays;

public class testc {
    public static void main(String[] args) {
//        Input -> aaabbbbcccc  output -> a3b4c4
//        Input -> aaabbbbddddaa
//        Output -> a3b4d4a2

        String str = "aaabbbbddddaa";
        char[] charr = str.toCharArray();
    String res = "";
      int count =0;
      int j=0;
        for(int i=0;i<=str.length()-2;i++){
            j= i+1;
            if(charr[i] == charr[j] && j<=str.length()-1) {
                while (charr[i] == charr[j]) {
                    count++;
                     i++;
                     j++;
                }
            }else{
                i--;
                res = res+charr[i]+count;
                count =0;
                i=j;
            }

        }
        System.out.println(res);




        int[] a = {45,69};
        int a1 = 



    }
}
