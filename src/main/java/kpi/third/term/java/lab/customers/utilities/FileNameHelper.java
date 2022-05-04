package kpi.third.term.java.lab.customers.utilities;

public class FileNameHelper {

    public static String getFileExtension(String fileName) {
        if( fileName.lastIndexOf(".") != -1 )
            return fileName.substring( fileName.lastIndexOf(".") + 1 );
        else return "";
    }


}
