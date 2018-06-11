package com.github.wendelsilverio.mathevaluator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializator {

    public static <T> boolean serialize(T t, File output) {
	try {
	    FileOutputStream fileOut = new FileOutputStream(output);
	    ObjectOutputStream out = new ObjectOutputStream(fileOut);
	    out.writeObject(t);
	    out.close();
	    fileOut.close();
	    return true;
	} catch (IOException i) {
	    i.printStackTrace();
	}
	return false;
    }

    public static <T> T unserialize(File input) {
	try {
	    FileInputStream fileIn = new FileInputStream(input);
	    ObjectInputStream in = new ObjectInputStream(fileIn);
	    @SuppressWarnings("unchecked")
	    T t = (T) in.readObject();
	    in.close();
	    fileIn.close();
	    return t;
	}catch(IOException i) {
	    i.printStackTrace();
	}catch(ClassNotFoundException c) {
	    c.printStackTrace();
	}
	return null;
    }
}
