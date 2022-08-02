import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class Main {

    Queue<String> waitingList;
    HashMap<String, Classes> hm;
    Set<String> users;

    public Main()
    {
        waitingList = new LinkedList<String>();
        hm = new HashMap<>();
        users = new HashSet<>();
    }

    public void addUser(String userName)
    {
        if(this.users.contains(userName))
        {
            System.out.println("User Already Exixts");
        }else{
            users.add(userName);
            System.out.println("User added " + userName);
        }
    }

    public void addClass(String type,Integer capacity,String date_string)
    {
        if(hm.containsKey(type))
        {
            System.out.println("Class of this type already created");
        }else{
            hm.put(type, new Classes(type, capacity,date_string));
            System.out.println("Class created");
        }
    }

    public void enrollUser(String userName, String classType)
    {
        if(hm.containsKey(classType))
        {
            Classes cl = hm.get(classType);
            int status = cl.enrollUser(userName);
            
            if(status == 1)
            {
                System.out.println(userName + " enrolled to class " + classType );
            }else if(status == -1)
            {
                System.out.println(userName + " is already enrolled");
            }else{
                System.out.println("Class is full ! adding " + userName +  "  to waiting list");
                waitingList.add(userName + ":" + classType);
            }

        }else{
            System.out.println("No class present with type " + classType);
        }
    }

    public void cancelEnrollment(String userName, String classType)
    {
        if(hm.containsKey(classType))
        {
            Classes cl = hm.get(classType);
            if(cl.checkUserEnrollment(userName))
            {
                if(cl.cancelClass(userName)){

                    //get the waiting list username and add it to the queue
                    System.out.println(userName + " enrollment to "+ classType + " cancelled");
                    if(waitingList.size() > 0)
                    {
                        String peek = waitingList.peek();
                     
                        String[] str = peek.split(":");
                        
                        if(classType.equals(str[1]))
                        {
                            System.out.print("Waiting list user ");
                            waitingList.remove();  
                            enrollUser(str[0], classType);
                        }
                    }
                }
            }else{
                System.out.println("User is not enrolled in this class");
            }

            
        }else{
            System.out.println("No class present with type " + classType);
        }
    }

    public static void main(String[] args)
    {
       Main obj = new Main();
       obj.addClass("dance", 2, "02/08/2022 22:00");
       obj.addClass("yoga", 1,"02/08/2022 20:00");
       obj.addClass("gym", 2,"02/08/2022 20:00");

       obj.addUser("ayush");
       obj.addUser("mansi");
       obj.addUser("shyam");
       obj.addUser("pratik");

       //enrollement
       obj.enrollUser("ayush", "dance");
       obj.enrollUser("mansi", "dance");
       obj.enrollUser("shyam", "dance");

       //cancel enrollment and check if waiting list user shyam is added or not
       obj.cancelEnrollment("ayush", "dance");
       obj.enrollUser("ayush", "dance");

    }
    
}
