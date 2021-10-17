package org.minecraft;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

public final class DisplayManager {

    /**
     * The width of the screen
     */
    private static final int WIDTH = 1280;

    /**
     * The height of the screen
     */
    private static final int HEIGHT = 720;

    /**
     * The fps cap
     */
    private static final int FPS_CAP = 120;

    private static long lastFrameTime;
    private static float delta;

    public static void createDisplay() {

        ContextAttribs attribs = new ContextAttribs(3, 2)
                .withForwardCompatible(true)
                .withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(new PixelFormat(), attribs);
            Display.setTitle("MineCraft");
            GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
            lastFrameTime = getCurrentTime();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

    }

    public static void updateDisplay() {

        Display.sync(FPS_CAP);
        Display.update();

        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime) / 1000f;
        lastFrameTime = currentFrameTime;

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {

                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    closeDisplay();
                }

                Mouse.setGrabbed(!Keyboard.isKeyDown(Keyboard.KEY_E));

            }
        }

    }

    public static void closeDisplay() {
        Display.destroy();
        System.exit(0);
    }

    public static float getFrameTimeSeconds() {
        return delta;
    }

    private static long getCurrentTime() {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }

}
