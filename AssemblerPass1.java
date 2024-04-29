import java.util.*;

public class AssemblerPass1 {
    // Symbol Table (ST)
    static Map<String, Integer> st = new HashMap<>();

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

        // Process the input program
        int locationCounter = 0;
        for (String line : input) {
            String[] tokens = line.split("\\s+");

            // Check if line contains a label
            if (tokens.length >= 2 && !tokens[0].startsWith("**")) {
                String label = tokens[0];
                st.put(label, locationCounter);
            }

            // Check if line contains a pseudo-operation that affects location counter
            if (tokens.length >= 2 && tokens[1].equals("DS")) {
                locationCounter += Integer.parseInt(tokens[2].substring(0, tokens[2].length() - 1));
            } else if (tokens.length >= 2 && tokens[1].equals("DC")) {
                locationCounter++;
            } else if (tokens.length >= 2 && tokens[1].equals("START")) {
                locationCounter = Integer.parseInt(tokens[2]);
            }
        }

        // Output Symbol Table
        System.out.println("Symbol Table (ST):");
        for (Map.Entry<String, Integer> entry : st.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
