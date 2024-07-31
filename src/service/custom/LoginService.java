package service.custom;

import dto.LoginDto;
import service.SuperService;

public interface LoginService extends SuperService {
    boolean saveLogin(LoginDto loginDto) throws Exception;
    LoginDto getLogin(String userName) throws Exception;
}


