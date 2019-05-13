/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counterparts;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CountWhireSpots {
    public void drawALine(){
        Mat image = Imgcodecs.imread(".\\src\\com\\edu\\sliit\\img\\dilationmE2.jpg", 3);
        Point pt1 = new Point(11.5, 2);
        Point pt2 = new Point(11.5, 200);
        Imgproc.line(image, pt1, pt2, new Scalar(0,255,0), 1);
        Imgcodecs.imwrite(".\\src\\com\\edu\\sliit\\img\\Line8.jpg", image);
    }
}
