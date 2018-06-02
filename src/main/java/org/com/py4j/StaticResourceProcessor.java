package org.com.py4j;

import java.io.IOException;

/**
 * @Author Warren
 * @CreateTime 29.May.2018
 * @Version V1.0
 */
public class StaticResourceProcessor {

    public void process (Request request,Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException pE) {
            pE.printStackTrace();
        }
    }

}
