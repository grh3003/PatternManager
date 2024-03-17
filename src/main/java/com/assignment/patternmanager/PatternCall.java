package com.assignment.patternmanager;
/**
 * @author ghebbar
 */

import java.util.Objects;

/**
 * Represents a pattern call, consisting of an identifier, a name, a pattern file path, and a flag indicating if it's called.
 */
public class PatternCall {
    int id;
    String name;
    String patternFile;
    boolean called;

    /**
     * Constructor for creating a PatternCall object.
     *
     * @param id          The unique identifier of the pattern call.
     * @param name        The user-defined name of the pattern call.
     * @param patternFile The project-relative path to the pattern file.
     * @param called      A boolean flag indicating whether the pattern is called or not.
     */
    public PatternCall(int id, String name, String patternFile, boolean called) {
        this.id = id;
        this.name = name;
        this.patternFile = patternFile;
        this.called = called;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPatternFile() {
        return patternFile;
    }

    public boolean isCalled() {
        return called;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "ID: "+id+", Name: "+name+", patternFile: "+patternFile+", isCalled: "+isCalled();
    }
    @Override
    public boolean equals(Object o) {
    	if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatternCall that = (PatternCall) o;
        return id == that.id &&
                called == that.called &&
                Objects.equals(name, that.name) &&
                Objects.equals(patternFile, that.patternFile);
    }
}
