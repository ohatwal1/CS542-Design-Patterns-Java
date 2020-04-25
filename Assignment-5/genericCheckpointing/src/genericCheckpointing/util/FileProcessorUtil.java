package genericCheckpointing.util;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileProcessorUtil implements TransferI {
    private BufferedReader br;
    private BufferedWriter bw;
    private String inFileName;
    private String outFileName;
    private File inFile;
    private File outFile;

    /**
     * Closes all handlers on the file
     */
    public void close() {
        try {
            if (bw != null) {
                bw.flush();
                bw.close();
            }
            br.close();
        } catch (IOException e) {
            MyLogger.writeMessage("IO Error occurred while closing file " + this.outFileName,
                    MyLogger.DebugLevel.NO_OUTPUT);
            e.printStackTrace();
            System.exit(1);
        }
        finally {
            assert true;
        }
    }

    /**
     * Writes the given string to file
     *
     * @param serializedOutput String to be written
     */
    public void write(String serializedOutput) {

        if (bw == null) {
            try {
                bw = new BufferedWriter(new FileWriter(this.outFile));
            } catch (IOException e) {
                MyLogger.writeMessage("Error writing to file: " + this.outFileName, MyLogger.DebugLevel.NO_OUTPUT);
                e.printStackTrace();
                System.exit(1);
            }
            finally {
                assert true;
            }
        }

        try {
            bw.write(serializedOutput);
            bw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Reads the string from file
     *
     * @return String read
     */
    public String read() {
        StringBuilder sb = new StringBuilder();

        if (br == null) {
            try {
                br = new BufferedReader(new FileReader(this.inFile));
            }
            catch (FileNotFoundException e)  {
                MyLogger.writeMessage("Error: file not found: " + this.inFileName, MyLogger.DebugLevel.NO_OUTPUT);
                e.printStackTrace();
                System.exit(1);
            }
            finally {
                assert true;
            }
        }

        while (true) {

            try {
                String line = br.readLine();

                if (line == null)
                    break;

                if (line.contains("</DPSerialization>")) {
                    sb.append(line);
                    break;
                }

                sb.append(line);
                sb.append("\n");
            }
            catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

        return sb.toString();
    }
    

    /**
     * Stores the name of file to be processed
     *
     * @param fileNameIn Name of the file
     */
	@Override
	public void setInFileName(String inFileName) {
        this.inFileName = inFileName;
        this.inFile = new File(inFileName);
	}

	@Override
	public void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
        this.outFile = new File(outFileName);
	}
}
