/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.FUN;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 *
 * @author march
 */
public class HiloPasos extends Thread{
    JProgressBar barrar;
    public static int retraso = 0;
    
    public HiloPasos(JProgressBar barrar){
        this.barrar = barrar;
    }
    
    @Override
    public void run(){
        int minimo = barrar.getMinimum();
        int maximo = barrar.getMaximum();
        
        Runnable ejecutor = new Runnable() {
            @Override
            public void run() {
                int valorActual = barrar.getValue();
                barrar.setValue(valorActual+1);
            }
        };
        
        for (int i = minimo; i < maximo; i++) {
            try {
                EventQueue.invokeAndWait(ejecutor);
                Thread.sleep(retraso);
            } catch (InterruptedException ex) {} 
            catch (InvocationTargetException ex) {}
        }
    }
}
