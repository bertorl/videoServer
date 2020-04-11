package com.aromero.videoserver.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomGeneratorUUID {


    public static Long generateRandomID() {
        return new Date().getTime() + randomInteger(999999, 9999999);
    }

    public static int randomInteger(int min, int max) {
        return new Random().nextInt(max) + min;
    }
}
