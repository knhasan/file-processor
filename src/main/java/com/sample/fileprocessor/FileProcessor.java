package com.sample.fileprocessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FileProcessor class.
 *
 */
public class FileProcessor {
    
    public enum Categories {
        PERSON, PLACE, ANIMAL, COMPUTER, OTHER;
        private Categories(){}
        
        private int count = 0;
        
        public void increment() {
            count++;
        }
        
        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        if(args == null || args.length == 0 || !Files.exists(Paths.get(args[0]), LinkOption.NOFOLLOW_LINKS)) {
            System.out.println("Usage: java com.sample.FileProcessor <path_to_input_file>");
            return;
        }
        Set<String> entries = new LinkedHashSet<String>();
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(args[0]), StandardCharsets.UTF_8);
            String input = null;
            while((input = reader.readLine()) != null) {
                //Assume line begins with non whitespace character
                String category = input.split("\\s+")[0];
                try {
                    if(Categories.valueOf(category) != null && entries.add(input)) {
                      Categories.valueOf(category).increment();
                    }
                } catch (IllegalArgumentException iae) {
                    //Category doesn't exist, ignore the entry and don't include
                    //in the set to be printed.
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error reading file: " + ioe.getMessage());
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                   //Ignore
                }
            }
            reader = null;
        }
        
        System.out.println("CATEGORY   COUNT");
        for(Categories c: Categories.values()) {
            System.out.println(c + "   " + c.getCount());
        }
        
        System.out.println("\n");
        for(String entry: entries) {
            System.out.println(entry);
        }
    }
}
