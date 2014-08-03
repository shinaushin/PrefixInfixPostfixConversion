package com.AustinJShin.conversion;
import java.util.*;

public class Convert {
	
	private ArrayList<String> list = new ArrayList<String>();
	
	public String convert(String base) 
	{
		
        String text = "";
        int[] array = count(base);
        
        for (int a = 0; a < base.length(); a++)
        {
            if (!base.substring(a,a+1).equals("0") && !base.substring(a,a+1).equals("1") && !base.substring(a,a+1).equals("2") && !base.substring(a,a+1).equals("3") && !base.substring(a,a+1).equals("4") && !base.substring(a,a+1).equals("5") && !base.substring(a,a+1).equals("6") && !base.substring(a,a+1).equals("7") && !base.substring(a,a+1).equals("8") && !base.substring(a,a+1).equals("9") && !base.substring(a,a+1).equals("+") && !base.substring(a,a+1).equals("-") && !base.substring(a,a+1).equals("/") && !base.substring(a,a+1).equals("*"))
            {
                return "Can't convert. Contains inconvertible symbol(s).";
            }
        }
       
        if ((base.substring(0,1).equals("+") || base.substring(0,1).equals("-") || base.substring(0,1).equals("*") || base.substring(0,1).equals("/")) && (base.substring(base.length()-1).equals("+") || base.substring(base.length()-1).equals("-") || base.substring(base.length()-1).equals("*") || base.substring(base.length()-1).equals("/")))
        {
            return "Can't convert. This is not prefix or postfix. Try again.";
        } else if (array[0] != array[1] + 1)
        {
        	text += "Can't convert. ";
            if (array[0] < array[1] + 1)
            {
               text += "Not enough numbers. Try again.";
            } else { text += "Not enough operation signs. Try again."; }
            return text;
        }
        
        String[] letters = new String[base.length()];
        for (int a = 0; a < letters.length; a++)
        {
            letters[a] = base.substring(a,a+1);
        }
        
        if (base.substring(base.length()-1).equals("+") || base.substring(base.length()-1).equals("-") || base.substring(base.length()-1).equals("*") || base.substring(base.length()-1).equals("/"))
        {
            int countSign = 0;
            for (int a = 0; a < letters.length; a++)
            {
                int countNum = 0;
                if (letters[a].equals("+") || letters[a].equals("-") || letters[a].equals("*") || letters[a].equals("/"))
                {
                    countSign++;
                    countNum = countSign + 1;
                    int count = 0;
                    for (int b = a; b > -1; b--)
                    {
                        if (letters[b].equals("1") || letters[b].equals("2") || letters[b].equals("3") || letters[b].equals("4") || letters[b].equals("5") || letters[b].equals("6") || letters[b].equals("7") || letters[b].equals("8") || letters[b].equals("9") || letters[b].equals("0"))
                        {
                            count++;
                        }
                    }
                    if (count < countNum)
                    {
                        return "Can't convert. Misplaced sign/number.";
                    }
                }
            }
        } else {
            int countSign = 0;
            for (int a = letters.length-1; a > -1; a--)
            {
                int countNum = 0;
                if (letters[a].equals("+") || letters[a].equals("-") || letters[a].equals("*") || letters[a].equals("/"))
                {
                    countSign++;
                    countNum = countSign + 1;
                    int count = 0;
                    for (int b = letters.length-1; b > a; b--)
                    {
                        if (letters[b].equals("1") || letters[b].equals("2") || letters[b].equals("3") || letters[b].equals("4") || letters[b].equals("5") || letters[b].equals("6") || letters[b].equals("7") || letters[b].equals("8") || letters[b].equals("9") || letters[b].equals("0"))
                        {
                            count++;
                        }
                    }
                    if (count < countNum)
                    {
                        return "Can't convert. Misplaced sign/number.";
                    }
                }
            }
        }
       
        if (base.substring(base.length()-1).equals("+") || base.substring(base.length()-1).equals("-") || base.substring(base.length()-1).equals("*") || base.substring(base.length()-1).equals("/"))
        {
            list = postConvert2Infix(base);
            for (int a = 1; a < list.size()-1; a++)
            {
                text += list.get(a);
            }
            return text;
        } else if (base.substring(0,1).equals("+") || base.substring(0,1).equals("-") || base.substring(0,1).equals("*") || base.substring(0,1).equals("/"))
        {
            list = preConvert2Infix(base);
            for (int a = 1; a < list.size()-1; a++)
            {
                text += list.get(a);
            }
            return text;
        }
        return "";
    }
	
