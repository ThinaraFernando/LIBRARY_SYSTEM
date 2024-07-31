package service;

import service.custom.impl.BookCategoryServiceImpl;
import service.custom.impl.BookServiceImpl;
import service.custom.impl.DefaultServiceImpl;
import service.custom.impl.IssueBookServiceImpl;
import service.custom.impl.LoginServiceImpl;
import service.custom.impl.MemberServiceImpl;
import service.custom.impl.ReturnBookServiceImpl;


public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {

    }

   

    public static ServiceFactory getInstance() {
        return (serviceFactory == null) ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    @SuppressWarnings("unchecked")
    public <T extends SuperService> T getService(ServiceType serviceType) {
        switch (serviceType) {
            case BookCategory:
                return (T) new BookCategoryServiceImpl();
            case Books:
                return (T) new BookServiceImpl();
            case Members:
                return (T) new MemberServiceImpl();
            case IssueBooks:
                return (T) new IssueBookServiceImpl();
            case ReturnBook:
                return (T) new ReturnBookServiceImpl();
            case LOGIN:
                return (T) new LoginServiceImpl();
            case DEFAULT:
                return (T) new DefaultServiceImpl();
        
            
            
            default:
                return null;
        }
    }

   
}
