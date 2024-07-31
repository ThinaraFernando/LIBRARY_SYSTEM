package service.custom;

import service.SuperService;

public interface DefaultService extends SuperService {

    int getTotalBooks() throws Exception;

    int getTotalIssuedBooks() throws Exception;

    int getTotalMembers() throws Exception;


    int getTotalReturnBooks() throws Exception;

}
