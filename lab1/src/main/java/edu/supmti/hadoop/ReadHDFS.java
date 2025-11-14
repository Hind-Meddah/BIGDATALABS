package edu.supmti.hadoop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class ReadHDFS {

    public static void main(String[] args) throws IOException {

        // Vérification des arguments
        if (args.length != 1) {
            System.out.println("Usage: ReadHDFS <chemin_complet_du_fichier>");
            System.exit(1);
        }

        String fullPath = args[0];   // ex: /user/root/input/purchases.txt

        // Configuration Hadoop
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        Path nomComplet = new Path(fullPath);

        if (!fs.exists(nomComplet)) {
            System.out.println("File does not exist: " + nomComplet);
            System.exit(1);
        }

        // Lecture du fichier HDFS
        FSDataInputStream inStream = fs.open(nomComplet);
        BufferedReader br = new BufferedReader(new InputStreamReader(inStream));

        String line;

        System.out.println("===== FILE CONTENT =====");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
        inStream.close();
        fs.close();
    }
}
