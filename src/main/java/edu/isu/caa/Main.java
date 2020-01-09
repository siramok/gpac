package edu.isu.caa;

import edu.isu.caa.GUI.MainApp;

/**
 * A main class for launching the app. A workaround to this problem: https://github.com/javafxports/openjdk-jfx/issues/236#issuecomment-426583174
 *
 * @author Andres Sewell
 */
public class Main {
    public static void main(String[] args) {
        MainApp.init(args);
    }
}
