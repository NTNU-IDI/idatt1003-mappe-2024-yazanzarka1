package edu.ntnu.idi.idatt.console.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Utils is a class containing utility methods that can be used in the console application.
 */
public class Utils {

  /**
   * Truncate a sentence to a given word count and append "..." if the sentence is truncated.
   *
   * @param sentence  the sentence to truncate
   * @param wordCount the number of words to truncate the sentence to
   * @return the truncated sentence
   */
  public static String truncateSentence(String sentence, int wordCount) {
    List<String> wordsArray = Arrays.stream(sentence.trim().split(" ")).toList();
    return wordsArray.subList(0, Math.min(wordsArray.size(), wordCount)).stream()
        .reduce((a, b) -> a + " " + b).orElse("").concat("...");
  }

}
