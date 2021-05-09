package com.aref.test.mydemo.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PalindromeServiceTest {

    private PalindromeService palindromeService = new PalindromeService();

    @Test
    void calculateLongestPalindrome_shouldReturnZero_whenWordIsNull() {
        int actualResult = palindromeService.calculateLongestPalindrome(null);
        assertEquals(0, actualResult);
    }

    @Test
    void calculateLongestPalindrome_shouldReturnZero_whenWordIsEmpty() {
        int actualResult = palindromeService.calculateLongestPalindrome("");
        assertEquals(0, actualResult);
    }

    @Test
    void calculateLongestPalindrome_shouldReturnZero_whenWordIsEmptySpace() {
        int actualResult = palindromeService.calculateLongestPalindrome(" ");
        assertEquals(0, actualResult);
    }

    @Test
    void calculateLongestPalindrome_shouldReturnZero_whenWordIsComposedOfNonChars() {
        int actualResult = palindromeService.calculateLongestPalindrome(" 546@!");
        assertEquals(0, actualResult);
    }

    @Test
    void calculateLongestPalindrome_shouldReturnOne_whenWordHasOneChar() {
        int actualResult = palindromeService.calculateLongestPalindrome("a");
        assertEquals(1, actualResult);
    }

    @Test
    void calculateLongestPalindrome_shouldReturnTwo_whenWordHasOnlyDuplicatedChar() {
        int actualResult = palindromeService.calculateLongestPalindrome("bb");
        assertEquals(2, actualResult);
    }

    @Test
    void calculateLongestPalindrome_shouldReturnResult_whenWordHasOnlyChars() {
        int actualResult = palindromeService.calculateLongestPalindrome("abrakadabra");
        assertEquals(3, actualResult);
    }

    @Test
    void calculateLongestPalindrome_shouldReturnResult_whenWordHasNonChars() {
        int actualResult = palindromeService.calculateLongestPalindrome("abr3aka5dab@ra!");
        assertEquals(3, actualResult);
    }

}