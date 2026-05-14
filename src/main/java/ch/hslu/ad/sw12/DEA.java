package ch.hslu.ad.sw12;

public final class DEA {

    private static boolean isZero(final char c) {
        return c == '0';
    }

    private static boolean isOne(final char c) {
        return c == '1';
    }

    public static boolean isWordLanguage(final String s) {

        DEAState state = DEAState.STATE_0;
        for (final char c : s.toCharArray()) {

            switch (state) {
                case STATE_0:
                    if (isZero(c)) {
                        state = DEAState.STATE_1_END;
                    } else {
                        state = DEAState.INVALID;
                    }
                    break;

                case STATE_1_END, STATE_3, STATE_4_END:
                    if (isOne(c)) {
                        state = DEAState.STATE_2;
                    } else {
                        state = DEAState.INVALID;
                    }
                    break;

                case STATE_2:
                    if (isOne(c)) {
                        state = DEAState.STATE_3;
                    } else if (isZero(c)) {
                        state = DEAState.STATE_4_END;
                    } else {
                        state = DEAState.INVALID;
                    }
                    break;

                case INVALID:
                    // if char or pattern is not allowed, return already
                    return false;

                default:
                    throw new IllegalStateException("invalid state " + state);
            }
        }

        return state == DEAState.STATE_1_END || state == DEAState.STATE_4_END;
    }
}
