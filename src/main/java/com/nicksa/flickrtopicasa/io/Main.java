/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nicksa.flickrtopicasa.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {

	public Main() throws Throwable {

	}

	public void copy(String from, String to) throws Throwable {
		File sourceFile = new File(from);
		File toFile = new File(to);

		// Does the source file exist?
		if (!sourceFile.exists()) {
			throw new IllegalStateException("Source file does not exist.");
		}

		// Is the destination path a directory?
		if (toFile.isDirectory()) {
			toFile = new File(toFile, sourceFile.getName());
		}

		// Does the destination path already exist on the filesystem?
		if (!toFile.exists()) {
			boolean successInMakingDirectory = toFile.getParentFile().mkdirs();
			if (!successInMakingDirectory) {
				throw new IllegalStateException("Could not create directories.");
			}
		}

		// Start copying.
		InputStream in = new FileInputStream(sourceFile);
		OutputStream out = new FileOutputStream(toFile);

		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	public static void main(String[] args) throws Throwable {
		String image = new String(
				"C:/Users/Nicksa/Desktop/suspicious-supermarket.jpg");
		String destination = new String(
				"C:/Users/Nicksa/Desktop/destination/newfile.jpg");
		Main main = new Main();
		main.copy(image, destination);

	}

}
