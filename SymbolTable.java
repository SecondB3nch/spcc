import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Symbol {
    String name;
    String type;
    int address;

    public Symbol(String name, String type, int address){
        this.name = name;
        this.type = type;
        this. address = address;

    }
}
public class SymbolTable {
    private static final ArrayList<Symbol> symbolTable = new ArrayList<>();
    private static int currentAddress = 1000; //Starting memory address

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an expression:");
        String expression = scanner.nextLine();

        extractAndStoreSymbols(expression);

        while (true) {
            System.out.println("Choose operation: \n1. Insert \n2. Display \n3. Delete \n 4. Search \n 5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    insertSymbol();
                    break;
                case 2:
                    displayTable();
                    break;
                case 3:
                    deleteSymbol();
                    break;
                case 4:
                    searchSymbol();
                    break;
                case 5:
                    System.out.println("Exiting program. ");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");

            }
        }
    }

    private static void extractAndStoreSymbols(String expression) {
        Pattern pattern = Pattern.compile("[a-zA-Z_]+|\\S");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String symbolName = matcher.group();
            String symbolType = (symbolName.matches("[a-zA-Z_]+")) ? "Identifier" : "Operator";
            Symbol symbol = new Symbol(symbolName, symbolType, currentAddress++);
            symbolTable.add(symbol);
        }
        System.out.println("Symbols extracted and stored in the table.");
    }

    private static void insertSymbol() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter symbol name: ");
        String name = scanner.next();
        System.out.print("Enter the symbol type identifier/operator:");
        String type = scanner.next();
        Symbol symbol = new Symbol(name, type, currentAddress++);
        symbolTable.add(symbol);
        System.out.println("Symbol inserted successfully.");
    }

    private static void displayTable() {
        if (symbolTable.isEmpty())
            System.out.println("Symbol Table is empty.");
        else
            System.out.println("Symbol Table:");
        System.out.printf("%-15s %-15s %-15s\n", "Name", "Type", "Address");
        for (Symbol symbol : symbolTable) {
            System.out.printf("%-15s %-15s %-15d \n", symbol.name, symbol.type, symbol.address);
        }
    }

    private static void deleteSymbol() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter symbol name to delete: ");
        String nameToDelete = scanner.next();
        for(Symbol symbol : symbolTable) {
            if (symbol.name.equals(nameToDelete)) {
                symbolTable.remove(symbol);
                System.out.println("Symbol Deleted successfully.");
                return;
            }
        }
        System.out.println("Symbol not found.");
    }
    private static void searchSymbol(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter symbol name to search:");
        String nameToSearch = scanner.next();
        for (Symbol symbol : symbolTable){
            if (symbol.name.equals(nameToSearch)) {
                System.out.println("Symbol found:");
                System.out.printf("%-15s %-15s %-15s\n", "Name", "Type", "Address");
                System.out.printf("%-15s %-15s %-15d\n", symbol.name, symbol.type, symbol.address);
                return;
            }
        }
        System.out.println("Symbol not found.");
    }
}
