package ch.hslu.ad.sw04.hash_maps;

import java.util.*;

public class CharCount {

    private static final String secretText =
            "GBFRFS QFMQ BRQ XBQ FBWFS RVDFWJWWQFW RPIRQBQPQBVWHCBEESF OFSRHCYPFRRFYQ.\n"
                + "GJIFB NFSGFW GBF IPHCRQJIFW GFR JYUCJIFQR GPSHC JWGFSF IPHCRQJIFW\n"
                + "FSRFQKQ. GJ JIFS AFGFS IPHCRQJIF GFR ZYJSQFMQFR GPSHC GFW DYFBHCFW\n"
                + "HVGFIPHCRQJIFW FSRFQKQ NBSG, BRQ FR XVFDYBHC XBQ CBYEF GFS CJFPEBDZFBQRJWJYLRF\n"
                + "GFW HVGF SFHCQ FBWEJHC FWQRHCYPFRRFYW. WVHC BX XBQQFYJYQFS JIFS DJYQ FBWF \n"
                + "RVYHCF XFQCVGF JYR RBHCFS.\n\n"
                + "PX GBF OFSRHCYPFRRFYPWDRQJIFYYF EPFS GBFRFR IFBRUBFY KP FSKFPDFW, RHCSFBIQ XJW\n"
                + "FBWEJHC GJR JYUCJIFQ BW FBWF KFBYF, BW GFS WJFHCRQFW KFBYF RHCSFBIQ XJW WVHC\n"
                + "FBWXJY GJR JYUCJIFQ PWQFS GBF IPHCRQJIFW, JYYFSGBWDR IFDBWWQ XJW GBFRXJY JX"
                + " FWGF\n"
                + "GFR JYUCJIFQR. WPW OFSRHCBFIQ XJW GBF PWQFSF KFBYF PX KFCW KFBHCFW WJHC SFHCQR\n"
                + "PWG RHCSFBIQ GBF IPHCRQJIFW, GBF JPE GFS SFHCQFW RFBQF KPOBFY RBWG JPE GBF\n"
                + "YBWZF RFBQF, GJXBQ GBF QJIFYYF NBFGFS OVYYRQJFWGBD BRQ. WPW ZJWW XJW FBWFW\n"
                + "IPHCRQJIFW JPR FBWFX ZYJSQFMQ BW GFS FSRQFW KFBYF RPHCFW, PWG GBSFZQ\n"
                + "GJSPWQFS EBWGFQ XJW GFW OFSRHCYPFRRFYQFW IPHCRQJIFW.";

    private static final Map<Character, Character> decrypt =
            Map.ofEntries(
                    Map.entry('F', 'E'),
                    Map.entry('W', 'N'),
                    Map.entry('B', 'I'),
                    Map.entry('R', 'S'),
                    Map.entry('Q', 'T'),
                    Map.entry('J', 'A'),
                    Map.entry('G', 'D'),
                    Map.entry('C', 'H'),
                    Map.entry('S', 'R'),
                    Map.entry('Y', 'L'),
                    Map.entry('H', 'C'),
                    Map.entry('P', 'U'),
                    Map.entry('I', 'B'),
                    Map.entry('X', 'M'),
                    Map.entry('K', 'Z'),
                    Map.entry('D', 'G'),
                    Map.entry('E', 'F'),
                    Map.entry('V', 'O'),
                    Map.entry('O', 'V'),
                    Map.entry('Z', 'K'),
                    Map.entry('U', 'P'),
                    Map.entry('M', 'X'),
                    Map.entry('N', 'W'),
                    Map.entry('A', 'J'),
                    Map.entry('L', 'Y'));

    static void main() {
        StringBuilder encoded = new StringBuilder();
        for (Character secretChar : secretText.toCharArray()) {
            if (secretChar >= 'A' && secretChar <= 'Z') {
                encoded.append(decrypt.get(secretChar));
            } else {
                encoded.append(secretChar);
            }
        }

        System.out.println(encoded);
    }

    public static HashMap<Character, Integer> countChars(final String text) {
        final HashMap<Character, Integer> charCount = new HashMap<>();
        for (char character : text.toCharArray()) {
            char lower = Character.toLowerCase(character);
            if (lower >= 'a' && lower <= 'z') {
                if (charCount.containsKey(lower)) {
                    charCount.compute(lower, (k, count) -> count + 1);
                } else {
                    charCount.put(lower, 1);
                }
            }
        }

        return charCount;
    }
}
