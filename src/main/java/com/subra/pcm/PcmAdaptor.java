package com.subra.pcm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.portal.pcm.FList;
import com.portal.pcm.Poid;
import com.portal.pcm.SparseArray;
import com.subra.antlr.gen.JSONParser.ValueContext;
import com.subra.antlr.listner.JsonTooFlist;

public class PcmAdaptor {

	public static void updateFlistWithNewFields(FList flist,String fldName,ValueContext val,JsonTooFlist ref){
		Class<?> fldClass = getFieldClass(fldName);
		Method fldGetInstMethod = getInstMethod(fldClass);
		Class<?> fldType = getFldType(fldClass);
		Method flistSetMethod = getFlistSetMethod(flist,fldClass,fldType);
		Object typeConvertedVal=strToTargetObj(fldType,val,ref);
		setFlistWithField( flist, fldGetInstMethod, flistSetMethod, fldClass, fldType, typeConvertedVal);		
	}
	
	private static Poid strToPoid(Object val){
		String strPoid = JsonTooFlist.stripQuotes(((String)val));
		String poid[] = strPoid.split(" ");
		int db=0;
		int id = 0;
		String type = null;
		int revision = 0;
		
		if(poid.length==3){
			db = Integer.parseInt(poid[0]);
			id = Integer.parseInt(poid[1]);
			type = poid[2];
			
		}else if(poid.length==4){
			db = Integer.parseInt(poid[0]);
			id = Integer.parseInt(poid[1]);
			type = poid[2];
			revision = Integer.parseInt(poid[3]);
		}else{
			throw new IllegalArgumentException("Invalid poid");
		}			
		Poid poidObj = new Poid(db,id,type,revision);
		return poidObj;
	}
	public static void setFlistWithField(FList flist,Method fldGetInstMethod,Method flistSetMethod,Class<?> fldClass,Class<?> fldType,Object val){
		Object fldInstance = null;
		try {
			fldInstance = fldGetInstMethod.invoke(null);
			flistSetMethod.invoke(flist, fldClass.cast(fldInstance),fldType.cast(val));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Class<?> getFieldClass(String fldName){
		Class<?> clazz = null;
		try {
			String camelCasefldName = Character.toUpperCase(fldName.charAt(0)) + fldName.substring(1);
			String fldNameWithPackage = "com.portal.pcm.fields."+camelCasefldName;			
			clazz = Class.forName(fldNameWithPackage);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clazz;
	}

	public static Method getInstMethod(Class<?> clazz){
		Method method=null;
		try {
			method = clazz.getMethod("getInst");
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return method;
	}
	
	public static Method getFlistSetMethod(FList flist,Class<?> fldClazz,Class<?> fldType){
		Method method=null;
		try {
			method = flist.getClass().getMethod("set",fldClazz.getSuperclass(),fldType);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return method;
	}
	
	
	public static Class<?> getFldType(Class<?> clazz){
		String superClass = clazz.getSuperclass().getName();
		//System.out.println("Ther super class = "+superClass);
		
		if(superClass.contains("com.portal.pcm.PoidField")){
			System.out.println("Value needs to be converted from string to Poid");
			return Poid.class;
		}else if(superClass.contains("com.portal.pcm.StrField")){
			return String.class;
		}else if(superClass.contains("com.portal.pcm.IntField")){
			return Integer.class;
		}else if(superClass.contains("com.portal.pcm.ArrayField")){
			return SparseArray.class;
		}else if(superClass.contains("com.portal.pcm.SubStructField")){
			return FList.class;
		}
		else{
			return null;
		}
	}
	private static Object strToTargetObj(Class<?> fldType,ValueContext val,JsonTooFlist ref){
		Object typeConvertedVal=null;
		if(fldType.getName().equals("com.portal.pcm.Poid")){
			typeConvertedVal=strToPoid(val.getText());
		}else if(fldType.getName().equals("java.lang.Integer")){
			typeConvertedVal = Integer.parseInt(val.getText());
		}
		else if(fldType.getName().equals("java.lang.String")){
			typeConvertedVal=JsonTooFlist.stripQuotes(val.getText());
		}else if(fldType.getName().equals("java.lang.SubStructField")){
			FList flist = ref.flistPtrs.get(val);
			typeConvertedVal=flist;
		}else if(fldType.getName().equals("com.portal.pcm.SparseArray")){
			SparseArray sparseArray = ref.sparseArrayptrs.get(val);
			if(null == sparseArray){
				System.out.println("Sparse tree cant be null over here");
				throw new IllegalStateException("sparseArray cant be null");
			}
			typeConvertedVal=sparseArray;
		}else if(fldType.getName().equals("com.portal.pcm.FList")){
			FList flist = ref.flistPtrs.get(val);
			if(flist == null){
				System.out.println("flist cant be null over here");
				throw new IllegalStateException("flist cant be null over here");
			}
			typeConvertedVal=flist;
		}
		else{
			throw new IllegalArgumentException(fldType.getName()+" type not yet supported");
		}
		return typeConvertedVal;
	}
}
