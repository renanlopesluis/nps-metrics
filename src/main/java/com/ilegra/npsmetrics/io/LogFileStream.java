package com.ilegra.npsmetrics.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogFileStream extends FileStream{
	
	@Override
	public List<String> read() throws IOException {
		List<Path> paths = FileManager.getAllFilesFromDirectory();
		return read(paths);
	}

	@Override
	public void write(InputStream input, String fileName) throws IOException {
		try {
			File file = new File(FileConfiguration.PATH.toString()+FileConfiguration.SPLITTER+fileName);
			file.getParentFile().mkdirs();
			OutputStream outputStream = new FileOutputStream(file);
			int read = 0;
			byte bytes[] = new byte[1024];
			
			while((read = input.read(bytes)) != -1)
				outputStream.write(bytes, 0, read);
			
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<String> read(List<Path> paths) throws IOException{
		List<String> lines = new ArrayList<String>();
		for (Path path : paths) {
			lines.addAll(read(path));
		}
		return lines;
	}
	
	private List<String> read(Path path) throws IOException {
		List<String> lines = new ArrayList<>();
		InputStream input = new FileInputStream(path.toString());
		Scanner reader = new Scanner(input);
		while (reader.hasNext()) {
			lines.add(reader.nextLine());
		}
		reader.close();
		delete(path.toFile());
		return lines;
	}

}
