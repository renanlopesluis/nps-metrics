package com.ilegra.npsmetrics.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class FileStream {
		
	public abstract void write(InputStream input, String fileName) throws IOException;
	public abstract List<String> read() throws IOException;
	
	public void delete(File file) {
		file.delete();
	}
}
