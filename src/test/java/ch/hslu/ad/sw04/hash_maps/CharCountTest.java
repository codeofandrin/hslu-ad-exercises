package ch.hslu.ad.sw04.hash_maps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

class CharCountTest {

    private final CharCount charCount = new CharCount();

    @Test
    void shouldCountLowercaseCharacters() {
        HashMap<Character, Integer> result = charCount.countChars("abcabc");

        assertThat(result)
                .hasSize(3)
                .containsEntry('a', 2)
                .containsEntry('b', 2)
                .containsEntry('c', 2);
    }

    @Test
    void shouldBeCaseInsensitive() {
        HashMap<Character, Integer> result = charCount.countChars("aA");

        assertThat(result).hasSize(1).containsEntry('a', 2);
    }

    @Test
    void shouldIgnoreNonAlphabeticCharacters() {
        HashMap<Character, Integer> result = charCount.countChars("a1! b@c#");

        assertThat(result)
                .hasSize(3)
                .containsEntry('a', 1)
                .containsEntry('b', 1)
                .containsEntry('c', 1);
    }

    @Test
    void shouldReturnEmptyMapForEmptyString() {
        HashMap<Character, Integer> result = charCount.countChars("");

        assertThat(result).isEmpty();
    }

    @Test
    void shouldHandleOnlyNonLetters() {
        HashMap<Character, Integer> result = charCount.countChars("1234!@#$");

        assertThat(result).isEmpty();
    }

    @Test
    void shouldHandleSingleCharacter() {
        HashMap<Character, Integer> result = charCount.countChars("z");

        assertThat(result).hasSize(1).containsEntry('z', 1);
    }

    @Test
    void shouldHandleMixedInputCorrectly() {
        HashMap<Character, Integer> result = charCount.countChars("Hello World!");

        assertThat(result)
                .containsEntry('h', 1)
                .containsEntry('e', 1)
                .containsEntry('l', 3)
                .containsEntry('o', 2)
                .containsEntry('w', 1)
                .containsEntry('r', 1)
                .containsEntry('d', 1);
    }

    @Test
    void shouldNotContainUnexpectedKeys() {
        HashMap<Character, Integer> result = charCount.countChars("abc");

        assertThat(result).doesNotContainKey('x');
    }
}
