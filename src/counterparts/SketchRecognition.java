/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counterparts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class SketchRecognition {

    private Mat image;
    private Mat originalImage;
    private List<MatOfPoint> contours;
    private Mat hierarchy;
    private int HEIGHT;
    private int WIDTH;
    private List<Rect> rects = new ArrayList<Rect>();
    private Mat imgGrayscale;
    private Random rng = new Random(12345);
    
    public SketchRecognition(Mat input) {
        this.originalImage = input;
        this.image = new Mat();
        this.hierarchy = new Mat();
        this.contours = new ArrayList<MatOfPoint>();
        this.HEIGHT = input.height();
        this.WIDTH = input.width();
        this.imgGrayscale = new Mat();
    }

    private void setFilter() {
        //Apply gaussian blur to remove noise
       
        Imgproc.cvtColor(originalImage, imgGrayscale, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(imgGrayscale, imgGrayscale, new Size(3, 3), 0);
        Imgproc.adaptiveThreshold(imgGrayscale, imgGrayscale, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 5, 4);
        Imgproc.medianBlur(imgGrayscale, imgGrayscale, 3);
        Imgproc.threshold(imgGrayscale, imgGrayscale, 0, 255, Imgproc.THRESH_OTSU);
        Imgproc.GaussianBlur(imgGrayscale, imgGrayscale, new Size(3, 3), 0);
        Imgproc.threshold(imgGrayscale, imgGrayscale, 0, 255, Imgproc.THRESH_OTSU);

        //Threshold
        // Imgproc.adaptiveThreshold(image, image, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 7, 1);

        //Invert the image
         //Core.bitwise_not(imgGrayscale, imgGrayscale);
         //Core.bitwise_not(imgGrayscale, imgGrayscale);
        //Dilate
         double dilation_size = 0.8; 
         Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(2*dilation_size + 1, 2*dilation_size+1));
         
        // Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_DILATE, new Size(3, 3), new Point(1, 1));
         Imgproc.dilate(imgGrayscale, imgGrayscale, element1);

        //print image
        // Imgcodecs.imwrite(".\\src\\com\\edu\\sliit\\img\\dilation6.jpg", imgGrayscale);
    }

    public List<MatOfPoint> findRectangle() {
        //   Imgproc.cvtColor(originalImage, image, Imgproc.COLOR_BGR2GRAY);
        setFilter();
        this.rects.clear();
        //Find Contours
        Imgproc.findContours(imgGrayscale, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));

        MatOfPoint2f[] contoursPoly  = new MatOfPoint2f[contours.size()];
        Rect[] boundRect = new Rect[contours.size()];
        Point[] centers = new Point[contours.size()];
        float[][] radius = new float[contours.size()][1];
        for (int i = 0; i < contours.size(); i++) {
            contoursPoly[i] = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contoursPoly[i], 3, true);
            boundRect[i] = Imgproc.boundingRect(new MatOfPoint(contoursPoly[i].toArray()));
            centers[i] = new Point();
            Imgproc.minEnclosingCircle(contoursPoly[i], centers[i], radius[i]);
        }
        Mat drawing = Mat.zeros(imgGrayscale.size(), CvType.CV_8UC3);
        List<MatOfPoint> contoursPolyList = new ArrayList<>(contoursPoly.length);
        for (MatOfPoint2f poly : contoursPoly) {
            contoursPolyList.add(new MatOfPoint(poly.toArray()));
        }
        for (int i = 0; i < contours.size(); i++) {
            Scalar color = new Scalar(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
            Imgproc.drawContours(drawing, contoursPolyList, i, color);
            Imgproc.rectangle(drawing, boundRect[i].tl(), boundRect[i].br(), color, 2);
            Imgproc.circle(drawing, centers[i], (int) radius[i][0], color, 2);
        }
       Imgcodecs.imwrite(".\\src\\com\\edu\\sliit\\img\\hmmm.jpg", drawing);
        
        System.out.println("*********************");
        
        
        
        
        
        
        
        
        
        
        
        List<MatOfPoint> ret = new ArrayList<>();
        for (MatOfPoint cnt : contours) {
            //System.out.println("Imgproc.contourArea(cnt) : "+Imgproc.contourArea(cnt));
            if (Imgproc.contourArea(cnt) > 1) {
                ret.add(cnt);
            }
        }
         return ret;
    }
}
