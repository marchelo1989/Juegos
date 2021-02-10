/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.FUN;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Marchelo
 */
public class Archivos {

        public void CopiarArchivos(String sourceFile, String destinationFile) {

            try {

                File inFile = new File(sourceFile);
                File outFile = new File(destinationFile);

                FileInputStream in = new FileInputStream(inFile);
                FileOutputStream out = new FileOutputStream(outFile);

                byte[] buffer = new byte[1024];
                int c;

                while ((c = in.read(buffer)) != -1) {
                    out.write(buffer, 0, c);
                }

                out.flush();
                in.close();
                out.close();

            } catch (IOException e) {
                System.out.println("Hubo un error de entrada/salida!!!");
//            Log.e(TAG, "Hubo un error de entrada/salida!!!");
            }
        }

//    public static void main(String args[]){
//        if(args.length == 2)
//            new CopiarArchivos(args[0], args[1]);
//    }
}
