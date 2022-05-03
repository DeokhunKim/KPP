package Personal.KPP.app.glossary.library.kmp;

public class KMP {

    // Function to implement the KMP algorithm
    public static void SearchPrint(String text, String pattern)
    {
        // base case 1: pattern is null or empty
        if (pattern == null || pattern.length() == 0)
        {
            System.out.println("The pattern occurs with shift 0");
            return;
        }

        // base case 2: text is NULL, or text's length is less than that of pattern's
        if (text == null || pattern.length() > text.length())
        {
            System.out.println("Pattern not found");
            return;
        }

        char[] chars = pattern.toCharArray();

        // next[i] stores the index of the next best partial match
        int[] next = new int[pattern.length() + 1];
        for (int i = 1; i < pattern.length(); i++)
        {
            int j = next[i + 1];

            while (j > 0 && chars[j] != chars[i]) {
                j = next[j];
            }

            if (j > 0 || chars[j] == chars[i]) {
                next[i + 1] = j + 1;
            }
        }

        for (int i = 0, j = 0; i < text.length(); i++)
        {
            if (j < pattern.length() && text.charAt(i) == pattern.charAt(j))
            {
                if (++j == pattern.length()) {
                    System.out.println("The pattern occurs with shift " + (i - j + 1));
                }
            }
            else if (j > 0)
            {
                j = next[j];
                i--;    // since `i` will be incremented in the next iteration
            }
        }
    }

    // Function to implement the KMP algorithm
    public static boolean isContain(String text, String pattern)
    {
        // base case 1: pattern is null or empty
        if (pattern == null || pattern.length() == 0)
        {
            return false;
        }

        // base case 2: text is NULL, or text's length is less than that of pattern's
        if (text == null || pattern.length() > text.length())
        {
            return false;
        }

        char[] chars = pattern.toCharArray();

        // next[i] stores the index of the next best partial match
        int[] next = new int[pattern.length() + 1];
        for (int i = 1; i < pattern.length(); i++)
        {
            int j = next[i + 1];

            while (j > 0 && chars[j] != chars[i]) {
                j = next[j];
            }

            if (j > 0 || chars[j] == chars[i]) {
                next[i + 1] = j + 1;
            }
        }

        for (int i = 0, j = 0; i < text.length(); i++)
        {
            if (j < pattern.length() && text.charAt(i) == pattern.charAt(j))
            {
                if (++j == pattern.length()) {
                    return true;
                }
            }
            else if (j > 0)
            {
                j = next[j];
                i--;    // since `i` will be incremented in the next iteration
            }
        }

        return false;
    }

    public static void main(String[] args) {
        KMP.SearchPrint("ABCABAABCABAC", "AB");
        System.out.println("args = " + KMP.isContain("ABCABAABCABAC", "AABCA"));;
    }


}
