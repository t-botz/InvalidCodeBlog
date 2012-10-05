
package com.invalidcodeexception.experiment.immutability;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @date Sep 24, 2012
 * @author thibaultdelor
 */
public class FileLogger {
    
    private final String path;

    public FileLogger(String path) throws IOException {
        this.path = path;
    }

    public void log(String s) throws IOException{
        try(FileOutputStream f = new FileOutputStream(path,true)){
            f.write(s.getBytes());
        }
        
    }
    
    public static void main(String[] args) throws IOException, InterruptedException{
        ExecutorService service = Executors.newFixedThreadPool(10);
        FileLogger log = new FileLogger("/tmp/testLoggger");
        for(int i=0; i<10; i++){
            service.submit(new LogThread(log));
        }
        service.shutdown();
        service.awaitTermination(100, TimeUnit.SECONDS);
    }
}
class LogThread extends Thread{

    
    private FileLogger log;

    public LogThread(FileLogger log) {
        this.log = log;
    }
    
    
    
    @Override
    public void run() {
        try{
            for(int i=0; i<10000; i++){
                log.log("Write this line to the loggggg!!!\n");
            }
            
        } catch (Exception e){}
        
    }
    
}