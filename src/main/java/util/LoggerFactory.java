package util;

import java.util.logging.Logger;

public class LoggerFactory {

    public static Logger getLogger(){
        return Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClass().getName());
    }
}
