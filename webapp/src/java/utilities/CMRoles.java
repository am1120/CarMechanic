package utilities;

/**
 * A class that contains user's roles
 * @author Alexander
 */
public class CMRoles {
    
    public static final int GUEST_USER = 0;
    public static final String GUEST_DESCRIPTION = "Guest User";
    
    public static final int SIMPLE_USER = 1;
    public static final String SIMPLE_DESCRIPTION = "Simple User";
    
    public static final int MODERATOR_USER = 2;
    public static final String MODERATOR_DESCRIPTION = "Moderator User";
    
    public static final int ADMINISTRATOR_USER = 3;
    public static final String ADMINISTRATOR_DESCRIPTION = "Administrator User";
    
    
    public static String getDescription(int role){
        switch (role){
            case GUEST_USER:
                return GUEST_DESCRIPTION;
            case SIMPLE_USER:
                return SIMPLE_DESCRIPTION;
            case MODERATOR_USER:
                return MODERATOR_DESCRIPTION;
            case ADMINISTRATOR_USER:
                return ADMINISTRATOR_DESCRIPTION;
            default: 
                return "Role not defined";             
                        
        }
    }
}
