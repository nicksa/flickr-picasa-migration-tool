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

import org.springframework.util.FileCopyUtils;

import java.io.File;

/**
 * Utilities that are more defensive than a lot of other libraries and that
 * handle a lot of nuanced use cases.
 *
 * @author nicksa
 *
 */
abstract public class IoUtils {

    /**
     * Nobody should ever need to instantiate this class, so we hide the constructor.
     */
	private IoUtils(){
	}

	public static void copyFile(String from, String to) throws Throwable {
		File sourceFile = new File(from);
		File toFile = new File(to);
		
		copyFile(sourceFile, toFile);

	}
	
	public static void copyFile(File sourceFile, File toFile) throws Throwable {
		// Does the source file exist?
		if (!sourceFile.exists()) {
			throw new IllegalStateException("Source file does not exist.");
		}

		// Is the destination path a directory?
		if (toFile.isDirectory()) {
			toFile = new File(toFile, sourceFile.getName());
		}

		// Does the destination path already exist on the filesystem?
		File parentDirectory = toFile.getParentFile();
		if (!parentDirectory.exists()) {
			boolean successInMakingDirectory = parentDirectory.mkdirs();
			if (!successInMakingDirectory) {
				throw new IllegalStateException("Could not create directories.");
			}
		}

		// Start copying.
		FileCopyUtils.copy(sourceFile, toFile);		
	}

}
