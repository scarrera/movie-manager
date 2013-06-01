package model;

import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import edu.umflix.exceptions.ClipNotFoundException;
import edu.umflix.exceptions.MovieNotFoundException;
import edu.umflix.model.Activity;
import edu.umflix.model.ClipData;
import edu.umflix.model.Clip;
import model.exceptions.ValuesInActivityException;
import model.exceptions.UserNotAllowedException;

import java.util.List;

/**
 * External interface for accessing movie clips for the UMFlix project
 */
public interface MovieManager {

    /**
     * Method that allows registered users to request movie clips for a given movie
     * @param userToken the token used to authenticate the calling user
     * @param movieId  the id of the requested movie
     * @return the list of clips associated with requested movie
     * @throws InvalidTokenException when received token is malformed
     * @throws MovieNotFoundException when requested movie does not exist in system
     * @throws UserNotAllowedException when the user passed as a token is not allowed to interact with the received movie
     * @see Clip
     */
    public List<Clip> getMovie(String userToken, Long movieId) throws InvalidTokenException, MovieNotFoundException, UserNotAllowedException;


    public ClipData getClipData(String userToken, Long clipId) throws InvalidTokenException, ClipNotFoundException;

    /**
     * Reports a given activiy to the UMFlix system
     * @param userToken the user token used to identify the sender of the activity
     * @param activity  the activity to send
     * @throws InvalidTokenException when the received user token is malformed
     * @throws ValuesInActivityException when the values set in the activity are missing or erroneous
     * @throws UserNotAllowedException when the user set in the activity is not allowed to interact with the movie set in the activity
     * @see Activity
     */
    public void sendActivity(String userToken, Activity activity) throws InvalidTokenException, ValuesInActivityException, UserNotAllowedException;
    public ClipData getAd(String userToken, Long movieId) throws InvalidTokenException;
}
