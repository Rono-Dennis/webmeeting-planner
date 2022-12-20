package com.example.WebMeetingPlanner;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
//        (exclude = DataSourceAutoConfiguration.class)

public class SecurityApplication {


    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);


//        public static int fintlongestsubsequence(){
//            int [] nums ;
//            int max = 0;
//            if(nums.length<=1)
//            {
//                return nums;
//            }
//            int[] dp = new int[nums.length];
//
//            for(int i=1; i<nums.length; i++){
//                for( int j=0; j<i; j++){
//                    if(nums[j] < nums[i]){
//                        dp[i] = Math.max(dp[i], dp[j]+1);
//                    }
//                }
//                max = Math.max(max, dp[i]);
//            }
//            return max;
//        }
    }

    static class HelloWorld {
        public static void main(String[] args) {
            int [] arr = {1,2,3,4,5,6,7,4};

            for (int i=0; i< arr.length; i++){
                for(int j = i+1; j<arr.length; j++){
                    if(arr[i] == arr[j]){
                        System.out.println(arr[i]);
                    }
                }
            }
        }
    }





}





