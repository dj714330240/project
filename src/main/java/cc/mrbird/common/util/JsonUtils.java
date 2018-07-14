/*
 * Copyright 2015-2023 sundy.net. All rights reserved.
* Support: http://www.3dfield.net
 * License: http://www.3dfield.net/license
 */
package cc.mrbird.common.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Assert;

import java.io.*;

/**
 * Utils - JSON
 * 
 * @author Administrator
 * @version 3.0
 */
public final class JsonUtils {

	/** ObjectMapper */
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 不可实例化
	 */
	private JsonUtils() {
	}

	/**
	 * 将对象转换为JSON字符串
	 * 
	 * @param value
	 *            对象
	 * @return JSOn字符串
	 */
	public static String toJson(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param valueType
	 *            对象类型
	 * @return 对象
	 */
	public static <T> T toObject(String json, Class<T> valueType) {
		Assert.hasText(json);
		Assert.notNull(valueType);
		try {
			return mapper.readValue(json, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param typeReference
	 *            对象类型
	 * @return 对象
	 */
	public static <T> T toObject(String json, TypeReference<?> typeReference) {
		Assert.hasText(json);
		Assert.notNull(typeReference);
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(json, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param javaType
	 *            对象类型
	 * @return 对象
	 */
	public static <T> T toObject(String json, JavaType javaType) {
		Assert.hasText(json);
		Assert.notNull(javaType);
		try {
			return mapper.readValue(json, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将对象转换为JSON流
	 * 
	 * @param writer
	 *            writer
	 * @param value
	 *            对象
	 */
	public static void writeValue(Writer writer, Object value) {
		try {
			mapper.writeValue(writer, value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void writeValue(File resultFile, Object value) {
		try {
			if(!resultFile.getParentFile().exists()){
				resultFile.getParentFile().mkdirs();
			}
			if(!resultFile.exists()){
				resultFile.createNewFile();
			}
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
			mapper.writeValue(resultFile,value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToJson(File file,JSONObject object) throws IOException
	{
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			if(!file.exists()){
				file.createNewFile();
			}
			String string = object.toString();
	        Writer write = new FileWriter(file);  
	        write.write("var json =" + string);  
	        write.flush();  
	        write.close();  
	}
	
	public static boolean writeToJsonEx(File file,JSONObject object) throws IOException
	{
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			if(!file.exists()){
				file.createNewFile();
			}
			String string = object.toString();
	        Writer write = new FileWriter(file);  
	        write.write(string);  
	        write.flush();  
	        write.close();  
	        return true;
	}
	
	public static boolean writeToJson(File file,String object) throws IOException
	{
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			if(!file.exists()){
				file.createNewFile();
			}
			String string = object;
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file),"UTF-8"); 
			 out.write(string); 
            out.flush(); 
            out.close(); 
//	        Writer write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));  
//	        write.write(string);  
//	        write.flush();  
//	        write.close();  
	        return true;
	}
}