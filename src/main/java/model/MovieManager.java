package model;

import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import edu.umflix.model.Activity;
import edu.umflix.model.ClipData;
import edu.umflix.model.Clip;

import java.util.List;

public interface MovieManager {
    public List<Clip> getMovie(String userToken, Long movieId) throws InvalidTokenException;
    public ClipData getClipData(String userToken, Long clipId) throws InvalidTokenException;
    public void sendActivity(String userToken, Activity activity) throws InvalidTokenException;
    public ClipData getAd(String userToken, Long movieId) throws InvalidTokenException;
}
