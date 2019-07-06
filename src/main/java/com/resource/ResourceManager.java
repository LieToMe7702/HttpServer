package com.resource;

import com.struct.AbstractManager;
import com.struct.Contexts;
import com.struct.IContexts;

import java.io.File;
import java.io.IOException;

public class ResourceManager extends AbstractManager {

    public ResourceManager(Contexts contexts) {
        super(contexts);
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
