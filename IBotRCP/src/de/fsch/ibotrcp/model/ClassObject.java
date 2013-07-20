/**
 * 
 */
package de.fsch.ibotrcp.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.eclipse.jdt.core.IField;

/**
 * @author Frank Schaare
 * @version 1.0 vom 04.10.2007
 *
 *	Ein Klassenobject zur Vorlage für den EditAssetView
 * @param <T>
 */
public class ClassObject 
{
	public String test;
private Object classObject = null;
private String name = null;
private String simpleName = null;
private String canonicalName = null;
private Constructor[] constructors = null;
private Constructor defaultConstructor = null;
private ArrayList<Field> fields = new ArrayList<Field>();
private ArrayList<Method> methods = new ArrayList<Method>();
private ArrayList<Method> getterMethods = new ArrayList<Method>();
private ArrayList<Method> setterMethods = new ArrayList<Method>();
private ArrayList<Method> otherMethods = new ArrayList<Method>();

	/**
	 * 
	 */
	public ClassObject() 
	{
	Class c = ClassObject.class;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCanonicalName() {
		return canonicalName;
	}

	public void setCanonicalName(String canonicalName) {
		this.canonicalName = canonicalName;
	}

	public ArrayList<Method> getMethods() {
		return methods;
	}

	public void setMethods(Method[] m) 
	{
		for (int i = 0; i < m.length; i++) 
		{
		Method method = m[i];
		
		methods.add(method);
			if (method.getName().startsWith("set")) 
			{
			setterMethods.add(method);	
			} 
			else if (method.getName().startsWith("get")) 
			{
			getterMethods.add(method);	
			} 
			else
			{
			otherMethods.add(method);	
			}
		}
	setFields();
	}

	private void setFields() 
	{
		for (Method	method : getterMethods) 
		{
		String name = method.getName();
		name = name.substring(3);
		name = name.toLowerCase();
				
		Field field = new Field();
		field.setElementName(name);
		field.setElementType(method.getGenericReturnType());
		field.setParent(this.simpleName);
		
		boolean writable = false;
		String setter = "set" + name.substring(0,1).toUpperCase();
		setter = setter + name.substring(1, name.length());

			for (Method	m : setterMethods) 
			{
				if (m.getName().equalsIgnoreCase(setter)) 
				{
				writable = true;	
				}
			}
		field.setReadOnly(writable);
		
		this.fields.add(field);
		}
		
	}

	public ArrayList<Method> getGetterMethods() {
		return getterMethods;
	}

	public void setGetterMethods(ArrayList<Method> getterMethods) {
		this.getterMethods = getterMethods;
	}

	public ArrayList<Method> getSetterMethods() {
		return setterMethods;
	}

	public void setSetterMethods(ArrayList<Method> setterMethods) {
		this.setterMethods = setterMethods;
	}

	public ArrayList<Method> getOtherMethods() {
		return otherMethods;
	}

	public void setOtherMethods(ArrayList<Method> otherMethods) {
		this.otherMethods = otherMethods;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public Object getClassObject() {
		return classObject;
	}

	public void setClassObject(Object classObject) 
	{
	this.classObject = classObject;
	}

	public Constructor[] getConstructors() {
		return constructors;
	}

	public void setConstructors(Constructor[] constructors) 
	{
	this.constructors = constructors;
	this.defaultConstructor = constructors[0];
	}



}
