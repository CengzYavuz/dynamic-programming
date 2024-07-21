import Graph.DirectedGraph;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {//O(n^2)


        File landmark_map = new File("landmark_map_data.txt");
        File personal_interest = new File("personal_interest.txt");
        File visitor_load = new File("visitor_load.txt");
        DirectedGraph tourMap = new DirectedGraph();

        if (landmark_map.exists() && personal_interest.exists() && visitor_load.exists()) {

            Scanner PIReader = new Scanner(personal_interest);
            Scanner VLReader = new Scanner(visitor_load);
            Scanner input = new Scanner(System.in);

            System.out.print("\nPlease enter the total number of landmarks (including Hotel): ");

            int n = Integer.valueOf(input.nextLine());

            PIReader.nextLine();
            VLReader.nextLine();

            while (PIReader.hasNextLine() && VLReader.hasNextLine()) {

                String personalLine[] = PIReader.nextLine().split("\\t");
                String visitorLoad = VLReader.nextLine().split("\\t")[1];
                tourMap.addVertex(personalLine[0], personalLine[1], visitorLoad);
            }
            VLReader.close();
            PIReader.close();

            Scanner landmarkReader = new Scanner(landmark_map);
            landmarkReader.nextLine();

            while (landmarkReader.hasNext()) {

                String words[] = landmarkReader.nextLine().split("\\t");

                tourMap.addEdge(tourMap.getVertex(words[0]), tourMap.getVertex(words[1]), words[3], words[2]);
            }
            tourMap.printMostAttractivePath(tourMap, n);

        } else {
            throw new Exception("Some file unable to open");
        }

    }
}