
import java.io.*;

import types.SkipNode;


public class ActivityCalendar {

    public static void main(String[] args) throws IOException {

        SkipList sL = new SkipList();


        File in = null;
        if (args.length > 0) {
            in = new File(args[0]);
        } else {
            System.err.println("Did not receive a file from command line;");
            System.exit(0);
        }

        // file in for queries
        BufferedReader reader = new BufferedReader(new FileReader(in));

        String readLine = null;

        while ((readLine = reader.readLine()) != null) {
            boolean executed = true;
            System.out.println("Command received:\n\t" + readLine);

            //split each line into an array of space-seperated words
            String[] iW = readLine.split(" ");
            switch (iW[0]) {
                case "AddActivity": {
                    Integer timeDate = Integer.valueOf(iW[1]);
                    String activity = iW[2];

                    sL.insertNode(new SkipNode(activity, timeDate));
                    break;
                }

                case "RemoveActivity": {
                    Integer timeDate = Integer.valueOf(iW[1]);

                    sL.removeNode(timeDate);
                    
                    break;
                }

                case "GetActivity": {
                    Integer timeDate = Integer.valueOf(iW[1]);

                    SkipNode n = sL.searchList(timeDate);

                    System.out.println("\nResponse:\n\t" + (n == null ? "No activity found" : n.getActivity()));

                    break;
                }

                case "GetActivitiesBetweenTimes": {
                    Integer start = Integer.valueOf(iW[1]);
                    Integer end = Integer.valueOf(iW[2]);

                    sL.subMap(start, end);

                    break;
                }

                case "GetActivitiesForOneDay": {
                    //turn date into proper dateTime format mmddhhmm

                    String s = iW[1];
                    Integer start = Integer.valueOf(s + "0000");

                    // add 1 to dd, then properly format to mmddhhmm
                    Integer end = Integer.valueOf(s);
                    end += 1;
                    s = String.valueOf(end);
                    end = Integer.valueOf(s + "0000");

                    sL.subMap(start, end);

                    break;
                }

                case "GetActivitiesFromEarlierInTheDay": {
                    Integer end = Integer.valueOf(iW[1]);

                    // get the proper format for the beginning of the day
                    // mmdd0000
                    String s = iW[1];
                    s = s.substring(0, 4);
                    s += "0000";
                    Integer start = Integer.valueOf(s);

                    sL.subMap(start, end);

                    break;
                }

                case "PrintSkipList": {
                    sL.printList();

                    break;
                }

                default: {
                    executed = false;
                    System.out.println("No Option For Command Given");
                    break;
                }
            }

            System.out.println("\n\t" + (executed ? "✅ Command executed successfully" : "❌ Command failed") + "\n" + "-".repeat(20) + "\n");

        }
        reader.close();
    }


}



