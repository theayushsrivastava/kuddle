
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Classes {
    String type;
    Integer capacity,enrolled;
    Set<String> userNames;
    String dateString;
    Long ts;
    public Classes(String type, Integer capacity,String dateString)
    {
        this.type = type;
        this.capacity = capacity;
        enrolled = 0;
        this.userNames = new HashSet<>();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        java.util.Date date;
        try {
            date = df.parse(dateString);
            ts = date.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //function to enroll a user
    public int enrollUser(String userName)
    {
        if(this.capacity <= enrolled)
        {
            return 0;
        }else{
            if(userNames.contains(userName))
            {
                return -1;
            }
            userNames.add(userName);
            enrolled += 1;
            
        }
        return 1;
    }

    //cancel user subscription
    public Boolean cancelClass(String userName)
    {
        if(userNames.contains(userName))
        {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp.getTime() + " : " + ts);
            if( (ts - timestamp.getTime() ) >= 30000 )
            {
                userNames.remove(userName);
                enrolled -= 1;
                return true;
            }else{
                System.out.println("Cannot cancel class now ");
                return false;
            }
        }else{
            return false;
        }
    }

    public Boolean checkUserEnrollment(String userName)
    {
        if(userNames.contains(userName))
        {
            return true;
        }else{
            return false;
        }
    }



    
}
