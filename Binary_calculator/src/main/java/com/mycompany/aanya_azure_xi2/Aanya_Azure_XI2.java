
package com.mycompany.aanya_azure_xi2;
import java.util.Scanner;
public class Aanya_Azure_XI2 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the first number");
        String n1=sc.nextLine();
        System.out.print("Enter the second number");
        String n2=sc.nextLine();
        System.out.println("Enter 1 to perform addition");
        System.out.println("Enter 2 to perform subtraction");
        System.out.println("Enter 3 to perform multiplication");
        System.out.println("Enter 4 to perform division");
        int c=sc.nextInt();
        Aanya_Azure_XI2 o1=new Aanya_Azure_XI2();
        switch(c){
            case 1: String res=o1.add(n1,n2);
                System.out.println(""+res);
            break;
            case 2:
                String r= o1.subtract(n1, n2);
                System.out.println(""+r);
                break;
            case 3:
                String ans=o1.multiply(n1, n2);
                System.out.println(""+ans);
                break;
            case 4:
                o1.divide(n1, n2);
                break;
          
        }
        
    }
    String add(String a, String b){
        int len1=a.length();
        int len2=b.length();
        //making both strings of same length by adding extra zeroes 
        int max=0;
        if (len1>=len2)
            max=len1;
        else
            max=len2;
         while (len1<max){
            a="0"+a;
            len1++;
        }
        while (len2<max){
            b="0"+b;
            len2++;
        }
        String res="";
        int carry=0;
        for(int i=max-1; i>=0; i--){
            int bit_a=a.charAt(i)-'0';
            int bit_b=b.charAt(i)-'0';
            int sum=bit_a+bit_b+carry;
            res=(sum%2)+res;
            carry=sum/2;
         }
        if (carry==1){
            res="1"+res;
        }
        
        return res;
    }
    String twos_comp(String a){
        String ans="";
        for (int i=0; i<a.length(); i++){
            if (a.charAt(i)=='1')
                ans=ans+"0";
            else
            ans=ans+"1";
        }
        
        String x=add(ans,"1");
        
        return x;
    }
    /*Steps to Subtract A - B using 2's Complement
Make sure A and B are of the same bit length.

Find the 1's complement of B.

Add 1 to it to get 2's complement of B.

Add this 2's complement to A (binary addition).

If there’s a carry, discard it → result is positive.

If there’s no carry, the result is negative (in 2’s complement).*/
    String subtract(String a,String b){
        int len1=a.length();
        int len2=b.length();
        //making both strings of same length by adding extra zeroes 
        int max=0;
        if (len1>=len2)
            max=len1;
        else
            max=len2;
        while (len1<max){
            a="0"+a;
            len1++;
        }
        while (len2<max){
            b="0"+b;
            len2++;
        }
        String c=twos_comp(b);
        String res=add(a,c);
        String new_s="";
        //if the result is positive then we exclude the first character
        if (res.length()>max){
            for (int i=1; i<=max; i++){
                new_s=new_s+res.charAt(i);
            }
            return new_s;
        }
     //if the result is negative then we take the 2'c complement of the answer
        else{
           res=twos_comp(res);
            return res;
        }
     }
    String multiply(String a, String b) {
        String result = "0";
        int bLen = b.length();

        // Traverse from right to left of b
        for (int i = bLen - 1; i >= 0; i--) {
            if (b.charAt(i) == '1') {
                // Shift a by (bLen - 1 - i)
                String temp = a;
                int shiftCount = bLen - 1 - i;
                for (int s = 0; s < shiftCount; s++) {
                    temp += "0"; // append zeros for left shift
                }
result = add(result, temp);
            }
        }
        return result;
    }
    String removeLeadingZeros(String d)
    {
        int i = 0;
        while (i < d.length() && d.charAt(i) == '0') {
            i++;
        }
        if (i == d.length()) return "0";
        else return d.substring(i);
    }
    // Compare two binary strings: returns 1 if a > b, 0 if equal, -1 if a < b
    int compare(String a, String b) {
        a = removeLeadingZeros(a);
        b = removeLeadingZeros(b);
        if (a.length() > b.length()) return 1;
        if (a.length() < b.length()) return -1;
        return a.compareTo(b);
    }
    void divide(String dividend, String divisor) {
        dividend = removeLeadingZeros(dividend);
        divisor = removeLeadingZeros(divisor);

        System.out.println("Dividend: " + dividend);
        System.out.println("Divisor: " + divisor);

        if (compare(dividend, divisor) < 0) {
            System.out.println("Quotient: 0");
            System.out.println("Remainder: " + dividend);
            return;
        }

        char[] quotient = new char[dividend.length()];
        String temp = "";

        for (int i = 0; i < dividend.length(); i++) {
            temp = temp + dividend.charAt(i);
            temp = removeLeadingZeros(temp);

            if (compare(temp, divisor) >= 0) {
                temp = sub(temp, divisor);
                quotient[i] = '1';
            } else {
                quotient[i] = '0';
            }
        }

        String q = "";
        for (int i = 0; i < quotient.length; i++) {
            q = q + quotient[i];
        }

        System.out.println("Quotient: " + removeLeadingZeros(q));
        System.out.println("Remainder: " + removeLeadingZeros(temp));
    }
    String sub(String a, String b) {
        a = removeLeadingZeros(a);
        b = removeLeadingZeros(b);

        int lenA = a.length();
        int lenB = b.length();
        int maxLen = Math.max(lenA, lenB);

        int[] result = new int[maxLen];
        // Pad b with zeros
        while (b.length() < maxLen) {
            b = "0" + b;
        }
        int borrow = 0;
        for (int i=maxLen-1; i>=0;i--)
        {
            int bitA = a.charAt(i) - '0';
            int bitB = b.charAt(i) - '0' + borrow;
            if (bitA < bitB)
            {
                bitA = bitA + 2;
                borrow = 1;
            }
            else borrow = 0;
            result[i] = bitA - bitB;
        }
        String res="";
        for (int r : result) {
            res += (char) (r + '0');
        }
        return removeLeadingZeros(res);
       
    }
}
