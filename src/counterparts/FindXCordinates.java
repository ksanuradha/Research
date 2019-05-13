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
import java.util.Map;

public class FindXCordinates {
    
     ArrayList<Double> dupicates=new ArrayList<>();
     ArrayList<Double> unikeYcordinates=new ArrayList<>();
    
    public HashMap<Double, ArrayList<Double>> findAverageXCordinates(HashMap<Integer, double[]> findRectangle){
        HashMap<Double, ArrayList<Double>> xCordinates = new HashMap<Double, ArrayList<Double>>();
        Iterator hmIterator = findRectangle.entrySet().iterator();
        while (hmIterator.hasNext()) { 
            Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
            double[] cordinates = ((double[])mapElement.getValue());
            dupicates.add(cordinates[1]);
        }
        unikeYcordinates=removeDuplicates(dupicates);
        for(int i=0;i<unikeYcordinates.size();i++){
            ArrayList<Double> xcordinatesForGivent = getXcordinatesForGivent(findRectangle,unikeYcordinates.get(i));
            xCordinates.put(unikeYcordinates.get(i), xcordinatesForGivent);
        }
        return xCordinates;
    }
    
    //Remove Duplicates
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) 
    { 
        // Create a new ArrayList 
        ArrayList<T> newList = new ArrayList<T>(); 
  
        // Traverse through the first list 
        for (T element : list) { 
  
            // If this element is not present in newList 
            // then add it 
            if (!newList.contains(element)) { 
  
                newList.add(element); 
            } 
        } 
  
        // return the new list 
        return newList; 
    } 
    
    private ArrayList<Double> getXcordinatesForGivent(HashMap<Integer, double[]> findRectangle,double yCordinate){
        ArrayList<Double> xCordinates=new ArrayList<>();
        Iterator hmIterator = findRectangle.entrySet().iterator();
        while (hmIterator.hasNext()) { 
            Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
            double[] cordinates = ((double[])mapElement.getValue()); 
            if(cordinates[1]==yCordinate){
                xCordinates.add(cordinates[0]);
            }
            //System.out.println(mapElement.getKey() + " : " + "Average Y Cordinate : "+cordinates[1]+" Average X Cordinate : "+cordinates[0]); 
        } 
        return xCordinates;
       // ArrayList<Integer> numbers = new ArrayList<>
    }
}
