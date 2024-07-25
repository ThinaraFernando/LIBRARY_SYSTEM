package service;

import service.custom.impl.BookCategoryServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {

    }

   

    public static ServiceFactory getInstance() {
        return (serviceFactory == null) ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    @SuppressWarnings("unchecked")
    public <T extends SuperService> T getBO(ServiceType boType) {
        switch (boType) {
            case BookCategory:
                return (T) new BookCategoryServiceImpl();
            default:
                return null;
        }
    }

   
}
