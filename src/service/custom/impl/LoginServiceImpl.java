package service.custom.impl;

import dao.DaoFactory;
import dao.DaoType;
import dao.custom.LoginDao;
import dao.custom.impl.LoginDaoImpl;
import dto.LoginDto;
import entity.LoginEntity;
import service.custom.LoginService;

public class LoginServiceImpl implements LoginService {

    LoginDao dao = DaoFactory.getInstance().getDAO(DaoType.LOGIN);

   private final LoginDao loginDao = new LoginDaoImpl();

    @Override
    public boolean saveLogin(LoginDto loginDto) throws Exception {
        return loginDao.add(new LoginEntity(loginDto.getUserName(), loginDto.getPassword()));
    }

    @Override
    public LoginDto getLogin(String userName) throws Exception {
        LoginEntity entity = loginDao.find(userName);
        return (entity != null) ? new LoginDto(entity.getUserName(), entity.getPassword()) : null;
    }
}
