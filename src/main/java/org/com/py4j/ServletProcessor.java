package org.com.py4j;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @Author Warren
 * @CreateTime 29.May.2018
 * @Version V1.0
 */
public class ServletProcessor {

    public void process (Request request,Response response) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/")+1);
        URLClassLoader loader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            String repository = new URL("file",null,classPath.getCanonicalPath()+File.separator).toString();
            urls[0] = new URL(null,repository,streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException pE) {
            System.out.println(pE.toString());
        }

        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException pE) {
            System.out.println(pE.toString());
        }

        Servlet servlet = null;
        try {
            RequestFacade requestFacade = new RequestFacade(request);
            ResponseFacade responseFacade = new ResponseFacade(response);
            servlet = (Servlet) myClass.newInstance();
            servlet.service(requestFacade,responseFacade);
        } catch (Exception pE) {
            System.out.println(pE.toString());
        } catch (Throwable t) {
            System.out.println(t.toString());
        }
    }

}
