/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counterparts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;
import org.opencv.core.MatOfPoint;

public class Demo {

    public static void main(String[] args) {

        // Gray Scalling
        // Thresholding
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat imgGrayscale = new Mat();
        Mat image = Imgcodecs.imread(".\\src\\com\\edu\\sliit\\img\\brailee.jpg", 1);
        System.out.println("Width : "+image.width());
        System.out.println("Height  : "+image.height());
        //List<MatOfPoint> ret=new SketchRecognition(image).findRectangle();
        HashMap<Integer, double[]> findRectangle = new BraileeDetect(image).findRectangle();  
        HashMap<Double, ArrayList<Double>> findAverageXCordinates = new FindXCordinates().findAverageXCordinates(findRectangle);
                
        // how to iterate a Hashmap
        Iterator hmIterator = findAverageXCordinates.entrySet().iterator();
        int a=1;
        while (hmIterator.hasNext()) { 
            Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
            ArrayList<Double> cordinates = ( (ArrayList<Double>)mapElement.getValue()); 
            System.out.println("Y cordinate : "+mapElement.getKey());
            for(int i=0;i<cordinates.size();i++){
                System.out.println("X Cordinates : "+a+" "+cordinates.get(i));
                 a++;
            }
           
            System.out.println("");
        } 
    }
}
