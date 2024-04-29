import java.util.*;

public class MacroProcessorPass2 {
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

        // Expand Macro Calls in the Program
        List<String> expandedCode = new ArrayList<>();
        for (String line : input) {
            if (!line.equals("MACRO") && !line.equals("MEND")) {
                // Check if line contains a macro call
                if (line.contains("INCR")) {
                    int index = line.indexOf("INCR");
                    String macroName = line.substring(index);
                    int mdtIndexStart = mnt.get(macroName);
                    String[] macroDefinition = mdt.get(mdtIndexStart).split("\n");

                    // Replace macro call with macro definition
                    for (String macroLine : macroDefinition) {
                        String expandedLine = line.replace(macroName, macroLine);
                        expandedCode.add(expandedLine);
                    }
                } else {
                    expandedCode.add(line);
                }
            }
        }

        // Output Expanded Source Code
        for (String line : expandedCode) {
            System.out.println(line);
        }
    }
}