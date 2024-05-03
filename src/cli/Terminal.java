package cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal implements IOInterface {
    private final Scanner sc;

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

    public Terminal() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public String readLine() {
        return this.sc.nextLine();
    }

    @Override
    public Boolean isInteractive() {
        return true;
    }
    @Override
    public void closeStream() {
        this.sc.close();
    }
}
