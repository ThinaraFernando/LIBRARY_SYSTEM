package service;

import service.custom.impl.BookCategoryServiceImpl;
import service.custom.impl.BookServiceImpl;
import service.custom.impl.MemberServiceImpl;


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
            
            
            default:
                return null;
        }
    }

   
}
