/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.pmml.benchmark.common.utils;

import java.io.*;
import java.net.URL;

/**
 * Utility to access files
 */
public class FileUtils {

    private FileUtils() {
        // Avoid instantiating class
    }

    /**
     * Retrieve the <code>File</code> of the given <b>file</b>
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File getFile(String fileName) throws IOException {
        File toReturn = getByClassloader(fileName);
//        if (toReturn == null) {
//            final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
//            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            toReturn =  resource != null ? new File(resource.getPath()) : null;
//        }
        if (toReturn == null || !toReturn.exists()) {
            throw new IOException("Failed to find " + fileName);
        }
        return toReturn;
    }

    /**
     * Retrieve the <code>FileInputStream</code> of the given <b>file</b>
     * @param fileName
     * @return
     * @throws IOException
     */
    public static FileInputStream getFileInputStream(String fileName) throws IOException {
        File sourceFile = getFile(fileName);
        return new FileInputStream(sourceFile);
    }

    private static File getByResourceHelper(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        return ResourceHelper.getResourcesByExtension(extension)
                .filter(file -> file.getName().equals(fileName))
                .findFirst()
                .orElse(null);
    }

    private static File getByClassloader(String fileName) {
        URL res = Thread.currentThread().getContextClassLoader().getResource(fileName);
        File toReturn = null;
        String name = fileName.substring(0, fileName.lastIndexOf('.'));
        if (res.getProtocol().equals("jar")) {
            try {
                InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
                toReturn = File.createTempFile(name, ".pmml");
                OutputStream out = new FileOutputStream(toReturn);
                int read;
                byte[] bytes = new byte[1024];

                while ((read = input.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                out.close();
                toReturn.deleteOnExit();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            //this will probably work in your IDE, but not from a JAR
            toReturn = new File(res.getFile());
        }

        if (toReturn != null && !toReturn.exists()) {
            throw new RuntimeException("Error: File " + toReturn + " not found!");
        }
        return toReturn;

    }

}
