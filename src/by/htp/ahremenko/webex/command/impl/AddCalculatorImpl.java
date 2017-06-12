package by.htp.ahremenko.webex.command.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import by.htp.ahremenko.domain.GameCrossesZeros;
//import by.htp.ahremenko.logic.Calculator;
import by.htp.ahremenko.webex.command.Command;

//import by.htp.ahremenko.logic.Calculator;

public class AddCalculatorImpl implements Command {
 
	private static final String executeClassName = "by.htp.ahremenko.logic.Calculator";

	@Override
	public String execute(String request, GameCrossesZeros theGame) {
		String[] params;
		params = request.split("\\s+");
		//System.out.println(params[1]+"-"+params[2]);
		
		//Calculator calc = new Calculator();
		
		try {
			Class exeClass = Class.forName(executeClassName);
			//Method method = exeClass.getMethod(params[0], Class<?> );
			Method method = exeClass.getMethod(params[0].toLowerCase(), int.class, int.class);
			//System.out.println(params[0].toLowerCase()+" " + );
			Object[] methParams = {Integer.parseInt(params[1]), Integer.parseInt(params[2])};
			
			Object result = method.invoke(exeClass, methParams);
			
			return result.toString();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//Integer res = Integer.parseInt(params[1]) + Integer.parseInt(params[2]);
		return null;
	}
	

}
