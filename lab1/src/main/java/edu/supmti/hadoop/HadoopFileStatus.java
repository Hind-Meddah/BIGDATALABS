package edu.supmti.hadoop;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class HadoopFileStatus {

    public static void main(String[] args) throws IOException {

        if (args.length != 2 && args.length != 3) {
            System.out.println("Usage:");
            System.out.println("  Afficher infos : HadoopFileStatus <chemin_dossier> <nom_fichier>");
            System.out.println("  Renommer fichier : HadoopFileStatus <chemin_dossier> <nom_fichier> <nouveau_nom>");
            System.exit(1);
        }

        String folderPath = args[0];     // ex: /user/root/input
        String fileName   = args[1];     // ex: purchases.txt

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        // Chemin complet du fichier
        Path filepath = new Path(folderPath, fileName);

        if (!fs.exists(filepath)) {
            System.out.println("File does not exist: " + filepath);
            System.exit(1);
        }

        FileStatus status = fs.getFileStatus(filepath);

        // ----- Affichage des informations -----
        System.out.println("====== FILE INFORMATION ======");
        System.out.println("File Name: " + filepath.getName());
        System.out.println("File Size: " + status.getLen() + " bytes");
        System.out.println("File Owner: " + status.getOwner());
        System.out.println("File Permission: " + status.getPermission());
        System.out.println("File Replication: " + status.getReplication());
        System.out.println("File Block Size: " + status.getBlockSize());

        BlockLocation[] blockLocations = fs.getFileBlockLocations(status, 0, status.getLen());
        System.out.println("====== BLOCK INFORMATION ======");
        for (BlockLocation block : blockLocations) {
            System.out.println("\nBlock offset: " + block.getOffset());
            System.out.println("Block length: " + block.getLength());
            System.out.print("Block hosts: ");
            for (String host : block.getHosts()) {
                System.out.print(host + " ");
            }
            System.out.println();
        }

        // ----- Mode RENOMMAGE si 3 arguments -----
        if (args.length == 3) {
            String newFileName = args[2];
            Path newFilePath = new Path(folderPath, newFileName);

            if (fs.rename(filepath, newFilePath)) {
                System.out.println("\nFile renamed successfully to: " + newFileName);
            } else {
                System.out.println("\nFailed to rename file.");
            }
        }

    }
}
