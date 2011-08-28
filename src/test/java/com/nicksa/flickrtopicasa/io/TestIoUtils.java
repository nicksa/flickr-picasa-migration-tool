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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class TestIoUtils {
    File in;
    File out;
    File extraSubdirectory;
    String tempDir = System.getProperty("java.io.tmpdir");
    String pathDir = System.getProperty("user.dir");

    @Test
    public void testCopyFileToDirectory() throws Throwable {
        IoUtils.copyFile(in, out.getParentFile());
        File x = new File(tempDir, in.getName());
        Assert.assertTrue(x.exists());
    }

    @Test
    public void testCopyFileRename() throws Throwable {
        IoUtils.copyFile(in, out);
        Assert.assertTrue(out.exists());
    }

    @Test
    public void testCopyFileToNonexistentDirectory() throws Throwable {
        Assert.assertFalse(extraSubdirectory.exists());
        IoUtils.copyFile(in, extraSubdirectory);
        Assert.assertTrue(extraSubdirectory.exists());
    }

    @Before
    public void setUp() {
        reset();
    }

    @After
    public void tearDown() {
        reset();
    }

    void reset() {
        in = new File(pathDir, "/src/test/resources/suspicious-supermarket.jpg");
        out = new File(tempDir, "newfile.jpg");
        if (out.exists()) {
            boolean deleted = out.delete();
            Assert.assertTrue(deleted);
        }

        extraSubdirectory = new File(tempDir, "/foo/foo.jpg");
        if (extraSubdirectory.exists()) {
            boolean deleted = extraSubdirectory.delete();
            Assert.assertTrue(deleted);
        }
        if (extraSubdirectory.getParentFile().exists()) {
            boolean deleted = extraSubdirectory.getParentFile().delete();
            Assert.assertTrue(deleted);
        }
    }
}
