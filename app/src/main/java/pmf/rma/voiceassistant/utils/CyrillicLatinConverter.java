package pmf.rma.voiceassistant.utils;

import java.util.HashMap;
import java.util.Map;

public class CyrillicLatinConverter {
    private static final char[] cyrillic = new char[] {
            '\u0410', 	'\u0430',	//A
            '\u0411',	'\u0431',	//B
            '\u0412', 	'\u0432',	//V
            '\u0413', 	'\u0433',	//G
            '\u0414',	'\u0434',	//D
            '\u0402',	'\u0452',	//?
            '\u0415', 	'\u0435',	//E
            '\u0416',	'\u0436',	//?
            '\u0417',	'\u0437',	//Z
            '\u0418',	'\u0438',	//I
            '\u0408',	'\u0458',	//J
            '\u041A',	'\u043A',	//K
            '\u041B',	'\u043B',	//L
            '\u0409',	'\u0459',	//Lj
            '\u041C',	'\u043C',	//M
            '\u041D',	'\u043D',	//N
            '\u040A',	'\u045A',	//Nj
            '\u041E', 	'\u043E',	//O
            '\u041F',	'\u043F', 	//P
            '\u0420',	'\u0440',	//R
            '\u0421',	'\u0441',	//S
            '\u0422',	'\u0442',	//T
            '\u040B',	'\u045B',	//?
            '\u0423',	'\u0443',	//U
            '\u0424',	'\u0444',	//F
            '\u0425',	'\u0445',	//H
            '\u0426',	'\u0446',	//C
            '\u0427', 	'\u0447',	//?
            '\u040F',	'\u045F',	//D?
            '\u0428',	'\u0448'	//?
    };

    private static final String[] latin = new String[] {
            "A", 		"a",
            "B",		"b",
            "V",		"v",
            "G",		"g",
            "D",		"d",
            "\u0110",	"\u0111",
            "E",		"e",
            "\u017D",	"\u017E",
            "Z",		"z",
            "I",		"i",
            "J",		"j",
            "K",		"k",
            "L",		"l",
            "Lj",		"lj",
            "M",		"m",
            "N",		"n",
            "Nj",		"nj",
            "O",		"o",
            "P",		"p",
            "R",		"r",
            "S",		"s",
            "T",		"t",
            "\u0106",	"\u0107",
            "U",		"u",
            "F",		"f",
            "H",		"h",
            "C",		"c",
            "\u010C",	"\u010D",
            "D\u017E",	"d\u017E",
            "\u0160",	"\u0161"};

    private static final Map<Character, String> cyrMapping = new HashMap<>();
    private static final Map<String, Character> latMapping = new HashMap<>();

    static {
        for (int i = 0; i < cyrillic.length; i++) {
            cyrMapping.put(cyrillic[i], latin[i]);
            latMapping.put(latin[i], cyrillic[i]);
        }
    }

    public static String latinToCyrillic(String latinText) {
        StringBuilder latBuilder = new StringBuilder(latinText);
        StringBuilder cyrBuilder = new StringBuilder();

        for (int i = 0; i < latBuilder.length(); i++) {
            String s = latBuilder.substring(i, i + 1);
            if (i < latBuilder.length() - 1 ) {
                char c = latBuilder.charAt(i + 1);
                if (((s.equals("L") || s.equals("l")
                        || s.equals("N") || s.equals("n")) && (c == 'J' || c == 'j'))) {
                    s = s + 'j';
                    i++;
                } else if ((s.equals("D") || s.equals("d"))
                        && (c == '\u017D' || c == '\u017E')) {
                    s = s + '\u017E';
                    i++;
                }
            }
            if (latMapping.containsKey(s)) {
                cyrBuilder.append(latMapping.get(s));
            } else {
                cyrBuilder.append(s);
            }
        }
        return cyrBuilder.toString();
    }

    public static String cyrillicToLatin(String cyrillicText) {
        StringBuilder cyrBuilder = new StringBuilder(cyrillicText);
        StringBuilder latBuilder = new StringBuilder();
        for (int i = 0; i < cyrBuilder.length(); i++) {
            char c = cyrBuilder.charAt(i);
            Character character = c;
            if (cyrMapping.containsKey(character)) {
                latBuilder.append(cyrMapping.get(character));
            } else {
                latBuilder.append(c);
            }
        }
        return latBuilder.toString();
    }
}
