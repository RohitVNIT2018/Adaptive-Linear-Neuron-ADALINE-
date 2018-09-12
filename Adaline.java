/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentsct;

/**
 *
 * @author Rohit
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.math.*;
import java.text.DecimalFormat;

public class Adaline {
    static double LearningRate=0.1;

    public static void main(String[] a)
    {
        //Reading dataset values
        String path="F:/SCT/data/t1.csv";
        BufferedReader br=null;
        String delimeter=",";
        String line="";
        String[] row;
        double y,x1,x2,x3,x4=1,output;
        double error,accuracy;
        double meanerror=1000,sumerror=0,sqerror=0;
        
        
        double[] wts=new double[4];
      
        wts[0]=0.2;
        wts[1]=0.2;
        wts[2]=0.2;
        wts[3]= 1;
        int count;
        int epoch=1;
        
        System.out.println("====Equation====");
        System.out.println(wts[0]+"* X1 + "+wts[1]+"* X2 + "+wts[2]+"* X3 + "+wts[3]+"* X4");
        
        try{
            
            while(epoch<=1000){
            br=new BufferedReader(new FileReader(path));
            count=0;
            while((line=br.readLine())!=null)
            {
                count++;
                row=line.split(delimeter);
                x1=Integer.parseInt(row[0]);
                x2=Integer.parseInt(row[1]);
                x3=Integer.parseInt(row[2]);
                y=Integer.parseInt(row[3]);
                if(y==2)
                    y=0;
                
                output=calculate(wts,x1,x2,x3,x4);
                error=y-output;
                
                System.out.println("Target Output:"+y+"  Output Found:"+output+"  error :"+error);
                
                
                wts[0]+=LearningRate*error*x1;
                wts[1]+=LearningRate*error*x2;
                wts[2]+=LearningRate*error*x3;
                wts[3]+=LearningRate*error*x4;
                
                sqerror=0.5*error*error;
                sumerror+=sqerror;
                
            }//Training Ended
                if(meanerror>sumerror)
                    meanerror=(sumerror/count);
                
                System.out.println("Mean error found is :"+meanerror);
                if(meanerror<=0.03)
                    break;
                
            }//200 epoch completed
        }
        catch(Exception e){
            System.out.println("Oops..!");
        }

        System.out.println("====Equation obtained====");
        System.out.println(wts[0]+"* X1 + "+wts[1]+"* X2 + "+wts[2]+"* X3 + "+wts[3]+"* X4");
        
        
        
        
        //======Testing over dataset
        int missclassified=0;
        int total=0;
        
        path="F:/SCT/data/Dataset.csv";
        
        try{
            br=new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null)
            {
                total++;
                row=line.split(delimeter);
                x1=Integer.parseInt(row[0]);
                x2=Integer.parseInt(row[1]);
                x3=Integer.parseInt(row[2]);
                y=Integer.parseInt(row[3]);
                if(y==2)
                    y=0;
                
                output=classify(wts,x1,x2,x3,x4);
                if(output != y)
                    missclassified++;
                
                //System.out.println("Target Class:"+y+"  Classified as:"+output);
 
            }
        }
        catch(Exception e){
            System.out.println("Oops..!");
        }
        
        System.out.println("====Performance Measures====");
        System.out.println("Total instances Tested :"+total);
        System.out.println("Total instances Missclassified :"+missclassified);
        accuracy =((total-missclassified)/(total*1.0))*100;
        System.out.println("Hence, Accuracy of Classifier :"+accuracy);
        
    }//main method ends
    static double calculate( double weights[], double x, double y, double z,double a)
	{
		double sum = x * weights[0] + y * weights[1] + z * weights[2] + weights[3]*a;
		return (sum >= 0) ? 1 : 0;
                //return sum;
	}
    
    static double classify( double weights[], double x, double y, double z,double a)
	{
		double sum = x * weights[0] + y * weights[1] + z * weights[2] + weights[3]*a;
		return (sum >= 0) ? 1 : 0;
                
	}

}
