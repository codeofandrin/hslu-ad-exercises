package ch.hslu.ad.sw12;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DEATest {

    @Test
    void testSingleZero() {
        assertThat(DEA.isWordLanguage("0")).isTrue();
    }

    @Test
    void testThreeChars() {
        assertThat(DEA.isWordLanguage("010")).isTrue();
    }

    @Test
    void testOneGroupThreeOnes() {
        assertThat(DEA.isWordLanguage("01110")).isTrue();
    }

    @Test
    void testTwoGroupsOneOne() {
        assertThat(DEA.isWordLanguage("0101010")).isTrue();
    }

    @Test
    void testExampleWord0111010() {
        assertThat(DEA.isWordLanguage("0111010")).isTrue();
    }

    @Test
    void testExampleWord0101110() {
        assertThat(DEA.isWordLanguage("0101110")).isTrue();
    }

    @Test
    void testTwoGroupsOfOneOne() {
        assertThat(DEA.isWordLanguage("010")).isTrue();
    }

    @Test
    void testFiveOnesGroup() {
        assertThat(DEA.isWordLanguage("011111" + "0")).isTrue();
    }

    @Test
    void testEmptyString() {
        assertThat(DEA.isWordLanguage("")).isFalse();
    }

    @Test
    void testOnlyOne() {
        assertThat(DEA.isWordLanguage("1")).isFalse();
    }

    @Test
    void testStartsWithOne() {
        assertThat(DEA.isWordLanguage("10")).isFalse();
    }

    @Test
    void testEvenNumberOfOnes() {
        assertThat(DEA.isWordLanguage("0110")).isFalse();
    }

    @Test
    void testEndsWithOne() {
        assertThat(DEA.isWordLanguage("011")).isFalse();
    }

    @Test
    void testEndsWithOneAfterGroup() {
        assertThat(DEA.isWordLanguage("0101")).isFalse();
    }

    @Test
    void testDoubleZeroAtStart() {
        assertThat(DEA.isWordLanguage("00")).isFalse();
    }

    @Test
    void testInvalidCharacter() {
        assertThat(DEA.isWordLanguage("012")).isFalse();
    }

    @Test
    void testOnlyZeros() {
        assertThat(DEA.isWordLanguage("000")).isFalse();
    }

    @Test
    void testInvalidChar() {
        assertThat(DEA.isWordLanguage("x")).isFalse();
    }
}
