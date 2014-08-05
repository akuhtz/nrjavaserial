package gnu.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerialManager.class);

    private static SerialManager instance;

    private static boolean loaded = false;

    private SerialManager() throws NativeResourceException {
        if (!loaded) {
            loaded = true;

            LOGGER.info("Load native library.");
            new NativeResource().load("libNRJavaSerial");
        }
    }

    public static SerialManager getInstance() throws NativeResourceException {
        if (instance == null) {
            instance = new SerialManager();
        }
        return instance;
    }
}
