/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.thamidev.highestcommonfactor;

/**
 *
 * @author thami
 */
public class HighestCommonFactor {

    public static void main(String[] args) {
        int arr[] = {8, 12};
        System.out.println(highestCommonFactor(arr));
    }
  
    // Function to find highest common factor of array of numbers
    static int highestCommonFactor(int arr[])
    {
        int result = arr[0];
        for (int element: arr){
            result = commonDevisor(result, element);
  
            if(result == 1)
            {
               return 1;
            }
        }
  
        return result;
    }
    
    //This function will run recursively until we have common factor returned
    static int commonDevisor(int a, int b)
    {
        if (a == 0)
            return b;
        return commonDevisor(b % a, a);
    }
}
