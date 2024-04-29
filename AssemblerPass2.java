import java.util.HashMap;
import java.util.Map;

public class AssemblerPass2 {
    // Define Machine Operation Table (MOT)
    static Map<String, String> mot = new HashMap<>();
    static {
        mot.put("A", "5A");
        mot.put("L", "6A");
        mot.put("ST", "7A");
        mot.put("END", "H");
    }

    // Define Pseudo Operation Table (POT)
    static Map<String, String> pot = new HashMap<>();
    static {
        pot.put("DS", "");
        pot.put("DC", "");
        pot.put("START", "");
        pot.put("USING", "");
    }

    // Define Symbol Table (ST)
    static Map<String, Integer> st = new HashMap<>();
    static {
        st.put("FIVE", 12);
        st.put("FOUR", 16);
        st.put("TEMP", 20);
    }

    public static void main(String[] args) {
        // Input Assembly Language Program
        String[] input = {
            "PG1 START 0",
            "** USING *,15",
            "** L 1,FIVE",
            "** A 1,FOUR",
            "** ST 1,TEMP",
            "FIVE DC F'5'",
            "FOUR DC F'4'",
            "TEMP DS 1F",
            "** END PG1"
        };

        // Output Machine Code
        StringBuilder output = new StringBuilder();

        // Process each line of the input program
        for (String line : input) {
            String[] tokens = line.split("\\s+");

            // Check if line contains an assembly instruction
            if (tokens.length >= 2) {
                String opcode = tokens[1];

                // Check if opcode is in MOT
                if (mot.containsKey(opcode)) {
                    output.append(mot.get(opcode)).append("\n");
                }
                // Check if opcode is in POT (for labels)
                else if (pot.containsKey(opcode)) {
                    // Check if it's a label definition
                    if (tokens.length >= 3 && tokens[2].equals("DS")) {
                        // Output nothing for DS directive
                    } else if (tokens.length >= 3 && tokens[2].equals("DC")) {
                        // Output machine code for DC directive
                        String label = tokens[0];
                        int value = Integer.parseInt(tokens[3].substring(2, tokens[3].length() - 1));
                        output.append(value).append("\n");
                    }
                }
            }
        }

        System.out.println(output.toString());
    }
}