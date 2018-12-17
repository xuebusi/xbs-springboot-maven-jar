package com.xuebusi.springboot.maven.py.jython;

import org.python.util.PythonInterpreter;

public class HelloPython {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("print('hello')");
    }
}