package loadbalancer.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	@Override
	public void writeOutput(StringBuilder sb, String outputFilePath) {

		File file = new File(outputFilePath);
		BufferedWriter writer = null;
		try {
			try {
				writer = new BufferedWriter(new FileWriter(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				writer.write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

}
