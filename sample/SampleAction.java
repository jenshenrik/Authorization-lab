/*
 *
 * Copyright (c) 2000, 2002, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * -Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduct the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 * Neither the name of Oracle nor the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY
 * DAMAGES OR LIABILITIES  SUFFERED BY LICENSEE AS A RESULT OF  OR
 * RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THE SOFTWARE OR
 * ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 * THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that Software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility.
 */

package sample;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessControlException;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <p> This is a Sample PrivilegedAction implementation, designed to be
 * used with the Sample application.
 *
 */
public class SampleAction implements PrivilegedAction {

    /**
     * <p> This Sample PrivilegedAction performs the following operations:
     * <ul>
     * <li> Access the System property, <i>java.home</i>
     * <li> Access the System property, <i>user.home</i>
     * <li> Access the file, <i>foo.txt</i>
     * </ul>
     *
     * @return <code>null</code> in all cases.
     *
     * @exception SecurityException if the caller does not have permission
     *          to perform the operations listed above.
     */
    public Object run() {
    	File basic = new File("movies/basic");
    	File silver = new File("movies/silver");
    	File gold = new File("movies/gold");
    	LinkedList<File> availableMovies = new LinkedList<File>();
    	
    	try {
    		File[] basicFiles = basic.listFiles();
    		for (File f : basicFiles) {
    			availableMovies.add(f);
    		}
    	} catch (AccessControlException e) {
    		System.out.println("You do not have access to Basic-subscription movies.\n");
    	}

    	try {
    		File[] basicFiles = silver.listFiles();
    		for (File f : basicFiles) {
    			availableMovies.add(f);
    		}
    	} catch (AccessControlException e) {
    		System.out.println("You do not have access to Silver-subscription movies.\n");
    	}

    	try {
    		File[] basicFiles = gold.listFiles();
    		for (File f : basicFiles) {
    			availableMovies.add(f);
    		}
    	} catch (AccessControlException e) {
    		System.out.println("You do not have access to Gold-subscription movies.\n");
    	}
    	
    	Collections.sort(availableMovies, null);
    	
    	System.out.println("You have access to the following movies:");
    	for (File f : availableMovies) {
    		System.out.println(f.getName());
    	}
    	
    	try {
    		basic.canWrite();
    		System.out.println("You have admin (writing) rights.\n");
    	} catch (AccessControlException e) {
    		System.out.println("You do not have admin rights.\n");
    	}
    	
    	for (File f : availableMovies) {
	    	try {
	    		//File f = availableMovies.get(0);
	    		System.out.println("Opening file " + f.getPath() + "...");
	    		Path filePath = Paths.get(f.getPath());
	    		List<String> lines = Files.readAllLines(filePath, Charset.defaultCharset());
	    		for (String line : lines) {
	    			System.out.println(line);
	    		}
	    		System.out.println();
	    	} catch (AccessControlException e) {
	    		System.out.println("You do not have access to read the requested file.\n");
	    	} catch (IOException e1) {
	    		System.out.println("I/O Exception.\n");
	    	}
    	}
        return null;
    }
}
