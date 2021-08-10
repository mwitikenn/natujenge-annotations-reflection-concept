package com.meliora.natujenge.core;

import com.meliora.natujenge.annotations.Autowired;
import com.meliora.natujenge.annotations.Helper;
import com.meliora.natujenge.annotations.Repository;
import com.meliora.natujenge.annotations.Service;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ApplicationContext {

    Map<Class<?>, Object> singletons;

    public ApplicationContext(String packageName) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.singletons = new HashMap<>();

        System.out.println("About to initialize all resources...");

        initializeRepositories(packageName);

        initializeServices(packageName);

        initializeHelpers(packageName);

        System.out.println("We have initialized all the resources we need ...");
    }

    public void initializeRepositories(String packageName) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("Scanning for repositories...");

        Reflections reflections = new Reflections(packageName);

        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(Repository.class);

        for (Class<?> cl : classSet) {
            System.out.println("className :: " + cl.getName());
            Object object = cl.getDeclaredConstructor().newInstance();

            this.autowire(cl, object);

            this.singletons.put(cl, object);
        }
        System.out.println("Initialized all repositories... numberOfSingletons " + singletons.size());
    }

    public void initializeServices(String packageName) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("Scanning for services...");

        Reflections reflections = new Reflections(packageName);

        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(Service.class);

        for (Class<?> cl : classSet) {
            System.out.println("className :: " + cl.getName());
            Object object = cl.getDeclaredConstructor().newInstance();

            this.autowire(cl, object);

            this.singletons.put(cl, object);
        }
        System.out.println("Initialized all services... numberOfSingletons " + singletons.size());
    }

    public void initializeHelpers(String packageName) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("Scanning for helpers...");

        Reflections reflections = new Reflections(packageName);

        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(Helper.class);

        for (Class<?> cl : classSet) {
            System.out.println("className :: " + cl.getName());
            Object object = cl.getDeclaredConstructor().newInstance();

            this.autowire(cl, object);

            this.singletons.put(cl, object);
        }
        System.out.println("Initialized all helpers... numberOfSingletons " + singletons.size());
    }

    public void autowire(Class<?> cl, Object object) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (Field field : cl.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object fieldInstance = this.getClassInstance(field.getType());
                field.set(object, fieldInstance);
            }
        }
    }

    public Object getClassInstance(Class<?> cl) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        if (this.singletons.containsKey(cl)) {
            return this.singletons.get(cl);
        }

        synchronized (singletons) {
            Object object = cl.getDeclaredConstructor().newInstance();
            this.singletons.put(cl, object);

            return object;
        }
    }
}
