package services;

import com.sun.istack.internal.NotNull;
import edu.umflix.authenticationhandler.AuthenticationHandler;
import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import edu.umflix.model.*;
import edu.umflix.persistence.ClipDao;
import edu.umflix.persistence.ClipDataDao;
import edu.umflix.persistence.RoleDao;
import model.MovieManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MovieManagerImpl implements MovieManager {

    @EJB(beanName = "ClipDataDaoImpl")
    ClipDataDao clipDataDao;

    @EJB(beanName = "ClipDaoImpl")
    ClipDao clipDao;

    @EJB(beanName = "RoleDaoImpl")
    RoleDao roleDao;

    @EJB(beanName = "AuthenticationHandlerImpl")
    AuthenticationHandler authenticationHandler;

    public List<Clip> getMovie(@NotNull String userToken, @NotNull Long movieId) throws InvalidTokenException{
        if(validateUser(userToken))
            return clipDao.getClips(movieId);
        else
            throw new IllegalArgumentException("User does not exist");
    }

    public ClipData getClipData(@NotNull String userToken, @NotNull Long clipId) throws InvalidTokenException{
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void sendActivity(@NotNull String userToken, @NotNull Activity activity) throws InvalidTokenException{
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public ClipData getAd(@NotNull String userToken, @NotNull Long movieId) throws InvalidTokenException{
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private boolean validateUser(String userToken) throws InvalidTokenException {
        Role admin = roleDao.getRole((long)1);
        Role publisher = roleDao.getRole((long)2);
        Role viewer = roleDao.getRole((long)3);
        Role reviewer = roleDao.getRole((long)4);
        List<Role> roles = new ArrayList<Role>();
        roles.add(admin);
        roles.add(publisher);
        roles.add(viewer);
        roles.add(reviewer);
        return authenticationHandler.validateToken(userToken, roles);
    }

    public void setClipDataDao(ClipDataDao clipDataDao) {
        this.clipDataDao = clipDataDao;
    }

    public void setClipDao(ClipDao clipDao) {
        this.clipDao = clipDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setAuthenticationHandler(AuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }
}
