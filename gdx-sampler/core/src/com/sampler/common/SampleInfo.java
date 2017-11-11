package com.sampler.common;

/**
 * Created by Marc Holman on 20/08/2016.
 */
public class SampleInfo {

    private final String name;
    private final Class<? extends SampleBase> clazz;

    public SampleInfo(Class<? extends SampleBase> clazz) {
        this.clazz = clazz;
        name = clazz.getSimpleName();
    }

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
