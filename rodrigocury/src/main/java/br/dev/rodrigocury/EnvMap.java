package br.dev.rodrigocury;

import java.util.Map;

public class EnvMap {
    public static void main (String[] args) {
    	test();
    }
    
    private static void list() {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                              envName,
                              env.get(envName));
        }
    }
    
    private static void test()  {
    	String env = "MYSQL_PASS";
        String value = System.getenv(env);
        if (value != null) {
            System.out.format("%s=%s%n",
                              env, value);
        } else {
            System.out.format("%s is"
                + " not assigned.%n", env);
        }
    
    }
}