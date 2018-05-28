package com.wxt.tools;


import java.util.Random;

/**
 * Created by yq on 2017/5/6.
 */
public class RandomNum {
    final static LoggerControler log = LoggerControler.getLogger(RandomNum.class);


    //    生成N位数的一个随机数
    public static String getNumRondom(int length) {
        String num = "";
//   random() 生成一个 [0,1)期间的随机数
        num = String.valueOf((long) (Math.random() * Math.pow(10, length)));
        log.info(num);
        return num;
    }

    public static int getNumRondom(int min, int max) {
        int num = 0;
        Random romdom = new Random();
//        min  10 max 20，[10,20)   [1,11)  11+10  21
        num = romdom.nextInt(max - min + 1) + min;
        log.info(num);
        return num;
    }

    public static String getStrngRandom(int length) {
        String val = "";
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            String charornum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if (charornum.equals("char")) {
                int tem = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + tem);
//            log.info(a);
            } else if (charornum.equals("num")) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        log.info(val);
        return val;

    }


}
