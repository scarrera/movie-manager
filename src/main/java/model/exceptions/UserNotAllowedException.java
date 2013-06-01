package model.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Santiago
 * Date: 01/06/13
 * Time: 04:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserNotAllowedException extends Exception{

    public UserNotAllowedException(String message){
        super(message);
    }
}
