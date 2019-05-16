/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counterparts;

import java.util.ArrayList;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
//20.0
public class CountWhireSpots {

    
//    public void drawALine() {
//        Mat image = Imgcodecs.imread(".\\src\\com\\edu\\sliit\\img\\dilation.jpg", 3);   
//            Point pt1 = new Point(20, 0);
//            Point pt2 = new Point(20, 179);
//            Imgproc.line(image, pt1, pt2, new Scalar(0, 255, 0), 1);
//        
//        Imgcodecs.imwrite(".\\src\\com\\edu\\sliit\\img\\XXX.jpg", image);
//    }
    //Best 2
    public void drawALine(ArrayList<Double> avegXYCordinates) {
        Mat image = Imgcodecs.imread(".\\src\\com\\edu\\sliit\\img\\dilation.jpg", 3);
        for (int i = 0; i < avegXYCordinates.size(); i++) {         
            Point pt1 = new Point(avegXYCordinates.get(i),0);
            Point pt2 = new Point(avegXYCordinates.get(i),179);
            Imgproc.line(image, pt1, pt2, new Scalar(0, 255, 0), 1);
        }
        Imgcodecs.imwrite(".\\src\\com\\edu\\sliit\\img\\XCordinates1.5.jpg", image);
    }
    
    
    
    
//    public void drawALine(ArrayList<Double> avegXYCordinates) {
//        Mat image = Imgcodecs.imread(".\\src\\com\\edu\\sliit\\img\\dilation.jpg", 3);
//        for (int i = 0; i < avegXYCordinates.size(); i++) {         
//            Point pt1 = new Point(0, avegXYCordinates.get(i));
//            Point pt2 = new Point(231, avegXYCordinates.get(i));
//            Imgproc.line(image, pt1, pt2, new Scalar(0, 255, 0), 1);
//        }
//        Imgcodecs.imwrite(".\\src\\com\\edu\\sliit\\img\\YCordinates.jpg", image);
//    }
}
//X Cordinates : 293 97.0
//dilationm
