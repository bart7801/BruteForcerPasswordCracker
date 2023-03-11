import java.util.Scanner;
import java.time.*;
import java.math.BigDecimal;

public class Main {
    static String newPass = "";
    static String chars = "";
    static int tries = 0;

    public static void main(String[] args) throws InterruptedException {
        chars += "0";
        chars += "~";
        for (int i = 33;i < 126;i++)
        {
            if ((char)i == 'Z' || (char)i == '0' || (char)i == '~')
            {
                continue;
            }
            chars += String.valueOf((char)i);
        }
        chars += "Z";
        Scanner sc = new Scanner(System.in);
        System.out.println((char)27 + "[31mWelcome to the java Brute Force Password Cracker!");
        Thread.sleep(3000);
        System.out.print("This program was created by ");
        System.out.println((char)27 + "[36mbart" + (char)27 + "[31m.");

        Thread.sleep(2500);
        System.out.println("Would you like it to print of guessed passwords? note: it will take longer y/n");
        String what = String.valueOf(sc.next().charAt(0)).toLowerCase();
        Thread.sleep(2500);
        System.out.println((char)27 + "[33m\nPlease enter your password > ");
        sc.nextLine(); String password = sc.nextLine();
        System.out.println((char)27 + "[36m\nCracking your password...");
        Instant start = Instant.now();
        crack(password, what);
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        BigDecimal time = new BigDecimal(Long.toString(timeElapsed));
        BigDecimal k = new BigDecimal("1000");
        BigDecimal zero = new BigDecimal("0");
        BigDecimal one = new BigDecimal("1");
        BigDecimal daTime = time.divide(k);
        if (daTime.compareTo(zero) > 0) {
            System.out.println("\n\nYour password has been found! Here are the statistics:");
            System.out.println("----------------------------------");
            System.out.println("Password: " + password);
            System.out.println("Password length: " +  password.length());
            System.out.println("Tries: " + tries);
            String plural = "seconds";
            if (daTime == one) {
                plural = "second";
            }
            System.out.println("Time to crack: " + daTime  + " " + plural);
        }
        else {
            System.out.println("\n\nYour password has been found! Here are the statistics:");
            System.out.println("----------------------------------");
            System.out.println("Password: " +  password);
            System.out.println("Password length: " + password.length());
            System.out.println("Tries: " + tries);
            System.out.println("Time to crack: " + daTime + " seconds");
        }
        sc.close();
    }

    private static void crack(String password,String what) {
        for (int length = 1; length <= 30; length++) {
            newPass = "";
            newPass = repeatString("0", length);
            int lastInd = length - 1;
            while (!newPass.equals(password)) {
                String end = repeatString("Z", newPass.length());
                if (newPass.equals(end)) {
                    break;
                }
                int indInChars = chars.indexOf(newPass.charAt(lastInd));
                if (indInChars < chars.length() && indInChars >= 0) {
                    boolean t = true;
                    int howManyZs = 0;
                    while (t == true) {
                        if (newPass.charAt(newPass.length() - 1 - howManyZs) == 'Z') {
                            howManyZs++;
                        } else {
                            t = false;
                        }
                    }
                    String reset0s = "";
                    for (int l = 0; l < howManyZs; l++) {
                        reset0s += "0";
                    }
                    if (lastInd < newPass.length() - 1 && lastInd > 0) {
                        lastInd--;
                        indInChars = chars.indexOf(newPass.charAt(lastInd)) + 1;
                        newPass = newPass.substring(0, lastInd) + chars.charAt(indInChars)
                                + newPass.substring(lastInd + 1);
                    } else if (newPass.length() - howManyZs == 1) {
                        newPass = chars.substring(chars.indexOf(newPass.charAt(0)) + 1,
                                chars.indexOf(newPass.charAt(0)) + 2) + reset0s;
                    } else if (newPass.length() - howManyZs > 1 && howManyZs != 0) {
                        newPass = newPass.substring(0, newPass.length() - 1 - howManyZs)
                                + chars.substring(chars.indexOf(newPass.charAt(newPass.length() - 1 - howManyZs)) + 1,
                                chars.indexOf(newPass.charAt(newPass.length() - 1 - howManyZs)) + 2)
                                + reset0s;
                    } else {
                        indInChars = chars.indexOf(newPass.charAt(lastInd)) + 1;
                        newPass = newPass.substring(0, lastInd) + chars.charAt(indInChars);
                    }
                    if (what.equals("y"))
                        System.out.println(newPass);
                    else
                    {

                    }
                    tries += 1;
                }
            }
            if (newPass.equals(password)) {
                break;
            }
        }
    }

    private static String repeatString(String s, int n) {
        StringBuilder sb = new StringBuilder(n);
        while (n-- > 0) {
            sb.append(s);
        }
        return sb.toString();
    }
}
