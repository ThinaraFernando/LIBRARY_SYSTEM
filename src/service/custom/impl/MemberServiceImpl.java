package service.custom.impl;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.DaoType;
import dao.custom.MemberDao;
import dto.MemberDto;
import entity.MemberEntity;
import service.custom.MemberService;

public class MemberServiceImpl implements MemberService {

        private final MemberDao memberDao = DaoFactory.getInstance().getDAO(DaoType.Members);


    @Override
    public boolean add(MemberDto dto) throws Exception {
        MemberEntity entity = new MemberEntity(dto.getMemberId(), dto.getName(), dto.getAddress(), dto.getPhone());
        return memberDao.add(entity);    }

    @Override
    public boolean delete(String id) throws Exception {
        return memberDao.delete(id);    
    }

    @Override
    public boolean update(MemberDto dto) throws Exception {
        MemberEntity entity = new MemberEntity(dto.getMemberId(), dto.getName(), dto.getAddress(), dto.getPhone());
        return memberDao.update(entity);   
     }

    @Override
    public MemberDto get(String id) throws Exception {
        MemberEntity entity = memberDao.getMemberEntity(id);
        if (entity != null) {
            return new MemberDto(entity.getMemberId(), entity.getName(), entity.getAddress(), entity.getPhone());
        }
        return null;   
     }

    @Override
    public ArrayList<MemberDto> getAll() throws Exception {
        ArrayList<MemberEntity> entities = memberDao.getAll();
        ArrayList<MemberDto> dtoList = new ArrayList<>();
        for (MemberEntity entity : entities) {
            dtoList.add(new MemberDto(entity.getMemberId(), entity.getName(), entity.getAddress(), entity.getPhone()));
        }
        return dtoList;
    }  
  }


