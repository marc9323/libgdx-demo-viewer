package com.sampler.common;

import com.badlogic.gdx.utils.reflect.ClassReflection;

/**
 * Created by Marc Holman on 20/08/2016.
 */
public class SampleFactory {

    public static SampleBase newSample(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name param is required.");
        }

        SampleInfo info = SampleInfos.find(name);

        try {
            return (SampleBase) ClassReflection.newInstance(info.getClazz());
        } catch (Exception e) {
            throw new RuntimeException("Cannot create sample with name= " + name, e);
        }
    }

    private SampleFactory() {
    }
}
