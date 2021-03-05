package dsproject;

import java.util.Random;

/**
 *
 * @author Khandine, Earl, Jared
 * 
 *  Question selector
 *  This class enables us to get a random question number 
 *  without having to worry about duplicates.
 */
public class QuestionSelector {
    
    /*
        Explanation of algorithm
       
        create an array (arr) like the following
        index- [0][1][2][3][4][5] 
        value- [1][2][3][4][5][6] .. total number of questions in binary tree
  
        When we need a random question number we can...
    
        - get a random number (rand) between 0 and last index of array
        - give the number in arr[rand] as the next random non duplicate number
        - swap the value in the last index with the value in arr[rand]
        - implicity decrement the last index by one 
        
        e.g
        start with:
        index- [0][1][2][3][4][5]
        value- [1][2][3][4][5][6]
    
        generate a random number between 0 and 5: 3
        -> arr[3] will be returned, which is 4
    
        ( we can now find question 4 in the binary tree and do 
          something with it, e.g. bt.find(4) )
    
        swap value in last index with that value
        -> new array looks like:
        index- [0][1][2][3][4][/]
        value- [1][2][3][6][5][/]
                         ^     ^ 
        next time we need a new question number we can...
       
        generate another random num, this time between 0 and 4 (new last index)
        if we get another 3, arr[3] is returned which is 6
        
        after another swap the array looks like:
        index- [0][1][2][3][/][/]
        value- [1][2][3][5][/][/]
                         ^  ^
    
        This can be repeated until the last index reaches below 0
        and guarantees that there are no duplicate numbers requested
    
        Because the numbers are stored in an array in memory, the benefits 
        are similar to hashing in that there is little expense needed to
        find a random number from the array.
    */
    
    
    // the max number of questions stored 
    int max;
    // current pointer to last index of array
    int lastIndex;
    // array for fisher yates number selection
    int[] arr;
    
    
    /*
        Constructor
    */
    public QuestionSelector(int max) {
        // init values
        this.max = max;
        this.lastIndex = max-1;
        arr = new int[max];
        /*
            init array with values to look like:
            index- [0][1][2][3][4][5][6]..[lastIndex]
            value- [1][2][3][4][5][6][7]..[max]
        */
        int q = 1;
        for (int i=0; i<arr.length; i++) {
            arr[i] = q;
            q++;
        }
    }
    
    
    public int getNextRandom() {
        
        // pre-check: if lastIndex < zero, we have run out of questions to get
        if (lastIndex < 0) {
            return 0;
            // this should not happen, because we check for valid input
            // in the GUI
        }
        
        // init random class
        Random rand = new Random();
        // generate random number between 0 and lastIndex
        int randNum = rand.nextInt((lastIndex - 0) + 1) + 0;
        
        // set return value
        int returnval = arr[randNum];
        
        // swap last value of array into this position
        if(randNum != lastIndex) {
            arr[randNum] = arr[lastIndex];
        }
        
        // decrease lastIndex by 1
        lastIndex--;
        
        // return random number, which will NEVER BE A DUPLICATE
        return returnval;
    }
    
}// end class
