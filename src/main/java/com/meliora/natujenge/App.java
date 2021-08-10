package com.meliora.natujenge;

import com.meliora.natujenge.core.ApplicationContext;

import java.lang.reflect.InvocationTargetException;

/**
 * Hello world!
 */
public class App {
    String packageName = this.getClass().getPackage().getName();
    private static ApplicationContext applicationContext;

    public App() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("Starting to set up stuff...");
        applicationContext = new ApplicationContext(packageName);
    }

    public static void main(String[] args) {
        try {
            App app = new App();
        } catch (Exception ex) {
            System.out.println("Exception " + ex);
        }
    }
}
