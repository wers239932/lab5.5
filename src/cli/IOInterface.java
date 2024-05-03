package cli;

import java.io.IOException;
import java.util.ArrayList;

public interface IOInterface {
    void writeLine(String line);

    void writeResponse(ArrayList<String> response);

    String readLine() throws IOException;

    Boolean isInteractive();
    public void closeStream();
}
