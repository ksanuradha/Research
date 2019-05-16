/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counterparts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Demo {

    public static void main(String[] args) {

        // Gray Scalling
        // Thresholding
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat imgGrayscale = new Mat();
        Mat image = Imgcodecs.imread(".\\src\\com\\edu\\sliit\\img\\brailee.jpg", 1);
        System.out.println("Width : "+image.width());
        System.out.println("Height  : "+image.height());
        HashMap<Integer, double[]> findRectangle = new BraileeDetect(image).findRectangle();
        FindXCordinates f =new FindXCordinates();
        HashMap<Integer, ArrayList<Double>> findAverageXCordinates = f.findAverageXCordinates(findRectangle);
        ArrayList<Double> avegXYCordinates = f.getAvegXYCordinates(findAverageXCordinates);
        //  CountWhireSpots c=new CountWhireSpots();
        // c.drawALine(avegXYCordinates);
    }
}