	public static int[] count(String base)
    {
        int countNum = 0;
        int countSign = 0;
        for (int a = 0; a < base.length(); a++)
		{
			if (base.substring(a,a+1).equals("1") || base.substring(a,a+1).equals("2") ||  base.substring(a,a+1).equals("3") ||  base.substring(a,a+1).equals("4") ||  base.substring(a,a+1).equals("5") ||  base.substring(a,a+1).equals("6") ||  base.substring(a,a+1).equals("7") ||  base.substring(a,a+1).equals("8") ||  base.substring(a,a+1).equals("9") ||  base.substring(a,a+1).equals("0"))
			{
				countNum++;
			} else {
				countSign++;
			}
		}
        int[] array = { countNum, countSign };
        return array;
    }
	
	public static ArrayList<String> postConvert2Infix(String base)
    {
		ArrayList<String> list = new ArrayList<String>();
        for (int a = 0; a < base.length(); a++)
        {
                list.add(base.substring(a,a+1));
        }

        for (int a = 0; a < list.size()-2; a++)
        {
                if (!list.get(a).equals("+") && !list.get(a).equals("-") && !list.get(a).equals("*") && !list.get(a).equals("/"))
                {
                        if (!list.get(a+1).equals("+") && !list.get(a+1).equals("-") && !list.get(a+1).equals("*") && !list.get(a+1).equals("/"))
                        {
                                if (list.get(a+2).equals("+") || list.get(a+2).equals("-") || list.get(a+2).equals("*") || list.get(a+2).equals("/"))
                                {
                                        String sign = list.get(a+2);
                                        list.remove(a+2);
                                        list.add(a+1, sign);
                                        list.add(a, "(");
                                        list.add(a+4, ")");
                                        a = a+4;
                                }
                        }
                }
        }

        int countP = 0;
        for (int a = 0; a < list.size(); a++)
        {
                if (list.get(a).equals("("))
                {
                        countP++;
                }

                if (list.get(a).equals(")"))
                {
                        countP--;
                }
                
                if (countP == 0)
                {
                        if (list.get(a).equals("+") || list.get(a).equals("-") || list.get(a).equals("*") || list.get(a).equals("/"))
                        {

                                if (list.get(a-1).equals(")"))
                                {
                                        String sign = list.get(a);
                                        list.remove(a);
                                        list.add(a, ")");

                                        //Starting from index a-1, find index to put the sign back in
                                        int count = 0;
                                        int index = 0;
                                        for (int b = a - 1; b > -1; b--)
                                        {
                                                if (list.get(b).equals("("))
                                                {
                                                        count--;
                                                }

                                                if (list.get(b).equals(")"))
                                                {
                                                        count++;
                                                }

                                                if (count == 0)
                                                {
                                                        list.add(b, sign);
                                                        index = b;
                                                        b = -1;
                                                }
                                        }

                                        //Then starting the index right before the sign, find index to put the "("
                                        for (int b = index-1; b > -1; b--)
                                        {
                                                if (list.get(b).equals("("))
                                                {
                                                        count--;
                                                }

                                                if (list.get(b).equals(")"))
                                                {
                                                        count++;
                                                }

                                                if (count == 0)
                                                {
                                                        list.add(b, "(");
                                                        b = -1;
                                                }
                                        }
                                } else {
                                        String sign = list.get(a);
                                        list.remove(a);
                                        list.add(a-1, sign);
                                        list.add(a+1, ")");
                                        int count = 0;
                                        for (int b = a-2; b > -1; b--)
                                        {
                                                if (list.get(b).equals("("))
                                                {
                                                        count--;
                                                }

                                                if (list.get(b).equals(")"))
                                                {
                                                        count++;
                                                } 

                                                if (count == 0)
                                                {
                                                        list.add(b, "(");
                                                        b = -1;
                                                } 
                                        }
                                        a++; a++;
                                }
                        }
                } 

                //FIX
                if (a == list.size()-1)
                {
                        for (int b = 0; b < list.size()-1; b++)
                        {
                                if (list.get(b).equals("1") || list.get(b).equals("2") ||  list.get(b).equals("3") ||  list.get(b).equals("4") ||  list.get(b).equals("5") ||  list.get(b).equals("6") ||  list.get(b).equals("7") ||  list.get(b).equals("8") ||  list.get(b).equals("9") ||  list.get(b).equals("0"))
                                {
                                        if (list.get(b+1).equals("1") || list.get(b+1).equals("2") ||  list.get(b+1).equals("3") ||  list.get(b+1).equals("4") ||  list.get(b+1).equals("5") ||  list.get(b+1).equals("6") ||  list.get(b+1).equals("7") ||  list.get(b+1).equals("8") ||  list.get(b+1).equals("9") ||  list.get(b+1).equals("0"))
                                        {
                                                a = -1;
                                                countP = 0;
                                        }
                                } else if (list.get(b).equals("1") || list.get(b).equals("2") ||  list.get(b).equals("3") ||  list.get(b).equals("4") ||  list.get(b).equals("5") ||  list.get(b).equals("6") ||  list.get(b).equals("7") ||  list.get(b).equals("8") ||  list.get(b).equals("9") ||  list.get(b).equals("0"))
                                {
                                        if (list.get(b+1).equals("("))
                                        {
                                                a = -1;
                                                countP = 0;
                                        }
                                } else if (list.get(b).equals(")"))
                                {
                                        if (list.get(b+1).equals("1") || list.get(b+1).equals("2") ||  list.get(b+1).equals("3") ||  list.get(b+1).equals("4") ||  list.get(b+1).equals("5") ||  list.get(b+1).equals("6") ||  list.get(b+1).equals("7") ||  list.get(b+1).equals("8") ||  list.get(b+1).equals("9") ||  list.get(b+1).equals("0"))
                                        {
                                                a = -1;
                                                countP = 0;
                                        }
                                } else if (list.get(list.size()-1).equals("+") || list.get(list.size()-1).equals("-") || list.get(list.size()-1).equals("*") || list.get(list.size()-1).equals("/"))
                                {
                                    a = -1;
                                    countP = 0;
                                }
                        }
                }
        }
        return list;
    }
    
