package com.resource;

import java.io.File;
import java.io.IOException;

public class ResourceManager {

    public ResourceManager() {
        initDictionaryPath();
    }

    private void initDictionaryPath() {
        File f = new File("");
        System.out.println(f);
        try {
            setDictionary(f.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String dictionary;

    public void setDictionary(String newDictionary) {
        dictionary = newDictionary + "/resource";
    }

    public File getResource(String url) {
        var path = dictionary + url + ".html";
        var file = new File(path);
        return file;
    }

}
