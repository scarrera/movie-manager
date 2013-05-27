
import java.lang.IllegalArgumentException;
import edu.umflix.authenticationhandler.AuthenticationHandler;
import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import edu.umflix.model.Clip;
import edu.umflix.model.Role;
import edu.umflix.persistence.ClipDao;
import edu.umflix.persistence.ClipDataDao;
import edu.umflix.persistence.RoleDao;
import model.MovieManager;
import org.junit.Test;
import services.MovieManagerImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieManagerImplTest {

    @Test
    public void testValidUserToken() throws InvalidTokenException, IllegalArgumentException {
        String userToken = "validToken";
        MovieManager movieManager = getMockManager();
        assertArrayEquals(((ArrayList<Clip>) movieManager.getMovie(userToken, (long) 0)).toArray(), new ArrayList<Clip>().toArray());
    }

    @Test(expected = InvalidTokenException.class)
    public void testInvalidUserToken() throws InvalidTokenException, IllegalArgumentException {
        String userToken = "invalidToken";
        MovieManager movieManager = getMockManager();
        movieManager.getMovie(userToken, (long) 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpiredUserToken() throws InvalidTokenException, IllegalArgumentException {
        String userToken = "expiredToken";
        MovieManager movieManager = getMockManager();
        movieManager.getMovie(userToken, (long) 0);
    }

    @Test
    public void testGetMovie() {
    }

    @Test
    public void testGetClipData() {
    }

    @Test
    public void testSendActivity() {
    }

    @Test
    public void testGetAd() {
    }

    private MovieManager getMockManager() throws InvalidTokenException {
        ClipDataDao clipDataDao = mock(ClipDataDao.class);
        RoleDao roleDao = mock(RoleDao.class);
        ClipDao clipDao = mock(ClipDao.class);
        AuthenticationHandler authenticationHandler = mock(AuthenticationHandler.class);

        //prepare mock clipDataDao

        //prepare mock roleDao
        when(roleDao.getRole((long) 1)).thenReturn(new Role());
        when(roleDao.getRole((long) 2)).thenReturn(new Role());
        when(roleDao.getRole((long) 3)).thenReturn(new Role());
        when(roleDao.getRole((long) 4)).thenReturn(new Role());

        //prepare mock clipDao
        when(clipDao.getClips((long) 0)).thenReturn(new ArrayList<Clip>());

        //prepare mock authenticationHandler
        List<Role> validRoles = new ArrayList<Role>();
        validRoles.add(roleDao.getRole((long) 1));
        validRoles.add(roleDao.getRole((long) 2));
        validRoles.add(roleDao.getRole((long) 3));
        validRoles.add(roleDao.getRole((long) 4));

        when(authenticationHandler.validateToken("validToken", validRoles)).thenReturn(true);
        when(authenticationHandler.validateToken("expiredToken", validRoles)).thenReturn(false);
        when(authenticationHandler.validateToken("invalidToken", validRoles)).thenThrow(InvalidTokenException.class);

        return new MovieManagerImpl(clipDataDao, clipDao, roleDao, authenticationHandler);
    }
}
