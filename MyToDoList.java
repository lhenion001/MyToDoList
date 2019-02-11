package mytodolist;

import java.util.Scanner;
/**
 *
 * @author Leona Henion <LHenion@gmail.com>
 */
public class MyToDoList {


	int stackpointer = -1;
	
	Scanner kbd = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyToDoList todoList = new MyToDoList();
		todoList.Run();
	}
	public void Run()
	{		
        System.out.println("Welcome to Task Manager");

        String[] taskArray;
        int size = -1;
        while (size == -1)
        {        	
            System.out.println("Please enter a size for your Task array...");
            size = GetNumberFromUser(1, Integer.MAX_VALUE);
        }

        taskArray = new String[size];
        boolean exit = false;
        while (!exit)
        {
            PrintMenu();

            int selection = -1;
            while (selection == -1)
            {
                selection = GetNumberFromUser(1,5);                    
            }

            switch (selection)
            {
                // Add a task
                case 1:                	                   
                    try {
                    	AddTask(taskArray);     
                    } catch(Exception ex) {
                    	System.out.println(ex.getMessage());
                    }
                                       
                    break;
                // Remove a task
                case 2:
                	try {
                		RemoveTask(taskArray);
                	} catch (Exception ex) {
                		System.out.println(ex.getMessage());
                	}                	                
                    break;
                // Print task
                case 3:
                	try {
                		EditTask(taskArray);
                	} catch (Exception ex) {
                		System.out.println(ex.getMessage());
                	}                	                
                    break;
                // Exit
                case 4:
                	PrintTasks(taskArray);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    break;                        
            }
        }
        System.out.println("Goodbye");
	}
	
	public void RemoveTask(String[] taskArray) throws Exception
	{
		if(stackpointer == -1) {
			throw new Exception("No tasks to remove");
		}
		System.out.print("Enter task number to remove: ");
		int task = kbd.nextInt();
		kbd.nextLine(); // Get rid of new line
		
		if(task > stackpointer+1) {
			throw new Exception("Number is outside size of task array");
		}
		int taskIndex = (taskArray.length-1)-stackpointer+(task-1);
		int stackPointerIndex = taskArray.length-(stackpointer+1);
		
		int a = taskIndex;
		int b = taskIndex-1;
		
		while(b >= stackPointerIndex)
		{
			taskArray[a] = taskArray[b];
			a--;
			b--;
		}
		taskArray[stackPointerIndex] = null;
		stackpointer--;	
	}
	public void EditTask(String[] taskArray) throws Exception {
		  		
		if(stackpointer == -1) {
			throw new Exception("No tasks to modify");
		}
		System.out.print("Enter task number to modify: ");
		int task = kbd.nextInt();
		kbd.nextLine(); // Get rid of new line
		
		if(task > stackpointer+1) {
			throw new Exception("Number is outside size of task array");
		}
		int taskIndex = (taskArray.length-1)-stackpointer+(task-1);
		
		System.out.print("Enter the new task name: ");
		String newTask = kbd.nextLine();
		taskArray[taskIndex]= newTask;
	}
	
    public void PrintTasks(String[] taskArray)
    {
    	if(stackpointer == -1) return;
    	int taskIndex = 1; //  = stackpoint+1 - 1 (for Task 1)
    	for(int i = stackpointer; i >=0 ; i--) {
    		System.out.println(taskIndex + " : " + taskArray[taskArray.length-1-i]);
    		taskIndex++;
    	}        
    }
    public void AddTask(String[] taskArray) throws Exception
    {
         if(stackpointer == taskArray.length-1)
                throw new Exception("Task Array is full");
         
         System.out.print("Enter task: ");
         String task = kbd.nextLine();
         stackpointer++;
         int a = stackpointer;
         int b = stackpointer-1;
         
         while(b >= 0)
         {
        	 int x = taskArray.length-1-a;
        	 int y = taskArray.length-1-b;
        	 taskArray[x] = taskArray[y];
        	 a--;
        	 b--;
         }
         taskArray[taskArray.length-1] = task;
    }

    public void PrintMenu()
    {
        System.out.println("Please select an option");
        System.out.println("1) Add a Task");
        System.out.println("2) Remove a Task");
        System.out.println("3) Modify a Task");
        System.out.println("4) Print all Tasks");
        System.out.println("5) Quit");
        System.out.print(">> ");
    }

    

    private int GetNumberFromUser(int min, int max)
    {
        int number = 0;

        try
        {
            number = kbd.nextInt();   
            kbd.nextLine();
            if(number < min || number > max)
                throw new Exception("Input out of range");
        }
        catch (Exception e)
        {
            number = -1;
            System.out.println("Bad Input");
        }
        return number;
    }    
}
