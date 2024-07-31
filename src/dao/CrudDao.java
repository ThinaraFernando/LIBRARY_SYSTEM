package dao;

import java.io.Serializable;
import java.util.List;

import entity.SuperEntity;

public interface CrudDao <Entity extends SuperEntity,ID extends Serializable> extends SuperDao{

    
    public boolean add(Entity entity)throws Exception;

    public boolean delete(ID id)throws Exception;

    public boolean update(Entity entity)throws Exception;

    public Entity find(ID id)throws Exception;

    public List<Entity> findAll()throws Exception;

}
