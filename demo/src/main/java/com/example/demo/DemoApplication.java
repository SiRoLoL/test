package com.example.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DemoApplication {
	public static String print(String input)
	{
		// Transform input into result string
		String result = "";
		int currentIndents = -1; // Current indentation level
		for (int i = 0; i < input.length(); i++)
		{
			char currentChar;
			switch(currentChar = input.charAt(i))
			{
				case '{':
					currentIndents++; // Increase indentation
					break;
				case '}':
					currentIndents--; // Decrease indentation
					break;
				case '"':
					i++;
					result += "..".repeat(currentIndents) + (currentIndents == 0 ? "" : " "); // Add indentation before property name;
					// Add property name to result string
					while ((currentChar = input.charAt(i)) != '"')
					{
						result += currentChar;
						i++;
					}
					result += '\n'; 
					break;
				default:
					break;
			}
		}

		return result;
	}

	public static String findMax(String input)
	{
		//Find max value
		int maxValue = 0;
		String maxProperties = null; // Path to max value
		Stack<String> stack = new Stack<>(); // Stack for property names

		for (int i = 0; i < input.length(); i++)
		{
			char currentChar;
			switch(currentChar = input.charAt(i))
			{
				case '}', ',':
					stack.pop(); // Remove a property name from the stack
					break;
				// Reading property name:
				case '"':
					i++;

					// Read all letters and combine into one word
					String currentWord = "";
					while ((currentChar = input.charAt(i)) != '"')
					{
						currentWord += currentChar;
						i++;
					}

					stack.push(currentWord); // Add property name to the stack
					break;
				// Reading property value:
				case ':':
					i += 2;

					// Read all numbers and combine into one
					int currentValue = 0;
					while (((currentChar = input.charAt(i)) - '0') < 10 && (currentChar - '0' >= 0))
					{
						currentValue *= 10;
						currentValue += Character.getNumericValue(currentChar);
						i++;
					}
					
					// Set new max value if it's greater than previous max value
					if (currentValue > maxValue)
					{
						List<String> propertyList = new ArrayList<>(stack);
						String propertyString = String.join(" -> ", propertyList);
						maxValue = currentValue;
						maxProperties = propertyString;
					}

					if (currentChar == '}' || currentChar == ',')
					{
						stack.pop(); // Remove a property name from the stack
					}
				default:
					break;
			}
		}

		String result = maxProperties + ": " + maxValue;
		return result;
	}

	private static String read()
	{
		String input = "";
		try
		{
			// Read input from json file
			BufferedReader reader = new BufferedReader(new FileReader("products.json"));

			String temp;
			while ((temp = reader.readLine()) != null)
			{
				input += temp; // Newlines are not necessary, so they will not be added
			}
			reader.close();
		}
		catch (FileNotFoundException e) {}
		catch (IOException e) {}

		return input;
	}

	public static void main(String[] args) {
		String input = read();
		System.out.println(print(input));
		System.out.println(findMax(input));
	}

}
