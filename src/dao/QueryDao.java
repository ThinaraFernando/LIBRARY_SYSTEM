package dao;


public interface QueryDao extends SuperDao {

    int getTotalBooks() throws Exception;

    int getTotalIssuedBooks() throws Exception;

    int getTotalMembers() throws Exception;
    
    int getTotalReturnBooks() throws Exception;
   

}
