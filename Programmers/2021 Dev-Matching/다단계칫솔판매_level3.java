import java.util.*;

class Solution {
    static HashMap<String, Person> map = new HashMap<>();
    
    static class Person{
        String refer;
        int profit;
        
        public Person(String refer){
            this.refer = refer;
        }
    }
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        map.put("center", new Person(null));
        
        for(int i = 0; i < enroll.length; i++){
            String refer = referral[i].equals("-") ? "center" : referral[i];
            map.put(enroll[i], new Person(refer));
        }
        
        for(int i = 0; i < seller.length; i++){
            getProfit(seller[i], amount[i] * 100);
        }
        
        
        int[] answer = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++){
            answer[i] = map.get(enroll[i]).profit;
        }
        return answer;
    }
    
    void getProfit(String seller, int amount){
        int notMyAmount = amount / 10;
        
        map.get(seller).profit += amount - notMyAmount;
        
        if(notMyAmount != 0 && map.get(seller).refer != null){
            getProfit(map.get(seller).refer, notMyAmount);
        } 
    }
}