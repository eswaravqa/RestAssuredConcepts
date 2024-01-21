package SpecificationConcept;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ConsoleToFile {
	
    public static void main(String[] args) {
        try {
            // Create a FileOutputStream to write to a file
            FileOutputStream fileOutputStream = new FileOutputStream("./test-output/LogFiles/TestConsoleOutputLogs.txt");

            // Create a PrintStream that writes to the file
            PrintStream printStream = new PrintStream(fileOutputStream);

            // Redirect the System.out to the PrintStream
            System.setOut(printStream);

            // Your console output
            System.out.println("Hello, world!");
            System.out.println("This will be written to the file.");

            // Flush and close the PrintStream
            printStream.flush();
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
