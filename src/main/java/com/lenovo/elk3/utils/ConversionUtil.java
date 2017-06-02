package com.lenovo.elk3.utils;


public class ConversionUtil {
	public static String converToBig(double num,String unit,String[] units,double unitNum,int f){
		
		for(int i = 0;i < units.length;i++){
			if(unit.equals(units[i])){
				for(int a = i,b = 0;a >=0;a--,b++){
					if(num >= Math.pow(unitNum,a)){
						return String.format("%." + f + "f",num / Math.pow(unitNum,a)) + units[b];
					}
				}
			}
		}
		return null;
	}
}
