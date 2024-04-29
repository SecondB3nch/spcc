import java.util.*;

public class MacroProcessorPass1 {
    // Macro Name Table (MNT)
    static Map<String, Integer> mnt = new HashMap<>();
    // Macro Definition Table (MDT)
    static List<String> mdt = new ArrayList<>();

    public static void main(String[] args) {
        // Input Assembly Language Program with Macro
        String[] input = {
            "MACRO",
            "INCR",
            "AR 2,3",
            "MR 1,2",
            "ST 1,2",
            "MEND",
            "PG1 START",
            "USING *,15",
            "L 1,FIVE",
            "A 1, FOUR",
            "INCR",
            "ST 1,TEMP",
            "M 1,FIVE",
            "INCR",
            "FIVE DC F'5'",
            "FOUR DC F'4'",
            "TEMP DS 1F",
            "END"
        };

        // Process Macro Definition
        int mdtIndex = -1;
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            if (line.equals("MACRO")) {
                String macroName = input[i + 1];
                mnt.put(macroName, mdtIndex + 1);
                i += 2; // Skip MACRO and macro name
                StringBuilder macroDefinition = new StringBuilder();
                while (!input[i].equals("MEND")) {
                    macroDefinition.append(input[i]).append("\n");
                    i++;
                }
                mdt.add(macroDefinition.toString());
                mdtIndex++;
            }
        }

        // Output MNT
        System.out.println("Macro Name Table (MNT):");
        for (Map.Entry<String, Integer> entry : mnt.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Output MDT
        System.out.println("\nMacro Definition Table (MDT):");
        for (int i = 0; i < mdt.size(); i++) {
            System.out.println(i + ": " + mdt.get(i));
        }
    }
}
