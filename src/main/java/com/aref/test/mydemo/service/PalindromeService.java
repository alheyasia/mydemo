package com.aref.test.mydemo.service;

import org.springframework.stereotype.Service;

@Service
public class PalindromeService {

    public int calculateLongestPalindrome(String word) {
        int result = 0;
        if (word == null || word.trim().length() == 0) {
            return 0;
        }
        word = deleteNonChars(word);

        if (word.length() == 0) {
            return 0;
        }
        if (word.length() <= 1) {
            return 1;
        }

        for (int i = 0; i < word.length() - 1; i++) {
            result = findLongestPoly(word, i, i, result);
            result = findLongestPoly(word, i, i + 1, result);
        }
        return result;
    }

    private String deleteNonChars(String word) {
        return word.replaceAll("[^a-zA-Z]", "");
    }

    private int findLongestPoly(String word, int startIndex, int endIndex, int result) {
        while (startIndex >= 0 && endIndex < word.length() && word.charAt(startIndex) == word.charAt(endIndex)) {
            startIndex--;
            endIndex++;
        }
        if (endIndex - startIndex - 1 > result) {
            return endIndex - startIndex - 1;
        }
        return result;
    }
}