	public static ArrayList<String> preConvert2Infix(String base)
    {
    	ArrayList<String> list = new ArrayList<String>();
        for (int a = 0; a < base.length(); a++)
        {
                list.add(base.substring(a,a+1));
        }

        for (int a = 0; a < list.size()-2; a++)
        {
                if (list.get(a).equals("+") || list.get(a).equals("-") || list.get(a).equals("*") || list.get(a).equals("/"))
                {
                        if (!list.get(a+1).equals("+") && !list.get(a+1).equals("-") && !list.get(a+1).equals("*") && !list.get(a+1).equals("/"))
                        {
                                if (!list.get(a+2).equals("+") && !list.get(a+2).equals("-") && !list.get(a+2).equals("*") && !list.get(a+2).equals("/"))
                                {
                                        String sign = list.get(a);
                                        list.add(a+2, sign);
                                        list.add(a, "(");
                                        list.remove(a+1);
                                        list.add(a+4, ")");
                                        a = a+4;
                                }
                        }
                }
        }
		
        int countP = 0;
        for (int a = 0; a < list.size(); a++)
        {
                if (list.get(a).equals("("))
                {
                        countP++;
                }

                if (list.get(a).equals(")"))
                {
                        countP--;
                }

                if (countP == 0)
                {
                       	if (list.get(a).equals("+") || list.get(a).equals("-") || list.get(a).equals("*") || list.get(a).equals("/"))
                        {
                                if (list.get(a+1).equals("("))
                                {
                                        String sign = list.get(a);
                                        list.remove(a);
                                        list.add(a, "(");

                                        int count = 0;
                                        int index = 0;
                                        for (int b = a + 1; b < list.size(); b++)
                                        {
                                                if (list.get(b).equals("("))
                                                {
                                                        count++;
                                                }

                                                if (list.get(b).equals(")"))
                                                {
                                                        count--;
                                                }

                                                if (count == 0)
                                                {
                                                        list.add(b+1, sign);
                                                        index = b+1;
                                                        b = list.size();
                                                }
                                        }

                                        for (int b = index+1; b < list.size(); b++)
                                        {
                                                if (list.get(b).equals("("))
                                                {
                                                        count++;
                                                }

                                                if (list.get(b).equals(")"))
                                                {
                                                        count--;
                                                }

                                                if (count == 0)
                                                {
                                                        list.add(b+1, ")");
                                                        index = b + 1;
                                                        b = list.size();
                                                }
                                        }
                                        a = index;
                                } else if ((list.get(a+1).equals("1") || list.get(a+1).equals("2") ||  list.get(a+1).equals("3") ||  list.get(a+1).equals("4") ||  list.get(a+1).equals("5") ||  list.get(a+1).equals("6") ||  list.get(a+1).equals("7") ||  list.get(a+1).equals("8") ||  list.get(a+1).equals("9") ||  list.get(a+1).equals("0")) && list.get(a+2).equals("("))
                                {
                                        String sign = list.get(a);
                                        list.remove(a);
                                        list.add(a+1, sign);
                                        list.add(a, "(");
                                        int count = 0;
                                        for (int b = a+3; b < list.size(); b++)
                                        {
                                                if (list.get(b).equals("("))
                                                {
                                                        count++;
                                                }

                                                if (list.get(b).equals(")"))
                                                {
                                                        count--;
                                                } 

                                                if (count == 0)
                                                {
                                                        list.add(b+1, ")");
                                                        b = list.size();
                                                } 
                                        }
                                        a++; a++;
                                }
                        }
                }

                if (a == list.size()-1)
                {
                        for (int b = 0; b < list.size()-1; b++)
                        {
                                if (list.get(b).equals("+") || list.get(b).equals("-") ||  list.get(b).equals("*") ||  list.get(b).equals("/"))
                                {
                                        if (list.get(b+1).equals("1") || list.get(b+1).equals("2") ||  list.get(b+1).equals("3") ||  list.get(b+1).equals("4") ||  list.get(b+1).equals("5") ||  list.get(b+1).equals("6") ||  list.get(b+1).equals("7") ||  list.get(b+1).equals("8") ||  list.get(b+1).equals("9") ||  list.get(b+1).equals("0") || list.get(b+1).equals("("))
                                        {

                                        } else {a = -1; countP = 0; }
                                } 
                        }

                        if (list.get(0).equals("+") || list.get(0).equals("-") || list.get(0).equals("*") || list.get(0).equals("/"))
                            {
                                a = -1;
                                countP = 0;
                            }
                } 
        } 

        return list;
    }
    
    public String calculate(String message)
	{
		String result = convert(message);
		
		if (result.contains("+") || result.contains("-") || result.contains("/") || result.contains("*"))
		{
			for (int a = 0; a < list.size()-4; a++)
	        {
	            double d = 0;
	            
	            if (list.get(a).equals("(") && list.get(a+4).equals(")"))
	            {
	                double b = Double.parseDouble(list.get(a+1));
	                double c = Double.parseDouble(list.get(a+3));
	                double e = 0;
	                
	                if (list.get(a+2).equals("+"))
	                {
	                    d = b + c;
	                } else if (list.get(a+2).equals("-"))
	                {
	                    d = b - c;
	                } else if (list.get(a+2).equals("*"))
	                {
	                    d = b * c;
	                } else if (list.get(a+2).equals("/"))
	                {
	                    d = b/c;
	                    d = d * 100;
	                    e = (int)d;
	                    d = e/100;
	                }
	                
	                for (int f = 0; f < 5; f++)
	                {
	                    list.remove(a);
	                }
	            
	                list.add(a,d+"");
	                a = -1;
	            }
	        }
			
			return list.get(0);
		} else { return result; }
	}
}
