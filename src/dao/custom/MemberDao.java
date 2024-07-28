package dao.custom;

import java.util.ArrayList;

import dao.SuperDao;

import entity.MemberEntity;

public interface MemberDao extends SuperDao {

    boolean add(MemberEntity entity) throws Exception;

    boolean update(MemberEntity entity) throws Exception;

    boolean delete(String code) throws Exception;

    MemberEntity getMemberEntity(String code) throws Exception;

    ArrayList<MemberEntity> getAll() throws Exception;

}
