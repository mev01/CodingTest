import java.util.*;

class Solution {
    static Person[] personArr;
    static class Person{
        boolean topVisited, botVisited;
        HashSet<Integer> topSet, botSet;
        
        public Person(){
            topSet = new HashSet<>();
            botSet = new HashSet<>();
        }
    }
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        personArr = new Person[n + 1];
        for(int i = 1; i <= n; i++){
            personArr[i] = new Person();
        }
        
        for(int[] result : results){
            personArr[result[0]].botSet.add(result[1]);
            personArr[result[1]].topSet.add(result[0]);
        }
        
        for(int i = 1; i <= n; i++){
            getUpResult(i);
            getDownResult(i);
            
            if(personArr[i].topSet.size() + personArr[i].botSet.size() == n - 1) ++answer;
        }
        
        return answer;
    }
    
    HashSet<Integer> getUpResult(int num){
        Person person = personArr[num];
        
        if(person.topVisited) return person.topSet;
        person.topVisited = true;
        
        Iterator it = person.topSet.iterator();
        HashSet<Integer> newSet = new HashSet<Integer>();
        
        while(it.hasNext()){
            HashSet<Integer> hashSet = getUpResult((int)it.next());
            
            Iterator it2 = hashSet.iterator();
            while(it2.hasNext()){
                newSet.add((int)it2.next());
            }
        }
        
        it = newSet.iterator();
        while(it.hasNext()){
            person.topSet.add((int)it.next());
        }
        
        return person.topSet;
    }
    
    HashSet<Integer> getDownResult(int num){
        Person person = personArr[num];
        
        if(person.botVisited) return person.botSet;
        person.botVisited = true;
        
        Iterator it = person.botSet.iterator();
        HashSet<Integer> newSet = new HashSet<Integer>();
        
        while(it.hasNext()){
            HashSet<Integer> hashSet = getDownResult((int)it.next());
            
            Iterator it2 = hashSet.iterator();
            while(it2.hasNext()){
                newSet.add((int)it2.next());
            }
        }
        
        it = newSet.iterator();
        while(it.hasNext()){
            person.botSet.add((int)it.next());
        }
        
        return person.botSet;
    }
}