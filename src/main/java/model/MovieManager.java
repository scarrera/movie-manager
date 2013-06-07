package model;

import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import edu.umflix.exceptions.MovieNotFoundException;
import edu.umflix.model.Activity;
import edu.umflix.model.ClipData;
import edu.umflix.model.Clip;
import model.exceptions.NoAdsException;
import model.exceptions.ValuesInActivityException;
import model.exceptions.UserNotAllowedException;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * External interface for accessing movie clips for the UMFlix project
 */
public interface MovieManager {

    /**
     * Method that allows registered users to request movie clips for a given movie
     *
     * @param userToken the token used to authenticate the calling user
     * @param movieId   the id of the requested movie
     * @return the list of clips associated with requested movie
     * @throws InvalidTokenException   when received token is malformed
     * @throws MovieNotFoundException  when requested movie does not exist in system
     * @throws UserNotAllowedException when the user passed as a token is not allowed to interact with the received movie
     * @see Clip
     */
    public List<Clip> getMovie(String userToken, Long movieId) throws InvalidTokenException, MovieNotFoundException, UserNotAllowedException;

    /**
     * Method that allows a user to obtain the ClipData associated with a Clip object.
     *
     * @param userToken the token used to authenticate the calling user
     * @param clipId    the id of the token for which the user wishes to retrieve its ClipData object
     * @return ClipData object associated with given Clip id
     * @throws InvalidTokenException when received token is malformed
     * @throws FileNotFoundException when clip id does not correspond with any ClipData in the system
     * @see ClipData
     */
    public ClipData getClipData(String userToken, Long clipId) throws InvalidTokenException, FileNotFoundException;

    /**
     * Reports a given activiy to the UMFlix system
     *
     * @param userToken the user token used to identify the sender of the activity
     * @param activity  the activity to send
     * @throws InvalidTokenException     when the received user token is malformed
     * @throws ValuesInActivityException when the values set in the activity are missing or erroneous
     * @throws UserNotAllowedException   when the user set in the activity is not allowed to interact with the movie set in the activity
     * @see Activity
     */
    public void sendActivity(String userToken, Activity activity) throws InvalidTokenException, ValuesInActivityException, UserNotAllowedException;

    /**
     * Method that allows a user to obtain an Ad's ClipData while playing a movie.
     *
     * @param userToken the user token used to identify the calling user
     * @param movieId   the movie for which the Ad is supposed to be played in
     * @return the ClipData associated with the Ad object
     * @throws InvalidTokenException when the received token is malformed
     * @throws NoAdsException        when no Ads are available in the system
     */
    public ClipData getAd(String userToken, Long movieId) throws InvalidTokenException, NoAdsException;
}
