package service.custom;

import java.util.ArrayList;

import dto.MemberDto;

import service.SuperService;

public interface MemberService extends SuperService {

    public boolean add(MemberDto dto) throws Exception;

    public boolean delete(String id) throws Exception;

    public boolean update(MemberDto dto) throws Exception;

    public MemberDto get(String id) throws Exception;

    public ArrayList<MemberDto> getAll() throws Exception;

}
