package com.neetcode150.dynamic_programming_2d;

public class Q2LongestCommonSubsequence {

	class LCSResult {
	    int length;
	    String sequence;

	    LCSResult(int length, String sequence) {
	        this.length = length;
	        this.sequence = sequence;
	    }
	}
	
    /*
    ============================
    INTUITION:
    ============================
    The Longest Common Subsequence (LCS) problem asks:
    "What is the longest sequence that appears in BOTH strings in the same order (not necessarily contiguous)?"

    Idea:
    - If characters match → include that character in LCS.
    - If they don't match → try skipping one character from either string and take the best result.

    We use Dynamic Programming to avoid recomputation.

    ============================
    EXPLANATION:
    ============================
    Let memo[i][j] represent:
    LCS length of first i characters of text1 and first j characters of text2.

    Transition:
    1. If text1[i-1] == text2[j-1]:
         memo[i][j] = 1 + memo[i-1][j-1]

    2. Else:
         memo[i][j] = max(
             memo[i-1][j],   // skip char from text1
             memo[i][j-1]    // skip char from text2
         )

    Base case:
    - If either string is empty → LCS = 0

    ============================
    DRY RUN:
    ============================
    text1 = "abcde"
    text2 = "ace"

    Table (memo):

        ""  a  c  e
    ""  0  0  0  0
    a   0  1  1  1
    b   0  1  1  1
    c   0  1  2  2
    d   0  1  2  2
    e   0  1  2  3

    Steps:
    - 'a' == 'a' → 1
    - 'c' == 'c' → 2
    - 'e' == 'e' → 3

    Final Answer = 3 ("ace")

    ============================
    TIME & SPACE COMPLEXITY:
    ============================
    Time:  O(m * n)
    Space: O(m * n)
    */

    public LCSResult longestCommonSubsequence(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();
        int[][] memo = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    memo[i][j] = 1 + memo[i - 1][j - 1];
                } else {
                    memo[i][j] = Math.max(memo[i][j - 1], memo[i - 1][j]);
                }
            }
        }

        // return memo[m][n]; //this is answer for this question comment this 
        // and remove below code in this method for solution. below code is for retrieve common subsequence string along with length 
        
        
        // Step 2: Backtrack to find LCS string
        StringBuilder sb = new StringBuilder();
        
        int i =m,  j=n;
        
        while(i>0 && j>0) {
        	
        	if(text1.charAt(i-1) == text2.charAt(j-1)) {
        		sb.append(text1.charAt(i-1));
        		i--;
        		j--;
        	}else if(memo[i-1][j] > memo[i][j-1]) {
        		i--;
        	}else
        		j--;
        }
        
        return new LCSResult(memo[m][n], sb.reverse().toString());
    }

    /*
    ============================
    MAIN METHOD (TESTING)
    ============================
    */
    public static void main(String[] args) {

        Q2LongestCommonSubsequence obj = new Q2LongestCommonSubsequence();

        String text1 = "abcde";
        String text2 = "ace";

        LCSResult result = obj.longestCommonSubsequence(text1, text2);

        System.out.println("Length of Longest Common Subsequence is: " + result.length + " and common subsequence is: "+result.sequence);

        /*
        Expected Output:
        Longest Common Subsequence length: 3
        */
    }
}
