package by.vistal.services;

public class ConvertStrCostToInt {

    public static Integer getInteger(String costEve){
        return Integer.valueOf(costEve.substring(0,costEve.indexOf('.'))
                .concat(costEve.substring(costEve.indexOf('.')+1)));
    }
}
