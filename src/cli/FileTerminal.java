package cli;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileTerminal implements IOInterface {
    private Scanner file;

    public FileTerminal(String filename) throws FileNotFoundException {

        this.file = new Scanner(new FileReader(filename));
    }

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }

    @Override
    public void writeResponse(ArrayList<String> response) {
        for (String line : response) {
            this.writeLine(line);
        }
    }

    @Override
    public String readLine() {
        String line = file.nextLine();
        return line;
    }

    @Override
    public Boolean isInteractive() {
        return false;
    }

    @Override
    public void closeStream() {
        this.file.close();
    }

}
